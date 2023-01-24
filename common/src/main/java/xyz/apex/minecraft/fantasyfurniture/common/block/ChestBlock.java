package xyz.apex.minecraft.fantasyfurniture.common.block;

import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.apexcore.common.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.ChestBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.common.menu.ChestMenu;

public final class ChestBlock extends HorizontalInventoryBlock.AsMultiBlock<ChestBlockEntity, ChestMenu>
{
    public ChestBlock(MultiBlockType multiBlockType, Properties properties)
    {
        super(multiBlockType, properties);
    }

    @Override
    public BlockEntityEntry<ChestBlockEntity> getInventoryBlockEntityType()
    {
        return AllBlockEntityTypes.CHEST;
    }

    @Override
    public MenuEntry<ChestMenu> getInventoryMenuType()
    {
        return AllMenuTypes.CHEST;
    }
}
