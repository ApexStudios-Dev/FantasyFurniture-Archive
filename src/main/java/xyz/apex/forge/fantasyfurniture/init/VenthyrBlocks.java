package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyBlockState;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.util.EventBusHelper;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetChairOriginOnly;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetTableLargeBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetTableSmallBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetTableWideBlock;
import xyz.apex.forge.fantasyfurniture.block.venthyr.*;
import xyz.apex.forge.fantasyfurniture.item.VenthyrTableBlockItem;

import java.util.function.Consumer;

import static net.minecraft.world.level.block.Block.box;
import static com.tterrag.registrate.providers.ProviderType.LANG;

public final class VenthyrBlocks extends ModBlocks
{
	public static final VenthyrBlocks INSTANCE = new VenthyrBlocks();
	public static final BooleanProperty FANCY = BooleanProperty.create("fancy");

	public final ResourceLocation fancyItemProperty = FFRegistry.INSTANCE.id("venthyr_fancy");

	private VenthyrBlocks()
	{
		super("venthyr", VenthyrHitBoxes::new);
	}

	@Override
	protected void updateFactories()
	{
		wallLightBlockFactory = VenthyrWallLightBlock::new;
		floorLightBlockFactory = VenthyrFloorLightBlock::new;
		tableSmallBlockFactory = VenthyrTableSmallBlock::new;
		tableWideBlockFactory = VenthyrTableWideBlock::new;
		tableLargeBlockFactory = VenthyrTableLargeBlock::new;
		chairBlockFactory = SetChairOriginOnly::new;
		chandelierBlockFactory = VenthyrChandelierBlock::new;

		tableSmallBlockItemFactory = VenthyrTableBlockItem::new;
		tableWideBlockItemFactory = VenthyrTableBlockItem::new;
		tableLargeBlockItemFactory = VenthyrTableBlockItem::new;
	}

	@Override
	protected BlockBuilder<FFRegistry, SetTableSmallBlock, FFRegistry> tableSmall(BlockBuilder<FFRegistry, SetTableSmallBlock, FFRegistry> builder)
	{
		return table(builder);
	}

	@Override
	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetTableSmallBlock, FFRegistry>> tableSmall(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetTableSmallBlock, FFRegistry>> builder)
	{
		return table(builder);
	}

	@Override
	protected BlockBuilder<FFRegistry, SetTableWideBlock, FFRegistry> tableWide(BlockBuilder<FFRegistry, SetTableWideBlock, FFRegistry> builder)
	{
		return table(builder);
	}

	@Override
	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetTableWideBlock, FFRegistry>> tableWide(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetTableWideBlock, FFRegistry>> builder)
	{
		return table(builder);
	}

	@Override
	protected BlockBuilder<FFRegistry, SetTableLargeBlock, FFRegistry> tableLarge(BlockBuilder<FFRegistry, SetTableLargeBlock, FFRegistry> builder)
	{
		return table(builder);
	}

	@Override
	protected ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetTableLargeBlock, FFRegistry>> tableLarge(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, SetTableLargeBlock, FFRegistry>> builder)
	{
		return table(builder);
	}

	private <BLOCK extends Block> BlockBuilder<FFRegistry, BLOCK, FFRegistry> table(BlockBuilder<FFRegistry, BLOCK, FFRegistry> builder)
	{
		var name = builder.getName().substring("%s/".formatted(serializedName).length());

		return builder
				.setData(LANG, (ctx, provider) -> {
					var block = ctx.get();
					var engName = "%s %s".formatted(serializedName, RegistrateLangProvider.toEnglishName(name));
					provider.add(block, engName);
					provider.add("%s.fancy".formatted(block.getDescriptionId()), "Fancy %s".formatted(engName));
				})
				.blockstate((ctx, provider) -> {
					var id = ctx.getId();

					provider.horizontalBlock(ctx.get(), blockState -> {
						var existingModel = provider.models().getExistingFile(Registrations.getExistingModelPath(id, ""));

						if(blockState.getValue(FANCY))
							return provider.models().getBuilder("%s_fancy".formatted(id)).parent(existingModel).texture(name, "%s:models/%s/%s_fancy".formatted(id.getNamespace(), serializedName, name));
						return existingModel;
					});
				})
				.loot((lootTables, block) -> lootTables
						.add(block, LootTable
								.lootTable()
								.withPool(RegistrateBlockLootTables
										.applyExplosionCondition(block, LootPool
												.lootPool()
												.setRolls(ConstantValue.exactly(1))
												.add(LootItem.lootTableItem(block))
												.apply(CopyBlockState
														.copyState(block)
														.copy(FANCY)
												)
										)
								)
						)
				)
		;
	}

