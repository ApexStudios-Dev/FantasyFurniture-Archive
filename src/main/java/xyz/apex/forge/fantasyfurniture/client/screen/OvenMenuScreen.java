package xyz.apex.forge.fantasyfurniture.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import xyz.apex.forge.apexcore.lib.client.screen.BaseMenuScreen;
import xyz.apex.forge.fantasyfurniture.init.ModElements;
import xyz.apex.forge.fantasyfurniture.menu.OvenMenu;

public final class OvenMenuScreen extends BaseMenuScreen<OvenMenu>
{
	public OvenMenuScreen(OvenMenu menu, Inventory playerInventory, Component title)
	{
		super(menu, playerInventory, title, ModElements.OVEN_TEXTURE);
	}

	@Override
	protected void init()
	{
		super.init();
		titleLabelX = (imageWidth - font.width(title)) / 2;
	}

	@Override
	protected void renderBg(PoseStack pose, float partialTicks, int mouseX, int mouseY)
	{
		super.renderBg(pose, partialTicks, mouseX, mouseY);

		var burnProgress = menu.getBurnProgress();
		var smeltProgress = menu.getSmeltProgress();

		if(burnProgress > 0)
		{
			var burnDrawAmount = burnProgress / 13;
			blit(pose, leftPos + 56, topPos + 36 + 12 - burnDrawAmount, 176, 12 - burnDrawAmount, 14, burnDrawAmount + 1);
		}

		if(smeltProgress > 0)
			blit(pose, leftPos + 79, topPos + 34, 176, 14, smeltProgress, 16);
	}
}