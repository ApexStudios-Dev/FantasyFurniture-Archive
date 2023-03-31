package xyz.apex.minecraft.fantasyfurniture.bone.common;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeHelper;

public interface BoneVoxelShapes
{
    // region: Wall Light
    VoxelShape WALL_LIGHT = Block.box(6, 4, 9, 10, 15, 16);
    // endregion

    // region: Floor Light
    VoxelShape FLOOR_LIGHT = VoxelShapeHelper.combine(
            Block.box(6, 0, 6, 10, 2, 10),
            Block.box(7, 2, 7, 9, 23, 9),
            Block.box(2.5, 21.75, 6.5, 5.5, 23.75, 9.5),
            Block.box(3.25, 23.75, 7.25, 4.75, 27.75, 8.75),
            Block.box(7.25, 24.75, 7.25, 8.75, 28.75, 8.75),
            Block.box(11.25, 23.75, 7.25, 12.75, 27.75, 8.75),
            Block.box(10.5, 21.75, 6.5, 13.5, 23.75, 9.5),
            Block.box(6.5, 22.75, 6.5, 9.5, 24.75, 9.5),
            Block.box(3, 17.75, 7, 7, 21.75, 9),
            Block.box(9, 17.75, 7, 13, 21.75, 9)
    );
    // endregion

    // region: Table Large
    VoxelShape TABLE_LARGE = VoxelShapeHelper.combine(
            Block.box(-15, 0, 1, -12, 2, 4),
            Block.box(-15, 0, 28, -12, 2, 31),
            Block.box(12, 0, 28, 15, 2, 31),
            Block.box(12, 0, 1, 15, 2, 4),
            Block.box(12, 12, 1, 15, 14, 4),
            Block.box(-15, 12, 1, -12, 14, 4),
            Block.box(12, 12, 28, 15, 14, 31),
            Block.box(-15, 12, 28, -12, 14, 31),
            Block.box(12.5, 2, 1.5, 14.5, 12, 3.5),
            Block.box(-14.5, 2, 1.5, -12.5, 12, 3.5),
            Block.box(12.5, 2, 28.5, 14.5, 12, 30.5),
            Block.box(-14.5, 2, 28.5, -12.5, 12, 30.5),
            Block.box(-16, 14, 0, 16, 16, 32)
    );
    // endregion

    // region: Table Small
    VoxelShape TABLE_SMALL = VoxelShapeHelper.combine(
            Block.box(1, 0, 1, 4, 2, 4),
            Block.box(1, 0, 12, 4, 2, 15),
            Block.box(12, 0, 12, 15, 2, 15),
            Block.box(12, 0, 1, 15, 2, 4),
            Block.box(12, 12, 1, 15, 14, 4),
            Block.box(1, 12, 1, 4, 14, 4),
            Block.box(12, 12, 12, 15, 14, 15),
            Block.box(1, 12, 12, 4, 14, 15),
            Block.box(12.5, 2, 1.5, 14.5, 12, 3.5),
            Block.box(1.5, 2, 1.5, 3.5, 12, 3.5),
            Block.box(12.5, 2, 12.5, 14.5, 12, 14.5),
            Block.box(1.5, 2, 12.5, 3.5, 12, 14.5),
            Block.box(0, 14, 0, 16, 16, 16)
    );
    // endregion

    // region: Table Wide
    VoxelShape TABLE_WIDE = VoxelShapeHelper.combine(
            Block.box(-15, 0, 1, -12, 2, 4),
            Block.box(-15, 0, 12, -12, 2, 15),
            Block.box(12, 0, 12, 15, 2, 15),
            Block.box(12, 0, 1, 15, 2, 4),
            Block.box(12, 12, 1, 15, 14, 4),
            Block.box(-15, 12, 1, -12, 14, 4),
            Block.box(12, 12, 12, 15, 14, 15),
            Block.box(-15, 12, 12, -12, 14, 15),
            Block.box(12.5, 2, 1.5, 14.5, 12, 3.5),
            Block.box(-14.5, 2, 1.5, -12.5, 12, 3.5),
            Block.box(12.5, 2, 12.5, 14.5, 12, 14.5),
            Block.box(-14.5, 2, 12.5, -12.5, 12, 14.5),
            Block.box(-16, 14, 0, 16, 16, 16)
    );
    // endregion

