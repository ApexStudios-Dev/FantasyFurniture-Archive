package xyz.apex.minecraft.fantasyfurniture.dunmer.common;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeHelper;

public interface DunmerVoxelShapes
{
    // region: Wall Light
    VoxelShape WALL_LIGHT = VoxelShapeHelper.combine(
            Block.box(4.5, 1, 7.5, 11.5, 2, 14.5),
            Block.box(4.5, 8, 7.5, 11.5, 9, 14.5),
            Block.box(5, 2, 8, 11, 8, 14),
            Block.box(6, 9, 9, 10, 10, 13),
            Block.box(7.5, 10, 10.5, 8.5, 13, 11.5),
            Block.box(7, 13, 9, 9, 15, 15),
            Block.box(6, 12, 15, 10, 16, 16)
    );
    // endregion

    // region: Floor Light
    VoxelShape FLOOR_LIGHT = VoxelShapeHelper.combine(
            Block.box(6, 0, 6, 10, 2, 10),
            Block.box(7, 2, 7, 9, 22, 9),
            Block.box(6, 22, 6, 10, 27, 10),
            Block.box(7, 27, 7, 9, 28, 9)
    );
    // endregion

    // region: Table Large
    VoxelShape TABLE_LARGE = VoxelShapeHelper.combine(
            Block.box(12, 0, 2, 14, 14, 4),
            Block.box(-14, 0, 2, -12, 14, 4),
            Block.box(-14, 0, 28, -12, 14, 30),
            Block.box(12, 0, 28, 14, 14, 30),
            Block.box(-16, 14, 0, 16, 16, 32)
    );
    // endregion

    // region: Table Small
    VoxelShape TABLE_SMALL = VoxelShapeHelper.combine(
            Block.box(1, 0, 1, 3, 14, 3),
            Block.box(1, 0, 13, 3, 14, 15),
            Block.box(13, 0, 13, 15, 14, 15),
            Block.box(13, 0, 1, 15, 14, 3),
            Block.box(0, 14, 0, 16, 16, 16)
    );
    // endregion

    // region: Table Wide
    VoxelShape TABLE_WIDE = VoxelShapeHelper.combine(
            Block.box(12, 0, 2, 14, 14, 4),
            Block.box(-14, 0, 2, -12, 14, 4),
            Block.box(-14, 0, 12, -12, 14, 14),
            Block.box(12, 0, 12, 14, 14, 14),
            Block.box(-16, 14, 0, 16, 16, 16)
    );
    // endregion

    // region: Bench
    VoxelShape BENCH = VoxelShapeHelper.combine(
            Block.box(12, 0, 2, 14, 5, 4),
            Block.box(-14, 0, 2, -12, 5, 4),
            Block.box(-14, 0, 12, -12, 5, 14),
            Block.box(12, 0, 12, 14, 5, 14),
            Block.box(-15, 5, 1, 15, 7, 15),
            Block.box(12.5, 2.5, 4, 13.5, 3.5, 12),
            Block.box(-13.5, 2.5, 4, -12.5, 3.5, 12)
    );
    // endregion

    // region: Chair
    VoxelShape CHAIR = VoxelShapeHelper.combine(
            Block.box(2, 0, 2, 4, 4, 4),
            Block.box(2, 0, 12, 4, 4, 14),
            Block.box(12, 0, 12, 14, 4, 14),
            Block.box(12, 0, 2, 14, 4, 4),
            Block.box(11.5, 4, 2.5, 13.5, 7, 4.5),
            Block.box(2.5, 4, 2.5, 4.5, 7, 4.5),
            Block.box(2.5, 4, 11.5, 4.5, 7, 13.5),
            Block.box(11.5, 4, 11.5, 13.5, 7, 13.5),
            Block.box(2, 7, 1, 14, 9, 15),
            Block.box(2.5, 9, 11.5, 13.5, 31.5, 13.5)
    );
    // endregion

    // region: Ceiling Light
    VoxelShape CEILING_LIGHT = Block.box(1, 2, 1, 15, 16, 15);
    // endregion

    // region: Cushion
    VoxelShape CUSHION = VoxelShapeHelper.combine(
            Block.box(3, 0, 3, 5, 4, 5),
            Block.box(3, 0, 11, 5, 4, 13),
            Block.box(11, 0, 11, 13, 4, 13),
            Block.box(11, 0, 3, 13, 4, 5),
            Block.box(2, 4, 2, 14, 5, 14),
            Block.box(2.5, 5, 2.5, 13.5, 7, 13.5)
    );
    // endregion

