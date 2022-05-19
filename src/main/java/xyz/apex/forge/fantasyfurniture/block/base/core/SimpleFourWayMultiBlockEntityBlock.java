package xyz.apex.forge.fantasyfurniture.block.base.core;

import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;

import javax.annotation.Nullable;

public abstract class SimpleFourWayMultiBlockEntityBlock<T extends TileEntity> extends SimpleFourWayWaterLoggedMultiBlock implements ITileEntityProvider
{
	public SimpleFourWayMultiBlockEntityBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	protected abstract TileEntityType<T> getBlockEntityType();

	protected BlockPos getBlockEntityPos(BlockState blockState, BlockPos pos)
	{
		if(pattern.isOrigin(blockState))
			return pos;

		int index = pattern.getIndex(blockState);
		BlockPos localSpace = pattern.getLocalPositions().get(index);
		return pattern.getOriginFromWorldSpace(blockState, pos, localSpace);
	}

	@Nullable
	protected T getBlockEntity(IBlockReader level, BlockPos pos, BlockState currentBlockState)
	{
		BlockPos blockEntityPos = getBlockEntityPos(currentBlockState, pos);
		return getBlockEntityType().getBlockEntity(level, blockEntityPos);
	}

	@Override
	public boolean triggerEvent(BlockState blockState, World level, BlockPos pos, int event, int param)
	{
		T blockEntity = getBlockEntity(level, pos, blockState);
		return blockEntity != null && blockEntity.triggerEvent(event, param);
	}

	@Nullable
	public INamedContainerProvider getMenuProvider(BlockState blockState, World level, BlockPos pos)
	{
		T blockEntity = getBlockEntity(level, pos, blockState);
		return blockEntity instanceof INamedContainerProvider ? (INamedContainerProvider) blockEntity : null;
	}

	@Nullable
	@Override
	public TileEntity newBlockEntity(IBlockReader level)
	{
		return getBlockEntityType().create();
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		if(pattern.isOrigin(state))
			return getBlockEntityType().create();

		return null;
	}

	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return pattern.isOrigin(state);
	}
}
