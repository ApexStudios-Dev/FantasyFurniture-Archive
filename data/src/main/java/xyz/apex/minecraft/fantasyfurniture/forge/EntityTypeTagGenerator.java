package xyz.apex.minecraft.fantasyfurniture.forge;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.data.event.GatherDataEvent;

import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

public final class EntityTypeTagGenerator extends EntityTypeTagsProvider
{
    public EntityTypeTagGenerator(GatherDataEvent event, PackOutput packOutput)
    {
        super(packOutput, event.getLookupProvider(), FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider)
    {
        tag(FantasyFurniture.SEAT_BLACKLIST).add(EntityType.SHULKER);
    }
}
