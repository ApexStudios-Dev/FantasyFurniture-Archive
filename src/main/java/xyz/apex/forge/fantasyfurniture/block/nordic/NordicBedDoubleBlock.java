package xyz.apex.forge.fantasyfurniture.block.nordic;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.BedBlock;

import javax.annotation.Nullable;
import java.util.List;

public final class NordicBedDoubleBlock extends BedBlock
{
	public static final VoxelShape SHAPE_A = VoxelShaper.or(
			box(-16D, 0D, 0D, 16D, 16D, 2D),
			box(-16D, 0D, 30D, 16D, 16D, 32D),
			box(-16D, 3D, 2D, 16D, 9D, 30D)
	);

	public static final VoxelShape SHAPE_B = VoxelShaper.or(
			box(0D, 0D, 0D, 32D, 16D, 2D),
			box(0D, 0D, 30D, 32D, 16D, 32D),
			box(0D, 3D, 2D, 32D, 9D, 30D)
	);

	public static final VoxelShape SHAPE_C = VoxelShaper.or(
			box(-16D, 0D, -16D, 16D, 16D, -14D),
			box(-16D, 0D, 14D, 16D, 16D, 16D),
			box(-16D, 3D, -14D, 16D, 9D, 14D)
	);

	public static final VoxelShape SHAPE_D = VoxelShaper.or(
			box(0D, 0D, -16D, 32D, 16D, -14D),
			box(0D, 0D, 14D, 32D, 16D, 16D),
			box(0D, 3D, -14D, 32D, 9D, 14D)
	);

	public static final VoxelShaper SHAPER_A = VoxelShaper.forHorizontal(SHAPE_A, Direction.NORTH);
	public static final VoxelShaper SHAPER_B = VoxelShaper.forHorizontal(SHAPE_B, Direction.NORTH);
	public static final VoxelShaper SHAPER_C = VoxelShaper.forHorizontal(SHAPE_C, Direction.NORTH);
	public static final VoxelShaper SHAPER_D = VoxelShaper.forHorizontal(SHAPE_D, Direction.NORTH);

	public static final int HEAD_LEFT = 3;
	public static final int HEAD_RIGHT = 2;
	public NordicBedDoubleBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	protected int getBedHeadMultiBlockIndex(BlockState blockState)
	{
		return pattern.isOrigin(blockState) ? HEAD_LEFT : HEAD_RIGHT;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		int index = pattern.getIndex(blockState);
		VoxelShaper shaper;

		switch(index)
		{
			default:
			case 0:
				shaper = SHAPER_A;
				break;
			case 1:
				shaper = SHAPER_B;
				break;
			case 2:
				shaper = SHAPER_C;
				break;
			case 3:
				shaper = SHAPER_D;
				break;
		}

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

	@Override
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader level, List<ITextComponent> tooltip, ITooltipFlag flag)
	{
		super.appendHoverText(stack, level, tooltip, flag);

		tooltip.add(new TranslationTextComponent(getDescriptionId() + ".warning").withStyle(style -> style.withItalic(true).withColor(TextFormatting.GRAY)));
	}
}
