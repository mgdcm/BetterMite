package com.github.FlyBird.BetterMite.block;

import com.github.FlyBird.BetterMite.item.Items;
import com.github.FlyBird.BetterMite.misc.DamageSourceExtend;
import net.minecraft.*;

import java.util.Random;

import static com.github.FlyBird.BetterMite.block.Blocks.stepSoundSweetBerryBush;

public class BlockSweetBerry extends BlockGrowingPlant{
    protected BlockSweetBerry(int par1) {
        super(par1);
        //  small  0.1875   0.0     0.1875    0.8125    0.5   0.8125
        // big     0.0625   0.0    0.0625   0.9375  1.0  0.9375
        this.setBlockBounds(0.25, 0.0, 0.25, 0.75, 0.4375, 0.75,false);
        this.setStepSound(stepSoundSweetBerryBush);
        this.setUnlocalizedName("sweetBerryBush");
    }

    private final Icon[] sweetBerry_Icon = new Icon[8];
    public static final DamageSource SWEET_BERRY_BUSH = (new DamageSourceExtend("sweetBerryBush"));

    //设置为false必须不能弄物品栏标签
    @Override
    public boolean canBeCarried() {
        return true;
    }

    //默认不可以被其他方块替换
    @Override
    public boolean isAlwaysReplaceable() {
        return false;
    }

    @Override
    public float getLowestOptimalTemperature() {
        return BiomeGenBase.forestHills.temperature;
    }

    @Override
    public float getHighestOptimalTemperature() {
        return BiomeGenBase.forest.temperature;
    }

    @Override
    public float getHumidityGrowthRateModifier(boolean high_humidity) {
        return 1.0f;
    }
    public static void setInBush(Entity entity){
        entity.setFire(-1);
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, int x, int y, int z, Entity par5Entity) {
        int stage= getBerryStage(par1World.getBlockMetadata(x,y,z));
        if(stage>=3)BlockSweetBerry.setInBush(par5Entity);
        if (par1World.isWorldServer()&&stage!=0) {
            if (par5Entity instanceof EntityLivingBase) {
                par5Entity.motionX *= 0.3405D;
                par5Entity.motionY *= 0.3405D;
                par5Entity.motionZ *= 0.3405D;
                par5Entity.fallDistance = 0;
                if (!par1World.isRemote && par1World.getBlockMetadata(x, y, z) > 0 && (par5Entity.lastTickPosX != par5Entity.posX || par5Entity.lastTickPosZ != par5Entity.posZ)) {
                    double d0 = Math.abs(par5Entity.posX - par5Entity.lastTickPosX);
                    double d1 = Math.abs(par5Entity.posZ - par5Entity.lastTickPosZ);
                    if (d0 >= 0.003F || d1 >= 0.003F) {
                        par5Entity.attackEntityFrom(new Damage(SWEET_BERRY_BUSH, Math.min((stage+1)/4.0f,1.0f)));
                    }
                }
            }
        }
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        for(int i = 0; i < sweetBerry_Icon.length; i++)
        {
            sweetBerry_Icon[i]=par1IconRegister.registerIcon( "sweet_berry_bush/stage" + i );
        }
    }

