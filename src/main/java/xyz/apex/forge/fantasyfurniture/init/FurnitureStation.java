package xyz.apex.forge.fantasyfurniture.init;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.MenuEntry;

import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.apexcore.lib.util.EventBusHelper;
import xyz.apex.forge.apexcore.lib.util.InterModUtil;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.commonality.tags.ItemTags;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.block.FurnitureStationBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.FurnitureStationBlockEntity;
import xyz.apex.forge.fantasyfurniture.client.screen.FurnitureStationMenuScreen;
import xyz.apex.forge.fantasyfurniture.menu.FurnitureStationMenu;
import xyz.apex.forge.fantasyfurniture.net.SyncFurnitureStationResultsPacket;

import java.util.Collections;
import java.util.List;

import static xyz.apex.forge.fantasyfurniture.init.ModRegistry.REGISTRATE;
import static com.tterrag.registrate.providers.ProviderType.ITEM_TAGS;
import static com.tterrag.registrate.providers.ProviderType.LANG;

public final class FurnitureStation
{
	public static final int CLAY_SLOT = 0;
	public static final int WOOD_SLOT = 1;
	public static final int STONE_SLOT = 2;
	public static final int RESULT_SLOT = 3;

	public static final BlockEntry<FurnitureStationBlock> BLOCK = block();
	public static final ItemEntry<BlockItem> BLOCK_ITEM = ItemEntry.cast(BLOCK.getSibling(Registry.ITEM_REGISTRY));
	public static final BlockEntityEntry<FurnitureStationBlockEntity> BLOCK_ENTITY = BlockEntityEntry.cast(BLOCK.getSibling(Registry.BLOCK_ENTITY_TYPE_REGISTRY));
	public static final MenuEntry<FurnitureStationMenu> MENU = container();

	private static final String TXT_ACCEPTS_ANY = "text.%s.recipe.accepts_any".formatted(Mods.FANTASY_FURNITURE);

	// items with this tag can be crafted from the furniture station
	public static final TagKey<Item> CRAFTABLE = ItemTags.tag(Mods.FANTASY_FURNITURE, "craftable");

	public static final TagKey<Item> CLAY = ItemTags.forgeTag("clay_ball"); // TODO: Move to commonality?
	public static final TagKey<Item> WOOD = ItemTags.Vanilla.PLANKS;
	public static final TagKey<Item> STONE = ItemTags.Vanilla.STONE_CRAFTING_MATERIALS;
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
		REGISTRATE.addDataGenerator(ITEM_TAGS, provider -> {
			provider.tag(CRAFTABLE);

			provider.tag(CLAY).add(Items.CLAY_BALL);
		});

		REGISTRATE.addDataGenerator(LANG, provider -> provider.add(TXT_ACCEPTS_ANY, "Accepts Any: %s"));

		EventBusHelper.addEnqueuedListener(FMLCommonSetupEvent.class, event -> FantasyFurniture.NETWORK.registerPacket(SyncFurnitureStationResultsPacket.class));

		EventBusHelper.addEnqueuedListener(InterModProcessEvent.class, event -> {
			event.getIMCStream(str -> str.equals(InterModUtil.FURNITURE_STATION_METHOD)).forEach(imc -> {
				var obj = imc.getMessageSupplier().get();

				if(obj instanceof ItemStack stack)
				{
					FantasyFurniture.LOGGER.info("Received Furniture Station Result ('{}') from Mod: '{}'", stack.getItem().getRegistryName(), imc.getSenderModId());
					registerAdditionalCraftingResult(stack);
				}
			});
		});

		EventBusHelper.addListener(PlayerEvent.PlayerLoggedInEvent.class, event -> {
			if(event.getPlayer() instanceof ServerPlayer player)
			{
				FantasyFurniture.LOGGER.info("Syncing FurnitureStation results to client ('{}')...", player.getScoreboardName());
				FantasyFurniture.NETWORK.sendTo(new SyncFurnitureStationResultsPacket(), player);
			}
		});
	}

	private static BlockEntry<FurnitureStationBlock> block()
	{
		return REGISTRATE
				.object("furniture_station")
				.block(FurnitureStationBlock::new)
				.lang("Furniture Station")
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.noOcclusion()
				.blockstate((ctx, provider) -> provider.horizontalBlock(ctx.get(), provider
						.models()
						.getExistingFile(new ResourceLocation(ctx.getId().getNamespace(), "block/%s".formatted(ctx.getId().getPath())))
				))
				.recipe((ctx, provider) -> UpgradeRecipeBuilder
						.smithing(DataIngredient.items(Items.CRAFTING_TABLE), DataIngredient.tag(ItemTags.Forge.LEATHER), ctx.get().asItem())
						.unlocks("has_crafting_table", RegistrateRecipeProvider.has(Items.CRAFTING_TABLE))
						.unlocks("has_leather", RegistrateRecipeProvider.has(ItemTags.Forge.LEATHER))
						.save(provider, ctx.getId())
				)
				.isValidSpawn(BlockHelper::never)
				.isRedstoneConductor(BlockHelper::never)
				.isSuffocating(BlockHelper::never)
				.isViewBlocking(BlockHelper::never)
				.addLayer(() -> RenderType::cutout)

				.item()
					.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								new ResourceLocation(ctx.getId().getNamespace(), "block/%s".formatted(ctx.getId().getPath()))
						)
					)
				.build()

				.simpleBlockEntity(FurnitureStationBlockEntity::new)
		.register();
	}

	private static MenuEntry<FurnitureStationMenu> container()
	{
		return REGISTRATE
				.object("furniture_station")
				.menu(
						FurnitureStationMenu::new,
						() -> FurnitureStationMenuScreen::new
				)
		.register();
	}
}