package xyz.apex.minecraft.fantasyfurniture.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.minecraft.apexcore.common.inventory.InventoryBlockEntity;

public final class CounterBlockEntity extends InventoryBlockEntity
{
    public static final int ROWS = 3;
    public static final int COLS = 9;
    public static final int SLOT_COUNT = ROWS * COLS;

    public CounterBlockEntity(BlockEntityType<? extends CounterBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
    {
        super(blockEntityType, pos, blockState, SLOT_COUNT);
    }
}
