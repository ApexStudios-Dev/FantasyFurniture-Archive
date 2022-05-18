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
import xyz.apex.forge.fantasyfurniture.block.entity.NordicWardrobeBlockEntity;
import xyz.apex.forge.fantasyfurniture.container.NordicWardrobeContainer;
import xyz.apex.forge.fantasyfurniture.init.Nordic;

public final class NordicWardrobeBlock extends SimpleFourWayMultiBlockContainerBlock<NordicWardrobeBlockEntity, NordicWardrobeContainer>
{
	public static final VoxelShape SHAPE_A = box(-15D, 0D, 0D, 15D, 32D, 16D);

	public static final VoxelShape SHAPE_B = box(1D, 0D, 0D, 31D, 32D, 16D);

	public static final VoxelShape SHAPE_C = box(-15D, -16D, 0D, 15D, 16D, 16D);

	public static final VoxelShape SHAPE_D = box(1D, -16D, 0D, 31D, 16D, 16D);

	public static final VoxelShaper SHAPER_A = VoxelShaper.forHorizontal(SHAPE_A, Direction.NORTH);
	public static final VoxelShaper SHAPER_B = VoxelShaper.forHorizontal(SHAPE_B, Direction.NORTH);
	public static final VoxelShaper SHAPER_C = VoxelShaper.forHorizontal(SHAPE_C, Direction.NORTH);
	public static final VoxelShaper SHAPER_D = VoxelShaper.forHorizontal(SHAPE_D, Direction.NORTH);

	public NordicWardrobeBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	protected TileEntityType<NordicWardrobeBlockEntity> getBlockEntityType()
	{
		return Nordic.WARDROBE_BLOCK_ENTITY.asBlockEntityType();
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
		int index = pattern.getIndex(blockState);
		VoxelShaper shaper;

		switch(index)
		{
			default:
			case 0:
				shaper = SHAPER_A;
				break;
			case 1:
				shaper = SHAPER_B;
				break;
			case 2:
				shaper = SHAPER_C;
				break;
			case 3:
				shaper = SHAPER_D;
				break;
		}

		return shaper.get(facing);
	}
}