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
import xyz.apex.minecraft.fantasyfurniture.common.block.components.SeatComponent;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

public final class CushionBlock extends SimpleComponentBlock
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public CushionBlock(Properties properties)
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
        return shapeCacher.getSafe(blockState);
    }

    private VoxelShape getShape(BlockState blockState)
    {
        VoxelShape shape;

        if(NordicSet.CUSHION.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.CUSHION;
        else if(VenthyrSet.CUSHION.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.CUSHION;
        else if(DunmerSet.CUSHION.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.CUSHION;
        else if(BoneSet.Wither.CUSHION.hasBlockState(blockState) || BoneSet.Skeleton.CUSHION.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.CUSHION;
        else if(NecrolordSet.CUSHION.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.CUSHION;
        else if(RoyalSet.CUSHION.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.CUSHION;
        else return Shapes.block();

        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        return VoxelShapeHelper.rotateHorizontal(shape, facing);
    }
}
