package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;

import xyz.apex.minecraft.apexcore.shared.platform.Platform;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;

import java.util.stream.Stream;

public final class EntityLootTableGenerator extends EntityLootSubProvider
{
    EntityLootTableGenerator()
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
        return Platform.INSTANCE.registries().getAllKnown(Registries.ENTITY_TYPE, FantasyFurniture.ID).stream();
    }
}
