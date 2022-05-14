package xyz.apex.forge.fantasyfurniture.init;

import com.google.common.collect.Sets;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.SmithingRecipeBuilder;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.Tags;

import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.block.base.FurnitureStationBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.FurnitureStationBlockEntity;
import xyz.apex.forge.fantasyfurniture.client.screen.FurnitureStationContainerScreen;
import xyz.apex.forge.fantasyfurniture.container.FurnitureStationContainer;
import xyz.apex.forge.utility.registrator.entry.BlockEntityEntry;
import xyz.apex.forge.utility.registrator.entry.BlockEntry;
import xyz.apex.forge.utility.registrator.entry.ContainerEntry;
import xyz.apex.forge.utility.registrator.entry.ItemEntry;

import static xyz.apex.forge.utility.registrator.AbstractRegistrator.LANG_EXT_PROVIDER;
import static xyz.apex.forge.utility.registrator.provider.RegistrateLangExtProvider.EN_GB;
import static com.tterrag.registrate.providers.ProviderType.ITEM_TAGS;
import static com.tterrag.registrate.providers.ProviderType.LANG;

public final class FurnitureStation
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();

	public static final int CLAY_SLOT = 0;
	public static final int WOOD_SLOT = 1;
	public static final int STONE_SLOT = 2;
	public static final int RESULT_SLOT = 3;

	public static final BlockEntry<FurnitureStationBlock> BLOCK = block();
	public static final ItemEntry<BlockItem> BLOCK_ITEM = Registrations.blockItem(BLOCK);
	public static final BlockEntityEntry<FurnitureStationBlockEntity> BLOCK_ENTITY = Registrations.blockEntity(BLOCK);
	public static final ContainerEntry<FurnitureStationContainer> CONTAINER = container();

	private static final String TXT_ACCEPTS_ANY = "text." + FantasyFurniture.ID + ".recipe.accepts_any";
	private static final String TXT_ACCEPTS_ANY_ARG = TXT_ACCEPTS_ANY + ".arg";

	// items with this tag can be crafted from the furniture station
	public static final ITag.INamedTag<Item> CRAFTABLE = REGISTRY.moddedItemTag("craftable");

	public static final ITag.INamedTag<Item> CLAY = REGISTRY.forgeItemTagOptional("clay_ball", Sets.newHashSet(() -> Items.CLAY_BALL));
	public static final ITag.INamedTag<Item> WOOD = ItemTags.PLANKS;
	public static final ITag.INamedTag<Item> STONE = ItemTags.STONE_CRAFTING_MATERIALS;

	public static boolean isValidClay(ItemStack stack)
	{
		return stack.getItem().is(CLAY);
	}

	public static boolean isValidWood(ItemStack stack)
	{
		return stack.getItem().is(WOOD);
	}

	public static boolean isValidStone(ItemStack stack)
	{
		return stack.getItem().is(STONE);
	}

	public static ITextComponent buildAcceptsAnyComponent(ITag.INamedTag<Item> tag)
	{
		return new TranslationTextComponent(TXT_ACCEPTS_ANY, new StringTextComponent(tag.getName().toString())
				.withStyle(TextFormatting.ITALIC)
		)
				.withStyle(TextFormatting.GRAY)
		;
	}

	static void bootstrap()
	{
		REGISTRY.addDataGenerator(ITEM_TAGS, provider -> provider.tag(CRAFTABLE));

		String acceptsAnyEnglish = "Accepts Any: %s";

		REGISTRY.addDataGenerator(LANG, provider -> provider.add(TXT_ACCEPTS_ANY, acceptsAnyEnglish));
		REGISTRY.addDataGenerator(LANG_EXT_PROVIDER, provider -> provider.add(EN_GB, TXT_ACCEPTS_ANY, acceptsAnyEnglish));
	}

	private static BlockEntry<FurnitureStationBlock> block()
	{
		return REGISTRY
				.block("furniture_station", FurnitureStationBlock::new)
				.lang("Furniture Station")
				.lang(EN_GB, "Furniture Station")

				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.noOcclusion()

				.blockState(Registrations::horizontalBlock)

				.recipe((ctx, provider) -> SmithingRecipeBuilder
						.smithing(DataIngredient.items(Items.CRAFTING_TABLE), DataIngredient.tag(Tags.Items.LEATHER), ctx.get().asItem())
						.unlocks("has_crafting_table", RegistrateRecipeProvider.hasItem(Items.CRAFTING_TABLE))
						.unlocks("has_leather", RegistrateRecipeProvider.hasItem(Tags.Items.LEATHER))
						.save(provider, ctx.getId())
				)

				.isValidSpawn(BlockHelper::never)
				.isRedstoneConductor(BlockHelper::never)
				.isSuffocating(BlockHelper::never)
				.isViewBlocking(BlockHelper::never)

				.addRenderType(() -> RenderType::cutout)

				.item()
					.model(Registrations::blockItem)
				.build()

				.simpleBlockEntity(FurnitureStationBlockEntity::new)
		.register();
	}

	private static ContainerEntry<FurnitureStationContainer> container()
	{
		return REGISTRY
				.container(
						"furniture_station",
						(containerType, windowId, playerInventory, buffer) -> new FurnitureStationContainer(containerType, windowId, playerInventory, IWorldPosCallable.NULL),
						() -> FurnitureStationContainerScreen::new
				)
		.register();
	}
}