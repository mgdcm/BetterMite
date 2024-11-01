package com.github.FlyBird.BetterMite.block;


import com.github.FlyBird.BetterMite.api.BetterMitePlayer;
import com.github.FlyBird.BetterMite.render.RenderTypes;
import com.github.FlyBird.BetterMite.tileentity.TileEntityGrindStone;
import net.minecraft.*;



public class BlockGrindStone extends Block implements ITileEntityProvider{
    private Icon GrindStonePivot;
    private Icon GrindStoneRound;
    private Icon GrindStoneSide;
    private Icon GrindStoneItem;
    private Icon GrindStoneLeg;

    protected BlockGrindStone(int par1) {
        super(par1, Material.stone, new BlockConstants().setNotAlwaysLegal().setNeverHidesAdjacentFaces());
        this.setBlockBoundsForAllThreads(0.125, 0.0, 0.125, 0.875, 1.0, 0.875);
        this.setHardness(2.0F);
        this.setResistance(6.0F);
        setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.GrindStonePivot = par1IconRegister.registerIcon( "grindstone/"+this.getTextureName()+"_pivot");
        this.GrindStoneRound = par1IconRegister.registerIcon( "grindstone/"+this.getTextureName()+"_round");
        this.GrindStoneSide = par1IconRegister.registerIcon( "grindstone/"+this.getTextureName()+"_side");
        this.GrindStoneLeg=par1IconRegister.registerIcon( "dark_oak_log");
        this.GrindStoneItem = par1IconRegister.registerIcon( "item/"+this.getTextureName());
    }

    @Override
    public Icon getIcon(int side, int metadata) {
        if (side == 1)
            return GrindStoneItem;
        if(side==2)
            return this.GrindStonePivot;
        if(side==3)
            return this.GrindStoneRound;
        if(side==4)
            return this.GrindStoneSide;
        if(side==5)
            return  this.GrindStoneLeg;
        else
            return this.GrindStoneSide;
    }

    @Override
    public int getRenderType() {
        return RenderTypes.grindStoneRenderType;
    }

    @Override
    public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
        if (!world.isAirOrPassableBlock(x, y + 1, z, false))
            return false;
        if (player.onServer()) {
            TileEntityGrindStone tile_entity = (TileEntityGrindStone)world.getBlockTileEntity(x, y, z);
            if (tile_entity != null ) {
                ((BetterMitePlayer) player).betterMite$displayGUIGrindStone(x, y, z, tile_entity.getSlots());
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityGrindStone();
    }
}
