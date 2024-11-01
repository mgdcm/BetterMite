package com.github.FlyBird.BetterMite.render;

import net.minecraft.*;

public class RenderBamboo {
    public static RenderBamboo instance=new RenderBamboo();

    public void drawBamboo(Block par1Block, int metadata, int x, int y, int z, Icon photo, IBlockAccess blockAccess) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(blockAccess, x, y, z));

        float var7 = 1.0f;
        int var8 = par1Block.colorMultiplier(blockAccess, x, y, z);
        float var9 = (float)(var8 >> 16 & 0xFF) / 255.0f;
        float var10 = (float)(var8 >> 8 & 0xFF) / 255.0f;
        float var11 = (float)(var8 & 0xFF) / 255.0f;
        if (EntityRenderer.anaglyphEnable) {
            float var12 = (var9 * 30.0f + var10 * 59.0f + var11 * 11.0f) / 100.0f;
            float var13 = (var9 * 30.0f + var10 * 70.0f) / 100.0f;
            float var14 = (var9 * 30.0f + var11 * 70.0f) / 100.0f;
            var9 = var12;
            var10 = var13;
            var11 = var14;
        }
        tessellator.setColorOpaque_F(var7 * var9, var7 * var10, var7 * var11);
        //UV 贴图中的 U 和 V 表示 2D 纹理的水平轴和垂直轴，因为 X、Y 和 Z 已经用于表示 3D 空间的这些轴。
            double offset_length = 0.125;

            double new_x = x + 0.4375;
            double new_y = y + 0.4375;
            double new_z = z + 0.4375;

            double chainMinU = photo.getInterpolatedU(3);
            double chainMinV = photo.getInterpolatedV(0);
            double chainMaxU = photo.getInterpolatedU(6);
            double chainMaxV = photo.getInterpolatedV(16);

            if (metadata == 0) {
                // Y方向渲染锁链
                tessellator.addVertexWithUV(new_x, y, new_z, chainMaxU, chainMaxV);
                tessellator.addVertexWithUV(new_x, y + 1.0, new_z, chainMaxU, chainMinV);
                tessellator.addVertexWithUV(new_x + offset_length, y + 1.0, new_z + offset_length, chainMinU, chainMinV);
                tessellator.addVertexWithUV(new_x + offset_length, y, new_z + offset_length, chainMinU, chainMaxV);

                tessellator.addVertexWithUV(new_x + offset_length, y, new_z + offset_length, chainMinU, chainMaxV);
                tessellator.addVertexWithUV(new_x + offset_length, y + 1.0, new_z + offset_length, chainMinU, chainMinV);
                tessellator.addVertexWithUV(new_x, y + 1.0, new_z, chainMaxU, chainMinV);
                tessellator.addVertexWithUV(new_x, y, new_z, chainMaxU, chainMaxV);

                chainMinU = photo.getInterpolatedU(0);
                chainMinV = photo.getInterpolatedV(0);
                chainMaxU = photo.getInterpolatedU(3);
                chainMaxV = photo.getInterpolatedV(16);

                tessellator.addVertexWithUV(new_x + offset_length, y, new_z, chainMinU, chainMaxV);
                tessellator.addVertexWithUV(new_x + offset_length, y + 1.0, new_z, chainMinU, chainMinV);
                tessellator.addVertexWithUV(new_x, y + 1.0, new_z + offset_length, chainMaxU, chainMinV);
                tessellator.addVertexWithUV(new_x, y, new_z + offset_length, chainMaxU, chainMaxV);

                tessellator.addVertexWithUV(new_x, y, new_z + offset_length, chainMaxU, chainMaxV);
                tessellator.addVertexWithUV(new_x, y + 1.0, new_z + offset_length, chainMaxU, chainMinV);
                tessellator.addVertexWithUV(new_x + offset_length, y + 1.0, new_z, chainMinU, chainMinV);
                tessellator.addVertexWithUV(new_x + offset_length, y, new_z, chainMinU, chainMaxV);
            }
            if (metadata == 8)//Z方向渲染锁链
            {
                chainMinU = photo.getInterpolatedU(3);
                chainMinV = photo.getInterpolatedV(0);
                chainMaxU = photo.getInterpolatedU(6);
                chainMaxV = photo.getInterpolatedV(16);

                tessellator.addVertexWithUV(new_x, new_y, z, chainMinU, chainMaxV);
                tessellator.addVertexWithUV(new_x + offset_length, new_y + offset_length, z, chainMaxU, chainMaxV);
                tessellator.addVertexWithUV(new_x + offset_length, new_y + offset_length, z + 1, chainMaxU, chainMinV);
                tessellator.addVertexWithUV(new_x, new_y, z + 1, chainMinU, chainMinV);

                tessellator.addVertexWithUV(new_x, new_y, z + 1, chainMinU, chainMinV);
                tessellator.addVertexWithUV(new_x + offset_length, new_y + offset_length, z + 1, chainMaxU, chainMinV);
                tessellator.addVertexWithUV(new_x + offset_length, new_y + offset_length, z, chainMaxU, chainMaxV);
                tessellator.addVertexWithUV(new_x, new_y, z, chainMinU, chainMaxV);

                chainMinU = photo.getInterpolatedU(0);
                chainMinV = photo.getInterpolatedV(0);
                chainMaxU = photo.getInterpolatedU(3);
                chainMaxV = photo.getInterpolatedV(16);

                tessellator.addVertexWithUV(new_x, new_y + offset_length, z, chainMinU, chainMaxV);
                tessellator.addVertexWithUV(new_x + offset_length, new_y, z, chainMaxU, chainMaxV);
                tessellator.addVertexWithUV(new_x + offset_length, new_y, z + 1, chainMaxU, chainMinV);
                tessellator.addVertexWithUV(new_x, new_y + offset_length, z + 1, chainMinU, chainMinV);

                tessellator.addVertexWithUV(new_x, new_y + offset_length, z + 1, chainMinU, chainMinV);
                tessellator.addVertexWithUV(new_x + offset_length, new_y, z + 1, chainMaxU, chainMinV);
                tessellator.addVertexWithUV(new_x + offset_length, new_y, z, chainMaxU, chainMaxV);
                tessellator.addVertexWithUV(new_x, new_y + offset_length, z, chainMinU, chainMaxV);
            }
            if (metadata == 4) {
                chainMinU = photo.getInterpolatedU(3);
                chainMinV = photo.getInterpolatedV(0);
                chainMaxU = photo.getInterpolatedU(6);
                chainMaxV = photo.getInterpolatedV(16);

                tessellator.addVertexWithUV(x, new_y, new_z, chainMinU, chainMaxV);
                tessellator.addVertexWithUV(x, new_y + offset_length, new_z + offset_length, chainMaxU, chainMaxV);
                tessellator.addVertexWithUV(x + 1, new_y + offset_length, new_z + offset_length, chainMaxU, chainMinV);
                tessellator.addVertexWithUV(x + 1, new_y, new_z, chainMinU, chainMinV);

                tessellator.addVertexWithUV(x + 1, new_y, new_z, chainMinU, chainMinV);
                tessellator.addVertexWithUV(x + 1, new_y + offset_length, new_z + offset_length, chainMaxU, chainMinV);
                tessellator.addVertexWithUV(x, new_y + offset_length, new_z + offset_length, chainMaxU, chainMaxV);
                tessellator.addVertexWithUV(x, new_y, new_z, chainMinU, chainMaxV);

                chainMinU = photo.getInterpolatedU(0);
                chainMinV = photo.getInterpolatedV(0);
                chainMaxU = photo.getInterpolatedU(3);
                chainMaxV = photo.getInterpolatedV(16);

                tessellator.addVertexWithUV(x, new_y + offset_length, new_z, chainMinU, chainMaxV);
                tessellator.addVertexWithUV(x, new_y, new_z + offset_length, chainMaxU, chainMaxV);
                tessellator.addVertexWithUV(x + 1, new_y, new_z + offset_length, chainMaxU, chainMinV);
                tessellator.addVertexWithUV(x + 1, new_y + offset_length, new_z, chainMinU, chainMinV);

                tessellator.addVertexWithUV(x + 1, new_y + offset_length, new_z, chainMinU, chainMinV);
                tessellator.addVertexWithUV(x + 1, new_y, new_z + offset_length, chainMaxU, chainMinV);
                tessellator.addVertexWithUV(x, new_y, new_z + offset_length, chainMaxU, chainMaxV);
                tessellator.addVertexWithUV(x, new_y + offset_length, new_z, chainMinU, chainMaxV);
            }
    }
}
