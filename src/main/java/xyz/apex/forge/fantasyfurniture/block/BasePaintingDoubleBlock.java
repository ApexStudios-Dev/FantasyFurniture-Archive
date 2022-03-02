package xyz.apex.forge.fantasyfurniture.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BasePaintingDoubleBlock extends BasePaintingBlock
{
	public static final EnumProperty<Type> TYPE = EnumProperty.create("type", Type.class);

	public BasePaintingDoubleBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction facing, BlockState facingBlockState, IWorld level, BlockPos pos, BlockPos facingPos)
	{
		Direction paintingFacing = blockState.getValue(FACING);
		Type type = blockState.getValue(TYPE);

		BlockPos placementPos = getPlacementPos(pos, type, paintingFacing);
		BlockState otherBlockState = level.getBlockState(placementPos);

		if(!otherBlockState.is(this))
			return Blocks.AIR.defaultBlockState();
		if(!otherBlockState.hasProperty(TYPE))
			return Blocks.AIR.defaultBlockState();
		if(otherBlockState.getValue(TYPE) != type.other())
			return Blocks.AIR.defaultBlockState();

		return blockState;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		World level = ctx.getLevel();
		BlockPos pos = ctx.getClickedPos();

		for(Direction facing : ctx.getNearestLookingDirections())
		{
			if(canSupportDoublePainting(level, pos, Type.MAIN, facing))
				return defaultBlockState().setValue(FACING, getFacingForPlacement(facing)).setValue(TYPE, Type.MAIN);
		}

		return null;
	}

	@Override
	public boolean canSurvive(BlockState blockState, IWorldReader level, BlockPos pos)
	{
		Type type = blockState.getValue(TYPE);
		return canSupportDoublePainting(level, pos, type);
	}

	@Override
	public void setPlacedBy(World level, BlockPos pos, BlockState blockState, @Nullable LivingEntity placer, ItemStack stack)
	{
		Direction facing = blockState.getValue(FACING);
		BlockPos placementPos = getPlacementPos(pos, Type.MAIN, facing);
		level.setBlockAndUpdate(placementPos, blockState.setValue(TYPE, Type.OTHER));
	}

	@Override
	public void playerWillDestroy(World level, BlockPos pos, BlockState blockState, PlayerEntity player)
	{
		if(!level.isClientSide && player.isCreative())
		{
			Direction facing = blockState.getValue(FACING);
			Type type = blockState.getValue(TYPE);
			BlockPos otherPos = getPlacementPos(pos, type, facing);
			BlockState otherBlockState = level.getBlockState(otherPos);

			if(otherBlockState.is(this) && otherBlockState.getValue(TYPE) == type.other())
			{
				if(type == Type.OTHER)
					level.setBlock(otherPos, Blocks.AIR.defaultBlockState(), 35);
			}
		}

		super.playerWillDestroy(level, pos, blockState, player);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(TYPE);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public BlockRenderType getRenderShape(BlockState blockState)
	{
		return blockState.getValue(TYPE) == Type.MAIN ? BlockRenderType.MODEL : BlockRenderType.INVISIBLE;
	}

	public static Direction getFacingForPlacement(Direction facing)
	{
		return facing.getOpposite();
	}

	public static boolean canSupportDoublePainting(IBlockReader level, BlockPos pos, Type type)
	{
		for(Direction facing : Direction.Plane.HORIZONTAL)
		{
			if(canSupportDoublePainting(level, pos, type, facing))
				return true;
		}

		return false;
	}

	public static boolean canSupportDoublePainting(IBlockReader level, BlockPos pos, Type type, Direction facing)
	{
		if(!canSupportPainting(level, pos, facing))
			return false;

		Direction facingForPlacement = getFacingForPlacement(facing);
		BlockPos placementPos = getPlacementPos(pos, type, facingForPlacement);
		return canSupportPainting(level, placementPos, facing);
	}

	public static BlockPos getPlacementPos(BlockPos pos, Type type, Direction facingForPlacement)
	{
		Direction placementFacing = (type == Type.MAIN ? facingForPlacement.getCounterClockWise() : facingForPlacement.getClockWise());
		return pos.relative(placementFacing);
	}

	public enum Type implements IStringSerializable
	{
		MAIN("main"),
		OTHER("other");

		private final String serializedName;

		Type(String serializedName)
		{
			this.serializedName = serializedName;
		}

		public Type other()
		{
			return this == MAIN ? OTHER : MAIN;
		}

		@Override
		public String getSerializedName()
		{
			return serializedName;
		}
	}
}
