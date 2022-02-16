package xyz.apex.forge.fantasyfurniture.block;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.fantasyfurniture.init.FFBlockEntities;

public final class NordicBedBlock extends BedBlock
{
	private static final VoxelShape NORTH_SHAPE = makeNorthShape();
	private static final VoxelShape SOUTH_SHAPE = makeSouthShape();
	private static final VoxelShape WEST_SHAPE = makeWestShape();
	private static final VoxelShape EAST_SHAPE = makeEastShape();

	public NordicBedBlock(Properties properties)
	{
		super(DyeColor.WHITE, properties);
	}

	@Override
	public TileEntity newBlockEntity(IBlockReader level)
	{
		return FFBlockEntities.NORDIC_BED.createBlockEntity();
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction direction = getConnectedDirection(blockState).getOpposite();

		VoxelShape backBoardN = box(0D, 0D, 0D, 16D, 14D, 2D);
		VoxelShape backBoardS = box(0D, 0D, 16D, 16D, 14D, 14D);

		VoxelShape backBoardE = box(16D, 0D, 0D, 14D, 14D, 16D);
		VoxelShape backBoardW = box(0D, 0D, 0D, 2D, 14D, 16D);

		VoxelShape base = box(0D, 3D, 0D, 16D, 8D, 16D);

		VoxelShape legNW = box(0D, 0D, 0D, 2D, 3D, 2D);
		VoxelShape legSW = box(0D, 0D, 14D, 2D, 3D, 16D);
		VoxelShape legNE = box(14D, 0D, 0D, 16D, 3D, 2D);
		VoxelShape legSE = box(14D, 0D, 14D, 16D, 3D, 16D);

		switch(direction)
		{
			case NORTH: return VoxelShapes.or(base, backBoardN, legNW, legNE);
			case EAST: return VoxelShapes.or(base, backBoardE, legNE, legSE);
			case SOUTH: return VoxelShapes.or(base, backBoardS, legSW, legSE);
			case WEST: return VoxelShapes.or(base, backBoardW, legNW, legSW);
		}

		return VoxelShapes.empty();
	}

