package com.github.FlyBird.BetterMite.mixins.GUI;

import com.github.FlyBird.BetterMite.block.BlockWoodWorkBench;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ContainerWorkbench.class)
public abstract class ContainerWorkbenchMixin extends MITEContainerCrafting {
    @Shadow public int x;

    @Shadow public int y;

    @Shadow public int z;

    public ContainerWorkbenchMixin(EntityPlayer player) {
        super(player);
    }
    /**
     * @author
     * @reason
     */
    @Overwrite
    public int getBlockMetadata() {
        return (this.world.getBlock(this.x,this.y,this.z) instanceof BlockWoodWorkBench)?this.world.getBlockMetadata(this.x, this.y, this.z)<4?0:13:this.world.getBlockMetadata(this.x, this.y, this.z);
    }
    @Inject(method = "canInteractWith",at=@At("RETURN"),cancellable = true)
    public void canInteractWith(EntityPlayer par1EntityPlayer, CallbackInfoReturnable<Boolean> cir) {
        if(!cir.getReturnValueZ())cir.setReturnValue(this.world.getBlock(this.x, this.y, this.z) instanceof BlockWoodWorkBench && par1EntityPlayer.getDistanceSq((double) this.x + 0.5, (double) this.y + 0.5, (double) this.z + 0.5) <= 64.0);
    }
}
