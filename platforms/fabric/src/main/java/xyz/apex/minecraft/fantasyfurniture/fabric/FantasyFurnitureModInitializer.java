package xyz.apex.minecraft.fantasyfurniture.fabric;

import net.fabricmc.api.ModInitializer;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

@ApiStatus.Internal
public final class FantasyFurnitureModInitializer implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        FantasyFurniture.INSTANCE.bootstrap();
    }
}
