package xyz.apex.minecraft.fantasyfurniture.nordic.common;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.lib.Services;
import xyz.apex.minecraft.apexcore.common.lib.hook.CreativeModeTabHooks;
import xyz.apex.minecraft.apexcore.common.lib.registry.Registrar;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.BlockEntry;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.ItemEntry;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.RegistryEntry;
import xyz.apex.minecraft.apexcore.common.lib.resgen.ProviderLookup;
import xyz.apex.minecraft.apexcore.common.lib.resgen.ProviderTypes;
import xyz.apex.minecraft.apexcore.common.lib.resgen.RecipeProvider;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.FurnitureSets;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.LargeContainerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.MediumContainerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.SmallContainerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.recipe.FurnitureStationRecipe;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.block.*;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.block.entity.BookshelfBlockEntity;

@ApiStatus.NonExtendable
public interface NordicFurnitureSet
{
    Logger LOGGER = LogManager.getLogger();
    String ID = "fantasyfurniture_nordic";

    NordicFurnitureSet INSTANCE = Services.singleton(NordicFurnitureSet.class);

    Registrar REGISTRAR = Registrar.create(ID);

    BlockEntry<Block> WOOL = FurnitureSets.wool(REGISTRAR, Block::new).lang("en_us", "Nordic Wool").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<CarpetBlock> CARPET = FurnitureSets.carpet(REGISTRAR, CarpetBlock::new, WOOL).lang("en_us", "Nordic Carpet").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicWallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(REGISTRAR, NordicWallLightBlock::new).lang("en_us", "Nordic Wall Light").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicFloorLightBlock> FLOOR_LIGHT = FurnitureSets.floorLight(REGISTRAR, NordicFloorLightBlock::new).lang("en_us", "Nordic Floor Light").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicStoolBlock> STOOL = FurnitureSets.stool(REGISTRAR, NordicStoolBlock::new).lang("en_us", "Nordic Stool").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicCushionBlock> CUSHION = FurnitureSets.cushion(REGISTRAR, NordicCushionBlock::new).lang("en_us", "Nordic Cushion").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicPaintingSmallBlock> PAINTING_SMALL = FurnitureSets.paintingSmall(REGISTRAR, NordicPaintingSmallBlock::new).lang("en_us", "Nordic Painting Small").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicPaintingWideBlock> PAINTING_WIDE = FurnitureSets.paintingWide(REGISTRAR, NordicPaintingWideBlock::new).lang("en_us", "Nordic Painting Wide").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicDrawerBlock> DRAWER = FurnitureSets.drawer(REGISTRAR, NordicDrawerBlock::new).lang("en_us", "Nordic Drawer").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicShelfBlock> SHELF = FurnitureSets.shelf(REGISTRAR, NordicShelfBlock::new).lang("en_us", "Nordic Shelf").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicSofaBlock> SOFA = FurnitureSets.sofa(REGISTRAR, NordicSofaBlock::new).lang("en_us", "Nordic Sofa").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicDeskLeftBlock> DESK_LEFT = FurnitureSets.deskLeft(REGISTRAR, NordicDeskLeftBlock::new).lang("en_us", "Nordic Desk Left").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicDeskRightBlock> DESK_RIGHT = FurnitureSets.deskRight(REGISTRAR, NordicDeskRightBlock::new).lang("en_us", "Nordic Desk Right").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicChairBlock> CHAIR = FurnitureSets.chair(REGISTRAR, NordicChairBlock::new).lang("en_us", "Nordic Chair").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicBenchBlock> BENCH = FurnitureSets.bench(REGISTRAR, NordicBenchBlock::new).lang("en_us", "Nordic Bench").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicBookshelfBlock> BOOKSHELF = FurnitureSets.bookshelf(REGISTRAR, NordicBookshelfBlock::new).lang("en_us", "Nordic Bookshelf").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicChestBlock> CHEST = FurnitureSets.chest(REGISTRAR, NordicChestBlock::new).lang("en_us", "Nordic Chest").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicDresserBlock> DRESSER = FurnitureSets.dresser(REGISTRAR, NordicDresserBlock::new).lang("en_us", "Nordic Dresser").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicWardrobeBottomBlock> WARDROBE_BOTTOM = FurnitureSets.wardrobeBottom(REGISTRAR, NordicWardrobeBottomBlock::new).lang("en_us", "Nordic Wardrobe Bottom").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicWardrobeTopBlock> WARDROBE_TOP = FurnitureSets.wardrobeTop(REGISTRAR, NordicWardrobeTopBlock::new).lang("en_us", "Nordic Wardrobe Top").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicChandelierLightBlock> CHANDELIER = FurnitureSets.chandelier(REGISTRAR, NordicChandelierLightBlock::new).lang("en_us", "Nordic Chandelier").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicLockboxBlock> LOCKBOX = FurnitureSets.lockbox(REGISTRAR, NordicLockboxBlock::new).lang("en_us", "Nordic Lockbox").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicCounterBlock> COUNTER = FurnitureSets.counter(REGISTRAR, NordicCounterBlock::new).lang("en_us", "Nordic Counter").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();

