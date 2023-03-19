package xyz.apex.minecraft.fantasyfurniture.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.common.menu.OvenMenu;

public class OvenBlockEntity extends AbstractFurnaceBlockEntity
{
    public OvenBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(AllBlockEntityTypes.OVEN.get(), pos, blockState, RecipeType.SMOKING);
    }

    @Override
    protected Component getDefaultName()
    {
        return getBlockState().getBlock().getName();
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory)
    {
        return new OvenMenu(containerId, inventory, this, dataAccess);
    }
}
