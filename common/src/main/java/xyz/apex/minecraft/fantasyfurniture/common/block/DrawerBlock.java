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
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.DrawerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

import java.util.function.Consumer;

public final class DrawerBlock extends BaseEntityBlockComponentHolder<DrawerBlockEntity>
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public DrawerBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    protected BlockEntityType<DrawerBlockEntity> getBlockEntityType()
    {
        return AllBlockEntityTypes.DRAWER.get();
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

        if(NordicSet.DRAWER.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.DRAWER;
        else if(VenthyrSet.DRAWER.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.DRAWER;
        else if(DunmerSet.DRAWER.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.DRAWER;
        else if(BoneSet.Wither.DRAWER.hasBlockState(blockState) || BoneSet.Skeleton.DRAWER.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.DRAWER;
        else if(NecrolordSet.DRAWER.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.DRAWER;
        else if(RoyalSet.DRAWER.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.DRAWER;
        else return Shapes.block();

        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
        return VoxelShapeHelper.rotateHorizontal(shape, facing);
    }
}
