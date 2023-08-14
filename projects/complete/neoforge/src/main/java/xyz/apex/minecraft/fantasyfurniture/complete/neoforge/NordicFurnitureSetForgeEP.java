package xyz.apex.minecraft.fantasyfurniture.complete.neoforge;

import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.fantasyfurniture.complete.common.CompleteFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.complete.common.CompleteFurnitureSetClient;

@ApiStatus.Internal
@Mod(CompleteFurnitureSet.ID)
public final class CompleteFurnitureSetForgeEP
{
    public CompleteFurnitureSetForgeEP()
    {
        CompleteFurnitureSet.INSTANCE.bootstrap();
        PhysicalSide.CLIENT.runWhenOn(() -> CompleteFurnitureSetClient.INSTANCE::bootstrap);
    }
}
