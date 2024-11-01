package com.github.FlyBird.BetterMite.render;

import net.minecraft.*;

public class RenderLantern {
    public static RenderLantern instance=new RenderLantern();

    public void DrawBlockLantern(Block par1Block, int metadata, int x, int y0, int z, IBlockAccess blockAccess)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(blockAccess, x, y0, z));
        float var7 = 1.0f;
        int var8 = par1Block.colorMultiplier(blockAccess, x, y0, z);
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

        Icon picture =par1Block.getIcon(2,0);//获取灯笼贴图

        double buttomMinU=picture.getInterpolatedU(0.0);
        double buttomMinV=picture.getInterpolatedV(9.0);
        double buttomMaxU=picture.getInterpolatedU(6.0);
        double buttomMaxV=picture.getInterpolatedV(15.0);

        double buttom_offset_x=((16-(6))/2.0)/16.0;
        double buttom_offset_z=((16-(15-9))/2.0)/16.0;
        double buttom_offset_length=0.375;

        double buttom_x=buttom_offset_x+x;
        double buttom_z=buttom_offset_z+z;

        double SideMinU=picture.getInterpolatedU(0.0);
        double SideMinV=picture.getInterpolatedV(2.0);
        double SideMaxU=picture.getInterpolatedU(6.0);
        double SideMaxV=picture.getInterpolatedV(9.0);

        double GapMinU=picture.getInterpolatedU(0.0);
        double GapMinV=picture.getInterpolatedV(2.0);
        double GapMaxU=picture.getInterpolatedU(6.0);
        double GapMaxV=picture.getInterpolatedV(3.0);

        double HeadSideMinU=picture.getInterpolatedU(1.0);
        double HeadSideMinV=picture.getInterpolatedV(0.0);
        double HeadSideMaxU=picture.getInterpolatedU(5.0);
        double HeadSideMaxV=picture.getInterpolatedV(2.0);

        double HeadTopMinU=picture.getInterpolatedU(1.0);
        double HeadTopMinV=picture.getInterpolatedV(10.0);
        double HeadTopMaxU=picture.getInterpolatedU(5.0);
        double HeadTopMaxV=picture.getInterpolatedV(14.0);

        double y=y0;
        if(metadata==1)
        {
            y=y0+0.0625;
        }

        //底部
        tessellator.addVertexWithUV(buttom_x, y , buttom_z, buttomMaxU, buttomMaxV);//1,1
        tessellator.addVertexWithUV(buttom_x+buttom_offset_length, y , buttom_z, buttomMinU, buttomMaxV);//0 1
        tessellator.addVertexWithUV(buttom_x+buttom_offset_length, y , buttom_z+buttom_offset_length, buttomMinU, buttomMinV);//0 0
        tessellator.addVertexWithUV(buttom_x, y , buttom_z+buttom_offset_length, buttomMaxU, buttomMinV);

        //四边
        tessellator.addVertexWithUV(buttom_x, y , buttom_z, SideMinU, SideMaxV);//0,1
        tessellator.addVertexWithUV(buttom_x, y , buttom_z+buttom_offset_length, SideMaxU, SideMaxV);//1,1
        tessellator.addVertexWithUV(buttom_x, y+0.4375 , buttom_z+buttom_offset_length, SideMaxU, SideMinV);
        tessellator.addVertexWithUV(buttom_x, y+0.4375 , buttom_z, SideMinU, SideMinV);

        tessellator.addVertexWithUV(buttom_x+buttom_offset_length, y+0.4375 , buttom_z, SideMinU, SideMinV);
        tessellator.addVertexWithUV(buttom_x+buttom_offset_length, y+0.4375 , buttom_z+buttom_offset_length, SideMaxU, SideMinV);
        tessellator.addVertexWithUV(buttom_x+buttom_offset_length, y , buttom_z+buttom_offset_length, SideMaxU, SideMaxV);
        tessellator.addVertexWithUV(buttom_x+buttom_offset_length, y , buttom_z, SideMinU, SideMaxV);

        tessellator.addVertexWithUV(buttom_x, y , buttom_z+buttom_offset_length, SideMaxU, SideMaxV);//1,1
        tessellator.addVertexWithUV(buttom_x+buttom_offset_length, y , buttom_z+buttom_offset_length, SideMinU, SideMaxV);//1,1
        tessellator.addVertexWithUV(buttom_x+buttom_offset_length, y+0.4375 , buttom_z+buttom_offset_length, SideMinU, SideMinV);
        tessellator.addVertexWithUV(buttom_x, y+0.4375 , buttom_z+buttom_offset_length, SideMaxU, SideMinV);

        tessellator.addVertexWithUV(buttom_x, y+0.4375 , buttom_z, SideMaxU, SideMinV);
        tessellator.addVertexWithUV(buttom_x+buttom_offset_length, y+0.4375 , buttom_z, SideMinU, SideMinV);
        tessellator.addVertexWithUV(buttom_x+buttom_offset_length, y , buttom_z, SideMinU, SideMaxV);//1,1
        tessellator.addVertexWithUV(buttom_x, y , buttom_z, SideMaxU, SideMaxV);//1,1

        //缝隙
        tessellator.addVertexWithUV(buttom_x, y+0.4375 , buttom_z, GapMinU, GapMaxV);
        tessellator.addVertexWithUV(buttom_x, y+0.4375 , buttom_z+buttom_offset_length, GapMaxU, GapMaxV);
        tessellator.addVertexWithUV(buttom_x+0.0625, y+0.4375 , buttom_z+buttom_offset_length, GapMaxU, GapMinV);
        tessellator.addVertexWithUV(buttom_x+0.0625, y+0.4375 , buttom_z, GapMinU, GapMinV);

        tessellator.addVertexWithUV(buttom_x+buttom_offset_length, y+0.4375 , buttom_z, GapMinU, GapMinV);   //上面的对面
        tessellator.addVertexWithUV(buttom_x+buttom_offset_length-0.0625, y+0.4375 , buttom_z, GapMinU, GapMaxV);
        tessellator.addVertexWithUV(buttom_x+buttom_offset_length-0.0625, y+0.4375 , buttom_z+buttom_offset_length, GapMaxU, GapMaxV);
        tessellator.addVertexWithUV(buttom_x+buttom_offset_length, y+0.4375 , buttom_z+buttom_offset_length, GapMaxU, GapMinV);

        GapMinU=picture.getInterpolatedU(1.0);
        GapMinV=picture.getInterpolatedV(2.0);
        GapMaxU=picture.getInterpolatedU(5.0);
        GapMaxV=picture.getInterpolatedV(3.0);

        tessellator.addVertexWithUV(buttom_x+0.0625, y+0.4375 , buttom_z, GapMaxU, GapMaxV);
        tessellator.addVertexWithUV(buttom_x+0.0625, y+0.4375 , buttom_z+0.0625, GapMaxU, GapMinV);
        tessellator.addVertexWithUV(buttom_x+0.0625+0.25, y+0.4375 , buttom_z+0.0625, GapMinU, GapMinV);
        tessellator.addVertexWithUV(buttom_x+0.0625+0.25, y+0.4375 , buttom_z, GapMinU, GapMaxV);

        tessellator.addVertexWithUV(buttom_x+0.0625, y+0.4375 , buttom_offset_length+buttom_z, GapMaxU, GapMinV);
        tessellator.addVertexWithUV(buttom_x+0.0625+0.25, y+0.4375 , buttom_offset_length+buttom_z, GapMinU, GapMinV);
        tessellator.addVertexWithUV(buttom_x+0.0625+0.25, y+0.4375 , buttom_offset_length+buttom_z-0.0625, GapMinU, GapMaxV);
        tessellator.addVertexWithUV(buttom_x+0.0625, y+0.4375 , buttom_offset_length+buttom_z-0.0625, GapMaxU, GapMaxV);

        //头顶四周
        tessellator.addVertexWithUV(buttom_x+0.0625, y+0.4375+0.125 , buttom_z+0.0625, HeadSideMinU, HeadSideMinV);
        tessellator.addVertexWithUV(buttom_x+0.0625+0.25, y+0.4375+0.125 , buttom_z+0.0625, HeadSideMaxU, HeadSideMinV);
        tessellator.addVertexWithUV(buttom_x+0.0625+0.25, y +0.4375, buttom_z+0.0625, HeadSideMaxU, HeadSideMaxV);//1,1
        tessellator.addVertexWithUV(buttom_x+0.0625, y+0.4375 , buttom_z+0.0625, HeadSideMinU, HeadSideMaxV);

        tessellator.addVertexWithUV(buttom_x+0.0625, y+0.4375 , buttom_z+0.0625+0.25, HeadSideMinU, HeadSideMaxV);
        tessellator.addVertexWithUV(buttom_x+0.0625+0.25, y +0.4375, buttom_z+0.0625+0.25, HeadSideMaxU, HeadSideMaxV);//1,1
        tessellator.addVertexWithUV(buttom_x+0.0625+0.25, y+0.4375+0.125 , buttom_z+0.0625+0.25, HeadSideMaxU, HeadSideMinV);
        tessellator.addVertexWithUV(buttom_x+0.0625, y+0.4375+0.125 , buttom_z+0.0625+0.25, HeadSideMinU, HeadSideMinV);

        tessellator.addVertexWithUV(0.25+buttom_x+0.0625, y+0.4375 , buttom_z+0.0625, HeadSideMinU, HeadSideMaxV);
        tessellator.addVertexWithUV(0.25+buttom_x+0.0625, y+0.4375+0.125 , buttom_z+0.0625, HeadSideMinU, HeadSideMinV);
        tessellator.addVertexWithUV(0.25+buttom_x+0.0625, y+0.4375+0.125 , buttom_z+0.0625+0.25, HeadSideMaxU, HeadSideMinV);
        tessellator.addVertexWithUV(0.25+buttom_x+0.0625, y+0.4375 , buttom_z+0.0625+0.25, HeadSideMaxU, HeadSideMaxV);

        tessellator.addVertexWithUV(buttom_x+0.0625, y+0.4375 , buttom_z+0.0625+0.25, HeadSideMaxU, HeadSideMaxV);
        tessellator.addVertexWithUV(buttom_x+0.0625, y+0.4375+0.125 , buttom_z+0.0625+0.25, HeadSideMaxU, HeadSideMinV);
        tessellator.addVertexWithUV(buttom_x+0.0625, y+0.4375+0.125 , buttom_z+0.0625, HeadSideMinU, HeadSideMinV);
        tessellator.addVertexWithUV(buttom_x+0.0625, y+0.4375 , buttom_z+0.0625, HeadSideMinU, HeadSideMaxV);

        //底部
        double new_x=buttom_x+0.0625;
        double new_y=y+0.4375+0.125;
        double new_z=buttom_z+0.0625;

        tessellator.addVertexWithUV(new_x, new_y , new_z, HeadTopMaxU, HeadTopMaxV);
        tessellator.addVertexWithUV(new_x, new_y , new_z+0.25, HeadTopMaxU, HeadTopMinV);
        tessellator.addVertexWithUV(new_x+0.25, new_y , new_z+0.25, HeadTopMinU, HeadTopMinV);
        tessellator.addVertexWithUV(new_x+0.25, new_y , new_z, HeadTopMinU, HeadTopMaxV);

        //绘制交叉锁链
        double ChainMinU=picture.getInterpolatedU(11.0);
        double ChainMinV=picture.getInterpolatedV(10.0);
        double ChainMaxU=picture.getInterpolatedU(14.0);
        double ChainMaxV=picture.getInterpolatedV(12.0);

        new_x=new_x+0.0625;
        new_z=new_z+0.0625;

        tessellator.addVertexWithUV(new_x,new_y , new_z, ChainMaxU, ChainMaxV);
        tessellator.addVertexWithUV(new_x,new_y+0.125, new_z, ChainMaxU, ChainMinV);
        tessellator.addVertexWithUV(new_x+0.125,new_y+0.125, new_z+0.125, ChainMinU, ChainMinV);
        tessellator.addVertexWithUV(new_x+0.125,new_y, new_z+0.125, ChainMinU, ChainMaxV);

        tessellator.addVertexWithUV(new_x+0.125,new_y, new_z+0.125, ChainMinU, ChainMaxV);
        tessellator.addVertexWithUV(new_x+0.125,new_y+0.125, new_z+0.125, ChainMinU, ChainMinV);
        tessellator.addVertexWithUV(new_x,new_y+0.125, new_z, ChainMaxU, ChainMinV);
        tessellator.addVertexWithUV(new_x,new_y , new_z, ChainMaxU, ChainMaxV);

        if(metadata==0) {//没有锁链链接时
            tessellator.addVertexWithUV(new_x + 0.125, new_y, new_z, ChainMinU, ChainMaxV);
            tessellator.addVertexWithUV(new_x + 0.125, new_y+0.125, new_z, ChainMinU, ChainMinV);
            tessellator.addVertexWithUV(new_x , new_y+0.125, new_z+0.125, ChainMaxU, ChainMinV);
            tessellator.addVertexWithUV(new_x , new_y, new_z+0.125, ChainMaxU, ChainMaxV);

            tessellator.addVertexWithUV(new_x , new_y, new_z+0.125, ChainMaxU, ChainMaxV);
            tessellator.addVertexWithUV(new_x , new_y+0.125, new_z+0.125, ChainMaxU, ChainMinV);
            tessellator.addVertexWithUV(new_x + 0.125, new_y+0.125, new_z, ChainMinU, ChainMinV);
            tessellator.addVertexWithUV(new_x + 0.125, new_y, new_z, ChainMinU, ChainMaxV);

        }
        if(metadata==1)//如果有锁链链接
        {
            double HaspMinU=picture.getInterpolatedU(11.0);
            double HaspMinV=picture.getInterpolatedV(1.0);
            double HaspMaxU=picture.getInterpolatedU(14.0);
            double HaspMaxV=picture.getInterpolatedV(5.0);

            new_y=new_y+0.0625;

            tessellator.addVertexWithUV(new_x+0.125, new_y , new_z, HaspMinU, HaspMaxV);
            tessellator.addVertexWithUV(new_x+0.125, new_y+0.25 , new_z, HaspMinU, HaspMinV);
            tessellator.addVertexWithUV(new_x, new_y+0.25 , new_z+0.125, HaspMaxU, HaspMinV);
            tessellator.addVertexWithUV(new_x, new_y , new_z+0.125, HaspMaxU, HaspMaxV);

            tessellator.addVertexWithUV(new_x, new_y , new_z+0.125, HaspMaxU, HaspMaxV);
            tessellator.addVertexWithUV(new_x, new_y+0.25 , new_z+0.125, HaspMaxU, HaspMinV);
            tessellator.addVertexWithUV(new_x+0.125, new_y+0.25 , new_z, HaspMinU, HaspMinV);
            tessellator.addVertexWithUV(new_x+0.125, new_y , new_z, HaspMinU, HaspMaxV);

            new_y=new_y+0.1875;

            HaspMinU=picture.getInterpolatedU(11.0);
            HaspMinV=picture.getInterpolatedV(6.0);
            HaspMaxU=picture.getInterpolatedU(14.0);
            HaspMaxV=picture.getInterpolatedV(8.0);

            tessellator.addVertexWithUV(new_x, new_y , new_z, HaspMaxU, HaspMaxV);
            tessellator.addVertexWithUV(new_x, new_y+0.125 , new_z, HaspMaxU, HaspMinV);
            tessellator.addVertexWithUV(new_x+0.125, new_y+0.125 , new_z+0.125, HaspMinU, HaspMinV);
            tessellator.addVertexWithUV(new_x+0.125, new_y , new_z+0.125, HaspMinU, HaspMaxV);

            tessellator.addVertexWithUV(new_x+0.125, new_y , new_z+0.125, HaspMinU, HaspMaxV);
            tessellator.addVertexWithUV(new_x+0.125, new_y+0.125 , new_z+0.125, HaspMinU, HaspMinV);
            tessellator.addVertexWithUV(new_x, new_y+0.125 , new_z, HaspMaxU, HaspMinV);
            tessellator.addVertexWithUV(new_x, new_y , new_z, HaspMaxU, HaspMaxV);
        }
    }
}
