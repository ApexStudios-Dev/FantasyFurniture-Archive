package xyz.apex.forge.fantasyfurniture.init;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;

import static net.minecraft.world.level.block.Block.box;

public abstract class HitBoxes
{
	public static final HitBoxes NORDIC = new Nordic();

	public final HitBox wallLight = new HitBox.WithShaper(this::wallLight, Direction.NORTH);

	protected abstract VoxelShape wallLight();

	private static final class Nordic extends HitBoxes
	{
		@Override
		protected VoxelShape wallLight()
		{
			return VoxelShaper.or(
					box(6D, 5D, 15D, 10D, 11D, 16D),
					box(6D, 2D, 8D, 10D, 15D, 15D)
			);
		}
	}
}