package com.github.FlyBird.BetterMite.entityFX;



import com.github.FlyBird.BetterMite.misc.OpenGLHelperExtend;
import net.minecraft.*;
import org.lwjgl.opengl.GL11;

public class EntityBigSmokeFX extends EntityFX {
    public static final int TEXTURE_COUNT = 12;
    public static final ResourceLocation[] TEXTURES = new ResourceLocation[TEXTURE_COUNT];

    static {
        for (int i = 0; i < TEXTURE_COUNT; i++) {
            TEXTURES[i] = new ResourceLocation("textures/particle/big_smoke_" + i + ".png");
        }
    }

    protected final int texIndex;
    protected boolean alphaFading = false;
    protected float alphaFadePerTick = -0.015F;
    protected final boolean atmosphericCombustion;
    protected boolean localizedCombustion = true;

    public EntityBigSmokeFX(World world, int x, int y, int z, boolean signalFire) {
        super(world, x, y, z);

        this.setPosition(x + 0.5 + (rand.nextDouble() / 3.0 * (rand.nextBoolean() ? 1 : -1)), y + rand.nextDouble() + rand.nextDouble(), z + 0.5 + (rand.nextDouble() / 3.0 * (rand.nextBoolean() ? 1 : -1)));

        this.particleScale = 6.0F * (rand.nextFloat() * 0.5F + 0.5F);
        this.setSize(0.25F, 0.25F);
        this.particleGravity = 0.000003F;
        this.motionY = 0.075 + this.rand.nextFloat() / 500.0F;
        this.noClip = false;

        if (signalFire) {
            this.particleMaxAge = rand.nextInt(50) + 280;
            this.particleAlpha = 0.95F;
        } else {
            this.particleMaxAge = rand.nextInt(50) + 80;
            this.particleAlpha = 0.9F;
        }

        this.atmosphericCombustion = isAtmosphericCombustion(world);
        this.texIndex = rand.nextInt(TEXTURE_COUNT);
    }

        @Override
        public void renderParticle(Tessellator tess, float partialTicks, float rotX, float rotXZ, float rotZ, float rotYZ, float rotXY) {
            // 渲染粒子代码（与之前相同）
            rotX = ActiveRenderInfo.rotationX;
            rotXZ = ActiveRenderInfo.rotationXZ;
            rotZ = ActiveRenderInfo.rotationZ;
            rotYZ = ActiveRenderInfo.rotationYZ;
            rotXY = ActiveRenderInfo.rotationXY;

            EntityLivingBase view = Minecraft.getMinecraft().renderViewEntity;
            double interpX = view.lastTickPosX + (view.posX - view.lastTickPosX) * partialTicks;
            double interpY = view.lastTickPosY + (view.posY - view.lastTickPosY) * partialTicks;
            double interpZ = view.lastTickPosZ + (view.posZ - view.lastTickPosZ) * partialTicks;

            float partialPosX = (float) (prevPosX + (posX - prevPosX) * partialTicks - interpX);
            float partialPosY = (float) (prevPosY + (posY - prevPosY) * partialTicks - interpY);
            float partialPosZ = (float) (prevPosZ + (posZ - prevPosZ) * partialTicks - interpZ);

            float scale = 0.1F * particleScale;

            double minU = this.particleTextureIndexX * 0.25;
            double maxU = minU + 0.25;
            double minV = this.particleTextureIndexY * 0.125;
            double maxV = minV + 0.125;

            GL11.glEnable(GL11.GL_BLEND);
            OpenGLHelperExtend.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);

            Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURES[this.texIndex % TEXTURES.length]);

            tess.startDrawingQuads();

            tess.setColorRGBA_F(particleRed, particleGreen, particleBlue, particleAlpha);


            tess.setBrightness(15 << 20 | 14 << 4); // slightly less than MiscUtil.MAX_LIGHT_BRIGHTNESS so that smoke doesn't glow super bright with shaders

            tess.addVertexWithUV(partialPosX - rotX * scale - rotYZ * scale, partialPosY - rotXZ * scale, partialPosZ - rotZ * scale - rotXY * scale, 1, 1);
            tess.addVertexWithUV(partialPosX - rotX * scale + rotYZ * scale, partialPosY + rotXZ * scale, partialPosZ - rotZ * scale + rotXY * scale, 1, 0);
            tess.addVertexWithUV(partialPosX + rotX * scale + rotYZ * scale, partialPosY + rotXZ * scale, partialPosZ + rotZ * scale + rotXY * scale, 0, 0);
            tess.addVertexWithUV(partialPosX + rotX * scale - rotYZ * scale, partialPosY - rotXZ * scale, partialPosZ + rotZ * scale - rotXY * scale, 0, 1);

            tess.draw();

            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDepthMask(false);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
        }

        @Override
        public void onUpdate() {
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;

            if (!this.atmosphericCombustion && this.localizedCombustion && this.particleAge % 10 == 0 && !isLocalizedCombustion(this.worldObj,
                    MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))) {
                this.localizedCombustion = false;
                this.alphaFading = true;
                this.alphaFadePerTick = -0.09F;
            }

            if (this.particleAge++ >= this.particleMaxAge - 60) {
                this.alphaFading = true;
            }

            if (this.particleAge < this.particleMaxAge && this.particleAlpha > 0.0F) {
                float motionScale = this.localizedCombustion ? 5000.0F : 10.0F;
                this.motionX += this.rand.nextFloat() / motionScale * (this.rand.nextBoolean() ? 1 : -1);
                this.motionZ += this.rand.nextFloat() / motionScale * (this.rand.nextBoolean() ? 1 : -1);
                this.motionY -= this.particleGravity;

                this.moveEntity(this.motionX, this.motionY, this.motionZ);

                if (this.alphaFading) {
                    this.particleAlpha = MathHelper.clamp_float(this.particleAlpha + this.alphaFadePerTick, 0.0F, 1.0F);
                }
            } else {
                this.setDead();
            }
        }

        @Override
        public int getFXLayer() {
            return 3;
        }

        private boolean isAtmosphericCombustion(World world) {
            // 判断大气燃烧的逻辑实现
            return true; // 简单示例，假设总是燃烧
        }

        private boolean isLocalizedCombustion(World world, int x, int y, int z) {
            // 判断局部燃烧的逻辑实现
            return true; // 简单示例，假设总是燃烧
        }
}