package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullBiFunction;
import org.jetbrains.annotations.NotNull;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.client.model.generators.ConfiguredModel;
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
import xyz.apex.forge.fantasyfurniture.block.base.core.BedBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.*;

import java.util.function.Supplier;

import static com.tterrag.registrate.providers.ProviderType.ITEM_MODEL;
import static com.tterrag.registrate.providers.ProviderType.LANG;

public class ModBlocks implements StringRepresentable, Comparable<ModBlocks>
{
	public final String serializedName;
	public final String englishName;

	public final TagKey<Item> itemGroupCategoryTag;
	public final ItemGroupCategory itemGroupCategory;

	public final BlockEntry<?>[] blocks;
	public final ItemEntry<?>[] items;
	public final HitBoxes hitBoxes;

	public final BlockEntry<SetCarpetBlock> carpetBlock;
	public final BlockEntry<SetWallLightBlock> wallLightBlock;
	public final BlockEntry<SetFloorLightBlock> floorLightBlock;
	public final BlockEntry<SetTableSmallBlock> tableSmallBlock;
	public final BlockEntry<SetTableWideBlock> tableWideBlock;
	public final BlockEntry<SetTableLargeBlock> tableLargeBlock;
	public final BlockEntry<SetStoolBlock> stoolBlock;
	public final BlockEntry<SetCushionBlock> cushionBlock;
	public final BlockEntry<SetPaintingSmallBlock> paintingSmallBlock;
	public final BlockEntry<SetPaintingWideBlock> paintingWideBlock;
	public final BlockEntry<SetDrawerBlock> drawerBlock;
	public final BlockEntry<SetShelfBlock> shelfBlock;
	public final BlockEntry<SetSofaBlock> sofaBlock;
	public final BlockEntry<SetDeskBlock> deskLeftBlock;
	public final BlockEntry<SetDeskBlock> deskRightBlock;
	public final BlockEntry<SetChairBlock> chairBlock;
	public final BlockEntry<SetBenchBlock> benchBlock;
	public final BlockEntry<SetBookshelfBlock> bookshelfBlock;
	public final BlockEntry<SetChestBlock> chestBlock;
	public final BlockEntry<SetDresserBlock> dresserBlock;
	public final BlockEntry<SetWardrobeBlock> wardrobeBlock;
	public final BlockEntry<SetWardrobeTopperBlock> wardrobeTopperBlock;
	public final BlockEntry<SetBedSingleBlock> bedSingleBlock;
	public final BlockEntry<SetBedDoubleBlock> bedDoubleBlock;
	public final BlockEntry<SetChandelierBlock> chandelierBlock;
	public final BlockEntry<SetLockboxBlock> lockboxBlock;
	public final BlockEntry<DoorBlock> doorSingleBlock;
	public final BlockEntry<DoorBlock> doorDoubleBlock;
	public final BlockEntry<Block> woolBlock;

