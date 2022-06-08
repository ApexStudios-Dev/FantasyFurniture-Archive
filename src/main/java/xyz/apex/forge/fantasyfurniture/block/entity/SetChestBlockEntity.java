package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.fantasyfurniture.container.SetChestContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public final class SetChestBlockEntity extends InventoryBlockEntity<SetChestContainer>
{
	public SetChestBlockEntity(BlockEntityType<? extends SetChestBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
	{
		super(blockEntityType, pos, blockState, SetChestContainer::new);
	}

	@Override
	protected ItemStackHandler createInventoryHandler()
	{
		return new ItemStackHandler(SetChestContainer.SIZE);
	}

	@Override
	protected MenuType<SetChestContainer> getContainerType()
	{
		return FFElements.CHEST_CONTAINER.asMenuType();
	}
}
