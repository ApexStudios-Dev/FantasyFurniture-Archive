package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.data.event.GatherDataEvent;

import xyz.apex.minecraft.apexcore.shared.registry.RegistryEntry;
import xyz.apex.minecraft.apexcore.shared.registry.block.BlockRegistryEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;

public final class BlockStateGenerator extends BlockStateProvider
{
    BlockStateGenerator(GatherDataEvent event)
    {
        super(event.getGenerator(), FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerStatesAndModels()
    {
        wool(NordicSet.WOOL);
    }

    private void wool(BlockRegistryEntry<?> entry)
    {
        var block = entry.get();

        var blockModelPath = nameWithPrefix(entry, ModelProvider.BLOCK_FOLDER);
        var itemModelPath = nameWithPrefix(entry, ModelProvider.ITEM_FOLDER);

        var model = models().cubeAll(blockModelPath.toString(), blockTexture(block));
        simpleBlock(block, model);
        itemModels().getBuilder(itemModelPath.toString()).parent(model);
    }

    private ResourceLocation nameWithPrefix(RegistryEntry<?> entry, String prefix)
    {
        var registryName = entry.getRegistryName();
        return new ResourceLocation(registryName.getNamespace(), "%s/%s".formatted(prefix, registryName.getPath()));
    }
}
