package xyz.apex.minecraft.fantasyfurniture.complete.mcforge;

import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.mcforge.lib.EventBuses;
import xyz.apex.minecraft.fantasyfurniture.complete.common.CompleteFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.complete.common.CompleteFurnitureSetClient;

@ApiStatus.Internal
public final class CompleteFurnitureSetImpl implements CompleteFurnitureSet
{
    @Override
    public void bootstrap()
    {
        CompleteFurnitureSet.super.bootstrap();
        EventBuses.registerForJavaFML();
        PhysicalSide.CLIENT.runWhenOn(() -> CompleteFurnitureSetClient.INSTANCE::bootstrap);
    }
}
