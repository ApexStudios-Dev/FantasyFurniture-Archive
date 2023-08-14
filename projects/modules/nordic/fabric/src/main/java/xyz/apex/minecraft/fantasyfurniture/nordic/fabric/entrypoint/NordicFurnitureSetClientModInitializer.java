package xyz.apex.minecraft.fantasyfurniture.nordic.fabric.entrypoint;

import net.fabricmc.api.ClientModInitializer;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSetClient;

@ApiStatus.Internal
public final class NordicFurnitureSetClientModInitializer implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        NordicFurnitureSetClient.INSTANCE.bootstrap();
    }
}
