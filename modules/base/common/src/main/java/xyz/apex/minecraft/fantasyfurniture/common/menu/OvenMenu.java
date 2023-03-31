package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;

public final class OvenMenu extends AbstractFurnaceMenu
{
    public OvenMenu(int containerId, Player player, FriendlyByteBuf data)
    {
        super(AllMenuTypes.OVEN.get(), RecipeType.SMOKING, RecipeBookType.SMOKER, containerId, player.getInventory());
    }

    public OvenMenu(int containerId, Inventory playerInventory, Container container, ContainerData containerData)
    {
        super(AllMenuTypes.OVEN.get(), RecipeType.SMOKING, RecipeBookType.SMOKER, containerId, playerInventory, container, containerData);
    }
}
