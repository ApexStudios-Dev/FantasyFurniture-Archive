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

public final class LockboxBlock extends SimpleComponentBlock
{
    public LockboxBlock(Properties properties)
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

        if(NordicSet.LOCKBOX.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.LOCKBOX;
        else if(VenthyrSet.LOCKBOX.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.LOCKBOX;
        else if(DunmerSet.LOCKBOX.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.LOCKBOX;
        else if(BoneSet.Wither.LOCKBOX.hasBlockState(blockState) || BoneSet.Skeleton.LOCKBOX.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.LOCKBOX;
        else if(NecrolordSet.LOCKBOX.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.LOCKBOX;
        else if(RoyalSet.LOCKBOX.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.LOCKBOX;
        else return super.getShape(blockState, level, pos, context);

        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        return VoxelShapeHelper.rotateHorizontal(shape, facing);
    }
}
