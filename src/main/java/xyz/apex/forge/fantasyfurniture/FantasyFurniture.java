package xyz.apex.forge.fantasyfurniture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

import xyz.apex.forge.apexcore.lib.util.EventBusHelper;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.fantasyfurniture.client.renderer.model.WidowBloomModel;
import xyz.apex.forge.fantasyfurniture.init.ModRegistry;

@Mod(Mods.FANTASY_FURNITURE)
public final class FantasyFurniture
{
	public static final Logger LOGGER = LogManager.getLogger();

	public FantasyFurniture()
	{
		ModRegistry.bootstrap();
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> Client::new);
	}

	private static final class Client
	{
		private Client()
		{
			EventBusHelper.addListener(EntityRenderersEvent.RegisterLayerDefinitions.class, event -> event.registerLayerDefinition(WidowBloomModel.LAYER_LOCATION, WidowBloomModel::createBodyLayer));
		}
	}
}