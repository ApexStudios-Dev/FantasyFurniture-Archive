package xyz.apex.minecraft.fantasyfurniture.venthyr.mcforge;

import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.mcforge.lib.EventBuses;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSetClient;

@ApiStatus.Internal
public final class VenthyrFurnitureSetImpl implements VenthyrFurnitureSet
{
    @Override
    public void bootstrap()
    {
        VenthyrFurnitureSet.super.bootstrap();
        EventBuses.registerForJavaFML();
        PhysicalSide.CLIENT.runWhenOn(() -> VenthyrFurnitureSetClient.INSTANCE::bootstrap);
    }
}
