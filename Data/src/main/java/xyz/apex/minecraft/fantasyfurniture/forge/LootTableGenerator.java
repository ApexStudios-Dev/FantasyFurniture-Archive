package xyz.apex.minecraft.fantasyfurniture.forge;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Map;

public final class LootTableGenerator extends LootTableProvider
{
    public LootTableGenerator(PackOutput packOutput)
    {
        super(packOutput, ImmutableSet.of(), ImmutableList.of(
                new SubProviderEntry(BlockLootTableGenerator::new, LootContextParamSets.BLOCK),
                new SubProviderEntry(EntityLootTableGenerator::new, LootContextParamSets.ENTITY)
        ));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationTracker)
    {
        map.forEach((name, table) -> LootTables.validate(validationTracker, name, table));
    }
}
