package xyz.apex.forge.fantasyfurniture.common.item.crafting;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import xyz.apex.forge.fantasyfurniture.AllRecipeSerializers;
import xyz.apex.forge.fantasyfurniture.common.block.furniture.IDyeable;

public final class DyeableRecipe extends CustomRecipe
{
	public DyeableRecipe(ResourceLocation recipeId, CraftingBookCategory bookCategory)
	{
		super(recipeId, bookCategory);
	}

	@Override
	public boolean matches(CraftingContainer container, Level level)
	{
		DyeColor color = null;
		var dyeable = ItemStack.EMPTY;

		for(var i = 0; i < container.getContainerSize(); i++)
		{
			var stack = container.getItem(i);

			if(!stack.isEmpty())
			{
				var dyeColor = DyeColor.getColor(stack);

				if(dyeColor == null)
				{
					if(stack.is(IDyeable.TAG))
					{
						if(!dyeable.isEmpty())
							return false;

						dyeable = stack;
					}
				}
				else
				{
					if(color != null)
						return false;

					color = dyeColor;
				}
			}
		}

		return color != null && !dyeable.isEmpty();
	}

	@Override
	public ItemStack assemble(CraftingContainer container)
	{
		DyeColor color = null;
		var dyeable = ItemStack.EMPTY;

		for(var i = 0; i < container.getContainerSize(); i++)
		{
			var stack = container.getItem(i);

			if(!stack.isEmpty())
			{
				var dyeColor = DyeColor.getColor(stack);

				if(dyeColor == null)
				{
					if(stack.is(IDyeable.TAG))
					{
						if(!dyeable.isEmpty())
							return ItemStack.EMPTY;

						dyeable = stack.copy();
					}
				}
				else
				{
					if(color != null)
						return ItemStack.EMPTY;

					color = dyeColor;
				}
			}
		}

		if(color == null || dyeable.isEmpty())
			return ItemStack.EMPTY;

		dyeable.setCount(1);
		return IDyeable.setDyeColor(dyeable, color);
	}

	@Override
	public boolean canCraftInDimensions(int width, int height)
	{
		return width * height >= 2;
	}

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return AllRecipeSerializers.DYEABLE.get();
	}
}
