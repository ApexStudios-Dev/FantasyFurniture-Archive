package xyz.apex.forge.fantasyfurniture;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import xyz.apex.forge.apexcore.lib.net.NetworkManager;
import xyz.apex.forge.apexcore.lib.util.EventBusHelper;
import xyz.apex.forge.fantasyfurniture.init.FFRegistry;
import xyz.apex.forge.fantasyfurniture.net.ServerTransferFurnitureStationResultPacket;

@Mod(FantasyFurniture.ID)
public final class FantasyFurniture
{
	public static final String ID = "fantasyfurniture";
	public static final NetworkManager NETWORK = new NetworkManager(ID, "network", "1");

	public FantasyFurniture()
	{
		FFRegistry.bootstrap();

		EventBusHelper.addEnqueuedListener(FMLCommonSetupEvent.class, event -> NETWORK.registerPackets(ServerTransferFurnitureStationResultPacket.class));
	}
}
