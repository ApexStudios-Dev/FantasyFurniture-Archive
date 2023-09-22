package xyz.apex.minecraft.fantasyfurniture.nordic.mcforge;

import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.mcforge.lib.EventBuses;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSetClient;

@ApiStatus.Internal
public final class NordicFurnitureSetImpl implements NordicFurnitureSet
{
    @Override
    public void bootstrap()
    {
        NordicFurnitureSet.super.bootstrap();
        EventBuses.registerForJavaFML();
        PhysicalSide.CLIENT.runWhenOn(() -> NordicFurnitureSetClient.INSTANCE::bootstrap);
    }
}
