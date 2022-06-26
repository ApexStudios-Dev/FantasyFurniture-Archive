package xyz.apex.forge.fantasyfurniture.init;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.util.Lazy;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.*;

public abstract class HitBoxes
{
	private final Lazy<VoxelShape> bedDoubleShape = Lazy.of(() -> bedDouble().optimize());
	private final Lazy<VoxelShape> bedSingleShape = Lazy.of(() -> bedSingle().optimize());
	private final Lazy<VoxelShape> benchShape = Lazy.of(() -> bench().optimize());
	private final Lazy<VoxelShape> bookshelfShape = Lazy.of(() -> bookshelf().optimize());
	private final Lazy<VoxelShape> chairShape = Lazy.of(() -> chair().optimize());
	private final Lazy<VoxelShape> chandelierShape = Lazy.of(() -> chandelier().optimize());
	private final Lazy<VoxelShape> chestShape = Lazy.of(() -> chest().optimize());
	private final Lazy<VoxelShape> cushionShape = Lazy.of(() -> cushion().optimize());
	private final Lazy<VoxelShape> deskLeftShape = Lazy.of(() -> deskLeft().optimize());
	private final Lazy<VoxelShape> deskRightShape = Lazy.of(() -> deskRight().optimize());
	private final Lazy<VoxelShape> drawerShape = Lazy.of(() -> drawer().optimize());
	private final Lazy<VoxelShape> dresserShape = Lazy.of(() -> dresser().optimize());
	private final Lazy<VoxelShape> floorLightShape = Lazy.of(() -> floorLight().optimize());
	private final Lazy<VoxelShape> lockboxShape = Lazy.of(() -> lockbox().optimize());
	private final Lazy<VoxelShape> paintingSmallShape = Lazy.of(() -> paintingSmall().optimize());
	private final Lazy<VoxelShape> paintingWideShape = Lazy.of(() -> paintingWide().optimize());
	private final Lazy<VoxelShape> shelfShape = Lazy.of(() -> shelf().optimize());
	private final Lazy<VoxelShape> shelfCenterShape = Lazy.of(() -> shelfCenter().optimize());
	private final Lazy<VoxelShape> shelfLeftShape = Lazy.of(() -> shelfLeft().optimize());
	private final Lazy<VoxelShape> shelfRightShape = Lazy.of(() -> shelfRight().optimize());
	private final Lazy<VoxelShape> sofaShape = Lazy.of(() -> sofa().optimize());
	private final Lazy<VoxelShape> sofaCenterShape = Lazy.of(() -> sofaCenter().optimize());
	private final Lazy<VoxelShape> sofaLeftShape = Lazy.of(() -> sofaLeft().optimize());
	private final Lazy<VoxelShape> sofaRightShape = Lazy.of(() -> sofaRight().optimize());
	private final Lazy<VoxelShape> sofaCornerShape = Lazy.of(() -> sofaCorner().optimize());
	private final Lazy<VoxelShape> stoolShape = Lazy.of(() -> stool().optimize());
	private final Lazy<VoxelShape> tableLargeShape = Lazy.of(() -> tableLarge().optimize());
	private final Lazy<VoxelShape> tableSmallShape = Lazy.of(() -> tableSmall().optimize());
	private final Lazy<VoxelShape> tableWideShape = Lazy.of(() -> tableWide().optimize());
	private final Lazy<VoxelShape> wallLightShape = Lazy.of(() -> wallLight().optimize());
	private final Lazy<VoxelShape> wardrobeShape = Lazy.of(() -> wardrobe().optimize());
	private final Lazy<VoxelShape> wardrobeTopperShape = Lazy.of(() -> wardrobeTopper().optimize());

