package xyz.apex.minecraft.fantasyfurniture.forge;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import xyz.apex.minecraft.fantasyfurniture.forge.dummies.DummyHorizontalFacingBlock;
import xyz.apex.minecraft.fantasyfurniture.forge.dummies.DummyHorizontalItemNameBlock;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface Nordic
{
    String NAME = "nordic";

    RegistryObject<Block> WOOL = dummyBlock("wool");
    RegistryObject<Block> CARPET = dummyBlock("carpet");
    RegistryObject<DummyHorizontalItemNameBlock> WALL_LIGHT = dummyBlock("wall_light", DummyHorizontalItemNameBlock::new, ItemNameBlockItem::new);
    RegistryObject<Block> FLOOR_LIGHT = dummyBlock("floor_light", DummyHorizontalFacingBlock::new);
    RegistryObject<DummyHorizontalFacingBlock> TABLE_LARGE = dummyBlock("table_large", DummyHorizontalFacingBlock::new);
    RegistryObject<Block> TABLE_SMALL = dummyBlock("table_small");
    RegistryObject<DummyHorizontalFacingBlock> TABLE_WIDE = dummyBlock("table_wide", DummyHorizontalFacingBlock::new);
    RegistryObject<DummyHorizontalFacingBlock> BENCH = dummyBlock("bench", DummyHorizontalFacingBlock::new);
    RegistryObject<DummyHorizontalFacingBlock> CHAIR = dummyBlock("chair", DummyHorizontalFacingBlock::new);
    RegistryObject<Block> CHANDELIER = dummyBlock("chandelier");
    RegistryObject<DummyHorizontalFacingBlock> CUSHION = dummyBlock("cushion", DummyHorizontalFacingBlock::new);
    RegistryObject<DummyHorizontalFacingBlock> STOOL = dummyBlock("stool", DummyHorizontalFacingBlock::new);
    RegistryObject<DummyHorizontalFacingBlock> CHEST = dummyBlock("chest", DummyHorizontalFacingBlock::new);
    RegistryObject<DummyHorizontalFacingBlock> BOOKSHELF = dummyBlock("bookshelf", DummyHorizontalFacingBlock::new);
    RegistryObject<DummyHorizontalFacingBlock> DESK_LEFT = dummyBlock("desk_left", DummyHorizontalFacingBlock::new);
    RegistryObject<DummyHorizontalFacingBlock> DESK_RIGHT = dummyBlock("desk_right", DummyHorizontalFacingBlock::new);
    RegistryObject<DummyHorizontalFacingBlock> DRAWER = dummyBlock("drawer", DummyHorizontalFacingBlock::new);
    RegistryObject<DummyHorizontalFacingBlock> DRESSER = dummyBlock("dresser", DummyHorizontalFacingBlock::new);
    RegistryObject<DummyHorizontalFacingBlock> LOCKBOX = dummyBlock("lockbox", DummyHorizontalFacingBlock::new);
    RegistryObject<DummyHorizontalFacingBlock> WARDROBE_BOTTOM = dummyBlock("wardrobe_bottom", DummyHorizontalFacingBlock::new);
    RegistryObject<DummyHorizontalFacingBlock> WARDROBE_TOP = dummyBlock("wardrobe_top", DummyHorizontalFacingBlock::new);

    static void bootstrap() {}

    private static <T extends Block> RegistryObject<T> dummyBlock(String blockName, Function<BlockBehaviour.Properties, T> blockFactory)
    {
        return FantasyFurnitureDataMod.dummyBlock("%s/%s".formatted(NAME, blockName), blockFactory);
    }

    private static <T extends Block> RegistryObject<T> dummyBlock(String blockName, Function<BlockBehaviour.Properties, T> blockFactory, BiFunction<T, Item.Properties, Item> itemFactory)
    {
        return FantasyFurnitureDataMod.dummyBlock("%s/%s".formatted(NAME, blockName), blockFactory, itemFactory);
    }

    private static RegistryObject<Block> dummyBlock(String blockName)
    {
        return FantasyFurnitureDataMod.dummyBlock("%s/%s".formatted(NAME, blockName));
    }
}
