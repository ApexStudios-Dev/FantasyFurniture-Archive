package xyz.apex.minecraft.fantasyfurniture.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.apexcore.common.component.entity.BaseBlockEntityComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.entity.BlockEntityComponentTypes;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.DyeableBlockEntityComponent;

import java.util.function.Consumer;

public class DyeableBlockEntity extends BaseBlockEntityComponentHolder
{
    protected DyeableBlockEntity(Consumer<Registrar> registrarConsumer, BlockEntityType<? extends DyeableBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
    {
        super(registrarConsumer, blockEntityType, pos, blockState);
    }

    @Override
    public void registerComponents(Registrar registrar)
    {
        registrar.register(DyeableBlockEntityComponent.COMPONENT_TYPE);
        registrar.register(BlockEntityComponentTypes.NAMEABLE);
    }
}
