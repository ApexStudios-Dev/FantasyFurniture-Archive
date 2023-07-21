package xyz.apex.minecraft.fantasyfurniture.neoforge.nordic;

import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.neoforge.lib.EventBuses;
import xyz.apex.minecraft.fantasyfurniture.common.nordic.NordicFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.neoforge.nordic.datagen.NordicDataGen;

@ApiStatus.Internal
@Mod(NordicFurnitureSet.ID)
public final class NordicFurnitureSetImpl implements NordicFurnitureSet
{
    public NordicFurnitureSetImpl()
    {
        bootstrap();
        NordicDataGen.bootstrap();
        EventBuses.registerForJavaFML();
    }
}
