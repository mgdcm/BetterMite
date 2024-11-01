package com.github.FlyBird.BetterMite.render;

import com.github.FlyBird.BetterMite.block.BlockStonecutter;
import net.minecraft.*;

public class RenderGrindStone {
    public static RenderGrindStone instance=new RenderGrindStone();
    public void renderStandardInventoryCube(Block block, int meta, RenderBlocks renderer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        BlockModelBase.instance.renderStandardInventoryCube(block, meta, renderer, minX, minY, minZ, maxX, maxY, maxZ);
        Tessellator tessellator=Tessellator.instance;
        tessellator.startDrawingQuads();
        //renderer.setRenderBounds(0.0F, 0, 0.5F, 1.0F, 0.4375F, 0.5F);
        tessellator.draw();
    }
    public void drawGrindStone(Block par1Block, int metadata, int x, int y, int z, IBlockAccess blockAccess,boolean IsItemIcon) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(blockAccess, x, y, z));
        tessellator.setColorOpaque_F(1.0f, 1.0f, 1.0f);

        Icon GrindStonePivot =par1Block.getIcon(2,0);
        Icon GrindStoneRound =par1Block.getIcon(3,0);
        Icon GrindStoneSide =par1Block.getIcon(4,0);
        Icon GrindStoneLeg =par1Block.getIcon(5,0);

        double PillarMinU=GrindStoneLeg.getInterpolatedU(6);
        double PillarMinV=GrindStoneLeg.getInterpolatedV(9);
        double PillarMaxU=GrindStoneLeg.getInterpolatedU(10);
        double PillarMaxV=GrindStoneLeg.getInterpolatedV(16);

        double set_x=x+0.125;
        double set_y=y;
        double set_z=z+0.375;
        double length_offset=0.25;

        if(!IsItemIcon) {
//画右支柱
            tessellator.addVertexWithUV(set_x, set_y, set_z, PillarMaxU, PillarMaxV);     //西面
            tessellator.addVertexWithUV(set_x, set_y, set_z + 0.25, PillarMaxU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y + 0.4375, set_z + 0.25, PillarMinU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y + 0.4375, set_z, PillarMinU, PillarMaxV);


            tessellator.addVertexWithUV(set_x + 0.125, set_y + 0.4375, set_z, PillarMinU, PillarMaxV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y + 0.4375, set_z + 0.25, PillarMinU, PillarMinV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z + 0.25, PillarMaxU, PillarMinV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z, PillarMaxU, PillarMaxV);

            PillarMinU=GrindStoneLeg.getInterpolatedU(12);
            PillarMinV=GrindStoneLeg.getInterpolatedV(9);
            PillarMaxU=GrindStoneLeg.getInterpolatedU(14);
            PillarMaxV=GrindStoneLeg.getInterpolatedV(16);

            tessellator.addVertexWithUV(set_x, set_y, set_z, PillarMaxU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y + 0.4375, set_z, PillarMinU, PillarMinV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y + 0.4375, set_z, PillarMinU, PillarMaxV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z, PillarMaxU, PillarMaxV);

            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z + 0.25, PillarMaxU, PillarMaxV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y + 0.4375, set_z + 0.25, PillarMinU, PillarMaxV);
            tessellator.addVertexWithUV(set_x, set_y + 0.4375, set_z + 0.25, PillarMinU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y, set_z + 0.25, PillarMaxU, PillarMinV);

            PillarMinU=GrindStoneLeg.getInterpolatedU(12);
            PillarMinV=GrindStoneLeg.getInterpolatedV(6);
            PillarMaxU=GrindStoneLeg.getInterpolatedU(14);
            PillarMaxV=GrindStoneLeg.getInterpolatedV(10);

            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z, PillarMinU, PillarMaxV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z + 0.25, PillarMinU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y, set_z + 0.25, PillarMaxU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y, set_z, PillarMaxU, PillarMaxV);


            PillarMinU=GrindStoneLeg.getInterpolatedU(10);
            PillarMinV=GrindStoneLeg.getInterpolatedV(16);
            PillarMaxU=GrindStoneLeg.getInterpolatedU(6);
            PillarMaxV=GrindStoneLeg.getInterpolatedV(6);
