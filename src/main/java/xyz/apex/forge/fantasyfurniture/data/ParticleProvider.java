package xyz.apex.forge.fantasyfurniture.data;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public abstract class ParticleProvider implements DataProvider
{
	public static final Logger LOGGER = LogManager.getLogger();

	protected static final ExistingFileHelper.ResourceType TEXTURE = new ExistingFileHelper.ResourceType(PackType.CLIENT_RESOURCES, ".png", "textures");

	protected final DataGenerator generator;
	protected final ExistingFileHelper fileHelper;

	private final Map<ResourceLocation, ParticleDefinition> particleDefinitions = Maps.newHashMap();

	public ParticleProvider(DataGenerator generator, ExistingFileHelper fileHelper)
	{
		this.generator = generator;
		this.fileHelper = fileHelper;
	}

	public abstract void registerParticleDefs();

	@Override
	public final void run(CachedOutput cache) throws IOException
	{
		particleDefinitions.clear();
		registerParticleDefs();
		validateParticleDefs();
		var dataPath = generator.getOutputFolder().resolve("assets");
		particleDefinitions.forEach((particleName, def) -> saveParticleDefinition(cache, particleName, def, dataPath));
	}

	@Override
	public String getName()
	{
		return "Particles";
	}

	private void validateParticleDefs()
	{
		particleDefinitions.forEach((particleName, definition) -> {
			for(var texture : definition.textures)
			{
				var relativePath = new ResourceLocation(texture.getNamespace(), "particle/%s".formatted(texture.getPath()));
				Preconditions.checkArgument(fileHelper.exists(relativePath, TEXTURE), "Texture %s does not exist in any known resource pack", relativePath);
			}
		});
	}

	public static void saveParticleDefinition(CachedOutput cache, ResourceLocation particleName, ParticleDefinition definition, Path dataPath)
	{
		try
		{
			var particlePath = dataPath.resolve(Paths.get(particleName.getNamespace(), "particles", "%s.json".formatted(particleName.getPath())));
			var serialized = serializeParticleDefinition(definition);
			DataProvider.saveStable(cache, serialized, particlePath);
		}
		catch(IOException e)
		{
			LOGGER.error("Couldn't save ParticleDefinition {}", particleName);
		}
	}

	private static JsonElement serializeParticleDefinition(ParticleDefinition definition)
	{
		var json = new JsonObject();

		var texturesJson = new JsonArray();
		definition.textures.stream().map(Object::toString).forEach(texturesJson::add);
		json.add("textures", texturesJson);

		return json;
	}

	/*
		Note: All texture paths are relative to `particle` folder under your namespace/textures path
		 Full relative path looks like so `assets/<namespace>/textures/particle/<particle_texture_path>`
		 Do not include the file extension in the path, it gets appended automatically by the game at runtime
		 and is assumed to be .PNG
		 Example Registration:
			build.texture("my_namespace", "my_particle")
		 This would result in the following path
			`assets/my_namespace/textures/particle/my_particle`
	 */
	public ParticleDefinition definition(ParticleType<?> particleType)
	{
		var name = Objects.requireNonNull(ForgeRegistries.PARTICLE_TYPES.getKey(particleType));
		return particleDefinitions.computeIfAbsent(name, $ -> new ParticleDefinition());
	}

	public final class ParticleDefinition
	{
		private final Set<ResourceLocation> textures = Sets.newHashSet();

		private ParticleDefinition()
		{
		}

		public ParticleDefinition texture(ParticleType<?> particleType)
		{
			var name = Objects.requireNonNull(ForgeRegistries.PARTICLE_TYPES.getKey(particleType));
			return texture(name);
		}

		public ParticleDefinition texture(ResourceLocation texture)
		{
			textures.add(texture);
			return this;
		}

		public ParticleDefinition textures(ResourceLocation... textures)
		{
			Collections.addAll(this.textures, textures);
			return this;
		}

		public ParticleDefinition texture(String namespace, String texturePath)
		{
			return texture(new ResourceLocation(namespace, texturePath));
		}
	}
}