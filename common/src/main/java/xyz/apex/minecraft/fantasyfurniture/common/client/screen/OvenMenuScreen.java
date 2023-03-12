package xyz.apex.minecraft.fantasyfurniture.common.client.screen;

import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmokingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import xyz.apex.minecraft.fantasyfurniture.common.menu.OvenMenu;

public final class OvenMenuScreen extends AbstractFurnaceScreen<OvenMenu>
{
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("minecraft", "textures/gui/container/smoker.png");

    public OvenMenuScreen(OvenMenu menu, Inventory inventory, Component displayName)
    {
        super(menu, new SmokingRecipeBookComponent(), inventory, displayName, BACKGROUND_TEXTURE);
    }
}
