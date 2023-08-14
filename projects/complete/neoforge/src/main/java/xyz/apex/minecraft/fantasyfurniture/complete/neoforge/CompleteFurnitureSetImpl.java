package xyz.apex.minecraft.fantasyfurniture.complete.neoforge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.neoforge.lib.EventBusHelper;
import xyz.apex.minecraft.apexcore.neoforge.lib.EventBuses;
import xyz.apex.minecraft.fantasyfurniture.complete.common.CompleteFurnitureSet;

@ApiStatus.Internal
public final class CompleteFurnitureSetImpl implements CompleteFurnitureSet
{
    @Override
    public void bootstrap()
    {
        CompleteFurnitureSet.super.bootstrap();
        EventBuses.registerForJavaFML();
    }
}
