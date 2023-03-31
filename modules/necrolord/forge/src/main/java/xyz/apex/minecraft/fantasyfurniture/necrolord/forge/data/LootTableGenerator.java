package xyz.apex.minecraft.fantasyfurniture.necrolord.forge.data;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.LootTableProvider;

final class LootTableGenerator extends LootTableProvider
{
    LootTableGenerator(GatherDataEvent event)
    {
        super(event, ImmutableList.of(new SubProviderEntry(BlockLootTableGenerator::new, LootContextParamSets.BLOCK)));
    }
}
