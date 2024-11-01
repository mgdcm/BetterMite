package com.github.FlyBird.BetterMite.network;


import com.github.FlyBird.BetterMite.api.BetterMiteNetHandler;
import net.minecraft.Entity;
import net.minecraft.NetHandler;
import net.minecraft.Packet;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class BoatMoveMessage extends Packet {
	public int dimensionId;
	public int entityId;
	public double x;
	public double y;
	public double z;
	public float yaw;
	public float pitch;

	public BoatMoveMessage() {

	}

	@Override
	public void readPacketData(DataInput dataInput) throws IOException {
		this.dimensionId = dataInput.readInt();
		this.entityId = dataInput.readInt();
		this.x = dataInput.readDouble();
		this.y = dataInput.readDouble();
		this.z = dataInput.readDouble();
		this.yaw = dataInput.readFloat();
		this.pitch = dataInput.readFloat();
	}

	@Override
	public void writePacketData(DataOutput dataOutput) throws IOException {
		dataOutput.writeInt(this.dimensionId);
		dataOutput.writeInt(this.entityId);
		dataOutput.writeDouble(this.x);
		dataOutput.writeDouble(this.y);
		dataOutput.writeDouble(this.z);
		dataOutput.writeFloat(this.yaw);
		dataOutput.writeFloat(this.pitch);
	}

	@Override
	public void processPacket(NetHandler netHandler) {
		((BetterMiteNetHandler)netHandler).handleBoatMoveInfo(this);
	}

	@Override
	public int getPacketSize() {
		return 0;
	}

	public BoatMoveMessage(Entity entityIn) {
		this.dimensionId = entityIn.worldObj.provider.dimensionId;
		this.entityId = entityIn.entityId;
		this.x = entityIn.posX;
		this.y = entityIn.posY;
		this.z = entityIn.posZ;
		this.yaw = entityIn.rotationYaw;
		this.pitch = entityIn.rotationPitch;
	}

}
