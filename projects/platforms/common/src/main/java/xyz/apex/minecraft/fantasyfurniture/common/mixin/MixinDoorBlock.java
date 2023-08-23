package xyz.apex.minecraft.fantasyfurniture.common.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentHolder;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.DoorComponent;

@Mixin(DoorBlock.class)
public abstract class MixinDoorBlock
{
    @ModifyReturnValue(
            method = "isWoodenDoor(Lnet/minecraft/world/level/block/state/BlockState;)Z",
            at = @At("RETURN")
    )
    private static boolean FantasyFurniture$isWoodenDoor(boolean original, BlockState blockState)
    {
        return BlockComponentHolder.mapAsComponent(blockState, DoorComponent.COMPONENT_TYPE, DoorComponent::getBlockSetType).map(BlockSetType::canOpenByHand).orElse(original);
    }
}