    // region: Stool
    VoxelShape STOOL = VoxelShapeHelper.combine(
            Block.box(12, 0, 2, 14, 5, 4),
            Block.box(2, 0, 2, 4, 5, 4),
            Block.box(2, 0, 12, 4, 5, 14),
            Block.box(12, 0, 12, 14, 5, 14),
            Block.box(1, 5, 1, 15, 7, 15),
            Block.box(12.5, 2.5, 4, 13.5, 3.5, 12),
            Block.box(2.5, 2.5, 4, 3.5, 3.5, 12)
    );
    // endregion

    // region: Chest
    VoxelShape CHEST = VoxelShapeHelper.combine(
            Block.box(12, 0, 2, 14, 14, 4),
            Block.box(-14, 0, 2, -12, 14, 4),
            Block.box(-14, 0, 12, -12, 14, 14),
            Block.box(12, 0, 12, 14, 14, 14),
            Block.box(-15, 4, 1, 15, 6, 15),
            Block.box(-15, 14, 1, 15, 16, 15),
            Block.box(-13, 6, 3, 13, 14, 13),
            Block.box(-2, 11, 2, 2, 14, 3)
    );
    // endregion

    // region: Bookshelf
    VoxelShape BOOKSHELF = VoxelShapeHelper.combine(
            Block.box(-14, 0, 2, -12, 30, 4),
            Block.box(-14, 0, 12, -12, 30, 14),
            Block.box(12, 0, 12, 14, 30, 14),
            Block.box(12, 0, 2, 14, 30, 4),
            Block.box(-12, 9, 4, 12, 32, 12),
            Block.box(-15, 9, 1, 15, 11, 15),
            Block.box(-15, 19, 1, 15, 21, 15),
            Block.box(-15, 30, 1, 15, 32, 15)
    );
    // endregion

    // region: Desk Left
    VoxelShape DESK_LEFT = VoxelShapeHelper.combine(
            Block.box(12, 0, 2, 14, 14, 4),
            Block.box(-14, 0, 2, -12, 14, 4),
            Block.box(-14, 0, 12, -12, 14, 14),
            Block.box(12, 0, 12, 14, 14, 14),
            Block.box(-16, 14, 0, 16, 16, 16),
            Block.box(4, 10, 2, 11, 14, 11)
    );
    // endregion

    // region: Desk Right
    VoxelShape DESK_RIGHT = VoxelShapeHelper.combine(
            Block.box(12, 0, 2, 14, 14, 4),
            Block.box(-14, 0, 2, -12, 14, 4),
            Block.box(-14, 0, 12, -12, 14, 14),
            Block.box(12, 0, 12, 14, 14, 14),
            Block.box(-16, 14, 0, 16, 16, 16),
            Block.box(-11, 10, 2, -4, 14, 11)
    );
    // endregion

    // region: Drawer
    VoxelShape DRAWER = VoxelShapeHelper.combine(
            Block.box(1, 0, 1, 3, 14, 3),
            Block.box(1, 0, 13, 3, 14, 15),
            Block.box(13, 0, 13, 15, 14, 15),
            Block.box(13, 0, 1, 15, 14, 3),
            Block.box(0, 14, 0, 16, 16, 16),
            Block.box(1, 6, 1, 15, 8, 15),
            Block.box(2, 6, 2, 14, 14, 14)
    );
    // endregion

    // region: Dresser
    VoxelShape DRESSER = VoxelShapeHelper.combine(
            Block.box(-15, 0, 1, -13, 14, 3),
            Block.box(-15, 0, 13, -13, 14, 15),
            Block.box(13, 0, 13, 15, 14, 15),
            Block.box(13, 0, 1, 15, 14, 3),
            Block.box(-16, 14, 0, 16, 16, 16),
            Block.box(-15, 6, 1, 15, 8, 15),
            Block.box(-14, 8, 2, 14, 14, 14)
    );
    // endregion

    // region: Lockbox
    VoxelShape LOCKBOX = Block.box(2, 0, 3, 14, 14, 13);
    // endregion

    // region: Wardrobe Top
    VoxelShape WARDROBE_TOP = VoxelShapeHelper.combine(
            Block.box(13, 0, 1, 15, 8, 3),
            Block.box(-15, 0, 1, -13, 8, 3),
            Block.box(-15, 0, 13, -13, 8, 15),
            Block.box(13, 0, 13, 15, 8, 15),
            Block.box(-16, 8, 0, 16, 10, 16),
            Block.box(-13, 0, 2, 13, 8, 14)
    );
    // endregion

