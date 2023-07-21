package xyz.apex.minecraft.fantasyfurniture.neoforge.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.FurnitureStation;

import java.util.concurrent.CompletableFuture;

@ApiStatus.Internal
final class BlockTagGenerator extends BlockTagsProvider
{
    BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, FantasyFurniture.ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        tag(BlockTags.MINEABLE_WITH_AXE).add(FurnitureStation.BLOCK.get());
    }
}
