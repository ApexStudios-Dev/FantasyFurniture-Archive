package xyz.apex.forge.fantasyfurniture.core.registrate;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.client.model.generators.ItemModelBuilder;

import xyz.apex.forge.apexcore.registrate.BasicRegistrate;
import xyz.apex.forge.apexcore.registrate.builder.ItemBuilder;
import xyz.apex.forge.fantasyfurniture.AllItems;
import xyz.apex.forge.fantasyfurniture.common.block.decorations.StackedBlock;
import xyz.apex.forge.fantasyfurniture.common.block.furniture.IDyeable;

public interface ItemTransformers
{
	static <ITEM extends Item> void stackedBlockItemModel(DataGenContext<Item, ITEM> ctx, RegistrateItemModelProvider provider, IntegerProperty property)
	{
		var maxValue = StackedBlock.getMaxValue(property);

		provider.withExistingParent(
				"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
				new ResourceLocation(ctx.getId().getNamespace(), "block/%s_%d".formatted(ctx.getId().getPath(), maxValue))
		);
	}

	static <ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> generatedModel(ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> builder)
	{
		return builder.model(ItemTransformers::getModelFile);
	}

	static <ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> cookieJarModel(ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> buider)
	{
		return buider
				.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								new ResourceLocation(ctx.getId().getNamespace(), "block/%s_full".formatted(ctx.getId().getPath()))
						)
				)
		;
	}

	static <ITEM extends Item> ItemModelBuilder getModelFile(DataGenContext<Item, ITEM> ctx, RegistrateItemModelProvider provider)
	{
		var suffix = "";

		return provider
				.withExistingParent(
						// <namespace>:generated/item/<path>[suffix] | Model we are generating
						"%s:generated/item/%s%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath(), suffix),
						// <namespace>:item/<path>[suffix] | Existing model, exported from BlockBench
						new ResourceLocation(ctx.getId().getNamespace(), "item/%s%s".formatted(ctx.getId().getPath(), suffix))
				)
				.texture(BlockTransformers.getTextureKey(ctx.getId()), BlockTransformers.getTexturePath(ctx.getId()))
		;
	}

	static <ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> applyDyeable(ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> builder)
	{
		// NOTE: ROYAL_WOOL & ROYAL_CARPET should be any DyeableBlock / DyeableCarpet blocks
		return builder.color(() -> () -> (stack, tintIndex) -> tintIndex == (AllItems.ROYAL_WOOL.isIn(stack) || AllItems.ROYAL_CARPET.isIn(stack) ? 0 : 1) ? IDyeable.getDyeColor(stack).map(IDyeable::tintFromDyeColor).orElse(-1) : -1);
	}
}
