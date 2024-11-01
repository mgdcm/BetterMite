package com.github.FlyBird.BetterMite.entity;


import com.github.FlyBird.BetterMite.misc.NBTTagCompoundExtend;
import net.minecraft.*;

public class EntityNewBoatWithChest extends EntityNewBoat implements IInventory {
	private ItemStack[] boatItems = new ItemStack[27];
	private boolean dropContentsWhenDead = true;

	public EntityNewBoatWithChest(World world) {
		super(world);
	}

	public EntityNewBoatWithChest(World world,double x,double y,double z) {
		super(world,x,y,z);
	}

	@Override
	protected boolean shouldHaveSeat() {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return 27;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return boatItems[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack returnValue = null;
		if (boatItems[slot] != null) {
			if (boatItems[slot].stackSize <= amount) {
				returnValue = boatItems[slot];
				boatItems[slot] = null;
			} else {
				returnValue = boatItems[slot].splitStack(amount);
				if (boatItems[slot].stackSize == 0)
					boatItems[slot] = null;
			}
		}
		return returnValue;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		if (boatItems[p_70304_1_] != null) {
			ItemStack is = boatItems[p_70304_1_];
			boatItems[p_70304_1_] = null;
			return is;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
		this.boatItems[p_70299_1_] = p_70299_2_;

		if (p_70299_2_ != null && p_70299_2_.stackSize > this.getInventoryStackLimit()) {
			p_70299_2_.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getCustomNameOrUnlocalized() {
		return hasCustomInventoryName() ? getBoatName() : "container.chest_boat";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	public boolean hasCustomInventoryName() {
		return getBoatName() != null;
	}



	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void onInventoryChanged() {

	}

	@Override
	public void openChest() {

	}

	@Override
	public void closeChest() {

	}

	@Override
	public void destroyInventory() {

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return !this.isDead && p_70300_1_.getDistanceSqToEntity(this) <= 64.0D;
	}


	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}


	public void travelToDimension(int p_71027_1_) {
		this.dropContentsWhenDead = false;
		super.travelToDimension(p_71027_1_);
	}

	/**
	 * Will get destroyed next tick.
	 */
	public void setDead() {
		if (this.dropContentsWhenDead) {
			for (int i = 0; i < this.getSizeInventory(); ++i) {
				ItemStack itemstack = this.getStackInSlot(i);

				if (itemstack != null) {
					float f = this.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0) {
						int j = this.rand.nextInt(21) + 10;

						if (j > itemstack.stackSize) {
							j = itemstack.stackSize;
						}

						itemstack.stackSize -= j;
						EntityItem entityitem = new EntityItem(this.worldObj, this.posX + (double) f, this.posY + (double) f1, this.posZ + (double) f2, new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (float) this.rand.nextGaussian() * f3;
						entityitem.motionY = (float) this.rand.nextGaussian() * f3 + 0.2F;
						entityitem.motionZ = (float) this.rand.nextGaussian() * f3;
						this.worldObj.spawnEntityInWorld(entityitem);
					}
				}
			}
		}

		super.setDead();
	}

	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		super.writeEntityToNBT(p_70014_1_);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.boatItems.length; ++i) {
			if (this.boatItems[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.boatItems[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		p_70014_1_.setTag("Items", nbttaglist);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {
		super.readEntityFromNBT(nbtTagCompound);
		NBTTagList nbttaglist = NBTTagCompoundExtend.getTagList("Items", 10,nbtTagCompound);
		this.boatItems = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j < this.boatItems.length) {
				this.boatItems[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	/**
	 * First layer of player interaction
	 */
	public boolean onEntityRightClicked(EntityPlayer player, ItemStack item_stack) {
		if (!this.worldObj.isRemote && player.isSneaking()) {
			//player.displayGUIChest((int) this.posX, (int) this.posY, (int) this.posZ,this);
			player.displayGUIChestForMinecart(this);
			return true;
		}
		return super.onEntityRightClicked(player,item_stack);
	}

/*	public boolean interactFirst(EntityPlayer player) {
		if (!this.worldObj.isRemote && player.isSneaking()) {
			player.displayGUIChest((int) this.posX, (int) this.posY, (int) this.posZ,this);
			return true;
		}
		return super.interactFirst(player);
	}*/

	@Override
	protected float getDefaultRiderOffset() {
		return 0.2f;
	}

	public float getChestHeight() {
		return isRaft() ? 0.15625f : -0.2f;
	}

	public float getChestXOffset() {
		return isRaft() ? -0.46875f : -0.5f;
	}

	public float getChestZOffset() {
		return isRaft() ? -1.15f : -1.1f;
	}
}
