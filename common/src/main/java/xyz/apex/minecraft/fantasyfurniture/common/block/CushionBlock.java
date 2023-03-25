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
import xyz.apex.minecraft.fantasyfurniture.common.block.components.SeatBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

public final class CushionBlock extends BaseBlockComponentHolder
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public CushionBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents(BlockComponentHolder.Registrar registrar)
    {
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
        registrar.register(SeatBlockComponent.COMPONENT_TYPE);
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

        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
        return VoxelShapeHelper.rotateHorizontal(shape, facing);
    }
}