	private static VoxelShape makeNorthShape()
	{
		VoxelShape shape = VoxelShapes.empty();
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0.125, 0, 1, 0.5, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.125, 0, 0.125, 0.5, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.125, 1.875, 0.125, 0.5, 2), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0.125, 1.875, 1, 0.5, 2), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.8125, 0.5, 0, 0.9375, 0.75, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.0625, 0.5, 0, 0.1875, 0.75, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.0625, 0.5, 1.875, 0.1875, 0.75, 2), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.8125, 0.5, 1.875, 0.9375, 0.75, 2), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.75, 0, 1, 0.875, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.75, 1.875, 1, 0.875, 2), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.1875, 0, 0.875, 0.3125, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.1875, 1.875, 0.875, 0.3125, 2), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.3125, 0.0625, 0.875, 0.5, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.3125, 1.875, 0.875, 0.5, 1.9375), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0.1875, 0.125, 1, 0.3125, 1.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.1875, 0.125, 0.125, 0.3125, 1.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.25, 0.125, 0.875, 0.25, 1.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.1875, 0.5, 0.0625, 0.8125, 0.75, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.1875, 0.5, 1.875, 0.8125, 0.75, 1.9375), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.4375, 1.5, 0.875, 0.4375, 1.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.0625, 0.5, 0.125, 0.9375, 0.5, 1.5), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.1875, 0.359375, 1.75, 0.8125, 0.734375, 1.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.9375, 0.3125, 0.125, 0.9375, 0.5, 1.5), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.0625, 0.3125, 0.125, 0.0625, 0.5, 1.5), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.0625, 0.3125, 1.5, 0.9375, 0.5, 1.5), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0.3125, 1.5, 0.875, 0.4375, 1.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.3125, 1.5, 0.125, 0.4375, 1.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0, 0, 1, 0.125, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0, 0, 0.125, 0.125, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0, 1.875, 0.125, 0.125, 2), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0, 1.875, 1, 0.125, 2), IBooleanFunction.OR);
		return shape;
	}

	private static VoxelShape makeSouthShape()
	{
		VoxelShape shape = VoxelShapes.empty();
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.125, 0.875, 0.125, 0.5, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0.125, 0.875, 1, 0.5, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0.125, -1, 1, 0.5, -0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.125, -1, 0.125, 0.5, -0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.0625, 0.5, 0.875, 0.1875, 0.75, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.8125, 0.5, 0.875, 0.9375, 0.75, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.8125, 0.5, -1, 0.9375, 0.75, -0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.0625, 0.5, -1, 0.1875, 0.75, -0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.75, 0.875, 1, 0.875, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.75, -1, 1, 0.875, -0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.1875, 0.875, 0.875, 0.3125, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.1875, -1, 0.875, 0.3125, -0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.3125, 0.875, 0.875, 0.5, 0.9375), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.3125, -0.9375, 0.875, 0.5, -0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.1875, -0.875, 0.125, 0.3125, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0.1875, -0.875, 1, 0.3125, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.25, -0.875, 0.875, 0.25, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.1875, 0.5, 0.875, 0.8125, 0.75, 0.9375), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.1875, 0.5, -0.9375, 0.8125, 0.75, -0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.4375, -0.875, 0.875, 0.4375, -0.5), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.0625, 0.5, -0.5, 0.9375, 0.5, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.1875, 0.359375, -0.875, 0.8125, 0.734375, -0.75), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.0625, 0.3125, -0.5, 0.0625, 0.5, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.9375, 0.3125, -0.5, 0.9375, 0.5, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.0625, 0.3125, -0.5, 0.9375, 0.5, -0.5), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.3125, -0.875, 0.125, 0.4375, -0.5), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0.3125, -0.875, 0.875, 0.4375, -0.5), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0, 0.875, 0.125, 0.125, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0, 0.875, 1, 0.125, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0, -1, 1, 0.125, -0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0, -1, 0.125, 0.125, -0.875), IBooleanFunction.OR);
		return shape;
	}

	private static VoxelShape makeWestShape()
	{
		VoxelShape shape = VoxelShapes.empty();
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.125, 0, 0.125, 0.5, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.125, 0.875, 0.125, 0.5, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(1.875, 0.125, 0.875, 2, 0.5, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(1.875, 0.125, 0, 2, 0.5, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.5, 0.0625, 0.125, 0.75, 0.1875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.5, 0.8125, 0.125, 0.75, 0.9375), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(1.875, 0.5, 0.8125, 2, 0.75, 0.9375), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(1.875, 0.5, 0.0625, 2, 0.75, 0.1875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.75, 0, 0.125, 0.875, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(1.875, 0.75, 0, 2, 0.875, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.1875, 0.125, 0.125, 0.3125, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(1.875, 0.1875, 0.125, 2, 0.3125, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.0625, 0.3125, 0.125, 0.125, 0.5, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(1.875, 0.3125, 0.125, 1.9375, 0.5, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.1875, 0, 1.875, 0.3125, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.1875, 0.875, 1.875, 0.3125, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.25, 0.125, 1.875, 0.25, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.0625, 0.5, 0.1875, 0.125, 0.75, 0.8125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(1.875, 0.5, 0.1875, 1.9375, 0.75, 0.8125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(1.5, 0.4375, 0.125, 1.875, 0.4375, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.5, 0.0625, 1.5, 0.5, 0.9375), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(1.75, 0.359375, 0.1875, 1.875, 0.734375, 0.8125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.3125, 0.0625, 1.5, 0.5, 0.0625), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.3125, 0.9375, 1.5, 0.5, 0.9375), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(1.5, 0.3125, 0.0625, 1.5, 0.5, 0.9375), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(1.5, 0.3125, 0.125, 1.875, 0.4375, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(1.5, 0.3125, 0.875, 1.875, 0.4375, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0, 0, 0.125, 0.125, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0, 0.875, 0.125, 0.125, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(1.875, 0, 0.875, 2, 0.125, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(1.875, 0, 0, 2, 0.125, 0.125), IBooleanFunction.OR);
		return shape;
	}

	private static VoxelShape makeEastShape()
	{
		VoxelShape shape = VoxelShapes.empty();
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0.125, 0.875, 1, 0.5, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0.125, 0, 1, 0.5, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-1, 0.125, 0, -0.875, 0.5, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-1, 0.125, 0.875, -0.875, 0.5, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0.5, 0.8125, 1, 0.75, 0.9375), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0.5, 0.0625, 1, 0.75, 0.1875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-1, 0.5, 0.0625, -0.875, 0.75, 0.1875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-1, 0.5, 0.8125, -0.875, 0.75, 0.9375), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0.75, 0, 1, 0.875, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-1, 0.75, 0, -0.875, 0.875, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0.1875, 0.125, 1, 0.3125, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-1, 0.1875, 0.125, -0.875, 0.3125, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0.3125, 0.125, 0.9375, 0.5, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-0.9375, 0.3125, 0.125, -0.875, 0.5, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-0.875, 0.1875, 0.875, 0.875, 0.3125, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-0.875, 0.1875, 0, 0.875, 0.3125, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-0.875, 0.25, 0.125, 0.875, 0.25, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0.5, 0.1875, 0.9375, 0.75, 0.8125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-0.9375, 0.5, 0.1875, -0.875, 0.75, 0.8125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-0.875, 0.4375, 0.125, -0.5, 0.4375, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-0.5, 0.5, 0.0625, 0.875, 0.5, 0.9375), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-0.875, 0.359375, 0.1875, -0.75, 0.734375, 0.8125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-0.5, 0.3125, 0.9375, 0.875, 0.5, 0.9375), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-0.5, 0.3125, 0.0625, 0.875, 0.5, 0.0625), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-0.5, 0.3125, 0.0625, -0.5, 0.5, 0.9375), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-0.875, 0.3125, 0.875, -0.5, 0.4375, 0.875), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-0.875, 0.3125, 0.125, -0.5, 0.4375, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0, 0.875, 1, 0.125, 1), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(0.875, 0, 0, 1, 0.125, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-1, 0, 0, -0.875, 0.125, 0.125), IBooleanFunction.OR);
		shape = VoxelShapes.join(shape, VoxelShapes.box(-1, 0, 0.875, -0.875, 0.125, 1), IBooleanFunction.OR);
		return shape;
	}
}
