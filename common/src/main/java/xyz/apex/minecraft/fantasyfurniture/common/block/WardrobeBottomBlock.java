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
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.WardrobeBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

public final class WardrobeBottomBlock extends BaseEntityBlockComponentHolder<WardrobeBlockEntity>
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public WardrobeBottomBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected BlockEntityType<WardrobeBlockEntity> getBlockEntityType()
    {
        return AllBlockEntityTypes.WARDROBE.get();
    }

    @Override
    public void registerComponents(BlockComponentHolder.Registrar registrar)
    {
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
        registrar.register(BlockComponentTypes.MULTI_BLOCK).setMultiBlockType(AllMultiBlockTypes.MB_1x2x2_FACING);
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

        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
        shape = VoxelShapeHelper.rotateHorizontal(shape, facing);
        var index = getRequiredComponent(BlockComponentTypes.MULTI_BLOCK).getMultiBlockType().getIndex(blockState);

        if(index == 2 || index == 3)
        {
            var offset = facing.getClockWise();
            shape = shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        if(index == 1 || index == 3) shape = shape.move(0D, -1D, 0D);

        return shape;
    }
}
