package xyz.apex.minecraft.fantasyfurniture.necrolord.common;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeHelper;

public interface NecrolordVoxelShapes
{
    // region: Wall Light
    VoxelShape WALL_LIGHT = Block.box(6, 4, 8, 10, 14.5, 16);
    // endregion

    // region: Floor Light
    VoxelShape FLOOR_LIGHT = VoxelShapeHelper.combine(
            Block.box(5, 0, 5, 11, 2, 11),
            Block.box(6.5, 2, 6.5, 9.5, 4, 9.5),
            Block.box(6.5, 10, 6.5, 9.5, 12, 9.5),
            Block.box(7, 4, 7, 9, 18, 9),
            Block.box(1.25, 18, 6.5, 14.75, 25, 9.5),
            Block.box(12.25, 25, 7, 14.25, 28, 9),
            Block.box(1.75, 25, 7, 3.75, 28, 9),
            Block.box(7, 25, 7, 9, 29, 9)
    );
    // endregion

    // region: Table Large
    VoxelShape TABLE_LARGE = VoxelShapeHelper.combine(
            Block.box(12, 0, 0, 16, 2, 4),
            Block.box(-16, 0, 0, -12, 2, 4),
            Block.box(-16, 0, 28, -12, 2, 32),
            Block.box(12, 0, 28, 16, 2, 32),
            Block.box(12, 9, 28, 16, 11, 32),
            Block.box(-16, 9, 28, -12, 11, 32),
            Block.box(-16, 9, 0, -12, 11, 4),
            Block.box(12, 9, 0, 16, 11, 4),
            Block.box(12, 9, 28, 16, 11, 32),
            Block.box(12, 14, 28, 16, 16, 32),
            Block.box(12, 14, 0, 16, 16, 4),
            Block.box(-16, 14, 0, -12, 16, 4),
            Block.box(-16, 14, 28, -12, 16, 32),
            Block.box(-15, 2, 29, -13, 14, 31),
            Block.box(13, 2, 29, 15, 14, 31),
            Block.box(13, 2, 1, 15, 14, 3),
            Block.box(-15, 2, 1, -13, 14, 3),
            Block.box(-15, 14, 1, 15, 16, 31)
    );
    // endregion

    // region: Table Small
    VoxelShape TABLE_SMALL = VoxelShapeHelper.combine(
            Block.box(12, 0, 0, 16, 2, 4),
            Block.box(0, 0, 0, 4, 2, 4),
            Block.box(0, 0, 12, 4, 2, 16),
            Block.box(12, 0, 12, 16, 2, 16),
            Block.box(12, 9, 12, 16, 11, 16),
            Block.box(0, 9, 12, 4, 11, 16),
            Block.box(0, 9, 0, 4, 11, 4),
            Block.box(12, 9, 0, 16, 11, 4),
            Block.box(12, 9, 12, 16, 11, 16),
            Block.box(12, 14, 12, 16, 16, 16),
            Block.box(12, 14, 0, 16, 16, 4),
            Block.box(0, 14, 0, 4, 16, 4),
            Block.box(0, 14, 12, 4, 16, 16),
            Block.box(1, 2, 13, 3, 14, 15),
            Block.box(13, 2, 13, 15, 14, 15),
            Block.box(13, 2, 1, 15, 14, 3),
            Block.box(1, 2, 1, 3, 14, 3),
            Block.box(1, 14, 1, 15, 16, 15)
    );
    // endregion

    // region: Table Wide
    VoxelShape TABLE_WIDE = VoxelShapeHelper.combine(
            Block.box(12, 0, 0, 16, 2, 4),
            Block.box(-16, 0, 0, -12, 2, 4),
            Block.box(-16, 0, 12, -12, 2, 16),
            Block.box(12, 0, 12, 16, 2, 16),
            Block.box(12, 9, 12, 16, 11, 16),
            Block.box(-16, 9, 12, -12, 11, 16),
            Block.box(-16, 9, 0, -12, 11, 4),
            Block.box(12, 9, 0, 16, 11, 4),
            Block.box(12, 9, 12, 16, 11, 16),
            Block.box(12, 14, 12, 16, 16, 16),
            Block.box(12, 14, 0, 16, 16, 4),
            Block.box(-16, 14, 0, -12, 16, 4),
            Block.box(-16, 14, 12, -12, 16, 16),
            Block.box(-15, 2, 13, -13, 14, 15),
            Block.box(13, 2, 13, 15, 14, 15),
            Block.box(13, 2, 1, 15, 14, 3),
            Block.box(-15, 2, 1, -13, 14, 3),
            Block.box(-15, 14, 1, 15, 16, 15)
    );
    // endregion

