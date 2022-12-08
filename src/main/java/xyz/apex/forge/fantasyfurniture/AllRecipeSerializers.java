package xyz.apex.forge.fantasyfurniture;

import com.tterrag.registrate.util.entry.RegistryEntry;

import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.forge.fantasyfurniture.common.item.crafting.DyeableRecipe;

import static xyz.apex.forge.fantasyfurniture.core.ModRegistry.REGISTRATE;

public interface AllRecipeSerializers
{
	RegistryEntry<SimpleCraftingRecipeSerializer<DyeableRecipe>> DYEABLE = REGISTRATE.simple("dyeable", ForgeRegistries.Keys.RECIPE_SERIALIZERS, () -> new SimpleCraftingRecipeSerializer<>(DyeableRecipe::new));

	static void bootstrap()
	{
	}
}
