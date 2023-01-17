package xyz.apex.minecraft.fantasyfurniture.shared.mixin.client;

import com.google.common.collect.Lists;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;

import xyz.apex.minecraft.fantasyfurniture.shared.recipe.CustomRecipeBookCategories;

import java.util.Collections;
import java.util.List;

@Mixin(RecipeBookCategories.class)
public abstract class MixinRecipeBookCategories
{
    @Shadow(remap = false) @Mutable @Final private static RecipeBookCategories[] $VALUES;

    @Inject(
            method = "getIconItems",
            at = @At("HEAD"),
            cancellable = true
    )
    private void FantasyFurniture$getIconItems(CallbackInfoReturnable<List<ItemStack>> cir)
    {
        var itemIcons = CustomRecipeBookCategories.getIcons((RecipeBookCategories) (Object) this);
        if(itemIcons != null) cir.setReturnValue(itemIcons);
    }

    @Inject(
            method = "getCategories",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void FantasyFurniture$getCategories(RecipeBookType recipeBookType, CallbackInfoReturnable<List<RecipeBookCategories>> cir)
    {
        var categories = CustomRecipeBookCategories.getCategories(recipeBookType);
        if(categories != null) cir.setReturnValue(categories);
    }

    @Inject(
            method = "<clinit>",
            at = @At("TAIL")
    )
    private static void FantasyFurniture$registerBookType(CallbackInfo ci)
    {
        var ordinal = $VALUES[$VALUES.length - 1].ordinal();
        var newTypes = Lists.<RecipeBookCategories>newArrayList();
        Collections.addAll(newTypes, $VALUES);
        CustomRecipeBookCategories.FURNITURE_STATION_NORDIC = InvokerRecipeBookCategories.FantasyFurniture$init("FURNITURE_STATION_NORDIC", ordinal + 1, new ItemStack[0]);
        newTypes.add(CustomRecipeBookCategories.FURNITURE_STATION_NORDIC);
        $VALUES = newTypes.toArray(RecipeBookCategories[]::new);
    }
}
