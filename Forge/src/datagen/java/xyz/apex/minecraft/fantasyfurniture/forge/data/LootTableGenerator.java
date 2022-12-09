package xyz.apex.minecraft.fantasyfurniture.forge.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.Map;

public final class LootTableGenerator extends LootTableProvider
{
    LootTableGenerator(GatherDataEvent event)
    {
        super(event.getGenerator().getPackOutput(), ImmutableSet.of(), ImmutableList.of(new SubProviderEntry(BlockLootTableGenerator::new, LootContextParamSets.BLOCK)));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationTracker)
    {
        map.forEach((name, table) -> LootTables.validate(validationTracker, name, table));
    }
}
