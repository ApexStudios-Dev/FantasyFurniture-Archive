package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetWallLightBlock;

import java.util.Random;

public final class DunmerWallLightBlock extends SetWallLightBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(4.5, 1, 7.5, 11.5, 2, 14.5),
			box(4.5, 8, 7.5, 11.5, 9, 14.5),
			box(5, 2, 8, 11, 8, 14),
			box(6, 9, 9, 10, 10, 13),
			box(7.5, 10, 10.5, 8.5, 13, 11.5),
			box(7, 13, 9, 9, 15, 15),
			box(6, 12, 15, 10, 16, 16)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public DunmerWallLightBlock(Properties properties)
	{
		super(properties);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos pos, Random rng)
	{
		// NOOP
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = blockState.getValue(FACING);
		return SHAPER.get(facing);
	}
}
