package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.Registrate;
import org.apache.commons.lang3.Validate;

import net.minecraftforge.fml.ModLoadingContext;

import xyz.apex.forge.commonality.Mods;

public final class ModRegistry
{
	public static final Registrate REGISTRATE = Registrate.create(Mods.FANTASY_FURNITURE);
	private static boolean bootstrap = false;

	public static void bootstrap()
	{
		if(bootstrap)
			return;

		Validate.isTrue(ModLoadingContext.get().getActiveContainer().getModId().equals(Mods.FANTASY_FURNITURE));
		bootstrap = true;

		ModBlocks.bootstrap();
		ModItems.bootstrap();
		ModElements.bootstrap();
	}
}