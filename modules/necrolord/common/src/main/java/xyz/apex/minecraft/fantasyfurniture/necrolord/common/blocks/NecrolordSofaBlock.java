package xyz.apex.minecraft.fantasyfurniture.necrolord.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ModBlockStateProperties;
import xyz.apex.minecraft.fantasyfurniture.necrolord.common.NecrolordVoxelShapes;

import java.util.function.Consumer;

public final class NecrolordSofaBlock extends BaseBlockComponentHolder
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> FantasyFurniture.getCorrectedShape(blockState, switch(blockState.getValue(ModBlockStateProperties.SOFA_TYPE)) {
        case LEFT -> NecrolordVoxelShapes.SOFA_LEFT;
        case RIGHT -> NecrolordVoxelShapes.SOFA_RIGHT;
        case SINGLE -> NecrolordVoxelShapes.SOFA_SINGLE;
        case CENTER -> NecrolordVoxelShapes.SOFA_CENTER;
        case CORNER -> NecrolordVoxelShapes.SOFA_CORNER;
    }));

    public NecrolordSofaBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}
