package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.data.event.GatherDataEvent;

import xyz.apex.minecraft.apexcore.shared.registry.RegistryEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;

import java.util.function.Function;

public final class BlockStateGenerator extends BlockStateProvider
{
    BlockStateGenerator(GatherDataEvent event)
    {
        super(event.getGenerator(), FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerStatesAndModels()
    {
        simpleBlock(NordicSet.WOOL);
        carpet(NordicSet.CARPET, NordicSet.WOOL);
    }

    private VariantBlockStateBuilder simpleBlock(RegistryEntry<? extends Block> entry)
    {
        return simpleBlock(entry, cubeAll(entry));
    }

    private VariantBlockStateBuilder simpleBlock(RegistryEntry<? extends Block> entry, Function<ModelFile, ConfiguredModel[]> expander)
    {
        return simpleBlock(entry, expander.apply(cubeAll(entry)));
    }

    private VariantBlockStateBuilder simpleBlock(RegistryEntry<? extends Block> entry, ModelFile model)
    {
        return simpleBlock(entry, new ConfiguredModel(model));
    }

    private VariantBlockStateBuilder simpleBlock(RegistryEntry<? extends Block> entry, ConfiguredModel... models)
    {
        return getVariantBuilder(entry.get()).partialState().setModels(models);
    }

    private VariantBlockStateBuilder carpet(RegistryEntry<? extends Block> carpet, RegistryEntry<?> wool)
    {
        var model = models().carpet(blockFolder(carpet).toString(), blockFolder(wool));
        return simpleBlock(carpet, model);
    }

    private ModelFile cubeAll(RegistryEntry<?> entry)
    {
        var path = blockFolder(entry);
        return models().cubeAll(path.toString(), path);
    }

    private ResourceLocation blockFolder(RegistryEntry<?> entry)
    {
        return nameWithPrefix(entry, ModelProvider.BLOCK_FOLDER);
    }

    private ResourceLocation nameWithPrefix(RegistryEntry<?> entry, String prefix)
    {
        var registryName = entry.getRegistryName();
        return new ResourceLocation(registryName.getNamespace(), "%s/%s".formatted(prefix, registryName.getPath()));
    }
}
