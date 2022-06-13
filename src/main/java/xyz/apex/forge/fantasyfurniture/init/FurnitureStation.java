package xyz.apex.forge.fantasyfurniture.init;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;

import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.apexcore.lib.util.EventBusHelper;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.block.base.FurnitureStationBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.FurnitureStationBlockEntity;
import xyz.apex.forge.fantasyfurniture.client.screen.FurnitureStationContainerScreen;
import xyz.apex.forge.fantasyfurniture.container.FurnitureStationContainer;
import xyz.apex.forge.fantasyfurniture.net.SyncFurnitureStationResultsPacket;
import xyz.apex.forge.utility.registrator.entry.BlockEntityEntry;
import xyz.apex.forge.utility.registrator.entry.BlockEntry;
import xyz.apex.forge.utility.registrator.entry.ItemEntry;
import xyz.apex.forge.utility.registrator.entry.MenuEntry;
import xyz.apex.java.utility.Lazy;

import java.util.Collections;
import java.util.List;

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
	public static final MenuEntry<FurnitureStationContainer> CONTAINER = container();

	private static final String TXT_ACCEPTS_ANY = "text." + FantasyFurniture.ID + ".recipe.accepts_any";
	private static final String TXT_ACCEPTS_ANY_ARG = TXT_ACCEPTS_ANY + ".arg";

	// items with this tag can be crafted from the furniture station
	public static final TagKey<Item> CRAFTABLE = REGISTRY.moddedItemTag("craftable");

	public static final TagKey<Item> CLAY = REGISTRY.forgeItemTag("clay_ball");
	public static final TagKey<Item> WOOD = ItemTags.PLANKS;
	public static final TagKey<Item> STONE = ItemTags.STONE_CRAFTING_MATERIALS;
	private static List<ItemStack> customCraftingResults = Lists.newArrayList();
	private static final Lazy<List<ItemStack>> preCachedResults = Lazy.of(() -> {
		FantasyFurniture.LOGGER.info("Precaching Furniture Station Crafting Results...");
		var results = Lists.<ItemStack>newArrayList();
		customCraftingResults.stream().filter(stack -> !stack.isEmpty()).forEach(results::add);

		for(var item : ForgeRegistries.ITEMS.tags().getTag(CRAFTABLE))
		{
			var flag = false;

			for(var result : results)
			{
				if(result.is(item))
				{
					flag = true;
					break;
				}
			}

			if(!flag)
				results.add(item.getDefaultInstance());
		}

		results.sort(FurnitureStation::compareItemStacks);
		return ImmutableList.copyOf(results);
	});
	private static final List<ItemStack> syncedFromServer = Lists.newArrayList();

	public static boolean isValidClay(ItemStack stack)
	{
		return stack.is(CLAY);
	}

	public static boolean isValidWood(ItemStack stack)
	{
		return stack.is(WOOD);
	}

	public static boolean isValidStone(ItemStack stack)
	{
		return stack.is(STONE);
	}

	public static List<ItemStack> getCraftingResults()
	{
		if(!syncedFromServer.isEmpty())
			return syncedFromServer;

		return preCachedResults.get();
	}

	public static Component buildAcceptsAnyComponent(TagKey<Item> tag)
	{
		return new TranslatableComponent(TXT_ACCEPTS_ANY, new TextComponent(tag.location().toString())
				.withStyle(ChatFormatting.ITALIC)
		)
				.withStyle(ChatFormatting.GRAY)
		;
	}

	public static void registerAdditionalCraftingResult(ItemStack stack)
	{
		customCraftingResults.add(stack.copy());
	}

	public static void syncCraftingResultsFromServer(SyncFurnitureStationResultsPacket packet)
	{
		syncedFromServer.clear();
		Collections.addAll(syncedFromServer, packet.getResults());
	}

	static void bootstrap()
	{
		REGISTRY.addDataGenerator(ITEM_TAGS, provider -> {
			provider.tag(CRAFTABLE);

			provider.tag(CLAY).add(Items.CLAY_BALL);
		});

		var acceptsAnyEnglish = "Accepts Any: %s";

		REGISTRY.addDataGenerator(LANG, provider -> provider.add(TXT_ACCEPTS_ANY, acceptsAnyEnglish));
		REGISTRY.addDataGenerator(LANG_EXT_PROVIDER, provider -> provider.add(EN_GB, TXT_ACCEPTS_ANY, acceptsAnyEnglish));

		EventBusHelper.addListener(PlayerEvent.PlayerLoggedInEvent.class, event -> {
			if(event.getPlayer() instanceof ServerPlayer player)
			{
				FantasyFurniture.LOGGER.info("Syncing FurnitureStation results to client ('{}')...", player.getScoreboardName());
				FantasyFurniture.NETWORK.sendTo(new SyncFurnitureStationResultsPacket(), player);
			}
		});
	}

	private static int compareItemStacks(ItemStack left, ItemStack right)
	{
		for(var furnitureSet : FurnitureSet.values())
		{
			var leftHasTag = left.is(furnitureSet.itemGroupCategoryTag);
			var rightHasTag = right.is(furnitureSet.itemGroupCategoryTag);

			if(leftHasTag && !rightHasTag)
				return -1;
			else if(!leftHasTag && rightHasTag)
				return 1;
		}

		return 0;
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

				.recipe((ctx, provider) -> UpgradeRecipeBuilder
						.smithing(DataIngredient.items(Items.CRAFTING_TABLE), DataIngredient.tag(Tags.Items.LEATHER), ctx.get().asItem())
						.unlocks("has_crafting_table", RegistrateRecipeProvider.has(Items.CRAFTING_TABLE))
						.unlocks("has_leather", RegistrateRecipeProvider.has(Tags.Items.LEATHER))
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

	private static MenuEntry<FurnitureStationContainer> container()
	{
		return REGISTRY
				.container(
						"furniture_station", FurnitureStationContainer::new,
						() -> FurnitureStationContainerScreen::new
				)
		.register();
	}
}
