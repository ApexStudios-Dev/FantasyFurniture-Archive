package xyz.apex.minecraft.fantasyfurniture.royal.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalVoxelShapes;

import java.util.function.Consumer;

public final class RoyalBenchBlock extends RoyalDyeableBlock
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> FantasyFurniture.getCorrectedShape(blockState, RoyalVoxelShapes.BENCH));

    public RoyalBenchBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}