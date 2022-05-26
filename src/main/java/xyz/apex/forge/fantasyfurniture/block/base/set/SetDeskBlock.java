package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.tileentity.TileEntityType;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.core.SimpleFourWayMultiBlockContainerBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.SetDeskBlockEntity;
import xyz.apex.forge.fantasyfurniture.container.SetDeskContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public class SetDeskBlock extends SimpleFourWayMultiBlockContainerBlock<SetDeskBlockEntity, SetDeskContainer>
{
	public SetDeskBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	protected TileEntityType<SetDeskBlockEntity> getBlockEntityType()
	{
		return FFElements.DESK_BLOCK_ENTITY.asBlockEntityType();
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
	}
}
