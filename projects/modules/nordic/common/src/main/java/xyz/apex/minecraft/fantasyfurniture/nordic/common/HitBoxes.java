package xyz.apex.minecraft.fantasyfurniture.nordic.common;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.helper.VoxelShapeHelper;

import static net.minecraft.world.level.block.Block.box;

@ApiStatus.NonExtendable
public interface HitBoxes
{
    VoxelShape SOFA_SINGLE = VoxelShapeHelper.combine(
            box(1D, 0D, 1D, 3D, 3D, 3D),
            box(1D, 0D, 13D, 3D, 3D, 15D),
            box(13D, 0D, 13D, 15D, 3D, 15D),
            box(13D, 0D, 1D, 15D, 3D, 3D),
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D),
            box(14D, 10D, 0D, 16D, 12D, 14D),
            box(0D, 10D, 0D, 2D, 12D, 14D),
            box(0D, 6D, 0D, 2D, 10D, 2D),
            box(14D, 6D, 0D, 16D, 10D, 2D)
    );

    VoxelShape SOFA_SINGLE_NORTH = VoxelShapeHelper.rotateHorizontal(SOFA_SINGLE, Direction.NORTH);
    VoxelShape SOFA_SINGLE_EAST = VoxelShapeHelper.rotateHorizontal(SOFA_SINGLE, Direction.EAST);
    VoxelShape SOFA_SINGLE_SOUTH = VoxelShapeHelper.rotateHorizontal(SOFA_SINGLE, Direction.SOUTH);
    VoxelShape SOFA_SINGLE_WEST = VoxelShapeHelper.rotateHorizontal(SOFA_SINGLE, Direction.WEST);

    VoxelShape SOFA_CENTER = VoxelShapeHelper.combine(
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D)
    );

    VoxelShape SOFA_CENTER_NORTH = VoxelShapeHelper.rotateHorizontal(SOFA_CENTER, Direction.NORTH);
    VoxelShape SOFA_CENTER_EAST = VoxelShapeHelper.rotateHorizontal(SOFA_CENTER, Direction.EAST);
    VoxelShape SOFA_CENTER_SOUTH = VoxelShapeHelper.rotateHorizontal(SOFA_CENTER, Direction.SOUTH);
    VoxelShape SOFA_CENTER_WEST = VoxelShapeHelper.rotateHorizontal(SOFA_CENTER, Direction.WEST);

    VoxelShape SOFA_LEFT = VoxelShapeHelper.combine(
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D),
            box(14D, 10D, 0D, 16D, 12D, 13D),
            box(14D, 6D, 0D, 16D, 10D, 2D),
            box(13D, 0D, 1D, 15D, 3D, 3D),
            box(13D, 0D, 13D, 15D, 3D, 15D)
    );

    VoxelShape SOFA_LEFT_NORTH = VoxelShapeHelper.rotateHorizontal(SOFA_LEFT, Direction.NORTH);
    VoxelShape SOFA_LEFT_EAST = VoxelShapeHelper.rotateHorizontal(SOFA_LEFT, Direction.EAST);
    VoxelShape SOFA_LEFT_SOUTH = VoxelShapeHelper.rotateHorizontal(SOFA_LEFT, Direction.SOUTH);
    VoxelShape SOFA_LEFT_WEST = VoxelShapeHelper.rotateHorizontal(SOFA_LEFT, Direction.WEST);

    VoxelShape SOFA_RIGHT = VoxelShapeHelper.combine(
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D),
            box(0D, 10D, 0D, 2D, 12D, 13D),
            box(0D, 6D, 0D, 2D, 10D, 2D),
            box(1D, 0D, 1D, 3D, 3D, 3D),
            box(1D, 0D, 13D, 3D, 3D, 15D)
    );

    VoxelShape SOFA_RIGHT_NORTH = VoxelShapeHelper.rotateHorizontal(SOFA_RIGHT, Direction.NORTH);
    VoxelShape SOFA_RIGHT_EAST = VoxelShapeHelper.rotateHorizontal(SOFA_RIGHT, Direction.EAST);
    VoxelShape SOFA_RIGHT_SOUTH = VoxelShapeHelper.rotateHorizontal(SOFA_RIGHT, Direction.SOUTH);
    VoxelShape SOFA_RIGHT_WEST = VoxelShapeHelper.rotateHorizontal(SOFA_RIGHT, Direction.WEST);

    VoxelShape SOFA_CORNER = VoxelShapeHelper.combine(
            box(1D, 0D, 1D, 3D, 3D, 3D),
            box(1D, 0D, 13D, 3D, 3D, 15D),
            box(13D, 0D, 13D, 15D, 3D, 15D),
            box(13D, 0D, 1D, 15D, 3D, 3D),
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D),
            box(13D, 6D, 0D, 16D, 16D, 13D)
    );

    VoxelShape SOFA_CORNER_NORTH = VoxelShapeHelper.rotateHorizontal(SOFA_CORNER, Direction.NORTH);
    VoxelShape SOFA_CORNER_EAST = VoxelShapeHelper.rotateHorizontal(SOFA_CORNER, Direction.EAST);
    VoxelShape SOFA_CORNER_SOUTH = VoxelShapeHelper.rotateHorizontal(SOFA_CORNER, Direction.SOUTH);
    VoxelShape SOFA_CORNER_WEST = VoxelShapeHelper.rotateHorizontal(SOFA_CORNER, Direction.WEST);

    static void bootstrap()
    {
    }
}
