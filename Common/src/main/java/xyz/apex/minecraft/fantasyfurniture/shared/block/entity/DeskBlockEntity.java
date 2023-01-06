package xyz.apex.minecraft.fantasyfurniture.shared.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.minecraft.apexcore.shared.inventory.InventoryBlockEntity;

public final class DeskBlockEntity extends InventoryBlockEntity
{
    public static final int ROWS = 3;
    public static final int COLS = 5;
    public static final int SLOT_COUNT = ROWS * COLS;

    public DeskBlockEntity(BlockEntityType<? extends DeskBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
    {
        super(blockEntityType, pos, blockState, SLOT_COUNT);
    }
}
