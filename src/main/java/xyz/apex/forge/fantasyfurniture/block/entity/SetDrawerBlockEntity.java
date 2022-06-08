package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.fantasyfurniture.container.SetDrawerContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public final class SetDrawerBlockEntity extends InventoryBlockEntity<SetDrawerContainer>
{
	public SetDrawerBlockEntity(BlockEntityType<? extends SetDrawerBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
	{
		super(blockEntityType, pos, blockState, SetDrawerContainer::new);
	}

	@Override
	protected ItemStackHandler createInventoryHandler()
	{
		return new ItemStackHandler(SetDrawerContainer.SIZE);
	}

	@Override
	protected MenuType<SetDrawerContainer> getContainerType()
	{
		return FFElements.DRAWER_CONTAINER.asMenuType();
	}
}
