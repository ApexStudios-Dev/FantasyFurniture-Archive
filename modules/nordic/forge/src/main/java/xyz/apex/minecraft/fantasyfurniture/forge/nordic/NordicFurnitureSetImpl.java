package xyz.apex.minecraft.fantasyfurniture.forge.nordic;

import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.forge.lib.EventBuses;
import xyz.apex.minecraft.fantasyfurniture.common.nordic.NordicFurnitureSet;

@ApiStatus.Internal
@Mod(NordicFurnitureSet.ID)
public final class NordicFurnitureSetImpl implements NordicFurnitureSet
{
    public NordicFurnitureSetImpl()
    {
        bootstrap();
        EventBuses.registerForJavaFML();
    }
}
