package xyz.apex.minecraft.fantasyfurniture.shared.init;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.minecraft.apexcore.shared.multiblock.SimpleMultiBlock;
import xyz.apex.minecraft.apexcore.shared.util.VoxelShapeHelper;
import xyz.apex.minecraft.fantasyfurniture.shared.block.*;

public interface AllVoxelShapes
{
    interface Bone
    {
        interface Wither
        {
            private static void bootstrap() {}
        }

        interface Skeleton
        {
            private static void bootstrap() {}
        }

        private static void bootstrap()
        {
            Wither.bootstrap();
            Skeleton.bootstrap();
        }
    }

    interface Dunmer
    {
        private static void bootstrap() {}
    }

    interface Necrolord
    {
        private static void bootstrap() {}
    }

    interface Nordic
    {
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

        VoxelShape CHANDELIER = box(1D, 0D, 1D, 15, 16D, 15D);

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

        private static void bootstrap() {}
    }

    interface Royal
    {
        private static void bootstrap() {}
    }

    interface Venthyr
    {
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

    static VoxelShape getFloorLightShape(VoxelShape current, FloorLightBlock block, BlockState blockState)
    {
        if(block.getMultiBlockType().isOrigin(blockState)) return current;
        return current.move(0D, -1D, 0D);
    }

