package xyz.apex.forge.fantasyfurniture.integration.jei;

import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.helpers.IStackHelper;
import mezz.jei.api.recipe.transfer.IRecipeTransferError;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import mezz.jei.api.registration.IRecipeTransferRegistration;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.container.FurnitureStationContainer;
import xyz.apex.forge.fantasyfurniture.net.ServerTransferFurnitureStationResultPacket;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings({ "unchecked", "SameParameterValue" })
public final class FurnitureStationRecipeTransferHandler implements IRecipeTransferHandler<FurnitureStationContainer>
{
	private final IRecipeTransferHandler<FurnitureStationContainer> basicHandler;

	FurnitureStationRecipeTransferHandler(IRecipeTransferRegistration registration)
	{
		IRecipeTransferInfo<FurnitureStationContainer> basicInfo = createBasicTransferInfo(FurnitureStationContainer.class, JeiIntegration.FURNITURE_STATION_RECIPES, 0, 4, 5, (9 * 3) + 9);
		basicHandler = createBasicTransferHandler(registration, basicInfo);
	}

	@Nullable
	@Override
	public IRecipeTransferError transferRecipe(FurnitureStationContainer container, Object recipe, IRecipeLayout recipeLayout, PlayerEntity player, boolean maxTransfer, boolean doTransfer)
	{
		IRecipeTransferError error = basicHandler.transferRecipe(container, recipe, recipeLayout, player, maxTransfer, doTransfer);

		if(error == null && doTransfer)
		{
			Screen screen = Minecraft.getInstance().screen;

			if(recipe instanceof Item)
			{
				Item selected = (Item) recipe;
				FantasyFurniture.NETWORK.sendToServer(new ServerTransferFurnitureStationResultPacket(selected));
			}
		}

		return error;
	}

	@Override
	public Class<FurnitureStationContainer> getContainerClass()
	{
		return FurnitureStationContainer.class;
	}

	private static <C extends Container> IRecipeTransferInfo<C> createBasicTransferInfo(Class<C> containerClass, ResourceLocation recipeCategory, int inputStart, int inputCount, int playerStart, int playerCount)
	{
		try
		{
			Class<?> clazz = Class.forName("mezz.jei.transfer.BasicRecipeTransferInfo");

			if(IRecipeTransferInfo.class.isAssignableFrom(clazz))
			{
				Constructor<?> constructor = clazz.getConstructor(Class.class, ResourceLocation.class, int.class, int.class, int.class, int.class);
				return (IRecipeTransferInfo<C>) constructor.newInstance(containerClass, recipeCategory, inputStart, inputCount, playerStart, playerCount);
			}
		}
		catch(ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}

		throw new NullPointerException();
	}

	private static <C extends Container> IRecipeTransferHandler<C> createBasicTransferHandler(IRecipeTransferRegistration registration, IRecipeTransferInfo<C> transferInfo)
	{
		try
		{
			Class<?> clazz = Class.forName("mezz.jei.transfer.BasicRecipeTransferHandler");

			if(IRecipeTransferHandler.class.isAssignableFrom(clazz))
			{
				Constructor<?> constructor = clazz.getConstructor(IStackHelper.class, IRecipeTransferHandlerHelper.class, IRecipeTransferInfo.class);
				IStackHelper stackHelper = registration.getJeiHelpers().getStackHelper();
				IRecipeTransferHandlerHelper transferHelper = registration.getTransferHelper();
				return (IRecipeTransferHandler<C>) constructor.newInstance(stackHelper, transferHelper, transferInfo);
			}
		}
		catch(ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}

		throw new NullPointerException();
	}
}
