package xyz.apex.forge.fantasyfurniture.init;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.PaintingMultiBlock;

public final class FFPatterns
{
	public static final MultiBlockPattern PATTERN_2x1 = MultiBlockPattern
			.builder()
			.layer("X")
			.layer("X")
			.setSpacesFor4Way()
			.build();

	public static final MultiBlockPattern PATTERN_1x2 = MultiBlockPattern
			.builder()
			.layer("XX")
			.setSpacesFor4Way()
			.build();

	public static final MultiBlockPattern PATTERN_1x2_PAINTING = MultiBlockPattern
			.builder()
			.layer("XX")
			.setSpacesFor4Way()
			.placementPredicate((world, pos, blockState) -> PaintingMultiBlock.canSupportPainting(world, pos))
			.build();

	public static final MultiBlockPattern PATTERN_2x2 = MultiBlockPattern
			.builder()
			.layer("XX", "XX")
			.setSpacesFor4Way()
			.build();

	static void bootstrap()
	{
	}
}
