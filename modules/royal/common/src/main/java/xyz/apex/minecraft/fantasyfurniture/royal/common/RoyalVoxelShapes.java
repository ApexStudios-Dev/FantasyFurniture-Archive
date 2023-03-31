package xyz.apex.minecraft.fantasyfurniture.royal.common;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeHelper;

public interface RoyalVoxelShapes
{
    // region: Wall Light
        VoxelShape WALL_LIGHT = Block.box(4.75, 1, 11.75, 11.25, 13, 16);
        // endregion

    // region: Floor Light
    VoxelShape FLOOR_LIGHT = VoxelShapeHelper.combine(
            Block.box(6, 0, 6, 10, 2, 10),
            Block.box(7, 2, 7, 9, 30, 9),
            Block.box(3, 20, 7, 13, 22, 9),
            Block.box(11, 22, 7, 13, 29, 9),
            Block.box(3, 22, 7, 5, 29, 9),
            Block.box(2.5, 24, 6.5, 5.5, 25, 9.5),
            Block.box(6.5, 25, 6.5, 9.5, 26, 9.5),
            Block.box(10.5, 24, 6.5, 13.5, 25, 9.5)
    );
    // endregion

    // region: Table Large
    VoxelShape TABLE_LARGE = VoxelShapeHelper.combine(
            Block.box(-15, 0, 1, -11, 14, 5),
            Block.box(-15, 0, 27, -11, 14, 31),
            Block.box(11, 0, 27, 15, 14, 31),
            Block.box(11, 0, 1, 15, 14, 5),
            Block.box(-16, 14, 0, 16, 16, 32)
    );
    // endregion

    // region: Table Small
    VoxelShape TABLE_SMALL = VoxelShapeHelper.combine(
            Block.box(1, 0, 1, 5, 14, 5),
            Block.box(1, 0, 11, 5, 14, 15),
            Block.box(11, 0, 11, 15, 14, 15),
            Block.box(11, 0, 1, 15, 14, 5),
            Block.box(0, 14, 0, 16, 16, 16)
    );
    // endregion

    // region: Table Wide
    VoxelShape TABLE_WIDE = VoxelShapeHelper.combine(
            Block.box(-15, 0, 1, -11, 14, 5),
            Block.box(-15, 0, 11, -11, 14, 15),
            Block.box(11, 0, 11, 15, 14, 15),
            Block.box(11, 0, 1, 15, 14, 5),
            Block.box(-16, 14, 0, 16, 16, 16)
    );
    // endregion

    // region: Bench
    VoxelShape BENCH = VoxelShapeHelper.combine(
            Block.box(-15, 0, 1, -11, 4, 5),
            Block.box(-15, 0, 11, -11, 4, 15),
            Block.box(11, 0, 11, 15, 4, 15),
            Block.box(11, 0, 1, 15, 4, 5),
            Block.box(-14.5, 4, 1.5, 14.5, 6, 14.5)
    );
    // endregion

    // region: Chair
    VoxelShape CHAIR = VoxelShapeHelper.combine(
            Block.box(1, 0, 1, 3, 3, 4),
            Block.box(1, 0, 12, 3, 3, 15),
            Block.box(13, 0, 12, 15, 3, 15),
            Block.box(13, 0, 1, 15, 3, 4),
            Block.box(13, 3, 2, 15, 5, 4),
            Block.box(13, 3, 12, 15, 5, 14),
            Block.box(1, 3, 12, 3, 5, 14),
            Block.box(1, 3, 2, 3, 5, 4),
            Block.box(1, 5, 1.5, 15, 7, 14.5),
            Block.box(1, 7, 2, 15, 9, 14),
            Block.box(13, 9, 2, 15, 12, 4),
            Block.box(1, 9, 2, 3, 12, 4),
            Block.box(1, 12, 1, 3, 15, 4),
            Block.box(13, 12, 1, 15, 15, 4),
            Block.box(13, 12, 4, 15, 14, 12),
            Block.box(1, 12, 4, 3, 14, 12),
            Block.box(1, 7, 12, 15, 14, 14),
            Block.box(2, 14, 12, 14, 20, 14),
            Block.box(1, 20, 12, 15, 25, 14),
            Block.box(3, 25, 12, 13, 26, 14),
            Block.box(5, 26, 12, 11, 27, 14)
    );
    // endregion

