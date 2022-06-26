package xyz.apex.forge.fantasyfurniture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

import xyz.apex.forge.commonality.Mods;

@Mod(Mods.FANTASY_FURNITURE)
public final class FantasyFurniture
{
	public static final Logger LOGGER = LogManager.getLogger();

	public FantasyFurniture()
	{
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> Client::new);
	}

	private static final class Client
	{
		private Client()
		{
		}
	}
}