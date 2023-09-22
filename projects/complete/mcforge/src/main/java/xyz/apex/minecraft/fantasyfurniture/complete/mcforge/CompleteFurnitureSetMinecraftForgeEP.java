package xyz.apex.minecraft.fantasyfurniture.complete.mcforge;

import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.complete.common.CompleteFurnitureSet;

@ApiStatus.Internal
@Mod(CompleteFurnitureSet.ID)
public final class CompleteFurnitureSetMinecraftForgeEP
{
    public CompleteFurnitureSetMinecraftForgeEP()
    {
        CompleteFurnitureSet.INSTANCE.bootstrap();
    }
}
