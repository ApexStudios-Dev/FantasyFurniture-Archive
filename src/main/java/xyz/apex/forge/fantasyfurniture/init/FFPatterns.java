package xyz.apex.forge.fantasyfurniture.init;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;

public final class FFPatterns
{
	public static final MultiBlockPattern PATTERN_1x2 = MultiBlockPattern
			.builder()
			.layer("XX")
			.setSpacesFor4Way()
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
