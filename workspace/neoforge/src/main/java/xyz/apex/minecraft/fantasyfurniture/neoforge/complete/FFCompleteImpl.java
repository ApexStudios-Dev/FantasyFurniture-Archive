package xyz.apex.minecraft.fantasyfurniture.neoforge.complete;

import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.neoforge.lib.EventBuses;
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
