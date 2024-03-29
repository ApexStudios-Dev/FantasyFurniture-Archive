package xyz.apex.forge.fantasyfurniture.common.block.decorations;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.BaseMultiBlock;
import xyz.apex.forge.apexcore.lib.block.MultiBlockPattern;
import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.AllPatterns;

import java.util.function.Consumer;

public final class TeaSetBlock extends BaseMultiBlock
{
	public static final VoxelShape SHAPE = box(-11D, 0D, 2D, 11D, 9D, 14D);
	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public TeaSetBlock(Properties properties)
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
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = SHAPER.get(facing);

		if(!isMultiBlockOrigin(blockState))
		{
			var other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return AllPatterns.PATTERN_1x2x1;
	}
}
