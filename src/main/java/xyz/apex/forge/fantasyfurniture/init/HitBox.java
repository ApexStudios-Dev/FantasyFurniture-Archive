package xyz.apex.forge.fantasyfurniture.init;

import org.apache.commons.lang3.Validate;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.util.Lazy;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.VoxelShaper;

import java.util.function.Supplier;

public class HitBox
{
	private final Lazy<VoxelShape> shape;

	HitBox(Supplier<VoxelShape> shape)
	{
		this.shape = Lazy.of(() -> shape.get().optimize());
	}

	public final VoxelShape shape()
	{
		return shape.get();
	}

	public VoxelShape get(BlockState blockState)
	{
		return shape();
	}

	public static final class WithShaper extends HitBox
	{
		private final Lazy<VoxelShaper> shaper;

		WithShaper(Supplier<VoxelShape> shape, Direction facing)
		{
			super(shape);

			Validate.isTrue(facing.getAxis().isHorizontal());
			shaper = Lazy.of(() -> VoxelShaper.forHorizontal(shape(), facing));
		}

		public VoxelShaper shaper()
		{
			return shaper.get();
		}

		@Override
		public VoxelShape get(BlockState blockState)
		{
			var facing = BaseBlock.getFacing(blockState);
			return shaper().get(facing);
		}
	}
}