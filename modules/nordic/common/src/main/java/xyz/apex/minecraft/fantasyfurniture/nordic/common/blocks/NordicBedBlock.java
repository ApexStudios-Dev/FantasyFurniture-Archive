package xyz.apex.minecraft.fantasyfurniture.nordic.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicVoxelShapes;

import java.util.function.Consumer;

public final class NordicBedBlock extends BaseBlockComponentHolder
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> {
        VoxelShape shape;

        if(NordicFurnitureSet.Blocks.BED_SINGLE.hasBlockState(blockState)) shape = NordicVoxelShapes.BED_SINGLE;
        else if(NordicFurnitureSet.Blocks.BED_DOUBLE.hasBlockState(blockState)) shape = NordicVoxelShapes.BED_DOUBLE;
        else return Shapes.block();

        return FantasyFurniture.getCorrectedShape(blockState, shape);
    });

    public NordicBedBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}
