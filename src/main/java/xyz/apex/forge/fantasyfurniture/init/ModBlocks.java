package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.commonality.tags.BlockTags;
import xyz.apex.forge.fantasyfurniture.block.FurnitureWallLightBlock;

import static xyz.apex.forge.fantasyfurniture.init.ModRegistry.REGISTRATE;

public final class ModBlocks
{
	public static final BlockEntry<Block> NORDIC_WOOL = wool("nordic", Block::new).register();
	public static final BlockEntry<CarpetBlock> NORDIC_CARPET = carpet("nordic", CarpetBlock::new).register();
	public static final BlockEntry<FurnitureWallLightBlock> NORDIC_WALLIGHT = wallLight("nordic", FurnitureWallLightBlock::new).register();

	static void bootstrap()
	{
	}

	// region: Constructors
	private static <BLOCK extends Block> BlockBuilder<Registrate, BLOCK, Registrate> wool(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/wool".formatted(type))
				.block(blockFactory)
				.lang("%s Wool".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.WOOL, MaterialColor.WOOL)
				.strength(.8F)
				.sound(SoundType.WOOL)
				.noOcclusion()
				.blockstate((ctx, provider) -> provider
						.simpleBlock(ctx.get(), provider
								.models()
								.cubeAll(
										"%s:block/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
										new ResourceLocation(ctx.getId().getNamespace(), "block/%s".formatted(ctx.getId().getPath()))
								)
						)
				)
				.tag(BlockTags.Vanilla.WOOL)
		;
	}

	private static <BLOCK extends CarpetBlock> BlockBuilder<Registrate, BLOCK, Registrate> carpet(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/carpet".formatted(type))
				.block(blockFactory)
				.lang("%s Carpet".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.CLOTH_DECORATION, MaterialColor.WOOL)
				.strength(.1F)
				.sound(SoundType.WOOL)
				.noOcclusion()
				.blockstate((ctx, provider) -> provider
						.simpleBlock(ctx.get(), provider
								.models()
								.carpet(
										"%s:block/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
										new ResourceLocation(ctx.getId().getNamespace(), "block/%s/wool".formatted(type))
								)
						)
				)
				.tag(BlockTags.Vanilla.CARPETS)
		;
	}

	private static <BLOCK extends FurnitureWallLightBlock> BlockBuilder<Registrate, BLOCK, Registrate> wallLight(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/wall_light".formatted(type))
				.block(blockFactory)
				.lang("%s Wall Light".formatted(RegistrateLangProvider.toEnglishName(type)))
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.DECORATION)
				.sound(SoundType.WOOD)
				.noOcclusion()
				.instabreak()
				.noCollission()
				.lightLevel(blockState -> FurnitureWallLightBlock.isWaterLogged(blockState) ? 0 : 14)
				.blockstate((ctx, provider) -> provider
						.horizontalBlock(ctx.get(), provider
								.models()
								.getExistingFile(new ResourceLocation(ctx.getId().getNamespace(), "block/%s".formatted(ctx.getId().getPath())))
						)
				)
		;
	}

	private static <BLOCK extends Block> BlockBuilder<Registrate, BLOCK, Registrate> applyFurnitureBlockDefaults(BlockBuilder<Registrate, BLOCK, Registrate> builder)
	{
		return builder
				.isValidSpawn(BlockHelper::never)
				.isRedstoneConductor(BlockHelper::never)
				.isSuffocating(BlockHelper::never)
				.isViewBlocking(BlockHelper::never)
				.addLayer(() -> RenderType::cutout)
		;
	}
	// endregion
}