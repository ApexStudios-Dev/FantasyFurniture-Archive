package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.providers.RegistrateLangProvider;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyBlockState;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.apexcore.lib.item.ItemGroupCategory;
import xyz.apex.forge.apexcore.lib.item.ItemGroupCategoryManager;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockFactory;
import xyz.apex.forge.apexcore.lib.util.EventBusHelper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.apexcore.revamp.block.ISeatBlock;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.block.base.core.BedBlock;
import xyz.apex.forge.fantasyfurniture.block.base.core.ShelfBlock;
import xyz.apex.forge.fantasyfurniture.block.base.core.SofaBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.*;
import xyz.apex.forge.fantasyfurniture.block.dunmer.*;
import xyz.apex.forge.fantasyfurniture.block.nordic.*;
import xyz.apex.forge.fantasyfurniture.block.venthyr.*;
import xyz.apex.forge.fantasyfurniture.item.VenthyrTableBlockItem;
import xyz.apex.forge.utility.registrator.entry.BlockEntry;
import xyz.apex.forge.utility.registrator.entry.ItemEntry;
import xyz.apex.forge.utility.registrator.factory.BlockFactory;

import static xyz.apex.forge.utility.registrator.AbstractRegistrator.LANG_EXT_PROVIDER;
import static xyz.apex.forge.utility.registrator.provider.RegistrateLangExtProvider.EN_GB;
import static com.tterrag.registrate.providers.ProviderType.ITEM_MODEL;
import static com.tterrag.registrate.providers.ProviderType.LANG;

public enum FurnitureSet
{
	// region: Nordic
	NORDIC(
			"nordic",

			NordicCarpetBlock::new,
			NordicWallLightBlock::new,
			NordicFloorLightBlock::new,
			NordicTableSmallBlock::new,
			NordicTableWideBlock::new,
			NordicTableLargeBlock::new,
			NordicStoolBlock::new,
			NordicCushionBlock::new,
			NordicPaintingSmallBlock::new,
			NordicPaintingWideBlock::new,
			NordicDrawerBlock::new,
			NordicShelfBlock::new,
			NordicSofaBlock::new,
			NordicDeskBlock::new,
			NordicChairBlock::new,
			NordicBenchBlock::new,
			NordicBookshelfBlock::new,
			NordicChestBlock::new,
			NordicDresserBlock::new,
			NordicWardrobeBlock::new,
			NordicWardrobeTopperBlock::new,
			NordicBedSingleBlock::new,
			NordicBedDoubleBlock::new,
			NordicChandelierBlock::new,
			DoorBlock::new
	),
	// endregion

	// region: Venthyr
	VENTHYR(
			"venthyr",

			VenthyrCarpetBlock::new,
			VenthyrWallLightBlock::new,
			VenthyrFloorLightBlock::new,
			VenthyrTableSmallBlock::new,
			VenthyrTableWideBlock::new,
			VenthyrTableLargeBlock::new,
			VenthyrStoolBlock::new,
			VenthyrCushionBlock::new,
			VenthyrPaintingSmallBlock::new,
			VenthyrPaintingWideBlock::new,
			VenthyrDrawerBlock::new,
			VenthyrShelfBlock::new,
			VenthyrSofaBlock::new,
			VenthyrDeskBlock::new,
			VenthyrChairBlock::new,
			VenthyrBenchBlock::new,
			VenthyrBookshelfBlock::new,
			VenthyrChestBlock::new,
			VenthyrDresserBlock::new,
			VenthyrWardrobeBlock::new,
			VenthyrWardrobeTopperBlock::new,
			VenthyrBedSingleBlock::new,
			VenthyrBedDoubleBlock::new,
			VenthyrChandelierBlock::new,
			DoorBlock::new
	),
	// endregion

	// region: Dunmer
	DUNMER(
			"dunmer",

			DunmerCarpetBlock::new,
			DunmerWallLightBlock::new,
			DunmerFloorLightBlock::new,
			DunmerTableSmallBlock::new,
			DunmerTableWideBlock::new,
			DunmerTableLargeBlock::new,
			DunmerStoolBlock::new,
			DunmerCushionBlock::new,
			DunmerPaintingSmallBlock::new,
			DunmerPaintingWideBlock::new,
			DunmerDrawerBlock::new,
			DunmerShelfBlock::new,
			DunmerSofaBlock::new,
			DunmerDeskBlock::new,
			DunmerChairBlock::new,
			DunmerBenchBlock::new,
			DunmerBookshelfBlock::new,
			DunmerChestBlock::new,
			DunmerDresserBlock::new,
			DunmerWardrobeBlock::new,
			DunmerWardrobeTopperBlock::new,
			DunmerBedSingleBlock::new,
			DunmerBedDoubleBlock::new,
			DunmerChandelierBlock::new,
			DoorBlock::new
	),
	// endregion
	;

	public static final ResourceLocation VENTHYR_FANCY_TABLE_ITEM_PROPERTY = FFRegistry.getInstance().id("venthyr_fancy");

	// region: Fields
	public final String serializedName;
	public final String englishName;

	public final TagKey<Item> itemGroupCategoryTag;
	public final ItemGroupCategory itemGroupCategory;

	public final BlockEntry<?>[] blocks;
	public final ItemEntry<?>[] items;

