package xyz.apex.minecraft.fantasyfurniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseEntityBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeHelper;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.DeskBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

public final class DeskBlock extends BaseEntityBlockComponentHolder<DeskBlockEntity>
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public DeskBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected BlockEntityType<DeskBlockEntity> getBlockEntityType()
    {
        return AllBlockEntityTypes.DESK.get();
    }

    @Override
    public void registerComponents(BlockComponentHolder.Registrar registrar)
    {
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
        registrar.register(BlockComponentTypes.MULTI_BLOCK).setMultiBlockType(AllMultiBlockTypes.MB_1x1x2_FACING);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return shapeCacher.getSafe(blockState);
    }

    private VoxelShape getShape(BlockState blockState)
    {
        VoxelShape shape;

        if(NordicSet.DESK_LEFT.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.DESK_LEFT;
        else if(NordicSet.DESK_RIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.DESK_RIGHT;
        else if(VenthyrSet.DESK_LEFT.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.DESK_LEFT;
        else if(VenthyrSet.DESK_RIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.DESK_RIGHT;
        else if(DunmerSet.DESK_LEFT.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.DESK_LEFT;
        else if(DunmerSet.DESK_RIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.DESK_RIGHT;
        else if(BoneSet.Wither.DESK_LEFT.hasBlockState(blockState) || BoneSet.Skeleton.DESK_LEFT.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.DESK_LEFT;
        else if(BoneSet.Wither.DESK_RIGHT.hasBlockState(blockState) || BoneSet.Skeleton.DESK_RIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.DESK_RIGHT;
        else if(NecrolordSet.DESK_LEFT.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.DESK_LEFT;
        else if(NecrolordSet.DESK_RIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.DESK_RIGHT;
        else if(RoyalSet.DESK_LEFT.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.DESK_LEFT;
        else if(RoyalSet.DESK_RIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.DESK_RIGHT;
        else return Shapes.block();

        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
        shape = VoxelShapeHelper.rotateHorizontal(shape, facing);

        if(!getRequiredComponent(BlockComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState))
        {
            var offset = facing.getClockWise();
            return shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        return shape;
    }
}
