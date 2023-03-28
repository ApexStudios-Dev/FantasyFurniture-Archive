package xyz.apex.minecraft.fantasyfurniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeHelper;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

import java.util.function.Consumer;

public final class PaintingSmallBlock extends BaseBlockComponentHolder
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public PaintingSmallBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    public void registerComponents(BlockComponentHolder.Registrar registrar)
    {
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return shapeCacher.getSafe(blockState);
    }

    private VoxelShape getShape(BlockState blockState)
    {
        VoxelShape shape;

        if(NordicSet.PAINTING_SMALL.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.PAINTING_SMALL;
        else if(VenthyrSet.PAINTING_SMALL.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.PAINTING_SMALL;
        else if(DunmerSet.PAINTING_SMALL.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.PAINTING_SMALL;
        else if(BoneSet.Wither.PAINTING_SMALL.hasBlockState(blockState) || BoneSet.Skeleton.PAINTING_SMALL.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.PAINTING_SMALL;
        else if(NecrolordSet.PAINTING_SMALL.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.PAINTING_SMALL;
        else if(RoyalSet.PAINTING_SMALL.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.PAINTING_SMALL;
        else return Shapes.block();

        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
        return VoxelShapeHelper.rotateHorizontal(shape, facing);
    }
}