    // region: Bench
    VoxelShape BENCH = VoxelShapeHelper.combine(
            Block.box(12, 0, 0, 16, 2, 4),
            Block.box(12, 5, 0, 16, 7, 4),
            Block.box(12, 5, 12, 16, 7, 16),
            Block.box(12, 0, 12, 16, 2, 16),
            Block.box(-16, 0, 12, -12, 2, 16),
            Block.box(-16, 5, 12, -12, 7, 16),
            Block.box(-16, 5, 0, -12, 7, 4),
            Block.box(-16, 0, 0, -12, 2, 4),
            Block.box(-15, 2, 1, -13, 5, 3),
            Block.box(-15, 2, 13, -13, 5, 15),
            Block.box(13, 2, 13, 15, 5, 15),
            Block.box(13, 2, 1, 15, 5, 3),
            Block.box(-15, 5, 1, 15, 7, 15)
    );
    // endregion

    // region: Chair
    VoxelShape CHAIR = VoxelShapeHelper.combine(
            Block.box(1, 0, 1, 4, 2, 4),
            Block.box(1, 7, 1, 4, 9, 4),
            Block.box(1, 0, 12, 4, 2, 15),
            Block.box(1, 7, 12, 4, 9, 15),
            Block.box(12, 0, 12, 15, 2, 15),
            Block.box(12, 7, 12, 15, 9, 15),
            Block.box(12, 7, 1, 15, 9, 4),
            Block.box(12, 0, 1, 15, 2, 4),
            Block.box(2, 0, 2, 14, 9, 14),
            Block.box(2, 9, 12, 14, 24, 14),
            Block.box(12, 20, 11.5, 15, 24, 14.5),
            Block.box(1, 20, 11.5, 4, 24, 14.5),
            Block.box(2, 24, 11.5, 5, 27, 14.5),
            Block.box(11, 24, 11.5, 14, 27, 14.5),
            Block.box(10, 27, 11.5, 12, 29, 14.5),
            Block.box(4, 27, 11.5, 6, 29, 14.5)
    );
    // endregion

    // region: Ceiling Light
    VoxelShape CEILING_LIGHT = Block.box(3, 0, 3, 13, 16, 13);
    // endregion

    // region: Cushion
    VoxelShape CUSHION = VoxelShapeHelper.combine(
            Block.box(2, 0, 2, 5, 2, 5),
            Block.box(2, 0, 11, 5, 2, 14),
            Block.box(11, 0, 11, 14, 2, 14),
            Block.box(11, 0, 2, 14, 2, 5),
            Block.box(11, 2, 3, 13, 3, 5),
            Block.box(3, 2, 3, 5, 3, 5),
            Block.box(3, 2, 11, 5, 3, 13),
            Block.box(11, 2, 11, 13, 3, 13),
            Block.box(2, 3, 2, 14, 4, 14),
            Block.box(2.5, 4, 2.5, 13.5, 7, 13.5)
    );
    // endregion

    // region: Stool
    VoxelShape STOOL = VoxelShapeHelper.combine(
            Block.box(2, 0, 2, 5, 2, 5),
            Block.box(2, 0, 11, 5, 2, 14),
            Block.box(11, 0, 11, 14, 2, 14),
            Block.box(11, 0, 2, 14, 2, 5),
            Block.box(11, 2, 3, 13, 3, 5),
            Block.box(3, 2, 3, 5, 3, 5),
            Block.box(3, 2, 11, 5, 3, 13),
            Block.box(11, 2, 11, 13, 3, 13),
            Block.box(2, 3, 2, 14, 6, 14)
    );
    // endregion

