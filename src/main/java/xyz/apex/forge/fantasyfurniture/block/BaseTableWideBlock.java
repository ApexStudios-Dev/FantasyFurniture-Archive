package xyz.apex.forge.fantasyfurniture.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Locale;

public class BaseTableWideBlock extends SimpleFourWayBlock
{
	public static final EnumProperty<Type> TYPE = EnumProperty.create("type", Type.class);

	public BaseTableWideBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction facing, BlockState facingBlockState, IWorld level, BlockPos currentPos, BlockPos facingPos)
	{
		if(facing == getConnectedDirection(blockState))
			return facingBlockState.is(this) && facingBlockState.getValue(TYPE) != blockState.getValue(TYPE) ? blockState : Blocks.AIR.defaultBlockState();
		else
			return super.updateShape(blockState, facing, facingBlockState, level, currentPos, facingPos);
	}

	@SuppressWarnings("ConstantConditions") // super is non null
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		return defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getClockWise()).setValue(TYPE, Type.MAIN);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(TYPE);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public boolean canSurvive(BlockState blockState, IWorldReader level, BlockPos pos)
	{
		if(blockState.getValue(TYPE) != Type.OTHER)
			return super.canSurvive(blockState, level, pos);
		else
		{
			Direction connected = getConnectedDirection(blockState);
			BlockState otherBlockState = level.getBlockState(pos.relative(connected));

			if(otherBlockState.getBlock() != this)
				return super.canSurvive(blockState, level, pos);

			return blockState.is(this) && blockState.getValue(TYPE) == Type.OTHER;
		}
	}

	@Override
	public void playerWillDestroy(World level, BlockPos pos, BlockState blockState, PlayerEntity player)
	{
		if(!level.isClientSide)
		{
			if(player.isCreative())
				preventCreativeDropFromBottomPart(level, pos, blockState, player);
			else
				dropResources(blockState, level, pos, null, player, player.getMainHandItem());
		}

		super.playerWillDestroy(level, pos, blockState, player);
	}

	@Override
	public void playerDestroy(World level, PlayerEntity player, BlockPos pos, BlockState blockState, @Nullable TileEntity tileEntity, ItemStack stack)
	{
		super.playerDestroy(level, player, pos, Blocks.AIR.defaultBlockState(), tileEntity, stack);
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
	}

	@Override
	public BlockRenderType getRenderShape(BlockState blockState)
	{
		return blockState.getValue(TYPE) == Type.MAIN ? BlockRenderType.INVISIBLE : BlockRenderType.MODEL;
	}

	@Override
	public void setPlacedBy(World level, BlockPos pos, BlockState blockState, @Nullable LivingEntity placer, ItemStack stack)
	{
		super.setPlacedBy(level, pos, blockState, placer, stack);

		if(!level.isClientSide)
		{
			Direction connectedDirection = getConnectedDirection(blockState);
			BlockPos relativePos = pos.relative(connectedDirection);
			level.setBlock(relativePos, blockState.setValue(TYPE, Type.OTHER), 3);
			level.blockUpdated(pos, Blocks.AIR);
			blockState.updateNeighbourShapes(level, pos, 3);
		}
	}

	@Override
	public long getSeed(BlockState blockState, BlockPos pos)
	{
		Direction connectedDirection = getConnectedDirection(blockState);
		BlockPos relativePos = pos.relative(connectedDirection, blockState.getValue(TYPE) == Type.OTHER ? 0 : 1);
		return MathHelper.getSeed(relativePos.getX(), pos.getY(), relativePos.getZ());
	}

	@Override
	public boolean isPathfindable(BlockState blockState, IBlockReader level, BlockPos pos, PathType pathType)
	{
		return false;
	}

	public static Direction getConnectedDirection(BlockState blockState)
	{
		Direction facing = blockState.getValue(FACING);
		return blockState.getValue(TYPE) == Type.OTHER ? facing.getOpposite() : facing;
	}

	protected static void preventCreativeDropFromBottomPart(World level, BlockPos pos, BlockState blockState, PlayerEntity player)
	{
		Type type = blockState.getValue(TYPE);

		if(type == Type.OTHER)
		{
			Direction connectedDirection = getConnectedDirection(blockState);
			BlockPos otherPos = pos.relative(connectedDirection);
			BlockState otherBlockState = level.getBlockState(otherPos);

			if(otherBlockState.getBlock() == blockState.getBlock() && otherBlockState.getValue(TYPE) == Type.MAIN)
			{
				level.setBlock(otherPos, Blocks.AIR.defaultBlockState(), 35);
				level.levelEvent(player, 2001, otherPos, Block.getId(otherBlockState));
			}
		}
	}

	public enum Type implements IStringSerializable
	{
		MAIN,
		OTHER;

		@Override
		public String getSerializedName()
		{
			return name().toLowerCase(Locale.ROOT);
		}
	}
}