    // region: Bench
    VoxelShape BENCH = VoxelShapeHelper.combine(
            Block.box(12, 0, 1, 15, 2, 4),
            Block.box(-15, 0, 1, -12, 2, 4),
            Block.box(-15, 0, 12, -12, 2, 15),
            Block.box(12, 0, 12, 15, 2, 15),
            Block.box(-14.5, 2, 1.5, -12.5, 5, 3.5),
            Block.box(12.5, 2, 1.5, 14.5, 5, 3.5),
            Block.box(12.5, 2, 12.5, 14.5, 5, 14.5),
            Block.box(-14.5, 2, 12.5, -12.5, 5, 14.5),
            Block.box(-15.5, 4.5, -0.5, 15.5, 7.5, 16.5)
    );
    // endregion

    // region: Chair
    VoxelShape CHAIR = VoxelShapeHelper.combine(
            Block.box(1.5, 0, 1.5, 4.5, 3, 4.5),
            Block.box(1.5, 0, 11.5, 4.5, 3, 14.5),
            Block.box(11.5, 0, 11.5, 14.5, 3, 14.5),
            Block.box(11.5, 0, 1.5, 14.5, 3, 4.5),
            Block.box(2, 3, 12, 4, 7, 14),
            Block.box(2, 3, 2, 4, 7, 4),
            Block.box(12, 3, 2, 14, 7, 4),
            Block.box(12, 3, 12, 14, 7, 14),
            Block.box(1.5, 7, 1.5, 14.5, 9, 14.5),
            Block.box(2, 9, 12, 14, 26, 14)
    );
    // endregion

    // region: Ceiling Light
    VoxelShape CEILING_LIGHT = Block.box(0, 0, 0, 16, 16, 16);
    // endregion

    // region: Cushion
    VoxelShape CUSHION = Block.box(4, 0, 4, 12, 7, 12);
    // endregion

    // region: Stool
    VoxelShape STOOL = VoxelShapeHelper.combine(
            Block.box(1, 0, 1, 4, 2, 4),
            Block.box(1, 0, 12, 4, 2, 15),
            Block.box(12, 0, 12, 15, 2, 15),
            Block.box(12, 0, 1, 15, 2, 4),
            Block.box(1.5, 2, 12.5, 3.5, 5, 14.5),
            Block.box(1.5, 2, 1.5, 3.5, 5, 3.5),
            Block.box(12.5, 2, 12.5, 14.5, 5, 14.5),
            Block.box(12.5, 2, 1.5, 14.5, 5, 3.5),
            Block.box(1, 5, 1, 15, 7, 15)
    );
    // endregion

    // region: Chest
    VoxelShape CHEST = VoxelShapeHelper.combine(
            Block.box(11, 0, 2, 14, 2, 5),
            Block.box(-14, 0, 2, -11, 2, 5),
            Block.box(-14, 0, 11, -11, 2, 14),
            Block.box(11, 0, 11, 14, 2, 14),
            Block.box(11.5, 2, 11.5, 13.5, 11, 13.5),
            Block.box(11.5, 2, 2.5, 13.5, 11, 4.5),
            Block.box(-13.5, 2, 2.5, -11.5, 11, 4.5),
            Block.box(-13.5, 2, 11.5, -11.5, 11, 13.5),
            Block.box(-14, 11, 2, 14, 13, 14),
            Block.box(-14, 3, 2, 14, 5, 14),
            Block.box(-13, 5, 3, 13, 11, 13)
    );
    // endregion

    // region: Bookshelf
    VoxelShape BOOKSHELF = VoxelShapeHelper.combine(
            Block.box(11, 0, 1, 15, 2, 5),
            Block.box(-15, 0, 1, -11, 2, 5),
            Block.box(-15, 0, 11, -11, 2, 15),
            Block.box(11, 0, 11, 15, 2, 15),
            Block.box(-15, 3, 1, 15, 5, 15),
            Block.box(-15, 30, 1, 15, 32, 15),
            Block.box(-14, 5, 2, 14, 30, 14),
            Block.box(12, 2, 2, 14, 3, 4),
            Block.box(12, 2, 12, 14, 3, 14),
            Block.box(-14, 2, 12, -12, 3, 14),
            Block.box(-14, 2, 2, -12, 3, 4)
    );
    // endregion