    // region: Ceiling Light
    VoxelShape CEILING_LIGHT = Block.box(0, 0, 0, 16, 16, 16);
    // endregion

    // region: Cushion
    VoxelShape CUSHION = VoxelShapeHelper.combine(
            Block.box(1, 0, 1, 5, 4, 5),
            Block.box(1, 0, 11, 5, 4, 15),
            Block.box(11, 0, 11, 15, 4, 15),
            Block.box(11, 0, 1, 15, 4, 5),
            Block.box(1.5, 4, 1.5, 14.5, 6, 14.5),
            Block.box(2, 6, 2, 14, 9, 14)
    );
    // endregion

    // region: Stool
    VoxelShape STOOL = VoxelShapeHelper.combine(
            Block.box(1, 0, 1, 5, 4, 5),
            Block.box(1, 0, 11, 5, 4, 15),
            Block.box(11, 0, 11, 15, 4, 15),
            Block.box(11, 0, 1, 15, 4, 5),
            Block.box(1.5, 4, 1.5, 14.5, 6, 14.5)
    );
    // endregion

    // region: Chest
    VoxelShape CHEST = VoxelShapeHelper.combine(
            Block.box(7.5, 0, 0.75, 11.25, 4, 4.5),
            Block.box(-11.25, 0, 0.75, -7.5, 4, 4.5),
            Block.box(-11.25, 0, 11.5, -7.5, 4, 15.25),
            Block.box(7.5, 0, 11.5, 11.25, 4, 15.25),
            Block.box(-12, 4, 1, 12, 6, 15),
            Block.box(-11, 6, 2, 11, 16, 14)
    );
    // endregion

    // region: Bookshelf
    VoxelShape BOOKSHELF = VoxelShapeHelper.combine(
            Block.box(12, 0, 0, 16, 4, 4),
            Block.box(-16, 0, 0, -12, 4, 4),
            Block.box(-16, 0, 12, -12, 4, 16),
            Block.box(12, 0, 12, 16, 4, 16),
            Block.box(-15, 4, 1, 15, 6, 15),
            Block.box(-15, 30, 1, 15, 32, 15),
            Block.box(12, 6, 2, 14, 30, 4),
            Block.box(12, 6, 12, 14, 30, 14),
            Block.box(-14, 6, 12, -12, 30, 14),
            Block.box(-14, 6, 2, -12, 30, 4),
            Block.box(-13, 6, 4, 13, 30, 13),
            Block.box(-13, 17, 2, 13, 19, 4)
    );
    // endregion

    // region: Desk Left
    VoxelShape DESK_LEFT = VoxelShapeHelper.combine(
            Block.box(-16, 14, 0, 16, 16, 16),
            Block.box(13, 11, 1, 15, 14, 4),
            Block.box(13, 3, 2, 15, 11, 4),
            Block.box(13, 0, 1, 15, 3, 4),
            Block.box(-15, 0, 1, -13, 3, 4),
            Block.box(-15, 11, 1, -13, 14, 4),
            Block.box(-15, 3, 2, -13, 11, 4),
            Block.box(-15, 0, 12, -13, 3, 15),
            Block.box(-15, 11, 12, -13, 14, 15),
            Block.box(-15, 3, 12, -13, 11, 14),
            Block.box(13, 0, 12, 15, 3, 15),
            Block.box(13, 11, 12, 15, 14, 15),
            Block.box(13, 3, 12, 15, 11, 14),
            Block.box(4, 10, 2, 12, 14, 14),
            Block.box(6, 12, 1, 10, 13, 2)
    );
    // endregion

