package com.github.FlyBird.BetterMite.mixins.entity;


import com.github.FlyBird.BetterMite.entity.EntityRabbit;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.Random;

//performWorldGenSpawning
@Mixin(SpawnerAnimals.class)
public class SpawnerAnimalsMixin {


    @Inject(method = {"performWorldGenSpawning"}, at=@At(value="INVOKE",target="Lnet/minecraft/World;spawnEntityInWorld(Lnet/minecraft/Entity;)Z"),locals = LocalCapture.CAPTURE_FAILHARD)
    private static void performWorldGenSpawning(World world, BiomeGenBase biome, EnumCreatureType creature_type, int min_x, int min_z, int range_x, int range_z, Random random, CallbackInfo ci, List creatures, SpawnListEntry entry, EntityLivingData entity_living_data, int group_size, int x, int z, int initial_x, int initial_z, boolean is_animal, int group_size_spawned, int i, boolean spawn_successful, int attempt, int y, double[] resulting_y_pos, Chunk chunk, float pos_x, float pos_y, float pos_z, EntityLiving var22, int blocks_high, boolean placement_prevented) {
        if(var22 instanceof EntityRabbit)
        {
            EntityRabbit entityRabbit=(EntityRabbit)var22;
            if(biome instanceof BiomeGenDesert)
                entityRabbit.setRabbitType(4);
            if(biome instanceof BiomeGenSnow)
            {
                System.out.println("rabbit x:"+entityRabbit.posX+"z:"+entityRabbit.posZ);
                entityRabbit.setRabbitType(1);
            }
                
            //System.out.println("rabbit x:"+entityRabbit.posX+"z:"+entityRabbit.posZ);
        }
    }
    @Inject(method={"canCreatureTypeSpawnOn"},at=@At(value= "RETURN",ordinal=5), cancellable = true)
    private static void canCreatureTypeSpawnOn( EnumCreatureType creature_type, Block block, int metadata, boolean initial_spawn,CallbackInfoReturnable<Boolean> cir) {
       if(block==Block.sand||block==Block.grass)
           cir.setReturnValue(true);
    }
}
