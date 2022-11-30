package xyz.apex.minecraft.fantasyfurniture.shared.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import xyz.apex.minecraft.apexcore.shared.registry.block.BlockRegistryEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public interface Builders
{
    Supplier<Item.Properties> ITEM_PROPERTIES = () -> new Item.Properties().tab(CreativeModeTab.TAB_MISC);
    Supplier<BlockBehaviour.Properties> STONE_PROPERTIES = () -> BlockBehaviour.Properties.copy(Blocks.STONE);
    Supplier<BlockBehaviour.Properties> WOOL_PROPERTIES = () -> BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL);

    // region: Wool
    static BlockRegistryEntry<Block> wool(String name, UnaryOperator<BlockBehaviour.Properties> blockProperties, UnaryOperator<Item.Properties> itemProperties)
    {
        var block = FantasyFurniture.Registries.BLOCKS.register(name, () -> new Block(blockProperties.apply(WOOL_PROPERTIES.get())));
        FantasyFurniture.Registries.ITEMS.register(name, () -> new BlockItem(block.get(), itemProperties.apply(ITEM_PROPERTIES.get())));
        return block;
    }

    static BlockRegistryEntry<Block> wool(String name, UnaryOperator<BlockBehaviour.Properties> blockProperties)
    {
        return wool(name, blockProperties, UnaryOperator.identity());
    }

    static BlockRegistryEntry<Block> wool(String name, Function<Item.Properties, Item.Properties> itemProperties)
    {
        return wool(name, UnaryOperator.identity(), itemProperties::apply);
    }

    static BlockRegistryEntry<Block> wool(String name)
    {
        return wool(name, UnaryOperator.identity(), UnaryOperator.identity());
    }
    // endregion
}
