package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import xyz.apex.forge.apexcore.revamp.block.BaseBlock;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class SetPaintingSmallBlock extends BaseBlock
{
	public SetPaintingSmallBlock(Properties properties)
	{
		super(properties);
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
