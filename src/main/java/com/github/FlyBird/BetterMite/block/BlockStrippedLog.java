package com.github.FlyBird.BetterMite.block;


import net.minecraft.*;

import static com.github.FlyBird.BetterMite.block.Blocks.stepSoundWood;

public class BlockStrippedLog extends BlockRotatedPillar {
    private Icon stripped_top;
    private Icon stripped_side;

    protected BlockStrippedLog(int par1) {
        super(par1, Material.wood);
        this.modifyMinHarvestLevel(1);
        this.setHardness(0.9f);
        this.setMaxStackSize(4);
        this.setStepSound(stepSoundWood);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }


    @Override
    protected Icon getSideIcon(int par1) {
        return this.stripped_side;
    }

    @Override
    protected Icon getEndIcon(int par1) {
        return this.stripped_top;
    }

    @Override
    public String getMetadataNotes() {
        return "Bits 1 and 2 used for subtype, bit 4 set if aligned WE, and bit 8 set if aligned NS";
    }

    @Override
    public boolean isValidMetadata(int metadata) {
        return metadata >= 0 && metadata < 16 && !BitHelper.isBitSet(metadata, 12);
    }

    @Override
    public int getBlockSubtypeUnchecked(int metadata) {
        return 0;
    }

    //起名字stripped
    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.stripped_side = par1IconRegister.registerIcon( this.getTextureName());
        this.stripped_top = par1IconRegister.registerIcon( this.getTextureName()+"_top");
    }

    @Override
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        if (info.wasExploded()) {
            return this.dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, 1.5f);
        }
        return super.dropBlockAsEntityItem(info);
    }

    @Override
    public String getNameDisambiguationForReferenceFile(int metadata) {
        return "stripped";
    }


}
