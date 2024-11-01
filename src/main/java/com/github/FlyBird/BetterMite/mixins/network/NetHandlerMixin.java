package com.github.FlyBird.BetterMite.mixins.network;

import com.github.FlyBird.BetterMite.api.BetterMiteNetHandler;
import com.github.FlyBird.BetterMite.network.ArmourStandInteractMessage;
import com.github.FlyBird.BetterMite.network.BoatMoveMessage;
import net.minecraft.NetHandler;

import org.spongepowered.asm.mixin.Mixin;

@Mixin({NetHandler.class})
public class NetHandlerMixin implements BetterMiteNetHandler {

  public void handleEnchantmentInfo(ArmourStandInteractMessage packet) {}

  public void handleBoatMoveInfo(BoatMoveMessage packet) {}
}
