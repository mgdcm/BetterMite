package com.github.FlyBird.BetterMite.api;

import net.minecraft.NBTTagCompound;

public interface BetterMiteEntity {


    default NBTTagCompound getEntityData()
    {
        return null;
    }
}
