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

@ApiStatus.NonExtendable
public interface NordicFurnitureSet
{
    Logger LOGGER = LogManager.getLogger();
    String ID = "fantasyfurniture_nordic";

    NordicFurnitureSet INSTANCE = Services.singleton(NordicFurnitureSet.class);

    Registrar REGISTRAR = Registrar.create(ID);

    BlockEntry<Block> WOOL = FurnitureSets.wool(REGISTRAR, Block::new).recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<CarpetBlock> CARPET = FurnitureSets.carpet(REGISTRAR, CarpetBlock::new, WOOL).recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicStoolBlock> STOOL = FurnitureSets.stool(REGISTRAR, NordicStoolBlock::new).recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicCushionBlock> CUSHION = FurnitureSets.cushion(REGISTRAR, NordicCushionBlock::new).recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicPaintingSmallBlock> PAINTING_SMALL = FurnitureSets.paintingSmall(REGISTRAR, NordicPaintingSmallBlock::new).recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicPaintingWideBlock> PAINTING_WIDE = FurnitureSets.paintingWide(REGISTRAR, NordicPaintingWideBlock::new).recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicDrawerBlock> DRAWER = FurnitureSets.drawer(REGISTRAR, NordicDrawerBlock::new).recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();
    BlockEntry<NordicSofaBlock> SOFA = FurnitureSets.sofa(REGISTRAR, NordicSofaBlock::new).recipe(NordicFurnitureSet::nordicFurnitureStationRecipe).register();

    BlockEntityEntry<SmallContainerBlockEntity> SMALL_CONTAINER_BLOCK_ENTITY = FurnitureSets.smallContainer(REGISTRAR, DRAWER).register();
    BlockEntityEntry<MediumContainerBlockEntity> MEDIUM_CONTAINER_BLOCK_ENTITY = FurnitureSets.mediumContainer(REGISTRAR).register();
    BlockEntityEntry<LargeContainerBlockEntity> LARGE_CONTAINER_BLOCK_ENTITY = FurnitureSets.largeContainer(REGISTRAR).register();

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
