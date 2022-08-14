package xyz.apex.forge.fantasyfurniture.init;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.BaseMultiBlock;
import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.block.WallLightBlock;
import xyz.apex.forge.fantasyfurniture.block.furniture.*;

import static net.minecraft.world.level.block.Block.box;

public abstract class HitBoxes
{
	public static final HitBoxes NORDIC = new Nordic();
	public static final HitBoxes DUNMER = new Dunmer();
	public static final HitBoxes VENTHYR = new Venthyr();
	public static final HitBoxes BONE = new Bone();
	public static final HitBoxes ROYAL = new Royal();

	private final HitBox.WithShaper bedDouble = new HitBox.WithShaper(this::bedDoubleShape, Direction.NORTH);
	private final HitBox.WithShaper bedSingle = new HitBox.WithShaper(this::bedSingleShape, Direction.NORTH);
	private final HitBox.WithShaper bench = new HitBox.WithShaper(this::benchShape, Direction.NORTH);
	private final HitBox.WithShaper bookshelf = new HitBox.WithShaper(this::bookshelfShape, Direction.NORTH);
	private final HitBox.WithShaper chair = new HitBox.WithShaper(this::chairShape, Direction.NORTH);
	private final HitBox chandelier = new HitBox(this::chandelierShape);
	private final HitBox.WithShaper chest = new HitBox.WithShaper(this::chestShape, Direction.NORTH);
	private final HitBox.WithShaper cushion = new HitBox.WithShaper(this::cushionShape, Direction.NORTH);
	private final HitBox.WithShaper deskLeft = new HitBox.WithShaper(this::deskLeftShape, Direction.NORTH);
	private final HitBox.WithShaper deskRight = new HitBox.WithShaper(this::deskRightShape, Direction.NORTH);
	private final HitBox.WithShaper drawer = new HitBox.WithShaper(this::drawerShape, Direction.NORTH);
	private final HitBox.WithShaper dresser = new HitBox.WithShaper(this::dresserShape, Direction.NORTH);
	private final HitBox.WithShaper lockbox = new HitBox.WithShaper(this::lockboxShape, Direction.NORTH);
	private final HitBox.WithShaper floorLight = new HitBox.WithShaper(this::floorLightShape, Direction.NORTH);
	private final HitBox.WithShaper paintingSmall = new HitBox.WithShaper(this::paintingSmallShape, Direction.NORTH);
	private final HitBox.WithShaper paintingWide = new HitBox.WithShaper(this::paintingWideShape, Direction.NORTH);
	private final HitBox.WithShaper shelfSingle = new HitBox.WithShaper(this::shelfSingleShape, Direction.NORTH);
	private final HitBox.WithShaper shelfCenter = new HitBox.WithShaper(this::shelfCenterShape, Direction.NORTH);
	private final HitBox.WithShaper shelfLeft = new HitBox.WithShaper(this::shelfLeftShape, Direction.NORTH);
	private final HitBox.WithShaper shelfRight = new HitBox.WithShaper(this::shelfRightShape, Direction.NORTH);
	private final HitBox.WithShaper sofaSingle = new HitBox.WithShaper(this::sofaSingleShape, Direction.NORTH);
	private final HitBox.WithShaper sofaCenter = new HitBox.WithShaper(this::sofaCenterShape, Direction.NORTH);
	private final HitBox.WithShaper sofaLeft = new HitBox.WithShaper(this::sofaLeftShape, Direction.NORTH);
	private final HitBox.WithShaper sofaRight = new HitBox.WithShaper(this::sofaRightShape, Direction.NORTH);
	private final HitBox.WithShaper sofaCorner = new HitBox.WithShaper(this::sofaCornerShape, Direction.NORTH);
	private final HitBox.WithShaper stool = new HitBox.WithShaper(this::stoolShape, Direction.NORTH);
	private final HitBox.WithShaper tableLarge = new HitBox.WithShaper(this::tableLargeShape, Direction.NORTH);
	private final HitBox.WithShaper tableSmall = new HitBox.WithShaper(this::tableSmallShape, Direction.NORTH);
	private final HitBox.WithShaper tableWide = new HitBox.WithShaper(this::tableWideShape, Direction.NORTH);
	private final HitBox.WithShaper wallLight = new HitBox.WithShaper(this::wallLightShape, Direction.NORTH);
	private final HitBox.WithShaper wardrobeTop = new HitBox.WithShaper(this::wardrobeTopShape, Direction.NORTH);
	private final HitBox.WithShaper wardrobeBottom = new HitBox.WithShaper(this::wardrobeBottomShape, Direction.NORTH);
	private final HitBox.WithShaper doorDouble = new HitBox.WithShaper(this::doorDoubleShape, Direction.NORTH);
	private final HitBox.WithShaper doorSingle = new HitBox.WithShaper(this::doorSingleShape, Direction.NORTH);
	private final HitBox.WithShaper counterSingle = new HitBox.WithShaper(this::counterSingleShape, Direction.NORTH);
	private final HitBox.WithShaper counterCorner = new HitBox.WithShaper(this::counterCornerShape, Direction.NORTH);
	private final HitBox.WithShaper oven = new HitBox.WithShaper(this::ovenShape, Direction.NORTH);

