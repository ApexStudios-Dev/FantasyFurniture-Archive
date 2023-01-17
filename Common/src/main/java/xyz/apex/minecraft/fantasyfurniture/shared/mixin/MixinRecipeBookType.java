package xyz.apex.minecraft.fantasyfurniture.shared.mixin;

import com.google.common.collect.Lists;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.inventory.RecipeBookType;

import xyz.apex.minecraft.fantasyfurniture.shared.recipe.CustomRecipeBookTypes;

import java.util.Collections;

@Mixin(RecipeBookType.class)
public abstract class MixinRecipeBookType
{
    @Shadow(remap = false) @Mutable @Final private static RecipeBookType[] $VALUES;

    @Inject(
            method = "<clinit>",
            at = @At("TAIL")
    )
    private static void FantasyFurniture$registerBookType(CallbackInfo ci)
    {
        var ordinal = $VALUES[$VALUES.length - 1].ordinal();
        var newTypes = Lists.<RecipeBookType>newArrayList();
        Collections.addAll(newTypes, $VALUES);
        CustomRecipeBookTypes.FURNITURE_STATION = InvokerRecipeBookType.FantasyFurniture$init("FURNITURE_STATION", ordinal + 1);
        newTypes.add(CustomRecipeBookTypes.FURNITURE_STATION);
        $VALUES = newTypes.toArray(RecipeBookType[]::new);
    }
}
