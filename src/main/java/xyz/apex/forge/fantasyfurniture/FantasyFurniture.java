package xyz.apex.forge.fantasyfurniture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;

import xyz.apex.forge.apexcore.lib.util.EventBusHelper;
import xyz.apex.forge.apexcore.lib.util.InterModUtil;
import xyz.apex.forge.fantasyfurniture.init.FFRegistry;
import xyz.apex.forge.fantasyfurniture.init.FurnitureStation;

@Mod(FantasyFurniture.ID)
public final class FantasyFurniture
{
	public static final String ID = "fantasyfurniture";
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String NBT_BLOCK_STATE_TAG = "BlockStateTag";

	public FantasyFurniture()
	{
		FFRegistry.bootstrap();

		EventBusHelper.addEnqueuedListener(InterModProcessEvent.class, event -> {
			event.getIMCStream(str -> str.equals(InterModUtil.FURNITURE_STATION_METHOD)).forEach(imc -> {
				Object obj = imc.getMessageSupplier().get();

				if(obj instanceof ItemStack)
				{
					ItemStack stack = (ItemStack) obj;
					LOGGER.info("Received Furniture Station Result ('{}') from Mod: '{}'", stack.getItem().getRegistryName(), imc.getSenderModId());
					FurnitureStation.registerAdditionalCraftingResult(stack);
				}
			});
		});
	}
}
