package xyz.apex.minecraft.fantasyfurniture.common.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BedBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentHolder;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.BedComponent;

@Mixin(BedBlock.class)
public abstract class MixinBedBlock
{
    @ModifyReturnValue(
            method = "isBunkBed",
            at = @At("RETURN")
    )
    private static boolean FantasyFurniture$isBunkBed(boolean original, BlockGetter level, BlockPos pos)
    {
        var blockState = level.getBlockState(pos.below());

        if(!(blockState.getBlock() instanceof BlockComponentHolder componentHolder))
            return original;

        return componentHolder.hasComponent(BedComponent.COMPONENT_TYPE) || original;
    }

    @ModifyReturnValue(
            method = "getBedOrientation",
            at = @At("RETURN")
    )
    private static Direction FantasyFurniture$getBedOrientation(Direction original, BlockGetter level, BlockPos pos)
    {
        var blockState = level.getBlockState(pos);
        var direction = BedComponent.getBedDirection(blockState);
        return direction;
    }
}
