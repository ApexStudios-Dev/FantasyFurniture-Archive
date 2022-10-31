package xyz.apex.forge.fantasyfurniture.events;

import net.minecraft.client.particle.FlameParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.fantasyfurniture.AllParticleTypes;
import xyz.apex.forge.fantasyfurniture.client.renderer.model.SkullBlossomsModel;
import xyz.apex.forge.fantasyfurniture.client.renderer.model.WidowBloomModel;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = Mods.FANTASY_FURNITURE, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents
{
	@SubscribeEvent
	public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
	{
		event.registerLayerDefinition(WidowBloomModel.LAYER_LOCATION, WidowBloomModel::createBodyLayer);
		event.registerLayerDefinition(SkullBlossomsModel.LAYER_LOCATION, SkullBlossomsModel::createBodyLayer);
	}

	@SubscribeEvent
	public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event)
	{
		event.register(AllParticleTypes.SMALL_SOUL_FLAME.get(), FlameParticle.SmallFlameProvider::new);
		event.register(AllParticleTypes.NECROLORD_FLAME.get(), FlameParticle.Provider::new);
		event.register(AllParticleTypes.SMALL_NECROLORD_FLAME.get(), FlameParticle.SmallFlameProvider::new);
	}
}
