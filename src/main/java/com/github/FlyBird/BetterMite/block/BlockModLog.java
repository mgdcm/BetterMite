package com.github.FlyBird.BetterMite.block;

import net.minecraft.*;

public class BlockModLog extends BlockLog {
    protected BlockModLog(int par1) {
        super(par1);
        this.setUnlocalizedName("log");
        this.id=this.getTextureName();
    }
    protected BlockModLog(int par1, String id) {
        super(par1);
        this.setTextureName(id);
        this.setUnlocalizedName("log");
        this.id = id;
    }
    protected BlockModLog setIsVanilla(){
        this.isVanilla=true;
        return this;
    }
    public final String id;
    public String getName(int metadata){
        return getNames()[metadata & 3];
    }

    @Override
    public String[] getNames() {
        return new String[]{id+"_log","stripped_"+id+"_log",id+"_wood","stripped_"+id+"_wood"};
    }

    private boolean isVanilla;
    public Icon side_icon, end_icon, stripped_side_icon, stripped_end_icon;

    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
        byte var7 = 4;
        int var8 = var7 + 1;
        if (par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8)) {
            for(int var9 = -var7; var9 <= var7; ++var9) {
                for(int var10 = -var7; var10 <= var7; ++var10) {
                    for(int var11 = -var7; var11 <= var7; ++var11) {
                        int var12 = par1World.getBlockId(par2 + var9, par3 + var10, par4 + var11);
                        if (var12 == Block.leaves.blockID) {
                            int var13 = par1World.getBlockMetadata(par2 + var9, par3 + var10, par4 + var11);
                            if ((var13 & 8) == 0) {
                                par1World.setBlockMetadataWithNotify(par2 + var9, par3 + var10, par4 + var11, var13 | 8, 4);
                            }
                        }
                    }
                }
            }
        }

    }
    public Icon getIcon(int par1, int par2) {
        int var3 = par2 & 12;
        int var4 = par2 & 1;
        int var5 = (par2 & 2) >> 1;
        return var5 == 0 ? (var3 == 0 && (par1 == 1 || par1 == 0) ? this.getEndIcon(var4) : (var3 != 4 || par1 != 5 && par1 != 4 ? (var3 != 8 || par1 != 2 && par1 != 3 ? this.getSideIcon(var4) : this.getEndIcon(var4)) : this.getEndIcon(var4))) : (getSideIcon(var4));
    }
    protected Icon getSideIcon(int par1) {
        return par1==0?side_icon:stripped_side_icon;
    }

    protected Icon getEndIcon(int par1) {
        return par1==0?end_icon:stripped_end_icon;
    }

    public static int limitToValidMetadata(int par0) {
        return par0 & 3;
    }

    public String getMetadataNotes() {
        return "Bits 1 used for is stripped, bit 2 used for is wood , bit 4 set if aligned WE, and bit 8 set if aligned NS";
    }

    public void registerIcons(IconRegister par1IconRegister) {
        this.side_icon = par1IconRegister.registerIcon("log/"+this.getTextureName()+"_side");
        this.stripped_side_icon = par1IconRegister.registerIcon("log/stripped_"+this.getTextureName()+"_side");
        this.end_icon = par1IconRegister.registerIcon("log/"+this.getTextureName()+"_top");
        this.stripped_end_icon = par1IconRegister.registerIcon("log/stripped_"+this.getTextureName()+"_top");
    }

    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        return info.wasExploded() ? this.dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, 1.5F) : super.dropBlockAsEntityItem(info);
    }
    public String getNameDisambiguationForReferenceFile(int metadata) {
        return "log";
    }
}
