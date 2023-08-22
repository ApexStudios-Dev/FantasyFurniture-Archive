package xyz.apex.minecraft.fantasyfurniture.common.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.InteractWithDoor;
import net.minecraft.world.level.pathfinder.Node;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentHolder;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.DoorComponent;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Mixin(InteractWithDoor.class)
public abstract class MixinInteractWithDoor
{
    @Inject(
            method = "closeDoorsThatIHaveOpenedOrPassedThrough",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;"
            ),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private static void FantasyFurniture$closeDoorsThatIHaveOpenedOrPassedThrough(ServerLevel level, LivingEntity entity, @Nullable Node previous, @Nullable Node next, Set<GlobalPos> doorPositions, Optional<List<LivingEntity>> nearestLivingEntities, CallbackInfo ci, Iterator<GlobalPos> itr, GlobalPos globalPos, BlockPos pos)
    {
        final var blockState = level.getBlockState(pos);

        BlockComponentHolder.runAsComponent(blockState, DoorComponent.COMPONENT_TYPE, component -> {
            if(component.getBlockSetType().canOpenByHand())
                component.setOpen(entity, level, pos, blockState, false);
        });
    }
}
