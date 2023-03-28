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

import java.util.function.Consumer;

public final class ChairBlock extends BaseBlockComponentHolder
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public ChairBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    public void registerComponents(BlockComponentHolder.Registrar registrar)
    {
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
        registrar.register(BlockComponentTypes.MULTI_BLOCK).setMultiBlockType(AllMultiBlockTypes.MB_1x2x1_FACING);
        registrar.register(SeatBlockComponent.COMPONENT_TYPE).setSitAtOriginOnly(true);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return shapeCacher.getSafe(blockState);
    }

    private VoxelShape getShape(BlockState blockState)
    {
        VoxelShape shape;

        if(NordicSet.CHAIR.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.CHAIR;
        else if(VenthyrSet.CHAIR.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.CHAIR;
        else if(DunmerSet.CHAIR.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.CHAIR;
        else if(BoneSet.Wither.CHAIR.hasBlockState(blockState) || BoneSet.Skeleton.CHAIR.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.CHAIR;
        else if(NecrolordSet.CHAIR.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.CHAIR;
        else if(RoyalSet.CHAIR.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.CHAIR;
        else return Shapes.block();

        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
        shape = VoxelShapeHelper.rotateHorizontal(shape, facing);
        if(!getRequiredComponent(BlockComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState)) shape = shape.move(0D, -1D, 0D);
        return shape;
    }
}
