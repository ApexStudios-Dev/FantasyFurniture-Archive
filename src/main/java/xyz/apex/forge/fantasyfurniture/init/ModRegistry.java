package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.Registrate;
import org.apache.commons.lang3.Validate;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModLoadingContext;

import xyz.apex.forge.commonality.Mods;

import static com.tterrag.registrate.providers.ProviderType.LANG;

public final class ModRegistry
{
	public static final Registrate REGISTRATE = Registrate.create(Mods.FANTASY_FURNITURE);
	public static final CreativeModeTab CREATIVE_MODE_TAB = new CreativeTab();
	private static boolean bootstrap = false;

	public static final String TXT_JEI_INGREDIENTS_KEY = "text.%s.jei.ingredients".formatted(Mods.FANTASY_FURNITURE);
	public static final String TXT_JEI_RESULTS_KEY = "text.%s.jei.results".formatted(Mods.FANTASY_FURNITURE);

	public static void bootstrap()
	{
		if(bootstrap)
			return;

		Validate.isTrue(ModLoadingContext.get().getActiveContainer().getModId().equals(Mods.FANTASY_FURNITURE));
		bootstrap = true;

		REGISTRATE.creativeModeTab(() -> CREATIVE_MODE_TAB, "Fantasy's Furniture");

		REGISTRATE.addDataGenerator(LANG, provider -> {
			provider.add(TXT_JEI_INGREDIENTS_KEY, "Ingredients");
			provider.add(TXT_JEI_RESULTS_KEY, "Results");
		});

		FurnitureStation.bootstrap();
		ModBlocks.bootstrap();
		ModItems.bootstrap();
		ModElements.bootstrap();
		ModItemGroupCategories.bootstrap();
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