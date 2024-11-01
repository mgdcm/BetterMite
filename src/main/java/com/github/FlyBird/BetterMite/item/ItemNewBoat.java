package com.github.FlyBird.BetterMite.item;

import com.github.FlyBird.BetterMite.entity.EntityNewBoat;
import com.github.FlyBird.BetterMite.entity.EntityNewBoatWithChest;
import com.google.common.collect.Maps;
import net.minecraft.*;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ItemNewBoat extends Item {

    private EntityNewBoat.Type type;

    public final boolean isChest;

    public final String name;


    public static final Map<String, BoatInfo> BOAT_INFO = Maps.newHashMap();
    public final BoatInfo boatinfo;

    public ItemNewBoat(int id,EntityNewBoat.Type type, boolean isChest) {
        super(id,Material.wood,type.getName() + (isChest ? "_chest_boat" : "_boat"));
        //super(type.getName() + (isChest ? "_chest_boat" : "_boat"));
        this.type = type;
        this.name = type.getName();
        this.setUnlocalizedName(type.getName() + (isChest ? "_chest_boat" : "_boat"));
        this.isChest = isChest;
        BOAT_INFO.put( type.getName().toLowerCase() + (isChest ? "_chest" : ""),
                new BoatInfo(new ItemStack(this), () -> Item.getItem(Block.planks), type.ordinal(), false));
        boatinfo=new BoatInfo(new ItemStack(this), () -> Item.getItem(Block.planks), type.ordinal(), false);
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabTransport);
    }


    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        World world=player.worldObj;
        ItemStack itemStack=player.getHeldItemStack();
        float f = 1.0F;
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f + 1.62D - (double) player.yOffset;
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
        //public final RaycastCollision tryRaycastVsBlocks(Raycast raycast) {
        //Raycast raycast=new Raycast(world,vec3,vec31);
        RaycastCollision movingobjectposition = world.getBlockCollisionForSelection(vec3, vec31, true);

        if (movingobjectposition == null) {
            return false;
        }
        Vec3 vec32 = player.getLook(f);
        boolean flag = false;
        float f9 = 1.0F;
        List list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3).expand(f9, f9, f9));
        int i;

        for (i = 0; i < list.size(); ++i) {
            Entity entity = (Entity) list.get(i);

            if (entity.canBeCollidedWith()) {
                float f10 = entity.getCollisionBorderSize(entity); //本来没参数的
                AxisAlignedBB axisalignedbb = entity.boundingBox.expand(f10, f10, f10);

                if (axisalignedbb.isVecInside(vec3)) {
                    flag = true;
                }
            }
        }

        if (flag) {
            return false;
        }       //movingobjectposition.isBlock() == MovingObjectPosition.MovingObjectType.BLOCK
        if (movingobjectposition.isBlock()) {
            i = movingobjectposition.block_hit_x;
            int j = movingobjectposition.block_hit_x;
            int k = movingobjectposition.block_hit_x;//block_hit_z  =? blockZ

            if (world.getBlock(i, j, k) == Block.snow) {  //snow_layer
                --j;
            }

            EntityNewBoat entityboat;
            if (isChest) {
                entityboat = new EntityNewBoatWithChest(world);
            } else {
                entityboat = new EntityNewBoat(world);
            }
            boolean isWater;
            if(world.isAirBlock(i,j,k))
                isWater=false;
            else
                isWater = world.getBlock(i, j, k).blockMaterial == Material.water;

            entityboat.setPositionAndRotation(movingobjectposition.position_hit.xCoord, movingobjectposition.position_hit.yCoord + (isWater ? -0.12 : 0), movingobjectposition.position_hit.zCoord, player.rotationYaw, 0);
            entityboat.motionX = entityboat.motionY = entityboat.motionZ = 0;
            entityboat.setBoatType( name);
            if (itemStack.hasDisplayName()) {
                entityboat.setBoatName(itemStack.getDisplayName());
            }

            if (!world.getCollidingBoundingBoxes(entityboat, entityboat.boundingBox.expand(-0.1D, -0.1D, -0.1D)).isEmpty()) {
                return false;
            }

            if (!world.isRemote) {
                world.spawnEntityInWorld(entityboat);
            }

            if (!player.capabilities.isCreativeMode) {
                --itemStack.stackSize;
            }
        }

        return true;
    }



    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        float f = 1.0F;
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f + 1.62D - (double) player.yOffset;
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
        //public final RaycastCollision tryRaycastVsBlocks(Raycast raycast) {
        //Raycast raycast=new Raycast(world,vec3,vec31);
        RaycastCollision movingobjectposition = world.getBlockCollisionForSelection(vec3, vec31, true);

        if (movingobjectposition == null) {
            return itemStack;
        }
        Vec3 vec32 = player.getLook(f);
        boolean flag = false;
        float f9 = 1.0F;
        List list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3).expand(f9, f9, f9));
        int i;

        for (i = 0; i < list.size(); ++i) {
            Entity entity = (Entity) list.get(i);

            if (entity.canBeCollidedWith()) {
                float f10 = entity.getCollisionBorderSize(entity); //本来没参数的
                AxisAlignedBB axisalignedbb = entity.boundingBox.expand(f10, f10, f10);

                if (axisalignedbb.isVecInside(vec3)) {
                    flag = true;
                }
            }
        }

        if (flag) {
            return itemStack;
        }//movingobjectposition.isBlock() == MovingObjectPosition.MovingObjectType.BLOCK
        if (movingobjectposition.isBlock()) {
            i = movingobjectposition.block_hit_x;
            int j = movingobjectposition.block_hit_x;
            int k = movingobjectposition.block_hit_x;//block_hit_z  =? blockZ

            if (world.getBlock(i, j, k) == Block.snow) {  //snow_layer
                --j;
            }

            EntityNewBoat entityboat;
            if (isChest) {
                entityboat = new EntityNewBoatWithChest(world);
            } else {
                entityboat = new EntityNewBoat(world);
            }
            boolean isWater = world.getBlock(i, j, k).blockMaterial == Material.water;
            entityboat.setPositionAndRotation(movingobjectposition.position_hit.xCoord, movingobjectposition.position_hit.yCoord + (isWater ? -0.12 : 0), movingobjectposition.position_hit.zCoord, player.rotationYaw, 0);
            entityboat.motionX = entityboat.motionY = entityboat.motionZ = 0;
            entityboat.setBoatType( name);
            if (itemStack.hasDisplayName()) {
                entityboat.setBoatName(itemStack.getDisplayName());
            }

            if (!world.getCollidingBoundingBoxes(entityboat, entityboat.boundingBox.expand(-0.1D, -0.1D, -0.1D)).isEmpty()) {
                return itemStack;
            }

            if (!world.isRemote) {
                world.spawnEntityInWorld(entityboat);
            }

            if (!player.capabilities.isCreativeMode) {
                --itemStack.stackSize;
            }
        }

        return itemStack;
    }

    public class BoatInfo {
        private final ItemStack boatItem;
        private final Supplier<Item> plank;
        private final int plankMeta;
        private final boolean raft;

        public BoatInfo(ItemStack boatItem, Supplier<Item> plank, int plankMeta, boolean raft) {
            this.boatItem = boatItem;
            this.plank = plank;
            this.plankMeta = plankMeta;
            this.raft = raft;
        }

        public ItemStack getBoatItem() {
            return boatItem.copy();
        }

        public ItemStack getPlank() {
            return new ItemStack(plank.get(), 1, plankMeta);
        }

        public boolean isRaft() {
            return raft;
        }
    }
}