	private final Lazy<VoxelShaper> bedDoubleShaper = Lazy.of(() -> VoxelShaper.forHorizontal(bedDoubleShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> bedSingleShaper = Lazy.of(() -> VoxelShaper.forHorizontal(bedSingleShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> benchShaper = Lazy.of(() -> VoxelShaper.forHorizontal(benchShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> bookshelfShaper = Lazy.of(() -> VoxelShaper.forHorizontal(bookshelfShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> chairShaper = Lazy.of(() -> VoxelShaper.forHorizontal(chairShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> chestShaper = Lazy.of(() -> VoxelShaper.forHorizontal(chestShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> cushionShaper = Lazy.of(() -> VoxelShaper.forHorizontal(cushionShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> deskLeftShaper = Lazy.of(() -> VoxelShaper.forHorizontal(deskLeftShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> deskRightShaper = Lazy.of(() -> VoxelShaper.forHorizontal(deskRightShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> drawerShaper = Lazy.of(() -> VoxelShaper.forHorizontal(drawerShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> dresserShaper = Lazy.of(() -> VoxelShaper.forHorizontal(dresserShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> lockboxShaper = Lazy.of(() -> VoxelShaper.forHorizontal(lockboxShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> paintingSmallShaper = Lazy.of(() -> VoxelShaper.forHorizontal(paintingSmallShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> paintingWideShaper = Lazy.of(() -> VoxelShaper.forHorizontal(paintingWideShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> shelfShaper = Lazy.of(() -> VoxelShaper.forHorizontal(shelfShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> shelfCenterShaper = Lazy.of(() -> VoxelShaper.forHorizontal(shelfCenterShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> shelfLeftShaper = Lazy.of(() -> VoxelShaper.forHorizontal(shelfLeftShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> shelfRightShaper = Lazy.of(() -> VoxelShaper.forHorizontal(shelfRightShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> sofaShaper = Lazy.of(() -> VoxelShaper.forHorizontal(sofaShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> sofaCenterShaper = Lazy.of(() -> VoxelShaper.forHorizontal(sofaCenterShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> sofaLeftShaper = Lazy.of(() -> VoxelShaper.forHorizontal(sofaLeftShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> sofaRightShaper = Lazy.of(() -> VoxelShaper.forHorizontal(sofaRightShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> sofaCornerShaper = Lazy.of(() -> VoxelShaper.forHorizontal(sofaCornerShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> stoolShaper = Lazy.of(() -> VoxelShaper.forHorizontal(stoolShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> tableLargeShaper = Lazy.of(() -> VoxelShaper.forHorizontal(tableLargeShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> tableSmallShaper = Lazy.of(() -> VoxelShaper.forHorizontal(tableSmallShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> tableWideShaper = Lazy.of(() -> VoxelShaper.forHorizontal(tableWideShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> wallLightShaper = Lazy.of(() -> VoxelShaper.forHorizontal(wallLightShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> wardrobeShaper = Lazy.of(() -> VoxelShaper.forHorizontal(wardrobeShape.get(), Direction.NORTH));
	private final Lazy<VoxelShaper> wardrobeTopperShaper = Lazy.of(() -> VoxelShaper.forHorizontal(wardrobeTopperShape.get(), Direction.NORTH));

	public final VoxelShape bedDouble(SetBedDoubleBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = bedDoubleShaper.get().get(facing);
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

	public final VoxelShape bedSingle(SetBedSingleBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = bedSingleShaper.get().get(facing);

		if(!block.isMultiBlockOrigin(blockState))
			shape = shape.move(facing.getStepX(), 0D, facing.getStepZ());

		return shape;
	}

	public final VoxelShape bench(SetBenchBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = benchShaper.get().get(facing);

		if(!block.isMultiBlockOrigin(blockState))
		{
			var other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}

	public final VoxelShape bookshelf(SetBookshelfBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = bookshelfShaper.get().get(facing);
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

	public final VoxelShape chair(SetChairBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = chairShaper.get().get(facing);

		if(!block.isMultiBlockOrigin(blockState))
			shape = shape.move(0D, -1D, 0D);

		return shape;
	}

	public final VoxelShape chandelier(SetChandelierBlock block, BlockState blockState)
	{
		return chandelierShape.get();
	}

	public final VoxelShape chest(SetChestBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = chestShaper.get().get(facing);

		if(!block.isMultiBlockOrigin(blockState))
		{
			var other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}

	public final VoxelShape cushion(SetCushionBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		return cushionShaper.get().get(facing);
	}

	public final VoxelShape desk(SetDeskBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shaper = block.getFurnitureSet().deskLeftBlock.is(block) ? deskLeftShaper.get() : deskRightShaper.get();
		var shape = shaper.get(facing);

		if(!block.isMultiBlockOrigin(blockState))
		{
			var other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}

	public final VoxelShape drawer(SetDrawerBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		return drawerShaper.get().get(facing);
	}

	public final VoxelShape dresser(SetDresserBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = dresserShaper.get().get(facing);

		if(!block.isMultiBlockOrigin(blockState))
		{
			var other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}

	public final VoxelShape floorLight(SetFloorLightBlock block, BlockState blockState)
	{
		var shape = floorLightShape.get();
		return block.isMultiBlockOrigin(blockState) ? shape : shape.move(0D, -1D, 0D);
	}

	public final VoxelShape lockbox(SetLockboxBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		return lockboxShaper.get().get(facing);
	}

	public final VoxelShape paintingSmall(SetPaintingSmallBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		return paintingSmallShaper.get().get(facing);
	}

	public final VoxelShape paintingWide(SetPaintingWideBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = paintingWideShaper.get().get(facing);

		if(!block.isMultiBlockOrigin(blockState))
		{
			var other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}

	public final VoxelShape shelf(SetShelfBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var connectionType = blockState.getValue(SetShelfBlock.CONNECTION_TYPE);

		return switch(connectionType)
				{
					case NONE -> shelfShaper.get().get(facing);
					case LEFT -> shelfLeftShaper.get().get(facing);
					case BOTH -> shelfCenterShaper.get().get(facing);
					case RIGHT -> shelfRightShaper.get().get(facing);
				};
	}

	public final VoxelShape sofa(SetSofaBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var connectionType = blockState.getValue(SetSofaBlock.CONNECTION_TYPE);

		return switch(connectionType)
				{
					case NONE -> sofaShaper.get().get(facing);
					case LEFT -> sofaLeftShaper.get().get(facing);
					case CENTER -> sofaCenterShaper.get().get(facing);
					case RIGHT -> sofaRightShaper.get().get(facing);
					case CORNER -> sofaCornerShaper.get().get(facing);
				};
	}

	public final VoxelShape stool(SetStoolBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		return stoolShaper.get().get(facing);
	}

	public final VoxelShape tableLarge(SetTableLargeBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = tableLargeShaper.get().get(facing);
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

	public final VoxelShape tableSmall(SetTableSmallBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		return tableSmallShaper.get().get(facing);
	}

	public final VoxelShape tableWide(SetTableWideBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = tableWideShaper.get().get(facing);

		if(!block.isMultiBlockOrigin(blockState))
		{
			var opposite = facing.getClockWise();
			shape = shape.move(opposite.getStepX(), 0D, opposite.getStepZ());
		}

		return shape;
	}

	public final VoxelShape wallLight(SetWallLightBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		return wallLightShaper.get().get(facing);
	}

	public final VoxelShape wardrobe(SetWardrobeBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = wardrobeShaper.get().get(facing);
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

	public final VoxelShape wardrobeTopper(SetWardrobeTopperBlock block, BlockState blockState)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = wardrobeTopperShaper.get().get(facing);

		if(!block.isMultiBlockOrigin(blockState))
		{
			var other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}

	protected abstract VoxelShape bedDouble();
	protected abstract VoxelShape bedSingle();
	protected abstract VoxelShape bench();
	protected abstract VoxelShape bookshelf();
	protected abstract VoxelShape chair();
	protected abstract VoxelShape chandelier();
	protected abstract VoxelShape chest();
	protected abstract VoxelShape cushion();
	protected abstract VoxelShape deskLeft();
	protected abstract VoxelShape deskRight();
	protected abstract VoxelShape drawer();
	protected abstract VoxelShape dresser();
	protected abstract VoxelShape floorLight();
	protected abstract VoxelShape lockbox();
	protected abstract VoxelShape paintingSmall();
	protected abstract VoxelShape paintingWide();
	protected abstract VoxelShape shelf();
	protected abstract VoxelShape shelfCenter();
	protected abstract VoxelShape shelfLeft();
	protected abstract VoxelShape shelfRight();
	protected abstract VoxelShape sofa();
	protected abstract VoxelShape sofaCenter();
	protected abstract VoxelShape sofaLeft();
	protected abstract VoxelShape sofaRight();
	protected abstract VoxelShape sofaCorner();
	protected abstract VoxelShape stool();
	protected abstract VoxelShape tableLarge();
	protected abstract VoxelShape tableSmall();
	protected abstract VoxelShape tableWide();
	protected abstract VoxelShape wallLight();
	protected abstract VoxelShape wardrobe();
	protected abstract VoxelShape wardrobeTopper();
}