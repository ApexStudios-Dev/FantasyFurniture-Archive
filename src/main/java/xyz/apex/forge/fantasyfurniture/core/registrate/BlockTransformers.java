package xyz.apex.forge.fantasyfurniture.core.registrate;

import com.google.common.collect.Lists;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.CopyBlockState;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.apexcore.lib.block.IMultiBlock;
import xyz.apex.forge.apexcore.lib.block.ISeatBlock;
import xyz.apex.forge.apexcore.registrate.BasicRegistrate;
import xyz.apex.forge.apexcore.registrate.builder.BlockBuilder;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.commonality.tags.BlockTags;
import xyz.apex.forge.fantasyfurniture.AllBlocks;
import xyz.apex.forge.fantasyfurniture.common.block.decorations.CandleBlock;
import xyz.apex.forge.fantasyfurniture.common.block.decorations.CookieJarBlock;
import xyz.apex.forge.fantasyfurniture.common.block.decorations.StackedBlock;
import xyz.apex.forge.fantasyfurniture.common.block.furniture.*;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public interface BlockTransformers
{
	static Property<?>[] getIgnoredProperties(Block block, String blockName)
	{
		var list = Lists.<Property<?>>newArrayList();

		if(block instanceof IDyeable)
			list.add(IDyeable.BLOCKSTATE_PROPERTY);
		if(block instanceof BedBlock)
			list.add(BedBlock.OCCUPIED);
		if(block instanceof FloorLightBlock)
			list.add(FloorLightBlock.PART);
		if(block instanceof SimpleWaterloggedBlock)
			list.add(BlockStateProperties.WATERLOGGED);
		if(!(blockName.equals("dunmer/bed_single") || blockName.equals("dunmer/bed_double")) && block instanceof IMultiBlock multiBlock)
			list.add(multiBlock.getMultiBlockPattern().getBlockProperty());
		if(block instanceof ISeatBlock)
			list.add(ISeatBlock.OCCUPIED);
		if(block instanceof FurnitureDoorBlock)
			list.addAll(Arrays.asList(FurnitureDoorBlock.POWERED, FurnitureDoorBlock.HALF));
		if(block instanceof CandleBlock || block instanceof OvenBlock || block instanceof OvenMultiBlock)
		{
			// lit is needed for dunmer oven blocks, flame only visible when active
			// other oven blocks do not currently make use of this property other than light
			if(!AllBlocks.DUNMER_OVEN.is(block))
				list.add(CandleBlock.LIT);
		}

		return list.toArray(Property[]::new);
	}

	static <BLOCK extends FurnitureDoorBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> applyDoorProperties(BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> builder)
	{
		return builder
				.blockState((ctx, provider) -> provider.getVariantBuilder(ctx.get()).forAllStatesExcept(blockState -> {
					var model = getModelFile(ctx, blockState, provider.models());
					var rightHinge = blockState.getOptionalValue(DoorBlock.HINGE).orElse(DoorHingeSide.LEFT) == DoorHingeSide.RIGHT;
					var yRot = (int) BaseBlock.getFacing(blockState).toYRot();
					var open = blockState.getOptionalValue(DoorBlock.OPEN).orElse(false);

					if(open)
						yRot += 90;
					if(rightHinge && open)
						yRot += 180;

					yRot %= 360;

					return ConfiguredModel
							.builder()
							.modelFile(model)
							.rotationY(yRot)
							.build();
				}, getIgnoredProperties(ctx.get(), ctx.getId().getPath())))
				.tag(BlockTags.Vanilla.WOODEN_DOORS)
		;
	}

	static <BLOCK extends Block> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> applyBlockDefaults(BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> builder)
	{
		return builder
				.lang(RegistrateLangProvider.toEnglishName(builder.getName().replace('/', '_')))
				.loot(BlockTransformers::lootTable)
				.transform(BlockTransformers::mineableAxe)
		;
	}

	static <BLOCK extends Block> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> applyFurnitureBlockDefaults(BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> builder)
	{
		return builder
				.transform(BlockTransformers::applyBlockDefaults)
				.noOcclusion()
				.isValidSpawn(BlockHelper::never)
				.isRedstoneConductor(BlockHelper::never)
				.isSuffocating(BlockHelper::never)
				.isViewBlocking(BlockHelper::never)
		;
	}

	static ToIntFunction<BlockState> lightLevel()
	{
		return lightLevel(blockState -> true);
	}

	static ToIntFunction<BlockState> lightLevel(Predicate<BlockState> lightLevelPredicate)
	{
		return blockState -> lightLevelPredicate.test(blockState) && !BaseBlock.isWaterLogged(blockState) ? 14 : 0;
	}

	static <BLOCK extends Block> void simpleBlockState(DataGenContext<Block, BLOCK> ctx, RegistrateBlockstateProvider provider, Function<BlockState, ModelFile> modelGetter)
	{
		provider.getVariantBuilder(ctx.get())
				.forAllStatesExcept(blockState -> ConfiguredModel
								.builder()
								.modelFile(modelGetter.apply(blockState))
								.build(),
						getIgnoredProperties(ctx.get(), ctx.getId().getPath())
				)
		;
	}

	static <BLOCK extends Block> void horizontalBlockState(DataGenContext<Block, BLOCK> ctx, RegistrateBlockstateProvider provider)
	{
		provider.getVariantBuilder(ctx.get())
				.forAllStatesExcept(blockState -> ConfiguredModel
								.builder()
								.rotationY(((int) blockState.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
								.modelFile(getModelFile(ctx, blockState, provider.models()))
								.build(),
						getIgnoredProperties(ctx.get(), ctx.getId().getPath())
				)
		;
	}

	static <BLOCK extends Block> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> applyDyeable(BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> builder)
	{
		return builder.color(() -> () -> (blockState, level, pos, tintIndex) -> tintIndex == (blockState.getBlock() instanceof DyeableBlock || blockState.getBlock() instanceof DyeableCarpetBlock ? 0 : 1) ? IDyeable.getDyeColor(blockState).map(IDyeable::tintFromDyeColor).orElse(-1) : -1);
	}

	static <BLOCK extends Block> BlockModelBuilder getModelFile(DataGenContext<Block, BLOCK> ctx, BlockState blockState, BlockModelProvider provider)
	{
		var suffix = "";

		if(ctx.get() instanceof StackedBlock block)
			suffix = "_%d".formatted(blockState.getValue(block.getStackSizeProperty()));
		else if(ctx.get() instanceof ShelfBlock)
			suffix = "_%s".formatted(blockState.getValue(ShelfBlock.CONNECTION).getSerializedName());
		else if(ctx.get() instanceof SofaBlock)
			suffix = "_%s".formatted(blockState.getValue(SofaBlock.CONNECTION).getSerializedName());
		else if(ctx.get() instanceof CounterBlock)
			suffix = "_%s".formatted(blockState.getValue(CounterBlock.CONNECTION).getSerializedName());
		else if(AllBlocks.DUNMER_BED_DOUBLE.is(ctx.get()) || AllBlocks.DUNMER_BED_SINGLE.is(ctx.get()))
			suffix = ((IMultiBlock) ctx.get()).isMultiBlockOrigin(blockState) ? "_head" : "_foot";
		else if(ctx.get() instanceof FurnitureDoorBlock)
		{
			var hinge = blockState.getValue(FurnitureDoorBlock.HINGE);
			var open = blockState.getValue(FurnitureDoorBlock.OPEN);

			if(open)
				suffix = hinge == DoorHingeSide.RIGHT ? "_left" : "_right";
			else
				suffix = hinge == DoorHingeSide.RIGHT ? "_right" : "_left";
		}
		else if(AllBlocks.DUNMER_OVEN.is(ctx.get()) && blockState.getValue(BlockStateProperties.LIT))
			suffix = "_lit";
		else if(ctx.get() instanceof CookieJarBlock)
			suffix = "_%s".formatted(blockState.getValue(CookieJarBlock.FILL_STAGE).serializedName);

		return provider
				.withExistingParent(
						// <namespace>:generated/block/<path>[suffix] | Model we are generating
						"%s:generated/block/%s%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath(), suffix),
						// <namespace>:block/<path>[suffix] | Existing model, exported from BlockBench
						new ResourceLocation(ctx.getId().getNamespace(), "block/%s%s".formatted(ctx.getId().getPath(), suffix))
				)
				.renderType(new ResourceLocation(Mods.MINECRAFT, "cutout"))
				.texture("particle", getParticlePath(ctx.getId()))
				.texture(getTextureKey(ctx.getId()), getTexturePath(ctx.getId()))
		;
	}

	static ResourceLocation getParticlePath(ResourceLocation registryName)
	{
		var path = registryName.getPath();
		var type = path.substring(0, path.indexOf('/'));

		if(type.equals("decorations"))
		{
			var name = path.substring(type.length() + 1);

			if(name.startsWith("berry_basket"))
				name = "berry_basket";
			else if(name.startsWith("bowl"))
				name = "bowl";
			else if(name.startsWith("tankards"))
				name = "tankards";
			else if(name.startsWith("venthyr"))
				name = "venthyr";
			else if(name.startsWith("dunmer"))
				return new ResourceLocation(registryName.getNamespace(), "block/particles/dunmer");
			else if(name.startsWith("bone"))
			{
				if(name.contains("wither"))
					return new ResourceLocation(registryName.getNamespace(), "block/particles/bone_wither");
				return new ResourceLocation(registryName.getNamespace(), "block/particles/bone");
			}
			else if(name.startsWith("royal"))
				return new ResourceLocation(registryName.getNamespace(), "block/particles/royal");
			else if(name.startsWith("necrolord"))
				return new ResourceLocation(registryName.getNamespace(), "block/particles/necrolord");
			else if(name.startsWith("spider_web"))
				return new ResourceLocation(registryName.getNamespace(), "block/particles/decorations/spider_webs");
			else if(name.startsWith("potion_bottles"))
				return new ResourceLocation(registryName.getNamespace(), "block/particles/decorations/potion_bottles");
			else if(name.startsWith("fairy_lights")) return new ResourceLocation(registryName.getNamespace(), "block/particles/decorations/fairy_lights_1");

			return new ResourceLocation(registryName.getNamespace(), "block/particles/%s/%s".formatted(type, name));
		}
		else if(type.equals("bone"))
		{
			var subType = path.substring(type.length() + 1);

			if(subType.contains("wither"))
				return new ResourceLocation(registryName.getNamespace(), "block/particles/bone_wither");
			return new ResourceLocation(registryName.getNamespace(), "block/particles/bone");
		}

		return new ResourceLocation(registryName.getNamespace(), "block/particles/%s".formatted(type));
	}

	static <BLOCK extends Block> void lootTable(RegistrateBlockLootTables lootTables, BLOCK block)
	{
		final AtomicReference<LootPoolEntryContainer.Builder<?>> item = new AtomicReference<>(LootItem.lootTableItem(block));

		final AtomicReference<LootPool.Builder> pool = new AtomicReference<>(lootTables
				.applyExplosionCondition(block, LootPool.lootPool())
				.setRolls(ConstantValue.exactly(1F))
		);

		if(block instanceof StackedBlock stacked)
		{
			for(var value : stacked.getStackSizeProperty().getPossibleValues())
			{
				pool.getAndUpdate(builder -> builder.apply(SetItemCountFunction
						.setCount(ConstantValue.exactly(value + 1))
						.when(LootItemBlockStatePropertyCondition
								.hasBlockStateProperties(block)
								.setProperties(StatePropertiesPredicate.Builder
										.properties()
										.hasProperty(stacked.getStackSizeProperty(), value)
								)
						)
				));
			}
		}

		if(block instanceof FurnitureDoorBlock)
		{
			item.getAndUpdate(builder -> builder.when(LootItemBlockStatePropertyCondition
					.hasBlockStateProperties(block)
					.setProperties(StatePropertiesPredicate.Builder
							.properties()
							.hasProperty(FurnitureDoorBlock.HALF, DoubleBlockHalf.LOWER)
					)
			));
		}

		if(block instanceof IDyeable)
		{
			pool.getAndUpdate(builder -> builder.apply(CopyBlockState
					.copyState(block)
					.copy(IDyeable.BLOCKSTATE_PROPERTY)
			));
		}

		lootTables.add(block, LootTable.lootTable().withPool(pool.get().add(item.get())));
	}

	static String getTextureKey(ResourceLocation registryName)
	{
		var path = registryName.getPath();
		return path.substring(path.lastIndexOf('/') + 1);
	}

	static ResourceLocation getTexturePath(ResourceLocation registryName)
	{
		var path = registryName.getPath();
		var type = path.substring(0, path.lastIndexOf('/'));
		var name = path.substring(type.length() + 1);

		name = switch(name) {
			case "desk_left", "desk_right" -> "desk";
			case "wardrobe_bottom", "wardrobe_top" -> "wardrobe";
			default -> name;
		};

		return new ResourceLocation(registryName.getNamespace(), "block/models/%s/%s".formatted(type, name));
	}

	static <BLOCK extends Block> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> clearMineable(BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> builder)
	{
		return builder.removeTag(
				BlockTags.Vanilla.MINEABLE_WITH_PICKAXE,
				BlockTags.Vanilla.MINEABLE_WITH_AXE,
				BlockTags.Vanilla.MINEABLE_WITH_SHOVEL,
				BlockTags.Vanilla.MINEABLE_WITH_HOE
		);
	}

	static <BLOCK extends Block> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> mineablePickaxe(BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> builder)
	{
		return builder
				.transform(BlockTransformers::clearMineable)
				.tag(BlockTags.Vanilla.MINEABLE_WITH_PICKAXE)
		;
	}

	static <BLOCK extends Block> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> mineableAxe(BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> builder)
	{
		return builder
				.transform(BlockTransformers::clearMineable)
				.tag(BlockTags.Vanilla.MINEABLE_WITH_AXE)
		;
	}
}