    // region: Desk Left
    VoxelShape DESK_LEFT = VoxelShapeHelper.combine(
            Block.box(-15, 0, 1, -12, 2, 4),
            Block.box(-15, 0, 12, -12, 2, 15),
            Block.box(12, 0, 12, 15, 2, 15),
            Block.box(12, 0, 1, 15, 2, 4),
            Block.box(12, 12, 1, 15, 14, 4),
            Block.box(-15, 12, 1, -12, 14, 4),
            Block.box(12, 12, 12, 15, 14, 15),
            Block.box(-15, 12, 12, -12, 14, 15),
            Block.box(12.5, 2, 1.5, 14.5, 12, 3.5),
            Block.box(-14.5, 2, 1.5, -12.5, 12, 3.5),
            Block.box(12.5, 2, 12.5, 14.5, 12, 14.5),
            Block.box(-14.5, 2, 12.5, -12.5, 12, 14.5),
            Block.box(-16, 14, 0, 16, 16, 16),
            Block.box(5, 9, 3, 12, 14, 13)
    );
    // endregion

    // region: Desk Right
    VoxelShape DESK_RIGHT = VoxelShapeHelper.combine(
            Block.box(-15, 0, 1, -12, 2, 4),
            Block.box(-15, 0, 12, -12, 2, 15),
            Block.box(12, 0, 12, 15, 2, 15),
            Block.box(12, 0, 1, 15, 2, 4),
            Block.box(12, 12, 1, 15, 14, 4),
            Block.box(-15, 12, 1, -12, 14, 4),
            Block.box(12, 12, 12, 15, 14, 15),
            Block.box(-15, 12, 12, -12, 14, 15),
            Block.box(12.5, 2, 1.5, 14.5, 12, 3.5),
            Block.box(-14.5, 2, 1.5, -12.5, 12, 3.5),
            Block.box(12.5, 2, 12.5, 14.5, 12, 14.5),
            Block.box(-14.5, 2, 12.5, -12.5, 12, 14.5),
            Block.box(-16, 14, 0, 16, 16, 16),
            Block.box(-12, 9, 3, -5, 14, 13)
    );
    // endregion

    // region: Drawer
    VoxelShape DRAWER = VoxelShapeHelper.combine(
            Block.box(1, 0, 1, 4, 2, 4),
            Block.box(1, 12, 1, 4, 14, 4),
            Block.box(1.5, 1, 1.5, 3.5, 12, 3.5),
            Block.box(1, 0, 12, 4, 2, 15),
            Block.box(1, 12, 12, 4, 14, 15),
            Block.box(1.5, 1, 12.5, 3.5, 12, 14.5),
            Block.box(12, 0, 12, 15, 2, 15),
            Block.box(12, 12, 12, 15, 14, 15),
            Block.box(12.5, 1, 12.5, 14.5, 12, 14.5),
            Block.box(12, 0, 1, 15, 2, 4),
            Block.box(12, 12, 1, 15, 14, 4),
            Block.box(12.5, 1, 1.5, 14.5, 12, 3.5),
            Block.box(1.5, 6, 1.5, 14.5, 14, 14.5),
            Block.box(0, 14, 0, 16, 16, 16)
    );
    // endregion

    // region: Dresser
    VoxelShape DRESSER = VoxelShapeHelper.combine(
            Block.box(-15, 0, 1, -12, 2, 4),
            Block.box(-15, 12, 1, -12, 14, 4),
            Block.box(-14.5, 1, 1.5, -12.5, 12, 3.5),
            Block.box(-15, 0, 12, -12, 2, 15),
            Block.box(-15, 12, 12, -12, 14, 15),
            Block.box(-14.5, 1, 12.5, -12.5, 12, 14.5),
            Block.box(12, 0, 12, 15, 2, 15),
            Block.box(12, 12, 12, 15, 14, 15),
            Block.box(12.5, 1, 12.5, 14.5, 12, 14.5),
            Block.box(12, 0, 1, 15, 2, 4),
            Block.box(12, 12, 1, 15, 14, 4),
            Block.box(12.5, 1, 1.5, 14.5, 12, 3.5),
            Block.box(-14.5, 6, 1.5, 14.5, 14, 14.5),
            Block.box(-16, 14, 0, 16, 16, 16)
    );
    // endregion

    // region: Lockbox
    VoxelShape LOCKBOX = VoxelShapeHelper.combine(
            Block.box(2, 0, 4, 5, 2, 7),
            Block.box(2, 0, 9, 5, 2, 12),
            Block.box(11, 0, 9, 14, 2, 12),
            Block.box(11, 0, 4, 14, 2, 7),
            Block.box(1, 2, 3, 15, 4, 13),
            Block.box(1.5, 4, 3.5, 14.5, 12.25, 12.5)
    );
    // endregion

