package xyz.apex.minecraft.fantasyfurniture.shared.init;

import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.shared.multiblock.SimpleMultiBlock;

public interface AllMultiBlockTypes
{
    MultiBlockType MB_1x2x1 = MultiBlockType.builder(1, 2, 1).build();

    MultiBlockType MB_1x1x2_FACING = MultiBlockType.builder(1, 1, 2).rotateLocalSpaceForFacing(SimpleMultiBlock.WithHorizontalFacing.FACING).build();
    MultiBlockType MB_2x1x2_FACING = MultiBlockType.builder(2, 1, 2).rotateLocalSpaceForFacing(SimpleMultiBlock.WithHorizontalFacing.FACING).build();
    MultiBlockType MB_1x2x1_FACING = MB_1x2x1.copy().rotateLocalSpaceForFacing(SimpleMultiBlock.WithHorizontalFacing.FACING).build();

    static void bootstrap() {}
}
