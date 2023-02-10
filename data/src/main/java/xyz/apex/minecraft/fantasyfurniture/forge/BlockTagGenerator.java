package xyz.apex.minecraft.fantasyfurniture.forge;

import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.minecraft.apexcore.common.util.ApexTags;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.init.NordicSet;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public final class BlockTagGenerator extends BlockTagsProvider
{
    public BlockTagGenerator(GatherDataEvent event, PackOutput packOutput)
    {
        super(packOutput, event.getLookupProvider(), FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        tag(BlockTags.WOOL, NordicSet.WOOL);
        tag(BlockTags.WOOL_CARPETS, NordicSet.CARPET);

        tag(BlockTags.WOODEN_DOORS,
                NordicSet.DOOR_DOUBLE, NordicSet.DOOR_SINGLE
        );

        tag(BlockTags.BEDS,
                NordicSet.BED_SINGLE, NordicSet.BED_DOUBLE
        );

        // multi-blocks have restricted movement
        tag(ApexTags.Blocks.IMMOVABLE,
                NordicSet.FLOOR_LIGHT, NordicSet.TABLE_LARGE, NordicSet.TABLE_WIDE,
                NordicSet.BENCH, NordicSet.CHAIR, NordicSet.CHEST, NordicSet.BOOKSHELF,
                NordicSet.DESK_LEFT, NordicSet.DESK_RIGHT, NordicSet.DRAWER, NordicSet.DRESSER,
                NordicSet.WARDROBE_BOTTOM, NordicSet.WARDROBE_TOP, NordicSet.PAINTING_WIDE,
                NordicSet.DOOR_DOUBLE, NordicSet.DOOR_SINGLE, NordicSet.BED_SINGLE, NordicSet.BED_DOUBLE,
                NordicSet.SHELF
        );

        tag(NordicSet.BLOCK_TAG,
                NordicSet.WOOL, NordicSet.CARPET, NordicSet.WALL_LIGHT,
                NordicSet.FLOOR_LIGHT, NordicSet.TABLE_LARGE, NordicSet.TABLE_WIDE,
                NordicSet.TABLE_SMALL, NordicSet.BENCH, NordicSet.CHAIR,
                NordicSet.CHANDELIER, NordicSet.CUSHION, NordicSet.STOOL,
                NordicSet.CHEST, NordicSet.BOOKSHELF, NordicSet.DESK_LEFT,
                NordicSet.DESK_RIGHT, NordicSet.DRAWER, NordicSet.DRESSER,
                NordicSet.LOCKBOX, NordicSet.WARDROBE_BOTTOM, NordicSet.WARDROBE_TOP,
                NordicSet.PAINTING_WIDE, NordicSet.PAINTING_SMALL, NordicSet.OVEN, NordicSet.DOOR_DOUBLE,
                NordicSet.DOOR_SINGLE, NordicSet.BED_SINGLE, NordicSet.BED_DOUBLE,
                NordicSet.SHELF
        );
    }

    private void tag(TagKey<Block> tag, @Nullable Object... values)
    {
        if(values == null || values.length == 0) return;
        var builder = tag(tag);
        Arrays.stream(values).filter(Objects::nonNull).forEach(value -> tag(builder, value));
    }

    private void tag(IntrinsicTagAppender<Block> builder, Object obj)
    {
        if(obj instanceof TagKey<?> valueTag)
        {
            if(valueTag.isFor(ForgeRegistries.Keys.BLOCKS)) builder.addOptionalTag(valueTag.location());
        }
        else if(obj instanceof Block block)
        {
            var registryName = ForgeRegistries.BLOCKS.getKey(block);
            if(registryName != null) builder.addOptional(registryName);
        }
        else if(obj instanceof Supplier<?> supplier) tag(builder, supplier.get()); // should wrap back around to Block instanceof branch
        else LogManager.getLogger().error("Unknown ObjectType: {}", obj.getClass().getCanonicalName());
    }

    private static TagKey<Block> tag(String namespace, String path)
    {
        return TagKey.create(ForgeRegistries.Keys.BLOCKS, new ResourceLocation(namespace, path));
    }
}
