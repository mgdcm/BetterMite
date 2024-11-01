package com.github.FlyBird.BetterMite.render;



import com.github.FlyBird.BetterMite.block.Blocks;
import com.github.FlyBird.BetterMite.misc.OpenGLHelperExtend;
import net.minecraft.*;
import org.lwjgl.opengl.GL11;


public class BlockSlimeBlockRender  {

	public static BlockSlimeBlockRender instance=new BlockSlimeBlockRender();

	//renderBlockAsItem 在这个下面
	private float innerSizeMax;
	private float innerSizeMin;

	public void renderInventoryBlock(Block block, int metadata, RenderBlocks renderer) {
		Tessellator tessellator = Tessellator.instance;
		OpenGLHelperExtend.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		OpenGLHelperExtend.enableBlend();
		OpenGLHelperExtend.translate(-0.5F, -0.5F, -0.5F);


		float f = 0.0625F;
		renderer.setRenderBounds(f * 3, f * 3, f * 3, f * 13, f * 13, f * 13);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
		tessellator.draw();

		renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
		tessellator.draw();

		OpenGLHelperExtend.translate(0.5F, 0.5F, 0.5F);
		OpenGLHelperExtend.disableBlend();
	}

     //Et-Futurm-Requiem还有一部分没抄过来

	/*innerSizeMax = *//*secondSize*//*13 * 0.0625F;
		innerSizeMin = (16 - *//*secondSize*//*13) * 0.0625F;
		renderer.renderStandardBlock(block, x, y, z);
		renderer.setRenderBounds(innerSizeMin, innerSizeMin, innerSizeMin, innerSizeMax, innerSizeMax, innerSizeMax);
		renderer.renderAllFaces = true;
		boolean flag = renderer.renderStandardBlock(block, x, y, z);
		renderer.renderAllFaces = false;
		return flag;*/

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer) {
		// 启用混合功能
		OpenGLHelperExtend.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		OpenGLHelperExtend.enableBlend();

		renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
		renderer.renderStandardBlock(Blocks.slimeBlock, x, y, z);

		float f = 0.0625F;
		renderer.setRenderBounds(f * 3, f * 3, f * 3, f * 13, f * 13, f * 13);
		renderer.renderAllFaces = true;
		boolean flag = renderer.renderStandardBlock(Blocks.slimeBlock, x, y, z);
		renderer.renderAllFaces = false;

		// 禁用混合功能
		//OpenGLHelperExtend.disableBlend();

		return flag;

		/*renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
		renderer.renderStandardBlock(Blocks.slimeBlock, x, y, z);
		float f = 0.0625F;
		renderer.setRenderBounds(f * 3, f * 3, f * 3, f * 13, f * 13, f * 13);
		renderer.renderAllFaces = true;
		boolean flag = renderer.renderStandardBlock(Blocks.slimeBlock, x, y, z);
		renderer.renderAllFaces = false;
		return flag;*/
	}

}