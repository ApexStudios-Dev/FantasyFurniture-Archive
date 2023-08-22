package xyz.apex.minecraft.fantasyfurniture.common.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.schedule.Activity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.apex.minecraft.fantasyfurniture.common.entity.ai.goal.OpenDoorsTask;

@Mixin(PiglinAi.class)
public abstract class MixinPiglinAi
{
    @Inject(
            method = "initCoreActivity",
            at = @At("TAIL")
    )
    private static void FantasyFurniture$initCoreActivity(Brain<Piglin> brain, CallbackInfo ci)
    {
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(OpenDoorsTask.create()));
    }
}
