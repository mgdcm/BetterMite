package com.github.FlyBird.BetterMite.render;

import net.minecraft.Block;
import net.minecraft.IBlockAccess;
import net.minecraft.Icon;
import net.minecraft.Tessellator;

public class RenderNormalCampfire {
    public static RenderNormalCampfire instance=new RenderNormalCampfire();

    public void DrawRenderCampfire(Block par1Block, int metadata, int x, int y, int z, IBlockAccess blockAccess)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(blockAccess, x, y, z));
        tessellator.setColorOpaque_F(1.0f, 1.0f, 1.0f);

        Icon picture =par1Block.getIcon(2,0);//获取营火贴图

        //木头边的UV图
        double WoodSideMinU=picture.getInterpolatedU(0);
        double WoodSideMinV=picture.getInterpolatedV(0);
        double WoodSideMaxU=picture.getInterpolatedU(16);
        double WoodSideMaxV=picture.getInterpolatedV(4);

        //木头正面的UV图
        double WoodFaceMinU=picture.getInterpolatedU(0);
        double WoodFaceMinV=picture.getInterpolatedV(4);
        double WoodFaceMaxU=picture.getInterpolatedU(4);
        double WoodFaceMaxV=picture.getInterpolatedV(8);

        //石头 侧面纹理
        double BaseSideMinU=picture.getInterpolatedU(0);
        double BaseSideMinV=picture.getInterpolatedV(15);
        double BaseSideMaxU=picture.getInterpolatedU(6);
        double BaseSideMaxV=picture.getInterpolatedV(16);

        //石头 底面纹理
        double StoneBaseMinU=picture.getInterpolatedU(0);
        double StoneBaseMinV=picture.getInterpolatedV(10);
        double StoneMaxU=picture.getInterpolatedU(16);
        double StoneBaseMaxV=picture.getInterpolatedV(16);

        double length_offset=0.25;
        double Set_x=x+0.0625;
        double Set_y=y;
        double Set_z=z;

        int direction=metadata&0xC;

        if(direction==4||direction==0) {
//绘制右支柱木头
            tessellator.addVertexWithUV(Set_x + length_offset, Set_y, Set_z, WoodSideMaxU, WoodSideMaxV);   //底面
            tessellator.addVertexWithUV(Set_x + length_offset, Set_y, Set_z + 1, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z + 1, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z, WoodSideMaxU, WoodSideMinV);

            tessellator.addVertexWithUV(Set_x, Set_y, Set_z, WoodFaceMaxU, WoodFaceMaxV);//木头正面
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z, WoodFaceMaxU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_x + length_offset, Set_y + length_offset, Set_z, WoodFaceMinU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_x + length_offset, Set_y, Set_z, WoodFaceMinU, WoodFaceMaxV);

            tessellator.addVertexWithUV(Set_x + length_offset, Set_y, Set_z + 1, WoodFaceMinU, WoodFaceMaxV);//木头另外一边正面
            tessellator.addVertexWithUV(Set_x + length_offset, Set_y + length_offset, Set_z + 1, WoodFaceMinU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z + 1, WoodFaceMaxU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z + 1, WoodFaceMaxU, WoodFaceMaxV);

            tessellator.addVertexWithUV(Set_x, Set_y, Set_z, WoodSideMaxU, WoodSideMinV);//侧面 右边
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z + 1, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z + 1, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z, WoodSideMaxU, WoodSideMaxV);

            Set_x = Set_x + length_offset;
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z, WoodSideMaxU, WoodSideMaxV);//侧面 左边
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z + 1, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z + 1, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z, WoodSideMaxU, WoodSideMinV);

            Set_x = Set_x - length_offset;
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z, WoodSideMaxU, WoodSideMinV);//侧面 最上面
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z + 1, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x + length_offset, Set_y + length_offset, Set_z + 1, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_x + length_offset, Set_y + length_offset, Set_z, WoodSideMaxU, WoodSideMaxV);

