package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.apexcore.common.ApexCore;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

final class BlockTagGenerator extends BlockTagsProvider
{
    BlockTagGenerator(GatherDataEvent event, PackOutput packOutput)
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
        var immovable = tag(ApexCore.BlockTags.IMMOVABLE);
        var isBoneSet = tag(BoneSet.BLOCK_TAG);

        nordic(wool, carpets, woodenDoors, beds, immovable, isBoneSet);
        venthyr(wool, carpets, woodenDoors, beds, immovable, isBoneSet);
        dunmer(wool, carpets, woodenDoors, beds, immovable, isBoneSet);
        boneWither(wool, carpets, woodenDoors, beds, immovable, isBoneSet);
        boneSkeleton(wool, carpets, woodenDoors, beds, immovable, isBoneSet);
        necrolord(wool, carpets, woodenDoors, beds, immovable, isBoneSet);
        royal(wool, carpets, woodenDoors, beds, immovable, isBoneSet);
    }

    private void nordic(IntrinsicTagAppender<Block> wool, IntrinsicTagAppender<Block> carpets, IntrinsicTagAppender<Block> woodenDoors, IntrinsicTagAppender<Block> beds, IntrinsicTagAppender<Block> immovable, IntrinsicTagAppender<Block> isBoneSet)
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
                NordicSet.CEILING_LIGHT.get(), NordicSet.CUSHION.get(), NordicSet.STOOL.get(),
                NordicSet.CHEST.get(), NordicSet.BOOKSHELF.get(), NordicSet.DESK_LEFT.get(),
                NordicSet.DESK_RIGHT.get(), NordicSet.DRAWER.get(), NordicSet.DRESSER.get(),
                NordicSet.LOCKBOX.get(), NordicSet.WARDROBE_BOTTOM.get(), NordicSet.WARDROBE_TOP.get(),
                NordicSet.PAINTING_WIDE.get(), NordicSet.PAINTING_SMALL.get(), NordicSet.OVEN.get(),
                NordicSet.DOOR_DOUBLE.get(), NordicSet.DOOR_SINGLE.get(), NordicSet.BED_SINGLE.get(),
                NordicSet.BED_DOUBLE.get(), NordicSet.SHELF.get(), NordicSet.SOFA.get(),
                NordicSet.COUNTER.get()
        );
    }

    private void venthyr(IntrinsicTagAppender<Block> wool, IntrinsicTagAppender<Block> carpets, IntrinsicTagAppender<Block> woodenDoors, IntrinsicTagAppender<Block> beds, IntrinsicTagAppender<Block> immovable, IntrinsicTagAppender<Block> isBoneSet)
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
                VenthyrSet.CEILING_LIGHT.get(), VenthyrSet.CUSHION.get(), VenthyrSet.STOOL.get(),
                VenthyrSet.CHEST.get(), VenthyrSet.BOOKSHELF.get(), VenthyrSet.DESK_LEFT.get(),
                VenthyrSet.DESK_RIGHT.get(), VenthyrSet.DRAWER.get(), VenthyrSet.DRESSER.get(),
                VenthyrSet.LOCKBOX.get(), VenthyrSet.WARDROBE_BOTTOM.get(), VenthyrSet.WARDROBE_TOP.get(),
                VenthyrSet.PAINTING_WIDE.get(), VenthyrSet.PAINTING_SMALL.get(), VenthyrSet.OVEN.get(),
                VenthyrSet.DOOR_DOUBLE.get(), VenthyrSet.DOOR_SINGLE.get(), VenthyrSet.BED_SINGLE.get(),
                VenthyrSet.BED_DOUBLE.get(), VenthyrSet.SHELF.get(), VenthyrSet.SOFA.get(),
                VenthyrSet.COUNTER.get()
        );
    }

    private void dunmer(IntrinsicTagAppender<Block> wool, IntrinsicTagAppender<Block> carpets, IntrinsicTagAppender<Block> woodenDoors, IntrinsicTagAppender<Block> beds, IntrinsicTagAppender<Block> immovable, IntrinsicTagAppender<Block> isBoneSet)
    {
        wool.add(DunmerSet.WOOL.get());
        carpets.add(DunmerSet.CARPET.get());
        woodenDoors.add(DunmerSet.DOOR_DOUBLE.get(), DunmerSet.DOOR_SINGLE.get());
        beds.add(DunmerSet.BED_SINGLE.get(), DunmerSet.BED_DOUBLE.get());

        immovable.add(
                DunmerSet.FLOOR_LIGHT.get(), DunmerSet.TABLE_LARGE.get(), DunmerSet.TABLE_WIDE.get(),
                DunmerSet.BENCH.get(), DunmerSet.CHAIR.get(), DunmerSet.CHEST.get(),
                DunmerSet.BOOKSHELF.get(), DunmerSet.DESK_LEFT.get(), DunmerSet.DESK_RIGHT.get(),
                DunmerSet.DRAWER.get(), DunmerSet.DRESSER.get(), DunmerSet.WARDROBE_BOTTOM.get(),
                DunmerSet.WARDROBE_TOP.get(), DunmerSet.PAINTING_WIDE.get(), DunmerSet.DOOR_DOUBLE.get(),
                DunmerSet.DOOR_SINGLE.get(), DunmerSet.BED_SINGLE.get(), DunmerSet.BED_DOUBLE.get(),
                DunmerSet.SHELF.get(), DunmerSet.SOFA.get(), DunmerSet.COUNTER.get()
        );

        tag(DunmerSet.BLOCK_TAG).add(
                DunmerSet.WOOL.get(), DunmerSet.CARPET.get(), DunmerSet.WALL_LIGHT.get(),
                DunmerSet.FLOOR_LIGHT.get(), DunmerSet.TABLE_LARGE.get(), DunmerSet.TABLE_WIDE.get(),
                DunmerSet.TABLE_SMALL.get(), DunmerSet.BENCH.get(), DunmerSet.CHAIR.get(),
                DunmerSet.CEILING_LIGHT.get(), DunmerSet.CUSHION.get(), DunmerSet.STOOL.get(),
                DunmerSet.CHEST.get(), DunmerSet.BOOKSHELF.get(), DunmerSet.DESK_LEFT.get(),
                DunmerSet.DESK_RIGHT.get(), DunmerSet.DRAWER.get(), DunmerSet.DRESSER.get(),
                DunmerSet.LOCKBOX.get(), DunmerSet.WARDROBE_BOTTOM.get(), DunmerSet.WARDROBE_TOP.get(),
                DunmerSet.PAINTING_WIDE.get(), DunmerSet.PAINTING_SMALL.get(), DunmerSet.OVEN.get(),
                DunmerSet.DOOR_DOUBLE.get(), DunmerSet.DOOR_SINGLE.get(), DunmerSet.BED_SINGLE.get(),
                DunmerSet.BED_DOUBLE.get(), DunmerSet.SHELF.get(), DunmerSet.SOFA.get(),
                DunmerSet.COUNTER.get()
        );
    }

    private void boneWither(IntrinsicTagAppender<Block> wool, IntrinsicTagAppender<Block> carpets, IntrinsicTagAppender<Block> woodenDoors, IntrinsicTagAppender<Block> beds, IntrinsicTagAppender<Block> immovable, IntrinsicTagAppender<Block> isBoneSet)
    {
        wool.add(BoneSet.Wither.WOOL.get());
        carpets.add(BoneSet.Wither.CARPET.get());
        woodenDoors.add(BoneSet.Wither.DOOR_DOUBLE.get(), BoneSet.Wither.DOOR_SINGLE.get());
        beds.add(BoneSet.Wither.BED_SINGLE.get(), BoneSet.Wither.BED_DOUBLE.get());
        isBoneSet.addTag(BoneSet.Wither.BLOCK_TAG);

        immovable.add(
                BoneSet.Wither.FLOOR_LIGHT.get(), BoneSet.Wither.TABLE_LARGE.get(), BoneSet.Wither.TABLE_WIDE.get(),
                BoneSet.Wither.BENCH.get(), BoneSet.Wither.CHAIR.get(), BoneSet.Wither.CHEST.get(),
                BoneSet.Wither.BOOKSHELF.get(), BoneSet.Wither.DESK_LEFT.get(), BoneSet.Wither.DESK_RIGHT.get(),
                BoneSet.Wither.DRAWER.get(), BoneSet.Wither.DRESSER.get(), BoneSet.Wither.WARDROBE_BOTTOM.get(),
                BoneSet.Wither.WARDROBE_TOP.get(), BoneSet.Wither.PAINTING_WIDE.get(), BoneSet.Wither.DOOR_DOUBLE.get(),
                BoneSet.Wither.DOOR_SINGLE.get(), BoneSet.Wither.BED_SINGLE.get(), BoneSet.Wither.BED_DOUBLE.get(),
                BoneSet.Wither.SHELF.get(), BoneSet.Wither.SOFA.get(), BoneSet.Wither.COUNTER.get()
        );

        tag(BoneSet.Wither.BLOCK_TAG).add(
                BoneSet.Wither.WOOL.get(), BoneSet.Wither.CARPET.get(), BoneSet.Wither.WALL_LIGHT.get(),
                BoneSet.Wither.FLOOR_LIGHT.get(), BoneSet.Wither.TABLE_LARGE.get(), BoneSet.Wither.TABLE_WIDE.get(),
                BoneSet.Wither.TABLE_SMALL.get(), BoneSet.Wither.BENCH.get(), BoneSet.Wither.CHAIR.get(),
                BoneSet.Wither.CEILING_LIGHT.get(), BoneSet.Wither.CUSHION.get(), BoneSet.Wither.STOOL.get(),
                BoneSet.Wither.CHEST.get(), BoneSet.Wither.BOOKSHELF.get(), BoneSet.Wither.DESK_LEFT.get(),
                BoneSet.Wither.DESK_RIGHT.get(), BoneSet.Wither.DRAWER.get(), BoneSet.Wither.DRESSER.get(),
                BoneSet.Wither.LOCKBOX.get(), BoneSet.Wither.WARDROBE_BOTTOM.get(), BoneSet.Wither.WARDROBE_TOP.get(),
                BoneSet.Wither.PAINTING_WIDE.get(), BoneSet.Wither.PAINTING_SMALL.get(), BoneSet.Wither.OVEN.get(),
                BoneSet.Wither.DOOR_DOUBLE.get(), BoneSet.Wither.DOOR_SINGLE.get(), BoneSet.Wither.BED_SINGLE.get(),
                BoneSet.Wither.BED_DOUBLE.get(), BoneSet.Wither.SHELF.get(), BoneSet.Wither.SOFA.get(),
                BoneSet.Wither.COUNTER.get()
        );
    }

    private void boneSkeleton(IntrinsicTagAppender<Block> wool, IntrinsicTagAppender<Block> carpets, IntrinsicTagAppender<Block> woodenDoors, IntrinsicTagAppender<Block> beds, IntrinsicTagAppender<Block> immovable, IntrinsicTagAppender<Block> isBoneSet)
    {
        wool.add(BoneSet.Skeleton.WOOL.get());
        carpets.add(BoneSet.Skeleton.CARPET.get());
        woodenDoors.add(BoneSet.Skeleton.DOOR_DOUBLE.get(), BoneSet.Skeleton.DOOR_SINGLE.get());
        beds.add(BoneSet.Skeleton.BED_SINGLE.get(), BoneSet.Skeleton.BED_DOUBLE.get());
        isBoneSet.addTag(BoneSet.Skeleton.BLOCK_TAG);

        immovable.add(
                BoneSet.Skeleton.FLOOR_LIGHT.get(), BoneSet.Skeleton.TABLE_LARGE.get(), BoneSet.Skeleton.TABLE_WIDE.get(),
                BoneSet.Skeleton.BENCH.get(), BoneSet.Skeleton.CHAIR.get(), BoneSet.Skeleton.CHEST.get(),
                BoneSet.Skeleton.BOOKSHELF.get(), BoneSet.Skeleton.DESK_LEFT.get(), BoneSet.Skeleton.DESK_RIGHT.get(),
                BoneSet.Skeleton.DRAWER.get(), BoneSet.Skeleton.DRESSER.get(), BoneSet.Skeleton.WARDROBE_BOTTOM.get(),
                BoneSet.Skeleton.WARDROBE_TOP.get(), BoneSet.Skeleton.PAINTING_WIDE.get(), BoneSet.Skeleton.DOOR_DOUBLE.get(),
                BoneSet.Skeleton.DOOR_SINGLE.get(), BoneSet.Skeleton.BED_SINGLE.get(), BoneSet.Skeleton.BED_DOUBLE.get(),
                BoneSet.Skeleton.SHELF.get(), BoneSet.Skeleton.SOFA.get(), BoneSet.Skeleton.COUNTER.get()
        );

        tag(BoneSet.Skeleton.BLOCK_TAG).add(
                BoneSet.Skeleton.WOOL.get(), BoneSet.Skeleton.CARPET.get(), BoneSet.Skeleton.WALL_LIGHT.get(),
                BoneSet.Skeleton.FLOOR_LIGHT.get(), BoneSet.Skeleton.TABLE_LARGE.get(), BoneSet.Skeleton.TABLE_WIDE.get(),
                BoneSet.Skeleton.TABLE_SMALL.get(), BoneSet.Skeleton.BENCH.get(), BoneSet.Skeleton.CHAIR.get(),
                BoneSet.Skeleton.CEILING_LIGHT.get(), BoneSet.Skeleton.CUSHION.get(), BoneSet.Skeleton.STOOL.get(),
                BoneSet.Skeleton.CHEST.get(), BoneSet.Skeleton.BOOKSHELF.get(), BoneSet.Skeleton.DESK_LEFT.get(),
                BoneSet.Skeleton.DESK_RIGHT.get(), BoneSet.Skeleton.DRAWER.get(), BoneSet.Skeleton.DRESSER.get(),
                BoneSet.Skeleton.LOCKBOX.get(), BoneSet.Skeleton.WARDROBE_BOTTOM.get(), BoneSet.Skeleton.WARDROBE_TOP.get(),
                BoneSet.Skeleton.PAINTING_WIDE.get(), BoneSet.Skeleton.PAINTING_SMALL.get(), BoneSet.Skeleton.OVEN.get(),
                BoneSet.Skeleton.DOOR_DOUBLE.get(), BoneSet.Skeleton.DOOR_SINGLE.get(), BoneSet.Skeleton.BED_SINGLE.get(),
                BoneSet.Skeleton.BED_DOUBLE.get(), BoneSet.Skeleton.SHELF.get(), BoneSet.Skeleton.SOFA.get(),
                BoneSet.Skeleton.COUNTER.get()
        );
    }

    private void necrolord(IntrinsicTagAppender<Block> wool, IntrinsicTagAppender<Block> carpets, IntrinsicTagAppender<Block> woodenDoors, IntrinsicTagAppender<Block> beds, IntrinsicTagAppender<Block> immovable, IntrinsicTagAppender<Block> isBoneSet)
    {
        wool.add(NecrolordSet.WOOL.get());
        carpets.add(NecrolordSet.CARPET.get());
        woodenDoors.add(NecrolordSet.DOOR_DOUBLE.get(), NecrolordSet.DOOR_SINGLE.get());
        beds.add(NecrolordSet.BED_SINGLE.get(), NecrolordSet.BED_DOUBLE.get());

        immovable.add(
                NecrolordSet.FLOOR_LIGHT.get(), NecrolordSet.TABLE_LARGE.get(), NecrolordSet.TABLE_WIDE.get(),
                NecrolordSet.BENCH.get(), NecrolordSet.CHAIR.get(), NecrolordSet.CHEST.get(),
                NecrolordSet.BOOKSHELF.get(), NecrolordSet.DESK_LEFT.get(), NecrolordSet.DESK_RIGHT.get(),
                NecrolordSet.DRAWER.get(), NecrolordSet.DRESSER.get(), NecrolordSet.WARDROBE_BOTTOM.get(),
                NecrolordSet.WARDROBE_TOP.get(), NecrolordSet.PAINTING_WIDE.get(), NecrolordSet.DOOR_DOUBLE.get(),
                NecrolordSet.DOOR_SINGLE.get(), NecrolordSet.BED_SINGLE.get(), NecrolordSet.BED_DOUBLE.get(),
                NecrolordSet.SHELF.get(), NecrolordSet.SOFA.get(), NecrolordSet.COUNTER.get()
        );

        tag(NecrolordSet.BLOCK_TAG).add(
                NecrolordSet.WOOL.get(), NecrolordSet.CARPET.get(), NecrolordSet.WALL_LIGHT.get(),
                NecrolordSet.FLOOR_LIGHT.get(), NecrolordSet.TABLE_LARGE.get(), NecrolordSet.TABLE_WIDE.get(),
                NecrolordSet.TABLE_SMALL.get(), NecrolordSet.BENCH.get(), NecrolordSet.CHAIR.get(),
                NecrolordSet.CEILING_LIGHT.get(), NecrolordSet.CUSHION.get(), NecrolordSet.STOOL.get(),
                NecrolordSet.CHEST.get(), NecrolordSet.BOOKSHELF.get(), NecrolordSet.DESK_LEFT.get(),
                NecrolordSet.DESK_RIGHT.get(), NecrolordSet.DRAWER.get(), NecrolordSet.DRESSER.get(),
                NecrolordSet.LOCKBOX.get(), NecrolordSet.WARDROBE_BOTTOM.get(), NecrolordSet.WARDROBE_TOP.get(),
                NecrolordSet.PAINTING_WIDE.get(), NecrolordSet.PAINTING_SMALL.get(), NecrolordSet.OVEN.get(),
                NecrolordSet.DOOR_DOUBLE.get(), NecrolordSet.DOOR_SINGLE.get(), NecrolordSet.BED_SINGLE.get(),
                NecrolordSet.BED_DOUBLE.get(), NecrolordSet.SHELF.get(), NecrolordSet.SOFA.get(),
                NecrolordSet.COUNTER.get()
        );
    }

    private void royal(IntrinsicTagAppender<Block> wool, IntrinsicTagAppender<Block> carpets, IntrinsicTagAppender<Block> woodenDoors, IntrinsicTagAppender<Block> beds, IntrinsicTagAppender<Block> immovable, IntrinsicTagAppender<Block> isBoneSet)
    {
        wool.add(RoyalSet.WOOL.get());
        carpets.add(RoyalSet.CARPET.get());
        woodenDoors.add(RoyalSet.DOOR_DOUBLE.get(), RoyalSet.DOOR_SINGLE.get());
        beds.add(RoyalSet.BED_SINGLE.get(), RoyalSet.BED_DOUBLE.get());

        immovable.add(
                RoyalSet.FLOOR_LIGHT.get(), RoyalSet.TABLE_LARGE.get(), RoyalSet.TABLE_WIDE.get(),
                RoyalSet.BENCH.get(), RoyalSet.CHAIR.get(), RoyalSet.CHEST.get(),
                RoyalSet.BOOKSHELF.get(), RoyalSet.DESK_LEFT.get(), RoyalSet.DESK_RIGHT.get(),
                RoyalSet.DRAWER.get(), RoyalSet.DRESSER.get(), RoyalSet.WARDROBE_BOTTOM.get(),
                RoyalSet.WARDROBE_TOP.get(), RoyalSet.PAINTING_WIDE.get(), RoyalSet.DOOR_DOUBLE.get(),
                RoyalSet.DOOR_SINGLE.get(), RoyalSet.BED_SINGLE.get(), RoyalSet.BED_DOUBLE.get(),
                RoyalSet.SHELF.get(), RoyalSet.SOFA.get(), RoyalSet.COUNTER.get()
        );

        tag(RoyalSet.BLOCK_TAG).add(
                RoyalSet.WOOL.get(), RoyalSet.CARPET.get(), RoyalSet.WALL_LIGHT.get(),
                RoyalSet.FLOOR_LIGHT.get(), RoyalSet.TABLE_LARGE.get(), RoyalSet.TABLE_WIDE.get(),
                RoyalSet.TABLE_SMALL.get(), RoyalSet.BENCH.get(), RoyalSet.CHAIR.get(),
                RoyalSet.CEILING_LIGHT.get(), RoyalSet.CUSHION.get(), RoyalSet.STOOL.get(),
                RoyalSet.CHEST.get(), RoyalSet.BOOKSHELF.get(), RoyalSet.DESK_LEFT.get(),
                RoyalSet.DESK_RIGHT.get(), RoyalSet.DRAWER.get(), RoyalSet.DRESSER.get(),
                RoyalSet.LOCKBOX.get(), RoyalSet.WARDROBE_BOTTOM.get(), RoyalSet.WARDROBE_TOP.get(),
                RoyalSet.PAINTING_WIDE.get(), RoyalSet.PAINTING_SMALL.get(), RoyalSet.OVEN.get(),
                RoyalSet.DOOR_DOUBLE.get(), RoyalSet.DOOR_SINGLE.get(), RoyalSet.BED_SINGLE.get(),
                RoyalSet.BED_DOUBLE.get(), RoyalSet.SHELF.get(), RoyalSet.SOFA.get(),
                RoyalSet.COUNTER.get()
        );
    }
}
