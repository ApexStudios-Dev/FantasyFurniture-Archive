package xyz.apex.minecraft.fantasyfurniture.common;

import net.minecraft.core.Direction;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.lib.Services;
import xyz.apex.minecraft.apexcore.common.lib.helper.TagHelper;
import xyz.apex.minecraft.apexcore.common.lib.registry.Registrar;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.BlockEntry;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.MenuEntry;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.RecipeEntry;
import xyz.apex.minecraft.apexcore.common.lib.resgen.ProviderTypes;
import xyz.apex.minecraft.apexcore.common.lib.resgen.state.MultiVariantBuilder;
import xyz.apex.minecraft.apexcore.common.lib.resgen.state.PropertyDispatch;
import xyz.apex.minecraft.apexcore.common.lib.resgen.state.Variant;
import xyz.apex.minecraft.fantasyfurniture.common.block.FurnitureStationBlock;
import xyz.apex.minecraft.fantasyfurniture.common.client.screen.FurnitureStationMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.common.menu.FurnitureStationMenu;
import xyz.apex.minecraft.fantasyfurniture.common.recipe.FurnitureStationRecipe;

@ApiStatus.NonExtendable
public interface FantasyFurniture
{
    Logger LOGGER = LogManager.getLogger();
    String ID = "fantasyfurniture";

    FantasyFurniture INSTANCE = Services.singleton(FantasyFurniture.class);

    Registrar REGISTRAR = Registrar.create(ID);

    BlockEntry<FurnitureStationBlock> FURNITURE_STATION_BLOCK = furnitureStation();
    MenuEntry<FurnitureStationMenu> FURNITURE_STATION_MENU = REGISTRAR.menu(FurnitureStationMenu::forNetwork, () -> () -> FurnitureStationMenuScreen::new);
    RecipeEntry<FurnitureStationRecipe> FURNITURE_STATION_RECIPE = REGISTRAR.recipe(FurnitureStationRecipe::fromJson, FurnitureStationRecipe::fromNetwork, FurnitureStationRecipe::toNetwork);
    TagKey<Item> FURNITURE_STATION_BINDING_AGENT = TagHelper.itemTag(ID, "binding_agent");

    default void bootstrap()
    {
        REGISTRAR.register();
        registerGenerators();
    }

    private void registerGenerators()
    {
        var descriptionKey = "pack.%s.description".formatted(ID);

        ProviderTypes.LANGUAGES.addListener(ID, (provider, lookup) -> provider
                .enUS()
                    .add(descriptionKey, "Fantasy's Furniture")
                .end()
        );

        ProviderTypes.registerDefaultMcMetaGenerator(ID, Component.translatable(descriptionKey));

        ProviderTypes.ITEM_TAGS.addListener(ID, (provider, lookup) -> provider
                .tag(FURNITURE_STATION_BINDING_AGENT)
                .addElement(Items.CLAY_BALL)
        );
    }

    private static BlockEntry<FurnitureStationBlock> furnitureStation()
    {
        return REGISTRAR
                .object("furniture_station")
                .block(FurnitureStationBlock::new)
                .copyInitialPropertiesFrom(() -> Blocks.CRAFTING_TABLE)
                .blockState((lookup, entry) -> MultiVariantBuilder
                        .builder(entry.value(), Variant.variant().model(lookup.lookup(ProviderTypes.MODELS).existingModel(ModelLocationUtils.getModelLocation(entry.value()))))
                        .with(PropertyDispatch
                                .property(BlockStateProperties.HORIZONTAL_FACING)
                                .select(Direction.EAST, Variant
                                        .variant()
                                        .yRot(Variant.Rotation.R90)
                                )
                                .select(Direction.SOUTH, Variant
                                        .variant()
                                        .yRot(Variant.Rotation.R180)
                                )
                                .select(Direction.WEST, Variant
                                        .variant()
                                        .yRot(Variant.Rotation.R270)
                                )
                                .select(Direction.NORTH, Variant.variant())
                        )
                )
                .recipe((provider, lookup, entry) -> ShapelessRecipeBuilder
                        .shapeless(RecipeCategory.MISC, entry)
                        .requires(Items.CRAFTING_TABLE)
                        .requires(Items.LEATHER)
                        .unlockedBy("has_crafting_table", provider.has(Items.CRAFTING_TABLE))
                        .unlockedBy("has_leather", provider.has(Items.LEATHER))
                        .save(provider::add)
                )
                .tag(BlockTags.MINEABLE_WITH_AXE)
                .defaultItem()
        .register();
    }
}
