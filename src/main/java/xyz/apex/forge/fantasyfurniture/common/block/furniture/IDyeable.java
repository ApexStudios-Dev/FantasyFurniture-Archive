package xyz.apex.forge.fantasyfurniture.common.block.furniture;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.jetbrains.annotations.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.util.FastColor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MaterialColor;

import xyz.apex.forge.apexcore.lib.block.IMultiBlock;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.commonality.tags.ItemTags;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface IDyeable
{
	String NBT_KEY = "DyeColor";
	EnumProperty<DyeColor> BLOCKSTATE_PROPERTY = EnumProperty.create("dye_color", DyeColor.class);
	TagKey<Item> TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "dyeable");
	String TRANSLATION_KEY = "text.%s.dyeable";

	default String getDyeableTranslationKey()
	{
		return TRANSLATION_KEY.formatted(((Block) this).getDescriptionId());
	}

	default MutableComponent getDyeableComponent()
	{
		return new TranslatableComponent(getDyeableTranslationKey()).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC);
	}

	static BlockState registerDefaultBlockState(BlockState blockState)
	{
		return setDyeColor(blockState, DyeColor.WHITE);
	}

	static Optional<DyeColor> getDyeColor(BlockGetter level, BlockPos pos, BlockState blockState)
	{
		if(blockState.getBlock() instanceof IMultiBlock multiBlock)
		{
			var origin = multiBlock.getMultiBlockOriginPos(blockState, pos);
			var originBlockState = level.getBlockState(origin);
			return getDyeColor(originBlockState).or(() -> getDyeColor(blockState));
		}

		return getDyeColor(blockState);
	}

	static boolean setDyeColor(LevelAccessor level, BlockPos pos, BlockState blockState, DyeColor dyeColor)
	{
		var changed = false;

		if(blockState.getBlock() instanceof IMultiBlock multiBlock)
		{
			var origin = multiBlock.getMultiBlockOriginPos(blockState, pos);

			for(var localPos : multiBlock.getMultiBlockLocalPositions())
			{
				var worldPos = multiBlock.getMultiBlockWorldSpaceFromLocalSpace(blockState, origin, localPos);

				if(worldPos.equals(pos))
					continue;

				var worldBlockState = level.getBlockState(worldPos);
				var newBlockState = setDyeColor(worldBlockState, dyeColor);

				if(newBlockState != worldBlockState)
					changed = level.setBlock(worldPos, newBlockState, Block.UPDATE_ALL) || changed;
			}
		}

		if(blockState.getBlock() instanceof FurnitureDoorBlock)
		{
			var half = blockState.getValue(FurnitureDoorBlock.HALF);
			var otherPos = half == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
			var otherBlockState = level.getBlockState(otherPos);
			var newOtherBlockState = setDyeColor(otherBlockState, dyeColor);

			if(newOtherBlockState != otherBlockState)
				changed = level.setBlock(otherPos, newOtherBlockState, Block.UPDATE_ALL) || changed;
		}

		var newBlockState = setDyeColor(blockState, dyeColor);

		if(newBlockState != blockState)
			changed = level.setBlock(pos, newBlockState, Block.UPDATE_ALL) || changed;

		return changed;
	}

	static MaterialColor getDyedMapColor(BlockState blockState, BlockGetter level, BlockPos pos, MaterialColor defaultColor)
	{
		return getDyeColor(level, pos, blockState).map(DyeColor::getMaterialColor).orElse(defaultColor);
	}

	static void registerProperties(Consumer<Property<?>> consumer)
	{
		consumer.accept(BLOCKSTATE_PROPERTY);
	}

	@Nullable
	static BlockState getStateForPlacement(BlockPlaceContext ctx, @Nullable BlockState placementBlockState)
	{
		if(placementBlockState != null)
		{
			var effectivelyFinal = new BlockState[] { placementBlockState };
			effectivelyFinal[0] = getDyeColor(ctx.getItemInHand()).map(c -> setDyeColor(effectivelyFinal[0], c)).orElse(effectivelyFinal[0]);

			// for stacked blocks, to retain colors when replacing
			var existingBlockState = ctx.getLevel().getBlockState(ctx.getClickedPos());

			if(existingBlockState.is(effectivelyFinal[0].getBlock()))
				effectivelyFinal[0] = getDyeColor(existingBlockState).map(color -> setDyeColor(effectivelyFinal[0], color)).orElse(effectivelyFinal[0]);

			placementBlockState = effectivelyFinal[0];
		}

		return placementBlockState;
	}

	static InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand)
	{
		var stack = player.getItemInHand(hand);
		var dyeColor = DyeColor.getColor(stack);

		if(dyeColor != null && setDyeColor(level, pos, blockState, dyeColor))
		{
			if(!player.isCreative())
				stack.shrink(1);

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}

	static ItemStack getCloneItemStack(BlockState blockState, BlockGetter level, BlockPos pos, ItemStack stack)
	{
		return getDyeColor(level, pos, blockState).map(color -> setDyeColor(stack, color)).orElse(stack);
	}

	static void appendHoverText(IDyeable dyeable, List<Component> tooltip)
	{
		tooltip.add(dyeable.getDyeableComponent());
	}

	static int tintFromDyeColor(DyeColor color)
	{
		if (color == DyeColor.BLACK)
			return 0x212121;

		var colors = color.getTextureDiffuseColors();
		var red = (int) (colors[0] * 255F);
		var green = (int) (colors[1] * 255F);
		var blue = (int) (colors[2] * 255F);

		return FastColor.ARGB32.color(255, red, green, blue);
	}

	static Optional<DyeColor> getDyeColor(ItemStack stack)
	{
		var tag = stack.getTag();

		if(tag != null)
		{
			if(tag.contains(BlockItem.BLOCK_STATE_TAG, Tag.TAG_COMPOUND))
			{
				var blockStateTag = tag.getCompound(BlockItem.BLOCK_STATE_TAG);
				var name = BLOCKSTATE_PROPERTY.getName();

				if(blockStateTag.contains(name, Tag.TAG_STRING))
					return BLOCKSTATE_PROPERTY.getValue(blockStateTag.getString(name));
			}

			if(tag.contains(NBT_KEY, Tag.TAG_STRING))
			{
				var dyeName = tag.getString(NBT_KEY);
				return Optional.ofNullable(DyeColor.byName(dyeName, null));
			}
		}

		return Optional.empty();
	}

	@CanIgnoreReturnValue
	static ItemStack setDyeColor(ItemStack stack, @Nullable DyeColor color)
	{
		var stackTag = stack.getTag();

		if(color == null)
		{
			if(stackTag != null && stackTag.contains(NBT_KEY, Tag.TAG_STRING))
			{
				stackTag.remove(NBT_KEY);
				stack.setTag(stackTag);
			}
		}
		else
		{
			if(stackTag == null)
				stackTag = new CompoundTag();

			stackTag.putString(NBT_KEY, color.getSerializedName());
			stack.setTag(stackTag);
		}

		return stack;
	}

	static boolean hasDyeColorProperty(BlockState blockState)
	{
		return blockState.hasProperty(BLOCKSTATE_PROPERTY);
	}

	static Optional<DyeColor> getDyeColor(BlockState blockState)
	{
		return blockState.getOptionalValue(BLOCKSTATE_PROPERTY);
	}

	static BlockState setDyeColor(BlockState blockState, DyeColor color)
	{
		return hasDyeColorProperty(blockState) ? blockState.setValue(BLOCKSTATE_PROPERTY, color) : blockState;
	}
}
