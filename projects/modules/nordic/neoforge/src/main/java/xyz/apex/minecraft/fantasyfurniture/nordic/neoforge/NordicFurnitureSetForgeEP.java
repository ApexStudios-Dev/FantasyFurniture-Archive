package xyz.apex.minecraft.fantasyfurniture.complete.neoforge;

import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.fantasyfurniture.complete.common.NordicFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.complete.common.NordicFurnitureSetClient;

@ApiStatus.Internal
@Mod(NordicFurnitureSet.ID)
public final class NordicFurnitureSetForgeEP
{
    public NordicFurnitureSetForgeEP()
    {
        NordicFurnitureSet.INSTANCE.bootstrap();
        PhysicalSide.CLIENT.runWhenOn(() -> NordicFurnitureSetClient.INSTANCE::bootstrap);
    }
}
