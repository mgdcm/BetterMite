package com.github.FlyBird.BetterMite.block;

import net.minecraft.*;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;

public class BlockModTrapDoor extends BlockTrapDoor {
    private BlockModWoodPlanks Planks;
    public BlockModTrapDoor(int par1, String id){
        this(par1, id,null);
    }
    public BlockModTrapDoor(int par1, String id,BlockModWoodPlanks planks){
        super(par1, Material.wood);
        this.Planks=planks;
        this.setUnlocalizedName("trapdoors."+id);
        this.setTextureName("trapdoors/"+id);
    }

    public void registerRecipeWithVanilla(RecipeRegistryEvent register,int i){
        register.registerShapedRecipe(new ItemStack(this, 2),true, new Object[]{"###", "###", '#', new ItemStack(planks,1,i)});
    }
    public void registerRecipe(RecipeRegistryEvent register){
        register.registerShapedRecipe(new ItemStack(this, 2),true, new Object[]{"###", "###", '#', Planks});
    }
}
