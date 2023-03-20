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

public final class BedDoubleBlock extends SimpleComponentBlock
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public BedDoubleBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents()
    {
        registerComponent(ComponentTypes.HORIZONTAL_FACING);
        registerComponent(ComponentTypes.BED);
        registerComponent(ComponentTypes.MULTI_BLOCK, AllMultiBlockTypes.MB_2x1x2_FACING_BED_DOUBLE);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return shapeCacher.getSafe(blockState);
    }

    private VoxelShape getShape(BlockState blockState)
    {
        VoxelShape shape;

        if(NordicSet.BED_DOUBLE.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.BED_DOUBLE;
        else if(VenthyrSet.BED_DOUBLE.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.BED_DOUBLE;
        else if(DunmerSet.BED_DOUBLE.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.BED_DOUBLE;
        else if(BoneSet.Wither.BED_DOUBLE.hasBlockState(blockState) || BoneSet.Skeleton.BED_DOUBLE.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.BED_DOUBLE;
        else if(NecrolordSet.BED_DOUBLE.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.BED_DOUBLE;
        else if(RoyalSet.BED_DOUBLE.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.BED_DOUBLE;
        else return Shapes.block();

        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        shape = VoxelShapeHelper.rotateHorizontal(shape, facing);

        var index = getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().getIndex(blockState);

        if(index == 2 || index == 3) shape = shape.move(facing.getStepX(), 0D, facing.getStepZ());

        if(index == 1 || index == 3)
        {
            var offset = facing.getClockWise();
            shape = shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        return shape;
    }
}
