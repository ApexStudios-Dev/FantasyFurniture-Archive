package xyz.apex.forge.fantasyfurniture.init;

import xyz.apex.forge.apexcore.lib.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.furniture.FloorLightBlock;

public final class ModPatterns
{
	public static final MultiBlockPattern PATTERN_1x1x2 = MultiBlockPattern
			.builder()
			.layer("X")
			.layer("X")
			.build();

	public static final MultiBlockPattern PATTERN_1x1x2_PAINTING = MultiBlockPattern
			.builder()
			.layer("X")
			.layer("X")
			.placementStateModifier((pattern, pos, blockState, index) -> index == MultiBlockPattern.INDEX_ORIGIN ? blockState : blockState.setValue(FloorLightBlock.PART, FloorLightBlock.Part.TOP))
			.build();

	public static final MultiBlockPattern PATTERN_1x2x1 = MultiBlockPattern
			.builder()
			.layer("XX")
			.build();

	public static final MultiBlockPattern PATTERN_2x1x1 = MultiBlockPattern
			.builder()
			.layer("X", "X")
			.build();

	public static final MultiBlockPattern PATTERN_2x2x1 = MultiBlockPattern
			.builder()
			.layer("XX", "XX")
			.build();

	public static final MultiBlockPattern PATTERN_1x2x2 = MultiBlockPattern
			.builder()
			.layer("XX")
			.layer("XX")
			.build();
}