package com.github.FlyBird.BetterMite.entity;



import net.minecraft.*;

import java.util.List;
import java.util.UUID;

public class EntityNewBoatSeat extends Entity {

	private EntityNewBoat boat;
	private UUID boatUUID;

	public EntityNewBoatSeat(World world,double x,double y,double z) {
		super(world);
		this.setSize(0, 0);
		this.setPosition(x,y,z);
		this.width = 0;
		this.height = 0;
		this.setInvisible(true);
		this.noClip = true;
	}

	public EntityNewBoatSeat(World world, EntityNewBoat boat,double x,double y,double z) {
		this(world,x,y,z);
		setBoat(boat);
	}

	@Override
	public void entityInit() {
		dataWatcher.addObject(17, boat == null ? 0 : boat.getUniqueIDSilent()); //Attached boat ID
	}

	public void setBoat(EntityNewBoat newBoat) {
		boat = newBoat;
		if (newBoat != null) {
			dataWatcher.updateObject(17, newBoat.entityId);
			this.boatUUID = newBoat.getUniqueIDSilent();
		}
	}

	@Override
	public boolean isEntityInvulnerable() {
		return true;
	}

	public void sitEntity(Entity entity) {
		entity.mountEntity(this);
	}

	@Override
	public void onUpdate() {

		if (isDead || !worldObj.getChunkFromBlockCoords((int) posX, (int) posZ).isChunkLoaded ) {//|| !ConfigBlocksItems.newBoatPassengerSeat
			return;
		}

		if (ridingEntity != null) {
			ridingEntity.mountEntity(null);
			ridingEntity = null;
		}

		if (riddenByEntity != null && !riddenByEntity.isEntityAlive()) {
			riddenByEntity = null;
		}

		int boatID = dataWatcher.getWatchableObjectInt(17);

		if (boat == null && !worldObj.isRemote) {
			if (boatID == 0 && boatUUID != null) {
				for (Entity entity : (List<Entity>) worldObj.loadedEntityList) {
					if (entity == null || entity.getUniqueID() == null)
						continue;
					if (entity.getUniqueID().equals(boatUUID) && entity instanceof EntityNewBoat && !entity.isDead) {
						if (riddenByEntity instanceof EntityPlayer) {
							riddenByEntity.mountEntity(null);
						}
						setBoat((EntityNewBoat) entity);
						break;
					}
				}
			}
		}

		if (boatID > 0 && boat == null) {
			Entity entity = worldObj.getEntityByID(boatID);
			if (entity instanceof EntityNewBoat && !entity.isDead) {
				setBoat((EntityNewBoat) entity);
			}
		}

		if (worldObj.isRemote) {
			boolean flag = false;
			if (boat != null && boat.getSeat() == null) {
				flag = true;
			} else if (boat != null && boat.getSeat() != null && boat.getSeat().entityId != boatID && boat.getSeat().riddenByEntity == null) {
				flag = true;
			}
			if (flag) {
				boat.setSeat(this);
			}
		}

		if (boat == null || boat.isDead) {
			kill();
			return;
		}

		copyLocationAndAnglesFrom(boat);
	}

	private void mountToBoat(Entity entity) {
		boat.sitEntity(entity);
	}

	@Override
	protected void kill() {
		if (riddenByEntity != null) {
			if (riddenByEntity instanceof EntityLivingBase) {
				((EntityLivingBase) riddenByEntity).dismountEntity(this);
			}
			riddenByEntity.mountEntity(null);
		}
		isDead = true;
		boat = null;
	}

	public void setPositionAndRotation2(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_) {
		if (boat != null) {
			copyLocationAndAnglesFrom(boat);
			prevRotationYaw = boat.prevRotationYaw;
			prevRotationPitch = boat.prevRotationPitch;
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		boatUUID = new UUID(nbt.getLong("boatUUIDMost"), nbt.getLong("boatUUIDLeast"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setLong("boatUUIDLeast", boatUUID.getLeastSignificantBits());
		nbt.setLong("boatUUIDMost", boatUUID.getMostSignificantBits());
	}

	/**
	 * The boat this entity is following.
	 */
	public EntityNewBoat getBoat() {
		return boat;
	}

	public boolean writeToNBTOptional(NBTTagCompound p_70039_1_) {
		if (this.ridingEntity != null) {
			ridingEntity.mountEntity(null);
			ridingEntity = null;
		}
		//This entity does not save on disk, we save it through the boat instead.
		return false;
	}

	@Override
	public void updateRiderPosition() {
		if (boat != null && riddenByEntity != null) {
			copyLocationAndAnglesFrom(boat);
			boat.updatePassenger(riddenByEntity);
		}
	}

	/*
	 * ====================DUMMY START====================
	 *
	 *  Any code beyond this point has been canceled out with
	 *  empty methods for performance reasons. This is to
	 *  ensure that code that doesn't matter isn't running.
	 *
	 * ===================================================
	 */


	@Deprecated
	@Override
	public void onEntityUpdate() {

	}

	@Deprecated
	@Override
	public void moveEntity(double p_70091_1_, double p_70091_3_, double p_70091_5_) {

	}

	@Deprecated
	@Override
	public void updateRidden() {

	}

	@Deprecated
	@Override
	public boolean isBurning() {
		return false;
	}

	@Deprecated
	@Override
	public boolean isRiding() {
		return false;

	}


	@Deprecated
	@Override
	public boolean isInvisibleToPlayer(EntityPlayer p_98034_1_) {
		return true;
	}

	@Deprecated
	@Override
	public boolean isInvisible() {
		return true;
	}

	@Deprecated
	@Override
	public void setInWeb() {

	}

	@Deprecated
	@Override
	public boolean canBeAttackedBy(EntityLivingBase attacker) {return true;}
/*
	public boolean hitByEntity(Entity p_85031_1_) {
		return true;
	}
*/

	@Deprecated
	@Override
	public AxisAlignedBB getBoundingBox() {
		return null;
	}

	@Deprecated
	@Override
	public AxisAlignedBB getCollisionBox(Entity p_70114_1_) {
		return null;
	}

	@Deprecated
	@Override
	public void applyEntityCollision(Entity p_70108_1_) {

	}

	@Deprecated
	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Deprecated
	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Deprecated
	@Override
	public boolean canBePushed() {
		return false;
	}

	//找不到
/*	@Deprecated
	@Override
	public boolean shouldRenderInPass(int pass) {
		return false;
	}*/

}
