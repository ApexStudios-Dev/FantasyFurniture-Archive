package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import org.apache.commons.lang3.Validate;

import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.forge.apexcore.registrate.BasicRegistrate;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.fantasyfurniture.block.furniture.IDyeable;

import static com.tterrag.registrate.providers.ProviderType.*;

public final class ModRegistry
{
	public static final String TXT_JEI_INGREDIENTS_KEY = "text.%s.jei.ingredients".formatted(Mods.FANTASY_FURNITURE);
	public static final String TXT_JEI_RESULTS_KEY = "text.%s.jei.results".formatted(Mods.FANTASY_FURNITURE);
	public static final Lazy<CreativeModeTab> CREATIVE_MODE_TAB = Lazy.of(CreativeTab::new);

	public static final BasicRegistrate REGISTRATE = BasicRegistrate.create(Mods.FANTASY_FURNITURE, registrate -> registrate
			.creativeModeTab(CREATIVE_MODE_TAB::get, "Fantasy's Furniture")
			.addDataGenerator(LANG, provider -> {
				provider.add(TXT_JEI_INGREDIENTS_KEY, "Ingredients");
				provider.add(TXT_JEI_RESULTS_KEY, "Results");
				provider.add(IDyeable.TRANSLATION_KEY, "Dyeable");
			})
			.addDataGenerator(ITEM_TAGS, provider -> {
				var tag = provider.tag(IDyeable.TAG);

				registrate.getAll(ForgeRegistries.Keys.BLOCKS)
				          .stream()
				          .map(RegistryEntry::get)
				          .filter(IDyeable.class::isInstance)
				          .map(ItemLike::asItem)
				          .forEach(tag::add)
				;
			})
	);

	private static boolean bootstrap = false;

	public static void bootstrap()
	{
		if(bootstrap)
			return;

		Validate.isTrue(ModLoadingContext.get().getActiveContainer().getModId().equals(Mods.FANTASY_FURNITURE));
		bootstrap = true;

		FurnitureStation.bootstrap();
		ModBlocks.bootstrap();
		ModItems.bootstrap();
		ModElements.bootstrap();
		ModItemGroupCategories.bootstrap();

		REGISTRATE.addDataGenerator(RECIPE, provider -> SpecialRecipeBuilder.special(ModElements.DYEABLE_RECIPE_SERIALIZER.get()).save(provider, ModElements.DYEABLE_RECIPE_SERIALIZER.getId().toString()));
	}

	private static final class CreativeTab extends CreativeModeTab
	{
		private CreativeTab()
		{
			super(Mods.FANTASY_FURNITURE);
		}

		@Override
		public ItemStack makeIcon()
		{
			return ModItems.NORDIC_BED_SINGLE.asStack();
		}
	}
}