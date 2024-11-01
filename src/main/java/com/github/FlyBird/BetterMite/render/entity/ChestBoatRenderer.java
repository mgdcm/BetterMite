package com.github.FlyBird.BetterMite.render.entity;


import com.github.FlyBird.BetterMite.block.Blocks;
import com.github.FlyBird.BetterMite.entity.EntityNewBoat;
import com.github.FlyBird.BetterMite.entity.EntityNewBoatWithChest;
import net.minecraft.TileEntityChest;
import net.minecraft.TileEntityRenderer;
import org.lwjgl.opengl.GL11;

public class ChestBoatRenderer extends NewBoatRenderer {
	private final TileEntityChest chest = new TileEntityChest();

	public ChestBoatRenderer() {
		chest.setBlock(Blocks.chest);
	}
	@Override
	protected void renderExtraBoatContents(EntityNewBoat boat, float partialTicks) {
		if (boat instanceof EntityNewBoatWithChest) {
			GL11.glRotatef(180f, 0f, 1f, 0f);
			GL11.glScalef(0.8f, 0.8f, 0.8f);
			GL11.glTranslatef(
					((EntityNewBoatWithChest) boat).getChestXOffset(),
					((EntityNewBoatWithChest) boat).getChestHeight(),
					((EntityNewBoatWithChest) boat).getChestZOffset());
			//chest.setWorldObj(this.renderManager.worldObj);
			TileEntityRenderer.instance.renderTileEntityAt(chest, 0, 0, 0, partialTicks);  //TileEntityRenderer  ?TileEntityRendererDispatcher
		}
	}
}
