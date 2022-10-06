package xyz.apex.forge.fantasyfurniture.block.furniture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.CounterBlockEntity;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModElements;
import xyz.apex.forge.fantasyfurniture.menu.MediumInventoryMenu;

import java.util.List;
import java.util.function.Consumer;

public class CounterBlock extends BaseBlock.WithContainer<CounterBlockEntity, MediumInventoryMenu>
{
	public static final EnumProperty<Connection> CONNECTION = EnumProperty.create("connection", Connection.class);

	public CounterBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(CONNECTION, Connection.SINGLE));
	}

	@Override
	protected BlockEntityType<CounterBlockEntity> getBlockEntityType()
	{
		return ModElements.COUNTER_BLOCK_ENTITY.get();
	}

	@Override
	protected MenuType<MediumInventoryMenu> getContainerType()
	{
		return ModElements.MEDIUM_INVENTORY_MENU.get();
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
		consumer.accept(CONNECTION);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_COUNTER.isIn(blockState))
			return HitBoxes.NORDIC.counter(this, blockState);
		else if(ModBlocks.DUNMER_COUNTER.isIn(blockState))
			return HitBoxes.DUNMER.counter(this, blockState);
		else if(ModBlocks.VENTHYR_COUNTER.isIn(blockState))
			return HitBoxes.VENTHYR.counter(this, blockState);
		else if(ModBlocks.BONE_SKELETON_COUNTER.isIn(blockState) || ModBlocks.BONE_WITHER_COUNTER.isIn(blockState))
			return HitBoxes.BONE.counter(this, blockState);
		else if(ModBlocks.ROYAL_COUNTER.isIn(blockState))
			return HitBoxes.ROYAL.counter(this, blockState);
		else if(ModBlocks.NECROLORD_COUNTER.isIn(blockState))
			return HitBoxes.NECROLORD.counter(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction facing, BlockState facingBlockState, LevelAccessor level, BlockPos pos, BlockPos facingPos)
	{
		blockState = super.updateShape(blockState, facing, facingBlockState, level, pos, facingPos);
		return getBlockState(level, pos, blockState, this);
	}

	@Nullable
	@Override
	protected BlockState modifyPlacementState(BlockState placementBlockState, BlockPlaceContext ctx)
	{
		placementBlockState = super.modifyPlacementState(placementBlockState, ctx);

		if(placementBlockState != null)
			placementBlockState = getBlockState(ctx.getLevel(), ctx.getClickedPos(), placementBlockState, this);

		return placementBlockState;
	}

	@Override
	public void neighborChanged(BlockState blockState, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving)
	{
		super.neighborChanged(blockState, level, pos, block, fromPos, isMoving);
		updateConnectionBlockState(level, pos, blockState, this);
	}

	public static void updateConnectionBlockState(LevelAccessor level, BlockPos pos, BlockState blockState, CounterBlock counter)
	{
		var newBlockState = getBlockState(level, pos, blockState, counter);

		if(newBlockState != blockState)
			level.setBlock(pos, newBlockState, 3);
	}

	public static BlockState getBlockState(LevelAccessor level, BlockPos pos, BlockState blockState, CounterBlock counter)
	{
		if(blockState.hasProperty(CONNECTION))
		{
			var connection = getConnection(level, pos, blockState, counter);
			return blockState.setValue(CONNECTION, connection);
		}

		return blockState;
	}

	public static Connection getConnection(LevelAccessor level, BlockPos pos, BlockState blockState, CounterBlock counter)
	{
		var facing = getFacing(blockState);

		var leftPos = pos.relative(facing.getCounterClockWise());
		var rightPos = pos.relative(facing.getClockWise());
		var frontPos = pos.relative(facing);

		var leftBlockState = level.getBlockState(leftPos);
		var rightBlockState = level.getBlockState(rightPos);
		var frontBlockState = level.getBlockState(frontPos);

		if(isCornerConnection(leftBlockState, rightBlockState, frontBlockState, facing, counter))
			return Connection.CORNER;

		return Connection.SINGLE;
	}

	public static boolean isCornerConnection(BlockState left, BlockState right, BlockState front, Direction facing, CounterBlock counter)
	{
		if(!front.is(counter))
			return false;

		var frontFacing = getFacing(front);

		if(left.is(counter))
		{
			var leftFacing = getFacing(left);
			return isCornerFacing(facing, leftFacing, frontFacing);
		}
		else if(right.is(counter))
		{
			var rightFacing = getFacing(right);
			return isCornerFacing(facing, rightFacing, frontFacing);
		}

		return false;
	}

	public static boolean isCornerFacing(Direction facing, Direction sideFacing, Direction frontFacing)
	{
		if(facing == sideFacing)
			return frontFacing.getCounterClockWise() == facing || sideFacing == frontFacing.getClockWise();
		else
			return sideFacing.getOpposite() == frontFacing || sideFacing == frontFacing.getOpposite();
	}

	public enum Connection implements StringRepresentable
	{
		CORNER("corner"),

		SINGLE("single");

		public final String serializedName;

		Connection(String serializedName)
		{
			this.serializedName = serializedName;
		}

		@Override
		public String getSerializedName()
		{
			return serializedName;
		}
	}

	public static class Dyeable extends CounterBlock implements IDyeable
	{
		public Dyeable(Properties properties)
		{
			super(properties);

			registerDefaultState(IDyeable.registerDefaultBlockState(defaultBlockState()));
		}

		@Override
		public MaterialColor getMapColor(BlockState blockState, BlockGetter level, BlockPos pos, MaterialColor defaultColor)
		{
			var color = super.getMapColor(blockState, level, pos, defaultColor);
			return IDyeable.getDyedMapColor(blockState, level, pos, color);
		}

		@Override
		protected void registerProperties(Consumer<Property<?>> consumer)
		{
			super.registerProperties(consumer);
			IDyeable.registerProperties(consumer);
		}

		@Override
		protected @Nullable BlockState modifyPlacementState(BlockState placementBlockState, BlockPlaceContext ctx)
		{
			placementBlockState = super.modifyPlacementState(placementBlockState, ctx);
			return IDyeable.getStateForPlacement(ctx, placementBlockState);
		}

		@Override
		public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
		{
			var interactionResult = IDyeable.use(blockState, level, pos, player, hand);

			if(interactionResult.consumesAction())
				return interactionResult;

			return super.use(blockState, level, pos, player, hand, result);
		}

		@Override
		public ItemStack getCloneItemStack(BlockState blockState, HitResult target, BlockGetter level, BlockPos pos, Player player)
		{
			var stack = super.getCloneItemStack(blockState, target, level, pos, player);
			return IDyeable.getCloneItemStack(blockState, level, pos, stack);
		}

		@Override
		public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag)
		{
			super.appendHoverText(stack, level, tooltip, flag);
			IDyeable.appendHoverText(this, tooltip);
		}
	}
}