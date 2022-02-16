package xyz.apex.forge.fantasyfurniture;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import org.apache.commons.lang3.Validate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import xyz.apex.forge.apexcore.lib.util.ForgeEventBusHelper;
import xyz.apex.forge.apexcore.lib.util.ModEventBusHelper;
import xyz.apex.forge.fantasyfurniture.init.FFBlocks;
import xyz.apex.forge.fantasyfurniture.init.FFRegistry;
import xyz.apex.java.utility.nullness.NonnullPredicate;
import xyz.apex.java.utility.nullness.NonnullSupplier;
import xyz.apex.java.utility.tuple.Pair;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mod(FantasyFurniture.ID)
public final class FantasyFurniture
{
	public static final String ID = "fantasyfurniture";

	private static final Map<PointOfInterestType, PointOfInterestTypeData> poiTypeDataMap = Maps.newHashMap();
	private static boolean registeredPoiData = false;

	public FantasyFurniture()
	{
		FFRegistry.bootstrap();

		ForgeEventBusHelper.addListener(EventPriority.HIGH, this::onVillagerTrades);
		ModEventBusHelper.addEnqueuedListener(this::onCommonSetup);
	}

	private void onCommonSetup(FMLCommonSetupEvent event)
	{
		poiTypeDataMap.values().forEach(PointOfInterestTypeData::register);

		PointOfInterestTypeData homeData = poiTypeDataMap.get(PointOfInterestType.HOME);

		if(homeData != null)
			PointOfInterestType.BEDS = PointOfInterestTypeData.buildNewMatchingStates(PointOfInterestType.BEDS, homeData.blocks);

		registeredPoiData = true;
	}

	private void onVillagerTrades(VillagerTradesEvent event)
	{
		VillagerProfession villagerProfession = event.getType();
		Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();

		if(villagerProfession == VillagerProfession.SHEPHERD)
		{
			List<VillagerTrades.ITrade> newTrades = Lists.newArrayList();
			newTrades.add(new BasicTrade(3, FFBlocks.NORDIC_BED.asItemStack(), 12, 10, .05F));
			trades.get(1).addAll(newTrades);
		}
	}

	public static void registerPoiBlock(PointOfInterestType poiType, NonnullSupplier<? extends Block> block, NonnullPredicate<BlockState> blockStateFilter)
	{
		Validate.isTrue(!registeredPoiData, "You must register custom Poi Block types before FMLCommonSetupEven has fired");
		poiTypeDataMap.computeIfAbsent(poiType, PointOfInterestTypeData::new).blocks.add(Pair.create(block, blockStateFilter));
	}

	public static void registerPoiBlock(PointOfInterestType poiType, NonnullSupplier<? extends Block> block)
	{
		registerPoiBlock(poiType, block, blockState -> true);
	}

	private static final class PointOfInterestTypeData
	{
		private final List<Pair<NonnullSupplier<? extends Block>, NonnullPredicate<BlockState>>> blocks = Lists.newArrayList();
		private final PointOfInterestType poiType;

		private PointOfInterestTypeData(PointOfInterestType poiType)
		{
			this.poiType = poiType;
		}

		private void register()
		{
			poiType.matchingStates = buildNewMatchingStates(poiType.matchingStates, blocks);
		}

		private static ImmutableSet<BlockState> buildNewMatchingStates(Set<BlockState> oldStates, List<Pair<NonnullSupplier<? extends Block>, NonnullPredicate<BlockState>>> blocks)
		{
			ImmutableSet.Builder<BlockState> newMatchingStates = ImmutableSet.builder();
			newMatchingStates.addAll(oldStates);

			for(Pair<NonnullSupplier<? extends Block>, NonnullPredicate<BlockState>> pair : blocks)
			{
				Block block = pair.getKey().get();
				NonnullPredicate<BlockState> blockStateFilter = pair.getValue();

				for(BlockState blockState : PointOfInterestType.getBlockStates(block))
				{
					if(blockStateFilter.test(blockState))
						newMatchingStates.add(blockState);
				}
			}

			return newMatchingStates.build();
		}
	}
}
