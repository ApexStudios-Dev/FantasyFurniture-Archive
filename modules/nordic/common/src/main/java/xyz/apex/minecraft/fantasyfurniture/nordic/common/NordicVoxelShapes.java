package xyz.apex.minecraft.fantasyfurniture.nordic.common;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeHelper;

public interface NordicVoxelShapes
{
    // region: Wall Light
    VoxelShape WALL_LIGHT = VoxelShapeHelper.combine(
            Block.box(6D, 5D, 15D, 10D, 11D, 16D),
            Block.box(6D, 2D, 8D, 10D, 15D, 15D)
    );
    // endregion

    // region: Floor Light
    VoxelShape FLOOR_LIGHT = VoxelShapeHelper.combine(
            Block.box(6D, 0D, 6D, 10D, 2D, 10D),
            Block.box(7D, 2D, 7D, 9D, 20D, 9D),
            Block.box(6.5D, 20.75D, 2.5D, 9.5D, 22.75D, 5.5D),
            Block.box(2.5D, 20.75D, 6.5D, 5.5D, 22.75D, 9.5D),
            Block.box(7.25D, 22.75D, 3.25D, 8.75D, 26.75D, 4.75D),
            Block.box(3.25D, 22.75D, 7.25D, 4.75D, 26.75D, 8.75D),
            Block.box(7.25D, 22.75D, 11.25D, 8.75D, 26.75D, 12.75D),
            Block.box(11.25D, 22.75D, 7.25D, 12.75D, 26.75D, 8.75D),
            Block.box(10.5D, 20.75D, 6.5D, 13.5D, 22.75D, 9.5D),
            Block.box(6.5D, 20.75D, 10.5D, 9.5D, 22.75D, 13.5D),
            Block.box(3D, 16.75D, 7D, 7D, 20.75, 9D),
            Block.box(9D, 16.75D, 7D, 13D, 20.75, 9D),
            Block.box(7D, 16.75D, 3D, 9D, 20.75, 7D),
            Block.box(7D, 16.75D, 9D, 9D, 20.75, 13D)
    );
    // endregion

    // region: Table Large
    VoxelShape TABLE_LARGE = VoxelShapeHelper.combine(
            Block.box(12D, 0D, 2D, 14D, 13D, 4D),
            Block.box(-14D, 0D, 2D, -12D, 13D, 4D),
            Block.box(-14D, 0D, 28D, -12D, 13D, 30D),
            Block.box(12D, 0D, 28D, 14D, 13D, 30D),
            Block.box(-16D, 13D, 0D, 16D, 16D, 32D)
    );
    // endregion

    // region: Table Small
    VoxelShape TABLE_SMALL = VoxelShapeHelper.combine(
            Block.box(1D, 0D, 1D, 3D, 13D, 3D),
            Block.box(1D, 0D, 13D, 3D, 13D, 15D),
            Block.box(13D, 0D, 13D, 15D, 13D, 15D),
            Block.box(13D, 0D, 1D, 15D, 13D, 3D),
            Block.box(0D, 13D, 0D, 16D, 16D, 16D)
    );
    // endregion

    // region: Table Wide
    VoxelShape TABLE_WIDE = VoxelShapeHelper.combine(
            Block.box(13D, 0D, 0D, 15D, 9D, 2D),
            Block.box(13D, 7D, 1D, 15D, 13D, 3D),
            Block.box(13D, 7D, 13D, 15D, 13D, 15D),
            Block.box(-15D, 7D, 13D, -13D, 13D, 15D),
            Block.box(-15D, 0D, 0D, -13D, 9D, 2D),
            Block.box(-15D, 0D, 14D, -13D, 9D, 16D),
            Block.box(13D, 0D, 14D, 15D, 9D, 16D),
            Block.box(-16D, 13D, 0D, 16D, 16D, 16D),
            Block.box(-15D, 7D, 1D, -13D, 13D, 3D)
    );
    // endregion

