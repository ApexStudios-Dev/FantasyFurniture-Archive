package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

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
	protected BlockEntityType<SetChestBlockEntity> getBlockEntityType()
	{
		return FFElements.CHEST_BLOCK_ENTITY.asBlockEntityType();
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.BLOCK;
	}
}
