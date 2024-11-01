package com.github.FlyBird.BetterMite.api;


import com.github.FlyBird.BetterMite.network.ArmourStandInteractMessage;
import com.github.FlyBird.BetterMite.network.BoatMoveMessage;

public interface BetterMiteNetHandler {

  default void handleArmourStandInteractInfo(ArmourStandInteractMessage packet) {}

  default void handleBoatMoveInfo(BoatMoveMessage packet) {}
}
