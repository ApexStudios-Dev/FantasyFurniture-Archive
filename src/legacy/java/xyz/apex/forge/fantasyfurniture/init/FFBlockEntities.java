package xyz.apex.forge.fantasyfurniture.init;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import xyz.apex.forge.fantasyfurniture.block.entity.DrawerBlockEntity;
import xyz.apex.forge.utility.registrator.entry.BlockEntityEntry;
import xyz.apex.forge.utility.registrator.entry.BlockEntry;

public final class FFBlockEntities
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();

	public static final BlockEntityEntry<DrawerBlockEntity> NORDIC_DRAWER = fromBlock(FFBlocks.NORDIC_DRAWER);

	static void bootstrap()
	{
	}

	private static <BLOCK extends Block, BLOCK_ENTITY extends TileEntity> BlockEntityEntry<BLOCK_ENTITY> fromBlock(BlockEntry<BLOCK> block)
	{
		return BlockEntityEntry.cast(block.getSibling(TileEntityType.class));
	}
}