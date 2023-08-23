package xyz.apex.minecraft.fantasyfurniture.fabric;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.registry.LandPathNodeTypesRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

import java.util.function.BiFunction;

@ApiStatus.Internal
public final class FantasyFurnitureImpl implements FantasyFurniture
{
    @Override
    public void bootstrap()
    {
        FantasyFurniture.super.bootstrap();

        UseEntityCallback.EVENT.register((player, level, hand, entity, hitResult) -> onEntityInteract(level, player, hand, entity));
    }

    @Override
    public void registerPathNodeType(Block block, BiFunction<BlockState, Boolean, @Nullable BlockPathTypes> getPathNodeType)
    {
        LandPathNodeTypesRegistry.register(block, getPathNodeType::apply);
    }
}
