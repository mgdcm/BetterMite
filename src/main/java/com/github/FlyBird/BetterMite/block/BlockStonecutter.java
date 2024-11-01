package com.github.FlyBird.BetterMite.block;


import com.github.FlyBird.BetterMite.render.RenderTypes;
import net.minecraft.*;

public class BlockStonecutter extends Block {

	private Icon sideIcon;
	private Icon bottomIcon;
	public Icon sawIcon;

	protected BlockStonecutter(int par1) {
		super(par1, Material.stone, new BlockConstants().setNotAlwaysLegal());
		this.setBlockBoundsForAllThreads(0.0, 0.0, 0.0, 1.0, 0.5625, 1.0);
		//this.setHarvestLevel("pickaxe", 0);
		this.setStepSound(soundStoneFootstep);
		this.setLightOpacity(0);
		this.setHardness(3.5F);
		this.setResistance(3.5F);
		//useNeighborBrightness = new boolean[]{true};
		//this.setBlockName(Utils.getUnlocalisedName("stonecutter"));
		this.setTextureName("stonecutter");
		this.setUnlocalizedName("stonecutter");
		setCreativeTab(CreativeTabs.tabDecorations);
	}

	public Icon getIcon(int side, int metadata) {
		return side == 1 ? this.blockIcon : side == 0 ? bottomIcon : this.sideIcon;
	}

	public void registerIcons(IconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(this.getTextureName() + "_top");
		this.sideIcon = iconRegister.registerIcon(this.getTextureName() + "_side");
		this.bottomIcon = iconRegister.registerIcon(this.getTextureName() + "_bottom");
		this.sawIcon = iconRegister.registerIcon(this.getTextureName() + "_saw");
	}

	@Override
	public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
		int diriction = MathHelper.floor_double((double) (entity.rotationYaw / 90.0F) + 0.5D) & 3;
		switch (diriction) {
			case 1:
				diriction = 3;
				break;
			case 2:
				diriction = 1;
				break;
			case 3:
				diriction = 2;
				break;
		}
		return diriction;
	}


/*	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
		int diriction = MathHelper.floor_double((double) (entityLivingBase.rotationYaw / 90.0F) + 0.5D) & 3;
		switch (diriction) {
			case 1:
				diriction = 3;
				break;
			case 2:
				diriction = 1;
				break;
			case 3:
				diriction = 2;
				break;
		}
		world.setBlockMetadataWithNotify(x, y, z, diriction, 2);
	}*/

	@Override
	public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
		return false;
	}

	/*@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}*/

	public int getRenderType() {
		return RenderTypes.stoneCutterRenderType;
	}

}
