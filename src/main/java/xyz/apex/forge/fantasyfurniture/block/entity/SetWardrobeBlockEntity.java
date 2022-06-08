package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.fantasyfurniture.container.SetWardrobeContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public final class SetWardrobeBlockEntity extends InventoryBlockEntity<SetWardrobeContainer>
{
	public SetWardrobeBlockEntity(BlockEntityType<? extends SetWardrobeBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
	{
		super(blockEntityType, pos, blockState, SetWardrobeContainer::new);
	}

	@Override
	protected ItemStackHandler createInventoryHandler()
	{
		return new ItemStackHandler(SetWardrobeContainer.SIZE);
	}

	@Override
	protected MenuType<SetWardrobeContainer> getContainerType()
	{
		return FFElements.WARDROBE_CONTAINER.asMenuType();
	}
}
