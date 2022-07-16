package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;

import java.util.function.Consumer;

public final class FoodBlock extends BaseBlock
{
	public static final VoxelShape SHAPE = box(2D, 0D, 2D, 14D, 5D, 14D);

	public FoodBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.NORMAL;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		return SHAPE;
	}
}