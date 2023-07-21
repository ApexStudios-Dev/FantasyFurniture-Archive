package xyz.apex.minecraft.fantasyfurniture.neoforge.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

import java.util.concurrent.CompletableFuture;

@ApiStatus.Internal
final class ItemTagGenerator extends ItemTagsProvider
{
    ItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagLookup, ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, blockTagLookup, FantasyFurniture.ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
    }
}
