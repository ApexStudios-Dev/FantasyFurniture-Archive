package xyz.apex.forge.fantasyfurniture.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.forge.apexcore.lib.block.entity.InventoryBlockEntity;
import xyz.apex.forge.fantasyfurniture.common.block.decorations.CookieJarBlock;
import xyz.apex.forge.fantasyfurniture.common.menu.CookieJarMenu;

public final class CookieJarBlockEntity extends InventoryBlockEntity implements ContainerListener
{
	public CookieJarBlockEntity(BlockEntityType<? extends InventoryBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
	{
		super(blockEntityType, pos, blockState, CookieJarMenu.SIZE);
	}

	@Override
	public void slotChanged(AbstractContainerMenu menu, int slot, ItemStack stack)
	{
		updateFillState();
	}

	@Override
	public void dataChanged(AbstractContainerMenu menu, int slot, int vale)
	{
	}

	public void updateFillState()
	{
		if(level != null)
		{
			var oldBlockState = getBlockState();
			var maxItems = 0;
			var filledItems = 0;

			for(var i = 0; i < itemHandler.getSlots(); i++)
			{
				var stack = itemHandler.getStackInSlot(i);

				if(stack.isEmpty())
					maxItems += itemHandler.getSlotLimit(i);
				else
				{
					maxItems += stack.getMaxStackSize();
					filledItems += stack.getCount();
				}
			}

			var fillPercent = filledItems * 100 / maxItems;
			BlockState newBlockState;

			if(fillPercent < 33)
				newBlockState = oldBlockState.setValue(CookieJarBlock.FILL_STAGE, CookieJarBlock.FillStage.EMPTY);
			else if(fillPercent < 66)
				newBlockState = oldBlockState.setValue(CookieJarBlock.FILL_STAGE, CookieJarBlock.FillStage.HALF);
			else
				newBlockState = oldBlockState.setValue(CookieJarBlock.FILL_STAGE, CookieJarBlock.FillStage.FULL);

			if(oldBlockState != newBlockState)
			{
				level.setBlockAndUpdate(worldPosition, newBlockState);
				setChanged(level, worldPosition, newBlockState);
			}
		}
	}
}
