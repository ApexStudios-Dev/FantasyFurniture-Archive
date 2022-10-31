package xyz.apex.forge.fantasyfurniture;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.util.Lazy;

import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.fantasyfurniture.common.item.crafting.DyeableRecipe;

public interface AllRecipeTypes
{
	Lazy<RecipeType<DyeableRecipe>> DYEABLE = Lazy.of(() -> RecipeType.register("%s:dyeable".formatted(Mods.FANTASY_FURNITURE)));

	static void bootstrap()
	{
	}
}
