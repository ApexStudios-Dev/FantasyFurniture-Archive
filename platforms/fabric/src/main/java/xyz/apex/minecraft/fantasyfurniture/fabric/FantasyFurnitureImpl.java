package xyz.apex.minecraft.fantasyfurniture.fabric;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.feature.seat.Seat;

@ApiStatus.Internal
public final class FantasyFurnitureImpl implements FantasyFurniture
{
    @Override
    public void bootstrap()
    {
        FantasyFurniture.super.bootstrap();

        UseBlockCallback.EVENT.register(Seat::onRightClickBlock);
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> Seat.onRightClickEntity(player, world, hand, entity));
    }
}
