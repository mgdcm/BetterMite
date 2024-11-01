package com.github.FlyBird.BetterMite.mixins.entity;

import com.github.FlyBird.BetterMite.block.Blocks;
import net.minecraft.Block;
import net.minecraft.EntityCow;
import net.minecraft.EntityLivestock;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EntityCow.class)
public abstract class EntityCowMixin extends EntityLivestock {
    public EntityCowMixin(World world) {
        super(world);
    }
    @Inject(method="isFoodSource",at=@At("RETURN"),locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    protected void isFoodSource(int block_id, CallbackInfoReturnable<Boolean> cir, Block block) {
        cir.setReturnValue(cir.getReturnValue()||block == Blocks.bigGrass);
    }

    @Inject(method="getFoodBlockIDs",at=@At("RETURN"),locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    protected void getFoodBlockIDs(CallbackInfoReturnable<int[]> cir, int[] block_ids) {
        cir.setReturnValue(new int[]{Block.tallGrass.blockID, Block.plantYellow.blockID, Blocks.bigGrass.blockID});
    }
}
