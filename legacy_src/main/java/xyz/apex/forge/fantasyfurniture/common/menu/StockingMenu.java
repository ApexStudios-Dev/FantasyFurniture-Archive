package xyz.apex.forge.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;
import xyz.apex.forge.apexcore.lib.container.BaseMenu;

import java.util.Objects;

public final class StockingMenu extends BaseMenu
{
	public static final int ROWS = 2;
	public static final int COLS = 3;
	public static final int SIZE = ROWS * COLS;

	public StockingMenu(@Nullable MenuType<? extends StockingMenu> menuType, int windowId, Inventory playerInventory, FriendlyByteBuf buffer)
	{
		super(menuType, windowId, playerInventory, buffer);

		var itemHandler = Objects.requireNonNull(getItemHandler());

		bindItemHandlerSlots(this, itemHandler, ROWS, COLS, 62, 26);
		bindPlayerInventory(this, 8, 84);
	}

	@Nullable
	@Override
	protected IItemHandler getItemHandler()
	{
		var blockEntity = Objects.requireNonNull(player.level.getBlockEntity(pos));
		return getItemHandlerFromBlockEntity(blockEntity).resolve().orElse(null);
	}

	@Override
	protected void onInventoryChanges()
	{
		setBlockEntityChanged();
	}
}
