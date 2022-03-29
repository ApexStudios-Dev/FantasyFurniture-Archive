package xyz.apex.forge.fantasyfurniture.client.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import xyz.apex.forge.fantasyfurniture.container.DrawerContainer;
import xyz.apex.forge.fantasyfurniture.init.FFRegistry;

@OnlyIn(Dist.CLIENT)
public final class DrawerContainerScreen extends InventoryContainerScreen<DrawerContainer>
{
	public static final ResourceLocation TEXTURE = FFRegistry.getInstance().id("textures/gui/container/small_storage.png");

	public DrawerContainerScreen(DrawerContainer menu, PlayerInventory playerInventory, ITextComponent titleComponent)
	{
		super(menu, playerInventory, titleComponent, TEXTURE);
	}
}
