package xyz.apex.minecraft.fantasyfurniture.bone.forge.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.apexcore.common.ApexCore;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

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
        var bone = tag(BoneFurnitureSet.BLOCK_TAG);

        wither(wool, carpets, woodenDoors, beds, immovable, bone);
        skeleton(wool, carpets, woodenDoors, beds, immovable, bone);
    }

    private void wither(IntrinsicTagAppender<Block> wool, IntrinsicTagAppender<Block> carpets, IntrinsicTagAppender<Block> woodenDoors, IntrinsicTagAppender<Block> beds, IntrinsicTagAppender<Block> immovable, IntrinsicTagAppender<Block> bone)
    {
        wool.add(BoneFurnitureSet.Wither.WOOL.get());
        carpets.add(BoneFurnitureSet.Wither.CARPET.get());
        woodenDoors.add(BoneFurnitureSet.Wither.DOOR_DOUBLE.get(), BoneFurnitureSet.Wither.DOOR_SINGLE.get());
        beds.add(BoneFurnitureSet.Wither.BED_DOUBLE.get(), BoneFurnitureSet.Wither.BED_SINGLE.get());
        bone.addTag(BoneFurnitureSet.Wither.BLOCK_TAG);

        immovable.add(
                BoneFurnitureSet.Wither.FLOOR_LIGHT.get(), BoneFurnitureSet.Wither.TABLE_LARGE.get(), BoneFurnitureSet.Wither.TABLE_WIDE.get(),
                BoneFurnitureSet.Wither.BENCH.get(), BoneFurnitureSet.Wither.CHAIR.get(), BoneFurnitureSet.Wither.CHEST.get(),
                BoneFurnitureSet.Wither.BOOKSHELF.get(), BoneFurnitureSet.Wither.DESK_LEFT.get(), BoneFurnitureSet.Wither.DESK_RIGHT.get(),
                BoneFurnitureSet.Wither.DRAWER.get(), BoneFurnitureSet.Wither.DRESSER.get(), BoneFurnitureSet.Wither.WARDROBE_BOTTOM.get(),
                BoneFurnitureSet.Wither.WARDROBE_TOP.get(), BoneFurnitureSet.Wither.PAINTING_WIDE.get(), BoneFurnitureSet.Wither.DOOR_DOUBLE.get(),
                BoneFurnitureSet.Wither.DOOR_SINGLE.get(), BoneFurnitureSet.Wither.BED_SINGLE.get(), BoneFurnitureSet.Wither.BED_DOUBLE.get()
        );

        tag(BoneFurnitureSet.Wither.BLOCK_TAG).add(
                BoneFurnitureSet.Wither.WOOL.get(), BoneFurnitureSet.Wither.CARPET.get(), BoneFurnitureSet.Wither.WALL_LIGHT.get(),
                BoneFurnitureSet.Wither.FLOOR_LIGHT.get(), BoneFurnitureSet.Wither.TABLE_LARGE.get(), BoneFurnitureSet.Wither.TABLE_SMALL.get(),
                BoneFurnitureSet.Wither.TABLE_WIDE.get(), BoneFurnitureSet.Wither.BENCH.get(), BoneFurnitureSet.Wither.CHAIR.get(),
                BoneFurnitureSet.Wither.CEILING_LIGHT.get(), BoneFurnitureSet.Wither.CUSHION.get(), BoneFurnitureSet.Wither.STOOL.get(),
                BoneFurnitureSet.Wither.CHEST.get(), BoneFurnitureSet.Wither.BOOKSHELF.get(), BoneFurnitureSet.Wither.DESK_LEFT.get(),
                BoneFurnitureSet.Wither.DESK_RIGHT.get(), BoneFurnitureSet.Wither.DRAWER.get(), BoneFurnitureSet.Wither.DRESSER.get(),
                BoneFurnitureSet.Wither.LOCKBOX.get(), BoneFurnitureSet.Wither.WARDROBE_BOTTOM.get(), BoneFurnitureSet.Wither.WARDROBE_TOP.get(),
                BoneFurnitureSet.Wither.PAINTING_SMALL.get(), BoneFurnitureSet.Wither.PAINTING_WIDE.get(), BoneFurnitureSet.Wither.OVEN.get(),
                BoneFurnitureSet.Wither.DOOR_DOUBLE.get(), BoneFurnitureSet.Wither.DOOR_SINGLE.get(), BoneFurnitureSet.Wither.BED_SINGLE.get(),
                BoneFurnitureSet.Wither.BED_DOUBLE.get(), BoneFurnitureSet.Wither.SHELF.get(), BoneFurnitureSet.Wither.SOFA.get(),
                BoneFurnitureSet.Wither.COUNTER.get()
        );
    }

    private void skeleton(IntrinsicTagAppender<Block> wool, IntrinsicTagAppender<Block> carpets, IntrinsicTagAppender<Block> woodenDoors, IntrinsicTagAppender<Block> beds, IntrinsicTagAppender<Block> immovable, IntrinsicTagAppender<Block> bone)
    {
        wool.add(BoneFurnitureSet.Skeleton.WOOL.get());
        carpets.add(BoneFurnitureSet.Skeleton.CARPET.get());
        woodenDoors.add(BoneFurnitureSet.Skeleton.DOOR_DOUBLE.get(), BoneFurnitureSet.Skeleton.DOOR_SINGLE.get());
        beds.add(BoneFurnitureSet.Skeleton.BED_DOUBLE.get(), BoneFurnitureSet.Skeleton.BED_SINGLE.get());
        bone.addTag(BoneFurnitureSet.Skeleton.BLOCK_TAG);

        immovable.add(
                BoneFurnitureSet.Skeleton.FLOOR_LIGHT.get(), BoneFurnitureSet.Skeleton.TABLE_LARGE.get(), BoneFurnitureSet.Skeleton.TABLE_WIDE.get(),
                BoneFurnitureSet.Skeleton.BENCH.get(), BoneFurnitureSet.Skeleton.CHAIR.get(), BoneFurnitureSet.Skeleton.CHEST.get(),
                BoneFurnitureSet.Skeleton.BOOKSHELF.get(), BoneFurnitureSet.Skeleton.DESK_LEFT.get(), BoneFurnitureSet.Skeleton.DESK_RIGHT.get(),
                BoneFurnitureSet.Skeleton.DRAWER.get(), BoneFurnitureSet.Skeleton.DRESSER.get(), BoneFurnitureSet.Skeleton.WARDROBE_BOTTOM.get(),
                BoneFurnitureSet.Skeleton.WARDROBE_TOP.get(), BoneFurnitureSet.Skeleton.PAINTING_WIDE.get(), BoneFurnitureSet.Skeleton.DOOR_DOUBLE.get(),
                BoneFurnitureSet.Skeleton.DOOR_SINGLE.get(), BoneFurnitureSet.Skeleton.BED_SINGLE.get(), BoneFurnitureSet.Skeleton.BED_DOUBLE.get()
        );

        tag(BoneFurnitureSet.Skeleton.BLOCK_TAG).add(
                BoneFurnitureSet.Skeleton.WOOL.get(), BoneFurnitureSet.Skeleton.CARPET.get(), BoneFurnitureSet.Skeleton.WALL_LIGHT.get(),
                BoneFurnitureSet.Skeleton.FLOOR_LIGHT.get(), BoneFurnitureSet.Skeleton.TABLE_LARGE.get(), BoneFurnitureSet.Skeleton.TABLE_SMALL.get(),
                BoneFurnitureSet.Skeleton.TABLE_WIDE.get(), BoneFurnitureSet.Skeleton.BENCH.get(), BoneFurnitureSet.Skeleton.CHAIR.get(),
                BoneFurnitureSet.Skeleton.CEILING_LIGHT.get(), BoneFurnitureSet.Skeleton.CUSHION.get(), BoneFurnitureSet.Skeleton.STOOL.get(),
                BoneFurnitureSet.Skeleton.CHEST.get(), BoneFurnitureSet.Skeleton.BOOKSHELF.get(), BoneFurnitureSet.Skeleton.DESK_LEFT.get(),
                BoneFurnitureSet.Skeleton.DESK_RIGHT.get(), BoneFurnitureSet.Skeleton.DRAWER.get(), BoneFurnitureSet.Skeleton.DRESSER.get(),
                BoneFurnitureSet.Skeleton.LOCKBOX.get(), BoneFurnitureSet.Skeleton.WARDROBE_BOTTOM.get(), BoneFurnitureSet.Skeleton.WARDROBE_TOP.get(),
                BoneFurnitureSet.Skeleton.PAINTING_SMALL.get(), BoneFurnitureSet.Skeleton.PAINTING_WIDE.get(), BoneFurnitureSet.Skeleton.OVEN.get(),
                BoneFurnitureSet.Skeleton.DOOR_DOUBLE.get(), BoneFurnitureSet.Skeleton.DOOR_SINGLE.get(), BoneFurnitureSet.Skeleton.BED_SINGLE.get(),
                BoneFurnitureSet.Skeleton.BED_DOUBLE.get(), BoneFurnitureSet.Skeleton.SHELF.get(), BoneFurnitureSet.Skeleton.SOFA.get(),
                BoneFurnitureSet.Skeleton.COUNTER.get()
        );
    }
}
