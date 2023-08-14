package xyz.apex.minecraft.fantasyfurniture.complete.fabric.entrypoint;

import net.fabricmc.api.ClientModInitializer;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.complete.common.CompleteFurnitureSetClient;

@ApiStatus.Internal
public final class CompleteFurnitureSetClientModInitializer implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        CompleteFurnitureSetClient.INSTANCE.bootstrap();
    }
}
