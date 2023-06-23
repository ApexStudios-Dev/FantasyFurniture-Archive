package xyz.apex.minecraft.fantasyfurniture.fabric.complete;

import net.fabricmc.api.ModInitializer;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.complete.FFComplete;

@ApiStatus.Internal
public final class FFCompleteImpl implements FFComplete, ModInitializer
{
    @Override
    public void onInitialize()
    {
        bootstrap();
    }
}
