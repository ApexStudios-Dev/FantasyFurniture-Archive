package xyz.apex.minecraft.fantasyfurniture.forge;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.data.event.GatherDataEvent;

import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

public final class ItemTagGenerator extends ItemTagsProvider
{
    public ItemTagGenerator(GatherDataEvent event, PackOutput packOutput, BlockTagGenerator blockTags)
    {
        super(packOutput, event.getLookupProvider(), blockTags, FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        copy(BlockTags.WOOL, ItemTags.WOOL);
        copy(BlockTags.WOOL_CARPETS, ItemTags.WOOL_CARPETS);
        copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        copy(BlockTags.BEDS, ItemTags.BEDS);
        copy(NordicSet.BLOCK_TAG, NordicSet.ITEM_TAG);
        copy(VenthyrSet.BLOCK_TAG, VenthyrSet.ITEM_TAG);
        copy(DunmerSet.BLOCK_TAG, DunmerSet.ITEM_TAG);
        copy(BoneSet.BLOCK_TAG, BoneSet.ITEM_TAG);
        copy(BoneSet.Wither.BLOCK_TAG, BoneSet.Wither.ITEM_TAG);
        copy(BoneSet.Skeleton.BLOCK_TAG, BoneSet.Skeleton.ITEM_TAG);
        copy(NecrolordSet.BLOCK_TAG, NecrolordSet.ITEM_TAG);
        copy(RoyalSet.BLOCK_TAG, RoyalSet.ITEM_TAG);
    }
}
