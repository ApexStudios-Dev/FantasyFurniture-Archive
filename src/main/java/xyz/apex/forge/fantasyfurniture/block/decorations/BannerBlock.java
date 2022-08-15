package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseMultiBlock;
import xyz.apex.forge.apexcore.lib.block.MultiBlockPattern;
import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.init.ModPatterns;

import java.util.function.Consumer;

public final class BannerBlock extends BaseMultiBlock
{
	public static final VoxelShape SHAPE = box(0, 0, 14, 16, 32, 16);
	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public BannerBlock(Properties properties)
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
		var shape = SHAPER.get(getFacing(blockState));
		return isMultiBlockOrigin(blockState) ? shape : shape.move(0D, -1D, 0D);
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return ModPatterns.PATTERN_1x1x2;
	}
}