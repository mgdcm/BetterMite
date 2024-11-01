package com.github.FlyBird.BetterMite.mixins.block;


import com.github.FlyBird.BetterMite.block.Blocks;
import net.minecraft.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Block.class)
public class canBePlacedOnBlockMixin {
    @ModifyVariable(method = "canBePlacedOnBlock", at = @At("HEAD"), ordinal = 1)
    private Block injected(Block block_below) {
        if(block_below== Blocks.dirtPath)
            return Block.tilledField;
        else
            return block_below;
    }

}
