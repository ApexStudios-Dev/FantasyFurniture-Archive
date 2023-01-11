package xyz.apex.minecraft.fantasyfurniture.forge;

import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.minecraft.apexcore.shared.registry.entry.RegistryEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;

import java.util.stream.Stream;

public final class EntityLootTableGenerator extends EntityLootSubProvider
{
    public EntityLootTableGenerator()
    {
        super(FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate()
    {
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes()
    {
        return FantasyFurniture.REGISTRAR.stream(ForgeRegistries.Keys.ENTITY_TYPES).map(RegistryEntry::get);
    }
}
