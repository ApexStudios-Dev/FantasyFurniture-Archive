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

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;

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
}
