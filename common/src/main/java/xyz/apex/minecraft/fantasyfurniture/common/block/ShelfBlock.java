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
import xyz.apex.minecraft.fantasyfurniture.common.block.components.ShelfBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ModBlockStateProperties;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

import java.util.function.Consumer;

public final class ShelfBlock extends BaseBlockComponentHolder
{
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public ShelfBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    public void registerComponents(BlockComponentHolder.Registrar registrar)
    {
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
        registrar.register(ShelfBlockComponent.COMPONENT_TYPE);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return shapeCacher.getSafe(blockState);
    }

    private VoxelShape getShape(BlockState blockState)
    {
        VoxelShape shape;

        if(NordicSet.SHELF.hasBlockState(blockState))
        {
            shape = switch(blockState.getValue(ModBlockStateProperties.SHELF_TYPE)) {
                case LEFT -> AllVoxelShapes.Nordic.SHELF_LEFT;
                case RIGHT -> AllVoxelShapes.Nordic.SHELF_RIGHT;
                case SINGLE -> AllVoxelShapes.Nordic.SHELF_SINGLE;
                case CENTER -> AllVoxelShapes.Nordic.SHELF_CENTER;
            };
        }
        else if(VenthyrSet.SHELF.hasBlockState(blockState))
        {
            shape = switch(blockState.getValue(ModBlockStateProperties.SHELF_TYPE)) {
                case LEFT -> AllVoxelShapes.Venthyr.SHELF_LEFT;
                case RIGHT -> AllVoxelShapes.Venthyr.SHELF_RIGHT;
                case SINGLE -> AllVoxelShapes.Venthyr.SHELF_SINGLE;
                case CENTER -> AllVoxelShapes.Venthyr.SHELF_CENTER;
            };
        }
        else if(DunmerSet.SHELF.hasBlockState(blockState))
        {
            shape = switch(blockState.getValue(ModBlockStateProperties.SHELF_TYPE)) {
                case LEFT -> AllVoxelShapes.Dunmer.SHELF_LEFT;
                case RIGHT -> AllVoxelShapes.Dunmer.SHELF_RIGHT;
                case SINGLE -> AllVoxelShapes.Dunmer.SHELF_SINGLE;
                case CENTER -> AllVoxelShapes.Dunmer.SHELF_CENTER;
            };
        }
        else if(BoneSet.Wither.SHELF.hasBlockState(blockState) || BoneSet.Skeleton.SHELF.hasBlockState(blockState))
        {
            shape = switch(blockState.getValue(ModBlockStateProperties.SHELF_TYPE)) {
                case LEFT -> AllVoxelShapes.Bone.SHELF_LEFT;
                case RIGHT -> AllVoxelShapes.Bone.SHELF_RIGHT;
                case SINGLE -> AllVoxelShapes.Bone.SHELF_SINGLE;
                case CENTER -> AllVoxelShapes.Bone.SHELF_CENTER;
            };
        }
        else if(NecrolordSet.SHELF.hasBlockState(blockState))
        {
            shape = switch(blockState.getValue(ModBlockStateProperties.SHELF_TYPE)) {
                case LEFT -> AllVoxelShapes.Necrolord.SHELF_LEFT;
                case RIGHT -> AllVoxelShapes.Necrolord.SHELF_RIGHT;
                case SINGLE -> AllVoxelShapes.Necrolord.SHELF_SINGLE;
                case CENTER -> AllVoxelShapes.Necrolord.SHELF_CENTER;
            };
        }
        else if(RoyalSet.SHELF.hasBlockState(blockState))
        {
            shape = switch(blockState.getValue(ModBlockStateProperties.SHELF_TYPE)) {
                case LEFT -> AllVoxelShapes.Royal.SHELF_LEFT;
                case RIGHT -> AllVoxelShapes.Royal.SHELF_RIGHT;
                case SINGLE -> AllVoxelShapes.Royal.SHELF_SINGLE;
                case CENTER -> AllVoxelShapes.Royal.SHELF_CENTER;
            };
        }
        else return Shapes.block();

        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
        return VoxelShapeHelper.rotateHorizontal(shape, facing);
    }
}