    // region: Chest
    VoxelShape CHEST = VoxelShapeHelper.combine(
            Block.box(10, 0, 1, 14, 2, 5),
            Block.box(-14, 0, 1, -10, 2, 5),
            Block.box(-14, 0, 11, -10, 2, 15),
            Block.box(10, 0, 11, 14, 2, 15),
            Block.box(10, 5, 11, 14, 7, 15),
            Block.box(10, 5, 1, 14, 7, 5),
            Block.box(-14, 5, 1, -10, 7, 5),
            Block.box(-14, 5, 11, -10, 7, 15),
            Block.box(-12, 0, 3, 12, 12, 13),
            Block.box(11, 0, 2, 13, 9, 4),
            Block.box(-13, 0, 2, -11, 9, 4),
            Block.box(-13, 0, 12, -11, 9, 14),
            Block.box(11, 0, 12, 13, 9, 14),
            Block.box(10, 9, 11, 14, 11, 15),
            Block.box(10, 9, 1, 14, 11, 5),
            Block.box(-14, 9, 1, -10, 11, 5),
            Block.box(-14, 9, 11, -10, 11, 15)
    );
    // endregion

    // region: Bookshelf
    VoxelShape BOOKSHELF = VoxelShapeHelper.combine(
            Block.box(12, 0, 0, 16, 2, 4),
            Block.box(-16, 0, 0, -12, 2, 4),
            Block.box(-16, 0, 12, -12, 2, 16),
            Block.box(12, 0, 12, 16, 2, 16),
            Block.box(12, 15, 12, 16, 17, 16),
            Block.box(12, 15, 0, 16, 17, 4),
            Block.box(-16, 15, 0, -12, 17, 4),
            Block.box(-16, 15, 12, -12, 17, 16),
            Block.box(-16, 30, 12, -12, 32, 16),
            Block.box(12, 30, 12, 16, 32, 16),
            Block.box(12, 30, 0, 16, 32, 4),
            Block.box(-15, 0, 1, 15, 32, 15)
    );
    // endregion

    // region: Desk Left
    VoxelShape DESK_LEFT = VoxelShapeHelper.combine(
            Block.box(12, 0, 1, 15, 2, 4),
            Block.box(12, 0, 12, 15, 2, 15),
            Block.box(1, 0, 12, 4, 2, 15),
            Block.box(1, 0, 1, 4, 2, 4),
            Block.box(1, 12, 1, 4, 14, 4),
            Block.box(1, 12, 12, 4, 14, 15),
            Block.box(12, 12, 12, 15, 14, 15),
            Block.box(12, 12, 1, 15, 14, 4),
            Block.box(2, 0, 2, 14, 14, 14),
            Block.box(-15, 0, 1, -11, 2, 5),
            Block.box(-15, 12, 1, -11, 14, 5),
            Block.box(-15, 12, 11, -11, 14, 15),
            Block.box(-14, 2, 12, -12, 12, 14),
            Block.box(-14, 2, 2, -12, 12, 4),
            Block.box(-16, 14, 0, 16, 16, 16),
            Block.box(-15, 0, 11, -11, 2, 15)
    );
    // endregion

    // region: Desk Right
    VoxelShape DESK_RIGHT = VoxelShapeHelper.combine(
            Block.box(-15, 0, 1, -12, 2, 4),
            Block.box(-15, 0, 12, -12, 2, 15),
            Block.box(-4, 0, 12, -1, 2, 15),
            Block.box(-4, 0, 1, -1, 2, 4),
            Block.box(-4, 12, 1, -1, 14, 4),
            Block.box(-4, 12, 12, -1, 14, 15),
            Block.box(-15, 12, 12, -12, 14, 15),
            Block.box(-15, 12, 1, -12, 14, 4),
            Block.box(-14, 0, 2, -2, 14, 14),
            Block.box(11, 0, 1, 15, 2, 5),
            Block.box(11, 12, 1, 15, 14, 5),
            Block.box(11, 12, 11, 15, 14, 15),
            Block.box(12, 2, 12, 14, 12, 14),
            Block.box(12, 2, 2, 14, 12, 4),
            Block.box(-16, 14, 0, 16, 16, 16),
            Block.box(11, 0, 11, 15, 2, 15)
    );
    // endregion

