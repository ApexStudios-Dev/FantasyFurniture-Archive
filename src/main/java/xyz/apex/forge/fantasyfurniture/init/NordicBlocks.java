package xyz.apex.forge.fantasyfurniture.init;

import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetChairOriginOnly;

import static net.minecraft.world.level.block.Block.box;

public final class NordicBlocks extends ModBlocks
{
	public static final NordicBlocks INSTANCE = new NordicBlocks();

	private NordicBlocks()
	{
		super("nordic", NordicHitBoxes::new);
	}

	@Override
	protected void updateFactories()
	{
		chairBlockFactory = SetChairOriginOnly::new;
	}

	static void bootstrap()
	{
	}

	private static final class NordicHitBoxes extends HitBoxes
	{
		@Override
		protected VoxelShape bedDouble()
		{
			return VoxelShaper.or(
					box(-16D, 3D, 2D, 16D, 5D, 30D),
					box(-14D, 5D, 2D, 14D, 8D, 30D),
					box(-16D, 3D, 0D, 16D, 5D, 2D),
					box(-16D, 0D, 0D, -14D, 8D, 2D),
					box(14D, 0D, 0D, 16D, 8D, 2D),
					box(-16D, 12D, 0D, -8D, 14D, 2D),
					box(8D, 12D, 0D, 16D, 14D, 2D),
					box(-10D, 12D, 0D, 10D, 16D, 2D),
					box(-15D, 5D, 0D, 15D, 12D, 2D),
					box(-15D, 5D, 30D, 15D, 12D, 32D),
					box(-16D, 3D, 30D, 16D, 5D, 32D),
					box(-16D, 0D, 30D, -14D, 8D, 32D),
					box(14D, 0D, 30D, 16D, 8D, 32D),
					box(-16D, 12D, 30D, -8D, 14D, 32D),
					box(8D, 12D, 30D, 16D, 14D, 32D),
					box(-10D, 12D, 30D, 10D, 16D, 32D)
			);
		}

		@Override
		protected VoxelShape bedSingle()
		{
			return VoxelShaper.or(
					box(0D, 0D, 0D, 16D, 14D, 2D),
					box(0D, 0D, 30D, 16D, 14D, 32D),
					box(0D, 3D, 2D, 16D, 5D, 30D),
					box(1D, 5D, 2D, 15D, 8D, 30D)
			);
		}

		@Override
		protected VoxelShape bench()
		{
			return VoxelShaper.or(
					box(12D, 0D, 2D, 14D, 3D, 4D),
					box(-14D, 0D, 2D, -12D, 3D, 4D),
					box(-14D, 0D, 12D, -12D, 3D, 14D),
					box(12D, 0D, 12D, 14D, 3D, 14D),
					box(12D, 3D, 11.5D, 14D, 5D, 13.5D),
					box(12D, 3D, 2.5D, 14D, 5D, 4.5D),
					box(-14D, 3D, 2.5D, -12D, 5D, 4.5D),
					box(-14D, 3D, 11.5D, -12D, 5D, 13.5D),
					box(-13.5D, 3.5D, 4.5D, -12.5D, 4.5D, 11.5D),
					box(12.5D, 3.5D, 4.5D, 13.5D, 4.5D, 11.5D),
					box(-15D, 5D, 2D, 15D, 7D, 14D)
			);
		}

		@Override
		protected VoxelShape bookshelf()
		{
			return VoxelShaper.or(
					box(-15D, 0D, 1D, 15D, 30D, 15D),
					box(-16D, 30D, 0D, 16D, 32D, 16D)
			);
		}

		@Override
		protected VoxelShape chair()
		{
			return VoxelShaper.or(
					box(2D, 0D, 2D, 4D, 4D, 4D),
					box(2.5D, 4.5D, 4.5D, 3.5D, 5.5D, 11.5D),
					box(12.5D, 4.5D, 4.5D, 13.5D, 5.5D, 11.5D),
					box(12D, 0D, 2D, 14D, 4D, 4D),
					box(2D, 0D, 12D, 4D, 4D, 14D),
					box(2D, 7D, 2D, 14D, 9D, 14D),
					box(2D, 9D, 13D, 14D, 25D, 14D),
					box(12D, 0D, 12D, 14D, 4D, 14D),
					box(2D, 4D, 11.5D, 4D, 7D, 13.5D),
					box(12D, 4D, 11.5D, 14D, 7D, 13.5D),
					box(2D, 4D, 2.5D, 4D, 7D, 4.5D),
					box(12D, 4D, 2.5D, 14D, 7D, 4.5D)
			);
		}

