package com.github.FlyBird.BetterMite.mixins.item;

import com.github.FlyBird.BetterMite.block.Blocks;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;


@Mixin({ItemAxe.class})
public abstract class ItemAxeMixin extends ItemTool {
    protected ItemAxeMixin(int par1, Material material) {
        super(par1, material);
    }

    @Shadow public abstract float getBaseDamageVsEntity();

    private static boolean tryBecomeWood(World world, int x, int y, int z, EnumFace face, EntityPlayer player, ItemStack item_stack) {
        if (!player.canPlayerEdit(x, y, z, item_stack)) {
            return false;
        }
        Block block = world.getBlock(x, y, z);
        int meta = block.getBlockSubtype(world.getBlockMetadata(x, y, z));
        int direction = world.getBlockMetadata(x, y, z);
        System.out.println("Direction:"+direction);
        if (block != Block.wood ) {
            return false;
        }
        if (player.onClient()) {
            player.swingArm();
            Minecraft.theMinecraft.playerController.setUseButtonDelayOverride(200);
        }
        else {
            //0为 oak   1 spruce   2birch  3jungle
            world.playSoundAtBlock(x, y, z, Block.wood.stepSound.getStepSound(), (Block.wood.stepSound.getVolume() + 1.0f) / 2.0f, Block.wood.stepSound.getPitch() * 0.8f);
            player.tryDamageHeldItem(DamageSource.generic, 10);
            player.addHungerServerSide(world.getBlockHardness(x, y, z) / 2.0f * EnchantmentHelper.getEnduranceModifier(player));
            if (meta == 0)
                world.setBlock(x, y, z, Blocks.strippedOak.blockID,direction,2);
            if (meta == 1)
                world.setBlock(x, y, z, Blocks.strippedSpruce.blockID,direction,2);
            if (meta == 2)
                world.setBlock(x, y, z, Blocks.strippedBirch.blockID,direction,2);
            if (meta == 3)
                world.setBlock(x, y, z, Blocks.strippedJungle.blockID,direction,2);
        }
        return true;
    }

    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        RaycastCollision rc = player.getSelectedObject(partial_tick, true);
        if (rc == null || !rc.isBlock()) {
            if (this.canBlock()) {
                player.setHeldItemInUse();
                return true;
            } else {
                return false;
            }
        }

        boolean flag=tryBecomeWood(rc.world, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, rc.face_hit, player, player.getHeldItemStack());
        if(!flag)
        {
            if (this.canBlock()) {
                player.setHeldItemInUse();
                return true;
            } else {
                return false;
            }
        }
        return flag;
    }
}