	private <BLOCK extends Block> ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, BLOCK, FFRegistry>> table(ItemBuilder<FFRegistry, BlockItem, BlockBuilder<FFRegistry, BLOCK, FFRegistry>> builder)
	{
		return builder
				.model((ctx, provider) -> {
					var id = ctx.getId();
					provider.withExistingParent("%s:item/%s".formatted(id.getNamespace(), id.getPath()), Registrations.getExistingModelPath(id, ""))
					        .override()
					        .predicate(fancyItemProperty, 1F)
					        .model(provider.getExistingFile(new ResourceLocation(id.getNamespace(), "%s_fancy".formatted(id.getPath()))))
					        .end()
					;
				})
				.onRegister(item -> {
					var stack = item.getDefaultInstance();
					var stackTag = stack.getOrCreateTag();
					var blockStateTag = new CompoundTag();
					blockStateTag.putString(FANCY.getName(), "false");
					stackTag.put(FantasyFurniture.NBT_BLOCK_STATE_TAG, blockStateTag);
					FurnitureStation.registerAdditionalCraftingResult(stack);

					stack = item.getDefaultInstance();
					stackTag = stack.getOrCreateTag();
					blockStateTag = new CompoundTag();
					blockStateTag.putString(FANCY.getName(), "true");
					stackTag.put(FantasyFurniture.NBT_BLOCK_STATE_TAG, blockStateTag);
					FurnitureStation.registerAdditionalCraftingResult(stack);
				})
		;
	}

	static void bootstrap()
	{
		EventBusHelper.addEnqueuedListener(FMLClientSetupEvent.class, event -> {
			Consumer<? super BlockItem> consumer = item -> ItemProperties.register(item, INSTANCE.fancyItemProperty, (stack, level, entity, seed) -> {
				var stackTag = stack.getTag();

				if(stackTag != null)
				{
					if(stackTag.contains(FantasyFurniture.NBT_BLOCK_STATE_TAG, net.minecraft.nbt.Tag.TAG_COMPOUND))
					{
						var blockStateTag = stackTag.getCompound(FantasyFurniture.NBT_BLOCK_STATE_TAG);
						var name = FANCY.getName();

						if(blockStateTag.contains(name, net.minecraft.nbt.Tag.TAG_STRING))
						{
							var strFancy = blockStateTag.getString(name);

							if(FANCY.getValue(strFancy).orElse(false))
								return 1F;
						}
					}
				}

				return 0F;
			});

			INSTANCE.tableLargeBlockItem.ifPresent(consumer);
			INSTANCE.tableWideBlockItem.ifPresent(consumer);
			INSTANCE.tableSmallBlockItem.ifPresent(consumer);
		});
	}

	private static final class VenthyrHitBoxes extends HitBoxes
	{
		@Override
		protected VoxelShape bedDouble()
		{
			return VoxelShaper.or(
					box(-16D, 0D, 0D, -13D, 2D, 3D),
					box(-15.5D, 2D, .5D, -13.5D, 12D, 2.5D),
					box(-15.5D, 2D, 29.5D, -13.5D, 12D, 31.5D),
					box(13.5D, 2D, 29.5D, 15.5D, 12D, 31.5D),
					box(13.5D, 2D, .5D, 15.5D, 12D, 2.5D),
					box(-16D, 12D, 0D, -13D, 14D, 3D),
					box(-16D, 12D, 29D, -13D, 14D, 32D),
					box(13D, 12D, 29D, 16D, 14D, 32D),
					box(13D, 12D, 0D, 16D, 14D, 3D),
					box(13D, 0D, 0D, 16D, 2D, 3D),
					box(13D, 0D, 29D, 16D, 2D, 32D),
					box(-16D, 0D, 29D, -13D, 2D, 32D),
					box(-15.5D, 0D, 2.5D, 15.5D, 5D, 29.5D),
					box(-13.5D, 0D, 29.5D, 13.5D, 12D, 31.5D),
					box(-13.5D, 0D, .5D, 13.5D, 14D, 2.5D),
					box(-14.5D, 0D, 2.5D, 14.5D, 8D, 29.5D)
			);
		}

