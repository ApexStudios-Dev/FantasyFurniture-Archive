package xyz.apex.minecraft.fantasyfurniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeHelper;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

public final class TableLargeBlock extends BaseBlockComponentHolder
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public TableLargeBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents(BlockComponentHolder.Registrar registrar)
    {
        registrar.register(BlockComponentTypes.MULTI_BLOCK).setMultiBlockType(AllMultiBlockTypes.MB_2x1x2_FACING);
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return shapeCacher.getSafe(blockState);
    }

    private VoxelShape getShape(BlockState blockState)
    {
        VoxelShape shape;

        if(NordicSet.TABLE_LARGE.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.TABLE_LARGE;
        else if(VenthyrSet.TABLE_LARGE.hasBlockState(blockState) || VenthyrSet.TABLE_LARGE_FANCY.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.TABLE_LARGE;
        else if(DunmerSet.TABLE_LARGE.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.TABLE_LARGE;
        else if(BoneSet.Wither.TABLE_LARGE.hasBlockState(blockState) || BoneSet.Skeleton.TABLE_LARGE.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.TABLE_LARGE;
        else if(NecrolordSet.TABLE_LARGE.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.TABLE_LARGE;
        else if(RoyalSet.TABLE_LARGE.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.TABLE_LARGE;
        else return Shapes.block();

        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
        shape = VoxelShapeHelper.rotateHorizontal(shape, facing);
        var index = getRequiredComponent(BlockComponentTypes.MULTI_BLOCK).getMultiBlockType().getIndex(blockState);

        if(index == 1 || index == 3)
        {
            var offset = facing.getClockWise();
            shape = shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        if(index == 2 || index == 3) shape = shape.move(facing.getStepX(), 0D, facing.getStepZ());

        return shape;
    }
}
