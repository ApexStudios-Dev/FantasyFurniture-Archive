package xyz.apex.forge.fantasyfurniture.block;

import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.stream.Stream;

final class BlockHelper
{
	static VoxelShape makeShape(VoxelShape... shapes)
	{
		return Stream.of(shapes).reduce((a, b) -> VoxelShapes.join(a, b, IBooleanFunction.OR)).orElseThrow(NullPointerException::new);
	}
}
