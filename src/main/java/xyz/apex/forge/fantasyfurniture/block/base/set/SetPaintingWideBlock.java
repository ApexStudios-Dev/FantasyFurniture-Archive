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
import xyz.apex.forge.apexcore.revamp.block.BaseMultiBlock;
import xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.init.FFPatterns;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class SetPaintingWideBlock extends BaseMultiBlock implements IFurnitureSetBlock
{
	protected final ModBlocks furnitureSet;

	public SetPaintingWideBlock(ModBlocks furnitureSet, Properties properties)
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
	public final VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		return furnitureSet.hitBoxes.paintingWide(this, blockState);
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
		return facing.getAxis().isHorizontal();
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return FFPatterns.PATTERN_1x2x1_PAINTING;
	}
}
