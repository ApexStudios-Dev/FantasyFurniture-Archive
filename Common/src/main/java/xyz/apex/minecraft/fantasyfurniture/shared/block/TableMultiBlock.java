package xyz.apex.minecraft.fantasyfurniture.shared.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlock;
import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.shared.multiblock.SimpleMultiBlock;
import xyz.apex.minecraft.apexcore.shared.util.VoxelShapeHelper;
import xyz.apex.minecraft.fantasyfurniture.shared.init.AllVoxelShapes;
import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;

public class TableMultiBlock extends SimpleMultiBlock.WithHorizontalFacing implements MultiBlock
{
    public static final DirectionProperty FACING = SimpleMultiBlock.WithHorizontalFacing.FACING;

    public TableMultiBlock(MultiBlockType multiBlockType, Properties properties)
    {
        super(multiBlockType, properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
    {
        var facing = blockState.getValue(FACING);
        var isWide = NordicSet.TABLE_WIDE.hasBlockState(blockState);
        var shape = isWide ? AllVoxelShapes.Nordic.TABLE_WIDE : AllVoxelShapes.Nordic.TABLE_LARGE;
        shape = VoxelShapeHelper.rotateHorizontal(shape, facing);
        var index = multiBlockType.getIndex(blockState);

        if(isWide && !multiBlockType.isOrigin(blockState) || !isWide && (index == 1 || index == 3))
        {
            var offset = facing.getClockWise();
            shape = shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        if(!isWide && (index == 2 || index == 3)) shape = shape.move(facing.getStepX(), 0D, facing.getStepZ());

        return shape;
    }
}
