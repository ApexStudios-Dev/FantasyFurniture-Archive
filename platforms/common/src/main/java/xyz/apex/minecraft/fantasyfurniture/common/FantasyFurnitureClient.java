package xyz.apex.minecraft.fantasyfurniture.common;

import org.jetbrains.annotations.ApiStatus;
import xyz.apex.lib.Services;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.common.lib.SideOnly;

@ApiStatus.NonExtendable
@SideOnly(PhysicalSide.CLIENT)
public interface FantasyFurnitureClient
{
    FantasyFurnitureClient INSTANCE = Services.singleton(FantasyFurnitureClient.class);

    default void bootstrap()
    {
    }
}
