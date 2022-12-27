package xyz.apex.minecraft.fantasyfurniture.forge.data;

import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public final class EntityTypeTagGenerator extends EntityTypeTagsProvider
{
    EntityTypeTagGenerator(GatherDataEvent event, PackOutput packOutput)
    {
        super(packOutput, event.getLookupProvider(), FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider)
    {
        tag(FantasyFurniture.SEAT_BLACKLIST).add(EntityType.SHULKER, EntityType.PLAYER);
    }

    private void tag(TagKey<EntityType<?>> tag, @Nullable Object... values)
    {
        if(values == null || values.length == 0) return;
        var builder = tag(tag);
        Arrays.stream(values).filter(Objects::nonNull).forEach(value -> tag(builder, value));
    }

    private void tag(IntrinsicTagAppender<EntityType<?>> builder, Object obj)
    {
        if(obj instanceof TagKey<?> valueTag)
        {
            if(valueTag.isFor(ForgeRegistries.Keys.ENTITY_TYPES)) builder.addOptionalTag(valueTag.location());
        }
        else if(obj instanceof EntityType<?> entityType)
        {
            var registryName = ForgeRegistries.ENTITY_TYPES.getKey(entityType);
            if(registryName != null) builder.addOptional(registryName);
        }
        else if(obj instanceof Supplier<?> supplier) tag(builder, supplier.get()); // should wrap back around to Block instanceof branch
        else LogManager.getLogger().error("Unknown ObjectType: {}", obj.getClass().getCanonicalName());
    }
}
