package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.data.event.GatherDataEvent;

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;

public final class LanguageGenerator extends LanguageProvider
{
    LanguageGenerator(GatherDataEvent event)
    {
        super(event.getGenerator(), FantasyFurniture.ID, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        addBlock(NordicSet.WOOL, "Nordic Wool");
        addBlock(NordicSet.CARPET, "Nordic Carpet");
    }
}
