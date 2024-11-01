package com.github.FlyBird.BetterMite.item;

import com.github.FlyBird.BetterMite.block.BlockBigGrass;
import com.github.FlyBird.BetterMite.block.Blocks;
import net.minecraft.*;

public class ItemBigGrass extends Item {
    private int layerType;
    public ItemBigGrass(int id,int layerType, String texture) {
        super(id, texture);
        this.layerType=layerType;
        this.setPlantProduct();
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    private boolean isSoilBlock(int blockID)
    {
        return blockID == Block.dirt.blockID || blockID == Block.grass.blockID;
    }

    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        RaycastCollision rc = player.getSelectedObject(partial_tick, false);
        if (rc == null || !rc.isBlock()) {
            return false;
        }
        if (rc.face_hit.isTop() && isSoilBlock(rc.getBlockHitID()) && rc.isNeighborAirBlock()) {

        }
        return false;
    }

    @Override
    public boolean hasIngestionPriority(ItemStack item_stack, boolean ctrl_is_down) {
        return false;
    }
}