//画左支柱
            set_x = x + 1 - 0.25;
            tessellator.addVertexWithUV(set_x, set_y, set_z, PillarMaxU, PillarMaxV);
            tessellator.addVertexWithUV(set_x, set_y, set_z + 0.25, PillarMaxU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y + 0.4375, set_z + 0.25, PillarMinU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y + 0.4375, set_z, PillarMinU, PillarMaxV);

            tessellator.addVertexWithUV(set_x + 0.125, set_y + 0.4375, set_z, PillarMinU, PillarMaxV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y + 0.4375, set_z + 0.25, PillarMinU, PillarMinV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z + 0.25, PillarMaxU, PillarMinV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z, PillarMaxU, PillarMaxV);

            PillarMinU=GrindStoneLeg.getInterpolatedU(12);
            PillarMinV=GrindStoneLeg.getInterpolatedV(9);
            PillarMaxU=GrindStoneLeg.getInterpolatedU(14);
            PillarMaxV=GrindStoneLeg.getInterpolatedV(6);

            tessellator.addVertexWithUV(set_x, set_y, set_z, PillarMaxU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y + 0.4375, set_z, PillarMinU, PillarMinV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y + 0.4375, set_z, PillarMinU, PillarMaxV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z, PillarMaxU, PillarMaxV);

            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z + 0.25, PillarMaxU, PillarMaxV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y + 0.4375, set_z + 0.25, PillarMinU, PillarMaxV);
            tessellator.addVertexWithUV(set_x, set_y + 0.4375, set_z + 0.25, PillarMinU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y, set_z + 0.25, PillarMaxU, PillarMinV);

            PillarMinU=GrindStoneLeg.getInterpolatedU(2);
            PillarMinV=GrindStoneLeg.getInterpolatedV(6);
            PillarMaxU=GrindStoneLeg.getInterpolatedU(4);
            PillarMaxV=GrindStoneLeg.getInterpolatedV(10);

            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z, PillarMinU, PillarMaxV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z + 0.25, PillarMinU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y, set_z + 0.25, PillarMaxU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y, set_z, PillarMaxU, PillarMaxV);

//画右耳机
            PillarMinU = GrindStonePivot.getInterpolatedU(6);
            PillarMinV = GrindStonePivot.getInterpolatedV(0);
            PillarMaxU = GrindStonePivot.getInterpolatedU(8);
            PillarMaxV = GrindStonePivot.getInterpolatedV(6);

            set_x = x + 0.125;
            set_y = y + 0.4375;
            set_z = z + 0.3125;

            tessellator.addVertexWithUV(set_x, set_y, set_z, PillarMaxU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y + 0.375, set_z, PillarMinU, PillarMinV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y + 0.375, set_z, PillarMinU, PillarMaxV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z, PillarMaxU, PillarMaxV);

            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z + 0.375, PillarMaxU, PillarMaxV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y + 0.375, set_z + 0.375, PillarMinU, PillarMaxV);
            tessellator.addVertexWithUV(set_x, set_y + 0.375, set_z + 0.375, PillarMinU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y, set_z + 0.375, PillarMaxU, PillarMinV);


            PillarMinU = GrindStonePivot.getInterpolatedU(0);
            PillarMinV = GrindStonePivot.getInterpolatedV(0);
            PillarMaxU = GrindStonePivot.getInterpolatedU(6);
            PillarMaxV = GrindStonePivot.getInterpolatedV(0);

            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z, PillarMinU, PillarMaxV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z + 0.375, PillarMinU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y, set_z + 0.375, PillarMaxU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y, set_z, PillarMaxU, PillarMaxV);

            tessellator.addVertexWithUV(set_x, set_y + 0.375, set_z, PillarMaxU, PillarMaxV);
            tessellator.addVertexWithUV(set_x, set_y + 0.375, set_z + 0.375, PillarMaxU, PillarMinV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y + 0.375, set_z + 0.375, PillarMinU, PillarMinV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y + 0.375, set_z, PillarMinU, PillarMaxV);

            PillarMinU = GrindStonePivot.getInterpolatedU(0);
            PillarMinV = GrindStonePivot.getInterpolatedV(0);
            PillarMaxU = GrindStonePivot.getInterpolatedU(6);
            PillarMaxV = GrindStonePivot.getInterpolatedV(6);

            tessellator.addVertexWithUV(set_x, set_y, set_z, PillarMinU, PillarMaxV);
            tessellator.addVertexWithUV(set_x, set_y, set_z + 0.375, PillarMaxU, PillarMaxV);
            tessellator.addVertexWithUV(set_x, set_y + 0.375, set_z + 0.375, PillarMaxU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y + 0.375, set_z, PillarMinU, PillarMinV);