    // region: Bench
    VoxelShape BENCH = VoxelShapeHelper.combine(
            Block.box(12D, 0D, 2D, 14D, 3D, 4D),
            Block.box(-14D, 0D, 2D, -12D, 3D, 4D),
            Block.box(-14D, 0D, 12D, -12D, 3D, 14D),
            Block.box(12D, 0D, 12D, 14D, 3D, 14D),
            Block.box(12D, 3D, 11.5D, 14D, 5D, 13.5D),
            Block.box(12D, 3D, 2.5D, 14D, 5D, 4.5D),
            Block.box(-14D, 3D, 2.5D, -12D, 5D, 4.5D),
            Block.box(-14D, 3D, 11.5D, -12D, 5D, 13.5D),
            Block.box(-13.5D, 3.5D, 4.5D, -12.5D, 4.5D, 11.5D),
            Block.box(12.5D, 3.5D, 4.5D, 13.5D, 4.5D, 11.5D),
            Block.box(-15D, 5D, 2D, 15D, 7D, 14D)
    );
    // endregion

    // region: Chair
    VoxelShape CHAIR = VoxelShapeHelper.combine(
            Block.box(2D, 0D, 2D, 4D, 4D, 4D),
            Block.box(2.5D, 4.5D, 4.5D, 3.5D, 5.5D, 11.5D),
            Block.box(12.5D, 4.5D, 4.5D, 13.5D, 5.5D, 11.5D),
            Block.box(12D, 0D, 2D, 14D, 4D, 4D),
            Block.box(2D, 0D, 12D, 4D, 4D, 14D),
            Block.box(2D, 7D, 2D, 14D, 9D, 14D),
            Block.box(2D, 9D, 13D, 14D, 25D, 14D),
            Block.box(12D, 0D, 12D, 14D, 4D, 14D),
            Block.box(2D, 4D, 11.5D, 4D, 7D, 13.5D),
            Block.box(12D, 4D, 11.5D, 14D, 7D, 13.5D),
            Block.box(2D, 4D, 2.5D, 4D, 7D, 4.5D),
            Block.box(12D, 4D, 2.5D, 14D, 7D, 4.5D)
    );
    // endregion

    // region: Ceiling Light
    VoxelShape CEILING_LIGHT = Block.box(1D, 0D, 1D, 15, 16D, 15D);
    // endregion

    // region: Cushion
    VoxelShape CUSHION = VoxelShapeHelper.combine(
            Block.box(2D, 0D, 2D, 4D, 2D, 4D),
            Block.box(2D, 0D, 12D, 4D, 2D, 14D),
            Block.box(12D, 0D, 12D, 14D, 2D, 14D),
            Block.box(12D, 0D, 2D, 14D, 2D, 4D),
            Block.box(2D, 5D, 2.25D, 14D, 7D, 13.75D),
            Block.box(1.75D, 4D, 2D, 14.25D, 5D, 14D),
            Block.box(2D, 2D, 2.5D, 4D, 4D, 4.5D),
            Block.box(12D, 2D, 2.5D, 14D, 4D, 4.5D),
            Block.box(12D, 2D, 11.5D, 14D, 4D, 13.5D),
            Block.box(2D, 2D, 11.5D, 4D, 4D, 13.5D),
            Block.box(2.5D, 2.5D, 4.5D, 3.5D, 3.5D, 11.5D),
            Block.box(12.5D, 2.5D, 4.5D, 13.5D, 3.5D, 11.5D)
    );
    // endregion

    // region: Stool
    VoxelShape STOOL = VoxelShapeHelper.combine(
            Block.box(2D, 0D, 2D, 4D, 3D, 4D),
            Block.box(12D, 0D, 12D, 14D, 3D, 14D),
            Block.box(12D, 0D, 2D, 14D, 3D, 4D),
            Block.box(2D, 0D, 12D, 4D, 3D, 14D),
            Block.box(2D, 3D, 11.5D, 4D, 5D, 13.5D),
            Block.box(12D, 3D, 11.5D, 14D, 5, 13.5D),
            Block.box(12D, 3D, 2.5D, 14D, 5D, 4.5D),
            Block.box(1.5D, 5D, 1.75D, 14.5D, 7D, 14.25D),
            Block.box(2D, 3D, 2.5D, 4D, 5D, 4.5D),
            Block.box(2.5D, 3.5D, 4.5D, 3.5D, 4.5D, 11.5D),
            Block.box(12.5D, 3.5D, 4.5D, 13.5D, 4.5D, 11.5D)
    );
    // endregion

