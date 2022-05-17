package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import org.apache.commons.lang3.Validate;

import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.ModLoadingContext;

import xyz.apex.forge.apexcore.lib.ApexRegistrator;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.block.base.ISeatBlock;
import xyz.apex.java.utility.Lazy;

import static xyz.apex.forge.utility.registrator.provider.RegistrateLangExtProvider.EN_GB;
import static com.tterrag.registrate.providers.ProviderType.LANG;

public final class FFRegistry extends ApexRegistrator<FFRegistry>
{
	private static final Lazy<FFRegistry> REGISTRY = create(FFRegistry::new);

	public static final String TXT_JEI_INGREDIENTS_KEY = "text." + FantasyFurniture.ID + ".jei.ingredients";
	public static final String TXT_JEI_RESULTS_KEY = "text." + FantasyFurniture.ID + ".jei.results";

	private static boolean bootstrap = false;

	private FFRegistry()
	{
		super(FantasyFurniture.ID);

		itemGroup(ModItemGroup::new, "Fantasy's Furniture");

		addDataGenerator(LANG, provider -> {
			provider.add(TXT_JEI_INGREDIENTS_KEY, "Ingredients");
			provider.add(TXT_JEI_RESULTS_KEY, "Results");

			getAll(Block.class)
			        .stream()
			        .filter(RegistryEntry::isPresent)
			        .map(RegistryEntry::get)
			        .filter(ISeatBlock.class::isInstance)
					.map(ISeatBlock.class::cast)
			        .map(ISeatBlock::getOccupiedTranslationKey)
			        .forEach(s -> provider.add(s, "This seat is occupied"));
		});

		addDataGenerator(LANG_EXT_PROVIDER, provider -> {
			provider.add(EN_GB, TXT_JEI_INGREDIENTS_KEY, "Ingredients");
			provider.add(EN_GB, TXT_JEI_RESULTS_KEY, "Results");

			getAll(Block.class)
			        .stream()
			        .filter(RegistryEntry::isPresent)
			        .map(RegistryEntry::get)
			        .filter(ISeatBlock.class::isInstance)
			        .map(ISeatBlock.class::cast)
			        .map(ISeatBlock::getOccupiedTranslationKey)
			        .forEach(s -> provider.add(EN_GB, s, "This seat is occupied"));
		});
	}

	public static void bootstrap()
	{
		if(bootstrap)
			return;

		Validate.isTrue(ModLoadingContext.get().getActiveContainer().getModId().equals(FantasyFurniture.ID), "Only FantasyFurniture can execute FFRegistry#bootstrap()");

		FFPatterns.bootstrap();
		Registrations.bootstrap();
		FurnitureStation.bootstrap();
		Nordic.bootstrap();
		Decorations.bootstrap();

		bootstrap = true;
	}

	public static FFRegistry getInstance()
	{
		return REGISTRY.get();
	}

	public static final class ModItemGroup extends ItemGroup
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
