package xyz.apex.minecraft.fantasyfurniture.bone.forge.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneFurnitureSet;

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
        copy(BoneFurnitureSet.BLOCK_TAG, BoneFurnitureSet.ITEM_TAG);
        copy(BoneFurnitureSet.Wither.BLOCK_TAG, BoneFurnitureSet.Wither.ITEM_TAG);
        copy(BoneFurnitureSet.Skeleton.BLOCK_TAG, BoneFurnitureSet.Skeleton.ITEM_TAG);
    }
}
