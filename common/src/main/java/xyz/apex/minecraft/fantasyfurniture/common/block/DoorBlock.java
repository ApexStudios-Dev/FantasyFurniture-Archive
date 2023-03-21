package xyz.apex.minecraft.fantasyfurniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.block.types.DoorBlockComponent;
import xyz.apex.minecraft.apexcore.common.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeHelper;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

import java.util.function.UnaryOperator;

public final class DoorBlock extends BaseBlockComponentHolder
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public DoorBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents(BlockComponentHolder.Registrar registrar)
    {
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING).setGetFacingDirectionFunc(UnaryOperator.identity());
        registrar.register(BlockComponentTypes.MULTI_BLOCK).setMultiBlockType(AllMultiBlockTypes.MB_1x2x1_FACING_DOOR);
        registrar.register(BlockComponentTypes.DOOR);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return shapeCacher.getSafe(blockState);
    }

    private VoxelShape getShape(BlockState blockState)
    {
        VoxelShape shape;

        if(NordicSet.DOOR_SINGLE.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.DOOR_SINGLE;
        else if(NordicSet.DOOR_DOUBLE.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.DOOR_DOUBLE;
        else if(VenthyrSet.DOOR_SINGLE.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.DOOR_SINGLE;
        else if(VenthyrSet.DOOR_DOUBLE.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.DOOR_DOUBLE;
        else if(DunmerSet.DOOR_SINGLE.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.DOOR_SINGLE;
        else if(DunmerSet.DOOR_DOUBLE.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.DOOR_DOUBLE;
        else if(BoneSet.Wither.DOOR_SINGLE.hasBlockState(blockState) || BoneSet.Skeleton.DOOR_SINGLE.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.DOOR_SINGLE;
        else if(BoneSet.Wither.DOOR_DOUBLE.hasBlockState(blockState) || BoneSet.Skeleton.DOOR_DOUBLE.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.DOOR_DOUBLE;
        else if(NecrolordSet.DOOR_SINGLE.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.DOOR_SINGLE;
        else if(NecrolordSet.DOOR_DOUBLE.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.DOOR_DOUBLE;
        else if(RoyalSet.DOOR_SINGLE.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.DOOR_SINGLE;
        else if(RoyalSet.DOOR_DOUBLE.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.DOOR_DOUBLE;
        else return Shapes.block();

        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING).getOpposite();
        var open = blockState.getValue(DoorBlockComponent.OPEN);
        var hinge = blockState.getValue(DoorBlockComponent.HINGE);

        Direction shapeFacing;

        if(hinge == DoorHingeSide.LEFT) shapeFacing = open ? facing.getCounterClockWise() : facing;
        else shapeFacing = open ? facing.getOpposite().getClockWise() : facing.getOpposite();

        shape = VoxelShapeHelper.rotateHorizontal(shape, shapeFacing);

        var x = 0D;
        var y = 0D;
        var z = 0D;

        if(!getRequiredComponent(BlockComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState)) y -= 1D;

        x -= shapeFacing.getStepX() * .8125D;
        z -= shapeFacing.getStepZ() * .8125D;

        if((hinge == DoorHingeSide.LEFT && !open) || (hinge == DoorHingeSide.RIGHT && open))
        {
            x += shapeFacing.getStepX() * .8125D;
            z += shapeFacing.getStepZ() * .8125D;
        }

        return shape.move(x, y, z);
    }
}
