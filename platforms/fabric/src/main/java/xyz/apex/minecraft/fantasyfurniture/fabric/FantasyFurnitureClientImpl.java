package xyz.apex.minecraft.fantasyfurniture.fabric;

import net.fabricmc.api.ClientModInitializer;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.common.lib.SideOnly;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurnitureClient;

@ApiStatus.Internal
@SideOnly(PhysicalSide.CLIENT)
public final class FantasyFurnitureClientImpl implements FantasyFurnitureClient, ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        bootstrap();
    }
}