    // region: Wardrobe Top
    VoxelShape WARDROBE_TOP = VoxelShapeHelper.combine(
            Block.box(11, 10, 1, 15, 12, 5),
            Block.box(-15, 10, 1, -11, 12, 5),
            Block.box(-15, 10, 11, -11, 12, 15),
            Block.box(11, 10, 11, 15, 12, 15),
            Block.box(11, 7, 11, 15, 9, 15),
            Block.box(11, 7, 1, 15, 9, 5),
            Block.box(-15, 7, 1, -11, 9, 5),
            Block.box(-15, 7, 11, -11, 9, 15),
            Block.box(12, 0, 12, 14, 10, 14),
            Block.box(12, 0, 2, 14, 10, 4),
            Block.box(-14, 0, 2, -12, 10, 4),
            Block.box(-14, 0, 12, -12, 10, 14),
            Block.box(-14, 0, 2, 14, 10.5, 14)
    );
    // endregion

    // region: Wardrobe Bottom
    VoxelShape WARDROBE_BOTTOM = VoxelShapeHelper.combine(
            Block.box(11, 0, 1, 15, 2, 5),
            Block.box(-15, 0, 1, -11, 2, 5),
            Block.box(-15, 0, 11, -11, 2, 15),
            Block.box(11, 0, 11, 15, 2, 15),
            Block.box(12, 2, 12, 14, 30, 14),
            Block.box(-14, 2, 12, -12, 30, 14),
            Block.box(-14, 2, 2, -12, 30, 4),
            Block.box(12, 2, 2, 14, 30, 4),
            Block.box(-15, 3, 1, 15, 5, 15),
            Block.box(-15, 30, 1, 15, 32, 15),
            Block.box(-14, 5, 2, 14, 30, 14)
    );
    // endregion

    // region: Painting Wide
    VoxelShape PAINTING_WIDE = VoxelShapeHelper.combine(
            Block.box(-16, 0, 13, -13, 3, 16),
            Block.box(13, 0, 13, 16, 3, 16),
            Block.box(13, 13, 13, 16, 16, 16),
            Block.box(-16, 13, 13, -13, 16, 16),
            Block.box(-13, 13, 13.5, 13, 15.5, 15.5),
            Block.box(-13, 0.5, 13.5, 13, 3, 15.5),
            Block.box(-15.5, 3, 13.5, 15.5, 13, 15.5)
    );
    // endregion

    // region: Painting Small
    VoxelShape PAINTING_SMALL = VoxelShapeHelper.combine(
            Block.box(0, 0, 13, 3, 3, 16),
            Block.box(13, 0, 13, 16, 3, 16),
            Block.box(13, 13, 13, 16, 16, 16),
            Block.box(0, 13, 13, 3, 16, 16),
            Block.box(3, 13, 13.5, 13, 15.5, 15.5),
            Block.box(3, 0.5, 13.5, 13, 3, 15.5),
            Block.box(0.5, 3, 13.5, 15.5, 13, 15.5)
    );
    // endregion

    // region: Oven
    VoxelShape OVEN = VoxelShapeHelper.combine(
            Block.box(0, 0, 0, 3, 2, 3),
            Block.box(0, 0, 13, 3, 2, 16),
            Block.box(13, 0, 13, 16, 2, 16),
            Block.box(13, 0, 0, 16, 2, 3),
            Block.box(13, 12, 0, 16, 14, 3),
            Block.box(13, 12, 13, 16, 14, 16),
            Block.box(0, 12, 13, 3, 14, 16),
            Block.box(0, 12, 0, 3, 14, 3),
            Block.box(0.5, 0.5, 0.5, 15.5, 14, 15.5),
            Block.box(0, 14, 0, 16, 16, 16)
    );
    // endregion

    // region: Door Double
    VoxelShape DOOR_DOUBLE = VoxelShapeHelper.combine(
            Block.box(0, 0, 0, 16, 3, 3),
            Block.box(0, 29, 0, 16, 32, 3),
            Block.box(14, 2, 0.5, 16, 29, 2.5),
            Block.box(10.5, 2, 0.5, 12.5, 29, 2.5),
            Block.box(7, 2, 0.5, 9, 29, 2.5),
            Block.box(3.5, 2, 0.5, 5.5, 29, 2.5),
            Block.box(0, 2, 0.5, 2, 29, 2.5)
    );
    // endregion