    // region: Desk Right
    VoxelShape DESK_RIGHT = VoxelShapeHelper.combine(
            Block.box(-16, 14, 0, 16, 16, 16),
            Block.box(13, 11, 1, 15, 14, 4),
            Block.box(13, 3, 2, 15, 11, 4),
            Block.box(13, 0, 1, 15, 3, 4),
            Block.box(-15, 0, 1, -13, 3, 4),
            Block.box(-15, 11, 1, -13, 14, 4),
            Block.box(-15, 3, 2, -13, 11, 4),
            Block.box(-15, 0, 12, -13, 3, 15),
            Block.box(-15, 11, 12, -13, 14, 15),
            Block.box(-15, 3, 12, -13, 11, 14),
            Block.box(13, 0, 12, 15, 3, 15),
            Block.box(13, 11, 12, 15, 14, 15),
            Block.box(13, 3, 12, 15, 11, 14),
            Block.box(-12, 10, 2, -4, 14, 14),
            Block.box(-10, 12, 1, -6, 13, 2)
    );
    // endregion

    // region: Drawer
    VoxelShape DRAWER = VoxelShapeHelper.combine(
            Block.box(1, 0, 1, 3, 3, 4),
            Block.box(1, 0, 12, 3, 3, 15),
            Block.box(13, 0, 12, 15, 3, 15),
            Block.box(13, 0, 1, 15, 3, 4),
            Block.box(13, 3, 2, 15, 14, 4),
            Block.box(1, 3, 2, 3, 14, 4),
            Block.box(1, 3, 12, 3, 14, 14),
            Block.box(13, 3, 12, 15, 14, 14),
            Block.box(0.5, 5, 1.5, 15.5, 7, 14.5),
            Block.box(0, 14, 0, 16, 16, 16),
            Block.box(2, 7, 3, 14, 14, 13)
    );
    // endregion

    // region: Dresser
    VoxelShape DRESSER = VoxelShapeHelper.combine(
            Block.box(-15, 0, 1, -13, 3, 4),
            Block.box(-15, 0, 12, -13, 3, 15),
            Block.box(13, 0, 12, 15, 3, 15),
            Block.box(13, 0, 1, 15, 3, 4),
            Block.box(13, 3, 2, 15, 14, 4),
            Block.box(-15, 3, 2, -13, 14, 4),
            Block.box(-15, 3, 12, -13, 14, 14),
            Block.box(13, 3, 12, 15, 14, 14),
            Block.box(-15.5, 5, 1.5, 15.5, 7, 14.5),
            Block.box(-16, 14, 0, 16, 16, 16),
            Block.box(-14, 7, 3, 14, 14, 13)
    );
    // endregion

    // region: Lockbox
    VoxelShape LOCKBOX = Block.box(2.5, 0, 3.5, 13.5, 9.25, 12.5);
    // endregion

    // region: Wardrobe Top
    VoxelShape WARDROBE_TOP = VoxelShapeHelper.combine(
            Block.box(12, 0, 2, 14, 14, 4),
            Block.box(-14, 0, 2, -12, 14, 4),
            Block.box(-14, 0, 12, -12, 14, 14),
            Block.box(12, 0, 12, 14, 14, 14),
            Block.box(-15, 14, 1, 15, 16, 15),
            Block.box(-13, 0, 3, 13, 14, 13)
    );
    // endregion

