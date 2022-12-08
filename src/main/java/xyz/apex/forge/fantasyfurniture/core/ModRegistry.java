package xyz.apex.forge.fantasyfurniture.core;

import org.apache.commons.lang3.Validate;

import net.minecraftforge.fml.ModLoadingContext;

import xyz.apex.forge.apexcore.registrate.BasicRegistrate;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.fantasyfurniture.*;
import xyz.apex.forge.fantasyfurniture.core.registrate.DataGenerators;

import static com.tterrag.registrate.providers.ProviderType.*;

public final class ModRegistry
{
	// public static final Lazy<CreativeModeTab> CREATIVE_MODE_TAB = Lazy.of(CreativeTab::new);

	public static final BasicRegistrate REGISTRATE = BasicRegistrate.create(Mods.FANTASY_FURNITURE, registrate -> registrate
			// .creativeModeTab(CREATIVE_MODE_TAB::get, "Fantasy's Furniture") // TODO: See ApexCore
			.addDataGenerator(ITEM_TAGS, DataGenerators::itemTags)
			.addDataGenerator(RECIPE, DataGenerators::recipes)
			.addDataGenerator(LANG, DataGenerators::lang)
			.addDataGenerator(BLOCK_TAGS, DataGenerators::blockTags)
			.addDataGenerator(BLOCKSTATE, DataGenerators::blockState)
	);

	private static boolean bootstrap = false;

	public static void bootstrap()
	{
		if(bootstrap)
			return;

		Validate.isTrue(ModLoadingContext.get().getActiveContainer().getModId().equals(Mods.FANTASY_FURNITURE));
		bootstrap = true;

		AllBlocks.bootstrap();
		AllItems.bootstrap();
		AllBlockEntities.bootstrap();
		AllParticleTypes.bootstrap();
		AllMenus.bootstrap();
		// TODO: See ApexCore
		// AllItemGroupCategories.bootstrap();
		AllRecipeSerializers.bootstrap();
		AllRecipeTypes.bootstrap();

		// TODO: See ApexCore
		/*AllItemGroupCategories.NORDIC.addTranslationGenerator(REGISTRATE, "Nordic");
		AllItemGroupCategories.DUNMER.addTranslationGenerator(REGISTRATE, "Dunmer");
		AllItemGroupCategories.VENTHYR.addTranslationGenerator(REGISTRATE, "Venthyr");
		AllItemGroupCategories.BONE.addTranslationGenerator(REGISTRATE, "Bone");
		// AllItemGroupCategories.BONE_SKELETON.addTranslationGenerator(REGISTRATE, "Bone - Skeleton");
		// AllItemGroupCategories.BONE_WITHER.addTranslationGenerator(REGISTRATE, "Bone - Wither");
		AllItemGroupCategories.DECORATIONS.addTranslationGenerator(REGISTRATE, "Decorations");
		AllItemGroupCategories.ROYAL.addTranslationGenerator(REGISTRATE, "Royal");
		AllItemGroupCategories.NECROLORD.addTranslationGenerator(REGISTRATE, "Necrolord");*/
	}

	// TODO: See ApexCore
	/*private static final class CreativeTab extends CreativeModeTab
	{
		private CreativeTab()
		{
			super(Mods.FANTASY_FURNITURE);
		}

		@Override
		public ItemStack makeIcon()
		{
			return AllItems.NORDIC_BED_SINGLE.asStack();
		}
	}*/
}
