package com.github.FlyBird.BetterMite.mixins.block;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockPumpkin.class)
public abstract class BlockPumpkinMixin extends BlockDirectional {
    @Shadow private Icon field_94475_c;

    protected BlockPumpkinMixin(int id, Material material, BlockConstants block_constants) {
        super(id, material, block_constants);
    }
    @Inject(method = "registerIcons",at=@At("RETURN"))
    public void registerIcons(IconRegister par1IconRegister, CallbackInfo ci) {
        //this.field_94475_c = this.blockIcon;
    }
}
