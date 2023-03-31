package xyz.apex.minecraft.fantasyfurniture.nordic.forge.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;

import java.util.concurrent.CompletableFuture;

final class ItemTagGenerator extends ItemTagsProvider
{
    ItemTagGenerator(GatherDataEvent event, CompletableFuture<TagLookup<Block>> blockTags)
    {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), blockTags, event.getModContainer().getModId(), event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        copy(BlockTags.WOOL, ItemTags.WOOL);
        copy(BlockTags.WOOL_CARPETS, ItemTags.WOOL_CARPETS);
        copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        copy(BlockTags.BEDS, ItemTags.BEDS);
        copy(NordicFurnitureSet.BLOCK_TAG, NordicFurnitureSet.ITEM_TAG);
    }
}
