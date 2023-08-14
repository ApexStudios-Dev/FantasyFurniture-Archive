package xyz.apex.forge.fantasyfurniture;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.fantasyfurniture.common.item.crafting.DyeableRecipe;

import static xyz.apex.forge.fantasyfurniture.core.ModRegistry.REGISTRATE;

public interface AllRecipeTypes
{
	RegistryEntry<RecipeType<DyeableRecipe>> DYEABLE = REGISTRATE.simple("dyeable", ForgeRegistries.Keys.RECIPE_TYPES, () -> RecipeType.simple(new ResourceLocation(Mods.FANTASY_FURNITURE, "dyeable")));

	static void bootstrap()
	{
	}
}
