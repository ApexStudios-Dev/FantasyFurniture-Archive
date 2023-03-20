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

public final class BookshelfBlock extends SimpleComponentBlock
{
    public BookshelfBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents()
    {
        registerComponent(ComponentTypes.HORIZONTAL_FACING);
        registerComponent(ComponentTypes.MULTI_BLOCK, AllMultiBlockTypes.MB_1x2x2_FACING);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        VoxelShape shape;

        if(NordicSet.BOOKSHELF.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.BOOKSHELF;
        else if(VenthyrSet.BOOKSHELF.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.BOOKSHELF;
        else if(DunmerSet.BOOKSHELF.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.BOOKSHELF;
        else if(BoneSet.Wither.BOOKSHELF.hasBlockState(blockState) || BoneSet.Skeleton.BOOKSHELF.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.BOOKSHELF;
        else if(NecrolordSet.BOOKSHELF.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.BOOKSHELF;
        else if(RoyalSet.BOOKSHELF.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.BOOKSHELF;
        else return super.getShape(blockState, level, pos, context);

        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        shape = VoxelShapeHelper.rotateHorizontal(shape, facing);
        var index = getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().getIndex(blockState);

        if(index == 2 || index == 3)
        {
            var offset = facing.getClockWise();
            shape = shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        if(index == 1 || index == 3) shape = shape.move(0D, -1D, 0D);

        return shape;
    }
}
