package xyz.apex.minecraft.fantasyfurniture.fabric.nordic;

import net.fabricmc.api.ModInitializer;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.nordic.NordicFurnitureSet;

@ApiStatus.Internal
public final class NordicFurnitureSetImpl implements NordicFurnitureSet, ModInitializer
{
    @Override
    public void onInitialize()
    {
        bootstrap();
    }
}
