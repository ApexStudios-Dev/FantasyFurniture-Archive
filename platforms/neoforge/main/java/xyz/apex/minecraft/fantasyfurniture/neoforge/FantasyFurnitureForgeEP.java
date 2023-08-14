package xyz.apex.minecraft.fantasyfurniture.neoforge;

import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurnitureClient;

@ApiStatus.Internal
@Mod(FantasyFurniture.ID)
public final class FantasyFurnitureForgeEP
{
    public FantasyFurnitureForgeEP()
    {
        FantasyFurniture.INSTANCE.bootstrap();
        PhysicalSide.CLIENT.runWhenOn(() -> FantasyFurnitureClient.INSTANCE::bootstrap);
    }
}
