package xyz.apex.minecraft.fantasyfurniture.forge.data;

import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.minecraft.fantasyfurniture.forge.FantasyFurnitureDataMod;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public final class ItemTagGenerator extends ItemTagsProvider
{
    public ItemTagGenerator(GatherDataEvent event, PackOutput packOutput, BlockTagGenerator blockTags)
    {
        super(packOutput, event.getLookupProvider(), blockTags, FantasyFurnitureDataMod.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        copy(BlockTags.WOOL, ItemTags.WOOL);
        copy(BlockTags.WOOL_CARPETS, ItemTags.WOOL_CARPETS);
    }

    private void tag(TagKey<Item> tag, @Nullable Object... values)
    {
        if(values == null || values.length == 0) return;
        var builder = tag(tag);
        Arrays.stream(values).filter(Objects::nonNull).forEach(value -> tag(builder, value));
    }

    private void tag(IntrinsicTagAppender<Item> builder, Object obj)
    {
        if(obj instanceof TagKey<?> valueTag)
        {
            if(valueTag.isFor(ForgeRegistries.Keys.ITEMS)) builder.addOptionalTag(valueTag.location());
        }
        else if(obj instanceof Item item)
        {
            var registryName = ForgeRegistries.ITEMS.getKey(item);
            if(registryName != null) builder.addOptional(registryName);
        }
        else if(obj instanceof ItemLike itemLike) tag(builder, itemLike.asItem()); // should wrap back around to Item instanceof branch
        else if(obj instanceof Supplier<?> supplier) tag(builder, supplier.get()); // should wrap back around to Item instanceof branch
        else LogManager.getLogger().error("Unknown ObjectType: {}", obj.getClass().getCanonicalName());
    }
}
