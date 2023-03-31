package xyz.apex.minecraft.fantasyfurniture.royal.forge.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.apexcore.common.ApexCore;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalFurnitureSet;

final class BlockTagGenerator extends BlockTagsProvider
{
    BlockTagGenerator(GatherDataEvent event, PackOutput packOutput)
    {
        super(packOutput, event.getLookupProvider(), FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        tag(BlockTags.WOOL).add(RoyalFurnitureSet.Blocks.WOOL.get());
        tag(BlockTags.WOOL_CARPETS).add(RoyalFurnitureSet.Blocks.CARPET.get());
        tag(BlockTags.WOODEN_DOORS).add(RoyalFurnitureSet.Blocks.DOOR_DOUBLE.get(), RoyalFurnitureSet.Blocks.DOOR_SINGLE.get());
        tag(BlockTags.BEDS).add(RoyalFurnitureSet.Blocks.BED_DOUBLE.get(), RoyalFurnitureSet.Blocks.BED_SINGLE.get());

        tag(ApexCore.BlockTags.IMMOVABLE).add(
                RoyalFurnitureSet.Blocks.FLOOR_LIGHT.get(), RoyalFurnitureSet.Blocks.TABLE_LARGE.get(), RoyalFurnitureSet.Blocks.TABLE_WIDE.get(),
                RoyalFurnitureSet.Blocks.BENCH.get(), RoyalFurnitureSet.Blocks.CHAIR.get(), RoyalFurnitureSet.Blocks.CHEST.get(),
                RoyalFurnitureSet.Blocks.BOOKSHELF.get(), RoyalFurnitureSet.Blocks.DESK_LEFT.get(), RoyalFurnitureSet.Blocks.DESK_RIGHT.get(),
                RoyalFurnitureSet.Blocks.DRAWER.get(), RoyalFurnitureSet.Blocks.DRESSER.get(), RoyalFurnitureSet.Blocks.WARDROBE_BOTTOM.get(),
                RoyalFurnitureSet.Blocks.WARDROBE_TOP.get(), RoyalFurnitureSet.Blocks.PAINTING_WIDE.get(), RoyalFurnitureSet.Blocks.DOOR_DOUBLE.get(),
                RoyalFurnitureSet.Blocks.DOOR_SINGLE.get(), RoyalFurnitureSet.Blocks.BED_SINGLE.get(), RoyalFurnitureSet.Blocks.BED_DOUBLE.get()
        );

        tag(RoyalFurnitureSet.BLOCK_TAG).add(
                RoyalFurnitureSet.Blocks.WOOL.get(), RoyalFurnitureSet.Blocks.CARPET.get(), RoyalFurnitureSet.Blocks.WALL_LIGHT.get(),
                RoyalFurnitureSet.Blocks.FLOOR_LIGHT.get(), RoyalFurnitureSet.Blocks.TABLE_LARGE.get(), RoyalFurnitureSet.Blocks.TABLE_SMALL.get(),
                RoyalFurnitureSet.Blocks.TABLE_WIDE.get(), RoyalFurnitureSet.Blocks.BENCH.get(), RoyalFurnitureSet.Blocks.CHAIR.get(),
                RoyalFurnitureSet.Blocks.CEILING_LIGHT.get(), RoyalFurnitureSet.Blocks.CUSHION.get(), RoyalFurnitureSet.Blocks.STOOL.get(),
                RoyalFurnitureSet.Blocks.CHEST.get(), RoyalFurnitureSet.Blocks.BOOKSHELF.get(), RoyalFurnitureSet.Blocks.DESK_LEFT.get(),
                RoyalFurnitureSet.Blocks.DESK_RIGHT.get(), RoyalFurnitureSet.Blocks.DRAWER.get(), RoyalFurnitureSet.Blocks.DRESSER.get(),
                RoyalFurnitureSet.Blocks.LOCKBOX.get(), RoyalFurnitureSet.Blocks.WARDROBE_BOTTOM.get(), RoyalFurnitureSet.Blocks.WARDROBE_TOP.get(),
                RoyalFurnitureSet.Blocks.PAINTING_SMALL.get(), RoyalFurnitureSet.Blocks.PAINTING_WIDE.get(), RoyalFurnitureSet.Blocks.OVEN.get(),
                RoyalFurnitureSet.Blocks.DOOR_DOUBLE.get(), RoyalFurnitureSet.Blocks.DOOR_SINGLE.get(), RoyalFurnitureSet.Blocks.BED_SINGLE.get(),
                RoyalFurnitureSet.Blocks.BED_DOUBLE.get(), RoyalFurnitureSet.Blocks.SHELF.get(), RoyalFurnitureSet.Blocks.SOFA.get(),
                RoyalFurnitureSet.Blocks.COUNTER.get()
        );
    }
}
