package xyz.apex.minecraft.fantasyfurniture.forge.data.providers;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.Validate;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.CounterType;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ShelfType;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.SofaType;

import java.util.function.Supplier;

public abstract class ItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider
{
    protected ItemModelProvider(GatherDataEvent event)
    {
        super(event.getGenerator().getPackOutput(), event.getModContainer().getModId(), event.getExistingFileHelper());
    }

    public ItemModelBuilder blockItem(Supplier<? extends ItemLike> item, ModelFile blockModel)
    {
        var itemModelPath = nameWithPrefix(item, ModelProvider.ITEM_FOLDER);
        return getBuilder(itemModelPath.toString()).parent(blockModel);
    }

    public void blockItem(Supplier<? extends ItemLike> item)
    {
        var blockModelPath = nameWithPrefix(item, ModelProvider.BLOCK_FOLDER);
        var blockModel = getExistingFile(blockModelPath);
        blockItem(item, blockModel);
    }

    public ResourceLocation nameWithPrefix(Supplier<? extends ItemLike> entry, String prefix)
    {
        var registryName = ForgeRegistries.ITEMS.getKey(entry.get().asItem());
        Validate.notNull(registryName);
        return new ResourceLocation(registryName.getNamespace(), "%s/%s".formatted(prefix, registryName.getPath()));
    }

    public void doorBlockItem(Supplier<? extends ItemLike> item)
    {
        var blockModelPath = nameWithPrefix(item, ModelProvider.BLOCK_FOLDER).withPath("%s_left"::formatted);
        var blockModel = getExistingFile(blockModelPath);
        blockItem(item, blockModel);
    }

    public void shelfBlockItem(Supplier<? extends ItemLike> item)
    {
        var blockModelPath = nameWithPrefix(item, ModelProvider.BLOCK_FOLDER).withPath(path -> "%s_%s".formatted(path, ShelfType.SINGLE.getSerializedName()));
        var blockModel = getExistingFile(blockModelPath);
        blockItem(item, blockModel);
    }

    public void sofaBlockItem(Supplier<? extends ItemLike> item)
    {
        var blockModelPath = nameWithPrefix(item, ModelProvider.BLOCK_FOLDER).withPath(path -> "%s_%s".formatted(path, SofaType.SINGLE.getSerializedName()));
        var blockModel = getExistingFile(blockModelPath);
        blockItem(item, blockModel);
    }

    public void counterBlockItem(Supplier<? extends ItemLike> item)
    {
        var blockModelPath = nameWithPrefix(item, ModelProvider.BLOCK_FOLDER).withPath(path -> "%s_%s".formatted(path, CounterType.SINGLE.getSerializedName()));
        var blockModel = getExistingFile(blockModelPath);
        blockItem(item, blockModel);
    }
}
