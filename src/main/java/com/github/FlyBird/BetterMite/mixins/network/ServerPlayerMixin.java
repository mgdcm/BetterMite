package com.github.FlyBird.BetterMite.mixins.network;



import com.github.FlyBird.BetterMite.api.BetterMitePlayer;
import com.github.FlyBird.BetterMite.gui.grindstone.ContainerGrindStone;
import com.github.FlyBird.BetterMite.gui.grindstone.GrindStoneSlots;
import com.github.FlyBird.BetterMite.network.S2COpenWindow;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({ServerPlayer.class})
public abstract class ServerPlayerMixin extends EntityPlayer implements ICrafting, BetterMitePlayer {
    public ServerPlayerMixin(World par1World, String par2Str) {
        super(par1World, par2Str);
    }

    @Shadow
    public NetServerHandler playerNetServerHandler;

    @Shadow
    private int currentWindowId;

    @Shadow
    protected abstract void incrementWindowID();

    @Override
    public void betterMite$displayGUIGrindStone(int x, int y, int z, GrindStoneSlots slots) {
        incrementWindowID();
        TileEntity tile_entity = this.worldObj.getBlockTileEntity(x, y, z);
        this.playerNetServerHandler.sendPacketToPlayer((Packet)(new S2COpenWindow(this.currentWindowId, S2COpenWindow.EnumInventoryType.GrindStone, tile_entity.getCustomInvName(), 9, tile_entity.hasCustomName())).setCoords(x, y, z));
        this.openContainer = (Container)new ContainerGrindStone(slots, this, x, y, z);
        this.openContainer.windowId = this.currentWindowId;
        sendContainerAndContentsToPlayer(this.openContainer, ((ContainerGrindStone)this.openContainer).getInventory());
        this.openContainer.addCraftingToCrafters(this);
    }
}