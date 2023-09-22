package xyz.apex.minecraft.fantasyfurniture.mcforge;

import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

@ApiStatus.Internal
@Mod(FantasyFurniture.ID)
public final class FantasyFurnitureMinecraftForgeEP
{
    public FantasyFurnitureMinecraftForgeEP()
    {
        FantasyFurniture.INSTANCE.bootstrap();
    }
}
