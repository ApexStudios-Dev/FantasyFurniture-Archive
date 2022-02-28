package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.state.properties.BedPart;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.fantasyfurniture.block.BaseBedBlockDouble;
import xyz.apex.forge.fantasyfurniture.block.BlockHelper;

public final class NordicBedDoubleBlock extends BaseBedBlockDouble
{
	public static final VoxelShape NORTH_MAIN_HEAD = BlockHelper.makeShape(
			box(0D, 0D, 0D, 32D, 16D, 2D),
			box(0D, 0D, 30D, 32D, 16D, 32D),
			box(0D, 3D, 2D, 32D, 9D, 30D)
	);

	public static final VoxelShape NORTH_MAIN_FOOT = BlockHelper.makeShape(
			box(0D, 0D, -16D, 32D, 16D, -14D),
			box(0D, 0D, 14D, 32D, 16D, 16D),
			box(0D, 3D, -14D, 32D, 9D, 14D)
	);

	public static final VoxelShape NORTH_PARTNER_HEAD = BlockHelper.makeShape(
			box(-16D, 0D, 0D, 16D, 16D, 2D),
			box(-16D, 0D, 30D, 16D, 16D, 32D),
			box(-16D, 3D, 2D, 16D, 9D, 30D)
	);

	public static final VoxelShape NORTH_PARTNER_FOOT = BlockHelper.makeShape(
			box(-16D, 0D, -16D, 16D, 16D, -14D),
			box(-16D, 0D, 14D, 16D, 16D, 16D),
			box(-16D, 3D, -14D, 16D, 9D, 14D)
	);

	public static final VoxelShape EAST_MAIN_HEAD = BlockHelper.makeShape(
			box(-16D, 0D, 0D, -14D, 16D, 32D),
			box(14D, 0D, 0D, 16D, 16D, 32D),
			box(-14D, 3D, 0D, 14D, 9D, 32D)
	);

	public static final VoxelShape EAST_MAIN_FOOT = BlockHelper.makeShape(
			box(0D, 0D, 0D, 2D, 16D, 32D),
			box(30D, 0D, 0D, 32D, 16D, 32D),
			box(2D, 3D, 0D, 30D, 9D, 32D)
	);

	public static final VoxelShape EAST_PARTNER_HEAD = BlockHelper.makeShape(
			box(-16D, 0D, -16D, -14D, 16D, 16D),
			box(14D, 0D, -16D, 16D, 16D, 16D),
			box(-14D, 3D, -16D, 14D, 9D, 16D)
	);

	public static final VoxelShape EAST_PARTNER_FOOT = BlockHelper.makeShape(
			box(0D, 0D, -16D, 2D, 16D, 16D),
			box(30D, 0D, -16D, 32D, 16D, 16D),
			box(2D, 3D, -16D, 30D, 9D, 16D)
	);

	public NordicBedDoubleBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(stateDefinition.any().setValue(PART, BedPart.FOOT).setValue(OCCUPIED, false).setValue(BED_SIDE, BedSide.MAIN));
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		BedSide bedSide = blockState.getValue(BED_SIDE);
		BedPart bedPart = blockState.getValue(PART);
		Direction facing = blockState.getValue(FACING);

		if(facing == Direction.NORTH)
		{
			if(bedSide == BedSide.MAIN)
			{
				if(bedPart == BedPart.HEAD)
					return NORTH_MAIN_HEAD;
				else
					return NORTH_MAIN_FOOT;
			}
			else
			{
				if(bedPart == BedPart.HEAD)
					return NORTH_PARTNER_HEAD;
				else
					return NORTH_PARTNER_FOOT;
			}
		}
		else if(facing == Direction.SOUTH)
		{
			if(bedSide == BedSide.PARTNER)
			{
				if(bedPart == BedPart.FOOT)
					return NORTH_MAIN_HEAD;
				else
					return NORTH_MAIN_FOOT;
			}
			else
			{
				if(bedPart == BedPart.FOOT)
					return NORTH_PARTNER_HEAD;
				else
					return NORTH_PARTNER_FOOT;
			}
		}
		else if(facing == Direction.EAST)
		{
			if(bedSide == BedSide.MAIN)
			{
				if(bedPart == BedPart.HEAD)
					return EAST_MAIN_HEAD;
				else
					return EAST_MAIN_FOOT;
			}
			else
			{
				if(bedPart == BedPart.HEAD)
					return EAST_PARTNER_HEAD;
				else
					return EAST_PARTNER_FOOT;
			}
		}
		else
		{
			if(bedSide == BedSide.PARTNER)
			{
				if(bedPart == BedPart.FOOT)
					return EAST_MAIN_HEAD;
				else
					return EAST_MAIN_FOOT;
			}
			else
			{
				if(bedPart == BedPart.FOOT)
					return EAST_PARTNER_HEAD;
				else
					return EAST_PARTNER_FOOT;
			}
		}
	}
}
