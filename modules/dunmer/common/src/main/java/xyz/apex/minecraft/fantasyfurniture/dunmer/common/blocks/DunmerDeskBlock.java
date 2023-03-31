package xyz.apex.minecraft.fantasyfurniture.dunmer.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseEntityBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.DunmerFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.DunmerVoxelShapes;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.blocks.entity.DunmerDeskBlockEntity;

import java.util.function.Consumer;

public final class DunmerDeskBlock extends BaseEntityBlockComponentHolder<DunmerDeskBlockEntity>
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> {
        VoxelShape shape;

        if(DunmerFurnitureSet.Blocks.DESK_RIGHT.hasBlockState(blockState)) shape = DunmerVoxelShapes.DESK_RIGHT;
        else if(DunmerFurnitureSet.Blocks.DESK_LEFT.hasBlockState(blockState)) shape = DunmerVoxelShapes.DESK_LEFT;
        else return Shapes.block();

        return FantasyFurniture.getCorrectedShape(blockState, shape);
    });

    public DunmerDeskBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    protected BlockEntityType<DunmerDeskBlockEntity> getBlockEntityType()
    {
        return DunmerFurnitureSet.BlockEntityTypes.DESK.get();
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}
