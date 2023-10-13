package xyz.apex.minecraft.fantasyfurniture.nordic.common.integrations.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.entry.CollapsibleEntryRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.ItemEntry;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;

public class NordicFurnitureSetReiClientPlugin implements REIClientPlugin
{
    @Override
    public void registerCollapsibleEntries(CollapsibleEntryRegistry registry)
    {
        registry.group(
                new ResourceLocation(NordicFurnitureSet.ID, "rei_group"),
                Component.literal("Nordic"),
                NordicFurnitureSet.REGISTRAR
                        .getAll(Registries.ITEM)
                        .stream()
                        .map(ItemEntry::cast)
                        .map(EntryStacks::of)
                        .toList()
        );
    }
}
