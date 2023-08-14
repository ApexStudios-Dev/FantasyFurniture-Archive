package xyz.apex.minecraft.fantasyfurniture.nordic.common;

import org.jetbrains.annotations.ApiStatus;
import xyz.apex.lib.Services;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.common.lib.SideOnly;

@ApiStatus.NonExtendable
@SideOnly(PhysicalSide.CLIENT)
public interface NordicFurnitureSetClient
{
    NordicFurnitureSetClient INSTANCE = Services.singleton(NordicFurnitureSetClient.class);

    default void bootstrap()
    {
    }
}
