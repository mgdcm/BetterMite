package com.github.FlyBird.BetterMite.mixins.world.structure;

import com.github.FlyBird.BetterMite.block.Blocks;
import net.minecraft.Block;
import net.minecraft.ComponentVillagePathGen;
import net.minecraft.ComponentVillageRoadPiece;
import org.spongepowered.asm.mixin.Mixin;
@Mixin(ComponentVillagePathGen.class)
public abstract class ComponentVillagePathGenMixin extends ComponentVillageRoadPiece {
    @Override
    protected int getBiomeSpecificBlock(int par1, int par2) {
        if(super.getBiomeSpecificBlock(par1, par2) == Block.gravel.blockID)return Blocks.dirtPath.blockID;
        else return super.getBiomeSpecificBlock(par1, par2);
    }
}
