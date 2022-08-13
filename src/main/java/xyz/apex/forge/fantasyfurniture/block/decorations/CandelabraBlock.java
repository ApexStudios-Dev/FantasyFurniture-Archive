package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.function.Consumer;

public final class CandelabraBlock extends BaseBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			Block.box(6, 0, 6, 10, 2, 10),
			Block.box(7, 2, 7, 9, 14, 9),
			Block.box(3, 5, 7, 13, 7, 9),
			Block.box(11, 7, 7, 13, 13, 9),
			Block.box(3, 7, 7, 5, 13, 9),
			Block.box(2.5, 9, 6.5, 5.5, 10, 9.5),
			Block.box(6.5, 10, 6.5, 9.5, 11, 9.5),
			Block.box(10.5, 9, 6.5, 13.5, 10, 9.5)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public CandelabraBlock(Properties properties)
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
		var facing = getFacing(blockState);
		return SHAPER.get(facing);
	}

	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
	{
		if(!isWaterLogged(blockState))
			spawnLightParticles(blockState, level, pos, rng);
	}

	private void spawnLightParticles(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
	{
		if(ModBlocks.ROYAL_CANDELABRA.isIn(blockState))
		{
			var x = pos.getX() + .5D;
			var y = pos.getY() + .5D + .45D + .075D;
			var z = pos.getZ() + .5D;

			var facing = getFacing(blockState).getClockWise();
			var stepX = facing.getStepX();
			var stepZ = facing.getStepZ();

			onLightParticle(level, pos, blockState, x, y, z, rng);
			onLightParticle(level, pos, blockState, x + (stepX * .25D), y - .05D, z + (stepZ * .25D), rng);
			onLightParticle(level, pos, blockState, x - (stepX * .25D), y - .05D, z - (stepZ * .25D), rng);
		}
	}

	private void onLightParticle(Level level, BlockPos pos, BlockState blockState, double pX, double pY, double pZ, RandomSource rng)
	{
		level.addParticle(ParticleTypes.SMALL_FLAME, pX, pY, pZ, 0D, 0D, 0D);
		level.addParticle(ParticleTypes.SMOKE, pX, pY, pZ, 0D, 0D, 0D);
	}
}