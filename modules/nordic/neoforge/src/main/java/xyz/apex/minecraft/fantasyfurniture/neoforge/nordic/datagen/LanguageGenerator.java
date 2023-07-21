package xyz.apex.minecraft.fantasyfurniture.neoforge.nordic.datagen;

import net.minecraft.Util;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.nordic.NordicBlocks;
import xyz.apex.minecraft.fantasyfurniture.common.nordic.NordicFurnitureSet;

@ApiStatus.Internal
final class LanguageGenerator extends LanguageProvider
{
    LanguageGenerator(PackOutput output)
    {
        super(output, NordicFurnitureSet.ID, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        addBlock(NordicBlocks.WOOL, "Nordic Wool");
        add(Util.makeDescriptionId("itemGroup", new ResourceLocation(NordicFurnitureSet.ID, "main")), "Nordic Furniture Set");
    }
}