    // region: Door Single
    VoxelShape DOOR_SINGLE = VoxelShapeHelper.combine(
            Block.box(0, 0, 0, 16, 3, 3),
            Block.box(0, 29, 0, 16, 32, 3),
            Block.box(14, 2, 0.5, 16, 29, 2.5),
            Block.box(10.5, 2, 0.5, 12.5, 29, 2.5),
            Block.box(7, 2, 0.5, 9, 29, 2.5),
            Block.box(3.5, 2, 0.5, 5.5, 29, 2.5),
            Block.box(0, 2, 0.5, 2, 29, 2.5)
    );
    // endregion

    // region: Bed Single
    VoxelShape BED_SINGLE = VoxelShapeHelper.combine(
            Block.box(0, 0, 0, 4, 2, 4),
            Block.box(0, 0, 28, 4, 2, 32),
            Block.box(12, 0, 28, 16, 2, 32),
            Block.box(12, 0, 0, 16, 2, 4),
            Block.box(12, 12, 28, 16, 14, 32),
            Block.box(0, 12, 28, 4, 14, 32),
            Block.box(0, 12, 0, 4, 14, 4),
            Block.box(12, 12, 0, 16, 14, 4),
            Block.box(13, 2, 29, 15, 12, 31),
            Block.box(1, 2, 29, 3, 12, 31),
            Block.box(1, 2, 1, 3, 12, 3),
            Block.box(13, 2, 1, 15, 12, 3),
            Block.box(4, 4, 1, 12, 13, 3),
            Block.box(4, 4, 29, 12, 13, 31),
            Block.box(1, 4, 3, 15, 8, 29)
    );
    // endregion

    // region: Bed Double
    VoxelShape BED_DOUBLE = VoxelShapeHelper.combine(
            Block.box(-16, 0, 0, -12, 2, 4),
            Block.box(-16, 0, 28, -12, 2, 32),
            Block.box(12, 0, 28, 16, 2, 32),
            Block.box(12, 0, 0, 16, 2, 4),
            Block.box(12, 12, 28, 16, 14, 32),
            Block.box(-16, 12, 28, -12, 14, 32),
            Block.box(12, 12, 0, 16, 14, 4),
            Block.box(-16, 12, 0, -12, 14, 4),
            Block.box(13, 2, 29, 15, 12, 31),
            Block.box(-15, 2, 29, -13, 12, 31),
            Block.box(-15, 2, 1, -13, 12, 3),
            Block.box(13, 2, 1, 15, 12, 3),
            Block.box(-13, 4, 1, 13, 13, 3),
            Block.box(-13, 4, 29, 13, 13, 31),
            Block.box(-15, 4, 3, 15, 8, 29)
    );
    // endregion

    // region: Shelf Single
    VoxelShape SHELF_SINGLE = VoxelShapeHelper.combine(
            Block.box(0, 6, 13, 3, 8, 16),
            Block.box(13, 6, 13, 16, 8, 16),
            Block.box(0.5, 8, 14, 2.5, 14, 16),
            Block.box(13.5, 8, 14, 15.5, 14, 16),
            Block.box(0, 14, 0, 16, 16, 16)
    );
    // endregion

    // region: Shelf Center
    VoxelShape SHELF_CENTER = Block.box(0, 14, 0, 16, 16, 16);
    // endregion

    // region: Shelf Left
    VoxelShape SHELF_LEFT = VoxelShapeHelper.combine(
            Block.box(13, 6, 13, 16, 8, 16),
            Block.box(13.5, 8, 14, 15.5, 14, 16),
            Block.box(0, 14, 0, 16, 16, 16)
    );
    // endregion

    // region: Shelf Right
    VoxelShape SHELF_RIGHT = VoxelShapeHelper.combine(
            Block.box(0, 6, 13, 3, 8, 16),
            Block.box(0.5, 8, 14, 2.5, 15, 16),
            Block.box(0, 14, 0, 16, 16, 16)
    );
    // endregion

