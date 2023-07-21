package xyz.apex.minecraft.fantasyfurniture.neoforge.nordic.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.nordic.NordicBlocks;
import xyz.apex.minecraft.fantasyfurniture.common.nordic.NordicFurnitureSet;

import java.util.concurrent.CompletableFuture;

@ApiStatus.Internal
final class ItemTagGenerator extends ItemTagsProvider
{
    ItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagLookup, ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, blockTagLookup, NordicFurnitureSet.ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        tag(ItemTags.WOOL).add(NordicBlocks.WOOL.asItem());
    }
}
