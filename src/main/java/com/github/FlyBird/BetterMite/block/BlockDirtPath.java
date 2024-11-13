package com.github.FlyBird.BetterMite.block;

import net.minecraft.*;

public class BlockDirtPath extends BlockUnderminable{

    private Icon dirt_path_side;
    private Icon dirt_path_top;
    private Icon dirt_bottom;

    protected BlockDirtPath(int par1) {
        super(par1, Material.dirt, new BlockConstants().setNotAlwaysLegal());
        this.setTickRandomly(true);
        this.setBlockBoundsForAllThreads(0.0, 0.0, 0.0, 1.0, 0.9375, 1.0);
        this.setLightOpacity(255);
        this.setCushioning(0.4f);
        this.setHardness(0.6f);
        this.setStepSound(soundGrassFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public Icon getIcon(int par1, int par2) {
        //par1  为0  渲染底部   1渲染顶部  其他渲染  边
        return par1 == 0 ? this.dirt_bottom : (par1 == 1 ? this.dirt_path_top : this.dirt_path_side);

    }

    @Override
    public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
        Block block_above =  world.getBlockWithRefreshedBounds(x, y + 1, z);
        return block_above == null;

    }

    @Override
    public boolean onNotLegal(World world, int x, int y, int z, int metadata) {
        return world.setBlock(x, y, z, BlockFarmland.dirt.blockID);
    }

    @Override
    public boolean canBeCarried() {
        return true;
    }

    @Override
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        return this.dropBlockAsEntityItem(info, Block.dirt);
    }

    @Override
    public int idPicked(World par1World, int par2, int par3, int par4) {
        return Blocks.dirt.blockID;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.dirt_path_top =par1IconRegister.registerIcon( "dirt_path_top");
        this.dirt_path_side =par1IconRegister.registerIcon( "dirt_path_side");
        this.dirt_bottom =par1IconRegister.registerIcon( "dirt");
    }

    @Override
    public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
        return false;
    }

    @Override
    public boolean isFaceFlatAndSolid(int metadata, EnumFace face) {
        return face.isBottom();
    }
}
