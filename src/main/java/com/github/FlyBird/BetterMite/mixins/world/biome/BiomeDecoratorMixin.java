package com.github.FlyBird.BetterMite.mixins.world.biome;


import com.github.FlyBird.BetterMite.block.Blocks;
import com.github.FlyBird.BetterMite.world.WorldGeneratorPlant;
import com.github.FlyBird.BetterMite.world.gen.WorldGenMelon;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin({BiomeDecorator.class})
public abstract class BiomeDecoratorMixin {
  @Unique
  protected WorldGeneratorPlant sweetBerruGen;
  @Unique
  protected WorldGeneratorPlant bigGrassGen;
  @Unique
  protected WorldGeneratorPlant fernGen;
  
  @Shadow
  protected Random randomGenerator;
  
  @Shadow
  protected int chunk_X;
  
  @Shadow
  protected int chunk_Z;
  
  @Shadow
  protected World currentWorld;

  @Shadow protected BiomeGenBase biome;
  @Unique
  protected int flowersExtendPerChunk;

  @Inject(method = {"<init>(Lnet/minecraft/BiomeGenBase;)V"}, at = {@At("RETURN")})
  public void BiomeDecorator(CallbackInfo callbackInfo) {
    this.sweetBerruGen = new WorldGeneratorPlant(Blocks.sweetBerryBush.blockID);//实例化甜果丛
    this.bigGrassGen = new WorldGeneratorPlant(Blocks.bigGrass.blockID);
    this.fernGen = new WorldGeneratorPlant(Blocks.tallGrass.blockID);
    this.flowersExtendPerChunk=2;
  }
  

  @Inject(method = {"decorate(Lnet/minecraft/World;Ljava/util/Random;II)V"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/BiomeDecorator;decorate()V", shift = At.Shift.AFTER)})
  private void Injector_world_decorate(CallbackInfo ci) {

    int var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
    int var4 = this.randomGenerator.nextInt(128);
    int var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
    if (this.biome==BiomeGenBase.jungle&&this.randomGenerator.nextInt(8) == 0) {
      (new WorldGenMelon()).generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
    }
    for (int i = 0; i < this.flowersExtendPerChunk; i++) {
      var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
      var4 = this.randomGenerator.nextInt(128);
      var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
      this.sweetBerruGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);

      var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
      var4 = this.randomGenerator.nextInt(128);
      var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
      this.bigGrassGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
      if(this.biome.isFreezing()) {
        var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
        var4 = this.randomGenerator.nextInt(128);
        var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
        this.fernGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
      }

    }


  }

}
