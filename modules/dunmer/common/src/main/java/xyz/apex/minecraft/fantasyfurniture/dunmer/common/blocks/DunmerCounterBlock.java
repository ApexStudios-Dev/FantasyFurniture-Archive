package xyz.apex.minecraft.fantasyfurniture.dunmer.common.blocks;

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
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.DunmerFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.DunmerVoxelShapes;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.blocks.entity.DunmerCounterBlockEntity;

import java.util.function.Consumer;

public final class DunmerCounterBlock extends BaseEntityBlockComponentHolder<DunmerCounterBlockEntity>
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> FantasyFurniture.getCorrectedShape(blockState, switch(blockState.getValue(ModBlockStateProperties.COUNTER_TYPE)) {
        case SINGLE -> DunmerVoxelShapes.COUNTER_SINGLE;
        case CORNER -> DunmerVoxelShapes.COUNTER_CORNER;
    }));

    public DunmerCounterBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    protected BlockEntityType<DunmerCounterBlockEntity> getBlockEntityType()
    {
        return DunmerFurnitureSet.BlockEntityTypes.COUNTER.get();
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}
