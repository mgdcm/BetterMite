package com.github.FlyBird.BetterMite.item;

import com.github.FlyBird.BetterMite.block.Blocks;
import com.github.FlyBird.BetterMite.misc.EnumWoodType;
import net.minecraft.Block;
import net.minecraft.ItemDoor;
import net.minecraft.Material;

public class ItemNewDoor extends ItemDoor {
    private final EnumWoodType woodType;

    public ItemNewDoor(int par1, EnumWoodType woodType) {
        super(par1, Material.wood);
        this.woodType=woodType;
    }

    @Override
    public Block getBlock() {
        if(woodType==EnumWoodType.BIRCH)
            return Blocks.birchDoor;
        if(woodType==EnumWoodType.JUNGLE)
            return Blocks.jungleDoor;
        if(woodType==EnumWoodType.SPRUCE)
            return Blocks.spruceDoor;
        else
            return null;
    }
}