//绘制左支柱木头
            Set_x = x + 1.0 - length_offset - 0.0625;

            tessellator.addVertexWithUV(Set_x + length_offset, Set_y, Set_z, WoodSideMaxU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_x + length_offset, Set_y, Set_z + 1, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z + 1, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z, WoodSideMaxU, WoodSideMinV);

            tessellator.addVertexWithUV(Set_x, Set_y, Set_z, WoodFaceMaxU, WoodFaceMaxV);//木头正面
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z, WoodFaceMaxU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_x + length_offset, Set_y + length_offset, Set_z, WoodFaceMinU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_x + length_offset, Set_y, Set_z, WoodFaceMinU, WoodFaceMaxV);

            tessellator.addVertexWithUV(Set_x + length_offset, Set_y, Set_z + 1, WoodFaceMinU, WoodFaceMaxV);//木头另外一边正面
            tessellator.addVertexWithUV(Set_x + length_offset, Set_y + length_offset, Set_z + 1, WoodFaceMinU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z + 1, WoodFaceMaxU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z + 1, WoodFaceMaxU, WoodFaceMaxV);

            tessellator.addVertexWithUV(Set_x, Set_y, Set_z, WoodSideMaxU, WoodSideMinV);//侧面 右边
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z + 1, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z + 1, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z, WoodSideMaxU, WoodSideMaxV);

            Set_x = Set_x + length_offset;
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z, WoodSideMaxU, WoodSideMaxV);//侧面 左边
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z + 1, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z + 1, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z, WoodSideMaxU, WoodSideMinV);

            Set_x = Set_x - length_offset;
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z, WoodSideMaxU, WoodSideMinV);//侧面 最上面
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z + 1, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x + length_offset, Set_y + length_offset, Set_z + 1, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_x + length_offset, Set_y + length_offset, Set_z, WoodSideMaxU, WoodSideMaxV);

//石头的绘制
            Set_x = x + length_offset + 0.0625;
            length_offset = 0.375;

            tessellator.addVertexWithUV(Set_x + length_offset, Set_y, Set_z, StoneMaxU, StoneBaseMaxV);
            tessellator.addVertexWithUV(Set_x + length_offset, Set_y, Set_z + 1, StoneBaseMinU, StoneBaseMaxV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z + 1, StoneBaseMinU, StoneBaseMinV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z, StoneMaxU, StoneBaseMinV);

            tessellator.addVertexWithUV(Set_x, Set_y + 0.0625, Set_z, StoneMaxU, StoneBaseMinV);
            tessellator.addVertexWithUV(Set_x, Set_y + 0.0625, Set_z + 1, StoneBaseMinU, StoneBaseMinV);
            tessellator.addVertexWithUV(Set_x + length_offset, Set_y + 0.0625, Set_z + 1, StoneBaseMinU, StoneBaseMaxV);
            tessellator.addVertexWithUV(Set_x + length_offset, Set_y + 0.0625, Set_z, StoneMaxU, StoneBaseMaxV);

            tessellator.addVertexWithUV(Set_x, Set_y, Set_z, BaseSideMaxU, BaseSideMaxV);
            tessellator.addVertexWithUV(Set_x, Set_y + 0.0625, Set_z, BaseSideMaxU, BaseSideMinV);
            tessellator.addVertexWithUV(Set_x + length_offset, Set_y + 0.0625, Set_z, BaseSideMinU, BaseSideMinV);
            tessellator.addVertexWithUV(Set_x + length_offset, Set_y, Set_z, BaseSideMinU, BaseSideMaxV);

            tessellator.addVertexWithUV(Set_x + length_offset, Set_y, Set_z + 1, BaseSideMinU, BaseSideMaxV);
            tessellator.addVertexWithUV(Set_x + length_offset, Set_y + 0.0625, Set_z + 1, BaseSideMinU, BaseSideMinV);
            tessellator.addVertexWithUV(Set_x, Set_y + 0.0625, Set_z + 1, BaseSideMaxU, BaseSideMinV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z + 1, BaseSideMaxU, BaseSideMaxV);

//上方木头一支
            Set_y = y + 0.1875;
            Set_x = x;
            Set_z = z + 0.0625;
            length_offset = 0.25;

            tessellator.addVertexWithUV(Set_x, Set_y, Set_z, WoodSideMaxU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x + 1, Set_y + length_offset, Set_z, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x + 1, Set_y, Set_z, WoodSideMinU, WoodSideMaxV);

            tessellator.addVertexWithUV(Set_x + 1, Set_y, Set_z + length_offset, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_x + 1, Set_y + length_offset, Set_z + length_offset, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z + length_offset, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z + length_offset, WoodSideMaxU, WoodSideMaxV);

            tessellator.addVertexWithUV(Set_x + 1, Set_y, Set_z, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_x + 1, Set_y, Set_z + length_offset, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z + length_offset, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z, WoodSideMaxU, WoodSideMaxV);

            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z, WoodSideMaxU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z + length_offset, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x + 1, Set_y + length_offset, Set_z + length_offset, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x + 1, Set_y + length_offset, Set_z, WoodSideMinU, WoodSideMaxV);

            tessellator.addVertexWithUV(Set_x, Set_y, Set_z, WoodFaceMinU, WoodFaceMaxV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z + length_offset, WoodFaceMaxU, WoodFaceMaxV);
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z + length_offset, WoodFaceMaxU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z, WoodFaceMinU, WoodFaceMinV);

            tessellator.addVertexWithUV(Set_x + 1, Set_y + length_offset, Set_z, WoodFaceMinU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_x + 1, Set_y + length_offset, Set_z + length_offset, WoodFaceMaxU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_x + 1, Set_y, Set_z + length_offset, WoodFaceMaxU, WoodFaceMaxV);
            tessellator.addVertexWithUV(Set_x + 1, Set_y, Set_z, WoodFaceMinU, WoodFaceMaxV);

