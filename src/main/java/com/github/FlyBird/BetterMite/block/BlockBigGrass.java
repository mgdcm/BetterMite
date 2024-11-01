package com.github.FlyBird.BetterMite.block;

import net.minecraft.*;

import static net.minecraft.BlockGrass.getTramplingEffect;
import static net.minecraft.BlockGrass.getTramplings;

public class BlockBigGrass extends BlockPlant {

    private static final String[] grassTypes = new String[]{"BigGrassTop","BigGrassBottom", "BigFernTop","BigFernBottom"};
    private Icon[] iconArray;

    protected BlockBigGrass(int id) {
        super(id, Material.vine);
        float size = 0.4F;
        this.setBlockBoundsForAllThreads((double)(0.5F - size), 0.0, (double)(0.5F - size), (double)(0.5F + size), 0.800000011920929, (double)(0.5F + size));
    }

    public Icon getIcon(int par1, int par2) {
        return this.iconArray[Math.min(par2,3)];
    }
    public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return this.iconArray[Math.min(par2,3)];
    }
    public int getBlockColor() {
        double var1 = 0.5;
        double var3 = 1.0;
        return ColorizerGrass.getGrassColor(var1, var3);
    }
    @Override
    public boolean canOccurAt(World world, int x, int y, int z, int metadata) {
        return switch (metadata){
            case 1->super.canOccurAt(world, x, y, z, metadata)&&world.getBiomeGenForCoords(x, z)==BiomeGenBase.plains;
            case 3->world.getBiomeGenForCoords(x, z).isFreezing() && super.canOccurAt(world, x, y, z, metadata);
            default -> false;
        };
    }

    public int getRenderColor(int par1) {
        return this.getBlockColor();
    }
    @Override
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int var5 = 0;
        int var6 = 0;
        int var7 = 0;
        int var8;
        int var9;
        int var10;

        for (var8 = -1; var8 <= 1; ++var8)
        {
            for (var9 = -1; var9 <= 1; ++var9)
            {
                var10 = par1IBlockAccess.getBiomeGenForCoords(par2 + var9, par4 + var8).getBiomeGrassColor();
                var5 += (var10 & 16711680) >> 16;
                var6 += (var10 & 65280) >> 8;
                var7 += var10 & 255;
            }
        }

        var8 = var5 / 9 & 255;
        var9 = var6 / 9 & 255;
        var10 = var7 / 9 & 255;
        float var11 = getTramplingEffect(getTramplings(par1IBlockAccess.getBlockMetadata(par2, par3, par4)));

        if (var11 > 0.0F)
        {
            float var12 = 1.0F - var11;
            var8 = (int)((float)var8 * var12 + 134.0F * var11);
            var9 = (int)((float)var9 * var12 + 96.0F * var11);
            var10 = (int)((float)var10 * var12 + 67.0F * var11);
        }

        return var8 << 16 | var9 << 8 | var10;
    }
    public void onBlockAboutToBeBroken(BlockBreakInfo info) {
        if(info.world.getBlockId(info.x, info.y-1, info.z) == this.blockID) {
            info.y--;
            info.world.destroyBlock(info,true);
            info.y++;
        }
        if(info.world.getBlockId(info.x, info.y+1, info.z) == this.blockID) {
            info.y++;
            info.world.destroyBlock(info,false);
            info.y--;
        }
    }

    @Override
    public boolean onBlockPlacedMITE(World world, int x, int y, int z, int metadata, Entity placer, boolean test_only) {
        if(!world.isAirBlock(x,y+1,z))return false;
        if(!(metadata==1||metadata==3))return super.onBlockPlacedMITE(world, x, y, z, metadata, placer, test_only);
        return super.onBlockPlacedMITE(world, x, y, z, metadata, placer, test_only)&&super.tryPlaceBlock(world, x, y+1, z, EnumFace.BOTTOM,metadata-1,placer,false,false, test_only);
    }

    public String getMetadataNotes() {
        return "0~1=BigGrass, 2~3=BigFern, (in mod 2):0:Top, 1:Bottom.";
    }

    public boolean isValidMetadata(int metadata) {
        return metadata >= 0 && metadata <= 3;
    }

    public int getBlockSubtypeUnchecked(int metadata) {
        return metadata&3;
    }

    public void registerIcons(IconRegister par1IconRegister) {
        this.iconArray = new Icon[grassTypes.length];

        for(int var2 = 0; var2 < this.iconArray.length; ++var2) {
            this.iconArray[var2] = switch (grassTypes[var2]){
                case "BigGrassTop"->par1IconRegister.registerIcon("tall_grass/top");
                case "BigGrassBottom"->par1IconRegister.registerIcon("tall_grass/bottom");
                case "BigFernTop"->par1IconRegister.registerIcon("tall_fern/top");
                case "BigFernBottom"->par1IconRegister.registerIcon("tall_fern/bottom");
                default -> par1IconRegister.registerIcon("deadbush");
            };
        }

    }

    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        return !info.wasSelfDropped() &&!info.wasNotLegal() && info.wasHarvestedByPlayer()&& this.getItemSubtype(info.getMetadata()) <= 1 ? this.dropBlockAsEntityItem(info, Item.seeds.itemID, 0, 1, 0.32F) : 0;
    }



    public String getNameDisambiguationForReferenceFile(int metadata) {
        return "bigGrass";
    }

    public boolean dropsAsSelfWhenTrampled(Entity entity) {
        return false;
    }
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        if (world.getBlockId(x, y - 1, z) == dirt.blockID) {
            world.setBlock(x, y - 1, z, grass.blockID);
        }
    }
    public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
        return block_below == grass || block_below == dirt || block_below == tilledField || block_below == Blocks.bigGrass&&(block_below_metadata&1)==1;
    }
    public boolean canBeReplacedBy(int metadata, Block other_block, int other_block_metadata) {
        return other_block != null && other_block != this;
    }

    public boolean showDestructionParticlesWhenReplacedBy(int metadata, Block other_block, int other_block_metadata) {
        return true;
    }
}
