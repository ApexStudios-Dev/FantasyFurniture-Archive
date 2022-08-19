package xyz.apex.forge.fantasyfurniture.menu;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import xyz.apex.forge.apexcore.lib.container.BaseMenu;
import xyz.apex.forge.fantasyfurniture.init.ModElements;

import java.util.Objects;

public final class CookieJarMenu extends BaseMenu
{
	public static final int ROWS = 3;
	public static final int COLS = 3;
	public static final int SIZE = ROWS * COLS;

	public CookieJarMenu(@Nullable MenuType<? extends BaseMenu> menuType, int windowId, Inventory playerInventory, FriendlyByteBuf buffer)
	{
		super(menuType, windowId, playerInventory, buffer);

		var itemHandler = Objects.requireNonNull(getItemHandler());

		bindItemHandlerSlots(this, itemHandler, ROWS, COLS, 62, 17, CookieSlotItemHandler::new);
		bindPlayerInventory(this);

		addSlotListener(Objects.requireNonNull(ModElements.COOKIE_JAR_BLOCK_ENTITY.getNullable(player.level, pos)));
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

	public static final class CookieSlotItemHandler extends SlotItemHandler
	{
		public CookieSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition)
		{
			super(itemHandler, index, xPosition, yPosition);
		}

		@Override
		public boolean mayPlace(@NotNull ItemStack stack)
		{
			return stack.is(Items.COOKIE);
		}
	}
}