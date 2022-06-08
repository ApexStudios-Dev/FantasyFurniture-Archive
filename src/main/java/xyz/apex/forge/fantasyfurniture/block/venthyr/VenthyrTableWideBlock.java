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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetTableWideBlock;

import javax.annotation.Nullable;

public final class VenthyrTableWideBlock extends SetTableWideBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(12D, 0D, 1D, 15D, 2D, 4D),
			box(-15D, 0D, 1D, -12D, 2D, 4D),
			box(-15D, 0D, 12D, -12D, 2D, 15D),
			box(12D, 0D, 12D, 15D, 2D, 15D),
			box(12.5D, 2D, 12.5D, 14.5D, 13D, 14.5D),
			box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
			box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
			box(-14.5D, 2D, 12.5D, -12.5D, 13D, 14.5D),
			box(-16D, 13D, 0D, 16D, 16D, 16D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);
	public static final BooleanProperty FANCY = VenthyrTableSmallBlock.FANCY;

	public VenthyrTableWideBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);

		registerDefaultState(defaultBlockState().setValue(FANCY, false));
	}

	@Nullable
	@Override
	protected BlockState getPlacementState(BlockPlaceContext ctx, BlockState defaultBlockState)
	{
		var blockState = super.getPlacementState(ctx, defaultBlockState);

		if(blockState != null)
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
							blockState = blockState.setValue(FANCY, true);
					}
				}
			}
		}

		return blockState;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = blockState.getValue(FACING);
		var shape = SHAPER.get(facing);

		if(!pattern.isOrigin(blockState))
		{
			var opposite = facing.getClockWise();
			shape = shape.move(opposite.getStepX(), 0D, opposite.getStepZ());
		}

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
	public ItemStack getPickBlock(BlockState blockState, HitResult result, BlockGetter level, BlockPos pos, Player player)
	{
		var stack = super.getPickBlock(blockState, result, level, pos, player);

		if(blockState.getValue(FANCY))
		{
			var stackTag = stack.getOrCreateTag();
			var blockStateTag = new CompoundTag();
			blockStateTag.putString(FANCY.getName(), "true");
			stackTag.put(FantasyFurniture.NBT_BLOCK_STATE_TAG, blockStateTag);
		}

		return stack;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(FANCY);
		super.createBlockStateDefinition(builder);
	}
}
