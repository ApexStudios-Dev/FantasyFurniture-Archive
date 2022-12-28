package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.data.event.GatherDataEvent;

import xyz.apex.minecraft.apexcore.shared.registry.entry.RegistryEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;

public final class ItemModelGenerator extends ItemModelProvider
{
    ItemModelGenerator(GatherDataEvent event, PackOutput packOutput)
    {
        super(packOutput, FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerModels()
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
    }

    private ItemModelBuilder blockItem(RegistryEntry<?> item, ModelFile blockModel)
    {
        var itemModelPath = nameWithPrefix(item, ModelProvider.ITEM_FOLDER);
        return getBuilder(itemModelPath.toString()).parent(blockModel);
    }

    private ItemModelBuilder blockItem(RegistryEntry<?> item)
    {
        var blockModelPath = nameWithPrefix(item, ModelProvider.BLOCK_FOLDER);
        var blockModel = getExistingFile(blockModelPath);
        return blockItem(item, blockModel);
    }

    private ResourceLocation nameWithPrefix(RegistryEntry<?> entry, String prefix)
    {
        var registryName = entry.getRegistryName();
        return new ResourceLocation(registryName.getNamespace(), "%s/%s".formatted(prefix, registryName.getPath()));
    }
}
