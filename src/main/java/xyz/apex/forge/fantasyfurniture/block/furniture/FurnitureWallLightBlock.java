package xyz.apex.forge.fantasyfurniture.block.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.revamp.block.WallLightBlock;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.Random;
import java.util.function.Consumer;

public class FurnitureWallLightBlock extends WallLightBlock
{
	public FurnitureWallLightBlock(Properties properties)
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
	protected void spawnLightParticles(Level level, BlockPos pos, BlockState blockState, double pX, double pY, double pZ, Random rng)
	{
		if(ModBlocks.NORDIC_WALL_LIGHT.has(blockState))
			super.spawnLightParticles(level, pos, blockState, pX, pY, pZ, rng);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_WALL_LIGHT.has(blockState))
			return HitBoxes.NORDIC.wallLight(this, blockState);
		else if(ModBlocks.DUNMER_WALL_LIGHT.has(blockState))
			return HitBoxes.DUNMER.wallLight(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}
}