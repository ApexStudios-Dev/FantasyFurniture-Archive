package xyz.apex.minecraft.fantasyfurniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.ComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.SimpleComponentBlock;
import xyz.apex.minecraft.apexcore.common.component.types.HorizontalFacingComponent;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeHelper;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

public final class PaintingSmallBlock extends SimpleComponentBlock
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public PaintingSmallBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents()
    {
        registerComponent(ComponentTypes.HORIZONTAL_FACING);
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

        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        return VoxelShapeHelper.rotateHorizontal(shape, facing);
    }
}
