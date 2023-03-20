package xyz.apex.minecraft.fantasyfurniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.ComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.SimpleComponentBlock;
import xyz.apex.minecraft.apexcore.common.component.types.HorizontalFacingComponent;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeHelper;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

public final class PaintingWideBlock extends SimpleComponentBlock
{
    public PaintingWideBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents()
    {
        registerComponent(ComponentTypes.HORIZONTAL_FACING);
        registerComponent(ComponentTypes.MULTI_BLOCK, AllMultiBlockTypes.MB_1x1x2_FACING);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        VoxelShape shape;

        if(NordicSet.PAINTING_WIDE.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.PAINTING_WIDE;
        else if(VenthyrSet.PAINTING_WIDE.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.PAINTING_WIDE;
        else if(DunmerSet.PAINTING_WIDE.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.PAINTING_WIDE;
        else if(BoneSet.Wither.PAINTING_WIDE.hasBlockState(blockState) || BoneSet.Skeleton.PAINTING_WIDE.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.PAINTING_WIDE;
        else if(NecrolordSet.PAINTING_WIDE.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.PAINTING_WIDE;
        else if(RoyalSet.PAINTING_WIDE.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.PAINTING_WIDE;
        else return super.getShape(blockState, level, pos, context);

        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        shape = VoxelShapeHelper.rotateHorizontal(shape, facing);

        if(!getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState))
        {
            var offset = facing.getClockWise();
            return shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        return shape;
    }
}
