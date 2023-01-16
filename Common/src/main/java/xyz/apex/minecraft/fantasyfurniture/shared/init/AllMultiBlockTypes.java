package xyz.apex.minecraft.fantasyfurniture.shared.init;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.shared.multiblock.SimpleMultiBlock;
import xyz.apex.minecraft.fantasyfurniture.shared.block.BedMultiBlock;
import xyz.apex.minecraft.fantasyfurniture.shared.block.DoorMultiBlock;

public interface AllMultiBlockTypes
{
    MultiBlockType MB_1x2x1 = MultiBlockType.builder(1, 2, 1).build();

    MultiBlockType MB_1x1x2_FACING = MultiBlockType.builder(1, 1, 2).rotateLocalSpaceForFacing(SimpleMultiBlock.WithHorizontalFacing.FACING).build();
    MultiBlockType MB_1x2x2_FACING = MultiBlockType.builder(1, 2, 2).rotateLocalSpaceForFacing(SimpleMultiBlock.WithHorizontalFacing.FACING).build();
    MultiBlockType MB_2x1x2_FACING = MultiBlockType.builder(2, 1, 2).rotateLocalSpaceForFacing(SimpleMultiBlock.WithHorizontalFacing.FACING).build();
    MultiBlockType MB_1x2x1_FACING = MB_1x2x1.copy().rotateLocalSpaceForFacing(SimpleMultiBlock.WithHorizontalFacing.FACING).build();
    MultiBlockType MB_1x2x1_FACING_DOOR = MB_1x2x1_FACING.copy().placementStateModifier(AllMultiBlockTypes::modifyDoorBlockState).build();
    MultiBlockType MB_2x1x1_FACING_BED = MultiBlockType.builder(2, 1, 1).rotateLocalSpaceForFacing(SimpleMultiBlock.WithHorizontalFacing.FACING).placementStateModifier(AllMultiBlockTypes::modifyBedSingleBlockState).build();
    MultiBlockType MB_2x1x2_FACING_BED = MB_2x1x2_FACING.copy().placementStateModifier(AllMultiBlockTypes::modifyBedDoubleBlockState).build();

    static void bootstrap() {}

    private static BlockState modifyDoorBlockState(MultiBlockType multiBlockType, BlockPos pos, BlockState blockState, int index)
    {
        var doorHalf = index == MultiBlockType.ORIGIN_INDEX ? DoubleBlockHalf.LOWER : DoubleBlockHalf.UPPER;
        return blockState.setValue(DoorMultiBlock.HALF, doorHalf);
    }

    private static BlockState modifyBedSingleBlockState(MultiBlockType multiBlockType, BlockPos pos, BlockState blockState, int index)
    {
        var bedPart = index == MultiBlockType.ORIGIN_INDEX ? BedPart.HEAD : BedPart.FOOT;
        return blockState.setValue(BedMultiBlock.PART, bedPart);
    }

    private static BlockState modifyBedDoubleBlockState(MultiBlockType multiBlockType, BlockPos pos, BlockState blockState, int index)
    {
        var bedPart = index == MultiBlockType.ORIGIN_INDEX || index == 1 ? BedPart.HEAD : BedPart.FOOT;
        return blockState.setValue(BedMultiBlock.PART, bedPart);
    }
}
