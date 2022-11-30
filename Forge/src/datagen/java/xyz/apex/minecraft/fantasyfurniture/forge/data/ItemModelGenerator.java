package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.data.event.GatherDataEvent;

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;

public final class ItemModelGenerator extends ItemModelProvider
{
    ItemModelGenerator(GatherDataEvent event)
    {
        super(event.getGenerator(), FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerModels()
    {
    }
}
