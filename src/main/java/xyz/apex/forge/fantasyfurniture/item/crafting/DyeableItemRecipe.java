package xyz.apex.forge.fantasyfurniture.item.crafting;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import xyz.apex.forge.fantasyfurniture.init.ModElements;
import xyz.apex.forge.fantasyfurniture.item.dyeable.IDyeableItem;

public class DyeableItemRecipe extends CustomRecipe
{
	public DyeableItemRecipe(ResourceLocation recipeId)
	{
		super(recipeId);
	}

	@Override
	public boolean matches(CraftingContainer container, Level level)
	{
		var dyeableStack = ItemStack.EMPTY;
		DyeColor dyeColor = null;

		for(var i = 0; i < container.getContainerSize(); i++)
		{
			var stack = container.getItem(i);

			if(!stack.isEmpty())
			{
				if(stack.getItem() instanceof IDyeableItem)
				{
					if(!dyeableStack.isEmpty())
						return false;

					dyeableStack = stack;
				}
				else
				{
					var color = DyeColor.getColor(stack);

					if(dyeColor != null)
						return false;

					dyeColor = color;
				}
			}
		}

		return dyeColor != null && !dyeableStack.isEmpty();
	}

	@Override
	public ItemStack assemble(CraftingContainer container)
	{
		var dyeableStack = ItemStack.EMPTY;
		DyeColor dyeColor = null;

		for(var i = 0; i < container.getContainerSize(); i++)
		{
			var stack = container.getItem(i);

			if(!stack.isEmpty())
			{
				if(stack.getItem() instanceof IDyeableItem)
				{
					if(!dyeableStack.isEmpty())
						return ItemStack.EMPTY;

					dyeableStack = stack.copy();
				}
				else
				{
					var color = DyeColor.getColor(stack);

					if(dyeColor != null)
						return ItemStack.EMPTY;

					dyeColor = color;
				}
			}
		}

		if(dyeColor == null || dyeableStack.isEmpty())
			return ItemStack.EMPTY;

		var stack = IDyeableItem.withDyeColor(dyeableStack, dyeColor);
		stack.setCount(1);
		return stack;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height)
	{
		return width * height >= 2;
	}

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return ModElements.DYEABLE_ITEM_RECIPE.get();
	}
}