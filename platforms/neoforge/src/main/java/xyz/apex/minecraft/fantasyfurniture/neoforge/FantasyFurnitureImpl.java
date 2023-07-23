package xyz.apex.minecraft.fantasyfurniture.neoforge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.neoforge.lib.EventBuses;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.feature.seat.Seat;
import xyz.apex.minecraft.fantasyfurniture.neoforge.datagen.FantasyFurnitureDataGen;

@ApiStatus.Internal
public final class FantasyFurnitureImpl implements FantasyFurniture
{
    @Override
    public void bootstrap()
    {
        FantasyFurniture.super.bootstrap();

        FantasyFurnitureDataGen.bootstrap();
        EventBuses.registerForJavaFML();

        MinecraftForge.EVENT_BUS.addListener(this::onPlayerRightClickBlock);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerRightClickEntity);
    }

    private void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event)
    {
        var result = Seat.onRightClickBlock(event.getEntity(), event.getLevel(), event.getHand(), event.getHitVec());

        if(result.consumesAction())
        {
            event.setCanceled(true);
            event.setCancellationResult(result);
        }
    }

    private void onPlayerRightClickEntity(PlayerInteractEvent.EntityInteract event)
    {
        var result = Seat.onRightClickEntity(event.getEntity(), event.getLevel(), event.getHand(), event.getTarget());

        if(result.consumesAction())
        {
            event.setCanceled(true);
            event.setCancellationResult(result);
        }
    }
}