	public final VoxelShape bedDouble(BedDoubleBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = bedDouble.get(blockState);
		var index = block.getMultiBlockIndex(blockState);

		if(index == 1 || index == 3)
		{
			var other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		if(index == 2 || index == 3)
			shape = shape.move(facing.getStepX(), 0D, facing.getStepZ());

		return shape;
	}

	public final VoxelShape bedSingle(BedSingleBlock block, BlockState blockState)
	{
		var shape = bedSingle.get(blockState);

		if(!block.isMultiBlockOrigin(blockState))
		{
			var facing = BaseBlock.getFacing(blockState);
			shape = shape.move(facing.getStepX(), 0D, facing.getStepZ());
		}

		return shape;
	}

	public final VoxelShape bench(BenchBlock block, BlockState blockState)
	{
		var shape = bench.get(blockState);

		if(!block.isMultiBlockOrigin(blockState))
		{
			var other = BaseBlock.getFacing(blockState).getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}

	public final VoxelShape bookshelf(BookshelfBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = bookshelf.get(blockState);
		var index = block.getMultiBlockIndex(blockState);

		if(index == 1 || index == 3)
		{
			var other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		if(index == 2 || index == 3)
			shape = shape.move(0D, -1D, 0D);

		return shape;
	}

	public final VoxelShape chair(ChairBlock block, BlockState blockState)
	{
		var shape = chair.get(blockState);

		if(!block.isMultiBlockOrigin(blockState))
			shape = shape.move(0D, -1D, 0D);

		return shape;
	}

	public final VoxelShape chandelier(ChandelierBlock block, BlockState blockState)
	{
		return chandelier.get(blockState);
	}

	public final VoxelShape chest(ChestBlock block, BlockState blockState)
	{
		var shape = chest.get(blockState);

		if(!block.isMultiBlockOrigin(blockState))
		{
			var other = BaseBlock.getFacing(blockState).getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}

	public final VoxelShape cushion(CushionBlock block, BlockState blockState)
	{
		return cushion.get(blockState);
	}

	public final VoxelShape deskLeft(DeskBlock block, BlockState blockState)
	{
		var shape = deskLeft.get(blockState);

		if(!block.isMultiBlockOrigin(blockState))
		{
			var other = BaseBlock.getFacing(blockState).getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}

	public final VoxelShape deskRight(DeskBlock block, BlockState blockState)
	{
		var shape = deskRight.get(blockState);

		if(!block.isMultiBlockOrigin(blockState))
		{
			var other = BaseBlock.getFacing(blockState).getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}

	public final VoxelShape drawer(DrawerBlock block, BlockState blockState)
	{
		return drawer.get(blockState);
	}

	public final VoxelShape dresser(DresserBlock block, BlockState blockState)
	{
		var shape = dresser.get(blockState);

		if(!block.isMultiBlockOrigin(blockState))
		{
			var other = BaseBlock.getFacing(blockState).getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}

	public final VoxelShape lockbox(LockboxBlock block, BlockState blockState)
	{
		return lockbox.get(blockState);
	}

	public final VoxelShape floorLight(FloorLightBlock block, BlockState blockState)
	{
		var shape = floorLight.get(blockState);
		return block.isMultiBlockOrigin(blockState) ? shape : shape.move(0D, -1D, 0D);
	}

	public final VoxelShape paintingSmall(PaintingSmallBlock block, BlockState blockState)
	{
		return paintingSmall.get(blockState);
	}

	public final VoxelShape paintingWide(PaintingWideBlock block, BlockState blockState)
	{
		var shape = paintingWide.get(blockState);

		if(!block.isMultiBlockOrigin(blockState))
		{
			var other = BaseBlock.getFacing(blockState).getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}

	public final VoxelShape shelf(ShelfBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var connection = blockState.getOptionalValue(ShelfBlock.CONNECTION).orElse(ShelfBlock.Connection.SINGLE);

		var shaper = switch(connection) {
			case SINGLE -> shelfSingle.shaper();
			case LEFT -> shelfLeft.shaper();
			case CENTER -> shelfCenter.shaper();
			case RIGHT -> shelfRight.shaper();
		};

		return shaper.get(facing);
	}

	public final VoxelShape sofa(SofaBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var connection = blockState.getOptionalValue(SofaBlock.CONNECTION).orElse(SofaBlock.Connection.SINGLE);

		var shaper = switch(connection) {
			case SINGLE -> sofaSingle.shaper();
			case LEFT -> sofaLeft.shaper();
			case CENTER -> sofaCenter.shaper();
			case RIGHT -> sofaRight.shaper();
			case CORNER -> sofaCorner.shaper();
		};

		return shaper.get(facing);
	}

	public final VoxelShape stool(StoolBlock block, BlockState blockState)
	{
		return stool.get(blockState);
	}

	public final VoxelShape tableLarge(TableLargeBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = tableLarge.get(blockState);
		var index = block.getMultiBlockIndex(blockState);

		if(index == 1 || index == 3)
		{
			var other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		if(index == 2 || index == 3)
			shape = shape.move(facing.getStepX(), 0D, facing.getStepZ());

		return shape;
	}

	public final VoxelShape tableSmall(TableSmallBlock block, BlockState blockState)
	{
		return tableSmall.get(blockState);
	}

	public final VoxelShape tableWide(TableWideBlock block, BlockState blockState)
	{
		var shape = tableWide.get(blockState);

		if(!block.isMultiBlockOrigin(blockState))
		{
			var opposite = BaseBlock.getFacing(blockState).getClockWise();
			shape = shape.move(opposite.getStepX(), 0D, opposite.getStepZ());
		}

		return shape;
	}

	public final VoxelShape wallLight(WallLightBlock block, BlockState blockState)
	{
		return wallLight.get(blockState);
	}

	public final VoxelShape wardrobeTop(WardrobeTopBlock block, BlockState blockState)
	{
		var shape = wardrobeTop.get(blockState);

		if(!block.isMultiBlockOrigin(blockState))
		{
			var other = BaseBlock.getFacing(blockState).getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}

	public final VoxelShape wardrobeBottom(WardrobeBottomBlock block, BlockState blockState)
	{
		var shape = wardrobeBottom.get(blockState);
		var index = block.getMultiBlockIndex(blockState);

		if(index == 1 || index == 3)
		{
			var other = BaseBlock.getFacing(blockState).getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		if(index == 2 || index == 3)
			shape = shape.move(0D, -1D, 0D);

		return shape;
	}

	public final VoxelShape doorDouble(FurnitureDoorBlock block, BlockState blockState)
	{
		var facing = blockState.getOptionalValue(FurnitureDoorBlock.FACING).orElse(Direction.NORTH);
		var open = blockState.getOptionalValue(FurnitureDoorBlock.OPEN).orElse(false);
		var half = blockState.getOptionalValue(FurnitureDoorBlock.HALF).orElse(DoubleBlockHalf.LOWER);
		var hinge = blockState.getOptionalValue(FurnitureDoorBlock.HINGE).orElse(DoorHingeSide.LEFT);

		Direction shapeFacing;

		if(hinge == DoorHingeSide.LEFT)
			shapeFacing = open ? facing.getClockWise() : facing.getOpposite();
		else
			shapeFacing = open ? facing.getClockWise() : facing;

		var shape = doorDouble.shaper().get(shapeFacing);

		var x = 0D;
		var y = 0D;
		var z = 0D;

		if(half == DoubleBlockHalf.UPPER)
			y -= 1D;

		if((hinge == DoorHingeSide.LEFT && open) || (hinge == DoorHingeSide.RIGHT && !open))
		{
			x -= (shapeFacing.getStepX() * .81D);
			z -= (shapeFacing.getStepZ() * .81D);
		}

		return shape.move(x, y, z);
	}

	public final VoxelShape doorSingle(FurnitureDoorBlock block, BlockState blockState)
	{
		var facing = blockState.getOptionalValue(FurnitureDoorBlock.FACING).orElse(Direction.NORTH);
		var open = blockState.getOptionalValue(FurnitureDoorBlock.OPEN).orElse(false);
		var half = blockState.getOptionalValue(FurnitureDoorBlock.HALF).orElse(DoubleBlockHalf.LOWER);
		var hinge = blockState.getOptionalValue(FurnitureDoorBlock.HINGE).orElse(DoorHingeSide.LEFT);

		Direction shapeFacing;

		if(hinge == DoorHingeSide.LEFT)
			shapeFacing = open ? facing.getClockWise() : facing.getOpposite();
		else
			shapeFacing = open ? facing.getClockWise() : facing;

		var shape = doorSingle.shaper().get(shapeFacing);

		var x = 0D;
		var y = 0D;
		var z = 0D;

		if(half == DoubleBlockHalf.UPPER)
			y -= 1D;

		if((hinge == DoorHingeSide.LEFT && open) || (hinge == DoorHingeSide.RIGHT && !open))
		{
			x -= (shapeFacing.getStepX() * .81D);
			z -= (shapeFacing.getStepZ() * .81D);
		}

		return shape.move(x, y, z);
	}

	public final VoxelShape counter(CounterBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var connection = blockState.getOptionalValue(CounterBlock.CONNECTION).orElse(CounterBlock.Connection.SINGLE);

		var shaper = switch(connection) {
			case SINGLE -> counterSingle.shaper();
			case CORNER -> counterCorner.shaper();
		};

		return shaper.get(facing);
	}

	public final VoxelShape oven(Block block, BlockState blockState)
	{
		var shape = oven.get(blockState);

		if(block instanceof OvenMultiBlock multiBlock && !multiBlock.isMultiBlockOrigin(blockState))
		{
			var facing = BaseMultiBlock.getFacing(blockState).getClockWise();
			var offX = facing.getStepX();
			var offZ = facing.getStepZ();
			shape = shape.move(offX, 0F, offZ);
		}

		return shape;
	}

	protected abstract VoxelShape bedDoubleShape();
	protected abstract VoxelShape bedSingleShape();
	protected abstract VoxelShape benchShape();
	protected abstract VoxelShape bookshelfShape();
	protected abstract VoxelShape chairShape();
	protected abstract VoxelShape chandelierShape();
	protected abstract VoxelShape chestShape();
	protected abstract VoxelShape cushionShape();
	protected abstract VoxelShape deskLeftShape();
	protected abstract VoxelShape deskRightShape();
	protected abstract VoxelShape drawerShape();
	protected abstract VoxelShape dresserShape();
	protected abstract VoxelShape lockboxShape();
	protected abstract VoxelShape floorLightShape();
	protected abstract VoxelShape paintingSmallShape();
	protected abstract VoxelShape paintingWideShape();
	protected abstract VoxelShape shelfSingleShape();
	protected abstract VoxelShape shelfCenterShape();
	protected abstract VoxelShape shelfLeftShape();
	protected abstract VoxelShape shelfRightShape();
	protected abstract VoxelShape sofaSingleShape();
	protected abstract VoxelShape sofaCenterShape();
	protected abstract VoxelShape sofaLeftShape();
	protected abstract VoxelShape sofaRightShape();
	protected abstract VoxelShape sofaCornerShape();
	protected abstract VoxelShape stoolShape();
	protected abstract VoxelShape tableLargeShape();
	protected abstract VoxelShape tableSmallShape();
	protected abstract VoxelShape tableWideShape();
	protected abstract VoxelShape wallLightShape();
	protected abstract VoxelShape wardrobeTopShape();
	protected abstract VoxelShape wardrobeBottomShape();
	protected abstract VoxelShape doorDoubleShape();
	protected abstract VoxelShape doorSingleShape();
	protected abstract VoxelShape counterSingleShape();
	protected abstract VoxelShape counterCornerShape();
	protected abstract VoxelShape ovenShape();

	private static final class Nordic extends HitBoxes
	{
		@Override
		protected VoxelShape bedDoubleShape()
		{
			return VoxelShaper.or(
					box(-16D, 3D, 2D, 16D, 5D, 30D),
					box(-14D, 5D, 2D, 14D, 8D, 30D),
					box(-16D, 3D, 0D, 16D, 5D, 2D),
					box(-16D, 0D, 0D, -14D, 8D, 2D),
					box(14D, 0D, 0D, 16D, 8D, 2D),
					box(-16D, 12D, 0D, -8D, 14D, 2D),
					box(8D, 12D, 0D, 16D, 14D, 2D),
					box(-10D, 12D, 0D, 10D, 16D, 2D),
					box(-15D, 5D, 0D, 15D, 12D, 2D),
					box(-15D, 5D, 30D, 15D, 12D, 32D),
					box(-16D, 3D, 30D, 16D, 5D, 32D),
					box(-16D, 0D, 30D, -14D, 8D, 32D),
					box(14D, 0D, 30D, 16D, 8D, 32D),
					box(-16D, 12D, 30D, -8D, 14D, 32D),
					box(8D, 12D, 30D, 16D, 14D, 32D),
					box(-10D, 12D, 30D, 10D, 16D, 32D)
			);
		}

		@Override
		protected VoxelShape bedSingleShape()
		{
			return VoxelShaper.or(
					box(0D, 0D, 0D, 16D, 14D, 2D),
					box(0D, 0D, 30D, 16D, 14D, 32D),
					box(0D, 3D, 2D, 16D, 5D, 30D),
					box(1D, 5D, 2D, 15D, 8D, 30D)
			);
		}

		@Override
		protected VoxelShape benchShape()
		{
			return VoxelShaper.or(
					box(12D, 0D, 2D, 14D, 3D, 4D),
					box(-14D, 0D, 2D, -12D, 3D, 4D),
					box(-14D, 0D, 12D, -12D, 3D, 14D),
					box(12D, 0D, 12D, 14D, 3D, 14D),
					box(12D, 3D, 11.5D, 14D, 5D, 13.5D),
					box(12D, 3D, 2.5D, 14D, 5D, 4.5D),
					box(-14D, 3D, 2.5D, -12D, 5D, 4.5D),
					box(-14D, 3D, 11.5D, -12D, 5D, 13.5D),
					box(-13.5D, 3.5D, 4.5D, -12.5D, 4.5D, 11.5D),
					box(12.5D, 3.5D, 4.5D, 13.5D, 4.5D, 11.5D),
					box(-15D, 5D, 2D, 15D, 7D, 14D)
			);
		}

		@Override
		protected VoxelShape bookshelfShape()
		{
			return VoxelShaper.or(
					box(-15D, 0D, 1D, 15D, 30D, 15D),
					box(-16D, 30D, 0D, 16D, 32D, 16D)
			);
		}

		@Override
		protected VoxelShape chairShape()
		{
			return VoxelShaper.or(
					box(2D, 0D, 2D, 4D, 4D, 4D),
					box(2.5D, 4.5D, 4.5D, 3.5D, 5.5D, 11.5D),
					box(12.5D, 4.5D, 4.5D, 13.5D, 5.5D, 11.5D),
					box(12D, 0D, 2D, 14D, 4D, 4D),
					box(2D, 0D, 12D, 4D, 4D, 14D),
					box(2D, 7D, 2D, 14D, 9D, 14D),
					box(2D, 9D, 13D, 14D, 25D, 14D),
					box(12D, 0D, 12D, 14D, 4D, 14D),
					box(2D, 4D, 11.5D, 4D, 7D, 13.5D),
					box(12D, 4D, 11.5D, 14D, 7D, 13.5D),
					box(2D, 4D, 2.5D, 4D, 7D, 4.5D),
					box(12D, 4D, 2.5D, 14D, 7D, 4.5D)
			);
		}

		@Override
		protected VoxelShape chandelierShape()
		{
			return box(1D, 0D, 1D, 15, 16D, 15D);
		}

		@Override
		protected VoxelShape chestShape()
		{
			return box(-15D, 0D, 2D, 15D, 14D, 16D);
		}

		@Override
		protected VoxelShape cushionShape()
		{
			return VoxelShaper.or(
					box(2D, 0D, 2D, 4D, 2D, 4D),
					box(2D, 0D, 12D, 4D, 2D, 14D),
					box(12D, 0D, 12D, 14D, 2D, 14D),
					box(12D, 0D, 2D, 14D, 2D, 4D),
					box(2D, 5D, 2.25D, 14D, 7D, 13.75D),
					box(1.75D, 4D, 2D, 14.25D, 5D, 14D),
					box(2D, 2D, 2.5D, 4D, 4D, 4.5D),
					box(12D, 2D, 2.5D, 14D, 4D, 4.5D),
					box(12D, 2D, 11.5D, 14D, 4D, 13.5D),
					box(2D, 2D, 11.5D, 4D, 4D, 13.5D),
					box(2.5D, 2.5D, 4.5D, 3.5D, 3.5D, 11.5D),
					box(12.5D, 2.5D, 4.5D, 13.5D, 3.5D, 11.5D)
			);
		}

		@Override
		protected VoxelShape deskLeftShape()
		{
			return VoxelShaper.or(
					box(13D, 0D, 0D, 15D, 9D, 2D),
					box(13D, 7D, 1D, 15D, 13D, 3D),
					box(13D, 7D, 13D, 15D, 13D, 15D),
					box(-15D, 7D, 13D, -13D, 13D, 15D),
					box(-15D, 0D, 0D, -13D, 9D, 2D),
					box(-15D, 0D, 14D, -13D, 9D, 16D),
					box(13D, 0D, 14D, 15D, 9D, 16D),
					box(-16D, 13D, 0D, 16D, 16D, 16D),
					box(-15D, 7D, 1D, -13D, 13D, 3D),
					box(5D, 9D, 2D, 12D, 13D, 11D)
			);
		}

		@Override
		protected VoxelShape deskRightShape()
		{
			return VoxelShaper.or(
					box(13D, 0D, 0D, 15D, 9D, 2D),
					box(13D, 7D, 1D, 15D, 13D, 3D),
					box(13D, 7D, 13D, 15D, 13D, 15D),
					box(-15D, 7D, 13D, -13D, 13D, 15D),
					box(-15D, 0D, 0D, -13D, 9D, 2D),
					box(-15D, 0D, 14D, -13D, 9D, 16D),
					box(13D, 0D, 14D, 15D, 9D, 16D),
					box(-16D, 13D, 0D, 16D, 16D, 16D),
					box(-15D, 7D, 1D, -13D, 13D, 3D),
					box(-12D, 9D, 2D, -5D, 13D, 11D)
			);
		}

		@Override
		protected VoxelShape drawerShape()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 15D, 13D, 15D),
					box(0D, 13D, 0D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape dresserShape()
		{
			return VoxelShaper.or(
					box(-15D, 0D, 1D, 15D, 16D, 15D),
					box(-16D, 13D, 14D, 16D, 16D, 16D),
					box(-16D, 13D, 0D, 16D, 16D, 2D)
			);
		}

		@Override
		protected VoxelShape lockboxShape()
		{
			return VoxelShaper.or(
					box(2, 0, 3, 14, 9, 13),
					box(2, 9, 5, 14, 10, 11)
			);
		}

		@Override
		protected VoxelShape floorLightShape()
		{
			return VoxelShaper.or(
					box(6D, 0D, 6D, 10D, 2D, 10D),
					box(7D, 2D, 7D, 9D, 20D, 9D),
					box(6.5D, 20.75D, 2.5D, 9.5D, 22.75D, 5.5D),
					box(2.5D, 20.75D, 6.5D, 5.5D, 22.75D, 9.5D),
					box(7.25D, 22.75D, 3.25D, 8.75D, 26.75D, 4.75D),
					box(3.25D, 22.75D, 7.25D, 4.75D, 26.75D, 8.75D),
					box(7.25D, 22.75D, 11.25D, 8.75D, 26.75D, 12.75D),
					box(11.25D, 22.75D, 7.25D, 12.75D, 26.75D, 8.75D),
					box(10.5D, 20.75D, 6.5D, 13.5D, 22.75D, 9.5D),
					box(6.5D, 20.75D, 10.5D, 9.5D, 22.75D, 13.5D),
					box(3D, 16.75D, 7D, 7D, 20.75, 9D),
					box(9D, 16.75D, 7D, 13D, 20.75, 9D),
					box(7D, 16.75D, 3D, 9D, 20.75, 7D),
					box(7D, 16.75D, 9D, 9D, 20.75, 13D)
			);
		}

		@Override
		protected VoxelShape paintingSmallShape()
		{
			return box(0D, 0D, 14D, 16D, 16D, 16D);
		}

		@Override
		protected VoxelShape paintingWideShape()
		{
			return box(-16D, 0D, 14D, 16D, 16D, 16D);
		}

		@Override
		protected VoxelShape shelfSingleShape()
		{
			return VoxelShaper.or(
					box(.5D, 9D, 2D, 2.5D, 14D, 13D),
					box(13.5D, 9D, 2D, 15.5D, 14D, 13D),
					box(0D, 14D, 0D, 16D, 16D, 16D),
					box(13D, 6D, 13D, 16D, 14D, 16D),
					box(0D, 6D, 13D, 3D, 14D, 16D)
			);
		}

		@Override
		protected VoxelShape shelfCenterShape()
		{
			return box(0D, 14D, 0D, 16D, 16D, 16D);
		}

		@Override
		protected VoxelShape shelfLeftShape()
		{
			return VoxelShaper.or(
					box(13.5D, 9D, 2D, 15.5D, 14D, 13D),
					box(0D, 14D, 0D, 16D, 16D, 16D),
					box(13D, 6D, 13D, 16D, 14D, 16D)
			);
		}

		@Override
		protected VoxelShape shelfRightShape()
		{
			return VoxelShaper.or(
					box(.5D, 9D, 2D, 2.5D, 14D, 13D),
					box(0D, 14D, 0D, 16D, 16D, 16D),
					box(0D, 6D, 13D, 3D, 14D, 16D)
			);
		}

		@Override
		protected VoxelShape sofaSingleShape()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 3D, 3D, 3D),
					box(1D, 0D, 13D, 3D, 3D, 15D),
					box(13D, 0D, 13D, 15D, 3D, 15D),
					box(13D, 0D, 1D, 15D, 3D, 3D),
					box(0D, 3D, 0D, 16D, 6D, 16D),
					box(0D, 6D, 13D, 16D, 16D, 16D),
					box(14D, 10D, 0D, 16D, 12D, 14D),
					box(0D, 10D, 0D, 2D, 12D, 14D),
					box(0D, 6D, 0D, 2D, 10D, 2D),
					box(14D, 6D, 0D, 16D, 10D, 2D)
			);
		}

		@Override
		protected VoxelShape sofaCenterShape()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 3D, 3D, 3D),
					box(1D, 0D, 13D, 3D, 3D, 15D),
					box(13D, 0D, 13D, 15D, 3D, 15D),
					box(13D, 0D, 1D, 15D, 3D, 3D),
					box(0D, 3D, 0D, 16D, 6D, 16D),
					box(0D, 6D, 13D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape sofaLeftShape()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 3D, 3D, 3D),
					box(1D, 0D, 13D, 3D, 3D, 15D),
					box(13D, 0D, 13D, 15D, 3D, 15D),
					box(13D, 0D, 1D, 15D, 3D, 3D),
					box(0D, 3D, 0D, 16D, 6D, 16D),
					box(0D, 6D, 13D, 16D, 16D, 16D),
					box(14D, 10D, 0D, 16D, 12D, 13D),
					box(14D, 6D, 0D, 16D, 10D, 2D)
			);
		}

		@Override
		protected VoxelShape sofaRightShape()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 3D, 3D, 3D),
					box(1D, 0D, 13D, 3D, 3D, 15D),
					box(13D, 0D, 13D, 15D, 3D, 15D),
					box(13D, 0D, 1D, 15D, 3D, 3D),
					box(0D, 3D, 0D, 16D, 6D, 16D),
					box(0D, 6D, 13D, 16D, 16D, 16D),
					box(0D, 10D, 0D, 2D, 12D, 13D),
					box(0D, 6D, 0D, 2D, 10D, 2D)
			);
		}

		@Override
		protected VoxelShape sofaCornerShape()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 3D, 3D, 3D),
					box(1D, 0D, 13D, 3D, 3D, 15D),
					box(13D, 0D, 13D, 15D, 3D, 15D),
					box(13D, 0D, 1D, 15D, 3D, 3D),
					box(0D, 3D, 0D, 16D, 6D, 16D),
					box(0D, 6D, 13D, 16D, 16D, 16D),
					box(13D, 6D, 0D, 16D, 16D, 13D)
			);
		}

