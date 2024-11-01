package com.github.FlyBird.BetterMite.tileentity;



import com.github.FlyBird.BetterMite.block.BlockGrindStone;
import com.github.FlyBird.BetterMite.gui.grindstone.GrindStoneSlots;
import net.minecraft.*;

import java.util.Arrays;

public class TileEntityGrindStone extends TileEntity implements ISidedInventory {
    private static final int[] slots_top = new int[] { 0 };

    private static final int[] slots_bottom = new int[] { 1 };

    private static final int[] slots_sides = new int[] { 1 };

    private final GrindStoneSlots slots = new GrindStoneSlots(this);

    private final ItemStack[] items = new ItemStack[3];

    public EntityPlayer player;

    private boolean isUsing;


    public void setItem(int index, ItemStack itemStack) {
        this.items[index] = itemStack;
    }

    public int getSizeInventory() {
        return 2;
    }


    public ItemStack getItem(int index) {
        return this.items[index];
    }

    public GrindStoneSlots getSlots() {
        return this.slots;
    }

    public int[] getAccessibleSlotsFromSide(int par1) {
        return (par1 == 0) ? slots_bottom : ((par1 == 1) ? slots_top : slots_sides);
    }

    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
        return isItemValidForSlot(par1, par2ItemStack);
    }

    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
        if (par3 == 0 && par1 == 1)
            return (!(par2ItemStack.getItem() instanceof ItemNugget) && par2ItemStack.getItem() != Item.potion);
        return true;
    }

    //如果槽位上有东西  就进行xxx操作
    public void updateEntity() {
        this.slots.updateInfo();
        if (!(getWorldObj()).isRemote) {
            ItemStack[] inputStack = {this.slots.getInPutStack(0),this.slots.getInPutStack(1)};
            if (inputStack[0] != null||inputStack[1] != null)
                input(inputStack);
            ItemStack outputStack = this.slots.getOutPutStack();
            if (outputStack != null)
                output(outputStack);
        }
    }

    public void output(ItemStack outputStack) {

    }

    public void input(ItemStack[] inputStack) {

    }

    public ItemStack getStackInSlot(int i) {
        return this.items[i];
    }

    public ItemStack decrStackSize(int index, int count) {
        if (this.items[index] != null) {
            ItemStack var3;
            if ((this.items[index]).stackSize <= count) {
                var3 = this.items[index];
                this.items[index] = null;
            } else {
                var3 = this.items[index].splitStack(count);
                if ((this.items[index]).stackSize == 0)
                    this.items[index] = null;
            }
            return var3;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.items[par1] != null) {
            ItemStack var2 = this.items[par1];
            this.items[par1] = null;
            return var2;
        }
        return null;
    }

    public void setInventorySlotContents(int var1, ItemStack var2) {
        this.items[var1] = var2;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        if (player.getWorld().getBlock(this.xCoord, this.yCoord, this.zCoord) instanceof BlockGrindStone && player.getWorld().getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) instanceof TileEntityGrindStone)
            return (player.getDistance(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D);
        return false;
    }

    public void openChest() {
        this.slots.updateInfo();
        this.isUsing = true;
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.slots.readFromNBT(par1NBTTagCompound, this);
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        this.slots.writeToNBT(par1NBTTagCompound);
    }

    public void closeChest() {
        this.isUsing = false;
    }

    public boolean isItemValidForSlot(int var1, ItemStack var2) {
        return this.slots.isItemValidForSlot(var1, var2);
    }

    public void dropAllItems() {
        this.slots.dropItems(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    public void destroyInventory() {
        Arrays.fill(this.items, null);
    }
}
