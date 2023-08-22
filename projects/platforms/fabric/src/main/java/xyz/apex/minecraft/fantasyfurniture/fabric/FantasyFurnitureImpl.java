package xyz.apex.minecraft.fantasyfurniture.fabric;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

@ApiStatus.Internal
public final class FantasyFurnitureImpl implements FantasyFurniture
{
    @Override
    public void bootstrap()
    {
        FantasyFurniture.super.bootstrap();

        UseEntityCallback.EVENT.register((player, level, hand, entity, hitResult) -> onEntityInteract(level, player, hand, entity));
    }
}
