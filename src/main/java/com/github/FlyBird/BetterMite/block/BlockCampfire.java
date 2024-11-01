package com.github.FlyBird.BetterMite.block;


import com.github.FlyBird.BetterMite.misc.DamageSourceExtend;
import com.github.FlyBird.BetterMite.misc.EnumParticles;
import com.github.FlyBird.BetterMite.render.RenderTypes;
import net.minecraft.*;
import org.lwjgl.Sys;

import java.util.Objects;
import java.util.Random;

public class BlockCampfire extends Block {

    private Icon BlockCampfireIcon;
    private Icon ItemCampfireIcon;
    private Icon campFire_FireIcon;
    private Icon campFire_LogLitIcon;
    public static final DamageSource CAMPFIRE_DAMAGE = (new DamageSourceExtend("CampFire"));

    public static Icon[] bigSmokeIcon = new Icon[12];
    private final float damage;

    protected BlockCampfire(int par1,float damage) {
        super(par1, Material.wood, new BlockConstants().setNotAlwaysLegal().setNeverHidesAdjacentFaces());
        this.setBlockBoundsForAllThreads(0.0, 0.0, 0.0, 1.0, 0.4375, 1.0);
        this.setHardness(0.5f);
        this.setStepSound(soundWoodFootstep);
        this.damage=damage;
        setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        if ((info.wasHarvestedByPlayer() || info.wasSelfDropped() || info.wasNotLegal())) {
            return this.dropBlockAsEntityItem(info, new ItemStack(Item.coal,2,1));
        }
        return 0;
    }
    private int FireTime=0;
    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
        if (par1World.isWorldServer()) {
            if (par5Entity instanceof EntityLivingBase) {
                this.FireTime++;
                if(FireTime>>4!=0) {
                    FireTime=0;
                    par5Entity.attackEntityFrom(new Damage(CAMPFIRE_DAMAGE, damage));
                }
            }
        }
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.campFire_FireIcon = par1IconRegister.registerIcon("campfire/" + this.getTextureName() + "_fire");
        this.BlockCampfireIcon = par1IconRegister.registerIcon("campfire_log");
        this.campFire_LogLitIcon = par1IconRegister.registerIcon("campfire/" + this.getTextureName() + "_log_lit");
        this.ItemCampfireIcon = par1IconRegister.registerIcon("item/" + this.getTextureName());

        for (int i = 0; i < 12; i++) {
            bigSmokeIcon[i] = par1IconRegister.registerIcon("particle/big_smoke_" + i);
        }
    }

    public Icon getFireIcon(int index) {
        if (index == 0)
            return this.campFire_FireIcon;
        else
            return this.campFire_LogLitIcon;
    }

    @Override
    public Icon getIcon(int side, int metadata) {
        if (side == 1)
            return this.ItemCampfireIcon;   //返回影子物品的图标
        return this.BlockCampfireIcon;
    }

    @Override
    public int getRenderType() {
        return RenderTypes.campfireRenderType;
    }

    @Override
    public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
        return false;
    }


    @Override
    public EnumDirection getDirectionFacing(int metadata) {
        return (metadata & 4) != 0 ? EnumDirection.WEST : ((metadata & 8) != 0 ? EnumDirection.NORTH : EnumDirection.DOWN);
    }

    @Override
    public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
        return this.getItemSubtype(metadata) | (direction.isUpOrDown() ? 0 : (direction.isEastOrWest() ? 4 : (direction.isNorthOrSouth() ? 8 : -1)));
    }

    @Override
    public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
        int metadata = super.getMetadataForPlacement(world, x, y, z, item_stack, entity, face, offset_x, offset_y, offset_z);
        if (face.isEastOrWest()) {
            metadata |= 4;
        } else if (face.isNorthOrSouth()) {
            metadata |= 8;
        }
        return metadata;
    }

    @Override
    public void randomDisplayTick(World par1World, int x, int y, int z, Random par5Random) {
        float var9;
        float var8;
        float var7;
        int var6;
        if (par5Random.nextInt(10) == 0) {
            par1World.playSound((float) x + 0.5f, (float) y + 0.5f, (float) z + 0.5f, "block.campfire.crackle", 1.0f + par5Random.nextFloat(), par5Random.nextFloat() * 0.7f + 0.3f, false);
        }

        for (var6 = 0; var6 < 4; ++var6) {
            var7 = (float) x +0.5f+ par5Random.nextFloat() * 0.1f;
            var8 = (float) y + par5Random.nextFloat();//随机返回一个0—1.0的浮点
            var9 = (float) z +0.5f;
            par1World.spawnParticle(EnumParticles.largerSmoke, x, y, z, 0.0, 0.0, 0.0);
            par1World.spawnParticle(EnumParticle.smoke, var7, var8, var9, 0.0, 0.0, 0.0);
        }

        if (par5Random.nextInt(5) == 0&& Objects.equals(this.getTextureName(), "campfire")) {
            for (int i = 0; i < par5Random.nextInt(1) + 1; ++i) {
                double var21 = (float)x + par5Random.nextFloat();
                double var22 = (double)y + this.maxY[Minecraft.getThreadIndex()];
                double var23 = (float)z + par5Random.nextFloat();
                par1World.spawnParticle(EnumParticle.lava, var21, var22, var23, 0.0, 0.0, 0.0);
            }
        }

    }

}

