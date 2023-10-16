package xyz.apex.minecraft.fantasyfurniture.venthyr.common.block;

import net.minecraft.world.level.block.entity.BlockEntityType;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentRegistrar;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.BlockComponentTypes;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;

public class VenthyrMediumContainerBlock extends BaseBlockComponentHolder
{
    public VenthyrMediumContainerBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected BlockEntityType<?> getBlockEntityType()
    {
        return VenthyrFurnitureSet.MEDIUM_CONTAINER_BLOCK_ENTITY.value();
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar)
    {
        registrar.register(BlockComponentTypes.MENU_PROVIDER);
    }
}
