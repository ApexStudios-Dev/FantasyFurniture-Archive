package xyz.apex.forge.fantasyfurniture.init;

import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.fantasyfurniture.client.screen.DrawerContainerScreen;
import xyz.apex.forge.fantasyfurniture.container.DrawerContainer;
import xyz.apex.forge.utility.registrator.entry.ContainerEntry;

public final class FFContainers
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();

	public static final ContainerEntry<DrawerContainer> DRAWER_CONTAINER = container("drawer", DrawerContainer.ROWS, DrawerContainer.COLS, DrawerContainer::new, DrawerContainerScreen::new);

	static void bootstrap()
	{
	}

	private static <CONTAINER extends Container, SCREEN extends Screen & IHasContainer<CONTAINER>> ContainerEntry<CONTAINER> container(String registryName, int rows, int cols, ContainerFactory<CONTAINER> containerFactory, xyz.apex.forge.utility.registrator.factory.ContainerFactory.ScreenFactory<CONTAINER, SCREEN> screenFactory)
	{
		return REGISTRY.container(
				registryName,
				(containerType, windowId, playerInventory, buffer) -> containerFactory.create(containerType, windowId, playerInventory, new ItemStackHandler(rows * cols)),
				() -> screenFactory
		).register();
	}

	@FunctionalInterface
	public interface ContainerFactory<CONTAINER extends Container>
	{
		CONTAINER create(ContainerType<?> containerType, int windowId, PlayerInventory playerInventory, IItemHandler itemHandler);
	}
}
