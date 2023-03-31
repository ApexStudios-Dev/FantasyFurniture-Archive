package xyz.apex.minecraft.fantasyfurniture.forge.data.providers;

import com.google.common.collect.Lists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.Validate;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponent;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentType;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.block.types.BedBlockComponent;
import xyz.apex.minecraft.apexcore.common.component.block.types.DoorBlockComponent;
import xyz.apex.minecraft.apexcore.common.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.OvenBlock;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ModBlockStateProperties;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class BlockStateProvider extends net.minecraftforge.client.model.generators.BlockStateProvider
{
    public static final ResourceLocation RENDER_TYPE_CUTOUT_NAME = new ResourceLocation("minecraft", "cutout");
    public static final ResourceLocation TINTED_CUBE_NAME = new ResourceLocation(FantasyFurniture.ID, "block/tinted_cube");
    public static final ResourceLocation TINTED_CUBE_ALL_NAME = new ResourceLocation(FantasyFurniture.ID, "block/tinted_cube_all");
    public static final ResourceLocation TINTED_CARPET_NAME = new ResourceLocation(FantasyFurniture.ID, "block/tinted_carpet");

    protected BlockStateProvider(GatherDataEvent event)
    {
        super(event.getGenerator().getPackOutput(), event.getModContainer().getModId(), event.getExistingFileHelper());
    }

    protected Property<?>[] gatherIgnoredProperties(Supplier<? extends Block> entry)
    {
        var ignoredProperties = Lists.<Property<?>>newArrayList();

        addIgnoredComponentProperty(ignoredProperties, entry, BlockComponentTypes.MULTI_BLOCK, component -> component.getMultiBlockType().getBlockProperty());
        addIgnoredComponentProperty(ignoredProperties, entry, BlockComponentTypes.DOOR, component -> DoorBlockComponent.POWERED, component -> DoorBlockComponent.HALF);
        addIgnoredComponentProperty(ignoredProperties, entry, BlockComponentTypes.BED, component -> BedBlockComponent.OCCUPIED, component -> BedBlockComponent.PART);

        // TODO
        // if(block instanceof SimpleWaterloggedBlock) ignoredProperties.add(BlockStateProperties.WATERLOGGED);

        return ignoredProperties.toArray(Property<?>[]::new);
    }

    @SafeVarargs
    protected final <T extends BlockComponent> void addIgnoredComponentProperty(List<Property<?>> ignoredProperties, Supplier<? extends Block> entry, BlockComponentType<T> componentType, Function<T, Property<?>>... propertyGetters)
    {
        if(!(entry.get() instanceof BlockComponentHolder holder)) return;

        holder.getOptionalComponent(componentType).ifPresent(component -> {
            for(var propertyGetter : propertyGetters)
            {
                var property = propertyGetter.apply(component);
                if(property != null) ignoredProperties.add(property);
            }
        });
    }

    protected void wallLight(Supplier<? extends Block> entry)
    {
        complexBlock(entry, (blockState, model) -> ConfiguredModel
                .builder()
                .rotationY((int) (blockState.getValue(WallTorchBlock.FACING).toYRot() + 180F) % 360)
                .modelFile(model)
                .build()
        );
    }

    protected void simpleExistingBlock(Supplier<? extends Block> entry)
    {
        simpleBlock(entry, existingModel(entry));
    }

    protected void facingBlock(Supplier<? extends Block> entry)
    {
        complexBlock(entry, (blockState, model) -> ConfiguredModel
                .builder()
                .rotationY((int) blockState.getValue(HorizontalFacingBlockComponent.FACING).getOpposite().toYRot() % 360)
                .modelFile(model)
                .build()
        );
    }

    protected void ovenBlock(Supplier<? extends Block> entry)
    {
        getVariantBuilder(entry.get()).forAllStatesExcept(blockState -> {
            var isLit = blockState.getValue(OvenBlock.LIT);
            var modelPath = blockFolder(entry);
            if(isLit) modelPath = modelPath.withPath("%s_lit"::formatted);

            return ConfiguredModel
                    .builder()
                    .rotationY((int) blockState.getValue(HorizontalFacingBlockComponent.FACING).getOpposite().toYRot() % 360)
                    .modelFile(models().getExistingFile(modelPath))
                    .build();
        }, gatherIgnoredProperties(entry));
    }

    protected void doorBlock(Supplier<? extends Block> entry)
    {
        var leftModel = models().getExistingFile(blockFolder(entry).withPath(path -> path + "_left"));
        var rightModel = models().getExistingFile(blockFolder(entry).withPath(path -> path + "_right"));

        getVariantBuilder(entry.get()).forAllStatesExcept(blockState -> {
            var open = blockState.getValue(DoorBlock.OPEN);
            var right = blockState.getValue(DoorBlock.HINGE) == DoorHingeSide.RIGHT;

            int yRot = (int) blockState.getValue(DoorBlock.FACING).getOpposite().toYRot();
            if(open) yRot = yRot + (right ? -90 : 90);
            yRot %= 360;

            var model = open ? (right ? leftModel : rightModel) : (right ? rightModel : leftModel);
            return ConfiguredModel.builder().modelFile(model).rotationY(yRot).build();
        }, gatherIgnoredProperties(entry));
    }

    protected void complexBlock(Supplier<? extends Block> entry, BiFunction<BlockState, ModelFile.ExistingModelFile, ConfiguredModel[]> models)
    {
        var modelFile = existingModel(entry);
        getVariantBuilder(entry.get()).forAllStatesExcept(blockState -> models.apply(blockState, modelFile), gatherIgnoredProperties(entry));
    }

    protected void shelfBlock(Supplier<? extends Block> entry)
    {
        getVariantBuilder(entry.get()).forAllStatesExcept(blockState -> {
            var shelfType = blockState.getValue(ModBlockStateProperties.SHELF_TYPE);

            var model = models().getExistingFile(blockFolder(entry)
                    .withPath(path -> "%s_%s".formatted(path, shelfType.getSerializedName()))
            );

            return ConfiguredModel
                    .builder()
                    .rotationY((int) blockState.getValue(HorizontalFacingBlockComponent.FACING).getOpposite().toYRot() % 360)
                    .modelFile(model)
                    .build();
        }, gatherIgnoredProperties(entry));
    }

    protected void sofaBlock(Supplier<? extends Block> entry)
    {
        getVariantBuilder(entry.get()).forAllStatesExcept(blockState -> {
            var sofaType = blockState.getValue(ModBlockStateProperties.SOFA_TYPE);

            var model = models().getExistingFile(blockFolder(entry)
                    .withPath(path -> "%s_%s".formatted(path, sofaType.getSerializedName()))
            );

            return ConfiguredModel
                    .builder()
                    .rotationY((int) blockState.getValue(HorizontalFacingBlockComponent.FACING).getOpposite().toYRot() % 360)
                    .modelFile(model)
                    .build();
        }, gatherIgnoredProperties(entry));
    }

    protected void counterBlock(Supplier<? extends Block> entry)
    {
        getVariantBuilder(entry.get()).forAllStatesExcept(blockState -> {
            var counterType = blockState.getValue(ModBlockStateProperties.COUNTER_TYPE);

            var model = models().getExistingFile(blockFolder(entry)
                    .withPath(path -> "%s_%s".formatted(path, counterType.getSerializedName()))
            );

            return ConfiguredModel
                    .builder()
                    .rotationY((int) blockState.getValue(HorizontalFacingBlockComponent.FACING).getOpposite().toYRot() % 360)
                    .modelFile(model)
                    .build();
        }, gatherIgnoredProperties(entry));
    }

    protected void simpleBlock(Supplier<? extends Block> entry)
    {
        var path = blockFolder(entry);
        simpleBlock(entry, models().cubeAll(path.toString(), path));
    }

    protected void simpleBlock(Supplier<? extends Block> entry, ModelFile model)
    {
        getVariantBuilder(entry.get()).partialState().setModels(new ConfiguredModel(model));
    }

    protected void simpleTintedBlock(Supplier<? extends Block> entry)
    {
        var path = blockFolder(entry);
        simpleBlock(entry, models()
                .getBuilder(path.toString())
                .parent(new ModelFile.UncheckedModelFile(TINTED_CUBE_ALL_NAME))
                .texture("all", path)
                .texture("all_tint", path.withPath("%s_tint"::formatted))
        );
    }

    protected void carpet(Supplier<? extends Block> carpet, Supplier<? extends Block> wool)
    {
        simpleBlock(carpet, models().carpet(blockFolder(carpet).toString(), blockFolder(wool)));
    }

    protected void carpetTinted(Supplier<? extends Block> carpet, Supplier<? extends Block> wool)
    {
        var woolPath = blockFolder(wool);
        simpleBlock(carpet, models()
                .getBuilder(blockFolder(carpet).toString())
                .parent(new ModelFile.UncheckedModelFile(TINTED_CARPET_NAME))
                .texture("wool", woolPath)
                .texture("wool_tint", woolPath.withPath("%s_tint"::formatted))
        );
    }

    protected ResourceLocation blockFolder(Supplier<? extends Block> entry)
    {
        return nameWithPrefix(entry, ModelProvider.BLOCK_FOLDER);
    }

    protected ResourceLocation blockFolder(ResourceLocation name)
    {
        return withPrefix(name, ModelProvider.BLOCK_FOLDER);
    }

    protected ResourceLocation nameWithPrefix(Supplier<? extends Block> entry, String prefix)
    {
        var registryName = ForgeRegistries.BLOCKS.getKey(entry.get());
        Validate.notNull(registryName);
        return withPrefix(registryName, prefix);
    }

    protected ResourceLocation withPrefix(ResourceLocation name, String prefix)
    {
        var path = name.getPath();
        if(path.startsWith(prefix)) return name;
        return new ResourceLocation(name.getNamespace(), "%s/%s".formatted(prefix, path));
    }

    protected ModelFile.ExistingModelFile existingModel(Supplier<? extends Block> entry)
    {
        return models().getExistingFile(blockFolder(entry));
    }
}
