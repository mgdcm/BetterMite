package com.github.FlyBird.BetterMite.block;

import net.minecraft.*;

public class PrismarineBlocks extends Block implements IBlockWithSubtypes{

    public BlockSubtypes subtypes = new BlockSubtypes(new String[]{"rough", "bricks", "dark"});

    private Icon[] prismarine=new Icon[3];

    protected PrismarineBlocks(int par1) {
        super(par1, Material.stone,  new BlockConstants().setNeverHidesAdjacentFaces());
        setHardness(1.5F);
        setResistance(10.0F);
       // setBlockTextureName("prismarine");
        //setBlockName(Utils.getUnlocalisedName("prismarine_block"));
        setUnlocalizedName("prismarine_block");
        setCreativeTab(CreativeTabs.tabDecorations);
    }


    public String getMetadataNotes() {
        return "Bits 1 and 2 used for subtype";
    }

    public boolean isValidMetadata(int metadata) {
        return metadata >= 0 && metadata < 3;
    }

    public int getBlockSubtypeUnchecked(int metadata) {
        return metadata & 3;
    }

    @Override
    public Icon getIcon(int side, int metadata) {
        //int index=metadata&2;
        return prismarine[metadata];
    }

    public void registerIcons(IconRegister par1IconRegister) {
        this.prismarine[0]=par1IconRegister.registerIcon("prismarine_rough");
        this.prismarine[1]=par1IconRegister.registerIcon("prismarine_bricks");
        this.prismarine[2]=par1IconRegister.registerIcon("prismarine_dark");
        this.subtypes.setIcons(this.prismarine);
    }

    //不知道啥用
    public String getNameDisambiguationForReferenceFile(int metadata) {
        return "prismarine_block";
    }

    @Override
    public String[] getTextures() {
        return this.subtypes.getTextures();
    }

    //设置
    @Override
    public String[] getNames() {
        return this.subtypes.getNames();
    }
}
