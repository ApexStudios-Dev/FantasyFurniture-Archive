package xyz.apex.forge.fantasyfurniture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.MissingMappingsEvent;
import net.minecraftforge.resource.PathPackResources;

import xyz.apex.forge.apexcore.lib.net.NetworkManager;
import xyz.apex.forge.apexcore.lib.util.EventBusHelper;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.fantasyfurniture.block.furniture.IDyeable;
import xyz.apex.forge.fantasyfurniture.client.renderer.model.SkullBlossomsModel;
import xyz.apex.forge.fantasyfurniture.client.renderer.model.WidowBloomModel;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModElements;
import xyz.apex.forge.fantasyfurniture.init.ModItems;
import xyz.apex.forge.fantasyfurniture.init.ModRegistry;

import java.io.IOException;

@Mod(Mods.FANTASY_FURNITURE)
public final class FantasyFurniture
{
	public static final Logger LOGGER = LogManager.getLogger();
	public static final NetworkManager NETWORK = new NetworkManager(Mods.FANTASY_FURNITURE, "net", "1");

	public FantasyFurniture()
	{
		ModRegistry.bootstrap();
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> Client::new);

		// EventBusHelper breaks when using MissingMappings event
		// It registers to the mod bus since the event implements the IModBus interface
		// although the event is fired on the forge bus, notify forge about this? (should the event even implement the interface? it's a marker to say what bus events should be registered to and fired on)
		MinecraftForge.EVENT_BUS.addListener(this::onMissingMappings);
		MinecraftForge.EVENT_BUS.addListener(this::onBlockPlaced);