//上方木头一支
            Set_z = z + 1 - 0.0625 - length_offset;
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z, WoodSideMaxU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x + 1, Set_y + length_offset, Set_z, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x + 1, Set_y, Set_z, WoodSideMinU, WoodSideMaxV);

            tessellator.addVertexWithUV(Set_x + 1, Set_y, Set_z + length_offset, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_x + 1, Set_y + length_offset, Set_z + length_offset, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z + length_offset, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z + length_offset, WoodSideMaxU, WoodSideMaxV);

            tessellator.addVertexWithUV(Set_x + 1, Set_y, Set_z, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_x + 1, Set_y, Set_z + length_offset, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z + length_offset, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z, WoodSideMaxU, WoodSideMaxV);

            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z, WoodSideMaxU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z + length_offset, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x + 1, Set_y + length_offset, Set_z + length_offset, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_x + 1, Set_y + length_offset, Set_z, WoodSideMinU, WoodSideMaxV);

            tessellator.addVertexWithUV(Set_x, Set_y, Set_z, WoodFaceMinU, WoodFaceMaxV);
            tessellator.addVertexWithUV(Set_x, Set_y, Set_z + length_offset, WoodFaceMaxU, WoodFaceMaxV);
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z + length_offset, WoodFaceMaxU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_x, Set_y + length_offset, Set_z, WoodFaceMinU, WoodFaceMinV);

            tessellator.addVertexWithUV(Set_x + 1, Set_y + length_offset, Set_z, WoodFaceMinU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_x + 1, Set_y + length_offset, Set_z + length_offset, WoodFaceMaxU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_x + 1, Set_y, Set_z + length_offset, WoodFaceMaxU, WoodFaceMaxV);
            tessellator.addVertexWithUV(Set_x + 1, Set_y, Set_z, WoodFaceMinU, WoodFaceMaxV);
        }
        if(direction==8)
        {
            Set_x=z+0.0625;
            Set_y=y;
            Set_z=x;

            tessellator.addVertexWithUV(Set_z, Set_y, Set_x, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z+1, Set_y, Set_x, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z+1, Set_y, Set_x + length_offset, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z, Set_y, Set_x + length_offset, WoodSideMaxU, WoodSideMaxV);

            tessellator.addVertexWithUV(Set_z , Set_y, Set_x+ length_offset, WoodFaceMinU, WoodFaceMaxV);
            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x + length_offset, WoodFaceMinU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x, WoodFaceMaxU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_z, Set_y, Set_x, WoodFaceMaxU, WoodFaceMaxV);//木头正面

            tessellator.addVertexWithUV(Set_z+1, Set_y, Set_x, WoodFaceMaxU, WoodFaceMaxV);
            tessellator.addVertexWithUV(Set_z+1, Set_y + length_offset, Set_x, WoodFaceMaxU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_z + 1, Set_y + length_offset, Set_x + length_offset, WoodFaceMinU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_z + 1, Set_y, Set_x + length_offset, WoodFaceMinU, WoodFaceMaxV);//木头另外一边正面

            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x, WoodSideMaxU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z+1, Set_y + length_offset, Set_x, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z+1, Set_y, Set_x, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z, Set_y, Set_x, WoodSideMaxU, WoodSideMinV);//侧面 右边

            Set_x = Set_x + length_offset;
            tessellator.addVertexWithUV(Set_z, Set_y, Set_x, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z+1, Set_y, Set_x, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z+1, Set_y + length_offset, Set_x, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x, WoodSideMaxU, WoodSideMaxV);//侧面 左边

            Set_x = Set_x - length_offset;
            tessellator.addVertexWithUV(Set_z , Set_y + length_offset, Set_x + length_offset, WoodSideMaxU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z+1, Set_y + length_offset, Set_x + length_offset, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z+1, Set_y + length_offset, Set_x, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x, WoodSideMaxU, WoodSideMinV);//侧面 最上面

