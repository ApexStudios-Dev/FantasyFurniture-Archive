package xyz.apex.forge.fantasyfurniture.init;

import xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetPaintingWideBlock;

public final class FFPatterns
{
	public static final MultiBlockPattern PATTERN_1x1x2 = MultiBlockPattern
			.builder()
			.layer("X")
			.layer("X")
			.build();

	public static final MultiBlockPattern PATTERN_1x2x1 = MultiBlockPattern
			.builder()
			.layer("XX")
			.build();

	public static final MultiBlockPattern PATTERN_1x2x1_PAINTING = MultiBlockPattern
			.builder()
			.layer("XX")
			.placementPredicate((multiBlock, levelReader, pos, blockState) -> SetPaintingWideBlock.canSupportPainting(levelReader, pos))
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

	static void bootstrap()
	{
	}
}
