package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.fantasyfurniture.container.SetDeskContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public final class SetDeskBlockEntity extends InventoryBlockEntity<SetDeskContainer>
{
	public SetDeskBlockEntity(BlockEntityType<? extends SetDeskBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
	{
		super(blockEntityType, pos, blockState, SetDeskContainer::new);
	}

	@Override
	protected ItemStackHandler createInventoryHandler()
	{
		return new ItemStackHandler(SetDeskContainer.SIZE);
	}

	@Override
	protected MenuType<SetDeskContainer> getContainerType()
	{
		return FFElements.DESK_CONTAINER.asMenuType();
	}
}
