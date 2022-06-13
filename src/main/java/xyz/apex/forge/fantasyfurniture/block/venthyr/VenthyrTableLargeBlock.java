package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetTableLargeBlock;
import xyz.apex.java.utility.nullness.NonnullConsumer;

import javax.annotation.Nullable;

public final class VenthyrTableLargeBlock extends SetTableLargeBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(-15D, 0D, 1D, -12D, 2D, 4D),
			box(12D, 0D, 1D, 15D, 2D, 4D),
			box(12D, 0D, 28D, 15D, 2D, 31D),
			box(-15D, 0D, 28D, -12D, 2D, 31D),
			box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
			box(-14.5D, 2D, 28.5D, -12.5D, 13D, 30.5D),
			box(12.5D, 2D, 28.5D, 14.5D, 13D, 30.5D),
			box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
			box(-16D, 13D, 0D, 16D, 16D, 32D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);
	public static final BooleanProperty FANCY = VenthyrTableSmallBlock.FANCY;

	public VenthyrTableLargeBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(FANCY, false));
	}

	@Nullable
	@Override
	protected BlockState modifyPlacementState(BlockState placementBlockState, BlockPlaceContext ctx)
	{
		placementBlockState = super.modifyPlacementState(placementBlockState, ctx);

		if(placementBlockState != null)
		{
			var stack = ctx.getItemInHand();
			var stackTag = stack.getTag();

			if(stackTag != null)
			{
				if(stackTag.contains(FantasyFurniture.NBT_BLOCK_STATE_TAG, Tag.TAG_COMPOUND))
				{
					var blockStateTag = stackTag.getCompound(FantasyFurniture.NBT_BLOCK_STATE_TAG);

					var name = FANCY.getName();

					if(blockStateTag.contains(name, Tag.TAG_STRING))
					{
						var strFancy = blockStateTag.getString(name);

						if(VenthyrTableLargeBlock.FANCY.getValue(strFancy).orElse(false))
							placementBlockState = placementBlockState.setValue(FANCY, true);
					}
				}
			}
		}

		return placementBlockState;
	}

	@Override
	protected void registerProperties(NonnullConsumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FANCY);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = SHAPER.get(facing);
		var index = getMultiBlockIndex(blockState);

		if(index == 1 || index == 3)
		{
			var other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		if(index == 2 || index == 3)
			shape = shape.move(facing.getStepX(), 0D, facing.getStepZ());

		return shape;
	}

	@Override
	public void fillItemCategory(CreativeModeTab itemGroup, NonNullList<ItemStack> items)
	{
		var name = FANCY.getName();

		// not fancy
		var stack = asItem().getDefaultInstance();
		var stackTag = stack.getOrCreateTag();
		var blockStateTag = new CompoundTag();
		blockStateTag.putString(name, "false");
		stackTag.put(FantasyFurniture.NBT_BLOCK_STATE_TAG, blockStateTag);
		items.add(stack);

		// fancy
		stack = asItem().getDefaultInstance();
		stackTag = stack.getOrCreateTag();
		blockStateTag = new CompoundTag();
		blockStateTag.putString(name, "true");
		stackTag.put(FantasyFurniture.NBT_BLOCK_STATE_TAG, blockStateTag);
		items.add(stack);
	}

	@Override
	public ItemStack getCloneItemStack(BlockState blockState, HitResult result, BlockGetter level, BlockPos pos, Player player)
	{
		var stack = super.getCloneItemStack(blockState, result, level, pos, player);

		if(blockState.getValue(FANCY))
		{
			var stackTag = stack.getOrCreateTag();
			var blockStateTag = new CompoundTag();
			blockStateTag.putString(FANCY.getName(), "true");
			stackTag.put(FantasyFurniture.NBT_BLOCK_STATE_TAG, blockStateTag);
		}

		return stack;
	}
}
