package xyz.apex.minecraft.fantasyfurniture.common.block;

import xyz.apex.minecraft.apexcore.common.component.ComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.SimpleComponentBlock;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllBlockEntityTypes;

public final class DrawerBlock extends SimpleComponentBlock
{
    public DrawerBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents()
    {
        registerComponent(ComponentTypes.INVENTORY);
        registerComponent(ComponentTypes.BLOCK_ENTITY, AllBlockEntityTypes.DRAWER);
        registerComponent(ComponentTypes.HORIZONTAL_FACING);
    }
}