	public final BlockEntry<Block> woolBlock;
	public final ItemEntry<BlockItem> woolBlockItem;
	public final BlockEntry<? extends SetCarpetBlock> carpetBlock;
	public final ItemEntry<BlockItem> carpetBlockItem;
	public final BlockEntry<? extends SetWallLightBlock> wallLightBlock;
	public final ItemEntry<BlockItem> wallLightBlockItem;
	public final BlockEntry<? extends SetFloorLightBlock> floorLightBlock;
	public final ItemEntry<BlockItem> floorLightBlockItem;
	public final BlockEntry<? extends SetTableSmallBlock> tableSmallBlock;
	public final ItemEntry<BlockItem> tableSmallBlockItem;
	public final BlockEntry<? extends SetTableWideBlock> tableWideBlock;
	public final ItemEntry<BlockItem> tableWideBlockItem;
	public final BlockEntry<? extends SetTableLargeBlock> tableLargeBlock;
	public final ItemEntry<BlockItem> tableLargeBlockItem;
	public final BlockEntry<? extends SetStoolBlock> stoolBlock;
	public final ItemEntry<BlockItem> stoolBlockItem;
	public final BlockEntry<? extends SetCushionBlock> cushionBlock;
	public final ItemEntry<BlockItem> cushionBlockItem;
	public final BlockEntry<? extends SetPaintingSmallBlock> paintingSmallBlock;
	public final ItemEntry<BlockItem> paintingSmallBlockItem;
	public final BlockEntry<? extends SetPaintingWideBlock> paintingWideBlock;
	public final ItemEntry<BlockItem> paintingWideBlockItem;
	public final BlockEntry<? extends SetDrawerBlock> drawerBlock;
	public final ItemEntry<BlockItem> drawerBlockItem;
	public final BlockEntry<? extends SetShelfBlock> shelfBlock;
	public final ItemEntry<BlockItem> shelfBlockItem;
	public final BlockEntry<? extends SetSofaBlock> sofaBlock;
	public final ItemEntry<BlockItem> sofaBlockItem;
	public final BlockEntry<? extends SetDeskBlock> deskLeftBlock;
	public final ItemEntry<BlockItem> deskLeftBlockItem;
	public final BlockEntry<? extends SetDeskBlock> deskRightBlock;
	public final ItemEntry<BlockItem> deskRightBlockItem;
	public final BlockEntry<? extends SetChairBlock> chairBlock;
	public final ItemEntry<BlockItem> chairBlockItem;
	public final BlockEntry<? extends SetBenchBlock> benchBlock;
	public final ItemEntry<BlockItem> benchBlockItem;
	public final BlockEntry<? extends SetBookshelfBlock> bookshelfBlock;
	public final ItemEntry<BlockItem> bookshelfBlockItem;
	public final BlockEntry<? extends SetChestBlock> chestBlock;
	public final ItemEntry<BlockItem> chestBlockItem;
	public final BlockEntry<? extends SetDresserBlock> dresserBlock;
	public final ItemEntry<BlockItem> dresserBlockItem;
	public final BlockEntry<? extends SetWardrobeBlock> wardrobeBlock;
	public final ItemEntry<BlockItem> wardrobeBlockItem;
	public final BlockEntry<? extends SetWardrobeTopperBlock> wardrobeTopperBlock;
	public final ItemEntry<BlockItem> wardrobeTopperBlockItem;
	public final BlockEntry<? extends SetBedSingleBlock> bedSingleBlock;
	public final ItemEntry<BlockItem> bedSingleBlockItem;
	public final BlockEntry<? extends SetBedDoubleBlock> bedDoubleBlock;
	public final ItemEntry<BlockItem> bedDoubleBlockItem;
	public final BlockEntry<? extends SetChandelierBlock> chandelierBlock;
	public final ItemEntry<BlockItem> chandelierBlockItem;
	public final BlockEntry<? extends DoorBlock> doorSingleBlock;
	public final ItemEntry<BlockItem> doorSingleBlockItem;
	public final BlockEntry<? extends DoorBlock> doorDoubleBlock;
	public final ItemEntry<BlockItem> doorDoubleBlockItem;
	// endregion

