package xyz.apex.forge.fantasyfurniture;

import com.tterrag.registrate.util.entry.RegistryEntry;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.ForgeRegistries;

import static xyz.apex.forge.fantasyfurniture.core.ModRegistry.REGISTRATE;

public interface AllParticleTypes
{
	RegistryEntry<SimpleParticleType> SMALL_SOUL_FLAME = REGISTRATE.simple("small_soul_fire_flame", ForgeRegistries.Keys.PARTICLE_TYPES, () -> new SimpleParticleType(false));
	RegistryEntry<SimpleParticleType> NECROLORD_FLAME = REGISTRATE.simple("necrolord_flame", ForgeRegistries.Keys.PARTICLE_TYPES, () -> new SimpleParticleType(false));
	RegistryEntry<SimpleParticleType> SMALL_NECROLORD_FLAME = REGISTRATE.simple("small_necrolord_flame", ForgeRegistries.Keys.PARTICLE_TYPES, () -> new SimpleParticleType(false));

	static void bootstrap()
	{
	}
}
