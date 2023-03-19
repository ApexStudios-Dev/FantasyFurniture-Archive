package xyz.apex.minecraft.fantasyfurniture.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.minecraft.apexcore.common.inventory.InventoryBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.common.menu.DresserMenu;

public final class DresserBlockEntity extends InventoryBlockEntity
{
    public static final int ROWS = 3;
    public static final int COLS = 9;
    public static final int SLOT_COUNT = ROWS * COLS;

    public DresserBlockEntity(BlockEntityType<? extends DresserBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
    {
        super(blockEntityType, pos, blockState, SLOT_COUNT);
    }

    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player player)
    {
        return new DresserMenu(AllMenuTypes.DRESSER.get(), windowId, player, getInventory());
    }
}