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
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.LockboxBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

public final class LockboxBlock extends BaseEntityBlockComponentHolder<LockboxBlockEntity>
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public LockboxBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected BlockEntityType<LockboxBlockEntity> getBlockEntityType()
    {
        return AllBlockEntityTypes.LOCKBOX.get();
    }

    @Override
    public void registerComponents(BlockComponentHolder.Registrar registrar)
    {
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

        if(NordicSet.LOCKBOX.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.LOCKBOX;
        else if(VenthyrSet.LOCKBOX.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.LOCKBOX;
        else if(DunmerSet.LOCKBOX.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.LOCKBOX;
        else if(BoneSet.Wither.LOCKBOX.hasBlockState(blockState) || BoneSet.Skeleton.LOCKBOX.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.LOCKBOX;
        else if(NecrolordSet.LOCKBOX.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.LOCKBOX;
        else if(RoyalSet.LOCKBOX.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.LOCKBOX;
        else return Shapes.block();

        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
        return VoxelShapeHelper.rotateHorizontal(shape, facing);
    }
}
