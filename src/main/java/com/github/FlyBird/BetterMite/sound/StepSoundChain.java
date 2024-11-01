package com.github.FlyBird.BetterMite.sound;

import net.minecraft.StepSound;

public class StepSoundChain extends StepSound {
    public StepSoundChain(String string, float volume, float pitch) {
        super(string, volume, pitch);
    }

    private float volume=0.5f;
    @Override
    public String getBreakSound() {
        volume=0.5f;
        return "block.chain.break";
    }

    @Override
    public String getPlaceSound() {
        volume=0.5f;
        return "block.chain.break";
    }

    @Override
    public String getStepSound() {
        volume=1.0f;
        return "block.chain.step";
    }

    @Override
    public float getVolume() {
        return this.volume;
    }
}