package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class SetPaintingSmallBlock extends BaseBlock implements IFurnitureSetBlock
{
	protected final ModBlocks furnitureSet;

	public SetPaintingSmallBlock(ModBlocks furnitureSet, Properties properties)
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
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(WATERLOGGED);
		consumer.accept(FACING_4_WAY);
	}

	@Nullable
	@Override
	protected BlockState modifyPlacementState(BlockState placementBlockState, BlockPlaceContext ctx)
	{
		placementBlockState = super.modifyPlacementState(placementBlockState, ctx);

		if(placementBlockState == null)
			return null;

		var level = ctx.getLevel();
		var pos = ctx.getClickedPos();

		for(var facing : ctx.getNearestLookingDirections())
		{
			var opposite = facing.getOpposite();

			if(canSupportPainting(level, pos, facing))
				return BaseBlock.setFacing(placementBlockState, opposite);
		}

		return null;

	}

	@Override
	public boolean canSurvive(BlockState blockState, LevelReader level, BlockPos pos)
	{
		return canSupportPainting(level, pos);
	}

	@Override
	public final VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		return furnitureSet.hitBoxes.paintingSmall(this, blockState);
	}

	public static boolean canSupportPainting(LevelReader level, BlockPos pos)
	{
		for(var facing : Direction.Plane.HORIZONTAL)
		{
			if(canSupportPainting(level, pos, facing))
				return true;
		}

		return false;
	}

	public static boolean canSupportPainting(LevelReader level, BlockPos pos, Direction facing)
	{
		if(!facing.getAxis().isHorizontal())
			return false;

		return true;
		/*BlockPos facingPos = pos.relative(facing);
		return level.getBlockState(facingPos).isFaceSturdy(level, facingPos, facing.getOpposite(), BlockVoxelShape.RIGID);*/
	}
}
