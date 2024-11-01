package com.github.FlyBird.BetterMite.block;

import com.github.FlyBird.BetterMite.tileentity.BarrelTileEntity;
import net.minecraft.*;

import java.util.Random;

import static com.github.FlyBird.BetterMite.block.Blocks.stepSoundWood;


public class BlockBarrel extends BlockDirectionalWithTileEntity{


    private final Random random = new Random();
    private Icon bottomIcon;
    private Icon sideIcon;
    private Icon topIcon;
    private Icon topOpenIcon;

    public BlockBarrel(int par1, Material par2) {
        super(par1, par2, new BlockConstants());
        setMaxStackSize(1);
        setHardness(BlockHardness.workbench);
        setCreativeTab(CreativeTabs.tabDecorations);
        this.setStepSound(stepSoundWood);
        setTickRandomly(true);
    }

    public boolean isActivated(int metadata) {
        return ((metadata & 0x8) != 0);
     }

    @Override
    public boolean updateTick(World world, int x, int y, int z, Random rand) {
        return super.updateTick(world, x, y, z, rand) && world.getBlock(x, y, z) != this;
    }

    @Override
    public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
        if (super.onNeighborBlockChange(world, x, y, z, neighbor_block_id)) {
            return true;
        } else {
            BarrelTileEntity chest_entity = (BarrelTileEntity)world.getBlockTileEntity(x, y, z);
            if (chest_entity != null) {
                chest_entity.updateContainingBlockInfo();
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
        BarrelTileEntity var7 = (BarrelTileEntity)par1World.getBlockTileEntity(par2, par3, par4);
        if (var7 != null) {
            for(int var8 = 0; var8 < var7.getSizeInventory(); ++var8) {
                ItemStack var9 = var7.getStackInSlot(var8);
                if (var9 != null) {
                    float var10 = this.random.nextFloat() * 0.8F + 0.1F;
                    float var11 = this.random.nextFloat() * 0.8F + 0.1F;

                    EntityItem var14;
                    for(float var12 = this.random.nextFloat() * 0.8F + 0.1F; var9.stackSize > 0; par1World.spawnEntityInWorld(var14)) {
                        int var13 = this.random.nextInt(21) + 10;
                        if (var13 > var9.stackSize) {
                            var13 = var9.stackSize;
                        }

                        var9.stackSize -= var13;
                        var14 = new EntityItem(par1World, (float)par2 + var10, (float)par3 + var11, (float)par4 + var12, new ItemStack(var9.itemID, var13, var9.getItemSubtype()));
                        if (var9.isItemDamaged()) {
                            var14.getEntityItem().setItemDamage(var9.getItemDamage());
                        }

                        float var15 = 0.05F;
                        var14.motionX = (float)this.random.nextGaussian() * var15;
                        var14.motionY = (float)this.random.nextGaussian() * var15 + 0.2F;
                        var14.motionZ = (float)this.random.nextGaussian() * var15;
                        if (var9.getItem().hasQuality()) {
                            var14.getEntityItem().setQuality(var9.getQuality());
                        }

                        if (var9.hasTagCompound()) {
                            var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                        }
                    }
                }
            }

            par1World.func_96440_m(par2, par3, par4, par5);
        }

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }



    public boolean canOpenChest(EntityLivingBase entity_living_base) {
        return  !entity_living_base.hasCurse(Curse.cannot_open_chests, true);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float dx, float dy, float dz) {
        if (player.onServer()) {
            if (this.canOpenChest(player)) {
                IInventory inventory = this.getInventory(world, x, y, z);
                if (inventory != null) {
                    player.displayGUIChest(x, y, z, inventory);
                }
            } else {
                world.playSoundAtBlock(x, y, z, "imported.random.chest_locked", 0.2F);
            }
        }

        return true;
    }


    public IInventory getInventory(World par1World, int par2, int par3, int par4) {
        BarrelTileEntity barrelTileEntity = (BarrelTileEntity)par1World.getBlockTileEntity(par2, par3, par4);
        if (barrelTileEntity == null)
            return null;
        if (isOcelotBlockingChest(par1World, par2, par3, par4))
            return null;
        return barrelTileEntity;
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new BarrelTileEntity(this);
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        if (!canProvidePower())
            return 0;
        int var6 = ((BarrelTileEntity)par1IBlockAccess.getBlockTileEntity(par2, par3, par4)).numUsingPlayers;
        return MathHelper.clamp_int(var6, 0, 15);
    }

    @Override
    public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return par5 == 1 ? this.isProvidingWeakPower(par1IBlockAccess, par2, par3, par4, par5) : 0;
    }

