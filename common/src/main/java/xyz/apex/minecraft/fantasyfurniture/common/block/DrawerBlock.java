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

public final class DrawerBlock extends SimpleComponentBlock
{
    public DrawerBlock(Properties properties)
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
        VoxelShape shape;

        if(NordicSet.DRAWER.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.DRAWER;
        else if(VenthyrSet.DRAWER.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.DRAWER;
        else if(DunmerSet.DRAWER.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.DRAWER;
        else if(BoneSet.Wither.DRAWER.hasBlockState(blockState) || BoneSet.Skeleton.DRAWER.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.DRAWER;
        else if(NecrolordSet.DRAWER.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.DRAWER;
        else if(RoyalSet.DRAWER.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.DRAWER;
        else return super.getShape(blockState, level, pos, context);

        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        return VoxelShapeHelper.rotateHorizontal(shape, facing);
    }
}
