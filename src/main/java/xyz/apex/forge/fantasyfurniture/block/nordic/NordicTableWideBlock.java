package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import xyz.apex.forge.fantasyfurniture.block.BaseTableWideBlock;
import xyz.apex.java.utility.Lazy;

public final class NordicTableWideBlock extends BaseTableWideBlock
{
	public static final Lazy<VoxelShape> SHAPE_MAIN_NORTH = Lazy.of(NordicTableWideBlock::createMainNorthShape);
	public static final Lazy<VoxelShape> SHAPE_MAIN_EAST = Lazy.of(NordicTableWideBlock::createMainEastShape);
	public static final Lazy<VoxelShape> SHAPE_MAIN_SOUTH = Lazy.of(NordicTableWideBlock::createMainSouthShape);
	public static final Lazy<VoxelShape> SHAPE_MAIN_WEST = Lazy.of(NordicTableWideBlock::createMainWestShape);
	public static final Lazy<VoxelShape> SHAPE_OTHER_NORTH = Lazy.of(NordicTableWideBlock::createOtherNorthShape);
	public static final Lazy<VoxelShape> SHAPE_OTHER_EAST = Lazy.of(NordicTableWideBlock::createOtherEastShape);
	public static final Lazy<VoxelShape> SHAPE_OTHER_SOUTH = Lazy.of(NordicTableWideBlock::createOtherSouthShape);
	public static final Lazy<VoxelShape> SHAPE_OTHER_WEST = Lazy.of(NordicTableWideBlock::createOtherWestShape);

	public NordicTableWideBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	private static VoxelShape createMainNorthShape()
	{
		return VoxelShapes.empty();
	}

	private static VoxelShape createMainEastShape()
	{
		return VoxelShapes.empty();
	}

	private static VoxelShape createMainSouthShape()
	{
		return VoxelShapes.empty();
	}

	private static VoxelShape createMainWestShape()
	{
		return VoxelShapes.empty();
	}

	private static VoxelShape createOtherNorthShape()
	{
		return VoxelShapes.empty();
	}

	private static VoxelShape createOtherEastShape()
	{
		return VoxelShapes.empty();
	}

	private static VoxelShape createOtherSouthShape()
	{
		return VoxelShapes.empty();
	}

	private static VoxelShape createOtherWestShape()
	{
		return VoxelShapes.empty();
	}
}
