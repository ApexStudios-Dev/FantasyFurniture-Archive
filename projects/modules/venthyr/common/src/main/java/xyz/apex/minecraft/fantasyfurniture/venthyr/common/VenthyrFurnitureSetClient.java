package xyz.apex.minecraft.fantasyfurniture.venthyr.common;

import org.jetbrains.annotations.ApiStatus;
import xyz.apex.lib.Services;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.common.lib.SideOnly;

@ApiStatus.NonExtendable
@SideOnly(PhysicalSide.CLIENT)
public interface VenthyrFurnitureSetClient
{
    VenthyrFurnitureSetClient INSTANCE = Services.singleton(VenthyrFurnitureSetClient.class);

    default void bootstrap()
    {
    }
}
