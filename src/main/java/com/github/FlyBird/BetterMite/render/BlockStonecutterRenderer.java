package com.github.FlyBird.BetterMite.render;


import com.github.FlyBird.BetterMite.block.BlockStonecutter;
import net.minecraft.Block;
import net.minecraft.IBlockAccess;
import net.minecraft.RenderBlocks;
import net.minecraft.Tessellator;

public class BlockStonecutterRenderer  {

	public static BlockStonecutterRenderer instance=new BlockStonecutterRenderer();

	public void renderStandardInventoryCube(Block block, int meta, RenderBlocks renderer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		BlockModelBase.instance.renderStandardInventoryCube(block, meta, renderer, minX, minY, minZ, maxX, maxY, maxZ);
		Tessellator tessellator=Tessellator.instance;
		tessellator.startDrawingQuads();
		renderer.setRenderBounds(0.0F, 0, 0.5F, 1.0F, 0.4375F, 0.5F);
		renderer.renderFaceZNeg(block, 0, .5625D, 0, ((BlockStonecutter) block).sawIcon);
		renderer.renderFaceZPos(block, 0, .5625D, 0, ((BlockStonecutter) block).sawIcon);
		tessellator.draw();
	}

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer) {
		int metadata = world.getBlockMetadata(x, y, z) % 4;

		switch (metadata) {
			case 1:
				renderer.uvRotateTop = 3;
				break;
			case 2:
				renderer.uvRotateTop = 2;
				break;
			case 3:
				renderer.uvRotateTop = 1;
				break;
			case 0:
			default:
		}

		renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, .5625F, 1.0F);
		renderer.renderStandardBlock(block, x, y, z);

		if (renderer.overrideBlockTexture==null) {
			renderer.setOverrideBlockTexture(((BlockStonecutter) block).sawIcon);
		}
		renderer.renderAllFaces=true;

		if (metadata < 2) {
			renderer.setRenderBounds(0F, 0.0F, 0.5F, 1F, 0.4375F, 0.5F);
			if (metadata == 1) {
				renderer.flipTexture = true;
			}
			BlockModelBase.instance.renderFaceZNeg(renderer, block, x, y, z, 0, .5625D, 0);
			renderer.flipTexture = metadata == 0;
			BlockModelBase.instance.renderFaceZPos(renderer, block, x, y, z, 0, .5625D, 0);
		} else {
			renderer.setRenderBounds(0.5F, 0.0F, 0F, 0.5F, 0.4375F, 1F);
			if (metadata == 3) {
				renderer.flipTexture = true;
			}
			BlockModelBase.instance.renderFaceXNeg(renderer, block, x, y, z, 0, .5625D, 0);
			renderer.flipTexture = metadata == 2;
			BlockModelBase.instance.renderFaceXPos(renderer, block, x, y, z, 0, .5625D, 0);
		}

		renderer.renderAllFaces=false;
		renderer.clearOverrideBlockTexture();
		renderer.flipTexture = false;
		renderer.uvRotateTop = 0;

		return true;
	}

}
