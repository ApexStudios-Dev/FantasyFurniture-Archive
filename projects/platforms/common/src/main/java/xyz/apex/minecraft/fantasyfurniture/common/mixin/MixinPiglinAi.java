package xyz.apex.minecraft.fantasyfurniture.common.mixin;

import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.apex.minecraft.fantasyfurniture.common.MixinShenanigans;

@Mixin(PiglinAi.class)
public abstract class MixinPiglinAi
{
    @Inject(
            method = "initCoreActivity",
            at = @At("TAIL")
    )
    private static void FantasyFurniture$initCoreActivity(Brain<Piglin> brain, CallbackInfo ci)
    {
        MixinShenanigans.registerPiglinDoorsTask(brain);
    }
}
