package xyz.apex.minecraft.fantasyfurniture.common.block;

import xyz.apex.minecraft.apexcore.common.component.ComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.SimpleComponentBlock;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.CounterComponent;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllBlockEntityTypes;

public final class CounterBlock extends SimpleComponentBlock
{
    public CounterBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents()
    {
        registerComponent(ComponentTypes.INVENTORY);
        registerComponent(ComponentTypes.BLOCK_ENTITY, AllBlockEntityTypes.COUNTER);
        registerComponent(ComponentTypes.HORIZONTAL_FACING);
        registerComponent(CounterComponent.COMPONENT_TYPE);
    }
}
