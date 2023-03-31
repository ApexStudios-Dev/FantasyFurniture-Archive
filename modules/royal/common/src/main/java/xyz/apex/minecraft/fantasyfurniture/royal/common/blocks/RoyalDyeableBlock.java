package xyz.apex.minecraft.fantasyfurniture.royal.common.blocks;

import net.minecraft.world.level.block.entity.BlockEntityType;
import xyz.apex.minecraft.apexcore.common.component.block.BaseEntityBlockComponentHolder;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.DyeableBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.royal.common.blocks.entity.RoyalDyeableBlockEntity;

import java.util.function.Consumer;

public class RoyalDyeableBlock extends BaseEntityBlockComponentHolder<RoyalDyeableBlockEntity>
{
    public RoyalDyeableBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    public void registerComponents(Registrar registrar)
    {
        super.registerComponents(registrar);
        registrar.register(DyeableBlockComponent.COMPONENT_TYPE);
    }

    @Override
    protected BlockEntityType<RoyalDyeableBlockEntity> getBlockEntityType()
    {
        return RoyalFurnitureSet.BlockEntityTypes.DYEABLE.get();
    }
}
