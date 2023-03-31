package xyz.apex.minecraft.fantasyfurniture.venthyr.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrVoxelShapes;

import java.util.function.Consumer;

public final class VenthyrBedBlock extends BaseBlockComponentHolder
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> {
        VoxelShape shape;

        if(VenthyrFurnitureSet.Blocks.BED_SINGLE.hasBlockState(blockState)) shape = VenthyrVoxelShapes.BED_SINGLE;
        else if(VenthyrFurnitureSet.Blocks.BED_DOUBLE.hasBlockState(blockState)) shape = VenthyrVoxelShapes.BED_DOUBLE;
        else return Shapes.block();

        return FantasyFurniture.getCorrectedShape(blockState, shape);
    });

    public VenthyrBedBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}