//画左耳机
            PillarMinU = GrindStonePivot.getInterpolatedU(6);
            PillarMinV = GrindStonePivot.getInterpolatedV(0);
            PillarMaxU = GrindStonePivot.getInterpolatedU(8);
            PillarMaxV = GrindStonePivot.getInterpolatedV(6);

            set_x = x + 1 - 0.25;
            set_y = y + 0.4375;
            set_z = z + 0.3125;

            tessellator.addVertexWithUV(set_x, set_y, set_z, PillarMaxU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y + 0.375, set_z, PillarMinU, PillarMinV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y + 0.375, set_z, PillarMinU, PillarMaxV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z, PillarMaxU, PillarMaxV);

            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z + 0.375, PillarMaxU, PillarMaxV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y + 0.375, set_z + 0.375, PillarMinU, PillarMaxV);
            tessellator.addVertexWithUV(set_x, set_y + 0.375, set_z + 0.375, PillarMinU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y, set_z + 0.375, PillarMaxU, PillarMinV);


            PillarMinU = GrindStonePivot.getInterpolatedU(0);
            PillarMinV = GrindStonePivot.getInterpolatedV(0);
            PillarMaxU = GrindStonePivot.getInterpolatedU(6);
            PillarMaxV = GrindStonePivot.getInterpolatedV(0);

            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z, PillarMinU, PillarMaxV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z + 0.375, PillarMinU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y, set_z + 0.375, PillarMaxU, PillarMinV);
            tessellator.addVertexWithUV(set_x, set_y, set_z, PillarMaxU, PillarMaxV);

            tessellator.addVertexWithUV(set_x, set_y + 0.375, set_z, PillarMaxU, PillarMaxV);
            tessellator.addVertexWithUV(set_x, set_y + 0.375, set_z + 0.375, PillarMaxU, PillarMinV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y + 0.375, set_z + 0.375, PillarMinU, PillarMinV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y + 0.375, set_z, PillarMinU, PillarMaxV);

            PillarMinU = GrindStonePivot.getInterpolatedU(0);
            PillarMinV = GrindStonePivot.getInterpolatedV(0);
            PillarMaxU = GrindStonePivot.getInterpolatedU(6);
            PillarMaxV = GrindStonePivot.getInterpolatedV(6);

            tessellator.addVertexWithUV(set_x + 0.125, set_y + 0.375, set_z, PillarMinU, PillarMinV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y + 0.375, set_z + 0.375, PillarMaxU, PillarMinV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z + 0.375, PillarMaxU, PillarMaxV);
            tessellator.addVertexWithUV(set_x + 0.125, set_y, set_z, PillarMinU, PillarMaxV);

            set_x = x + 0.25;
            set_y = y + 0.25;
            set_z = z + 0.125;

            double SideMinU = GrindStoneSide.getInterpolatedU(0);
            double SideMinV = GrindStoneSide.getInterpolatedV(0);
            double SideMaxU = GrindStoneSide.getInterpolatedU(12);
            double SideMaxV = GrindStoneSide.getInterpolatedV(12);

            tessellator.addVertexWithUV(set_x, set_y, set_z, SideMinU, SideMaxV);
            tessellator.addVertexWithUV(set_x, set_y, set_z + 0.75, SideMaxU, SideMaxV);
            tessellator.addVertexWithUV(set_x, set_y + 0.75, set_z + 0.75, SideMaxU, SideMinV);
            tessellator.addVertexWithUV(set_x, set_y + 0.75, set_z, SideMinU, SideMinV);

            set_x = x + 1 - 0.25;

            tessellator.addVertexWithUV(set_x, set_y + 0.75, set_z, SideMinU, SideMinV);
            tessellator.addVertexWithUV(set_x, set_y + 0.75, set_z + 0.75, SideMaxU, SideMinV);
            tessellator.addVertexWithUV(set_x, set_y, set_z + 0.75, SideMaxU, SideMaxV);
            tessellator.addVertexWithUV(set_x, set_y, set_z, SideMinU, SideMaxV);

            double RoundMinU = GrindStoneRound.getInterpolatedU(0);
            double RoundMinV = GrindStoneRound.getInterpolatedV(0);
            double RoundMaxU = GrindStoneRound.getInterpolatedU(8);
            double RoundMaxV = GrindStoneRound.getInterpolatedV(12);

            set_x = x + 0.25;
            tessellator.addVertexWithUV(set_x, set_y, set_z, RoundMaxU, RoundMaxV);
            tessellator.addVertexWithUV(set_x, set_y + 0.75, set_z, RoundMaxU, RoundMinV);
            tessellator.addVertexWithUV(set_x + 0.5, set_y + 0.75, set_z, RoundMinU, RoundMinV);
            tessellator.addVertexWithUV(set_x + 0.5, set_y, set_z, RoundMinU, RoundMaxV);

            set_z = set_z + 0.75;
            tessellator.addVertexWithUV(set_x + 0.5, set_y + 0.75, set_z, RoundMinU, RoundMinV);
            tessellator.addVertexWithUV(set_x, set_y + 0.75, set_z, RoundMaxU, RoundMinV);
            tessellator.addVertexWithUV(set_x, set_y, set_z, RoundMaxU, RoundMaxV);
            tessellator.addVertexWithUV(set_x + 0.5, set_y, set_z, RoundMinU, RoundMaxV);

            set_z = set_z - 0.75;
            tessellator.addVertexWithUV(set_x + 0.5, set_y, set_z, RoundMinU, RoundMaxV);
            tessellator.addVertexWithUV(set_x + 0.5, set_y, set_z + 0.75, RoundMinU, RoundMinV);
            tessellator.addVertexWithUV(set_x, set_y, set_z + 0.75, RoundMaxU, RoundMinV);
            tessellator.addVertexWithUV(set_x, set_y, set_z, RoundMaxU, RoundMaxV);

            set_y = set_y + 0.75;
            tessellator.addVertexWithUV(set_x, set_y, set_z, RoundMaxU, RoundMaxV);
            tessellator.addVertexWithUV(set_x, set_y, set_z + 0.75, RoundMaxU, RoundMinV);
            tessellator.addVertexWithUV(set_x + 0.5, set_y, set_z + 0.75, RoundMinU, RoundMinV);
            tessellator.addVertexWithUV(set_x + 0.5, set_y, set_z, RoundMinU, RoundMaxV);
        }

    }

}
