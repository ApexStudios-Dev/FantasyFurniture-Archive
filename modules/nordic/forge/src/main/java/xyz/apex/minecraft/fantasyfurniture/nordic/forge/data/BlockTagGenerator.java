package xyz.apex.minecraft.fantasyfurniture.nordic.forge.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.apexcore.common.ApexCore;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;

final class BlockTagGenerator extends BlockTagsProvider
{
    BlockTagGenerator(GatherDataEvent event, PackOutput packOutput)
    {
        super(packOutput, event.getLookupProvider(), FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        tag(BlockTags.WOOL).add(NordicFurnitureSet.Blocks.WOOL.get());
        tag(BlockTags.WOOL_CARPETS).add(NordicFurnitureSet.Blocks.CARPET.get());
        tag(BlockTags.WOODEN_DOORS).add(NordicFurnitureSet.Blocks.DOOR_DOUBLE.get(), NordicFurnitureSet.Blocks.DOOR_SINGLE.get());
        tag(BlockTags.BEDS).add(NordicFurnitureSet.Blocks.BED_DOUBLE.get(), NordicFurnitureSet.Blocks.BED_SINGLE.get());

        tag(ApexCore.BlockTags.IMMOVABLE).add(
                NordicFurnitureSet.Blocks.FLOOR_LIGHT.get(), NordicFurnitureSet.Blocks.TABLE_LARGE.get(), NordicFurnitureSet.Blocks.TABLE_WIDE.get(),
                NordicFurnitureSet.Blocks.BENCH.get(), NordicFurnitureSet.Blocks.CHAIR.get(), NordicFurnitureSet.Blocks.CHEST.get(),
                NordicFurnitureSet.Blocks.BOOKSHELF.get(), NordicFurnitureSet.Blocks.DESK_LEFT.get(), NordicFurnitureSet.Blocks.DESK_RIGHT.get(),
                NordicFurnitureSet.Blocks.DRAWER.get(), NordicFurnitureSet.Blocks.DRESSER.get(), NordicFurnitureSet.Blocks.WARDROBE_BOTTOM.get(),
                NordicFurnitureSet.Blocks.WARDROBE_TOP.get(), NordicFurnitureSet.Blocks.PAINTING_WIDE.get(), NordicFurnitureSet.Blocks.DOOR_DOUBLE.get(),
                NordicFurnitureSet.Blocks.DOOR_SINGLE.get(), NordicFurnitureSet.Blocks.BED_SINGLE.get(), NordicFurnitureSet.Blocks.BED_DOUBLE.get()
        );

        tag(NordicFurnitureSet.BLOCK_TAG).add(
                NordicFurnitureSet.Blocks.WOOL.get(), NordicFurnitureSet.Blocks.CARPET.get(), NordicFurnitureSet.Blocks.WALL_LIGHT.get(),
                NordicFurnitureSet.Blocks.FLOOR_LIGHT.get(), NordicFurnitureSet.Blocks.TABLE_LARGE.get(), NordicFurnitureSet.Blocks.TABLE_SMALL.get(),
                NordicFurnitureSet.Blocks.TABLE_WIDE.get(), NordicFurnitureSet.Blocks.BENCH.get(), NordicFurnitureSet.Blocks.CHAIR.get(),
                NordicFurnitureSet.Blocks.CEILING_LIGHT.get(), NordicFurnitureSet.Blocks.CUSHION.get(), NordicFurnitureSet.Blocks.STOOL.get(),
                NordicFurnitureSet.Blocks.CHEST.get(), NordicFurnitureSet.Blocks.BOOKSHELF.get(), NordicFurnitureSet.Blocks.DESK_LEFT.get(),
                NordicFurnitureSet.Blocks.DESK_RIGHT.get(), NordicFurnitureSet.Blocks.DRAWER.get(), NordicFurnitureSet.Blocks.DRESSER.get(),
                NordicFurnitureSet.Blocks.LOCKBOX.get(), NordicFurnitureSet.Blocks.WARDROBE_BOTTOM.get(), NordicFurnitureSet.Blocks.WARDROBE_TOP.get(),
                NordicFurnitureSet.Blocks.PAINTING_SMALL.get(), NordicFurnitureSet.Blocks.PAINTING_WIDE.get(), NordicFurnitureSet.Blocks.OVEN.get(),
                NordicFurnitureSet.Blocks.DOOR_DOUBLE.get(), NordicFurnitureSet.Blocks.DOOR_SINGLE.get(), NordicFurnitureSet.Blocks.BED_SINGLE.get(),
                NordicFurnitureSet.Blocks.BED_DOUBLE.get(), NordicFurnitureSet.Blocks.SHELF.get(), NordicFurnitureSet.Blocks.SOFA.get(),
                NordicFurnitureSet.Blocks.COUNTER.get()
        );
    }
}