    // region: Wardrobe Bottom
    VoxelShape WARDROBE_BOTTOM = VoxelShapeHelper.combine(
            Block.box(-15, 0, 1, -13, 30, 3),
            Block.box(-15, 0, 13, -13, 30, 15),
            Block.box(13, 0, 13, 15, 30, 15),
            Block.box(13, 0, 1, 15, 30, 3),
            Block.box(-16, 30, 0, 16, 32, 16),
            Block.box(-16, 20, 0, 16, 22, 16),
            Block.box(-16, 2, 0, 16, 4, 16),
            Block.box(-13, 4, 2, 13, 30, 14)
    );
    // endregion

    // region: Painting Wide
    VoxelShape PAINTING_WIDE = VoxelShapeHelper.combine(
            Block.box(-16, 0, 14, 16, 2, 16),
            Block.box(-16, 14, 14, 16, 16, 16),
            Block.box(-15, 2, 14, 15, 14, 16)
    );
    // endregion

    // region: Painting Small
    VoxelShape PAINTING_SMALL = VoxelShapeHelper.combine(
            Block.box(0, 1, 14, 16, 3, 16),
            Block.box(0, 13, 14, 16, 15, 16),
            Block.box(1, 3, 14, 15, 13, 16)
    );
    // endregion

    // region: Oven
    VoxelShape OVEN = VoxelShapeHelper.combine(
            Block.box(12.5, 0, 6.5, 15.5, 3, 9.5),
            Block.box(-15.5, 0, 6.5, -12.5, 3, 9.5),
            Block.box(-15, 3, 7, -13, 16, 9),
            Block.box(13, 3, 7, 15, 16, 9),
            Block.box(-16, 12, 7, 16, 14, 9),
            Block.box(-5, 10.5, 5.5, 5, 15.5, 10.5),
            Block.box(-6, 0, 2, 6, 3, 14)
    );
    // endregion

    // region: Door Double
    VoxelShape DOOR_DOUBLE = Block.box(0, 0, 0, 16, 32, 2);
    // endregion

    // region: Door Single
    VoxelShape DOOR_SINGLE = VoxelShapeHelper.combine(
            Block.box(0, 0, 0, 16, 21, 2),
            Block.box(0, 29, 0, 16, 32, 2),
            Block.box(13, 21, 0, 16, 29, 2),
            Block.box(0, 21, 0, 3, 29, 2),
            Block.box(4, 21, 0.5, 5, 30, 1.5),
            Block.box(6, 21, 0.5, 7, 30, 1.5),
            Block.box(9, 21, 0.5, 10, 30, 1.5),
            Block.box(11, 21, 0.5, 12, 30, 1.5),
            Block.box(3, 27, 0.5, 13, 28, 1.5),
            Block.box(3, 22, 0.5, 13, 23, 1.5)
    );
    // endregion

    // region: Bed Single
    VoxelShape BED_SINGLE = VoxelShapeHelper.combine(
            Block.box(0D, 0D, 0D, 2D, 13D, 2D),
            Block.box(0D, 0D, 30D, 2D, 11D, 32D),
            Block.box(14D, 0D, 30D, 16D, 11D, 32D),
            Block.box(14D, 0D, 0D, 16D, 13D, 2D),
            Block.box(2D, 3D, 0D, 14D, 14.25D, 2D),
            Block.box(2D, 3D, 30D, 14D, 12.25D, 32D),
            Block.box(1D, 5D, 2D, 15D, 8D, 30D),
            Block.box(0D, 3D, 2D, 16D, 5D, 30D)
    );
    // endregion

    // region: Bed Double
    VoxelShape BED_DOUBLE = VoxelShapeHelper.combine(
            Block.box(-16D, 0D, 0D, -14D, 13D, 2D),
            Block.box(-16D, 0D, 30D, -14D, 11D, 32D),
            Block.box(14D, 0D, 30D, 16D, 11D, 32D),
            Block.box(14D, 0D, 0D, 16D, 13D, 2D),
            Block.box(-14D, 3D, 0D, 14D, 14.25D, 2D),
            Block.box(-14D, 3D, 30D, 14D, 12.25D, 32D),
            Block.box(-15D, 5D, 2D, 15D, 8D, 30D),
            Block.box(-16D, 3D, 2D, 16D, 5D, 30D)
    );
    // endregion

    // region: Shelf Single
    VoxelShape SHELF_SINGLE = VoxelShapeHelper.combine(
            Block.box(13, 8, 14, 15, 14, 16),
            Block.box(1, 8, 14, 3, 14, 16),
            Block.box(0, 14, 0, 16, 16, 16)
    );
    // endregion

    // region: Shelf Center
    VoxelShape SHELF_CENTER = Block.box(0, 14, 0, 16, 16, 16);
    // endregion

