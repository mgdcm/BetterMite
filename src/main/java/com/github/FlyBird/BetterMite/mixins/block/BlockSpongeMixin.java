package com.github.FlyBird.BetterMite.mixins.block;

import net.minecraft.*;
import org.lwjgl.Sys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Random;

@Mixin(BlockSponge.class)
public class BlockSpongeMixin extends Block {
    protected BlockSpongeMixin(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }
    @Unique protected Icon wetBlockIcon;
    @Override
    public boolean onBlockPlacedMITE(World world, int x, int y, int z, int metadata, Entity placer, boolean test_only) {
        WaterUptake(world,x,y,z);
        return super.onBlockPlacedMITE(world, x, y, z, metadata, placer, test_only);
    }

    @Override
    public boolean updateTick(World world, int x, int y, int z, Random random) {
        WaterUptake(world,x,y,z);
        return super.updateTick(world, x, y, z, random);
    }

    @Override
    public String getUnlocalizedName() {
        return super.getUnlocalizedName();
    }

    @Override
    public boolean isValidMetadata(int metadata) {
        return metadata<=1;
    }
    @Override
    public int getBlockSubtypeUnchecked(int metadata) {
        return metadata&1;
    }
    @Unique
    private static void WaterUptake(World world, int x, int y, int z){
        if(world.isWorldServer()&&world.getBlockMetadata(x,y,z)==0){
            for(int i=-4;i<4;i++){
                for(int j=-4;j<4;j++){
                    for(int k=-4;k<4;k++){
                        if(world.getBlock(x+i,y+j,z+k)!=null&&world.getBlock(x+i,y+j,z+k).isWater()){
                            world.setBlockToAir(x+i,y+j,z+k);
                            world.setBlockMetadataWithNotify(x,y,z,1,2);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.wetBlockIcon=par1IconRegister.registerIcon(this.getTextureName()+"_wet");
        super.registerIcons(par1IconRegister);
    }

    @Override
    public Icon getIcon(int par1, int par2) {
        if(par2==1)return this.wetBlockIcon;
        return super.getIcon(par1, par2);
    }

    @Override
    public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
        WaterUptake(world,x,y,z);
        return super.onNeighborBlockChange(world, x, y, z, neighbor_block_id);
    }
}
