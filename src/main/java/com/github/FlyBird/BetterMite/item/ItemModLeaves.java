package com.github.FlyBird.BetterMite.item;

import com.github.FlyBird.BetterMite.block.BlockModLeaves;
import com.github.FlyBird.BetterMite.block.BlockModLog;
import net.minecraft.*;

public class ItemModLeaves extends ItemBlock {
    public ItemModLeaves(Block block) {
        super(block);
    }

    public int getMetadata(int par1) {
        return par1 | 4;
    }

    public Icon getIconFromSubtype(int par1) {
        return this.getBlock().getIcon(0, par1);
    }

    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
        int var3 = par1ItemStack.getItemSubtype();
        return (var3 & 1) == 1 ? ColorizerFoliage.getFoliageColorPine() : ((var3 & 2) == 2 ? ColorizerFoliage.getFoliageColorBirch() : ColorizerFoliage.getFoliageColorBasic());
    }
    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        if (par1ItemStack == null) {
            return super.getUnlocalizedName();
        } else {
            return "leaves" + "." + ((BlockModLeaves)this.getBlock()).id;
        }
    }
}
