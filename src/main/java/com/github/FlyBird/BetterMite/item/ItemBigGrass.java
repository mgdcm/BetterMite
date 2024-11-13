package com.github.FlyBird.BetterMite.item;

import com.github.FlyBird.BetterMite.block.BlockBigGrass;
import com.github.FlyBird.BetterMite.block.Blocks;
import net.minecraft.*;

public class ItemBigGrass extends ItemColored {

    public ItemBigGrass(Block block) {
        super(block);
    }

    public Icon getIconFromSubtype(int par1) {
        return this.getBlock().getIcon(0, (par1&2));
    }


    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return "tile."+((par1ItemStack.getItemSubtype()&3)>>1==0?"tall_grass":"large_fern");
    }
}
