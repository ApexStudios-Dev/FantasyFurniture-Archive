package xyz.apex.minecraft.fantasyfurniture.bone.forge.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.RecipeProvider;

import java.util.function.Consumer;
import java.util.function.Supplier;

final class RecipeGenerator extends RecipeProvider
{
    RecipeGenerator(GatherDataEvent event)
    {
        super(event);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer)
    {
        wither(consumer);
        skeleton(consumer);
    }

    private void wither(Consumer<FinishedRecipe> consumer)
    {
        witherStation(consumer, BoneFurnitureSet.Wither.WOOL);
        witherStation(consumer, BoneFurnitureSet.Wither.CARPET);
        witherStation(consumer, BoneFurnitureSet.Wither.WALL_LIGHT);
        witherStation(consumer, BoneFurnitureSet.Wither.FLOOR_LIGHT);
        witherStation(consumer, BoneFurnitureSet.Wither.TABLE_LARGE);
        witherStation(consumer, BoneFurnitureSet.Wither.TABLE_SMALL);
        witherStation(consumer, BoneFurnitureSet.Wither.TABLE_WIDE);
        witherStation(consumer, BoneFurnitureSet.Wither.BENCH);
        witherStation(consumer, BoneFurnitureSet.Wither.CHAIR);
        witherStation(consumer, BoneFurnitureSet.Wither.CEILING_LIGHT);
        witherStation(consumer, BoneFurnitureSet.Wither.CUSHION);
        witherStation(consumer, BoneFurnitureSet.Wither.STOOL);
        witherStation(consumer, BoneFurnitureSet.Wither.CHEST);
        witherStation(consumer, BoneFurnitureSet.Wither.BOOKSHELF);
        witherStation(consumer, BoneFurnitureSet.Wither.DESK_LEFT);
        witherStation(consumer, BoneFurnitureSet.Wither.DESK_RIGHT);
        witherStation(consumer, BoneFurnitureSet.Wither.DRAWER);
        witherStation(consumer, BoneFurnitureSet.Wither.DRESSER);
        witherStation(consumer, BoneFurnitureSet.Wither.LOCKBOX);
        witherStation(consumer, BoneFurnitureSet.Wither.WARDROBE_BOTTOM);
        witherStation(consumer, BoneFurnitureSet.Wither.WARDROBE_TOP);
        witherStation(consumer, BoneFurnitureSet.Wither.PAINTING_SMALL);
        witherStation(consumer, BoneFurnitureSet.Wither.PAINTING_WIDE);
        witherStation(consumer, BoneFurnitureSet.Wither.OVEN);
        witherStation(consumer, BoneFurnitureSet.Wither.DOOR_DOUBLE);
        witherStation(consumer, BoneFurnitureSet.Wither.DOOR_SINGLE);
        witherStation(consumer, BoneFurnitureSet.Wither.BED_SINGLE);
        witherStation(consumer, BoneFurnitureSet.Wither.BED_DOUBLE);
        witherStation(consumer, BoneFurnitureSet.Wither.SHELF);
        witherStation(consumer, BoneFurnitureSet.Wither.SOFA);
        witherStation(consumer, BoneFurnitureSet.Wither.COUNTER);
    }

    private void skeleton(Consumer<FinishedRecipe> consumer)
    {
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.WOOL);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.CARPET);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.WALL_LIGHT);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.FLOOR_LIGHT);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.TABLE_LARGE);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.TABLE_SMALL);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.TABLE_WIDE);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.BENCH);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.CHAIR);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.CEILING_LIGHT);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.CUSHION);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.STOOL);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.CHEST);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.BOOKSHELF);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.DESK_LEFT);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.DESK_RIGHT);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.DRAWER);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.DRESSER);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.LOCKBOX);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.WARDROBE_BOTTOM);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.WARDROBE_TOP);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.PAINTING_SMALL);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.PAINTING_WIDE);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.OVEN);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.DOOR_DOUBLE);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.DOOR_SINGLE);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.BED_SINGLE);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.BED_DOUBLE);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.SHELF);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.SOFA);
        skeletonStation(consumer, BoneFurnitureSet.Skeleton.COUNTER);
    }

    private void witherStation(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> block)
    {
        furnitureStation(consumer, BoneFurnitureSet.Wither.SUBSET_ID, () -> Items.BONE, () -> Blocks.BLACK_WOOL, block);
    }

    private void skeletonStation(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> block)
    {
        furnitureStation(consumer, BoneFurnitureSet.Skeleton.SUBSET_ID, () -> Items.BONE, () -> Blocks.WHITE_WOOL, block);
    }
}
