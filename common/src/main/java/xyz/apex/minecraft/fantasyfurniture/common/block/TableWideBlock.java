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

public final class TableWideBlock extends SimpleComponentBlock
{
    public TableWideBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents()
    {
        registerComponent(ComponentTypes.MULTI_BLOCK, AllMultiBlockTypes.MB_1x1x2_FACING);
        registerComponent(ComponentTypes.HORIZONTAL_FACING);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        VoxelShape shape;

        if(NordicSet.TABLE_WIDE.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.TABLE_WIDE;
        else if(VenthyrSet.TABLE_WIDE.hasBlockState(blockState) || VenthyrSet.TABLE_WIDE_FANCY.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.TABLE_WIDE;
        else if(DunmerSet.TABLE_WIDE.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.TABLE_WIDE;
        else if(BoneSet.Wither.TABLE_WIDE.hasBlockState(blockState) || BoneSet.Skeleton.TABLE_WIDE.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.TABLE_WIDE;
        else if(NecrolordSet.TABLE_WIDE.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.TABLE_WIDE;
        else if(RoyalSet.TABLE_WIDE.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.TABLE_WIDE;
        else return super.getShape(blockState, level, pos, context);

        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        shape = VoxelShapeHelper.rotateHorizontal(shape, facing);

        if(!getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState))
        {
            var offset = facing.getClockWise();
            shape = shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        return shape;
    }
}
