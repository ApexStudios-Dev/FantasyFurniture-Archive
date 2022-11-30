package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.data.event.GatherDataEvent;

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;

public final class ItemTagGenerator extends ItemTagsProvider
{
    ItemTagGenerator(GatherDataEvent event, BlockTagGenerator blockTags)
    {
        super(event.getGenerator(), blockTags, FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags()
    {
        copy(BlockTags.WOOL, ItemTags.WOOL);
    }
}
