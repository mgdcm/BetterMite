package com.github.FlyBird.BetterMite.config;

import com.github.FlyBird.BetterMite.api.BetterMiteChunkProviderServer;
import com.github.FlyBird.BetterMite.entity.EntityArmourStand;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.ConfigTab;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.interfaces.IValueChangeCallback;
import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.util.JsonUtils;
import net.minecraft.ChunkCoordIntPair;
import net.minecraft.ChunkProviderServer;
import net.minecraft.KeyBinding;
import net.minecraft.WorldServer;

import java.util.ArrayList;
import java.util.List;

import static com.github.FlyBird.BetterMite.BetterMiteStart.hashSetEntityArmourStand;


public class BetterMiteConfigs extends SimpleConfigs {
    public static final ConfigBoolean ArmourStandLoadChunk = new ConfigBoolean("盔甲架加载区块", false);
    public static final List<ConfigBase<?>> hide;
    public static final List<ConfigTab> tabs = new ArrayList<>();
    private static final BetterMiteConfigs Instance;
    static {
        hide = List.of(ArmourStandLoadChunk);
        ArmourStandLoadChunk.setValueChangeCallback(new IValueChangeCallback<ConfigBoolean>() {
            @Override
            public void onValueChanged(ConfigBoolean configBoolean) {
                if (hashSetEntityArmourStand != null){   //有盔甲架
                    if (ArmourStandLoadChunk.getBooleanValue())//开启盔甲架区块加载
                    {
                        for (EntityArmourStand entity : hashSetEntityArmourStand) {
                            WorldServer worldServer = entity.getWorldServer();
                            ChunkProviderServer theChunkProviderServer = worldServer.theChunkProviderServer;
                            BetterMiteChunkProviderServer chunkProviderServer1 = (BetterMiteChunkProviderServer) theChunkProviderServer;
                            int chunk_x = (int) (entity.posX) >> 4;
                            int chunk_z = (int) (entity.posZ) >> 4;
                            long chunk = ChunkCoordIntPair.chunkXZ2Int(chunk_x, chunk_z);
                            chunkProviderServer1.addChunk(chunk);
                            theChunkProviderServer.loadChunk(chunk_x, chunk_z);
                        }
                    } else {
                        for (EntityArmourStand entity : hashSetEntityArmourStand) {
                            WorldServer worldServer = entity.getWorldServer();
                            ChunkProviderServer theChunkProviderServer = worldServer.theChunkProviderServer;
                            BetterMiteChunkProviderServer chunkProviderServer1 = (BetterMiteChunkProviderServer) theChunkProviderServer;
                            int chunk_x = (int) (entity.posX) >> 4;
                            int chunk_z = (int) (entity.posZ) >> 4;
                            long chunk = ChunkCoordIntPair.chunkXZ2Int(chunk_x, chunk_z);

                            chunkProviderServer1.removeChunk(chunk);
                            theChunkProviderServer.unloadChunksIfNotNearSpawn(chunk_x, chunk_z);
                        }

                    }
                }
            }
        });

        List<ConfigBase<?>> values = new ArrayList<>();
        values.addAll(hide);
        tabs.add(new ConfigTab("通用", hide));// TODO rename it
        Instance = new BetterMiteConfigs("BetterMite", null, values);
    }
    public BetterMiteConfigs(String name, List<ConfigHotkey> hotkeys, List<?> values) {
        super(name, hotkeys, values);
    }


    @Override
    public List<ConfigTab> getConfigTabs() {
        return tabs;
    }

    public static BetterMiteConfigs getInstance() {
        return Instance;
    }

    @Override
    public void save() {
        JsonObject configRoot = new JsonObject();
        ConfigUtils.writeConfigBase(configRoot, "Values", hide);
    /*    JsonObject valuesRoot = JsonUtils.getNestedObject(configRoot, "Values", true);
        assert valuesRoot != null;
        ConfigUtils.writeConfigBase(valuesRoot, "pos", pos);*/
        JsonUtils.writeJsonToFile(configRoot, this.optionsFile);
    }

    public void load() {
        if (!this.optionsFile.exists()) {
            this.save();
        } else {
            JsonElement jsonElement = JsonUtils.parseJsonFile(this.optionsFile);
            if (jsonElement != null && jsonElement.isJsonObject()) {
                JsonObject obj = jsonElement.getAsJsonObject();
                ConfigUtils.readConfigBase(obj, "HotKeys", this.hotkeys);
                ConfigUtils.readConfigBase(obj, "Values", this.values);
                this.save();
                KeyBinding.resetKeyBindingArrayAndHash();
            }

        }
    }
}