		@Override
		protected VoxelShape chandelier()
		{
			return box(1D, 0D, 1D, 15, 16D, 15D);
		}

		@Override
		protected VoxelShape chest()
		{
			return box(-15D, 0D, 2D, 15D, 14D, 16D);
		}

		@Override
		protected VoxelShape cushion()
		{
			return VoxelShaper.or(
					box(2D, 0D, 2D, 4D, 2D, 4D),
					box(2D, 0D, 12D, 4D, 2D, 14D),
					box(12D, 0D, 12D, 14D, 2D, 14D),
					box(12D, 0D, 2D, 14D, 2D, 4D),
					box(2D, 5D, 2.25D, 14D, 7D, 13.75D),
					box(1.75D, 4D, 2D, 14.25D, 5D, 14D),
					box(2D, 2D, 2.5D, 4D, 4D, 4.5D),
					box(12D, 2D, 2.5D, 14D, 4D, 4.5D),
					box(12D, 2D, 11.5D, 14D, 4D, 13.5D),
					box(2D, 2D, 11.5D, 4D, 4D, 13.5D),
					box(2.5D, 2.5D, 4.5D, 3.5D, 3.5D, 11.5D),
					box(12.5D, 2.5D, 4.5D, 13.5D, 3.5D, 11.5D)
			);
		}

		@Override
		protected VoxelShape deskLeft()
		{
			return VoxelShaper.or(
					box(13D, 0D, 0D, 15D, 9D, 2D),
					box(13D, 7D, 1D, 15D, 13D, 3D),
					box(13D, 7D, 13D, 15D, 13D, 15D),
					box(-15D, 7D, 13D, -13D, 13D, 15D),
					box(-15D, 0D, 0D, -13D, 9D, 2D),
					box(-15D, 0D, 14D, -13D, 9D, 16D),
					box(13D, 0D, 14D, 15D, 9D, 16D),
					box(-16D, 13D, 0D, 16D, 16D, 16D),
					box(-15D, 7D, 1D, -13D, 13D, 3D),
					box(5D, 9D, 2D, 12D, 13D, 11D)
			);
		}

		@Override
		protected VoxelShape deskRight()
		{
			return VoxelShaper.or(
					box(13D, 0D, 0D, 15D, 9D, 2D),
					box(13D, 7D, 1D, 15D, 13D, 3D),
					box(13D, 7D, 13D, 15D, 13D, 15D),
					box(-15D, 7D, 13D, -13D, 13D, 15D),
					box(-15D, 0D, 0D, -13D, 9D, 2D),
					box(-15D, 0D, 14D, -13D, 9D, 16D),
					box(13D, 0D, 14D, 15D, 9D, 16D),
					box(-16D, 13D, 0D, 16D, 16D, 16D),
					box(-15D, 7D, 1D, -13D, 13D, 3D),
					box(-12D, 9D, 2D, -5D, 13D, 11D)
			);
		}