    // region: Sofa Single
    VoxelShape SOFA_SINGLE = VoxelShapeHelper.combine(
            Block.box(1.5, 0, 1, 4.5, 2, 4),
            Block.box(1.5, 0, 12, 4.5, 2, 15),
            Block.box(11.5, 0, 12, 14.5, 2, 15),
            Block.box(11.5, 0, 1, 14.5, 2, 4),
            Block.box(12, 2, 1.5, 14, 4, 3.5),
            Block.box(2, 2, 1.5, 4, 4, 3.5),
            Block.box(2, 2, 12.5, 4, 4, 14.5),
            Block.box(12, 2, 12.5, 14, 4, 14.5),
            Block.box(12, 3, 3.5, 14, 4, 12.5),
            Block.box(2, 3, 3.5, 4, 4, 12.5),
            Block.box(2, 4, 1.5, 14, 6, 14.5),
            Block.box(2, 6, 12.5, 14, 15, 14.5),
            Block.box(14, 3.5, 12, 16, 15.5, 15),
            Block.box(14, 6, 2, 16, 11.5, 12),
            Block.box(14, 3.5, 1, 16, 6.5, 12),
            Block.box(0, 3.5, 1, 2, 6.5, 12),
            Block.box(0, 3.5, 12, 2, 15.5, 15),
            Block.box(0, 6, 2, 2, 11.5, 12)
    );
    // endregion

    // region: Sofa Center
    VoxelShape SOFA_CENTER = VoxelShapeHelper.combine(
            Block.box(0, 4, 1.5, 16, 6, 14.5),
            Block.box(0, 6, 12.5, 16, 15, 14.5)
    );
    // endregion

    // region: Sofa Left
    VoxelShape SOFA_LEFT = VoxelShapeHelper.combine(
            Block.box(0, 4, 1.5, 14, 6, 14.5),
            Block.box(0, 6, 12.5, 14, 15, 14.5),
            Block.box(14, 3.5, 12, 16, 15.5, 15),
            Block.box(14, 3.5, 2, 16, 11.5, 12),
            Block.box(14, 3.5, 1, 16, 6.5, 2),
            Block.box(12, 2, 1.5, 14, 4, 3.5),
            Block.box(12, 2, 12.5, 14, 4, 14.5),
            Block.box(12, 3, 3.5, 14, 4, 12.5),
            Block.box(11.5, 0, 1, 14.5, 2, 4),
            Block.box(11.5, 0, 12, 14.5, 2, 15)
    );
    // endregion

    // region: Sofa Right
    VoxelShape SOFA_RIGHT = VoxelShapeHelper.combine(
            Block.box(2, 4, 1.5, 16, 6, 14.5),
            Block.box(2, 6, 12.5, 16, 15, 14.5),
            Block.box(0, 3.5, 12, 2, 15.5, 15),
            Block.box(0, 3.5, 2, 2, 11.5, 12),
            Block.box(0, 3.5, 1, 2, 6.5, 2),
            Block.box(2, 2, 1.5, 4, 4, 3.5),
            Block.box(2, 2, 12.5, 4, 4, 14.5),
            Block.box(2, 3, 3.5, 4, 4, 12.5),
            Block.box(1.5, 0, 1, 4.5, 2, 4),
            Block.box(1.5, 0, 12, 4.5, 2, 15)
    );
    // endregion

    // region: Sofa Corner
    VoxelShape SOFA_CORNER = VoxelShapeHelper.combine(
            Block.box(1, 0, 1, 4, 2, 4),
            Block.box(1, 0, 11.5, 4, 2, 14.5),
            Block.box(11.5, 0, 11.5, 14.5, 2, 14.5),
            Block.box(11.5, 0, 1, 14.5, 2, 4),
            Block.box(12, 2, 1.5, 14, 4, 3.5),
            Block.box(1.5, 2, 1.5, 3.5, 4, 3.5),
            Block.box(1.5, 2, 12, 3.5, 4, 14),
            Block.box(12, 2, 12, 14, 4, 14),
            Block.box(0, 4, 1.5, 1.5, 6, 14.5),
            Block.box(1.5, 4, 0, 14.5, 6, 14.5),
            Block.box(12.5, 6, 0, 14.5, 15, 14.5),
            Block.box(12, 4, 12, 15, 15.5, 15),
            Block.box(0, 6, 12.5, 12, 15, 14.5)
    );
    // endregion

    // region: Counter Single
    VoxelShape COUNTER_SINGLE = VoxelShapeHelper.combine(
            Block.box(0, 0, 3, 16, 13, 16),
            Block.box(0, 13, 0, 16, 16, 16),
            Block.box(1, 2, 2, 15, 11, 3)
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
