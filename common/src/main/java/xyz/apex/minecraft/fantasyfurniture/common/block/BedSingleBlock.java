package xyz.apex.minecraft.fantasyfurniture.common.block;

import xyz.apex.minecraft.apexcore.common.component.ComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.SimpleComponentBlock;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMultiBlockTypes;

public final class BedSingleBlock extends SimpleComponentBlock
{
    public BedSingleBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents()
    {
        registerComponent(ComponentTypes.HORIZONTAL_FACING);
        registerComponent(ComponentTypes.BED);
        registerComponent(ComponentTypes.MULTI_BLOCK, AllMultiBlockTypes.MB_2x1x1_FACING_BED_SINGLE);
    }
}
