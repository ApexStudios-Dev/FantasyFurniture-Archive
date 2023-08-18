package xyz.apex.minecraft.fantasyfurniture.common.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.common.lib.SideOnly;
import xyz.apex.minecraft.apexcore.common.lib.menu.SimpleContainerMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.common.menu.LargeContainerMenu;

@SideOnly(PhysicalSide.CLIENT)
public final class LargeContainerMenuScreen extends SimpleContainerMenuScreen<LargeContainerMenu>
{
    public LargeContainerMenuScreen(LargeContainerMenu menu, Inventory playerInventory, Component title)
    {
        super(menu, playerInventory, title);
    }
}
