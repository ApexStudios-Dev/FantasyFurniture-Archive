package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.core.SimpleFourWayBlockEntityBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.WidowBloomBlockEntity;
import xyz.apex.forge.fantasyfurniture.init.Decorations;

public final class WidowBloomBlock extends SimpleFourWayBlockEntityBlock<WidowBloomBlockEntity>
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(6D, 0D, 6D, 10D, 3D, 10D),
			box(5.5D, 3D, 5.5D, 10.5D, 6D, 10.5D),
			box(5D, 6D, 5D, 11D, 8D, 11D),
			box(4.5D, 8D, 4.5D, 11.5D, 10D, 11.5D),
			box(5D, 10D, 5D, 11D, 11D, 11D)
	);

	public WidowBloomBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected TileEntityType<WidowBloomBlockEntity> getBlockEntityType()
	{
		return Decorations.VENTHYR_WIDOW_BLOOM_BLOCK_ENTITY.asBlockEntityType();
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext  ctx)
	{
		return SHAPE;
	}
}
