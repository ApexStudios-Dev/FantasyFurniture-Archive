package xyz.apex.forge.fantasyfurniture.menu;

import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.Nullable;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.items.CapabilityItemHandler;

import xyz.apex.forge.apexcore.revamp.container.BaseMenu;

public final class SmallInventoryMenu extends BaseMenu
{
	public static final int ROWS = 3;
	public static final int COLS = 5;
	public static final int SIZE = ROWS * COLS;

	public SmallInventoryMenu(@Nullable MenuType<? extends SmallInventoryMenu> menuType, int windowId, Inventory playerInventory, FriendlyByteBuf buffer)
	{
		super(menuType, windowId, playerInventory, buffer);

		Validate.notNull(blockEntity);
		var itemHandler = blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).resolve().orElseThrow();

		bindItemHandlerSlots(this, itemHandler, ROWS, COLS, 44, 18);
		bindPlayerInventory(this, 8, 84);
	}
}