package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.fantasyfurniture.container.SetDresserContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public final class SetDresserBlockEntity extends InventoryBlockEntity<SetDresserContainer>
{
	public SetDresserBlockEntity(BlockEntityType<? extends SetDresserBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
	{
		super(blockEntityType, pos, blockState, SetDresserContainer::new);
	}

	@Override
	protected ItemStackHandler createInventoryHandler()
	{
		return new ItemStackHandler(SetDresserContainer.SIZE);
	}

	@Override
	protected MenuType<SetDresserContainer> getContainerType()
	{
		return FFElements.DRESSER_CONTAINER.asMenuType();
	}
}
