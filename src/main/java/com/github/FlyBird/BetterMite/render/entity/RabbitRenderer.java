package com.github.FlyBird.BetterMite.render.entity;


import com.github.FlyBird.BetterMite.entity.EntityRabbit;
import com.github.FlyBird.BetterMite.model.ModelRabbit;
import net.minecraft.Entity;
import net.minecraft.EntityLivingBase;
import net.minecraft.RenderLiving;
import net.minecraft.ResourceLocation;
import org.lwjgl.opengl.GL11;
import net.minecraft.EnumChatFormatting;

public class RabbitRenderer extends RenderLiving {

	private static final ResourceLocation BROWN = new ResourceLocation("textures/entity/rabbit/brown.png");
	private static final ResourceLocation WHITE = new ResourceLocation("textures/entity/rabbit/white.png");
	private static final ResourceLocation BLACK = new ResourceLocation("textures/entity/rabbit/black.png");
	private static final ResourceLocation GOLD = new ResourceLocation("textures/entity/rabbit/gold.png");
	private static final ResourceLocation SALT = new ResourceLocation("textures/entity/rabbit/salt.png");
	private static final ResourceLocation WHITE_SPLOTCHED = new ResourceLocation("textures/entity/rabbit/white_splotched.png");
	private static final ResourceLocation TOAST = new ResourceLocation("textures/entity/rabbit/toast.png");

	public RabbitRenderer() {
		super(new ModelRabbit(), 0.3F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float patialTickTime) {
		GL11.glScalef(0.65F, 0.65F, 0.65F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityRabbit rabbit = (EntityRabbit) entity;
		String s = EnumChatFormatting.func_110646_a(rabbit.getEntityName());//getEntityName  = getCommandSenderName ?

		if (s != null && s.equals("Toast"))
			return TOAST;
		else
			switch (rabbit.getRabbitType()) {
				case 0:
				default:
					return BROWN;
				case 1:
					return WHITE;
				case 2:
					return BLACK;
				case 3:
					return WHITE_SPLOTCHED;
				case 4:
					return GOLD;
				case 5:
					return SALT;
			}
	}

	@Override
	protected void setTextures() {

	}
}