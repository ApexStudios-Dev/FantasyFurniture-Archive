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

public final class ChairBlock extends SimpleComponentBlock
{
    public ChairBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents()
    {
        registerComponent(ComponentTypes.HORIZONTAL_FACING);
        registerComponent(ComponentTypes.MULTI_BLOCK, AllMultiBlockTypes.MB_1x2x1_FACING);
        registerComponent(SeatComponent.COMPONENT_TYPE).setSitAtOriginOnly(true);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        VoxelShape shape;

        if(NordicSet.CHAIR.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.CHAIR;
        else if(VenthyrSet.CHAIR.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.CHAIR;
        else if(DunmerSet.CHAIR.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.CHAIR;
        else if(BoneSet.Wither.CHAIR.hasBlockState(blockState) || BoneSet.Skeleton.CHAIR.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.CHAIR;
        else if(NecrolordSet.CHAIR.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.CHAIR;
        else if(RoyalSet.CHAIR.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.CHAIR;
        else return super.getShape(blockState, level, pos, context);

        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        shape = VoxelShapeHelper.rotateHorizontal(shape, facing);
        if(!getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState)) shape = shape.move(0D, -1D, 0D);
        return shape;
    }
}
