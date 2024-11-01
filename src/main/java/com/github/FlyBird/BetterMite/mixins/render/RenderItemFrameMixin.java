package com.github.FlyBird.BetterMite.mixins.render;

import net.minecraft.*;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(RenderItemFrame.class)
public abstract class RenderItemFrameMixin extends Render {
    @Shadow @Final private static ResourceLocation mapBackgroundTextures;

    /**
     * @author
     * @reason
     */
    @Overwrite
    private void func_82402_b(EntityItemFrame par1EntityItemFrame) {
        ItemStack var2 = par1EntityItemFrame.getDisplayedItem();
        if (var2 != null) {
            EntityItem var3 = new EntityItem(par1EntityItemFrame.worldObj, 0.0, 0.0, 0.0, var2);
            var3.getEntityItem().stackSize = 1;
            var3.hoverStart = 0.0F;
            GL11.glPushMatrix();
            GL11.glTranslatef(-0.453125F * (float)Direction.offsetX[par1EntityItemFrame.hangingDirection], -0.18F, -0.453125F * (float)Direction.offsetZ[par1EntityItemFrame.hangingDirection]);
            GL11.glRotatef(180.0F + par1EntityItemFrame.rotationYaw, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef((float)(-90 * par1EntityItemFrame.getRotation()), 0.0F, 0.0F, 1.0F);
            switch (par1EntityItemFrame.getRotation()) {
                case 1:
                    GL11.glTranslatef(-0.16F, -0.16F, 0.0F);
                    break;
                case 2:
                    GL11.glTranslatef(0.0F, -0.32F, 0.0F);
                    break;
                case 3:
                    GL11.glTranslatef(0.16F, -0.16F, 0.0F);
            }

            if (var3.getEntityItem().getItem() == Item.map) {
                this.renderManager.renderEngine.bindTexture(mapBackgroundTextures);
                Tessellator var4 = Tessellator.instance;
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScalef(0.00390625F*2, 0.00390625F*2, 0.00390625F*2);
                GL11.glTranslatef(-64.0F, -84.0F, -2.0F);
                GL11.glNormal3f(0.0F, 0.0F, -1.0F);
                var4.startDrawingQuads();
                byte var5 = 7;
                var4.addVertexWithUV((double)(-var5), (double)(128 + var5), 0.0, 0.0, 1.0);
                var4.addVertexWithUV((double)(128 + var5), (double)(128 + var5), 0.0, 1.0, 1.0);
                var4.addVertexWithUV((double)(128 + var5), (double)(-var5), 0.0, 1.0, 0.0);
                var4.addVertexWithUV((double)(-var5), (double)(-var5), 0.0, 0.0, 0.0);
                var4.draw();
                MapData var6 = Item.map.getMapData(var3.getEntityItem(), par1EntityItemFrame.worldObj);
                GL11.glTranslatef(0.0F, 0.0F, -1.0F);
                if (var6 != null) {
                    this.renderManager.itemRenderer.mapItemRenderer.renderMap((EntityPlayer)null, this.renderManager.renderEngine, var6);
                }
            } else {
                if (var3.getEntityItem().getItem() == Item.compass) {
                    TextureManager var11 = Minecraft.getMinecraft().getTextureManager();
                    var11.bindTexture(TextureMap.locationItemsTexture);
                    TextureAtlasSprite var13 = ((TextureMap)var11.getTexture(TextureMap.locationItemsTexture)).getAtlasSprite(Item.compass.getIconIndex(var3.getEntityItem()).getIconName());
                    if (var13 instanceof TextureCompass var14) {
                        double var7 = var14.currentAngle;
                        double var9 = var14.angleDelta;
                        var14.currentAngle = 0.0;
                        var14.angleDelta = 0.0;
                        var14.updateCompass(par1EntityItemFrame.worldObj, par1EntityItemFrame.posX, par1EntityItemFrame.posZ, (double)MathHelper.wrapAngleTo180_float((float)(180 + par1EntityItemFrame.hangingDirection * 90)), false, true);
                        var14.currentAngle = var7;
                        var14.angleDelta = var9;
                    }
                }

                RenderItem.renderInFrame = true;
                RenderManager.instance.renderEntityWithPosYaw(var3, 0.0, 0.0, 0.0, 0.0F, 0.0F);
                RenderItem.renderInFrame = false;
                if (var3.getEntityItem().getItem() == Item.compass) {
                    TextureAtlasSprite var12 = ((TextureMap)Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationItemsTexture)).getAtlasSprite(Item.compass.getIconIndex(var3.getEntityItem()).getIconName());
                    if (var12.getFrameCount() > 0) {
                        var12.updateAnimation();
                    }
                }
            }

            GL11.glPopMatrix();
        }

    }
//    @ModifyArg(method = "func_82402_b",at= @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glScalef(FFF)V"),index = 0)
//    private float RenderMapSizeX(float x){
//        return 2.0f*x;
//    }
//    @ModifyArg(method = "func_82402_b",at= @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glScalef(FFF)V"),index = 1)
//    private float RenderMapSizeY(float y){
//        return 2.0f*y;
//    }
//    @ModifyArg(method = "func_82402_b",at= @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glScalef(FFF)V"),index = 2)
//    private float RenderMapSizeZ(float z){
//        return 2.0f*z;
//    }
//    @ModifyArg(method = "func_82402_b",at= @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V"),index = 1)
//    private float RenderMapStartX(float x){
//        return 0;
//    }
}
