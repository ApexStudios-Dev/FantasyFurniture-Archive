package xyz.apex.minecraft.fantasyfurniture.dunmer.forge.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.apexcore.common.ApexCore;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.DunmerFurnitureSet;

final class BlockTagGenerator extends BlockTagsProvider
{
    BlockTagGenerator(GatherDataEvent event, PackOutput packOutput)
    {
        super(packOutput, event.getLookupProvider(), FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        tag(BlockTags.WOOL).add(DunmerFurnitureSet.Blocks.WOOL.get());
        tag(BlockTags.WOOL_CARPETS).add(DunmerFurnitureSet.Blocks.CARPET.get());
        tag(BlockTags.WOODEN_DOORS).add(DunmerFurnitureSet.Blocks.DOOR_DOUBLE.get(), DunmerFurnitureSet.Blocks.DOOR_SINGLE.get());
        tag(BlockTags.BEDS).add(DunmerFurnitureSet.Blocks.BED_DOUBLE.get(), DunmerFurnitureSet.Blocks.BED_SINGLE.get());

        tag(ApexCore.BlockTags.IMMOVABLE).add(
                DunmerFurnitureSet.Blocks.FLOOR_LIGHT.get(), DunmerFurnitureSet.Blocks.TABLE_LARGE.get(), DunmerFurnitureSet.Blocks.TABLE_WIDE.get(),
                DunmerFurnitureSet.Blocks.BENCH.get(), DunmerFurnitureSet.Blocks.CHAIR.get(), DunmerFurnitureSet.Blocks.CHEST.get(),
                DunmerFurnitureSet.Blocks.BOOKSHELF.get(), DunmerFurnitureSet.Blocks.DESK_LEFT.get(), DunmerFurnitureSet.Blocks.DESK_RIGHT.get(),
                DunmerFurnitureSet.Blocks.DRAWER.get(), DunmerFurnitureSet.Blocks.DRESSER.get(), DunmerFurnitureSet.Blocks.WARDROBE_BOTTOM.get(),
                DunmerFurnitureSet.Blocks.WARDROBE_TOP.get(), DunmerFurnitureSet.Blocks.PAINTING_WIDE.get(), DunmerFurnitureSet.Blocks.DOOR_DOUBLE.get(),
                DunmerFurnitureSet.Blocks.DOOR_SINGLE.get(), DunmerFurnitureSet.Blocks.BED_SINGLE.get(), DunmerFurnitureSet.Blocks.BED_DOUBLE.get()
        );

        tag(DunmerFurnitureSet.BLOCK_TAG).add(
                DunmerFurnitureSet.Blocks.WOOL.get(), DunmerFurnitureSet.Blocks.CARPET.get(), DunmerFurnitureSet.Blocks.WALL_LIGHT.get(),
                DunmerFurnitureSet.Blocks.FLOOR_LIGHT.get(), DunmerFurnitureSet.Blocks.TABLE_LARGE.get(), DunmerFurnitureSet.Blocks.TABLE_SMALL.get(),
                DunmerFurnitureSet.Blocks.TABLE_WIDE.get(), DunmerFurnitureSet.Blocks.BENCH.get(), DunmerFurnitureSet.Blocks.CHAIR.get(),
                DunmerFurnitureSet.Blocks.CEILING_LIGHT.get(), DunmerFurnitureSet.Blocks.CUSHION.get(), DunmerFurnitureSet.Blocks.STOOL.get(),
                DunmerFurnitureSet.Blocks.CHEST.get(), DunmerFurnitureSet.Blocks.BOOKSHELF.get(), DunmerFurnitureSet.Blocks.DESK_LEFT.get(),
                DunmerFurnitureSet.Blocks.DESK_RIGHT.get(), DunmerFurnitureSet.Blocks.DRAWER.get(), DunmerFurnitureSet.Blocks.DRESSER.get(),
                DunmerFurnitureSet.Blocks.LOCKBOX.get(), DunmerFurnitureSet.Blocks.WARDROBE_BOTTOM.get(), DunmerFurnitureSet.Blocks.WARDROBE_TOP.get(),
                DunmerFurnitureSet.Blocks.PAINTING_SMALL.get(), DunmerFurnitureSet.Blocks.PAINTING_WIDE.get(), DunmerFurnitureSet.Blocks.OVEN.get(),
                DunmerFurnitureSet.Blocks.DOOR_DOUBLE.get(), DunmerFurnitureSet.Blocks.DOOR_SINGLE.get(), DunmerFurnitureSet.Blocks.BED_SINGLE.get(),
                DunmerFurnitureSet.Blocks.BED_DOUBLE.get(), DunmerFurnitureSet.Blocks.SHELF.get(), DunmerFurnitureSet.Blocks.SOFA.get(),
                DunmerFurnitureSet.Blocks.COUNTER.get()
        );
    }
}
