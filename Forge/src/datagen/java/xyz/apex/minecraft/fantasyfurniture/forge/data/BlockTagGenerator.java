package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.data.event.GatherDataEvent;

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;

public final class BlockTagGenerator extends BlockTagsProvider
{
    BlockTagGenerator(GatherDataEvent event)
    {
        super(event.getGenerator(), FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags()
    {
        tag(BlockTags.WOOL).add(NordicSet.WOOL.get());
        tag(BlockTags.WOOL_CARPETS).add(NordicSet.CARPET.get());
    }
}
