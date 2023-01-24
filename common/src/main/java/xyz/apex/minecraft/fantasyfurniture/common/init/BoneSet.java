package xyz.apex.minecraft.fantasyfurniture.common.init;

public interface BoneSet
{
    static void bootstrap()
    {
        Wither.bootstrap();
        Skeleton.bootstrap();
    }

    interface Wither
    {
        private static void bootstrap()
        {
        }
    }

    interface Skeleton
    {
        private static void bootstrap()
        {
        }
    }
}
