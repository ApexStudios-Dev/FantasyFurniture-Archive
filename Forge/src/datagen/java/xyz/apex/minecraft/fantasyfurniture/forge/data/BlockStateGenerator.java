package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.data.event.GatherDataEvent;

import xyz.apex.minecraft.apexcore.shared.registry.RegistryEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.block.WallLightBlock;
import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;

import java.util.function.BiFunction;

public final class BlockStateGenerator extends BlockStateProvider
{
    private final ModelFile blockBlock = new ModelFile.UncheckedModelFile(new ResourceLocation("minecraft", "block/block"));

    BlockStateGenerator(GatherDataEvent event)
    {
        super(event.getGenerator(), FantasyFurniture.ID, event.getExistingFileHelper());
    }

    private void registerTemplates()
    {
        var cutout = new ResourceLocation("minecraft", "cutout");

        // NOTE: All main line models created in BlockBench *MUST* parent these models in order for them to function correctly
        template(new ResourceLocation(FantasyFurniture.ID, "templates/wall_light"))
                .renderType(cutout)
                .transforms()
                    .transform(ItemTransforms.TransformType.FIXED)
                        .translation(0F, 0F, -4F)
                        .scale(.5F, .5F, .5F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/floor_light"))
                .renderType(cutout)
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(0F, -2.75F, 0F)
                        .scale(.5F, .5F, .5F)
                    .end()
                .end()
        ;
    }

    @Override
    protected void registerStatesAndModels()
    {
        registerTemplates();

        simpleBlock(NordicSet.WOOL);
        carpet(NordicSet.CARPET, NordicSet.WOOL);
        wallLight(NordicSet.WALL_LIGHT);
        floorLight(NordicSet.FLOOR_LIGHT);
    }

    private void wallLight(RegistryEntry<? extends Block> entry)
    {
        complexBlock(entry, (blockState, model) -> ConfiguredModel
                .builder()
                .rotationY((int) (blockState.getValue(WallLightBlock.FACING).toYRot() + 180F) % 360)
                .modelFile(model)
                .build()
        );
    }

    private void floorLight(RegistryEntry<? extends Block> entry)
    {
        getVariantBuilder(entry.get()).partialState().setModels(new ConfiguredModel(existingModel(entry)));
    }

    private void complexBlock(RegistryEntry<? extends Block> entry, BiFunction<BlockState, ModelFile.ExistingModelFile, ConfiguredModel[]> models, Property<?>... ignoredProperties)
    {
        var modelFile = existingModel(entry);
        getVariantBuilder(entry.get()).forAllStatesExcept(blockState -> models.apply(blockState, modelFile), ignoredProperties);
    }

    private BlockModelBuilder template(ResourceLocation modelPath)
    {
        return models()
                .getBuilder(blockFolder(modelPath).toString())
                .parent(blockBlock)
        ;
    }

    private void simpleBlock(RegistryEntry<? extends Block> entry)
    {
        var path = blockFolder(entry);
        simpleBlock(entry, models().cubeAll(path.toString(), path));
    }

    private void simpleBlock(RegistryEntry<? extends Block> entry, ModelFile model)
    {
        getVariantBuilder(entry.get()).partialState().setModels(new ConfiguredModel(model));
    }

    private void carpet(RegistryEntry<? extends Block> carpet, RegistryEntry<?> wool)
    {
        simpleBlock(carpet, models().carpet(blockFolder(carpet).toString(), blockFolder(wool)));
    }

    private ResourceLocation blockFolder(RegistryEntry<?> entry)
    {
        return nameWithPrefix(entry, ModelProvider.BLOCK_FOLDER);
    }

    private ResourceLocation blockFolder(ResourceLocation name)
    {
        return withPrefix(name, ModelProvider.BLOCK_FOLDER);
    }

    private ResourceLocation nameWithPrefix(RegistryEntry<?> entry, String prefix)
    {
        return withPrefix(entry.getRegistryName(), prefix);
    }

    private ResourceLocation withPrefix(ResourceLocation name, String prefix)
    {
        var path = name.getPath();
        if(path.startsWith(prefix)) return name;
        return new ResourceLocation(name.getNamespace(), "%s/%s".formatted(prefix, path));
    }

    private ModelFile.ExistingModelFile existingModel(RegistryEntry<? extends Block> entry)
    {
        return models().getExistingFile(blockFolder(entry));
    }
}
