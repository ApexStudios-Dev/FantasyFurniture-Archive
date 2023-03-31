package xyz.apex.minecraft.fantasyfurniture.venthyr.common;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeHelper;

public interface VenthyrVoxelShapes
{
    // region: Wall Light
    VoxelShape WALL_LIGHT = VoxelShapeHelper.combine(
            Block.box(6D, 1D, 15D, 10D, 3D, 16D),
            Block.box(5D, 3D, 15D, 11D, 12D, 16D),
            Block.box(6D, 12D, 15D, 10D, 14D, 16D),
            Block.box(7D, 3.5D, 14D, 9D, 5.5D, 15D),
            Block.box(4.25D, 2.5D, 10.5D, 11.75D, 11.5D, 14D)
    );
    // endregion

    // region: Floor Light
    VoxelShape FLOOR_LIGHT = VoxelShapeHelper.combine(
            Block.box(6D, 0D, 6D, 10D, 2D, 10D),
            Block.box(7D, 2D, 7D, 9D, 20D, 9D),
            Block.box(4D, 17.75D, 7D, 7D, 21.75D, 9D),
            Block.box(9D, 17.75D, 7D, 12D, 21.75D, 9D),
            Block.box(7D, 17.75D, 4D, 9D, 21.75D, 7D),
            Block.box(7D, 17.75D, 9D, 9D, 21.75D, 12D),
            Block.box(2.5D, 20.75D, 2.5D, 13.5D, 24D, 13.5D),
            Block.box(10.25D, 24D, 10.25D, 12.5D, 28.75D, 12.5D),
            Block.box(3.5D, 24D, 10.25D, 5.75D, 28.75D, 12.5D),
            Block.box(3.5D, 24D, 3.5D, 5.75D, 28.75D, 5.75D),
            Block.box(10.25D, 24D, 3.5D, 12.5D, 28.75D, 5.75D)
    );
    // endregion

    // region: Table Large
    VoxelShape TABLE_LARGE = VoxelShapeHelper.combine(
            Block.box(-15D, 0D, 1D, -12D, 2D, 4D),
            Block.box(12D, 0D, 1D, 15D, 2D, 4D),
            Block.box(12D, 0D, 28D, 15D, 2D, 31D),
            Block.box(-15D, 0D, 28D, -12D, 2D, 31D),
            Block.box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
            Block.box(-14.5D, 2D, 28.5D, -12.5D, 13D, 30.5D),
            Block.box(12.5D, 2D, 28.5D, 14.5D, 13D, 30.5D),
            Block.box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
            Block.box(-16D, 13D, 0D, 16D, 16D, 32D)
    );
    // endregion

    // region: Table Small
    VoxelShape TABLE_SMALL = VoxelShapeHelper.combine(
            Block.box(1D, 0D, 1D, 4D, 2D, 4D),
            Block.box(1D, 0D, 12D, 4D, 2D, 15D),
            Block.box(12D, 0D, 12D, 15D, 2D, 15D),
            Block.box(12D, 0D, 1D, 15D, 2D, 4D),
            Block.box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
            Block.box(1.5D, 2D, 1.5D, 3.5D, 13D, 3.5D),
            Block.box(1.5D, 2D, 12.5D, 3.5D, 13, 14.5D),
            Block.box(12.5D, 2D, 12.5D, 14.5D, 13D, 14.5D),
            Block.box(0D, 13D, 0D, 16D, 16D, 16D)
    );
    // endregion

    // region: Table Wide
    VoxelShape TABLE_WIDE = VoxelShapeHelper.combine(
            Block.box(12D, 0D, 1D, 15D, 2D, 4D),
            Block.box(-15D, 0D, 1D, -12D, 2D, 4D),
            Block.box(-15D, 0D, 12D, -12D, 2D, 15D),
            Block.box(12D, 0D, 12D, 15D, 2D, 15D),
            Block.box(12.5D, 2D, 12.5D, 14.5D, 13D, 14.5D),
            Block.box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
            Block.box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
            Block.box(-14.5D, 2D, 12.5D, -12.5D, 13D, 14.5D),
            Block.box(-16D, 13D, 0D, 16D, 16D, 16D)
    );
    // endregion

    // region: Bench
    VoxelShape BENCH = VoxelShapeHelper.combine(
            Block.box(-14D, 0D, 2D, -11D, 4D, 5D),
            Block.box(-14D, 0D, 11D, -11D, 4D, 14D),
            Block.box(11D, 0D, 11D, 14D, 4D, 14D),
            Block.box(11D, 0D, 2D, 14D, 4D, 5D),
            Block.box(-15D, 4D, 1D, 15D, 7D, 15D)
    );
    // endregion

