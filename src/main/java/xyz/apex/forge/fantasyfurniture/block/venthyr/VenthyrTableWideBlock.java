package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.HitResult;

import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetTableWideBlock;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.VenthyrBlocks;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public final class VenthyrTableWideBlock extends SetTableWideBlock
{
	public VenthyrTableWideBlock(ModBlocks furnitureSet,Properties properties)
	{
		super(furnitureSet, properties);

		registerDefaultState(defaultBlockState().setValue(VenthyrBlocks.FANCY, false));
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(VenthyrBlocks.FANCY);
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

					var name = VenthyrBlocks.FANCY.getName();

					if(blockStateTag.contains(name, Tag.TAG_STRING))
					{
						var strFancy = blockStateTag.getString(name);

						if(VenthyrBlocks.FANCY.getValue(strFancy).orElse(false))
							placementBlockState = placementBlockState.setValue(VenthyrBlocks.FANCY, true);
					}
				}
			}
		}

		return placementBlockState;
	}

	@Override
	public void fillItemCategory(CreativeModeTab itemGroup, NonNullList<ItemStack> items)
	{
		var name = VenthyrBlocks.FANCY.getName();

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

		if(blockState.getValue(VenthyrBlocks.FANCY))
		{
			var stackTag = stack.getOrCreateTag();
			var blockStateTag = new CompoundTag();
			blockStateTag.putString(VenthyrBlocks.FANCY.getName(), "true");
			stackTag.put(FantasyFurniture.NBT_BLOCK_STATE_TAG, blockStateTag);
		}

		return stack;
	}
}
