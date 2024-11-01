package com.github.FlyBird.BetterMite.api;

import java.util.Set;

public interface BetterMiteChunkProviderServer {
  void addChunk(long paramLong);
  
  void removeChunk(long paramLong);
  
  void clearChunks();
  
  Set<Long> getChunks();
  
  boolean isChunkForceLoaded(long paramLong);
}
