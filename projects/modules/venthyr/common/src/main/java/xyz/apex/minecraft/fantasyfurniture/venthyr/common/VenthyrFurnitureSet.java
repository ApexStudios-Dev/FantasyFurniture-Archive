package xyz.apex.minecraft.fantasyfurniture.venthyr.common;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
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
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.block.*;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.block.entity.OvenBlockEntity;

@ApiStatus.NonExtendable
public interface VenthyrFurnitureSet
{
    Logger LOGGER = LogManager.getLogger();
    String ID = "fantasyfurniture_venthyr";

    VenthyrFurnitureSet INSTANCE = Services.singleton(VenthyrFurnitureSet.class);

    Registrar REGISTRAR = Registrar.create(ID);
    Component JEI_NAME = Component.translatable("gui.jei.category.furniture_set.venthyr");

    WoodType WOOD_TYPE = FurnitureSets.woodType(REGISTRAR, builder -> builder.copyFrom(WoodType.OAK));

    BlockEntry<Block> WOOL = FurnitureSets.wool(REGISTRAR, Block::new).lang("en_us", "Venthyr Wool").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<CarpetBlock> CARPET = FurnitureSets.carpet(REGISTRAR, CarpetBlock::new, WOOL).lang("en_us", "Venthyr Carpet").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrWallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(REGISTRAR, WOOD_TYPE, VenthyrWallLightBlock::new).lang("en_us", "Venthyr Wall Light").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).renderType(() -> RenderType::cutout).register();
    BlockEntry<VenthyrFloorLightBlock> FLOOR_LIGHT = FurnitureSets.floorLight(REGISTRAR, WOOD_TYPE, VenthyrFloorLightBlock::new).lang("en_us", "Venthyr Floor Light").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrTableBlock> TABLE = FurnitureSets.table(REGISTRAR, WOOD_TYPE, VenthyrTableBlock::new).lang("en_us", "Venthyr Table").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrStoolBlock> STOOL = FurnitureSets.stool(REGISTRAR, WOOD_TYPE, VenthyrStoolBlock::new).lang("en_us", "Venthyr Stool").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrCushionBlock> CUSHION = FurnitureSets.cushion(REGISTRAR, WOOD_TYPE, VenthyrCushionBlock::new).lang("en_us", "Venthyr Cushion").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrPaintingSmallBlock> PAINTING_SMALL = FurnitureSets.paintingSmall(REGISTRAR, WOOD_TYPE, VenthyrPaintingSmallBlock::new).lang("en_us", "Venthyr Painting Small").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrPaintingWideBlock> PAINTING_WIDE = FurnitureSets.paintingWide(REGISTRAR, WOOD_TYPE, VenthyrPaintingWideBlock::new).lang("en_us", "Venthyr Painting Wide").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrDrawerBlock> DRAWER = FurnitureSets.drawer(REGISTRAR, WOOD_TYPE, VenthyrDrawerBlock::new).lang("en_us", "Venthyr Drawer").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrShelfBlock> SHELF = FurnitureSets.shelf(REGISTRAR, WOOD_TYPE, VenthyrShelfBlock::new).lang("en_us", "Venthyr Shelf").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).renderType(() -> RenderType::cutout).register();
    BlockEntry<VenthyrSofaBlock> SOFA = FurnitureSets.sofa(REGISTRAR, WOOD_TYPE, VenthyrSofaBlock::new).lang("en_us", "Venthyr Sofa").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrDeskLeftBlock> DESK_LEFT = FurnitureSets.deskLeft(REGISTRAR, WOOD_TYPE, VenthyrDeskLeftBlock::new).lang("en_us", "Venthyr Desk Left").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).renderType(() -> RenderType::cutout).register();
    BlockEntry<VenthyrDeskRightBlock> DESK_RIGHT = FurnitureSets.deskRight(REGISTRAR, WOOD_TYPE, VenthyrDeskRightBlock::new).lang("en_us", "Venthyr Desk Right").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).renderType(() -> RenderType::cutout).register();
    BlockEntry<VenthyrChairBlock> CHAIR = FurnitureSets.chair(REGISTRAR, WOOD_TYPE, VenthyrChairBlock::new).lang("en_us", "Venthyr Chair").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).renderType(() -> RenderType::cutout).register();
    BlockEntry<VenthyrBenchBlock> BENCH = FurnitureSets.bench(REGISTRAR, WOOD_TYPE, VenthyrBenchBlock::new).lang("en_us", "Venthyr Bench").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrBookshelfBlock> BOOKSHELF = FurnitureSets.bookshelf(REGISTRAR, WOOD_TYPE, VenthyrBookshelfBlock::new).lang("en_us", "Venthyr Bookshelf").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrChestBlock> CHEST = FurnitureSets.chest(REGISTRAR, WOOD_TYPE, VenthyrChestBlock::new).lang("en_us", "Venthyr Chest").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrDresserBlock> DRESSER = FurnitureSets.dresser(REGISTRAR, WOOD_TYPE, VenthyrDresserBlock::new).lang("en_us", "Venthyr Dresser").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrWardrobeBottomBlock> WARDROBE_BOTTOM = FurnitureSets.wardrobeBottom(REGISTRAR, WOOD_TYPE, VenthyrWardrobeBottomBlock::new).lang("en_us", "Venthyr Wardrobe Bottom").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrWardrobeTopBlock> WARDROBE_TOP = FurnitureSets.wardrobeTop(REGISTRAR, WOOD_TYPE, VenthyrWardrobeTopBlock::new).lang("en_us", "Venthyr Wardrobe Top").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).renderType(() -> RenderType::cutout).register();
    BlockEntry<VenthyrBedSingleBlock> BED_SINGLE = FurnitureSets.bedSingle(REGISTRAR, WOOD_TYPE, VenthyrBedSingleBlock::new).lang("en_us", "Venthyr Bed Single").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrBedDoubleBlock> BED_DOUBLE = FurnitureSets.bedDouble(REGISTRAR, WOOD_TYPE, VenthyrBedDoubleBlock::new).lang("en_us", "Venthyr Bed Double").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrDoorSingleBlock> DOOR_SINGLE = FurnitureSets.doorSingle(REGISTRAR, WOOD_TYPE, VenthyrDoorSingleBlock::new).lang("en_us", "Venthyr Door Single").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrDoorDoubleBlock> DOOR_DOUBLE = FurnitureSets.doorDouble(REGISTRAR, WOOD_TYPE, VenthyrDoorDoubleBlock::new).lang("en_us", "Venthyr Door Double").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrChandelierLightBlock> CHANDELIER = FurnitureSets.chandelier(REGISTRAR, WOOD_TYPE, VenthyrChandelierLightBlock::new).lang("en_us", "Venthyr Chandelier").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrLockboxBlock> LOCKBOX = FurnitureSets.lockbox(REGISTRAR, WOOD_TYPE, VenthyrLockboxBlock::new).lang("en_us", "Venthyr Lockbox").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrCounterBlock> COUNTER = FurnitureSets.counter(REGISTRAR, WOOD_TYPE, VenthyrCounterBlock::new).lang("en_us", "Venthyr Counter").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).register();
    BlockEntry<VenthyrOvenBlock> OVEN = FurnitureSets.oven(REGISTRAR, WOOD_TYPE, VenthyrOvenBlock::new).lang("en_us", "Venthyr Oven").recipe(VenthyrFurnitureSet::venthyrFurnitureStationRecipe).renderType(() -> RenderType::cutout).register();

    BlockEntityEntry<SmallContainerBlockEntity> SMALL_CONTAINER_BLOCK_ENTITY = FurnitureSets.smallContainer(REGISTRAR, DRAWER, LOCKBOX, DESK_LEFT, DESK_RIGHT).lang("en_us", "Venthyr Small Container").register();
    BlockEntityEntry<MediumContainerBlockEntity> MEDIUM_CONTAINER_BLOCK_ENTITY = FurnitureSets.mediumContainer(REGISTRAR, DRESSER, COUNTER).lang("en_us", "Venthyr Medium Container").register();
    BlockEntityEntry<LargeContainerBlockEntity> LARGE_CONTAINER_BLOCK_ENTITY = FurnitureSets.largeContainer(REGISTRAR, CHEST, WARDROBE_BOTTOM).lang("en_us", "Venthyr Large Container").register();
    BlockEntityEntry<BookshelfBlockEntity> BOOKSHELF_BLOCK_ENTITY = REGISTRAR.object("bookshelf").blockEntity(BookshelfBlockEntity::new).validBlock(BOOKSHELF).lang("en_us", "Venthyr Bookshelf").register();
    BlockEntityEntry<OvenBlockEntity> OVEN_BLOCK_ENTITY = REGISTRAR.object("furnace").blockEntity(OvenBlockEntity::new).validBlock(OVEN).lang("en_us", "Venthyr Oven").register();

    RegistryEntry<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureSets.creativeModeTab(REGISTRAR, "Venthyr Furniture-Set", () -> BED_SINGLE::asStack);

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
                    .add(descriptionKey, "Fantasy's Furniture - Venthyr")
                    .add(((TranslatableContents) JEI_NAME.getContents()).getKey(), "Venthyr Furniture Set")
                .end()
        );

        ProviderTypes.registerDefaultMcMetaGenerator(ID, Component.translatable(descriptionKey));
    }

    private static <B extends Block> void venthyrFurnitureStationRecipe(RecipeProvider provider, ProviderLookup lookup, BlockEntry<B> entry)
    {
        FurnitureStationRecipe.builder(RecipeCategory.MISC, Ingredient.of(Items.OAK_PLANKS), Ingredient.of(Items.BROWN_WOOL), entry)
                .unlockedBy("has_oak_planks", provider.has(Items.OAK_PLANKS))
                .unlockedBy("has_brown_wool", provider.has(Items.BROWN_WOOL))
                .group("furniture_set/venthyr")
                .save(provider, entry.getRegistryName());
    }
}
