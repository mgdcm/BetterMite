package com.github.FlyBird.BetterMite.item.register;

import com.github.FlyBird.BetterMite.block.Blocks;
import com.github.FlyBird.BetterMite.item.Items;
import net.minecraft.*;

public class RecipesFurnaceExtend extends Items {

    protected RecipesFurnaceExtend(int id, Material material, String texture) {
        super(id, material, texture);
    }

    public static void registerFurnaceRecipes() {

        FurnaceRecipes.smelting().addSmelting(Blocks.birchLog.blockID, new ItemStack(Item.coal,1,1));
        FurnaceRecipes.smelting().addSmelting(Blocks.spruceLog.blockID, new ItemStack(Item.coal,1,1));
        FurnaceRecipes.smelting().addSmelting(Blocks.jungleLog.blockID, new ItemStack(Item.coal,1,1));
        FurnaceRecipes.smelting().addSmelting(Blocks.oakLog.blockID, new ItemStack(Item.coal,1,1));


        FurnaceRecipes.smelting().addSmelting(Items.rabbitRaw.itemID, new ItemStack(Items.rabbitCooked,1));
        ItemFood.setCookingResult(rabbitRaw, rabbitCooked, 1);

    }

}
