package com.github.FlyBird.BetterMite.network;

import com.github.FlyBird.BetterMite.api.BetterMiteNetHandler;
import com.github.FlyBird.BetterMite.entity.EntityArmourStand;
import net.minecraft.*;

;import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ArmourStandInteractMessage extends Packet {

	public int standID;
	public Vec3 hitPos;

	public ArmourStandInteractMessage() {
	}

	@Override
	public void readPacketData(DataInput dataInput) throws IOException {
		standID = dataInput.readInt();
		hitPos = Vec3.createVectorHelper(dataInput.readDouble(), dataInput.readDouble(), dataInput.readDouble());
	}

	@Override
	public void writePacketData(DataOutput dataOutput) throws IOException {
		dataOutput.writeInt(standID);
		dataOutput.writeDouble(hitPos.xCoord);
		dataOutput.writeDouble(hitPos.yCoord);
		dataOutput.writeDouble(hitPos.zCoord);
	}

	@Override
	public void processPacket(NetHandler netHandler) {
		((BetterMiteNetHandler)netHandler).handleArmourStandInteractInfo(this);
	}

	@Override
	public int getPacketSize() {
		return 4;
	}

	public ArmourStandInteractMessage( EntityArmourStand stand) {
		standID = stand.hashCode();  //hashCode 就是获取实体id
		RaycastCollision hit = Minecraft.getMinecraft().objectMouseOver;
		hitPos = Vec3.createVectorHelper(hit.position_hit.xCoord - stand.posX, hit.position_hit.yCoord - stand.posY, hit.position_hit.zCoord - stand.posZ);
	}


}