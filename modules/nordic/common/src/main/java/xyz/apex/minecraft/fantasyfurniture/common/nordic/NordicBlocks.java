package xyz.apex.minecraft.fantasyfurniture.common.nordic;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.registry.entries.BlockEntry;

@ApiStatus.NonExtendable
public interface NordicBlocks
{
    BlockEntry<Block> WOOL = NordicFurnitureSet.BUILDERS
            .block("wool")
            .copyInitialPropertiesFrom(() -> Blocks.WHITE_WOOL)
            .simpleItem()
    .register();

    static void bootstrap()
    {
    }
}
