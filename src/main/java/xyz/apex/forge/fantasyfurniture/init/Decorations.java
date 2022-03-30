package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
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
import xyz.apex.forge.utility.registrator.entry.BlockEntry;

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

					.blockState((ctx, provider) -> provider.horizontalBlock(ctx.get(), getModelFile(ctx, provider)))
					// .loot((ctx, provider) -> )
					// .recipe((ctx, provider) -> { })

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> provider.withExistingParent(ctx.getName(), getModelPath(ctx.getId())))
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

	private static ResourceLocation getModelPath(String namespace, String path)
	{
		return new ResourceLocation(namespace, "decorations/" + path);
	}

	private static ResourceLocation getModelPath(ResourceLocation name)
	{
		return getModelPath(name.getNamespace(), name.getPath());
	}

	private static <BLOCK extends Block> ModelFile getModelFile(DataGenContext<Block, BLOCK> ctx, RegistrateBlockstateProvider provider)
	{
		return provider.models().getExistingFile(getModelPath(ctx.getId()));
	}
}
