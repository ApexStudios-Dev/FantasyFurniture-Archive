package xyz.apex.minecraft.fantasyfurniture.royal.forge.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.RecipeProvider;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalFurnitureSet;

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
        royalStation(consumer, RoyalFurnitureSet.Blocks.WOOL);
        royalStation(consumer, RoyalFurnitureSet.Blocks.CARPET);
        royalStation(consumer, RoyalFurnitureSet.Blocks.WALL_LIGHT);
        royalStation(consumer, RoyalFurnitureSet.Blocks.FLOOR_LIGHT);
        royalStation(consumer, RoyalFurnitureSet.Blocks.TABLE_LARGE);
        royalStation(consumer, RoyalFurnitureSet.Blocks.TABLE_SMALL);
        royalStation(consumer, RoyalFurnitureSet.Blocks.TABLE_WIDE);
        royalStation(consumer, RoyalFurnitureSet.Blocks.BENCH);
        royalStation(consumer, RoyalFurnitureSet.Blocks.CHAIR);
        royalStation(consumer, RoyalFurnitureSet.Blocks.CEILING_LIGHT);
        royalStation(consumer, RoyalFurnitureSet.Blocks.CUSHION);
        royalStation(consumer, RoyalFurnitureSet.Blocks.STOOL);
        royalStation(consumer, RoyalFurnitureSet.Blocks.CHEST);
        royalStation(consumer, RoyalFurnitureSet.Blocks.BOOKSHELF);
        royalStation(consumer, RoyalFurnitureSet.Blocks.DESK_LEFT);
        royalStation(consumer, RoyalFurnitureSet.Blocks.DESK_RIGHT);
        royalStation(consumer, RoyalFurnitureSet.Blocks.DRAWER);
        royalStation(consumer, RoyalFurnitureSet.Blocks.DRESSER);
        royalStation(consumer, RoyalFurnitureSet.Blocks.LOCKBOX);
        royalStation(consumer, RoyalFurnitureSet.Blocks.WARDROBE_BOTTOM);
        royalStation(consumer, RoyalFurnitureSet.Blocks.WARDROBE_TOP);
        royalStation(consumer, RoyalFurnitureSet.Blocks.PAINTING_SMALL);
        royalStation(consumer, RoyalFurnitureSet.Blocks.PAINTING_WIDE);
        royalStation(consumer, RoyalFurnitureSet.Blocks.OVEN);
        royalStation(consumer, RoyalFurnitureSet.Blocks.DOOR_DOUBLE);
        royalStation(consumer, RoyalFurnitureSet.Blocks.DOOR_SINGLE);
        royalStation(consumer, RoyalFurnitureSet.Blocks.BED_SINGLE);
        royalStation(consumer, RoyalFurnitureSet.Blocks.BED_DOUBLE);
        royalStation(consumer, RoyalFurnitureSet.Blocks.SHELF);
        royalStation(consumer, RoyalFurnitureSet.Blocks.SOFA);
        royalStation(consumer, RoyalFurnitureSet.Blocks.COUNTER);
    }

    private void royalStation(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> block)
    {
        furnitureStation(consumer, null, () -> Items.GOLD_INGOT, () -> Blocks.WHITE_WOOL, block);
    }
}
