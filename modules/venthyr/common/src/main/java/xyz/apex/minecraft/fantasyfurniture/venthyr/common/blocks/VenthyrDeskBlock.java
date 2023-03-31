package xyz.apex.minecraft.fantasyfurniture.venthyr.common.blocks;

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
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrVoxelShapes;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.blocks.entity.VenthyrDeskBlockEntity;

import java.util.function.Consumer;

public final class VenthyrDeskBlock extends BaseEntityBlockComponentHolder<VenthyrDeskBlockEntity>
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> {
        VoxelShape shape;

        if(VenthyrFurnitureSet.Blocks.DESK_RIGHT.hasBlockState(blockState)) shape = VenthyrVoxelShapes.DESK_RIGHT;
        else if(VenthyrFurnitureSet.Blocks.DESK_LEFT.hasBlockState(blockState)) shape = VenthyrVoxelShapes.DESK_LEFT;
        else return Shapes.block();

        return FantasyFurniture.getCorrectedShape(blockState, shape);
    });

    public VenthyrDeskBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    protected BlockEntityType<VenthyrDeskBlockEntity> getBlockEntityType()
    {
        return VenthyrFurnitureSet.BlockEntityTypes.DESK.get();
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}
