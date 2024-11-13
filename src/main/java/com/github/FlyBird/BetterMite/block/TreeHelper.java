package com.github.FlyBird.BetterMite.block;

import com.github.FlyBird.BetterMite.item.ItemModLeaves;
import com.github.FlyBird.BetterMite.item.ItemMultiTextureTileFuelBlock;
import com.github.FlyBird.BetterMite.item.ItemModDoor;
import net.minecraft.*;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;

import static com.github.FlyBird.BetterMite.block.Blocks.getNextBlockID;
import static net.xiaoyu233.fml.reload.utils.IdUtil.getNextItemID;

public class TreeHelper{
    public final String id;
    public final BlockModLog Log;
    public final BlockModWoodPlanks Planks;
    public final BlockModLeaves Leaves;
    public final Block Stairs;
    public final BlockModWoodSlab SingleSlab;
    public final BlockDoubleSlab DoubleSlab;
    public final Block Door;
    public final ItemModDoor ItemDoor;
    public final BlockModTrapDoor TrapDoor;
    public final BlockWoodWorkBench WoodWorkBench;
    public TreeHelper(String id) {
        this.id = id;
        this.Log = new BlockModLog(getNextBlockID(),id);
        this.Planks = new BlockModWoodPlanks(getNextBlockID(),id);
        this.Leaves = new BlockModLeaves(getNextBlockID(),id);
        this.Stairs = (new BlockModStairs(getNextBlockID(), this.Planks, 0));
        this.SingleSlab = (new BlockModWoodSlab(getNextBlockID(), this.Planks, 0,id));
        this.DoubleSlab = (new BlockDoubleSlab(getNextBlockID(), this.SingleSlab));
        this.Door = (new BlockModDoor(getNextBlockID())).setTextureName("doors/"+id).setUnlocalizedName(id+"_door");
        this.ItemDoor = (new ItemModDoor(getNextItemID(),Door,Planks));
        this.TrapDoor = (new BlockModTrapDoor(getNextBlockID(),id,Planks));
        this.WoodWorkBench = (new BlockWoodWorkBench(getNextBlockID(), Log));
        Item.itemsList[this.Log.blockID] = (new ItemMultiTextureTileFuelBlock(this.Log, Log.getNames(),1600));
        Item.itemsList[this.Planks.blockID] = (new ItemMultiTextureTileFuelBlock(this.Planks, new String[]{id},400)).setUnlocalizedName("planks");
        Item.itemsList[this.Stairs.blockID] = (new ItemMultiTextureTileFuelBlock(this.Stairs, new String[]{id},400)).setUnlocalizedName("stairs");
        String[] workbenchNameList = new String[8];for (int i=0;i<workbenchNameList.length;i++) workbenchNameList[i]=((i<4)?"flint":"obsidian")+"."+Log.getName(i);
        Item.itemsList[this.WoodWorkBench.blockID] = (new ItemMultiTextureTileFuelBlock(this.WoodWorkBench, workbenchNameList,1600));
        Item.itemsList[this.SingleSlab.blockID] = (new ItemSlab(this.SingleSlab, this.DoubleSlab, false));
        Item.itemsList[this.DoubleSlab.blockID] = (new ItemSlab(this.SingleSlab, this.DoubleSlab, true));
        Item.itemsList[this.Leaves.blockID] = (new ItemModLeaves(this.Leaves));
    }
    public void registerRecipes(RecipeRegistryEvent register){
        for(int i=0;i<=1;i++)register.registerShapedRecipe(new ItemStack(Log, 3, i+2), true, "AA", "AA", 'A', new ItemStack(Log, 1, i));
        register.registerShapedRecipe(new ItemStack(Planks, 4),true,"A",'A',Log);
        register.registerShapedRecipe(new ItemStack(Stairs, 4),true,"A  ","AA ","AAA",'A',new ItemStack(Planks));
        register.registerShapedRecipe(new ItemStack(SingleSlab, 6),true,"AAA",'A',new ItemStack(Planks));
        TrapDoor.registerRecipe(register);
        ItemDoor.registerRecipe(register);
        WoodWorkBench.registerRecipe(register);
    }
}