//绘制左支柱木头
            Set_x = z + 1.0 - length_offset - 0.0625;
            tessellator.addVertexWithUV(Set_z, Set_y, Set_x, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z+1, Set_y, Set_x, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z+1, Set_y, Set_x + length_offset, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z, Set_y, Set_x + length_offset, WoodSideMaxU, WoodSideMaxV);

            tessellator.addVertexWithUV(Set_z, Set_y, Set_x + length_offset, WoodFaceMinU, WoodFaceMaxV);
            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x + length_offset, WoodFaceMinU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x, WoodFaceMaxU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_z, Set_y, Set_x, WoodFaceMaxU, WoodFaceMaxV);//木头正面

            tessellator.addVertexWithUV(Set_z+1, Set_y, Set_x, WoodFaceMaxU, WoodFaceMaxV);
            tessellator.addVertexWithUV(Set_z+1, Set_y + length_offset, Set_x, WoodFaceMaxU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_z+1, Set_y + length_offset, Set_x + length_offset, WoodFaceMinU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_z+1, Set_y, Set_x + length_offset, WoodFaceMinU, WoodFaceMaxV);//木头另外一边正面

            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x, WoodSideMaxU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z+1, Set_y + length_offset, Set_x, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z+1, Set_y, Set_x, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z, Set_y, Set_x, WoodSideMaxU, WoodSideMinV);//侧面 右边

            Set_x = Set_x + length_offset;
            tessellator.addVertexWithUV(Set_z, Set_y, Set_x, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z+1, Set_y, Set_x, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z+1, Set_y + length_offset, Set_x, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x, WoodSideMaxU, WoodSideMaxV);//侧面 左边

            Set_x = Set_x - length_offset;
            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x + length_offset, WoodSideMaxU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z+1, Set_y + length_offset, Set_x + length_offset, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z+1, Set_y + length_offset, Set_x , WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x, WoodSideMaxU, WoodSideMinV);//侧面 最上面

//石头的绘制
            Set_x = z + length_offset + 0.0625;
            length_offset = 0.375;
            tessellator.addVertexWithUV(Set_z, Set_y, Set_x, StoneMaxU, StoneBaseMinV);
            tessellator.addVertexWithUV(Set_z+1, Set_y, Set_x, StoneBaseMinU, StoneBaseMinV);
            tessellator.addVertexWithUV(Set_z + 1, Set_y, Set_x + length_offset, StoneBaseMinU, StoneBaseMaxV);
            tessellator.addVertexWithUV(Set_z, Set_y, Set_x + length_offset, StoneMaxU, StoneBaseMaxV);

            tessellator.addVertexWithUV(Set_z, Set_y + 0.0625, Set_x + length_offset, StoneMaxU, StoneBaseMaxV);
            tessellator.addVertexWithUV(Set_z+1, Set_y + 0.0625, Set_x + length_offset, StoneBaseMinU, StoneBaseMaxV);
            tessellator.addVertexWithUV(Set_z+1, Set_y + 0.0625, Set_x, StoneBaseMinU, StoneBaseMinV);
            tessellator.addVertexWithUV(Set_z, Set_y + 0.0625, Set_x, StoneMaxU, StoneBaseMinV);

            tessellator.addVertexWithUV(Set_z, Set_y, Set_x + length_offset, BaseSideMinU, BaseSideMaxV);
            tessellator.addVertexWithUV(Set_z, Set_y + 0.0625, Set_x + length_offset, BaseSideMinU, BaseSideMinV);
            tessellator.addVertexWithUV(Set_z, Set_y + 0.0625, Set_x, BaseSideMaxU, BaseSideMinV);
            tessellator.addVertexWithUV(Set_z, Set_y, Set_x, BaseSideMaxU, BaseSideMaxV);

            tessellator.addVertexWithUV(Set_z+1, Set_y, Set_x, BaseSideMaxU, BaseSideMaxV);
            tessellator.addVertexWithUV(Set_z+1, Set_y + 0.0625, Set_x, BaseSideMaxU, BaseSideMinV);
            tessellator.addVertexWithUV(Set_z+1, Set_y + 0.0625, Set_x + length_offset, BaseSideMinU, BaseSideMinV);
            tessellator.addVertexWithUV(Set_z+1, Set_y, Set_x + length_offset, BaseSideMinU, BaseSideMaxV);

