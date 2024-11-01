package com.github.FlyBird.BetterMite.world;

import com.github.FlyBird.BetterMite.block.BlockSweetBerry;
import com.github.FlyBird.BetterMite.block.Blocks;
import net.minecraft.Block;
import net.minecraft.World;
import net.minecraft.WorldGenerator;

import java.util.Random;

public class WorldGeneratorPlant extends WorldGenerator {

  private final Block block;
  
  public WorldGeneratorPlant(int par1) {
    this.block = Block.getBlock(par1);
  }

  @Override
  public boolean generate(World par1World, Random par2Random, int block_x, int block_y, int block_z) {

    int attempts = 5;
    if(block.blockID==Blocks.bigGrass.blockID)attempts*=100;
    for (int var6 = 0; var6 < attempts; var6++) {
      //循环一次获得一个散点图
      int set_y = block_y + par2Random.nextInt(4) - par2Random.nextInt(4);
      if (set_y >= 0 && set_y <= 255) {
        int set_x = block_x + par2Random.nextInt(8) - par2Random.nextInt(8);
        int set_z = block_z + par2Random.nextInt(8) - par2Random.nextInt(8);

        if (par1World.isAirBlock(set_x, set_y, set_z) && (!par1World.provider.hasNoSky || set_y < 127)) {
          if(block.blockID==Blocks.sweetBerryBush.blockID&&this.block.canOccurAt(par1World, set_x, set_y, set_z, 6))
          {
                par1World.setBlock(set_x, set_y, set_z, block.blockID, BlockSweetBerry.setBerryStage(0, par2Random.nextInt(3)), 2);
            System.out.print("barries :" + set_x + ","+set_y +","+ set_z);
          }
          if (par1World.isAirBlock(set_x, set_y, set_z) && this.block.canOccurAt(par1World, set_x, set_y, set_z, 1)) {
            if(block.blockID==Blocks.bigGrass.blockID)
            {
              par1World.setBlock(set_x, set_y+1, set_z, block.blockID, 0, 2);
              par1World.setBlock(set_x, set_y, set_z, block.blockID, 1, 2);
              System.out.print("tallgrass :" + set_x + ","+set_y +","+ set_z);
            }
          }
          if (par1World.isAirBlock(set_x, set_y, set_z) && this.block.canOccurAt(par1World, set_x, set_y, set_z, 3)) {
            if(block.blockID==Blocks.bigGrass.blockID)
            {
              if(var6%25!=0)continue;
              par1World.setBlock(set_x, set_y+1, set_z, block.blockID, 2, 2);
              par1World.setBlock(set_x, set_y, set_z, block.blockID, 3, 2);
              System.out.print("tallfern :" + set_x + ","+set_y +","+ set_z);
            }
          }
          if (par1World.isAirBlock(set_x, set_y, set_z) && this.block.canOccurAt(par1World, set_x, set_y, set_z, 2)) {
            if(block.blockID==Blocks.tallGrass.blockID)
            {
              par1World.setBlock(set_x, set_y, set_z, block.blockID, 2, 2);
              System.out.print("fern :" + set_x + ","+set_y +","+ set_z);
            }
          }
        }

      }
    }
    return true;
  }

}
