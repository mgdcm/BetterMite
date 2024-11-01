package com.github.FlyBird.BetterMite;

import com.github.FlyBird.BetterMite.config.BetterMiteConfigs;
import com.github.FlyBird.BetterMite.entity.EntityArmourStand;
import fi.dy.masa.malilib.config.ConfigManager;
import net.fabricmc.api.ModInitializer;
import net.xiaoyu233.fml.reload.event.MITEEvents;

import java.util.HashSet;
import java.util.Set;

public class BetterMiteStart implements ModInitializer {
    public static Set<EntityArmourStand> hashSetEntityArmourStand=new HashSet<EntityArmourStand>();
    public static final String MOD_ID="BetterMite";

    @Override
    public void onInitialize() {   //相当于main函数，万物起源
        MITEEvents.MITE_EVENT_BUS.register(new EventListen());//注册一个事件监听
        BetterMiteConfigs.getInstance().load();
        ConfigManager.getInstance().registerConfig(BetterMiteConfigs.getInstance());
    }


}