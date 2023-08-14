package xyz.apex.forge.fantasyfurniture.common.block.decorations;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IForgeShearable;
import xyz.apex.forge.apexcore.lib.block.BaseMultiBlock;
import xyz.apex.forge.apexcore.lib.block.MultiBlockPattern;
import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.AllPatterns;

import java.util.function.Consumer;

public final class SpiderWebMultiBlock extends BaseMultiBlock implements IForgeShearable
{
	public static final VoxelShape SHAPE = Block.box(-16, 0, 14, 16, 16, 16);
	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public SpiderWebMultiBlock(Properties properties)
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
	public void entityInside(BlockState blockState, Level level, BlockPos pos, Entity entity)
	{
		entity.makeStuckInBlock(blockState, new Vec3(.25D, .05D, .25D));
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = getFacing(blockState);
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
