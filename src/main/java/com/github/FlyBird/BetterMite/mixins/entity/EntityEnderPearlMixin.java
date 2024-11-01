package com.github.FlyBird.BetterMite.mixins.entity;

import com.github.FlyBird.BetterMite.entity.EntityEndermite;
import net.minecraft.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityEnderPearl.class)
public class EntityEnderPearlMixin   {
    EntityEnderPearl This= (EntityEnderPearl)(Object)this;

    @Inject(method = {"onImpact"}, at=@At(value="INVOKE",target="Lnet/minecraft/EntityEnderPearl;getThrower()Lnet/minecraft/EntityLivingBase;",ordinal = 6))
    protected void onImpact(RaycastCollision rc, CallbackInfo ci) {
        if (This.getThrower().getRNG().nextFloat() < 0.05F && This.getThrower().worldObj.getGameRules().getGameRuleBooleanValue("doMobSpawning")) {
            EntityEndermite entityendermite = new EntityEndermite(This.getThrower().worldObj);
            entityendermite.setSpawnedByPlayer(true);
            entityendermite.setLocationAndAngles(This.posX, This.posY, This.posZ, This.rotationYaw, This.rotationPitch);
            This.worldObj.spawnEntityInWorld(entityendermite);
        }

    }


}
