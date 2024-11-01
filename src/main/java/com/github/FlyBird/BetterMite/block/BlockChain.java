package com.github.FlyBird.BetterMite.block;


import com.github.FlyBird.BetterMite.render.RenderTypes;
import net.minecraft.*;

import static com.github.FlyBird.BetterMite.block.Blocks.stepSoundChain;

public class BlockChain extends Block {
    protected BlockChain(int par1,Material material) {
        super(par1, material, new BlockConstants().setNotAlwaysLegal().setNeverHidesAdjacentFaces());
        this.setBlockBoundsForAllThreads(0.40625, 0.0, 0.40625, 0.59375, 1.0, 0.59375);
        this.setMaxStackSize(16);
        this.setHardness(0.9f);//小于1 可以用手  大于1需要工具 默认斧头
        this.setStepSound(stepSoundChain);
        setCreativeTab(CreativeTabs.tabDecorations);
    }
    @Override
    public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
        return false;
    }

    private Icon chain;
    private Icon itemChain;

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.chain = par1IconRegister.registerIcon(this.getTextureName());//blocks
        this.itemChain = par1IconRegister.registerIcon( "item/"+this.getTextureName());
    }


    @Override
    public Icon getIcon(int side, int metadata) {
        if(side==1)
            return itemChain;
        return this.chain;
    }

    //返回0就像方块贴图  返回1则是drawCrossedSquares渲染交叉      2只是一个直角    30像线一样
    @Override
    public int getRenderType() {
        return RenderTypes.chainRenderType;
    }

    @Override
    public EnumDirection getDirectionFacing(int metadata) {
        return (metadata & 4) != 0 ? EnumDirection.WEST : ((metadata & 8) != 0 ? EnumDirection.NORTH : EnumDirection.DOWN);
    }

    @Override
    public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
        return this.getItemSubtype(metadata) | (direction.isUpOrDown() ? 0 : (direction.isEastOrWest() ? 4 : (direction.isNorthOrSouth() ? 8 : -1)));
    }

    @Override
    public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int x, int y, int z) {
        int meta=par1IBlockAccess.getBlockMetadata(x,y,z);
        if(meta==8)
            this.setBlockBounds(0.40625, 0.40625, 0.0, 0.59375, 0.59375, 1.0,false);
        if(meta==4)
            this.setBlockBounds(0, 0.40625, 0.40625, 1, 0.59375, 0.59375,false);
        if(meta==0)
            this.setBlockBounds(0.40625, 0.0, 0.40625, 0.59375, 1.0, 0.59375,false);
    }

    @Override
    public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
        int metadata = super.getMetadataForPlacement(world, x, y, z, item_stack, entity, face, offset_x, offset_y, offset_z);
        if (face.isEastOrWest()) {
            metadata |= 4;
        } else if (face.isNorthOrSouth()) {

            metadata |= 8;
        }

        return metadata;
    }

    @Override
    public void onFallenUpon(World par1World, int x, int y, int z, Entity par5Entity, float par6) {
        if(!par1World.isRemote)
            par1World.playSoundAtBlock(x, y, z, this.stepSound.getStepSound(), (this.stepSound.getVolume() + 1.0F) / 2.0F, this.stepSound.getPitch() * 0.8F);
    }
}
