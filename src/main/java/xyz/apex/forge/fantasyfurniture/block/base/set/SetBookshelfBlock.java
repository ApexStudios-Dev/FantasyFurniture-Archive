package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.tileentity.TileEntityType;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.core.SimpleFourWayMultiBlockContainerBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.SetBookshelfBlockEntity;
import xyz.apex.forge.fantasyfurniture.container.SetBookshelfContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public class SetBookshelfBlock extends SimpleFourWayMultiBlockContainerBlock<SetBookshelfBlockEntity, SetBookshelfContainer>
{
	public SetBookshelfBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	protected TileEntityType<SetBookshelfBlockEntity> getBlockEntityType()
	{
		return FFElements.BOOKSHELF_BLOCK_ENTITY.asBlockEntityType();
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
	}
}
