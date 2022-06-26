package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.revamp.block.BaseMultiBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetBedDoubleBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetBedSingleBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetChairOriginOnly;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetWallLightBlock;
import xyz.apex.forge.fantasyfurniture.block.dunmer.*;

import static net.minecraft.world.level.block.Block.box;

public final class DunmerBlocks extends ModBlocks
{
	public static final DunmerBlocks INSTANCE = new DunmerBlocks();

	private DunmerBlocks()
	{
		super("dunmer", DunmerHitBoxes::new);
	}

	@Override
	protected void updateFactories()
	{
		wallLightBlockFactory = DunmerWallLightBlock::new;
		floorLightBlockFactory = DunmerFloorLightBlock::new;
		chairBlockFactory = SetChairOriginOnly::new;
		bedSingleBlockFactory = DunmerBedSingleBlock::new;
		bedDoubleBlockFactory = DunmerBedDoubleBlock::new;
		chandelierBlockFactory = DunmerChandelierBlock::new;
	}

	@Override
	protected BlockBuilder<FFRegistry, DoorBlock, FFRegistry> door(BlockBuilder<FFRegistry, DoorBlock, FFRegistry> builder)
	{
		var type = builder.getName().substring("%s/door_".formatted(serializedName).length());
		var nameIndex = type.equals("single") ? 1 : 2;
		return builder.lang("%s Door %d".formatted(englishName, nameIndex));
	}

	@Override
	protected BlockBuilder<FFRegistry, SetWallLightBlock, FFRegistry> wallLight(BlockBuilder<FFRegistry, SetWallLightBlock, FFRegistry> builder)
	{
		return builder.lightLevel(blockState -> 14);
	}

	@Override
	protected BlockBuilder<FFRegistry, SetBedSingleBlock, FFRegistry> bedSingle(BlockBuilder<FFRegistry, SetBedSingleBlock, FFRegistry> builder)
	{
		return bed(builder);
	}

	@Override
	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetBedSingleBlock, FFRegistry>> bedSingle(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetBedSingleBlock, FFRegistry>> builder)
	{
		return bed(builder);
	}

	@Override
	protected BlockBuilder<FFRegistry, SetBedDoubleBlock, FFRegistry> bedDouble(BlockBuilder<FFRegistry, SetBedDoubleBlock, FFRegistry> builder)
	{
		return bed(builder);
	}

	@Override
	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetBedDoubleBlock, FFRegistry>> bedDouble(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetBedDoubleBlock, FFRegistry>> builder)
	{
		return bed(builder);
	}

	private <BLOCK extends BaseMultiBlock> BlockBuilder<FFRegistry, BLOCK, FFRegistry> bed(BlockBuilder<FFRegistry, BLOCK, FFRegistry> builder)
	{
		return builder
				.blockstate((ctx, provider) -> provider
						.horizontalBlock(ctx.get(), blockState -> provider
								.models()
								.getExistingFile(Registrations.getExistingModelPath(ctx.getId(), ctx.get().getMultiBlockPattern().isOrigin(blockState) ? "_head" : "_foot"))
						)
				)
		;
	}

