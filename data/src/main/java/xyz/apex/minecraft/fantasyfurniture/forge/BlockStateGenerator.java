package xyz.apex.minecraft.fantasyfurniture.forge;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.Validate;

import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlock;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.ShelfBlock;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ModBlockStateProperties;
import xyz.apex.minecraft.fantasyfurniture.common.init.NordicSet;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public final class BlockStateGenerator extends BlockStateProvider
{
    private final ModelFile blockBlock = new ModelFile.UncheckedModelFile(new ResourceLocation("minecraft", "block/block"));

    public BlockStateGenerator(GatherDataEvent event, PackOutput packOutput)
    {
        super(packOutput, FantasyFurniture.ID, event.getExistingFileHelper());
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
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(0F, -2.75F, 0F)
                        .scale(.5F, .5F, .5F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/table_large"))
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(0F, -3.5F, 0F)
                        .scale(.35F, .35F, .35F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/table_small"))
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .scale(.625F, .625F, .625F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/table_wide"))
                .renderType(cutout)
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(-2.5F, -2.25F, 0F)
                        .scale(.45F, .45F, .45F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/bench"))
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(-2.5F, -2.25F, 0F)
                        .scale(.45F, .45F, .45F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/chair"))
                .renderType(cutout)
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(0F, -1.5F, 0F)
                        .scale(.5F, .5F, .5F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/chandelier"));

        template(new ResourceLocation(FantasyFurniture.ID, "templates/cushion"));

        template(new ResourceLocation(FantasyFurniture.ID, "templates/stool"));

        template(new ResourceLocation(FantasyFurniture.ID, "templates/chest"))
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(-2.25F, -2.25F, 0F)
                        .scale(.45F, .45F, .45F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/bookshelf"))
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, -135F, 0F)
                        .translation(-2F, -3.5F, 0F)
                        .scale(.35F, .35F, .35F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/desk_left"))
                .renderType(cutout)
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(-2.5F, -2.25F, 0F)
                        .scale(.45F, .45F, .45F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/desk_right"))
                .renderType(cutout)
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(-2.5F, -2.25F, 0F)
                        .scale(.45F, .45F, .45F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/drawer"));

        template(new ResourceLocation(FantasyFurniture.ID, "templates/dresser"))
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(-2.5F, -2.25F, 0F)
                        .scale(.45F, .45F, .45F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/lockbox"));

        template(new ResourceLocation(FantasyFurniture.ID, "templates/wardrobe_bottom"))
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(-2F, -3.5F, 0F)
                        .scale(.35F, .35F, .35F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/wardrobe_top"))
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(-2F, -3.5F, 0F)
                        .scale(.35F, .35F, .35F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/painting_wide"))
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(0F, -1.5F, 0F)
                        .scale(.5F, .5F, .5F)
                    .end()
                    .transform(ItemTransforms.TransformType.FIXED)
                        .translation(4F, 0F, -4F)
                        .scale(.5F, .5F, .5F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/painting_small"))
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .scale(.625F, .625F, .625F)
                    .end()
                    .transform(ItemTransforms.TransformType.FIXED)
                        .translation(0F ,0F, -8F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/oven"))
                .renderType(cutout)
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/door_double"))
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(0F, -2.25F, 0F)
                        .scale(.45F, .45F, .45F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/door_single"))
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 225F, 0F)
                        .translation(0F, -2.25F, 0F)
                        .scale(.45F, .45F, .45F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/bed_single"))
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 45F, 0F)
                        .translation(-2.5F, .25F, 0F)
                        .scale(.45F, .45F, .45F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/bed_double"))
                .transforms()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(30F, 45F, 0F)
                        .translation(0F, -.75F, 0F)
                        .scale(.3F, .3F, .3F)
                    .end()
                .end()
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/shelf"))
                .renderType(cutout)
        ;

        template(new ResourceLocation(FantasyFurniture.ID, "templates/sofa"))
                //.renderType(cutout)
        ;
    }

    @Override
    protected void registerStatesAndModels()
    {
        registerTemplates();

        facingBlock(FantasyFurniture.FURNITURE_STATION_BLOCK, HorizontalDirectionalBlock.FACING);

        simpleBlock(NordicSet.WOOL);
        carpet(NordicSet.CARPET, NordicSet.WOOL);
        wallLight(NordicSet.WALL_LIGHT);
        templatedBlock(NordicSet.FLOOR_LIGHT);
        facingBlock(NordicSet.TABLE_LARGE, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.TABLE_SMALL, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.TABLE_WIDE, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.BENCH, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.CHAIR, HorizontalDirectionalBlock.FACING);
        templatedBlock(NordicSet.CHANDELIER);
        facingBlock(NordicSet.CUSHION, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.STOOL, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.CHEST, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.BOOKSHELF, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.DESK_LEFT, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.DESK_RIGHT, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.DRAWER, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.DRESSER, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.LOCKBOX, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.WARDROBE_BOTTOM, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.WARDROBE_TOP, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.PAINTING_WIDE, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.PAINTING_SMALL, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.OVEN, HorizontalDirectionalBlock.FACING);
        doorBlock(NordicSet.DOOR_DOUBLE);
        doorBlock(NordicSet.DOOR_SINGLE);
        facingBlock(NordicSet.BED_SINGLE, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.BED_DOUBLE, HorizontalDirectionalBlock.FACING);
        shelfBlock(NordicSet.SHELF);
        sofaBlock(NordicSet.SOFA);
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    private Property<?>[] gatherIgnoredProperties(Supplier<? extends Block> entry)
    {
        var block = entry.get();
        var properties = Lists.newArrayList();

        if(block instanceof MultiBlock multiBlock) properties.add(multiBlock.getMultiBlockType().getBlockProperty());
        if(block instanceof SimpleWaterloggedBlock) properties.add(BlockStateProperties.WATERLOGGED);
        if(block instanceof DoorBlock)
        {
            properties.add(DoorBlock.POWERED);
            properties.add(DoorBlock.HALF);
        }

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

    private void facingBlock(Supplier<? extends Block> entry, DirectionProperty facingProperty)
    {
        complexBlock(entry, (blockState, model) -> ConfiguredModel
                .builder()
                .rotationY((int) blockState.getValue(facingProperty).getOpposite().toYRot() % 360)
                .modelFile(model)
                .build()
        );
    }

    private void doorBlock(Supplier<? extends Block> entry)
    {
        var leftModel = models().getExistingFile(blockFolder(entry).withPath(path -> path + "_left"));
        var rightModel = models().getExistingFile(blockFolder(entry).withPath(path -> path + "_right"));

        getVariantBuilder(entry.get()).forAllStatesExcept(blockState -> {
            var open = blockState.getValue(DoorBlock.OPEN);
            var right = blockState.getValue(DoorBlock.HINGE) == DoorHingeSide.RIGHT;

            int yRot = (int) blockState.getValue(DoorBlock.FACING).toYRot();
            if(open) yRot = yRot + (right ? -90 : 90);
            yRot %= 360;

            var model = open ? (right ? leftModel : rightModel) : (right ? rightModel : leftModel);
            return ConfiguredModel.builder().modelFile(model).rotationY(yRot).build();
        }, gatherIgnoredProperties(entry));
    }

    private void complexBlock(Supplier<? extends Block> entry, BiFunction<BlockState, ModelFile.ExistingModelFile, ConfiguredModel[]> models)
    {
        var modelFile = existingModel(entry);
        getVariantBuilder(entry.get()).forAllStatesExcept(blockState -> models.apply(blockState, modelFile), gatherIgnoredProperties(entry));
    }

    private void shelfBlock(Supplier<? extends Block> entry)
    {
        getVariantBuilder(entry.get()).forAllStatesExcept(blockState -> {
            var shelfType = blockState.getValue(ModBlockStateProperties.SHELF_TYPE);

            var model = models().getExistingFile(blockFolder(entry)
                    .withPath(path -> "%s_%s".formatted(path, shelfType.getSerializedName()))
            );

            return ConfiguredModel
                    .builder()
                    .rotationY((int) blockState.getValue(ShelfBlock.FACING).getOpposite().toYRot() % 360)
                    .modelFile(model)
                    .build();
        }, gatherIgnoredProperties(entry));
    }

    private void sofaBlock(Supplier<? extends Block> entry)
    {
        getVariantBuilder(entry.get()).forAllStatesExcept(blockState -> {
            var sofaType = blockState.getValue(ModBlockStateProperties.SOFA_TYPE);

            var model = models().getExistingFile(blockFolder(entry)
                    .withPath(path -> "%s_%s".formatted(path, sofaType.getSerializedName()))
            );

            return ConfiguredModel
                    .builder()
                    .rotationY((int) blockState.getValue(ShelfBlock.FACING).getOpposite().toYRot() % 360)
                    .modelFile(model)
                    .build();
        }, gatherIgnoredProperties(entry));
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
