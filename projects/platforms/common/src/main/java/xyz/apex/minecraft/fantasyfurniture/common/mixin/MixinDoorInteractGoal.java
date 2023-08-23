package xyz.apex.minecraft.fantasyfurniture.common.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.DoorInteractGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentHolder;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.DoorComponent;

@Mixin(DoorInteractGoal.class)
public abstract class MixinDoorInteractGoal
{
    @Shadow protected boolean hasDoor;
    @Shadow protected Mob mob;
    @Shadow protected BlockPos doorPos;

    @Inject(
            method = "setOpen",
            at = @At("HEAD"),
            cancellable = true
    )
    private void FantasyFurniture$setOpen(boolean open, CallbackInfo ci)
    {
        if(!hasDoor)
            return;

        final var level = mob.level();
        final var blockState = level.getBlockState(doorPos);

        BlockComponentHolder.runAsComponent(blockState, DoorComponent.COMPONENT_TYPE, component -> {
            component.setOpen(mob, level, doorPos, blockState, open);
            ci.cancel();
        });
    }

    @ModifyReturnValue(
            method = "isOpen",
            at = @At("RETURN")
    )
    private boolean FantasyFurniture$isOpen(boolean original)
    {
        final var blockState = mob.level().getBlockState(doorPos);
        return BlockComponentHolder.mapAsComponent(blockState, DoorComponent.COMPONENT_TYPE, component -> blockState.getValue(DoorComponent.OPEN)).orElse(original);
    }

    @Inject(
            method = "canUse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/DoorBlock;isWoodenDoor(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Z",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    private void FantasyFurniture$canUse(CallbackInfoReturnable<Boolean> cir)
    {
        final var blockState = mob.level().getBlockState(doorPos);

        BlockComponentHolder.runAsComponent(blockState, DoorComponent.COMPONENT_TYPE, component -> {
            hasDoor = component.hasComponent(DoorComponent.COMPONENT_TYPE);

            if(hasDoor)
                cir.setReturnValue(true);
        });
    }
}
