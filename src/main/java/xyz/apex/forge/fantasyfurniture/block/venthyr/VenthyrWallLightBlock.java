package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetWallLightBlock;

import java.util.Random;

public final class VenthyrWallLightBlock extends SetWallLightBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(6D, 1D, 15D, 10D, 3D, 16D),
			box(5D, 3D, 15D, 11D, 12D, 16D),
			box(6D, 12D, 15D, 10D, 14D, 16D),
			box(7D, 3.5D, 14D, 9D, 5.5D, 15D),
			box(4.25D, 2.5D, 10.5D, 11.75D, 11.5D, 14D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public VenthyrWallLightBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = getFacing(blockState);
		return SHAPER.get(facing);
	}

	@Override
	protected void spawnLightParticles(Level level, BlockPos pos, BlockState blockState, double pX, double pY, double pZ, Random rng)
	{
		var x = pX;
		var y = pY + .35D;
		var z = pZ;

		var xOffset = 0D;
		var zOffset = 0D;

		if(supportsFacing(blockState))
		{
			var facing =getFacing(blockState).getOpposite();
			var face = facing.getClockWise();

			xOffset = .15D * face.getStepX();
			zOffset = .15D * face.getStepZ();

			x += .25D * facing.getStepX();
			z += .25D * facing.getStepZ();
		}

		onLightParticle(level, pos, blockState, x + xOffset, y, z + zOffset, rng);
		onLightParticle(level, pos, blockState, x - xOffset, y, z - zOffset, rng);
	}
}
