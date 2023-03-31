package xyz.apex.minecraft.fantasyfurniture.bone.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneVoxelShapes;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

import java.util.function.Consumer;

public final class BoneBedBlock extends BaseBlockComponentHolder
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> {
        VoxelShape shape;

        if(BoneFurnitureSet.Wither.BED_SINGLE.hasBlockState(blockState) || BoneFurnitureSet.Skeleton.BED_SINGLE.hasBlockState(blockState)) shape = BoneVoxelShapes.BED_SINGLE;
        else if(BoneFurnitureSet.Wither.BED_DOUBLE.hasBlockState(blockState) || BoneFurnitureSet.Skeleton.BED_DOUBLE.hasBlockState(blockState)) shape = BoneVoxelShapes.BED_DOUBLE;
        else return Shapes.block();

        return FantasyFurniture.getCorrectedShape(blockState, shape);
    });

    public BoneBedBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}
