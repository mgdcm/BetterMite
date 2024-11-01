package com.github.FlyBird.BetterMite.gui.grindstone;

import com.github.FlyBird.BetterMite.tileentity.TileEntityGrindStone;
import net.minecraft.*;


public class GrindStoneSlots extends InventoryBasic {
    public static final int slotSize = 3;

    public TileEntityGrindStone tileEntityGrindStone;

    private ContainerGrindStone containerGrindStone;

    private final Slot[] input=new Slot[2];

    private final Slot output;

    public int getSize() {
        return slotSize;
    }

    public int getInputIndex(int slotIndex) {
        return slotIndex;
    }

    public int getOutputIndex() {
        return 2;
    }

    public void updateInfo() {
        if (this.containerGrindStone != null)
            this.containerGrindStone.updateInfo();
    }

    //槽位的实例化
    public GrindStoneSlots(IInventory iInventory) {
        super("GrindStone", true, 2);
        //par3 为槽的数量
        if (iInventory instanceof TileEntityGrindStone) {
            this.tileEntityGrindStone = (TileEntityGrindStone)iInventory;
        } else {
            this.tileEntityGrindStone = null;
        }
        this.input[0] = new Slot(iInventory, getInputIndex(0), 49, 19) {
            public boolean isItemValid(ItemStack par1ItemStack) {
                return (par1ItemStack.getItem() instanceof ItemEnchantedBook || par1ItemStack.isItemEnchanted()||par1ItemStack.isItemDamaged());
            }
        };
        this.input[1] = new Slot(iInventory, getInputIndex(1), 49, 40) {
            public boolean isItemValid(ItemStack par1ItemStack) {
                return (par1ItemStack.getItem() instanceof ItemEnchantedBook || par1ItemStack.isItemEnchanted()||par1ItemStack.isItemDamaged());
            }
        };

        this.output = new Slot(iInventory, getOutputIndex(), 129, 34) {
            public boolean isItemValid(ItemStack par1ItemStack) {
                return false;
            }

            @Override
            public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {
                //0为失败  1武器修复  2武器袪魔  3附魔书袪魔
                if(GrindStoneSlots.this.containerGrindStone.getOutSlotCondition()!=0){
                    GrindStoneSlots.this.input[0].putStack(null);
                    GrindStoneSlots.this.input[1].putStack(null);
                    if(GrindStoneSlots.this.containerGrindStone.getOutSlotCondition()==2)
                        par1EntityPlayer.addExperience(-200);
                    if(GrindStoneSlots.this.containerGrindStone.getOutSlotCondition()==3)
                        par1EntityPlayer.addExperience(50);
                }

            }

        };
    }

    public ItemStack getInPutStack(int index) {
        return this.input[index].getStack();
    }

    public ItemStack getOutPutStack() {
        return this.output.getStack();
    }

    public Slot getInPut(int index) {
        return this.input[index];
    }

    public Slot getOutPut() {
        return this.output;
    }

    public void dropItems(World world, int x, int y, int z) {
        for (int var2 = 0; var2 < this.tileEntityGrindStone.getSizeInventory(); var2++) {
            ItemStack var3 = this.tileEntityGrindStone.getItem(var2);
            if (var3 != null) {
                float var10 = world.rand.nextFloat() * 0.8F + 0.1F;
                float var11 = world.rand.nextFloat() * 0.8F + 0.1F;
                EntityItem var14;
                for (float var12 = world.rand.nextFloat() * 0.8F + 0.1F; var3.stackSize > 0; world.spawnEntityInWorld(var14)) {
                    int var13 = world.rand.nextInt(21) + 10;
                    if (var13 > var3.stackSize)
                        var13 = var3.stackSize;
                    var3.stackSize -= var13;
                    var14 = new EntityItem(world, (x + var10), (y + var11), (z + var12), new ItemStack(var3.itemID, var13, var3.getItemSubtype()));
                    if (var3.isItemDamaged())
                        var14.getEntityItem().setItemDamage(var3.getItemDamage());
                    float var15 = 0.05F;
                    var14.motionX = ((float) world.rand.nextGaussian() * var15);
                    var14.motionY = ((float) world.rand.nextGaussian() * var15 + 0.2F);
                    var14.motionZ = ((float) world.rand.nextGaussian() * var15);
                    if (var3.getItem().hasQuality())
                        var14.getEntityItem().setQuality(var3.getQuality());
                    if (var3.hasTagCompound())
                        var14.getEntityItem().setTagCompound((NBTTagCompound) var3.getTagCompound().copy());
                }
            }
        }
    }

    public void writeToNBT(NBTTagCompound nbt) {
        tryWriteSlotStack(nbt, this.input[0], "Input0");
        tryWriteSlotStack(nbt, this.input[1], "Input1");

        tryWriteSlotStack(nbt, this.output, "Output");
    }

    public void readFromNBT(NBTTagCompound nbt, TileEntityGrindStone tileEntityGrindStone) {
        tileEntityGrindStone.setItem(getInputIndex(0), ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("Input0")));
        tileEntityGrindStone.setItem(getInputIndex(1), ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("Input1")));

        tileEntityGrindStone.setItem(getOutputIndex(), ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("Output")));
    }

    private void tryWriteSlotStack(NBTTagCompound nbt, Slot slot, String name) {
        if (slot.getStack() != null)
            nbt.setCompoundTag(name, slot.getStack().writeToNBT(new NBTTagCompound()));
    }

    void onContainerClosed() {
        if (!this.containerGrindStone.world.isRemote && this.tileEntityGrindStone != null)
            this.tileEntityGrindStone.closeChest();
    }

    //初始化槽位
    public void initSlots(ContainerGrindStone test) {
        test.addSlot(this.input[0]);
        test.addSlot(this.input[1]);
        test.addSlot(this.output);
        if (this.tileEntityGrindStone != null)
            this.tileEntityGrindStone.openChest();
        this.containerGrindStone = test;
    }
}