    // region: Chest
    VoxelShape CHEST = Block.box(-15D, 0D, 2D, 15D, 14D, 16D);
    // endregion

    // region: Bookshelf
    VoxelShape BOOKSHELF = VoxelShapeHelper.combine(
            Block.box(-15D, 0D, 1D, 15D, 30D, 15D),
            Block.box(-16D, 30D, 0D, 16D, 32D, 16D)
    );
    // endregion

    // region: Desk Left
    VoxelShape DESK_LEFT = VoxelShapeHelper.combine(
            Block.box(13D, 0D, 0D, 15D, 9D, 2D),
            Block.box(13D, 7D, 1D, 15D, 13D, 3D),
            Block.box(13D, 7D, 13D, 15D, 13D, 15D),
            Block.box(-15D, 7D, 13D, -13D, 13D, 15D),
            Block.box(-15D, 0D, 0D, -13D, 9D, 2D),
            Block.box(-15D, 0D, 14D, -13D, 9D, 16D),
            Block.box(13D, 0D, 14D, 15D, 9D, 16D),
            Block.box(-16D, 13D, 0D, 16D, 16D, 16D),
            Block.box(-15D, 7D, 1D, -13D, 13D, 3D),
            Block.box(5D, 9D, 2D, 12D, 13D, 11D)
    );
    // endregion

    // region: Desk Right
    VoxelShape DESK_RIGHT = VoxelShapeHelper.combine(
            Block.box(13D, 0D, 0D, 15D, 9D, 2D),
            Block.box(13D, 7D, 1D, 15D, 13D, 3D),
            Block.box(13D, 7D, 13D, 15D, 13D, 15D),
            Block.box(-15D, 7D, 13D, -13D, 13D, 15D),
            Block.box(-15D, 0D, 0D, -13D, 9D, 2D),
            Block.box(-15D, 0D, 14D, -13D, 9D, 16D),
            Block.box(13D, 0D, 14D, 15D, 9D, 16D),
            Block.box(-16D, 13D, 0D, 16D, 16D, 16D),
            Block.box(-15D, 7D, 1D, -13D, 13D, 3D),
            Block.box(-12D, 9D, 2D, -5D, 13D, 11D)
    );
    // endregion

    // region: Drawer
    VoxelShape DRAWER = VoxelShapeHelper.combine(
            Block.box(1D, 0D, 1D, 15D, 13D, 15D),
            Block.box(0D, 13D, 0D, 16D, 16D, 16D)
    );
    // endregion

    // region: Dresser
    VoxelShape DRESSER = VoxelShapeHelper.combine(
            Block.box(-15D, 0D, 1D, 15D, 16D, 15D),
            Block.box(-16D, 13D, 14D, 16D, 16D, 16D),
            Block.box(-16D, 13D, 0D, 16D, 16D, 2D)
    );
    // endregion

    // region: Lockbox
    VoxelShape LOCKBOX = VoxelShapeHelper.combine(
            Block.box(2, 0, 3, 14, 9, 13),
            Block.box(2, 9, 5, 14, 10, 11)
    );
    // endregion

    // region: Wardrobe Top
    VoxelShape WARDROBE_TOP = VoxelShapeHelper.combine(
            Block.box(-15D, 3D, 0D, 15D, 14D, 16D),
            Block.box(-16D, 0D, 0D, 16D, 3D, 16D)
    );
    // endregion

    // region: Wardrobe Bottom
    VoxelShape WARDROBE_BOTTOM = VoxelShapeHelper.combine(
            Block.box(-14.75D, 0D, .25D, -12.25D, 31D, 2.75D),
            Block.box(-14.75D, 0D, 13.25D, -12.25D, 31D, 15.75D),
            Block.box(12.25D, 0D, 13.25D, 14.75D, 31D, 15.75D),
            Block.box(12.25D, 0D, .25D, 14.75D, 31D, 2.75D),
            Block.box(-14D, 2D, 1D, 14D, 31D, 15D),
            Block.box(-15D, 31D, 0D, 15D, 32D, 16D)
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
            Block.box(0D, 0D, 0D, 16D, 1D, 16D),
            Block.box(0D, 1D, 1D, 16D, 9D, 16D),
            Block.box(0D, 9D, 0D, 16D, 10D, 16D),
            Block.box(1D, 10D, 3D, 15D, 14D, 16D),
            Block.box(2D, 14D, 3D, 14D, 16D, 16D)
    );
    // endregion