    // region: Drawer
    VoxelShape DRAWER = VoxelShapeHelper.combine(
            Block.box(0, 0, 0, 3, 2, 3),
            Block.box(0, 14, 0, 3, 16, 3),
            Block.box(0, 14, 13, 3, 16, 16),
            Block.box(0, 0, 13, 3, 2, 16),
            Block.box(13, 0, 13, 16, 2, 16),
            Block.box(13, 14, 13, 16, 16, 16),
            Block.box(13, 14, 0, 16, 16, 3),
            Block.box(13, 0, 0, 16, 2, 3),
            Block.box(1, 0, 1, 15, 16, 15)
    );
    // endregion

    // region: Dresser
    VoxelShape DRESSER = VoxelShapeHelper.combine(
            Block.box(-16, 0, 0, -13, 2, 3),
            Block.box(-16, 0, 13, -13, 2, 16),
            Block.box(13, 0, 13, 16, 2, 16),
            Block.box(13, 0, 0, 16, 2, 3),
            Block.box(13, 14, 0, 16, 16, 3),
            Block.box(13, 14, 13, 16, 16, 16),
            Block.box(-16, 14, 13, -13, 16, 16),
            Block.box(-16, 14, 0, -13, 16, 3),
            Block.box(-15, 0, 1, 15, 16, 15)
    );
    // endregion

    // region: Lockbox
    VoxelShape LOCKBOX = Block.box(0.5, 0, 2.5, 15.5, 10, 13.5);
    // endregion

    // region: Wardrobe Top
    VoxelShape WARDROBE_TOP = VoxelShapeHelper.combine(
            Block.box(-15, 0, 1, 15, 11, 15),
            Block.box(12, 9, 0, 16, 11, 4),
            Block.box(-16, 9, 0, -12, 11, 4),
            Block.box(-16, 9, 12, -12, 11, 16),
            Block.box(12, 9, 12, 16, 11, 16)
    );
    // endregion

    // region: Wardrobe Bottom
    VoxelShape WARDROBE_BOTTOM = VoxelShapeHelper.combine(
            Block.box(12, 0, 0, 16, 2, 4),
            Block.box(-16, 0, 0, -12, 2, 4),
            Block.box(-16, 0, 12, -12, 2, 16),
            Block.box(12, 0, 12, 16, 2, 16),
            Block.box(12, 8, 12, 16, 10, 16),
            Block.box(-16, 8, 12, -12, 10, 16),
            Block.box(-16, 8, 0, -12, 10, 4),
            Block.box(12, 8, 0, 16, 10, 4),
            Block.box(12, 30, 0, 16, 32, 4),
            Block.box(-16, 30, 0, -12, 32, 4),
            Block.box(-16, 30, 12, -12, 32, 16),
            Block.box(12, 30, 12, 16, 32, 16),
            Block.box(-15, 0, 1, 15, 32, 15)
    );
    // endregion

    // region: Painting Wide
    VoxelShape PAINTING_WIDE = VoxelShapeHelper.combine(
            Block.box(-16, 0, 13, -13, 3, 16),
            Block.box(-16, 13, 13, -13, 16, 16),
            Block.box(13, 13, 13, 16, 16, 16),
            Block.box(13, 0, 13, 16, 3, 16),
            Block.box(-15, 1, 14, 15, 15, 16)
    );
    // endregion

    // region: Painting Small
    VoxelShape PAINTING_SMALL = VoxelShapeHelper.combine(
            Block.box(0, 0, 13, 3, 3, 16),
            Block.box(0, 13, 13, 3, 16, 16),
            Block.box(13, 13, 13, 16, 16, 16),
            Block.box(13, 0, 13, 16, 3, 16),
            Block.box(1, 1, 14, 15, 15, 16)
    );
    // endregion