	public final ItemEntry<BlockItem> carpetBlockItem;
	public final ItemEntry<BlockItem> wallLightBlockItem;
	public final ItemEntry<BlockItem> floorLightBlockItem;
	public final ItemEntry<BlockItem> tableSmallBlockItem;
	public final ItemEntry<BlockItem> tableWideBlockItem;
	public final ItemEntry<BlockItem> tableLargeBlockItem;
	public final ItemEntry<BlockItem> stoolBlockItem;
	public final ItemEntry<BlockItem> cushionBlockItem;
	public final ItemEntry<BlockItem> paintingSmallBlockItem;
	public final ItemEntry<BlockItem> paintingWideBlockItem;
	public final ItemEntry<BlockItem> drawerBlockItem;
	public final ItemEntry<BlockItem> shelfBlockItem;
	public final ItemEntry<BlockItem> sofaBlockItem;
	public final ItemEntry<BlockItem> deskLeftBlockItem;
	public final ItemEntry<BlockItem> deskRightBlockItem;
	public final ItemEntry<BlockItem> chairBlockItem;
	public final ItemEntry<BlockItem> benchBlockItem;
	public final ItemEntry<BlockItem> bookshelfBlockItem;
	public final ItemEntry<BlockItem> chestBlockItem;
	public final ItemEntry<BlockItem> dresserBlockItem;
	public final ItemEntry<BlockItem> wardrobeBlockItem;
	public final ItemEntry<BlockItem> wardrobeTopperBlockItem;
	public final ItemEntry<BlockItem> bedSingleBlockItem;
	public final ItemEntry<BlockItem> bedDoubleBlockItem;
	public final ItemEntry<BlockItem> chandelierBlockItem;
	public final ItemEntry<BlockItem> lockboxBlockItem;
	public final ItemEntry<BlockItem> woolBlockItem;
	public final ItemEntry<BlockItem> doorSingleBlockItem;
	public final ItemEntry<BlockItem> doorDoubleBlockItem;

	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetCarpetBlock> carpetBlockFactory = SetCarpetBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetWallLightBlock> wallLightBlockFactory = SetWallLightBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetFloorLightBlock> floorLightBlockFactory = SetFloorLightBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetTableSmallBlock> tableSmallBlockFactory = SetTableSmallBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetTableWideBlock> tableWideBlockFactory = SetTableWideBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetTableLargeBlock> tableLargeBlockFactory = SetTableLargeBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetStoolBlock> stoolBlockFactory = SetStoolBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetCushionBlock> cushionBlockFactory = SetCushionBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetPaintingSmallBlock> paintingSmallBlockFactory = SetPaintingSmallBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetPaintingWideBlock> paintingWideBlockFactory = SetPaintingWideBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetDrawerBlock> drawerBlockFactory = SetDrawerBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetShelfBlock> shelfBlockFactory = SetShelfBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetSofaBlock> sofaBlockFactory = SetSofaBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetDeskBlock> deskBlockFactory = SetDeskBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetChairBlock> chairBlockFactory = SetChairBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetBenchBlock> benchBlockFactory = SetBenchBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetBookshelfBlock> bookshelfBlockFactory = SetBookshelfBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetChestBlock> chestBlockFactory = SetChestBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetDresserBlock> dresserBlockFactory = SetDresserBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetWardrobeBlock> wardrobeBlockFactory = SetWardrobeBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetWardrobeTopperBlock> wardrobeTopperBlockFactory = SetWardrobeTopperBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetBedSingleBlock> bedSingleBlockFactory = SetBedSingleBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetBedDoubleBlock> bedDoubleBlockFactory = SetBedDoubleBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetChandelierBlock> chandelierBlockFactory = SetChandelierBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, SetLockboxBlock> lockboxBlockFactory = SetLockboxBlock::new;
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, Block> woolBlockFactory = (furnitureSet, properties) -> new Block(properties);
	protected NonNullBiFunction<ModBlocks, BlockBehaviour.Properties, DoorBlock> doorBlockFactory = (furnitureSet, properties) -> new DoorBlock(properties);

	protected NonNullBiFunction<Block, Item.Properties, BlockItem> carpetBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> wallLightBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> floorLightBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> tableSmallBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> tableWideBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> tableLargeBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> stoolBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> cushionBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> paintingSmallBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> paintingWideBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> drawerBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> shelfBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> sofaBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> deskBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> chairBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> benchBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> bookshelfBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> chestBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> dresserBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> wardrobeBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> wardrobeTopperBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> bedSingleBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> bedDoubleBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> chandelierBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> lockboxBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> woolBlockItemFactory = BlockItem::new;
	protected NonNullBiFunction<Block, Item.Properties, BlockItem> doorBlockItemFactory = BlockItem::new;

	protected ModBlocks(String serializedName, Supplier<HitBoxes> hitBoxesSupplier)
	{
		this(serializedName, RegistrateLangProvider.toEnglishName(serializedName), hitBoxesSupplier);
	}

