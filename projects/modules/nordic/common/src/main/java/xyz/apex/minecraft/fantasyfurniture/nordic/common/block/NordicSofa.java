package xyz.apex.minecraft.fantasyfurniture.nordic.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.block.SofaBlock;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.ConnectionBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.HitBoxes;

public final class NordicSofa extends SofaBlock
{
    public NordicSofa(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);

        return switch(blockState.getValue(ConnectionBlockComponent.PROPERTY)) {
            default -> switch(facing) {
                default -> HitBoxes.SOFA_SINGLE_NORTH;
                case EAST -> HitBoxes.SOFA_SINGLE_EAST;
                case SOUTH -> HitBoxes.SOFA_SINGLE_SOUTH;
                case WEST -> HitBoxes.SOFA_SINGLE_WEST;
            };

            case LEFT -> switch(facing) {
                default -> HitBoxes.SOFA_LEFT_NORTH;
                case EAST -> HitBoxes.SOFA_LEFT_EAST;
                case SOUTH -> HitBoxes.SOFA_LEFT_SOUTH;
                case WEST -> HitBoxes.SOFA_LEFT_WEST;
            };
            case RIGHT -> switch(facing) {
                default -> HitBoxes.SOFA_RIGHT_NORTH;
                case EAST -> HitBoxes.SOFA_RIGHT_EAST;
                case SOUTH -> HitBoxes.SOFA_RIGHT_SOUTH;
                case WEST -> HitBoxes.SOFA_RIGHT_WEST;
            };

            case CENTER -> switch(facing) {
                default -> HitBoxes.SOFA_CENTER_NORTH;
                case EAST -> HitBoxes.SOFA_CENTER_EAST;
                case SOUTH -> HitBoxes.SOFA_CENTER_SOUTH;
                case WEST -> HitBoxes.SOFA_CENTER_WEST;
            };

            case CORNER -> switch(facing) {
                default -> HitBoxes.SOFA_CORNER_NORTH;
                case EAST -> HitBoxes.SOFA_CORNER_EAST;
                case SOUTH -> HitBoxes.SOFA_CORNER_SOUTH;
                case WEST -> HitBoxes.SOFA_CORNER_WEST;
            };
        };
    }
}