		@Override
		protected VoxelShape stoolShape()
		{
			return VoxelShaper.or(
					box(2D, 0D, 2D, 4D, 3D, 4D),
					box(12D, 0D, 12D, 14D, 3D, 14D),
					box(12D, 0D, 2D, 14D, 3D, 4D),
					box(2D, 0D, 12D, 4D, 3D, 14D),
					box(2D, 3D, 11.5D, 4D, 5D, 13.5D),
					box(12D, 3D, 11.5D, 14D, 5, 13.5D),
					box(12D, 3D, 2.5D, 14D, 5D, 4.5D),
					box(1.5D, 5D, 1.75D, 14.5D, 7D, 14.25D),
					box(2D, 3D, 2.5D, 4D, 5D, 4.5D),
					box(2.5D, 3.5D, 4.5D, 3.5D, 4.5D, 11.5D),
					box(12.5D, 3.5D, 4.5D, 13.5D, 4.5D, 11.5D)
			);
		}

		@Override
		protected VoxelShape tableLargeShape()
		{
			return VoxelShaper.or(
					box(12D, 0D, 2D, 14D, 13D, 4D),
					box(-14D, 0D, 2D, -12D, 13D, 4D),
					box(-14D, 0D, 28D, -12D, 13D, 30D),
					box(12D, 0D, 28D, 14D, 13D, 30D),
					box(-16D, 13D, 0D, 16D, 16D, 32D)
			);
		}

