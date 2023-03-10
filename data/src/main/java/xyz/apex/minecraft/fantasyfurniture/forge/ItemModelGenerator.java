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
import xyz.apex.minecraft.fantasyfurniture.common.init.DunmerSet;
import xyz.apex.minecraft.fantasyfurniture.common.init.NordicSet;
import xyz.apex.minecraft.fantasyfurniture.common.init.VenthyrSet;

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
