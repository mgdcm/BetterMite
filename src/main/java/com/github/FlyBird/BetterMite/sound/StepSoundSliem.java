package com.github.FlyBird.BetterMite.sound;

import net.minecraft.StepSound;

//"mob.slime.big"
public class StepSoundSliem extends StepSound {
    public StepSoundSliem(String name, float volume, float pitch) {
        super(name, volume, pitch);
    }

    @Override
    public String getBreakSound() {

        return "mob.slime.big";
    }

    @Override
    public String getPlaceSound() {

        return "mob.slime.big";
    }

    @Override
    public String getStepSound() {
        return "mob.slime.big";
    }


}
