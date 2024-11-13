package com.github.FlyBird.BetterMite.mixins.entity;

import com.github.FlyBird.BetterMite.entity.EntityArmorStand;
import com.github.FlyBird.BetterMite.entity.EntityNewBoat;
import com.github.FlyBird.BetterMite.entity.EntityNewBoatSeat;
import com.github.FlyBird.BetterMite.entity.EntityNewBoatWithChest;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityTrackerEntry.class)
public abstract class EntityTrackerEntryMixin {
    @Shadow
    public Entity myEntity;

    @Inject(method = "getPacketForThisEntity", at = @At("HEAD"), cancellable = true)
    private void getPacketForThisEntity(CallbackInfoReturnable<Packet> cbi) {
        // 保持最初的isDead判断
        if (this.myEntity.isDead) {
            this.myEntity.worldObj.getWorldLogAgent().logWarning("Fetching addPacket for removed entity");
        }

        // 一堆if else
        if (this.myEntity instanceof EntityArmorStand) {
            Packet ret = new Packet24MobSpawn((EntityArmorStand) this.myEntity);
            cbi.setReturnValue(ret);
        }
        if(this.myEntity instanceof EntityNewBoat  )
        {
            Packet ret = new Packet23VehicleSpawn(this.myEntity, /*type*/200);
            cbi.setReturnValue(ret);
        }
        if(this.myEntity instanceof EntityNewBoatSeat)
        {
            Packet ret = new Packet23VehicleSpawn(this.myEntity, /*type*/201);
            cbi.setReturnValue(ret);
        }
        if(this.myEntity instanceof EntityNewBoatWithChest)
        {
            Packet ret = new Packet23VehicleSpawn(this.myEntity, /*type*/202);
            cbi.setReturnValue(ret);
        }
    }
}