    // region: Wardrobe Bottom
    VoxelShape WARDROBE_BOTTOM = VoxelShapeHelper.combine(
            Block.box(12, 0, 2, 14, 3, 5),
            Block.box(-14, 0, 2, -12, 3, 5),
            Block.box(-14, 0, 11, -12, 3, 14),
            Block.box(12, 0, 11, 14, 3, 14),
            Block.box(12, 3, 11, 14, 4, 13),
            Block.box(12, 3, 3, 14, 4, 5),
            Block.box(-14, 3, 3, -12, 4, 5),
            Block.box(-14, 3, 11, -12, 4, 13),
            Block.box(-15, 4, 1, 15, 6, 15),
            Block.box(12, 6, 2, 14, 30, 4),
            Block.box(-14, 6, 2, -12, 30, 4),
            Block.box(-14, 6, 12, -12, 30, 14),
            Block.box(12, 6, 12, 14, 30, 14),
            Block.box(-12, 6, 3, 12, 30, 13),
            Block.box(-15, 30, 1, 15, 32, 15)
    );
    // endregion

    // region: Painting Wide
    VoxelShape PAINTING_WIDE = VoxelShapeHelper.combine(
            Block.box(-16, 0, 14, -13, 3, 16),
            Block.box(-16, 13, 14, -13, 16, 16),
            Block.box(13, 13, 14, 16, 16, 16),
            Block.box(13, 0, 14, 16, 3, 16),
            Block.box(-13, 1, 14, 13, 3, 16),
            Block.box(-13, 13, 14, 13, 15, 16),
            Block.box(-15, 3, 14, 15, 13, 16)
    );
    // endregion

    // region: Painting Small
    VoxelShape PAINTING_SMALL = VoxelShapeHelper.combine(
            Block.box(0, 0, 14, 3, 3, 16),
            Block.box(0, 13, 14, 3, 16, 16),
            Block.box(13, 13, 14, 16, 16, 16),
            Block.box(13, 0, 14, 16, 3, 16),
            Block.box(3, 1, 14, 13, 3, 16),
            Block.box(3, 13, 14, 13, 15, 16),
            Block.box(1, 3, 14, 15, 13, 16)
    );
    // endregion

    // region: Oven
    VoxelShape OVEN = VoxelShapeHelper.combine(
            Block.box(0, 0, 0, 3.5, 4, 3.5),
            Block.box(0, 0, 12.5, 3.5, 4, 16),
            Block.box(12.5, 0, 12.5, 16, 4, 16),
            Block.box(12.5, 0, 0, 16, 4, 3.5),
            Block.box(0, 4, 0, 16, 6, 16),
            Block.box(0, 14, 0, 16, 16, 16),
            Block.box(0.5, 6, 0.5, 15.5, 14, 15.5),
            Block.box(2.5, 7, -0.5, 12.5, 13, 0.5)
    );
    // endregion

    // region: Door Double
    VoxelShape DOOR_DOUBLE = VoxelShapeHelper.combine(
            Block.box(0, 0, 0.5, 14, 30, 2.5),
            Block.box(14, 0, 0, 16, 32, 3),
            Block.box(13, 25, 0, 14, 32, 3),
            Block.box(12, 27, 0, 13, 32, 3),
            Block.box(9, 28, 0, 12, 32, 3),
            Block.box(5, 29, 0, 9, 32, 3),
            Block.box(0, 30, 0, 5, 32, 3)
    );
    // endregion

    // region: Door Single
    VoxelShape DOOR_SINGLE = VoxelShapeHelper.combine(
            Block.box(0, 0, 0, 2, 32, 3),
            Block.box(2, 30, 0, 14, 32, 3),
            Block.box(14, 0, 0, 16, 32, 3),
            Block.box(2, 0, 0.5, 14, 30, 2.5)
    );
    // endregion

