package xyz.apex.forge.fantasyfurniture.init;

import org.apache.commons.lang3.Validate;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModLoadingContext;

import xyz.apex.forge.apexcore.lib.ApexRegistrator;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.java.utility.Lazy;

import static xyz.apex.forge.utility.registrator.provider.RegistrateLangExtProvider.EN_GB;
import static com.tterrag.registrate.providers.ProviderType.LANG;

public final class FFRegistry extends ApexRegistrator<FFRegistry>
{
	private static final Lazy<FFRegistry> REGISTRY = create(FFRegistry::new);

	public static final String TXT_JEI_INGREDIENTS_KEY = "text." + FantasyFurniture.ID + ".jei.ingredients";
	public static final String TXT_JEI_RESULTS_KEY = "text." + FantasyFurniture.ID + ".jei.results";

	private static boolean bootstrap = false;

	public static final CreativeModeTab MOD_ITEM_GROUP = new ModItemGroup();

	private FFRegistry()
	{
		super(FantasyFurniture.ID);

		creativeModeTab(() -> MOD_ITEM_GROUP, "Fantasy's Furniture");

		addDataGenerator(LANG, provider -> {
			provider.add(TXT_JEI_INGREDIENTS_KEY, "Ingredients");
			provider.add(TXT_JEI_RESULTS_KEY, "Results");
		});

		addDataGenerator(LANG_EXT_PROVIDER, provider -> {
			provider.add(EN_GB, TXT_JEI_INGREDIENTS_KEY, "Ingredients");
			provider.add(EN_GB, TXT_JEI_RESULTS_KEY, "Results");
		});
	}

	public static void bootstrap()
	{
		if(bootstrap)
			return;

		Validate.isTrue(ModLoadingContext.get().getActiveContainer().getModId().equals(FantasyFurniture.ID), "Only FantasyFurniture can execute FFRegistry#bootstrap()");

		Registrations.bootstrap();
		FFPatterns.bootstrap();

		FurnitureSet.bootstrap();
		Decorations.bootstrap();

		FurnitureStation.bootstrap();

		FFElements.bootstrap();

		bootstrap = true;
	}

	public static FFRegistry getInstance()
	{
		return REGISTRY.get();
	}

	public static final class ModItemGroup extends CreativeModeTab
	{
		private ModItemGroup()
		{
			super(FantasyFurniture.ID);
		}

		@Override
		public ItemStack makeIcon()
		{
			return FurnitureStation.BLOCK.asItemStack();
		}
	}
}
