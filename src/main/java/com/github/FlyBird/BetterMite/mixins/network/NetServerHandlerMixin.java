package com.github.FlyBird.BetterMite.mixins.network;

import com.github.FlyBird.BetterMite.api.BetterMiteNetHandler;
import com.github.FlyBird.BetterMite.entity.EntityArmorStand;
import com.github.FlyBird.BetterMite.network.ArmourStandInteractMessage;
import com.github.FlyBird.BetterMite.network.BoatMoveMessage;
import net.minecraft.*;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({NetServerHandler.class})
public abstract class NetServerHandlerMixin extends NetHandler implements BetterMiteNetHandler {
  @Shadow
  public ServerPlayer playerEntity;
  
  public void handleArmourStandInteractInfo(ArmourStandInteractMessage packet) {
    World world = this.playerEntity.worldObj;
    EntityArmorStand stand = (EntityArmorStand) world.getEntityByID(packet.standID);
    stand.interact(playerEntity, packet.hitPos);
  }

  @Override
  public void handleBoatMoveInfo(BoatMoveMessage packet) {
    WorldServer vehWorld = this.playerEntity.getWorldServer();
    if (vehWorld != null) {
      Entity vehicle = vehWorld.getEntityByID(packet.entityId);
      if (vehicle == null)
        return;
      if (vehicle.riddenByEntity !=playerEntity) {
        /* Only take position updates from the riding player */
        return ;
      }
      double expectedDelta = vehicle.motionX * vehicle.motionX + vehicle.motionY * vehicle.motionY + vehicle.motionZ * vehicle.motionZ;
      double deltaX = packet.x - vehicle.posX;
      double deltaY = packet.y - vehicle.posY;
      double deltaZ = packet.z - vehicle.posZ;
      double actualDelta = deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
      MinecraftServer server = MinecraftServer.getServer();
      if (Math.abs(expectedDelta - actualDelta) > 100.0D && !server.isSinglePlayer()) {
        System.err.println("Vehicle moved wrongly");
        vehicle.setPositionAndRotation(vehicle.posX - 1, vehicle.posY, vehicle.posZ - 1, vehicle.rotationYaw, vehicle.rotationPitch);
        //ctx.getServerHandler().sendPacket(new S18PacketEntityTeleport(vehicle));
        //playerEntity.sendPacket(new Packet34EntityTeleport(vehicle));
        playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet34EntityTeleport(vehicle));
        return ;
      }
      vehicle.setPositionAndRotation(packet.x, packet.y, packet.z, packet.yaw, packet.pitch);
      playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet34EntityTeleport(vehicle));
    }
  }

  @Shadow
  public boolean isServerHandler() {
    return false;
  }
  
  @Shadow
  public INetworkManager getNetManager() {
    return null;
  }
}
