package com.github.FlyBird.BetterMite.sound;

import net.minecraft.StepSound;

public class StepSoundSweetBerryBush extends StepSound {
    public StepSoundSweetBerryBush(String string, float volume, float pitch) {
        super(string, volume, pitch);
    }

    private float volume=0.8f;
    private float pitch=1.0f;
    @Override
    public String getBreakSound() {
        volume=0.8f;
        return "block.sweet_berry_bush.break";
    }

    @Override
    public String getPlaceSound() {
        pitch=1.1f;
        return "block.sweet_berry_bush.place";
    }

    @Override
    public String getStepSound() {
        pitch=1.0f;
        return "step.grass";
    }

    @Override
    public float getVolume() {
        return this.volume;
    }
}
