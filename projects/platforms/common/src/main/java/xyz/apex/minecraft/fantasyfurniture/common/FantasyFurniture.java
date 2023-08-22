package xyz.apex.minecraft.fantasyfurniture.common;

import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.lib.Services;
import xyz.apex.minecraft.apexcore.common.lib.helper.TagHelper;
import xyz.apex.minecraft.apexcore.common.lib.registry.Registrar;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.*;
import xyz.apex.minecraft.apexcore.common.lib.resgen.ProviderTypes;
import xyz.apex.minecraft.apexcore.common.lib.resgen.state.MultiVariantBuilder;
import xyz.apex.minecraft.apexcore.common.lib.resgen.state.Variant;
import xyz.apex.minecraft.fantasyfurniture.common.block.FurnitureStationBlock;
import xyz.apex.minecraft.fantasyfurniture.common.client.screen.FurnitureStationMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.common.client.screen.LargeContainerMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.common.client.screen.MediumContainerMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.common.client.screen.SmallContainerMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.common.entity.SeatEntity;
import xyz.apex.minecraft.fantasyfurniture.common.menu.FurnitureStationMenu;
import xyz.apex.minecraft.fantasyfurniture.common.menu.LargeContainerMenu;
import xyz.apex.minecraft.fantasyfurniture.common.menu.MediumContainerMenu;
import xyz.apex.minecraft.fantasyfurniture.common.menu.SmallContainerMenu;
import xyz.apex.minecraft.fantasyfurniture.common.recipe.FurnitureStationRecipe;

@ApiStatus.NonExtendable
public interface FantasyFurniture
{
    Logger LOGGER = LogManager.getLogger();
    String ID = "fantasyfurniture";

    FantasyFurniture INSTANCE = Services.singleton(FantasyFurniture.class);

    Registrar REGISTRAR = Registrar.create(ID);

    MenuEntry<SmallContainerMenu> SMALL_MENU = REGISTRAR.object("small_container").menu(SmallContainerMenu::forNetwork, () -> () -> SmallContainerMenuScreen::new);
    MenuEntry<MediumContainerMenu> MEDIUM_MENU = REGISTRAR.object("medium_container").menu(MediumContainerMenu::forNetwork, () -> () -> MediumContainerMenuScreen::new);
    MenuEntry<LargeContainerMenu> LARGE_MENU = REGISTRAR.object("large_container").menu(LargeContainerMenu::forNetwork, () -> () -> LargeContainerMenuScreen::new);

    BlockEntry<FurnitureStationBlock> FURNITURE_STATION_BLOCK = furnitureStation();
    MenuEntry<FurnitureStationMenu> FURNITURE_STATION_MENU = REGISTRAR.menu(FurnitureStationMenu::forNetwork, () -> () -> FurnitureStationMenuScreen::new);
    RecipeEntry<FurnitureStationRecipe> FURNITURE_STATION_RECIPE = REGISTRAR.recipe(FurnitureStationRecipe::fromJson, FurnitureStationRecipe::fromNetwork, FurnitureStationRecipe::toNetwork);
    TagKey<Item> FURNITURE_STATION_BINDING_AGENT = TagHelper.itemTag(ID, "binding_agent");

    EntityEntry<SeatEntity> SEAT_ENTITY = seat();
    TagKey<EntityType<?>> SEAT_BLACKLIST = TagHelper.entityTag(ID, "seat_blacklist");

    RegistryEntry<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureSets.creativeModeTab(REGISTRAR, "Fantasy's Furniture");

    default void bootstrap()
    {
        REGISTRAR.register();
        registerGenerators();
    }

    default InteractionResult onEntityInteract(Level level, Player player, InteractionHand hand, Entity entity)
    {
        return SeatEntity.tryStandUp(level, entity);
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

        ProviderTypes.ENTITY_TYPE_TAGS.addListener(ID, (provider, lookup) -> provider
                .tag(SEAT_BLACKLIST)
                .addElement(EntityType.SHULKER)
        );
    }

    private static BlockEntry<FurnitureStationBlock> furnitureStation()
    {
        return REGISTRAR
                .object("furniture_station")
                .block(FurnitureStationBlock::new)
                .copyInitialPropertiesFrom(() -> Blocks.CRAFTING_TABLE)
                .blockState((lookup, entry) -> MultiVariantBuilder
                        .builder(entry.value(), Variant.variant().model(lookup.lookup(ProviderTypes.MODELS).getModelPath(entry.value())))
                        .with(FurnitureSets.facingProperties())
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

    private static EntityEntry<SeatEntity> seat()
    {
        return REGISTRAR
                .object("seat")
                .entity(MobCategory.MISC, SeatEntity::new)
                .renderer(() -> () ->NoopRenderer::new)
                .tag(EntityTypeTags.NON_CONTROLLING_RIDER)
                .sized(.25F, .35F)
                .noSummon()
                .clearLootTable()
        .register();
    }
}
