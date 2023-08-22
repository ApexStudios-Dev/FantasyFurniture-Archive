package xyz.apex.minecraft.fantasyfurniture.common.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.DoorInteractGoal;
import net.minecraft.world.level.block.DoorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
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

    @Inject(
            method = "isOpen",
            at = @At("HEAD"),
            cancellable = true
    )
    private void FantasyFurniture$isOpen(CallbackInfoReturnable<Boolean> cir)
    {
        final var blockState = mob.level().getBlockState(doorPos);
        BlockComponentHolder.runAsComponent(blockState, DoorComponent.COMPONENT_TYPE, component -> cir.setReturnValue(blockState.getValue(DoorComponent.OPEN)));
    }

    // IntelliJ (MCDev Plugin) complain about a lot of stuff here,
    // but you can safely ignore it, known issue with ModifyConstant and instanceof redirections
    // code still compiles and runs as expected
    @ModifyConstant(
            method = "isOpen",
            constant = @Constant(classValue = DoorBlock.class)
    )
    private boolean FantasyFurniture$isOpen(Object obj, Class objClas)
    {
        if(obj instanceof DoorBlock)
            return true;
        else if(obj instanceof BlockComponentHolder componentHolder)
            return componentHolder.hasComponent(DoorComponent.COMPONENT_TYPE);
        else
            return false;
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
