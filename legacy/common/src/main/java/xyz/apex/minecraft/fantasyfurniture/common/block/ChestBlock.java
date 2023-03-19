package xyz.apex.minecraft.fantasyfurniture.common.block;

import xyz.apex.minecraft.apexcore.common.component.ComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.SimpleComponentBlock;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMultiBlockTypes;

public final class ChestBlock extends SimpleComponentBlock
{
    public ChestBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents()
    {
        registerComponent(ComponentTypes.INVENTORY);
        registerComponent(ComponentTypes.BLOCK_ENTITY, AllBlockEntityTypes.CHEST);
        registerComponent(ComponentTypes.HORIZONTAL_FACING);
        registerComponent(ComponentTypes.MULTI_BLOCK, AllMultiBlockTypes.MB_1x1x2_FACING);
    }
}