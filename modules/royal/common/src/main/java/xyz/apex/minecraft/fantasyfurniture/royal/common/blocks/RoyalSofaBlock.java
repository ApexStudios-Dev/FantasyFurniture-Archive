package xyz.apex.minecraft.fantasyfurniture.royal.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ModBlockStateProperties;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalVoxelShapes;

import java.util.function.Consumer;

public final class RoyalSofaBlock extends RoyalDyeableBlock
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> FantasyFurniture.getCorrectedShape(blockState, switch(blockState.getValue(ModBlockStateProperties.SOFA_TYPE)) {
        case LEFT -> RoyalVoxelShapes.SOFA_LEFT;
        case RIGHT -> RoyalVoxelShapes.SOFA_RIGHT;
        case SINGLE -> RoyalVoxelShapes.SOFA_SINGLE;
        case CENTER -> RoyalVoxelShapes.SOFA_CENTER;
        case CORNER -> RoyalVoxelShapes.SOFA_CORNER;
    }));

    public RoyalSofaBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}