    // region: Chair
    VoxelShape CHAIR = VoxelShapeHelper.combine(
            Block.box(1D, 0D, 1D, 4D, 5D, 4D),
            Block.box(12D, 0D, 1D, 15D, 5D, 4D),
            Block.box(12D, 0D, 12D, 15D, 5D, 15D),
            Block.box(1D, 0D, 12D, 4D, 5D, 15D),
            Block.box(.5D, 5D, .5D, 15.5D, 9D, 15.5D),
            Block.box(1D, 9D, 12D, 15D, 31D, 15D)
    );
    // endregion

    // region: Ceiling Light
    VoxelShape CEILING_LIGHT = Block.box(1D, 0D, 1D, 15, 16D, 15D);
    // endregion

    // region: Cushion
    VoxelShape CUSHION = VoxelShapeHelper.combine(
            Block.box(2D, 0D, 2D, 5D, 3D, 5D),
            Block.box(2D, 0D, 11D, 5D, 3D, 14D),
            Block.box(11D, 0D, 11D, 14D, 3D, 14D),
            Block.box(11D, 0D, 2D, 14D, 3D, 5D),
            Block.box(1D, 3D, 1D, 15D, 4D, 15D),
            Block.box(2D, 4D, 2D, 14D, 7D, 14D)
    );
    // endregion

    // region: Stool
    VoxelShape STOOL = VoxelShapeHelper.combine(
            Block.box(2D, 0D, 2D, 5D, 4D, 5D),
            Block.box(2D, 0D, 11D, 5D, 4D, 14D),
            Block.box(11D, 0D, 11D, 14D, 4D, 14D),
            Block.box(11D, 0D, 2D, 14D, 4D, 5D),
            Block.box(1D, 4D, 1D, 15D, 7D, 15D)
    );
    // endregion

    // region: Chest
    VoxelShape CHEST = Block.box(-13D, 0D, 1D, 13D, 14.25D, 15D);
    // endregion

    // region: Bookshelf
    VoxelShape BOOKSHELF = VoxelShapeHelper.combine(
            Block.box(-16D, 0D, 2D, 16D, 3D, 16D),
            Block.box(-15D, 3D, 3D, 15D, 29D, 16D),
            Block.box(-16D, 29D, 2D, 16D, 32D, 16D)
    );
    // endregion

    // region: Desk Left
    VoxelShape DESK_LEFT = VoxelShapeHelper.combine(
            Block.box(-15D, 0D, 1D, -12D, 2D, 4D),
            Block.box(-15D, 0D, 12D, -12D, 2D, 15D),
            Block.box(12D, 0D, 12D, 15, 2D, 15D),
            Block.box(12D, 0D, 1D, 15, 2D, 4D),
            Block.box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
            Block.box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
            Block.box(-14.5D, 2D, 12.5D, -12.5D, 13D, 14.5D),
            Block.box(12.5D, 2D, 12.5D, 14.5D, 13, 14.5D),
            Block.box(-16D, 13D, 0D, 16D, 16D, 16D),
            Block.box(5D, 9D, 2, 12D, 13D, 11D),
            Block.box(12.5D, 9D, 3.5D, 14.5D, 13D, 12.5D),
            Block.box(-14.5D, 9D, 3.5D, -12.5D, 13D, 12.5D)
    );
    // endregion

    // region: Desk Right
    VoxelShape DESK_RIGHT = VoxelShapeHelper.combine(
            Block.box(-15D, 0D, 1D, -12D, 2D, 4D),
            Block.box(-15D, 0D, 12D, -12D, 2D, 15D),
            Block.box(12D, 0D, 12D, 15D, 2D, 15D),
            Block.box(12D, 0D, 1D, 15D, 2D, 4D),
            Block.box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
            Block.box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
            Block.box(-14.5D, 2D, 12.5D, -12.5D, 13D, 14.5D),
            Block.box(12.5D, 2D, 12.5D, 14.5D, 13D, 14.5D),
            Block.box(-16D, 13D, 0D, 16D, 16D, 16D),
            Block.box(-12D, 9D, 2D, -5D, 13D, 11D),
            Block.box(12.5D, 9D, 3.5D, 14.5D, 13D, 12.5D),
            Block.box(-14.5D, 9D, 3.5D, -12.5D, 13D, 12.5D)
    );
    // endregion

