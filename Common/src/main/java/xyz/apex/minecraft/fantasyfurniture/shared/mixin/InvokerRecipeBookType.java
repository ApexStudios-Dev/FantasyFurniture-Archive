package xyz.apex.minecraft.fantasyfurniture.shared.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.inventory.RecipeBookType;

@Mixin(RecipeBookType.class)
public interface InvokerRecipeBookType
{
    @Invoker("<init>")
    static RecipeBookType FantasyFurniture$init(String name, int original)
    {
        throw new AssertionError("Internal Mixins Error!");
    }
}
