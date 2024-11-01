package com.github.FlyBird.BetterMite;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.xiaoyu233.fml.util.EnumExtends;
import org.lwjgl.opengl.EXTSecondaryColor;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

public class EarlyRiser implements PreLaunchEntrypoint {

    public void onPreLaunch() {
        EnumExtends.PARTICLE.addEnum("soul_fire_flame");
        EnumExtends.PARTICLE.addEnum("big_smoke");
    }
}
