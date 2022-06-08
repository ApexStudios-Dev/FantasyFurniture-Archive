package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.forge.apexcore.lib.block.entity.BaseBlockEntity;
import xyz.apex.forge.apexcore.lib.util.NameableMutable;
import xyz.apex.forge.fantasyfurniture.container.FurnitureStationContainer;
import xyz.apex.forge.fantasyfurniture.init.FurnitureStation;

import javax.annotation.Nullable;

public final class FurnitureStationBlockEntity extends BaseBlockEntity implements MenuProvider, NameableMutable
{
	public static final String NBT_CUSTOM_NAME = InventoryBlockEntity.NBT_CUSTOM_NAME;

	@Nullable private Component customName = null;

	public FurnitureStationBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState blockState)
	{
		super(blockEntityType, pos, blockState);
	}

	@Override
	public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player player)
	{
		var level = this.level == null ? player.level : this.level;
		return new FurnitureStationContainer(FurnitureStation.CONTAINER.asMenuType(), windowId, playerInventory, ContainerLevelAccess.create(level, worldPosition));
	}

	@Override
	public void setCustomName(@Nullable Component customName)
	{
		this.customName = customName;
	}

	@Override
	public void load(CompoundTag tagCompound)
	{
		if(tagCompound.contains(NBT_CUSTOM_NAME, Tag.TAG_STRING))
		{
			var customNameJson = tagCompound.getString(NBT_CUSTOM_NAME);
			customName = TextComponent.Serializer.fromJson(customNameJson);
		}

		super.load(tagCompound);
	}

	@Override
	public CompoundTag save(CompoundTag tagCompound)
	{
		if(customName != null)
		{
			var customNameJson = TextComponent.Serializer.toJson(customName);
			tagCompound.putString(NBT_CUSTOM_NAME, customNameJson);
		}

		return super.save(tagCompound);
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
	protected CompoundTag writeUpdateTag(CompoundTag tagCompound)
	{
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
		if(tagCompound.contains(NBT_CUSTOM_NAME, Tag.TAG_STRING))
		{
			var customNameJson = tagCompound.getString(NBT_CUSTOM_NAME);
			customName = TextComponent.Serializer.fromJson(customNameJson);
		}

		super.readeUpdateTag(tagCompound);
	}
}