    static VoxelShape getTableWideShape(VoxelShape current, SimpleMultiBlock.WithHorizontalFacing block, BlockState blockState)
    {
        var facing = blockState.getValue(SimpleMultiBlock.WithHorizontalFacing.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);

        if(!block.getMultiBlockType().isOrigin(blockState))
        {
            var offset = facing.getClockWise();
            shape = shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        return shape;
    }

    static VoxelShape getTableLargeShape(VoxelShape current, SimpleMultiBlock.WithHorizontalFacing block, BlockState blockState)
    {
        var facing = blockState.getValue(SimpleMultiBlock.WithHorizontalFacing.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);
        var index = block.getMultiBlockType().getIndex(blockState);

        if(index == 1 || index == 3)
        {
            var offset = facing.getClockWise();
            shape = shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        if(index == 2 || index == 3) shape = shape.move(facing.getStepX(), 0D, facing.getStepZ());

        return shape;
    }

    static VoxelShape getBenchShape(VoxelShape current, SimpleSeatBlock.WithMultiBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(SimpleSeatBlock.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);

        if(!block.getMultiBlockType().isOrigin(blockState))
        {
            var other = facing.getClockWise();
            shape = shape.move(other.getStepX(), 0D, other.getStepZ());
        }

        return shape;
    }

    static VoxelShape getChairShape(VoxelShape current, SimpleSeatBlock.WithMultiBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(SimpleSeatBlock.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);
        if(!block.getMultiBlockType().isOrigin(blockState)) shape = shape.move(0D, -1D, 0D);
        return shape;
    }

    static VoxelShape getCushionShape(VoxelShape current, SimpleSeatBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(SimpleSeatBlock.FACING);
        return VoxelShapeHelper.rotateHorizontal(current, facing);
    }

    static VoxelShape getStoolShape(VoxelShape current, SimpleSeatBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(SimpleSeatBlock.FACING);
        return VoxelShapeHelper.rotateHorizontal(current, facing);
    }

    static VoxelShape getChestShape(VoxelShape current, ChestBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(SimpleMultiBlock.WithHorizontalFacing.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);

        if(!block.getMultiBlockType().isOrigin(blockState))
        {
            var offset = facing.getClockWise();
            return shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        return shape;
    }

    static VoxelShape getBookshelfShape(VoxelShape current, BookshelfBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(SimpleMultiBlock.WithHorizontalFacing.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);
        var index = block.getMultiBlockType().getIndex(blockState);

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
        var facing = blockState.getValue(SimpleMultiBlock.WithHorizontalFacing.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);

        if(!block.getMultiBlockType().isOrigin(blockState))
        {
            var offset = facing.getClockWise();
            return shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        return shape;
    }

    static VoxelShape getDrawerShape(VoxelShape current, DrawerBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(SimpleMultiBlock.WithHorizontalFacing.FACING);
        return VoxelShapeHelper.rotateHorizontal(current, facing);
    }

    static VoxelShape getDresserShape(VoxelShape current, DresserBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(SimpleMultiBlock.WithHorizontalFacing.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);

        if(!block.getMultiBlockType().isOrigin(blockState))
        {
            var offset = facing.getClockWise();
            return shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        return shape;
    }

    static VoxelShape getLockboxShape(VoxelShape current, LockboxBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(SimpleMultiBlock.WithHorizontalFacing.FACING);
        return VoxelShapeHelper.rotateHorizontal(current, facing);
    }

    static VoxelShape getWardrobeTopShape(VoxelShape current, SimpleMultiBlock.WithHorizontalFacing block, BlockState blockState)
    {
        var facing = blockState.getValue(SimpleMultiBlock.WithHorizontalFacing.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);

        if(!block.getMultiBlockType().isOrigin(blockState))
        {
            var offset = facing.getClockWise();
            return shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        return shape;
    }

    static VoxelShape getWardrobeBottomShape(VoxelShape current, WardrobeBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(SimpleMultiBlock.WithHorizontalFacing.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);
        var index = block.getMultiBlockType().getIndex(blockState);

        if(index == 2 || index == 3)
        {
            var offset = facing.getClockWise();
            shape = shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        if(index == 1 || index == 3) shape = shape.move(0D, -1D, 0D);

        return shape;
    }

    static VoxelShape getPaintingSmallShape(VoxelShape current, SimpleHorizontalFacingBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(SimpleMultiBlock.WithHorizontalFacing.FACING);
        return VoxelShapeHelper.rotateHorizontal(current, facing);
    }

    static VoxelShape getPaintingWideShape(VoxelShape current, SimpleMultiBlock.WithHorizontalFacing block, BlockState blockState)
    {
        var facing = blockState.getValue(SimpleMultiBlock.WithHorizontalFacing.FACING);
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);

        if(!block.getMultiBlockType().isOrigin(blockState))
        {
            var offset = facing.getClockWise();
            return shape.move(offset.getStepX(), 0D, offset.getStepZ());
        }

        return shape;
    }

    static VoxelShape getOvenShape(VoxelShape current, OvenBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(SimpleMultiBlock.WithHorizontalFacing.FACING);
        return VoxelShapeHelper.rotateHorizontal(current, facing);
    }

    static VoxelShape getDoorShape(VoxelShape current, DoorMultiBlock block, BlockState blockState)
    {
        var facing = blockState.getValue(SimpleMultiBlock.WithHorizontalFacing.FACING).getOpposite();
        var open = blockState.getValue(DoorMultiBlock.OPEN);
        var hinge = blockState.getValue(DoorMultiBlock.HINGE);

        /*if(hinge == DoorHingeSide.RIGHT) facing = facing.getOpposite();
        var shape = VoxelShapeHelper.rotateHorizontal(current, facing);

        if(open)
        {
            facing = hinge == DoorHingeSide.RIGHT ? facing.getClockWise() : facing.getCounterClockWise();
            shape = VoxelShapeHelper.rotateHorizontal(current, facing);
            //facing = hinge == DoorHingeSide.RIGHT ? facing.getCounterClockWise() : facing.getClockWise();
            shape = shape.move(-facing.getStepX(), 0D, -facing.getStepZ());
        }

        shape = block.getMultiBlockType().isOrigin(blockState) ? shape : shape.move(0D, -1D, 0D);*/

        Direction shapeFacing;

        if(hinge == DoorHingeSide.LEFT) shapeFacing = open ? facing.getCounterClockWise() : facing;
        else shapeFacing = open ? facing.getOpposite().getClockWise() : facing.getOpposite();

        var shape = VoxelShapeHelper.rotateHorizontal(current, shapeFacing);

        var x = 0D;
        var y = 0D;
        var z = 0D;

        if(!block.getMultiBlockType().isOrigin(blockState)) y -= 1D;

        x -= shapeFacing.getStepX() * .8125D;
        z -= shapeFacing.getStepZ() * .8125D;

        if((hinge == DoorHingeSide.LEFT && !open) || (hinge == DoorHingeSide.RIGHT && open))
        {
            x += shapeFacing.getStepX() * .8125D;
            z += shapeFacing.getStepZ() * .8125D;
        }

        return shape.move(x, y, z);
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
