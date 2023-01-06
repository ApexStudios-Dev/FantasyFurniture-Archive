package xyz.apex.minecraft.fantasyfurniture.shared.block;

import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.shared.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.apexcore.shared.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.block.entity.ChestBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.shared.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.shared.init.AllMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.shared.menu.ChestMenu;

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
