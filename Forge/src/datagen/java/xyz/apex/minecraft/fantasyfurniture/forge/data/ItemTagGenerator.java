package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.data.event.GatherDataEvent;

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;

public final class ItemTagGenerator extends ItemTagsProvider
{
    ItemTagGenerator(GatherDataEvent event, BlockTagGenerator blockTags)
    {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), blockTags, FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        copy(BlockTags.WOOL, ItemTags.WOOL);
        copy(BlockTags.WOOL_CARPETS, ItemTags.WOOL_CARPETS);
    }
}