		EventBusHelper.addListener(AddPackFindersEvent.class, event -> {
			// registerBuiltInPack(event, <mod_id>);
		});
	}

	private void onMissingMappings(MissingMappingsEvent event)
	{
		var blockMappings = event.getMappings(Registry.BLOCK_REGISTRY, Mods.FANTASY_FURNITURE);

		if(!blockMappings.isEmpty())
		{
			for(var mapping : blockMappings)
			{
				switch(mapping.getKey().toString())
				{
					case "fantasyfurniture:bone/bed_double" -> mapping.remap(ModBlocks.BONE_SKELETON_BED_DOUBLE.get());
					case "fantasyfurniture:bone/bed_single" -> mapping.remap(ModBlocks.BONE_SKELETON_BED_SINGLE.get());
					case "fantasyfurniture:bone/bench" -> mapping.remap(ModBlocks.BONE_SKELETON_BENCH.get());
					case "fantasyfurniture:bone/bookshelf" -> mapping.remap(ModBlocks.BONE_SKELETON_BOOKSHELF.get());
					case "fantasyfurniture:bone/carpet" -> mapping.remap(ModBlocks.BONE_SKELETON_CARPET.get());
					case "fantasyfurniture:bone/chair" -> mapping.remap(ModBlocks.BONE_SKELETON_CHAIR.get());
					case "fantasyfurniture:bone/chandelier" -> mapping.remap(ModBlocks.BONE_SKELETON_CHANDELIER.get());
					case "fantasyfurniture:bone/chest" -> mapping.remap(ModBlocks.BONE_SKELETON_CHEST.get());
					case "fantasyfurniture:bone/cushion" -> mapping.remap(ModBlocks.BONE_SKELETON_SKULL.get());
					case "fantasyfurniture:bone/desk_left" -> mapping.remap(ModBlocks.BONE_SKELETON_DESK_LEFT.get());
					case "fantasyfurniture:bone/desk_right" -> mapping.remap(ModBlocks.BONE_SKELETON_DESK_RIGHT.get());
					case "fantasyfurniture:bone/door_double" -> mapping.remap(ModBlocks.BONE_SKELETON_DOOR_DOUBLE.get());
					case "fantasyfurniture:bone/door_single" -> mapping.remap(ModBlocks.BONE_SKELETON_DOOR_SINGLE.get());
					case "fantasyfurniture:bone/drawer" -> mapping.remap(ModBlocks.BONE_SKELETON_DRAWER.get());
					case "fantasyfurniture:bone/dresser" -> mapping.remap(ModBlocks.BONE_SKELETON_DRESSER.get());
					case "fantasyfurniture:bone/floor_light" -> mapping.remap(ModBlocks.BONE_SKELETON_FLOOR_LIGHT.get());
					case "fantasyfurniture:bone/lockbox" -> mapping.remap(ModBlocks.BONE_SKELETON_LOCKBOX.get());
					case "fantasyfurniture:bone/painting_small" -> mapping.remap(ModBlocks.BONE_SKELETON_PAINTING_SMALL.get());
					case "fantasyfurniture:bone/painting_wide" -> mapping.remap(ModBlocks.BONE_SKELETON_PAINTING_WIDE.get());
					case "fantasyfurniture:bone/shelf" -> mapping.remap(ModBlocks.BONE_SKELETON_SHELF.get());
					case "fantasyfurniture:bone/sofa" -> mapping.remap(ModBlocks.BONE_SKELETON_SOFA.get());
					case "fantasyfurniture:bone/stool" -> mapping.remap(ModBlocks.BONE_SKELETON_STOOL.get());
					case "fantasyfurniture:bone/table_large" -> mapping.remap(ModBlocks.BONE_SKELETON_TABLE_LARGE.get());
					case "fantasyfurniture:bone/table_small" -> mapping.remap(ModBlocks.BONE_SKELETON_TABLE_SMALL.get());
					case "fantasyfurniture:bone/table_wide" -> mapping.remap(ModBlocks.BONE_SKELETON_TABLE_WIDE.get());
					case "fantasyfurniture:bone/wall_light" -> mapping.remap(ModBlocks.BONE_SKELETON_WALL_LIGHT.get());
					case "fantasyfurniture:bone/wardrobe_bottom" -> mapping.remap(ModBlocks.BONE_SKELETON_WARDROBE_BOTTOM.get());
					case "fantasyfurniture:bone/wardrobe_top" -> mapping.remap(ModBlocks.BONE_SKELETON_WARDROBE_TOP.get());
					case "fantasyfurniture:bone/wool" -> mapping.remap(ModBlocks.BONE_SKELETON_WOOL.get());
					case "fantasyfurniture:decorations/dunmer/pottery_0" -> mapping.remap(ModBlocks.DUNMER_POTTERY_0.get());
					case "fantasyfurniture:decorations/dunmer/pottery_1" -> mapping.remap(ModBlocks.DUNMER_POTTERY_1.get());
					case "fantasyfurniture:decorations/nordic/boiled_creme_treats" -> mapping.remap(ModBlocks.NORDIC_BOILED_CREME_TREATS.get());
					case "fantasyfurniture:decorations/nordic/mead_bottles" -> mapping.remap(ModBlocks.NORDIC_MEAD_BOTTLES.get());
					case "fantasyfurniture:decorations/nordic/soul_gems_dark" -> mapping.remap(ModBlocks.NORDIC_SOUL_GEMS_DARK.get());
					case "fantasyfurniture:decorations/nordic/soul_gems_light" -> mapping.remap(ModBlocks.NORDIC_SOUL_GEMS_LIGHT.get());
					case "fantasyfurniture:decorations/nordic/sweetrolls" -> mapping.remap(ModBlocks.NORDIC_SWEETROLLS.get());
					case "fantasyfurniture:decorations/venthyr/chalices" -> mapping.remap(ModBlocks.VENTHYR_CHALICES.get());
					case "fantasyfurniture:decorations/venthyr/food_0" -> mapping.remap(ModBlocks.VENTHYR_FOOD_0.get());
					case "fantasyfurniture:decorations/venthyr/food_1" -> mapping.remap(ModBlocks.VENTHYR_FOOD_1.get());
					case "fantasyfurniture:decorations/venthyr/platter" -> mapping.remap(ModBlocks.VENTHYR_PLATTER.get());
					case "fantasyfurniture:decorations/venthyr/tea_cups" -> mapping.remap(ModBlocks.VENTHYR_TEA_CUPS.get());
					case "fantasyfurniture:decorations/venthyr/tea_set" -> mapping.remap(ModBlocks.VENTHYR_TEA_SET.get());
					case "fantasyfurniture:decorations/venthyr/tomes" -> mapping.remap(ModBlocks.VENTHYR_TOMES.get());
					case "fantasyfurniture:decorations/venthyr/widow_bloom" -> mapping.remap(ModBlocks.VENTHYR_WIDOW_BLOOM.get());

					case "fantasyfurniture:decorations/boiled_creme_treats" -> mapping.remap(ModBlocks.NORDIC_BOILED_CREME_TREATS.get());
					case "fantasyfurniture:decorations/chalices" -> mapping.remap(ModBlocks.VENTHYR_CHALICES.get());
					case "fantasyfurniture:decorations/food_0" -> mapping.remap(ModBlocks.VENTHYR_FOOD_0.get());
					case "fantasyfurniture:decorations/food_1" -> mapping.remap(ModBlocks.VENTHYR_FOOD_1.get());
					case "fantasyfurniture:decorations/mead_bottles" -> mapping.remap(ModBlocks.NORDIC_MEAD_BOTTLES.get());
					case "fantasyfurniture:decorations/platter" -> mapping.remap(ModBlocks.VENTHYR_PLATTER.get());
					case "fantasyfurniture:decorations/pottery_0" -> mapping.remap(ModBlocks.DUNMER_POTTERY_0.get());
					case "fantasyfurniture:decorations/pottery_1" -> mapping.remap(ModBlocks.DUNMER_POTTERY_1.get());
					case "fantasyfurniture:decorations/soul_gems_dark" -> mapping.remap(ModBlocks.NORDIC_SOUL_GEMS_DARK.get());
					case "fantasyfurniture:decorations/soul_gems_light" -> mapping.remap(ModBlocks.NORDIC_SOUL_GEMS_LIGHT.get());
					case "fantasyfurniture:decorations/sweetrolls" -> mapping.remap(ModBlocks.NORDIC_SWEETROLLS.get());
					case "fantasyfurniture:decorations/tea_cups" -> mapping.remap(ModBlocks.VENTHYR_TEA_CUPS.get());
					case "fantasyfurniture:decorations/tea_set" -> mapping.remap(ModBlocks.VENTHYR_TEA_SET.get());
					case "fantasyfurniture:decorations/tomes" -> mapping.remap(ModBlocks.VENTHYR_TOMES.get());
					case "fantasyfurniture:decorations/widow_bloom" -> mapping.remap(ModBlocks.VENTHYR_WIDOW_BLOOM.get());
				}
			}
		}

		var itemMappings = event.getMappings(Registry.ITEM_REGISTRY, Mods.FANTASY_FURNITURE);

		if(!itemMappings.isEmpty())
		{
			for(var mapping : itemMappings)
			{
				switch(mapping.getKey().toString())
				{
					case "fantasyfurniture:bone/bed_double" -> mapping.remap(ModItems.BONE_SKELETON_BED_DOUBLE.get());
					case "fantasyfurniture:bone/bed_single" -> mapping.remap(ModItems.BONE_SKELETON_BED_SINGLE.get());
					case "fantasyfurniture:bone/bench" -> mapping.remap(ModItems.BONE_SKELETON_BENCH.get());
					case "fantasyfurniture:bone/bookshelf" -> mapping.remap(ModItems.BONE_SKELETON_BOOKSHELF.get());
					case "fantasyfurniture:bone/carpet" -> mapping.remap(ModItems.BONE_SKELETON_CARPET.get());
					case "fantasyfurniture:bone/chair" -> mapping.remap(ModItems.BONE_SKELETON_CHAIR.get());
					case "fantasyfurniture:bone/chandelier" -> mapping.remap(ModItems.BONE_SKELETON_CHANDELIER.get());
					case "fantasyfurniture:bone/chest" -> mapping.remap(ModItems.BONE_SKELETON_CHEST.get());
					case "fantasyfurniture:bone/cushion" -> mapping.remap(ModItems.BONE_SKELETON_SKULL.get());
					case "fantasyfurniture:bone/desk_left" -> mapping.remap(ModItems.BONE_SKELETON_DESK_LEFT.get());
					case "fantasyfurniture:bone/desk_right" -> mapping.remap(ModItems.BONE_SKELETON_DESK_RIGHT.get());
					case "fantasyfurniture:bone/door_double" -> mapping.remap(ModItems.BONE_SKELETON_DOOR_DOUBLE.get());
					case "fantasyfurniture:bone/door_single" -> mapping.remap(ModItems.BONE_SKELETON_DOOR_SINGLE.get());
					case "fantasyfurniture:bone/drawer" -> mapping.remap(ModItems.BONE_SKELETON_DRAWER.get());
					case "fantasyfurniture:bone/dresser" -> mapping.remap(ModItems.BONE_SKELETON_DRESSER.get());
					case "fantasyfurniture:bone/floor_light" -> mapping.remap(ModItems.BONE_SKELETON_FLOOR_LIGHT.get());
					case "fantasyfurniture:bone/lockbox" -> mapping.remap(ModItems.BONE_SKELETON_LOCKBOX.get());
					case "fantasyfurniture:bone/painting_small" -> mapping.remap(ModItems.BONE_SKELETON_PAINTING_SMALL.get());
					case "fantasyfurniture:bone/painting_wide" -> mapping.remap(ModItems.BONE_SKELETON_PAINTING_WIDE.get());
					case "fantasyfurniture:bone/shelf" -> mapping.remap(ModItems.BONE_SKELETON_SHELF.get());
					case "fantasyfurniture:bone/sofa" -> mapping.remap(ModItems.BONE_SKELETON_SOFA.get());
					case "fantasyfurniture:bone/stool" -> mapping.remap(ModItems.BONE_SKELETON_STOOL.get());
					case "fantasyfurniture:bone/table_large" -> mapping.remap(ModItems.BONE_SKELETON_TABLE_LARGE.get());
					case "fantasyfurniture:bone/table_small" -> mapping.remap(ModItems.BONE_SKELETON_TABLE_SMALL.get());
					case "fantasyfurniture:bone/table_wide" -> mapping.remap(ModItems.BONE_SKELETON_TABLE_WIDE.get());
					case "fantasyfurniture:bone/wall_light" -> mapping.remap(ModItems.BONE_SKELETON_WALL_LIGHT.get());
					case "fantasyfurniture:bone/wardrobe_bottom" -> mapping.remap(ModItems.BONE_SKELETON_WARDROBE_BOTTOM.get());
					case "fantasyfurniture:bone/wardrobe_top" -> mapping.remap(ModItems.BONE_SKELETON_WARDROBE_TOP.get());
					case "fantasyfurniture:bone/wool" -> mapping.remap(ModItems.BONE_SKELETON_WOOL.get());
					case "fantasyfurniture:decorations/dunmer/pottery_0" -> mapping.remap(ModItems.DUNMER_POTTERY_0.get());
					case "fantasyfurniture:decorations/dunmer/pottery_1" -> mapping.remap(ModItems.DUNMER_POTTERY_1.get());
					case "fantasyfurniture:decorations/nordic/boiled_creme_treats" -> mapping.remap(ModItems.NORDIC_BOILED_CREME_TREATS.get());
					case "fantasyfurniture:decorations/nordic/mead_bottles" -> mapping.remap(ModItems.NORDIC_MEAD_BOTTLES.get());
					case "fantasyfurniture:decorations/nordic/soul_gems_dark" -> mapping.remap(ModItems.NORDIC_SOUL_GEMS_DARK.get());
					case "fantasyfurniture:decorations/nordic/soul_gems_light" -> mapping.remap(ModItems.NORDIC_SOUL_GEMS_LIGHT.get());
					case "fantasyfurniture:decorations/nordic/sweetrolls" -> mapping.remap(ModItems.NORDIC_SWEETROLLS.get());
					case "fantasyfurniture:decorations/venthyr/chalices" -> mapping.remap(ModItems.VENTHYR_CHALICES.get());
					case "fantasyfurniture:decorations/venthyr/food_0" -> mapping.remap(ModItems.VENTHYR_FOOD_0.get());
					case "fantasyfurniture:decorations/venthyr/food_1" -> mapping.remap(ModItems.VENTHYR_FOOD_1.get());
					case "fantasyfurniture:decorations/venthyr/platter" -> mapping.remap(ModItems.VENTHYR_PLATTER.get());
					case "fantasyfurniture:decorations/venthyr/tea_cups" -> mapping.remap(ModItems.VENTHYR_TEA_CUPS.get());
					case "fantasyfurniture:decorations/venthyr/tea_set" -> mapping.remap(ModItems.VENTHYR_TEA_SET.get());
					case "fantasyfurniture:decorations/venthyr/tomes" -> mapping.remap(ModItems.VENTHYR_TOMES.get());
					case "fantasyfurniture:decorations/venthyr/widow_bloom" -> mapping.remap(ModItems.VENTHYR_WIDOW_BLOOM.get());

					case "fantasyfurniture:decorations/boiled_creme_treats" -> mapping.remap(ModItems.NORDIC_BOILED_CREME_TREATS.get());
					case "fantasyfurniture:decorations/chalices" -> mapping.remap(ModItems.VENTHYR_CHALICES.get());
					case "fantasyfurniture:decorations/food_0" -> mapping.remap(ModItems.VENTHYR_FOOD_0.get());
					case "fantasyfurniture:decorations/food_1" -> mapping.remap(ModItems.VENTHYR_FOOD_1.get());
					case "fantasyfurniture:decorations/mead_bottles" -> mapping.remap(ModItems.NORDIC_MEAD_BOTTLES.get());
					case "fantasyfurniture:decorations/platter" -> mapping.remap(ModItems.VENTHYR_PLATTER.get());
					case "fantasyfurniture:decorations/pottery_0" -> mapping.remap(ModItems.DUNMER_POTTERY_0.get());
					case "fantasyfurniture:decorations/pottery_1" -> mapping.remap(ModItems.DUNMER_POTTERY_1.get());
					case "fantasyfurniture:decorations/soul_gems_dark" -> mapping.remap(ModItems.NORDIC_SOUL_GEMS_DARK.get());
					case "fantasyfurniture:decorations/soul_gems_light" -> mapping.remap(ModItems.NORDIC_SOUL_GEMS_LIGHT.get());
					case "fantasyfurniture:decorations/sweetrolls" -> mapping.remap(ModItems.NORDIC_SWEETROLLS.get());
					case "fantasyfurniture:decorations/tea_cups" -> mapping.remap(ModItems.VENTHYR_TEA_CUPS.get());
					case "fantasyfurniture:decorations/tea_set" -> mapping.remap(ModItems.VENTHYR_TEA_SET.get());
					case "fantasyfurniture:decorations/tomes" -> mapping.remap(ModItems.VENTHYR_TOMES.get());
					case "fantasyfurniture:decorations/widow_bloom" -> mapping.remap(ModItems.VENTHYR_WIDOW_BLOOM.get());
				}
			}
		}
	}

	private void onBlockPlaced(BlockEvent.EntityPlaceEvent event)
	{
		var entity = event.getEntity();

		if(entity instanceof LivingEntity living)
		{
			var blockState = event.getPlacedBlock();

			if(IDyeable.hasDyeColorProperty(blockState))
			{
				for(var hand : InteractionHand.values())
				{
					var stack = living.getItemInHand(hand);
					var dyeColor = DyeColor.getColor(stack);

					if(dyeColor != null && IDyeable.setDyeColor(event.getLevel(), event.getPos(), blockState, dyeColor))
					{
						if(!(living instanceof Player player) || !player.isCreative())
							stack.shrink(1);
						return;
					}
				}
			}
		}
	}

	private static void registerBuiltInPack(AddPackFindersEvent event, String modId)
	{
		if(ModList.get().isLoaded(modId))
		{
			try
			{
				var modFile = ModList.get().getModFileById(Mods.FANTASY_FURNITURE).getFile();
				var resourcePath = modFile.findResource("mod_support", modId);
				var pack = new PathPackResources("%s:%s".formatted(modFile.getFileName(), resourcePath), resourcePath);
				var metadataSection = pack.getMetadataSection(PackMetadataSection.SERIALIZER);

				if(metadataSection != null)
				{
					event.addRepositorySource((consumer, constructor) -> consumer
							.accept(constructor.create(
									"builtin/mod_support/%s".formatted(modId),
									Component.literal("Fantasy's Furniture Mod-Support-Pack"),
									false,
									() -> pack,
									metadataSection,
									Pack.Position.TOP,
									PackSource.BUILT_IN,
									false
							))
					);
				}
			}
			catch(IOException e)
			{
				throw new IllegalStateException("Fatal Error occurred while initializing mod-support builtin-resource-pack: '%s'".formatted(modId), e);
			}
		}
	}

	private static final class Client
	{
		private Client()
		{
			EventBusHelper.addListener(EntityRenderersEvent.RegisterLayerDefinitions.class, event -> {
				event.registerLayerDefinition(WidowBloomModel.LAYER_LOCATION, WidowBloomModel::createBodyLayer);
				event.registerLayerDefinition(SkullBlossomsModel.LAYER_LOCATION, SkullBlossomsModel::createBodyLayer);
			});

			EventBusHelper.addListener(RegisterParticleProvidersEvent.class, event -> {
				var particleEngine = Minecraft.getInstance().particleEngine;
				particleEngine.register(ModElements.SMALL_SOUL_FLAME.get(), FlameParticle.SmallFlameProvider::new);
			});
		}
	}
}