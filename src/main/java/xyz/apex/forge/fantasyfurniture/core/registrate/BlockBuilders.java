package xyz.apex.forge.fantasyfurniture.core.registrate;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.apexcore.registrate.BasicRegistrate;
import xyz.apex.forge.apexcore.registrate.builder.BlockBuilder;
import xyz.apex.forge.apexcore.registrate.builder.factory.BlockFactory;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.commonality.tags.BlockTags;
import xyz.apex.forge.fantasyfurniture.common.block.decorations.BannerBlock;
import xyz.apex.forge.fantasyfurniture.common.block.decorations.CandleBlock;
import xyz.apex.forge.fantasyfurniture.common.block.decorations.*;
import xyz.apex.forge.fantasyfurniture.common.block.furniture.ChestBlock;
import xyz.apex.forge.fantasyfurniture.common.block.furniture.*;

import static xyz.apex.forge.fantasyfurniture.core.ModRegistry.REGISTRATE;

public interface BlockBuilders
{
	static BlockBuilder<BasicRegistrate, BerryBasketBlock, BasicRegistrate> berryBasket(String type)
	{
		return REGISTRATE
				.object("decorations/berry_basket_%s".formatted(type))
				.block(BerryBasketBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang(type.equals("empty") ? "Basket" : "%s Basket".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, BoltsOfClothBlock, BasicRegistrate> boltsOfCloth()
	{
		return REGISTRATE
				.object("decorations/bolts_of_cloth")
				.block(BoltsOfClothBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("Bolts of Cloth")
				.initialProperties(Material.WOOL)
				.strength(.8F)
				.sound(SoundType.WOOL)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends CrownBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> crown(String furnitureType, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("decorations/%s_crown".formatted(furnitureType))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("%s Crown".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.WOOL)
				.strength(.8F)
				.sound(SoundType.WOOL)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends CrownBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> cushionedCrown(String furnitureType, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("decorations/%s_cushioned_crown".formatted(furnitureType))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("%s Cushioned Crown".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.WOOL)
				.strength(.8F)
				.sound(SoundType.WOOL)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, CandelabraBlock, BasicRegistrate> candelabra(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_candelabra".formatted(furnitureType))
				.block(CandelabraBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("%s Candelabra".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
				.lightLevel(BlockTransformers.lightLevel())
		;
	}

	static BlockBuilder<BasicRegistrate, BookStackBlock, BasicRegistrate> bookStack()
	{
		return REGISTRATE
				.object("decorations/book_stack")
				.block(BookStackBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("Book Stack")
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends FloatingTomesBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> floatingTomes(BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("decorations/floating_tomes")
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("Floating Tomes")
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, StackablePumpkinsBlock, BasicRegistrate> stackablePumpkins()
	{
		return REGISTRATE
				.object("decorations/stackable_pumpkins")
				.block(StackablePumpkinsBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("Stackable Pumpkins")
				.initialProperties(Material.WOOD, MaterialColor.COLOR_ORANGE)
				.strength(1F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, BowlBlock, BasicRegistrate> bowl(String type)
	{
		return REGISTRATE
				.object("decorations/bowl_%s".formatted(type))
				.block(BowlBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang(type.equals("empty") ? "Bowl" : "%s Bowl".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, CookieJarBlock, BasicRegistrate> cookieJar()
	{
		return REGISTRATE
				.object("decorations/cookie_jar")
				.block(CookieJarBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.transform(BlockTransformers::mineablePickaxe)
				.lang("Cookie Jar")
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, BannerBlock, BasicRegistrate> banner(String type)
	{
		return REGISTRATE
				.object("decorations/%s_banner".formatted(type))
				.block(BannerBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("%s Banner".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.noCollission()
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, TankardsBlock, BasicRegistrate> tankards(String type)
	{
		return REGISTRATE
				.object("decorations/tankards_%s".formatted(type))
				.block(TankardsBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang(type.equals("empty") ? "Tankards" : type.equals("honeymead") ? "Honeyed Tankards" : "%s Tankards".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, MushroomsBlock, BasicRegistrate> mushrooms(String type, MaterialColor color)
	{
		return REGISTRATE
				.object("decorations/mushrooms_%s".formatted(type))
				.block(MushroomsBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("%s Mushrooms".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.PLANT, color)
				.sound(SoundType.GRASS)
				.noCollission()
				.randomTicks()
				.instabreak()
				.hasPostProcess(BlockHelper::always)
				.offsetType(BlockBehaviour.OffsetType.XZ)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, CoinStackBlock, BasicRegistrate> coinStack(String type)
	{
		return REGISTRATE
				.object("decorations/coin_stack_%s".formatted(type))
				.block(CoinStackBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("%s Coin Stack".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.METAL)
				.strength(2.5F)
				.sound(SoundType.METAL)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, MuffinsBlock, BasicRegistrate> muffins(String type)
	{
		return REGISTRATE
				.object("decorations/muffins_%s".formatted(type))
				.block(MuffinsBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("%s Muffins".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.CAKE)
				.strength(.5F)
				.sound(SoundType.WOOL)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, PaperStackBlock, BasicRegistrate> paperStack()
	{
		return REGISTRATE
				.object("decorations/paper_stack")
				.block(PaperStackBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("Paper Stack")
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends FairyLightsBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> fairyLights(BlockFactory<BLOCK> blockFactory, int type)
	{
		return REGISTRATE
				.object("decorations/fairy_lights_%s".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("Fairy Lights (%d)".formatted(type))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.lightLevel(blockState -> 8)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, StockingBlock, BasicRegistrate> stocking()
	{
		return REGISTRATE
				.object("decorations/stocking")
				.block(StockingBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("Stocking")
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends WallMirrorBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> wallMirror(String furnitureType, String mirrorType, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("decorations/%s_wall_mirror_%s".formatted(furnitureType, mirrorType))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("%s Wall Mirror %s".formatted(RegistrateLangProvider.toEnglishName(furnitureType), RegistrateLangProvider.toEnglishName(mirrorType)))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends WallMirrorMultiBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> wallMirrorMultiBlock(String furnitureType, String mirrorType, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("decorations/%s_wall_mirror_%s".formatted(furnitureType, mirrorType))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("%s Wall Mirror %s".formatted(RegistrateLangProvider.toEnglishName(furnitureType), RegistrateLangProvider.toEnglishName(mirrorType)))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, BoiledCremeTreatsBlock, BasicRegistrate> boiledCremeTreats(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_boiled_creme_treats".formatted(furnitureType))
				.block(BoiledCremeTreatsBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("Boiled Creme Treats")
				.initialProperties(Material.CAKE)
				.strength(.5F)
				.sound(SoundType.WOOL)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, SweetRollsBlock, BasicRegistrate> sweetRolls(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_sweetrolls".formatted(furnitureType))
				.block(SweetRollsBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("Sweetrolls")
				.initialProperties(Material.CAKE)
				.strength(.5F)
				.sound(SoundType.WOOL)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends PresentsStackBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> presentsStack(BlockFactory<BLOCK> blockFactory, int type)
	{
		return REGISTRATE
				.object("decorations/presents_stack_%s".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("Presents Stack (%d)".formatted(type))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, SnowballsBlock, BasicRegistrate> snowballs()
	{
		return REGISTRATE
				.object("decorations/snowballs")
				.block(SnowballsBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("Snowballs")
				.initialProperties(Material.SNOW)
				.strength(1.5F)
				.sound(SoundType.SNOW)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, MeadBottlesBlock, BasicRegistrate> meadBottles(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_mead_bottles".formatted(furnitureType))
				.block(MeadBottlesBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.transform(BlockTransformers::mineablePickaxe)
				.lang("Mead Bottles")
				.initialProperties(Material.GLASS)
				.strength(.3F)
				.sound(SoundType.GLASS)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends PotionBottlesBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> potionBottles(BlockFactory<BLOCK> blockFactory, String suffix)
	{
		return REGISTRATE
				.object("decorations/potion_bottles%s".formatted(suffix))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.transform(BlockTransformers::mineablePickaxe)
				.lang("Potion Bottles")
				.initialProperties(Material.GLASS)
				.strength(.3F)
				.sound(SoundType.GLASS)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, SoulGemsBlock, BasicRegistrate> soulGems(String furnitureType, String type)
	{
		return REGISTRATE
				.object("decorations/%s_soul_gems_%s".formatted(furnitureType, type))
				.block(SoulGemsBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.transform(BlockTransformers::mineablePickaxe)
				.lang(type.equals("dark") ? "Black Soul Gems" : "Soul Gems")
				.initialProperties(Material.GLASS)
				.strength(.3F)
				.sound(SoundType.GLASS)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, GravestoneBlock, BasicRegistrate> gravestone()
	{
		return REGISTRATE
				.object("decorations/gravestone")
				.block(GravestoneBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.transform(BlockTransformers::mineablePickaxe)
				.lang("Gravestone")
				.initialProperties(Material.STONE, MaterialColor.STONE)
				.strength(.3F)
				.sound(SoundType.STONE)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends BrewingCauldronBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> brewingCauldron(BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("decorations/brewing_cauldron")
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.transform(BlockTransformers::mineablePickaxe)
				.lang("Brewing Cauldron")
				.initialProperties(Material.STONE, MaterialColor.STONE)
				.strength(.3F)
				.sound(SoundType.STONE)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, HangingHerbsBlock, BasicRegistrate> hangingHerbs()
	{
		return REGISTRATE
				.object("decorations/hanging_herbs")
				.block(HangingHerbsBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("Hanging Herbs")
				.initialProperties(Material.WOOD, MaterialColor.WOOD)
				.strength(.3F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, ChainBlock, BasicRegistrate> chain(String type)
	{
		return REGISTRATE
				.object("decorations/%s_chain".formatted(type))
				.block(ChainBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("%s Chain".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.METAL, MaterialColor.NONE)
				.strength(5F, 6F)
				.sound(SoundType.CHAIN)
				.blockState((ctx, provider) -> provider.getVariantBuilder(ctx.get()).forAllStatesExcept(blockState -> {
					var model = BlockTransformers.getModelFile(ctx, blockState, provider.models());
					var axis = blockState.getValue(RotatedPillarBlock.AXIS);

					var xRot = 0;
					var yRot = 0;

					if(axis == Direction.Axis.X)
					{
						xRot = 90;
						yRot = 90;
					}
					else if(axis == Direction.Axis.Z)
						xRot = 90;

					return ConfiguredModel
							.builder()
							.modelFile(model)
							.rotationX(xRot)
							.rotationY(yRot)
					.build();
				}, BlockTransformers.getIgnoredProperties(ctx.get(), ctx.getId().getPath())
				))
		;
	}

	static BlockBuilder<BasicRegistrate, SpiderWebBlock, BasicRegistrate> spiderWebSmall()
	{
		return REGISTRATE
				.object("decorations/spider_web_small")
				.block(SpiderWebBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.noCollission()
				.lang("Spider Web Small")
				.initialProperties(Material.WEB)
				.strength(4F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, SpiderWebMultiBlock, BasicRegistrate> spiderWebWide()
	{
		return REGISTRATE
				.object("decorations/spider_web_wide")
				.block(SpiderWebMultiBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.noCollission()
				.lang("Spider Web Wide")
				.initialProperties(Material.WEB)
				.strength(4F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, FoodBlock, BasicRegistrate> food(String furnitureType, int type)
	{
		return REGISTRATE
				.object("decorations/%s_food_%d".formatted(furnitureType, type))
				.block(FoodBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("%s Food %d".formatted(RegistrateLangProvider.toEnglishName(furnitureType), type + 1))
				.initialProperties(Material.CAKE)
				.strength(2.5F)
				.sound(SoundType.WOOL)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, TeaSetBlock, BasicRegistrate> teaSet(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_tea_set".formatted(furnitureType))
				.block(TeaSetBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.transform(BlockTransformers::mineablePickaxe)
				.lang("%s Tea Set".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.METAL)
				.strength(2.5F)
				.sound(SoundType.METAL)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, TeaCupsBlock, BasicRegistrate> teaCups(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_tea_cups".formatted(furnitureType))
				.block(TeaCupsBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.transform(BlockTransformers::mineablePickaxe)
				.lang("%s Tea Cups".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.METAL)
				.strength(2.5F)
				.sound(SoundType.METAL)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, PlatterBlock, BasicRegistrate> platter(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_platter".formatted(furnitureType))
				.block(PlatterBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.transform(BlockTransformers::mineablePickaxe)
				.lang("%s Platter".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.METAL)
				.strength(2.5F)
				.sound(SoundType.METAL)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, WidowBloomBlock, BasicRegistrate> widowBloom(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_widow_bloom".formatted(furnitureType))
				.block(WidowBloomBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.transform(BlockTransformers::mineablePickaxe)
				.lang("Widowbloom Vase")
				.initialProperties(Material.DECORATION)
				.strength(2.5F)
				.sound(SoundType.STONE)
				.blockState((ctx, provider) -> provider.getVariantBuilder(ctx.get()).forAllStatesExcept(blockState -> ConfiguredModel
								.builder()
								.modelFile(provider
										.models()
										.getBuilder("%s:block/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()))
										.texture("particle", "minecraft:block/basalt_top")
								)
						.build(),
						BlockTransformers.getIgnoredProperties(ctx.get(), ctx.getId().getPath()))
				)
		;
	}

	static BlockBuilder<BasicRegistrate, TomesBlock, BasicRegistrate> tomes(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_tomes".formatted(furnitureType))
				.block(TomesBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("%s Tomes".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends ChalicesBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> chalices(String furnitureType, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("decorations/%s_chalices".formatted(furnitureType))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.transform(BlockTransformers::mineablePickaxe)
				.lang("%s Chalices".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.METAL)
				.strength(2.5F)
				.sound(SoundType.METAL)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, BonePileBlock, BasicRegistrate> bonePile(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_pile".formatted(furnitureType))
				.block(BonePileBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.transform(BlockTransformers::mineablePickaxe)
				.lang("%s Pile".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.METAL)
				.strength(2.5F)
				.sound(SoundType.METAL)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, PotteryBlock, BasicRegistrate> pottery(String furnitureType, int type)
	{
		return REGISTRATE
				.object("decorations/%s_pottery_%d".formatted(furnitureType, type))
				.block(PotteryBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.transform(BlockTransformers::mineablePickaxe)
				.lang("%s Pottery %d".formatted(RegistrateLangProvider.toEnglishName(furnitureType), type + 1))
				.initialProperties(Material.DECORATION)
				.strength(2.5F)
				.sound(SoundType.STONE)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static BlockBuilder<BasicRegistrate, CandleBlock, BasicRegistrate> candles(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_candles".formatted(furnitureType))
				.block(CandleBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang(furnitureType.equals("bone") ? "Bone Soul Sand Candles" : "%s Candles".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.DECORATION, MaterialColor.SAND)
				.strength(.1F)
				.sound(SoundType.CANDLE)
				.blockState(BlockTransformers::horizontalBlockState)
				.tag(BlockTags.Vanilla.CANDLES)
		;
	}

	static BlockBuilder<BasicRegistrate, SkullBlossomsBlock, BasicRegistrate> skullBlossoms(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_skull_blossoms".formatted(furnitureType))
				.block(SkullBlossomsBlock::new)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.transform(BlockTransformers::mineablePickaxe)
				.lang("%s Skull Blossoms".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.DECORATION)
				.strength(2.5F)
				.sound(SoundType.STONE)
				.blockState((ctx, provider) -> provider.getVariantBuilder(ctx.get()).forAllStatesExcept(blockState -> ConfiguredModel
								.builder()
								.modelFile(provider
										.models()
										.getBuilder("%s:block/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()))
										.texture("particle", "minecraft:block/basalt_top")
								)
						.build(),
						BlockTransformers.getIgnoredProperties(ctx.get(), ctx.getId().getPath()))
				)
		;
	}

	static <BLOCK extends Block> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> wool(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/wool".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyBlockDefaults)
				.transform(BlockTransformers::clearMineable)
				.initialProperties(Material.WOOL, MaterialColor.WOOL)
				.strength(.8F)
				.sound(SoundType.WOOL)
				.blockState((ctx, provider) -> BlockTransformers.simpleBlockState(ctx, provider, blockState -> provider
						.models()
						.cubeAll("%s:block/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()), new ResourceLocation(ctx.getId().getNamespace(), "block/%s".formatted(ctx.getId().getPath())))
				))
				.tag(BlockTags.Vanilla.WOOL)
		;
	}

	static <BLOCK extends Block> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> woolDyeable(String type, BlockFactory<BLOCK> blockFactory)
	{
		return wool(type, blockFactory)
				.transform(BlockTransformers::applyDyeable)
				.blockState((ctx, provider) -> BlockTransformers.simpleBlockState(ctx, provider, blockState -> provider
						.models()
						.getBuilder("%s:block/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()))
						.parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Mods.FANTASY_FURNITURE, "block/dyeable/cube_all")))
						.texture("all", new ResourceLocation(ctx.getId().getNamespace(), "block/%s".formatted(ctx.getId().getPath())))
						.texture("tint_all", new ResourceLocation(ctx.getId().getNamespace(), "block/%s_tint".formatted(ctx.getId().getPath())))
				))
		;
	}

	static <BLOCK extends CarpetBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> carpet(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/carpet".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyBlockDefaults)
				.transform(BlockTransformers::clearMineable)
				.initialProperties(Material.CLOTH_DECORATION, MaterialColor.WOOL)
				.strength(.1F)
				.sound(SoundType.WOOL)
				.blockState((ctx, provider) -> BlockTransformers.simpleBlockState(ctx, provider, blockState -> provider
						.models()
						.carpet("%s:block/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()), new ResourceLocation(ctx.getId().getNamespace(), "block/%s/wool".formatted(type)))
				))
				.tag(BlockTags.Vanilla.WOOL_CARPETS)
		;
	}

	static <BLOCK extends CarpetBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> carpetDyeable(String type, BlockFactory<BLOCK> blockFactory)
	{
		return carpet(type, blockFactory)
				.transform(BlockTransformers::applyDyeable)
				.blockState((ctx, provider) -> BlockTransformers.simpleBlockState(ctx, provider, blockState -> provider
						.models()
						.getBuilder("%s:block/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()))
						.parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Mods.FANTASY_FURNITURE, "block/dyeable/carpet")))
						.texture("wool", new ResourceLocation(ctx.getId().getNamespace(), "block/%s/wool".formatted(type)))
						.texture("tint_wool", new ResourceLocation(ctx.getId().getNamespace(), "block/%s/wool_tint".formatted(type)))
				))
		;
	}

	static <BLOCK extends FurnitureWallLightBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> wallLight(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/wall_light".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.DECORATION)
				.sound(SoundType.WOOD)
				.instabreak()
				.noCollission()
				.lightLevel(BlockTransformers.lightLevel())
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends FloorLightBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> floorLight(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/floor_light".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.DECORATION)
				.sound(SoundType.WOOD)
				.instabreak()
				.lightLevel(BlockTransformers.lightLevel(blockState -> blockState.getOptionalValue(FloorLightBlock.PART).orElse(FloorLightBlock.Part.BOTTOM).isTop()))
				.blockState((ctx, provider) -> BlockTransformers.simpleBlockState(ctx, provider, blockState -> BlockTransformers.getModelFile(ctx, blockState, provider.models())))
		;
	}

	static <BLOCK extends TableSmallBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> tableSmall(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/table_small".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends TableSmallBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> tableSmallFancy(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/table_small_fancy".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends TableWideBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> tableWide(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/table_wide".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends TableWideBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> tableWideFancy(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/table_wide_fancy".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends TableLargeBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> tableLarge(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/table_large".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends TableLargeBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> tableLargeFancy(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/table_large_fancy".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends StoolBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> stool(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/stool".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends CushionBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> cushion(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/cushion".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends CushionBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> floorCushion(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("decorations/%s_floor_cushion".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.lang("%s Floor Cushion".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends CushionBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> skull(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/skull".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends PaintingSmallBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> paintingSmall(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/painting_small".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.sound(SoundType.WOOD)
				.instabreak()
				.noCollission()
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends PaintingWideBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> paintingWide(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/painting_wide".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.sound(SoundType.WOOD)
				.instabreak()
				.noCollission()
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends DrawerBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> drawer(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/drawer".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends OvenBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> oven(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/oven".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.STONE)
				.strength(3.5F)
				.sound(SoundType.STONE)
				.lightLevel(blockState -> !BaseBlock.isWaterLogged(blockState) && blockState.getValue(BlockStateProperties.LIT) ? 13 : 0)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends OvenMultiBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> ovenMultiblock(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/oven".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.STONE)
				.strength(3.5F)
				.sound(SoundType.STONE)
				.lightLevel(blockState -> !BaseBlock.isWaterLogged(blockState) && blockState.getValue(BlockStateProperties.LIT) ? 13 : 0)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends ShelfBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> shelf(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/shelf".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends SofaBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> sofa(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/sofa".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends CounterBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> counter(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/counter".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends DeskBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> deskLeft(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/desk_left".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends DeskBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> deskRight(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/desk_right".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends ChairBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> chair(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/chair".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends BenchBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> bench(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/bench".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends BookshelfBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> bookshelf(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/bookshelf".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends ChestBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> chest(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/chest".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends DresserBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> dresser(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/dresser".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends WardrobeBottomBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> wardrobeBottom(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/wardrobe_bottom".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends WardrobeTopBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> wardrobeTop(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/wardrobe_top".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}

	static <BLOCK extends BedSingleBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> bedSingle(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/bed_single".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
				.tag(BlockTags.Vanilla.BEDS)
		;
	}

	static <BLOCK extends BedDoubleBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> bedDouble(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/bed_double".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(BlockTransformers::horizontalBlockState)
				.tag(BlockTags.Vanilla.BEDS)
		;
	}

	static <BLOCK extends ChandelierBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> chandelier(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/chandelier".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.lightLevel(BlockTransformers.lightLevel())
				.blockState((ctx, provider) -> BlockTransformers.simpleBlockState(ctx, provider, blockState -> BlockTransformers.getModelFile(ctx, blockState, provider.models())))
		;
	}

	static <BLOCK extends FurnitureDoorBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> doorSingle(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/door_single".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.noOcclusion()
				.blockState(BlockTransformers::horizontalBlockState)
				.transform(BlockTransformers::applyDoorProperties)
		;
	}

	static <BLOCK extends FurnitureDoorBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> doorDouble(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/door_double".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.noOcclusion()
				.blockState(BlockTransformers::horizontalBlockState)
				.transform(BlockTransformers::applyDoorProperties)
		;
	}

	static <BLOCK extends LockboxBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> lockbox(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/lockbox".formatted(type))
				.block(blockFactory)
				.transform(BlockTransformers::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.noOcclusion()
				.blockState(BlockTransformers::horizontalBlockState)
		;
	}
}
