package xyz.apex.minecraft.fantasyfurniture.common.init;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.minecraft.apexcore.common.component.ComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.components.DoorComponent;
import xyz.apex.minecraft.apexcore.common.component.components.HorizontalFacingComponent;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeHelper;
import xyz.apex.minecraft.fantasyfurniture.common.block.*;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.CounterType;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ModBlockStateProperties;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ShelfType;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.SofaType;

import java.util.function.Function;

public interface AllVoxelShapes
{
    interface Bone
    {
        VoxelShape WALL_LIGHT = box(6, 4, 9, 10, 15, 16);

        VoxelShape FLOOR_LIGHT = shape(
                box(6, 0, 6, 10, 2, 10),
                box(7, 2, 7, 9, 23, 9),
                box(2.5, 21.75, 6.5, 5.5, 23.75, 9.5),
                box(3.25, 23.75, 7.25, 4.75, 27.75, 8.75),
                box(7.25, 24.75, 7.25, 8.75, 28.75, 8.75),
                box(11.25, 23.75, 7.25, 12.75, 27.75, 8.75),
                box(10.5, 21.75, 6.5, 13.5, 23.75, 9.5),
                box(6.5, 22.75, 6.5, 9.5, 24.75, 9.5),
                box(3, 17.75, 7, 7, 21.75, 9),
                box(9, 17.75, 7, 13, 21.75, 9)
        );

        VoxelShape TABLE_LARGE = shape(
                box(-15, 0, 1, -12, 2, 4),
                box(-15, 0, 28, -12, 2, 31),
                box(12, 0, 28, 15, 2, 31),
                box(12, 0, 1, 15, 2, 4),
                box(12, 12, 1, 15, 14, 4),
                box(-15, 12, 1, -12, 14, 4),
                box(12, 12, 28, 15, 14, 31),
                box(-15, 12, 28, -12, 14, 31),
                box(12.5, 2, 1.5, 14.5, 12, 3.5),
                box(-14.5, 2, 1.5, -12.5, 12, 3.5),
                box(12.5, 2, 28.5, 14.5, 12, 30.5),
                box(-14.5, 2, 28.5, -12.5, 12, 30.5),
                box(-16, 14, 0, 16, 16, 32)
        );

        VoxelShape TABLE_SMALL = shape(
                box(1, 0, 1, 4, 2, 4),
                box(1, 0, 12, 4, 2, 15),
                box(12, 0, 12, 15, 2, 15),
                box(12, 0, 1, 15, 2, 4),
                box(12, 12, 1, 15, 14, 4),
                box(1, 12, 1, 4, 14, 4),
                box(12, 12, 12, 15, 14, 15),
                box(1, 12, 12, 4, 14, 15),
                box(12.5, 2, 1.5, 14.5, 12, 3.5),
                box(1.5, 2, 1.5, 3.5, 12, 3.5),
                box(12.5, 2, 12.5, 14.5, 12, 14.5),
                box(1.5, 2, 12.5, 3.5, 12, 14.5),
                box(0, 14, 0, 16, 16, 16)
        );

        VoxelShape TABLE_WIDE = shape(
                box(-15, 0, 1, -12, 2, 4),
                box(-15, 0, 12, -12, 2, 15),
                box(12, 0, 12, 15, 2, 15),
                box(12, 0, 1, 15, 2, 4),
                box(12, 12, 1, 15, 14, 4),
                box(-15, 12, 1, -12, 14, 4),
                box(12, 12, 12, 15, 14, 15),
                box(-15, 12, 12, -12, 14, 15),
                box(12.5, 2, 1.5, 14.5, 12, 3.5),
                box(-14.5, 2, 1.5, -12.5, 12, 3.5),
                box(12.5, 2, 12.5, 14.5, 12, 14.5),
                box(-14.5, 2, 12.5, -12.5, 12, 14.5),
                box(-16, 14, 0, 16, 16, 16)
        );

        VoxelShape BENCH = shape(
                box(12, 0, 1, 15, 2, 4),
                box(-15, 0, 1, -12, 2, 4),
                box(-15, 0, 12, -12, 2, 15),
                box(12, 0, 12, 15, 2, 15),
                box(-14.5, 2, 1.5, -12.5, 5, 3.5),
                box(12.5, 2, 1.5, 14.5, 5, 3.5),
                box(12.5, 2, 12.5, 14.5, 5, 14.5),
                box(-14.5, 2, 12.5, -12.5, 5, 14.5),
                box(-15.5, 4.5, -0.5, 15.5, 7.5, 16.5)
        );

        VoxelShape CHAIR = shape(
                box(1.5, 0, 1.5, 4.5, 3, 4.5),
                box(1.5, 0, 11.5, 4.5, 3, 14.5),
                box(11.5, 0, 11.5, 14.5, 3, 14.5),
                box(11.5, 0, 1.5, 14.5, 3, 4.5),
                box(2, 3, 12, 4, 7, 14),
                box(2, 3, 2, 4, 7, 4),
                box(12, 3, 2, 14, 7, 4),
                box(12, 3, 12, 14, 7, 14),
                box(1.5, 7, 1.5, 14.5, 9, 14.5),
                box(2, 9, 12, 14, 26, 14)
        );

        VoxelShape CEILING_LIGHT = box(0, 0, 0, 16, 16, 16);

        VoxelShape CUSHION = box(4, 0, 4, 12, 7, 12);

        VoxelShape STOOL = shape(
                box(1, 0, 1, 4, 2, 4),
                box(1, 0, 12, 4, 2, 15),
                box(12, 0, 12, 15, 2, 15),
                box(12, 0, 1, 15, 2, 4),
                box(1.5, 2, 12.5, 3.5, 5, 14.5),
                box(1.5, 2, 1.5, 3.5, 5, 3.5),
                box(12.5, 2, 12.5, 14.5, 5, 14.5),
                box(12.5, 2, 1.5, 14.5, 5, 3.5),
                box(1, 5, 1, 15, 7, 15)
        );

        VoxelShape CHEST = shape(
                box(11, 0, 2, 14, 2, 5),
                box(-14, 0, 2, -11, 2, 5),
                box(-14, 0, 11, -11, 2, 14),
                box(11, 0, 11, 14, 2, 14),
                box(11.5, 2, 11.5, 13.5, 11, 13.5),
                box(11.5, 2, 2.5, 13.5, 11, 4.5),
                box(-13.5, 2, 2.5, -11.5, 11, 4.5),
                box(-13.5, 2, 11.5, -11.5, 11, 13.5),
                box(-14, 11, 2, 14, 13, 14),
                box(-14, 3, 2, 14, 5, 14),
                box(-13, 5, 3, 13, 11, 13)
        );

        VoxelShape BOOKSHELF = shape(
                box(11, 0, 1, 15, 2, 5),
                box(-15, 0, 1, -11, 2, 5),
                box(-15, 0, 11, -11, 2, 15),
                box(11, 0, 11, 15, 2, 15),
                box(-15, 3, 1, 15, 5, 15),
                box(-15, 30, 1, 15, 32, 15),
                box(-14, 5, 2, 14, 30, 14),
                box(12, 2, 2, 14, 3, 4),
                box(12, 2, 12, 14, 3, 14),
                box(-14, 2, 12, -12, 3, 14),
                box(-14, 2, 2, -12, 3, 4)
        );

        VoxelShape DESK_LEFT = shape(
                box(-15, 0, 1, -12, 2, 4),
                box(-15, 0, 12, -12, 2, 15),
                box(12, 0, 12, 15, 2, 15),
                box(12, 0, 1, 15, 2, 4),
                box(12, 12, 1, 15, 14, 4),
                box(-15, 12, 1, -12, 14, 4),
                box(12, 12, 12, 15, 14, 15),
                box(-15, 12, 12, -12, 14, 15),
                box(12.5, 2, 1.5, 14.5, 12, 3.5),
                box(-14.5, 2, 1.5, -12.5, 12, 3.5),
                box(12.5, 2, 12.5, 14.5, 12, 14.5),
                box(-14.5, 2, 12.5, -12.5, 12, 14.5),
                box(-16, 14, 0, 16, 16, 16),
                box(5, 9, 3, 12, 14, 13)
        );

        VoxelShape DESK_RIGHT = shape(
                box(-15, 0, 1, -12, 2, 4),
                box(-15, 0, 12, -12, 2, 15),
                box(12, 0, 12, 15, 2, 15),
                box(12, 0, 1, 15, 2, 4),
                box(12, 12, 1, 15, 14, 4),
                box(-15, 12, 1, -12, 14, 4),
                box(12, 12, 12, 15, 14, 15),
                box(-15, 12, 12, -12, 14, 15),
                box(12.5, 2, 1.5, 14.5, 12, 3.5),
                box(-14.5, 2, 1.5, -12.5, 12, 3.5),
                box(12.5, 2, 12.5, 14.5, 12, 14.5),
                box(-14.5, 2, 12.5, -12.5, 12, 14.5),
                box(-16, 14, 0, 16, 16, 16),
                box(-12, 9, 3, -5, 14, 13)
        );

        VoxelShape DRAWER = shape(
                box(1, 0, 1, 4, 2, 4),
                box(1, 12, 1, 4, 14, 4),
                box(1.5, 1, 1.5, 3.5, 12, 3.5),
                box(1, 0, 12, 4, 2, 15),
                box(1, 12, 12, 4, 14, 15),
                box(1.5, 1, 12.5, 3.5, 12, 14.5),
                box(12, 0, 12, 15, 2, 15),
                box(12, 12, 12, 15, 14, 15),
                box(12.5, 1, 12.5, 14.5, 12, 14.5),
                box(12, 0, 1, 15, 2, 4),
                box(12, 12, 1, 15, 14, 4),
                box(12.5, 1, 1.5, 14.5, 12, 3.5),
                box(1.5, 6, 1.5, 14.5, 14, 14.5),
                box(0, 14, 0, 16, 16, 16)
        );

        VoxelShape DRESSER = shape(
                box(-15, 0, 1, -12, 2, 4),
                box(-15, 12, 1, -12, 14, 4),
                box(-14.5, 1, 1.5, -12.5, 12, 3.5),
                box(-15, 0, 12, -12, 2, 15),
                box(-15, 12, 12, -12, 14, 15),
                box(-14.5, 1, 12.5, -12.5, 12, 14.5),
                box(12, 0, 12, 15, 2, 15),
                box(12, 12, 12, 15, 14, 15),
                box(12.5, 1, 12.5, 14.5, 12, 14.5),
                box(12, 0, 1, 15, 2, 4),
                box(12, 12, 1, 15, 14, 4),
                box(12.5, 1, 1.5, 14.5, 12, 3.5),
                box(-14.5, 6, 1.5, 14.5, 14, 14.5),
                box(-16, 14, 0, 16, 16, 16)
        );

        VoxelShape LOCKBOX = shape(
                box(2, 0, 4, 5, 2, 7),
                box(2, 0, 9, 5, 2, 12),
                box(11, 0, 9, 14, 2, 12),
                box(11, 0, 4, 14, 2, 7),
                box(1, 2, 3, 15, 4, 13),
                box(1.5, 4, 3.5, 14.5, 12.25, 12.5)
        );

        VoxelShape WARDROBE_TOP = shape(
                box(11, 10, 1, 15, 12, 5),
                box(-15, 10, 1, -11, 12, 5),
                box(-15, 10, 11, -11, 12, 15),
                box(11, 10, 11, 15, 12, 15),
                box(11, 7, 11, 15, 9, 15),
                box(11, 7, 1, 15, 9, 5),
                box(-15, 7, 1, -11, 9, 5),
                box(-15, 7, 11, -11, 9, 15),
                box(12, 0, 12, 14, 10, 14),
                box(12, 0, 2, 14, 10, 4),
                box(-14, 0, 2, -12, 10, 4),
                box(-14, 0, 12, -12, 10, 14),
                box(-14, 0, 2, 14, 10.5, 14)
        );

        VoxelShape WARDROBE_BOTTOM = shape(
                box(11, 0, 1, 15, 2, 5),
                box(-15, 0, 1, -11, 2, 5),
                box(-15, 0, 11, -11, 2, 15),
                box(11, 0, 11, 15, 2, 15),
                box(12, 2, 12, 14, 30, 14),
                box(-14, 2, 12, -12, 30, 14),
                box(-14, 2, 2, -12, 30, 4),
                box(12, 2, 2, 14, 30, 4),
                box(-15, 3, 1, 15, 5, 15),
                box(-15, 30, 1, 15, 32, 15),
                box(-14, 5, 2, 14, 30, 14)
        );

