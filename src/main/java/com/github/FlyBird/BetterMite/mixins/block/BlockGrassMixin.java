package com.github.FlyBird.BetterMite.mixins.block;

import com.github.FlyBird.BetterMite.block.Blocks;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(BlockGrass.class)
public abstract class BlockGrassMixin extends Block {
    protected BlockGrassMixin(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }
    @Inject(method = "fertilize",at=@At("RETURN"))
    public void tallGrassGen(World world, int x, int y, int z, ItemStack item_stack, CallbackInfoReturnable<Boolean> cir){
        if(item_stack.getItem() != Item.dyePowder)return;
        world.getBlockMetadata(x, y, z);
        Random itemRand = Item.itemRand;
        int dx;
        int var7;
        int var8;
        if (!world.isRemote) {
            label70:
            for(dx = 0; dx < 128; ++dx) {
                var7 = x;
                var8 = y + 1;
                int var9 = z;
                int subtype;
                for(subtype = 0; subtype < dx / 16; ++subtype) {
                    var7 += itemRand.nextInt(3) - 1;
                    var8 += (itemRand.nextInt(3) - 1) * itemRand.nextInt(3) / 2;
                    var9 += itemRand.nextInt(3) - 1;
                    if (world.getBlockId(var7, var8 - 1, var9) != Block.grass.blockID || world.isBlockNormalCube(var7, var8, var9)) {
                        continue label70;
                    }
                }
                if (world.getBlockId(var7,var8,var9)==tallGrass.blockID&&world.isAirBlock(var7,var8+1,var9) && itemRand.nextInt(8) == 0) {
                    Blocks.bigGrass.tryPlaceBlock(world, var7, var8, var9, EnumFace.BOTTOM,1,null,false,false, false);
                    //Blocks.bigGrass.tryPlaceBlock(world, x, y+1, z, EnumFace.BOTTOM,0,null,false,false, false);
                }
            }
        }
    }
}
