package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.providers.RegistrateLangProvider;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.functions.CopyBlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.apexcore.lib.item.ItemGroupCategory;
import xyz.apex.forge.apexcore.lib.item.ItemGroupCategoryManager;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockFactory;
import xyz.apex.forge.apexcore.lib.util.EventBusHelper;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.block.base.core.BedBlock;
import xyz.apex.forge.fantasyfurniture.block.base.core.ISeatBlock;
import xyz.apex.forge.fantasyfurniture.block.base.core.ShelfBlock;
import xyz.apex.forge.fantasyfurniture.block.base.core.SofaBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.*;
import xyz.apex.forge.fantasyfurniture.block.nordic.*;
import xyz.apex.forge.fantasyfurniture.block.venthyr.*;
import xyz.apex.forge.fantasyfurniture.item.VenthyrTableBlockItem;
import xyz.apex.forge.utility.registrator.entry.BlockEntry;
import xyz.apex.forge.utility.registrator.entry.ItemEntry;
import xyz.apex.forge.utility.registrator.factory.BlockFactory;

import static xyz.apex.forge.utility.registrator.AbstractRegistrator.LANG_EXT_PROVIDER;
import static xyz.apex.forge.utility.registrator.provider.RegistrateLangExtProvider.EN_GB;
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
			NordicChandelierBlock::new
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
			VenthyrChandelierBlock::new
	),
	// endregion
	;

	public static final ResourceLocation VENTHYR_FANCY_TABLE_ITEM_PROPERTY = FFRegistry.getInstance().id("venthyr_fancy");

	// region: Fields
	public final String serializedName;
	public final String englishName;

	public final ITag.INamedTag<Item> itemGroupCategoryTag;
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
			MultiBlockFactory<SetChairBlock> chairBlockFactory,
			MultiBlockFactory<SetBenchBlock> benchBlockFactory,
			MultiBlockFactory<SetBookshelfBlock> bookshelfBlockFactory,
			MultiBlockFactory<SetChestBlock> chestBlockFactory,
			MultiBlockFactory<SetDresserBlock> dresserBlockFactory,
			MultiBlockFactory<SetWardrobeBlock> wardrobeBlockFactory,
			MultiBlockFactory<SetWardrobeTopperBlock> wardrobeTopperBlockFactory,
			MultiBlockFactory<SetBedSingleBlock> bedSingleBlockFactory,
			MultiBlockFactory<SetBedDoubleBlock> bedDoubleBlockFactory,
			BlockFactory<SetChandelierBlock> chandelierBlockFactory
	)
	{
		this.serializedName = serializedName;

		boolean isVenthyr = serializedName.equals("venthyr");

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
				chandelierBlock
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
				chandelierBlockItem
		};

		itemGroupCategory = ItemGroupCategory
			.builder(itemGroupCategoryTag.getName().toString())
				.tagged(itemGroupCategoryTag)
				.defaultIcon(bedSingleBlock::asItemStack)
		.build();
	}

	static void bootstrap()
	{
		FFRegistry registry = FFRegistry.getInstance();

		for(FurnitureSet furnitureSet : values())
		{
			registry.addDataGenerator(LANG, provider -> {
				for(BlockEntry<?> entry : furnitureSet.blocks)
				{
					entry.ifPresent(block -> {
						if(block instanceof ISeatBlock)
						{
							ISeatBlock seat = (ISeatBlock) block;
							provider.add(seat.getOccupiedTranslationKey(), "This seat is occupied");
						}

						if(block instanceof BedBlock)
						{
							BedBlock bed = (BedBlock) block;
							provider.add(bed.getOccupiedTranslationKey(), "This bed is occupied");
						}
					});
				}
			});

			furnitureSet.itemGroupCategory
					.addTranslationGenerator(registry, furnitureSet.englishName)
					.addTranslationGenerator(registry, EN_GB, furnitureSet.englishName);

			registry.addDataGenerator(LANG_EXT_PROVIDER, provider -> {
				for(BlockEntry<?> entry : furnitureSet.blocks)
				{
					entry.ifPresent(block -> {
						if(block instanceof ISeatBlock)
						{
							ISeatBlock seat = (ISeatBlock) block;
							provider.add(EN_GB, seat.getOccupiedTranslationKey(), "This seat is occupied");
						}

						if(block instanceof BedBlock)
						{
							BedBlock bed = (BedBlock) block;
							provider.add(EN_GB, bed.getOccupiedTranslationKey(), "This bed is occupied");
						}
					});
				}
			});

			EventBusHelper.addEnqueuedListener(FMLCommonSetupEvent.class, event -> {
				ItemGroupCategoryManager instance = ItemGroupCategoryManager.getInstance(FFRegistry.MOD_ITEM_GROUP);
				instance.addCategory(furnitureSet.itemGroupCategory);
			});
		}

		EventBusHelper.addEnqueuedListener(FMLClientSetupEvent.class, event -> {
			VENTHYR.tableLargeBlockItem.ifPresent(item -> {
				ItemModelsProperties.register(item, VENTHYR_FANCY_TABLE_ITEM_PROPERTY, (stack, level, entity) -> {
					CompoundNBT stackTag = stack.getTag();

					if(stackTag != null)
					{
						if(stackTag.contains(FantasyFurniture.NBT_BLOCK_STATE_TAG, Constants.NBT.TAG_COMPOUND))
						{
							CompoundNBT blockStateTag = stackTag.getCompound(FantasyFurniture.NBT_BLOCK_STATE_TAG);
							String name = VenthyrTableLargeBlock.FANCY.getName();

							if(blockStateTag.contains(name, Constants.NBT.TAG_STRING))
							{
								String strFancy = blockStateTag.getString(name);

								if(VenthyrTableLargeBlock.FANCY.getValue(strFancy).orElse(false))
									return 1F;
							}
						}
					}

					return 0F;
				});
			});

			VENTHYR.tableWideBlockItem.ifPresent(item -> {
				ItemModelsProperties.register(item, VENTHYR_FANCY_TABLE_ITEM_PROPERTY, (stack, level, entity) -> {
					CompoundNBT stackTag = stack.getTag();

					if(stackTag != null)
					{
						if(stackTag.contains(FantasyFurniture.NBT_BLOCK_STATE_TAG, Constants.NBT.TAG_COMPOUND))
						{
							CompoundNBT blockStateTag = stackTag.getCompound(FantasyFurniture.NBT_BLOCK_STATE_TAG);
							String name = VenthyrTableWideBlock.FANCY.getName();

							if(blockStateTag.contains(name, Constants.NBT.TAG_STRING))
							{
								String strFancy = blockStateTag.getString(name);

								if(VenthyrTableWideBlock.FANCY.getValue(strFancy).orElse(false))
									return 1F;
							}
						}
					}

					return 0F;
				});
			});

			VENTHYR.tableSmallBlockItem.ifPresent(item -> {
				ItemModelsProperties.register(item, VENTHYR_FANCY_TABLE_ITEM_PROPERTY, (stack, level, entity) -> {
					CompoundNBT stackTag = stack.getTag();

					if(stackTag != null)
					{
						if(stackTag.contains(FantasyFurniture.NBT_BLOCK_STATE_TAG, Constants.NBT.TAG_COMPOUND))
						{
							CompoundNBT blockStateTag = stackTag.getCompound(FantasyFurniture.NBT_BLOCK_STATE_TAG);
							String name = VenthyrTableSmallBlock.FANCY.getName();

							if(blockStateTag.contains(name, Constants.NBT.TAG_STRING))
							{
								String strFancy = blockStateTag.getString(name);

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
	private static BlockEntry<Block> wool(String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		FFRegistry registry = FFRegistry.getInstance();
		String location = registry.idString("block/" + serializedName + "/wool");

		return registry
				.block(serializedName + "/wool")
					.lang(englishName + " Wool")
					.lang(EN_GB, englishName + " Wool")

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
	private static <BLOCK extends SetCarpetBlock> BlockEntry<BLOCK> carpet(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		FFRegistry registry = FFRegistry.getInstance();
		ResourceLocation locationWool = registry.id("block/" + serializedName + "/wool");
		String location = registry.idString("block/" + serializedName + "/carpet");

		return registry
				.block(serializedName + "/carpet", blockFactory)
					.lang(englishName + " Carpet")
					.lang(EN_GB, englishName + " Carpet")

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
	private static <BLOCK extends SetWallLightBlock> BlockEntry<BLOCK> wallLight(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.block(serializedName + "/wall_light", blockFactory)
					.lang(englishName + " Wall Light")
					.lang(EN_GB, englishName + " Wall Light")

					.initialProperties(Material.DECORATION)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.instabreak()
					.noCollission()
					.lightLevel(blockState -> 14)

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
	private static <BLOCK extends SetFloorLightBlock> BlockEntry<BLOCK> floorLight(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock(serializedName + "/floor_light", blockFactory, FFPatterns.PATTERN_2x1_FLOOR_LIGHT)
					.lang(englishName + " Floor Light")
					.lang(EN_GB, englishName + " Floor Light")

					.initialProperties(Material.DECORATION)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.instabreak()
					.lightLevel(blockState -> blockState.getValue(SetFloorLightBlock.SIDE) == SetFloorLightBlock.Side.TOP ? 14 : 0)

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
	private static <BLOCK extends SetTableSmallBlock> BlockEntry<BLOCK> tableSmall(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.block(serializedName + "/table_small", blockFactory)
					.lang(englishName + " Table Small")
					.lang(EN_GB, englishName + " Table Small")

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

	private static <BLOCK extends SetTableSmallBlock> BlockEntry<BLOCK> venthyrTableSmall(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.block(serializedName + "/table_small", blockFactory)
					// .lang(englishName + " Table Small")
					// .lang(EN_GB, englishName + " Table Small")
					.setData(LANG, (ctx, provider) -> {
			            BLOCK block = ctx.get();
			            provider.add(block, englishName + " Table Small");
			            provider.add(block.getDescriptionId() + ".fancy", "Fancy " + englishName + " Table Small");
		            })
			        .setData(LANG_EXT_PROVIDER, (ctx, provider) -> {
				        BLOCK block = ctx.get();
				        provider.add(EN_GB, block, englishName + " Table Small");
				        provider.add(EN_GB, block.getDescriptionId() + ".fancy", "Fancy " + englishName + " Table Small");
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
				                    .withPool(BlockLootTables
						                    .applyExplosionCondition(block, LootPool
								                    .lootPool()
								                    .setRolls(ConstantRange.exactly(1))
								                    .add(ItemLootEntry.lootTableItem(block))
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
							ResourceLocation id = ctx.getId();
							provider.withExistingParent(id.getNamespace() + ":item/" + id.getPath(), Registrations.getExistingModelPath(id, ""))
							        .override()
							            .predicate(VENTHYR_FANCY_TABLE_ITEM_PROPERTY, 1F)
										.model(provider.getExistingFile(new ResourceLocation(id.getNamespace(), id.getPath() + "_fancy")))
							        .end()
							;
						})
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
						.onRegister(item -> {
							ItemStack stack = item.getDefaultInstance();
							CompoundNBT stackTag = stack.getOrCreateTag();
							CompoundNBT blockStateTag = new CompoundNBT();
							blockStateTag.putString(VenthyrTableSmallBlock.FANCY.getName(), "false");
							stackTag.put(FantasyFurniture.NBT_BLOCK_STATE_TAG, blockStateTag);
							FurnitureStation.registerAdditionalCraftingResult(stack);

							stack = item.getDefaultInstance();
							stackTag = stack.getOrCreateTag();
							blockStateTag = new CompoundNBT();
							blockStateTag.putString(VenthyrTableSmallBlock.FANCY.getName(), "true");
							stackTag.put(FantasyFurniture.NBT_BLOCK_STATE_TAG, blockStateTag);
							FurnitureStation.registerAdditionalCraftingResult(stack);
						})
					.build()
        .register();
	}
	// endregion

	// region: Wide
	private static <BLOCK extends SetTableWideBlock> BlockEntry<BLOCK> tableWide(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock(serializedName + "/table_wide", blockFactory, FFPatterns.PATTERN_1x2)
					.lang(englishName + " Table Wide")
					.lang(EN_GB, englishName + " Table Wide")

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

	private static <BLOCK extends SetTableWideBlock> BlockEntry<BLOCK> venthyrTableWide(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock(serializedName + "/table_wide", blockFactory, FFPatterns.PATTERN_1x2)
					// .lang(englishName + " Table Wide")
					// .lang(EN_GB, englishName + " Table Wide")
                    .setData(LANG, (ctx, provider) -> {
			            BLOCK block = ctx.get();
			            provider.add(block, englishName + " Table Wide");
			            provider.add(block.getDescriptionId() + ".fancy", "Fancy " + englishName + " Table Wide");
		            })
			        .setData(LANG_EXT_PROVIDER, (ctx, provider) -> {
				        BLOCK block = ctx.get();
				        provider.add(EN_GB, block, englishName + " Table Wide");
				        provider.add(EN_GB, block.getDescriptionId() + ".fancy", "Fancy " + englishName + " Table Wide");
			        })

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState((ctx, provider) -> {
						ResourceLocation id = ctx.getId();
						provider.horizontalBlock(ctx.get(), blockState -> {
							ModelFile.ExistingModelFile existingModel = provider.models().getExistingFile(Registrations.getExistingModelPath(id, ""));

							if(blockState.getValue(VenthyrTableWideBlock.FANCY))
								return provider.models().getBuilder(id + "_fancy").parent(existingModel).texture("table_wide", id.getNamespace() + ":models/" + serializedName + "/table_wide_fancy");
							return existingModel;
						});
					})

                    .loot((lootTables, block) -> lootTables
		                    .add(block, LootTable
				                    .lootTable()
				                    .withPool(BlockLootTables
						                    .applyExplosionCondition(block, LootPool
								                    .lootPool()
								                    .setRolls(ConstantRange.exactly(1))
								                    .add(ItemLootEntry.lootTableItem(block))
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
							ResourceLocation id = ctx.getId();
							provider.withExistingParent(id.getNamespace() + ":item/" + id.getPath(), Registrations.getExistingModelPath(id, ""))
							        .override()
							            .predicate(VENTHYR_FANCY_TABLE_ITEM_PROPERTY, 1F)
										.model(provider.getExistingFile(new ResourceLocation(id.getNamespace(), id.getPath() + "_fancy")))
							        .end()
							;
						 })
						 .tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
						 .onRegister(item -> {
							ItemStack stack = item.getDefaultInstance();
							CompoundNBT stackTag = stack.getOrCreateTag();
							CompoundNBT blockStateTag = new CompoundNBT();
							blockStateTag.putString(VenthyrTableWideBlock.FANCY.getName(), "false");
							stackTag.put(FantasyFurniture.NBT_BLOCK_STATE_TAG, blockStateTag);
							FurnitureStation.registerAdditionalCraftingResult(stack);

							stack = item.getDefaultInstance();
							stackTag = stack.getOrCreateTag();
							blockStateTag = new CompoundNBT();
							blockStateTag.putString(VenthyrTableWideBlock.FANCY.getName(), "true");
							stackTag.put(FantasyFurniture.NBT_BLOCK_STATE_TAG, blockStateTag);
							FurnitureStation.registerAdditionalCraftingResult(stack);
						})
					.build()
        .register();
	}
	// endregion

	// region: Large
	private static <BLOCK extends SetTableLargeBlock> BlockEntry<BLOCK> tableLarge(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock(serializedName + "/table_large", blockFactory, FFPatterns.PATTERN_2x2)
					.lang(englishName + " Table Large")
					.lang(EN_GB, englishName + " Table Large")

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

	private static <BLOCK extends SetTableLargeBlock> BlockEntry<BLOCK> venthyrTableLarge(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock(serializedName + "/table_large", blockFactory, FFPatterns.PATTERN_2x2)
					// .lang(englishName + " Table Large")
					// .lang(EN_GB, englishName + " Table Large")
		            .setData(LANG, (ctx, provider) -> {
			            BLOCK block = ctx.get();
			            provider.add(block, englishName + " Table Large");
			            provider.add(block.getDescriptionId() + ".fancy", "Fancy " + englishName + " Table Large");
		            })
			        .setData(LANG_EXT_PROVIDER, (ctx, provider) -> {
				        BLOCK block = ctx.get();
				        provider.add(EN_GB, block, englishName + " Table Large");
				        provider.add(EN_GB, block.getDescriptionId() + ".fancy", "Fancy " + englishName + " Table Large");
			        })

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState((ctx, provider) -> {
						ResourceLocation id = ctx.getId();
						provider.horizontalBlock(ctx.get(), blockState -> {
							ModelFile.ExistingModelFile existingModel = provider.models().getExistingFile(Registrations.getExistingModelPath(id, ""));

							if(blockState.getValue(VenthyrTableLargeBlock.FANCY))
								return provider.models().getBuilder(id + "_fancy").parent(existingModel).texture("table_large", id.getNamespace() + ":models/" + serializedName + "/table_large_fancy");
							return existingModel;
						});
					})

                    .loot((lootTables, block) -> lootTables
		                    .add(block, LootTable
				                    .lootTable()
				                    .withPool(BlockLootTables
						                    .applyExplosionCondition(block, LootPool
								                    .lootPool()
								                    .setRolls(ConstantRange.exactly(1))
								                    .add(ItemLootEntry.lootTableItem(block))
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
							ResourceLocation id = ctx.getId();
							provider.withExistingParent(id.getNamespace() + ":item/" + id.getPath(), Registrations.getExistingModelPath(id, ""))
							        .override()
							            .predicate(VENTHYR_FANCY_TABLE_ITEM_PROPERTY, 1F)
										.model(provider.getExistingFile(new ResourceLocation(id.getNamespace(), id.getPath() + "_fancy")))
							        .end()
							;
						})
						 .tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
		                 .onRegister(item -> {
							ItemStack stack = item.getDefaultInstance();
							CompoundNBT stackTag = stack.getOrCreateTag();
							CompoundNBT blockStateTag = new CompoundNBT();
							blockStateTag.putString(VenthyrTableLargeBlock.FANCY.getName(), "false");
							stackTag.put(FantasyFurniture.NBT_BLOCK_STATE_TAG, blockStateTag);
							FurnitureStation.registerAdditionalCraftingResult(stack);

							stack = item.getDefaultInstance();
							stackTag = stack.getOrCreateTag();
							blockStateTag = new CompoundNBT();
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
	private static <BLOCK extends SetStoolBlock> BlockEntry<BLOCK> stool(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.block(serializedName + "/stool", blockFactory)
					.lang(englishName + " Stool")
					.lang(EN_GB, englishName + " Stool")

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
	private static <BLOCK extends SetCushionBlock> BlockEntry<BLOCK> cushion(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.block(serializedName + "/cushion", blockFactory)
					.lang(englishName + " Cushion")
					.lang(EN_GB, englishName + " Cushion")

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
	private static <BLOCK extends SetPaintingSmallBlock> BlockEntry<BLOCK> paintingSmall(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.block(serializedName + "/painting_small", blockFactory)
					.lang(englishName + " Painting Small")
					.lang(EN_GB, englishName + " Painting Small")

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
	private static <BLOCK extends SetPaintingWideBlock> BlockEntry<BLOCK> paintingWide(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock(serializedName + "/painting_wide", blockFactory, FFPatterns.PATTERN_1x2_PAINTING)
					.lang(englishName + " Painting Wide")
					.lang(EN_GB, englishName + " Painting Wide")

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
	private static <BLOCK extends SetDrawerBlock> BlockEntry<BLOCK> drawer(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.block(serializedName + "/drawer", blockFactory)
					.lang(englishName + " Drawer")
					.lang(EN_GB, englishName + " Drawer")

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
	private static <BLOCK extends SetShelfBlock> BlockEntry<BLOCK> shelf(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		FFRegistry registry = FFRegistry.getInstance();

		return registry
				.block(serializedName + "/shelf", blockFactory)
					.lang(englishName + " Shelf")
					.lang(EN_GB, englishName + " Shelf")

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.horizontalBlock(ctx.get(), blockState -> {
						ShelfBlock.ConnectionType connectionType = blockState.getValue(ShelfBlock.CONNECTION_TYPE);
						String suffix;

						if(connectionType == ShelfBlock.ConnectionType.LEFT || connectionType == ShelfBlock.ConnectionType.RIGHT)
							suffix = "_" + connectionType.getSerializedName();
						else if(connectionType == ShelfBlock.ConnectionType.BOTH)
							suffix = "_center";
						else
							suffix = "";

						return provider.models().getExistingFile(registry.id("block/" + serializedName + "/shelf" + suffix));
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
	private static <BLOCK extends SetSofaBlock> BlockEntry<BLOCK> sofa(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		FFRegistry registry = FFRegistry.getInstance();

		return registry
				.block(serializedName + "/sofa", blockFactory)
					.lang(englishName + " Sofa")
					.lang(EN_GB, englishName + " Sofa")

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.getVariantBuilder(ctx.get()).forAllStates(blockState -> {
						Direction facing = blockState.getValue(SofaBlock.FACING);
						SofaBlock.ConnectionType connectionType = blockState.getValue(SofaBlock.CONNECTION_TYPE);

						return ConfiguredModel
								.builder()
									.modelFile(provider.models().getExistingFile(registry.id("block/" + serializedName + "/sofa_" + connectionType.getSerializedName())))
									.rotationY(((int) facing.toYRot() + 180) % 360)
								.build();
					}))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> provider.withExistingParent("item/" + ctx.getName(), registry.id("block/" + serializedName + "/sofa_single")))
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Desk
	private static <BLOCK extends SetDeskBlock> BlockEntry<BLOCK> desk(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, String side, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		String engName = englishName + " Desk " + RegistrateLangProvider.toEnglishName(side);

		return FFRegistry.getInstance()
				.multiBlock(serializedName + "/desk_" + side, blockFactory, FFPatterns.PATTERN_1x2)
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
	private static <BLOCK extends SetChairBlock> BlockEntry<BLOCK> chair(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock(serializedName + "/chair", blockFactory, FFPatterns.PATTERN_2x1)
					.lang(englishName + " Chair")
					.lang(EN_GB, englishName + " Chair")

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
	private static <BLOCK extends SetBenchBlock> BlockEntry<BLOCK> bench(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock(serializedName + "/bench", blockFactory, FFPatterns.PATTERN_1x2)
					.lang(englishName + " Bench")
					.lang(EN_GB, englishName + " Bench")

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
	private static <BLOCK extends SetBookshelfBlock> BlockEntry<BLOCK> bookshelf(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock(serializedName + "/bookshelf", blockFactory, FFPatterns.PATTERN_2x2_VERTICAL)
					.lang(englishName + " Bookshelf")
					.lang(EN_GB, englishName + " Bookshelf")

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
	private static <BLOCK extends SetChestBlock> BlockEntry<BLOCK> chest(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock(serializedName + "/chest", blockFactory, FFPatterns.PATTERN_1x2)
					.lang(englishName + " Chest")
					.lang(EN_GB, englishName + " Chest")

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
	private static <BLOCK extends SetDresserBlock> BlockEntry<BLOCK> dresser(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock(serializedName + "/dresser", blockFactory, FFPatterns.PATTERN_1x2)
					.lang(englishName + " Dresser")
					.lang(EN_GB, englishName + " Dresser")

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
	private static <BLOCK extends SetWardrobeBlock> BlockEntry<BLOCK> wardrobeBottom(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock(serializedName + "/wardrobe_bottom", blockFactory, FFPatterns.PATTERN_2x2_VERTICAL)
					.lang(englishName + " Wardrobe")
					.lang(EN_GB, englishName + " Wardrobe")

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
	private static <BLOCK extends SetWardrobeTopperBlock> BlockEntry<BLOCK> wardrobeTop(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock(serializedName + "/wardrobe_top", blockFactory, FFPatterns.PATTERN_1x2)
					.lang(englishName + " Wardrobe Top")
					.lang(EN_GB, englishName + " Wardrobe Top")

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
	private static <BLOCK extends SetBedSingleBlock> BlockEntry<BLOCK> bedSingle(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock(serializedName + "/bed_single", blockFactory, FFPatterns.PATTERN_BED_SINGLE)
					.lang(englishName + " Bed Single")
					.lang(EN_GB, englishName + " Bed Single")

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

					.tag(BlockTags.BEDS)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ItemTags.BEDS, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Double
	private static <BLOCK extends SetBedDoubleBlock> BlockEntry<BLOCK> bedDouble(MultiBlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.multiBlock(serializedName + "/bed_double", blockFactory, FFPatterns.PATTERN_2x2)
					.lang(englishName + " Bed Double")
					.lang(EN_GB, englishName + " Bed Double")

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

					.tag(BlockTags.BEDS)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ItemTags.BEDS, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion
	// endregion

	// region: Chandelier
	private static <BLOCK extends SetChandelierBlock> BlockEntry<BLOCK> chandelier(BlockFactory<BLOCK> blockFactory, String serializedName, String englishName, ITag.INamedTag<Item> itemGroupCategoryTag)
	{
		return FFRegistry.getInstance()
				.block(serializedName + "/chandelier", blockFactory)
					.lang(englishName + " Chandelier")
					.lang(EN_GB, englishName + " Chandelier")

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.lightLevel(blockState -> 14)

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
}
