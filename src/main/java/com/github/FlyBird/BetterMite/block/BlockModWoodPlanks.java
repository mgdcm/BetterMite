package com.github.FlyBird.BetterMite.block;

import net.minecraft.BlockWood;
import net.minecraft.Icon;
import net.minecraft.IconRegister;

public class BlockModWoodPlanks extends BlockWood {
    public final String id;
    public BlockModWoodPlanks(int par1,String id) {
        super(par1);
        this.id=id;
        this.setResistance(5.0F);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("planks");
    }
    protected Icon icon;
    public boolean isValidMetadata(int metadata) {
        return metadata == 0;
    }

    public int getBlockSubtypeUnchecked(int metadata) {
        return 0;
    }

    public void registerIcons(IconRegister par1IconRegister) {
        this.icon=par1IconRegister.registerIcon("planks/"+this.id);
    }

    public Icon getIcon(int side, int metadata) {
        return this.icon;
    }
}
