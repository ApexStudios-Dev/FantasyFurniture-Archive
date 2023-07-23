package xyz.apex.minecraft.fantasyfurniture.neoforge.nordic;

import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.nordic.NordicFurnitureSet;

@ApiStatus.Internal
@Mod(NordicFurnitureSet.ID)
public final class NordicFurnitureSetForgeEP
{
    public NordicFurnitureSetForgeEP()
    {
        NordicFurnitureSet.INSTANCE.bootstrap();
    }
}
