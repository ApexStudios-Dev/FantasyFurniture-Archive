package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.apexcore.lib.block.entity.BaseBlockEntity;
import xyz.apex.forge.apexcore.lib.util.NameableMutable;
import xyz.apex.forge.fantasyfurniture.init.Registrations;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class InventoryBlockEntity<MENU extends AbstractContainerMenu> extends BaseBlockEntity implements MenuProvider, NameableMutable, ContainerListener
{
	public static final String NBT_INVENTORY = "Inventory";
	public static final String NBT_CUSTOM_NAME = "CustomName";

	@Nullable private ItemStackHandler inventory = null;
	private final LazyOptional<IItemHandler> itemHandlerCapability = LazyOptional.of(this::createItemHandler);
	@Nullable private Component customName = null;
	private final Registrations.MenuFactory<MENU> menuFactory;

	public InventoryBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState blockState, Registrations.MenuFactory<MENU> menuFactory)
	{
		super(blockEntityType, pos, blockState);

		this.menuFactory = menuFactory;

		itemHandlerCapability.addListener(opt -> inventory = null);
	}

	public final IItemHandler getItemHandler()
	{
		return itemHandlerCapability.orElseGet(this::createItemHandler);
	}

	protected abstract ItemStackHandler createInventoryHandler();
	protected abstract MenuType<MENU> getContainerType();

	private IItemHandler createItemHandler()
	{
		if(inventory == null)
			inventory = createInventoryHandler();
		return inventory;
	}

	@Override
	public final AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player player)
	{
		var drawerContainer = menuFactory.create(getContainerType(), windowId, playerInventory, getItemHandler());
		drawerContainer.addSlotListener(this);
		return drawerContainer;
	}

	@Override
	public void setCustomName(@Nullable Component customName)
	{
		this.customName = customName;
	}

	@Override
	public void deserializeNBT(CompoundTag tagCompound)
	{
		if(tagCompound.contains(NBT_INVENTORY, Tag.TAG_COMPOUND))
		{
			itemHandlerCapability.invalidate();
			inventory = createInventoryHandler();
			var inventoryTag = tagCompound.getCompound(NBT_INVENTORY);
			inventory.deserializeNBT(inventoryTag);
		}

		if(tagCompound.contains(NBT_CUSTOM_NAME, Tag.TAG_STRING))
		{
			var customNameJson = tagCompound.getString(NBT_CUSTOM_NAME);
			customName = TextComponent.Serializer.fromJson(customNameJson);
		}

		super.deserializeNBT(tagCompound);
	}

	@Override
	public CompoundTag serializeNBT()
	{
		var tagCompound = super.serializeNBT();

		if(inventory != null)
		{
			var inventoryTag = inventory.serializeNBT();
			tagCompound.put(NBT_INVENTORY, inventoryTag);
		}

		if(customName != null)
		{
			var customNameJson = TextComponent.Serializer.toJson(customName);
			tagCompound.putString(NBT_CUSTOM_NAME, customNameJson);
		}

		return tagCompound;
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side)
	{
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return itemHandlerCapability.cast();

		return super.getCapability(capability, side);
	}

	@Nullable
	@Override
	public Component getCustomName()
	{
		return customName;
	}

	@Override
	public Component getDisplayName()
	{
		return customName == null ? getName() : customName;
	}

	@Override
	public Component getName()
	{
		return new TranslatableComponent(getBlockState().getBlock().getDescriptionId());
	}

	@Override
	public void slotChanged(AbstractContainerMenu container, int slotIndex, ItemStack stack)
	{
		setChanged();
	}

	@Override
	public void dataChanged(AbstractContainerMenu container, int varToUpdate, int newValue)
	{
	}

	@Override
	protected CompoundTag writeUpdateTag(CompoundTag tagCompound)
	{
		if(inventory != null)
		{
			var inventoryTag = inventory.serializeNBT();
			tagCompound.put(NBT_INVENTORY, inventoryTag);
		}

		if(customName != null)
		{
			var customNameJson = TextComponent.Serializer.toJson(customName);
			tagCompound.putString(NBT_CUSTOM_NAME, customNameJson);
		}

		return super.writeUpdateTag(tagCompound);
	}

	@Override
	protected void readeUpdateTag(CompoundTag tagCompound)
	{
		if(tagCompound.contains(NBT_INVENTORY, Tag.TAG_COMPOUND))
		{
			itemHandlerCapability.invalidate();
			inventory = createInventoryHandler();
			var inventoryTag = tagCompound.getCompound(NBT_INVENTORY);
			inventory.deserializeNBT(inventoryTag);
		}

		if(tagCompound.contains(NBT_CUSTOM_NAME, Tag.TAG_STRING))
		{
			var customNameJson = tagCompound.getString(NBT_CUSTOM_NAME);
			customName = TextComponent.Serializer.fromJson(customNameJson);
		}

		super.readeUpdateTag(tagCompound);
	}
}
