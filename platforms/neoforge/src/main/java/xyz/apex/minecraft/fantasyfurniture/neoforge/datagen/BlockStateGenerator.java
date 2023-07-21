package xyz.apex.minecraft.fantasyfurniture.neoforge.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.feature.station.FurnitureStation;

import java.util.function.Function;

@ApiStatus.Internal
final class BlockStateGenerator extends BlockStateProvider
{
    private static final int DEFAULT_ANGLE_OFFSET = 180; // from super

    BlockStateGenerator(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, FantasyFurniture.ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        furnitureStation();
    }

    private void furnitureStation()
    {
        var registryName = FurnitureStation.BLOCK.getRegistryName();
        var modelPath = registryName.withPrefix("block/");
        var model = models().getExistingFile(modelPath);
        var block = FurnitureStation.BLOCK.get();

        horizontalBlock(block, model, BlockStateProperties.WATERLOGGED);
        simpleBlockItem(block, model);
    }

    private void horizontalBlock(Block block, ModelFile model, Property<?>... ignoredProperties)
    {
        horizontalBlock(block, model, DEFAULT_ANGLE_OFFSET, ignoredProperties);
    }

    private void horizontalBlock(Block block, ModelFile model, int angleOffset, Property<?>... ignoredProperties)
    {
        horizontalBlock(block, blockState -> model, angleOffset, ignoredProperties);
    }

    private void horizontalBlock(Block block, Function<BlockState, ModelFile> modelFunc, Property<?>... ignoredProperties)
    {
        horizontalBlock(block, modelFunc, DEFAULT_ANGLE_OFFSET, ignoredProperties);
    }

    private void horizontalBlock(Block block, Function<BlockState, ModelFile> modelFunc, int angleOffset, Property<?>... ignoredProperties)
    {
        getVariantBuilder(block)
                .forAllStatesExcept(blockState -> ConfiguredModel.builder()
                        .modelFile(modelFunc.apply(blockState))
                        .rotationY(((int) blockState.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + angleOffset) % 360)
                        .build(),
                        ignoredProperties
                );
    }
}
