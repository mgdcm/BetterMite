package com.github.FlyBird.BetterMite.block;


import com.github.FlyBird.BetterMite.api.BetterMiteEntity;
import com.github.FlyBird.BetterMite.render.RenderTypes;
import net.minecraft.*;

import java.util.Map;
import java.util.WeakHashMap;

import static com.github.FlyBird.BetterMite.block.Blocks.stepSoundSlime;

public class SlimeBlock extends Block  {

	private static final Map<Entity, Double> SLIME_BOUNCE_CACHE = new WeakHashMap<>();
	private long lastBounceTick;

	public SlimeBlock(int blockID) {
		super(blockID, Material.clay, new BlockConstants().setNeverHidesAdjacentFaces());
		setHardness(0.0F);
		setTextureName("slime");
		setStepSound(stepSoundSlime);
		//setBlockName(Utils.getUnlocalisedName("slime"));
		setUnlocalizedName("slime");
		setCreativeTab(CreativeTabs.tabRedstone);
	}

	@Override
	public Object getCollisionBounds(World world, int x, int y, int z, Entity entity) {
		float f = 0.125F;
		return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1 - f, z + 1);
	}

//	@Override
//	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
//		float f = 0.125F;
//		return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1 - f, z + 1);
//	}

	/*@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		int blockid = par1IBlockAccess.getBlockId(par2, par3, par4);
		return blockid != this.blockID && super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
	}
	*/
	@Override
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance) {
		if (!entity.isSneaking()) {
			entity.fallDistance = 0;
			if (entity.motionY < 0.1) {
				SLIME_BOUNCE_CACHE.put(entity, -entity.motionY);
				lastBounceTick = world.getTotalWorldTime();
			}
		}
		super.onFallenUpon(world, x, y, z, entity, fallDistance);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {

		if (lastBounceTick == world.getTotalWorldTime()) {
			Double bounce = SLIME_BOUNCE_CACHE.remove(entity);
			if (bounce != null) {
				entity.motionY = bounce;
			}
		} else {
			SLIME_BOUNCE_CACHE.clear();
		}
		double d = 0.4 + Math.abs(entity.motionY) * 0.2;
		entity.motionX *= d;
		entity.motionZ *= d;
		super.onEntityCollidedWithBlock(world, x, y, z, entity);
	}

	@Override
	public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
		return false;
	}

		//	isAlwaysOpaqueStandardFormCube   isOpaqueCube
	@Override
	public boolean isAlwaysOpaqueStandardFormCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override   //如果你的图片开通了alpha 混合   如果不设置1 那么会报错    如果你没开同alpha混合  不设置0 也会报错
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	public int getRenderType() {
		return RenderTypes.silmeRenderType;
	}

}