	FurnitureSet(
			String serializedName,

			BlockFactory<SetCarpetBlock> carpetBlockFactory,
			BlockFactory<SetWallLightBlock> wallLightBlockFactory,
			MultiBlockFactory<SetFloorLightBlock> floorLightBlockFactory,
			BlockFactory<SetTableSmallBlock> tableSmallBlockFactory,
			MultiBlockFactory<SetTableWideBlock> tableWideBlockFactory,
			MultiBlockFactory<SetTableLargeBlock> tableLargeBlockFactory,
			BlockFactory<SetStoolBlock> stoolBlockFactory,
			BlockFactory<SetCushionBlock> cushionBlockFactory,
			BlockFactory<SetPaintingSmallBlock> paintingSmallBlockFactory,
			MultiBlockFactory<SetPaintingWideBlock> paintingWideBlockFactory,
			BlockFactory<SetDrawerBlock> drawerBlockFactory,
			BlockFactory<SetShelfBlock> shelfBlockFactory,
			BlockFactory<SetSofaBlock> sofaBlockFactory,
			MultiBlockFactory<SetDeskBlock> deskBlockFactory,
			BlockFactory<SetChairBlock> chairBlockFactory,
			BlockFactory<SetBenchBlock> benchBlockFactory,
			MultiBlockFactory<SetBookshelfBlock> bookshelfBlockFactory,
			MultiBlockFactory<SetChestBlock> chestBlockFactory,
			MultiBlockFactory<SetDresserBlock> dresserBlockFactory,
			MultiBlockFactory<SetWardrobeBlock> wardrobeBlockFactory,
			MultiBlockFactory<SetWardrobeTopperBlock> wardrobeTopperBlockFactory,
			MultiBlockFactory<SetBedSingleBlock> bedSingleBlockFactory,
			MultiBlockFactory<SetBedDoubleBlock> bedDoubleBlockFactory,
			BlockFactory<SetChandelierBlock> chandelierBlockFactory,
			BlockFactory<DoorBlock> doorBlockFactory
	)
	{
		this.serializedName = serializedName;

		var isVenthyr = serializedName.equals("venthyr");

		englishName = RegistrateLangProvider.toEnglishName(serializedName);
		itemGroupCategoryTag = FFRegistry.getInstance().moddedItemTag("item_category/" + serializedName);

		woolBlock = wool(serializedName, englishName, itemGroupCategoryTag);
		woolBlockItem = Registrations.blockItem(woolBlock);

		carpetBlock = carpet(carpetBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		carpetBlockItem = Registrations.blockItem(carpetBlock);

		wallLightBlock = wallLight(wallLightBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		wallLightBlockItem = Registrations.blockItem(wallLightBlock);

		floorLightBlock = floorLight(floorLightBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		floorLightBlockItem = Registrations.blockItem(floorLightBlock);

		tableSmallBlock = isVenthyr ? venthyrTableSmall(tableSmallBlockFactory, serializedName, englishName, itemGroupCategoryTag) : tableSmall(tableSmallBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		tableSmallBlockItem = Registrations.blockItem(tableSmallBlock);

		tableWideBlock = isVenthyr ? venthyrTableWide(tableWideBlockFactory, serializedName, englishName, itemGroupCategoryTag) : tableWide(tableWideBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		tableWideBlockItem = Registrations.blockItem(tableWideBlock);

		tableLargeBlock = isVenthyr ? venthyrTableLarge(tableLargeBlockFactory, serializedName, englishName, itemGroupCategoryTag) : tableLarge(tableLargeBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		tableLargeBlockItem = Registrations.blockItem(tableLargeBlock);

		stoolBlock = stool(stoolBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		stoolBlockItem = Registrations.blockItem(stoolBlock);

		cushionBlock = cushion(cushionBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		cushionBlockItem = Registrations.blockItem(cushionBlock);

		paintingSmallBlock = paintingSmall(paintingSmallBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		paintingSmallBlockItem = Registrations.blockItem(paintingSmallBlock);

		paintingWideBlock = paintingWide(paintingWideBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		paintingWideBlockItem = Registrations.blockItem(paintingWideBlock);

		drawerBlock = drawer(drawerBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		drawerBlockItem = Registrations.blockItem(drawerBlock);

		shelfBlock = shelf(shelfBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		shelfBlockItem = Registrations.blockItem(shelfBlock);

		sofaBlock = sofa(sofaBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		sofaBlockItem = Registrations.blockItem(sofaBlock);

		deskLeftBlock = desk(deskBlockFactory, serializedName, englishName, "left", itemGroupCategoryTag);
		deskLeftBlockItem = Registrations.blockItem(deskLeftBlock);

		deskRightBlock = desk(deskBlockFactory, serializedName, englishName, "right", itemGroupCategoryTag);
		deskRightBlockItem = Registrations.blockItem(deskRightBlock);

		chairBlock = chair(chairBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		chairBlockItem = Registrations.blockItem(chairBlock);

		benchBlock = bench(benchBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		benchBlockItem = Registrations.blockItem(benchBlock);

		bookshelfBlock = bookshelf(bookshelfBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		bookshelfBlockItem = Registrations.blockItem(bookshelfBlock);

		chestBlock = chest(chestBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		chestBlockItem = Registrations.blockItem(chestBlock);

		dresserBlock = dresser(dresserBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		dresserBlockItem = Registrations.blockItem(dresserBlock);

		wardrobeBlock = wardrobeBottom(wardrobeBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		wardrobeBlockItem = Registrations.blockItem(wardrobeBlock);

		wardrobeTopperBlock = wardrobeTop(wardrobeTopperBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		wardrobeTopperBlockItem = Registrations.blockItem(wardrobeTopperBlock);

		bedSingleBlock = bedSingle(bedSingleBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		bedSingleBlockItem = Registrations.blockItem(bedSingleBlock);

		bedDoubleBlock = bedDouble(bedDoubleBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		bedDoubleBlockItem = Registrations.blockItem(bedDoubleBlock);

		chandelierBlock = chandelier(chandelierBlockFactory, serializedName, englishName, itemGroupCategoryTag);
		chandelierBlockItem = Registrations.blockItem(chairBlock);

		doorSingleBlock = door(doorBlockFactory, serializedName, englishName, "single", itemGroupCategoryTag);
		doorSingleBlockItem = Registrations.blockItem(chairBlock);

		doorDoubleBlock = door(doorBlockFactory, serializedName, englishName, "double", itemGroupCategoryTag);
		doorDoubleBlockItem = Registrations.blockItem(chairBlock);

		blocks = new BlockEntry<?>[] {
				woolBlock,
				carpetBlock,
				wallLightBlock,
				floorLightBlock,
				tableSmallBlock,
				tableWideBlock,
				tableLargeBlock,
				stoolBlock,
				cushionBlock,
				paintingSmallBlock,
				paintingWideBlock,
				drawerBlock,
				shelfBlock,
				sofaBlock,
				deskLeftBlock,
				deskRightBlock,
				chairBlock,
				benchBlock,
				bookshelfBlock,
				chestBlock,
				dresserBlock,
				wardrobeBlock,
				wardrobeTopperBlock,
				bedSingleBlock,
				bedDoubleBlock,
				chandelierBlock,
				doorSingleBlock,
				doorDoubleBlock
		};

		items = new ItemEntry<?>[] {
				woolBlockItem,
				carpetBlockItem,
				wallLightBlockItem,
				floorLightBlockItem,
				tableSmallBlockItem,
				tableWideBlockItem,
				tableLargeBlockItem,
				stoolBlockItem,
				cushionBlockItem,
				paintingSmallBlockItem,
				paintingWideBlockItem,
				drawerBlockItem,
				shelfBlockItem,
				sofaBlockItem,
				deskLeftBlockItem,
				deskRightBlockItem,
				chairBlockItem,
				benchBlockItem,
				bookshelfBlockItem,
				chestBlockItem,
				dresserBlockItem,
				wardrobeBlockItem,
				wardrobeTopperBlockItem,
				bedSingleBlockItem,
				bedDoubleBlockItem,
				chandelierBlockItem,
				doorSingleBlockItem,
				doorDoubleBlockItem
		};

		itemGroupCategory = ItemGroupCategory
			.builder(itemGroupCategoryTag.location().toString())
				.tagged(itemGroupCategoryTag)
				.defaultIcon(bedSingleBlock::asItemStack)
		.build();
	}

	static void bootstrap()
	{
		var registry = FFRegistry.getInstance();

		for(var furnitureSet : values())
		{
			registry.addDataGenerator(LANG, provider -> {
				for(var entry : furnitureSet.blocks)
				{
					entry.ifPresent(block -> {
						if(block instanceof ISeatBlock seat)
							provider.add(seat.getOccupiedTranslationKey(), "This seat is occupied");
						if(block instanceof BedBlock bed)
							provider.add(bed.getOccupiedTranslationKey(), "This bed is occupied");
					});
				}
			});

			furnitureSet.itemGroupCategory
					.addTranslationGenerator(registry, furnitureSet.englishName)
					.addTranslationGenerator(registry, EN_GB, furnitureSet.englishName);

			registry.addDataGenerator(LANG_EXT_PROVIDER, provider -> {
				for(var entry : furnitureSet.blocks)
				{
					entry.ifPresent(block -> {
						if(block instanceof ISeatBlock seat)
							provider.add(EN_GB, seat.getOccupiedTranslationKey(), "This seat is occupied");
						if(block instanceof BedBlock bed)
							provider.add(EN_GB, bed.getOccupiedTranslationKey(), "This bed is occupied");
					});
				}
			});

			EventBusHelper.addEnqueuedListener(FMLCommonSetupEvent.class, event -> {
				var instance = ItemGroupCategoryManager.getInstance(FFRegistry.MOD_ITEM_GROUP);
				instance.addCategory(furnitureSet.itemGroupCategory);
			});
		}

		EventBusHelper.addEnqueuedListener(FMLClientSetupEvent.class, event -> {
			VENTHYR.tableLargeBlockItem.ifPresent(item -> {
				ItemProperties.register(item, VENTHYR_FANCY_TABLE_ITEM_PROPERTY, (stack, level, entity, seed) -> {
					var stackTag = stack.getTag();

					if(stackTag != null)
					{
						if(stackTag.contains(FantasyFurniture.NBT_BLOCK_STATE_TAG, net.minecraft.nbt.Tag.TAG_COMPOUND))
						{
							var blockStateTag = stackTag.getCompound(FantasyFurniture.NBT_BLOCK_STATE_TAG);
							var name = VenthyrTableLargeBlock.FANCY.getName();

							if(blockStateTag.contains(name, net.minecraft.nbt.Tag.TAG_STRING))
							{
								var strFancy = blockStateTag.getString(name);

								if(VenthyrTableLargeBlock.FANCY.getValue(strFancy).orElse(false))
									return 1F;
							}
						}
					}

					return 0F;
				});
			});

			VENTHYR.tableWideBlockItem.ifPresent(item -> {
				ItemProperties.register(item, VENTHYR_FANCY_TABLE_ITEM_PROPERTY, (stack, level, entity, seed) -> {
					var stackTag = stack.getTag();

					if(stackTag != null)
					{
						if(stackTag.contains(FantasyFurniture.NBT_BLOCK_STATE_TAG, net.minecraft.nbt.Tag.TAG_COMPOUND))
						{
							var blockStateTag = stackTag.getCompound(FantasyFurniture.NBT_BLOCK_STATE_TAG);
							var name = VenthyrTableWideBlock.FANCY.getName();

							if(blockStateTag.contains(name, net.minecraft.nbt.Tag.TAG_STRING))
							{
								var strFancy = blockStateTag.getString(name);

								if(VenthyrTableWideBlock.FANCY.getValue(strFancy).orElse(false))
									return 1F;
							}
						}
					}

					return 0F;
				});
			});

			VENTHYR.tableSmallBlockItem.ifPresent(item -> {
				ItemProperties.register(item, VENTHYR_FANCY_TABLE_ITEM_PROPERTY, (stack, level, entity, seed) -> {
					var stackTag = stack.getTag();

					if(stackTag != null)
					{
						if(stackTag.contains(FantasyFurniture.NBT_BLOCK_STATE_TAG, net.minecraft.nbt.Tag.TAG_COMPOUND))
						{
							var blockStateTag = stackTag.getCompound(FantasyFurniture.NBT_BLOCK_STATE_TAG);
							var name = VenthyrTableSmallBlock.FANCY.getName();

							if(blockStateTag.contains(name, net.minecraft.nbt.Tag.TAG_STRING))
							{
								var strFancy = blockStateTag.getString(name);

								if(VenthyrTableSmallBlock.FANCY.getValue(strFancy).orElse(false))
									return 1F;
							}
						}
					}

					return 0F;
				});
			});
		});
	}

	// region: Wool
	private static BlockEntry<Block> wool(String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		var registry = FFRegistry.getInstance();
		var location = registry.idString("block/%s/wool".formatted(serializedName));

		return registry
				.block("%s/wool".formatted(serializedName))
					.lang("%s Wool".formatted(englishName))
					.lang(EN_GB, "%s Wool".formatted(englishName))

					.initialProperties(Material.WOOL, MaterialColor.WOOL)
					.strength(.8F)
					.sound(SoundType.WOOL)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.simpleBlock(ctx.get(), provider.models().cubeAll(location, new ResourceLocation(location))))

					.tag(BlockTags.WOOL)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ItemTags.WOOL, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Carpet
	private static <BLOCK extends SetCarpetBlock> BlockEntry<BLOCK> carpet(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		var registry = FFRegistry.getInstance();
		var locationWool = registry.id("block/%s/wool".formatted(serializedName));
		var location = registry.idString("block/%s/carpet".formatted(serializedName));

		return registry
				.block("%s/carpet".formatted(serializedName), blockFactory)
					.lang("%s Carpet".formatted(englishName))
					.lang(EN_GB, "%s Carpet".formatted(englishName))

					.initialProperties(Material.CLOTH_DECORATION, MaterialColor.WOOL)
					.strength(.1F)
					.sound(SoundType.WOOL)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.simpleBlock(ctx.get(), provider.models().carpet(location, locationWool)))

					.tag(BlockTags.CARPETS)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ItemTags.CARPETS, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Wall Light
	private static <BLOCK extends SetWallLightBlock> BlockEntry<BLOCK> wallLight(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.block("%s/wall_light".formatted(serializedName), blockFactory)
					.lang("%s Wall Light".formatted(englishName))
					.lang(EN_GB, "%s Wall Light".formatted(englishName))

					.initialProperties(Material.DECORATION)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.instabreak()
					.noCollission()
			        .lightLevel(blockState -> serializedName.equals("dunmer") || !SetWallLightBlock.isWaterLogged(blockState) ? 14 : 0)

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Floor Light
	private static <BLOCK extends SetFloorLightBlock> BlockEntry<BLOCK> floorLight(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock("%s/floor_light".formatted(serializedName), blockFactory, FFPatterns.PATTERN_2x1_FLOOR_LIGHT)
					.lang("%s Floor Light".formatted(englishName))
					.lang(EN_GB, "%s Floor Light".formatted(englishName))

					.initialProperties(Material.DECORATION)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.instabreak()
					.lightLevel(blockState -> blockState.getValue(SetFloorLightBlock.WATERLOGGED) || blockState.getValue(SetFloorLightBlock.SIDE) == SetFloorLightBlock.Side.BOTTOM ? 0 : 14)

					.blockState((ctx, provider) -> provider.simpleBlock(ctx.get(), provider.models().getExistingFile(Registrations.getExistingModelPath(ctx.getId(), ""))))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Table
	// region: Small
	private static <BLOCK extends SetTableSmallBlock> BlockEntry<BLOCK> tableSmall(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.block("%s/table_small".formatted(serializedName), blockFactory)
					.lang("%s Table Small".formatted(englishName))
					.lang(EN_GB, "%s Table Small".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
        .register();
	}

	private static <BLOCK extends SetTableSmallBlock> BlockEntry<BLOCK> venthyrTableSmall(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.block("%s/table_small".formatted(serializedName), blockFactory)
					.setData(LANG, (ctx, provider) -> {
			            var block = ctx.get();
			            provider.add(block, "%s Table Small".formatted(englishName));
			            provider.add("%s.fancy".formatted(block.getDescriptionId()), "Fancy %s Table Small".formatted(englishName));
		            })
			        .setData(LANG_EXT_PROVIDER, (ctx, provider) -> {
				        var block = ctx.get();
				        provider.add(EN_GB, block, "%s Table Small".formatted(englishName));
				        provider.add(EN_GB, "%s.fancy".formatted(block.getDescriptionId()), "Fancy %s Table Small".formatted(englishName));
			        })

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState((ctx, provider) -> {
						ResourceLocation id = ctx.getId();
						provider.horizontalBlock(ctx.get(), blockState -> {
							ModelFile.ExistingModelFile existingModel = provider.models().getExistingFile(Registrations.getExistingModelPath(id, ""));

							if(blockState.getValue(VenthyrTableSmallBlock.FANCY))
								return provider.models().getBuilder(id + "_fancy").parent(existingModel).texture("table_small", id.getNamespace() + ":models/" + serializedName + "/table_small_fancy");
							return existingModel;
						});
					})

                    .loot((lootTables, block) -> lootTables
		                    .add(block, LootTable
				                    .lootTable()
				                    .withPool(BlockLoot
						                    .applyExplosionCondition(block, LootPool
								                    .lootPool()
								                    .setRolls(ConstantValue.exactly(1))
								                    .add(LootItem.lootTableItem(block))
								                    .apply(CopyBlockState
										                    .copyState(block)
										                    .copy(VenthyrTableSmallBlock.FANCY)
								                    )
						                    )
				                    )
		                    )
                    )

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item(VenthyrTableBlockItem::new)
						.model((ctx, provider) -> {
							var id = ctx.getId();
							provider.withExistingParent("%s:item/%s".formatted(id.getNamespace(), id.getPath()), Registrations.getExistingModelPath(id, ""))
							        .override()
							            .predicate(VENTHYR_FANCY_TABLE_ITEM_PROPERTY, 1F)
										.model(provider.getExistingFile(new ResourceLocation(id.getNamespace(), "%s_fancy".formatted(id.getPath()))))
							        .end()
							;
						})
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
						.onRegister(item -> {
							var stack = item.getDefaultInstance();
							var stackTag = stack.getOrCreateTag();
							var blockStateTag = new CompoundTag();
							blockStateTag.putString(VenthyrTableSmallBlock.FANCY.getName(), "false");
							stackTag.put(FantasyFurniture.NBT_BLOCK_STATE_TAG, blockStateTag);
							FurnitureStation.registerAdditionalCraftingResult(stack);

							stack = item.getDefaultInstance();
							stackTag = stack.getOrCreateTag();
							blockStateTag = new CompoundTag();
							blockStateTag.putString(VenthyrTableSmallBlock.FANCY.getName(), "true");
							stackTag.put(FantasyFurniture.NBT_BLOCK_STATE_TAG, blockStateTag);
							FurnitureStation.registerAdditionalCraftingResult(stack);
						})
					.build()
        .register();
	}
	// endregion

	// region: Wide
	private static <BLOCK extends SetTableWideBlock> BlockEntry<BLOCK> tableWide(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock("%s/table_wide".formatted(serializedName), blockFactory, FFPatterns.PATTERN_1x2)
					.lang("%s Table Wide".formatted(englishName))
					.lang(EN_GB, "%s Table Wide".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
        .register();
	}

	private static <BLOCK extends SetTableWideBlock> BlockEntry<BLOCK> venthyrTableWide(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock("%s/table_wide".formatted(serializedName), blockFactory, FFPatterns.PATTERN_1x2)
					.setData(LANG, (ctx, provider) -> {
			            var block = ctx.get();
			            provider.add(block, "%s Table Wide".formatted(englishName));
			            provider.add("%s.fancy".formatted(block.getDescriptionId()), "Fancy %s Table Wide".formatted(englishName));
		            })
			        .setData(LANG_EXT_PROVIDER, (ctx, provider) -> {
				        var block = ctx.get();
				        provider.add(EN_GB, block, "%s Table Wide".formatted(englishName));
				        provider.add(EN_GB, "%s.fancy".formatted(block.getDescriptionId()), "Fancy %s Table Wide".formatted(englishName));
			        })

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState((ctx, provider) -> {
						var id = ctx.getId();
						provider.horizontalBlock(ctx.get(), blockState -> {
							var existingModel = provider.models().getExistingFile(Registrations.getExistingModelPath(id, ""));

							if(blockState.getValue(VenthyrTableWideBlock.FANCY))
								return provider.models().getBuilder("%s_fancy".formatted(id)).parent(existingModel).texture("table_wide", "%s:models/%s/table_wide_fancy".formatted(id.getNamespace(), serializedName));
							return existingModel;
						});
					})

                    .loot((lootTables, block) -> lootTables
		                    .add(block, LootTable
				                    .lootTable()
				                    .withPool(BlockLoot
						                    .applyExplosionCondition(block, LootPool
								                    .lootPool()
								                    .setRolls(ConstantValue.exactly(1))
								                    .add(LootItem.lootTableItem(block))
								                    .apply(CopyBlockState
										                    .copyState(block)
										                    .copy(VenthyrTableWideBlock.FANCY)
								                    )
						                    )
				                    )
		                    )
                    )

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item(VenthyrTableBlockItem::new)
					     .model((ctx, provider) -> {
							var id = ctx.getId();
							provider.withExistingParent("%s:item/%s".formatted(id.getNamespace(), id.getPath()), Registrations.getExistingModelPath(id, ""))
							        .override()
							            .predicate(VENTHYR_FANCY_TABLE_ITEM_PROPERTY, 1F)
										.model(provider.getExistingFile(new ResourceLocation(id.getNamespace(), "%s_fancy".formatted(id.getPath()))))
							        .end()
							;
						 })
						 .tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
						 .onRegister(item -> {
							var stack = item.getDefaultInstance();
							var stackTag = stack.getOrCreateTag();
							var blockStateTag = new CompoundTag();
							blockStateTag.putString(VenthyrTableWideBlock.FANCY.getName(), "false");
							stackTag.put(FantasyFurniture.NBT_BLOCK_STATE_TAG, blockStateTag);
							FurnitureStation.registerAdditionalCraftingResult(stack);

							stack = item.getDefaultInstance();
							stackTag = stack.getOrCreateTag();
							blockStateTag = new CompoundTag();
							blockStateTag.putString(VenthyrTableWideBlock.FANCY.getName(), "true");
							stackTag.put(FantasyFurniture.NBT_BLOCK_STATE_TAG, blockStateTag);
							FurnitureStation.registerAdditionalCraftingResult(stack);
						})
					.build()
        .register();
	}
	// endregion

	// region: Large
	private static <BLOCK extends SetTableLargeBlock> BlockEntry<BLOCK> tableLarge(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock("%s/table_large".formatted(serializedName), blockFactory, FFPatterns.PATTERN_2x2)
					.lang("%s Table Large".formatted(englishName))
					.lang(EN_GB, "%s Table Large".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
        .register();
	}

	private static <BLOCK extends SetTableLargeBlock> BlockEntry<BLOCK> venthyrTableLarge(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock("%s/table_large".formatted(serializedName), blockFactory, FFPatterns.PATTERN_2x2)
					.setData(LANG, (ctx, provider) -> {
			            var block = ctx.get();
			            provider.add(block, "%s Table Large".formatted(englishName));
			            provider.add("%s.fancy".formatted(block.getDescriptionId()), "Fancy %s Table Large".formatted(englishName));
		            })
			        .setData(LANG_EXT_PROVIDER, (ctx, provider) -> {
				        var block = ctx.get();
				        provider.add(EN_GB, block, "%s Table Large".formatted(englishName));
				        provider.add(EN_GB, "%s.fancy".formatted(block.getDescriptionId()), "Fancy %s Table Large".formatted(englishName));
			        })

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState((ctx, provider) -> {
						var id = ctx.getId();
						provider.horizontalBlock(ctx.get(), blockState -> {
							var existingModel = provider.models().getExistingFile(Registrations.getExistingModelPath(id, ""));

							if(blockState.getValue(VenthyrTableLargeBlock.FANCY))
								return provider.models().getBuilder("%s_fancy".formatted(id)).parent(existingModel).texture("table_large", "%s:models/%s/table_large_fancy".formatted(id.getNamespace(), serializedName));
							return existingModel;
						});
					})

                    .loot((lootTables, block) -> lootTables
		                    .add(block, LootTable
				                    .lootTable()
				                    .withPool(BlockLoot
						                    .applyExplosionCondition(block, LootPool
								                    .lootPool()
								                    .setRolls(ConstantValue.exactly(1))
								                    .add(LootItem.lootTableItem(block))
								                    .apply(CopyBlockState
										                    .copyState(block)
										                    .copy(VenthyrTableLargeBlock.FANCY)
								                    )
						                    )
				                    )
		                    )
                    )

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item(VenthyrTableBlockItem::new)
					     .model((ctx, provider) -> {
							var id = ctx.getId();
							provider.withExistingParent("%s:item/%s".formatted(id.getNamespace(), id.getPath()), Registrations.getExistingModelPath(id, ""))
							        .override()
							            .predicate(VENTHYR_FANCY_TABLE_ITEM_PROPERTY, 1F)
										.model(provider.getExistingFile(new ResourceLocation(id.getNamespace(), "%s_fancy".formatted(id.getPath()))))
							        .end()
							;
						})
						 .tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
		                 .onRegister(item -> {
							var stack = item.getDefaultInstance();
			                 var stackTag = stack.getOrCreateTag();
			                 var blockStateTag = new CompoundTag();
							blockStateTag.putString(VenthyrTableLargeBlock.FANCY.getName(), "false");
							stackTag.put(FantasyFurniture.NBT_BLOCK_STATE_TAG, blockStateTag);
							FurnitureStation.registerAdditionalCraftingResult(stack);

							stack = item.getDefaultInstance();
							stackTag = stack.getOrCreateTag();
							blockStateTag = new CompoundTag();
							blockStateTag.putString(VenthyrTableLargeBlock.FANCY.getName(), "true");
							stackTag.put(FantasyFurniture.NBT_BLOCK_STATE_TAG, blockStateTag);
							FurnitureStation.registerAdditionalCraftingResult(stack);
						})
					.build()
        .register();
	}
	// endregion
	// endregion

	// region: Stool
	private static <BLOCK extends SetStoolBlock> BlockEntry<BLOCK> stool(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.block("%s/stool".formatted(serializedName), blockFactory)
					.lang("%s Stool".formatted(englishName))
					.lang(EN_GB, "%s Stool".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Cushion
	private static <BLOCK extends SetCushionBlock> BlockEntry<BLOCK> cushion(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.block("%s/cushion".formatted(serializedName), blockFactory)
					.lang("%s Cushion".formatted(englishName))
					.lang(EN_GB, "%s Cushion".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Painting
	// region: Small
	private static <BLOCK extends SetPaintingSmallBlock> BlockEntry<BLOCK> paintingSmall(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.block("%s/painting_small".formatted(serializedName), blockFactory)
					.lang("%s Painting Small".formatted(englishName))
					.lang(EN_GB, "%s Painting Small".formatted(englishName))

					.initialProperties(Material.WOOD)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.instabreak()
					.noCollission()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Wide
	private static <BLOCK extends SetPaintingWideBlock> BlockEntry<BLOCK> paintingWide(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock("%s/painting_wide".formatted(serializedName), blockFactory, FFPatterns.PATTERN_1x2_PAINTING)
					.lang("%s Painting Wide".formatted(englishName))
					.lang(EN_GB, "%s Painting Wide".formatted(englishName))

					.initialProperties(Material.WOOD)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.instabreak()
					.noCollission()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion
	// endregion

	// region: Drawer
	private static <BLOCK extends SetDrawerBlock> BlockEntry<BLOCK> drawer(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.block("%s/drawer".formatted(serializedName), blockFactory)
					.lang("%s Drawer".formatted(englishName))
					.lang(EN_GB, "%s Drawer".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Shelf
	private static <BLOCK extends SetShelfBlock> BlockEntry<BLOCK> shelf(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		FFRegistry registry = FFRegistry.getInstance();

		return registry
				.block("%s/shelf".formatted(serializedName), blockFactory)
					.lang("%s Shelf".formatted(englishName))
					.lang(EN_GB, "%s Shelf".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.horizontalBlock(ctx.get(), blockState -> {
						var connectionType = blockState.getValue(ShelfBlock.CONNECTION_TYPE);
						String suffix;

						if(connectionType == ShelfBlock.ConnectionType.LEFT || connectionType == ShelfBlock.ConnectionType.RIGHT)
							suffix = "_" + connectionType.getSerializedName();
						else if(connectionType == ShelfBlock.ConnectionType.BOTH)
							suffix = "_center";
						else
							suffix = "";

						return provider.models().getExistingFile(registry.id("block/%s/shelf%s".formatted(serializedName, suffix)));
					}, 180))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Sofa
	private static <BLOCK extends SetSofaBlock> BlockEntry<BLOCK> sofa(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		var registry = FFRegistry.getInstance();

		return registry
				.block("%s/sofa".formatted(serializedName), blockFactory)
					.lang("%s Sofa".formatted(englishName))
					.lang(EN_GB, "%s Sofa".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.getVariantBuilder(ctx.get()).forAllStates(blockState -> {
						var facing = BaseBlock.getFacing(blockState);
						var connectionType = blockState.getValue(SofaBlock.CONNECTION_TYPE);

						return ConfiguredModel
								.builder()
									.modelFile(provider.models().getExistingFile(registry.id("block/%s/sofa_%s".formatted(serializedName, connectionType.getSerializedName()))))
									.rotationY(((int) facing.toYRot() + 180) % 360)
								.build();
					}))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> provider.withExistingParent("item/%s".formatted(ctx.getName()), registry.id("block/%s/sofa_single".formatted(serializedName))))
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Desk
	private static <BLOCK extends SetDeskBlock> BlockEntry<BLOCK> desk(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, String side, TagKey<Item> itemGroupCategoryTag)
	{
		var engName = "%s Desk %s".formatted(englishName, RegistrateLangProvider.toEnglishName(side));

		return FFRegistry.getInstance()
				.multiBlock("%s/desk_%s".formatted(serializedName, side), blockFactory, FFPatterns.PATTERN_1x2)
					.lang(engName)
					.lang(EN_GB, engName)

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Chair
	private static <BLOCK extends SetChairBlock> BlockEntry<BLOCK> chair(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.block("%s/chair".formatted(serializedName), blockFactory)
					.lang("%s Chair".formatted(englishName))
					.lang(EN_GB, "%s Chair".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Bench
	private static <BLOCK extends SetBenchBlock> BlockEntry<BLOCK> bench(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.block("%s/bench".formatted(serializedName), blockFactory)
					.lang("%s Bench".formatted(englishName))
					.lang(EN_GB, "%s Bench".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Bookshelf
	private static <BLOCK extends SetBookshelfBlock> BlockEntry<BLOCK> bookshelf(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock("%s/bookshelf".formatted(serializedName), blockFactory, FFPatterns.PATTERN_2x2_VERTICAL)
					.lang("%s Bookshelf".formatted(englishName))
					.lang(EN_GB, "%s Bookshelf".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Chest
	private static <BLOCK extends SetChestBlock> BlockEntry<BLOCK> chest(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock("%s/chest".formatted(serializedName), blockFactory, FFPatterns.PATTERN_1x2)
					.lang("%s Chest".formatted(englishName))
					.lang(EN_GB, "%s Chest".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Dresser
	private static <BLOCK extends SetDresserBlock> BlockEntry<BLOCK> dresser(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock("%s/dresser".formatted(serializedName), blockFactory, FFPatterns.PATTERN_1x2)
					.lang("%s Dresser".formatted(englishName))
					.lang(EN_GB, "%s Dresser".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Wardrobe
	// region: Bottom
	private static <BLOCK extends SetWardrobeBlock> BlockEntry<BLOCK> wardrobeBottom(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock("%s/wardrobe_bottom".formatted(serializedName), blockFactory, FFPatterns.PATTERN_2x2_VERTICAL)
					.lang("%s Wardrobe".formatted(englishName))
					.lang(EN_GB, "%s Wardrobe".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Top
	private static <BLOCK extends SetWardrobeTopperBlock> BlockEntry<BLOCK> wardrobeTop(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock("%s/wardrobe_top".formatted(serializedName), blockFactory, FFPatterns.PATTERN_1x2)
					.lang("%s Wardrobe Top".formatted(englishName))
					.lang(EN_GB, "%s Wardrobe Top".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion
	// endregion

	// region: Bed
	// region: Single
	private static <BLOCK extends SetBedSingleBlock> BlockEntry<BLOCK> bedSingle(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock("%s/bed_single".formatted(serializedName), blockFactory, FFPatterns.PATTERN_BED_SINGLE)
					.lang("%s Bed Single".formatted(englishName))
					.lang(EN_GB, "%s Bed Single".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState((ctx, provider) -> {
						var id = ctx.getId();

						provider.horizontalBlock(ctx.get(), blockState -> {
							var suffix = "";

							if(serializedName.equals("dunmer"))
								suffix = ((SetBedSingleBlock) blockState.getBlock()).getMultiBlockPattern().isOrigin(blockState) ? "_head" : "_foot";

							return provider.models().getExistingFile(Registrations.getExistingModelPath(id, suffix));
						});
					})

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.tag(BlockTags.BEDS)

					.item()
						.model((ctx, provider) -> {
							var id = ctx.getId();
							var suffix = "";

							if(serializedName.equals("dunmer"))
								suffix = "_full";

							provider.withExistingParent(id.getNamespace() + ":item/" + id.getPath(), Registrations.getExistingModelPath(id, suffix));
						})
						.tag(FurnitureStation.CRAFTABLE, ItemTags.BEDS, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Double
	private static <BLOCK extends SetBedDoubleBlock> BlockEntry<BLOCK> bedDouble(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock("%s/bed_double".formatted(serializedName), blockFactory, FFPatterns.PATTERN_2x2)
					.lang("%s Bed Double".formatted(englishName))
					.lang(EN_GB, "%s Bed Double".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState((ctx, provider) -> {
						ResourceLocation id = ctx.getId();

						provider.horizontalBlock(ctx.get(), blockState -> {
							String suffix = "";

							if(serializedName.equals("dunmer"))
								suffix = ((SetBedDoubleBlock) blockState.getBlock()).getMultiBlockPattern().isOrigin(blockState) ? "_head" : "_foot";

							return provider.models().getExistingFile(Registrations.getExistingModelPath(id, suffix));
						});
					})

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.tag(BlockTags.BEDS)

					.item()
						.model((ctx, provider) -> {
							ResourceLocation id = ctx.getId();
							String suffix = "";

							if(serializedName.equals("dunmer"))
								suffix = "_full";

							provider.withExistingParent(id.getNamespace() + ":item/" + id.getPath(), Registrations.getExistingModelPath(id, suffix));
						})
						.tag(FurnitureStation.CRAFTABLE, ItemTags.BEDS, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion
	// endregion

	// region: Chandelier
	private static <BLOCK extends SetChandelierBlock> BlockEntry<BLOCK> chandelier(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.block("%s/chandelier".formatted(serializedName), blockFactory)
					.lang("%s Chandelier".formatted(englishName))
					.lang(EN_GB, "%s Chandelier".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.lightLevel(blockState -> blockState.getValue(SetChandelierBlock.WATERLOGGED) ? 0 : 14)

					.blockState(Registrations::simpleBlockWithStates)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Door
	private static <BLOCK extends DoorBlock> BlockEntry<BLOCK> door(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, String type, TagKey<Item> itemGroupCategoryTag)
	{
		String engName;

		if(serializedName.equals("dunmer"))
			engName = englishName + " Door " + (type.equals("single") ? 1 : 2);
		else
			engName = englishName + " Door " + RegistrateLangProvider.toEnglishName(type);

		return FFRegistry.getInstance()
				.block("%s/door_%s".formatted(serializedName, type), blockFactory)
					.lang(engName)
					.lang(EN_GB, engName)

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState((ctx, provider) -> {
						var id = ctx.getId();
						var path = id.getPath();
						var namespace = id.getNamespace();
						provider.doorBlock(ctx.get(), new ResourceLocation(namespace, "block/%s_bottom".formatted(path)), new ResourceLocation(namespace, "block/%s_top".formatted(path)));
					})
					.loot((lootTables, block) -> lootTables.add(block, BlockLoot.createDoorTable(block)))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

			        .tag(BlockTags.WOODEN_DOORS)

					.item()
						.clearDataGenerator(ITEM_MODEL)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag, ItemTags.WOODEN_DOORS)
					.build()
		.register();
	}
	// endregion
}
