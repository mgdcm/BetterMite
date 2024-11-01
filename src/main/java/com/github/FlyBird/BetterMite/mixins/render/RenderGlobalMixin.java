package com.github.FlyBird.BetterMite.mixins.render;



import com.github.FlyBird.BetterMite.entityFX.EntityBigSmokeFX;
import com.github.FlyBird.BetterMite.entityFX.EntitySoulFlameFX;

import com.github.FlyBird.BetterMite.misc.EnumParticles;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderGlobal.class)

public class RenderGlobalMixin {
@Shadow private WorldClient theWorld;

@Shadow private Minecraft mc;

@Inject(method ="doSpawnParticle",at=@At(value = "RETURN",ordinal = 3))
    private void doSpawnParticle(EnumParticle enum_particle, int index, int metadata, double posX, double posY, double posZ, double par8, double par10, double par12, CallbackInfoReturnable<EntityFX> cir) {
        EntityFX var21;
        if (enum_particle ==  EnumParticles.soulFlame) {
            var21 = new EntitySoulFlameFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
            this.mc.effectRenderer.addEffect(var21);
        }
        if(enum_particle==EnumParticles.largerSmoke) {
            var21 = new EntityBigSmokeFX(this.theWorld, (int)posX, (int)posY, (int)posZ, false);
            this.mc.effectRenderer.addEffect(var21);
        }

    }

}



