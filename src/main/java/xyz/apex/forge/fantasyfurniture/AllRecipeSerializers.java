package xyz.apex.forge.fantasyfurniture;

import com.tterrag.registrate.util.entry.RegistryEntry;

import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.forge.fantasyfurniture.common.item.crafting.DyeableRecipe;

import static xyz.apex.forge.fantasyfurniture.core.ModRegistry.REGISTRATE;

public interface AllRecipeSerializers
{
	RegistryEntry<SimpleRecipeSerializer<DyeableRecipe>> DYEABLE = REGISTRATE.simple("dyeable", ForgeRegistries.Keys.RECIPE_SERIALIZERS, () -> new SimpleRecipeSerializer<>(DyeableRecipe::new));

	static void bootstrap()
	{
	}
}
