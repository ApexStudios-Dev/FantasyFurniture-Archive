package xyz.apex.minecraft.fantasyfurniture.common.block;

import xyz.apex.minecraft.apexcore.common.component.ComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.SimpleComponentBlock;

public final class TableSmallBlock extends SimpleComponentBlock
{
    public TableSmallBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents()
    {
        registerComponent(ComponentTypes.HORIZONTAL_FACING);
    }
}
