package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.tileentity.TileEntityType;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.core.SimpleFourWayMultiBlockContainerBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.SetChestBlockEntity;
import xyz.apex.forge.fantasyfurniture.container.SetChestContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public class SetChestBlock extends SimpleFourWayMultiBlockContainerBlock<SetChestBlockEntity, SetChestContainer>
{
	public SetChestBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	protected TileEntityType<SetChestBlockEntity> getBlockEntityType()
	{
		return FFElements.CHEST_BLOCK_ENTITY.asBlockEntityType();
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
	}
}
