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

public final class StoolBlock extends SimpleComponentBlock
{
    public StoolBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents()
    {
        registerComponent(ComponentTypes.HORIZONTAL_FACING);
        registerComponent(SeatComponent.COMPONENT_TYPE);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        VoxelShape shape;

        if(NordicSet.STOOL.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.STOOL;
        else if(VenthyrSet.STOOL.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.STOOL;
        else if(DunmerSet.STOOL.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.STOOL;
        else if(BoneSet.Wither.STOOL.hasBlockState(blockState) || BoneSet.Skeleton.STOOL.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.STOOL;
        else if(NecrolordSet.STOOL.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.STOOL;
        else if(RoyalSet.STOOL.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.STOOL;
        else return super.getShape(blockState, level, pos, context);

        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        return VoxelShapeHelper.rotateHorizontal(shape, facing);
    }
}
