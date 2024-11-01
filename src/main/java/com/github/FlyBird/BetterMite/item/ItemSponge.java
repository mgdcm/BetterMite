package com.github.FlyBird.BetterMite.item;

import net.minecraft.Block;
import net.minecraft.ItemBlock;
import net.minecraft.ItemStack;

public class ItemSponge extends ItemBlock {
    public ItemSponge(Block block) {
        super(block);
    }
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        if (par1ItemStack == null) {
            return super.getUnlocalizedName();
        } else {
            int var2 = par1ItemStack.getItemSubtype();
            return var2 >= 0 && var2 <= 1 ? (super.getUnlocalizedName(par1ItemStack) + ((var2==0)?"":"_wet")) : super.getUnlocalizedName(par1ItemStack);
        }
    }
}
