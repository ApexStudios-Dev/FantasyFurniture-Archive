package xyz.apex.minecraft.fantasyfurniture.nordic.fabric;

import net.fabricmc.api.ModInitializer;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;

public final class NordicFurnitureSetFabric implements NordicFurnitureSet, ModInitializer
{
    public static final NordicFurnitureSetFabric INSTANCE = new NordicFurnitureSetFabric();

    @Override
    public void onInitialize()
    {
        bootstrap();
    }
}
