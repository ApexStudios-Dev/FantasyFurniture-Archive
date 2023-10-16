package xyz.apex.minecraft.fantasyfurniture.venthyr.fabric.entrypoint;

import net.fabricmc.api.ClientModInitializer;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSetClient;

@ApiStatus.Internal
public final class VenthyrFurnitureSetClientModInitializer implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        VenthyrFurnitureSetClient.INSTANCE.bootstrap();
    }
}
