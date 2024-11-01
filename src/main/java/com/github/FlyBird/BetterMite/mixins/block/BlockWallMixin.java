package com.github.FlyBird.BetterMite.mixins.block;

import net.minecraft.Block;
import net.minecraft.BlockFence;
import net.minecraft.BlockWall;
import net.minecraft.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BlockWall.class)
public class BlockWallMixin {

    /**
     * @author FlyBird
     * @reason 与高版本墙体连接对齐
     */
    @Overwrite
    public boolean canConnectWallTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        Block block = par1IBlockAccess.getBlock(par2, par3, par4);
        if (block == null || block instanceof BlockFence) {
            return false;
        }
        return  block.connectsWithFence()||block instanceof BlockWall;
    }
}
