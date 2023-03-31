package xyz.apex.minecraft.fantasyfurniture.nordic.common.blocks;

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
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicVoxelShapes;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.blocks.entity.NordicDeskBlockEntity;

import java.util.function.Consumer;

public final class NordicDeskBlock extends BaseEntityBlockComponentHolder<NordicDeskBlockEntity>
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> {
        VoxelShape shape;

        if(NordicFurnitureSet.Blocks.DESK_RIGHT.hasBlockState(blockState)) shape = NordicVoxelShapes.DESK_RIGHT;
        else if(NordicFurnitureSet.Blocks.DESK_LEFT.hasBlockState(blockState)) shape = NordicVoxelShapes.DESK_LEFT;
        else return Shapes.block();

        return FantasyFurniture.getCorrectedShape(blockState, shape);
    });

    public NordicDeskBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    protected BlockEntityType<NordicDeskBlockEntity> getBlockEntityType()
    {
        return NordicFurnitureSet.BlockEntityTypes.DESK.get();
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}
