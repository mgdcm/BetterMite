package com.github.FlyBird.BetterMite.block;

import net.minecraft.Block;
import net.minecraft.BlockStairs;

public class BlockModStairs extends BlockStairs {
    protected BlockModStairs(int par1, Block par2Block, int par3) {
        super(par1, par2Block, par3);
        this.setUnlocalizedName("stairs");
    }
}
