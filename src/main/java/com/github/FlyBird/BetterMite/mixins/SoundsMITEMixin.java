package com.github.FlyBird.BetterMite.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.SoundsMITE;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;


@Mixin({SoundsMITE.class})
public abstract class SoundsMITEMixin {
    @Shadow
    private List<String> sounds = new ArrayList<>();

    @Inject(method = {"<init>(Lnet/minecraft/SoundManager;)V"}, at = {@At("RETURN")})
    public void injectCtor(CallbackInfo callbackInfo) {
        add("sound/block/barrel/open1.ogg");
        add("sound/block/barrel/open2.ogg");
        add("sound/block/barrel/close.ogg");

        add("sound/block/chain/break1.ogg");
        add("sound/block/chain/break2.ogg");
        add("sound/block/chain/break3.ogg");
        add("sound/block/chain/break4.ogg");
        add("sound/block/chain/step1.ogg");
        add("sound/block/chain/step2.ogg");
        add("sound/block/chain/step3.ogg");
        add("sound/block/chain/step4.ogg");
        add("sound/block/chain/step5.ogg");
        add("sound/block/chain/step6.ogg");

        add("sound/dig/newwood1.ogg");
        add("sound/dig/newwood2.ogg");
        add("sound/dig/newwood3.ogg");
        add("sound/dig/newwood4.ogg");
        add("sound/step/newwood1.ogg");
        add("sound/step/newwood2.ogg");
        add("sound/step/newwood3.ogg");
        add("sound/step/newwood4.ogg");
        add("sound/step/newwood5.ogg");
        add("sound/step/newwood6.ogg");

        add("sound/block/lantern/break1.ogg");
        add("sound/block/lantern/break2.ogg");
        add("sound/block/lantern/break3.ogg");
        add("sound/block/lantern/break4.ogg");
        add("sound/block/lantern/break5.ogg");
        add("sound/block/lantern/break6.ogg");
        add("sound/block/lantern/place1.ogg");
        add("sound/block/lantern/place2.ogg");
        add("sound/block/lantern/place3.ogg");
        add("sound/block/lantern/place4.ogg");
        add("sound/block/lantern/place5.ogg");
        add("sound/block/lantern/place6.ogg");

        add("sound/block/sweet_berry_bush/break1.ogg");
        add("sound/block/sweet_berry_bush/break2.ogg");
        add("sound/block/sweet_berry_bush/break3.ogg");
        add("sound/block/sweet_berry_bush/break4.ogg");
        add("sound/block/sweet_berry_bush/place1.ogg");
        add("sound/block/sweet_berry_bush/place2.ogg");
        add("sound/block/sweet_berry_bush/place3.ogg");
        add("sound/block/sweet_berry_bush/place4.ogg");
        add("sound/block/sweet_berry_bush/place5.ogg");
        add("sound/block/sweet_berry_bush/place6.ogg");

        add("sound/item/sweet_berries/pick_from_bush1.ogg");
        add("sound/item/sweet_berries/pick_from_bush2.ogg");

        add("sound/mob/rabbit/bunnymurder.ogg");
        add("sound/mob/rabbit/hop1.ogg");
        add("sound/mob/rabbit/hop2.ogg");
        add("sound/mob/rabbit/hop3.ogg");
        add("sound/mob/rabbit/hop4.ogg");
        add("sound/mob/rabbit/hurt1.ogg");
        add("sound/mob/rabbit/hurt2.ogg");
        add("sound/mob/rabbit/hurt3.ogg");
        add("sound/mob/rabbit/hurt4.ogg");
        add("sound/mob/rabbit/idle4.ogg");
        add("sound/mob/rabbit/idle2.ogg");
        add("sound/mob/rabbit/idle3.ogg");
        add("sound/mob/rabbit/idle4.ogg");

        add("sound/block/campfire/crackle1.ogg");
        add("sound/block/campfire/crackle2.ogg");
        add("sound/block/campfire/crackle3.ogg");
        add("sound/block/campfire/crackle4.ogg");
        add("sound/block/campfire/crackle5.ogg");
        add("sound/block/campfire/crackle6.ogg");

        add("sound/entity/boat/paddle_land1.ogg");
        add("sound/entity/boat/paddle_land2.ogg");
        add("sound/entity/boat/paddle_land3.ogg");
        add("sound/entity/boat/paddle_land4.ogg");
        add("sound/entity/boat/paddle_land5.ogg");
        add("sound/entity/boat/paddle_land6.ogg");

        add("sound/entity/boat/paddle_water1.ogg");
        add("sound/entity/boat/paddle_water2.ogg");
        add("sound/entity/boat/paddle_water3.ogg");
        add("sound/entity/boat/paddle_water4.ogg");
        add("sound/entity/boat/paddle_water5.ogg");
        add("sound/entity/boat/paddle_water6.ogg");
        add("sound/entity/boat/paddle_water7.ogg");
        add("sound/entity/boat/paddle_water8.ogg");

        add("sound/entity/armorstand/break1.ogg");
        add("sound/entity/armorstand/break2.ogg");
        add("sound/entity/armorstand/break3.ogg");
        add("sound/entity/armorstand/break4.ogg");

        add("sound/entity/armorstand/hit1.ogg");
        add("sound/entity/armorstand/hit2.ogg");
        add("sound/entity/armorstand/hit3.ogg");
        add("sound/entity/armorstand/hit4.ogg");
    }

    @Shadow
    private boolean add(String path) {
        return this.sounds.add(path);
    }

    //解决不能读取jar包内声音的问题
    @ModifyExpressionValue(method = "load", at = @At(value = "INVOKE", target = "Lnet/minecraft/ResourcePack;resourceExists(Lnet/minecraft/ResourceLocation;)Z"))
    private boolean widen(boolean original) {
        return true;
    }
}

