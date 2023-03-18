package xyz.apex.minecraft.fantasyfurniture.forge;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.Validate;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.minecraft.apexcore.common.component.Component;
import xyz.apex.minecraft.apexcore.common.component.ComponentBlock;
import xyz.apex.minecraft.apexcore.common.component.ComponentType;
import xyz.apex.minecraft.apexcore.common.component.ComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.components.DoorComponent;
import xyz.apex.minecraft.apexcore.common.component.components.HorizontalFacingComponent;
import xyz.apex.minecraft.apexcore.common.util.function.Lazy;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.DyeableComponent;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.OvenComponent;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ModBlockStateProperties;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public final class BlockStateGenerator extends BlockStateProvider
{
    private final ResourceLocation renderTypeCutout = new ResourceLocation("minecraft", "cutout");

    private final Supplier<BlockModelBuilder> tintedCube = Lazy.of(() -> models()
            .getBuilder("%s:block/tinted_cube".formatted(FantasyFurniture.ID))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation("minecraft", "block/block")))
            .renderType(renderTypeCutout)
            .element()
                .from(0F, 0F, 0F)
                .to(16F, 16F, 16F)
                .allFaces((face, builder) -> builder.texture("#%s".formatted(face.getSerializedName())).cullface(face))
            .end()

            .element()
                .from(0F, 0F, 0F)
                .to(16F, 16F, 16F)
                .allFaces((face, builder) -> builder.texture("#%s_tint".formatted(face.getSerializedName())).cullface(face).tintindex(DyeableComponent.TINT_INDEX))
            .end()
    );

    private final Supplier<BlockModelBuilder> tintedCubeAll = Lazy.of(() -> {
        var model = models().getBuilder("%s:block/tinted_cube_all".formatted(FantasyFurniture.ID)).parent(tintedCube.get()).texture("particle", "#all_tint");

        Direction.stream().map(Direction::getSerializedName).forEach(face -> model
                .texture("%s".formatted(face), "#all")
                .texture("%s_tint".formatted(face), "#all_tint")
        );

        return model;
    });

    private final Supplier<BlockModelBuilder> tintedCarpet = Lazy.of(() -> models()
            .getBuilder("%s:block/tinted_carpet".formatted(FantasyFurniture.ID))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation("minecraft", "block/thin_block")))
            .renderType(renderTypeCutout)
            .texture("particle", "#wool_tint")
            .element()
                .from(0F, 0F, 0F)
                .to(16F, 1F, 16F)
                .face(Direction.DOWN)
                    .uvs(0F, 0F, 16F, 16F)
                    .texture("#wool")
                    .cullface(Direction.DOWN)
                .end()

                .face(Direction.UP)
                    .uvs(0F, 0F, 16F, 16F)
                    .texture("#wool")
                .end()

                .face(Direction.NORTH)
                    .uvs(0F, 15F, 16F, 16F)
                    .texture("#wool")
                    .cullface(Direction.NORTH)
                .end()

                .face(Direction.SOUTH)
                    .uvs(0F, 15F, 16F, 16F)
                    .texture("#wool")
                    .cullface(Direction.SOUTH)
                .end()

                .face(Direction.WEST)
                    .uvs(0F, 15F, 16F, 16F)
                    .texture("#wool")
                    .cullface(Direction.WEST)
                .end()

                .face(Direction.EAST)
                    .uvs(0F, 15F, 16F, 16F)
                    .texture("#wool")
                    .cullface(Direction.EAST)
                .end()
            .end()

            .element()
                .from(0F, 0F, 0F)
                .to(16F, 1F, 16F)
                .face(Direction.DOWN)
                    .uvs(0F, 0F, 16F, 16F)
                    .texture("#wool_tint")
                    .cullface(Direction.DOWN)
                    .tintindex(DyeableComponent.TINT_INDEX)
                .end()

                .face(Direction.UP)
                    .uvs(0F, 0F, 16F, 16F)
                    .texture("#wool_tint")
                    .tintindex(DyeableComponent.TINT_INDEX)
                .end()

                .face(Direction.NORTH)
                    .uvs(0F, 15F, 16F, 16F)
                    .texture("#wool_tint")
                    .cullface(Direction.NORTH)
                    .tintindex(DyeableComponent.TINT_INDEX)
                .end()

                .face(Direction.SOUTH)
                    .uvs(0F, 15F, 16F, 16F)
                    .texture("#wool_tint")
                    .cullface(Direction.SOUTH)
                    .tintindex(DyeableComponent.TINT_INDEX)
                .end()

                .face(Direction.WEST)
                    .uvs(0F, 15F, 16F, 16F)
                    .texture("#wool_tint")
                    .cullface(Direction.WEST)
                    .tintindex(DyeableComponent.TINT_INDEX)
                .end()

                .face(Direction.EAST)
                    .uvs(0F, 15F, 16F, 16F)
                    .texture("#wool_tint")
                    .cullface(Direction.EAST)
                    .tintindex(DyeableComponent.TINT_INDEX)
                .end()
            .end()
    );

    public BlockStateGenerator(GatherDataEvent event, PackOutput packOutput)
    {
        super(packOutput, FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerStatesAndModels()
    {
        facingBlock(FantasyFurniture.FURNITURE_STATION_BLOCK);

        nordic();
        venthyr();
        dunmer();
        boneWither();
        boneSkeleton();
        necrolord();
        royal();
    }

    private void nordic()
    {
        simpleBlock(NordicSet.WOOL);
        carpet(NordicSet.CARPET, NordicSet.WOOL);
        wallLight(NordicSet.WALL_LIGHT);
        simpleExistingBlock(NordicSet.FLOOR_LIGHT);
        facingBlock(NordicSet.TABLE_LARGE);
        facingBlock(NordicSet.TABLE_SMALL);
        facingBlock(NordicSet.TABLE_WIDE);
        facingBlock(NordicSet.BENCH);
        facingBlock(NordicSet.CHAIR);
        simpleExistingBlock(NordicSet.CEILING_LIGHT);
        facingBlock(NordicSet.CUSHION);
        facingBlock(NordicSet.STOOL);
        facingBlock(NordicSet.CHEST);
        facingBlock(NordicSet.BOOKSHELF);
        facingBlock(NordicSet.DESK_LEFT);
        facingBlock(NordicSet.DESK_RIGHT);
        facingBlock(NordicSet.DRAWER);
        facingBlock(NordicSet.DRESSER);
        facingBlock(NordicSet.LOCKBOX);
        facingBlock(NordicSet.WARDROBE_BOTTOM);
        facingBlock(NordicSet.WARDROBE_TOP);
        facingBlock(NordicSet.PAINTING_WIDE);
        facingBlock(NordicSet.PAINTING_SMALL);
        ovenBlock(NordicSet.OVEN);
        doorBlock(NordicSet.DOOR_DOUBLE);
        doorBlock(NordicSet.DOOR_SINGLE);
        facingBlock(NordicSet.BED_SINGLE);
        facingBlock(NordicSet.BED_DOUBLE);
        shelfBlock(NordicSet.SHELF);
        sofaBlock(NordicSet.SOFA);
        counterBlock(NordicSet.COUNTER);
    }

    private void venthyr()
    {
        simpleBlock(VenthyrSet.WOOL);
        carpet(VenthyrSet.CARPET, VenthyrSet.WOOL);
        wallLight(VenthyrSet.WALL_LIGHT);
        simpleExistingBlock(VenthyrSet.FLOOR_LIGHT);
        facingBlock(VenthyrSet.TABLE_LARGE);
        facingBlock(VenthyrSet.TABLE_LARGE_FANCY);
        facingBlock(VenthyrSet.TABLE_SMALL);
        facingBlock(VenthyrSet.TABLE_SMALL_FANCY);
        facingBlock(VenthyrSet.TABLE_WIDE);
        facingBlock(VenthyrSet.TABLE_WIDE_FANCY);
        facingBlock(VenthyrSet.BENCH);
        facingBlock(VenthyrSet.CHAIR);
        simpleExistingBlock(VenthyrSet.CEILING_LIGHT);
        facingBlock(VenthyrSet.CUSHION);
        facingBlock(VenthyrSet.STOOL);
        facingBlock(VenthyrSet.CHEST);
        facingBlock(VenthyrSet.BOOKSHELF);
        facingBlock(VenthyrSet.DESK_LEFT);
        facingBlock(VenthyrSet.DESK_RIGHT);
        facingBlock(VenthyrSet.DRAWER);
        facingBlock(VenthyrSet.DRESSER);
        facingBlock(VenthyrSet.LOCKBOX);
        facingBlock(VenthyrSet.WARDROBE_BOTTOM);
        facingBlock(VenthyrSet.WARDROBE_TOP);
        facingBlock(VenthyrSet.PAINTING_WIDE);
        facingBlock(VenthyrSet.PAINTING_SMALL);
        ovenBlock(VenthyrSet.OVEN);
        doorBlock(VenthyrSet.DOOR_DOUBLE);
        doorBlock(VenthyrSet.DOOR_SINGLE);
        facingBlock(VenthyrSet.BED_SINGLE);
        facingBlock(VenthyrSet.BED_DOUBLE);
        shelfBlock(VenthyrSet.SHELF);
        sofaBlock(VenthyrSet.SOFA);
        counterBlock(VenthyrSet.COUNTER);
    }

    private void dunmer()
    {
        simpleBlock(DunmerSet.WOOL);
        carpet(DunmerSet.CARPET, DunmerSet.WOOL);
        wallLight(DunmerSet.WALL_LIGHT);
        simpleExistingBlock(DunmerSet.FLOOR_LIGHT);
        facingBlock(DunmerSet.TABLE_LARGE);
        facingBlock(DunmerSet.TABLE_SMALL);
        facingBlock(DunmerSet.TABLE_WIDE);
        facingBlock(DunmerSet.BENCH);
        facingBlock(DunmerSet.CHAIR);
        simpleExistingBlock(DunmerSet.CEILING_LIGHT);
        facingBlock(DunmerSet.CUSHION);
        facingBlock(DunmerSet.STOOL);
        facingBlock(DunmerSet.CHEST);
        facingBlock(DunmerSet.BOOKSHELF);
        facingBlock(DunmerSet.DESK_LEFT);
        facingBlock(DunmerSet.DESK_RIGHT);
        facingBlock(DunmerSet.DRAWER);
        facingBlock(DunmerSet.DRESSER);
        facingBlock(DunmerSet.LOCKBOX);
        facingBlock(DunmerSet.WARDROBE_BOTTOM);
        facingBlock(DunmerSet.WARDROBE_TOP);
        facingBlock(DunmerSet.PAINTING_WIDE);
        facingBlock(DunmerSet.PAINTING_SMALL);
        ovenBlock(DunmerSet.OVEN);
        doorBlock(DunmerSet.DOOR_DOUBLE);
        doorBlock(DunmerSet.DOOR_SINGLE);
        facingBlock(DunmerSet.BED_SINGLE);
        facingBlock(DunmerSet.BED_DOUBLE);
        shelfBlock(DunmerSet.SHELF);
        sofaBlock(DunmerSet.SOFA);
        counterBlock(DunmerSet.COUNTER);
    }

    private void boneWither()
    {
        simpleBlock(BoneSet.Wither.WOOL);
        carpet(BoneSet.Wither.CARPET, BoneSet.Wither.WOOL);
        wallLight(BoneSet.Wither.WALL_LIGHT);
        facingBlock(BoneSet.Wither.FLOOR_LIGHT);
        facingBlock(BoneSet.Wither.TABLE_LARGE);
        facingBlock(BoneSet.Wither.TABLE_SMALL);
        facingBlock(BoneSet.Wither.TABLE_WIDE);
        facingBlock(BoneSet.Wither.BENCH);
        facingBlock(BoneSet.Wither.CHAIR);
        simpleExistingBlock(BoneSet.Wither.CEILING_LIGHT);
        facingBlock(BoneSet.Wither.CUSHION);
        facingBlock(BoneSet.Wither.STOOL);
        facingBlock(BoneSet.Wither.CHEST);
        facingBlock(BoneSet.Wither.BOOKSHELF);
        facingBlock(BoneSet.Wither.DESK_LEFT);
        facingBlock(BoneSet.Wither.DESK_RIGHT);
        facingBlock(BoneSet.Wither.DRAWER);
        facingBlock(BoneSet.Wither.DRESSER);
        facingBlock(BoneSet.Wither.LOCKBOX);
        facingBlock(BoneSet.Wither.WARDROBE_BOTTOM);
        facingBlock(BoneSet.Wither.WARDROBE_TOP);
        facingBlock(BoneSet.Wither.PAINTING_WIDE);
        facingBlock(BoneSet.Wither.PAINTING_SMALL);
        ovenBlock(BoneSet.Wither.OVEN);
        doorBlock(BoneSet.Wither.DOOR_DOUBLE);
        doorBlock(BoneSet.Wither.DOOR_SINGLE);
        facingBlock(BoneSet.Wither.BED_SINGLE);
        facingBlock(BoneSet.Wither.BED_DOUBLE);
        shelfBlock(BoneSet.Wither.SHELF);
        sofaBlock(BoneSet.Wither.SOFA);
        counterBlock(BoneSet.Wither.COUNTER);
    }

    private void boneSkeleton()
    {
        simpleBlock(BoneSet.Skeleton.WOOL);
        carpet(BoneSet.Skeleton.CARPET, BoneSet.Skeleton.WOOL);
        wallLight(BoneSet.Skeleton.WALL_LIGHT);
        facingBlock(BoneSet.Skeleton.FLOOR_LIGHT);
        facingBlock(BoneSet.Skeleton.TABLE_LARGE);
        facingBlock(BoneSet.Skeleton.TABLE_SMALL);
        facingBlock(BoneSet.Skeleton.TABLE_WIDE);
        facingBlock(BoneSet.Skeleton.BENCH);
        facingBlock(BoneSet.Skeleton.CHAIR);
        simpleExistingBlock(BoneSet.Skeleton.CEILING_LIGHT);
        facingBlock(BoneSet.Skeleton.CUSHION);
        facingBlock(BoneSet.Skeleton.STOOL);
        facingBlock(BoneSet.Skeleton.CHEST);
        facingBlock(BoneSet.Skeleton.BOOKSHELF);
        facingBlock(BoneSet.Skeleton.DESK_LEFT);
        facingBlock(BoneSet.Skeleton.DESK_RIGHT);
        facingBlock(BoneSet.Skeleton.DRAWER);
        facingBlock(BoneSet.Skeleton.DRESSER);
        facingBlock(BoneSet.Skeleton.LOCKBOX);
        facingBlock(BoneSet.Skeleton.WARDROBE_BOTTOM);
        facingBlock(BoneSet.Skeleton.WARDROBE_TOP);
        facingBlock(BoneSet.Skeleton.PAINTING_WIDE);
        facingBlock(BoneSet.Skeleton.PAINTING_SMALL);
        ovenBlock(BoneSet.Skeleton.OVEN);
        doorBlock(BoneSet.Skeleton.DOOR_DOUBLE);
        doorBlock(BoneSet.Skeleton.DOOR_SINGLE);
        facingBlock(BoneSet.Skeleton.BED_SINGLE);
        facingBlock(BoneSet.Skeleton.BED_DOUBLE);
        shelfBlock(BoneSet.Skeleton.SHELF);
        sofaBlock(BoneSet.Skeleton.SOFA);
        counterBlock(BoneSet.Skeleton.COUNTER);
    }

    private void necrolord()
    {
        simpleBlock(NecrolordSet.WOOL);
        carpet(NecrolordSet.CARPET, NecrolordSet.WOOL);
        wallLight(NecrolordSet.WALL_LIGHT);
        facingBlock(NecrolordSet.FLOOR_LIGHT);
        facingBlock(NecrolordSet.TABLE_LARGE);
        facingBlock(NecrolordSet.TABLE_SMALL);
        facingBlock(NecrolordSet.TABLE_WIDE);
        facingBlock(NecrolordSet.BENCH);
        facingBlock(NecrolordSet.CHAIR);
        simpleExistingBlock(NecrolordSet.CEILING_LIGHT);
        facingBlock(NecrolordSet.CUSHION);
        facingBlock(NecrolordSet.STOOL);
        facingBlock(NecrolordSet.CHEST);
        facingBlock(NecrolordSet.BOOKSHELF);
        facingBlock(NecrolordSet.DESK_LEFT);
        facingBlock(NecrolordSet.DESK_RIGHT);
        facingBlock(NecrolordSet.DRAWER);
        facingBlock(NecrolordSet.DRESSER);
        facingBlock(NecrolordSet.LOCKBOX);
        facingBlock(NecrolordSet.WARDROBE_BOTTOM);
        facingBlock(NecrolordSet.WARDROBE_TOP);
        facingBlock(NecrolordSet.PAINTING_WIDE);
        facingBlock(NecrolordSet.PAINTING_SMALL);
        ovenBlock(NecrolordSet.OVEN);
        doorBlock(NecrolordSet.DOOR_DOUBLE);
        doorBlock(NecrolordSet.DOOR_SINGLE);
        facingBlock(NecrolordSet.BED_SINGLE);
        facingBlock(NecrolordSet.BED_DOUBLE);
        shelfBlock(NecrolordSet.SHELF);
        sofaBlock(NecrolordSet.SOFA);
        counterBlock(NecrolordSet.COUNTER);
    }

    private void royal()
    {
        simpleTintedBlock(RoyalSet.WOOL);
        carpetTinted(RoyalSet.CARPET, RoyalSet.WOOL);
        wallLight(RoyalSet.WALL_LIGHT);
        facingBlock(RoyalSet.FLOOR_LIGHT);
        facingBlock(RoyalSet.TABLE_LARGE);
        facingBlock(RoyalSet.TABLE_SMALL);
        facingBlock(RoyalSet.TABLE_WIDE);
        facingBlock(RoyalSet.BENCH);
        facingBlock(RoyalSet.CHAIR);
        simpleExistingBlock(RoyalSet.CEILING_LIGHT);
        facingBlock(RoyalSet.CUSHION);
        facingBlock(RoyalSet.STOOL);
        facingBlock(RoyalSet.CHEST);
        facingBlock(RoyalSet.BOOKSHELF);
        facingBlock(RoyalSet.DESK_LEFT);
        facingBlock(RoyalSet.DESK_RIGHT);
        facingBlock(RoyalSet.DRAWER);
        facingBlock(RoyalSet.DRESSER);
        facingBlock(RoyalSet.LOCKBOX);
        facingBlock(RoyalSet.WARDROBE_BOTTOM);
        facingBlock(RoyalSet.WARDROBE_TOP);
        facingBlock(RoyalSet.PAINTING_WIDE);
        facingBlock(RoyalSet.PAINTING_SMALL);
        ovenBlock(RoyalSet.OVEN);
        doorBlock(RoyalSet.DOOR_DOUBLE);
        doorBlock(RoyalSet.DOOR_SINGLE);
        facingBlock(RoyalSet.BED_SINGLE);
        facingBlock(RoyalSet.BED_DOUBLE);
        shelfBlock(RoyalSet.SHELF);
        sofaBlock(RoyalSet.SOFA);
        counterBlock(RoyalSet.COUNTER);
    }

    private Property<?>[] gatherIgnoredProperties(Supplier<? extends Block> entry)
    {
        var ignoredProperties = Lists.<Property<?>>newArrayList();

        addIgnoredComponentProperty(ignoredProperties, entry, ComponentTypes.MULTI_BLOCK, component -> component.getMultiBlockType().getBlockProperty());
        addIgnoredComponentProperty(ignoredProperties, entry, ComponentTypes.DOOR, component -> DoorComponent.POWERED, component -> DoorComponent.HALF);

        // TODO
        // if(block instanceof SimpleWaterloggedBlock) ignoredProperties.add(BlockStateProperties.WATERLOGGED);

        return ignoredProperties.toArray(Property<?>[]::new);
    }

    @SafeVarargs
    private <T extends Component> void addIgnoredComponentProperty(List<Property<?>> ignoredProperties, Supplier<? extends Block> entry, ComponentType<T> componentType, Function<T, Property<?>>... propertyGetters)
    {
        if(!(entry.get() instanceof ComponentBlock block)) return;

        block.getOptionalComponent(componentType).ifPresent(component -> {
            for(var propertyGetter : propertyGetters)
            {
                var property = propertyGetter.apply(component);
                if(property != null) ignoredProperties.add(property);
            }
        });
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

    private void simpleExistingBlock(Supplier<? extends Block> entry)
    {
        simpleBlock(entry, existingModel(entry));
    }

    private void facingBlock(Supplier<? extends Block> entry)
    {
        complexBlock(entry, (blockState, model) -> ConfiguredModel
                .builder()
                .rotationY((int) blockState.getValue(HorizontalFacingComponent.FACING).getOpposite().toYRot() % 360)
                .modelFile(model)
                .build()
        );
    }

    private void ovenBlock(Supplier<? extends Block> entry)
    {
        getVariantBuilder(entry.get()).forAllStatesExcept(blockState -> {
            var isLit = blockState.getValue(OvenComponent.LIT);
            var modelPath = blockFolder(entry);
            if(isLit) modelPath = modelPath.withPath("%s_lit"::formatted);

            return ConfiguredModel
                    .builder()
                    .rotationY((int) blockState.getValue(HorizontalFacingComponent.FACING).getOpposite().toYRot() % 360)
                    .modelFile(models().getExistingFile(modelPath))
                    .build();
        }, gatherIgnoredProperties(entry));
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
                    .rotationY((int) blockState.getValue(HorizontalFacingComponent.FACING).getOpposite().toYRot() % 360)
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
                    .rotationY((int) blockState.getValue(HorizontalFacingComponent.FACING).getOpposite().toYRot() % 360)
                    .modelFile(model)
                    .build();
        }, gatherIgnoredProperties(entry));
    }

    private void counterBlock(Supplier<? extends Block> entry)
    {
        getVariantBuilder(entry.get()).forAllStatesExcept(blockState -> {
            var counterType = blockState.getValue(ModBlockStateProperties.COUNTER_TYPE);

            var model = models().getExistingFile(blockFolder(entry)
                    .withPath(path -> "%s_%s".formatted(path, counterType.getSerializedName()))
            );

            return ConfiguredModel
                    .builder()
                    .rotationY((int) blockState.getValue(HorizontalFacingComponent.FACING).getOpposite().toYRot() % 360)
                    .modelFile(model)
                    .build();
        }, gatherIgnoredProperties(entry));
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

    private void simpleTintedBlock(Supplier<? extends Block> entry)
    {
        var path = blockFolder(entry);
        simpleBlock(entry, models()
                .getBuilder(path.toString())
                .parent(tintedCubeAll.get())
                .texture("all", path)
                .texture("all_tint", path.withPath("%s_tint"::formatted))
        );
    }

    private void carpet(Supplier<? extends Block> carpet, Supplier<? extends Block> wool)
    {
        simpleBlock(carpet, models().carpet(blockFolder(carpet).toString(), blockFolder(wool)));
    }

    private void carpetTinted(Supplier<? extends Block> carpet, Supplier<? extends Block> wool)
    {
        var woolPath = blockFolder(wool);
        simpleBlock(carpet, models()
                .getBuilder(blockFolder(carpet).toString())
                .parent(tintedCarpet.get())
                .texture("wool", woolPath)
                .texture("wool_tint", woolPath.withPath("%s_tint"::formatted))
        );
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
