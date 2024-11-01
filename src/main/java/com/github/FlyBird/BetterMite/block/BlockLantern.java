package com.github.FlyBird.BetterMite.block;

import com.github.FlyBird.BetterMite.render.RenderTypes;
import net.minecraft.*;

import static com.github.FlyBird.BetterMite.block.Blocks.stepSoundLantern;

public class BlockLantern extends Block {
    Icon blocklantern;
    Icon itemlantern;

    protected BlockLantern(int par1) {
        super(par1, Material.wood, new BlockConstants().setNotAlwaysLegal().setNeverHidesAdjacentFaces());
        this.setLightValue(1.0f);
        this.setHardness(0.5f);
        this.setMaxStackSize(1);
        this.setBlockBoundsForAllThreads(0.3125, 0.0, 0.3125, 0.6878, 1.0, 0.6878);
        this.setStepSound(stepSoundLantern);
        setCreativeTab(CreativeTabs.tabDecorations);
    }
    //metadata=0 没锁链   等于1  有锁链
    @Override
    public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int x, int y, int z) {
        int direction =par1IBlockAccess.getBlockMetadata(x,y+1,z);
        Block block=par1IBlockAccess.getBlock(x, y + 1, z);
        float r = 0.0625F;
        float f = 0.375F;
        float f1 = f / 2.0F;

        if(!par1IBlockAccess.isAirBlock(x,y+1,z)) {
            //锁链必须朝上
            if (block instanceof BlockChain) {
                if(direction == 0) {
                    par1IBlockAccess.getWorld().setBlockMetadataWithNotify(x, y, z, 1, 2);
                    this.setBlockBounds(0.5F - f1, r * 1, 0.5F - f1, 0.5F + f1, r * 8, 0.5F + f1,false);
                }
                else {
                    par1IBlockAccess.getWorld().setBlockMetadataWithNotify(x, y, z, 0, 2);
                    this.setBlockBounds(0.5F - f1, 0.0F, 0.5F - f1, 0.5F + f1, r * 7, 0.5F + f1,false);
                }
            }
            else {
                par1IBlockAccess.getWorld().setBlockMetadataWithNotify(x, y, z, 1, 2);
                this.setBlockBounds(0.5F - f1, r * 1, 0.5F - f1, 0.5F + f1, r * 8, 0.5F + f1,false);
            }
        }
        else {
            par1IBlockAccess.getWorld().setBlockMetadataWithNotify(x, y, z, 0, 2);
            this.setBlockBounds(0.5F - f1, 0.0F, 0.5F - f1, 0.5F + f1, r * 7, 0.5F + f1,false);
        }
    }

    @Override
    public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
        int direction =world.getBlockMetadata(x,y+1,z);
        Block block=world.getBlock(x, y + 1, z);
        if(!world.isAirBlock(x, y - 1, z))
            return true;
        else
        {
            if(!world.isAirBlock(x,y+1,z)) {
                if(block instanceof BlockChain)
                {
                    //之前用metadata判断是不是上方有锁链但是不行，因该是没有及时更新metadata值
                    return direction == 0;
                }
                else
                    return true;
            }
            return false;
        }
    }

    @Override
    public int getRenderType() {
        return RenderTypes.lanternRenderType;
    }

    @Override
    public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
        return false;
    }

    @Override
    public Icon getIcon(int side, int metadata) {
        if(side==1)
            return this.itemlantern;

        return this.blocklantern;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.blocklantern = par1IconRegister.registerIcon( this.getTextureName());
        this.itemlantern = par1IconRegister.registerIcon( "item/"+this.getTextureName());
    }

    @Override
    public void onFallenUpon(World par1World, int x, int y, int z, Entity par5Entity, float par6) {
        if(!par1World.isRemote)
            par1World.playSoundAtBlock(x, y, z, this.stepSound.getBreakSound(), (this.stepSound.getVolume() + 1.0F) / 2.0F, this.stepSound.getPitch() * 0.8F);
    }
}
