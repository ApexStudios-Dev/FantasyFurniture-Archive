package xyz.apex.minecraft.fantasyfurniture.neoforge.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.feature.seat.Seat;
import xyz.apex.minecraft.fantasyfurniture.common.feature.seat.SeatBlock;
import xyz.apex.minecraft.fantasyfurniture.common.feature.station.FurnitureStation;

import java.util.concurrent.CompletableFuture;

@ApiStatus.Internal
final class BlockTagGenerator extends BlockTagsProvider
{
    BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, FantasyFurniture.ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        tag(BlockTags.MINEABLE_WITH_AXE).add(FurnitureStation.BLOCK.get());

        var sittable = tag(Seat.SITTABLE);
        ForgeRegistries.BLOCKS.getValues().stream().filter(this::isDefaultSittable).forEach(sittable::add);
    }

    private boolean isDefaultSittable(Block block)
    {
        var registryName = ForgeRegistries.BLOCKS.getKey(block);

        // modded blocks are not included
        if(registryName == null || !registryName.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE))
            return false;

        // TODO: Do we want to keep vanilla block support in production
        //  was used mainly for testing
        if(block instanceof SlabBlock)
            return true;
        if(block instanceof StairBlock)
            return true;
        // if we add mixins to vanilla blocks in future
        // auto add them to the sittable tag
        if(block instanceof SeatBlock)
            return true;
        return false;
    }
}
