package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;

public final class BlockTagGenerator extends BlockTagsProvider
{
    BlockTagGenerator(GatherDataEvent event)
    {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        tag(BlockTags.WOOL).add(NordicSet.WOOL.get());
        tag(BlockTags.WOOL_CARPETS).add(NordicSet.CARPET.get());
    }
}
