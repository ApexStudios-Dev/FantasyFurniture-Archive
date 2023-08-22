package xyz.apex.minecraft.fantasyfurniture.common.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentHolder;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.DoorComponent;

@Mixin(WalkNodeEvaluator.class)
public abstract class MixinWalkNodeEvaluator
{
    @Inject(
            method = "getBlockPathTypeRaw",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void FantasyFurniture$getBlockPathTypeRaw(BlockGetter level, BlockPos pos, CallbackInfoReturnable<BlockPathTypes> cir)
    {
        final var blockState = level.getBlockState(pos);

        BlockComponentHolder.runAsComponent(blockState, DoorComponent.COMPONENT_TYPE, component -> {
            if(blockState.getValue(DoorComponent.OPEN))
                cir.setReturnValue(BlockPathTypes.DOOR_OPEN);
            else
                cir.setReturnValue(component.getBlockSetType().canOpenByHand() ? BlockPathTypes.DOOR_WOOD_CLOSED : BlockPathTypes.DOOR_IRON_CLOSED);
        });
    }
}
