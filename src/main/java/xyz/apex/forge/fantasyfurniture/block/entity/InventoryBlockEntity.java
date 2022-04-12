package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.apexcore.lib.util.INameableMutable;
import xyz.apex.forge.fantasyfurniture.init.Registrations;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class InventoryBlockEntity<CONTAINER extends Container> extends TileEntity implements INamedContainerProvider, INameableMutable, IContainerListener
{
	public static final String NBT_INVENTORY = "Inventory";
	public static final String NBT_CUSTOM_NAME = "CustomName";

	@Nullable private ItemStackHandler inventory = null;
	private final LazyOptional<IItemHandler> itemHandlerCapability = LazyOptional.of(this::createItemHandler);
	@Nullable private ITextComponent customName = null;
	private final Registrations.ContainerFactory<CONTAINER> containerFactory;

	public InventoryBlockEntity(TileEntityType<? extends InventoryBlockEntity> blockEntityType, Registrations.ContainerFactory<CONTAINER> containerFactory)
	{
		super(blockEntityType);

		this.containerFactory = containerFactory;

		itemHandlerCapability.addListener(opt -> inventory = null);
	}

	public final IItemHandler getItemHandler()
	{
		return itemHandlerCapability.orElseGet(this::createItemHandler);
	}

	protected abstract ItemStackHandler createInventoryHandler();
	protected abstract ContainerType<CONTAINER> getContainerType();

	private IItemHandler createItemHandler()
	{
		if(inventory == null)
			inventory = createInventoryHandler();
		return inventory;
	}

	@Override
	public final Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player)
	{
		CONTAINER drawerContainer = containerFactory.create(getContainerType(), windowId, playerInventory, getItemHandler());
		drawerContainer.addSlotListener(this);
		return drawerContainer;
	}

	@Override
	public void setCustomName(@Nullable ITextComponent customName)
	{
		this.customName = customName;
	}

	@Override
	public void load(BlockState blockState, CompoundNBT tagCompound)
	{
		if(tagCompound.contains(NBT_INVENTORY, Constants.NBT.TAG_COMPOUND))
		{
			itemHandlerCapability.invalidate();
			inventory = createInventoryHandler();
			CompoundNBT inventoryTag = tagCompound.getCompound(NBT_INVENTORY);
			inventory.deserializeNBT(inventoryTag);
		}

		if(tagCompound.contains(NBT_CUSTOM_NAME, Constants.NBT.TAG_STRING))
		{
			String customNameJson = tagCompound.getString(NBT_CUSTOM_NAME);
			customName = ITextComponent.Serializer.fromJson(customNameJson);
		}

		super.load(blockState, tagCompound);
	}

	@Override
	public CompoundNBT save(CompoundNBT tagCompound)
	{
		if(inventory != null)
		{
			CompoundNBT inventoryTag = inventory.serializeNBT();
			tagCompound.put(NBT_INVENTORY, inventoryTag);
		}

		if(customName != null)
		{
			String customNameJson = ITextComponent.Serializer.toJson(customName);
			tagCompound.putString(NBT_CUSTOM_NAME, customNameJson);
		}

		return super.save(tagCompound);
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
	public ITextComponent getCustomName()
	{
		return customName;
	}

	@Override
	public ITextComponent getDisplayName()
	{
		return customName == null ? getName() : customName;
	}

	@Override
	public ITextComponent getName()
	{
		return getBlockState().getBlock().getName();
	}

	@Override
	public void refreshContainer(Container container, NonNullList<ItemStack> stacks)
	{
	}

	@Override
	public void slotChanged(Container container, int slotIndex, ItemStack stack)
	{
		setChanged();
	}

	@Override
	public void setContainerData(Container container, int varToUpdate, int newValue)
	{
	}
}
