package xyz.apex.minecraft.fantasyfurniture.complete.fabric.entrypoint;

import net.fabricmc.api.ModInitializer;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.complete.common.CompleteFurnitureSet;

@ApiStatus.Internal
public final class CompleteFurnitureSetModInitializer implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        CompleteFurnitureSet.INSTANCE.bootstrap();
    }
}
