package xyz.apex.minecraft.fantasyfurniture.common.block;

import xyz.apex.minecraft.apexcore.common.component.ComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.SimpleComponentBlock;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMultiBlockTypes;

import java.util.function.UnaryOperator;

public final class DoorBlock extends SimpleComponentBlock
{
    public DoorBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents()
    {
        registerComponent(ComponentTypes.HORIZONTAL_FACING).setGetFacingDirectionFunc(UnaryOperator.identity());
        registerComponent(ComponentTypes.MULTI_BLOCK, AllMultiBlockTypes.MB_1x2x1_FACING_DOOR);
        registerComponent(ComponentTypes.DOOR);
    }
}
