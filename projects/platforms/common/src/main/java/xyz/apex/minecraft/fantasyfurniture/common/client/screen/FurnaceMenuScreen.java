package xyz.apex.minecraft.fantasyfurniture.common.client.screen;

import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.common.lib.SideOnly;
import xyz.apex.minecraft.fantasyfurniture.common.menu.FurnaceMenu;

@SideOnly(PhysicalSide.CLIENT)
public final class FurnaceMenuScreen extends AbstractFurnaceScreen<FurnaceMenu>
{
    public FurnaceMenuScreen(FurnaceMenu menu, Inventory inventory, Component title)
    {
        super(menu, menu.getRecipeBookComponent(), inventory, title, menu.getMenuTexture(), menu.getLitProgressSprite(), menu.getBurnProgressSprite());
    }
}
