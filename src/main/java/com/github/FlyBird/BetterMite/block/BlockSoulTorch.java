package com.github.FlyBird.BetterMite.block;

import com.github.FlyBird.BetterMite.misc.EnumParticles;
import net.minecraft.*;

import java.util.Random;

import static com.github.FlyBird.BetterMite.block.Blocks.stepSoundWood;

public class BlockSoulTorch extends BlockTorch {
    public static Icon soulFlame;

    protected BlockSoulTorch(int BlockID) {
        super(BlockID);
        this.setLightValue(0.625f);
        this.setStepSound(stepSoundWood);
    }

    @Override
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        int var6 = par1World.getBlockMetadata(par2, par3, par4);
        double var7 = (double)par2 + 0.5;
        double var9 = (double)par3 + 0.7;
        double var11 = (double)par4 + 0.5;
        double var13 = 0.22f;
        double var15 = 0.27f;
        if (var6 == 1) {
            par1World.spawnParticle(EnumParticle.smoke, var7 - var15, var9 + var13, var11, 0.0, 0.0, 0.0);
            par1World.spawnParticle( EnumParticles.soulFlame, var7 - var15, var9 + var13, var11, 0.0, 0.0, 0.0);
        } else if (var6 == 2) {
            par1World.spawnParticle(EnumParticle.smoke, var7 + var15, var9 + var13, var11, 0.0, 0.0, 0.0);
            par1World.spawnParticle( EnumParticles.soulFlame, var7 + var15, var9 + var13, var11, 0.0, 0.0, 0.0);
        } else if (var6 == 3) {
            par1World.spawnParticle(EnumParticle.smoke, var7, var9 + var13, var11 - var15, 0.0, 0.0, 0.0);
            par1World.spawnParticle( EnumParticles.soulFlame, var7, var9 + var13, var11 - var15, 0.0, 0.0, 0.0);
        } else if (var6 == 4) {
            par1World.spawnParticle(EnumParticle.smoke, var7, var9 + var13, var11 + var15, 0.0, 0.0, 0.0);
            par1World.spawnParticle( EnumParticles.soulFlame, var7, var9 + var13, var11 + var15, 0.0, 0.0, 0.0);
        } else {
            par1World.spawnParticle(EnumParticle.smoke, var7, var9, var11, 0.0, 0.0, 0.0);
            par1World.spawnParticle( EnumParticles.soulFlame, var7, var9, var11, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        super.registerIcons(par1IconRegister);
        soulFlame = par1IconRegister.registerIcon( "particle/soul_fire_flame");
    }
}