        VoxelShape PAINTING_WIDE = shape(
                box(-16, 0, 13, -13, 3, 16),
                box(13, 0, 13, 16, 3, 16),
                box(13, 13, 13, 16, 16, 16),
                box(-16, 13, 13, -13, 16, 16),
                box(-13, 13, 13.5, 13, 15.5, 15.5),
                box(-13, 0.5, 13.5, 13, 3, 15.5),
                box(-15.5, 3, 13.5, 15.5, 13, 15.5)
        );

        VoxelShape PAINTING_SMALL = shape(
                box(0, 0, 13, 3, 3, 16),
                box(13, 0, 13, 16, 3, 16),
                box(13, 13, 13, 16, 16, 16),
                box(0, 13, 13, 3, 16, 16),
                box(3, 13, 13.5, 13, 15.5, 15.5),
                box(3, 0.5, 13.5, 13, 3, 15.5),
                box(0.5, 3, 13.5, 15.5, 13, 15.5)
        );

        VoxelShape OVEN = shape(
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

        VoxelShape DOOR_DOUBLE = shape(
                box(0, 0, 0, 16, 3, 3),
                box(0, 29, 0, 16, 32, 3),
                box(14, 2, 0.5, 16, 29, 2.5),
                box(10.5, 2, 0.5, 12.5, 29, 2.5),
                box(7, 2, 0.5, 9, 29, 2.5),
                box(3.5, 2, 0.5, 5.5, 29, 2.5),
                box(0, 2, 0.5, 2, 29, 2.5)
        );

        VoxelShape DOOR_SINGLE = shape(
                box(0, 0, 0, 16, 3, 3),
                box(0, 29, 0, 16, 32, 3),
                box(14, 2, 0.5, 16, 29, 2.5),
                box(10.5, 2, 0.5, 12.5, 29, 2.5),
                box(7, 2, 0.5, 9, 29, 2.5),
                box(3.5, 2, 0.5, 5.5, 29, 2.5),
                box(0, 2, 0.5, 2, 29, 2.5)
        );

        VoxelShape BED_SINGLE = shape(
                box(0, 0, 0, 4, 2, 4),
                box(0, 0, 28, 4, 2, 32),
                box(12, 0, 28, 16, 2, 32),
                box(12, 0, 0, 16, 2, 4),
                box(12, 12, 28, 16, 14, 32),
                box(0, 12, 28, 4, 14, 32),
                box(0, 12, 0, 4, 14, 4),
                box(12, 12, 0, 16, 14, 4),
                box(13, 2, 29, 15, 12, 31),
                box(1, 2, 29, 3, 12, 31),
                box(1, 2, 1, 3, 12, 3),
                box(13, 2, 1, 15, 12, 3),
                box(4, 4, 1, 12, 13, 3),
                box(4, 4, 29, 12, 13, 31),
                box(1, 4, 3, 15, 8, 29)
        );

        VoxelShape BED_DOUBLE = shape(
                box(-16, 0, 0, -12, 2, 4),
                box(-16, 0, 28, -12, 2, 32),
                box(12, 0, 28, 16, 2, 32),
                box(12, 0, 0, 16, 2, 4),
                box(12, 12, 28, 16, 14, 32),
                box(-16, 12, 28, -12, 14, 32),
                box(12, 12, 0, 16, 14, 4),
                box(-16, 12, 0, -12, 14, 4),
                box(13, 2, 29, 15, 12, 31),
                box(-15, 2, 29, -13, 12, 31),
                box(-15, 2, 1, -13, 12, 3),
                box(13, 2, 1, 15, 12, 3),
                box(-13, 4, 1, 13, 13, 3),
                box(-13, 4, 29, 13, 13, 31),
                box(-15, 4, 3, 15, 8, 29)
        );

        VoxelShape SHELF_SINGLE = shape(
                box(0, 6, 13, 3, 8, 16),
                box(13, 6, 13, 16, 8, 16),
                box(0.5, 8, 14, 2.5, 14, 16),
                box(13.5, 8, 14, 15.5, 14, 16),
                box(0, 14, 0, 16, 16, 16)
        );

        VoxelShape SHELF_CENTER = box(0, 14, 0, 16, 16, 16);

        VoxelShape SHELF_LEFT = shape(
                box(13, 6, 13, 16, 8, 16),
                box(13.5, 8, 14, 15.5, 14, 16),
                box(0, 14, 0, 16, 16, 16)
        );

        VoxelShape SHELF_RIGHT = shape(
                box(0, 6, 13, 3, 8, 16),
                box(0.5, 8, 14, 2.5, 15, 16),
                box(0, 14, 0, 16, 16, 16)
        );

        VoxelShape SOFA_SINGLE = shape(
                box(1.5, 0, 1, 4.5, 2, 4),
                box(1.5, 0, 12, 4.5, 2, 15),
                box(11.5, 0, 12, 14.5, 2, 15),
                box(11.5, 0, 1, 14.5, 2, 4),
                box(12, 2, 1.5, 14, 4, 3.5),
                box(2, 2, 1.5, 4, 4, 3.5),
                box(2, 2, 12.5, 4, 4, 14.5),
                box(12, 2, 12.5, 14, 4, 14.5),
                box(12, 3, 3.5, 14, 4, 12.5),
                box(2, 3, 3.5, 4, 4, 12.5),
                box(2, 4, 1.5, 14, 6, 14.5),
                box(2, 6, 12.5, 14, 15, 14.5),
                box(14, 3.5, 12, 16, 15.5, 15),
                box(14, 6, 2, 16, 11.5, 12),
                box(14, 3.5, 1, 16, 6.5, 12),
                box(0, 3.5, 1, 2, 6.5, 12),
                box(0, 3.5, 12, 2, 15.5, 15),
                box(0, 6, 2, 2, 11.5, 12)
        );

        VoxelShape SOFA_CENTER = shape(
                Block.box(0, 4, 1.5, 16, 6, 14.5),
                Block.box(0, 6, 12.5, 16, 15, 14.5)
        );

        VoxelShape SOFA_LEFT = shape(
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

        VoxelShape SOFA_RIGHT = shape(
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

        VoxelShape SOFA_CORNER = shape(
                box(1, 0, 1, 4, 2, 4),
                box(1, 0, 11.5, 4, 2, 14.5),
                box(11.5, 0, 11.5, 14.5, 2, 14.5),
                box(11.5, 0, 1, 14.5, 2, 4),
                box(12, 2, 1.5, 14, 4, 3.5),
                box(1.5, 2, 1.5, 3.5, 4, 3.5),
                box(1.5, 2, 12, 3.5, 4, 14),
                box(12, 2, 12, 14, 4, 14),
                box(0, 4, 1.5, 1.5, 6, 14.5),
                box(1.5, 4, 0, 14.5, 6, 14.5),
                box(12.5, 6, 0, 14.5, 15, 14.5),
                box(12, 4, 12, 15, 15.5, 15),
                box(0, 6, 12.5, 12, 15, 14.5)
        );

        VoxelShape COUNTER_SINGLE = shape(
                box(0, 0, 3, 16, 13, 16),
                box(0, 13, 0, 16, 16, 16),
                box(1, 2, 2, 15, 11, 3)
        );

        VoxelShape COUNTER_CORNER = shape(
                box(0, 0, 0, 13, 13, 3),
                box(0, 0, 3, 16, 13, 16),
                box(0, 13, 0, 16, 16, 16)
        );

        static VoxelShape getShelfShape(ShelfBlock block, BlockState blockState)
        {
            return AllVoxelShapes.getShelfShape(shelfType -> switch(shelfType) {
                case LEFT -> SHELF_LEFT;
                case RIGHT -> SHELF_RIGHT;
                case SINGLE -> SHELF_SINGLE;
                case CENTER -> SHELF_CENTER;
            }, block, blockState);
        }

        static VoxelShape getSofaShape(SofaBlock block, BlockState blockState)
        {
            return AllVoxelShapes.getSofaShape(sofaShape -> switch(sofaShape) {
                case LEFT -> SOFA_LEFT;
                case RIGHT -> SOFA_RIGHT;
                case SINGLE -> SOFA_SINGLE;
                case CENTER -> SOFA_CENTER;
                case CORNER -> SOFA_CORNER;
            }, block, blockState);
        }

        static VoxelShape getCounterShape(CounterBlock block, BlockState blockState)
        {
            return AllVoxelShapes.getCounterShape(counterType -> switch(counterType) {
                case SINGLE -> COUNTER_SINGLE;
                case CORNER -> COUNTER_CORNER;
            }, block, blockState);
        }

        private static void bootstrap() {}
    }

    interface Dunmer
    {
        VoxelShape WALL_LIGHT = shape(
                box(4.5, 1, 7.5, 11.5, 2, 14.5),
                box(4.5, 8, 7.5, 11.5, 9, 14.5),
                box(5, 2, 8, 11, 8, 14),
                box(6, 9, 9, 10, 10, 13),
                box(7.5, 10, 10.5, 8.5, 13, 11.5),
                box(7, 13, 9, 9, 15, 15),
                box(6, 12, 15, 10, 16, 16)
        );

        VoxelShape FLOOR_LIGHT = shape(
                box(6, 0, 6, 10, 2, 10),
                box(7, 2, 7, 9, 22, 9),
                box(6, 22, 6, 10, 27, 10),
                box(7, 27, 7, 9, 28, 9)
        );

        VoxelShape TABLE_LARGE = shape(
                box(12, 0, 2, 14, 14, 4),
                box(-14, 0, 2, -12, 14, 4),
                box(-14, 0, 28, -12, 14, 30),
                box(12, 0, 28, 14, 14, 30),
                box(-16, 14, 0, 16, 16, 32)
        );

        VoxelShape TABLE_SMALL = shape(
                box(1, 0, 1, 3, 14, 3),
                box(1, 0, 13, 3, 14, 15),
                box(13, 0, 13, 15, 14, 15),
                box(13, 0, 1, 15, 14, 3),
                box(0, 14, 0, 16, 16, 16)
        );

        VoxelShape TABLE_WIDE = shape(
                box(12, 0, 2, 14, 14, 4),
                box(-14, 0, 2, -12, 14, 4),
                box(-14, 0, 12, -12, 14, 14),
                box(12, 0, 12, 14, 14, 14),
                box(-16, 14, 0, 16, 16, 16)
        );

        VoxelShape BENCH = shape(
                box(12, 0, 2, 14, 5, 4),
                box(-14, 0, 2, -12, 5, 4),
                box(-14, 0, 12, -12, 5, 14),
                box(12, 0, 12, 14, 5, 14),
                box(-15, 5, 1, 15, 7, 15),
                box(12.5, 2.5, 4, 13.5, 3.5, 12),
                box(-13.5, 2.5, 4, -12.5, 3.5, 12)
        );

        VoxelShape CHAIR = shape(
                box(2, 0, 2, 4, 4, 4),
                box(2, 0, 12, 4, 4, 14),
                box(12, 0, 12, 14, 4, 14),
                box(12, 0, 2, 14, 4, 4),
                box(11.5, 4, 2.5, 13.5, 7, 4.5),
                box(2.5, 4, 2.5, 4.5, 7, 4.5),
                box(2.5, 4, 11.5, 4.5, 7, 13.5),
                box(11.5, 4, 11.5, 13.5, 7, 13.5),
                box(2, 7, 1, 14, 9, 15),
                box(2.5, 9, 11.5, 13.5, 31.5, 13.5)
        );

        VoxelShape CEILING_LIGHT = box(1, 2, 1, 15, 16, 15);

        VoxelShape CUSHION = shape(
                box(3, 0, 3, 5, 4, 5),
                box(3, 0, 11, 5, 4, 13),
                box(11, 0, 11, 13, 4, 13),
                box(11, 0, 3, 13, 4, 5),
                box(2, 4, 2, 14, 5, 14),
                box(2.5, 5, 2.5, 13.5, 7, 13.5)
        );

        VoxelShape STOOL = shape(
                box(12, 0, 2, 14, 5, 4),
                box(2, 0, 2, 4, 5, 4),
                box(2, 0, 12, 4, 5, 14),
                box(12, 0, 12, 14, 5, 14),
                box(1, 5, 1, 15, 7, 15),
                box(12.5, 2.5, 4, 13.5, 3.5, 12),
                box(2.5, 2.5, 4, 3.5, 3.5, 12)
        );

        VoxelShape CHEST = shape(
                box(12, 0, 2, 14, 14, 4),
                box(-14, 0, 2, -12, 14, 4),
                box(-14, 0, 12, -12, 14, 14),
                box(12, 0, 12, 14, 14, 14),
                box(-15, 4, 1, 15, 6, 15),
                box(-15, 14, 1, 15, 16, 15),
                box(-13, 6, 3, 13, 14, 13),
                box(-2, 11, 2, 2, 14, 3)
        );

        VoxelShape BOOKSHELF = shape(
                box(-14, 0, 2, -12, 30, 4),
                box(-14, 0, 12, -12, 30, 14),
                box(12, 0, 12, 14, 30, 14),
                box(12, 0, 2, 14, 30, 4),
                box(-12, 9, 4, 12, 32, 12),
                box(-15, 9, 1, 15, 11, 15),
                box(-15, 19, 1, 15, 21, 15),
                box(-15, 30, 1, 15, 32, 15)
        );

        VoxelShape DESK_LEFT = shape(
                box(12, 0, 2, 14, 14, 4),
                box(-14, 0, 2, -12, 14, 4),
                box(-14, 0, 12, -12, 14, 14),
                box(12, 0, 12, 14, 14, 14),
                box(-16, 14, 0, 16, 16, 16),
                box(4, 10, 2, 11, 14, 11)
        );

        VoxelShape DESK_RIGHT = shape(
                box(12, 0, 2, 14, 14, 4),
                box(-14, 0, 2, -12, 14, 4),
                box(-14, 0, 12, -12, 14, 14),
                box(12, 0, 12, 14, 14, 14),
                box(-16, 14, 0, 16, 16, 16),
                box(-11, 10, 2, -4, 14, 11)
        );

        VoxelShape DRAWER = shape(
                box(1, 0, 1, 3, 14, 3),
                box(1, 0, 13, 3, 14, 15),
                box(13, 0, 13, 15, 14, 15),
                box(13, 0, 1, 15, 14, 3),
                box(0, 14, 0, 16, 16, 16),
                box(1, 6, 1, 15, 8, 15),
                box(2, 6, 2, 14, 14, 14)
        );

        VoxelShape DRESSER = shape(
                box(-15, 0, 1, -13, 14, 3),
                box(-15, 0, 13, -13, 14, 15),
                box(13, 0, 13, 15, 14, 15),
                box(13, 0, 1, 15, 14, 3),
                box(-16, 14, 0, 16, 16, 16),
                box(-15, 6, 1, 15, 8, 15),
                box(-14, 8, 2, 14, 14, 14)
        );

        VoxelShape LOCKBOX = box(2, 0, 3, 14, 14, 13);

        VoxelShape WARDROBE_TOP = shape(
                box(13, 0, 1, 15, 8, 3),
                box(-15, 0, 1, -13, 8, 3),
                box(-15, 0, 13, -13, 8, 15),
                box(13, 0, 13, 15, 8, 15),
                box(-16, 8, 0, 16, 10, 16),
                box(-13, 0, 2, 13, 8, 14)
        );

        VoxelShape WARDROBE_BOTTOM = shape(
                box(-15, 0, 1, -13, 30, 3),
                box(-15, 0, 13, -13, 30, 15),
                box(13, 0, 13, 15, 30, 15),
                box(13, 0, 1, 15, 30, 3),
                box(-16, 30, 0, 16, 32, 16),
                box(-16, 20, 0, 16, 22, 16),
                box(-16, 2, 0, 16, 4, 16),
                box(-13, 4, 2, 13, 30, 14)
        );

        VoxelShape PAINTING_WIDE = shape(
                box(-16, 0, 14, 16, 2, 16),
                box(-16, 14, 14, 16, 16, 16),
                box(-15, 2, 14, 15, 14, 16)
        );

        VoxelShape PAINTING_SMALL = shape(
                box(0, 1, 14, 16, 3, 16),
                box(0, 13, 14, 16, 15, 16),
                box(1, 3, 14, 15, 13, 16)
        );

        VoxelShape OVEN = shape(
                Block.box(12.5, 0, 6.5, 15.5, 3, 9.5),
                Block.box(-15.5, 0, 6.5, -12.5, 3, 9.5),
                Block.box(-15, 3, 7, -13, 16, 9),
                Block.box(13, 3, 7, 15, 16, 9),
                Block.box(-16, 12, 7, 16, 14, 9),
                Block.box(-5, 10.5, 5.5, 5, 15.5, 10.5),
                Block.box(-6, 0, 2, 6, 3, 14)
        );

        VoxelShape DOOR_DOUBLE = box(0, 0, 0, 16, 32, 2);

        VoxelShape DOOR_SINGLE = shape(
                box(0, 0, 0, 16, 21, 2),
                box(0, 29, 0, 16, 32, 2),
                box(13, 21, 0, 16, 29, 2),
                box(0, 21, 0, 3, 29, 2),
                box(4, 21, 0.5, 5, 30, 1.5),
                box(6, 21, 0.5, 7, 30, 1.5),
                box(9, 21, 0.5, 10, 30, 1.5),
                box(11, 21, 0.5, 12, 30, 1.5),
                box(3, 27, 0.5, 13, 28, 1.5),
                box(3, 22, 0.5, 13, 23, 1.5)
        );

        VoxelShape BED_SINGLE = shape(
                box(0D, 0D, 0D, 2D, 13D, 2D),
                box(0D, 0D, 30D, 2D, 11D, 32D),
                box(14D, 0D, 30D, 16D, 11D, 32D),
                box(14D, 0D, 0D, 16D, 13D, 2D),
                box(2D, 3D, 0D, 14D, 14.25D, 2D),
                box(2D, 3D, 30D, 14D, 12.25D, 32D),
                box(1D, 5D, 2D, 15D, 8D, 30D),
                box(0D, 3D, 2D, 16D, 5D, 30D)
        );

        VoxelShape BED_DOUBLE = shape(
                box(-16D, 0D, 0D, -14D, 13D, 2D),
                box(-16D, 0D, 30D, -14D, 11D, 32D),
                box(14D, 0D, 30D, 16D, 11D, 32D),
                box(14D, 0D, 0D, 16D, 13D, 2D),
                box(-14D, 3D, 0D, 14D, 14.25D, 2D),
                box(-14D, 3D, 30D, 14D, 12.25D, 32D),
                box(-15D, 5D, 2D, 15D, 8D, 30D),
                box(-16D, 3D, 2D, 16D, 5D, 30D)
        );

        VoxelShape SHELF_SINGLE = shape(
                box(13, 8, 14, 15, 14, 16),
                box(1, 8, 14, 3, 14, 16),
                box(0, 14, 0, 16, 16, 16)
        );

        VoxelShape SHELF_CENTER = box(0, 14, 0, 16, 16, 16);

        VoxelShape SHELF_LEFT = shape(
                box(13, 8, 14, 15, 14, 16),
                box(0, 14, 0, 16, 16, 16)
        );

        VoxelShape SHELF_RIGHT = shape(
                box(1, 8, 14, 3, 14, 16),
                box(0, 14, 0, 16, 16, 16)
        );

        VoxelShape SOFA_SINGLE = shape(
                box(0, 4, 1, 16, 6, 15),
                box(2, 0, 12, 4, 4, 14),
                box(2, 0, 2, 4, 4, 4),
                box(12, 0, 2, 14, 4, 4),
                box(12, 0, 12, 14, 4, 14),
                box(1, 6, 12, 15, 16, 14),
                box(13, 10, 2, 15, 12, 12),
                box(13, 6, 3, 15, 10, 5),
                box(1, 6, 3, 3, 10, 5),
                box(1, 10, 2, 3, 12, 12),
                box(3, 6, 2, 13, 7, 13)
        );

        VoxelShape SOFA_CENTER = shape(
                Block.box(0, 4, 1, 16, 6, 15),
                Block.box(0, 6, 2, 16, 7, 12),
                Block.box(0, 6, 12, 16, 16, 14)
        );

        VoxelShape SOFA_LEFT = shape(
                Block.box(0, 4, 1, 16, 6, 15),
                Block.box(0, 6, 2, 13, 7, 12),
                Block.box(0, 6, 12, 15, 16, 14),
                Block.box(13, 10, 2, 15, 12, 12),
                Block.box(13, 6, 3, 15, 10, 12),
                Block.box(12, 0, 12, 14, 4, 14),
                Block.box(12, 0, 2, 14, 4, 4)
        );

        VoxelShape SOFA_RIGHT = shape(
                Block.box(0, 4, 1, 16, 6, 15),
                Block.box(3, 6, 2, 16, 7, 12),
                Block.box(1, 6, 12, 16, 16, 14),
                Block.box(1, 10, 2, 3, 12, 12),
                Block.box(1, 6, 3, 3, 10, 12),
                Block.box(2, 0, 12, 4, 4, 14),
                Block.box(2, 0, 2, 4, 4, 4)
        );

        VoxelShape SOFA_CORNER = shape(
                box(2, 0, 12, 4, 4, 14),
                box(2, 0, 2, 4, 4, 4),
                box(12, 0, 2, 14, 4, 4),
                box(12, 0, 12, 14, 4, 14),
                box(12, 6, 12, 14, 16, 14),
                box(12, 6, 0, 14, 16, 12),
                box(0, 6, 12, 12, 16, 14),
                box(1, 4, 0, 15, 6, 15),
                box(0, 4, 1, 1, 6, 15),
                box(0, 6, 2, 13, 7, 13),
                box(2, 6, 0, 13, 7, 2)
        );

        VoxelShape COUNTER_SINGLE = shape(
                box(0, 0, 3, 2, 1, 5),
                box(0, 0, 14, 2, 1, 16),
                box(14, 0, 14, 16, 1, 16),
                box(14, 0, 3, 16, 1, 5),
                box(0, 1, 3, 16, 14, 16),
                box(0, 14, 0, 16, 16, 16)
        );

        VoxelShape COUNTER_CORNER = shape(
                box(0, 0, 0, 2, 1, 2),
                box(11, 0, 0, 13, 1, 5),
                box(13, 0, 3, 16, 1, 5),
                box(14, 0, 14, 16, 1, 16),
                box(0, 0, 14, 2, 1, 16),
                box(0, 1, 3, 16, 14, 16),
                box(0, 1, 0, 13, 14, 3),
                box(0, 14, 0, 16, 16, 16)
        );

        static VoxelShape getShelfShape(ShelfBlock block, BlockState blockState)
        {
            return AllVoxelShapes.getShelfShape(shelfType -> switch(shelfType) {
                case LEFT -> SHELF_LEFT;
                case RIGHT -> SHELF_RIGHT;
                case SINGLE -> SHELF_SINGLE;
                case CENTER -> SHELF_CENTER;
            }, block, blockState);
        }

        static VoxelShape getSofaShape(SofaBlock block, BlockState blockState)
        {
            return AllVoxelShapes.getSofaShape(sofaShape -> switch(sofaShape) {
                case LEFT -> SOFA_LEFT;
                case RIGHT -> SOFA_RIGHT;
                case SINGLE -> SOFA_SINGLE;
                case CENTER -> SOFA_CENTER;
                case CORNER -> SOFA_CORNER;
            }, block, blockState);
        }

        static VoxelShape getCounterShape(CounterBlock block, BlockState blockState)
        {
            return AllVoxelShapes.getCounterShape(counterType -> switch(counterType) {
                case SINGLE -> COUNTER_SINGLE;
                case CORNER -> COUNTER_CORNER;
            }, block, blockState);
        }

        private static void bootstrap() {}
    }

    interface Necrolord
    {
        VoxelShape WALL_LIGHT = Block.box(6, 4, 8, 10, 14.5, 16);

        VoxelShape FLOOR_LIGHT = shape(
                Block.box(5, 0, 5, 11, 2, 11),
                Block.box(6.5, 2, 6.5, 9.5, 4, 9.5),
                Block.box(6.5, 10, 6.5, 9.5, 12, 9.5),
                Block.box(7, 4, 7, 9, 18, 9),
                Block.box(1.25, 18, 6.5, 14.75, 25, 9.5),
                Block.box(12.25, 25, 7, 14.25, 28, 9),
                Block.box(1.75, 25, 7, 3.75, 28, 9),
                Block.box(7, 25, 7, 9, 29, 9)
        );

        VoxelShape TABLE_LARGE = shape(
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

        VoxelShape TABLE_SMALL = shape(
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

        VoxelShape TABLE_WIDE = shape(
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

        VoxelShape BENCH = shape(
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

        VoxelShape CHAIR = shape(
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

        VoxelShape CEILING_LIGHT = Block.box(3, 0, 3, 13, 16, 13);

        VoxelShape CUSHION = shape(
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

        VoxelShape STOOL = shape(
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

        VoxelShape CHEST = shape(
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

        VoxelShape BOOKSHELF = shape(
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

        VoxelShape DESK_LEFT = shape(
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

        VoxelShape DESK_RIGHT = shape(
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

        VoxelShape DRAWER = shape(
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

        VoxelShape DRESSER = shape(
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

        VoxelShape LOCKBOX = Block.box(0.5, 0, 2.5, 15.5, 10, 13.5);

        VoxelShape WARDROBE_TOP = shape(
                Block.box(-15, 0, 1, 15, 11, 15),
                Block.box(12, 9, 0, 16, 11, 4),
                Block.box(-16, 9, 0, -12, 11, 4),
                Block.box(-16, 9, 12, -12, 11, 16),
                Block.box(12, 9, 12, 16, 11, 16)
        );

        VoxelShape WARDROBE_BOTTOM = shape(
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

        VoxelShape PAINTING_WIDE = shape(
                Block.box(-16, 0, 13, -13, 3, 16),
                Block.box(-16, 13, 13, -13, 16, 16),
                Block.box(13, 13, 13, 16, 16, 16),
                Block.box(13, 0, 13, 16, 3, 16),
                Block.box(-15, 1, 14, 15, 15, 16)
        );

        VoxelShape PAINTING_SMALL = shape(
                Block.box(0, 0, 13, 3, 3, 16),
                Block.box(0, 13, 13, 3, 16, 16),
                Block.box(13, 13, 13, 16, 16, 16),
                Block.box(13, 0, 13, 16, 3, 16),
                Block.box(1, 1, 14, 15, 15, 16)
        );

        VoxelShape OVEN = shape(
                Block.box(1, 0, 1, 4, 2, 4),
                Block.box(1, 0, 12, 4, 2, 15),
                Block.box(12, 0, 12, 15, 2, 15),
                Block.box(12, 0, 1, 15, 2, 4),
                Block.box(0, 2, 0, 16, 16, 16)
        );

        VoxelShape DOOR_DOUBLE = shape(
                Block.box(0, 0, 0.5, 13, 29, 2.5),
                Block.box(13, 0, 0, 16, 32, 3),
                Block.box(12, 20, 0, 13, 32, 3),
                Block.box(11, 24, 0, 12, 32, 3),
                Block.box(9, 27, 0, 11, 32, 3),
                Block.box(4, 28, 0, 9, 32, 3),
                Block.box(0, 29, 0, 4, 32, 3)
        );

        VoxelShape DOOR_SINGLE = Block.box(0, 0, 0, 16, 32, 3);

        VoxelShape BED_SINGLE = shape(
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

        VoxelShape BED_DOUBLE = shape(
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

        VoxelShape SHELF_SINGLE = shape(
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

        VoxelShape SHELF_CENTER = Block.box(0, 14, 0, 16, 16, 16);

        VoxelShape SHELF_LEFT = shape(
                Block.box(12, 4, 13, 15, 6, 16),
                Block.box(12.5, 6, 14, 14.5, 11, 16),
                Block.box(12, 11, 13, 15, 14, 16),
                Block.box(12.5, 12, 8, 14.5, 14, 13),
                Block.box(0, 14, 0, 16, 16, 16)
        );

        VoxelShape SHELF_RIGHT = shape(
                Block.box(1, 4, 13, 4, 6, 16),
                Block.box(1.5, 6, 14, 3.5, 11, 16),
                Block.box(1, 11, 13, 4, 14, 16),
                Block.box(1.5, 12, 8, 3.5, 14, 13),
                Block.box(0, 14, 0, 16, 16, 16)
        );

        VoxelShape SOFA_SINGLE = shape(
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

        VoxelShape SOFA_CENTER = shape(
                Block.box(0, 3, 1, 16, 6, 15),
                Block.box(0, 6, 12, 16, 16, 15)
        );

        VoxelShape SOFA_LEFT = shape(
                Block.box(12, 0, 1, 15, 2, 4),
                Block.box(12, 2, 2, 14, 3, 4),
                Block.box(12, 2, 12, 14, 3, 14),
                Block.box(12, 0, 12, 15, 2, 15),
                Block.box(0, 3, 1, 15, 6, 15),
                Block.box(0, 6, 12, 15, 16, 15),
                Block.box(13, 9, 1, 15, 11, 12),
                Block.box(13, 6, 2, 15, 9, 4)
        );

        VoxelShape SOFA_RIGHT = shape(
                Block.box(1, 0, 1, 4, 2, 4),
                Block.box(2, 2, 2, 4, 3, 4),
                Block.box(2, 2, 12, 4, 3, 14),
                Block.box(1, 0, 12, 4, 2, 15),
                Block.box(1, 3, 1, 16, 6, 15),
                Block.box(1, 6, 12, 16, 16, 15),
                Block.box(1, 9, 1, 3, 11, 12),
                Block.box(1, 6, 2, 3, 9, 4)
        );

        VoxelShape SOFA_CORNER = shape(
                Block.box(12, 0, 12, 15, 2, 15),
                Block.box(12, 2, 12, 14, 3, 14),
                Block.box(0, 3, 12, 15, 16, 15),
                Block.box(12, 3, 0, 15, 16, 12),
                Block.box(1, 3, 0, 15, 6, 12),
                Block.box(0, 3, 1, 14, 6, 12)
        );

        VoxelShape COUNTER_SINGLE = shape(
                Block.box(0, 0, 3, 16, 13, 16),
                Block.box(1, 1, 2, 15, 12, 3),
                Block.box(0, 13, 0, 16, 16, 16)
        );

        VoxelShape COUNTER_CORNER = shape(
                Block.box(0, 0, 0, 13, 13, 3),
                Block.box(0, 0, 3, 16, 13, 16),
                Block.box(0, 13, 0, 16, 16, 16)
        );

        static VoxelShape getShelfShape(ShelfBlock block, BlockState blockState)
        {
            return AllVoxelShapes.getShelfShape(shelfType -> switch(shelfType) {
                case LEFT -> SHELF_LEFT;
                case RIGHT -> SHELF_RIGHT;
                case SINGLE -> SHELF_SINGLE;
                case CENTER -> SHELF_CENTER;
            }, block, blockState);
        }

        static VoxelShape getSofaShape(SofaBlock block, BlockState blockState)
        {
            return AllVoxelShapes.getSofaShape(sofaShape -> switch(sofaShape) {
                case LEFT -> SOFA_LEFT;
                case RIGHT -> SOFA_RIGHT;
                case SINGLE -> SOFA_SINGLE;
                case CENTER -> SOFA_CENTER;
                case CORNER -> SOFA_CORNER;
            }, block, blockState);
        }

        static VoxelShape getCounterShape(CounterBlock block, BlockState blockState)
        {
            return AllVoxelShapes.getCounterShape(counterType -> switch(counterType) {
                case SINGLE -> COUNTER_SINGLE;
                case CORNER -> COUNTER_CORNER;
            }, block, blockState);
        }

        private static void bootstrap() {}
    }

    interface Nordic
    {
        VoxelShape WALL_LIGHT = shape(
                box(6D, 5D, 15D, 10D, 11D, 16D),
                box(6D, 2D, 8D, 10D, 15D, 15D)
        );

        VoxelShape FLOOR_LIGHT = shape(
                box(6D, 0D, 6D, 10D, 2D, 10D),
                box(7D, 2D, 7D, 9D, 20D, 9D),
                box(6.5D, 20.75D, 2.5D, 9.5D, 22.75D, 5.5D),
                box(2.5D, 20.75D, 6.5D, 5.5D, 22.75D, 9.5D),
                box(7.25D, 22.75D, 3.25D, 8.75D, 26.75D, 4.75D),
                box(3.25D, 22.75D, 7.25D, 4.75D, 26.75D, 8.75D),
                box(7.25D, 22.75D, 11.25D, 8.75D, 26.75D, 12.75D),
                box(11.25D, 22.75D, 7.25D, 12.75D, 26.75D, 8.75D),
                box(10.5D, 20.75D, 6.5D, 13.5D, 22.75D, 9.5D),
                box(6.5D, 20.75D, 10.5D, 9.5D, 22.75D, 13.5D),
                box(3D, 16.75D, 7D, 7D, 20.75, 9D),
                box(9D, 16.75D, 7D, 13D, 20.75, 9D),
                box(7D, 16.75D, 3D, 9D, 20.75, 7D),
                box(7D, 16.75D, 9D, 9D, 20.75, 13D)
        );

        VoxelShape TABLE_LARGE = shape(
                box(12D, 0D, 2D, 14D, 13D, 4D),
                box(-14D, 0D, 2D, -12D, 13D, 4D),
                box(-14D, 0D, 28D, -12D, 13D, 30D),
                box(12D, 0D, 28D, 14D, 13D, 30D),
                box(-16D, 13D, 0D, 16D, 16D, 32D)
        );

        VoxelShape TABLE_SMALL = shape(
                box(1D, 0D, 1D, 3D, 13D, 3D),
                box(1D, 0D, 13D, 3D, 13D, 15D),
                box(13D, 0D, 13D, 15D, 13D, 15D),
                box(13D, 0D, 1D, 15D, 13D, 3D),
                box(0D, 13D, 0D, 16D, 16D, 16D)
        );

        VoxelShape TABLE_WIDE = shape(
                box(13D, 0D, 0D, 15D, 9D, 2D),
                box(13D, 7D, 1D, 15D, 13D, 3D),
                box(13D, 7D, 13D, 15D, 13D, 15D),
                box(-15D, 7D, 13D, -13D, 13D, 15D),
                box(-15D, 0D, 0D, -13D, 9D, 2D),
                box(-15D, 0D, 14D, -13D, 9D, 16D),
                box(13D, 0D, 14D, 15D, 9D, 16D),
                box(-16D, 13D, 0D, 16D, 16D, 16D),
                box(-15D, 7D, 1D, -13D, 13D, 3D)
        );

        VoxelShape BENCH = shape(
                box(12D, 0D, 2D, 14D, 3D, 4D),
                box(-14D, 0D, 2D, -12D, 3D, 4D),
                box(-14D, 0D, 12D, -12D, 3D, 14D),
                box(12D, 0D, 12D, 14D, 3D, 14D),
                box(12D, 3D, 11.5D, 14D, 5D, 13.5D),
                box(12D, 3D, 2.5D, 14D, 5D, 4.5D),
                box(-14D, 3D, 2.5D, -12D, 5D, 4.5D),
                box(-14D, 3D, 11.5D, -12D, 5D, 13.5D),
                box(-13.5D, 3.5D, 4.5D, -12.5D, 4.5D, 11.5D),
                box(12.5D, 3.5D, 4.5D, 13.5D, 4.5D, 11.5D),
                box(-15D, 5D, 2D, 15D, 7D, 14D)
        );

        VoxelShape CHAIR = shape(
                box(2D, 0D, 2D, 4D, 4D, 4D),
                box(2.5D, 4.5D, 4.5D, 3.5D, 5.5D, 11.5D),
                box(12.5D, 4.5D, 4.5D, 13.5D, 5.5D, 11.5D),
                box(12D, 0D, 2D, 14D, 4D, 4D),
                box(2D, 0D, 12D, 4D, 4D, 14D),
                box(2D, 7D, 2D, 14D, 9D, 14D),
                box(2D, 9D, 13D, 14D, 25D, 14D),
                box(12D, 0D, 12D, 14D, 4D, 14D),
                box(2D, 4D, 11.5D, 4D, 7D, 13.5D),
                box(12D, 4D, 11.5D, 14D, 7D, 13.5D),
                box(2D, 4D, 2.5D, 4D, 7D, 4.5D),
                box(12D, 4D, 2.5D, 14D, 7D, 4.5D)
        );

        VoxelShape CEILING_LIGHT = box(1D, 0D, 1D, 15, 16D, 15D);

        VoxelShape CUSHION = shape(
                box(2D, 0D, 2D, 4D, 2D, 4D),
                box(2D, 0D, 12D, 4D, 2D, 14D),
                box(12D, 0D, 12D, 14D, 2D, 14D),
                box(12D, 0D, 2D, 14D, 2D, 4D),
                box(2D, 5D, 2.25D, 14D, 7D, 13.75D),
                box(1.75D, 4D, 2D, 14.25D, 5D, 14D),
                box(2D, 2D, 2.5D, 4D, 4D, 4.5D),
                box(12D, 2D, 2.5D, 14D, 4D, 4.5D),
                box(12D, 2D, 11.5D, 14D, 4D, 13.5D),
                box(2D, 2D, 11.5D, 4D, 4D, 13.5D),
                box(2.5D, 2.5D, 4.5D, 3.5D, 3.5D, 11.5D),
                box(12.5D, 2.5D, 4.5D, 13.5D, 3.5D, 11.5D)
        );

        VoxelShape STOOL = shape(
                box(2D, 0D, 2D, 4D, 3D, 4D),
                box(12D, 0D, 12D, 14D, 3D, 14D),
                box(12D, 0D, 2D, 14D, 3D, 4D),
                box(2D, 0D, 12D, 4D, 3D, 14D),
                box(2D, 3D, 11.5D, 4D, 5D, 13.5D),
                box(12D, 3D, 11.5D, 14D, 5, 13.5D),
                box(12D, 3D, 2.5D, 14D, 5D, 4.5D),
                box(1.5D, 5D, 1.75D, 14.5D, 7D, 14.25D),
                box(2D, 3D, 2.5D, 4D, 5D, 4.5D),
                box(2.5D, 3.5D, 4.5D, 3.5D, 4.5D, 11.5D),
                box(12.5D, 3.5D, 4.5D, 13.5D, 4.5D, 11.5D)
        );

        VoxelShape CHEST = box(-15D, 0D, 2D, 15D, 14D, 16D);

        VoxelShape BOOKSHELF = shape(
                box(-15D, 0D, 1D, 15D, 30D, 15D),
                box(-16D, 30D, 0D, 16D, 32D, 16D)
        );

        VoxelShape DESK_LEFT = shape(
                box(13D, 0D, 0D, 15D, 9D, 2D),
                box(13D, 7D, 1D, 15D, 13D, 3D),
                box(13D, 7D, 13D, 15D, 13D, 15D),
                box(-15D, 7D, 13D, -13D, 13D, 15D),
                box(-15D, 0D, 0D, -13D, 9D, 2D),
                box(-15D, 0D, 14D, -13D, 9D, 16D),
                box(13D, 0D, 14D, 15D, 9D, 16D),
                box(-16D, 13D, 0D, 16D, 16D, 16D),
                box(-15D, 7D, 1D, -13D, 13D, 3D),
                box(5D, 9D, 2D, 12D, 13D, 11D)
        );

        VoxelShape DESK_RIGHT = shape(
                box(13D, 0D, 0D, 15D, 9D, 2D),
                box(13D, 7D, 1D, 15D, 13D, 3D),
                box(13D, 7D, 13D, 15D, 13D, 15D),
                box(-15D, 7D, 13D, -13D, 13D, 15D),
                box(-15D, 0D, 0D, -13D, 9D, 2D),
                box(-15D, 0D, 14D, -13D, 9D, 16D),
                box(13D, 0D, 14D, 15D, 9D, 16D),
                box(-16D, 13D, 0D, 16D, 16D, 16D),
                box(-15D, 7D, 1D, -13D, 13D, 3D),
                box(-12D, 9D, 2D, -5D, 13D, 11D)
        );

        VoxelShape DRAWER = shape(
                box(1D, 0D, 1D, 15D, 13D, 15D),
                box(0D, 13D, 0D, 16D, 16D, 16D)
        );

        VoxelShape DRESSER = shape(
                box(-15D, 0D, 1D, 15D, 16D, 15D),
                box(-16D, 13D, 14D, 16D, 16D, 16D),
                box(-16D, 13D, 0D, 16D, 16D, 2D)
        );

        VoxelShape LOCKBOX = shape(
                box(2, 0, 3, 14, 9, 13),
                box(2, 9, 5, 14, 10, 11)
        );

        VoxelShape WARDROBE_TOP = shape(
                box(-15D, 3D, 0D, 15D, 14D, 16D),
                box(-16D, 0D, 0D, 16D, 3D, 16D)
        );

        VoxelShape WARDROBE_BOTTOM = shape(
                box(-14.75D, 0D, .25D, -12.25D, 31D, 2.75D),
                box(-14.75D, 0D, 13.25D, -12.25D, 31D, 15.75D),
                box(12.25D, 0D, 13.25D, 14.75D, 31D, 15.75D),
                box(12.25D, 0D, .25D, 14.75D, 31D, 2.75D),
                box(-14D, 2D, 1D, 14D, 31D, 15D),
                box(-15D, 31D, 0D, 15D, 32D, 16D)
        );

        VoxelShape PAINTING_WIDE = box(-16D, 0D, 14D, 16D, 16D, 16D);

        VoxelShape PAINTING_SMALL = box(0D, 0D, 14D, 16D, 16D, 16D);

        VoxelShape OVEN = shape(
                Block.box(0D, 0D, 0D, 16D, 1D, 16D),
                Block.box(0D, 1D, 1D, 16D, 9D, 16D),
                Block.box(0D, 9D, 0D, 16D, 10D, 16D),
                Block.box(1D, 10D, 3D, 15D, 14D, 16D),
                Block.box(2D, 14D, 3D, 14D, 16D, 16D)
        );

        VoxelShape DOOR_DOUBLE = shape(
                box(0D, 0D, 0D, 13D, 2D, 3D),
                box(0D, 10D, 0D, 13D, 12D, 3D),
                box(0D, 20D, 0D, 13D, 22D, 3D),
                box(13D, 0D, 0D, 16D, 32D, 3D),
                box(12D, 28D, 0D, 13D, 32D, 3D),
                box(10D, 29D, 0D, 12D, 32D, 3D),
                box(8D, 30D, 0D, 10D, 32D, 3D),
                box(3D, 31D, 0D, 8D, 32D, 3D),
                box(0D, 2D, .5D, 13D, 32D, 2.5D)
        );

        VoxelShape DOOR_SINGLE = shape(
                box(0D, 0D, 0D, 13D, 2D, 3D),
                box(0D, 10D, 0D, 13D, 12D, 3D),
                box(0D, 20D, 0D, 13D, 22D, 3D),
                box(0D, 30D, 0D, 13D, 32D, 3D),
                box(13D, 0D, 0D, 16D, 32D, 3D),
                box(0D, 2D, .5D, 13D, 30D, 2.5D)
        );

        VoxelShape BED_SINGLE = shape(
                box(0D, 0D, 0D, 16D, 14D, 2D),
                box(0D, 0D, 30D, 16D, 14D, 32D),
                box(0D, 3D, 2D, 16D, 5D, 30D),
                box(1D, 5D, 2D, 15D, 8D, 30D)
        );

        VoxelShape BED_DOUBLE = shape(
                box(-16D, 3D, 2D, 16D, 5D, 30D),
                box(-14D, 5D, 2D, 14D, 8D, 30D),
                box(-16D, 3D, 0D, 16D, 5D, 2D),
                box(-16D, 0D, 0D, -14D, 8D, 2D),
                box(14D, 0D, 0D, 16D, 8D, 2D),
                box(-16D, 12D, 0D, -8D, 14D, 2D),
                box(8D, 12D, 0D, 16D, 14D, 2D),
                box(-10D, 12D, 0D, 10D, 16D, 2D),
                box(-15D, 5D, 0D, 15D, 12D, 2D),
                box(-15D, 5D, 30D, 15D, 12D, 32D),
                box(-16D, 3D, 30D, 16D, 5D, 32D),
                box(-16D, 0D, 30D, -14D, 8D, 32D),
                box(14D, 0D, 30D, 16D, 8D, 32D),
                box(-16D, 12D, 30D, -8D, 14D, 32D),
                box(8D, 12D, 30D, 16D, 14D, 32D),
                box(-10D, 12D, 30D, 10D, 16D, 32D)
        );

        VoxelShape SHELF_SINGLE = shape(
                box(.5D, 9D, 2D, 2.5D, 14D, 13D),
                box(13.5D, 9D, 2D, 15.5D, 14D, 13D),
                box(0D, 14D, 0D, 16D, 16D, 16D),
                box(13D, 6D, 13D, 16D, 14D, 16D),
                box(0D, 6D, 13D, 3D, 14D, 16D)
        );

        VoxelShape SHELF_CENTER = box(0D, 14D, 0D, 16D, 16D, 16D);

        VoxelShape SHELF_LEFT = shape(
                box(13.5D, 9D, 2D, 15.5D, 14D, 13D),
                box(0D, 14D, 0D, 16D, 16D, 16D),
                box(13D, 6D, 13D, 16D, 14D, 16D)
        );

        VoxelShape SHELF_RIGHT = shape(
                box(.5D, 9D, 2D, 2.5D, 14D, 13D),
                box(0D, 14D, 0D, 16D, 16D, 16D),
                box(0D, 6D, 13D, 3D, 14D, 16D)
        );

        VoxelShape SOFA_SINGLE = shape(
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

        VoxelShape SOFA_CENTER = shape(
                box(0D, 3D, 0D, 16D, 6D, 16D),
                box(0D, 6D, 13D, 16D, 16D, 16D)
        );

        VoxelShape SOFA_LEFT = shape(
                box(0D, 3D, 0D, 16D, 6D, 16D),
                box(0D, 6D, 13D, 16D, 16D, 16D),
                box(14D, 10D, 0D, 16D, 12D, 13D),
                box(14D, 6D, 0D, 16D, 10D, 2D),
                box(13D, 0D, 1D, 15D, 3D, 3D),
                box(13D, 0D, 13D, 15D, 3D, 15D)
        );

        VoxelShape SOFA_RIGHT = shape(
                box(0D, 3D, 0D, 16D, 6D, 16D),
                box(0D, 6D, 13D, 16D, 16D, 16D),
                box(0D, 10D, 0D, 2D, 12D, 13D),
                box(0D, 6D, 0D, 2D, 10D, 2D),
                box(1D, 0D, 1D, 3D, 3D, 3D),
                box(1D, 0D, 13D, 3D, 3D, 15D)
        );

        VoxelShape SOFA_CORNER = shape(
                box(1D, 0D, 1D, 3D, 3D, 3D),
                box(1D, 0D, 13D, 3D, 3D, 15D),
                box(13D, 0D, 13D, 15D, 3D, 15D),
                box(13D, 0D, 1D, 15D, 3D, 3D),
                box(0D, 3D, 0D, 16D, 6D, 16D),
                box(0D, 6D, 13D, 16D, 16D, 16D),
                box(13D, 6D, 0D, 16D, 16D, 13D)
        );

        VoxelShape COUNTER_SINGLE = shape(
                box(0D, 0D, 3D, 16D, 13D, 16D),
                box(0D, 13D, 0D, 16D, 16D, 16D),
                box(1D, 1D, 2D, 15D, 12D, 3D)
        );

        VoxelShape COUNTER_CORNER = shape(
                box(0D, 0D, 0D, 13D, 13D, 4D),
                box(0D, 0D, 3D, 16D, 13D, 16D),
                box(0D, 13D, 0D, 16D, 16D, 16D)
        );

        static VoxelShape getShelfShape(ShelfBlock block, BlockState blockState)
        {
            return AllVoxelShapes.getShelfShape(shelfType -> switch(shelfType) {
                case LEFT -> SHELF_LEFT;
                case RIGHT -> SHELF_RIGHT;
                case SINGLE -> SHELF_SINGLE;
                case CENTER -> SHELF_CENTER;
            }, block, blockState);
        }

        static VoxelShape getSofaShape(SofaBlock block, BlockState blockState)
        {
            return AllVoxelShapes.getSofaShape(sofaShape -> switch(sofaShape) {
                case LEFT -> SOFA_LEFT;
                case RIGHT -> SOFA_RIGHT;
                case SINGLE -> SOFA_SINGLE;
                case CENTER -> SOFA_CENTER;
                case CORNER -> SOFA_CORNER;
            }, block, blockState);
        }

        static VoxelShape getCounterShape(CounterBlock block, BlockState blockState)
        {
            return AllVoxelShapes.getCounterShape(counterType -> switch(counterType) {
                case SINGLE -> COUNTER_SINGLE;
                case CORNER -> COUNTER_CORNER;
            }, block, blockState);
        }

        private static void bootstrap() {}
    }

    interface Royal
    {
        VoxelShape WALL_LIGHT = box(4.75, 1, 11.75, 11.25, 13, 16);

        VoxelShape FLOOR_LIGHT = shape(
                box(6, 0, 6, 10, 2, 10),
                box(7, 2, 7, 9, 30, 9),
                box(3, 20, 7, 13, 22, 9),
                box(11, 22, 7, 13, 29, 9),
                box(3, 22, 7, 5, 29, 9),
                box(2.5, 24, 6.5, 5.5, 25, 9.5),
                box(6.5, 25, 6.5, 9.5, 26, 9.5),
                box(10.5, 24, 6.5, 13.5, 25, 9.5)
        );

        VoxelShape TABLE_LARGE = shape(
                box(-15, 0, 1, -11, 14, 5),
                box(-15, 0, 27, -11, 14, 31),
                box(11, 0, 27, 15, 14, 31),
                box(11, 0, 1, 15, 14, 5),
                box(-16, 14, 0, 16, 16, 32)
        );

        VoxelShape TABLE_SMALL = shape(
                box(1, 0, 1, 5, 14, 5),
                box(1, 0, 11, 5, 14, 15),
                box(11, 0, 11, 15, 14, 15),
                box(11, 0, 1, 15, 14, 5),
                box(0, 14, 0, 16, 16, 16)
        );

        VoxelShape TABLE_WIDE = shape(
                box(-15, 0, 1, -11, 14, 5),
                box(-15, 0, 11, -11, 14, 15),
                box(11, 0, 11, 15, 14, 15),
                box(11, 0, 1, 15, 14, 5),
                box(-16, 14, 0, 16, 16, 16)
        );

        VoxelShape BENCH = shape(
                Block.box(-15, 0, 1, -11, 4, 5),
                Block.box(-15, 0, 11, -11, 4, 15),
                Block.box(11, 0, 11, 15, 4, 15),
                Block.box(11, 0, 1, 15, 4, 5),
                Block.box(-14.5, 4, 1.5, 14.5, 6, 14.5)
        );

        VoxelShape CHAIR = shape(
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

        VoxelShape CEILING_LIGHT = Block.box(0, 0, 0, 16, 16, 16);

        VoxelShape CUSHION = shape(
                Block.box(1, 0, 1, 5, 4, 5),
                Block.box(1, 0, 11, 5, 4, 15),
                Block.box(11, 0, 11, 15, 4, 15),
                Block.box(11, 0, 1, 15, 4, 5),
                Block.box(1.5, 4, 1.5, 14.5, 6, 14.5),
                Block.box(2, 6, 2, 14, 9, 14)
        );

        VoxelShape STOOL = shape(
                box(1, 0, 1, 5, 4, 5),
                box(1, 0, 11, 5, 4, 15),
                box(11, 0, 11, 15, 4, 15),
                box(11, 0, 1, 15, 4, 5),
                box(1.5, 4, 1.5, 14.5, 6, 14.5)
        );

        VoxelShape CHEST = shape(
                Block.box(7.5, 0, 0.75, 11.25, 4, 4.5),
                Block.box(-11.25, 0, 0.75, -7.5, 4, 4.5),
                Block.box(-11.25, 0, 11.5, -7.5, 4, 15.25),
                Block.box(7.5, 0, 11.5, 11.25, 4, 15.25),
                Block.box(-12, 4, 1, 12, 6, 15),
                Block.box(-11, 6, 2, 11, 16, 14)
        );

        VoxelShape BOOKSHELF = shape(
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

        VoxelShape DESK_LEFT = shape(
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

        VoxelShape DESK_RIGHT = shape(
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

        VoxelShape DRAWER = shape(
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

        VoxelShape DRESSER = shape(
                box(-15, 0, 1, -13, 3, 4),
                box(-15, 0, 12, -13, 3, 15),
                box(13, 0, 12, 15, 3, 15),
                box(13, 0, 1, 15, 3, 4),
                box(13, 3, 2, 15, 14, 4),
                box(-15, 3, 2, -13, 14, 4),
                box(-15, 3, 12, -13, 14, 14),
                box(13, 3, 12, 15, 14, 14),
                box(-15.5, 5, 1.5, 15.5, 7, 14.5),
                box(-16, 14, 0, 16, 16, 16),
                box(-14, 7, 3, 14, 14, 13)
        );

        VoxelShape LOCKBOX = box(2.5, 0, 3.5, 13.5, 9.25, 12.5);

        VoxelShape WARDROBE_TOP = shape(
                Block.box(12, 0, 2, 14, 14, 4),
                Block.box(-14, 0, 2, -12, 14, 4),
                Block.box(-14, 0, 12, -12, 14, 14),
                Block.box(12, 0, 12, 14, 14, 14),
                Block.box(-15, 14, 1, 15, 16, 15),
                Block.box(-13, 0, 3, 13, 14, 13)
        );

        VoxelShape WARDROBE_BOTTOM = shape(
                box(12, 0, 2, 14, 3, 5),
                box(-14, 0, 2, -12, 3, 5),
                box(-14, 0, 11, -12, 3, 14),
                box(12, 0, 11, 14, 3, 14),
                box(12, 3, 11, 14, 4, 13),
                box(12, 3, 3, 14, 4, 5),
                box(-14, 3, 3, -12, 4, 5),
                box(-14, 3, 11, -12, 4, 13),
                box(-15, 4, 1, 15, 6, 15),
                box(12, 6, 2, 14, 30, 4),
                box(-14, 6, 2, -12, 30, 4),
                box(-14, 6, 12, -12, 30, 14),
                box(12, 6, 12, 14, 30, 14),
                box(-12, 6, 3, 12, 30, 13),
                box(-15, 30, 1, 15, 32, 15)
        );

        VoxelShape PAINTING_WIDE = shape(
                box(-16, 0, 14, -13, 3, 16),
                box(-16, 13, 14, -13, 16, 16),
                box(13, 13, 14, 16, 16, 16),
                box(13, 0, 14, 16, 3, 16),
                box(-13, 1, 14, 13, 3, 16),
                box(-13, 13, 14, 13, 15, 16),
                box(-15, 3, 14, 15, 13, 16)
        );

        VoxelShape PAINTING_SMALL = shape(
                box(0, 0, 14, 3, 3, 16),
                box(0, 13, 14, 3, 16, 16),
                box(13, 13, 14, 16, 16, 16),
                box(13, 0, 14, 16, 3, 16),
                box(3, 1, 14, 13, 3, 16),
                box(3, 13, 14, 13, 15, 16),
                box(1, 3, 14, 15, 13, 16)
        );

        VoxelShape OVEN = shape(
                Block.box(0, 0, 0, 3.5, 4, 3.5),
                Block.box(0, 0, 12.5, 3.5, 4, 16),
                Block.box(12.5, 0, 12.5, 16, 4, 16),
                Block.box(12.5, 0, 0, 16, 4, 3.5),
                Block.box(0, 4, 0, 16, 6, 16),
                Block.box(0, 14, 0, 16, 16, 16),
                Block.box(0.5, 6, 0.5, 15.5, 14, 15.5),
                Block.box(2.5, 7, -0.5, 12.5, 13, 0.5)
        );

        VoxelShape DOOR_DOUBLE = shape(
                Block.box(0, 0, 0.5, 14, 30, 2.5),
                Block.box(14, 0, 0, 16, 32, 3),
                Block.box(13, 25, 0, 14, 32, 3),
                Block.box(12, 27, 0, 13, 32, 3),
                Block.box(9, 28, 0, 12, 32, 3),
                Block.box(5, 29, 0, 9, 32, 3),
                Block.box(0, 30, 0, 5, 32, 3)
        );

        VoxelShape DOOR_SINGLE = shape(
                Block.box(0, 0, 0, 2, 32, 3),
                Block.box(2, 30, 0, 14, 32, 3),
                Block.box(14, 0, 0, 16, 32, 3),
                Block.box(2, 0, 0.5, 14, 30, 2.5)
        );

        VoxelShape BED_SINGLE = shape(
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

        VoxelShape BED_DOUBLE = shape(
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

        VoxelShape SHELF_SINGLE = shape(
                box(0, 14, 0, 16, 16, 16),
                box(13, 11, 3, 15, 14, 6),
                box(13, 12, 6, 15, 14, 16),
                box(13, 8, 14, 15, 12, 16),
                box(13, 5, 13, 15, 8, 16),
                box(1, 5, 13, 3, 8, 16),
                box(1, 11, 3, 3, 14, 6),
                box(1, 12, 6, 3, 14, 16),
                box(1, 8, 14, 3, 12, 16)
        );

        VoxelShape SHELF_CENTER = box(0, 14, 0, 16, 16, 16);

        VoxelShape SHELF_LEFT = shape(
                box(0, 14, 0, 16, 16, 16),
                box(13, 11, 3, 15, 14, 6),
                box(13, 12, 6, 15, 14, 16),
                box(13, 8, 14, 15, 12, 16),
                box(13, 5, 13, 15, 8, 16)
        );

        VoxelShape SHELF_RIGHT = shape(
                box(0, 14, 0, 16, 16, 16),
                box(1, 11, 3, 3, 14, 6),
                box(1, 12, 6, 3, 14, 16),
                box(1, 8, 14, 3, 12, 16),
                box(1, 5, 13, 3, 8, 16)
        );

        VoxelShape SOFA_SINGLE = shape(
                box(13, 0, 12, 15, 3, 15),
                box(13, 0, 1, 15, 3, 4),
                box(1, 0, 1, 3, 3, 4),
                box(1, 0, 12, 3, 3, 15),
                box(1, 3, 12, 3, 4, 14),
                box(13, 3, 12, 15, 4, 14),
                box(13, 3, 2, 15, 4, 4),
                box(1, 3, 2, 3, 4, 4),
                box(1, 4, 1.5, 15, 6, 14.5),
                box(13, 6, 2, 15, 9, 12),
                box(1, 6, 2, 3, 9, 12),
                box(1, 6, 12, 15, 13, 14),
                box(2, 13, 12, 14, 15, 14),
                box(4, 15, 12, 12, 16, 14)
        );

        VoxelShape SOFA_CENTER = shape(
                Block.box(0, 4, 1.5, 16, 6, 14.5),
                Block.box(0, 6, 12, 16, 16, 14)
        );

        VoxelShape SOFA_LEFT = shape(
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

        VoxelShape SOFA_RIGHT = shape(
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

        VoxelShape SOFA_CORNER = shape(
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

        VoxelShape COUNTER_SINGLE = shape(
                Block.box(0, 0, 3, 16, 13, 16),
                Block.box(0, 13, 0, 16, 16, 16)
        );

        VoxelShape COUNTER_CORNER = shape(
                Block.box(0, 0, 0, 13, 13, 3),
                Block.box(0, 0, 3, 16, 13, 16),
                Block.box(0, 13, 0, 16, 16, 16)
        );

        static VoxelShape getShelfShape(ShelfBlock block, BlockState blockState)
        {
            return AllVoxelShapes.getShelfShape(shelfType -> switch(shelfType) {
                case LEFT -> SHELF_LEFT;
                case RIGHT -> SHELF_RIGHT;
                case SINGLE -> SHELF_SINGLE;
                case CENTER -> SHELF_CENTER;
            }, block, blockState);
        }

        static VoxelShape getSofaShape(SofaBlock block, BlockState blockState)
        {
            return AllVoxelShapes.getSofaShape(sofaShape -> switch(sofaShape) {
                case LEFT -> SOFA_LEFT;
                case RIGHT -> SOFA_RIGHT;
                case SINGLE -> SOFA_SINGLE;
                case CENTER -> SOFA_CENTER;
                case CORNER -> SOFA_CORNER;
            }, block, blockState);
        }

        static VoxelShape getCounterShape(CounterBlock block, BlockState blockState)
        {
            return AllVoxelShapes.getCounterShape(counterType -> switch(counterType) {
                case SINGLE -> COUNTER_SINGLE;
                case CORNER -> COUNTER_CORNER;
            }, block, blockState);
        }

        private static void bootstrap() {}
    }

    interface Venthyr
    {
        VoxelShape WALL_LIGHT = shape(
                box(6D, 1D, 15D, 10D, 3D, 16D),
                box(5D, 3D, 15D, 11D, 12D, 16D),
                box(6D, 12D, 15D, 10D, 14D, 16D),
                box(7D, 3.5D, 14D, 9D, 5.5D, 15D),
                box(4.25D, 2.5D, 10.5D, 11.75D, 11.5D, 14D)
        );

        VoxelShape FLOOR_LIGHT = shape(
                box(6D, 0D, 6D, 10D, 2D, 10D),
                box(7D, 2D, 7D, 9D, 20D, 9D),
                box(4D, 17.75D, 7D, 7D, 21.75D, 9D),
                box(9D, 17.75D, 7D, 12D, 21.75D, 9D),
                box(7D, 17.75D, 4D, 9D, 21.75D, 7D),
                box(7D, 17.75D, 9D, 9D, 21.75D, 12D),
                box(2.5D, 20.75D, 2.5D, 13.5D, 24D, 13.5D),
                box(10.25D, 24D, 10.25D, 12.5D, 28.75D, 12.5D),
                box(3.5D, 24D, 10.25D, 5.75D, 28.75D, 12.5D),
                box(3.5D, 24D, 3.5D, 5.75D, 28.75D, 5.75D),
                box(10.25D, 24D, 3.5D, 12.5D, 28.75D, 5.75D)
        );

        VoxelShape TABLE_LARGE = shape(
                box(-15D, 0D, 1D, -12D, 2D, 4D),
                box(12D, 0D, 1D, 15D, 2D, 4D),
                box(12D, 0D, 28D, 15D, 2D, 31D),
                box(-15D, 0D, 28D, -12D, 2D, 31D),
                box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
                box(-14.5D, 2D, 28.5D, -12.5D, 13D, 30.5D),
                box(12.5D, 2D, 28.5D, 14.5D, 13D, 30.5D),
                box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
                box(-16D, 13D, 0D, 16D, 16D, 32D)
        );

        VoxelShape TABLE_SMALL = shape(
                box(1D, 0D, 1D, 4D, 2D, 4D),
                box(1D, 0D, 12D, 4D, 2D, 15D),
                box(12D, 0D, 12D, 15D, 2D, 15D),
                box(12D, 0D, 1D, 15D, 2D, 4D),
                box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
                box(1.5D, 2D, 1.5D, 3.5D, 13D, 3.5D),
                box(1.5D, 2D, 12.5D, 3.5D, 13, 14.5D),
                box(12.5D, 2D, 12.5D, 14.5D, 13D, 14.5D),
                box(0D, 13D, 0D, 16D, 16D, 16D)
        );

        VoxelShape TABLE_WIDE = shape(
                box(12D, 0D, 1D, 15D, 2D, 4D),
                box(-15D, 0D, 1D, -12D, 2D, 4D),
                box(-15D, 0D, 12D, -12D, 2D, 15D),
                box(12D, 0D, 12D, 15D, 2D, 15D),
                box(12.5D, 2D, 12.5D, 14.5D, 13D, 14.5D),
                box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
                box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
                box(-14.5D, 2D, 12.5D, -12.5D, 13D, 14.5D),
                box(-16D, 13D, 0D, 16D, 16D, 16D)
        );

        VoxelShape BENCH = shape(
                box(-14D, 0D, 2D, -11D, 4D, 5D),
                box(-14D, 0D, 11D, -11D, 4D, 14D),
                box(11D, 0D, 11D, 14D, 4D, 14D),
                box(11D, 0D, 2D, 14D, 4D, 5D),
                box(-15D, 4D, 1D, 15D, 7D, 15D)
        );

        VoxelShape CHAIR = shape(
                box(1D, 0D, 1D, 4D, 5D, 4D),
                box(12D, 0D, 1D, 15D, 5D, 4D),
                box(12D, 0D, 12D, 15D, 5D, 15D),
                box(1D, 0D, 12D, 4D, 5D, 15D),
                box(.5D, 5D, .5D, 15.5D, 9D, 15.5D),
                box(1D, 9D, 12D, 15D, 31D, 15D)
        );

        VoxelShape CEILING_LIGHT = box(1D, 0D, 1D, 15, 16D, 15D);

        VoxelShape CUSHION = shape(
                box(2D, 0D, 2D, 5D, 3D, 5D),
                box(2D, 0D, 11D, 5D, 3D, 14D),
                box(11D, 0D, 11D, 14D, 3D, 14D),
                box(11D, 0D, 2D, 14D, 3D, 5D),
                box(1D, 3D, 1D, 15D, 4D, 15D),
                box(2D, 4D, 2D, 14D, 7D, 14D)
        );

        VoxelShape STOOL = shape(
                box(2D, 0D, 2D, 5D, 4D, 5D),
                box(2D, 0D, 11D, 5D, 4D, 14D),
                box(11D, 0D, 11D, 14D, 4D, 14D),
                box(11D, 0D, 2D, 14D, 4D, 5D),
                box(1D, 4D, 1D, 15D, 7D, 15D)
        );

        VoxelShape CHEST = box(-13D, 0D, 1D, 13D, 14.25D, 15D);

        VoxelShape BOOKSHELF = shape(
                box(-16D, 0D, 2D, 16D, 3D, 16D),
                box(-15D, 3D, 3D, 15D, 29D, 16D),
                box(-16D, 29D, 2D, 16D, 32D, 16D)
        );

        VoxelShape DESK_LEFT = shape(
                box(-15D, 0D, 1D, -12D, 2D, 4D),
                box(-15D, 0D, 12D, -12D, 2D, 15D),
                box(12D, 0D, 12D, 15, 2D, 15D),
                box(12D, 0D, 1D, 15, 2D, 4D),
                box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
                box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
                box(-14.5D, 2D, 12.5D, -12.5D, 13D, 14.5D),
                box(12.5D, 2D, 12.5D, 14.5D, 13, 14.5D),
                box(-16D, 13D, 0D, 16D, 16D, 16D),
                box(5D, 9D, 2, 12D, 13D, 11D),
                box(12.5D, 9D, 3.5D, 14.5D, 13D, 12.5D),
                box(-14.5D, 9D, 3.5D, -12.5D, 13D, 12.5D)
        );

        VoxelShape DESK_RIGHT = shape(
                box(-15D, 0D, 1D, -12D, 2D, 4D),
                box(-15D, 0D, 12D, -12D, 2D, 15D),
                box(12D, 0D, 12D, 15D, 2D, 15D),
                box(12D, 0D, 1D, 15D, 2D, 4D),
                box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
                box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
                box(-14.5D, 2D, 12.5D, -12.5D, 13D, 14.5D),
                box(12.5D, 2D, 12.5D, 14.5D, 13D, 14.5D),
                box(-16D, 13D, 0D, 16D, 16D, 16D),
                box(-12D, 9D, 2D, -5D, 13D, 11D),
                box(12.5D, 9D, 3.5D, 14.5D, 13D, 12.5D),
                box(-14.5D, 9D, 3.5D, -12.5D, 13D, 12.5D)
        );

        VoxelShape DRAWER = shape(
                box(.5D, 0D, .5D, 3.5D, 2D, 3.5D),
                box(.5D, 0D, 12.5D, 3.5D, 2D, 15.5D),
                box(12.5D, 0D, 12.5D, 15.5D, 2D, 15.5D),
                box(12.5D, 0D, .5D, 15.5D, 2D, 3.5D),
                box(13D, 2D, 1D, 15D, 13D, 3D),
                box(1D, 2D, 1D, 3D, 13D, 3D),
                box(1D, 2D, 13D, 3D, 13D, 15D),
                box(13D, 2D, 13D, 15D, 13D, 15D),
                box(1D, 5D, 1D, 15D, 13D, 15D),
                box(0D, 13D, 0D, 16D, 16D, 16D)
        );

        VoxelShape DRESSER = shape(
                box(-15.5D, 0D, .5D, -12.5D, 2D, 3.5D),
                box(-15.5D, 0D, 12.5D, -12.5D, 2D, 15.5D),
                box(12.5D, 0D, 12.5D, 15.5D, 2D, 15.5D),
                box(12.5D, 0D, .5D, 15.5D, 2D, 3.5D),
                box(13D, 2D, 1D, 15D, 13D, 3D),
                box(-15D, 2D, 1D, -13D, 13D, 3D),
                box(-15D, 2D, 13D, -13D, 13D, 15D),
                box(13D, 2D, 13D, 15D, 13D, 15D),
                box(-15D, 5D, 1D, 15D, 13D, 15D),
                box(-16D, 13D, 0D, 16D, 16D, 16D)
        );

        VoxelShape LOCKBOX = shape(
                box(2, 1, 4, 14, 10, 12),
                box(1.5, 6, 3.5, 14.5, 7, 12.5),
                box(1.5, 1, 3.5, 14.5, 2, 12.5),
                box(7, 4, 3.5, 9, 6, 4.25),
                box(12, 0, 4, 14, 1, 6),
                box(2, 0, 4, 4, 1, 6),
                box(2, 0, 10, 4, 1, 12),
                box(12, 0, 10, 14, 1, 12)
        );

        VoxelShape WARDROBE_TOP = shape(
                box(-15D, 0D, 1D, 15D, 9D, 15D),
                box(-16D, 9D, 0D, 16D, 11D, 16D)
        );

        VoxelShape WARDROBE_BOTTOM = shape(
                box(-16D, 0D, 0D, -12D, 3D, 4D),
                box(-16D, 0D, 12D, -12D, 3D, 16D),
                box(12D, 0D, 12D, 16D, 3D, 16D),
                box(12D, 0D, 0D, 16D, 3D, 4D),
                box(-15D, 1D, 1D, 15D, 29D, 15D),
                box(-16D, 29D, 0D, 16D, 32D, 16D)
        );

        VoxelShape PAINTING_WIDE = box(-16D, 0D, 14D, 16D, 16D, 16D);

        VoxelShape PAINTING_SMALL = box(0D, 0D, 14D, 16D, 16D, 16D);

        VoxelShape OVEN = shape(
                Block.box(1, 0, 1, 4, 2, 4),
                Block.box(1, 0, 12, 4, 2, 15),
                Block.box(12, 0, 12, 15, 2, 15),
                Block.box(12, 0, 1, 15, 2, 4),
                Block.box(0, 2, 0, 16, 4, 16),
                Block.box(0, 14, 0, 16, 16, 16),
                Block.box(1, 4, 1, 15, 14, 15),
                Block.box(3, 5, 0, 13, 13, 1)
        );

        VoxelShape DOOR_DOUBLE = shape(
                box(0, 0, 0, 16, 1, 3),
                box(14, 1, 0, 16, 32, 3),
                box(0, 31, 0, 14, 32, 3),
                box(0, 0, 0, 1, 31, 3),
                box(6, 30, 0, 14, 31, 3),
                box(8, 29, 0, 14, 30, 3),
                box(10, 27, 0, 14, 29, 3),
                box(11, 25, 0, 14, 27, 3),
                box(12, 22, 0, 14, 25, 3),
                box(13, 17, 0, 14, 22, 3),
                box(1, 1, 0.5, 14, 31, 2.5)
        );

        VoxelShape DOOR_SINGLE = shape(
                box(0, 0, 0, 16, 1, 3),
                box(14, 1, 0, 16, 32, 3),
                box(0, 30, 0, 14, 32, 3),
                box(0, 1, 0, 1, 30, 3),
                box(1, 1, 0.5, 14, 30, 2.5)
        );

        VoxelShape BED_SINGLE = shape(
                box(0D, 0D, 29D, 3D, 2D, 32D),
                box(13D, 0D, 29D, 16D, 2D, 32D),
                box(13D, 0D, 0D, 16D, 2D, 3D),
                box(0D, 0D, 0D, 3D, 2D, 3D),
                box(.5D, 2D, .5D, 2.5D, 12D, 2.5D),
                box(.5D, 2D, 29.5D, 2.5D, 12D, 31.5D),
                box(13.5D, 2D, 29.5D, 15.5D, 12D, 31.5D),
                box(13.5D, 2D, .5D, 15.5D, 12D, 2.5D),
                box(13D, 12D, 0D, 16D, 14D, 3D),
                box(0D, 12D, 0D, 3D, 14D, 3D),
                box(0D, 12D, 29D, 3D, 14D, 32D),
                box(13D, 12D, 29D, 16D, 14D, 32D),
                box(.5D, 0D, 2D, 15.5D, 5D, 30D),
                box(2.5D, 0D, 29.5D, 13.5D, 11D, 31.5D),
                box(2.5D, 0D, .5D, 13.5D, 13D, 2.5D),
                box(1.5D, 5D, 2.5D, 14.5D, 8D, 29.5D)
        );

        VoxelShape BED_DOUBLE = shape(
                box(-16D, 0D, 0D, -13D, 2D, 3D),
                box(-15.5D, 2D, .5D, -13.5D, 12D, 2.5D),
                box(-15.5D, 2D, 29.5D, -13.5D, 12D, 31.5D),
                box(13.5D, 2D, 29.5D, 15.5D, 12D, 31.5D),
                box(13.5D, 2D, .5D, 15.5D, 12D, 2.5D),
                box(-16D, 12D, 0D, -13D, 14D, 3D),
                box(-16D, 12D, 29D, -13D, 14D, 32D),
                box(13D, 12D, 29D, 16D, 14D, 32D),
                box(13D, 12D, 0D, 16D, 14D, 3D),
                box(13D, 0D, 0D, 16D, 2D, 3D),
                box(13D, 0D, 29D, 16D, 2D, 32D),
                box(-16D, 0D, 29D, -13D, 2D, 32D),
                box(-15.5D, 0D, 2.5D, 15.5D, 5D, 29.5D),
                box(-13.5D, 0D, 29.5D, 13.5D, 12D, 31.5D),
                box(-13.5D, 0D, .5D, 13.5D, 14D, 2.5D),
                box(-14.5D, 0D, 2.5D, 14.5D, 8D, 29.5D)
        );

        VoxelShape SHELF_SINGLE = shape(
                box(0D, 13D, 0D, 16D, 16D, 16D),
                box(13D, 9D, 10D, 16D, 13D, 16D),
                box(13D, 11D, 3D, 16D, 13D, 10D),
                box(13D, 10D, 0D, 16D, 13D, 3D),
                box(13D, 6D, 13D, 16D, 9D, 16D),
                box(13D, 3D, 12D, 16D, 6D, 16D),
                box(0D, 3D, 12D, 3D, 6D, 16D),
                box(0D, 9D, 10D, 3D, 13D, 16D),
                box(0D, 11D, 3D, 3D, 13D, 10D),
                box(0D, 10D, 0D, 3D, 13D, 3D),
                box(0D, 6D, 13D, 3D, 9D, 16D)
        );

        VoxelShape SHELF_CENTER = box(0D, 13D, 0D, 16D, 16D, 16D);

        VoxelShape SHELF_LEFT = shape(
                box(0D, 13D, 0D, 16D, 16D, 16D),
                box(13D, 3D, 12D, 16D, 6D, 16D),
                box(13D, 9D, 10D, 16D, 13D, 16D),
                box(13D, 11D, 3D, 16D, 13D, 10D),
                box(13D, 10D, 0D, 16D, 13D, 3D),
                box(13D, 6D, 13D, 16D, 9D, 16D)
        );

        VoxelShape SHELF_RIGHT = shape(
                box(0D, 3D, 12D, 3D, 6D, 16D),
                box(0D, 6D, 13D, 3D, 9D, 16D),
                box(0D, 9D, 10D, 3D, 13D, 16D),
                box(0D, 11D, 3D, 3D, 13D, 10D),
                box(0D, 10D, 0D, 3D, 13D, 3D),
                box(0D, 13D, 0D, 16D, 16D, 16D)
        );

        VoxelShape SOFA_SINGLE = shape(
                box(1D, 0D, 1D, 4D, 2D, 4D),
                box(1D, 0D, 12D, 4D, 2D, 15D),
                box(12D, 0D, 12D, 15D, 2D, 15D),
                box(12D, 0D, 1D, 15D, 2D, 4D),
                box(0D, 2D, 0D, 16D, 6D, 16D),
                box(13D, 6D, 0D, 16D, 10D, 13D),
                box(0D, 6D, 0D, 3D, 10D, 13D),
                box(0D, 6D, 13D, 16D, 16D, 16D)
        );

        VoxelShape SOFA_CENTER = shape(
                Block.box(0, 2, 0, 16, 6, 16),
                Block.box(0, 6, 13, 16, 16, 16)
        );

        VoxelShape SOFA_LEFT = shape(
                Block.box(12, 0, 1, 15, 2, 4),
                Block.box(12, 0, 12, 15, 2, 15),
                Block.box(0, 2, 0, 16, 6, 16),
                Block.box(13, 6, 0, 16, 10, 13),
                Block.box(0, 6, 13, 16, 16, 16)
        );

        VoxelShape SOFA_RIGHT = shape(
                Block.box(1, 0, 1, 4, 2, 4),
                Block.box(1, 0, 12, 4, 2, 15),
                Block.box(0, 2, 13, 16, 16, 16),
                Block.box(0, 2, 0, 3, 10, 13),
                Block.box(3, 2, 0, 16, 6, 13)
        );

        VoxelShape SOFA_CORNER = shape(
                box(1D, 0D, 1D, 4D, 2D, 4D),
                box(1D, 0D, 12D, 4D, 2D, 15D),
                box(12D, 0D, 12D, 15D, 2D, 15D),
                box(12D, 0D, 1D, 15D, 2D, 4D),
                box(13D, 6D, 0D, 16D, 16D, 16D),
                box(0D, 6D, 13D, 13D, 16D, 16D),
                box(0D, 2D, 0D, 16D, 6D, 16D)
        );

        VoxelShape COUNTER_SINGLE = shape(
                box(0, 0, 3, 16, 13, 16),
                box(0, 13, 0, 16, 16, 16),
                box(1, 1, 2, 15, 12, 3)
        );

        VoxelShape COUNTER_CORNER = shape(
                box(0, 0, 3, 16, 13, 16),
                box(0, 13, 0, 16, 16, 16),
                box(1, 1, 2, 15, 12, 3)
        );

        static VoxelShape getShelfShape(ShelfBlock block, BlockState blockState)
        {
            return AllVoxelShapes.getShelfShape(shelfType -> switch(shelfType) {
                case LEFT -> SHELF_LEFT;
                case RIGHT -> SHELF_RIGHT;
                case SINGLE -> SHELF_SINGLE;
                case CENTER -> SHELF_CENTER;
            }, block, blockState);
        }

        static VoxelShape getSofaShape(SofaBlock block, BlockState blockState)
        {
            return AllVoxelShapes.getSofaShape(sofaShape -> switch(sofaShape) {
                case LEFT -> SOFA_LEFT;
                case RIGHT -> SOFA_RIGHT;
                case SINGLE -> SOFA_SINGLE;
                case CENTER -> SOFA_CENTER;
                case CORNER -> SOFA_CORNER;
            }, block, blockState);
        }

        static VoxelShape getCounterShape(CounterBlock block, BlockState blockState)
        {
            return AllVoxelShapes.getCounterShape(counterType -> switch(counterType) {
                case SINGLE -> COUNTER_SINGLE;
                case CORNER -> COUNTER_CORNER;
            }, block, blockState);
        }

        private static void bootstrap() {}
    }

    static void bootstrap()
    {
        Bone.bootstrap();
        Dunmer.bootstrap();
        Necrolord.bootstrap();
        Nordic.bootstrap();
        Royal.bootstrap();
        Venthyr.bootstrap();
    }

    static VoxelShape getWallLightShape(VoxelShape current, WallLightBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        return VoxelShapeHelper.rotateHorizontal(current, facing);
    }

    static VoxelShape getFloorLightShape(VoxelShape current, FloorLightBlock block, BlockState blockState)
    {
        var shape = current;

        if(!block.getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState)) shape = shape.move(0D, -1D, 0D);

        if(blockState.hasProperty(HorizontalFacingComponent.FACING))
        {
            var facing = blockState.getValue(HorizontalFacingComponent.FACING);
            return VoxelShapeHelper.rotateHorizontal(shape, facing);
        }

        return shape;
    }

    static VoxelShape getTableWideShape(VoxelShape current, TableWideBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);

        if(!block.getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState))
        {
            var offset = facing.getClockWise();
            shape = shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        return shape;
    }

    static VoxelShape getTableLargeShape(VoxelShape current, TableLargeBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);
        var index = block.getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().getIndex(blockState);

        if(index == 1 || index == 3)
        {
            var offset = facing.getClockWise();
            shape = shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        if(index == 2 || index == 3) shape = shape.move(facing.getStepX(), 0D, facing.getStepZ());

        return shape;
    }

    static VoxelShape getBenchShape(VoxelShape current, BenchBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);

        if(!block.getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState))
        {
            var other = facing.getClockWise();
            shape = shape.move(other.getStepX(), 0D, other.getStepZ());
        }

        return shape;
    }

    static VoxelShape getChairShape(VoxelShape current, ChairBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);
        if(!block.getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState)) shape = shape.move(0D, -1D, 0D);
        return shape;
    }

    static VoxelShape getCushionShape(VoxelShape current, CushionBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        return VoxelShapeHelper.rotateHorizontal(current, facing);
    }

    static VoxelShape getStoolShape(VoxelShape current, StoolBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        return VoxelShapeHelper.rotateHorizontal(current, facing);
    }

    static VoxelShape getChestShape(VoxelShape current, ChestBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);

        if(!block.getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState))
        {
            var offset = facing.getClockWise();
            return shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        return shape;
    }

    static VoxelShape getBookshelfShape(VoxelShape current, BookshelfBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);
        var index = block.getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().getIndex(blockState);

        if(index == 2 || index == 3)
        {
            var offset = facing.getClockWise();
            shape = shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        if(index == 1 || index == 3) shape = shape.move(0D, -1D, 0D);

        return shape;
    }

    static VoxelShape getDeskShape(VoxelShape current, DeskBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);

        if(!block.getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState))
        {
            var offset = facing.getClockWise();
            return shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        return shape;
    }

    static VoxelShape getDrawerShape(VoxelShape current, DrawerBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        return VoxelShapeHelper.rotateHorizontal(current, facing);
    }

    static VoxelShape getDresserShape(VoxelShape current, DresserBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);

        if(!block.getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState))
        {
            var offset = facing.getClockWise();
            return shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        return shape;
    }

    static VoxelShape getLockboxShape(VoxelShape current, LockboxBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        return VoxelShapeHelper.rotateHorizontal(current, facing);
    }

    static VoxelShape getWardrobeTopShape(VoxelShape current, WardrobeTopBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);

        if(!block.getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState))
        {
            var offset = facing.getClockWise();
            return shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        return shape;
    }

    static VoxelShape getWardrobeBottomShape(VoxelShape current, WardrobeBottomBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);
        var index = block.getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().getIndex(blockState);

        if(index == 2 || index == 3)
        {
            var offset = facing.getClockWise();
            shape = shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        if(index == 1 || index == 3) shape = shape.move(0D, -1D, 0D);

        return shape;
    }

    static VoxelShape getPaintingSmallShape(VoxelShape current, PaintingSmallBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        return VoxelShapeHelper.rotateHorizontal(current, facing);
    }

    static VoxelShape getPaintingWideShape(VoxelShape current, PaintingWideBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);

        if(!block.getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState))
        {
            var offset = facing.getClockWise();
            return shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        return shape;
    }

    static VoxelShape getOvenShape(VoxelShape current, OvenBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);

        var multiBlockComponent = block.getComponent(ComponentTypes.MULTI_BLOCK);

        if(multiBlockComponent != null && !multiBlockComponent.getMultiBlockType().isOrigin(blockState))
        {
            var offset = facing.getClockWise();
            return shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        return shape;
    }

    static VoxelShape getDoorShape(VoxelShape current, DoorBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING).getOpposite();
        var open = blockState.getValue(DoorComponent.OPEN);
        var hinge = blockState.getValue(DoorComponent.HINGE);

        Direction shapeFacing;

        if(hinge == DoorHingeSide.LEFT) shapeFacing = open ? facing.getCounterClockWise() : facing;
        else shapeFacing = open ? facing.getOpposite().getClockWise() : facing.getOpposite();

        var shape = VoxelShapeHelper.rotateHorizontal(current, shapeFacing);

        var x = 0D;
        var y = 0D;
        var z = 0D;

        if(!block.getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState)) y -= 1D;

        x -= shapeFacing.getStepX() * .8125D;
        z -= shapeFacing.getStepZ() * .8125D;

        if((hinge == DoorHingeSide.LEFT && !open) || (hinge == DoorHingeSide.RIGHT && open))
        {
            x += shapeFacing.getStepX() * .8125D;
            z += shapeFacing.getStepZ() * .8125D;
        }

        return shape.move(x, y, z);
    }

    static VoxelShape getBedSingleShape(VoxelShape current, BedSingleBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);
        if(!block.getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState)) shape = shape.move(facing.getStepX(), 0D, facing.getStepZ());
        return shape;
    }

    static VoxelShape getBedDoubleShape(VoxelShape current, BedDoubleBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);
        var index = block.getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().getIndex(blockState);

        if(index == 2 || index == 3) shape = shape.move(facing.getStepX(), 0D, facing.getStepZ());

        if(index == 1 || index == 3)
        {
            var offset = facing.getClockWise();
            shape = shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        return shape;
    }

    private static VoxelShape getShelfShape(Function<ShelfType, VoxelShape> shapeGetter, ShelfBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        var shelfType = blockState.getValue(ModBlockStateProperties.SHELF_TYPE);
        var current = shapeGetter.apply(shelfType);
        return VoxelShapeHelper.rotateHorizontal(current, facing);
    }

    private static VoxelShape getSofaShape(Function<SofaType, VoxelShape> shapeGetter, SofaBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        var sofaType = blockState.getValue(ModBlockStateProperties.SOFA_TYPE);
        var current = shapeGetter.apply(sofaType);
        return VoxelShapeHelper.rotateHorizontal(current, facing);
    }

    private static VoxelShape getCounterShape(Function<CounterType, VoxelShape> shapeGetter, CounterBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        var counterType = blockState.getValue(ModBlockStateProperties.COUNTER_TYPE);
        var current = shapeGetter.apply(counterType);
        return VoxelShapeHelper.rotateHorizontal(current, facing);
    }

    private static VoxelShape shape(VoxelShape... shapes)
    {
        return VoxelShapeHelper.combine(shapes);
    }

    private static VoxelShape box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ)
    {
        return Block.box(minX, minY, minZ, maxX, maxY, maxZ);
    }
}
