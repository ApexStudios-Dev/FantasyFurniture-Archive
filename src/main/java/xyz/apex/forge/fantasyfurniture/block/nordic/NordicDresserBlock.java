package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.SimpleFourWayMultiBlockContainerBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.NordicDresserBlockEntity;
import xyz.apex.forge.fantasyfurniture.container.NordicDresserContainer;
import xyz.apex.forge.fantasyfurniture.init.Nordic;

public final class NordicDresserBlock extends SimpleFourWayMultiBlockContainerBlock<NordicDresserBlockEntity, NordicDresserContainer>
{
	public static final VoxelShape SHAPE_A = VoxelShaper.or(
			box(-15D, 0D, 1D, 15D, 16D, 15D),
			box(-16D, 13D, 14D, 16D, 16D, 16D),
			box(-16D, 13D, 0D, 16D, 16D, 2D)
	);

	public static final VoxelShape SHAPE_B = VoxelShaper.or(
			box(1D, 0D, 1D, 31D, 16D, 15D),
			box(0D, 13D, 14D, 32D, 16D, 16D),
			box(0D, 13D, 0D, 32D, 16D, 2D)
	);

	public static final VoxelShaper SHAPER_A = VoxelShaper.forHorizontal(SHAPE_A, Direction.NORTH);
	public static final VoxelShaper SHAPER_B = VoxelShaper.forHorizontal(SHAPE_B, Direction.NORTH);

	public NordicDresserBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	protected TileEntityType<NordicDresserBlockEntity> getBlockEntityType()
	{
		return Nordic.DRESSER_BLOCK_ENTITY.asBlockEntityType();
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		VoxelShaper shaper = pattern.isOrigin(blockState) ? SHAPER_A : SHAPER_B;
		return shaper.get(facing);
	}
}
