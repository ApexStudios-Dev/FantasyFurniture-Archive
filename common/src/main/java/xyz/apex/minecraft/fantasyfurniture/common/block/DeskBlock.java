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

public final class DeskBlock extends SimpleComponentBlock
{
    public DeskBlock(Properties properties)
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

        if(NordicSet.DESK_LEFT.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.DESK_LEFT;
        else if(NordicSet.DESK_RIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.DESK_RIGHT;
        else if(VenthyrSet.DESK_LEFT.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.DESK_LEFT;
        else if(VenthyrSet.DESK_RIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.DESK_RIGHT;
        else if(DunmerSet.DESK_LEFT.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.DESK_LEFT;
        else if(DunmerSet.DESK_RIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.DESK_RIGHT;
        else if(BoneSet.Wither.DESK_LEFT.hasBlockState(blockState) || BoneSet.Skeleton.DESK_LEFT.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.DESK_LEFT;
        else if(BoneSet.Wither.DESK_RIGHT.hasBlockState(blockState) || BoneSet.Skeleton.DESK_RIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.DESK_RIGHT;
        else if(NecrolordSet.DESK_LEFT.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.DESK_LEFT;
        else if(NecrolordSet.DESK_RIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.DESK_RIGHT;
        else if(RoyalSet.DESK_LEFT.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.DESK_LEFT;
        else if(RoyalSet.DESK_RIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.DESK_RIGHT;
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