    // region: Oven
    VoxelShape OVEN = VoxelShapeHelper.combine(
            Block.box(1, 0, 1, 4, 2, 4),
            Block.box(1, 0, 12, 4, 2, 15),
            Block.box(12, 0, 12, 15, 2, 15),
            Block.box(12, 0, 1, 15, 2, 4),
            Block.box(0, 2, 0, 16, 16, 16)
    );
    // endregion

    // region: Door Double
    VoxelShape DOOR_DOUBLE = VoxelShapeHelper.combine(
            Block.box(0, 0, 0.5, 13, 29, 2.5),
            Block.box(13, 0, 0, 16, 32, 3),
            Block.box(12, 20, 0, 13, 32, 3),
            Block.box(11, 24, 0, 12, 32, 3),
            Block.box(9, 27, 0, 11, 32, 3),
            Block.box(4, 28, 0, 9, 32, 3),
            Block.box(0, 29, 0, 4, 32, 3)
    );
    // endregion

    // region: Door Single
    VoxelShape DOOR_SINGLE = Block.box(0, 0, 0, 16, 32, 3);
    // endregion

    // region: Bed Single
    VoxelShape BED_SINGLE = VoxelShapeHelper.combine(
            Block.box(0, 0, 0, 4, 2, 4),
            Block.box(0, 0, 28, 4, 2, 32),
            Block.box(12, 0, 28, 16, 2, 32),
            Block.box(12, 0, 0, 16, 2, 4),
            Block.box(12, 13, 0, 16, 15, 4),
            Block.box(0, 13, 0, 4, 15, 4),
            Block.box(0, 12, 28, 4, 14, 32),
            Block.box(12, 12, 28, 16, 14, 32),
            Block.box(13, 2, 29, 15, 12, 31),
            Block.box(1, 2, 29, 3, 12, 31),
            Block.box(1, 2, 1, 3, 13, 3),
            Block.box(13, 2, 1, 15, 13, 3),
            Block.box(3, 3, 1, 13, 12, 3),
            Block.box(3, 3, 29, 13, 11, 31),
            Block.box(1, 3, 3, 15, 8, 29)
    );
    // endregion

    // region: Bed Double
    VoxelShape BED_DOUBLE = VoxelShapeHelper.combine(
            Block.box(-16, 0, 0, -12, 2, 4),
            Block.box(-16, 0, 28, -12, 2, 32),
            Block.box(12, 0, 28, 16, 2, 32),
            Block.box(12, 0, 0, 16, 2, 4),
            Block.box(12, 13, 0, 16, 15, 4),
            Block.box(-16, 13, 0, -12, 15, 4),
            Block.box(-16, 12, 28, -12, 14, 32),
            Block.box(12, 12, 28, 16, 14, 32),
            Block.box(13, 2, 29, 15, 12, 31),
            Block.box(-15, 2, 29, -13, 12, 31),
            Block.box(-15, 2, 1, -13, 13, 3),
            Block.box(13, 2, 1, 15, 13, 3),
            Block.box(-13, 3, 1, 13, 12, 3),
            Block.box(-13, 3, 29, 13, 11, 31),
            Block.box(-15, 3, 3, 15, 8, 29)
    );
    // endregion

    // region: Shelf Single
    VoxelShape SHELF_SINGLE = VoxelShapeHelper.combine(
            Block.box(12, 4, 13, 15, 6, 16),
            Block.box(12.5, 6, 14, 14.5, 11, 16),
            Block.box(12, 11, 13, 15, 14, 16),
            Block.box(12.5, 12, 8, 14.5, 14, 13),
            Block.box(1, 4, 13, 4, 6, 16),
            Block.box(1.5, 6, 14, 3.5, 11, 16),
            Block.box(1, 11, 13, 4, 14, 16),
            Block.box(1.5, 12, 8, 3.5, 14, 13),
            Block.box(0, 14, 0, 16, 16, 16)
    );
    // endregion

    // region: Shelf Center
    VoxelShape SHELF_CENTER = Block.box(0, 14, 0, 16, 16, 16);
    // endregion

