package xyz.apex.minecraft.fantasyfurniture.common.block;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentRegistrar;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BaseBlockEntityComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.BlockComponentTypes;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.FurnaceBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.component.FurnaceBlockEntityComponent;

public class FurnaceBlock extends BaseBlockComponentHolder
{
    public FurnaceBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar)
    {
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
        registrar.register(BlockComponentTypes.MENU_PROVIDER, component -> component.withExtraData((level, pos, blockState, buffer) -> buffer.writeBlockPos(pos)));
        registrar.register(BlockComponentTypes.WATERLOGGED);
        registrar.register(FurnaceBlockComponent.COMPONENT_TYPE);
    }

    @Nullable
    @Override
    protected <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState)
    {
        return level.isClientSide ? null : (level1, blockPos, blockState1, blockEntity) -> {
            if(blockEntity instanceof BaseBlockEntityComponentHolder componentHolder)
                FurnaceBlockEntityComponent.serverTick(level1, blockPos, blockState1, componentHolder);
        };
    }
}
