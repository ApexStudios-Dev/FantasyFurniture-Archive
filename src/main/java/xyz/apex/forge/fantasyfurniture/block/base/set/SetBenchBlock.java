package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern;
import xyz.apex.forge.apexcore.revamp.block.SeatMultiBlock;
import xyz.apex.forge.fantasyfurniture.init.FFPatterns;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.function.Consumer;

public class SetBenchBlock extends SeatMultiBlock implements IFurnitureSetBlock
{
	protected final ModBlocks furnitureSet;

	public SetBenchBlock(ModBlocks furnitureSet, Properties properties)
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
	public double getSeatYOffset(BlockState blockState)
	{
		return .2D;
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return FFPatterns.PATTERN_1x2x1;
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
	}

	@Override
	public final VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		return furnitureSet.hitBoxes.bench(this, blockState);
	}
}
