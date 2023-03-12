package xyz.apex.minecraft.fantasyfurniture.common.block;

import xyz.apex.minecraft.apexcore.common.component.ComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.SimpleComponentBlock;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMultiBlockTypes;

public final class WardrobeBottomBlock extends SimpleComponentBlock
{
    public WardrobeBottomBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents()
    {
        registerComponent(ComponentTypes.INVENTORY);
        registerComponent(ComponentTypes.BLOCK_ENTITY, AllBlockEntityTypes.WARDROBE);
        registerComponent(ComponentTypes.HORIZONTAL_FACING);
        registerComponent(ComponentTypes.MULTI_BLOCK, AllMultiBlockTypes.MB_1x2x2_FACING);
    }
}
