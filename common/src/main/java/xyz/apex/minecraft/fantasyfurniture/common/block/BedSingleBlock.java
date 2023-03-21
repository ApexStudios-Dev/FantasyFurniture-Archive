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

public final class BedSingleBlock extends BaseBlockComponentHolder
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public BedSingleBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents(BlockComponentHolder.Registrar registrar)
    {
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
        registrar.register(BlockComponentTypes.BED);
        registrar.register(BlockComponentTypes.MULTI_BLOCK).setMultiBlockType(AllMultiBlockTypes.MB_2x1x1_FACING_BED_SINGLE);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return shapeCacher.getSafe(blockState);
    }

    private VoxelShape getShape(BlockState blockState)
    {
        VoxelShape shape;

        if(NordicSet.BED_SINGLE.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.BED_SINGLE;
        else if(VenthyrSet.BED_SINGLE.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.BED_SINGLE;
        else if(DunmerSet.BED_SINGLE.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.BED_SINGLE;
        else if(BoneSet.Wither.BED_SINGLE.hasBlockState(blockState) || BoneSet.Skeleton.BED_SINGLE.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.BED_SINGLE;
        else if(NecrolordSet.BED_SINGLE.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.BED_SINGLE;
        else if(RoyalSet.BED_SINGLE.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.BED_SINGLE;
        else return Shapes.block();

        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
        shape = VoxelShapeHelper.rotateHorizontal(shape, facing);
        if(!getRequiredComponent(BlockComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState)) shape = shape.move(facing.getStepX(), 0D, facing.getStepZ());
        return shape;
    }
}
