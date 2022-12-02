package xyz.apex.minecraft.fantasyfurniture.shared.init;

import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;

import xyz.apex.minecraft.apexcore.shared.registry.block.BlockRegistryEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.block.WallLightBlock;

public interface NordicSet
{
    BlockRegistryEntry<Block> WOOL = FantasyFurniture.Registries.BLOCKS
            .generic("nordic/wool")
            .flammability(30, 60)
            .initialProperties(FantasyFurniture.WOOL_PROPERTIES)
    .register();

    BlockRegistryEntry<CarpetBlock> CARPET = FantasyFurniture.Registries.BLOCKS
            .builder("nordic/carpet", CarpetBlock::new)
            .flammability(60, 20)
            .initialProperties(FantasyFurniture.CARPET_PROPERTIES)
    .register();

    BlockRegistryEntry<WallLightBlock> WALL_LIGHT = FantasyFurniture.Registries.BLOCKS
            .builder("nordic/wall_light", WallLightBlock::torchLike)
            .initialProperties(FantasyFurniture.TORCH_PROPERTIES)
    .register();

    static void bootstrap()
    {
        FantasyFurniture.Registries.ITEMS.genericBlockBuilder(WOOL).register();
        FantasyFurniture.Registries.ITEMS.genericBlockBuilder(CARPET).register();
        FantasyFurniture.Registries.ITEMS.blockBuilder(WALL_LIGHT, ItemNameBlockItem::new).register();
    }
}
