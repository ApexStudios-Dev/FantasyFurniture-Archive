package xyz.apex.minecraft.fantasyfurniture.shared.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;

@Mixin(RecipeBookCategories.class)
public interface InvokerRecipeBookCategories
{
    @Invoker("<init>")
    static RecipeBookCategories FantasyFurniture$init(String name, int original, ItemStack[] itemIcons)
    {
        throw new AssertionError("Internal Mixins Error!");
    }
}
