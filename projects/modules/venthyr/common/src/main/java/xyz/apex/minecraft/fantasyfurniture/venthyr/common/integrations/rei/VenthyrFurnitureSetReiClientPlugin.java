package xyz.apex.minecraft.fantasyfurniture.venthyr.common.integrations.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.entry.CollapsibleEntryRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import xyz.apex.minecraft.apexcore.common.lib.registry.entry.ItemEntry;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;

public class VenthyrFurnitureSetReiClientPlugin implements REIClientPlugin
{
    @Override
    public void registerCollapsibleEntries(CollapsibleEntryRegistry registry)
    {
        registry.group(
                new ResourceLocation(VenthyrFurnitureSet.ID, "rei_group"),
                Component.literal("Venthyr"), // TODO: localize
                VenthyrFurnitureSet.REGISTRAR
                        .getAll(Registries.ITEM)
                        .stream()
                        .map(ItemEntry::cast)
                        .map(EntryStacks::of)
                        .toList()
        );
    }
}
