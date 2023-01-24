package xyz.apex.minecraft.fantasyfurniture.common.block;

import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.apexcore.common.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.WardrobeBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.common.menu.WardrobeMenu;

public final class WardrobeBlock extends HorizontalInventoryBlock.AsMultiBlock<WardrobeBlockEntity, WardrobeMenu>
{
    public WardrobeBlock(MultiBlockType multiBlockType, Properties properties)
    {
        super(multiBlockType, properties);
    }

    @Override
    public BlockEntityEntry<WardrobeBlockEntity> getInventoryBlockEntityType()
    {
        return AllBlockEntityTypes.WARDROBE;
    }

    @Override
    public MenuEntry<WardrobeMenu> getInventoryMenuType()
    {
        return AllMenuTypes.WARDROBE;
    }
}
