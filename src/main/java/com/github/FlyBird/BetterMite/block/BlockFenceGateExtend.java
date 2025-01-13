package com.github.FlyBird.BetterMite.block;

import net.minecraft.*;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;

public class BlockFenceGateExtend extends BlockFenceGate {
    private final int type;
    public BlockFenceGateExtend(int par1,int type) {
        super(par1);
        this.type=type;
        this.setHardness(2.0F).setResistance(5.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("fence_gates."+Block.planks.getNames()[type]);
    }

    @Override
    public Icon getIcon(int par1, int par2) {
        return Block.planks.getIcon(par1,type);
    }
    public void registerRecipe(RecipeRegistryEvent register){
        register.registerShapedRecipe(new ItemStack(this), true, new Object[] {"ABA","ABA", 'A',Item.stick, 'B',new ItemStack(Block.planks,1,type)});
    }
}
