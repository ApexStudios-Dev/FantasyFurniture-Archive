package xyz.apex.minecraft.fantasyfurniture.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BaseBlockEntityComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BlockEntityComponentRegistrar;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.types.BlockEntityComponentTypes;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.component.BookshelfInventoryBlockEntityComponent;
import xyz.apex.minecraft.fantasyfurniture.common.menu.LargeContainerMenu;

public class BookshelfBlockEntity extends BaseBlockEntityComponentHolder
{
    public BookshelfBlockEntity(BlockEntityType<? extends BookshelfBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
    {
        super(blockEntityType, pos, blockState);
    }

    @Override
    protected void registerComponents(BlockEntityComponentRegistrar registrar)
    {
        registrar.register(BookshelfInventoryBlockEntityComponent.COMPONENT_TYPE, component -> component.withSlotCount(LargeContainerMenu.SLOT_COUNT));
        registrar.register(BlockEntityComponentTypes.NAMEABLE);
        registrar.register(BlockEntityComponentTypes.LOCK_CODE);
        registrar.register(BlockEntityComponentTypes.LOOT_TABLE);
    }

    @Override
    protected AbstractContainerMenu createMenu(int syncId, Inventory playerInventory)
    {
        return new LargeContainerMenu(FantasyFurniture.LARGE_MENU.value(), syncId, playerInventory, this);
    }
}
