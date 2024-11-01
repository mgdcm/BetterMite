package com.github.FlyBird.BetterMite.gui.grindstone;


import com.github.FlyBird.BetterMite.block.BlockGrindStone;
import com.github.FlyBird.BetterMite.tileentity.TileEntityGrindStone;
import net.minecraft.*;


import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ContainerGrindStone extends Container {
    @Nullable
    private final TileEntityGrindStone tileEntityTest;

    private final GrindStoneSlots slots;

    private final int blockX;

    private final int blockY;

    private final int blockZ;

    private int lastEXP;

    private int outSlotCondition;

    public void addSlot(Slot slot) {
        addSlotToContainer(slot);
    }

    public ContainerGrindStone(GrindStoneSlots slots, EntityPlayer player, int x, int y, int z) {
        super(player);
        this.blockX = x;
        this.blockY = y;
        this.blockZ = z;
        this.outSlotCondition =0;
        slots.initSlots(this);//先把自己的槽位加入
        this.slots = slots;
        if (!(player.getWorld()).isRemote) {
            this.tileEntityTest = (TileEntityGrindStone) player.getWorldServer().getBlockTileEntity(x, y, z);
        } else {
            this.tileEntityTest = null;
        }
        onCraftMatrixChanged(slots);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++)
                addSlot(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
        }
        for (int i = 0; i < 9; i++)
            addSlot(new Slot(player.inventory, i, 8 + i * 18, 142));
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {           //如果你要转移的槽位不是空的  并且有东西
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < this.slots.getSize()) {     //如果要转移的槽位是 自己注册的槽位
                //该方法的第二个参数和第三个参数传入想要放入的物品槽的开始ID（包含）和结束ID（不包含），在这里也就是2-39，即玩家背包中的36个物品槽
                //该方法的最后一个参数传入是正向查找第一个可用物品槽（2-39），还是反向查找（39-2），     当然如果  可以放入的物品槽只有一个（后面会遇到）那么这两者是没有区别的
                if (!mergeItemStack(itemstack1, this.slots.getSize(), this.inventorySlots.size(), false))       //尝试在 3-39寻找可以迁移的物品槽
                    return null;        //没能成功放入的话
            }//如果是附魔书  或者工具 或者装备
            else if (itemstack1.getItem() instanceof ItemEnchantedBook||itemstack1.getItem() instanceof ItemTool||itemstack1.getItem() instanceof ItemArmor) {
                if (!mergeItemStack(itemstack1, this.slots.getInputIndex(0), this.slots.getInputIndex(1) + 1, false))   //试着能不能迁移到第一个格子和第二个格子
                    return null;
            }
            if (itemstack1.stackSize == 0) {        //如果迁移成功了  并且数量为0  设置为空
                slot.putStack(null);
            } else {
                slot.onSlotChanged();//物品槽发生变动    如果数量不为0
            }
            if (itemstack1.stackSize == itemstack.stackSize)    //如果数量没有变动
                return null;
            //设置从槽中要    取出的物品栈
            slot.onPickupFromSlot(playerIn, itemstack1);
        }
        return itemstack;       //返回要迁移的物品栈
    }

    public List<ItemStack> getInventory() {
        List<ItemStack> nonnulllist = new ArrayList<>();
        for (Object o : this.inventorySlots)
            nonnulllist.add(((Slot)o).getStack());
        return nonnulllist;
    }

    public void onContainerClosed(EntityPlayer par1EntityPlayer) {
        super.onContainerClosed(par1EntityPlayer);
        if (!this.world.isRemote)
            this.slots.onContainerClosed();
    }

    void updateInfo() {
        //if (!this.world.isRemote)
           // this.player.sendPacket( new S2CBarrelInfo(this.tileEntityTest.getEXP()));
    }

    public boolean canInteractWith(EntityPlayer player) {
        if (this.world.getBlock(this.blockX, this.blockY, this.blockZ) instanceof BlockGrindStone && this.world.getBlockTileEntity(this.blockX, this.blockY, this.blockZ) instanceof TileEntityGrindStone)
            return (player.getDistanceSq(this.blockX + 0.5D, this.blockY + 0.5D, this.blockZ + 0.5D) <= 64.0D);
        return false;
    }

    //0为失败   1为修复武器    2武器袪魔  3为附魔书袪魔
    public int getOutSlotCondition() {return this.outSlotCondition;}
    //用来显示预览
    public void updateOutput() {
        ItemStack item_stack_in_first_slot = this.slots.getInPutStack(0);
        ItemStack item_stack_in_second_slot = this.slots.getInPutStack(1);
        ItemStack item_stack_in_out_slot=null;
        //如果两个槽位都是空的，输出槽位不显示任何东西

        if (item_stack_in_first_slot == null&&item_stack_in_second_slot == null) {
            outSlotCondition =0;
            this.slots.getOutPut().putStack(null);
        }
        if (item_stack_in_first_slot != null&&item_stack_in_second_slot != null) {//修复物品
            boolean is_repairing_by_combination=ItemStack.areItemStacksEqual(item_stack_in_first_slot, item_stack_in_second_slot, true, false, true, true);
            if(is_repairing_by_combination)
            {
                if(item_stack_in_first_slot.isItemDamaged() && item_stack_in_second_slot.isItemDamaged())
                {
                    if(item_stack_in_first_slot.isItemEnchanted()&&item_stack_in_second_slot.isItemEnchanted())
                        outSlotCondition =0;
                    else {
                        outSlotCondition =1;
                        if(item_stack_in_first_slot.isItemEnchanted())
                            item_stack_in_out_slot=item_stack_in_first_slot.copy();
                        else if(item_stack_in_second_slot.isItemEnchanted())
                            item_stack_in_out_slot=item_stack_in_second_slot.copy();
                        else
                            item_stack_in_out_slot=item_stack_in_second_slot.copy();
                        item_stack_in_out_slot.setItemDamage(CraftingManager.getResultingDurabilityFromCombiningItems(item_stack_in_first_slot, item_stack_in_second_slot));
                    }
                }
            }
            else
                outSlotCondition =0;
        }
        else {//单纯袪魔
            if (item_stack_in_first_slot != null) {
                if(item_stack_in_first_slot.isItemEnchanted()) {
                    outSlotCondition = 2;
                    item_stack_in_out_slot = item_stack_in_first_slot.copy();
                    item_stack_in_out_slot.clearEnchantTagList();
                }
                if(item_stack_in_first_slot.getItem() instanceof ItemEnchantedBook)
                {
                    outSlotCondition = 3;
                    item_stack_in_out_slot = new ItemStack(Item.book,1);;
                }
            }
            if (item_stack_in_second_slot != null) {
                if(item_stack_in_second_slot.isItemEnchanted()) {
                    outSlotCondition = 2;
                    item_stack_in_out_slot = item_stack_in_second_slot.copy();
                    item_stack_in_out_slot.clearEnchantTagList();
                }
                if(item_stack_in_second_slot.getItem() instanceof ItemEnchantedBook)
                {
                    outSlotCondition = 3;
                    item_stack_in_out_slot = new ItemStack(Item.book,1);;
                }
            }

        }
        this.slots.getOutPut().putStack(item_stack_in_out_slot);
        this.detectAndSendChanges();
        //第一个忽略栈的数量    质量    损害但不是子id    tagcompound
        //ItemStack.areItemStacksEqual(item_stack_in_first_slot, item_stack_in_second_slot, true, false, true, true)
    }

    @Override   //好像没啥用？
    public void onCraftMatrixChanged(IInventory par1IInventory) {
        super.onCraftMatrixChanged(par1IInventory);
        if (par1IInventory == this.slots) {
            //this.updateOutput();
        }
    }

    @Override
    public void onUpdate() {
        if (this.player instanceof EntityOtherPlayerMP) {
            return;
        }
        this.updateOutput();
        super.onUpdate();
    }
}
