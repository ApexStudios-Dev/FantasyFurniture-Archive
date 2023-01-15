package xyz.apex.minecraft.fantasyfurniture.shared.mixin;

import com.google.common.collect.Sets;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

@Mixin(PoiTypes.class)
public abstract class MixinPoiTypes
{
    @Shadow @Mutable @Final private static Set<BlockState> BEDS;

    @Inject(
            method = "<clinit>",
            at = @At("TAIL")
    )
    private static void FantasyFurniture$clinit(CallbackInfo ci)
    {
        BEDS = Sets.newHashSet(BEDS);
    }
}
