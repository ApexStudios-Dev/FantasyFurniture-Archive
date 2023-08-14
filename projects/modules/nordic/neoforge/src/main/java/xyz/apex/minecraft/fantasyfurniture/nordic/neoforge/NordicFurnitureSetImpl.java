package xyz.apex.minecraft.fantasyfurniture.complete.neoforge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.neoforge.lib.EventBusHelper;
import xyz.apex.minecraft.apexcore.neoforge.lib.EventBuses;
import xyz.apex.minecraft.fantasyfurniture.complete.common.NordicFurnitureSet;

@ApiStatus.Internal
public final class NordicFurnitureSetImpl implements NordicFurnitureSet
{
    @Override
    public void bootstrap()
    {
        NordicFurnitureSet.super.bootstrap();
        EventBuses.registerForJavaFML();
    }
}
