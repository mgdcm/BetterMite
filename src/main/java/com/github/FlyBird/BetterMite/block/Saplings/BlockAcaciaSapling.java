package com.github.FlyBird.BetterMite.block.Saplings;

import net.minecraft.*;

import java.util.Random;

public class BlockAcaciaSapling extends BlockPlant {
    public static final String[] WOOD_TYPES = new String[]{"oak", "spruce", "birch", "jungle"};
    private Icon saplingIcon;
    private final String id;
    private final float temperature;
    protected BlockAcaciaSapling(int par1,String id,int temperature) {
        super(par1);
        float var2 = 0.4F;
        this.setBlockBoundsForAllThreads((double)(0.5F - var2), 0.0, (double)(0.5F - var2), (double)(0.5F + var2), (double)(var2 * 2.0F), (double)(0.5F + var2));
        this.setMaxStackSize(16);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setCushioning(0.2F);
        this.setTextureName("saplings");
        this.id=id;
        this.temperature=temperature;
    }

    public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (super.updateTick(par1World, par2, par3, par4, par5Random)) {
            return true;
        } else {
            return par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(28) == 0 && this.markOrGrowMarked(par1World, par2, par3, par4, par5Random);
        }
    }

    public Icon getIcon(int par1, int par2) {
        return this.saplingIcon;
    }

    public boolean markOrGrowMarked(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (!canGrowInBiome(this.getItemSubtype(par1World.getBlockMetadata(par2, par3, par4)), par1World.getBiomeGenForCoords(par2, par4))) {
            return false;
        } else {
            int var6 = par1World.getBlockMetadata(par2, par3, par4);
            if ((var6 & 8) == 0) {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, var6 | 8, 4);
                return true;
            } else {
                this.growTree(par1World, par2, par3, par4, par5Random);
                return par1World.getBlock(par2, par3, par4) != this || par1World.getBlockMetadata(par2, par3, par4) != var6;
            }
        }
    }

    private void growTree(World par1World, int par2, int par3, int par4, Random par5Random) {
        int var6 = par1World.getBlockMetadata(par2, par3, par4) & 3;
        Object var7 = null;
        int var8 = 0;
        int var9 = 0;
        boolean var10 = false;
        if (var6 == 1) {
            var7 = new WorldGenTaiga2(true);
        } else if (var6 == 2) {
            var7 = new WorldGenForest(true);
        } else if (var6 == 3) {
            for(var8 = 0; var8 >= -1; --var8) {
                for(var9 = 0; var9 >= -1; --var9) {
                    if (this.isSameSapling(par1World, par2 + var8, par3, par4 + var9, 3) && this.isSameSapling(par1World, par2 + var8 + 1, par3, par4 + var9, 3) && this.isSameSapling(par1World, par2 + var8, par3, par4 + var9 + 1, 3) && this.isSameSapling(par1World, par2 + var8 + 1, par3, par4 + var9 + 1, 3)) {
                        var7 = new WorldGenHugeTrees(true, 10 + par5Random.nextInt(20), 3, 3);
                        var10 = true;
                        break;
                    }
                }

                if (var7 != null) {
                    break;
                }
            }

            if (var7 == null) {
                var9 = 0;
                var8 = 0;
                var7 = new WorldGenTrees(true, 4 + par5Random.nextInt(7), 3, 3, false);
            }
        } else {
            var7 = new WorldGenTrees(true);
            if (par5Random.nextInt(10) == 0) {
                var7 = new WorldGenBigTree(true);
            }
        }

        if (var10) {
            par1World.setBlock(par2 + var8, par3, par4 + var9, 0, 0, 4);
            par1World.setBlock(par2 + var8 + 1, par3, par4 + var9, 0, 0, 4);
            par1World.setBlock(par2 + var8, par3, par4 + var9 + 1, 0, 0, 4);
            par1World.setBlock(par2 + var8 + 1, par3, par4 + var9 + 1, 0, 0, 4);
        } else {
            par1World.setBlock(par2, par3, par4, 0, 0, 4);
        }

        if (!((WorldGenerator)var7).generate(par1World, par5Random, par2 + var8, par3, par4 + var9)) {
            if (var10) {
                par1World.setBlock(par2 + var8, par3, par4 + var9, this.blockID, var6, 4);
                par1World.setBlock(par2 + var8 + 1, par3, par4 + var9, this.blockID, var6, 4);
                par1World.setBlock(par2 + var8, par3, par4 + var9 + 1, this.blockID, var6, 4);
                par1World.setBlock(par2 + var8 + 1, par3, par4 + var9 + 1, this.blockID, var6, 4);
            } else {
                par1World.setBlock(par2, par3, par4, this.blockID, var6, 4);
            }
        }

    }

    public boolean isSameSapling(World par1World, int par2, int par3, int par4, int par5) {
        return par1World.getBlockId(par2, par3, par4) == this.blockID && (par1World.getBlockMetadata(par2, par3, par4) & 3) == par5;
    }

    public String getMetadataNotes() {
        return "bit 8 used for (one) intermediate growth stage";
    }

    public boolean isValidMetadata(int metadata) {
        return metadata >= 0 && metadata < 4 || metadata >= 8 && metadata < 12;
    }

    public int getBlockSubtypeUnchecked(int metadata) {
        return metadata & 3;
    }

    public void registerIcons(IconRegister par1IconRegister) {
        this.saplingIcon = par1IconRegister.registerIcon(this.getTextureName() + "/" + id);
    }

    public static boolean canGrowInBiome(int subtype, BiomeGenBase biome) {
        if (!biome.hasRainfall()) {
            return false;
        } else{
            return biome.temperature >= biome.temperature;
        }
    }
}
