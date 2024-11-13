package com.github.FlyBird.BetterMite.mixins.block;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockLog.class)
public abstract class BlockLogMixin extends BlockRotatedPillar {
    @Shadow public BlockSubtypes subtypes;

    @Shadow public Icon[] end_icons;

    @Shadow public abstract String[] getTextures();

    protected BlockLogMixin(int par1, Material par2Material) {
        super(par1, par2Material);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void registerIcons(IconRegister par1IconRegister) {
        this.subtypes.setIcons(this.registerIcons(par1IconRegister, this.getTextures(), "log/","_side"));
        this.end_icons = this.registerIcons(par1IconRegister, this.getTextures(), "log/","_top");
    }
}