    @Override
    public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int x, int y, int z) {
        int meta=par1IBlockAccess.getBlockMetadata(x,y,z);
        switch (getBerryStage(meta)){
            case 0:
                this.setBlockBounds(0.25, 0.0, 0.25, 0.75, 0.4375, 0.75,false);
                break;
            case 1:
                this.setBlockBounds(0.1875, 0.0, 0.1875, 0.8125, 0.5, 0.8125,false);
                break;
            case 2:
                this.setBlockBounds(0.1875, 0.0, 0.1875, 0.8125, 0.75, 0.8125,false);
                break;
            default:
                this.setBlockBounds(0.0625, 0.0, 0.0625, 0.9375, 1.0, 0.9375,false);
        }
    }

    @Override
    public Icon getIcon(int side, int metadata) {
        return sweetBerry_Icon[Math.min(getBerryStage(metadata),getMaxBerryGrowth())];
    }

    //返回0就像正方体渲染  返回1则是drawCrossedSquares渲染交叉      2只是一个直角    30像线一样
    @Override
    public int getRenderType() {
        return 1;
    }

    //能被种在什么上？
    @Override
    public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
        return block_below == dirt || block_below == grass;
    }

    public float getGrowthRate(World world, int x, int y, int z) {
        float growth_rate = 16f;
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
        growth_rate *= this.getTemperatureGrowthRateModifier(biome.temperature);
        growth_rate *= this.getHumidityGrowthRateModifier(biome.isHighHumidity());
        return growth_rate * this.getGlobalGrowthRateModifierFromMITE();
    }

    public static int getBerryStage(int metadata)
    {
        return metadata >> 1;
    }

    public static int getMaxBerryGrowth() {
        return 7;
    }

    public static int setBerryStage(int metadata, int growth) {
        return metadata & 1 | MathHelper.clamp_int(growth, 0, BlockSweetBerry.getMaxBerryGrowth()) << 1;
    }

    @Override
    public boolean isValidMetadata(int metadata) {
        return (metadata & 1) == 0;
    }

    public static int incrementBerryStage(int metadata) {
        return BlockSweetBerry.setBerryStage(metadata, getBerryStage(metadata) + 1);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
        int metadata = world.getBlockMetadata(x, y, z);
        int stage= getBerryStage(metadata);  //0 1 2 3 4 5 6 7
        if(stage==7&&player.getHeldItem()!=null&&player.getHeldItem().itemID==Item.dyePowder.itemID&&player.getHeldItemStack().getItemSubtype()==15){
            world.setBlockMetadataWithNotify(x, y, z, BlockSweetBerry.setBerryStage(metadata, 3), 2);
            if (player.onServer() && !player.inCreativeMode()) player.convertOneOfHeldItem(null);
            if (player.onServer())world.playAuxSFX(2005, x, y, z, 0);
            player.triggerAchievement(AchievementList.plantDoctor);
            return true;
        }

        if (stage >= 4 && stage != BlockSweetBerry.getMaxBerryGrowth()) {
            if (!world.isRemote ) {
                ItemStack stack = new ItemStack(Items.sweetBerry, Math.max(1,world.rand.nextInt(stage)-3));
                float f = 0.7F;
                double d0 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                double d1 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                double d2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                EntityItem entityitem = new EntityItem(world, x + d0, y + d1, z + d2, stack);
                entityitem.delayBeforeCanPickup = 10;
                world.spawnEntityInWorld(entityitem);
            }
            world.playSound(x, y, z, "item.sweet_berries.pick_from_bush", 1.0F, 0.8F + world.rand.nextFloat() * 0.4F, false);
            if(!world.isWorldClient()){
                if(world.rand.nextFloat()<0.15f) world.setBlockMetadataWithNotify(x, y, z, BlockSweetBerry.setBerryStage(metadata, 7), 2);
                else world.setBlockMetadataWithNotify(x, y, z, BlockSweetBerry.setBerryStage(metadata, 3), 2);
            }
            return true;
        }
        return true;
    }

    @Override
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        if (getBerryStage(info.getMetadata())>= 4 && (info.wasHarvestedByPlayer() || info.wasSelfDropped() || info.wasNotLegal())) {
            return this.dropBlockAsEntityItem(info, Items.sweetBerry);
        }
        return 0;
    }

    @Override
    public boolean updateTick(World world, int x, int y, int z, Random rand) {
        if (super.updateTick(world, x, y, z, rand)) {
            return true;
        }
        int metadata = world.getBlockMetadata(x, y, z);
        int stage= getBerryStage(metadata);
        if (stage>=BlockSweetBerry.getMaxBerryGrowth()-1) {
            return false;
        }
        float growth_rate = this.getGrowthRate(world, x, y, z);
        if (growth_rate == 0.0f) {
            return false;
        }
        int blv = world.getBlockLightValue(x, y + 1, z);
        if (this.isLightLevelSuitableForGrowth(blv) && rand.nextFloat() < growth_rate) {
            world.setBlockMetadataWithNotify(x, y, z, BlockSweetBerry.incrementBerryStage(metadata), 2);
            return true;
        }
        return false;
    }

    //这个位置能不能发生
    @Override
    public boolean canOccurAt(World world, int x, int y, int z, int metadata) {
        return (world.getBiomeGenForCoords(x, z).isFreezing() && super.canOccurAt(world, x, y, z, metadata));
    }

    //设置能不能穿过
    @Override
    public boolean isSolid(boolean[] is_solid, int metadata) {
        return false;
    }
}