    // region: Door Double
    VoxelShape DOOR_DOUBLE = VoxelShapeHelper.combine(
            Block.box(0D, 0D, 0D, 13D, 2D, 3D),
            Block.box(0D, 10D, 0D, 13D, 12D, 3D),
            Block.box(0D, 20D, 0D, 13D, 22D, 3D),
            Block.box(13D, 0D, 0D, 16D, 32D, 3D),
            Block.box(12D, 28D, 0D, 13D, 32D, 3D),
            Block.box(10D, 29D, 0D, 12D, 32D, 3D),
            Block.box(8D, 30D, 0D, 10D, 32D, 3D),
            Block.box(3D, 31D, 0D, 8D, 32D, 3D),
            Block.box(0D, 2D, .5D, 13D, 32D, 2.5D)
    );
    // endregion

    // region: Double Single
    VoxelShape DOOR_SINGLE = VoxelShapeHelper.combine(
            Block.box(0D, 0D, 0D, 13D, 2D, 3D),
            Block.box(0D, 10D, 0D, 13D, 12D, 3D),
            Block.box(0D, 20D, 0D, 13D, 22D, 3D),
            Block.box(0D, 30D, 0D, 13D, 32D, 3D),
            Block.box(13D, 0D, 0D, 16D, 32D, 3D),
            Block.box(0D, 2D, .5D, 13D, 30D, 2.5D)
    );
    // endregion

    // region: Bed Single
    VoxelShape BED_SINGLE = VoxelShapeHelper.combine(
            Block.box(0D, 0D, 0D, 16D, 14D, 2D),
            Block.box(0D, 0D, 30D, 16D, 14D, 32D),
            Block.box(0D, 3D, 2D, 16D, 5D, 30D),
            Block.box(1D, 5D, 2D, 15D, 8D, 30D)
    );
    // endregion

    // region: Bed Double
    VoxelShape BED_DOUBLE = VoxelShapeHelper.combine(
            Block.box(-16D, 3D, 2D, 16D, 5D, 30D),
            Block.box(-14D, 5D, 2D, 14D, 8D, 30D),
            Block.box(-16D, 3D, 0D, 16D, 5D, 2D),
            Block.box(-16D, 0D, 0D, -14D, 8D, 2D),
            Block.box(14D, 0D, 0D, 16D, 8D, 2D),
            Block.box(-16D, 12D, 0D, -8D, 14D, 2D),
            Block.box(8D, 12D, 0D, 16D, 14D, 2D),
            Block.box(-10D, 12D, 0D, 10D, 16D, 2D),
            Block.box(-15D, 5D, 0D, 15D, 12D, 2D),
            Block.box(-15D, 5D, 30D, 15D, 12D, 32D),
            Block.box(-16D, 3D, 30D, 16D, 5D, 32D),
            Block.box(-16D, 0D, 30D, -14D, 8D, 32D),
            Block.box(14D, 0D, 30D, 16D, 8D, 32D),
            Block.box(-16D, 12D, 30D, -8D, 14D, 32D),
            Block.box(8D, 12D, 30D, 16D, 14D, 32D),
            Block.box(-10D, 12D, 30D, 10D, 16D, 32D)
    );
    // endregion

    // region: Shelf Single
    VoxelShape SHELF_SINGLE = VoxelShapeHelper.combine(
            Block.box(.5D, 9D, 2D, 2.5D, 14D, 13D),
            Block.box(13.5D, 9D, 2D, 15.5D, 14D, 13D),
            Block.box(0D, 14D, 0D, 16D, 16D, 16D),
            Block.box(13D, 6D, 13D, 16D, 14D, 16D),
            Block.box(0D, 6D, 13D, 3D, 14D, 16D)
    );
    // endregion

    // region: Shelf Center
    VoxelShape SHELF_CENTER = Block.box(0D, 14D, 0D, 16D, 16D, 16D);
    // endregion

    // region: Shelf Left
    VoxelShape SHELF_LEFT = VoxelShapeHelper.combine(
            Block.box(13.5D, 9D, 2D, 15.5D, 14D, 13D),
            Block.box(0D, 14D, 0D, 16D, 16D, 16D),
            Block.box(13D, 6D, 13D, 16D, 14D, 16D)
    );
    // endregion