    // region: Shelf Left
    VoxelShape SHELF_LEFT = VoxelShapeHelper.combine(
            Block.box(13, 8, 14, 15, 14, 16),
            Block.box(0, 14, 0, 16, 16, 16)
    );
    // endregion

    // region: Shelf Right
    VoxelShape SHELF_RIGHT = VoxelShapeHelper.combine(
            Block.box(1, 8, 14, 3, 14, 16),
            Block.box(0, 14, 0, 16, 16, 16)
    );
    // endregion

    // region: Sofa Single
    VoxelShape SOFA_SINGLE = VoxelShapeHelper.combine(
            Block.box(0, 4, 1, 16, 6, 15),
            Block.box(2, 0, 12, 4, 4, 14),
            Block.box(2, 0, 2, 4, 4, 4),
            Block.box(12, 0, 2, 14, 4, 4),
            Block.box(12, 0, 12, 14, 4, 14),
            Block.box(1, 6, 12, 15, 16, 14),
            Block.box(13, 10, 2, 15, 12, 12),
            Block.box(13, 6, 3, 15, 10, 5),
            Block.box(1, 6, 3, 3, 10, 5),
            Block.box(1, 10, 2, 3, 12, 12),
            Block.box(3, 6, 2, 13, 7, 13)
    );
    // endregion

    // region: Sofa Center
    VoxelShape SOFA_CENTER = VoxelShapeHelper.combine(
            Block.box(0, 4, 1, 16, 6, 15),
            Block.box(0, 6, 2, 16, 7, 12),
            Block.box(0, 6, 12, 16, 16, 14)
    );
    // endregion

    // region: Sofa Left
    VoxelShape SOFA_LEFT = VoxelShapeHelper.combine(
            Block.box(0, 4, 1, 16, 6, 15),
            Block.box(0, 6, 2, 13, 7, 12),
            Block.box(0, 6, 12, 15, 16, 14),
            Block.box(13, 10, 2, 15, 12, 12),
            Block.box(13, 6, 3, 15, 10, 12),
            Block.box(12, 0, 12, 14, 4, 14),
            Block.box(12, 0, 2, 14, 4, 4)
    );
    // endregion

    // region: Sofa Right
    VoxelShape SOFA_RIGHT = VoxelShapeHelper.combine(
            Block.box(0, 4, 1, 16, 6, 15),
            Block.box(3, 6, 2, 16, 7, 12),
            Block.box(1, 6, 12, 16, 16, 14),
            Block.box(1, 10, 2, 3, 12, 12),
            Block.box(1, 6, 3, 3, 10, 12),
            Block.box(2, 0, 12, 4, 4, 14),
            Block.box(2, 0, 2, 4, 4, 4)
    );
    // endregion

    // region: Sofa Corner
    VoxelShape SOFA_CORNER = VoxelShapeHelper.combine(
            Block.box(2, 0, 12, 4, 4, 14),
            Block.box(2, 0, 2, 4, 4, 4),
            Block.box(12, 0, 2, 14, 4, 4),
            Block.box(12, 0, 12, 14, 4, 14),
            Block.box(12, 6, 12, 14, 16, 14),
            Block.box(12, 6, 0, 14, 16, 12),
            Block.box(0, 6, 12, 12, 16, 14),
            Block.box(1, 4, 0, 15, 6, 15),
            Block.box(0, 4, 1, 1, 6, 15),
            Block.box(0, 6, 2, 13, 7, 13),
            Block.box(2, 6, 0, 13, 7, 2)
    );
    // endregion

    // region: Counter Single
    VoxelShape COUNTER_SINGLE = VoxelShapeHelper.combine(
            Block.box(0, 0, 3, 2, 1, 5),
            Block.box(0, 0, 14, 2, 1, 16),
            Block.box(14, 0, 14, 16, 1, 16),
            Block.box(14, 0, 3, 16, 1, 5),
            Block.box(0, 1, 3, 16, 14, 16),
            Block.box(0, 14, 0, 16, 16, 16)
    );
    // endregion

    // region: Counter Corner
    VoxelShape COUNTER_CORNER = VoxelShapeHelper.combine(
            Block.box(0, 0, 0, 2, 1, 2),
            Block.box(11, 0, 0, 13, 1, 5),
            Block.box(13, 0, 3, 16, 1, 5),
            Block.box(14, 0, 14, 16, 1, 16),
            Block.box(0, 0, 14, 2, 1, 16),
            Block.box(0, 1, 3, 16, 14, 16),
            Block.box(0, 1, 0, 13, 14, 3),
            Block.box(0, 14, 0, 16, 16, 16)
    );
    // endregion

    static void bootstrap() {}
}
