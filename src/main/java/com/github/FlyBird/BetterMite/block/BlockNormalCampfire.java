package com.github.FlyBird.BetterMite.block;

import com.github.FlyBird.BetterMite.render.RenderTypes;
import net.minecraft.*;

public class BlockNormalCampfire extends Block {
    private Icon BlockCampfireIcon;

    protected BlockNormalCampfire(int par1) {
        super(par1, Material.wood, new BlockConstants().setNotAlwaysLegal().setNeverHidesAdjacentFaces());
        this.setHardness(0.5f);
        this.setBlockBoundsForAllThreads(0.0, 0.0, 0.0, 1.0, 0.4375, 1.0);
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.BlockCampfireIcon = par1IconRegister.registerIcon( "campfire_log");
    }

    @Override
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        if ((info.wasHarvestedByPlayer() || info.wasSelfDropped() || info.wasNotLegal())) {
            return this.dropBlockAsEntityItem(info, new ItemStack(Item.coal,2,1));
        }
        return 0;
    }

    //设置为false必须不能弄物品栏标签
    @Override
    public boolean canBeCarried() {
        return false;
    }

    @Override
    public Icon getIcon(int side, int metadata) {
        return this.BlockCampfireIcon;
    }

    @Override
    public int getRenderType() {
        return RenderTypes.normalcampfireRenderType;
    }

    @Override
    public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
        return false;
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
    public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
        int metadata = super.getMetadataForPlacement(world, x, y, z, item_stack, entity, face, offset_x, offset_y, offset_z);
        if (face.isEastOrWest()) {
            metadata |= 4;
        } else if (face.isNorthOrSouth()) {
            metadata |= 8;
        }
        return metadata;
    }
}
