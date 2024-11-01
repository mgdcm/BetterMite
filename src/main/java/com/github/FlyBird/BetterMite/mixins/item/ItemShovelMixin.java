package com.github.FlyBird.BetterMite.mixins.item;


import com.github.FlyBird.BetterMite.block.Blocks;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;


@Mixin({ItemShovel.class})
public abstract class ItemShovelMixin extends ItemTool {

    protected ItemShovelMixin(int par1, Material material) {
        super(par1, material);
    }

    private static boolean tryOutFire(World world, int x, int y, int z, EnumFace face, EntityPlayer player, ItemStack item_stack) {
        if (!player.canPlayerEdit(x, y, z, item_stack)) {
            return false;
        }
        if (face.isBottom()) {
            return false;
        }
        Block block = world.getBlock(x, y, z);
        int metadata=world.getBlockMetadata(x, y, z);
        if (!world.isAirBlock(x, y + 1, z) && world.getBlockWithRefreshedBounds(x, y + 1, z).getBlockBoundsMinY(Minecraft.getThreadIndex()) <= 0.0) {
            return false;
        }
        if (block != Blocks.campfire && block != Blocks.soulCampfire) {
            return false;
        }
        if (player.onClient()) {
            player.swingArm();
            Minecraft.theMinecraft.playerController.setUseButtonDelayOverride(200);
        } else {
            //Auxiliary sound    1004灭火声音
            world.playAuxSFXAtEntity(null, 1004, x, y, z, 0);
            player.tryDamageHeldItem(DamageSource.generic, 25);
            world.setBlock(x, y, z, Blocks.normalCampfire.blockID, metadata, 2);
        }
        return true;
    }


    private static boolean tryTillSoil(World world, int x, int y, int z, EnumFace face, EntityPlayer player, ItemStack item_stack) {
        if (!player.canPlayerEdit(x, y, z, item_stack)) {
            return false;
        }
        if (face.isBottom()) {
            return false;
        }
        Block block = world.getBlock(x, y, z);
        if (!world.isAirBlock(x, y + 1, z) && world.getBlockWithRefreshedBounds(x, y + 1, z).getBlockBoundsMinY(Minecraft.getThreadIndex()) <= 0.0) {
            return false;
        }
        if (block != Block.grass && block != Block.dirt) {
            return false;
        }
        if (player.onClient()) {
            player.swingArm();
            Minecraft.theMinecraft.playerController.setUseButtonDelayOverride(200);
        } else {
            world.playSoundAtBlock(x, y, z, Block.tilledField.stepSound.getStepSound(), (Block.tilledField.stepSound.getVolume() + 1.0f) / 2.0f, Block.tilledField.stepSound.getPitch() * 0.8f);
            player.tryDamageHeldItem(DamageSource.generic, 25);
            player.addHungerServerSide(world.getBlockHardness(x, y, z) / 2.0f * EnchantmentHelper.getEnduranceModifier(player));
            world.setBlock(x, y, z, Blocks.dirtPath.blockID);
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
        if (rc.face_hit.isTop()||rc.face_hit.isSide()) {
            boolean flag1=tryTillSoil(rc.world, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, rc.face_hit, player, player.getHeldItemStack());
            boolean flag2=tryOutFire(rc.world, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, rc.face_hit, player, player.getHeldItemStack());
            if(!flag1&&!flag2)
            {
                if (this.canBlock()) {
                    player.setHeldItemInUse();
                    return true;
                } else {
                    return false;
                }
            }
            else
                return true;
        }
        else
        {
            if (this.canBlock()) {
                player.setHeldItemInUse();
                return true;
            } else {
                return false;
            }
        }

    }
}
