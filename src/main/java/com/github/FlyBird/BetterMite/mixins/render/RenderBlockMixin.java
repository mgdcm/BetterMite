package com.github.FlyBird.BetterMite.mixins.render;

import com.github.FlyBird.BetterMite.block.BlockBigGrass;
import com.github.FlyBird.BetterMite.block.BlockCampfire;
import com.github.FlyBird.BetterMite.block.Blocks;
import com.github.FlyBird.BetterMite.render.*;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(RenderBlocks.class)
public abstract class RenderBlockMixin {

    @Shadow
    private Icon overrideBlockTexture;

    @Shadow
    public IBlockAccess blockAccess;


    @Shadow public abstract void drawCrossedSquares(Block par1Block, int par2, double par3, double par5, double par7, float par9);

    @Unique
    public Icon getBlockIconFromSideAndMetadata(Block par1Block, int side, int metadata) {
        Icon par1Icon = par1Block.getIcon(side, metadata);
        return par1Icon != null ? par1Icon : ((TextureMap) Minecraft.theMinecraft.getTextureManager().getTexture(TextureMap.locationBlocksTexture)).getAtlasSprite("missingno");
    }

    @Inject(method = "renderItemIn3d", at = @At("HEAD"), cancellable = true)
    private static void renderItemIn3d(int renderType, CallbackInfoReturnable<Boolean> cir) {
        if (renderType == RenderTypes.silmeRenderType) {
            cir.setReturnValue(true);
        }
        if (renderType == RenderTypes.grindStoneRenderType) {
            cir.setReturnValue(false);
        }
        if (renderType == RenderTypes.stoneCutterRenderType) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "renderBlockAsItem", at = @At("RETURN"))
    private void renderBlockAsItem(Block par1Block, int meta, float par3, CallbackInfo ci) {
        int renderType =par1Block.getRenderType();

        if (renderType == RenderTypes.silmeRenderType)BlockSlimeBlockRender.instance.renderInventoryBlock(par1Block,meta,(RenderBlocks) (Object)this);
        if(renderType==RenderTypes.stoneCutterRenderType) BlockStonecutterRenderer.instance.renderStandardInventoryCube(par1Block,meta,(RenderBlocks) (Object)this,0.0, 0.0, 0.0, 1.0, 0.5625, 1.0);
        //if(renderType==RenderTypes.grindStoneRenderType) RenderGrindStone.instance.renderStandardInventoryCube(par1Block,meta,(RenderBlocks) (Object)this,0.125, 0.0, 0.125, 0.875, 1.0, 0.875);
    }
    /**
     * @author
     * @reason
     */
    @Overwrite
    public boolean renderCrossedSquares(Block par1Block, int par2, int par3, int par4) {
        Tessellator.instance.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
        float var6 = 1.0F;
        int var7 = par1Block.colorMultiplier(this.blockAccess, par2, par3, par4);
        float var8 = (float)(var7 >> 16 & 255) / 255.0F;
        float var9 = (float)(var7 >> 8 & 255) / 255.0F;
        float var10 = (float)(var7 & 255) / 255.0F;
        if (EntityRenderer.anaglyphEnable) {
            float var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
            float var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
            float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
            var8 = var11;
            var9 = var12;
            var10 = var13;
        }

        Tessellator.instance.setColorOpaque_F(var6 * var8, var6 * var9, var6 * var10);
        double var19 = (double)par2;
        double var20 = (double)par3;
        double var15 = (double)par4;
        if (par1Block == Blocks.tallGrass||par1Block==Blocks.bigGrass) {
            long var17;
            if(par1Block==Blocks.tallGrass)var17 = (long)(par2 * 3129871L) ^ (long)par4 * 116129781L ^ (long)par3;
            else var17 = (long)(par2 * 3129871L) ^ (long)par4 * 116129781L;
            var17 = var17 * var17 * 42317861L + var17 * 11L;
            var19 += ((double)((float)(var17 >> 16 & 15L) / 15.0F) - 0.5) * 0.5;
            var20 += ((double)((float)(var17 >> 20 & 15L) / 15.0F) - 1.0) * 0.2;
            var15 += ((double)((float)(var17 >> 24 & 15L) / 15.0F) - 0.5) * 0.5;
        }

        this.drawCrossedSquares(par1Block, this.blockAccess.getBlockMetadata(par2, par3, par4), var19, var20, var15, 1.0F);
        return true;
    }
//    @Inject(method = "renderCrossedSquares",at = @At(value = "INVOKE", target = "Lnet/minecraft/RenderBlocks;drawCrossedSquares(Lnet/minecraft/Block;IDDDF)V"),locals = LocalCapture.CAPTURE_FAILSOFT)
//    public void renderCrossedSquares(Block par1Block, int par2, int par3, int par4, CallbackInfoReturnable<Boolean> cir, float var6, int var7, float var8, float var9, float var10, double var19, double var20, double var15, long var16) {
//        if (par1Block == Blocks.bigGrass) {
//            long var17 = (par2 * 3129871L) ^ (long)par4 * 116129781L ^ (long)par3;
//            var17 = var17 * var17 * 42317861L + var17 * 11L;
//            var19 += ((double)((float)(var17 >> 16 & 15L) / 15.0F) - 0.5) * 0.5;
//            var20 += ((double)((float)(var17 >> 20 & 15L) / 15.0F) - 1.0) * 0.2;
//            var15 += ((double)((float)(var17 >> 24 & 15L) / 15.0F) - 0.5) * 0.5;
//        }
//    }

