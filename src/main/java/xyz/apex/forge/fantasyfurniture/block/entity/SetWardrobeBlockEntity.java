package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.forge.apexcore.revamp.block.entity.InventoryBlockEntity;
import xyz.apex.forge.fantasyfurniture.container.SetWardrobeContainer;

public final class SetWardrobeBlockEntity extends InventoryBlockEntity
{
	public SetWardrobeBlockEntity(BlockEntityType<? extends SetWardrobeBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
	{
		super(blockEntityType, pos, blockState, SetWardrobeContainer.SIZE);
	}
}
