package com.github.FlyBird.BetterMite.mixins.entity;

import com.github.FlyBird.BetterMite.item.ItemSponge;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(TileEntityFurnace.class)
public abstract class TileEntityFurnaceMixin extends TileEntity {
    @Shadow public abstract ItemStack getInputItemStack();

    @Shadow public abstract ItemStack getFuelItemStack();

    @Shadow private ItemStack[] furnaceItemStacks;

    @Shadow @Final public static int FUEL;

    @Inject(method = "smeltItem",at= @At(value = "INVOKE", target = "Lnet/minecraft/FurnaceRecipes;getSmeltingResult(Lnet/minecraft/ItemStack;I)Lnet/minecraft/ItemStack;",shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void smeltItemI(int heat_level, CallbackInfo ci){
        if(this.getInputItemStack()!=null&&this.getInputItemStack().getItem() instanceof ItemSponge&&this.getFuelItemStack()!=null&&this.getFuelItemStack().getItem() instanceof ItemBucket bucket){
            if(bucket.getContents()==null)this.furnaceItemStacks[FUEL]=new ItemStack(bucket.getPeerForContents(Material.water));
            else if(bucket.getContents()==Material.lava)this.furnaceItemStacks[FUEL]=new ItemStack(bucket.getPeerForContents(Material.stone));
        }
    }
    @Inject(method = "isItemFuel",at=@At("RETURN"), cancellable = true)
    private void canInsertItemI(ItemStack item_stack, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(cir.getReturnValue()||item_stack!=null&&item_stack.getItem() instanceof ItemBucket);
    }
}
