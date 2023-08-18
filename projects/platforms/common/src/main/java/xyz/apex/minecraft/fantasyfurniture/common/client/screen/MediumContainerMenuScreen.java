package xyz.apex.minecraft.fantasyfurniture.common.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.common.lib.SideOnly;
import xyz.apex.minecraft.apexcore.common.lib.menu.SimpleContainerMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.common.menu.MediumContainerMenu;

@SideOnly(PhysicalSide.CLIENT)
public final class MediumContainerMenuScreen extends SimpleContainerMenuScreen<MediumContainerMenu>
{
    public MediumContainerMenuScreen(MediumContainerMenu menu, Inventory playerInventory, Component title)
    {
        super(menu, playerInventory, title);
    }
}
