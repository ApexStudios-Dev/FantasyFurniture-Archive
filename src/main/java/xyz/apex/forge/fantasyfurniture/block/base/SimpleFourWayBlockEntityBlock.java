package xyz.apex.forge.fantasyfurniture.block.base;

import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class SimpleFourWayBlockEntityBlock<T extends TileEntity> extends SimpleFourWayBlock implements ITileEntityProvider
{
	public SimpleFourWayBlockEntityBlock(Properties properties)
	{
		super(properties);
	}

	protected abstract TileEntityType<T> getBlockEntityType();

	@Nullable
	protected T getBlockEntity(IBlockReader level, BlockPos pos)
	{
		return getBlockEntityType().getBlockEntity(level, pos);
	}

	@Override
	public boolean triggerEvent(BlockState blockState, World level, BlockPos pos, int event, int param)
	{
		T blockEntity = getBlockEntity(level, pos);
		return blockEntity != null && blockEntity.triggerEvent(event, param);
	}

	@Nullable
	public INamedContainerProvider getMenuProvider(BlockState blockState, World level, BlockPos pos)
	{
		T blockEntity = getBlockEntity(level, pos);
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
		return getBlockEntityType().create();
	}

	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;
	}
}
