package xyz.apex.minecraft.fantasyfurniture.fabric.mixin;

import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.apex.minecraft.fantasyfurniture.common.feature.seat.Seat;

@Mixin(LivingEntity.class)
@ApiStatus.Internal
@ApiStatus.NonExtendable
public abstract class MixinLivingEntity
{
    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;tick()V"
            )
    )
    private void FantasyFurniture$tick(CallbackInfo ci)
    {
        var self = (LivingEntity) (Object) this;
        Seat.onEntityTick(self);
    }
}
