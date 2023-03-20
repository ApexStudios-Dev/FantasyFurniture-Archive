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
import xyz.apex.minecraft.fantasyfurniture.common.block.components.SeatComponent;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

public final class BenchBlock extends SimpleComponentBlock
{
    public BenchBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents()
    {
        registerComponent(ComponentTypes.HORIZONTAL_FACING);
        registerComponent(ComponentTypes.MULTI_BLOCK, AllMultiBlockTypes.MB_1x1x2_FACING);
        registerComponent(SeatComponent.COMPONENT_TYPE);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        VoxelShape shape;

        if(NordicSet.BENCH.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.BENCH;
        else if(VenthyrSet.BENCH.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.BENCH;
        else if(DunmerSet.BENCH.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.BENCH;
        else if(BoneSet.Wither.BENCH.hasBlockState(blockState) || BoneSet.Skeleton.BENCH.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.BENCH;
        else if(NecrolordSet.BENCH.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.BENCH;
        else if(RoyalSet.BENCH.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.BENCH;
        else return super.getShape(blockState, level, pos, context);

        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        shape = VoxelShapeHelper.rotateHorizontal(shape, facing);

        if(!getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState))
        {
            var other = facing.getClockWise();
            shape = shape.move(other.getStepX(), 0D, other.getStepZ());
        }

        return shape;
    }
}
