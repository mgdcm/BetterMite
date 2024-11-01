package com.github.FlyBird.BetterMite.entity;

import net.minecraft.NBTTagFloat;
import net.minecraft.NBTTagList;

public class Rotations {

	protected final float x;
	protected final float y;
	protected final float z;

	public Rotations(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Rotations(NBTTagList nbt) {
		NBTTagFloat xTag = (NBTTagFloat) nbt.tagAt(0);
		NBTTagFloat yTag = (NBTTagFloat) nbt.tagAt(1);
		NBTTagFloat zTag = (NBTTagFloat) nbt.tagAt(2);
		this.x = xTag.data;
		this.y = yTag.data;
		this.z = zTag.data;
	}

	public NBTTagList writeToNBT() {
		NBTTagList nbttaglist = new NBTTagList();
		nbttaglist.appendTag(new NBTTagFloat("x",this.x));
		nbttaglist.appendTag(new NBTTagFloat("y",this.y));
		nbttaglist.appendTag(new NBTTagFloat("z",this.z));
		return nbttaglist;
	}

	@Override
	public boolean equals(Object p_equals_1_) {
		if (!(p_equals_1_ instanceof Rotations))
			return false;
		Rotations rotations = (Rotations) p_equals_1_;
		return x == rotations.x && y == rotations.y && z == rotations.z;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	@Override
	public int hashCode() {
		return (int) (x + y + z);
	}
}