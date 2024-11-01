package com.github.FlyBird.BetterMite.block;

import com.github.FlyBird.BetterMite.misc.EnumWoodType;
import net.minecraft.*;

public class BlockTrapDoors extends BlockTrapDoor {
    private final EnumWoodType woodType;

    protected BlockTrapDoors(int par1, EnumWoodType type) {
        super(par1, Material.wood);
        this.woodType = type;
        //this.setLightOpacity(0);
    }

    public EnumWoodType getWoodType() {
        return woodType;
    }
}
