package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.apexcore.common.util.TagHelper;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

import java.util.concurrent.CompletableFuture;

final class ItemTagGenerator extends ItemTagsProvider
{
    ItemTagGenerator(GatherDataEvent event, CompletableFuture<TagsProvider.TagLookup<Block>> blockTags)
    {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), blockTags, FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
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
