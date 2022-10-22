package xyz.apex.forge.fantasyfurniture.block.decorations;

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

public final class GravestoneBlock extends BaseBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			Block.box(1.5, 0, 6, 14.5, 2, 10),
			Block.box(2, 2, 6.5, 14, 15, 9.5),
			Block.box(4, 15, 6.5, 12, 16, 9.5)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public GravestoneBlock(Properties properties)
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