package com.github.FlyBird.BetterMite.network;


import com.github.FlyBird.BetterMite.api.BetterMitePlayer;
import com.github.FlyBird.BetterMite.gui.grindstone.GrindStoneSlots;
import net.minecraft.*;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class S2COpenWindow extends Packet100OpenWindow {
    public int betterMITE_InventoryType;

    public S2COpenWindow() {}

    public S2COpenWindow(int windowId, EnumInventoryType enumInventoryType, String windowTitle, int slotsCount, boolean useProvidedWindowTitle) {
        super(windowId, 255, windowTitle, slotsCount, useProvidedWindowTitle);
        this.betterMITE_InventoryType = enumInventoryType.getType();
    }

    public boolean hasCoords() {
        return true;
    }

    public boolean hasTileEntity() {
        return hasCoords();
    }

    public void readPacketData(DataInput par1DataInput) throws IOException {
        this.windowId = par1DataInput.readByte() & 0xFF;
        this.betterMITE_InventoryType = par1DataInput.readByte() & 0xFF;
        this.windowTitle = Packet100OpenWindow.readString(par1DataInput, 32767);
        this.slotsCount = par1DataInput.readByte() & 0xFF;
        this.useProvidedWindowTitle = par1DataInput.readBoolean();
        if (hasCoords()) {
            this.x = par1DataInput.readInt();
            this.y = par1DataInput.readInt();
            this.z = par1DataInput.readInt();
        }
    }

    public void writePacketData(DataOutput par1DataOutput) throws IOException {
        par1DataOutput.writeByte(this.windowId & 0xFF);
        par1DataOutput.writeByte(this.betterMITE_InventoryType & 0xFF);
        Packet100OpenWindow.writeString(this.windowTitle, par1DataOutput);
        par1DataOutput.writeByte(this.slotsCount & 0xFF);
        par1DataOutput.writeBoolean(this.useProvidedWindowTitle);
        if (hasCoords()) {
            if (!this.has_set_coords)
                Minecraft.setErrorMessage("S2COpenWindow: coords not set for type " + this.betterMITE_InventoryType);
            par1DataOutput.writeInt(this.x);
            par1DataOutput.writeInt(this.y);
            par1DataOutput.writeInt(this.z);
        }
    }

    public void processPacket(NetHandler netHandler) {
        netHandler.handleOpenWindow(this);
    }

    public int getPacketSize() {
        int bytes = 2 + Packet.getPacketSizeOfString(this.windowTitle) + 2;
        if (hasCoords())
            bytes += 12;
        return bytes;
    }

    public void handleOpenWindow(EntityClientPlayerMP player) {
        WorldClient world = player.worldObj.getAsWorldClient();
        TileEntity tile_entity = world.getBlockTileEntity(this.x, this.y, this.z);
        if (hasTileEntity() && tile_entity == null)
            Minecraft.setErrorMessage("handleOpenWindow: no tile entity found at " + StringHelper.getCoordsAsString(this.x, this.y, this.z));
        if (this.betterMITE_InventoryType == EnumInventoryType.GrindStone.getType()) {
            ((BetterMitePlayer) player).betterMite$displayGUIGrindStone(this.x, this.y, this.z, new GrindStoneSlots(new InventoryBasic(this.windowTitle, this.useProvidedWindowTitle, this.slotsCount)));
            player.openContainer.windowId = this.windowId;
        }
        else {
            Minecraft.setErrorMessage("handleOpenWindow: type not handled " + this.betterMITE_InventoryType);
        }
    }

    public enum EnumInventoryType {
        GrindStone(0);           //常量名()   会直接调用构造器        相当于 public static final 名字=new 类名（...）
        //stoneCutter(3);

        final int type;

        //有参构造器   默认是private的
        EnumInventoryType(int type) {
            this.type = type;
        }

        public int getType() {
            return this.type;
        }
    }
}