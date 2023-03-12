package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;

public final class OvenMenu extends AbstractFurnaceMenu
{
    public OvenMenu(MenuType<? extends OvenMenu> menuType, int containerId, Player player, FriendlyByteBuf data)
    {
        super(menuType, RecipeType.SMOKING, RecipeBookType.SMOKER, containerId, player.getInventory());
    }

    public OvenMenu(MenuType<? extends OvenMenu> menuType, int containerId, Inventory playerInventory, Container container, ContainerData containerData)
    {
        super(menuType, RecipeType.SMOKING, RecipeBookType.SMOKER, containerId, playerInventory, container, containerData);
    }
}
