package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.Registrate;
import org.apache.commons.lang3.Validate;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModLoadingContext;

import xyz.apex.forge.commonality.Mods;

public final class ModRegistry
{
	public static final Registrate REGISTRATE = Registrate.create(Mods.FANTASY_FURNITURE);
	public static final CreativeModeTab CREATIVE_MODE_TAB = new CreativeTab();
	private static boolean bootstrap = false;

	public static void bootstrap()
	{
		if(bootstrap)
			return;

		Validate.isTrue(ModLoadingContext.get().getActiveContainer().getModId().equals(Mods.FANTASY_FURNITURE));
		bootstrap = true;

		REGISTRATE.creativeModeTab(() -> CREATIVE_MODE_TAB, "Fantasy's Furniture");

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