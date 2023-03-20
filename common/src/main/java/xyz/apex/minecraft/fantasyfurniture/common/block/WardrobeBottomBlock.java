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

public final class WardrobeBottomBlock extends SimpleComponentBlock
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public WardrobeBottomBlock(Properties properties)
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
        return shapeCacher.getSafe(blockState);
    }

    private VoxelShape getShape(BlockState blockState)
    {
        VoxelShape shape;

        if(NordicSet.WARDROBE_BOTTOM.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.WARDROBE_BOTTOM;
        else if(VenthyrSet.WARDROBE_BOTTOM.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.WARDROBE_BOTTOM;
        else if(DunmerSet.WARDROBE_BOTTOM.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.WARDROBE_BOTTOM;
        else if(BoneSet.Wither.WARDROBE_BOTTOM.hasBlockState(blockState) || BoneSet.Skeleton.WARDROBE_BOTTOM.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.WARDROBE_BOTTOM;
        else if(NecrolordSet.WARDROBE_BOTTOM.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.WARDROBE_BOTTOM;
        else if(RoyalSet.WARDROBE_BOTTOM.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.WARDROBE_BOTTOM;
        else return Shapes.block();

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
