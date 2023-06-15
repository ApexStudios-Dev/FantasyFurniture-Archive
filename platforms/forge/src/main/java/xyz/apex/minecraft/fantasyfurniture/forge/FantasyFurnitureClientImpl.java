package xyz.apex.minecraft.fantasyfurniture.forge;

import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.common.lib.SideOnly;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurnitureClient;

@ApiStatus.Internal
@SideOnly(PhysicalSide.CLIENT)
final class FantasyFurnitureClientImpl implements FantasyFurnitureClient
{
    FantasyFurnitureClientImpl()
    {
    }
}
