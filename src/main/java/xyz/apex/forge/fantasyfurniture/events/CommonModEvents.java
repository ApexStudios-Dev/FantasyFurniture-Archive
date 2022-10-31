package xyz.apex.forge.fantasyfurniture.events;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.resource.PathPackResources;

import xyz.apex.forge.apexcore.lib.item.ItemGroupCategoryManager;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.fantasyfurniture.AllItemGroupCategories;
import xyz.apex.forge.fantasyfurniture.AllParticleTypes;
import xyz.apex.forge.fantasyfurniture.core.ModInitializer;
import xyz.apex.forge.fantasyfurniture.core.ModRegistry;
import xyz.apex.forge.fantasyfurniture.core.data.ParticleProvider;
import xyz.apex.forge.fantasyfurniture.core.net.C2SSyncSelectedResultPacket;

import java.io.IOException;

@SuppressWarnings({ "resource", "unused" })
@Mod.EventBusSubscriber(modid = Mods.FANTASY_FURNITURE, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class CommonModEvents
{
	private static final String OPTIFINE_ID = "optifine";

	@SubscribeEvent
	public static void onCommonSetup(FMLCommonSetupEvent event)
	{
		ModInitializer.NETWORK.registerPacket(C2SSyncSelectedResultPacket.class);

		ItemGroupCategoryManager.getInstance(ModRegistry.CREATIVE_MODE_TAB.get()).addCategories(
				AllItemGroupCategories.NORDIC, AllItemGroupCategories.DUNMER,
				AllItemGroupCategories.VENTHYR, AllItemGroupCategories.BONE,
				/*AllItemGroupCategories.BONE_SKELETON,*/ /*AllItemGroupCategories.BONE_WITHER,*/
				AllItemGroupCategories.ROYAL, AllItemGroupCategories.NECROLORD,
				AllItemGroupCategories.DECORATIONS
		);
	}

	@SubscribeEvent
	public static void onGatherData(GatherDataEvent event)
	{
		var generator = event.getGenerator();
		var includeClient = event.includeClient();

		generator.addProvider(includeClient, new ParticleProvider(generator, event.getExistingFileHelper()) {
			@Override
			public void registerParticleDefs()
			{
				definition(AllParticleTypes.SMALL_SOUL_FLAME.get()).texture(ParticleTypes.SOUL_FIRE_FLAME);
				definition(AllParticleTypes.NECROLORD_FLAME.get()).texture(AllParticleTypes.NECROLORD_FLAME.get());
				definition(AllParticleTypes.SMALL_NECROLORD_FLAME.get()).texture(AllParticleTypes.NECROLORD_FLAME.get());
			}
		});
	}

	@SubscribeEvent
	public static void onRegisterPackFinders(AddPackFindersEvent event)
	{
		// registerBuiltInPack(event, <mod_id>);
		registerBuiltInPack(event, Mods.CTM);
		// registerBuiltInPack(event, OPTIFINE_ID);
		registerBuiltInPack(event, Mods.XYCRAFT_CORE);
	}

	private static void registerBuiltInPack(AddPackFindersEvent event, String modId)
	{
		var modLoaded = modId.equals(OPTIFINE_ID) ? isOptifineInstalled() : ModList.get().isLoaded(modId);

		if(modLoaded)
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

	private static boolean isOptifineInstalled()
	{
		if(ModList.get().isLoaded(OPTIFINE_ID))
			return true;

		try
		{
			Class.forName("net.optifine.ConnectedTextures");
			return true;
		}
		catch(ClassNotFoundException ignored)
		{
		}

		return false;
	}
}
