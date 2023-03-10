package xyz.apex.minecraft.fantasyfurniture.forge;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.Validate;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlock;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.OvenBlock;
import xyz.apex.minecraft.fantasyfurniture.common.block.ShelfBlock;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ModBlockStateProperties;
import xyz.apex.minecraft.fantasyfurniture.common.init.DunmerSet;
import xyz.apex.minecraft.fantasyfurniture.common.init.NordicSet;
import xyz.apex.minecraft.fantasyfurniture.common.init.VenthyrSet;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public final class BlockStateGenerator extends BlockStateProvider
{
    public BlockStateGenerator(GatherDataEvent event, PackOutput packOutput)
    {
        super(packOutput, FantasyFurniture.ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerStatesAndModels()
    {
        facingBlock(FantasyFurniture.FURNITURE_STATION_BLOCK, HorizontalDirectionalBlock.FACING);

        nordic();
        venthyr();
        dunmer();
    }

    private void nordic()
    {
        simpleBlock(NordicSet.WOOL);
        carpet(NordicSet.CARPET, NordicSet.WOOL);
        wallLight(NordicSet.WALL_LIGHT);
        simpleExistingBlock(NordicSet.FLOOR_LIGHT);
        facingBlock(NordicSet.TABLE_LARGE, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.TABLE_SMALL, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.TABLE_WIDE, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.BENCH, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.CHAIR, HorizontalDirectionalBlock.FACING);
        simpleExistingBlock(NordicSet.CHANDELIER);
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
        ovenBlock(NordicSet.OVEN);
        doorBlock(NordicSet.DOOR_DOUBLE);
        doorBlock(NordicSet.DOOR_SINGLE);
        facingBlock(NordicSet.BED_SINGLE, HorizontalDirectionalBlock.FACING);
        facingBlock(NordicSet.BED_DOUBLE, HorizontalDirectionalBlock.FACING);
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
        facingBlock(VenthyrSet.TABLE_LARGE, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.TABLE_LARGE_FANCY, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.TABLE_SMALL, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.TABLE_SMALL_FANCY, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.TABLE_WIDE, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.TABLE_WIDE_FANCY, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.BENCH, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.CHAIR, HorizontalDirectionalBlock.FACING);
        simpleExistingBlock(VenthyrSet.CHANDELIER);
        facingBlock(VenthyrSet.CUSHION, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.STOOL, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.CHEST, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.BOOKSHELF, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.DESK_LEFT, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.DESK_RIGHT, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.DRAWER, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.DRESSER, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.LOCKBOX, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.WARDROBE_BOTTOM, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.WARDROBE_TOP, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.PAINTING_WIDE, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.PAINTING_SMALL, HorizontalDirectionalBlock.FACING);
        ovenBlock(VenthyrSet.OVEN);
        doorBlock(VenthyrSet.DOOR_DOUBLE);
        doorBlock(VenthyrSet.DOOR_SINGLE);
        facingBlock(VenthyrSet.BED_SINGLE, HorizontalDirectionalBlock.FACING);
        facingBlock(VenthyrSet.BED_DOUBLE, HorizontalDirectionalBlock.FACING);
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
        facingBlock(DunmerSet.TABLE_LARGE, HorizontalDirectionalBlock.FACING);
        facingBlock(DunmerSet.TABLE_SMALL, HorizontalDirectionalBlock.FACING);
        facingBlock(DunmerSet.TABLE_WIDE, HorizontalDirectionalBlock.FACING);
        facingBlock(DunmerSet.BENCH, HorizontalDirectionalBlock.FACING);
        facingBlock(DunmerSet.CHAIR, HorizontalDirectionalBlock.FACING);
        simpleExistingBlock(DunmerSet.CHANDELIER);
        facingBlock(DunmerSet.CUSHION, HorizontalDirectionalBlock.FACING);
        facingBlock(DunmerSet.STOOL, HorizontalDirectionalBlock.FACING);
        facingBlock(DunmerSet.CHEST, HorizontalDirectionalBlock.FACING);
        facingBlock(DunmerSet.BOOKSHELF, HorizontalDirectionalBlock.FACING);
        facingBlock(DunmerSet.DESK_LEFT, HorizontalDirectionalBlock.FACING);
        facingBlock(DunmerSet.DESK_RIGHT, HorizontalDirectionalBlock.FACING);
        facingBlock(DunmerSet.DRAWER, HorizontalDirectionalBlock.FACING);
        facingBlock(DunmerSet.DRESSER, HorizontalDirectionalBlock.FACING);
        facingBlock(DunmerSet.LOCKBOX, HorizontalDirectionalBlock.FACING);
        facingBlock(DunmerSet.WARDROBE_BOTTOM, HorizontalDirectionalBlock.FACING);
        facingBlock(DunmerSet.WARDROBE_TOP, HorizontalDirectionalBlock.FACING);
        facingBlock(DunmerSet.PAINTING_WIDE, HorizontalDirectionalBlock.FACING);
        facingBlock(DunmerSet.PAINTING_SMALL, HorizontalDirectionalBlock.FACING);
        ovenBlock(DunmerSet.OVEN);
        doorBlock(DunmerSet.DOOR_DOUBLE);
        doorBlock(DunmerSet.DOOR_SINGLE);
        facingBlock(DunmerSet.BED_SINGLE, HorizontalDirectionalBlock.FACING);
        facingBlock(DunmerSet.BED_DOUBLE, HorizontalDirectionalBlock.FACING);
        shelfBlock(DunmerSet.SHELF);
        sofaBlock(DunmerSet.SOFA);
        counterBlock(DunmerSet.COUNTER);
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

    private void simpleExistingBlock(Supplier<? extends Block> entry)
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

    private void ovenBlock(Supplier<? extends Block> entry)
    {
        getVariantBuilder(entry.get()).forAllStatesExcept(blockState -> {
            var isLit = blockState.getValue(OvenBlock.LIT);
            var modelPath = blockFolder(entry);
            if(isLit) modelPath = modelPath.withPath("%s_lit"::formatted);

            return ConfiguredModel
                    .builder()
                    .rotationY((int) blockState.getValue(ShelfBlock.FACING).getOpposite().toYRot() % 360)
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

    private void counterBlock(Supplier<? extends Block> entry)
    {
        getVariantBuilder(entry.get()).forAllStatesExcept(blockState -> {
            var counterType = blockState.getValue(ModBlockStateProperties.COUNTER_TYPE);

            var model = models().getExistingFile(blockFolder(entry)
                    .withPath(path -> "%s_%s".formatted(path, counterType.getSerializedName()))
            );

            return ConfiguredModel
                    .builder()
                    .rotationY((int) blockState.getValue(ShelfBlock.FACING).getOpposite().toYRot() % 360)
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
