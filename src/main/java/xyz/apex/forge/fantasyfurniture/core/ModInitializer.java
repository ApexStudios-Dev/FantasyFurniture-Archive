package xyz.apex.forge.fantasyfurniture.core;

import com.tterrag.registrate.util.entry.RegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.fml.common.Mod;

import xyz.apex.forge.apexcore.lib.net.NetworkManager;
import xyz.apex.forge.apexcore.lib.util.EventBusHelper;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.commonality.trust.TrustManager;
import xyz.apex.forge.fantasyfurniture.AllItems;
import xyz.apex.forge.fantasyfurniture.core.registrate.DataGenerators;

@Mod(Mods.FANTASY_FURNITURE)
public final class ModInitializer
{
	public static final Logger LOGGER = LogManager.getLogger();
	public static final NetworkManager NETWORK = new NetworkManager(Mods.FANTASY_FURNITURE, "net", "1");

	public ModInitializer()
	{
		TrustManager.throwIfUntrusted(Mods.FANTASY_FURNITURE);
		ModRegistry.bootstrap();

		EventBusHelper.addListener(CreativeModeTabEvent.Register.class, event -> event.registerCreativeModeTab(new ResourceLocation(Mods.FANTASY_FURNITURE, "main"), builder -> builder
				.displayItems((flags, items, showOp) -> ModRegistry.REGISTRATE
						.getAll(Registries.ITEM)
						.stream()
						.map(RegistryEntry::get)
						.forEach(items::accept))
				.icon(AllItems.NORDIC_BED_SINGLE::asStack)
				.title(Component.translatable(DataGenerators.ITEM_GROUP_KEY))
		));

		EventBusHelper.addListener(CreativeModeTabEvent.BuildContents.class, event -> {
			event.registerSimple(CreativeModeTabs.FUNCTIONAL_BLOCKS, AllItems.FURNITURE_STATION);
		});
	}
}
