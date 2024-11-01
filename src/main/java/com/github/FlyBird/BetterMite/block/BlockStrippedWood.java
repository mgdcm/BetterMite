package com.github.FlyBird.BetterMite.block;

import net.minecraft.Block;
import net.minecraft.BlockConstants;
import net.minecraft.CreativeTabs;
import net.minecraft.Material;

import static com.github.FlyBird.BetterMite.block.Blocks.stepSoundWood;

public class BlockStrippedWood extends Block {
    protected BlockStrippedWood(int par1) {
        super(par1, Material.wood, new BlockConstants());
        this.modifyMinHarvestLevel(1);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(1.0f);
        this.setStepSound(stepSoundWood);
    }
}
