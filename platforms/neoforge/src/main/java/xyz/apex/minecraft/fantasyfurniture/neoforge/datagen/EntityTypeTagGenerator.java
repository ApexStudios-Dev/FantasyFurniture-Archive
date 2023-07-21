package xyz.apex.minecraft.fantasyfurniture.neoforge.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.feature.seat.Seat;

import java.util.concurrent.CompletableFuture;

final class EntityTypeTagGenerator extends EntityTypeTagsProvider
{
    EntityTypeTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, FantasyFurniture.ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        tag(Seat.BLACKLIST).add(EntityType.ARMOR_STAND);
    }
}
