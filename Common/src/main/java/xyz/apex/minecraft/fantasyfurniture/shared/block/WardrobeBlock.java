package xyz.apex.minecraft.fantasyfurniture.shared.block;

import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.shared.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.apexcore.shared.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.block.entity.WardrobeBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.shared.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.shared.init.AllMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.shared.menu.WardrobeMenu;

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
