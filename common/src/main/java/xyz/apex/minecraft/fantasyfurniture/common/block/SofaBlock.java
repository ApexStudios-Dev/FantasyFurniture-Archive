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
import xyz.apex.minecraft.fantasyfurniture.common.block.components.SofaBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ModBlockStateProperties;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

public final class SofaBlock extends BaseBlockComponentHolder
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public SofaBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents(BlockComponentHolder.Registrar registrar)
    {
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
        registrar.register(SofaBlockComponent.COMPONENT_TYPE);
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

        if(NordicSet.SOFA.hasBlockState(blockState))
        {
            shape = switch(blockState.getValue(ModBlockStateProperties.SOFA_TYPE)) {
                case LEFT -> AllVoxelShapes.Nordic.SOFA_LEFT;
                case RIGHT -> AllVoxelShapes.Nordic.SOFA_RIGHT;
                case SINGLE -> AllVoxelShapes.Nordic.SOFA_SINGLE;
                case CENTER -> AllVoxelShapes.Nordic.SOFA_CENTER;
                case CORNER -> AllVoxelShapes.Nordic.SOFA_CORNER;
            };
        }
        else if(VenthyrSet.SOFA.hasBlockState(blockState))
        {
            shape = switch(blockState.getValue(ModBlockStateProperties.SOFA_TYPE)) {
                case LEFT -> AllVoxelShapes.Venthyr.SOFA_LEFT;
                case RIGHT -> AllVoxelShapes.Venthyr.SOFA_RIGHT;
                case SINGLE -> AllVoxelShapes.Venthyr.SOFA_SINGLE;
                case CENTER -> AllVoxelShapes.Venthyr.SOFA_CENTER;
                case CORNER -> AllVoxelShapes.Venthyr.SOFA_CORNER;
            };
        }
        else if(DunmerSet.SOFA.hasBlockState(blockState))
        {
            shape = switch(blockState.getValue(ModBlockStateProperties.SOFA_TYPE)) {
                case LEFT -> AllVoxelShapes.Dunmer.SOFA_LEFT;
                case RIGHT -> AllVoxelShapes.Dunmer.SOFA_RIGHT;
                case SINGLE -> AllVoxelShapes.Dunmer.SOFA_SINGLE;
                case CENTER -> AllVoxelShapes.Dunmer.SOFA_CENTER;
                case CORNER -> AllVoxelShapes.Dunmer.SOFA_CORNER;
            };
        }
        else if(BoneSet.Wither.SOFA.hasBlockState(blockState) || BoneSet.Skeleton.SOFA.hasBlockState(blockState))
        {
            shape = switch(blockState.getValue(ModBlockStateProperties.SOFA_TYPE)) {
                case LEFT -> AllVoxelShapes.Bone.SOFA_LEFT;
                case RIGHT -> AllVoxelShapes.Bone.SOFA_RIGHT;
                case SINGLE -> AllVoxelShapes.Bone.SOFA_SINGLE;
                case CENTER -> AllVoxelShapes.Bone.SOFA_CENTER;
                case CORNER -> AllVoxelShapes.Bone.SOFA_CORNER;
            };
        }
        else if(NecrolordSet.SOFA.hasBlockState(blockState))
        {
            shape = switch(blockState.getValue(ModBlockStateProperties.SOFA_TYPE)) {
                case LEFT -> AllVoxelShapes.Necrolord.SOFA_LEFT;
                case RIGHT -> AllVoxelShapes.Necrolord.SOFA_RIGHT;
                case SINGLE -> AllVoxelShapes.Necrolord.SOFA_SINGLE;
                case CENTER -> AllVoxelShapes.Necrolord.SOFA_CENTER;
                case CORNER -> AllVoxelShapes.Necrolord.SOFA_CORNER;
            };
        }
        else if(RoyalSet.SOFA.hasBlockState(blockState))
        {
            shape = switch(blockState.getValue(ModBlockStateProperties.SOFA_TYPE)) {
                case LEFT -> AllVoxelShapes.Royal.SOFA_LEFT;
                case RIGHT -> AllVoxelShapes.Royal.SOFA_RIGHT;
                case SINGLE -> AllVoxelShapes.Royal.SOFA_SINGLE;
                case CENTER -> AllVoxelShapes.Royal.SOFA_CENTER;
                case CORNER -> AllVoxelShapes.Royal.SOFA_CORNER;
            };
        }
        else return Shapes.block();

        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
        return VoxelShapeHelper.rotateHorizontal(shape, facing);
    }
}
