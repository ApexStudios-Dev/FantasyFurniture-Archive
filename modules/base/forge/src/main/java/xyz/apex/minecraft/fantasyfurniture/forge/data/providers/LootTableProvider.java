package xyz.apex.minecraft.fantasyfurniture.forge.data.providers;

import com.google.common.collect.ImmutableSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Map;

public class LootTableProvider extends net.minecraft.data.loot.LootTableProvider
{
    protected LootTableProvider(GatherDataEvent event, List<SubProviderEntry> providers)
    {
        super(event.getGenerator().getPackOutput(), ImmutableSet.of(), providers);
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationTracker)
    {
        map.forEach((name, table) -> LootTables.validate(validationTracker, name, table));
    }
}
