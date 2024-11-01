package com.github.FlyBird.BetterMite.entity;



import com.github.FlyBird.BetterMite.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.*;

import java.util.Collections;
import java.util.List;


public class EntityEndermite extends EntityMob {

	private final EntityAINearestAttackableTarget.Sorter sorter;

	private int lifetime = 0;
	private boolean playerSpawned = false;

	public EntityEndermite(World world) {
		super(world);
		sorter = new EntityAINearestAttackableTarget.Sorter(this);

		setSize(0.4F, 0.3F);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		tasks.addTask(3, new EntityAIWander(this, 1.0D));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 10, true));
	}

	@Override
	public float getEyeHeight() {
		return 0.1F;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(8.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.25D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(2.0D);
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	protected String getLivingSound() {
		return "mob.silverfish.say";
	}

	@Override
	protected String getHurtSound() {
		return "mob.silverfish.hit";
	}

	@Override
	protected String getDeathSound() {
		return "mob.silverfish.kill";
	}

	@Override
	protected void playStepSound(int x, int y, int z, int blockid) {
		playSound("mob.silverfish.step", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
		return;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		lifetime = nbt.getInteger("Lifetime");
		playerSpawned = nbt.getBoolean("PlayerSpawned");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("Lifetime", lifetime);
		nbt.setBoolean("PlayerSpawned", playerSpawned);
	}

	@Override
	public void onUpdate() {
		renderYawOffset = rotationYaw;
		super.onUpdate();
	}

	public boolean isSpawnedByPlayer() {
		return playerSpawned;
	}

	public void setSpawnedByPlayer(boolean spawnedByPlayer) {
		playerSpawned = spawnedByPlayer;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (worldObj.isRemote)
			for (int i = 0; i < 2; i++)
				worldObj.spawnParticle(EnumParticle.portal_underworld, posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height, posZ + (rand.nextDouble() - 0.5D) * width, (rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2.0D);
		else {
			if (!isNoDespawnRequired())
				lifetime++;

			if (lifetime >= 2400)
				setDead();
		}

		if (isSpawnedByPlayer()) {
			double range = 64;
			double radius = range / 2.0;
			int tagetChance = 10;
			if (rand.nextInt(tagetChance) != 0) {
				List<EntityEnderman> list = worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getBoundingBox(posX - radius, posY - 4, posZ - radius, posX + radius, posY + 4, posZ + radius), new IEntitySelector() {
					@Override
					public boolean isEntityApplicable(Entity entity) {
						return entity instanceof EntityEnderman;
					}
				});
				Collections.sort(list, sorter);
				if (!list.isEmpty()) {
					EntityEnderman enderman = list.get(0);
					enderman.setTarget(this);
				}
			}
		}
	}

	@Override
	protected boolean isValidLightLevel() {
		return true;
	}

	@Override
	public boolean getCanSpawnHere(boolean perform_light_check) {
		if (super.getCanSpawnHere(false)) {
			EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, 5.0D,true);
			return entityplayer == null;
		} else
			return false;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	/*@Override
	public ItemStack getPickedResult(MovingObjectPosition target) {
		return ModEntityList.getEggFor(getClass());
	}*/
}