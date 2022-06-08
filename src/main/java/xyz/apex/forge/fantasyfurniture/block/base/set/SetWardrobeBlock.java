package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.core.SimpleFourWayMultiBlockContainerBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.SetWardrobeBlockEntity;
import xyz.apex.forge.fantasyfurniture.container.SetWardrobeContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public class SetWardrobeBlock extends SimpleFourWayMultiBlockContainerBlock<SetWardrobeBlockEntity, SetWardrobeContainer>
{
	public SetWardrobeBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	protected BlockEntityType<SetWardrobeBlockEntity> getBlockEntityType()
	{
		return FFElements.WARDROBE_BLOCK_ENTITY.asBlockEntityType();
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.BLOCK;
	}
}