	private <BLOCK extends Block> ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, BLOCK, FFRegistry>> bed(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, BLOCK, FFRegistry>> builder)
	{
		return builder
				.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								Registrations.getExistingModelPath(ctx.getId(), "_full")
						)
				)
		;
	}

	static void bootstrap()
	{
	}

	private static final class DunmerHitBoxes extends HitBoxes
	{
		@Override
		protected VoxelShape bedDouble()
		{
			return VoxelShaper.or(
					box(-16D, 0D, 0D, -14D, 13D, 2D),
					box(-16D, 0D, 30D, -14D, 11D, 32D),
					box(14D, 0D, 30D, 16D, 11D, 32D),
					box(14D, 0D, 0D, 16D, 13D, 2D),
					box(-14D, 3D, 0D, 14D, 14.25D, 2D),
					box(-14D, 3D, 30D, 14D, 12.25D, 32D),
					box(-15D, 5D, 2D, 15D, 8D, 30D),
					box(-16D, 3D, 2D, 16D, 5D, 30D)
			);
		}

		@Override
		protected VoxelShape bedSingle()
		{
			return VoxelShaper.or(
					box(0D, 0D, 0D, 2D, 13D, 2D),
					box(0D, 0D, 30D, 2D, 11D, 32D),
					box(14D, 0D, 30D, 16D, 11D, 32D),
					box(14D, 0D, 0D, 16D, 13D, 2D),
					box(2D, 3D, 0D, 14D, 14.25D, 2D),
					box(2D, 3D, 30D, 14D, 12.25D, 32D),
					box(1D, 5D, 2D, 15D, 8D, 30D),
					box(0D, 3D, 2D, 16D, 5D, 30D)
			);
		}

		@Override
		protected VoxelShape bench()
		{
			return VoxelShaper.or(
					box(12, 0, 2, 14, 5, 4),
					box(-14, 0, 2, -12, 5, 4),
					box(-14, 0, 12, -12, 5, 14),
					box(12, 0, 12, 14, 5, 14),
					box(-15, 5, 1, 15, 7, 15),
					box(12.5, 2.5, 4, 13.5, 3.5, 12),
					box(-13.5, 2.5, 4, -12.5, 3.5, 12)
			);
		}

		@Override
		protected VoxelShape bookshelf()
		{
			return VoxelShaper.or(
					box(-14, 0, 2, -12, 30, 4),
					box(-14, 0, 12, -12, 30, 14),
					box(12, 0, 12, 14, 30, 14),
					box(12, 0, 2, 14, 30, 4),
					box(-12, 9, 4, 12, 32, 12),
					box(-15, 9, 1, 15, 11, 15),
					box(-15, 19, 1, 15, 21, 15),
					box(-15, 30, 1, 15, 32, 15)
			);
		}

		@Override
		protected VoxelShape chair()
		{
			return VoxelShaper.or(
					box(2, 0, 2, 4, 4, 4),
					box(2, 0, 12, 4, 4, 14),
					box(12, 0, 12, 14, 4, 14),
					box(12, 0, 2, 14, 4, 4),
					box(11.5, 4, 2.5, 13.5, 7, 4.5),
					box(2.5, 4, 2.5, 4.5, 7, 4.5),
					box(2.5, 4, 11.5, 4.5, 7, 13.5),
					box(11.5, 4, 11.5, 13.5, 7, 13.5),
					box(2, 7, 1, 14, 9, 15),
					box(2.5, 9, 11.5, 13.5, 31.5, 13.5)
			);
		}

		@Override
		protected VoxelShape chandelier()
		{
			return box(1, 2, 1, 15, 16, 15);
		}

		@Override
		protected VoxelShape chest()
		{
			return VoxelShaper.or(
					box(12, 0, 2, 14, 14, 4),
					box(-14, 0, 2, -12, 14, 4),
					box(-14, 0, 12, -12, 14, 14),
					box(12, 0, 12, 14, 14, 14),
					box(-15, 4, 1, 15, 6, 15),
					box(-15, 14, 1, 15, 16, 15),
					box(-13, 6, 3, 13, 14, 13),
					box(-2, 11, 2, 2, 14, 3)
			);
		}

		@Override
		protected VoxelShape cushion()
		{
			return VoxelShaper.or(
					box(3, 0, 3, 5, 4, 5),
					box(3, 0, 11, 5, 4, 13),
					box(11, 0, 11, 13, 4, 13),
					box(11, 0, 3, 13, 4, 5),
					box(2, 4, 2, 14, 5, 14),
					box(2.5, 5, 2.5, 13.5, 7, 13.5)
			);
		}

		@Override
		protected VoxelShape deskLeft()
		{
			return VoxelShaper.or(
					box(12, 0, 2, 14, 14, 4),
					box(-14, 0, 2, -12, 14, 4),
					box(-14, 0, 12, -12, 14, 14),
					box(12, 0, 12, 14, 14, 14),
					box(-16, 14, 0, 16, 16, 16),
					box(4, 10, 2, 11, 14, 11)
			);
		}

		@Override
		protected VoxelShape deskRight()
		{
			return VoxelShaper.or(
					box(12, 0, 2, 14, 14, 4),
					box(-14, 0, 2, -12, 14, 4),
					box(-14, 0, 12, -12, 14, 14),
					box(12, 0, 12, 14, 14, 14),
					box(-16, 14, 0, 16, 16, 16),
					box(-11, 10, 2, -4, 14, 11)
			);
		}

		@Override
		protected VoxelShape drawer()
		{
			return VoxelShaper.or(
					box(1, 0, 1, 3, 14, 3),
					box(1, 0, 13, 3, 14, 15),
					box(13, 0, 13, 15, 14, 15),
					box(13, 0, 1, 15, 14, 3),
					box(0, 14, 0, 16, 16, 16),
					box(1, 6, 1, 15, 8, 15),
					box(2, 6, 2, 14, 14, 14)
			);
		}

		@Override
		protected VoxelShape dresser()
		{
			return VoxelShaper.or(
					box(-15, 0, 1, -13, 14, 3),
					box(-15, 0, 13, -13, 14, 15),
					box(13, 0, 13, 15, 14, 15),
					box(13, 0, 1, 15, 14, 3),
					box(-16, 14, 0, 16, 16, 16),
					box(-15, 6, 1, 15, 8, 15),
					box(-14, 8, 2, 14, 14, 14)
			);
		}

		@Override
		protected VoxelShape floorLight()
		{
			return VoxelShaper.or(
					box(6, 0, 6, 10, 2, 10),
					box(7, 2, 7, 9, 22, 9),
					box(6, 22, 6, 10, 27, 10),
					box(7, 27, 7, 9, 28, 9)
			);
		}

		@Override
		protected VoxelShape lockbox()
		{
			return box(2, 0, 3, 14, 14, 13);
		}

		@Override
		protected VoxelShape paintingSmall()
		{
			return VoxelShaper.or(
					box(0, 1, 14, 16, 3, 16),
					box(0, 13, 14, 16, 15, 16),
					box(1, 3, 14, 15, 13, 16)
			);
		}

		@Override
		protected VoxelShape paintingWide()
		{
			return VoxelShaper.or(
					box(-16, 0, 14, 16, 2, 16),
					box(-16, 14, 14, 16, 16, 16),
					box(-15, 2, 14, 15, 14, 16)
			);
		}

		@Override
		protected VoxelShape shelf()
		{
			return VoxelShaper.or(
					box(13, 8, 14, 15, 14, 16),
					box(1, 8, 14, 3, 14, 16),
					box(0, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape shelfCenter()
		{
			return VoxelShaper.or(
					box(13, 8, 14, 15, 14, 16),
					box(0, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape shelfLeft()
		{
			return box(0, 14, 0, 16, 16, 16);
		}

		@Override
		protected VoxelShape shelfRight()
		{
			return VoxelShaper.or(
					box(1, 8, 14, 3, 14, 16),
					box(0, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape sofa()
		{
			return VoxelShaper.or(
					box(0, 4, 1, 16, 6, 15),
					box(2, 0, 12, 4, 4, 14),
					box(2, 0, 2, 4, 4, 4),
					box(12, 0, 2, 14, 4, 4),
					box(12, 0, 12, 14, 4, 14),
					box(1, 6, 12, 15, 16, 14),
					box(13, 10, 2, 15, 12, 12),
					box(13, 6, 3, 15, 10, 5),
					box(1, 6, 3, 3, 10, 5),
					box(1, 10, 2, 3, 12, 12),
					box(3, 6, 2, 13, 7, 13)
			);
		}

		@Override
		protected VoxelShape sofaCenter()
		{
			return VoxelShaper.or(
					box(2, 0, 2, 4, 4, 4),
					box(12, 0, 2, 14, 4, 4),
					box(12, 0, 12, 14, 4, 14),
					box(2, 0, 12, 4, 4, 14),
					box(0, 4, 1, 16, 6, 15),
					box(0, 6, 12, 16, 16, 14),
					box(0, 6, 2, 16, 7, 13)
			);
		}

		@Override
		protected VoxelShape sofaLeft()
		{
			return VoxelShaper.or(
					box(2, 0, 12, 4, 4, 14),
					box(2, 0, 2, 4, 4, 4),
					box(12, 0, 2, 14, 4, 4),
					box(12, 0, 12, 14, 4, 14),
					box(0, 4, 1, 15, 6, 15),
					box(0, 6, 12, 15, 16, 14),
					box(13, 10, 2, 15, 12, 12),
					box(13, 6, 3, 15, 10, 5),
					box(0, 6, 2, 13, 7, 13)
			);
		}

		@Override
		protected VoxelShape sofaRight()
		{
			return VoxelShaper.or(
					box(12, 0, 12, 14, 4, 14),
					box(12, 0, 2, 14, 4, 4),
					box(2, 0, 2, 4, 4, 4),
					box(2, 0, 12, 4, 4, 14),
					box(1, 4, 1, 16, 6, 15),
					box(1, 6, 12, 16, 16, 14),
					box(1, 10, 2, 3, 12, 12),
					box(1, 6, 3, 3, 10, 5),
					box(3, 6, 2, 16, 7, 13)
			);
		}

		@Override
		protected VoxelShape sofaCorner()
		{
			return VoxelShaper.or(
					box(2, 0, 12, 4, 4, 14),
					box(2, 0, 2, 4, 4, 4),
					box(12, 0, 2, 14, 4, 4),
					box(12, 0, 12, 14, 4, 14),
					box(12, 6, 12, 14, 16, 14),
					box(12, 6, 0, 14, 16, 12),
					box(0, 6, 12, 12, 16, 14),
					box(1, 4, 0, 15, 6, 15),
					box(0, 4, 1, 1, 6, 15),
					box(0, 6, 2, 13, 7, 13),
					box(2, 6, 0, 13, 7, 2)
			);
		}

		@Override
		protected VoxelShape stool()
		{
			return VoxelShaper.or(
					box(12, 0, 2, 14, 5, 4),
					box(2, 0, 2, 4, 5, 4),
					box(2, 0, 12, 4, 5, 14),
					box(12, 0, 12, 14, 5, 14),
					box(1, 5, 1, 15, 7, 15),
					box(12.5, 2.5, 4, 13.5, 3.5, 12),
					box(2.5, 2.5, 4, 3.5, 3.5, 12)
			);
		}

		@Override
		protected VoxelShape tableLarge()
		{
			return VoxelShaper.or(
					box(12, 0, 2, 14, 14, 4),
					box(-14, 0, 2, -12, 14, 4),
					box(-14, 0, 28, -12, 14, 30),
					box(12, 0, 28, 14, 14, 30),
					box(-16, 14, 0, 16, 16, 32)
			);
		}

		@Override
		protected VoxelShape tableSmall()
		{
			return VoxelShaper.or(
					box(1, 0, 1, 3, 14, 3),
					box(1, 0, 13, 3, 14, 15),
					box(13, 0, 13, 15, 14, 15),
					box(13, 0, 1, 15, 14, 3),
					box(0, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape tableWide()
		{
			return VoxelShaper.or(
					box(12, 0, 2, 14, 14, 4),
					box(-14, 0, 2, -12, 14, 4),
					box(-14, 0, 12, -12, 14, 14),
					box(12, 0, 12, 14, 14, 14),
					box(-16, 14, 0, 16, 16, 16)
			);
		}

		@Override
		protected VoxelShape wallLight()
		{
			return VoxelShaper.or(
					box(4.5, 1, 7.5, 11.5, 2, 14.5),
					box(4.5, 8, 7.5, 11.5, 9, 14.5),
					box(5, 2, 8, 11, 8, 14),
					box(6, 9, 9, 10, 10, 13),
					box(7.5, 10, 10.5, 8.5, 13, 11.5),
					box(7, 13, 9, 9, 15, 15),
					box(6, 12, 15, 10, 16, 16)
			);
		}

		@Override
		protected VoxelShape wardrobe()
		{
			return VoxelShaper.or(
					box(-15, 0, 1, -13, 30, 3),
					box(-15, 0, 13, -13, 30, 15),
					box(13, 0, 13, 15, 30, 15),
					box(13, 0, 1, 15, 30, 3),
					box(-16, 30, 0, 16, 32, 16),
					box(-16, 20, 0, 16, 22, 16),
					box(-16, 2, 0, 16, 4, 16),
					box(-13, 4, 2, 13, 30, 14)
			);
		}

		@Override
		protected VoxelShape wardrobeTopper()
		{
			return VoxelShaper.or(
					box(13, 0, 1, 15, 8, 3),
					box(-15, 0, 1, -13, 8, 3),
					box(-15, 0, 13, -13, 8, 15),
					box(13, 0, 13, 15, 8, 15),
					box(-16, 8, 0, 16, 10, 16),
					box(-13, 0, 2, 13, 8, 14)
			);
		}
	}
}