    // region: Drawer
    VoxelShape DRAWER = VoxelShapeHelper.combine(
            Block.box(.5D, 0D, .5D, 3.5D, 2D, 3.5D),
            Block.box(.5D, 0D, 12.5D, 3.5D, 2D, 15.5D),
            Block.box(12.5D, 0D, 12.5D, 15.5D, 2D, 15.5D),
            Block.box(12.5D, 0D, .5D, 15.5D, 2D, 3.5D),
            Block.box(13D, 2D, 1D, 15D, 13D, 3D),
            Block.box(1D, 2D, 1D, 3D, 13D, 3D),
            Block.box(1D, 2D, 13D, 3D, 13D, 15D),
            Block.box(13D, 2D, 13D, 15D, 13D, 15D),
            Block.box(1D, 5D, 1D, 15D, 13D, 15D),
            Block.box(0D, 13D, 0D, 16D, 16D, 16D)
    );
    // endregion

    // region: Dresser
    VoxelShape DRESSER = VoxelShapeHelper.combine(
            Block.box(-15.5D, 0D, .5D, -12.5D, 2D, 3.5D),
            Block.box(-15.5D, 0D, 12.5D, -12.5D, 2D, 15.5D),
            Block.box(12.5D, 0D, 12.5D, 15.5D, 2D, 15.5D),
            Block.box(12.5D, 0D, .5D, 15.5D, 2D, 3.5D),
            Block.box(13D, 2D, 1D, 15D, 13D, 3D),
            Block.box(-15D, 2D, 1D, -13D, 13D, 3D),
            Block.box(-15D, 2D, 13D, -13D, 13D, 15D),
            Block.box(13D, 2D, 13D, 15D, 13D, 15D),
            Block.box(-15D, 5D, 1D, 15D, 13D, 15D),
            Block.box(-16D, 13D, 0D, 16D, 16D, 16D)
    );
    // endregion

    // region: Lockbox
    VoxelShape LOCKBOX = VoxelShapeHelper.combine(
            Block.box(2, 1, 4, 14, 10, 12),
            Block.box(1.5, 6, 3.5, 14.5, 7, 12.5),
            Block.box(1.5, 1, 3.5, 14.5, 2, 12.5),
            Block.box(7, 4, 3.5, 9, 6, 4.25),
            Block.box(12, 0, 4, 14, 1, 6),
            Block.box(2, 0, 4, 4, 1, 6),
            Block.box(2, 0, 10, 4, 1, 12),
            Block.box(12, 0, 10, 14, 1, 12)
    );
    // endregion

    // region: Wardrobe Top
    VoxelShape WARDROBE_TOP = VoxelShapeHelper.combine(
            Block.box(-15D, 0D, 1D, 15D, 9D, 15D),
            Block.box(-16D, 9D, 0D, 16D, 11D, 16D)
    );
    // endregion

    // region: Wardrobe Bottom
    VoxelShape WARDROBE_BOTTOM = VoxelShapeHelper.combine(
            Block.box(-16D, 0D, 0D, -12D, 3D, 4D),
            Block.box(-16D, 0D, 12D, -12D, 3D, 16D),
            Block.box(12D, 0D, 12D, 16D, 3D, 16D),
            Block.box(12D, 0D, 0D, 16D, 3D, 4D),
            Block.box(-15D, 1D, 1D, 15D, 29D, 15D),
            Block.box(-16D, 29D, 0D, 16D, 32D, 16D)
    );
    // endregion

    // region: Painting Wide
    VoxelShape PAINTING_WIDE = Block.box(-16D, 0D, 14D, 16D, 16D, 16D);
    // endregion

    // region: Painting Small
    VoxelShape PAINTING_SMALL = Block.box(0D, 0D, 14D, 16D, 16D, 16D);
    // endregion

    // region: Oven
    VoxelShape OVEN = VoxelShapeHelper.combine(
            Block.box(1, 0, 1, 4, 2, 4),
            Block.box(1, 0, 12, 4, 2, 15),
            Block.box(12, 0, 12, 15, 2, 15),
            Block.box(12, 0, 1, 15, 2, 4),
            Block.box(0, 2, 0, 16, 4, 16),
            Block.box(0, 14, 0, 16, 16, 16),
            Block.box(1, 4, 1, 15, 14, 15),
            Block.box(3, 5, 0, 13, 13, 1)
    );
    // endregion

    // region: Door Double
    VoxelShape DOOR_DOUBLE = VoxelShapeHelper.combine(
            Block.box(0, 0, 0, 16, 1, 3),
            Block.box(14, 1, 0, 16, 32, 3),
            Block.box(0, 31, 0, 14, 32, 3),
            Block.box(0, 0, 0, 1, 31, 3),
            Block.box(6, 30, 0, 14, 31, 3),
            Block.box(8, 29, 0, 14, 30, 3),
            Block.box(10, 27, 0, 14, 29, 3),
            Block.box(11, 25, 0, 14, 27, 3),
            Block.box(12, 22, 0, 14, 25, 3),
            Block.box(13, 17, 0, 14, 22, 3),
            Block.box(1, 1, 0.5, 14, 31, 2.5)
    );
    // endregion

