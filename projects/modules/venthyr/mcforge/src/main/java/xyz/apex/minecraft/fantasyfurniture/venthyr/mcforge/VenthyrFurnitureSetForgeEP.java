package xyz.apex.minecraft.fantasyfurniture.venthyr.mcforge;

import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;

@ApiStatus.Internal
@Mod(VenthyrFurnitureSet.ID)
public final class VenthyrFurnitureSetForgeEP
{
    public VenthyrFurnitureSetForgeEP()
    {
        VenthyrFurnitureSet.INSTANCE.bootstrap();
    }
}
