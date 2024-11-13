package com.github.FlyBird.BetterMite.item;

import net.minecraft.Block;
import net.minecraft.ItemMultiTextureTile;
import net.minecraft.ItemStack;

public class ItemMultiTextureTileFuelBlock extends ItemMultiTextureTile {
    protected int burnTime;
    public ItemMultiTextureTileFuelBlock(Block block, String[] names,int burnTime) {
        super(block, names);
        this.burnTime=burnTime;
    }

    @Override
    public int getBurnTime(ItemStack item_stack) {
        return burnTime;
    }
}