    // region: Door Single
    VoxelShape DOOR_SINGLE = VoxelShapeHelper.combine(
            Block.box(0, 0, 0, 16, 1, 3),
            Block.box(14, 1, 0, 16, 32, 3),
            Block.box(0, 30, 0, 14, 32, 3),
            Block.box(0, 1, 0, 1, 30, 3),
            Block.box(1, 1, 0.5, 14, 30, 2.5)
    );
    // endregion

    // region: Bed Single
    VoxelShape BED_SINGLE = VoxelShapeHelper.combine(
            Block.box(0D, 0D, 29D, 3D, 2D, 32D),
            Block.box(13D, 0D, 29D, 16D, 2D, 32D),
            Block.box(13D, 0D, 0D, 16D, 2D, 3D),
            Block.box(0D, 0D, 0D, 3D, 2D, 3D),
            Block.box(.5D, 2D, .5D, 2.5D, 12D, 2.5D),
            Block.box(.5D, 2D, 29.5D, 2.5D, 12D, 31.5D),
            Block.box(13.5D, 2D, 29.5D, 15.5D, 12D, 31.5D),
            Block.box(13.5D, 2D, .5D, 15.5D, 12D, 2.5D),
            Block.box(13D, 12D, 0D, 16D, 14D, 3D),
            Block.box(0D, 12D, 0D, 3D, 14D, 3D),
            Block.box(0D, 12D, 29D, 3D, 14D, 32D),
            Block.box(13D, 12D, 29D, 16D, 14D, 32D),
            Block.box(.5D, 0D, 2D, 15.5D, 5D, 30D),
            Block.box(2.5D, 0D, 29.5D, 13.5D, 11D, 31.5D),
            Block.box(2.5D, 0D, .5D, 13.5D, 13D, 2.5D),
            Block.box(1.5D, 5D, 2.5D, 14.5D, 8D, 29.5D)
    );
    // endregion

    // region: Bed Double
    VoxelShape BED_DOUBLE = VoxelShapeHelper.combine(
            Block.box(-16D, 0D, 0D, -13D, 2D, 3D),
            Block.box(-15.5D, 2D, .5D, -13.5D, 12D, 2.5D),
            Block.box(-15.5D, 2D, 29.5D, -13.5D, 12D, 31.5D),
            Block.box(13.5D, 2D, 29.5D, 15.5D, 12D, 31.5D),
            Block.box(13.5D, 2D, .5D, 15.5D, 12D, 2.5D),
            Block.box(-16D, 12D, 0D, -13D, 14D, 3D),
            Block.box(-16D, 12D, 29D, -13D, 14D, 32D),
            Block.box(13D, 12D, 29D, 16D, 14D, 32D),
            Block.box(13D, 12D, 0D, 16D, 14D, 3D),
            Block.box(13D, 0D, 0D, 16D, 2D, 3D),
            Block.box(13D, 0D, 29D, 16D, 2D, 32D),
            Block.box(-16D, 0D, 29D, -13D, 2D, 32D),
            Block.box(-15.5D, 0D, 2.5D, 15.5D, 5D, 29.5D),
            Block.box(-13.5D, 0D, 29.5D, 13.5D, 12D, 31.5D),
            Block.box(-13.5D, 0D, .5D, 13.5D, 14D, 2.5D),
            Block.box(-14.5D, 0D, 2.5D, 14.5D, 8D, 29.5D)
    );
    // endregion

    // region: Shelf Single
    VoxelShape SHELF_SINGLE = VoxelShapeHelper.combine(
            Block.box(0D, 13D, 0D, 16D, 16D, 16D),
            Block.box(13D, 9D, 10D, 16D, 13D, 16D),
            Block.box(13D, 11D, 3D, 16D, 13D, 10D),
            Block.box(13D, 10D, 0D, 16D, 13D, 3D),
            Block.box(13D, 6D, 13D, 16D, 9D, 16D),
            Block.box(13D, 3D, 12D, 16D, 6D, 16D),
            Block.box(0D, 3D, 12D, 3D, 6D, 16D),
            Block.box(0D, 9D, 10D, 3D, 13D, 16D),
            Block.box(0D, 11D, 3D, 3D, 13D, 10D),
            Block.box(0D, 10D, 0D, 3D, 13D, 3D),
            Block.box(0D, 6D, 13D, 3D, 9D, 16D)
    );
    // endregion

