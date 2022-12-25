package xyz.apex.minecraft.fantasyfurniture.shared.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.minecraft.apexcore.shared.util.VoxelShapeHelper;

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

    private static VoxelShape shape(VoxelShape... shapes)
    {
        return VoxelShapeHelper.combine(shapes);
    }

    private static VoxelShape box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ)
    {
        return Block.box(minX, minY, minZ, maxX, maxY, maxZ);
    }
}
