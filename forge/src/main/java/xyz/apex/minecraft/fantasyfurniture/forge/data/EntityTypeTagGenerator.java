package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.SeatComponent;

final class EntityTypeTagGenerator extends EntityTypeTagsProvider
{
    EntityTypeTagGenerator(GatherDataEvent event, PackOutput packOutput)
    {
        super(packOutput, event.getLookupProvider(), FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider)
    {
        tag(SeatComponent.SEAT_BLACKLIST).add(EntityType.SHULKER);
    }
}
