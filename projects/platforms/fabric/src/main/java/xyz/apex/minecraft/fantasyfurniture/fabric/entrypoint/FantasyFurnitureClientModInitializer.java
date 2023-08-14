package xyz.apex.minecraft.fantasyfurniture.fabric.entrypoint;

import net.fabricmc.api.ClientModInitializer;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurnitureClient;

@ApiStatus.Internal
public final class FantasyFurnitureClientModInitializer implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        FantasyFurnitureClient.INSTANCE.bootstrap();
    }
}
