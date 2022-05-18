package xyz.apex.forge.fantasyfurniture.block.nordic;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.BedBlock;

public final class NordicBedSingleBlock extends BedBlock
{
	public static final VoxelShape SHAPE_A = VoxelShaper.or(
			box(0D, 0D, 0D, 16D, 14D, 2D),
			box(0D, 0D, 30D, 16D, 14D, 32D),
			box(0D, 3D, 2D, 16D, 5D, 30D),
			box(1D, 5D, 2D, 15D, 8D, 30D)
	);

	public static final VoxelShape SHAPE_B = VoxelShaper.or(
			box(0D, 0D, -16D, 16D, 14D, -14D),
			box(0D, 0D, 14D, 16D, 14D, 16D),
			box(0D, 3D, -14D, 16D, 5D, 14D),
			box(1D, 5D, -14D, 15D, 8D, 14D)
	);

	public static final VoxelShaper SHAPER_A = VoxelShaper.forHorizontal(SHAPE_A, Direction.NORTH);
	public static final VoxelShaper SHAPER_B = VoxelShaper.forHorizontal(SHAPE_B, Direction.NORTH);

	public NordicBedSingleBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	protected int getBedHeadMultiBlockIndex(BlockState blockState)
	{
		return 1;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		VoxelShaper shaper = pattern.isOrigin(blockState) ? SHAPER_A : SHAPER_B;
		return shaper.get(facing);
	}

	@Override
	public void onFixBedRotations(LivingEntity entity, MatrixStack pose)
	{
		pose.scale(.9F, .9F, .9F);
		pose.mulPose(Vector3f.XP.rotationDegrees(180));
		pose.mulPose(Vector3f.ZP.rotationDegrees(180));
		pose.translate(3.25D, 0D, 0D);
	}
}