    private static boolean isOcelotBlockingChest(World par0World, int par1, int par2, int par3) {

        for (Object o : par0World.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getAABBPool().getAABB(par1, par2 + 1, par3, par1 + 1, par2 + 2, par3 + 1))) {
            EntityOcelot var6 = (EntityOcelot) o;
            if (var6.isSitting()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
        return Container.calcRedstoneFromInventory(this.getInventory(par1World, par2, par3, par4));
    }


    @Override
    public Icon getIcon(int side, int metadate) {
        int Direction = metadate & 7;
        //面向北方     左边为5    右边为4    面前为2  面后为5   下面为0
        if(Direction == 0||Direction == 1)   //Y轴(垂直)方向
        {
            if(side == 0)
                return this.bottomIcon;
            if(side == 1)
                return isActivated(metadate) ?this.topOpenIcon :this.topIcon;
        }
        if(Direction==2)//朝北
        {
            if(side==2)
                return isActivated(metadate)?this.topOpenIcon :this.topIcon;
            if(side==3)
                return this.bottomIcon;
        }
        if(Direction==3)//朝南
        {
            if(side==3)
                return isActivated(metadate) ?this.topOpenIcon :this.topIcon;
            if(side==2)
                return this.bottomIcon;
        }
        if(Direction==4)//西
        {
            if(side==4)
                return isActivated(metadate) ?this.topOpenIcon :this.topIcon;
            if(side==5)
                return this.bottomIcon;
        }
        if(Direction==5)//东
        {
            if(side==5)
                return isActivated(metadate)?this.topOpenIcon :this.topIcon;
            if(side==4)
                return this.bottomIcon;
        }
        return this.sideIcon;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.bottomIcon = par1IconRegister.registerIcon( "barrel_bottom");
        this.sideIcon = par1IconRegister.registerIcon( "barrel_side");
        this.topIcon = par1IconRegister.registerIcon( "barrel_top");
        this.topOpenIcon =par1IconRegister.registerIcon( "barrel_top_open");
    }

    @Override
    public boolean playerSwingsOnBlockActivated(boolean empty_handed) {
        return empty_handed;
    }

    @Override
    public boolean canBePlacedOnBlock(int metadata, Block block_below, int block_below_metadata, double block_below_bounds_max_y) {
        return block_below.isTopFlatAndSolid(block_below_metadata) && super.canBePlacedOnBlock(metadata, block_below, block_below_metadata, block_below_bounds_max_y);
    }

    @Override
    public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
        return false;
    }

    @Override
    public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
        return true;
    }

    //BlockDirectional 在这个类里面，人家给了多种标准的方向
    @Override
    public EnumDirection getDirectionFacing(int metadata) {
        int direction = metadata & 7;
        if (direction == 0) {
            return EnumDirection.DOWN;
        }
        if (direction == 1) {
            return EnumDirection.UP;
        }
        if (direction == 2) {
            return EnumDirection.NORTH;
        }
        if (direction == 3) {
            return EnumDirection.SOUTH;
        }
        if (direction == 4) {
            return EnumDirection.WEST;
        }
        if (direction == 5) {
            return EnumDirection.EAST;
        }
        return null;
    }
    @Override
    public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
        return this.getItemSubtype(metadata) | (direction.isUpOrDown() ? 0 : (direction.isEastOrWest() ? 4 : (direction.isNorthOrSouth() ? 8 : -1)));
    }
}

