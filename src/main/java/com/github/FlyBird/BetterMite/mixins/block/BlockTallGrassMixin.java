package com.github.FlyBird.BetterMite.mixins.block;

import com.github.FlyBird.BetterMite.block.BlockSweetBerry;
import com.github.FlyBird.BetterMite.block.Blocks;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(BlockTallGrass.class)
public abstract class BlockTallGrassMixin extends BlockPlant {
    protected BlockTallGrassMixin(int id, Material material) {
        super(id, material);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
        if(player.getHeldItem()!=null&&player.getHeldItem().itemID==Item.dyePowder.itemID&&player.getHeldItemStack().getItemSubtype()==15){
            if(!world.isWorldClient())Blocks.bigGrass.tryPlaceBlock(world, x, y, z, EnumFace.BOTTOM,((world.getBlockMetadata(x,y,z)-1)<<1)+1,null,false,false, false);
            if (player.onServer() && !player.inCreativeMode()) player.convertOneOfHeldItem(null);
            if (player.onServer())world.playAuxSFX(2005, x, y, z, 0);
            return true;
        }
        return true;
    }
}
