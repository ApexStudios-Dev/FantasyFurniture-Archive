package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.AbstractRegistrate;
import org.apache.commons.lang3.Validate;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import xyz.apex.forge.commonality.Mods;

import static com.tterrag.registrate.providers.ProviderType.LANG;

public final class FFRegistry extends AbstractRegistrate<FFRegistry>
{
	public static final FFRegistry INSTANCE = new FFRegistry();

	public static final String TXT_JEI_INGREDIENTS_KEY = "text." + Mods.FANTASY_FURNITURE + ".jei.ingredients";
	public static final String TXT_JEI_RESULTS_KEY = "text." + Mods.FANTASY_FURNITURE + ".jei.results";

	private static boolean bootstrap = false;

	public static final CreativeModeTab MOD_ITEM_GROUP = new ModItemGroup();

	private FFRegistry()
	{
		super(Mods.FANTASY_FURNITURE);

		creativeModeTab(() -> MOD_ITEM_GROUP, "Fantasy's Furniture");

		addDataGenerator(LANG, provider -> {
			provider.add(TXT_JEI_INGREDIENTS_KEY, "Ingredients");
			provider.add(TXT_JEI_RESULTS_KEY, "Results");
		});
	}

	public static void bootstrap()
	{
		if(bootstrap)
			return;

		Validate.isTrue(ModLoadingContext.get().getActiveContainer().getModId().equals(Mods.FANTASY_FURNITURE), "Only FantasyFurniture can execute FFRegistry#bootstrap()");

		INSTANCE.registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus());

		Registrations.bootstrap();
		FFPatterns.bootstrap();

		FurnitureSet.bootstrap();
		Decorations.bootstrap();

		FurnitureStation.bootstrap();

		FFElements.bootstrap();

		bootstrap = true;
	}

	public static final class ModItemGroup extends CreativeModeTab
	{
		private ModItemGroup()
		{
			super(Mods.FANTASY_FURNITURE);
		}

		@Override
		public ItemStack makeIcon()
		{
			return FurnitureStation.BLOCK.asStack();
		}
	}
}
