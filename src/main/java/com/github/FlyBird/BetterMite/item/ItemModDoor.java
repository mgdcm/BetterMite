package com.github.FlyBird.BetterMite.item;

import com.github.FlyBird.BetterMite.block.BlockModWoodPlanks;
import net.minecraft.Block;
import net.minecraft.ItemDoor;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;

public class ItemModDoor extends ItemDoor {
    private final Block DoorBlock;
    private final BlockModWoodPlanks Planks;
    public ItemModDoor(int par1, Block doorBlock,String id) {
        this(par1, doorBlock, (BlockModWoodPlanks) null);
        this.setTextureName("doors/"+id);
        this.setUnlocalizedName("doors."+id);
    }
    public ItemModDoor(int par1, Block doorBlock, BlockModWoodPlanks planks) {
        super(par1, Material.wood);
        DoorBlock = doorBlock;
        Planks = planks;
        if(Planks!=null){
            this.setTextureName("doors/"+Planks.id);
            this.setUnlocalizedName("doors."+Planks.id);
        }
    }
    public void registerRecipe(RecipeRegistryEvent register){
        register.registerShapedRecipe(new ItemStack(this, 1), true, new Object[]{"AA", "AA", "AA", Character.valueOf('A'), Planks});
    }
    public void registerRecipeWithVanilla(RecipeRegistryEvent register, int i){
        register.registerShapedRecipe(new ItemStack(this, 1), true, new Object[]{"AA", "AA", "AA", Character.valueOf('A'), new ItemStack((Block.planks), 1, i)});
    }
    @Override
    public Block getBlock() {
        return DoorBlock;
    }
}
