package xyz.apex.minecraft.fantasyfurniture.complete.common;

import org.jetbrains.annotations.ApiStatus;
import xyz.apex.lib.Services;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.common.lib.SideOnly;

@ApiStatus.NonExtendable
@SideOnly(PhysicalSide.CLIENT)
public interface CompleteFurnitureSetClient
{
    CompleteFurnitureSetClient INSTANCE = Services.singleton(CompleteFurnitureSetClient.class);

    default void bootstrap()
    {
    }
}