	protected ModBlocks(String serializedName, String englishName, Supplier<HitBoxes> hitBoxesSupplier)
	{
		this.serializedName = serializedName;
		this.englishName = englishName;

		hitBoxes = hitBoxesSupplier.get();

		updateFactories();

		itemGroupCategoryTag = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_category/%s".formatted(serializedName));

		woolBlock = wool(this);
		woolBlockItem = Registrations.blockItem(woolBlock);

		carpetBlock = carpet(this);
		carpetBlockItem = Registrations.blockItem(carpetBlock);

		wallLightBlock = wallLight(this);
		wallLightBlockItem = Registrations.blockItem(wallLightBlock);

		floorLightBlock = floorLight(this);
		floorLightBlockItem = Registrations.blockItem(floorLightBlock);

		tableSmallBlock = tableSmall(this);
		tableSmallBlockItem = Registrations.blockItem(tableSmallBlock);

		tableWideBlock = tableWide(this);
		tableWideBlockItem = Registrations.blockItem(tableWideBlock);

		tableLargeBlock = tableLarge(this);
		tableLargeBlockItem = Registrations.blockItem(tableLargeBlock);

		stoolBlock = stool(this);
		stoolBlockItem = Registrations.blockItem(stoolBlock);

		cushionBlock = cushion(this);
		cushionBlockItem = Registrations.blockItem(cushionBlock);

		paintingSmallBlock = paintingSmall(this);
		paintingSmallBlockItem = Registrations.blockItem(paintingSmallBlock);

		paintingWideBlock = paintingWide(this);
		paintingWideBlockItem = Registrations.blockItem(paintingWideBlock);

		drawerBlock = drawer(this);
		drawerBlockItem = Registrations.blockItem(drawerBlock);

		shelfBlock = shelf(this);
		shelfBlockItem = Registrations.blockItem(shelfBlock);

		sofaBlock = sofa(this);
		sofaBlockItem = Registrations.blockItem(sofaBlock);

		deskLeftBlock = desk(this, "left");
		deskLeftBlockItem = Registrations.blockItem(deskLeftBlock);

		deskRightBlock = desk(this, "right");
		deskRightBlockItem = Registrations.blockItem(deskRightBlock);

		chairBlock = chair(this);
		chairBlockItem = Registrations.blockItem(chairBlock);

		benchBlock = bench(this);
		benchBlockItem = Registrations.blockItem(benchBlock);

		bookshelfBlock = bookshelf(this);
		bookshelfBlockItem = Registrations.blockItem(bookshelfBlock);

		chestBlock = chest(this);
		chestBlockItem = Registrations.blockItem(chestBlock);

		dresserBlock = dresser(this);
		dresserBlockItem = Registrations.blockItem(dresserBlock);

		wardrobeBlock = wardrobeBottom(this);
		wardrobeBlockItem = Registrations.blockItem(wardrobeBlock);

		wardrobeTopperBlock = wardrobeTop(this);
		wardrobeTopperBlockItem = Registrations.blockItem(wardrobeTopperBlock);

		bedSingleBlock = bedSingle(this);
		bedSingleBlockItem = Registrations.blockItem(bedSingleBlock);

		bedDoubleBlock = bedDouble(this);
		bedDoubleBlockItem = Registrations.blockItem(bedDoubleBlock);

		chandelierBlock = chandelier(this);
		chandelierBlockItem = Registrations.blockItem(chairBlock);

		doorSingleBlock = door(this, "single");
		doorSingleBlockItem = Registrations.blockItem(chairBlock);

		doorDoubleBlock = door(this, "double");
		doorDoubleBlockItem = Registrations.blockItem(chairBlock);

		lockboxBlock = lockbox(this);
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

		FFRegistry.INSTANCE.addDataGenerator(LANG, provider -> {
			for(var entry: blocks)
			{
				entry.ifPresent(block -> {
					if(block instanceof ISeatBlock seat)
						provider.add(seat.getOccupiedTranslationKey(), "This seat is occupied");
					if(block instanceof BedBlock bed)
						provider.add(bed.getOccupiedTranslationKey(), "This bed is occupied");
				});
			}
		});

		itemGroupCategory.addTranslationGenerator(FFRegistry.INSTANCE, englishName);
		EventBusHelper.addEnqueuedListener(FMLCommonSetupEvent.class, event -> ItemGroupCategoryManager.getInstance(FFRegistry.MOD_ITEM_GROUP).addCategory(itemGroupCategory));
	}

	protected void updateFactories()
	{
	}

	public final boolean is(ModBlocks other)
	{
		return serializedName.equals(other.serializedName);
	}

