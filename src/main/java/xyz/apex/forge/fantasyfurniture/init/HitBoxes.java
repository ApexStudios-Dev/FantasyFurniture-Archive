package xyz.apex.forge.fantasyfurniture.init;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.apexcore.revamp.block.WallLightBlock;
import xyz.apex.forge.fantasyfurniture.block.furniture.*;

import static net.minecraft.world.level.block.Block.box;

public abstract class HitBoxes
{
	public static final HitBoxes NORDIC = new Nordic();

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
	}
}