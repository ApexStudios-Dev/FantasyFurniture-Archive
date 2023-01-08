package xyz.apex.minecraft.fantasyfurniture.forge;

import net.minecraft.data.DataProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import xyz.apex.minecraft.fantasyfurniture.forge.data.*;
import xyz.apex.minecraft.fantasyfurniture.forge.dummies.DummyEntity;
import xyz.apex.minecraft.fantasyfurniture.forge.dummies.DummyHorizontalFacingBlock;

import java.util.function.BiFunction;
import java.util.function.Function;

@Mod(FantasyFurnitureDataMod.ID)
public final class FantasyFurnitureDataMod
{
    public static final String ID = "fantasyfurniture";

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, ID);

    public static final RegistryObject<EntityType<?>> SEAT_ENTITY_TYPE = dummyEntityType("seat");
    public static final RegistryObject<DummyHorizontalFacingBlock> FURNITURE_STATION_BLOCK = dummyBlock("furniture_station", DummyHorizontalFacingBlock::new);
    public static final RegistryObject<RecipeSerializer<FurnitureStationRecipe>> FURNITURE_STATION_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("furniture_station", FurnitureStationRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<FurnitureStationRecipe>> FURNITURE_STATION_RECIPE_TYPE = RECIPE_TYPES.register("furniture_station", () -> new RecipeType<>() {});

    public FantasyFurnitureDataMod()
    {
        var modBus = FMLJavaModLoadingContext.get().getModEventBus();

        Nordic.bootstrap();

        BLOCKS.register(modBus);
        ITEMS.register(modBus);
        ENTITY_TYPES.register(modBus);
        RECIPE_SERIALIZERS.register(modBus);
        RECIPE_TYPES.register(modBus);

        modBus.addListener(this::onGatherData);
    }

    private void onGatherData(GatherDataEvent event)
    {
        var generator = event.getGenerator();

        var client = event.includeClient();
        var server = event.includeServer();

        generator.addProvider(client, (DataProvider.Factory<BlockBenchConverter>) output -> new BlockBenchConverter(event, output));
        generator.addProvider(client, (DataProvider.Factory<BlockStateGenerator>) output -> new BlockStateGenerator(event, output));
        generator.addProvider(client, (DataProvider.Factory<ItemModelGenerator>) output -> new ItemModelGenerator(event, output));

        generator.addProvider(client, (DataProvider.Factory<LanguageGenerator>) LanguageGenerator::new);

        var blockTags = generator.addProvider(server, (DataProvider.Factory<BlockTagGenerator>) output -> new BlockTagGenerator(event, output));
        generator.addProvider(server, (DataProvider.Factory<ItemTagGenerator>) output -> new ItemTagGenerator(event, output, blockTags));
        generator.addProvider(server, (DataProvider.Factory<EntityTypeTagGenerator>) output -> new EntityTypeTagGenerator(event, output));

        generator.addProvider(server, (DataProvider.Factory<LootTableGenerator>) LootTableGenerator::new);
        generator.addProvider(server, (DataProvider.Factory<RecipeGenerator>) RecipeGenerator::new);
    }

    static <T extends Block> RegistryObject<T> dummyBlock(String blockName, Function<BlockBehaviour.Properties, T> blockFactory, BiFunction<T, Item.Properties, Item> itemFactory)
    {
        var block = BLOCKS.register(blockName, () -> blockFactory.apply(BlockBehaviour.Properties.copy(Blocks.STONE)));
        dummyItem(blockName, properties -> itemFactory.apply(block.get(), properties));
        return block;
    }

    static <T extends Block> RegistryObject<T> dummyBlock(String blockName, Function<BlockBehaviour.Properties, T> blockFactory)
    {
        return dummyBlock(blockName, blockFactory, BlockItem::new);
    }

    static RegistryObject<Block> dummyBlock(String blockName)
    {
        return dummyBlock(blockName, Block::new, BlockItem::new);
    }

    static <T extends Item> RegistryObject<T> dummyItem(String itemName, Function<Item.Properties, T> itemFactory)
    {
        return ITEMS.register(itemName, () -> itemFactory.apply(new Item.Properties()));
    }

    static RegistryObject<Item> dummyItem(String itemName)
    {
        return dummyItem(itemName, Item::new);
    }

    private static RegistryObject<EntityType<?>> dummyEntityType(String entityTypeName)
    {
        return ENTITY_TYPES.register(entityTypeName, () -> EntityType.Builder.of(DummyEntity::new, MobCategory.MISC).build("%s:%s".formatted(ID, entityTypeName)));
    }
}
