package xyz.apex.forge.fantasyfurniture;

import xyz.apex.forge.apexcore.lib.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.common.block.furniture.FloorLightBlock;

public interface AllPatterns
{
	MultiBlockPattern PATTERN_1x1x2 = MultiBlockPattern
			.builder()
			.layer("X")
			.layer("X")
			.build();

	MultiBlockPattern PATTERN_1x1x2_LIGHT = MultiBlockPattern
			.builder()
			.layer("X")
			.layer("X")
			.placementStateModifier((pattern, pos, blockState, index) -> index == MultiBlockPattern.INDEX_ORIGIN ? blockState : blockState.setValue(FloorLightBlock.PART, FloorLightBlock.Part.TOP))
			.build();

	MultiBlockPattern PATTERN_1x2x1 = MultiBlockPattern
			.builder()
			.layer("XX")
			.build();

	MultiBlockPattern PATTERN_2x1x1 = MultiBlockPattern
			.builder()
			.layer("X", "X")
			.build();

	MultiBlockPattern PATTERN_2x2x1 = MultiBlockPattern
			.builder()
			.layer("XX", "XX")
			.build();

	MultiBlockPattern PATTERN_1x2x2 = MultiBlockPattern
			.builder()
			.layer("XX")
			.layer("XX")
			.build();

	static void bootstrap()
	{
	}
}
