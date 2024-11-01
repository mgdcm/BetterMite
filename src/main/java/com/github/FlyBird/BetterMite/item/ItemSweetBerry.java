package com.github.FlyBird.BetterMite.item;

import net.minecraft.*;

public class ItemSweetBerry extends ItemFood {
    private final int cropId;
    public ItemSweetBerry(int id, int satiation, int nutrition, boolean has_protein, boolean has_phytonutrients, int crop_block_id, String texture) {
        super(id, Material.seed, satiation, nutrition, has_protein, false, has_phytonutrients, texture);
        this.cropId = crop_block_id;
        this.setPlantProduct();
        this.setFoodValue(satiation, nutrition, 1000, has_protein, false, has_phytonutrients);
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
        if (player.worldObj.areSkillsEnabled() && !player.hasSkill(Skill.FARMING)) {
            return false;
        }
        if (rc.face_hit.isTop() && isSoilBlock(rc.getBlockHitID()) && rc.isNeighborAirBlock()) {
            return player.tryPlaceHeldItemAsBlock(rc, Block.getBlock(this.cropId));
        }
        if (rc.getBlockHit() instanceof BlockCrops || rc.getBlockHit() instanceof BlockStem || rc.face_hit.isTop() && rc.getBlockHit() instanceof BlockFarmland) {
            player.cancelRightClick();
            return true;
        }
        return false;
    }

    @Override
    public boolean hasIngestionPriority(ItemStack item_stack, boolean ctrl_is_down) {
        return false;
    }
}
