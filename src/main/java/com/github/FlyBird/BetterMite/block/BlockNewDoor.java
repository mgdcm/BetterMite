package com.github.FlyBird.BetterMite.block;

import com.github.FlyBird.BetterMite.misc.EnumWoodType;
import net.minecraft.BlockDoor;
import net.minecraft.CreativeTabs;
import net.minecraft.Material;

public class BlockNewDoor extends BlockDoor{
    private final EnumWoodType woodType;

    public BlockNewDoor(int blockID, EnumWoodType woodType) {
        super(blockID, Material.wood);
        this.woodType = woodType;
        //setCreativeTab(CreativeTabs.tabDecorations);
    }

    public EnumWoodType getWoodType() {
        return woodType;
    }
}
