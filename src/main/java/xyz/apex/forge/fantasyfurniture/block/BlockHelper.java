package xyz.apex.forge.fantasyfurniture.block;

import com.google.common.collect.Maps;

import net.minecraft.block.BlockState;
import net.minecraft.state.Property;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import xyz.apex.java.utility.nullness.NonnullFunction;

import java.util.Map;
import java.util.stream.Stream;

public final class BlockHelper
{
	private final Map<String, VoxelShape> shapeMap = Maps.newHashMap();
	private final NonnullFunction<BlockState, VoxelShape> shapeValueMapper;
	private final NonnullFunction<BlockState, String> shapeKeyMapper;

	public BlockHelper(NonnullFunction<BlockState, String> shapeKeyMapper, NonnullFunction<BlockState, VoxelShape> shapeValueMapper)
	{
		this.shapeKeyMapper = shapeKeyMapper;
		this.shapeValueMapper = shapeValueMapper;
	}

	public void clearCache()
	{
		shapeMap.clear();
	}

	public VoxelShape getVoxelShape(BlockState blockState)
	{
		String key = shapeKeyMapper.apply(blockState);
		return shapeMap.computeIfAbsent(key, $ -> shapeValueMapper.apply(blockState));
	}

	public static <T extends IStringSerializable & Comparable<T>> BlockHelper forProperty(Property<T> property, NonnullFunction<T, VoxelShape> shapeMapper)
	{
		return new BlockHelper(blockState -> blockState.getValue(property).getSerializedName(), blockState -> shapeMapper.apply(blockState.getValue(property)));
	}

	public static VoxelShape makeShape(VoxelShape... shapes)
	{
		return Stream.of(shapes).reduce((a, b) -> VoxelShapes.join(a, b, IBooleanFunction.OR)).orElseThrow(NullPointerException::new);
	}
}
