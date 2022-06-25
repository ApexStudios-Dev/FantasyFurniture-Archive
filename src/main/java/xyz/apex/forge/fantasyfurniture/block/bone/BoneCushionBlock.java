package xyz.apex.forge.fantasyfurniture.block.bone;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetCushionBlock;

public final class BoneCushionBlock extends SetCushionBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(2D, 0D, 2D, 4D, 2D, 4D),
			box(2D, 0D, 12D, 4D, 2D, 14D),
			box(12D, 0D, 12D, 14D, 2D, 14D),
			box(12D, 0D, 2D, 14D, 2D, 4D),
			box(2D, 5D, 2.25D, 14D, 7D, 13.75D),
			box(1.75D, 4D, 2D, 14.25D, 5D, 14D),
			box(2D, 2D, 2.5D, 4D, 4D, 4.5D),
			box(12D, 2D, 2.5D, 14D, 4D, 4.5D),
			box(12D, 2D, 11.5D, 14D, 4D, 13.5D),
			box(2D, 2D, 11.5D, 4D, 4D, 13.5D),
			box(2.5D, 2.5D, 4.5D, 3.5D, 3.5D, 11.5D),
			box(12.5D, 2.5D, 4.5D, 13.5D, 3.5D, 11.5D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public BoneCushionBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		return SHAPER.get(facing);
	}
}
