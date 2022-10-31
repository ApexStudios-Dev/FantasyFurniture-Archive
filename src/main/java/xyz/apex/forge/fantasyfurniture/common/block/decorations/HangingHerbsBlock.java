package xyz.apex.forge.fantasyfurniture.common.block.decorations;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.VoxelShaper;

import java.util.function.Consumer;

public final class HangingHerbsBlock extends BaseBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			Block.box(2, 14, 12.5, 3, 16, 13.5),
			Block.box(13, 14, 12.5, 14, 16, 13.5),
			Block.box(1, 12, 12, 15, 14, 14),
			Block.box(0.5, 1, 10.5, 15.5, 12, 15.5)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public HangingHerbsBlock(Properties properties)
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

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		return SHAPER.get(facing);
	}
}
