package xyz.apex.forge.fantasyfurniture.init;

import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockFourWay;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;

public final class FFPatterns
{
	public static final MultiBlockPattern PATTERN_1x2 = MultiBlockPattern
			.builder()
			.layer("XX")
			// .setSpacesFor4Way()
			.worldSpaceFromLocalSpace((blockState, pos) -> {
				Direction facing = blockState.getValue(MultiBlockFourWay.FACING);

				if(facing == Direction.NORTH)
					return pos.rotate(Rotation.CLOCKWISE_90);
				else if(facing == Direction.SOUTH)
					return pos.rotate(Rotation.COUNTERCLOCKWISE_90);
				else if(facing == Direction.EAST)
					return pos.rotate(Rotation.CLOCKWISE_180);

				return pos;
			}).originFromWorldSpace((blockState, pos) -> {
				Direction facing = blockState.getValue(MultiBlockFourWay.FACING);

				if(facing == Direction.NORTH)
					return pos.rotate(Rotation.CLOCKWISE_90);
				else if(facing == Direction.SOUTH)
					return pos.rotate(Rotation.COUNTERCLOCKWISE_90);
				else if(facing == Direction.EAST)
					return pos.rotate(Rotation.CLOCKWISE_180);

				return pos;
			})
			.build();

	static void bootstrap()
	{
	}
}
