package xyz.apex.forge.fantasyfurniture.core;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.apex.forge.apexcore.lib.net.NetworkManager;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.commonality.trust.TrustManager;

@Mod(Mods.FANTASY_FURNITURE)
public final class ModInitializer
{
	public static final Logger LOGGER = LogManager.getLogger();
	public static final NetworkManager NETWORK = new NetworkManager(Mods.FANTASY_FURNITURE, "net", "1");

	public ModInitializer()
	{
		TrustManager.throwIfUntrusted(Mods.FANTASY_FURNITURE);
		ModRegistry.bootstrap();
	}
}
