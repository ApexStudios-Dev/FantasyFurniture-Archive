package xyz.apex.minecraft.fantasyfurniture.neoforge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.neoforge.lib.EventBusHelper;
import xyz.apex.minecraft.apexcore.neoforge.lib.EventBuses;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

@ApiStatus.Internal
public final class FantasyFurnitureImpl implements FantasyFurniture
{
    @Override
    public void bootstrap()
    {
        FantasyFurniture.super.bootstrap();
        EventBuses.registerForJavaFML();
    }
}
