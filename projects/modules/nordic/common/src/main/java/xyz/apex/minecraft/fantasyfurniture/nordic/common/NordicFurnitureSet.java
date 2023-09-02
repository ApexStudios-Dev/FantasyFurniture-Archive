package xyz.apex.minecraft.fantasyfurniture.nordic.common;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
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
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.BookshelfBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.LargeContainerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.MediumContainerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.SmallContainerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.recipe.FurnitureStationRecipe;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.block.*;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.block.entity.OvenBlockEntity;

@ApiStatus.NonExtendable
public interface NordicFurnitureSet
{
    Logger LOGGER = LogManager.getLogger();
    String ID = "fantasyfurniture_nordic";

    NordicFurnitureSet INSTANCE = Services.singleton(NordicFurnitureSet.class);

    Registrar REGISTRAR = Registrar.create(ID);

    WoodType WOOD_TYPE = FurnitureSets.woodType(REGISTRAR, builder -> builder.copyFrom(WoodType.OAK));

    BlockEntry<Block> WOOL = FurnitureSets.wool(REGISTRAR, Block::new).lang("en_us", "Nordic Wool").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<CarpetBlock> CARPET = FurnitureSets.carpet(REGISTRAR, CarpetBlock::new, WOOL).lang("en_us", "Nordic Carpet").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicWallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(REGISTRAR, WOOD_TYPE, NordicWallLightBlock::new).lang("en_us", "Nordic Wall Light").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).renderType(() -> RenderType::cutout).register();
    BlockEntry<NordicFloorLightBlock> FLOOR_LIGHT = FurnitureSets.floorLight(REGISTRAR, WOOD_TYPE, NordicFloorLightBlock::new).lang("en_us", "Nordic Floor Light").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicTableBlock> TABLE = FurnitureSets.table(REGISTRAR, WOOD_TYPE, NordicTableBlock::new).lang("en_us", "Nordic Table").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicStoolBlock> STOOL = FurnitureSets.stool(REGISTRAR, WOOD_TYPE, NordicStoolBlock::new).lang("en_us", "Nordic Stool").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicCushionBlock> CUSHION = FurnitureSets.cushion(REGISTRAR, WOOD_TYPE, NordicCushionBlock::new).lang("en_us", "Nordic Cushion").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicPaintingSmallBlock> PAINTING_SMALL = FurnitureSets.paintingSmall(REGISTRAR, WOOD_TYPE, NordicPaintingSmallBlock::new).lang("en_us", "Nordic Painting Small").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicPaintingWideBlock> PAINTING_WIDE = FurnitureSets.paintingWide(REGISTRAR, WOOD_TYPE, NordicPaintingWideBlock::new).lang("en_us", "Nordic Painting Wide").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicDrawerBlock> DRAWER = FurnitureSets.drawer(REGISTRAR, WOOD_TYPE, NordicDrawerBlock::new).lang("en_us", "Nordic Drawer").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicShelfBlock> SHELF = FurnitureSets.shelf(REGISTRAR, WOOD_TYPE, NordicShelfBlock::new).lang("en_us", "Nordic Shelf").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).renderType(() -> RenderType::cutout).register();
    BlockEntry<NordicSofaBlock> SOFA = FurnitureSets.sofa(REGISTRAR, WOOD_TYPE, NordicSofaBlock::new).lang("en_us", "Nordic Sofa").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicDeskLeftBlock> DESK_LEFT = FurnitureSets.deskLeft(REGISTRAR, WOOD_TYPE, NordicDeskLeftBlock::new).lang("en_us", "Nordic Desk Left").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).renderType(() -> RenderType::cutout).register();
    BlockEntry<NordicDeskRightBlock> DESK_RIGHT = FurnitureSets.deskRight(REGISTRAR, WOOD_TYPE, NordicDeskRightBlock::new).lang("en_us", "Nordic Desk Right").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).renderType(() -> RenderType::cutout).register();
    BlockEntry<NordicChairBlock> CHAIR = FurnitureSets.chair(REGISTRAR, WOOD_TYPE, NordicChairBlock::new).lang("en_us", "Nordic Chair").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).renderType(() -> RenderType::cutout).register();
    BlockEntry<NordicBenchBlock> BENCH = FurnitureSets.bench(REGISTRAR, WOOD_TYPE, NordicBenchBlock::new).lang("en_us", "Nordic Bench").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicBookshelfBlock> BOOKSHELF = FurnitureSets.bookshelf(REGISTRAR, WOOD_TYPE, NordicBookshelfBlock::new).lang("en_us", "Nordic Bookshelf").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicChestBlock> CHEST = FurnitureSets.chest(REGISTRAR, WOOD_TYPE, NordicChestBlock::new).lang("en_us", "Nordic Chest").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicDresserBlock> DRESSER = FurnitureSets.dresser(REGISTRAR, WOOD_TYPE, NordicDresserBlock::new).lang("en_us", "Nordic Dresser").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicWardrobeBottomBlock> WARDROBE_BOTTOM = FurnitureSets.wardrobeBottom(REGISTRAR, WOOD_TYPE, NordicWardrobeBottomBlock::new).lang("en_us", "Nordic Wardrobe Bottom").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicWardrobeTopBlock> WARDROBE_TOP = FurnitureSets.wardrobeTop(REGISTRAR, WOOD_TYPE, NordicWardrobeTopBlock::new).lang("en_us", "Nordic Wardrobe Top").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).renderType(() -> RenderType::cutout).register();
    BlockEntry<NordicBedSingleBlock> BED_SINGLE = FurnitureSets.bedSingle(REGISTRAR, WOOD_TYPE, NordicBedSingleBlock::new).lang("en_us", "Nordic Bed Single").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicBedDoubleBlock> BED_DOUBLE = FurnitureSets.bedDouble(REGISTRAR, WOOD_TYPE, NordicBedDoubleBlock::new).lang("en_us", "Nordic Bed Double").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicDoorSingleBlock> DOOR_SINGLE = FurnitureSets.doorSingle(REGISTRAR, WOOD_TYPE, NordicDoorSingleBlock::new).lang("en_us", "Nordic Door Single").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicDoorDoubleBlock> DOOR_DOUBLE = FurnitureSets.doorDouble(REGISTRAR, WOOD_TYPE, NordicDoorDoubleBlock::new).lang("en_us", "Nordic Door Double").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicChandelierLightBlock> CHANDELIER = FurnitureSets.chandelier(REGISTRAR, WOOD_TYPE, NordicChandelierLightBlock::new).lang("en_us", "Nordic Chandelier").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicLockboxBlock> LOCKBOX = FurnitureSets.lockbox(REGISTRAR, WOOD_TYPE, NordicLockboxBlock::new).lang("en_us", "Nordic Lockbox").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicCounterBlock> COUNTER = FurnitureSets.counter(REGISTRAR, WOOD_TYPE, NordicCounterBlock::new).lang("en_us", "Nordic Counter").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicOvenBlock> OVEN = FurnitureSets.oven(REGISTRAR, WOOD_TYPE, NordicOvenBlock::new).lang("en_us", "Nordic Oven").recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).renderType(() -> RenderType::cutout).register();

    BlockEntityEntry<SmallContainerBlockEntity> SMALL_CONTAINER_BLOCK_ENTITY = FurnitureSets.smallContainer(REGISTRAR, DRAWER, LOCKBOX, DESK_LEFT, DESK_RIGHT).lang("en_us", "Nordic Small Container").register();
    BlockEntityEntry<MediumContainerBlockEntity> MEDIUM_CONTAINER_BLOCK_ENTITY = FurnitureSets.mediumContainer(REGISTRAR, DRESSER, COUNTER).lang("en_us", "Nordic Medium Container").register();
    BlockEntityEntry<LargeContainerBlockEntity> LARGE_CONTAINER_BLOCK_ENTITY = FurnitureSets.largeContainer(REGISTRAR, CHEST, WARDROBE_BOTTOM).lang("en_us", "Nordic Large Container").register();
    BlockEntityEntry<BookshelfBlockEntity> BOOKSHELF_BLOCK_ENTITY = REGISTRAR.object("bookshelf").blockEntity(BookshelfBlockEntity::new).validBlock(BOOKSHELF).lang("en_us", "Nordic Bookshelf").register();
    BlockEntityEntry<OvenBlockEntity> OVEN_BLOCK_ENTITY = REGISTRAR.object("furnace").blockEntity(OvenBlockEntity::new).validBlock(OVEN).lang("en_us", "Nordic Oven").register();

    RegistryEntry<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureSets.creativeModeTab(REGISTRAR, "Nordic Furniture-Set", () -> BED_SINGLE::asStack);

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
                .save(provider, entry.getRegistryName());
    }
}