		@Override
		protected VoxelShape drawer()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 15D, 13D, 15D),
					box(0D, 13D, 0D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape dresser()
		{
			return VoxelShaper.or(
					box(-15D, 0D, 1D, 15D, 16D, 15D),
					box(-16D, 13D, 14D, 16D, 16D, 16D),
					box(-16D, 13D, 0D, 16D, 16D, 2D)
			);
		}

		@Override
		protected VoxelShape floorLight()
		{
			return VoxelShaper.or(
					box(6D, 0D, 6D, 10D, 2D, 10D),
					box(7D, 2D, 7D, 9D, 20D, 9D),
					box(6.5D, 20.75D, 2.5D, 9.5D, 22.75D, 5.5D),
					box(2.5D, 20.75D, 6.5D, 5.5D, 22.75D, 9.5D),
					box(7.25D, 22.75D, 3.25D, 8.75D, 26.75D, 4.75D),
					box(3.25D, 22.75D, 7.25D, 4.75D, 26.75D, 8.75D),
					box(7.25D, 22.75D, 11.25D, 8.75D, 26.75D, 12.75D),
					box(11.25D, 22.75D, 7.25D, 12.75D, 26.75D, 8.75D),
					box(10.5D, 20.75D, 6.5D, 13.5D, 22.75D, 9.5D),
					box(6.5D, 20.75D, 10.5D, 9.5D, 22.75D, 13.5D),
					box(3D, 16.75D, 7D, 7D, 20.75, 9D),
					box(9D, 16.75D, 7D, 13D, 20.75, 9D),
					box(7D, 16.75D, 3D, 9D, 20.75, 7D),
					box(7D, 16.75D, 9D, 9D, 20.75, 13D)
			);
		}

		@Override
		protected VoxelShape lockbox()
		{
			return VoxelShaper.or(
					box(2, 0, 3, 14, 9, 13),
					box(2, 9, 5, 14, 10, 11)
			);
		}

		@Override
		protected VoxelShape paintingSmall()
		{
			return box(0D, 0D, 14D, 16D, 16D, 16D);
		}

		@Override
		protected VoxelShape paintingWide()
		{
			return box(-16D, 0D, 14D, 16D, 16D, 16D);
		}

		@Override
		protected VoxelShape shelf()
		{
			return VoxelShaper.or(
					box(.5D, 9D, 2D, 2.5D, 14D, 13D),
					box(13.5D, 9D, 2D, 15.5D, 14D, 13D),
					box(0D, 14D, 0D, 16D, 16D, 16D),
					box(13D, 6D, 13D, 16D, 14D, 16D),
					box(0D, 6D, 13D, 3D, 14D, 16D)
			);
		}

		@Override
		protected VoxelShape shelfCenter()
		{
			return box(0D, 14D, 0D, 16D, 16D, 16D);
		}

		@Override
		protected VoxelShape shelfLeft()
		{
			return VoxelShaper.or(
					box(13.5D, 9D, 2D, 15.5D, 14D, 13D),
					box(0D, 14D, 0D, 16D, 16D, 16D),
					box(13D, 6D, 13D, 16D, 14D, 16D)
			);
		}

		@Override
		protected VoxelShape shelfRight()
		{
			return VoxelShaper.or(
					box(.5D, 9D, 2D, 2.5D, 14D, 13D),
					box(0D, 14D, 0D, 16D, 16D, 16D),
					box(0D, 6D, 13D, 3D, 14D, 16D)
			);
		}

		@Override
		protected VoxelShape sofa()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 3D, 3D, 3D),
					box(1D, 0D, 13D, 3D, 3D, 15D),
					box(13D, 0D, 13D, 15D, 3D, 15D),
					box(13D, 0D, 1D, 15D, 3D, 3D),
					box(0D, 3D, 0D, 16D, 6D, 16D),
					box(0D, 6D, 13D, 16D, 16D, 16D),
					box(14D, 10D, 0D, 16D, 12D, 14D),
					box(0D, 10D, 0D, 2D, 12D, 14D),
					box(0D, 6D, 0D, 2D, 10D, 2D),
					box(14D, 6D, 0D, 16D, 10D, 2D)
			);
		}

		@Override
		protected VoxelShape sofaCenter()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 3D, 3D, 3D),
					box(1D, 0D, 13D, 3D, 3D, 15D),
					box(13D, 0D, 13D, 15D, 3D, 15D),
					box(13D, 0D, 1D, 15D, 3D, 3D),
					box(0D, 3D, 0D, 16D, 6D, 16D),
					box(0D, 6D, 13D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape sofaLeft()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 3D, 3D, 3D),
					box(1D, 0D, 13D, 3D, 3D, 15D),
					box(13D, 0D, 13D, 15D, 3D, 15D),
					box(13D, 0D, 1D, 15D, 3D, 3D),
					box(0D, 3D, 0D, 16D, 6D, 16D),
					box(0D, 6D, 13D, 16D, 16D, 16D),
					box(14D, 10D, 0D, 16D, 12D, 13D),
					box(14D, 6D, 0D, 16D, 10D, 2D)
			);
		}

		@Override
		protected VoxelShape sofaRight()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 3D, 3D, 3D),
					box(1D, 0D, 13D, 3D, 3D, 15D),
					box(13D, 0D, 13D, 15D, 3D, 15D),
					box(13D, 0D, 1D, 15D, 3D, 3D),
					box(0D, 3D, 0D, 16D, 6D, 16D),
					box(0D, 6D, 13D, 16D, 16D, 16D),
					box(0D, 10D, 0D, 2D, 12D, 13D),
					box(0D, 6D, 0D, 2D, 10D, 2D)
			);
		}

		@Override
		protected VoxelShape sofaCorner()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 3D, 3D, 3D),
					box(1D, 0D, 13D, 3D, 3D, 15D),
					box(13D, 0D, 13D, 15D, 3D, 15D),
					box(13D, 0D, 1D, 15D, 3D, 3D),
					box(0D, 3D, 0D, 16D, 6D, 16D),
					box(0D, 6D, 13D, 16D, 16D, 16D),
					box(13D, 6D, 0D, 16D, 16D, 13D)
			);
		}

		@Override
		protected VoxelShape stool()
		{
			return VoxelShaper.or(
					box(2D, 0D, 2D, 4D, 3D, 4D),
					box(12D, 0D, 12D, 14D, 3D, 14D),
					box(12D, 0D, 2D, 14D, 3D, 4D),
					box(2D, 0D, 12D, 4D, 3D, 14D),
					box(2D, 3D, 11.5D, 4D, 5D, 13.5D),
					box(12D, 3D, 11.5D, 14D, 5, 13.5D),
					box(12D, 3D, 2.5D, 14D, 5D, 4.5D),
					box(1.5D, 5D, 1.75D, 14.5D, 7D, 14.25D),
					box(2D, 3D, 2.5D, 4D, 5D, 4.5D),
					box(2.5D, 3.5D, 4.5D, 3.5D, 4.5D, 11.5D),
					box(12.5D, 3.5D, 4.5D, 13.5D, 4.5D, 11.5D)
			);
		}

		@Override
		protected VoxelShape tableLarge()
		{
			return VoxelShaper.or(
					box(12D, 0D, 2D, 14D, 13D, 4D),
					box(-14D, 0D, 2D, -12D, 13D, 4D),
					box(-14D, 0D, 28D, -12D, 13D, 30D),
					box(12D, 0D, 28D, 14D, 13D, 30D),
					box(-16D, 13D, 0D, 16D, 16D, 32D)
			);
		}

		@Override
		protected VoxelShape tableSmall()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 3D, 13D, 3D),
					box(1D, 0D, 13D, 3D, 13D, 15D),
					box(13D, 0D, 13D, 15D, 13D, 15D),
					box(13D, 0D, 1D, 15D, 13D, 3D),
					box(0D, 13D, 0D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape tableWide()
		{
			return VoxelShaper.or(
					box(13D, 0D, 0D, 15D, 9D, 2D),
					box(13D, 7D, 1D, 15D, 13D, 3D),
					box(13D, 7D, 13D, 15D, 13D, 15D),
					box(-15D, 7D, 13D, -13D, 13D, 15D),
					box(-15D, 0D, 0D, -13D, 9D, 2D),
					box(-15D, 0D, 14D, -13D, 9D, 16D),
					box(13D, 0D, 14D, 15D, 9D, 16D),
					box(-16D, 13D, 0D, 16D, 16D, 16D),
					box(-15D, 7D, 1D, -13D, 13D, 3D)
			);
		}

		@Override
		protected VoxelShape wallLight()
		{
			return VoxelShaper.or(
					box(6D, 5D, 15D, 10D, 11D, 16D),
					box(6D, 2D, 8D, 10D, 15D, 15D)
			);
		}

		@Override
		protected VoxelShape wardrobe()
		{
			return VoxelShaper.or(
					box(-14.75D, 0D, .25D, -12.25D, 31D, 2.75D),
					box(-14.75D, 0D, 13.25D, -12.25D, 31D, 15.75D),
					box(12.25D, 0D, 13.25D, 14.75D, 31D, 15.75D),
					box(12.25D, 0D, .25D, 14.75D, 31D, 2.75D),
					box(-14D, 2D, 1D, 14D, 31D, 15D),
					box(-15D, 31D, 0D, 15D, 32D, 16D)
			);
		}

		@Override
		protected VoxelShape wardrobeTopper()
		{
			return VoxelShaper.or(
					box(-15D, 3D, 0D, 15D, 14D, 16D),
					box(-16D, 0D, 0D, 16D, 3D, 16D)
			);
		}
	}
}
