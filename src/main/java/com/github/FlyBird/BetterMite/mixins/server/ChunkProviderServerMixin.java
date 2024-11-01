package com.github.FlyBird.BetterMite.mixins.server;


import com.github.FlyBird.BetterMite.api.BetterMiteChunkProviderServer;
import com.github.FlyBird.BetterMite.config.BetterMiteConfigs;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.ChunkProviderServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.HashSet;
import java.util.Set;

@Mixin({ChunkProviderServer.class})
public abstract class ChunkProviderServerMixin implements BetterMiteChunkProviderServer {
  @Unique
  private Set<Long> forceLoadChunks = new HashSet<>();
  
  @WrapWithCondition(method = {"unloadChunksIfNotNearSpawn"}, at = {@At(value = "INVOKE", target = "Ljava/util/Set;add(Ljava/lang/Object;)Z")})
  private <E> boolean prevent(Set<?> instance, E e) {
    return !isChunkForceLoaded(((Long)e).longValue());
  }
  
  public void addChunk(long chunk) {
    this.forceLoadChunks.add(Long.valueOf(chunk));
  }
  
  public void removeChunk(long chunk) {
    this.forceLoadChunks.remove(Long.valueOf(chunk));
  }
  
  public void clearChunks() {
    this.forceLoadChunks.clear();
  }
  
  public Set<Long> getChunks() {
    return this.forceLoadChunks;
  }
  
  public boolean isChunkForceLoaded(long chunk) {
    return this.forceLoadChunks.contains(Long.valueOf(chunk));
  }
}
