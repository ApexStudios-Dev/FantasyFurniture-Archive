package xyz.apex.forge.fantasyfurniture.init;

import org.apache.commons.lang3.Validate;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fml.ModLoadingContext;

import xyz.apex.forge.apexcore.lib.ApexRegistrator;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.java.utility.Lazy;

public final class FFRegistry extends ApexRegistrator<FFRegistry>
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
			return Items.STONE.getDefaultInstance();
		}
	}
}
