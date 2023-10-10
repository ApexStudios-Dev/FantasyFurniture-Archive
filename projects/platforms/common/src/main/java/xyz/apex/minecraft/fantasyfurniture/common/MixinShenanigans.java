package xyz.apex.minecraft.fantasyfurniture.common;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.schedule.Activity;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.entity.ai.goal.OpenDoorsTask;

// Exists because mixins are funky
// TODO: is this the best location for these?
@ApiStatus.NonExtendable
@ApiStatus.Internal
public interface MixinShenanigans
{
    static void registerVillagerDoorsTask(Brain<Villager> brain)
    {
        brain.addActivity(Activity.CORE, ImmutableList.of(Pair.of(0, OpenDoorsTask.create())));
    }

    static void registerPiglinDoorsTask(Brain<Piglin> brain)
    {
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(OpenDoorsTask.create()));
    }
}
