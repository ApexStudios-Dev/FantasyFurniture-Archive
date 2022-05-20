package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.tileentity.TileEntityType;

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
	protected TileEntityType<SetWardrobeBlockEntity> getBlockEntityType()
	{
		return FFElements.WARDROBE_BLOCK_ENTITY.asBlockEntityType();
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.BLOCK;
	}
}
