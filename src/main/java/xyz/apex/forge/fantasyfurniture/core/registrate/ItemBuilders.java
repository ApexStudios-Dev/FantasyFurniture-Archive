package xyz.apex.forge.fantasyfurniture.core.registrate;

import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullBiFunction;

import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraftforge.client.model.generators.ModelFile;

import xyz.apex.forge.apexcore.registrate.BasicRegistrate;
import xyz.apex.forge.apexcore.registrate.builder.ItemBuilder;
import xyz.apex.forge.apexcore.registrate.entry.BlockEntry;
import xyz.apex.forge.apexcore.registrate.entry.ItemEntry;
import xyz.apex.forge.commonality.tags.ItemTags;
import xyz.apex.forge.fantasyfurniture.AllBlocks;
import xyz.apex.forge.fantasyfurniture.AllItemGroupCategories;
import xyz.apex.forge.fantasyfurniture.common.block.decorations.SkullBlossomsBlock;
import xyz.apex.forge.fantasyfurniture.common.block.furniture.*;
import xyz.apex.forge.fantasyfurniture.common.item.SkullBlossomsBlockItem;
import xyz.apex.forge.fantasyfurniture.common.item.WidowBloomBlockItem;
import xyz.apex.forge.fantasyfurniture.core.FurnitureStation;

import static xyz.apex.forge.fantasyfurniture.core.ModRegistry.REGISTRATE;
import static com.tterrag.registrate.providers.ProviderType.LANG;

public interface ItemBuilders
{
	static <BLOCK extends Block, ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> wool(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return blockItem(itemFactory, block).tag(ItemTags.Vanilla.WOOL);
	}

	static <BLOCK extends CarpetBlock, ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> carpet(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return blockItem(itemFactory, block).tag(ItemTags.Vanilla.WOOL_CARPETS);
	}

