package xyz.apex.minecraft.fantasyfurniture.common;

import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.common.lib.SideOnly;

@ApiStatus.Internal
@ApiStatus.NonExtendable
@SideOnly(PhysicalSide.CLIENT)
public interface FantasyFurnitureClient
{
    default void bootstrap()
    {
    }
}
