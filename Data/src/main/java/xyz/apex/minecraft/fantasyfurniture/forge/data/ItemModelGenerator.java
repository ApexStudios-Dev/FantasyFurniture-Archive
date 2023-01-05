package xyz.apex.minecraft.fantasyfurniture.forge.data;

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

import xyz.apex.minecraft.fantasyfurniture.forge.FantasyFurnitureDataMod;
import xyz.apex.minecraft.fantasyfurniture.forge.Nordic;

import java.util.function.Supplier;

public final class ItemModelGenerator extends ItemModelProvider
{
    public ItemModelGenerator(GatherDataEvent event, PackOutput packOutput)
    {
        super(packOutput, FantasyFurnitureDataMod.ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerModels()
    {
        blockItem(Nordic.WOOL);
        blockItem(Nordic.CARPET);
        blockItem(Nordic.WALL_LIGHT);
        blockItem(Nordic.FLOOR_LIGHT);
        blockItem(Nordic.TABLE_LARGE);
        blockItem(Nordic.TABLE_SMALL);
        blockItem(Nordic.TABLE_WIDE);
        blockItem(Nordic.BENCH);
        blockItem(Nordic.CHAIR);
        blockItem(Nordic.CHANDELIER);
        blockItem(Nordic.CUSHION);
        blockItem(Nordic.STOOL);
        blockItem(Nordic.CHEST);
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
}
