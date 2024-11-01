package com.github.FlyBird.BetterMite.block;

import net.minecraft.Block;
import net.minecraft.BlockWall;
import net.minecraft.Icon;

public class BlockWallExtend extends BlockWall {
    Block block;
    public BlockWallExtend(int par1, Block par2Block) {
        super(par1, par2Block);
        block=par2Block;
    }

    public Icon getIcon(int par1, int par2) {//par1 有点像方块id  par2像meta
        return block.getBlockTextureFromSide(par1);
    }

    @Override
    public int getBlockSubtypeUnchecked(int metadata) {
        return 0;
    }//只有1和1计算的时候得1
}
