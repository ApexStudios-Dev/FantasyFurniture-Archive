package xyz.apex.minecraft.fantasyfurniture.forge;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;

import xyz.apex.minecraft.apexcore.common.util.ApexTags;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.init.NordicSet;
import xyz.apex.minecraft.fantasyfurniture.common.init.VenthyrSet;

public final class BlockTagGenerator extends BlockTagsProvider
{
    public BlockTagGenerator(GatherDataEvent event, PackOutput packOutput)
    {
        super(packOutput, event.getLookupProvider(), FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        var wool = tag(BlockTags.WOOL);
        var carpets = tag(BlockTags.WOOL_CARPETS);
        var woodenDoors = tag(BlockTags.WOODEN_DOORS);
        var beds = tag(BlockTags.BEDS);
        var immovable = tag(ApexTags.Blocks.IMMOVABLE);

        nordic(wool, carpets, woodenDoors, beds, immovable);
        venthyr(wool, carpets, woodenDoors, beds, immovable);
    }

    private void nordic(IntrinsicTagAppender<Block> wool, IntrinsicTagAppender<Block> carpets, IntrinsicTagAppender<Block> woodenDoors, IntrinsicTagAppender<Block> beds, IntrinsicTagAppender<Block> immovable)
    {
        wool.add(NordicSet.WOOL.get());
        carpets.add(NordicSet.CARPET.get());
        woodenDoors.add(NordicSet.DOOR_DOUBLE.get(), NordicSet.DOOR_SINGLE.get());
        beds.add(NordicSet.BED_SINGLE.get(), NordicSet.BED_DOUBLE.get());

        immovable.add(
                NordicSet.FLOOR_LIGHT.get(), NordicSet.TABLE_LARGE.get(), NordicSet.TABLE_WIDE.get(),
                NordicSet.BENCH.get(), NordicSet.CHAIR.get(), NordicSet.CHEST.get(),
                NordicSet.BOOKSHELF.get(), NordicSet.DESK_LEFT.get(), NordicSet.DESK_RIGHT.get(),
                NordicSet.DRAWER.get(), NordicSet.DRESSER.get(), NordicSet.WARDROBE_BOTTOM.get(),
                NordicSet.WARDROBE_TOP.get(), NordicSet.PAINTING_WIDE.get(), NordicSet.DOOR_DOUBLE.get(),
                NordicSet.DOOR_SINGLE.get(), NordicSet.BED_SINGLE.get(), NordicSet.BED_DOUBLE.get(),
                NordicSet.SHELF.get(), NordicSet.SOFA.get(), NordicSet.COUNTER.get()
        );

        tag(NordicSet.BLOCK_TAG).add(
                NordicSet.WOOL.get(), NordicSet.CARPET.get(), NordicSet.WALL_LIGHT.get(),
                NordicSet.FLOOR_LIGHT.get(), NordicSet.TABLE_LARGE.get(), NordicSet.TABLE_WIDE.get(),
                NordicSet.TABLE_SMALL.get(), NordicSet.BENCH.get(), NordicSet.CHAIR.get(),
                NordicSet.CHANDELIER.get(), NordicSet.CUSHION.get(), NordicSet.STOOL.get(),
                NordicSet.CHEST.get(), NordicSet.BOOKSHELF.get(), NordicSet.DESK_LEFT.get(),
                NordicSet.DESK_RIGHT.get(), NordicSet.DRAWER.get(), NordicSet.DRESSER.get(),
                NordicSet.LOCKBOX.get(), NordicSet.WARDROBE_BOTTOM.get(), NordicSet.WARDROBE_TOP.get(),
                NordicSet.PAINTING_WIDE.get(), NordicSet.PAINTING_SMALL.get(), NordicSet.OVEN.get(), NordicSet.DOOR_DOUBLE.get(),
                NordicSet.DOOR_SINGLE.get(), NordicSet.BED_SINGLE.get(), NordicSet.BED_DOUBLE.get(),
                NordicSet.SHELF.get(), NordicSet.SOFA.get(), NordicSet.COUNTER.get()
        );
    }

    private void venthyr(IntrinsicTagAppender<Block> wool, IntrinsicTagAppender<Block> carpets, IntrinsicTagAppender<Block> woodenDoors, IntrinsicTagAppender<Block> beds, IntrinsicTagAppender<Block> immovable)
    {
        wool.add(VenthyrSet.WOOL.get());
        carpets.add(VenthyrSet.CARPET.get());
        woodenDoors.add(VenthyrSet.DOOR_DOUBLE.get(), VenthyrSet.DOOR_SINGLE.get());
        beds.add(VenthyrSet.BED_SINGLE.get(), VenthyrSet.BED_DOUBLE.get());

        immovable.add(
                VenthyrSet.FLOOR_LIGHT.get(), VenthyrSet.TABLE_LARGE.get(), VenthyrSet.TABLE_LARGE_FANCY.get(),
                VenthyrSet.TABLE_WIDE.get(), VenthyrSet.TABLE_WIDE_FANCY.get(), VenthyrSet.BENCH.get(),
                VenthyrSet.CHAIR.get(), VenthyrSet.CHEST.get(), VenthyrSet.BOOKSHELF.get(),
                VenthyrSet.DESK_LEFT.get(), VenthyrSet.DESK_RIGHT.get(), VenthyrSet.DRAWER.get(),
                VenthyrSet.DRESSER.get(), VenthyrSet.WARDROBE_BOTTOM.get(), VenthyrSet.WARDROBE_TOP.get(),
                VenthyrSet.PAINTING_WIDE.get(), VenthyrSet.DOOR_DOUBLE.get(), VenthyrSet.DOOR_SINGLE.get(),
                VenthyrSet.BED_SINGLE.get(), VenthyrSet.BED_DOUBLE.get(), VenthyrSet.SHELF.get(),
                VenthyrSet.SOFA.get(), VenthyrSet.COUNTER.get()
        );

        tag(VenthyrSet.BLOCK_TAG).add(
                VenthyrSet.WOOL.get(), VenthyrSet.CARPET.get(), VenthyrSet.WALL_LIGHT.get(),
                VenthyrSet.FLOOR_LIGHT.get(), VenthyrSet.TABLE_LARGE.get(), VenthyrSet.TABLE_LARGE_FANCY.get(),
                VenthyrSet.TABLE_WIDE.get(), VenthyrSet.TABLE_WIDE_FANCY.get(), VenthyrSet.TABLE_SMALL.get(),
                VenthyrSet.TABLE_SMALL_FANCY.get(), VenthyrSet.BENCH.get(), VenthyrSet.CHAIR.get(),
                VenthyrSet.CHANDELIER.get(), VenthyrSet.CUSHION.get(), VenthyrSet.STOOL.get(),
                VenthyrSet.CHEST.get(), VenthyrSet.BOOKSHELF.get(), VenthyrSet.DESK_LEFT.get(),
                VenthyrSet.DESK_RIGHT.get(), VenthyrSet.DRAWER.get(), VenthyrSet.DRESSER.get(),
                VenthyrSet.LOCKBOX.get(), VenthyrSet.WARDROBE_BOTTOM.get(), VenthyrSet.WARDROBE_TOP.get(),
                VenthyrSet.PAINTING_WIDE.get(), VenthyrSet.PAINTING_SMALL.get(), VenthyrSet.OVEN.get(),
                VenthyrSet.DOOR_DOUBLE.get(), VenthyrSet.DOOR_SINGLE.get(), VenthyrSet.BED_SINGLE.get(),
                VenthyrSet.BED_DOUBLE.get(), VenthyrSet.SHELF.get(), VenthyrSet.SOFA.get(),
                VenthyrSet.COUNTER.get()
        );
    }
}
