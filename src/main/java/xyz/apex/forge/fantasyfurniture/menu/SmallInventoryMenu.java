package xyz.apex.forge.fantasyfurniture.menu;

import org.jetbrains.annotations.Nullable;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.items.IItemHandler;

import xyz.apex.forge.apexcore.lib.container.BaseMenu;

import java.util.Objects;

public final class SmallInventoryMenu extends BaseMenu
{
	public static final int ROWS = 3;
	public static final int COLS = 5;
	public static final int SIZE = ROWS * COLS;

	public SmallInventoryMenu(@Nullable MenuType<? extends SmallInventoryMenu> menuType, int windowId, Inventory playerInventory, FriendlyByteBuf buffer)
	{
		super(menuType, windowId, playerInventory, buffer);

		var itemHandler = Objects.requireNonNull(getItemHandler());

		bindItemHandlerSlots(this, itemHandler, ROWS, COLS, 44, 18);
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