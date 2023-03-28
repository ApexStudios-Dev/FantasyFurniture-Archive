package xyz.apex.minecraft.fantasyfurniture.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.apexcore.common.component.entity.BaseBlockEntityComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.entity.BlockEntityComponentTypes;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.DyeableBlockEntityComponent;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllBlockEntityTypes;

import java.util.function.Consumer;

public final class DyeableBlockEntity extends BaseBlockEntityComponentHolder
{
    public DyeableBlockEntity(Consumer<Registrar> registrarConsumer, BlockPos pos, BlockState blockState)
    {
        super(registrarConsumer, AllBlockEntityTypes.DYEABLE.get(), pos, blockState);
    }

    @Override
    public void registerComponents(Registrar registrar)
    {
        registrar.register(DyeableBlockEntityComponent.COMPONENT_TYPE);
        registrar.register(BlockEntityComponentTypes.NAMEABLE);
    }
}
