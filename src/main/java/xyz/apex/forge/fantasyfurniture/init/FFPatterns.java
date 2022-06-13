package xyz.apex.forge.fantasyfurniture.init;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.core.PaintingMultiBlock;
import xyz.apex.forge.fantasyfurniture.block.nordic.NordicFloorLightBlock;

public final class FFPatterns
{
	public static final MultiBlockPattern PATTERN_2x1 = MultiBlockPattern
			.builder()
			.layer("X")
			.layer("X")
			.setSpacesFor4Way()
			.build();

	public static final MultiBlockPattern PATTERN_2x1_FLOOR_LIGHT = MultiBlockPattern
			.builder()
			.layer("X")
			.layer("X")
			.placementStateModifier((pattern, pos, blockState, index) ->
					blockState.setValue(NordicFloorLightBlock.SIDE, pattern.isOrigin(blockState) ? NordicFloorLightBlock.Side.BOTTOM : NordicFloorLightBlock.Side.TOP)
			)
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

	public static final MultiBlockPattern PATTERN_2x2_VERTICAL = MultiBlockPattern
			.builder()
			.layer("XX")
			.layer("XX")
			.setSpacesFor4Way()
			.build();

	public static final MultiBlockPattern PATTERN_BED_SINGLE = MultiBlockPattern
			.builder()
			.layer("X", "X")
			.build();

	public static final class Revamp
	{
		public static final xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern PATTERN_1x1x2 = xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern
				.builder()
				.layer("X")
				.layer("X")
				.build();

		public static final xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern PATTERN_1x2x1 = xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern
				.builder()
				.layer("XX")
				.build();
	}

	static void bootstrap()
	{
	}
}
