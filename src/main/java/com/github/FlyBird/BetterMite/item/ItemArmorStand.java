package com.github.FlyBird.BetterMite.item;



import com.github.FlyBird.BetterMite.entity.EntityArmorStand;
import com.github.FlyBird.BetterMite.entity.Rotations;
import com.github.FlyBird.BetterMite.misc.NBTTagCompoundExtend;
import net.minecraft.*;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ItemArmorStand extends Item  {

	public ItemArmorStand(int id, Material material, String texture) {
		super(id,material,texture);
		setMaxStackSize(16);
		//setTextureName("wooden_armorstand");
		setUnlocalizedName("wooden_armorstand");
		setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
		RaycastCollision rc = player.getSelectedObject(partial_tick, false);
		if (rc == null) {
			// 处理 rc 为空的情况，例如返回原始的 itemStack 或其他逻辑
			return false;
		}
		int x=rc.block_hit_x;
		int y=rc.block_hit_y;
		int z=rc.block_hit_z;

		World world = player.getWorld();
		ItemStack stack=player.getHeldItemStack();
		if(rc.face_hit.isBottom())
			return false;
		if(rc.face_hit.isTop())
			y++;
		if(rc.face_hit.isNorth())
			z--;
		if(rc.face_hit.isNorth())
			z++;
		if(rc.face_hit.isWest())
			x--;
		if(rc.face_hit.isEast())
			x++;

		if (!player.canPlayerEdit(x, y, z, stack))  //!player.canPlayerEdit(x, y, z, side, stack)
			return false;
		boolean flag1 = !world.isAirBlock(x, y, z) && !world.getBlock(x, y, z).isAlwaysReplaceable();//isReplaceable(world, x, y , z)
		flag1 |= !world.isAirBlock(x, y + 1, z) && !world.getBlock(x, y + 1, z).isAlwaysReplaceable();

		if (flag1)
			return false;
		double d0 = x;
		double d1 = y;
		double d2 = z;
		List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(d0, d1, d2, d0 + 1.0D, d1 + 2.0D, d2 + 1.0D));

		if (list.size() > 0)
			return false;
		if (!world.isRemote) {
			world.setBlockToAir(x, y, z);
			world.setBlockToAir(x, y + 1, z);
			EntityArmorStand stand = new EntityArmorStand(world, d0 + 0.5D, d1, d2 + 0.5D);
			float f3 = MathHelper.floor_float((MathHelper.wrapAngleTo180_float(player.rotationYaw - 180.0F) + 22.5F) / 45.0F) * 45.0F;
			stand.setLocationAndAngles(d0 + 0.5D, d1, d2 + 0.5D, f3, 0.0F);
			applyRandomRotations(stand, world.rand);
			NBTTagCompound nbt = stack.getTagCompound();

			if (nbt != null && NBTTagCompoundExtend.hasKey("EntityTag",10,nbt)) {   //nbt.hasKey("EntityTag", 10)
				NBTTagCompound nbt1 = new NBTTagCompound();
				stand.writeToNBTOptional(nbt1);
				merge(nbt1, nbt.getCompoundTag("EntityTag"));
				stand.readFromNBT(nbt1);
			}
           //178 75 253
			world.spawnEntityInWorld(stand);
			if (!player.capabilities.isCreativeMode)
				stack.stackSize--;
		}

		return true;
	}

	/*@Override
	@SuppressWarnings("unchecked")
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side == 0)
			return false;
		else {
			if (side == 1)
				y++;
			if (side == 2)
				z--;
			if (side == 3)
				z++;
			if (side == 4)
				x--;
			if (side == 5)
				x++;

			if (!player.canPlayerEdit(x, y, z, side, stack))
				return false;
			else {
				boolean flag1 = !world.isAirBlock(x, y, z) && !world.getBlock(x, y, z).isReplaceable(world, x, y, z);
				flag1 |= !world.isAirBlock(x, y + 1, z) && !world.getBlock(x, y + 1, z).isReplaceable(world, x, y + 1, z);

				if (flag1)
					return false;
				else {
					double d0 = x;
					double d1 = y;
					double d2 = z;
					List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(d0, d1, d2, d0 + 1.0D, d1 + 2.0D, d2 + 1.0D));

					if (list.size() > 0)
						return false;
					else {
						if (!world.isRemote) {
							world.setBlockToAir(x, y, z);
							world.setBlockToAir(x, y + 1, z);
							EntityArmourStand stand = new EntityArmourStand(world, d0 + 0.5D, d1, d2 + 0.5D);
							float f3 = MathHelper.floor_float((MathHelper.wrapAngleTo180_float(player.rotationYaw - 180.0F) + 22.5F) / 45.0F) * 45.0F;
							stand.setLocationAndAngles(d0 + 0.5D, d1, d2 + 0.5D, f3, 0.0F);
							applyRandomRotations(stand, world.rand);
							NBTTagCompound nbt = stack.getTagCompound();

							if (nbt != null && nbt.hasKey("EntityTag", 10)) {
								NBTTagCompound nbt1 = new NBTTagCompound();
								stand.writeToNBTOptional(nbt1);
								merge(nbt1, nbt.getCompoundTag("EntityTag"));
								stand.readFromNBT(nbt1);
							}

							world.spawnEntityInWorld(stand);
						}

						stack.stackSize--;
						return true;
					}
				}
			}
		}
	}*/

	private void applyRandomRotations(EntityArmorStand armorStand, Random rand) {
		Rotations rotations = armorStand.getHeadRotation();
		float f = rand.nextFloat() * 5.0F;
		float f1 = rand.nextFloat() * 20.0F - 10.0F;
		Rotations rotations1 = new Rotations(rotations.getX() + f, rotations.getY() + f1, rotations.getZ());
		armorStand.setHeadRotation(rotations1);
		rotations = armorStand.getBodyRotation();
		f = rand.nextFloat() * 10.0F - 5.0F;
		rotations1 = new Rotations(rotations.getX(), rotations.getY() + f, rotations.getZ());
		armorStand.setBodyRotation(rotations1);
	}

	@SuppressWarnings("unchecked")
	public void merge(NBTTagCompound nbt, NBTTagCompound other) {
		Iterator<String> iterator = other.getTagMap(other).keySet().iterator();

		while (iterator.hasNext()) {
			String s = iterator.next();
			NBTBase nbtbase = other.getTag(s);

			if (nbtbase.getId() == 10) {
				if (NBTTagCompoundExtend.hasKey(s, 10,nbt)) {
					NBTTagCompound nbttagcompound1 = nbt.getCompoundTag(s);
					merge(nbttagcompound1, (NBTTagCompound) nbtbase);
				} else
					nbt.setTag(s, nbtbase.copy());
			} else
				nbt.setTag(s, nbtbase.copy());
		}
	}
}