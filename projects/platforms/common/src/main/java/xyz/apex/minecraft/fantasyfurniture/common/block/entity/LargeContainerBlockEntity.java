package xyz.apex.minecraft.fantasyfurniture.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BaseBlockEntityComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BlockEntityComponentRegistrar;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.types.BlockEntityComponentTypes;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.menu.LargeContainerMenu;

public final class LargeContainerBlockEntity extends BaseBlockEntityComponentHolder
{
    public LargeContainerBlockEntity(BlockEntityType<? extends LargeContainerBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
    {
        super(blockEntityType, pos, blockState);
    }

    @Override
    protected void registerComponents(BlockEntityComponentRegistrar registrar)
    {
        registrar.register(BlockEntityComponentTypes.INVENTORY, component -> component.setSlotCount(LargeContainerMenu.SLOT_COUNT));
        registrar.register(BlockEntityComponentTypes.NAMEABLE);
    }

    @Override
    protected AbstractContainerMenu createMenu(int syncId, Inventory playerInventory)
    {
        return new LargeContainerMenu(FantasyFurniture.LARGE_MENU.value(), syncId, playerInventory, getRequiredComponent(BlockEntityComponentTypes.INVENTORY));
    }

    @Override
    public int[] getSlotsForFace(Direction side)
    {
        return LargeContainerMenu.SLOTS;
    }
}
