package xyz.apex.minecraft.fantasyfurniture.forge.data;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.Validate;

import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.minecraft.fantasyfurniture.forge.FantasyFurnitureDataMod;
import xyz.apex.minecraft.fantasyfurniture.forge.Nordic;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public final class BlockStateGenerator extends BlockStateProvider
{
    private final ModelFile blockBlock = new ModelFile.UncheckedModelFile(new ResourceLocation("minecraft", "block/block"));

    public BlockStateGenerator(GatherDataEvent event, PackOutput packOutput)
    {
        super(packOutput, FantasyFurnitureDataMod.ID, event.getExistingFileHelper());
    }

    private void registerTemplates()
    {
        var cutout = new ResourceLocation("minecraft", "cutout");

        // NOTE: All main line models created in BlockBench *MUST* parent these models in order for them to function correctly
        template(new ResourceLocation(FantasyFurnitureDataMod.ID, "templates/wall_light"))
                .renderType(cutout)
                .transforms()
                    .transform(ItemTransforms.TransformType.FIXED)
                        .translation(0F, 0F, -4F)
                        .scale(.5F, .5F, .5F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurnitureDataMod.ID, "templates/floor_light"))
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(0F, -2.75F, 0F)
                        .scale(.5F, .5F, .5F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurnitureDataMod.ID, "templates/table_large"))
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(0F, -3.5F, 0F)
                        .scale(.35F, .35F, .35F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurnitureDataMod.ID, "templates/table_small"))
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .scale(.625F, .625F, .625F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurnitureDataMod.ID, "templates/table_wide"))
                .renderType(cutout)
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(-2.5F, -2.25F, 0F)
                        .scale(.45F, .45F, .45F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurnitureDataMod.ID, "templates/bench"))
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(-2.5F, -2.25F, 0F)
                        .scale(.45F, .45F, .45F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurnitureDataMod.ID, "templates/chair"))
                .renderType(cutout)
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(0F, -1.5F, 0F)
                        .scale(.5F, .5F, .5F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurnitureDataMod.ID, "templates/chandelier"));

        template(new ResourceLocation(FantasyFurnitureDataMod.ID, "templates/cushion"));

        template(new ResourceLocation(FantasyFurnitureDataMod.ID, "templates/stool"));

        template(new ResourceLocation(FantasyFurnitureDataMod.ID, "templates/chest"))
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(-2.25F, -2.25F, 0F)
                        .scale(.45F, .45F, .45F)
                    .end()
                .end()
        ;
    }

    @Override
    protected void registerStatesAndModels()
    {
        registerTemplates();

        simpleBlock(Nordic.WOOL);
        carpet(Nordic.CARPET, Nordic.WOOL);
        wallLight(Nordic.WALL_LIGHT);
        templatedBlock(Nordic.FLOOR_LIGHT);
        table(Nordic.TABLE_LARGE, true);
        table(Nordic.TABLE_SMALL, false);
        table(Nordic.TABLE_WIDE, true);
        facingBlock(Nordic.BENCH, HorizontalDirectionalBlock.FACING);
        facingBlock(Nordic.CHAIR, HorizontalDirectionalBlock.FACING);
        templatedBlock(Nordic.CHANDELIER);
        facingBlock(Nordic.CUSHION, HorizontalDirectionalBlock.FACING);
        facingBlock(Nordic.STOOL, HorizontalDirectionalBlock.FACING);
        facingBlock(Nordic.CHEST, HorizontalDirectionalBlock.FACING);
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    private Property<?>[] gatherIgnoredProperties(Supplier<? extends Block> entry)
    {
        var block = entry.get();
        var properties = Lists.newArrayList();

        // if(block instanceof MultiBlock multiBlock) properties.add(multiBlock.getMultiBlockType().getBlockProperty());
        if(block instanceof SimpleWaterloggedBlock) properties.add(BlockStateProperties.WATERLOGGED);

        return properties.toArray(Property<?>[]::new);
    }

    private void wallLight(Supplier<? extends Block> entry)
    {
        complexBlock(entry, (blockState, model) -> ConfiguredModel
                .builder()
                .rotationY((int) (blockState.getValue(WallTorchBlock.FACING).toYRot() + 180F) % 360)
                .modelFile(model)
                .build()
        );
    }

    private void templatedBlock(Supplier<? extends Block> entry)
    {
        simpleBlock(entry, existingModel(entry));
    }

    private void table(Supplier<? extends Block> entry, boolean withFacing)
    {
        if(withFacing) facingBlock(entry, HorizontalDirectionalBlock.FACING);
        else getVariantBuilder(entry.get()).partialState().setModels(new ConfiguredModel(existingModel(entry)));
    }

    private void facingBlock(Supplier<? extends Block> entry, DirectionProperty facingProperty)
    {
        complexBlock(entry, (blockState, model) -> ConfiguredModel
                .builder()
                .rotationY((int) blockState.getValue(facingProperty).getOpposite().toYRot() % 360)
                .modelFile(model)
                .build()
        );
    }

    private void complexBlock(Supplier<? extends Block> entry, BiFunction<BlockState, ModelFile.ExistingModelFile, ConfiguredModel[]> models)
    {
        var modelFile = existingModel(entry);
        getVariantBuilder(entry.get()).forAllStatesExcept(blockState -> models.apply(blockState, modelFile), gatherIgnoredProperties(entry));
    }

    private BlockModelBuilder template(ResourceLocation modelPath)
    {
        return models()
                .getBuilder(blockFolder(modelPath).toString())
                .parent(blockBlock)
        ;
    }

    private void simpleBlock(Supplier<? extends Block> entry)
    {
        var path = blockFolder(entry);
        simpleBlock(entry, models().cubeAll(path.toString(), path));
    }

    private void simpleBlock(Supplier<? extends Block> entry, ModelFile model)
    {
        getVariantBuilder(entry.get()).partialState().setModels(new ConfiguredModel(model));
    }

    private void carpet(Supplier<? extends Block> carpet, Supplier<? extends Block> wool)
    {
        simpleBlock(carpet, models().carpet(blockFolder(carpet).toString(), blockFolder(wool)));
    }

    private ResourceLocation blockFolder(Supplier<? extends Block> entry)
    {
        return nameWithPrefix(entry, ModelProvider.BLOCK_FOLDER);
    }

    private ResourceLocation blockFolder(ResourceLocation name)
    {
        return withPrefix(name, ModelProvider.BLOCK_FOLDER);
    }

    private ResourceLocation nameWithPrefix(Supplier<? extends Block> entry, String prefix)
    {
        var registryName = ForgeRegistries.BLOCKS.getKey(entry.get());
        Validate.notNull(registryName);
        return withPrefix(registryName, prefix);
    }

    private ResourceLocation withPrefix(ResourceLocation name, String prefix)
    {
        var path = name.getPath();
        if(path.startsWith(prefix)) return name;
        return new ResourceLocation(name.getNamespace(), "%s/%s".formatted(prefix, path));
    }

    private ModelFile.ExistingModelFile existingModel(Supplier<? extends Block> entry)
    {
        return models().getExistingFile(blockFolder(entry));
    }
}
