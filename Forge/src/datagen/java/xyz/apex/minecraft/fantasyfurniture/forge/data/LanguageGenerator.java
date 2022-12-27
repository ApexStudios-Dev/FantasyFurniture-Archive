package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;

public final class LanguageGenerator extends LanguageProvider
{
    LanguageGenerator(PackOutput packOutput)
    {
        super(packOutput, FantasyFurniture.ID, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        addBlock(NordicSet.WOOL, "Nordic Wool");
        addBlock(NordicSet.CARPET, "Nordic Carpet");
        addBlock(NordicSet.WALL_LIGHT, "Nordic Wall Light");
        addBlock(NordicSet.FLOOR_LIGHT, "Nordic Floor Light");
        addBlock(NordicSet.TABLE_LARGE, "Nordic Table Large");
        addBlock(NordicSet.TABLE_SMALL, "Nordic Table Small");
        addBlock(NordicSet.TABLE_WIDE, "Nordic Table Wide");
        addBlock(NordicSet.BENCH, "Nordic Bench");
    }
}
