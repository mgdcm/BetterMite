package com.github.FlyBird.BetterMite.mixins.entity;

import com.github.FlyBird.BetterMite.entity.EntityArmorStand;
import com.github.FlyBird.BetterMite.entity.EntityNewBoat;
import com.github.FlyBird.BetterMite.entity.EntityNewBoatSeat;
import com.github.FlyBird.BetterMite.entity.EntityNewBoatWithChest;
import net.minecraft.Entity;
import net.minecraft.EntityTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityTracker.class)
public abstract class EntityTrackerMixin {
    @Shadow
    public abstract void addEntityToTracker(Entity par1Entity, int par2, int par3, boolean par4);

    @Inject(method = "addEntityToTracker(Lnet/minecraft/Entity;)V", at = @At("HEAD"), cancellable = true)
    public void addEntityToTracker_Inject(Entity par1Entity, CallbackInfo cbi) {
        if (par1Entity instanceof EntityArmorStand) {
            this.addEntityToTracker(par1Entity, /*entityViewDistance*/ 64, /*updateFrequency*/ 5, true);
            cbi.cancel();
        }
        if (par1Entity instanceof EntityNewBoat||par1Entity instanceof EntityNewBoatSeat||par1Entity instanceof EntityNewBoatWithChest) {
            this.addEntityToTracker(par1Entity, /*entityViewDistance*/ 80, /*updateFrequency*/ 3, true);
            cbi.cancel();
        }
    }
}
