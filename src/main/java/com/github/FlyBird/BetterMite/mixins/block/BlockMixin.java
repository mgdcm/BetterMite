package com.github.FlyBird.BetterMite.mixins.block;

import com.github.FlyBird.BetterMite.item.ItemSponge;
import net.minecraft.Block;
import net.minecraft.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {
    @Shadow @Final public static Block sponge;

    @Inject(method="<clinit>",at=@At("TAIL"))
    private static void clinit(CallbackInfo ci){
        Item.itemsList[sponge.blockID] = new ItemSponge(sponge);
    }
}
