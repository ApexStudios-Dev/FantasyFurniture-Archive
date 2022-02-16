package xyz.apex.forge.fantasyfurniture.init;

import org.apache.commons.lang3.Validate;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.ModLoadingContext;

import xyz.apex.forge.apexcore.lib.item.ItemGroupCategoryManager;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.utility.registrator.AbstractRegistrator;
import xyz.apex.java.utility.Lazy;

public final class FFRegistry extends AbstractRegistrator<FFRegistry>
{
	private static final Lazy<FFRegistry> REGISTRY = create(FFRegistry::new);

	private static boolean bootstrap = false;

	private FFRegistry()
	{
		super(FantasyFurniture.ID);

		//skipErrors();

		itemGroup(ModItemGroup::new, "Fantasy's Furniture");
	}

	public static void bootstrap()
	{
		if(bootstrap)
			return;

		Validate.isTrue(ModLoadingContext.get().getActiveContainer().getModId().equals(FantasyFurniture.ID), "Only FantasyFurniture can execute FFRegistry#bootstrap()");

		FFBlocks.bootstrap();
		FFItems.bootstrap();
		FFBlockEntities.bootstrap();
		FFEntities.bootstrap();
		FFTags.bootstrap();

		FFItemGroupCategories.bootstrap();

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

			ItemGroupCategoryManager.getInstance(this).addCategories(
					FFItemGroupCategories.NORDIC,
					FFItemGroupCategories.DECORATIONS
			);
		}

		@Override
		public ItemStack makeIcon()
		{
			return FFItems.NORDIC_BED_SINGLE.asItemStack();
		}
	}
}