	@Override
	public String getSerializedName()
	{
		return serializedName;
	}

	@Override
	public int compareTo(@NotNull ModBlocks other)
	{
		return serializedName.compareTo(other.serializedName);
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj instanceof ModBlocks other)
			return is(other);
		return false;
	}

	@Override
	public int hashCode()
	{
		return serializedName.hashCode();
	}

	@Override
	public String toString()
	{
		return "FurnitureSet['%s']".formatted(serializedName);
	}

	protected BlockBuilder<FFRegistry, Block, FFRegistry> wool(BlockBuilder<FFRegistry, Block, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, Block, FFRegistry>> wool(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, Block, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetCarpetBlock, FFRegistry> carpet(BlockBuilder<FFRegistry, SetCarpetBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetCarpetBlock, FFRegistry>> carpet(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetCarpetBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetWallLightBlock, FFRegistry> wallLight(BlockBuilder<FFRegistry, SetWallLightBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetWallLightBlock, FFRegistry>> wallLight(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetWallLightBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetFloorLightBlock, FFRegistry> floorLight(BlockBuilder<FFRegistry, SetFloorLightBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetFloorLightBlock, FFRegistry>> floorLight(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetFloorLightBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetTableSmallBlock, FFRegistry> tableSmall(BlockBuilder<FFRegistry, SetTableSmallBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetTableSmallBlock, FFRegistry>> tableSmall(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetTableSmallBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetTableWideBlock, FFRegistry> tableWide(BlockBuilder<FFRegistry, SetTableWideBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetTableWideBlock, FFRegistry>> tableWide(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetTableWideBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetTableLargeBlock, FFRegistry> tableLarge(BlockBuilder<FFRegistry, SetTableLargeBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetTableLargeBlock, FFRegistry>> tableLarge(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetTableLargeBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetStoolBlock, FFRegistry> stool(BlockBuilder<FFRegistry, SetStoolBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetStoolBlock, FFRegistry>> stool(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetStoolBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetCushionBlock, FFRegistry> cushion(BlockBuilder<FFRegistry, SetCushionBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetCushionBlock, FFRegistry>> cushion(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetCushionBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetPaintingSmallBlock, FFRegistry> paintingSmall(BlockBuilder<FFRegistry, SetPaintingSmallBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetPaintingSmallBlock, FFRegistry>> paintingSmall(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetPaintingSmallBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetPaintingWideBlock, FFRegistry> paintingWide(BlockBuilder<FFRegistry, SetPaintingWideBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetPaintingWideBlock, FFRegistry>> paintingWide(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetPaintingWideBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetDrawerBlock, FFRegistry> drawer(BlockBuilder<FFRegistry, SetDrawerBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetDrawerBlock, FFRegistry>> drawer(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetDrawerBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetShelfBlock, FFRegistry> shelf(BlockBuilder<FFRegistry, SetShelfBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetShelfBlock, FFRegistry>> shelf(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetShelfBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetSofaBlock, FFRegistry> sofa(BlockBuilder<FFRegistry, SetSofaBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetSofaBlock, FFRegistry>> sofa(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetSofaBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetDeskBlock, FFRegistry> desk(BlockBuilder<FFRegistry, SetDeskBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetDeskBlock, FFRegistry>> desk(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetDeskBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetChairBlock, FFRegistry> chair(BlockBuilder<FFRegistry, SetChairBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetChairBlock, FFRegistry>> chair(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetChairBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetBenchBlock, FFRegistry> bench(BlockBuilder<FFRegistry, SetBenchBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetBenchBlock, FFRegistry>> bench(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetBenchBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetBookshelfBlock, FFRegistry> bookshelf(BlockBuilder<FFRegistry, SetBookshelfBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetBookshelfBlock, FFRegistry>> bookshelf(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetBookshelfBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetChestBlock, FFRegistry> chest(BlockBuilder<FFRegistry, SetChestBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetChestBlock, FFRegistry>> chest(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetChestBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetDresserBlock, FFRegistry> dresser(BlockBuilder<FFRegistry, SetDresserBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetDresserBlock, FFRegistry>> dresser(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetDresserBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetWardrobeBlock, FFRegistry> wardrobeBottom(BlockBuilder<FFRegistry, SetWardrobeBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetWardrobeBlock, FFRegistry>> wardrobeBottom(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetWardrobeBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetWardrobeTopperBlock, FFRegistry> wardrobeTop(BlockBuilder<FFRegistry, SetWardrobeTopperBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetWardrobeTopperBlock, FFRegistry>> wardrobeTop(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetWardrobeTopperBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetBedSingleBlock, FFRegistry> bedSingle(BlockBuilder<FFRegistry, SetBedSingleBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetBedSingleBlock, FFRegistry>> bedSingle(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetBedSingleBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetBedDoubleBlock, FFRegistry> bedDouble(BlockBuilder<FFRegistry, SetBedDoubleBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetBedDoubleBlock, FFRegistry>> bedDouble(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetBedDoubleBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetChandelierBlock, FFRegistry> chandelier(BlockBuilder<FFRegistry, SetChandelierBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetChandelierBlock, FFRegistry>> chandelier(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetChandelierBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, DoorBlock, FFRegistry> door(BlockBuilder<FFRegistry, DoorBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, DoorBlock, FFRegistry>> door(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, DoorBlock, FFRegistry>> builder)
	{
		return builder;
	}

	protected BlockBuilder<FFRegistry, SetLockboxBlock, FFRegistry> lockbox(BlockBuilder<FFRegistry, SetLockboxBlock, FFRegistry> builder)
	{
		return builder;
	}

	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetLockboxBlock, FFRegistry>> lockbox(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetLockboxBlock, FFRegistry>> builder)
	{
		return builder;
	}

	private static BlockEntry<Block> wool(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/wool".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.woolBlockFactory.apply(furnitureSet, properties))
					.lang("%s Wool".formatted(furnitureSet.englishName))
					.initialProperties(Material.WOOL, MaterialColor.WOOL)
					.strength(.8F)
					.sound(SoundType.WOOL)
					.noOcclusion()
					.blockstate((ctx, provider) -> {
						var location = FFRegistry.INSTANCE.idString("block/%s/wool".formatted(furnitureSet.serializedName));
						provider.simpleBlock(ctx.get(), provider.models().cubeAll(location, new ResourceLocation(location)));
					})
					.tag(BlockTags.Vanilla.WOOL)
					.transform(furnitureSet::wool)

					.item(furnitureSet.woolBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ItemTags.Vanilla.WOOL, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::wool)
					.build()
		.register();
	}

	private static BlockEntry<SetCarpetBlock> carpet(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/carpet".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.carpetBlockFactory.apply(furnitureSet, properties))
					.lang("%s Carpet".formatted(furnitureSet.englishName))
					.initialProperties(Material.CLOTH_DECORATION, MaterialColor.WOOL)
					.strength(.1F)
					.sound(SoundType.WOOL)
					.noOcclusion()
					.blockstate((ctx, provider) -> provider
							.simpleBlock(ctx.get(), provider
									.models()
									.carpet(FFRegistry.INSTANCE.idString("block/%s/carpet".formatted(furnitureSet.serializedName)), FFRegistry.INSTANCE.id("block/%s/wool".formatted(furnitureSet.serializedName)))
							)
					)
					.tag(BlockTags.Vanilla.CARPETS)
					.transform(furnitureSet::carpet)

					.item(furnitureSet.carpetBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ItemTags.Vanilla.CARPETS, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::carpet)
					.build()
		.register();
	}

	private static BlockEntry<SetWallLightBlock> wallLight(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/wall_light".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.wallLightBlockFactory.apply(furnitureSet, properties))
					.lang("%s Wall Light".formatted(furnitureSet.englishName))
					.initialProperties(Material.DECORATION)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.instabreak()
					.noCollission()
			        .lightLevel(blockState -> SetWallLightBlock.isWaterLogged(blockState) ? 0 : 14)
					.blockstate(Registrations::horizontalBlock)
					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)
					.addLayer(() -> RenderType::cutout)
					.transform(furnitureSet::wallLight)

					.item(furnitureSet.wallLightBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::wallLight)
					.build()
		.register();
	}

	private static BlockEntry<SetFloorLightBlock> floorLight(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/floor_light".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.floorLightBlockFactory.apply(furnitureSet, properties))
					.lang("%s Floor Light".formatted(furnitureSet.englishName))
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
					.transform(furnitureSet::floorLight)

					.item(furnitureSet.floorLightBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE,furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::floorLight)
					.build()
		.register();
	}

	private static BlockEntry<SetTableSmallBlock> tableSmall(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/table_small".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.tableSmallBlockFactory.apply(furnitureSet, properties))
					.lang("%s Table Small".formatted(furnitureSet.englishName))
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
					.transform(furnitureSet::tableSmall)

					.item(furnitureSet.tableSmallBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::tableSmall)
					.build()
        .register();
	}

	private static BlockEntry<SetTableWideBlock> tableWide(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/table_wide".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.tableWideBlockFactory.apply(furnitureSet, properties))
					.lang("%s Table Wide".formatted(furnitureSet.englishName))
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
					.transform(furnitureSet::tableWide)

					.item(furnitureSet.tableWideBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::tableWide)
					.build()
        .register();
	}

	private static BlockEntry<SetTableLargeBlock> tableLarge(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/table_large".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.tableLargeBlockFactory.apply(furnitureSet, properties))
					.lang("%s Table Large".formatted(furnitureSet.englishName))
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
					.transform(furnitureSet::tableLarge)

					.item(furnitureSet.tableLargeBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::tableLarge)
					.build()
        .register();
	}

	private static BlockEntry<SetStoolBlock> stool(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/stool".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.stoolBlockFactory.apply(furnitureSet, properties))
					.lang("%s Stool".formatted(furnitureSet.englishName))
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
					.transform(furnitureSet::stool)

					.item(furnitureSet.stoolBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::stool)
					.build()
		.register();
	}

	private static BlockEntry<SetCushionBlock> cushion(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/cushion".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.cushionBlockFactory.apply(furnitureSet, properties))
					.lang("%s Cushion".formatted(furnitureSet.englishName))
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
					.transform(furnitureSet::cushion)

					.item(furnitureSet.cushionBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::cushion)
					.build()
		.register();
	}

	private static BlockEntry<SetPaintingSmallBlock> paintingSmall(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/painting_small".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.paintingSmallBlockFactory.apply(furnitureSet, properties))
					.lang("%s Painting Small".formatted(furnitureSet.englishName))
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
					.transform(furnitureSet::paintingSmall)

					.item(furnitureSet.paintingSmallBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::paintingSmall)
					.build()
		.register();
	}

	private static BlockEntry<SetPaintingWideBlock> paintingWide(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/painting_wide".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.paintingWideBlockFactory.apply(furnitureSet, properties))
					.lang("%s Painting Wide".formatted(furnitureSet.englishName))
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
					.transform(furnitureSet::paintingWide)

					.item(furnitureSet.paintingWideBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::paintingWide)
					.build()
		.register();
	}

	private static BlockEntry<SetDrawerBlock> drawer(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/drawer".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.drawerBlockFactory.apply(furnitureSet, properties))
					.lang("%s Drawer".formatted(furnitureSet.englishName))
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
					.transform(furnitureSet::drawer)

					.item(furnitureSet.drawerBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::drawer)
					.build()
		.register();
	}

	private static BlockEntry<SetShelfBlock> shelf(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/shelf".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.shelfBlockFactory.apply(furnitureSet, properties))
					.lang("%s Shelf".formatted(furnitureSet.englishName))
					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.blockstate((ctx, provider) -> provider.horizontalBlock(ctx.get(), blockState -> {
						var connectionType = blockState.getValue(SetShelfBlock.CONNECTION_TYPE);
						String suffix;

						if(connectionType == SetShelfBlock.ConnectionType.LEFT || connectionType == SetShelfBlock.ConnectionType.RIGHT)
							suffix = "_%s".formatted(connectionType.getSerializedName());
						else if(connectionType == SetShelfBlock.ConnectionType.BOTH)
							suffix = "_center";
						else
							suffix = "";

						return provider.models().getExistingFile(FFRegistry.INSTANCE.id("block/%s/shelf%s".formatted(furnitureSet.serializedName, suffix)));
					}, 180))
					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)
					.addLayer(() -> RenderType::cutout)
					.transform(furnitureSet::shelf)

					.item(furnitureSet.shelfBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::shelf)
					.build()
		.register();
	}

	private static BlockEntry<SetSofaBlock> sofa(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/sofa".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.sofaBlockFactory.apply(furnitureSet, properties))
					.lang("%s Sofa".formatted(furnitureSet.englishName))
					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.blockstate((ctx, provider) -> provider.getVariantBuilder(ctx.get()).forAllStates(blockState -> {
						var facing = BaseBlock.getFacing(blockState);
						var connectionType = blockState.getValue(SetSofaBlock.CONNECTION_TYPE);

						return ConfiguredModel
								.builder()
									.modelFile(provider.models().getExistingFile(FFRegistry.INSTANCE.id("block/%s/sofa_%s".formatted(furnitureSet.serializedName, connectionType.getSerializedName()))))
									.rotationY(((int) facing.toYRot() + 180) % 360)
								.build();
					}))
					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)
					.addLayer(() -> RenderType::cutout)
					.transform(furnitureSet::sofa)

					.item(furnitureSet.sofaBlockItemFactory)
						.model((ctx, provider) -> provider.withExistingParent("item/%s".formatted(ctx.getName()), FFRegistry.INSTANCE.id("block/%s/sofa_single".formatted(furnitureSet.serializedName))))
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::sofa)
					.build()
		.register();
	}

	private static BlockEntry<SetDeskBlock> desk(ModBlocks furnitureSet, String side)
	{
		return FFRegistry.INSTANCE
				.object("%s/desk_%s".formatted(furnitureSet.serializedName, side))
				.block(properties -> furnitureSet.deskBlockFactory.apply(furnitureSet, properties))
					.lang("%s Desk %s".formatted(furnitureSet.englishName, RegistrateLangProvider.toEnglishName(side)))
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
					.transform(furnitureSet::desk)

					.item(furnitureSet.deskBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::desk)
					.build()
		.register();
	}

	private static BlockEntry<SetChairBlock> chair(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/chair".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.chairBlockFactory.apply(furnitureSet, properties))
					.lang("%s Chair".formatted(furnitureSet.englishName))
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
					.transform(furnitureSet::chair)

					.item(furnitureSet.chairBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::chair)
					.build()
		.register();
	}

	private static BlockEntry<SetBenchBlock> bench(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/bench".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.benchBlockFactory.apply(furnitureSet, properties))
					.lang("%s Bench".formatted(furnitureSet.englishName))
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
					.transform(furnitureSet::bench)

					.item(furnitureSet.benchBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::bench)
					.build()
		.register();
	}

	private static BlockEntry<SetBookshelfBlock> bookshelf(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/bookshelf".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.bookshelfBlockFactory.apply(furnitureSet, properties))
					.lang("%s Bookshelf".formatted(furnitureSet.englishName))
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
					.transform(furnitureSet::bookshelf)

					.item(furnitureSet.bookshelfBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::bookshelf)
					.build()
		.register();
	}

	private static BlockEntry<SetChestBlock> chest(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/chest".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.chestBlockFactory.apply(furnitureSet, properties))
					.lang("%s Chest".formatted(furnitureSet.englishName))
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
					.transform(furnitureSet::chest)

					.item(furnitureSet.chestBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::chest)
					.build()
		.register();
	}

	private static BlockEntry<SetDresserBlock> dresser(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/dresser".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.dresserBlockFactory.apply(furnitureSet, properties))
					.lang("%s Dresser".formatted(furnitureSet.englishName))
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
					.transform(furnitureSet::dresser)

					.item(furnitureSet.dresserBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::dresser)
					.build()
		.register();
	}

	private static BlockEntry<SetWardrobeBlock> wardrobeBottom(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/wardrobe_bottom".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.wardrobeBlockFactory.apply(furnitureSet, properties))
					.lang("%s Wardrobe".formatted(furnitureSet.englishName))
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
					.transform(furnitureSet::wardrobeBottom)

					.item(furnitureSet.wardrobeBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::wardrobeBottom)
					.build()
		.register();
	}

	private static BlockEntry<SetWardrobeTopperBlock> wardrobeTop(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/wardrobe_top".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.wardrobeTopperBlockFactory.apply(furnitureSet, properties))
					.lang("%s Wardrobe Top".formatted(furnitureSet.englishName))
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
					.transform(furnitureSet::wardrobeTop)

					.item(furnitureSet.wardrobeTopperBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::wardrobeTop)
					.build()
		.register();
	}

	private static BlockEntry<SetBedSingleBlock> bedSingle(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/bed_single".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.bedSingleBlockFactory.apply(furnitureSet, properties))
					.lang("%s Bed Single".formatted(furnitureSet.englishName))
					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.blockstate((ctx, provider) -> provider
							.horizontalBlock(ctx.get(), blockState -> provider
									.models()
									.getExistingFile(Registrations.getExistingModelPath(ctx.getId(), ""))
							)
					)
					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)
					.addLayer(() -> RenderType::cutout)
					.tag(BlockTags.Vanilla.BEDS)
					.transform(furnitureSet::bedSingle)

					.item(furnitureSet.bedSingleBlockItemFactory)
						.model((ctx, provider) -> provider
								.withExistingParent(
										"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
										Registrations.getExistingModelPath(ctx.getId(), "")
								)
						)
						.tag(FurnitureStation.CRAFTABLE, ItemTags.Vanilla.BEDS, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::bedSingle)
					.build()
		.register();
	}

	private static BlockEntry<SetBedDoubleBlock> bedDouble(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/bed_double".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.bedDoubleBlockFactory.apply(furnitureSet, properties))
					.lang("%s Bed Double".formatted(furnitureSet.englishName))
					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.blockstate((ctx, provider) -> provider
							.horizontalBlock(ctx.get(), blockState -> provider
									.models()
									.getExistingFile(Registrations.getExistingModelPath(ctx.getId(), ""))
							)
					)
					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)
					.addLayer(() -> RenderType::cutout)
					.tag(BlockTags.Vanilla.BEDS)
					.transform(furnitureSet::bedDouble)

					.item(furnitureSet.bedDoubleBlockItemFactory)
						.model((ctx, provider) -> provider
								.withExistingParent(
										"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
										Registrations.getExistingModelPath(ctx.getId(), "")
								)
						)
						.tag(FurnitureStation.CRAFTABLE, ItemTags.Vanilla.BEDS, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::bedDouble)
					.build()
		.register();
	}

	private static BlockEntry<SetChandelierBlock> chandelier(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/chandelier".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.chandelierBlockFactory.apply(furnitureSet, properties))
					.lang("%s Chandelier".formatted(furnitureSet.englishName))
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
					.transform(furnitureSet::chandelier)

					.item(furnitureSet.chandelierBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::chandelier)
					.build()
		.register();
	}

	private static BlockEntry<DoorBlock> door(ModBlocks furnitureSet, String type)
	{
		return FFRegistry.INSTANCE
				.object("%s/door_%s".formatted(furnitureSet.serializedName, type))
				.block(properties -> furnitureSet.doorBlockFactory.apply(furnitureSet, properties))
					.lang("%s Door %s".formatted(furnitureSet.englishName, RegistrateLangProvider.toEnglishName(type)))
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
					.transform(furnitureSet::door)

					.item(furnitureSet.doorBlockItemFactory)
						.setData(ITEM_MODEL, NonNullBiConsumer.noop())
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag, ItemTags.Vanilla.WOODEN_DOORS)
						.transform(furnitureSet::door)
					.build()
		.register();
	}

	private static BlockEntry<SetLockboxBlock> lockbox(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("%s/lockbox".formatted(furnitureSet.serializedName))
				.block(properties -> furnitureSet.lockboxBlockFactory.apply(furnitureSet, properties))
					.lang("%s Lockbox".formatted(furnitureSet.englishName))
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
					.transform(furnitureSet::lockbox)

					.item(furnitureSet.lockboxBlockItemFactory)
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, furnitureSet.itemGroupCategoryTag)
						.transform(furnitureSet::lockbox)
					.build()
		.register();
	}
}