		@Override
		protected VoxelShape bedSingle()
		{
			return VoxelShaper.or(
					box(0D, 0D, 29D, 3D, 2D, 32D),
					box(13D, 0D, 29D, 16D, 2D, 32D),
					box(13D, 0D, 0D, 16D, 2D, 3D),
					box(0D, 0D, 0D, 3D, 2D, 3D),
					box(.5D, 2D, .5D, 2.5D, 12D, 2.5D),
					box(.5D, 2D, 29.5D, 2.5D, 12D, 31.5D),
					box(13.5D, 2D, 29.5D, 15.5D, 12D, 31.5D),
					box(13.5D, 2D, .5D, 15.5D, 12D, 2.5D),
					box(13D, 12D, 0D, 16D, 14D, 3D),
					box(0D, 12D, 0D, 3D, 14D, 3D),
					box(0D, 12D, 29D, 3D, 14D, 32D),
					box(13D, 12D, 29D, 16D, 14D, 32D),
					box(.5D, 0D, 2D, 15.5D, 5D, 30D),
					box(2.5D, 0D, 29.5D, 13.5D, 11D, 31.5D),
					box(2.5D, 0D, .5D, 13.5D, 13D, 2.5D),
					box(1.5D, 5D, 2.5D, 14.5D, 8D, 29.5D)
			);
		}

		@Override
		protected VoxelShape bench()
		{
			return VoxelShaper.or(
					box(-14D, 0D, 2D, -11D, 4D, 5D),
					box(-14D, 0D, 11D, -11D, 4D, 14D),
					box(11D, 0D, 11D, 14D, 4D, 14D),
					box(11D, 0D, 2D, 14D, 4D, 5D),
					box(-15D, 4D, 1D, 15D, 7D, 15D)
			);
		}

		@Override
		protected VoxelShape bookshelf()
		{
			return VoxelShaper.or(
					box(-16D, 0D, 2D, 16D, 3D, 16D),
					box(-15D, 3D, 3D, 15D, 29D, 16D),
					box(-16D, 29D, 2D, 16D, 32D, 16D)
			);
		}

