package xyz.apex.minecraft.fantasyfurniture.shared.mixin;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.stats.RecipeBookSettings;
import net.minecraft.world.inventory.RecipeBookType;

import xyz.apex.minecraft.fantasyfurniture.shared.recipe.CustomRecipeBookTypes;

import java.util.Map;

@Mixin(RecipeBookSettings.class)
public abstract class MixinRecipeBookSettings
{
    @Shadow @Mutable @Final private static Map<RecipeBookType, Pair<String, String>> TAG_FIELDS;

    @Inject(
            method = "<clinit>",
            at = @At("TAIL")
    )
    private static void FantasyFurniture$init(CallbackInfo ci)
    {
        if(TAG_FIELDS instanceof ImmutableMap) TAG_FIELDS = Maps.newHashMap(TAG_FIELDS);
        TAG_FIELDS.put(CustomRecipeBookTypes.FURNITURE_STATION, Pair.of("isFurnitureStationGuiOpen", "isFurnitureStationFilteringCraftable"));
    }
}
