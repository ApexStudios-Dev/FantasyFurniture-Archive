package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import xyz.apex.forge.apexcore.lib.block.entity.BaseBlockEntity;
import xyz.apex.forge.apexcore.lib.util.INameableMutable;
import xyz.apex.forge.fantasyfurniture.container.FurnitureStationContainer;
import xyz.apex.forge.fantasyfurniture.init.FurnitureStation;

import javax.annotation.Nullable;

public final class FurnitureStationBlockEntity extends BaseBlockEntity implements INamedContainerProvider, INameableMutable
{
	public static final String NBT_CUSTOM_NAME = InventoryBlockEntity.NBT_CUSTOM_NAME;

	@Nullable private ITextComponent customName = null;

	public FurnitureStationBlockEntity(TileEntityType<?> blockEntityType)
	{
		super(blockEntityType);
	}

	@Override
	public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player)
	{
		World level = this.level == null ? player.level : this.level;
		return new FurnitureStationContainer(FurnitureStation.CONTAINER.asContainerType(), windowId, playerInventory, IWorldPosCallable.create(level, worldPosition));
	}

	@Override
	public void setCustomName(@Nullable ITextComponent customName)
	{
		this.customName = customName;
	}

	@Override
	public void load(BlockState blockState, CompoundNBT tagCompound)
	{
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
		if(customName != null)
		{
			String customNameJson = ITextComponent.Serializer.toJson(customName);
			tagCompound.putString(NBT_CUSTOM_NAME, customNameJson);
		}

		return super.save(tagCompound);
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
		return new TranslationTextComponent(getBlockState().getBlock().getDescriptionId());
	}

	@Override
	protected CompoundNBT writeUpdateTag(CompoundNBT tagCompound)
	{
		if(customName != null)
		{
			String customNameJson = ITextComponent.Serializer.toJson(customName);
			tagCompound.putString(NBT_CUSTOM_NAME, customNameJson);
		}

		return super.writeUpdateTag(tagCompound);
	}

	@Override
	protected void readeUpdateTag(CompoundNBT tagCompound)
	{
		if(tagCompound.contains(NBT_CUSTOM_NAME, Constants.NBT.TAG_STRING))
		{
			String customNameJson = tagCompound.getString(NBT_CUSTOM_NAME);
			customName = ITextComponent.Serializer.fromJson(customNameJson);
		}

		super.readeUpdateTag(tagCompound);
	}
}