    // region: Shelf Left
    VoxelShape SHELF_LEFT = VoxelShapeHelper.combine(
            Block.box(12, 4, 13, 15, 6, 16),
            Block.box(12.5, 6, 14, 14.5, 11, 16),
            Block.box(12, 11, 13, 15, 14, 16),
            Block.box(12.5, 12, 8, 14.5, 14, 13),
            Block.box(0, 14, 0, 16, 16, 16)
    );
    // endregion

    // region: Shelf Right
    VoxelShape SHELF_RIGHT = VoxelShapeHelper.combine(
            Block.box(1, 4, 13, 4, 6, 16),
            Block.box(1.5, 6, 14, 3.5, 11, 16),
            Block.box(1, 11, 13, 4, 14, 16),
            Block.box(1.5, 12, 8, 3.5, 14, 13),
            Block.box(0, 14, 0, 16, 16, 16)
    );
    // endregion

    // region: Sofa Single
    VoxelShape SOFA_SINGLE = VoxelShapeHelper.combine(
            Block.box(1, 0, 1, 4, 2, 4),
            Block.box(2, 2, 2, 4, 3, 4),
            Block.box(12, 0, 1, 15, 2, 4),
            Block.box(12, 2, 2, 14, 3, 4),
            Block.box(2, 2, 12, 4, 3, 14),
            Block.box(1, 0, 12, 4, 2, 15),
            Block.box(12, 0, 12, 15, 2, 15),
            Block.box(12, 2, 12, 14, 3, 14),
            Block.box(1, 3, 1, 15, 6, 15),
            Block.box(1, 6, 12, 15, 16, 15),
            Block.box(1, 9, 1, 3, 11, 12),
            Block.box(1, 6, 2, 3, 9, 4),
            Block.box(13, 9, 1, 15, 11, 12),
            Block.box(13, 6, 2, 15, 9, 4)
    );
    // endregion

    // region: Sofa Center
    VoxelShape SOFA_CENTER = VoxelShapeHelper.combine(
            Block.box(0, 3, 1, 16, 6, 15),
            Block.box(0, 6, 12, 16, 16, 15)
    );
    // endregion

    // region: Sofa Left
    VoxelShape SOFA_LEFT = VoxelShapeHelper.combine(
            Block.box(12, 0, 1, 15, 2, 4),
            Block.box(12, 2, 2, 14, 3, 4),
            Block.box(12, 2, 12, 14, 3, 14),
            Block.box(12, 0, 12, 15, 2, 15),
            Block.box(0, 3, 1, 15, 6, 15),
            Block.box(0, 6, 12, 15, 16, 15),
            Block.box(13, 9, 1, 15, 11, 12),
            Block.box(13, 6, 2, 15, 9, 4)
    );
    // endregion

    // region: Sofa Right
    VoxelShape SOFA_RIGHT = VoxelShapeHelper.combine(
            Block.box(1, 0, 1, 4, 2, 4),
            Block.box(2, 2, 2, 4, 3, 4),
            Block.box(2, 2, 12, 4, 3, 14),
            Block.box(1, 0, 12, 4, 2, 15),
            Block.box(1, 3, 1, 16, 6, 15),
            Block.box(1, 6, 12, 16, 16, 15),
            Block.box(1, 9, 1, 3, 11, 12),
            Block.box(1, 6, 2, 3, 9, 4)
    );
    // endregion

    // region: Sofa Corner
    VoxelShape SOFA_CORNER = VoxelShapeHelper.combine(
            Block.box(12, 0, 12, 15, 2, 15),
            Block.box(12, 2, 12, 14, 3, 14),
            Block.box(0, 3, 12, 15, 16, 15),
            Block.box(12, 3, 0, 15, 16, 12),
            Block.box(1, 3, 0, 15, 6, 12),
            Block.box(0, 3, 1, 14, 6, 12)
    );
    // endregion

    // region: Counter Single
    VoxelShape COUNTER_SINGLE = VoxelShapeHelper.combine(
            Block.box(0, 0, 3, 16, 13, 16),
            Block.box(1, 1, 2, 15, 12, 3),
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