	static <BLOCK extends ShelfBlock, ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> shelf(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return blockItem(itemFactory, block)
				.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								new ResourceLocation(ctx.getId().getNamespace(), "block/%s_single".formatted(ctx.getId().getPath()))
						)
				)
		;
	}

	static <BLOCK extends SofaBlock, ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> sofa(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return blockItem(itemFactory, block)
				.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								new ResourceLocation(ctx.getId().getNamespace(), "block/%s_single".formatted(ctx.getId().getPath()))
						)
				)
		;
	}

	static <BLOCK extends CounterBlock, ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> counter(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return blockItem(itemFactory, block)
				.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								new ResourceLocation(ctx.getId().getNamespace(), "block/%s_single".formatted(ctx.getId().getPath()))
						)
				)
		;
	}

	static <BLOCK extends BedBlock, ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> bed(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return blockItem(itemFactory, block).tag(ItemTags.Vanilla.BEDS);
	}

	static <BLOCK extends FurnitureDoorBlock, ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> door(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return blockItem(itemFactory, block)
				.tag(ItemTags.Vanilla.WOODEN_DOORS)
				.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								new ResourceLocation(ctx.getId().getNamespace(), "block/%s_left".formatted(ctx.getId().getPath()))
						)
				)
		;
	}

	static <BLOCK extends Block, ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> blockItem(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return REGISTRATE
				.object(block.getId().getPath())
				.item(properties -> itemFactory.apply(block.get(), properties))
				.setData(LANG, NonNullBiConsumer.noop())
				.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								new ResourceLocation(ctx.getId().getNamespace(), "block/%s".formatted(ctx.getId().getPath()))
						)
				)
				.tag(FurnitureStation.CRAFTABLE)
		;
	}

	static ItemEntry<WidowBloomBlockItem> widowBloomItem()
	{
		return REGISTRATE
				.object(AllBlocks.VENTHYR_WIDOW_BLOOM.getId().getPath())
				.item(properties -> new WidowBloomBlockItem(AllBlocks.VENTHYR_WIDOW_BLOOM.get(), properties))
				.setData(LANG, NonNullBiConsumer.noop())
				.tag(FurnitureStation.CRAFTABLE, AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.VENTHYR_TAG)
				.model((ctx, provider) -> provider
						.getBuilder("%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()))
						.parent(new ModelFile.UncheckedModelFile("minecraft:builtin/entity"))
						.transforms()
							.transform(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND)
								.rotation(75F, 45F, 0F)
								.translation(0F, 3F, 4F)
								.scale(.375F, .375F, .375F)
								.end()
							.transform(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND)
								.rotation(75F, 45F, 0F)
								.translation(0F, 3F, 4F)
								.scale(.375F, .375F, .375F)
							.end()
							.transform(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)
								.rotation(0F, 135F, 0F)
								.translation(0F, 7F, 0F)
								.scale(.4F, .4F, .4F)
							.end()
							.transform(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND)
								.rotation(0F, 135F, 0F)
								.translation(0F, 7F, 0F)
								.scale(.4F, .4F, .4F)
							.end()
							.transform(ItemTransforms.TransformType.HEAD)
								.rotation(0F, 0F, 0F)
								.translation(0F, 30F, 0F)
								.scale(1F, 1F, 1F)
							.end()
							.transform(ItemTransforms.TransformType.GROUND)
								.rotation(0F, 0F, 0F)
								.translation(0F, 6F, 0F)
								.scale(.25F, .25F, .25F)
							.end()
							.transform(ItemTransforms.TransformType.FIXED)
								.rotation(-90F, 0F, 0F)
								.translation(0F, 0F, -23F)
								.scale(1F, 1F, 1F)
							.end()
							.transform(ItemTransforms.TransformType.GUI)
								.rotation(30F, -135F, 0F)
								.translation(0F, 3F, 0F)
								.scale(.5F, .5F, .5F)
							.end()
						.end()
				)
		.register();
	}

	static <BLOCK extends SkullBlossomsBlock> ItemBuilder<BasicRegistrate, SkullBlossomsBlockItem, BasicRegistrate> skullBlossoms(BlockEntry<BLOCK> block)
	{
		return REGISTRATE
				.object(block.getId().getPath())
				.item(properties -> new SkullBlossomsBlockItem(block.get(), properties))
				.setData(LANG, NonNullBiConsumer.noop())
				.tag(FurnitureStation.CRAFTABLE, AllItemGroupCategories.DECORATIONS_TAG)
				.model((ctx, provider) -> provider
						.getBuilder("%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()))
						.parent(new ModelFile.UncheckedModelFile("minecraft:builtin/entity"))
						.transforms()
							.transform(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND)
								.rotation(75F, 45F, 0F)
								.translation(0F, 3F, 4F)
								.scale(.375F, .375F, .375F)
								.end()
							.transform(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND)
								.rotation(75F, 45F, 0F)
								.translation(0F, 3F, 4F)
								.scale(.375F, .375F, .375F)
							.end()
							.transform(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)
								.rotation(0F, 135F, 0F)
								.translation(0F, 7F, 0F)
								.scale(.4F, .4F, .4F)
							.end()
							.transform(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND)
								.rotation(0F, 135F, 0F)
								.translation(0F, 7F, 0F)
								.scale(.4F, .4F, .4F)
							.end()
							.transform(ItemTransforms.TransformType.HEAD)
								.rotation(0F, 0F, 0F)
								.translation(0F, 30F, 0F)
								.scale(1F, 1F, 1F)
							.end()
							.transform(ItemTransforms.TransformType.GROUND)
								.rotation(0F, 0F, 0F)
								.translation(0F, 6F, 0F)
								.scale(.25F, .25F, .25F)
							.end()
							.transform(ItemTransforms.TransformType.FIXED)
								.rotation(-90F, 0F, 0F)
								.translation(0F, 0F, -23F)
								.scale(1F, 1F, 1F)
							.end()
							.transform(ItemTransforms.TransformType.GUI)
								.rotation(30F, -135F, 0F)
								.translation(0F, 4F, 0F)
								.scale(.5F, .5F, .5F)
							.end()
						.end()
				);
	}
}
