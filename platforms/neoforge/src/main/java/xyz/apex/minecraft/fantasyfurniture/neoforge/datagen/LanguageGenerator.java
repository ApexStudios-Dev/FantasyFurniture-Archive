package xyz.apex.minecraft.fantasyfurniture.neoforge.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.feature.station.FurnitureStation;

@ApiStatus.Internal
final class LanguageGenerator extends LanguageProvider
{
    LanguageGenerator(PackOutput output)
    {
        super(output, FantasyFurniture.ID, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        addBlock(FurnitureStation.BLOCK, "Furniture Station");
    }
}
