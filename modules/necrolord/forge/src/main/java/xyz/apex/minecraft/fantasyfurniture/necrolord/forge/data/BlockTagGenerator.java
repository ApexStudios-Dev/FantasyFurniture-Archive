package xyz.apex.minecraft.fantasyfurniture.necrolord.forge.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.apexcore.common.ApexCore;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.necrolord.common.NecrolordFurnitureSet;

final class BlockTagGenerator extends BlockTagsProvider
{
    BlockTagGenerator(GatherDataEvent event, PackOutput packOutput)
    {
        super(packOutput, event.getLookupProvider(), FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        tag(BlockTags.WOOL).add(NecrolordFurnitureSet.Blocks.WOOL.get());
        tag(BlockTags.WOOL_CARPETS).add(NecrolordFurnitureSet.Blocks.CARPET.get());
        tag(BlockTags.WOODEN_DOORS).add(NecrolordFurnitureSet.Blocks.DOOR_DOUBLE.get(), NecrolordFurnitureSet.Blocks.DOOR_SINGLE.get());
        tag(BlockTags.BEDS).add(NecrolordFurnitureSet.Blocks.BED_DOUBLE.get(), NecrolordFurnitureSet.Blocks.BED_SINGLE.get());

        tag(ApexCore.BlockTags.IMMOVABLE).add(
                NecrolordFurnitureSet.Blocks.FLOOR_LIGHT.get(), NecrolordFurnitureSet.Blocks.TABLE_LARGE.get(), NecrolordFurnitureSet.Blocks.TABLE_WIDE.get(),
                NecrolordFurnitureSet.Blocks.BENCH.get(), NecrolordFurnitureSet.Blocks.CHAIR.get(), NecrolordFurnitureSet.Blocks.CHEST.get(),
                NecrolordFurnitureSet.Blocks.BOOKSHELF.get(), NecrolordFurnitureSet.Blocks.DESK_LEFT.get(), NecrolordFurnitureSet.Blocks.DESK_RIGHT.get(),
                NecrolordFurnitureSet.Blocks.DRAWER.get(), NecrolordFurnitureSet.Blocks.DRESSER.get(), NecrolordFurnitureSet.Blocks.WARDROBE_BOTTOM.get(),
                NecrolordFurnitureSet.Blocks.WARDROBE_TOP.get(), NecrolordFurnitureSet.Blocks.PAINTING_WIDE.get(), NecrolordFurnitureSet.Blocks.DOOR_DOUBLE.get(),
                NecrolordFurnitureSet.Blocks.DOOR_SINGLE.get(), NecrolordFurnitureSet.Blocks.BED_SINGLE.get(), NecrolordFurnitureSet.Blocks.BED_DOUBLE.get()
        );

        tag(NecrolordFurnitureSet.BLOCK_TAG).add(
                NecrolordFurnitureSet.Blocks.WOOL.get(), NecrolordFurnitureSet.Blocks.CARPET.get(), NecrolordFurnitureSet.Blocks.WALL_LIGHT.get(),
                NecrolordFurnitureSet.Blocks.FLOOR_LIGHT.get(), NecrolordFurnitureSet.Blocks.TABLE_LARGE.get(), NecrolordFurnitureSet.Blocks.TABLE_SMALL.get(),
                NecrolordFurnitureSet.Blocks.TABLE_WIDE.get(), NecrolordFurnitureSet.Blocks.BENCH.get(), NecrolordFurnitureSet.Blocks.CHAIR.get(),
                NecrolordFurnitureSet.Blocks.CEILING_LIGHT.get(), NecrolordFurnitureSet.Blocks.CUSHION.get(), NecrolordFurnitureSet.Blocks.STOOL.get(),
                NecrolordFurnitureSet.Blocks.CHEST.get(), NecrolordFurnitureSet.Blocks.BOOKSHELF.get(), NecrolordFurnitureSet.Blocks.DESK_LEFT.get(),
                NecrolordFurnitureSet.Blocks.DESK_RIGHT.get(), NecrolordFurnitureSet.Blocks.DRAWER.get(), NecrolordFurnitureSet.Blocks.DRESSER.get(),
                NecrolordFurnitureSet.Blocks.LOCKBOX.get(), NecrolordFurnitureSet.Blocks.WARDROBE_BOTTOM.get(), NecrolordFurnitureSet.Blocks.WARDROBE_TOP.get(),
                NecrolordFurnitureSet.Blocks.PAINTING_SMALL.get(), NecrolordFurnitureSet.Blocks.PAINTING_WIDE.get(), NecrolordFurnitureSet.Blocks.OVEN.get(),
                NecrolordFurnitureSet.Blocks.DOOR_DOUBLE.get(), NecrolordFurnitureSet.Blocks.DOOR_SINGLE.get(), NecrolordFurnitureSet.Blocks.BED_SINGLE.get(),
                NecrolordFurnitureSet.Blocks.BED_DOUBLE.get(), NecrolordFurnitureSet.Blocks.SHELF.get(), NecrolordFurnitureSet.Blocks.SOFA.get(),
                NecrolordFurnitureSet.Blocks.COUNTER.get()
        );
    }
}
