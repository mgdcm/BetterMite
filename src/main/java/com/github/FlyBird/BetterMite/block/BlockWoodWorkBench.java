package com.github.FlyBird.BetterMite.block;

import net.minecraft.*;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;

public class BlockWoodWorkBench extends BlockWorkbench {
    protected final BlockModLog Log;
    protected Icon icon_flint_top,icon_obsidian_top;
    protected BlockWoodWorkBench(int par1, BlockModLog log) {
        super(par1);
        this.Log=log;
        this.setUnlocalizedName("workbench");
    }
    public static Material getToolMaterial(int metadata) {
        return tool_materials[metadata < 4?0:8];
    }
    public void registerRecipe(RecipeRegistryEvent register){
        for(int i=0;i<8;i++){
            ItemStack knife = new ItemStack((i<4)?Item.knifeFlint : Item.knifeObsidian);
            knife.setAsComponentOfCraftingProduct(new ItemStack(Log,1,i&3));
            register.registerShapedRecipe(new ItemStack(this, 1, i),true, new Object[]{"K", "#", 'K', ((i&1)==0)?Item.knifeFlint:Item.knifeObsidian, '#', new ItemStack(Log,1,i)});
            register.registerShapedRecipe(new ItemStack(this, 1, i),true, new Object[]{"FS", "s#", 'F', ((i&1)==0)?Item.flint:Block.obsidian, 'S', Item.silk, 's', Item.stick, '#', new ItemStack(Log,1,i)});
            register.registerShapedRecipe(new ItemStack(this, 1, i),true, new Object[]{"FS", "s#", 'F', ((i&1)==0)?Item.flint:Block.obsidian, 'S', Item.sinew, 's', Item.stick, '#', new ItemStack(Log,1,i)});
            register.registerShapedRecipe(knife, false, new Object[]{"#", '#', new ItemStack(this, 1, i)}).difficulty(25.0f);
        }
    }

    protected BlockWoodWorkBench(int par1, Block log) {
        this(par1,(BlockModLog) log);
    }
    public Icon getIcon(int side, int metadata) {
        return metadata < 4 ? (side == 1 ? this.icon_flint_top : Log.getIcon(side, metadata & 3)) : (side == 1 ? this.icon_obsidian_top : Log.getIcon(side, metadata & 3));
    }
    public void registerIcons(IconRegister par1IconRegister) {
        this.icon_flint_top = par1IconRegister.registerIcon("crafting_table/flint/top");
        this.icon_obsidian_top = par1IconRegister.registerIcon("crafting_table/obsidian/top");
    }

    @Override
    public String getMetadataNotes() {
        return "Bit 1, 2 used for wood type, bit 4 set it is obsidian workbench, else it is flint workbench.";
    }

    public ItemStack BlockComponent() {
        return new ItemStack(Log, 1, 0);
    }
    public boolean isValidMetadata(int metadata) {
        return metadata < 8;
    }

    public int getBlockSubtypeUnchecked(int metadata) {
        return metadata & 7;
    }
}