    // region: Shelf Center
    VoxelShape SHELF_CENTER = Block.box(0D, 13D, 0D, 16D, 16D, 16D);
    // endregion

    // region: Shelf Left
    VoxelShape SHELF_LEFT = VoxelShapeHelper.combine(
            Block.box(0D, 13D, 0D, 16D, 16D, 16D),
            Block.box(13D, 3D, 12D, 16D, 6D, 16D),
            Block.box(13D, 9D, 10D, 16D, 13D, 16D),
            Block.box(13D, 11D, 3D, 16D, 13D, 10D),
            Block.box(13D, 10D, 0D, 16D, 13D, 3D),
            Block.box(13D, 6D, 13D, 16D, 9D, 16D)
    );
    // endregion

    // region: Shelf Right
    VoxelShape SHELF_RIGHT = VoxelShapeHelper.combine(
            Block.box(0D, 3D, 12D, 3D, 6D, 16D),
            Block.box(0D, 6D, 13D, 3D, 9D, 16D),
            Block.box(0D, 9D, 10D, 3D, 13D, 16D),
            Block.box(0D, 11D, 3D, 3D, 13D, 10D),
            Block.box(0D, 10D, 0D, 3D, 13D, 3D),
            Block.box(0D, 13D, 0D, 16D, 16D, 16D)
    );
    // endregion

    // region: Sofa Single
    VoxelShape SOFA_SINGLE = VoxelShapeHelper.combine(
            Block.box(1D, 0D, 1D, 4D, 2D, 4D),
            Block.box(1D, 0D, 12D, 4D, 2D, 15D),
            Block.box(12D, 0D, 12D, 15D, 2D, 15D),
            Block.box(12D, 0D, 1D, 15D, 2D, 4D),
            Block.box(0D, 2D, 0D, 16D, 6D, 16D),
            Block.box(13D, 6D, 0D, 16D, 10D, 13D),
            Block.box(0D, 6D, 0D, 3D, 10D, 13D),
            Block.box(0D, 6D, 13D, 16D, 16D, 16D)
    );
    // endregion

    // region: Sofa Center
    VoxelShape SOFA_CENTER = VoxelShapeHelper.combine(
            Block.box(0, 2, 0, 16, 6, 16),
            Block.box(0, 6, 13, 16, 16, 16)
    );
    // endregion

    // region: Sofa Left
    VoxelShape SOFA_LEFT = VoxelShapeHelper.combine(
            Block.box(12, 0, 1, 15, 2, 4),
            Block.box(12, 0, 12, 15, 2, 15),
            Block.box(0, 2, 0, 16, 6, 16),
            Block.box(13, 6, 0, 16, 10, 13),
            Block.box(0, 6, 13, 16, 16, 16)
    );
    // endregion

    // region: Sofa Right
    VoxelShape SOFA_RIGHT = VoxelShapeHelper.combine(
            Block.box(1, 0, 1, 4, 2, 4),
            Block.box(1, 0, 12, 4, 2, 15),
            Block.box(0, 2, 13, 16, 16, 16),
            Block.box(0, 2, 0, 3, 10, 13),
            Block.box(3, 2, 0, 16, 6, 13)
    );
    // endregion

    // region: Sofa Corner
    VoxelShape SOFA_CORNER = VoxelShapeHelper.combine(
            Block.box(1D, 0D, 1D, 4D, 2D, 4D),
            Block.box(1D, 0D, 12D, 4D, 2D, 15D),
            Block.box(12D, 0D, 12D, 15D, 2D, 15D),
            Block.box(12D, 0D, 1D, 15D, 2D, 4D),
            Block.box(13D, 6D, 0D, 16D, 16D, 16D),
            Block.box(0D, 6D, 13D, 13D, 16D, 16D),
            Block.box(0D, 2D, 0D, 16D, 6D, 16D)
    );
    // endregion

    // region: Counter Single
    VoxelShape COUNTER_SINGLE = VoxelShapeHelper.combine(
            Block.box(0, 0, 3, 16, 13, 16),
            Block.box(0, 13, 0, 16, 16, 16),
            Block.box(1, 1, 2, 15, 12, 3)
    );
    // endregion

    // region: Counter Corner
    VoxelShape COUNTER_CORNER = VoxelShapeHelper.combine(
            Block.box(0, 0, 3, 16, 13, 16),
            Block.box(0, 13, 0, 16, 16, 16),
            Block.box(1, 1, 2, 15, 12, 3)
    );
    // endregion

    static void bootstrap() {}
}
