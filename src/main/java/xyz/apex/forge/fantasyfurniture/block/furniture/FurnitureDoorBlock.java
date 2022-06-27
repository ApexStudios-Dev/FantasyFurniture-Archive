package xyz.apex.forge.fantasyfurniture.block.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

public class FurnitureDoorBlock extends DoorBlock
{
	public FurnitureDoorBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public RenderShape getRenderShape(BlockState blockState)
	{
		var half = blockState.getOptionalValue(HALF).orElse(DoubleBlockHalf.LOWER);
		return half == DoubleBlockHalf.LOWER ? RenderShape.MODEL : RenderShape.INVISIBLE;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_DOOR_DOUBLE.has(blockState))
			return HitBoxes.NORDIC.doorDouble(this, blockState);
		else if(ModBlocks.NORDIC_DOOR_SINGLE.has(blockState))
			return HitBoxes.NORDIC.doorSingle(this, blockState);
		else if(ModBlocks.DUNMER_DOOR_DOUBLE.has(blockState))
			return HitBoxes.DUNMER.doorDouble(this, blockState);
		else if(ModBlocks.DUNMER_DOOR_SINGLE.has(blockState))
			return HitBoxes.DUNMER.doorSingle(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}
}