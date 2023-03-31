package xyz.apex.minecraft.fantasyfurniture.venthyr.forge.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.apexcore.common.ApexCore;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;

final class BlockTagGenerator extends BlockTagsProvider
{
    BlockTagGenerator(GatherDataEvent event, PackOutput packOutput)
    {
        super(packOutput, event.getLookupProvider(), FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        tag(BlockTags.WOOL).add(VenthyrFurnitureSet.Blocks.WOOL.get());
        tag(BlockTags.WOOL_CARPETS).add(VenthyrFurnitureSet.Blocks.CARPET.get());
        tag(BlockTags.WOODEN_DOORS).add(VenthyrFurnitureSet.Blocks.DOOR_DOUBLE.get(), VenthyrFurnitureSet.Blocks.DOOR_SINGLE.get());
        tag(BlockTags.BEDS).add(VenthyrFurnitureSet.Blocks.BED_DOUBLE.get(), VenthyrFurnitureSet.Blocks.BED_SINGLE.get());

        tag(ApexCore.BlockTags.IMMOVABLE).add(
                VenthyrFurnitureSet.Blocks.FLOOR_LIGHT.get(), VenthyrFurnitureSet.Blocks.TABLE_LARGE.get(), VenthyrFurnitureSet.Blocks.TABLE_WIDE.get(),
                VenthyrFurnitureSet.Blocks.BENCH.get(), VenthyrFurnitureSet.Blocks.CHAIR.get(), VenthyrFurnitureSet.Blocks.CHEST.get(),
                VenthyrFurnitureSet.Blocks.BOOKSHELF.get(), VenthyrFurnitureSet.Blocks.DESK_LEFT.get(), VenthyrFurnitureSet.Blocks.DESK_RIGHT.get(),
                VenthyrFurnitureSet.Blocks.DRAWER.get(), VenthyrFurnitureSet.Blocks.DRESSER.get(), VenthyrFurnitureSet.Blocks.WARDROBE_BOTTOM.get(),
                VenthyrFurnitureSet.Blocks.WARDROBE_TOP.get(), VenthyrFurnitureSet.Blocks.PAINTING_WIDE.get(), VenthyrFurnitureSet.Blocks.DOOR_DOUBLE.get(),
                VenthyrFurnitureSet.Blocks.DOOR_SINGLE.get(), VenthyrFurnitureSet.Blocks.BED_SINGLE.get(), VenthyrFurnitureSet.Blocks.BED_DOUBLE.get(),
                VenthyrFurnitureSet.Blocks.TABLE_LARGE_FANCY.get(), VenthyrFurnitureSet.Blocks.TABLE_WIDE_FANCY.get()
        );

        tag(VenthyrFurnitureSet.BLOCK_TAG).add(
                VenthyrFurnitureSet.Blocks.WOOL.get(), VenthyrFurnitureSet.Blocks.CARPET.get(), VenthyrFurnitureSet.Blocks.WALL_LIGHT.get(),
                VenthyrFurnitureSet.Blocks.FLOOR_LIGHT.get(), VenthyrFurnitureSet.Blocks.TABLE_LARGE.get(), VenthyrFurnitureSet.Blocks.TABLE_SMALL.get(),
                VenthyrFurnitureSet.Blocks.TABLE_WIDE.get(), VenthyrFurnitureSet.Blocks.BENCH.get(), VenthyrFurnitureSet.Blocks.CHAIR.get(),
                VenthyrFurnitureSet.Blocks.CEILING_LIGHT.get(), VenthyrFurnitureSet.Blocks.CUSHION.get(), VenthyrFurnitureSet.Blocks.STOOL.get(),
                VenthyrFurnitureSet.Blocks.CHEST.get(), VenthyrFurnitureSet.Blocks.BOOKSHELF.get(), VenthyrFurnitureSet.Blocks.DESK_LEFT.get(),
                VenthyrFurnitureSet.Blocks.DESK_RIGHT.get(), VenthyrFurnitureSet.Blocks.DRAWER.get(), VenthyrFurnitureSet.Blocks.DRESSER.get(),
                VenthyrFurnitureSet.Blocks.LOCKBOX.get(), VenthyrFurnitureSet.Blocks.WARDROBE_BOTTOM.get(), VenthyrFurnitureSet.Blocks.WARDROBE_TOP.get(),
                VenthyrFurnitureSet.Blocks.PAINTING_SMALL.get(), VenthyrFurnitureSet.Blocks.PAINTING_WIDE.get(), VenthyrFurnitureSet.Blocks.OVEN.get(),
                VenthyrFurnitureSet.Blocks.DOOR_DOUBLE.get(), VenthyrFurnitureSet.Blocks.DOOR_SINGLE.get(), VenthyrFurnitureSet.Blocks.BED_SINGLE.get(),
                VenthyrFurnitureSet.Blocks.BED_DOUBLE.get(), VenthyrFurnitureSet.Blocks.SHELF.get(), VenthyrFurnitureSet.Blocks.SOFA.get(),
                VenthyrFurnitureSet.Blocks.COUNTER.get(), VenthyrFurnitureSet.Blocks.TABLE_LARGE_FANCY.get(), VenthyrFurnitureSet.Blocks.TABLE_SMALL_FANCY.get(),
                VenthyrFurnitureSet.Blocks.TABLE_WIDE_FANCY.get()
        );
    }
}
