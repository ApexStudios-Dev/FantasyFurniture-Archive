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
import xyz.apex.minecraft.fantasyfurniture.common.block.components.CounterComponent;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ModBlockStateProperties;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

public final class CounterBlock extends SimpleComponentBlock
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public CounterBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents()
    {
        registerComponent(ComponentTypes.HORIZONTAL_FACING);
        registerComponent(CounterComponent.COMPONENT_TYPE);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return shapeCacher.getSafe(blockState);
    }

    private VoxelShape getShape(BlockState blockState)
    {
        VoxelShape shape;

        if(NordicSet.COUNTER.hasBlockState(blockState))
        {
            shape = switch(blockState.getValue(ModBlockStateProperties.COUNTER_TYPE)) {
                case SINGLE -> AllVoxelShapes.Nordic.COUNTER_SINGLE;
                case CORNER -> AllVoxelShapes.Nordic.COUNTER_CORNER;
            };
        }
        else if(VenthyrSet.COUNTER.hasBlockState(blockState))
        {
            shape = switch(blockState.getValue(ModBlockStateProperties.COUNTER_TYPE)) {
                case SINGLE -> AllVoxelShapes.Venthyr.COUNTER_SINGLE;
                case CORNER -> AllVoxelShapes.Venthyr.COUNTER_CORNER;
            };
        }
        else if(DunmerSet.COUNTER.hasBlockState(blockState))
        {
            shape = switch(blockState.getValue(ModBlockStateProperties.COUNTER_TYPE)) {
                case SINGLE -> AllVoxelShapes.Dunmer.COUNTER_SINGLE;
                case CORNER -> AllVoxelShapes.Dunmer.COUNTER_CORNER;
            };
        }
        else if(BoneSet.Wither.COUNTER.hasBlockState(blockState) || BoneSet.Skeleton.COUNTER.hasBlockState(blockState))
        {
            shape = switch(blockState.getValue(ModBlockStateProperties.COUNTER_TYPE)) {
                case SINGLE -> AllVoxelShapes.Bone.COUNTER_SINGLE;
                case CORNER -> AllVoxelShapes.Bone.COUNTER_CORNER;
            };
        }
        else if(NecrolordSet.COUNTER.hasBlockState(blockState))
        {
            shape = switch(blockState.getValue(ModBlockStateProperties.COUNTER_TYPE)) {
                case SINGLE -> AllVoxelShapes.Necrolord.COUNTER_SINGLE;
                case CORNER -> AllVoxelShapes.Necrolord.COUNTER_CORNER;
            };
        }
        else if(RoyalSet.COUNTER.hasBlockState(blockState))
        {
            shape = switch(blockState.getValue(ModBlockStateProperties.COUNTER_TYPE)) {
                case SINGLE -> AllVoxelShapes.Royal.COUNTER_SINGLE;
                case CORNER -> AllVoxelShapes.Royal.COUNTER_CORNER;
            };
        }
        else return Shapes.block();

        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        return VoxelShapeHelper.rotateHorizontal(shape, facing);
    }
}
