package xyz.apex.minecraft.fantasyfurniture.forge.data;

import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.minecraft.apexcore.shared.util.Tags;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public final class BlockTagGenerator extends BlockTagsProvider
{
    BlockTagGenerator(GatherDataEvent event, PackOutput packOutput)
    {
        super(packOutput, event.getLookupProvider(), FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        tag(Tags.Blocks.Vanilla.WOOL, NordicSet.WOOL);
        tag(Tags.Blocks.Vanilla.WOOL_CARPETS, NordicSet.CARPET);

        // multi-blocks have restricted movement
        tag(Tags.Blocks.Fabric.MOVEMENT_RESTRICTED,
                NordicSet.FLOOR_LIGHT, NordicSet.TABLE_LARGE, NordicSet.TABLE_WIDE
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
}
