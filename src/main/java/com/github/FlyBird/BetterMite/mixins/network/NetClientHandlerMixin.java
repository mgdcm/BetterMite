package com.github.FlyBird.BetterMite.mixins.network;

import com.github.FlyBird.BetterMite.api.BetterMiteNetHandler;
import com.github.FlyBird.BetterMite.entity.EntityNewBoat;
import com.github.FlyBird.BetterMite.entity.EntityNewBoatSeat;
import com.github.FlyBird.BetterMite.entity.EntityNewBoatWithChest;
import net.minecraft.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.SoftOverride;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({NetClientHandler.class})
public abstract class NetClientHandlerMixin extends NetHandler implements BetterMiteNetHandler {
  @Shadow
  private WorldClient worldClient;
  
  @Shadow
  private Minecraft mc;
  
  @Inject(method = {"handleVehicleSpawn"}, at = {@At("HEAD")}, cancellable = true)
  private void BetterMiteEntity(Packet23VehicleSpawn par1Packet23VehicleSpawn, CallbackInfo ci) {
    double x, y, z;
    if (par1Packet23VehicleSpawn.position_set_using_unscaled_integers) {
      x = par1Packet23VehicleSpawn.unscaled_pos_x;
      y = par1Packet23VehicleSpawn.unscaled_pos_y;
      z = par1Packet23VehicleSpawn.unscaled_pos_z;
    } else {
      x = SpatialScaler.getPosX(par1Packet23VehicleSpawn.scaled_pos_x);
      y = SpatialScaler.getPosY(par1Packet23VehicleSpawn.scaled_pos_y);
      z = SpatialScaler.getPosZ(par1Packet23VehicleSpawn.scaled_pos_z);
    }
    if (par1Packet23VehicleSpawn.type == 200) {
      EntityNewBoat entity = new EntityNewBoat(this.worldClient, x, y, z);
      entity.rotationYaw = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_yaw);
      entity.rotationPitch = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_pitch);

      entity.entityId = par1Packet23VehicleSpawn.entityId;
      this.worldClient.addEntityToWorld(par1Packet23VehicleSpawn.entityId, entity);
      if (par1Packet23VehicleSpawn.throwerEntityId > 0) {
        if (par1Packet23VehicleSpawn.type == 60) {

          entity.setVelocity(par1Packet23VehicleSpawn.exact_motion_x, par1Packet23VehicleSpawn.exact_motion_y, par1Packet23VehicleSpawn.exact_motion_z);
          return;
        }

        entity.setVelocity(par1Packet23VehicleSpawn.approx_motion_x, par1Packet23VehicleSpawn.approx_motion_y, par1Packet23VehicleSpawn.approx_motion_z);
      }

      // 不再继续执行原版的判断
      ci.cancel();
    }
    if(par1Packet23VehicleSpawn.type == 201)
    {
      EntityNewBoatSeat entity = new EntityNewBoatSeat(this.worldClient,x,y,z);
      entity.rotationYaw = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_yaw);
      entity.rotationPitch = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_pitch);

      entity.entityId = par1Packet23VehicleSpawn.entityId;
      this.worldClient.addEntityToWorld(par1Packet23VehicleSpawn.entityId, entity);
      if (par1Packet23VehicleSpawn.throwerEntityId > 0) {
        if (par1Packet23VehicleSpawn.type == 60) {
          entity.setVelocity(par1Packet23VehicleSpawn.exact_motion_x, par1Packet23VehicleSpawn.exact_motion_y, par1Packet23VehicleSpawn.exact_motion_z);
          return;
        }
        entity.setVelocity(par1Packet23VehicleSpawn.approx_motion_x, par1Packet23VehicleSpawn.approx_motion_y, par1Packet23VehicleSpawn.approx_motion_z);
      }
      // 不再继续执行原版的判断
      ci.cancel();
    }
    if(par1Packet23VehicleSpawn.type == 202)
    {
      EntityNewBoatWithChest entity = new EntityNewBoatWithChest(this.worldClient,x,y,z);
      entity.rotationYaw = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_yaw);
      entity.rotationPitch = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_pitch);

      entity.entityId = par1Packet23VehicleSpawn.entityId;
      this.worldClient.addEntityToWorld(par1Packet23VehicleSpawn.entityId, entity);
      if (par1Packet23VehicleSpawn.throwerEntityId > 0) {
        if (par1Packet23VehicleSpawn.type == 60) {
          entity.setVelocity(par1Packet23VehicleSpawn.exact_motion_x, par1Packet23VehicleSpawn.exact_motion_y, par1Packet23VehicleSpawn.exact_motion_z);
          return;
        }
        entity.setVelocity(par1Packet23VehicleSpawn.approx_motion_x, par1Packet23VehicleSpawn.approx_motion_y, par1Packet23VehicleSpawn.approx_motion_z);
      }
      // 不再继续执行原版的判断
      ci.cancel();
    }
  }
  

  @Shadow
  private Entity getEntityByID(int par1) {
    return null;
  }
}
