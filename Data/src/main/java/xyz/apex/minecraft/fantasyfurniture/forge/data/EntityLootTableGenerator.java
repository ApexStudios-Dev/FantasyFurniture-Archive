package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraftforge.registries.RegistryObject;

import xyz.apex.minecraft.fantasyfurniture.forge.FantasyFurnitureDataMod;

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
        return FantasyFurnitureDataMod.ENTITY_TYPES.getEntries().stream().map(RegistryObject::get);
    }
}
