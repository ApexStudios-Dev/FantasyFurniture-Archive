package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

public class SetCarpetBlock extends BaseBlock implements IFurnitureSetBlock
{
	public static final VoxelShape SHAPE = box(0D, 0D, 0D, 16D, 1D, 16D);

	protected final ModBlocks furnitureSet;

	public SetCarpetBlock(ModBlocks furnitureSet, Properties properties)
	{
		super(properties);

		this.furnitureSet = furnitureSet;
	}

	@Override
	public final ModBlocks getFurnitureSet()
	{
		return furnitureSet;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		return SHAPE;
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction facing, BlockState facingBlockState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos)
	{
		return !blockState.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, facing, facingBlockState, level, currentPos, facingPos);
	}

	@Override
	public boolean canSurvive(BlockState blockState, LevelReader level, BlockPos pos)
	{
		return !level.isEmptyBlock(pos.below());
	}
}
