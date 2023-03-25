package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.apexcore.common.util.TagHelper;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

import java.util.concurrent.CompletableFuture;

final class ItemTagGenerator extends ItemTagsProvider
{
    ItemTagGenerator(GatherDataEvent event, PackOutput packOutput, CompletableFuture<TagsProvider.TagLookup<Block>> blockTags)
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

        // see FantasyFurniture.ItemTags as to why a custom tag is required
        tag(FantasyFurniture.ItemTags.BOOKSHELF_BOOKS)
                // vanilla books are allowed in our bookshelf
                .add(Items.BOOK, Items.ENCHANTED_BOOK, Items.WRITABLE_BOOK, Items.KNOWLEDGE_BOOK, Items.WRITTEN_BOOK)
                // try to support forge & fabric, unsure if these tags actually exist
                .addOptionalTag(TagHelper.forgeItemTag("books").location())
                .addOptionalTag(TagHelper.fabricItemTag("books").location())
                // include vanilla tag just in case its enabled
                .addOptionalTag(ItemTags.BOOKSHELF_BOOKS.location());
    }
}
