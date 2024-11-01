package com.github.FlyBird.BetterMite.entityFX;


import com.github.FlyBird.BetterMite.block.BlockSoulTorch;
import net.minecraft.EntityFX;
import net.minecraft.Tessellator;
import net.minecraft.World;

public class EntitySoulFlameFX extends EntityFX {
    private float flameScale;

    public EntitySoulFlameFX(World world, double d, double e, double f, double g, double h, double i) {
        super(world, d, e, f, g, h, i);
        this.motionX = this.motionX * (double)0.01f + g;
        this.motionY = this.motionY * (double)0.01f + h;
        this.motionZ = this.motionZ * (double)0.01f + i;

        d += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05f;
        e += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05f;
        f += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05f;

        this.flameScale = this.particleScale;
        this.particleBlue = 1.0f;
        this.particleGreen = 1.0f;
        this.particleRed = 1.0f;
        this.particleMaxAge = (int)(8.0 / (Math.random() * 0.8 + 0.2)) + 4;
        this.noClip = true;
        this.setParticleIcon(BlockSoulTorch.soulFlame);
    }

    @Override
    public int getFXLayer() {
        return 1;
    }

    @Override
    public void renderParticle(Tessellator tessellator, float f, float g, float h, float i, float j, float k) {
        float f2 = ((float)this.particleAge + f) / (float)this.particleMaxAge;
        this.particleScale = this.flameScale * (1.0f - f2 * f2 * 0.5f);
        super.renderParticle(tessellator, f, g, h, i, j, k);
    }

    @Override
    public int getBrightnessForRender(float f) {
        float f2 = ((float)this.particleAge + f) / (float)this.particleMaxAge;
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        int n = super.getBrightnessForRender(f);
        int n2 = n & 0xFF;
        int n3 = n >> 16 & 0xFF;
        if ((n2 += (int)(f2 * 15.0f * 16.0f)) > 240) {
            n2 = 240;
        }
        return n2 | n3 << 16;
    }

    @Override
    public float getBrightness(float f) {
        float f2 = ((float)this.particleAge + f) / (float)this.particleMaxAge;
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        float f3 = super.getBrightness(f);
        return f3 * f2 + (1.0f - f2);
    }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
        }
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.96f;
        this.motionY *= 0.96f;
        this.motionZ *= 0.96f;
        if (this.onGround) {
            this.motionX *= 0.7f;
            this.motionZ *= 0.7f;
        }
    }
}