package xyz.apex.minecraft.fantasyfurniture.nordic.fabric.entrypoint;

import net.fabricmc.api.ModInitializer;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;

@ApiStatus.Internal
public final class NordicFurnitureSetModInitializer implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        NordicFurnitureSet.INSTANCE.bootstrap();
    }
}
