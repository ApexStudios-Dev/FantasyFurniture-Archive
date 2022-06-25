package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
import xyz.apex.forge.apexcore.lib.util.EventBusHelper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.apexcore.revamp.block.ISeatBlock;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.commonality.tags.BlockTags;
import xyz.apex.forge.commonality.tags.ItemTags;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.block.base.core.BedBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.*;
import xyz.apex.forge.fantasyfurniture.block.bone.*;
import xyz.apex.forge.fantasyfurniture.block.dunmer.*;
import xyz.apex.forge.fantasyfurniture.block.nordic.*;
import xyz.apex.forge.fantasyfurniture.block.venthyr.*;
import xyz.apex.forge.fantasyfurniture.item.VenthyrTableBlockItem;

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
			NordicLockboxBlock::new,
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
			VenthyrLockboxBlock::new,
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
			DunmerLockboxBlock::new,
			DoorBlock::new
	),
	// endregion

	// region: Bone
	BONE(
			"bone",

			BoneCarpetBlock::new,
			BoneWallLightBlock::new,
			BoneFloorLightBlock::new,
			BoneTableSmallBlock::new,
			BoneTableWideBlock::new,
			BoneTableLargeBlock::new,
			BoneStoolBlock::new,
			BoneCushionBlock::new,
			BonePaintingSmallBlock::new,
			BonePaintingWideBlock::new,
			BoneDrawerBlock::new,
			BoneShelfBlock::new,
			BoneSofaBlock::new,
			BoneDeskBlock::new,
			BoneChairBlock::new,
			BoneBenchBlock::new,
			BoneBookshelfBlock::new,
			BoneChestBlock::new,
			BoneDresserBlock::new,
			BoneWardrobeBlock::new,
			BoneWardrobeTopperBlock::new,
			BoneBedSingleBlock::new,
			BoneBedDoubleBlock::new,
			BoneChandelierBlock::new,
			BoneLockboxBlock::new,
			DoorBlock::new
	),
	// endregion
	;

	public static final ResourceLocation VENTHYR_FANCY_TABLE_ITEM_PROPERTY = FFRegistry.INSTANCE.id("venthyr_fancy");

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
	public final BlockEntry<? extends SetLockboxBlock> lockboxBlock;
	public final ItemEntry<BlockItem> lockboxBlockItem;
	// endregion

	FurnitureSet(
			String serializedName,

			NonNullFunction<BlockBehaviour.Properties, SetCarpetBlock> carpetBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetWallLightBlock> wallLightBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetFloorLightBlock> floorLightBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetTableSmallBlock> tableSmallBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetTableWideBlock> tableWideBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetTableLargeBlock> tableLargeBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetStoolBlock> stoolBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetCushionBlock> cushionBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetPaintingSmallBlock> paintingSmallBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetPaintingWideBlock> paintingWideBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetDrawerBlock> drawerBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetShelfBlock> shelfBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetSofaBlock> sofaBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetDeskBlock> deskBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetChairBlock> chairBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetBenchBlock> benchBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetBookshelfBlock> bookshelfBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetChestBlock> chestBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetDresserBlock> dresserBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetWardrobeBlock> wardrobeBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetWardrobeTopperBlock> wardrobeTopperBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetBedSingleBlock> bedSingleBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetBedDoubleBlock> bedDoubleBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetChandelierBlock> chandelierBlockFactory,
			NonNullFunction<BlockBehaviour.Properties, SetLockboxBlock> lockboxFactory,
			NonNullFunction<BlockBehaviour.Properties, DoorBlock> doorBlockFactory
	)
	{
		this.serializedName = serializedName;

		var isVenthyr = serializedName.equals("venthyr");

		englishName = RegistrateLangProvider.toEnglishName(serializedName);
		itemGroupCategoryTag = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_category/" + serializedName);

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

		lockboxBlock = lockbox(lockboxFactory, serializedName, englishName, itemGroupCategoryTag);
		lockboxBlockItem = Registrations.blockItem(lockboxBlock);

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
				doorDoubleBlock,
				lockboxBlock
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
				doorDoubleBlockItem,
				lockboxBlockItem
		};

		itemGroupCategory = ItemGroupCategory
			.builder(itemGroupCategoryTag.location().toString())
				.tagged(itemGroupCategoryTag)
				.defaultIcon(bedSingleBlock::asStack)
		.build();
	}

	static void bootstrap()
	{
		for(var furnitureSet : values())
		{
			FFRegistry.INSTANCE.addDataGenerator(LANG, provider -> {
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
					.addTranslationGenerator(FFRegistry.INSTANCE, furnitureSet.englishName);

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
		return FFRegistry.INSTANCE
				.object("%s/wool".formatted(serializedName))
				.block(Block::new)
					.lang("%s Wool".formatted(englishName))

					.initialProperties(Material.WOOL, MaterialColor.WOOL)
					.strength(.8F)
					.sound(SoundType.WOOL)
					.noOcclusion()

					.blockstate((ctx, provider) -> {
						var location = FFRegistry.INSTANCE.idString("block/%s/wool".formatted(serializedName));
						provider.simpleBlock(ctx.get(), provider.models().cubeAll(location, new ResourceLocation(location)));
					})

					.tag(BlockTags.Vanilla.WOOL)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ItemTags.Vanilla.WOOL, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Carpet
	private static <BLOCK extends SetCarpetBlock> BlockEntry<BLOCK> carpet(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/carpet".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Carpet".formatted(englishName))

					.initialProperties(Material.CLOTH_DECORATION, MaterialColor.WOOL)
					.strength(.1F)
					.sound(SoundType.WOOL)
					.noOcclusion()

					.blockstate((ctx, provider) -> provider
							.simpleBlock(ctx.get(), provider
									.models()
									.carpet(FFRegistry.INSTANCE.idString("block/%s/carpet".formatted(serializedName)), FFRegistry.INSTANCE.id("block/%s/wool".formatted(serializedName)))
							)
					)

					.tag(BlockTags.Vanilla.CARPETS)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ItemTags.Vanilla.CARPETS, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Wall Light
	private static <BLOCK extends SetWallLightBlock> BlockEntry<BLOCK> wallLight(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/wall_light".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Wall Light".formatted(englishName))

					.initialProperties(Material.DECORATION)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.instabreak()
					.noCollission()
			        .lightLevel(blockState -> serializedName.equals("dunmer") || !SetWallLightBlock.isWaterLogged(blockState) ? 14 : 0)

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Floor Light
	private static <BLOCK extends SetFloorLightBlock> BlockEntry<BLOCK> floorLight(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/floor_light".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Floor Light".formatted(englishName))

					.initialProperties(Material.DECORATION)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.instabreak()
					.lightLevel(blockState -> blockState.getValue(SetFloorLightBlock.WATERLOGGED) || blockState.getValue(SetFloorLightBlock.SIDE) == SetFloorLightBlock.Side.BOTTOM ? 0 : 14)

					.blockstate((ctx, provider) -> provider.simpleBlock(ctx.get(), provider.models().getExistingFile(Registrations.getExistingModelPath(ctx.getId(), ""))))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Table
	// region: Small
	private static <BLOCK extends SetTableSmallBlock> BlockEntry<BLOCK> tableSmall(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/table_small".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Table Small".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
        .register();
	}

	private static <BLOCK extends SetTableSmallBlock> BlockEntry<BLOCK> venthyrTableSmall(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/table_small".formatted(serializedName))
				.block(blockFactory)
					.setData(LANG, (ctx, provider) -> {
			            var block = ctx.get();
			            provider.add(block, "%s Table Small".formatted(englishName));
			            provider.add("%s.fancy".formatted(block.getDescriptionId()), "Fancy %s Table Small".formatted(englishName));
		            })

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate((ctx, provider) -> {
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
				                    .withPool(RegistrateBlockLootTables
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

					.addLayer(() -> RenderType::cutout)

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
	private static <BLOCK extends SetTableWideBlock> BlockEntry<BLOCK> tableWide(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/table_wide".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Table Wide".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
        .register();
	}

	private static <BLOCK extends SetTableWideBlock> BlockEntry<BLOCK> venthyrTableWide(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/table_wide".formatted(serializedName))
				.block(blockFactory)
					.setData(LANG, (ctx, provider) -> {
			            var block = ctx.get();
			            provider.add(block, "%s Table Wide".formatted(englishName));
			            provider.add("%s.fancy".formatted(block.getDescriptionId()), "Fancy %s Table Wide".formatted(englishName));
		            })

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate((ctx, provider) -> {
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
				                    .withPool(RegistrateBlockLootTables
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

					.addLayer(() -> RenderType::cutout)

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
	private static <BLOCK extends SetTableLargeBlock> BlockEntry<BLOCK> tableLarge(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/table_large".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Table Large".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
        .register();
	}

	private static <BLOCK extends SetTableLargeBlock> BlockEntry<BLOCK> venthyrTableLarge(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/table_large".formatted(serializedName))
				.block(blockFactory)
					.setData(LANG, (ctx, provider) -> {
			            var block = ctx.get();
			            provider.add(block, "%s Table Large".formatted(englishName));
			            provider.add("%s.fancy".formatted(block.getDescriptionId()), "Fancy %s Table Large".formatted(englishName));
		            })

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate((ctx, provider) -> {
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
				                    .withPool(RegistrateBlockLootTables
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

					.addLayer(() -> RenderType::cutout)

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
	private static <BLOCK extends SetStoolBlock> BlockEntry<BLOCK> stool(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/stool".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Stool".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Cushion
	private static <BLOCK extends SetCushionBlock> BlockEntry<BLOCK> cushion(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/cushion".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Cushion".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Painting
	// region: Small
	private static <BLOCK extends SetPaintingSmallBlock> BlockEntry<BLOCK> paintingSmall(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/painting_small".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Painting Small".formatted(englishName))

					.initialProperties(Material.WOOD)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.instabreak()
					.noCollission()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Wide
	private static <BLOCK extends SetPaintingWideBlock> BlockEntry<BLOCK> paintingWide(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/painting_wide".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Painting Wide".formatted(englishName))

					.initialProperties(Material.WOOD)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.instabreak()
					.noCollission()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion
	// endregion

	// region: Drawer
	private static <BLOCK extends SetDrawerBlock> BlockEntry<BLOCK> drawer(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/drawer".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Drawer".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Shelf
	private static <BLOCK extends SetShelfBlock> BlockEntry<BLOCK> shelf(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/shelf".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Shelf".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate((ctx, provider) -> provider.horizontalBlock(ctx.get(), blockState -> {
						var connectionType = blockState.getValue(SetShelfBlock.CONNECTION_TYPE);
						String suffix;

						if(connectionType == SetShelfBlock.ConnectionType.LEFT || connectionType == SetShelfBlock.ConnectionType.RIGHT)
							suffix = "_" + connectionType.getSerializedName();
						else if(connectionType == SetShelfBlock.ConnectionType.BOTH)
							suffix = "_center";
						else
							suffix = "";

						return provider.models().getExistingFile(FFRegistry.INSTANCE.id("block/%s/shelf%s".formatted(serializedName, suffix)));
					}, 180))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Sofa
	private static <BLOCK extends SetSofaBlock> BlockEntry<BLOCK> sofa(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/sofa".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Sofa".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate((ctx, provider) -> provider.getVariantBuilder(ctx.get()).forAllStates(blockState -> {
						var facing = BaseBlock.getFacing(blockState);
						var connectionType = blockState.getValue(SetSofaBlock.CONNECTION_TYPE);

						return ConfiguredModel
								.builder()
									.modelFile(provider.models().getExistingFile(FFRegistry.INSTANCE.id("block/%s/sofa_%s".formatted(serializedName, connectionType.getSerializedName()))))
									.rotationY(((int) facing.toYRot() + 180) % 360)
								.build();
					}))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> provider.withExistingParent("item/%s".formatted(ctx.getName()), FFRegistry.INSTANCE.id("block/%s/sofa_single".formatted(serializedName))))
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Desk
	private static <BLOCK extends SetDeskBlock> BlockEntry<BLOCK> desk(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, String side, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/desk_%s".formatted(serializedName, side))
				.block(blockFactory)
					.lang("%s Desk %s".formatted(englishName, RegistrateLangProvider.toEnglishName(side)))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Chair
	private static <BLOCK extends SetChairBlock> BlockEntry<BLOCK> chair(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/chair".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Chair".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Bench
	private static <BLOCK extends SetBenchBlock> BlockEntry<BLOCK> bench(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/bench".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Bench".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Bookshelf
	private static <BLOCK extends SetBookshelfBlock> BlockEntry<BLOCK> bookshelf(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/bookshelf".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Bookshelf".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Chest
	private static <BLOCK extends SetChestBlock> BlockEntry<BLOCK> chest(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/chest".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Chest".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Dresser
	private static <BLOCK extends SetDresserBlock> BlockEntry<BLOCK> dresser(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/dresser".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Dresser".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Wardrobe
	// region: Bottom
	private static <BLOCK extends SetWardrobeBlock> BlockEntry<BLOCK> wardrobeBottom(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/wardrobe_bottom".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Wardrobe".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Top
	private static <BLOCK extends SetWardrobeTopperBlock> BlockEntry<BLOCK> wardrobeTop(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/wardrobe_top".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Wardrobe Top".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

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
	private static <BLOCK extends SetBedSingleBlock> BlockEntry<BLOCK> bedSingle(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/bed_single".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Bed Single".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate((ctx, provider) -> {
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

					.addLayer(() -> RenderType::cutout)

					.tag(BlockTags.Vanilla.BEDS)

					.item()
						.model((ctx, provider) -> {
							var id = ctx.getId();
							var suffix = "";

							if(serializedName.equals("dunmer"))
								suffix = "_full";

							provider.withExistingParent(id.getNamespace() + ":item/" + id.getPath(), Registrations.getExistingModelPath(id, suffix));
						})
						.tag(FurnitureStation.CRAFTABLE, ItemTags.Vanilla.BEDS, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Double
	private static <BLOCK extends SetBedDoubleBlock> BlockEntry<BLOCK> bedDouble(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/bed_double".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Bed Double".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate((ctx, provider) -> {
						ResourceLocation id = ctx.getId();

						provider.horizontalBlock(ctx.get(), blockState -> {
							String suffix = "";

							if(serializedName.equals("dunmer"))
								suffix = ((SetBedDoubleBlock) blockState.getBlock()).isMultiBlockOrigin(blockState) ? "_head" : "_foot";

							return provider.models().getExistingFile(Registrations.getExistingModelPath(id, suffix));
						});
					})

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.tag(BlockTags.Vanilla.BEDS)

					.item()
						.model((ctx, provider) -> {
							ResourceLocation id = ctx.getId();
							String suffix = "";

							if(serializedName.equals("dunmer"))
								suffix = "_full";

							provider.withExistingParent(id.getNamespace() + ":item/" + id.getPath(), Registrations.getExistingModelPath(id, suffix));
						})
						.tag(FurnitureStation.CRAFTABLE, ItemTags.Vanilla.BEDS, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion
	// endregion

	// region: Chandelier
	private static <BLOCK extends SetChandelierBlock> BlockEntry<BLOCK> chandelier(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/chandelier".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Chandelier".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.lightLevel(blockState -> blockState.getValue(SetChandelierBlock.WATERLOGGED) ? 0 : 14)

					.blockstate(Registrations::simpleBlockWithStates)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Door
	private static <BLOCK extends DoorBlock> BlockEntry<BLOCK> door(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, String type, TagKey<Item> itemGroupCategoryTag)
	{
		String engName;

		if(serializedName.equals("dunmer"))
			engName = englishName + " Door " + (type.equals("single") ? 1 : 2);
		else
			engName = englishName + " Door " + RegistrateLangProvider.toEnglishName(type);

		return FFRegistry.INSTANCE
				.object("%s/door_%s".formatted(serializedName, type))
				.block(blockFactory)
					.lang(engName)

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate((ctx, provider) -> {
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

					.addLayer(() -> RenderType::cutout)

			        .tag(BlockTags.Vanilla.WOODEN_DOORS)

					.item()
						.setData(ITEM_MODEL, NonNullBiConsumer.noop())
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag, ItemTags.Vanilla.WOODEN_DOORS)
					.build()
		.register();
	}
	// endregion

	// region: Lockbox
	private static <BLOCK extends SetLockboxBlock> BlockEntry<BLOCK> lockbox(NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory, String serializedName, String englishName, TagKey<Item> itemGroupCategoryTag)
	{
		return FFRegistry.INSTANCE
				.object("%s/lockbox".formatted(serializedName))
				.block(blockFactory)
					.lang("%s Lockbox".formatted(englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion
}
