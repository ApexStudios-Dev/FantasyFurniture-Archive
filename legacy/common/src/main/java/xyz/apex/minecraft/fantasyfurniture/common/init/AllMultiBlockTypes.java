package xyz.apex.minecraft.fantasyfurniture.common.init;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

import xyz.apex.minecraft.apexcore.common.component.components.BedComponent;
import xyz.apex.minecraft.apexcore.common.component.components.DoorComponent;
import xyz.apex.minecraft.apexcore.common.component.components.HorizontalFacingComponent;
import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlockPattern;
import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlockType;

public interface AllMultiBlockTypes
{
    MultiBlockPattern MB_1x2x1 = MultiBlockPattern.builder(1, 2, 1).build();
    MultiBlockPattern MB_1x1x2_FACING = MultiBlockPattern.builder(1, 1, 2).rotateLocalSpaceForFacing(HorizontalFacingComponent.FACING).build();
    MultiBlockPattern MB_1x2x2_FACING = MultiBlockPattern.builder(1, 2, 2).rotateLocalSpaceForFacing(HorizontalFacingComponent.FACING).build();
    MultiBlockPattern MB_2x1x2_FACING = MultiBlockPattern.builder(2, 1, 2).rotateLocalSpaceForFacing(HorizontalFacingComponent.FACING).build();
    MultiBlockPattern MB_1x2x1_FACING = MB_1x2x1.copy().rotateLocalSpaceForFacing(HorizontalFacingComponent.FACING).build();
    MultiBlockPattern MB_1x2x1_FACING_DOOR = MB_1x2x1_FACING.copy().placementStateModifier(AllMultiBlockTypes::modifyDoorBlockState).build();
    MultiBlockPattern MB_2x1x1_FACING_BED_SINGLE = MultiBlockPattern.builder(2, 1, 1).rotateLocalSpaceForFacing(HorizontalFacingComponent.FACING).placementStateModifier(AllMultiBlockTypes::modifyBedSingleBlockState).build();
    MultiBlockPattern MB_2x1x2_FACING_BED_DOUBLE = MB_2x1x2_FACING.copy().placementStateModifier(AllMultiBlockTypes::modifyBedDoubleBlockState).build();

    static void bootstrap() {}

    private static BlockState modifyDoorBlockState(MultiBlockType multiBlockType, BlockPos pos, BlockState blockState, int index)
    {
        var doorHalf = index == MultiBlockPattern.ORIGIN_INDEX ? DoubleBlockHalf.LOWER : DoubleBlockHalf.UPPER;
        return blockState.setValue(DoorComponent.HALF, doorHalf);
    }

    private static BlockState modifyBedSingleBlockState(MultiBlockType multiBlockType, BlockPos pos, BlockState blockState, int index)
    {
        var bedPart = index == MultiBlockPattern.ORIGIN_INDEX ? BedPart.HEAD : BedPart.FOOT;
        return blockState.setValue(BedComponent.PART, bedPart);
    }

    private static BlockState modifyBedDoubleBlockState(MultiBlockType multiBlockType, BlockPos pos, BlockState blockState, int index)
    {
        var bedPart = index == MultiBlockPattern.ORIGIN_INDEX || index == 1 ? BedPart.HEAD : BedPart.FOOT;
        return blockState.setValue(BedComponent.PART, bedPart);
    }
}