    // region: Shelf Right
    VoxelShape SHELF_RIGHT = VoxelShapeHelper.combine(
            Block.box(.5D, 9D, 2D, 2.5D, 14D, 13D),
            Block.box(0D, 14D, 0D, 16D, 16D, 16D),
            Block.box(0D, 6D, 13D, 3D, 14D, 16D)
    );
    // endregion

    // region: Sofa Single
    VoxelShape SOFA_SINGLE = VoxelShapeHelper.combine(
            Block.box(1D, 0D, 1D, 3D, 3D, 3D),
            Block.box(1D, 0D, 13D, 3D, 3D, 15D),
            Block.box(13D, 0D, 13D, 15D, 3D, 15D),
            Block.box(13D, 0D, 1D, 15D, 3D, 3D),
            Block.box(0D, 3D, 0D, 16D, 6D, 16D),
            Block.box(0D, 6D, 13D, 16D, 16D, 16D),
            Block.box(14D, 10D, 0D, 16D, 12D, 14D),
            Block.box(0D, 10D, 0D, 2D, 12D, 14D),
            Block.box(0D, 6D, 0D, 2D, 10D, 2D),
            Block.box(14D, 6D, 0D, 16D, 10D, 2D)
    );
    // endregion

    // region: Sofa Center
    VoxelShape SOFA_CENTER = VoxelShapeHelper.combine(
            Block.box(0D, 3D, 0D, 16D, 6D, 16D),
            Block.box(0D, 6D, 13D, 16D, 16D, 16D)
    );
    // endregion

    // region: Sofa Left
    VoxelShape SOFA_LEFT = VoxelShapeHelper.combine(
            Block.box(0D, 3D, 0D, 16D, 6D, 16D),
            Block.box(0D, 6D, 13D, 16D, 16D, 16D),
            Block.box(14D, 10D, 0D, 16D, 12D, 13D),
            Block.box(14D, 6D, 0D, 16D, 10D, 2D),
            Block.box(13D, 0D, 1D, 15D, 3D, 3D),
            Block.box(13D, 0D, 13D, 15D, 3D, 15D)
    );
    // endregion

    // region: Sofa Right
    VoxelShape SOFA_RIGHT = VoxelShapeHelper.combine(
            Block.box(0D, 3D, 0D, 16D, 6D, 16D),
            Block.box(0D, 6D, 13D, 16D, 16D, 16D),
            Block.box(0D, 10D, 0D, 2D, 12D, 13D),
            Block.box(0D, 6D, 0D, 2D, 10D, 2D),
            Block.box(1D, 0D, 1D, 3D, 3D, 3D),
            Block.box(1D, 0D, 13D, 3D, 3D, 15D)
    );
    // endregion

    // region: Sofa Corner
    VoxelShape SOFA_CORNER = VoxelShapeHelper.combine(
            Block.box(1D, 0D, 1D, 3D, 3D, 3D),
            Block.box(1D, 0D, 13D, 3D, 3D, 15D),
            Block.box(13D, 0D, 13D, 15D, 3D, 15D),
            Block.box(13D, 0D, 1D, 15D, 3D, 3D),
            Block.box(0D, 3D, 0D, 16D, 6D, 16D),
            Block.box(0D, 6D, 13D, 16D, 16D, 16D),
            Block.box(13D, 6D, 0D, 16D, 16D, 13D)
    );
    // endregion

    // region: Counter Single
    VoxelShape COUNTER_SINGLE = VoxelShapeHelper.combine(
            Block.box(0D, 0D, 3D, 16D, 13D, 16D),
            Block.box(0D, 13D, 0D, 16D, 16D, 16D),
            Block.box(1D, 1D, 2D, 15D, 12D, 3D)
    );
    // endregion

    // region: Counter Corner
    VoxelShape COUNTER_CORNER = VoxelShapeHelper.combine(
            Block.box(0D, 0D, 0D, 13D, 13D, 4D),
            Block.box(0D, 0D, 3D, 16D, 13D, 16D),
            Block.box(0D, 13D, 0D, 16D, 16D, 16D)
    );
    // endregion

    static void bootstrap() {}
}
