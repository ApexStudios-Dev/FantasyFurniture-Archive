package xyz.apex.minecraft.fantasyfurniture.forge;

import org.apache.commons.lang3.Validate;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.CounterType;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ShelfType;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.SofaType;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

import java.util.function.Supplier;

public final class ItemModelGenerator extends ItemModelProvider
{
    public ItemModelGenerator(GatherDataEvent event, PackOutput packOutput)
    {
        super(packOutput, FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerModels()
    {
        blockItem(FantasyFurniture.FURNITURE_STATION_BLOCK);

        nordic();
        venthyr();
        dunmer();
        boneWither();
        boneSkeleton();
        necrolord();
    }

    private void nordic()
    {
        blockItem(NordicSet.WOOL);
        blockItem(NordicSet.CARPET);
        blockItem(NordicSet.WALL_LIGHT);
        blockItem(NordicSet.FLOOR_LIGHT);
        blockItem(NordicSet.TABLE_LARGE);
        blockItem(NordicSet.TABLE_SMALL);
        blockItem(NordicSet.TABLE_WIDE);
        blockItem(NordicSet.BENCH);
        blockItem(NordicSet.CHAIR);
        blockItem(NordicSet.CHANDELIER);
        blockItem(NordicSet.CUSHION);
        blockItem(NordicSet.STOOL);
        blockItem(NordicSet.CHEST);
        blockItem(NordicSet.BOOKSHELF);
        blockItem(NordicSet.DESK_LEFT);
        blockItem(NordicSet.DESK_RIGHT);
        blockItem(NordicSet.DRAWER);
        blockItem(NordicSet.DRESSER);
        blockItem(NordicSet.LOCKBOX);
        blockItem(NordicSet.WARDROBE_BOTTOM);
        blockItem(NordicSet.WARDROBE_TOP);
        blockItem(NordicSet.PAINTING_WIDE);
        blockItem(NordicSet.PAINTING_SMALL);
        blockItem(NordicSet.OVEN);
        doorBlockItem(NordicSet.DOOR_DOUBLE);
        doorBlockItem(NordicSet.DOOR_SINGLE);
        blockItem(NordicSet.BED_SINGLE);
        blockItem(NordicSet.BED_DOUBLE);
        shelfBlockItem(NordicSet.SHELF);
        sofaBlockItem(NordicSet.SOFA);
        sofaBlockItem(NordicSet.COUNTER);
    }

    private void venthyr()
    {
        blockItem(VenthyrSet.WOOL);
        blockItem(VenthyrSet.CARPET);
        blockItem(VenthyrSet.WALL_LIGHT);
        blockItem(VenthyrSet.FLOOR_LIGHT);
        blockItem(VenthyrSet.TABLE_LARGE);
        blockItem(VenthyrSet.TABLE_LARGE_FANCY);
        blockItem(VenthyrSet.TABLE_SMALL);
        blockItem(VenthyrSet.TABLE_SMALL_FANCY);
        blockItem(VenthyrSet.TABLE_WIDE);
        blockItem(VenthyrSet.TABLE_WIDE_FANCY);
        blockItem(VenthyrSet.BENCH);
        blockItem(VenthyrSet.CHAIR);
        blockItem(VenthyrSet.CHANDELIER);
        blockItem(VenthyrSet.CUSHION);
        blockItem(VenthyrSet.STOOL);
        blockItem(VenthyrSet.CHEST);
        blockItem(VenthyrSet.BOOKSHELF);
        blockItem(VenthyrSet.DESK_LEFT);
        blockItem(VenthyrSet.DESK_RIGHT);
        blockItem(VenthyrSet.DRAWER);
        blockItem(VenthyrSet.DRESSER);
        blockItem(VenthyrSet.LOCKBOX);
        blockItem(VenthyrSet.WARDROBE_BOTTOM);
        blockItem(VenthyrSet.WARDROBE_TOP);
        blockItem(VenthyrSet.PAINTING_WIDE);
        blockItem(VenthyrSet.PAINTING_SMALL);
        blockItem(VenthyrSet.OVEN);
        doorBlockItem(VenthyrSet.DOOR_DOUBLE);
        doorBlockItem(VenthyrSet.DOOR_SINGLE);
        blockItem(VenthyrSet.BED_SINGLE);
        blockItem(VenthyrSet.BED_DOUBLE);
        shelfBlockItem(VenthyrSet.SHELF);
        sofaBlockItem(VenthyrSet.SOFA);
        sofaBlockItem(VenthyrSet.COUNTER);
    }

    private void dunmer()
    {
        blockItem(DunmerSet.WOOL);
        blockItem(DunmerSet.CARPET);
        blockItem(DunmerSet.WALL_LIGHT);
        blockItem(DunmerSet.FLOOR_LIGHT);
        blockItem(DunmerSet.TABLE_LARGE);
        blockItem(DunmerSet.TABLE_SMALL);
        blockItem(DunmerSet.TABLE_WIDE);
        blockItem(DunmerSet.BENCH);
        blockItem(DunmerSet.CHAIR);
        blockItem(DunmerSet.CHANDELIER);
        blockItem(DunmerSet.CUSHION);
        blockItem(DunmerSet.STOOL);
        blockItem(DunmerSet.CHEST);
        blockItem(DunmerSet.BOOKSHELF);
        blockItem(DunmerSet.DESK_LEFT);
        blockItem(DunmerSet.DESK_RIGHT);
        blockItem(DunmerSet.DRAWER);
        blockItem(DunmerSet.DRESSER);
        blockItem(DunmerSet.LOCKBOX);
        blockItem(DunmerSet.WARDROBE_BOTTOM);
        blockItem(DunmerSet.WARDROBE_TOP);
        blockItem(DunmerSet.PAINTING_WIDE);
        blockItem(DunmerSet.PAINTING_SMALL);
        blockItem(DunmerSet.OVEN);
        doorBlockItem(DunmerSet.DOOR_DOUBLE);
        doorBlockItem(DunmerSet.DOOR_SINGLE);
        blockItem(DunmerSet.BED_SINGLE);
        blockItem(DunmerSet.BED_DOUBLE);
        shelfBlockItem(DunmerSet.SHELF);
        sofaBlockItem(DunmerSet.SOFA);
        sofaBlockItem(DunmerSet.COUNTER);
    }

    private void boneWither()
    {
        blockItem(BoneSet.Wither.WOOL);
        blockItem(BoneSet.Wither.CARPET);
        blockItem(BoneSet.Wither.WALL_LIGHT);
        blockItem(BoneSet.Wither.FLOOR_LIGHT);
        blockItem(BoneSet.Wither.TABLE_LARGE);
        blockItem(BoneSet.Wither.TABLE_SMALL);
        blockItem(BoneSet.Wither.TABLE_WIDE);
        blockItem(BoneSet.Wither.BENCH);
        blockItem(BoneSet.Wither.CHAIR);
        blockItem(BoneSet.Wither.CHANDELIER);
        blockItem(BoneSet.Wither.CUSHION);
        blockItem(BoneSet.Wither.STOOL);
        blockItem(BoneSet.Wither.CHEST);
        blockItem(BoneSet.Wither.BOOKSHELF);
        blockItem(BoneSet.Wither.DESK_LEFT);
        blockItem(BoneSet.Wither.DESK_RIGHT);
        blockItem(BoneSet.Wither.DRAWER);
        blockItem(BoneSet.Wither.DRESSER);
        blockItem(BoneSet.Wither.LOCKBOX);
        blockItem(BoneSet.Wither.WARDROBE_BOTTOM);
        blockItem(BoneSet.Wither.WARDROBE_TOP);
        blockItem(BoneSet.Wither.PAINTING_WIDE);
        blockItem(BoneSet.Wither.PAINTING_SMALL);
        blockItem(BoneSet.Wither.OVEN);
        doorBlockItem(BoneSet.Wither.DOOR_DOUBLE);
        doorBlockItem(BoneSet.Wither.DOOR_SINGLE);
        blockItem(BoneSet.Wither.BED_SINGLE);
        blockItem(BoneSet.Wither.BED_DOUBLE);
        shelfBlockItem(BoneSet.Wither.SHELF);
        sofaBlockItem(BoneSet.Wither.SOFA);
        sofaBlockItem(BoneSet.Wither.COUNTER);
    }

    private void boneSkeleton()
    {
        blockItem(BoneSet.Skeleton.WOOL);
        blockItem(BoneSet.Skeleton.CARPET);
        blockItem(BoneSet.Skeleton.WALL_LIGHT);
        blockItem(BoneSet.Skeleton.FLOOR_LIGHT);
        blockItem(BoneSet.Skeleton.TABLE_LARGE);
        blockItem(BoneSet.Skeleton.TABLE_SMALL);
        blockItem(BoneSet.Skeleton.TABLE_WIDE);
        blockItem(BoneSet.Skeleton.BENCH);
        blockItem(BoneSet.Skeleton.CHAIR);
        blockItem(BoneSet.Skeleton.CHANDELIER);
        blockItem(BoneSet.Skeleton.CUSHION);
        blockItem(BoneSet.Skeleton.STOOL);
        blockItem(BoneSet.Skeleton.CHEST);
        blockItem(BoneSet.Skeleton.BOOKSHELF);
        blockItem(BoneSet.Skeleton.DESK_LEFT);
        blockItem(BoneSet.Skeleton.DESK_RIGHT);
        blockItem(BoneSet.Skeleton.DRAWER);
        blockItem(BoneSet.Skeleton.DRESSER);
        blockItem(BoneSet.Skeleton.LOCKBOX);
        blockItem(BoneSet.Skeleton.WARDROBE_BOTTOM);
        blockItem(BoneSet.Skeleton.WARDROBE_TOP);
        blockItem(BoneSet.Skeleton.PAINTING_WIDE);
        blockItem(BoneSet.Skeleton.PAINTING_SMALL);
        blockItem(BoneSet.Skeleton.OVEN);
        doorBlockItem(BoneSet.Skeleton.DOOR_DOUBLE);
        doorBlockItem(BoneSet.Skeleton.DOOR_SINGLE);
        blockItem(BoneSet.Skeleton.BED_SINGLE);
        blockItem(BoneSet.Skeleton.BED_DOUBLE);
        shelfBlockItem(BoneSet.Skeleton.SHELF);
        sofaBlockItem(BoneSet.Skeleton.SOFA);
        sofaBlockItem(BoneSet.Skeleton.COUNTER);
    }

    private void necrolord()
    {
        blockItem(NecrolordSet.WOOL);
        blockItem(NecrolordSet.CARPET);
        blockItem(NecrolordSet.WALL_LIGHT);
        blockItem(NecrolordSet.FLOOR_LIGHT);
        blockItem(NecrolordSet.TABLE_LARGE);
        blockItem(NecrolordSet.TABLE_SMALL);
        blockItem(NecrolordSet.TABLE_WIDE);
        blockItem(NecrolordSet.BENCH);
        blockItem(NecrolordSet.CHAIR);
        blockItem(NecrolordSet.CHANDELIER);
        blockItem(NecrolordSet.CUSHION);
        blockItem(NecrolordSet.STOOL);
        blockItem(NecrolordSet.CHEST);
        blockItem(NecrolordSet.BOOKSHELF);
        blockItem(NecrolordSet.DESK_LEFT);
        blockItem(NecrolordSet.DESK_RIGHT);
        blockItem(NecrolordSet.DRAWER);
        blockItem(NecrolordSet.DRESSER);
        blockItem(NecrolordSet.LOCKBOX);
        blockItem(NecrolordSet.WARDROBE_BOTTOM);
        blockItem(NecrolordSet.WARDROBE_TOP);
        blockItem(NecrolordSet.PAINTING_WIDE);
        blockItem(NecrolordSet.PAINTING_SMALL);
        blockItem(NecrolordSet.OVEN);
        doorBlockItem(NecrolordSet.DOOR_DOUBLE);
        doorBlockItem(NecrolordSet.DOOR_SINGLE);
        blockItem(NecrolordSet.BED_SINGLE);
        blockItem(NecrolordSet.BED_DOUBLE);
        shelfBlockItem(NecrolordSet.SHELF);
        sofaBlockItem(NecrolordSet.SOFA);
        sofaBlockItem(NecrolordSet.COUNTER);
    }

    private ItemModelBuilder blockItem(Supplier<? extends ItemLike> item, ModelFile blockModel)
    {
        var itemModelPath = nameWithPrefix(item, ModelProvider.ITEM_FOLDER);
        return getBuilder(itemModelPath.toString()).parent(blockModel);
    }

    private ItemModelBuilder blockItem(Supplier<? extends ItemLike> item)
    {
        var blockModelPath = nameWithPrefix(item, ModelProvider.BLOCK_FOLDER);
        var blockModel = getExistingFile(blockModelPath);
        return blockItem(item, blockModel);
    }

    private ResourceLocation nameWithPrefix(Supplier<? extends ItemLike> entry, String prefix)
    {
        var registryName = ForgeRegistries.ITEMS.getKey(entry.get().asItem());
        Validate.notNull(registryName);
        return new ResourceLocation(registryName.getNamespace(), "%s/%s".formatted(prefix, registryName.getPath()));
    }

    private ItemModelBuilder doorBlockItem(Supplier<? extends ItemLike> item)
    {
        var blockModelPath = nameWithPrefix(item, ModelProvider.BLOCK_FOLDER).withPath(path -> path + "_left");
        var blockModel = getExistingFile(blockModelPath);
        return blockItem(item, blockModel);
    }

    private ItemModelBuilder shelfBlockItem(Supplier<? extends ItemLike> item)
    {
        var blockModelPath = nameWithPrefix(item, ModelProvider.BLOCK_FOLDER).withPath(path -> "%s_%s".formatted(path, ShelfType.SINGLE.getSerializedName()));
        var blockModel = getExistingFile(blockModelPath);
        return blockItem(item, blockModel);
    }

    private ItemModelBuilder sofaBlockItem(Supplier<? extends ItemLike> item)
    {
        var blockModelPath = nameWithPrefix(item, ModelProvider.BLOCK_FOLDER).withPath(path -> "%s_%s".formatted(path, SofaType.SINGLE.getSerializedName()));
        var blockModel = getExistingFile(blockModelPath);
        return blockItem(item, blockModel);
    }

    private ItemModelBuilder counterBlockItem(Supplier<? extends ItemLike> item)
    {
        var blockModelPath = nameWithPrefix(item, ModelProvider.BLOCK_FOLDER).withPath(path -> "%s_%s".formatted(path, CounterType.SINGLE.getSerializedName()));
        var blockModel = getExistingFile(blockModelPath);
        return blockItem(item, blockModel);
    }
}
