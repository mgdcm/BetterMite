package com.github.FlyBird.BetterMite.world.gen;

import net.minecraft.Block;
import net.minecraft.World;
import net.minecraft.WorldGenerator;

import java.util.Random;

public class WorldGenMelon extends WorldGenerator {
    public WorldGenMelon() {
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
        for(int var6 = 0; var6 < 64; ++var6) {
            int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int var8 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int metadata = par2Random.nextInt(4);
            if (par1World.isAirBlock(var7, var8, var9) && par1World.getBlockId(var7, var8 - 1, var9) == Block.grass.blockID && Block.melon.canOccurAt(par1World, var7, var8, var9, metadata)) {
                par1World.setBlock(var7, var8, var9, Block.melon.blockID, metadata, 2);
            }
        }

        return true;
    }
}