		@Override
		protected VoxelShape chair()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 4D, 5D, 4D),
					box(12D, 0D, 1D, 15D, 5D, 4D),
					box(12D, 0D, 12D, 15D, 5D, 15D),
					box(1D, 0D, 12D, 4D, 5D, 15D),
					box(.5D, 5D, .5D, 15.5D, 9D, 15.5D),
					box(1D, 9D, 12D, 15D, 31D, 15D)
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
			return box(-13D, 0D, 1D, 13D, 14.25D, 15D);
		}

		@Override
		protected VoxelShape cushion()
		{
			return VoxelShaper.or(
					box(2D, 0D, 2D, 5D, 3D, 5D),
					box(2D, 0D, 11D, 5D, 3D, 14D),
					box(11D, 0D, 11D, 14D, 3D, 14D),
					box(11D, 0D, 2D, 14D, 3D, 5D),
					box(1D, 3D, 1D, 15D, 4D, 15D),
					box(2D, 4D, 2D, 14D, 7D, 14D)
			);
		}

		@Override
		protected VoxelShape deskLeft()
		{
			return VoxelShaper.or(
					box(-15D, 0D, 1D, -12D, 2D, 4D),
					box(-15D, 0D, 12D, -12D, 2D, 15D),
					box(12D, 0D, 12D, 15, 2D, 15D),
					box(12D, 0D, 1D, 15, 2D, 4D),
					box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
					box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
					box(-14.5D, 2D, 12.5D, -12.5D, 13D, 14.5D),
					box(12.5D, 2D, 12.5D, 14.5D, 13, 14.5D),
					box(-16D, 13D, 0D, 16D, 16D, 16D),
					box(5D, 9D, 2, 12D, 13D, 11D),
					box(12.5D, 9D, 3.5D, 14.5D, 13D, 12.5D),
					box(-14.5D, 9D, 3.5D, -12.5D, 13D, 12.5D)
			);
		}

		@Override
		protected VoxelShape deskRight()
		{
			return VoxelShaper.or(
					box(-15D, 0D, 1D, -12D, 2D, 4D),
					box(-15D, 0D, 12D, -12D, 2D, 15D),
					box(12D, 0D, 12D, 15D, 2D, 15D),
					box(12D, 0D, 1D, 15D, 2D, 4D),
					box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
					box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
					box(-14.5D, 2D, 12.5D, -12.5D, 13D, 14.5D),
					box(12.5D, 2D, 12.5D, 14.5D, 13D, 14.5D),
					box(-16D, 13D, 0D, 16D, 16D, 16D),
					box(-12D, 9D, 2D, -5D, 13D, 11D),
					box(12.5D, 9D, 3.5D, 14.5D, 13D, 12.5D),
					box(-14.5D, 9D, 3.5D, -12.5D, 13D, 12.5D)
			);
		}

		@Override
		protected VoxelShape drawer()
		{
			return VoxelShaper.or(
					box(.5D, 0D, .5D, 3.5D, 2D, 3.5D),
					box(.5D, 0D, 12.5D, 3.5D, 2D, 15.5D),
					box(12.5D, 0D, 12.5D, 15.5D, 2D, 15.5D),
					box(12.5D, 0D, .5D, 15.5D, 2D, 3.5D),
					box(13D, 2D, 1D, 15D, 13D, 3D),
					box(1D, 2D, 1D, 3D, 13D, 3D),
					box(1D, 2D, 13D, 3D, 13D, 15D),
					box(13D, 2D, 13D, 15D, 13D, 15D),
					box(1D, 5D, 1D, 15D, 13D, 15D),
					box(0D, 13D, 0D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape dresser()
		{
			return VoxelShaper.or(
					box(-15.5D, 0D, .5D, -12.5D, 2D, 3.5D),
					box(-15.5D, 0D, 12.5D, -12.5D, 2D, 15.5D),
					box(12.5D, 0D, 12.5D, 15.5D, 2D, 15.5D),
					box(12.5D, 0D, .5D, 15.5D, 2D, 3.5D),
					box(13D, 2D, 1D, 15D, 13D, 3D),
					box(-15D, 2D, 1D, -13D, 13D, 3D),
					box(-15D, 2D, 13D, -13D, 13D, 15D),
					box(13D, 2D, 13D, 15D, 13D, 15D),
					box(-15D, 5D, 1D, 15D, 13D, 15D),
					box(-16D, 13D, 0D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape floorLight()
		{
			return VoxelShaper.or(
					box(6D, 0D, 6D, 10D, 2D, 10D),
					box(7D, 2D, 7D, 9D, 20D, 9D),
					box(4D, 17.75D, 7D, 7D, 21.75D, 9D),
					box(9D, 17.75D, 7D, 12D, 21.75D, 9D),
					box(7D, 17.75D, 4D, 9D, 21.75D, 7D),
					box(7D, 17.75D, 9D, 9D, 21.75D, 12D),
					box(2.5D, 20.75D, 2.5D, 13.5D, 24D, 13.5D),
					box(10.25D, 24D, 10.25D, 12.5D, 28.75D, 12.5D),
					box(3.5D, 24D, 10.25D, 5.75D, 28.75D, 12.5D),
					box(3.5D, 24D, 3.5D, 5.75D, 28.75D, 5.75D),
					box(10.25D, 24D, 3.5D, 12.5D, 28.75D, 5.75D)
			);
		}

		@Override
		protected VoxelShape lockbox()
		{
			return VoxelShaper.or(
					box(2, 1, 4, 14, 10, 12),
					box(1.5, 6, 3.5, 14.5, 7, 12.5),
					box(1.5, 1, 3.5, 14.5, 2, 12.5),
					box(7, 4, 3.5, 9, 6, 4.25),
					box(12, 0, 4, 14, 1, 6),
					box(2, 0, 4, 4, 1, 6),
					box(2, 0, 10, 4, 1, 12),
					box(12, 0, 10, 14, 1, 12)
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
					box(0D, 13D, 0D, 16D, 16D, 16D),
					box(13D, 9D, 10D, 16D, 13D, 16D),
					box(13D, 11D, 3D, 16D, 13D, 10D),
					box(13D, 10D, 0D, 16D, 13D, 3D),
					box(13D, 6D, 13D, 16D, 9D, 16D),
					box(13D, 3D, 12D, 16D, 6D, 16D),
					box(0D, 3D, 12D, 3D, 6D, 16D),
					box(0D, 9D, 10D, 3D, 13D, 16D),
					box(0D, 11D, 3D, 3D, 13D, 10D),
					box(0D, 10D, 0D, 3D, 13D, 3D),
					box(0D, 6D, 13D, 3D, 9D, 16D)
			);
		}

		@Override
		protected VoxelShape shelfCenter()
		{
			return box(0D, 13D, 0D, 16D, 16D, 16D);
		}

		@Override
		protected VoxelShape shelfLeft()
		{
			return VoxelShaper.or(
					box(0D, 13D, 0D, 16D, 16D, 16D),
					box(13D, 3D, 12D, 16D, 6D, 16D),
					box(13D, 9D, 10D, 16D, 13D, 16D),
					box(13D, 11D, 3D, 16D, 13D, 10D),
					box(13D, 10D, 0D, 16D, 13D, 3D),
					box(13D, 6D, 13D, 16D, 9D, 16D)
			);
		}

		@Override
		protected VoxelShape shelfRight()
		{
			return VoxelShaper.or(
					box(0D, 3D, 12D, 3D, 6D, 16D),
					box(0D, 6D, 13D, 3D, 9D, 16D),
					box(0D, 9D, 10D, 3D, 13D, 16D),
					box(0D, 11D, 3D, 3D, 13D, 10D),
					box(0D, 10D, 0D, 3D, 13D, 3D),
					box(0D, 13D, 0D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape sofa()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 4D, 2D, 4D),
					box(1D, 0D, 12D, 4D, 2D, 15D),
					box(12D, 0D, 12D, 15D, 2D, 15D),
					box(12D, 0D, 1D, 15D, 2D, 4D),
					box(0D, 2D, 0D, 16D, 6D, 16D),
					box(13D, 6D, 0D, 16D, 10D, 13D),
					box(0D, 6D, 0D, 3D, 10D, 13D),
					box(0D, 6D, 13D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape sofaCenter()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 4D, 2D, 4D),
					box(1D, 0D, 12D, 4D, 2D, 15D),
					box(12D, 0D, 12D, 15D, 2D, 15D),
					box(12D, 0D, 1D, 15D, 2D, 4D),
					box(0D, 2D, 0D, 16D, 6D, 16D),
					box(0D, 6D, 13D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape sofaLeft()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 4D, 2D, 4D),
					box(1D, 0D, 12D, 4D, 2D, 15D),
					box(12D, 0D, 12D, 15D, 2D, 15D),
					box(12D, 0D, 1D, 15D, 2D, 4D),
					box(0D, 2D, 0D, 16D, 6D, 16D),
					box(13D, 6D, 0D, 16D, 10D, 13D),
					box(0D, 6D, 13D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape sofaRight()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 4D, 2D, 4D),
					box(1D, 0D, 12D, 4D, 2D, 15D),
					box(12D, 0D, 12D, 15D, 2D, 15D),
					box(12D, 0D, 1D, 15D, 2D, 4D),
					box(0D, 2D, 0D, 16D, 6D, 16D),
					box(0D, 6D, 13D, 16D, 16D, 16D),
					box(0D, 6D, 0D, 3D, 10D, 13D)
			);
		}

		@Override
		protected VoxelShape sofaCorner()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 4D, 2D, 4D),
					box(1D, 0D, 12D, 4D, 2D, 15D),
					box(12D, 0D, 12D, 15D, 2D, 15D),
					box(12D, 0D, 1D, 15D, 2D, 4D),
					box(13D, 6D, 0D, 16D, 16D, 16D),
					box(0D, 6D, 13D, 13D, 16D, 16D),
					box(0D, 2D, 0D, 16D, 6D, 16D)
			);
		}

		@Override
		protected VoxelShape stool()
		{
			return VoxelShaper.or(
					box(2D, 0D, 2D, 5D, 4D, 5D),
					box(2D, 0D, 11D, 5D, 4D, 14D),
					box(11D, 0D, 11D, 14D, 4D, 14D),
					box(11D, 0D, 2D, 14D, 4D, 5D),
					box(1D, 4D, 1D, 15D, 7D, 15D)
			);
		}

		@Override
		protected VoxelShape tableLarge()
		{
			return VoxelShaper.or(
					box(-15D, 0D, 1D, -12D, 2D, 4D),
					box(12D, 0D, 1D, 15D, 2D, 4D),
					box(12D, 0D, 28D, 15D, 2D, 31D),
					box(-15D, 0D, 28D, -12D, 2D, 31D),
					box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
					box(-14.5D, 2D, 28.5D, -12.5D, 13D, 30.5D),
					box(12.5D, 2D, 28.5D, 14.5D, 13D, 30.5D),
					box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
					box(-16D, 13D, 0D, 16D, 16D, 32D)
			);
		}

		@Override
		protected VoxelShape tableSmall()
		{
			return VoxelShaper.or(
					box(1D, 0D, 1D, 4D, 2D, 4D),
					box(1D, 0D, 12D, 4D, 2D, 15D),
					box(12D, 0D, 12D, 15D, 2D, 15D),
					box(12D, 0D, 1D, 15D, 2D, 4D),
					box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
					box(1.5D, 2D, 1.5D, 3.5D, 13D, 3.5D),
					box(1.5D, 2D, 12.5D, 3.5D, 13, 14.5D),
					box(12.5D, 2D, 12.5D, 14.5D, 13D, 14.5D),
					box(0D, 13D, 0D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape tableWide()
		{
			return VoxelShaper.or(
					box(12D, 0D, 1D, 15D, 2D, 4D),
					box(-15D, 0D, 1D, -12D, 2D, 4D),
					box(-15D, 0D, 12D, -12D, 2D, 15D),
					box(12D, 0D, 12D, 15D, 2D, 15D),
					box(12.5D, 2D, 12.5D, 14.5D, 13D, 14.5D),
					box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
					box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
					box(-14.5D, 2D, 12.5D, -12.5D, 13D, 14.5D),
					box(-16D, 13D, 0D, 16D, 16D, 16D)
			);
		}

		@Override
		protected VoxelShape wallLight()
		{
			return VoxelShaper.or(
					box(6D, 1D, 15D, 10D, 3D, 16D),
					box(5D, 3D, 15D, 11D, 12D, 16D),
					box(6D, 12D, 15D, 10D, 14D, 16D),
					box(7D, 3.5D, 14D, 9D, 5.5D, 15D),
					box(4.25D, 2.5D, 10.5D, 11.75D, 11.5D, 14D)
			);
		}

		@Override
		protected VoxelShape wardrobe()
		{
			return VoxelShaper.or(
					box(-16D, 0D, 0D, -12D, 3D, 4D),
					box(-16D, 0D, 12D, -12D, 3D, 16D),
					box(12D, 0D, 12D, 16D, 3D, 16D),
					box(12D, 0D, 0D, 16D, 3D, 4D),
					box(-15D, 1D, 1D, 15D, 29D, 15D),
					box(-16D, 29D, 0D, 16D, 32D, 16D)
			);
		}

		@Override
		protected VoxelShape wardrobeTopper()
		{
			return VoxelShaper.or(
					box(-15D, 0D, 1D, 15D, 9D, 15D),
					box(-16D, 9D, 0D, 16D, 11D, 16D)
			);
		}
	}
}