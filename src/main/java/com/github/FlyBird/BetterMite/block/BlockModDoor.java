package com.github.FlyBird.BetterMite.block;

import net.minecraft.BlockDoor;
import net.minecraft.Material;

public class BlockModDoor extends BlockDoor{

    public BlockModDoor(int blockID) {
        super(blockID, Material.wood);
        //setCreativeTab(CreativeTabs.tabDecorations);
    }
    public BlockModDoor(int par1, String id){
        this(par1);
        this.setUnlocalizedName("doors."+id);
        this.setTextureName("doors/"+id);
    }
}