    @Inject(method = "renderBlockByRenderType", at = @At("HEAD"), cancellable = true)
    public void renderBlockByRenderTypes(Block par1Block, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {

        int RenderType = par1Block.getRenderType();
        if (RenderType == RenderTypes.lanternRenderType) {
            RenderLantern.instance.DrawBlockLantern(par1Block, this.blockAccess.getBlockMetadata(x, y, z), x, y, z, this.blockAccess);
            cir.setReturnValue(true);
        }
        if (RenderType == RenderTypes.chainRenderType) {
            Icon Photo = this.overrideBlockTexture == null ? this.getBlockIconFromSideAndMetadata(par1Block, 0, this.blockAccess.getBlockMetadata(x, y, z)) : this.overrideBlockTexture;
            RenderChain.instance.drawChain(par1Block, this.blockAccess.getBlockMetadata(x, y, z), x, y, z, Photo, this.blockAccess);
            cir.setReturnValue(true);
        }
        if (RenderType == RenderTypes.campfireRenderType) {
            RenderCampfire.instance.DrawRenderCampfire((BlockCampfire) par1Block, this.blockAccess.getBlockMetadata(x, y, z), x, y, z, this.blockAccess);
            cir.setReturnValue(true);
        }
        if (RenderType == RenderTypes.stoneCutterRenderType) {
            BlockStonecutterRenderer.instance.renderWorldBlock(blockAccess,x,y,z,par1Block,(RenderBlocks) (Object)this);
            cir.setReturnValue(true);
        }
        if (RenderType == RenderTypes.normalcampfireRenderType) {
            RenderNormalCampfire.instance.DrawRenderCampfire(par1Block, this.blockAccess.getBlockMetadata(x, y, z), x, y, z, this.blockAccess);
            cir.setReturnValue(true);
        }
        if(RenderType == RenderTypes.silmeRenderType)
        {
            BlockSlimeBlockRender.instance.renderWorldBlock(blockAccess,x,y,z,par1Block,(RenderBlocks) (Object)this);
            cir.setReturnValue(true);
        }
        if(RenderType==RenderTypes.grindStoneRenderType)
        {
            RenderGrindStone.instance.drawGrindStone(par1Block,this.blockAccess.getBlockMetadata(x,y,z),x, y, z, this.blockAccess, false);
            cir.setReturnValue(true);
        }

    }
}


