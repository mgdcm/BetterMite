package com.github.FlyBird.BetterMite.block;


import net.minecraft.*;

public class BlockSlimePistonHead extends BlockPistonExtension {

    public BlockSlimePistonHead(int blockID) {
        super(blockID);
    }


    @Override
    public void onBlockAboutToBeBroken(BlockBreakInfo info) {
        int x = info.x;
        int y = info.y;
        int z = info.z;
        int metadata=info.world.getBlockMetadata(x,y,z);
        if (info.isResponsiblePlayerInCreativeMode()) {
            int direction = getDirectionMeta(metadata);
            Block baseBlock = info.world.getBlock(x - Facing.offsetsXForSide[direction], y - Facing.offsetsYForSide[direction], z - Facing.offsetsZForSide[direction]);
            if(baseBlock == Blocks.slimePiston || baseBlock == Blocks.stickySlimePiston){
                info.world.setBlockToAir(x - Facing.offsetsXForSide[direction], y - Facing.offsetsYForSide[direction], z - Facing.offsetsZForSide[direction]);
            }
        }
        super.onBlockAboutToBeBroken(info);
    }

 /*   @Override
    public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player){
        if(player.capabilities.isCreativeMode){
            int direction = getDirectionMeta(metadata);
            Block baseBlock = world.getBlock(x - Facing.offsetsXForSide[direction], y - Facing.offsetsYForSide[direction], z - Facing.offsetsZForSide[direction]);
            if(baseBlock == Blocks.slimePiston || baseBlock == Blocks.stickySlimePiston){
                world.setBlockToAir(x - Facing.offsetsXForSide[direction], y - Facing.offsetsYForSide[direction], z - Facing.offsetsZForSide[direction]);
            }
        }
        super.onBlockHarvested(world, x, y, z, metadata, player);
    }*/
	
	@Override
    public void breakBlock(World world, int x, int y, int z, int blockID, int metadata){
        super.breakBlock(world, x, y, z, blockID, metadata);
        int i1 = Facing.oppositeSide[getDirectionMeta(metadata)];
        x += Facing.offsetsXForSide[i1];
        y += Facing.offsetsYForSide[i1];
        z += Facing.offsetsZForSide[i1];
        Block baseBlock = world.getBlock(x, y, z);
        if(baseBlock == Blocks.slimePiston || baseBlock == Blocks.stickySlimePiston){
            metadata = world.getBlockMetadata(x, y, z);
            if(BlockSlimePistonBase.isExtended(metadata)){
                //baseBlock.dropBlockAsItem(world, x, y, z, metadata, 0);
                baseBlock.dropBlockAsEntityItem(new BlockBreakInfo(world, x, y, z).setNeighborChanged(blockID));
                world.setBlockToAir(x, y, z);
            }
        }
    }

    @Override
    public boolean onNeighborBlockChange(World world, int x, int y, int z, int blockID){
        int direction = getDirectionMeta(world.getBlockMetadata(x, y, z));
        Block baseBlock = world.getBlock(x - Facing.offsetsXForSide[direction], y - Facing.offsetsYForSide[direction], z - Facing.offsetsZForSide[direction]);
        if (baseBlock != Blocks.slimePiston && baseBlock != Blocks.stickySlimePiston){
            world.setBlockToAir(x, y, z);
        }else{
            baseBlock.onNeighborBlockChange(world, x - Facing.offsetsXForSide[direction], y - Facing.offsetsYForSide[direction], z - Facing.offsetsZForSide[direction], blockID);
        }
        return super.onNeighborBlockChange(world,  x, y, z,  blockID);
    }


    @Override
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        int metadata = info.world.getBlockMetadata(info.x, info.y, info.z);
        return (metadata & 8) != 0 ? this.dropBlockAsEntityItem(info, new ItemStack(Blocks.stickySlimePiston,1)):this.dropBlockAsEntityItem(info, new ItemStack(Blocks.slimePiston,1));
    }

	/*@Override
    public Item getItem(World world, int x, int y, int z){
        int metadata = world.getBlockMetadata(x, y, z);
        return (metadata & 8) != 0 ? Item.getItem(Blocks.stickySlimePiston) : Item.getItem(Blocks.slimePiston);
    }*/
}
