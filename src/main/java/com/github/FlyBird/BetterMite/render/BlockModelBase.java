package com.github.FlyBird.BetterMite.render;

import net.minecraft.*;

public class BlockModelBase {
    public static BlockModelBase instance =new BlockModelBase();
    public void renderFaceZPos(RenderBlocks renderer, Block block, double dx, double dy, double dz) {
        this.renderFaceZPos(renderer, block, dx, dy, dz, 0, 0, 0);
    }

    public void renderFaceZPos(RenderBlocks renderer, Block block, double dx, double dy, double dz, double offx, double offy, double offz) {
        renderFaceZPos(renderer, block, dx, dy, dz, offx, offy, offz,
                renderer.getBlockIcon(block, renderer.blockAccess, MathHelper.floor_double(dx), MathHelper.floor_double(dy), MathHelper.floor_double(dz), 3));
    }

    /**
     * Renders the ZPos face with proper shading like renderStandardBlock.
     */
    public void renderFaceZPos(RenderBlocks renderer, Block block, double dx, double dy, double dz, double offx, double offy, double offz, Icon icon) {
        final Tessellator tessellator = Tessellator.instance;
        renderer.enableAO = false;

        int x = MathHelper.floor_double(dx);
        int y = MathHelper.floor_double(dy);
        int z = MathHelper.floor_double(dz);

        float f5 = 0.8F;
        int l = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);

        if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y, z + 1, 3)) {
            tessellator.setBrightness(renderer.renderMaxZ - Math.abs(offz) < 1.0D ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1));
            tessellator.setColorOpaque_F(f5, f5, f5);
            renderer.renderFaceZPos(block, dx + offx, dy + offy, dz + offz, icon);
        }
    }

    /**
     * Renders the ZNeg face with proper shading like renderStandardBlock.
     */
    public void renderFaceZNeg(RenderBlocks renderer, Block block, double dx, double dy, double dz) {
        this.renderFaceZNeg(renderer, block, dx, dy, dz, 0, 0, 0);
    }

    public void renderFaceZNeg(RenderBlocks renderer, Block block, double dx, double dy, double dz, double offx, double offy, double offz) {
        renderFaceZNeg(renderer, block, dx, dy, dz, offx, offy, offz,
                renderer.getBlockIcon(block, renderer.blockAccess, MathHelper.floor_double(dx), MathHelper.floor_double(dy), MathHelper.floor_double(dz), 2));
    }

    /**
     * Renders the ZNeg face with proper shading like renderStandardBlock.
     */
    public void renderFaceZNeg(RenderBlocks renderer, Block block, double dx, double dy, double dz, double offx, double offy, double offz, Icon icon) {
        final Tessellator tessellator = Tessellator.instance;
        renderer.enableAO = false;

        int x = MathHelper.floor_double(dx);
        int y = MathHelper.floor_double(dy);
        int z = MathHelper.floor_double(dz);

        float f5 = 0.8F;
        int l = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);

        if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y, z - 1, 2)) {
            tessellator.setBrightness(renderer.renderMinZ + Math.abs(offz) > 0.0D ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1));
            tessellator.setColorOpaque_F(f5, f5, f5);
            renderer.renderFaceZNeg(block, dx + offx, dy + offy, dz + offz, icon);
        }
    }


    protected void renderStandardInventoryCube(Block block, int meta, RenderBlocks renderer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        Tessellator tessellator = Tessellator.instance;
        renderer.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, meta));
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, meta));
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, meta));
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, meta));
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, meta));
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, meta));
        tessellator.draw();
    }

    public void renderFaceXNeg(RenderBlocks renderer, Block block, double dx, double dy, double dz) {
        this.renderFaceXNeg(renderer, block, dx, dy, dz, 0, 0, 0);
    }

    public void renderFaceXNeg(RenderBlocks renderer, Block block, double dx, double dy, double dz, double offx, double offy, double offz) {
        renderFaceXNeg(renderer, block, dx, dy, dz, offx, offy, offz,
                renderer.getBlockIcon(block, renderer.blockAccess, MathHelper.floor_double(dx), MathHelper.floor_double(dy), MathHelper.floor_double(dz), 4));
    }

    /**
     * Renders the XNeg face with proper shading like renderStandardBlock.
     */
    public void renderFaceXNeg(RenderBlocks renderer, Block block, double dx, double dy, double dz, double offx, double offy, double offz, Icon icon) {
        final Tessellator tessellator = Tessellator.instance;
        renderer.enableAO = false;

        int x = MathHelper.floor_double(dx);
        int y = MathHelper.floor_double(dy);
        int z = MathHelper.floor_double(dz);

        float f6 = 0.6F;

        int l = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);

        if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x - 1, y, z, 4)) {
            tessellator.setBrightness(renderer.renderMinX + Math.abs(offx) > 0.0D ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z));
            tessellator.setColorOpaque_F(f6, f6, f6);
            renderer.renderFaceXNeg(block, dx + offx, dy + offy, dz + offz, icon);
        }
    }

    /**
     * Renders the XPos face with proper shading like renderStandardBlock.
     */
    public void renderFaceXPos(RenderBlocks renderer, Block block, double dx, double dy, double dz) {
        this.renderFaceXPos(renderer, block, dx, dy, dz, 0, 0, 0);
    }

    public void renderFaceXPos(RenderBlocks renderer, Block block, double dx, double dy, double dz, double offx, double offy, double offz) {
        renderFaceXPos(renderer, block, dx, dy, dz, offx, offy, offz,
                renderer.getBlockIcon(block, renderer.blockAccess, MathHelper.floor_double(dx), MathHelper.floor_double(dy), MathHelper.floor_double(dz), 5));
    }

    /**
     * Renders the XPos face with proper shading like renderStandardBlock.
     */
    public void renderFaceXPos(RenderBlocks renderer, Block block, double dx, double dy, double dz, double offx, double offy, double offz, Icon icon) {
        final Tessellator tessellator = Tessellator.instance;
        renderer.enableAO = false;

        int x = MathHelper.floor_double(dx);
        int y = MathHelper.floor_double(dy);
        int z = MathHelper.floor_double(dz);

        float f6 = 0.6F;

        int l = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);

        if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x + 1, y, z, 5)) {
            tessellator.setBrightness(renderer.renderMaxX - Math.abs(offx) < 1.0D ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z));
            tessellator.setColorOpaque_F(f6, f6, f6);
            renderer.renderFaceXPos(block, dx + offx, dy + offy, dz + offz, icon);
        }
    }
}
