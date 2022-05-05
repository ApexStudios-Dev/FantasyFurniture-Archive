package xyz.apex.forge.fantasyfurniture;

import net.minecraftforge.fml.common.Mod;

import xyz.apex.forge.fantasyfurniture.init.FFRegistry;

@Mod(FantasyFurniture.ID)
public final class FantasyFurniture
{
	public static final String ID = "fantasyfurniture";

	public FantasyFurniture()
	{
		FFRegistry.bootstrap();
	}
}
