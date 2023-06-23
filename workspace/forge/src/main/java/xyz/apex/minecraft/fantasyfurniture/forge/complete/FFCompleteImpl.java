package xyz.apex.minecraft.fantasyfurniture.forge.complete;

import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.forge.lib.EventBuses;
import xyz.apex.minecraft.fantasyfurniture.common.complete.FFComplete;

@ApiStatus.Internal
@Mod(FFComplete.ID)
public final class FFCompleteImpl implements FFComplete
{
    public FFCompleteImpl()
    {
        bootstrap();
        EventBuses.registerForJavaFML();
    }
}