    BlockEntityEntry<SmallContainerBlockEntity> SMALL_CONTAINER_BLOCK_ENTITY = FurnitureSets.smallContainer(REGISTRAR, DRAWER, LOCKBOX, DESK_LEFT, DESK_RIGHT).lang("en_us", "Nordic Small Container").register();
    BlockEntityEntry<MediumContainerBlockEntity> MEDIUM_CONTAINER_BLOCK_ENTITY = FurnitureSets.mediumContainer(REGISTRAR, DRESSER, COUNTER).lang("en_us", "Nordic Medium Container").register();
    BlockEntityEntry<LargeContainerBlockEntity> LARGE_CONTAINER_BLOCK_ENTITY = FurnitureSets.largeContainer(REGISTRAR, CHEST, WARDROBE_BOTTOM).lang("en_us", "Nordic Large Container").register();
    BlockEntityEntry<BookshelfBlockEntity> BOOKSHELF_BLOCK_ENTITY = REGISTRAR.object("bookshelf").blockEntity(BookshelfBlockEntity::new).validBlock(BOOKSHELF).lang("en_us", "Nordic Bookshelf").register();

    RegistryEntry<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureSets.creativeModeTab(REGISTRAR, "Nordic Furniture-Set");

    default void bootstrap()
    {
        CreativeModeTabHooks.get().modify(FantasyFurniture.CREATIVE_MODE_TAB.getRegistryKey(), output -> REGISTRAR
                .getAll(Registries.ITEM)
                .stream()
                .filter(RegistryEntry::isBound)
                .map(ItemEntry::cast)
                .forEach(output::accept)
        );

        REGISTRAR.register();
        registerGenerators();
    }

    private void registerGenerators()
    {
        var descriptionKey = "pack.%s.description".formatted(ID);

        ProviderTypes.LANGUAGES.addListener(ID, (provider, lookup) -> provider
                .enUS()
                    .add(descriptionKey, "Fantasy's Furniture - Nordic")
                .end()
        );

        ProviderTypes.registerDefaultMcMetaGenerator(ID, Component.translatable(descriptionKey));
    }

    private static <B extends Block> void nordicFurnitureStationRecipe(RecipeProvider provider, ProviderLookup lookup, BlockEntry<B> entry)
    {
        FurnitureStationRecipe.builder(RecipeCategory.MISC, Ingredient.of(Items.OAK_PLANKS), Ingredient.of(Items.BROWN_WOOL), entry)
                .unlockedBy("has_oak_planks", provider.has(Items.OAK_PLANKS))
                .unlockedBy("has_brown_wool", provider.has(Items.BROWN_WOOL))
                .group("furniture_set/nordic")
                .save(provider::add, entry.getRegistryName());
    }
}
