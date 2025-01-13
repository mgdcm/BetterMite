package com.github.FlyBird.BetterMite.block;

import net.minecraft.*;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;

public class BlockWoodFenceExtend extends BlockFence {
    private final int type;
    public BlockWoodFenceExtend(int par1, int type) {
        super(par1, "planks_"+Block.planks.getTextures()[type], Material.wood);
        this.type=type;
        this.setHardness(0.4F).setResistance(1.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("fences."+Block.planks.getNames()[type]);
    }
    public void registerRecipe(RecipeRegistryEvent register){
        register.registerShapedRecipe(new ItemStack(this,3), true, new Object[] {"ABA","ABA", 'A', new ItemStack(Block.planks,1,type), 'B',Item.stick});
    }
}
