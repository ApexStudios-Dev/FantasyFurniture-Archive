package xyz.apex.minecraft.fantasyfurniture.nordic.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseEntityBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ModBlockStateProperties;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicVoxelShapes;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.blocks.entity.NordicCounterBlockEntity;

import java.util.function.Consumer;

public final class NordicCounterBlock extends BaseEntityBlockComponentHolder<NordicCounterBlockEntity>
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> FantasyFurniture.getCorrectedShape(blockState, switch(blockState.getValue(ModBlockStateProperties.COUNTER_TYPE)) {
        case SINGLE -> NordicVoxelShapes.COUNTER_SINGLE;
        case CORNER -> NordicVoxelShapes.COUNTER_CORNER;
    }));

    public NordicCounterBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    protected BlockEntityType<NordicCounterBlockEntity> getBlockEntityType()
    {
        return NordicFurnitureSet.BlockEntityTypes.COUNTER.get();
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}
