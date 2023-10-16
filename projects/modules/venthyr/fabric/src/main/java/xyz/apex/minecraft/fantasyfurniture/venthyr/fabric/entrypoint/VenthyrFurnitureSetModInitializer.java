package xyz.apex.minecraft.fantasyfurniture.venthyr.fabric.entrypoint;

import net.fabricmc.api.ModInitializer;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;

@ApiStatus.Internal
public final class VenthyrFurnitureSetModInitializer implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        VenthyrFurnitureSet.INSTANCE.bootstrap();
    }
}
