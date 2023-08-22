package xyz.apex.minecraft.fantasyfurniture.common.mixin;

import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentHolder;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.DoorComponent;

@Mixin(DoorBlock.class)
public abstract class MixinDoorBlock
{
    @Inject(
            method = "isWoodenDoor(Lnet/minecraft/world/level/block/state/BlockState;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void FantasyFurniture$isWoodenDoor(BlockState blockState, CallbackInfoReturnable<Boolean> cir)
    {
        BlockComponentHolder.runAsComponent(blockState, DoorComponent.COMPONENT_TYPE, component -> {
            if(component.getBlockSetType().canOpenByHand())
                cir.setReturnValue(true);
        });
    }
}
