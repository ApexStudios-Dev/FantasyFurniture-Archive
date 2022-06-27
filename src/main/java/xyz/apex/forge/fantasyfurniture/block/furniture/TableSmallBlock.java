package xyz.apex.forge.fantasyfurniture.block.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.function.Consumer;

public class TableSmallBlock extends BaseBlock
{
	public TableSmallBlock(Properties properties)
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
		if(ModBlocks.NORDIC_TABLE_SMALL.has(blockState))
			return HitBoxes.NORDIC.tableSmall(this, blockState);
		else if(ModBlocks.DUNMER_TABLE_SMALL.has(blockState))
			return HitBoxes.DUNMER.tableSmall(this, blockState);
		else if(ModBlocks.VENTHYR_TABLE_SMALL.has(blockState) || ModBlocks.VENTHYR_TABLE_SMALL_FANCY.has(blockState))
			return HitBoxes.VENTHYR.tableSmall(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}
}