package xyz.apex.minecraft.fantasyfurniture.neoforge.nordic;

import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.neoforge.lib.EventBuses;
import xyz.apex.minecraft.fantasyfurniture.common.nordic.NordicFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.neoforge.nordic.datagen.NordicDataGen;

@ApiStatus.Internal
public final class NordicFurnitureSetImpl implements NordicFurnitureSet
{
    @Override
    public void bootstrap()
    {
        NordicFurnitureSet.super.bootstrap();

        NordicDataGen.bootstrap();
        EventBuses.registerForJavaFML();
    }
}
