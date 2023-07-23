package xyz.apex.minecraft.fantasyfurniture.neoforge;

import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

@ApiStatus.Internal
@Mod(FantasyFurniture.ID)
public final class FantasyFurnitureForgeEP
{
    public FantasyFurnitureForgeEP()
    {
        FantasyFurniture.INSTANCE.bootstrap();
    }
}