    // region: Bed Single
    VoxelShape BED_SINGLE = VoxelShapeHelper.combine(
            Block.box(0, 0, 0, 3, 3, 2),
            Block.box(1, 3, 0, 3, 9, 2),
            Block.box(0, 9, 0, 3, 12, 2),
            Block.box(0, 9, 30, 3, 12, 32),
            Block.box(0, 0, 30, 3, 3, 32),
            Block.box(1, 3, 30, 3, 9, 32),
            Block.box(13, 9, 30, 16, 12, 32),
            Block.box(13, 0, 30, 16, 3, 32),
            Block.box(13, 3, 30, 15, 9, 32),
            Block.box(13, 9, 0, 16, 12, 2),
            Block.box(13, 0, 0, 16, 3, 2),
            Block.box(13, 3, 0, 15, 9, 2),
            Block.box(1, 3, 2, 15, 5, 32),
            Block.box(3, 2, 30, 4, 3, 32),
            Block.box(12, 2, 30, 13, 3, 32),
            Block.box(12, 2, 0, 13, 3, 2),
            Block.box(3, 2, 0, 4, 3, 2),
            Block.box(2, 3, 0, 14, 15, 2),
            Block.box(2, 3, 30, 14, 15, 32),
            Block.box(2, 3, 2, 14, 8, 30)
    );
    // endregion

    // region: Bed Double
    VoxelShape BED_DOUBLE = VoxelShapeHelper.combine(
            Block.box(-16, 0, 0, -13, 3, 2),
            Block.box(-15, 3, 0, -13, 9, 2),
            Block.box(-16, 9, 0, -13, 12, 2),
            Block.box(-16, 9, 30, -13, 12, 32),
            Block.box(-16, 0, 30, -13, 3, 32),
            Block.box(-15, 3, 30, -13, 9, 32),
            Block.box(13, 9, 30, 16, 12, 32),
            Block.box(13, 0, 30, 16, 3, 32),
            Block.box(13, 3, 30, 15, 9, 32),
            Block.box(13, 9, 0, 16, 12, 2),
            Block.box(13, 0, 0, 16, 3, 2),
            Block.box(13, 3, 0, 15, 9, 2),
            Block.box(-15, 3, 2, 15, 5, 32),
            Block.box(-13, 2, 30, -12, 3, 32),
            Block.box(12, 2, 30, 13, 3, 32),
            Block.box(12, 2, 0, 13, 3, 2),
            Block.box(-13, 2, 0, -12, 3, 2),
            Block.box(-14, 3, 0, 14, 15, 2),
            Block.box(-14, 3, 30, 14, 15, 32),
            Block.box(-14, 3, 2, 14, 8, 30)
    );
    // endregion

    // region: Shelf Single
    VoxelShape SHELF_SINGLE = VoxelShapeHelper.combine(
            Block.box(0, 14, 0, 16, 16, 16),
            Block.box(13, 11, 3, 15, 14, 6),
            Block.box(13, 12, 6, 15, 14, 16),
            Block.box(13, 8, 14, 15, 12, 16),
            Block.box(13, 5, 13, 15, 8, 16),
            Block.box(1, 5, 13, 3, 8, 16),
            Block.box(1, 11, 3, 3, 14, 6),
            Block.box(1, 12, 6, 3, 14, 16),
            Block.box(1, 8, 14, 3, 12, 16)
    );
    // endregion

    // region: Shelf Center
    VoxelShape SHELF_CENTER = Block.box(0, 14, 0, 16, 16, 16);
    // endregion

    // region: Shelf Left
    VoxelShape SHELF_LEFT = VoxelShapeHelper.combine(
            Block.box(0, 14, 0, 16, 16, 16),
            Block.box(13, 11, 3, 15, 14, 6),
            Block.box(13, 12, 6, 15, 14, 16),
            Block.box(13, 8, 14, 15, 12, 16),
            Block.box(13, 5, 13, 15, 8, 16)
    );
    // endregion

    // region: Shelf Right
    VoxelShape SHELF_RIGHT = VoxelShapeHelper.combine(
            Block.box(0, 14, 0, 16, 16, 16),
            Block.box(1, 11, 3, 3, 14, 6),
            Block.box(1, 12, 6, 3, 14, 16),
            Block.box(1, 8, 14, 3, 12, 16),
            Block.box(1, 5, 13, 3, 8, 16)
    );
    // endregion

