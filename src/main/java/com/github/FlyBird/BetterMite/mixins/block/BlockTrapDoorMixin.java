package com.github.FlyBird.BetterMite.mixins.block;

import net.minecraft.BlockTrapDoor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockTrapDoor.class)
public class BlockTrapDoorMixin {

  /*  @Inject(method = "isOpen", at = @At("HEAD"))
    public final void isOpen(int metadata, CallbackInfoReturnable<Boolean> cir) {
            boolean flag=(metadata & 4) != 0;
            if(flag)
                ((BlockTrapDoor)(Object)this).setLightOpacity(0);
            else
                ((BlockTrapDoor)(Object)this).setLightOpacity(0);
    }*/
}
