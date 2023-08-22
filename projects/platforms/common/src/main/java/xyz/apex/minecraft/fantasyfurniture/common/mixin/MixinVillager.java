package xyz.apex.minecraft.fantasyfurniture.common.mixin;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.schedule.Activity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.apex.minecraft.fantasyfurniture.common.entity.ai.goal.OpenDoorsTask;

@Mixin(Villager.class)
public abstract class MixinVillager
{
    @Inject(
            method = "registerBrainGoals",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/ai/behavior/VillagerGoalPackages;getCorePackage(Lnet/minecraft/world/entity/npc/VillagerProfession;F)Lcom/google/common/collect/ImmutableList;"
            )
    )
    private void FantasyFurniture$initCoreActivity(Brain<Villager> brain, CallbackInfo ci)
    {
        brain.addActivity(Activity.CORE, ImmutableList.of(Pair.of(0, OpenDoorsTask.create())));
    }
}
