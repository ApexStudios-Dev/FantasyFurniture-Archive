package xyz.apex.minecraft.fantasyfurniture.forge.data;

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

import xyz.apex.minecraft.fantasyfurniture.forge.FantasyFurnitureDataMod;
import xyz.apex.minecraft.fantasyfurniture.forge.Nordic;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public final class BlockTagGenerator extends BlockTagsProvider
{
    private static final TagKey<Block> MOVEMENT_RESTRICTED = tag("c", "movement_restricted");

    public BlockTagGenerator(GatherDataEvent event, PackOutput packOutput)
    {
        super(packOutput, event.getLookupProvider(), FantasyFurnitureDataMod.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        tag(BlockTags.WOOL, Nordic.WOOL);
        tag(BlockTags.WOOL_CARPETS, Nordic.CARPET);

        // multi-blocks have restricted movement
        tag(MOVEMENT_RESTRICTED,
                Nordic.FLOOR_LIGHT, Nordic.TABLE_LARGE, Nordic.TABLE_WIDE,
                Nordic.BENCH, Nordic.CHAIR, Nordic.CHEST
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
