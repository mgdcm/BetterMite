package com.github.FlyBird.BetterMite.mixins.item;

import com.github.FlyBird.BetterMite.block.BlockBigGrass;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ItemShears.class)
public abstract class ItemShearsMixin extends ItemTool {

    protected ItemShearsMixin(int par1, Material material) {
        super(par1, material);
    }
    @Inject(method = "onItemRightClick",at= @At(value = "INVOKE", target = "Lnet/minecraft/BlockBreakInfo;dropBlockAsItself(Z)I"),locals = LocalCapture.CAPTURE_FAILSOFT,cancellable = true)
    public void bigGrassShearsDestroy(EntityPlayer player, float partial_tick, boolean ctrl_is_down, CallbackInfoReturnable<Boolean> cir, RaycastCollision rc, Block block, World world, int x, int y, int z, BlockBreakInfo info){
        if(world.getBlock(x,y-1,z) instanceof BlockBigGrass&&(world.getBlockMetadata(x,y-1,z)&1)==1){
            info.dropBlockAsItself(true);
            world.setBlockToAir(x,y-1,z);
            world.playSoundAtBlock(x, y, z, "mob.sheep.shear", 1.0F, 1.0F);
            player.tryDamageHeldItem(DamageSource.generic, this.getToolDecayFromBreakingBlock(info));
            cir.setReturnValue(true);
        }
    }
}
