package xyz.apex.minecraft.fantasyfurniture.shared.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.minecraft.apexcore.shared.inventory.InventoryBlockEntity;

public final class BookshelfBlockEntity extends InventoryBlockEntity
{
    public static final int ROWS = 6;
    public static final int COLS = 9;
    public static final int SLOT_COUNT = ROWS * COLS;

    public BookshelfBlockEntity(BlockEntityType<? extends BookshelfBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
    {
        super(blockEntityType, pos, blockState, SLOT_COUNT);
    }
}
