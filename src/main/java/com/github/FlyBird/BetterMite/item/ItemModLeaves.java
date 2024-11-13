package com.github.FlyBird.BetterMite.item;

import com.github.FlyBird.BetterMite.block.BlockModLeaves;
import com.github.FlyBird.BetterMite.block.BlockModLog;
import net.minecraft.Block;
import net.minecraft.BlockLeaves;
import net.minecraft.ItemLeaves;
import net.minecraft.ItemStack;

public class ItemModLeaves extends ItemLeaves {
    public ItemModLeaves(Block block) {
        super(block);
    }
    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        if (par1ItemStack == null) {
            return super.getUnlocalizedName();
        } else {
            return "leaves" + "." + ((BlockModLeaves)this.getBlock()).id;
        }
    }
}