//上方木头一支
            Set_y = y + 0.1875;
            Set_x = z;
            Set_z = x + 0.0625;
            length_offset = 0.25;

            tessellator.addVertexWithUV(Set_z, Set_y, Set_x + 1, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x + 1, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z, Set_y, Set_x, WoodSideMaxU, WoodSideMaxV);

            tessellator.addVertexWithUV(Set_z + length_offset, Set_y, Set_x, WoodSideMaxU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z + length_offset, Set_y + length_offset, Set_x, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z + length_offset, Set_y + length_offset, Set_x + 1, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z + length_offset, Set_y, Set_x + 1, WoodSideMinU, WoodSideMaxV);

            tessellator.addVertexWithUV(Set_z, Set_y, Set_x, WoodSideMaxU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z+length_offset, Set_y, Set_x, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z+length_offset, Set_y, Set_x+1, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z, Set_y, Set_x+1, WoodSideMinU, WoodSideMaxV);

            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x + 1, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z+length_offset, Set_y + length_offset, Set_x + 1, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z+length_offset, Set_y + length_offset, Set_x, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x, WoodSideMaxU, WoodSideMaxV);

            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x, WoodFaceMinU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_z+length_offset, Set_y + length_offset, Set_x, WoodFaceMaxU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_z+length_offset, Set_y, Set_x, WoodFaceMaxU, WoodFaceMaxV);
            tessellator.addVertexWithUV(Set_z, Set_y, Set_x, WoodFaceMinU, WoodFaceMaxV);

            tessellator.addVertexWithUV(Set_z, Set_y, Set_x + 1, WoodFaceMinU, WoodFaceMaxV);
            tessellator.addVertexWithUV(Set_z+length_offset, Set_y, Set_x + 1, WoodFaceMaxU, WoodFaceMaxV);
            tessellator.addVertexWithUV(Set_z+length_offset, Set_y + length_offset, Set_x + 1, WoodFaceMaxU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x + 1, WoodFaceMinU, WoodFaceMinV);

//上方木头一支
            Set_z = x + 1 - 0.0625 - length_offset;
            tessellator.addVertexWithUV(Set_z, Set_y, Set_x + 1, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x + 1, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z, Set_y, Set_x, WoodSideMaxU, WoodSideMaxV);

            tessellator.addVertexWithUV(Set_z + length_offset, Set_y, Set_x, WoodSideMaxU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z + length_offset, Set_y + length_offset, Set_x, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z + length_offset, Set_y + length_offset, Set_x+1, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z + length_offset, Set_y, Set_x+1, WoodSideMinU, WoodSideMaxV);

            tessellator.addVertexWithUV(Set_z, Set_y, Set_x, WoodSideMaxU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z+length_offset, Set_y, Set_x, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z+length_offset, Set_y, Set_x + 1, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z, Set_y, Set_x + 1, WoodSideMinU, WoodSideMaxV);

            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x + 1, WoodSideMinU, WoodSideMaxV);
            tessellator.addVertexWithUV(Set_z+length_offset ,Set_y + length_offset, Set_x + 1, WoodSideMinU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z+length_offset, Set_y + length_offset, Set_x, WoodSideMaxU, WoodSideMinV);
            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x, WoodSideMaxU, WoodSideMaxV);

            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x, WoodFaceMinU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_z+length_offset, Set_y + length_offset, Set_x, WoodFaceMaxU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_z+length_offset, Set_y, Set_x, WoodFaceMaxU, WoodFaceMaxV);
            tessellator.addVertexWithUV(Set_z, Set_y, Set_x, WoodFaceMinU, WoodFaceMaxV);

            tessellator.addVertexWithUV(Set_z, Set_y, Set_x + 1, WoodFaceMinU, WoodFaceMaxV);
            tessellator.addVertexWithUV(Set_z+length_offset, Set_y, Set_x + 1, WoodFaceMaxU, WoodFaceMaxV);
            tessellator.addVertexWithUV(Set_z+length_offset, Set_y + length_offset, Set_x + 1, WoodFaceMaxU, WoodFaceMinV);
            tessellator.addVertexWithUV(Set_z, Set_y + length_offset, Set_x + 1, WoodFaceMinU, WoodFaceMinV);
        }

    }
}
