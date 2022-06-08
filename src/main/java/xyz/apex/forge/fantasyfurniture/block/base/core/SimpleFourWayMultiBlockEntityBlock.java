package xyz.apex.forge.fantasyfurniture.block.base.core;

import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;

import javax.annotation.Nullable;

public abstract class SimpleFourWayMultiBlockEntityBlock<T extends BlockEntity> extends SimpleFourWayWaterLoggedMultiBlock implements EntityBlock
{
	public SimpleFourWayMultiBlockEntityBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	protected abstract BlockEntityType<T> getBlockEntityType();

	protected BlockPos getBlockEntityPos(BlockState blockState, BlockPos pos)
	{
		if(pattern.isOrigin(blockState))
			return pos;

		var index = pattern.getIndex(blockState);
		var localSpace = pattern.getLocalPositions().get(index);
		return pattern.getOriginFromWorldSpace(blockState, pos, localSpace);
	}

	@Nullable
	protected T getBlockEntity(BlockGetter level, BlockPos pos, BlockState currentBlockState)
	{
		var blockEntityPos = getBlockEntityPos(currentBlockState, pos);
		return getBlockEntityType().getBlockEntity(level, blockEntityPos);
	}

	@Override
	public boolean triggerEvent(BlockState blockState, Level level, BlockPos pos, int event, int param)
	{
		var blockEntity = getBlockEntity(level, pos, blockState);
		return blockEntity != null && blockEntity.triggerEvent(event, param);
	}

	@Nullable
	@Override
	public MenuProvider getMenuProvider(BlockState blockState, Level level, BlockPos pos)
	{
		var blockEntity = getBlockEntity(level, pos, blockState);
		return blockEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState)
	{
		return getBlockEntityType().create(pos, blockState);
	}
}
