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
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.DresserBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

public final class DresserBlock extends BaseEntityBlockComponentHolder<DresserBlockEntity>
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public DresserBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected BlockEntityType<DresserBlockEntity> getBlockEntityType()
    {
        return AllBlockEntityTypes.DRESSER.get();
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

        if(NordicSet.DRESSER.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.DRESSER;
        else if(VenthyrSet.DRESSER.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.DRESSER;
        else if(DunmerSet.DRESSER.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.DRESSER;
        else if(BoneSet.Wither.DRESSER.hasBlockState(blockState) || BoneSet.Skeleton.DRESSER.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.DRESSER;
        else if(NecrolordSet.DRESSER.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.DRESSER;
        else if(RoyalSet.DRESSER.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.DRESSER;
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