    // region: Sofa Single
    VoxelShape SOFA_SINGLE = VoxelShapeHelper.combine(
            Block.box(13, 0, 12, 15, 3, 15),
            Block.box(13, 0, 1, 15, 3, 4),
            Block.box(1, 0, 1, 3, 3, 4),
            Block.box(1, 0, 12, 3, 3, 15),
            Block.box(1, 3, 12, 3, 4, 14),
            Block.box(13, 3, 12, 15, 4, 14),
            Block.box(13, 3, 2, 15, 4, 4),
            Block.box(1, 3, 2, 3, 4, 4),
            Block.box(1, 4, 1.5, 15, 6, 14.5),
            Block.box(13, 6, 2, 15, 9, 12),
            Block.box(1, 6, 2, 3, 9, 12),
            Block.box(1, 6, 12, 15, 13, 14),
            Block.box(2, 13, 12, 14, 15, 14),
            Block.box(4, 15, 12, 12, 16, 14)
    );
    // endregion

    // region: Sofa Center
    VoxelShape SOFA_CENTER = VoxelShapeHelper.combine(
            Block.box(0, 4, 1.5, 16, 6, 14.5),
            Block.box(0, 6, 12, 16, 16, 14)
    );
    // endregion

    // region: Sofa Left
    VoxelShape SOFA_LEFT = VoxelShapeHelper.combine(
            Block.box(13, 0, 1, 15, 3, 4),
            Block.box(13, 0, 12, 15, 3, 15),
            Block.box(13, 3, 12, 15, 4, 14),
            Block.box(13, 3, 2, 15, 4, 4),
            Block.box(0, 4, 1.5, 15, 6, 14.5),
            Block.box(0, 6, 12, 15, 13, 14),
            Block.box(0, 13, 12, 14, 15, 14),
            Block.box(0, 15, 12, 12, 16, 14),
            Block.box(13, 6, 2, 15, 9, 12)
    );
    // endregion

    // region: Sofa Right
    VoxelShape SOFA_RIGHT = VoxelShapeHelper.combine(
            Block.box(1, 0, 1, 3, 3, 4),
            Block.box(1, 0, 12, 3, 3, 15),
            Block.box(1, 3, 12, 3, 4, 14),
            Block.box(1, 3, 2, 3, 4, 4),
            Block.box(1, 4, 1.5, 16, 6, 14.5),
            Block.box(1, 6, 12, 16, 13, 14),
            Block.box(2, 13, 12, 16, 15, 14),
            Block.box(4, 15, 12, 16, 16, 14),
            Block.box(1, 6, 2, 3, 9, 12)
    );
    // endregion

    // region: Sofa Corner
    VoxelShape SOFA_CORNER = VoxelShapeHelper.combine(
            Block.box(0.5, 0, 0.5, 4.25, 4, 4.25),
            Block.box(11.5, 0, 11.25, 15.25, 4, 15),
            Block.box(12, 0, 1, 15, 3, 3),
            Block.box(12, 3, 1, 14, 4, 3),
            Block.box(1, 3, 12, 3, 4, 14),
            Block.box(1, 0, 12, 3, 3, 15),
            Block.box(0, 4, 1.5, 1.5, 6, 14.5),
            Block.box(1.5, 4, 0, 14.5, 6, 14.5),
            Block.box(12, 6, 0, 14, 16, 14),
            Block.box(0, 6, 12, 12, 16, 14)
    );
    // endregion

    // region: Counter Single
    VoxelShape COUNTER_SINGLE = VoxelShapeHelper.combine(
            Block.box(0, 0, 3, 16, 13, 16),
            Block.box(0, 13, 0, 16, 16, 16)
    );
    // endregion

    // region: Counter Corner
    VoxelShape COUNTER_CORNER = VoxelShapeHelper.combine(
            Block.box(0, 0, 0, 13, 13, 3),
            Block.box(0, 0, 3, 16, 13, 16),
            Block.box(0, 13, 0, 16, 16, 16)
    );
    // endregion

    static void bootstrap() {}
}
