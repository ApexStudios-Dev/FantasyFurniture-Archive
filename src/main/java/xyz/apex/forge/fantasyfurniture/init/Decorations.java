package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.providers.RegistrateLangProvider;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.apexcore.lib.util.EventBusHelper;
import xyz.apex.forge.fantasyfurniture.block.BerryBasketBlock;
import xyz.apex.forge.fantasyfurniture.block.BoiledCremeTreatsBlock;
import xyz.apex.forge.fantasyfurniture.block.BoltsOfClothBlock;
import xyz.apex.forge.utility.registrator.entry.BlockEntry;

import javax.annotation.Nullable;

import static xyz.apex.forge.utility.registrator.provider.RegistrateLangExtProvider.EN_GB;

public final class Decorations
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();

	// region: Berry Basket
	public static final BlockEntry<BerryBasketBlock> BERRY_BASKET_EMPTY = berryBasket("empty");
	public static final BlockEntry<BerryBasketBlock> BERRY_BASKET_SWEETBERRY = berryBasket("sweetberry");

	private static BlockEntry<BerryBasketBlock> berryBasket(String type)
	{
		String codeName = "berry_basket_" + type;
		String englishName;

		if(type.equals("empty"))
			englishName = "Berry Basket";
		else
			englishName = RegistrateLangProvider.toEnglishName(type) + " Basket";

		return REGISTRY
				.block(codeName, BerryBasketBlock::new)
					.lang(englishName)
					.lang(EN_GB, englishName)

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.horizontalBlock(ctx.get(), new ModelBuilder(ctx).getFile(provider)))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> provider.withExistingParent(ctx.getName(), new ModelBuilder(ctx.getId()).getCompiledPath()))
					.build()
		.register();
	}
	// endregion

	// region: Boiled Crème Treats
	public static final BlockEntry<BoiledCremeTreatsBlock> BOILED_CREME_TREATS = boiledCremeTreats();

	private static BlockEntry<BoiledCremeTreatsBlock> boiledCremeTreats()
	{
		return REGISTRY
				.block("boiled_creme_treats", BoiledCremeTreatsBlock::new)
					.lang("Boiled Creme Treats")
					.lang(EN_GB, "Boiled Creme Treats")

					.initialProperties(Material.CAKE)
					.strength(2.5F)
					.sound(SoundType.WOOL)
					.noOcclusion()
					.noCollission()

					.blockState((ctx, provider) -> provider.horizontalBlock(ctx.get(), blockState -> {
						int count = blockState.getValue(BoiledCremeTreatsBlock.COUNT);
						return new ModelBuilder(ctx).suffix("_" + count).getFile(provider);
					}))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> provider.withExistingParent(ctx.getName(), new ModelBuilder(ctx.getId()).suffix("_0").getCompiledPath()))
					.build()
		.register();
	}
	// endregion

	// region: Bolts of Cloth
	public static final BlockEntry<BoltsOfClothBlock> BOLTS_OF_CLOTH = boltsOfCloth();

	private static BlockEntry<BoltsOfClothBlock> boltsOfCloth()
	{
		return REGISTRY
				.block("bolts_of_cloth", BoltsOfClothBlock::new)
					.lang("Bolts of Cloth")
					.lang(EN_GB,"Bolts of Cloth")

					.initialProperties(Material.WOOL)
					.strength(2.5F)
					.sound(SoundType.WOOL)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.horizontalBlock(ctx.get(), new ModelBuilder(ctx).getFile(provider)))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> provider.withExistingParent(ctx.getName(), new ModelBuilder(ctx.getId()).getCompiledPath()))
					.build()
		.register();
	}
	// endregion

	static void bootstrap()
	{
		EventBusHelper.addEnqueuedListener(FMLCommonSetupEvent.class, event -> {
			BerryBasketBlock.registerBasketMapping(Items.SWEET_BERRIES, BERRY_BASKET_SWEETBERRY);
		});
	}

	public static final class ModelBuilder
	{
		private final ResourceLocation id;
		@Nullable private String prefix = null;
		@Nullable private String suffix = null;

		private ModelBuilder(ResourceLocation id)
		{
			this.id = id;
		}

		private <BLOCK extends Block> ModelBuilder(DataGenContext<Block, BLOCK> ctx)
		{
			this(ctx.getId());
		}

		public ModelBuilder prefix(String prefix)
		{
			this.prefix = prefix;
			return this;
		}

		public ModelBuilder suffix(String suffix)
		{
			this.suffix = suffix;
			return this;
		}

		public ResourceLocation getCompiledPath()
		{
			String path = "decorations/" + id.getPath();

			if(prefix != null)
				path = "decorations/" + prefix + id.getPath();
			if(suffix != null)
				path = path + suffix;

			return new ResourceLocation(id.getNamespace(), path);
		}

		public ModelFile.ExistingModelFile getFile(RegistrateBlockstateProvider provider)
		{
			return provider.models().getExistingFile(getCompiledPath());
		}

		public ModelFile.ExistingModelFile getFile(RegistrateItemModelProvider provider)
		{
			return provider.getExistingFile(getCompiledPath());
		}
	}
}