		@Override
		protected VoxelShape tableSmallShape()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 3D, 13D, 3D),
					box(1D, 0D, 13D, 3D, 13D, 15D),
					box(13D, 0D, 13D, 15D, 13D, 15D),
					box(13D, 0D, 1D, 15D, 13D, 3D),
					box(0D, 13D, 0D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape tableWideShape()
		{
			return VoxelShaper.or(
					box(13D, 0D, 0D, 15D, 9D, 2D),
					box(13D, 7D, 1D, 15D, 13D, 3D),
					box(13D, 7D, 13D, 15D, 13D, 15D),
					box(-15D, 7D, 13D, -13D, 13D, 15D),
					box(-15D, 0D, 0D, -13D, 9D, 2D),
					box(-15D, 0D, 14D, -13D, 9D, 16D),
					box(13D, 0D, 14D, 15D, 9D, 16D),
					box(-16D, 13D, 0D, 16D, 16D, 16D),
					box(-15D, 7D, 1D, -13D, 13D, 3D)
			);
		}

		@Override
		protected VoxelShape wallLightShape()
		{
			return VoxelShaper.or(
					box(6D, 5D, 15D, 10D, 11D, 16D),
					box(6D, 2D, 8D, 10D, 15D, 15D)
			);
		}

		@Override
		protected VoxelShape wardrobeTopShape()
		{
			return VoxelShaper.or(
					box(-15D, 3D, 0D, 15D, 14D, 16D),
					box(-16D, 0D, 0D, 16D, 3D, 16D)
			);
		}

		@Override
		protected VoxelShape wardrobeBottomShape()
		{
			return VoxelShaper.or(
					box(-14.75D, 0D, .25D, -12.25D, 31D, 2.75D),
					box(-14.75D, 0D, 13.25D, -12.25D, 31D, 15.75D),
					box(12.25D, 0D, 13.25D, 14.75D, 31D, 15.75D),
					box(12.25D, 0D, .25D, 14.75D, 31D, 2.75D),
					box(-14D, 2D, 1D, 14D, 31D, 15D),
					box(-15D, 31D, 0D, 15D, 32D, 16D)
			);
		}

		@Override
		protected VoxelShape doorDoubleShape()
		{
			return VoxelShaper.or(
					box(0, 0, 0, 13, 2, 3),
					box(0, 10, 0, 13, 12, 3),
					box(0, 20, 0, 13, 22, 3),
					box(13, 0, 0, 16, 32, 3),
					box(12, 28, 0, 13, 32, 3),
					box(10, 29, 0, 12, 32, 3),
					box(8, 30, 0, 10, 32, 3),
					box(3, 31, 0, 8, 32, 3),
					box(0, 2, 0.5, 13, 32, 2.5)
			);
		}

		@Override
		protected VoxelShape doorSingleShape()
		{
			return VoxelShaper.or(
					box(0, 0, 0, 13, 2, 3),
					box(0, 10, 0, 13, 12, 3),
					box(0, 20, 0, 13, 22, 3),
					box(0, 30, 0, 13, 32, 3),
					box(13, 0, 0, 16, 32, 3),
					box(0, 2, 0.5, 13, 30, 2.5)
			);
		}

		@Override
		protected VoxelShape counterSingleShape()
		{
			return VoxelShaper.or(
					box(0, 0, 3, 16, 13, 16),
					box(0, 13, 0, 16, 16, 16),
					box(1, 1, 2, 15, 12, 3)
			);
		}

		@Override
		protected VoxelShape counterCornerShape()
		{
			return VoxelShaper.or(
					box(0, 0, 0, 13, 13, 4),
					box(0, 0, 3, 16, 13, 16),
					box(0, 13, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape ovenShape()
		{
			return VoxelShaper.or(
					Block.box(0, 0, 0, 16, 1, 16),
					Block.box(0, 1, 1, 16, 9, 16),
					Block.box(0, 9, 0, 16, 10, 16),
					Block.box(1, 10, 3, 15, 14, 16),
					Block.box(2, 14, 3, 14, 16, 16)
			);
		}
	}

	private static final class Dunmer extends HitBoxes
	{
		@Override
		protected VoxelShape bedDoubleShape()
		{
			return VoxelShaper.or(
					box(-16D, 0D, 0D, -14D, 13D, 2D),
					box(-16D, 0D, 30D, -14D, 11D, 32D),
					box(14D, 0D, 30D, 16D, 11D, 32D),
					box(14D, 0D, 0D, 16D, 13D, 2D),
					box(-14D, 3D, 0D, 14D, 14.25D, 2D),
					box(-14D, 3D, 30D, 14D, 12.25D, 32D),
					box(-15D, 5D, 2D, 15D, 8D, 30D),
					box(-16D, 3D, 2D, 16D, 5D, 30D)
			);
		}

		@Override
		protected VoxelShape bedSingleShape()
		{
			return VoxelShaper.or(
					box(0D, 0D, 0D, 2D, 13D, 2D),
					box(0D, 0D, 30D, 2D, 11D, 32D),
					box(14D, 0D, 30D, 16D, 11D, 32D),
					box(14D, 0D, 0D, 16D, 13D, 2D),
					box(2D, 3D, 0D, 14D, 14.25D, 2D),
					box(2D, 3D, 30D, 14D, 12.25D, 32D),
					box(1D, 5D, 2D, 15D, 8D, 30D),
					box(0D, 3D, 2D, 16D, 5D, 30D)
			);
		}

		@Override
		protected VoxelShape benchShape()
		{
			return VoxelShaper.or(
					box(12, 0, 2, 14, 5, 4),
					box(-14, 0, 2, -12, 5, 4),
					box(-14, 0, 12, -12, 5, 14),
					box(12, 0, 12, 14, 5, 14),
					box(-15, 5, 1, 15, 7, 15),
					box(12.5, 2.5, 4, 13.5, 3.5, 12),
					box(-13.5, 2.5, 4, -12.5, 3.5, 12)
			);
		}

		@Override
		protected VoxelShape bookshelfShape()
		{
			return VoxelShaper.or(
					box(-14, 0, 2, -12, 30, 4),
					box(-14, 0, 12, -12, 30, 14),
					box(12, 0, 12, 14, 30, 14),
					box(12, 0, 2, 14, 30, 4),
					box(-12, 9, 4, 12, 32, 12),
					box(-15, 9, 1, 15, 11, 15),
					box(-15, 19, 1, 15, 21, 15),
					box(-15, 30, 1, 15, 32, 15)
			);
		}

		@Override
		protected VoxelShape chairShape()
		{
			return VoxelShaper.or(
					box(2, 0, 2, 4, 4, 4),
					box(2, 0, 12, 4, 4, 14),
					box(12, 0, 12, 14, 4, 14),
					box(12, 0, 2, 14, 4, 4),
					box(11.5, 4, 2.5, 13.5, 7, 4.5),
					box(2.5, 4, 2.5, 4.5, 7, 4.5),
					box(2.5, 4, 11.5, 4.5, 7, 13.5),
					box(11.5, 4, 11.5, 13.5, 7, 13.5),
					box(2, 7, 1, 14, 9, 15),
					box(2.5, 9, 11.5, 13.5, 31.5, 13.5)
			);
		}

		@Override
		protected VoxelShape chandelierShape()
		{
			return box(1, 2, 1, 15, 16, 15);
		}

		@Override
		protected VoxelShape chestShape()
		{
			return VoxelShaper.or(
					box(12, 0, 2, 14, 14, 4),
					box(-14, 0, 2, -12, 14, 4),
					box(-14, 0, 12, -12, 14, 14),
					box(12, 0, 12, 14, 14, 14),
					box(-15, 4, 1, 15, 6, 15),
					box(-15, 14, 1, 15, 16, 15),
					box(-13, 6, 3, 13, 14, 13),
					box(-2, 11, 2, 2, 14, 3)
			);
		}

		@Override
		protected VoxelShape cushionShape()
		{
			return VoxelShaper.or(
					box(3, 0, 3, 5, 4, 5),
					box(3, 0, 11, 5, 4, 13),
					box(11, 0, 11, 13, 4, 13),
					box(11, 0, 3, 13, 4, 5),
					box(2, 4, 2, 14, 5, 14),
					box(2.5, 5, 2.5, 13.5, 7, 13.5)
			);
		}

		@Override
		protected VoxelShape deskLeftShape()
		{
			return VoxelShaper.or(
					box(12, 0, 2, 14, 14, 4),
					box(-14, 0, 2, -12, 14, 4),
					box(-14, 0, 12, -12, 14, 14),
					box(12, 0, 12, 14, 14, 14),
					box(-16, 14, 0, 16, 16, 16),
					box(4, 10, 2, 11, 14, 11)
			);
		}

		@Override
		protected VoxelShape deskRightShape()
		{
			return VoxelShaper.or(
					box(12, 0, 2, 14, 14, 4),
					box(-14, 0, 2, -12, 14, 4),
					box(-14, 0, 12, -12, 14, 14),
					box(12, 0, 12, 14, 14, 14),
					box(-16, 14, 0, 16, 16, 16),
					box(-11, 10, 2, -4, 14, 11)
			);
		}

		@Override
		protected VoxelShape drawerShape()
		{
			return VoxelShaper.or(
					box(1, 0, 1, 3, 14, 3),
					box(1, 0, 13, 3, 14, 15),
					box(13, 0, 13, 15, 14, 15),
					box(13, 0, 1, 15, 14, 3),
					box(0, 14, 0, 16, 16, 16),
					box(1, 6, 1, 15, 8, 15),
					box(2, 6, 2, 14, 14, 14)
			);
		}

		@Override
		protected VoxelShape dresserShape()
		{
			return VoxelShaper.or(
					box(-15, 0, 1, -13, 14, 3),
					box(-15, 0, 13, -13, 14, 15),
					box(13, 0, 13, 15, 14, 15),
					box(13, 0, 1, 15, 14, 3),
					box(-16, 14, 0, 16, 16, 16),
					box(-15, 6, 1, 15, 8, 15),
					box(-14, 8, 2, 14, 14, 14)
			);
		}

		@Override
		protected VoxelShape lockboxShape()
		{
			return box(2, 0, 3, 14, 14, 13);
		}

		@Override
		protected VoxelShape floorLightShape()
		{
			return VoxelShaper.or(
					box(6, 0, 6, 10, 2, 10),
					box(7, 2, 7, 9, 22, 9),
					box(6, 22, 6, 10, 27, 10),
					box(7, 27, 7, 9, 28, 9)
			);
		}

		@Override
		protected VoxelShape paintingSmallShape()
		{
			return VoxelShaper.or(
					box(0, 1, 14, 16, 3, 16),
					box(0, 13, 14, 16, 15, 16),
					box(1, 3, 14, 15, 13, 16)
			);
		}

		@Override
		protected VoxelShape paintingWideShape()
		{
			return VoxelShaper.or(
					box(-16, 0, 14, 16, 2, 16),
					box(-16, 14, 14, 16, 16, 16),
					box(-15, 2, 14, 15, 14, 16)
			);
		}

		@Override
		protected VoxelShape shelfSingleShape()
		{
			return VoxelShaper.or(
					box(13, 8, 14, 15, 14, 16),
					box(1, 8, 14, 3, 14, 16),
					box(0, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape shelfCenterShape()
		{
			return box(0, 14, 0, 16, 16, 16);
		}

		@Override
		protected VoxelShape shelfLeftShape()
		{
			return VoxelShaper.or(
					box(13, 8, 14, 15, 14, 16),
					box(0, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape shelfRightShape()
		{
			return VoxelShaper.or(
					box(1, 8, 14, 3, 14, 16),
					box(0, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape sofaSingleShape()
		{
			return VoxelShaper.or(
					box(0, 4, 1, 16, 6, 15),
					box(2, 0, 12, 4, 4, 14),
					box(2, 0, 2, 4, 4, 4),
					box(12, 0, 2, 14, 4, 4),
					box(12, 0, 12, 14, 4, 14),
					box(1, 6, 12, 15, 16, 14),
					box(13, 10, 2, 15, 12, 12),
					box(13, 6, 3, 15, 10, 5),
					box(1, 6, 3, 3, 10, 5),
					box(1, 10, 2, 3, 12, 12),
					box(3, 6, 2, 13, 7, 13)
			);
		}

		@Override
		protected VoxelShape sofaCenterShape()
		{
			return VoxelShaper.or(
					box(2, 0, 2, 4, 4, 4),
					box(12, 0, 2, 14, 4, 4),
					box(12, 0, 12, 14, 4, 14),
					box(2, 0, 12, 4, 4, 14),
					box(0, 4, 1, 16, 6, 15),
					box(0, 6, 12, 16, 16, 14),
					box(0, 6, 2, 16, 7, 13)
			);
		}

		@Override
		protected VoxelShape sofaLeftShape()
		{
			return VoxelShaper.or(
					box(2, 0, 12, 4, 4, 14),
					box(2, 0, 2, 4, 4, 4),
					box(12, 0, 2, 14, 4, 4),
					box(12, 0, 12, 14, 4, 14),
					box(0, 4, 1, 15, 6, 15),
					box(0, 6, 12, 15, 16, 14),
					box(13, 10, 2, 15, 12, 12),
					box(13, 6, 3, 15, 10, 5),
					box(0, 6, 2, 13, 7, 13)
			);
		}

		@Override
		protected VoxelShape sofaRightShape()
		{
			return VoxelShaper.or(
					box(12, 0, 12, 14, 4, 14),
					box(12, 0, 2, 14, 4, 4),
					box(2, 0, 2, 4, 4, 4),
					box(2, 0, 12, 4, 4, 14),
					box(1, 4, 1, 16, 6, 15),
					box(1, 6, 12, 16, 16, 14),
					box(1, 10, 2, 3, 12, 12),
					box(1, 6, 3, 3, 10, 5),
					box(3, 6, 2, 16, 7, 13)
			);
		}

		@Override
		protected VoxelShape sofaCornerShape()
		{
			return VoxelShaper.or(
					box(2, 0, 12, 4, 4, 14),
					box(2, 0, 2, 4, 4, 4),
					box(12, 0, 2, 14, 4, 4),
					box(12, 0, 12, 14, 4, 14),
					box(12, 6, 12, 14, 16, 14),
					box(12, 6, 0, 14, 16, 12),
					box(0, 6, 12, 12, 16, 14),
					box(1, 4, 0, 15, 6, 15),
					box(0, 4, 1, 1, 6, 15),
					box(0, 6, 2, 13, 7, 13),
					box(2, 6, 0, 13, 7, 2)
			);
		}

		@Override
		protected VoxelShape stoolShape()
		{
			return VoxelShaper.or(
					box(12, 0, 2, 14, 5, 4),
					box(2, 0, 2, 4, 5, 4),
					box(2, 0, 12, 4, 5, 14),
					box(12, 0, 12, 14, 5, 14),
					box(1, 5, 1, 15, 7, 15),
					box(12.5, 2.5, 4, 13.5, 3.5, 12),
					box(2.5, 2.5, 4, 3.5, 3.5, 12)
			);
		}

		@Override
		protected VoxelShape tableLargeShape()
		{
			return VoxelShaper.or(
					box(12, 0, 2, 14, 14, 4),
					box(-14, 0, 2, -12, 14, 4),
					box(-14, 0, 28, -12, 14, 30),
					box(12, 0, 28, 14, 14, 30),
					box(-16, 14, 0, 16, 16, 32)
			);
		}

		@Override
		protected VoxelShape tableSmallShape()
		{
			return VoxelShaper.or(
					box(1, 0, 1, 3, 14, 3),
					box(1, 0, 13, 3, 14, 15),
					box(13, 0, 13, 15, 14, 15),
					box(13, 0, 1, 15, 14, 3),
					box(0, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape tableWideShape()
		{
			return VoxelShaper.or(
					box(12, 0, 2, 14, 14, 4),
					box(-14, 0, 2, -12, 14, 4),
					box(-14, 0, 12, -12, 14, 14),
					box(12, 0, 12, 14, 14, 14),
					box(-16, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape wallLightShape()
		{
			return VoxelShaper.or(
					box(4.5, 1, 7.5, 11.5, 2, 14.5),
					box(4.5, 8, 7.5, 11.5, 9, 14.5),
					box(5, 2, 8, 11, 8, 14),
					box(6, 9, 9, 10, 10, 13),
					box(7.5, 10, 10.5, 8.5, 13, 11.5),
					box(7, 13, 9, 9, 15, 15),
					box(6, 12, 15, 10, 16, 16)
			);
		}

		@Override
		protected VoxelShape wardrobeTopShape()
		{
			return VoxelShaper.or(
					box(13, 0, 1, 15, 8, 3),
					box(-15, 0, 1, -13, 8, 3),
					box(-15, 0, 13, -13, 8, 15),
					box(13, 0, 13, 15, 8, 15),
					box(-16, 8, 0, 16, 10, 16),
					box(-13, 0, 2, 13, 8, 14)
			);
		}

		@Override
		protected VoxelShape wardrobeBottomShape()
		{
			return VoxelShaper.or(
					box(-15, 0, 1, -13, 30, 3),
					box(-15, 0, 13, -13, 30, 15),
					box(13, 0, 13, 15, 30, 15),
					box(13, 0, 1, 15, 30, 3),
					box(-16, 30, 0, 16, 32, 16),
					box(-16, 20, 0, 16, 22, 16),
					box(-16, 2, 0, 16, 4, 16),
					box(-13, 4, 2, 13, 30, 14)
			);
		}

		@Override
		protected VoxelShape doorDoubleShape()
		{
			return box(0, 0, 0.5, 16, 32, 2.5);
		}

		@Override
		protected VoxelShape doorSingleShape()
		{
			return VoxelShaper.or(
					box(0, 0, 0.5, 16, 21, 2.5),
					box(0, 29, 0.5, 16, 32, 2.5),
					box(13, 21, 0.5, 16, 29, 2.5),
					box(0, 21, 0.5, 3, 29, 2.5),
					box(4, 21, 1, 5, 30, 2),
					box(6, 21, 1, 7, 30, 2),
					box(9, 21, 1, 10, 30, 2),
					box(11, 21, 1, 12, 30, 2),
					box(3, 27, 1, 13, 28, 2),
					box(3, 22, 1, 13, 23, 2)
			);
		}

		@Override
		protected VoxelShape counterSingleShape()
		{
			return VoxelShaper.or(
					box(0, 0, 3, 2, 1, 5),
					box(0, 0, 14, 2, 1, 16),
					box(14, 0, 14, 16, 1, 16),
					box(14, 0, 3, 16, 1, 5),
					box(0, 1, 3, 16, 14, 16),
					box(0, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape counterCornerShape()
		{
			return VoxelShaper.or(
					box(0, 0, 0, 2, 1, 2),
					box(11, 0, 0, 13, 1, 5),
					box(13, 0, 3, 16, 1, 5),
					box(14, 0, 14, 16, 1, 16),
					box(0, 0, 14, 2, 1, 16),
					box(0, 1, 3, 16, 14, 16),
					box(0, 1, 0, 13, 14, 3),
					box(0, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape ovenShape()
		{
			return VoxelShaper.or(
					Block.box(-9, 0, 0, 9, 3, 16),
					Block.box(12.5, 0, 6.5, 15.5, 3, 9.5),
					Block.box(-15.5, 0, 6.5, -12.5, 3, 9.5),
					Block.box(-15, 3, 7, -13, 16, 9),
					Block.box(13, 3, 7, 15, 16, 9),
					Block.box(-16, 12, 7, 16, 14, 9),
					Block.box(-5, 10.5, 5.5, 5, 15.5, 10.5)
			);
		}
	}

	private static final class Venthyr extends HitBoxes
	{
		@Override
		protected VoxelShape bedDoubleShape()
		{
			return VoxelShaper.or(
					box(-16D, 0D, 0D, -13D, 2D, 3D),
					box(-15.5D, 2D, .5D, -13.5D, 12D, 2.5D),
					box(-15.5D, 2D, 29.5D, -13.5D, 12D, 31.5D),
					box(13.5D, 2D, 29.5D, 15.5D, 12D, 31.5D),
					box(13.5D, 2D, .5D, 15.5D, 12D, 2.5D),
					box(-16D, 12D, 0D, -13D, 14D, 3D),
					box(-16D, 12D, 29D, -13D, 14D, 32D),
					box(13D, 12D, 29D, 16D, 14D, 32D),
					box(13D, 12D, 0D, 16D, 14D, 3D),
					box(13D, 0D, 0D, 16D, 2D, 3D),
					box(13D, 0D, 29D, 16D, 2D, 32D),
					box(-16D, 0D, 29D, -13D, 2D, 32D),
					box(-15.5D, 0D, 2.5D, 15.5D, 5D, 29.5D),
					box(-13.5D, 0D, 29.5D, 13.5D, 12D, 31.5D),
					box(-13.5D, 0D, .5D, 13.5D, 14D, 2.5D),
					box(-14.5D, 0D, 2.5D, 14.5D, 8D, 29.5D)
			);
		}

		@Override
		protected VoxelShape bedSingleShape()
		{
			return VoxelShaper.or(
					box(0D, 0D, 29D, 3D, 2D, 32D),
					box(13D, 0D, 29D, 16D, 2D, 32D),
					box(13D, 0D, 0D, 16D, 2D, 3D),
					box(0D, 0D, 0D, 3D, 2D, 3D),
					box(.5D, 2D, .5D, 2.5D, 12D, 2.5D),
					box(.5D, 2D, 29.5D, 2.5D, 12D, 31.5D),
					box(13.5D, 2D, 29.5D, 15.5D, 12D, 31.5D),
					box(13.5D, 2D, .5D, 15.5D, 12D, 2.5D),
					box(13D, 12D, 0D, 16D, 14D, 3D),
					box(0D, 12D, 0D, 3D, 14D, 3D),
					box(0D, 12D, 29D, 3D, 14D, 32D),
					box(13D, 12D, 29D, 16D, 14D, 32D),
					box(.5D, 0D, 2D, 15.5D, 5D, 30D),
					box(2.5D, 0D, 29.5D, 13.5D, 11D, 31.5D),
					box(2.5D, 0D, .5D, 13.5D, 13D, 2.5D),
					box(1.5D, 5D, 2.5D, 14.5D, 8D, 29.5D)
			);
		}

		@Override
		protected VoxelShape benchShape()
		{
			return VoxelShaper.or(
					box(-14D, 0D, 2D, -11D, 4D, 5D),
					box(-14D, 0D, 11D, -11D, 4D, 14D),
					box(11D, 0D, 11D, 14D, 4D, 14D),
					box(11D, 0D, 2D, 14D, 4D, 5D),
					box(-15D, 4D, 1D, 15D, 7D, 15D)
			);
		}

		@Override
		protected VoxelShape bookshelfShape()
		{
			return VoxelShaper.or(
					box(-16D, 0D, 2D, 16D, 3D, 16D),
					box(-15D, 3D, 3D, 15D, 29D, 16D),
					box(-16D, 29D, 2D, 16D, 32D, 16D)
			);
		}

		@Override
		protected VoxelShape chairShape()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 4D, 5D, 4D),
					box(12D, 0D, 1D, 15D, 5D, 4D),
					box(12D, 0D, 12D, 15D, 5D, 15D),
					box(1D, 0D, 12D, 4D, 5D, 15D),
					box(.5D, 5D, .5D, 15.5D, 9D, 15.5D),
					box(1D, 9D, 12D, 15D, 31D, 15D)
			);
		}

		@Override
		protected VoxelShape chandelierShape()
		{
			return box(1D, 0D, 1D, 15, 16D, 15D);
		}

		@Override
		protected VoxelShape chestShape()
		{
			return box(-13D, 0D, 1D, 13D, 14.25D, 15D);
		}

		@Override
		protected VoxelShape cushionShape()
		{
			return VoxelShaper.or(
					box(2D, 0D, 2D, 5D, 3D, 5D),
					box(2D, 0D, 11D, 5D, 3D, 14D),
					box(11D, 0D, 11D, 14D, 3D, 14D),
					box(11D, 0D, 2D, 14D, 3D, 5D),
					box(1D, 3D, 1D, 15D, 4D, 15D),
					box(2D, 4D, 2D, 14D, 7D, 14D)
			);
		}

		@Override
		protected VoxelShape deskLeftShape()
		{
			return VoxelShaper.or(
					box(-15D, 0D, 1D, -12D, 2D, 4D),
					box(-15D, 0D, 12D, -12D, 2D, 15D),
					box(12D, 0D, 12D, 15, 2D, 15D),
					box(12D, 0D, 1D, 15, 2D, 4D),
					box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
					box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
					box(-14.5D, 2D, 12.5D, -12.5D, 13D, 14.5D),
					box(12.5D, 2D, 12.5D, 14.5D, 13, 14.5D),
					box(-16D, 13D, 0D, 16D, 16D, 16D),
					box(5D, 9D, 2, 12D, 13D, 11D),
					box(12.5D, 9D, 3.5D, 14.5D, 13D, 12.5D),
					box(-14.5D, 9D, 3.5D, -12.5D, 13D, 12.5D)
			);
		}

		@Override
		protected VoxelShape deskRightShape()
		{
			return VoxelShaper.or(
					box(-15D, 0D, 1D, -12D, 2D, 4D),
					box(-15D, 0D, 12D, -12D, 2D, 15D),
					box(12D, 0D, 12D, 15D, 2D, 15D),
					box(12D, 0D, 1D, 15D, 2D, 4D),
					box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
					box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
					box(-14.5D, 2D, 12.5D, -12.5D, 13D, 14.5D),
					box(12.5D, 2D, 12.5D, 14.5D, 13D, 14.5D),
					box(-16D, 13D, 0D, 16D, 16D, 16D),
					box(-12D, 9D, 2D, -5D, 13D, 11D),
					box(12.5D, 9D, 3.5D, 14.5D, 13D, 12.5D),
					box(-14.5D, 9D, 3.5D, -12.5D, 13D, 12.5D)
			);
		}

		@Override
		protected VoxelShape drawerShape()
		{
			return VoxelShaper.or(
					box(.5D, 0D, .5D, 3.5D, 2D, 3.5D),
					box(.5D, 0D, 12.5D, 3.5D, 2D, 15.5D),
					box(12.5D, 0D, 12.5D, 15.5D, 2D, 15.5D),
					box(12.5D, 0D, .5D, 15.5D, 2D, 3.5D),
					box(13D, 2D, 1D, 15D, 13D, 3D),
					box(1D, 2D, 1D, 3D, 13D, 3D),
					box(1D, 2D, 13D, 3D, 13D, 15D),
					box(13D, 2D, 13D, 15D, 13D, 15D),
					box(1D, 5D, 1D, 15D, 13D, 15D),
					box(0D, 13D, 0D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape dresserShape()
		{
			return VoxelShaper.or(
					box(-15.5D, 0D, .5D, -12.5D, 2D, 3.5D),
					box(-15.5D, 0D, 12.5D, -12.5D, 2D, 15.5D),
					box(12.5D, 0D, 12.5D, 15.5D, 2D, 15.5D),
					box(12.5D, 0D, .5D, 15.5D, 2D, 3.5D),
					box(13D, 2D, 1D, 15D, 13D, 3D),
					box(-15D, 2D, 1D, -13D, 13D, 3D),
					box(-15D, 2D, 13D, -13D, 13D, 15D),
					box(13D, 2D, 13D, 15D, 13D, 15D),
					box(-15D, 5D, 1D, 15D, 13D, 15D),
					box(-16D, 13D, 0D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape lockboxShape()
		{
			return VoxelShaper.or(
					box(2, 1, 4, 14, 10, 12),
					box(1.5, 6, 3.5, 14.5, 7, 12.5),
					box(1.5, 1, 3.5, 14.5, 2, 12.5),
					box(7, 4, 3.5, 9, 6, 4.25),
					box(12, 0, 4, 14, 1, 6),
					box(2, 0, 4, 4, 1, 6),
					box(2, 0, 10, 4, 1, 12),
					box(12, 0, 10, 14, 1, 12)
			);
		}

		@Override
		protected VoxelShape floorLightShape()
		{
			return VoxelShaper.or(
					box(6D, 0D, 6D, 10D, 2D, 10D),
					box(7D, 2D, 7D, 9D, 20D, 9D),
					box(4D, 17.75D, 7D, 7D, 21.75D, 9D),
					box(9D, 17.75D, 7D, 12D, 21.75D, 9D),
					box(7D, 17.75D, 4D, 9D, 21.75D, 7D),
					box(7D, 17.75D, 9D, 9D, 21.75D, 12D),
					box(2.5D, 20.75D, 2.5D, 13.5D, 24D, 13.5D),
					box(10.25D, 24D, 10.25D, 12.5D, 28.75D, 12.5D),
					box(3.5D, 24D, 10.25D, 5.75D, 28.75D, 12.5D),
					box(3.5D, 24D, 3.5D, 5.75D, 28.75D, 5.75D),
					box(10.25D, 24D, 3.5D, 12.5D, 28.75D, 5.75D)
			);
		}

		@Override
		protected VoxelShape paintingSmallShape()
		{
			return box(0D, 0D, 14D, 16D, 16D, 16D);
		}

		@Override
		protected VoxelShape paintingWideShape()
		{
			return box(-16D, 0D, 14D, 16D, 16D, 16D);
		}

		@Override
		protected VoxelShape shelfSingleShape()
		{
			return VoxelShaper.or(
					box(0D, 13D, 0D, 16D, 16D, 16D),
					box(13D, 9D, 10D, 16D, 13D, 16D),
					box(13D, 11D, 3D, 16D, 13D, 10D),
					box(13D, 10D, 0D, 16D, 13D, 3D),
					box(13D, 6D, 13D, 16D, 9D, 16D),
					box(13D, 3D, 12D, 16D, 6D, 16D),
					box(0D, 3D, 12D, 3D, 6D, 16D),
					box(0D, 9D, 10D, 3D, 13D, 16D),
					box(0D, 11D, 3D, 3D, 13D, 10D),
					box(0D, 10D, 0D, 3D, 13D, 3D),
					box(0D, 6D, 13D, 3D, 9D, 16D)
			);
		}

		@Override
		protected VoxelShape shelfCenterShape()
		{
			return box(0D, 13D, 0D, 16D, 16D, 16D);
		}

		@Override
		protected VoxelShape shelfLeftShape()
		{
			return VoxelShaper.or(
					box(0D, 13D, 0D, 16D, 16D, 16D),
					box(13D, 3D, 12D, 16D, 6D, 16D),
					box(13D, 9D, 10D, 16D, 13D, 16D),
					box(13D, 11D, 3D, 16D, 13D, 10D),
					box(13D, 10D, 0D, 16D, 13D, 3D),
					box(13D, 6D, 13D, 16D, 9D, 16D)
			);
		}

		@Override
		protected VoxelShape shelfRightShape()
		{
			return VoxelShaper.or(
					box(0D, 3D, 12D, 3D, 6D, 16D),
					box(0D, 6D, 13D, 3D, 9D, 16D),
					box(0D, 9D, 10D, 3D, 13D, 16D),
					box(0D, 11D, 3D, 3D, 13D, 10D),
					box(0D, 10D, 0D, 3D, 13D, 3D),
					box(0D, 13D, 0D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape sofaSingleShape()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 4D, 2D, 4D),
					box(1D, 0D, 12D, 4D, 2D, 15D),
					box(12D, 0D, 12D, 15D, 2D, 15D),
					box(12D, 0D, 1D, 15D, 2D, 4D),
					box(0D, 2D, 0D, 16D, 6D, 16D),
					box(13D, 6D, 0D, 16D, 10D, 13D),
					box(0D, 6D, 0D, 3D, 10D, 13D),
					box(0D, 6D, 13D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape sofaCenterShape()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 4D, 2D, 4D),
					box(1D, 0D, 12D, 4D, 2D, 15D),
					box(12D, 0D, 12D, 15D, 2D, 15D),
					box(12D, 0D, 1D, 15D, 2D, 4D),
					box(0D, 2D, 0D, 16D, 6D, 16D),
					box(0D, 6D, 13D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape sofaLeftShape()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 4D, 2D, 4D),
					box(1D, 0D, 12D, 4D, 2D, 15D),
					box(12D, 0D, 12D, 15D, 2D, 15D),
					box(12D, 0D, 1D, 15D, 2D, 4D),
					box(0D, 2D, 0D, 16D, 6D, 16D),
					box(13D, 6D, 0D, 16D, 10D, 13D),
					box(0D, 6D, 13D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape sofaRightShape()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 4D, 2D, 4D),
					box(1D, 0D, 12D, 4D, 2D, 15D),
					box(12D, 0D, 12D, 15D, 2D, 15D),
					box(12D, 0D, 1D, 15D, 2D, 4D),
					box(0D, 2D, 0D, 16D, 6D, 16D),
					box(0D, 6D, 13D, 16D, 16D, 16D),
					box(0D, 6D, 0D, 3D, 10D, 13D)
			);
		}

		@Override
		protected VoxelShape sofaCornerShape()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 4D, 2D, 4D),
					box(1D, 0D, 12D, 4D, 2D, 15D),
					box(12D, 0D, 12D, 15D, 2D, 15D),
					box(12D, 0D, 1D, 15D, 2D, 4D),
					box(13D, 6D, 0D, 16D, 16D, 16D),
					box(0D, 6D, 13D, 13D, 16D, 16D),
					box(0D, 2D, 0D, 16D, 6D, 16D)
			);
		}

		@Override
		protected VoxelShape stoolShape()
		{
			return VoxelShaper.or(
					box(2D, 0D, 2D, 5D, 4D, 5D),
					box(2D, 0D, 11D, 5D, 4D, 14D),
					box(11D, 0D, 11D, 14D, 4D, 14D),
					box(11D, 0D, 2D, 14D, 4D, 5D),
					box(1D, 4D, 1D, 15D, 7D, 15D)
			);
		}

		@Override
		protected VoxelShape tableLargeShape()
		{
			return VoxelShaper.or(
					box(-15D, 0D, 1D, -12D, 2D, 4D),
					box(12D, 0D, 1D, 15D, 2D, 4D),
					box(12D, 0D, 28D, 15D, 2D, 31D),
					box(-15D, 0D, 28D, -12D, 2D, 31D),
					box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
					box(-14.5D, 2D, 28.5D, -12.5D, 13D, 30.5D),
					box(12.5D, 2D, 28.5D, 14.5D, 13D, 30.5D),
					box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
					box(-16D, 13D, 0D, 16D, 16D, 32D)
			);
		}

		@Override
		protected VoxelShape tableSmallShape()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 4D, 2D, 4D),
					box(1D, 0D, 12D, 4D, 2D, 15D),
					box(12D, 0D, 12D, 15D, 2D, 15D),
					box(12D, 0D, 1D, 15D, 2D, 4D),
					box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
					box(1.5D, 2D, 1.5D, 3.5D, 13D, 3.5D),
					box(1.5D, 2D, 12.5D, 3.5D, 13, 14.5D),
					box(12.5D, 2D, 12.5D, 14.5D, 13D, 14.5D),
					box(0D, 13D, 0D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape tableWideShape()
		{
			return VoxelShaper.or(
					box(12D, 0D, 1D, 15D, 2D, 4D),
					box(-15D, 0D, 1D, -12D, 2D, 4D),
					box(-15D, 0D, 12D, -12D, 2D, 15D),
					box(12D, 0D, 12D, 15D, 2D, 15D),
					box(12.5D, 2D, 12.5D, 14.5D, 13D, 14.5D),
					box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
					box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
					box(-14.5D, 2D, 12.5D, -12.5D, 13D, 14.5D),
					box(-16D, 13D, 0D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape wallLightShape()
		{
			return VoxelShaper.or(
					box(6D, 1D, 15D, 10D, 3D, 16D),
					box(5D, 3D, 15D, 11D, 12D, 16D),
					box(6D, 12D, 15D, 10D, 14D, 16D),
					box(7D, 3.5D, 14D, 9D, 5.5D, 15D),
					box(4.25D, 2.5D, 10.5D, 11.75D, 11.5D, 14D)
			);
		}

		@Override
		protected VoxelShape wardrobeTopShape()
		{
			return VoxelShaper.or(
					box(-15D, 0D, 1D, 15D, 9D, 15D),
					box(-16D, 9D, 0D, 16D, 11D, 16D)
			);
		}

		@Override
		protected VoxelShape wardrobeBottomShape()
		{
			return VoxelShaper.or(
					box(-16D, 0D, 0D, -12D, 3D, 4D),
					box(-16D, 0D, 12D, -12D, 3D, 16D),
					box(12D, 0D, 12D, 16D, 3D, 16D),
					box(12D, 0D, 0D, 16D, 3D, 4D),
					box(-15D, 1D, 1D, 15D, 29D, 15D),
					box(-16D, 29D, 0D, 16D, 32D, 16D)
			);
		}

		@Override
		protected VoxelShape doorDoubleShape()
		{
			return VoxelShaper.or(
					box(0, 0, 0, 16, 1, 3),
					box(14, 1, 0, 16, 32, 3),
					box(0, 31, 0, 14, 32, 3),
					box(0, 0, 0, 1, 31, 3),
					box(6, 30, 0, 14, 31, 3),
					box(8, 29, 0, 14, 30, 3),
					box(10, 27, 0, 14, 29, 3),
					box(11, 25, 0, 14, 27, 3),
					box(12, 22, 0, 14, 25, 3),
					box(13, 17, 0, 14, 22, 3),
					box(1, 1, 0.5, 14, 31, 2.5)
			);
		}

		@Override
		protected VoxelShape doorSingleShape()
		{
			return VoxelShaper.or(
					box(0, 0, 0, 16, 1, 3),
					box(14, 1, 0, 16, 32, 3),
					box(0, 30, 0, 14, 32, 3),
					box(0, 1, 0, 1, 30, 3),
					box(1, 1, 0.5, 14, 30, 2.5)
			);
		}

		@Override
		protected VoxelShape counterSingleShape()
		{
			return VoxelShaper.or(
					box(0, 0, 3, 16, 13, 16),
					box(0, 13, 0, 16, 16, 16),
					box(1, 1, 2, 15, 12, 3)
			);
		}

		@Override
		protected VoxelShape counterCornerShape()
		{
			return VoxelShaper.or(
					box(0, 0, 3, 16, 13, 16),
					box(0, 13, 0, 16, 16, 16),
					box(1, 1, 2, 15, 12, 3)
			);
		}

		@Override
		protected VoxelShape ovenShape()
		{
			return VoxelShaper.or(
					Block.box(1, 0, 1, 4, 2, 4),
					Block.box(1, 0, 12, 4, 2, 15),
					Block.box(12, 0, 12, 15, 2, 15),
					Block.box(12, 0, 1, 15, 2, 4),
					Block.box(0, 2, 0, 16, 4, 16),
					Block.box(0, 14, 0, 16, 16, 16),
					Block.box(1, 4, 1, 15, 14, 15),
					Block.box(3, 5, 0, 13, 13, 1)
			);
		}
	}

	private static final class Bone extends HitBoxes
	{
		@Override
		protected VoxelShape bedDoubleShape()
		{
			return VoxelShaper.or(
					box(-16, 0, 0, -12, 2, 4),
					box(-16, 0, 28, -12, 2, 32),
					box(12, 0, 28, 16, 2, 32),
					box(12, 0, 0, 16, 2, 4),
					box(12, 12, 28, 16, 14, 32),
					box(-16, 12, 28, -12, 14, 32),
					box(12, 12, 0, 16, 14, 4),
					box(-16, 12, 0, -12, 14, 4),
					box(13, 2, 29, 15, 12, 31),
					box(-15, 2, 29, -13, 12, 31),
					box(-15, 2, 1, -13, 12, 3),
					box(13, 2, 1, 15, 12, 3),
					box(-13, 4, 1, 13, 13, 3),
					box(-13, 4, 29, 13, 13, 31),
					box(-15, 4, 3, 15, 8, 29)
			);
		}

		@Override
		protected VoxelShape bedSingleShape()
		{
			return VoxelShaper.or(
					box(0, 0, 0, 4, 2, 4),
					box(0, 0, 28, 4, 2, 32),
					box(12, 0, 28, 16, 2, 32),
					box(12, 0, 0, 16, 2, 4),
					box(12, 12, 28, 16, 14, 32),
					box(0, 12, 28, 4, 14, 32),
					box(0, 12, 0, 4, 14, 4),
					box(12, 12, 0, 16, 14, 4),
					box(13, 2, 29, 15, 12, 31),
					box(1, 2, 29, 3, 12, 31),
					box(1, 2, 1, 3, 12, 3),
					box(13, 2, 1, 15, 12, 3),
					box(4, 4, 1, 12, 13, 3),
					box(4, 4, 29, 12, 13, 31),
					box(1, 4, 3, 15, 8, 29)
			);
		}

		@Override
		protected VoxelShape benchShape()
		{
			return VoxelShaper.or(
					box(12, 0, 1, 15, 2, 4),
					box(-15, 0, 1, -12, 2, 4),
					box(-15, 0, 12, -12, 2, 15),
					box(12, 0, 12, 15, 2, 15),
					box(-14.5, 2, 1.5, -12.5, 5, 3.5),
					box(12.5, 2, 1.5, 14.5, 5, 3.5),
					box(12.5, 2, 12.5, 14.5, 5, 14.5),
					box(-14.5, 2, 12.5, -12.5, 5, 14.5),
					box(-15.5, 4.5, -0.5, 15.5, 7.5, 16.5)
			);
		}

		@Override
		protected VoxelShape bookshelfShape()
		{
			return VoxelShaper.or(
					box(11, 0, 1, 15, 2, 5),
					box(-15, 0, 1, -11, 2, 5),
					box(-15, 0, 11, -11, 2, 15),
					box(11, 0, 11, 15, 2, 15),
					box(-15, 3, 1, 15, 5, 15),
					box(-15, 30, 1, 15, 32, 15),
					box(-14, 5, 2, 14, 30, 14),
					box(12, 2, 2, 14, 3, 4),
					box(12, 2, 12, 14, 3, 14),
					box(-14, 2, 12, -12, 3, 14),
					box(-14, 2, 2, -12, 3, 4)
			);
		}

		@Override
		protected VoxelShape chairShape()
		{
			return VoxelShaper.or(
					box(1.5, 0, 1.5, 4.5, 3, 4.5),
					box(1.5, 0, 11.5, 4.5, 3, 14.5),
					box(11.5, 0, 11.5, 14.5, 3, 14.5),
					box(11.5, 0, 1.5, 14.5, 3, 4.5),
					box(2, 3, 12, 4, 7, 14),
					box(2, 3, 2, 4, 7, 4),
					box(12, 3, 2, 14, 7, 4),
					box(12, 3, 12, 14, 7, 14),
					box(1.5, 7, 1.5, 14.5, 9, 14.5),
					box(2, 9, 12, 14, 26, 14)
			);
		}

		@Override
		protected VoxelShape chandelierShape()
		{
			return box(0, 0, 0, 16, 16, 16);
		}

		@Override
		protected VoxelShape chestShape()
		{
			return VoxelShaper.or(
					box(11, 0, 2, 14, 2, 5),
					box(-14, 0, 2, -11, 2, 5),
					box(-14, 0, 11, -11, 2, 14),
					box(11, 0, 11, 14, 2, 14),
					box(11.5, 2, 11.5, 13.5, 11, 13.5),
					box(11.5, 2, 2.5, 13.5, 11, 4.5),
					box(-13.5, 2, 2.5, -11.5, 11, 4.5),
					box(-13.5, 2, 11.5, -11.5, 11, 13.5),
					box(-14, 11, 2, 14, 13, 14),
					box(-14, 3, 2, 14, 5, 14),
					box(-13, 5, 3, 13, 11, 13)
			);
		}

		@Override
		protected VoxelShape cushionShape()
		{
			return box(4, 0, 4, 12, 7, 12);
		}

		@Override
		protected VoxelShape deskLeftShape()
		{
			return VoxelShaper.or(
					box(-15, 0, 1, -12, 2, 4),
					box(-15, 0, 12, -12, 2, 15),
					box(12, 0, 12, 15, 2, 15),
					box(12, 0, 1, 15, 2, 4),
					box(12, 12, 1, 15, 14, 4),
					box(-15, 12, 1, -12, 14, 4),
					box(12, 12, 12, 15, 14, 15),
					box(-15, 12, 12, -12, 14, 15),
					box(12.5, 2, 1.5, 14.5, 12, 3.5),
					box(-14.5, 2, 1.5, -12.5, 12, 3.5),
					box(12.5, 2, 12.5, 14.5, 12, 14.5),
					box(-14.5, 2, 12.5, -12.5, 12, 14.5),
					box(-16, 14, 0, 16, 16, 16),
					box(5, 9, 3, 12, 14, 13)
			);
		}

		@Override
		protected VoxelShape deskRightShape()
		{
			return VoxelShaper.or(
					box(-15, 0, 1, -12, 2, 4),
					box(-15, 0, 12, -12, 2, 15),
					box(12, 0, 12, 15, 2, 15),
					box(12, 0, 1, 15, 2, 4),
					box(12, 12, 1, 15, 14, 4),
					box(-15, 12, 1, -12, 14, 4),
					box(12, 12, 12, 15, 14, 15),
					box(-15, 12, 12, -12, 14, 15),
					box(12.5, 2, 1.5, 14.5, 12, 3.5),
					box(-14.5, 2, 1.5, -12.5, 12, 3.5),
					box(12.5, 2, 12.5, 14.5, 12, 14.5),
					box(-14.5, 2, 12.5, -12.5, 12, 14.5),
					box(-16, 14, 0, 16, 16, 16),
					box(-12, 9, 3, -5, 14, 13)
			);
		}

		@Override
		protected VoxelShape drawerShape()
		{
			return VoxelShaper.or(
					box(1, 0, 1, 4, 2, 4),
					box(1, 12, 1, 4, 14, 4),
					box(1.5, 1, 1.5, 3.5, 12, 3.5),
					box(1, 0, 12, 4, 2, 15),
					box(1, 12, 12, 4, 14, 15),
					box(1.5, 1, 12.5, 3.5, 12, 14.5),
					box(12, 0, 12, 15, 2, 15),
					box(12, 12, 12, 15, 14, 15),
					box(12.5, 1, 12.5, 14.5, 12, 14.5),
					box(12, 0, 1, 15, 2, 4),
					box(12, 12, 1, 15, 14, 4),
					box(12.5, 1, 1.5, 14.5, 12, 3.5),
					box(1.5, 6, 1.5, 14.5, 14, 14.5),
					box(0, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape dresserShape()
		{
			return VoxelShaper.or(
					box(-15, 0, 1, -12, 2, 4),
					box(-15, 12, 1, -12, 14, 4),
					box(-14.5, 1, 1.5, -12.5, 12, 3.5),
					box(-15, 0, 12, -12, 2, 15),
					box(-15, 12, 12, -12, 14, 15),
					box(-14.5, 1, 12.5, -12.5, 12, 14.5),
					box(12, 0, 12, 15, 2, 15),
					box(12, 12, 12, 15, 14, 15),
					box(12.5, 1, 12.5, 14.5, 12, 14.5),
					box(12, 0, 1, 15, 2, 4),
					box(12, 12, 1, 15, 14, 4),
					box(12.5, 1, 1.5, 14.5, 12, 3.5),
					box(-14.5, 6, 1.5, 14.5, 14, 14.5),
					box(-16, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape lockboxShape()
		{
			return VoxelShaper.or(
					box(2, 0, 4, 5, 2, 7),
					box(2, 0, 9, 5, 2, 12),
					box(11, 0, 9, 14, 2, 12),
					box(11, 0, 4, 14, 2, 7),
					box(1, 2, 3, 15, 4, 13),
					box(1.5, 4, 3.5, 14.5, 12.25, 12.5)
			);
		}

		@Override
		protected VoxelShape floorLightShape()
		{
			return VoxelShaper.or(
					box(6, 0, 6, 10, 2, 10),
					box(7, 2, 7, 9, 23, 9),
					box(2.5, 21.75, 6.5, 5.5, 23.75, 9.5),
					box(3.25, 23.75, 7.25, 4.75, 27.75, 8.75),
					box(7.25, 24.75, 7.25, 8.75, 28.75, 8.75),
					box(11.25, 23.75, 7.25, 12.75, 27.75, 8.75),
					box(10.5, 21.75, 6.5, 13.5, 23.75, 9.5),
					box(6.5, 22.75, 6.5, 9.5, 24.75, 9.5),
					box(3, 17.75, 7, 7, 21.75, 9),
					box(9, 17.75, 7, 13, 21.75, 9)
			);
		}

		@Override
		protected VoxelShape paintingSmallShape()
		{
			return VoxelShaper.or(
					box(0, 0, 13, 3, 3, 16),
					box(13, 0, 13, 16, 3, 16),
					box(13, 13, 13, 16, 16, 16),
					box(0, 13, 13, 3, 16, 16),
					box(3, 13, 13.5, 13, 15.5, 15.5),
					box(3, 0.5, 13.5, 13, 3, 15.5),
					box(0.5, 3, 13.5, 15.5, 13, 15.5)
			);
		}

		@Override
		protected VoxelShape paintingWideShape()
		{
			return VoxelShaper.or(
					box(-16, 0, 13, -13, 3, 16),
					box(13, 0, 13, 16, 3, 16),
					box(13, 13, 13, 16, 16, 16),
					box(-16, 13, 13, -13, 16, 16),
					box(-13, 13, 13.5, 13, 15.5, 15.5),
					box(-13, 0.5, 13.5, 13, 3, 15.5),
					box(-15.5, 3, 13.5, 15.5, 13, 15.5)
			);
		}

		@Override
		protected VoxelShape shelfSingleShape()
		{
			return VoxelShaper.or(
					box(0, 6, 13, 3, 8, 16),
					box(13, 6, 13, 16, 8, 16),
					box(0.5, 8, 14, 2.5, 14, 16),
					box(13.5, 8, 14, 15.5, 14, 16),
					box(0, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape shelfCenterShape()
		{
			return box(0, 14, 0, 16, 16, 16);
		}

		@Override
		protected VoxelShape shelfLeftShape()
		{
			return VoxelShaper.or(
					box(13, 6, 13, 16, 8, 16),
					box(13.5, 8, 14, 15.5, 14, 16),
					box(0, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape shelfRightShape()
		{
			return VoxelShaper.or(
					box(0, 6, 13, 3, 8, 16),
					box(0.5, 8, 14, 2.5, 15, 16),
					box(0, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape sofaSingleShape()
		{
			return VoxelShaper.or(
					box(1.5, 0, 1, 4.5, 2, 4),
					box(1.5, 0, 12, 4.5, 2, 15),
					box(11.5, 0, 12, 14.5, 2, 15),
					box(11.5, 0, 1, 14.5, 2, 4),
					box(12, 2, 1.5, 14, 4, 3.5),
					box(2, 2, 1.5, 4, 4, 3.5),
					box(2, 2, 12.5, 4, 4, 14.5),
					box(12, 2, 12.5, 14, 4, 14.5),
					box(12, 3, 3.5, 14, 4, 12.5),
					box(2, 3, 3.5, 4, 4, 12.5),
					box(2, 4, 1.5, 14, 6, 14.5),
					box(2, 6, 12.5, 14, 15, 14.5),
					box(14, 3.5, 12, 16, 15.5, 15),
					box(14, 6, 2, 16, 11.5, 12),
					box(14, 3.5, 1, 16, 6.5, 12),
					box(0, 3.5, 1, 2, 6.5, 12),
					box(0, 3.5, 12, 2, 15.5, 15),
					box(0, 6, 2, 2, 11.5, 12)
			);
		}

		@Override
		protected VoxelShape sofaCenterShape()
		{
			return VoxelShaper.or(
					box(1.5, 0, 1, 4.5, 2, 4),
					box(1.5, 0, 12, 4.5, 2, 15),
					box(11.5, 0, 12, 14.5, 2, 15),
					box(11.5, 0, 1, 14.5, 2, 4),
					box(12, 2, 1.5, 14, 4, 3.5),
					box(2, 2, 1.5, 4, 4, 3.5),
					box(2, 2, 12.5, 4, 4, 14.5),
					box(12, 2, 12.5, 14, 4, 14.5),
					box(12, 3, 3.5, 14, 4, 12.5),
					box(2, 3, 3.5, 4, 4, 12.5),
					box(0, 4, 1.5, 16, 6, 14.5),
					box(0, 6, 12.5, 16, 15, 14.5)
			);
		}

		@Override
		protected VoxelShape sofaLeftShape()
		{
			return VoxelShaper.or(
					box(1.5, 0, 1, 4.5, 2, 4),
					box(1.5, 0, 12, 4.5, 2, 15),
					box(11.5, 0, 12, 14.5, 2, 15),
					box(11.5, 0, 1, 14.5, 2, 4),
					box(12, 2, 1.5, 14, 4, 3.5),
					box(2, 2, 1.5, 4, 4, 3.5),
					box(2, 2, 12.5, 4, 4, 14.5),
					box(12, 2, 12.5, 14, 4, 14.5),
					box(12, 3, 3.5, 14, 4, 12.5),
					box(2, 3, 3.5, 4, 4, 12.5),
					box(0, 4, 1.5, 14, 6, 14.5),
					box(0, 6, 12.5, 14, 15, 14.5),
					box(14, 3.5, 12, 16, 15.5, 15),
					box(14, 6, 2, 16, 11.5, 12),
					box(14, 3.5, 1, 16, 6.5, 12)
			);
		}

		@Override
		protected VoxelShape sofaRightShape()
		{
			return VoxelShaper.or(
					box(11.5, 0, 1, 14.5, 2, 4),
					box(11.5, 0, 12, 14.5, 2, 15),
					box(1.5, 0, 12, 4.5, 2, 15),
					box(1.5, 0, 1, 4.5, 2, 4),
					box(2, 2, 1.5, 4, 4, 3.5),
					box(12, 2, 1.5, 14, 4, 3.5),
					box(12, 2, 12.5, 14, 4, 14.5),
					box(2, 2, 12.5, 4, 4, 14.5),
					box(2, 3, 3.5, 4, 4, 12.5),
					box(12, 3, 3.5, 14, 4, 12.5),
					box(2, 4, 1.5, 16, 6, 14.5),
					box(2, 6, 12.5, 16, 15, 14.5),
					box(0, 3.5, 12, 2, 15.5, 15),
					box(0, 6, 2, 2, 11.5, 12),
					box(0, 3.5, 1, 2, 6.5, 12)
			);
		}

		@Override
		protected VoxelShape sofaCornerShape()
		{
			return VoxelShaper.or(
					box(1, 0, 1, 4, 2, 4),
					box(1, 0, 11.5, 4, 2, 14.5),
					box(11.5, 0, 11.5, 14.5, 2, 14.5),
					box(11.5, 0, 1, 14.5, 2, 4),
					box(12, 2, 1.5, 14, 4, 3.5),
					box(1.5, 2, 1.5, 3.5, 4, 3.5),
					box(1.5, 2, 12, 3.5, 4, 14),
					box(12, 2, 12, 14, 4, 14),
					box(0, 4, 1.5, 1.5, 6, 14.5),
					box(1.5, 4, 0, 14.5, 6, 14.5),
					box(12.5, 6, 0, 14.5, 15, 14.5),
					box(12, 4, 12, 15, 15.5, 15),
					box(0, 6, 12.5, 12, 15, 14.5)
			);
		}

		@Override
		protected VoxelShape stoolShape()
		{
			return VoxelShaper.or(
					box(1, 0, 1, 4, 2, 4),
					box(1, 0, 12, 4, 2, 15),
					box(12, 0, 12, 15, 2, 15),
					box(12, 0, 1, 15, 2, 4),
					box(1.5, 2, 12.5, 3.5, 5, 14.5),
					box(1.5, 2, 1.5, 3.5, 5, 3.5),
					box(12.5, 2, 12.5, 14.5, 5, 14.5),
					box(12.5, 2, 1.5, 14.5, 5, 3.5),
					box(1, 5, 1, 15, 7, 15)
			);
		}

		@Override
		protected VoxelShape tableLargeShape()
		{
			return VoxelShaper.or(
					box(-15, 0, 1, -12, 2, 4),
					box(-15, 0, 28, -12, 2, 31),
					box(12, 0, 28, 15, 2, 31),
					box(12, 0, 1, 15, 2, 4),
					box(12, 12, 1, 15, 14, 4),
					box(-15, 12, 1, -12, 14, 4),
					box(12, 12, 28, 15, 14, 31),
					box(-15, 12, 28, -12, 14, 31),
					box(12.5, 2, 1.5, 14.5, 12, 3.5),
					box(-14.5, 2, 1.5, -12.5, 12, 3.5),
					box(12.5, 2, 28.5, 14.5, 12, 30.5),
					box(-14.5, 2, 28.5, -12.5, 12, 30.5),
					box(-16, 14, 0, 16, 16, 32)
			);
		}

		@Override
		protected VoxelShape tableSmallShape()
		{
			return VoxelShaper.or(
					box(1, 0, 1, 4, 2, 4),
					box(1, 0, 12, 4, 2, 15),
					box(12, 0, 12, 15, 2, 15),
					box(12, 0, 1, 15, 2, 4),
					box(12, 12, 1, 15, 14, 4),
					box(1, 12, 1, 4, 14, 4),
					box(12, 12, 12, 15, 14, 15),
					box(1, 12, 12, 4, 14, 15),
					box(12.5, 2, 1.5, 14.5, 12, 3.5),
					box(1.5, 2, 1.5, 3.5, 12, 3.5),
					box(12.5, 2, 12.5, 14.5, 12, 14.5),
					box(1.5, 2, 12.5, 3.5, 12, 14.5),
					box(0, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape tableWideShape()
		{
			return VoxelShaper.or(
					box(-15, 0, 1, -12, 2, 4),
					box(-15, 0, 12, -12, 2, 15),
					box(12, 0, 12, 15, 2, 15),
					box(12, 0, 1, 15, 2, 4),
					box(12, 12, 1, 15, 14, 4),
					box(-15, 12, 1, -12, 14, 4),
					box(12, 12, 12, 15, 14, 15),
					box(-15, 12, 12, -12, 14, 15),
					box(12.5, 2, 1.5, 14.5, 12, 3.5),
					box(-14.5, 2, 1.5, -12.5, 12, 3.5),
					box(12.5, 2, 12.5, 14.5, 12, 14.5),
					box(-14.5, 2, 12.5, -12.5, 12, 14.5),
					box(-16, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape wallLightShape()
		{
			return box(6, 4, 9, 10, 15, 16);
		}

		@Override
		protected VoxelShape wardrobeTopShape()
		{
			return VoxelShaper.or(
					box(11, 10, 1, 15, 12, 5),
					box(-15, 10, 1, -11, 12, 5),
					box(-15, 10, 11, -11, 12, 15),
					box(11, 10, 11, 15, 12, 15),
					box(11, 7, 11, 15, 9, 15),
					box(11, 7, 1, 15, 9, 5),
					box(-15, 7, 1, -11, 9, 5),
					box(-15, 7, 11, -11, 9, 15),
					box(12, 0, 12, 14, 10, 14),
					box(12, 0, 2, 14, 10, 4),
					box(-14, 0, 2, -12, 10, 4),
					box(-14, 0, 12, -12, 10, 14),
					box(-14, 0, 2, 14, 10.5, 14)
			);
		}

		@Override
		protected VoxelShape wardrobeBottomShape()
		{
			return VoxelShaper.or(
					box(11, 0, 1, 15, 2, 5),
					box(-15, 0, 1, -11, 2, 5),
					box(-15, 0, 11, -11, 2, 15),
					box(11, 0, 11, 15, 2, 15),
					box(12, 2, 12, 14, 30, 14),
					box(-14, 2, 12, -12, 30, 14),
					box(-14, 2, 2, -12, 30, 4),
					box(12, 2, 2, 14, 30, 4),
					box(-15, 3, 1, 15, 5, 15),
					box(-15, 30, 1, 15, 32, 15),
					box(-14, 5, 2, 14, 30, 14)
			);
		}

		@Override
		protected VoxelShape doorDoubleShape()
		{
			return VoxelShaper.or(
					box(0, 0, 0, 16, 3, 3),
					box(0, 29, 0, 16, 32, 3),
					box(14, 2, 0.5, 16, 29, 2.5),
					box(10.5, 2, 0.5, 12.5, 29, 2.5),
					box(7, 2, 0.5, 9, 29, 2.5),
					box(3.5, 2, 0.5, 5.5, 29, 2.5),
					box(0, 2, 0.5, 2, 29, 2.5)
			);
		}

		@Override
		protected VoxelShape doorSingleShape()
		{
			return VoxelShaper.or(
					box(0, 0, 0, 16, 3, 3),
					box(0, 29, 0, 16, 32, 3),
					box(14, 2, 0.5, 16, 29, 2.5),
					box(10.5, 2, 0.5, 12.5, 29, 2.5),
					box(7, 2, 0.5, 9, 29, 2.5),
					box(3.5, 2, 0.5, 5.5, 29, 2.5),
					box(0, 2, 0.5, 2, 29, 2.5)
			);
		}

		@Override
		protected VoxelShape counterSingleShape()
		{
			return VoxelShaper.or(
					box(0, 0, 3, 16, 13, 16),
					box(0, 13, 0, 16, 16, 16),
					box(1, 2, 2, 15, 11, 3)
			);
		}

		@Override
		protected VoxelShape counterCornerShape()
		{
			return VoxelShaper.or(
					box(0, 0, 0, 13, 13, 3),
					box(0, 0, 3, 16, 13, 16),
					box(0, 13, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape ovenShape()
		{
			return VoxelShaper.or(
					Block.box(0, 0, 0, 3, 2, 3),
					Block.box(0, 0, 13, 3, 2, 16),
					Block.box(13, 0, 13, 16, 2, 16),
					Block.box(13, 0, 0, 16, 2, 3),
					Block.box(13, 12, 0, 16, 14, 3),
					Block.box(13, 12, 13, 16, 14, 16),
					Block.box(0, 12, 13, 3, 14, 16),
					Block.box(0, 12, 0, 3, 14, 3),
					Block.box(0.5, 0.5, 0.5, 15.5, 14, 15.5),
					Block.box(0, 14, 0, 16, 16, 16)
			);
		}
	}

	private static final class Royal extends HitBoxes
	{
		@Override
		protected VoxelShape bedDoubleShape()
		{
			return VoxelShaper.or(
					Block.box(-16, 0, 0, -13, 3, 2),
					Block.box(-15, 3, 0, -13, 9, 2),
					Block.box(-16, 9, 0, -13, 12, 2),
					Block.box(-16, 9, 30, -13, 12, 32),
					Block.box(-16, 0, 30, -13, 3, 32),
					Block.box(-15, 3, 30, -13, 9, 32),
					Block.box(13, 9, 30, 16, 12, 32),
					Block.box(13, 0, 30, 16, 3, 32),
					Block.box(13, 3, 30, 15, 9, 32),
					Block.box(13, 9, 0, 16, 12, 2),
					Block.box(13, 0, 0, 16, 3, 2),
					Block.box(13, 3, 0, 15, 9, 2),
					Block.box(-15, 3, 2, 15, 5, 32),
					Block.box(-13, 2, 30, -12, 3, 32),
					Block.box(12, 2, 30, 13, 3, 32),
					Block.box(12, 2, 0, 13, 3, 2),
					Block.box(-13, 2, 0, -12, 3, 2),
					Block.box(-14, 3, 0, 14, 15, 2),
					Block.box(-14, 3, 30, 14, 15, 32),
					Block.box(-14, 3, 2, 14, 8, 30)
			);
		}

		@Override
		protected VoxelShape bedSingleShape()
		{
			return VoxelShaper.or(
					Block.box(0, 0, 0, 3, 3, 2),
					Block.box(1, 3, 0, 3, 9, 2),
					Block.box(0, 9, 0, 3, 12, 2),
					Block.box(0, 9, 30, 3, 12, 32),
					Block.box(0, 0, 30, 3, 3, 32),
					Block.box(1, 3, 30, 3, 9, 32),
					Block.box(13, 9, 30, 16, 12, 32),
					Block.box(13, 0, 30, 16, 3, 32),
					Block.box(13, 3, 30, 15, 9, 32),
					Block.box(13, 9, 0, 16, 12, 2),
					Block.box(13, 0, 0, 16, 3, 2),
					Block.box(13, 3, 0, 15, 9, 2),
					Block.box(1, 3, 2, 15, 5, 32),
					Block.box(3, 2, 30, 4, 3, 32),
					Block.box(12, 2, 30, 13, 3, 32),
					Block.box(12, 2, 0, 13, 3, 2),
					Block.box(3, 2, 0, 4, 3, 2),
					Block.box(2, 3, 0, 14, 15, 2),
					Block.box(2, 3, 30, 14, 15, 32),
					Block.box(2, 3, 2, 14, 8, 30)
			);
		}

		@Override
		protected VoxelShape benchShape()
		{
			return VoxelShaper.or(
					Block.box(-15, 0, 1, -11, 4, 5),
					Block.box(-15, 0, 11, -11, 4, 15),
					Block.box(11, 0, 11, 15, 4, 15),
					Block.box(11, 0, 1, 15, 4, 5),
					Block.box(-14.5, 4, 1.5, 14.5, 6, 14.5)
			);
		}

		@Override
		protected VoxelShape bookshelfShape()
		{
			return VoxelShaper.or(
					Block.box(12, 0, 0, 16, 4, 4),
					Block.box(-16, 0, 0, -12, 4, 4),
					Block.box(-16, 0, 12, -12, 4, 16),
					Block.box(12, 0, 12, 16, 4, 16),
					Block.box(-15, 4, 1, 15, 6, 15),
					Block.box(-15, 30, 1, 15, 32, 15),
					Block.box(12, 6, 2, 14, 30, 4),
					Block.box(12, 6, 12, 14, 30, 14),
					Block.box(-14, 6, 12, -12, 30, 14),
					Block.box(-14, 6, 2, -12, 30, 4),
					Block.box(-13, 6, 4, 13, 30, 13),
					Block.box(-13, 17, 2, 13, 19, 4)
			);
		}

		@Override
		protected VoxelShape chairShape()
		{
			return VoxelShaper.or(
					Block.box(1, 0, 1, 3, 3, 4),
					Block.box(1, 0, 12, 3, 3, 15),
					Block.box(13, 0, 12, 15, 3, 15),
					Block.box(13, 0, 1, 15, 3, 4),
					Block.box(13, 3, 2, 15, 5, 4),
					Block.box(13, 3, 12, 15, 5, 14),
					Block.box(1, 3, 12, 3, 5, 14),
					Block.box(1, 3, 2, 3, 5, 4),
					Block.box(1, 5, 1.5, 15, 7, 14.5),
					Block.box(1, 7, 2, 15, 9, 14),
					Block.box(13, 9, 2, 15, 12, 4),
					Block.box(1, 9, 2, 3, 12, 4),
					Block.box(1, 12, 1, 3, 15, 4),
					Block.box(13, 12, 1, 15, 15, 4),
					Block.box(13, 12, 4, 15, 14, 12),
					Block.box(1, 12, 4, 3, 14, 12),
					Block.box(1, 7, 12, 15, 14, 14),
					Block.box(2, 14, 12, 14, 20, 14),
					Block.box(1, 20, 12, 15, 25, 14),
					Block.box(3, 25, 12, 13, 26, 14),
					Block.box(5, 26, 12, 11, 27, 14)
			);
		}

		@Override
		protected VoxelShape chandelierShape()
		{
			return Block.box(0, 0, 0, 16, 16, 16);
		}

		@Override
		protected VoxelShape chestShape()
		{
			return VoxelShaper.or(
					Block.box(7.5, 0, 0.75, 11.25, 4, 4.5),
					Block.box(-11.25, 0, 0.75, -7.5, 4, 4.5),
					Block.box(-11.25, 0, 11.5, -7.5, 4, 15.25),
					Block.box(7.5, 0, 11.5, 11.25, 4, 15.25),
					Block.box(-12, 4, 1, 12, 6, 15),
					Block.box(-11, 6, 2, 11, 16, 14)
			);
		}

		@Override
		protected VoxelShape cushionShape()
		{
			return VoxelShaper.or(
					Block.box(1, 0, 1, 5, 4, 5),
					Block.box(1, 0, 11, 5, 4, 15),
					Block.box(11, 0, 11, 15, 4, 15),
					Block.box(11, 0, 1, 15, 4, 5),
					Block.box(1.5, 4, 1.5, 14.5, 6, 14.5),
					Block.box(2, 6, 2, 14, 9, 14)
			);
		}

		@Override
		protected VoxelShape deskLeftShape()
		{
			return VoxelShaper.or(
					Block.box(-16, 14, 0, 16, 16, 16),
					Block.box(13, 11, 1, 15, 14, 4),
					Block.box(13, 3, 2, 15, 11, 4),
					Block.box(13, 0, 1, 15, 3, 4),
					Block.box(-15, 0, 1, -13, 3, 4),
					Block.box(-15, 11, 1, -13, 14, 4),
					Block.box(-15, 3, 2, -13, 11, 4),
					Block.box(-15, 0, 12, -13, 3, 15),
					Block.box(-15, 11, 12, -13, 14, 15),
					Block.box(-15, 3, 12, -13, 11, 14),
					Block.box(13, 0, 12, 15, 3, 15),
					Block.box(13, 11, 12, 15, 14, 15),
					Block.box(13, 3, 12, 15, 11, 14),
					Block.box(4, 10, 2, 12, 14, 14),
					Block.box(6, 12, 1, 10, 13, 2)
			);
		}

		@Override
		protected VoxelShape deskRightShape()
		{
			return VoxelShaper.or(
					Block.box(-16, 14, 0, 16, 16, 16),
					Block.box(13, 11, 1, 15, 14, 4),
					Block.box(13, 3, 2, 15, 11, 4),
					Block.box(13, 0, 1, 15, 3, 4),
					Block.box(-15, 0, 1, -13, 3, 4),
					Block.box(-15, 11, 1, -13, 14, 4),
					Block.box(-15, 3, 2, -13, 11, 4),
					Block.box(-15, 0, 12, -13, 3, 15),
					Block.box(-15, 11, 12, -13, 14, 15),
					Block.box(-15, 3, 12, -13, 11, 14),
					Block.box(13, 0, 12, 15, 3, 15),
					Block.box(13, 11, 12, 15, 14, 15),
					Block.box(13, 3, 12, 15, 11, 14),
					Block.box(-12, 10, 2, -4, 14, 14),
					Block.box(-10, 12, 1, -6, 13, 2)
			);
		}

		@Override
		protected VoxelShape drawerShape()
		{
			return VoxelShaper.or(
					Block.box(1, 0, 1, 3, 3, 4),
					Block.box(1, 0, 12, 3, 3, 15),
					Block.box(13, 0, 12, 15, 3, 15),
					Block.box(13, 0, 1, 15, 3, 4),
					Block.box(13, 3, 2, 15, 14, 4),
					Block.box(1, 3, 2, 3, 14, 4),
					Block.box(1, 3, 12, 3, 14, 14),
					Block.box(13, 3, 12, 15, 14, 14),
					Block.box(0.5, 5, 1.5, 15.5, 7, 14.5),
					Block.box(0, 14, 0, 16, 16, 16),
					Block.box(2, 7, 3, 14, 14, 13)
			);
		}

		@Override
		protected VoxelShape dresserShape()
		{
			return VoxelShaper.or(
					box(-15, 0, 1, -13, 3, 4),
					box(-15, 0, 12, -13, 3, 15),
					box(13, 0, 12, 15, 3, 15),
					box(13, 0, 1, 15, 3, 4),
					box(13, 3, 2, 15, 14, 4),
					box(-15, 3, 2, -13, 14, 4),
					box(-15, 3, 12, -13, 14, 14),
					box(13, 3, 12, 15, 14, 14),
					box(-15.5, 5, 1.5, 15.5, 7, 14.5),
					box(-16, 14, 0, 16, 16, 16),
					box(-14, 7, 3, 14, 14, 13)
			);
		}

		@Override
		protected VoxelShape lockboxShape()
		{
			return box(2.5, 0, 3.5, 13.5, 9.25, 12.5);
		}

		@Override
		protected VoxelShape floorLightShape()
		{
			return VoxelShaper.or(
					box(6, 0, 6, 10, 2, 10),
					box(7, 2, 7, 9, 30, 9),
					box(3, 20, 7, 13, 22, 9),
					box(11, 22, 7, 13, 29, 9),
					box(3, 22, 7, 5, 29, 9),
					box(2.5, 24, 6.5, 5.5, 25, 9.5),
					box(6.5, 25, 6.5, 9.5, 26, 9.5),
					box(10.5, 24, 6.5, 13.5, 25, 9.5)
			);
		}

		@Override
		protected VoxelShape paintingSmallShape()
		{
			return VoxelShaper.or(
					box(0, 0, 14, 3, 3, 16),
					box(0, 13, 14, 3, 16, 16),
					box(13, 13, 14, 16, 16, 16),
					box(13, 0, 14, 16, 3, 16),
					box(3, 1, 14, 13, 3, 16),
					box(3, 13, 14, 13, 15, 16),
					box(1, 3, 14, 15, 13, 16)
			);
		}

		@Override
		protected VoxelShape paintingWideShape()
		{
			return VoxelShaper.or(
					box(-16, 0, 14, -13, 3, 16),
					box(-16, 13, 14, -13, 16, 16),
					box(13, 13, 14, 16, 16, 16),
					box(13, 0, 14, 16, 3, 16),
					box(-13, 1, 14, 13, 3, 16),
					box(-13, 13, 14, 13, 15, 16),
					box(-15, 3, 14, 15, 13, 16)
			);
		}

		@Override
		protected VoxelShape shelfSingleShape()
		{
			return VoxelShaper.or(
					box(0, 14, 0, 16, 16, 16),
					box(13, 11, 3, 15, 14, 6),
					box(13, 12, 6, 15, 14, 16),
					box(13, 8, 14, 15, 12, 16),
					box(13, 5, 13, 15, 8, 16),
					box(1, 5, 13, 3, 8, 16),
					box(1, 11, 3, 3, 14, 6),
					box(1, 12, 6, 3, 14, 16),
					box(1, 8, 14, 3, 12, 16)
			);
		}

		@Override
		protected VoxelShape shelfCenterShape()
		{
			return box(0, 14, 0, 16, 16, 16);
		}

		@Override
		protected VoxelShape shelfLeftShape()
		{
			return VoxelShaper.or(
					box(0, 14, 0, 16, 16, 16),
					box(13, 11, 3, 15, 14, 6),
					box(13, 12, 6, 15, 14, 16),
					box(13, 8, 14, 15, 12, 16),
					box(13, 5, 13, 15, 8, 16)
			);
		}

		@Override
		protected VoxelShape shelfRightShape()
		{
			return VoxelShaper.or(
					box(0, 14, 0, 16, 16, 16),
					box(1, 11, 3, 3, 14, 6),
					box(1, 12, 6, 3, 14, 16),
					box(1, 8, 14, 3, 12, 16),
					box(1, 5, 13, 3, 8, 16)
			);
		}

		@Override
		protected VoxelShape sofaSingleShape()
		{
			return VoxelShaper.or(
					box(13, 0, 12, 15, 3, 15),
					box(13, 0, 1, 15, 3, 4),
					box(1, 0, 1, 3, 3, 4),
					box(1, 0, 12, 3, 3, 15),
					box(1, 3, 12, 3, 4, 14),
					box(13, 3, 12, 15, 4, 14),
					box(13, 3, 2, 15, 4, 4),
					box(1, 3, 2, 3, 4, 4),
					box(1, 4, 1.5, 15, 6, 14.5),
					box(13, 6, 2, 15, 9, 12),
					box(1, 6, 2, 3, 9, 12),
					box(1, 6, 12, 15, 13, 14),
					box(2, 13, 12, 14, 15, 14),
					box(4, 15, 12, 12, 16, 14)
			);
		}

		@Override
		protected VoxelShape sofaCenterShape()
		{
			return VoxelShaper.or(
					Block.box(0, 4, 1.5, 16, 6, 14.5), Block.box(0, 6, 12, 16, 16, 14)
			);
		}

		@Override
		protected VoxelShape sofaLeftShape()
		{
			return VoxelShaper.or(
					Block.box(13, 0, 1, 15, 3, 4),
					Block.box(13, 0, 12, 15, 3, 15),
					Block.box(13, 3, 12, 15, 4, 14),
					Block.box(13, 3, 2, 15, 4, 4),
					Block.box(0, 4, 1.5, 15, 6, 14.5),
					Block.box(0, 6, 12, 15, 13, 14),
					Block.box(0, 13, 12, 14, 15, 14),
					Block.box(0, 15, 12, 12, 16, 14),
					Block.box(13, 6, 2, 15, 9, 12)
			);
		}

		@Override
		protected VoxelShape sofaRightShape()
		{
			return VoxelShaper.or(
					Block.box(1, 0, 1, 3, 3, 4),
					Block.box(1, 0, 12, 3, 3, 15),
					Block.box(1, 3, 12, 3, 4, 14),
					Block.box(1, 3, 2, 3, 4, 4),
					Block.box(1, 4, 1.5, 16, 6, 14.5),
					Block.box(1, 6, 12, 16, 13, 14),
					Block.box(2, 13, 12, 16, 15, 14),
					Block.box(4, 15, 12, 16, 16, 14),
					Block.box(1, 6, 2, 3, 9, 12)
			);
		}

		@Override
		protected VoxelShape sofaCornerShape()
		{
			return VoxelShaper.or(
					Block.box(0.5, 0, 0.5, 4.25, 4, 4.25),
					Block.box(11.5, 0, 11.25, 15.25, 4, 15),
					Block.box(12, 0, 1, 15, 3, 3),
					Block.box(12, 3, 1, 14, 4, 3),
					Block.box(1, 3, 12, 3, 4, 14),
					Block.box(1, 0, 12, 3, 3, 15),
					Block.box(0, 4, 1.5, 1.5, 6, 14.5),
					Block.box(1.5, 4, 0, 14.5, 6, 14.5),
					Block.box(12, 6, 0, 14, 16, 14),
					Block.box(0, 6, 12, 12, 16, 14)
			);
		}

		@Override
		protected VoxelShape stoolShape()
		{
			return VoxelShaper.or(
					box(1, 0, 1, 5, 4, 5),
					box(1, 0, 11, 5, 4, 15),
					box(11, 0, 11, 15, 4, 15),
					box(11, 0, 1, 15, 4, 5),
					box(1.5, 4, 1.5, 14.5, 6, 14.5)
			);
		}

		@Override
		protected VoxelShape tableLargeShape()
		{
			return VoxelShaper.or(
					box(-15, 0, 1, -11, 14, 5),
					box(-15, 0, 27, -11, 14, 31),
					box(11, 0, 27, 15, 14, 31),
					box(11, 0, 1, 15, 14, 5),
					box(-16, 14, 0, 16, 16, 32)
			);
		}

		@Override
		protected VoxelShape tableSmallShape()
		{
			return VoxelShaper.or(
					box(1, 0, 1, 5, 14, 5),
					box(1, 0, 11, 5, 14, 15),
					box(11, 0, 11, 15, 14, 15),
					box(11, 0, 1, 15, 14, 5),
					box(0, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape tableWideShape()
		{
			return VoxelShaper.or(
					box(-15, 0, 1, -11, 14, 5),
					box(-15, 0, 11, -11, 14, 15),
					box(11, 0, 11, 15, 14, 15),
					box(11, 0, 1, 15, 14, 5),
					box(-16, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape wallLightShape()
		{
			return box(4.75, 1, 11.75, 11.25, 13, 16);
		}

		@Override
		protected VoxelShape wardrobeTopShape()
		{
			return VoxelShaper.or(
					Block.box(12, 0, 2, 14, 14, 4),
					Block.box(-14, 0, 2, -12, 14, 4),
					Block.box(-14, 0, 12, -12, 14, 14),
					Block.box(12, 0, 12, 14, 14, 14),
					Block.box(-15, 14, 1, 15, 16, 15),
					Block.box(-13, 0, 3, 13, 14, 13)
			);
		}

		@Override
		protected VoxelShape wardrobeBottomShape()
		{
			return VoxelShaper.or(
					box(12, 0, 2, 14, 3, 5),
					box(-14, 0, 2, -12, 3, 5),
					box(-14, 0, 11, -12, 3, 14),
					box(12, 0, 11, 14, 3, 14),
					box(12, 3, 11, 14, 4, 13),
					box(12, 3, 3, 14, 4, 5),
					box(-14, 3, 3, -12, 4, 5),
					box(-14, 3, 11, -12, 4, 13),
					box(-15, 4, 1, 15, 6, 15),
					box(12, 6, 2, 14, 30, 4),
					box(-14, 6, 2, -12, 30, 4),
					box(-14, 6, 12, -12, 30, 14),
					box(12, 6, 12, 14, 30, 14),
					box(-12, 6, 3, 12, 30, 13),
					box(-15, 30, 1, 15, 32, 15)
			);
		}

		@Override
		protected VoxelShape doorDoubleShape()
		{
			return VoxelShaper.or(
					Block.box(14, 0, 0, 16, 32, 3),
					Block.box(13, 25, 0, 14, 32, 3),
					Block.box(12, 27, 0, 13, 32, 3),
					Block.box(9, 28, 0, 12, 32, 3),
					Block.box(5, 29, 0, 9, 32, 3),
					Block.box(0, 30, 0, 5, 32, 3),
					Block.box(0, 0, 0.5, 14, 30, 2.5),
					Block.box(1, 11, -0.5, 5, 15, 3.5)
			);
		}

		@Override
		protected VoxelShape doorSingleShape()
		{
			return VoxelShaper.or(
					Block.box(0, 0, 0, 2, 32, 3),
					Block.box(14, 0, 0, 16, 32, 3),
					Block.box(2, 30, 0, 14, 32, 3),
					Block.box(2, 0, 0.5, 14, 30, 2.5),
					Block.box(3, 11, -0.5, 7, 15, 3.5)
			);
		}

		@Override
		protected VoxelShape counterSingleShape()
		{
			return VoxelShaper.or(
					Block.box(0, 0, 3, 16, 13, 16),
					Block.box(0, 13, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape counterCornerShape()
		{
			return VoxelShaper.or(
					Block.box(0, 0, 0, 13, 13, 3),
					Block.box(0, 0, 3, 16, 13, 16),
					Block.box(0, 13, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape ovenShape()
		{
			return VoxelShaper.or(
					Block.box(0, 0, 0, 3.5, 4, 3.5),
					Block.box(0, 0, 12.5, 3.5, 4, 16),
					Block.box(12.5, 0, 12.5, 16, 4, 16),
					Block.box(12.5, 0, 0, 16, 4, 3.5),
					Block.box(0, 4, 0, 16, 6, 16),
					Block.box(0, 14, 0, 16, 16, 16),
					Block.box(0.5, 6, 0.5, 15.5, 14, 15.5),
					Block.box(2.5, 7, -0.5, 12.5, 13, 0.5)
			);
		}
	}
}