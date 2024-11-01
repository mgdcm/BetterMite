package com.github.FlyBird.BetterMite.render.entity;


import com.github.FlyBird.BetterMite.entity.EntityNewBoat;
import com.github.FlyBird.BetterMite.model.ModelNewBoat;
import com.github.FlyBird.BetterMite.model.ModelRaft;
import net.minecraft.Entity;
import net.minecraft.MathHelper;
import net.minecraft.Render;
import net.minecraft.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class NewBoatRenderer extends Render {

	private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation("textures/entity/boat/oak.png"); //If the resource location is null for some reason

	public NewBoatRenderer() {
		super();
		this.shadowSize = 0.5F;
	}

	protected ModelNewBoat modelBoat = new ModelNewBoat();
	protected ModelRaft modelRaft = new ModelRaft();

	@Override
	public void doRender(Entity uncastedentity, double x, double y, double z, float entityYaw,
						 float partialTicks) {
		if (!(uncastedentity instanceof EntityNewBoat)) return;
		EntityNewBoat entity = (EntityNewBoat) uncastedentity;
		GL11.glPushMatrix();
		this.setupTranslation(x, y, z);
		this.setupRotation(entity, entityYaw, partialTicks);
		this.bindEntityTexture(entity);



		(entity.isRaft() ? modelRaft : modelBoat).render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		this.renderExtraBoatContents(entity, partialTicks);


		GL11.glPopMatrix();
		this.renderMultipass(entity, x, y, z, entityYaw, partialTicks);
	}

	protected void renderExtraBoatContents(EntityNewBoat boat, float partialTicks) {

	}

	public void setupRotation(EntityNewBoat p_188311_1_, float p_188311_2_, float p_188311_3_) {
		GL11.glRotatef(180F - p_188311_2_, 0.0F, 1.0F, 0.0F);
		float f = (float) p_188311_1_.getTimeSinceHit() - p_188311_3_;
		float f1 = p_188311_1_.getDamageTaken() - p_188311_3_;

		if (f1 < 0.0F) {
			f1 = 0.0F;
		}

		if (f > 0.0F) {
			GL11.glRotatef(MathHelper.sin(f) * f * f1 / 10.0F * (float) p_188311_1_.getForwardDirection(), 1.0F, 0.0F, 0.0F);
		}

		GL11.glScalef(-1.0F, -1.0F, 1.0F);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {
		ResourceLocation loc = DEFAULT_TEXTURE;
		if (entity instanceof EntityNewBoat && ((EntityNewBoat) entity).getResourceLocation() != null) {
			loc = ((EntityNewBoat) entity).getResourceLocation();
		}
		return loc;
	}

	public void setupTranslation(double p_188309_1_, double p_188309_3_, double p_188309_5_) {
		GL11.glTranslatef((float) p_188309_1_, (float) p_188309_3_ + 0.375F, (float) p_188309_5_);
	}

	public void renderMultipass(EntityNewBoat entityBoat, double x, double y, double z, float yaw, float partialTicks) {
		GL11.glPushMatrix();
		this.setupTranslation(x, y, z);
		this.setupRotation(entityBoat, yaw, partialTicks);
		this.bindEntityTexture(entityBoat);
		if (!entityBoat.isRaft()) {
			modelBoat.renderMultipass(entityBoat, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		}
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		this.renderExtraBoatContents(entityBoat, partialTicks);
		GL11.glPopMatrix();
	}
}
