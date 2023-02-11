package xyz.apex.minecraft.fantasyfurniture.forge;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.data.event.GatherDataEvent;

import xyz.apex.minecraft.apexcore.forge.data.BlockBenchModelConverter;
import xyz.apex.minecraft.apexcore.forge.data.BlockBenchModelDeserializer;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.CounterType;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ShelfType;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.SofaType;
import xyz.apex.minecraft.fantasyfurniture.common.init.NordicSet;

import java.util.Arrays;

public final class BlockBenchConverter extends BlockBenchModelConverter
{
    private final ResourceLocation renderTypeCutout = new ResourceLocation("minecraft", "cutout");
    private final ModelFile modelBlockBlock = new ModelFile.ExistingModelFile(new ResourceLocation("minecraft", "block/block"), existingFileHelper);

    public BlockBenchConverter(GatherDataEvent event)
    {
        super(event, FantasyFurniture.ID);

        // we dont care about textures, we specify our own during conversion
        // and existing values could potentially throw errors if
        // pointing to none existent files
        BlockBenchModelDeserializer.PARSE_TEXTURES = false;
    }

    @Override
    protected void convertModels()
    {
        convertBasic("furniture_station");

        convertFurnitureSet(NordicSet.NAME);
    }

    private void convertFurnitureSet(String furnitureSet)
    {
        convertFurniture(furnitureSet, "wall_light").renderType(renderTypeCutout);
        convertFurniture(furnitureSet, "floor_light");
        convertFurniture(furnitureSet, "table_small");
        convertFurniture(furnitureSet, "table_large");
        convertFurniture(furnitureSet, "table_wide").renderType(renderTypeCutout);
        convertFurniture(furnitureSet, "bench");
        convertFurniture(furnitureSet, "chair").renderType(renderTypeCutout);
        convertFurniture(furnitureSet, "chandelier");
        convertFurniture(furnitureSet, "cushion");
        convertFurniture(furnitureSet, "stool");
        convertFurniture(furnitureSet, "chest");
        convertFurniture(furnitureSet, "bookshelf");
        convertDesk(furnitureSet, "left");
        convertDesk(furnitureSet, "right");
        convertFurniture(furnitureSet, "drawer");
        convertFurniture(furnitureSet, "dresser");
        convertFurniture(furnitureSet, "lockbox");
        convertWardrobe(furnitureSet, "bottom");
        convertWardrobe(furnitureSet, "top").renderType(renderTypeCutout);
        convertFurniture(furnitureSet, "painting_wide");
        convertFurniture(furnitureSet, "painting_small");
        convertFurniture(furnitureSet, "oven").renderType(renderTypeCutout);
        convertDoor(furnitureSet, "double", true);
        convertDoor(furnitureSet, "double", false);
        convertDoor(furnitureSet, "single", true);
        convertDoor(furnitureSet, "single", false);
        convertFurniture(furnitureSet, "bed_single");
        convertFurniture(furnitureSet, "bed_double");
        Arrays.stream(ShelfType.values()).forEach(shelfType -> convertShelf(furnitureSet, shelfType));
        Arrays.stream(SofaType.values()).forEach(sofaType -> convertSofa(furnitureSet, sofaType));
        Arrays.stream(CounterType.values()).forEach(counterType -> convertCounter(furnitureSet, counterType));
    }

    private BlockModelBuilder convertDesk(String furnitureSet, String type)
    {
        return convertFurnitureEx(
                furnitureSet,
                "desk_%s".formatted(type),
                "desk"
        ).renderType(renderTypeCutout);
    }

    private BlockModelBuilder convertWardrobe(String furnitureSet, String type)
    {
        return convertFurnitureEx(
                furnitureSet,
                "wardrobe_%s".formatted(type),
                "wardrobe"
        );
    }

    private BlockModelBuilder convertDoor(String furnitureSet, String doorType, boolean isLeft)
    {
        return convertFurnitureEx(
                furnitureSet,
                "door_%s_%s".formatted(doorType, isLeft ? "left" : "right"),
                "door_%s".formatted(doorType)
        );
    }

    private BlockModelBuilder convertShelf(String furnitureSet, ShelfType shelfType)
    {
        return convertFurnitureEx(
                furnitureSet,
                "shelf_%s".formatted(shelfType.getSerializedName()),
                "shelf"
        ).renderType(renderTypeCutout);
    }

    private BlockModelBuilder convertSofa(String furnitureSet, SofaType sofaType)
    {
        return convertFurnitureEx(
                furnitureSet,
                "sofa_%s".formatted(sofaType.getSerializedName()),
                "sofa"
        );
    }

    private BlockModelBuilder convertCounter(String furnitureSet, CounterType counterType)
    {
        return convertFurnitureEx(
                furnitureSet,
                "counter_%s".formatted(counterType.getSerializedName()),
                "counter"
        );
    }

    private BlockModelBuilder convertBasic(String blockName)
    {
        return convert(
                new ResourceLocation(modId, "block/%s".formatted(blockName)), // ${modId}:block/${blockName}
                new ResourceLocation(modId, "block/%s/particle".formatted(blockName)), // ${modId}:block/${blockName}/particle
                blockName,
                new ResourceLocation(modId, "block/%s/block".formatted(blockName)) // ${modId}:block/${blockName}/block
        );
    }

    private BlockModelBuilder convertFurniture(String furnitureSet, String furnitureType)
    {
        return convertFurnitureEx(furnitureSet, furnitureType, furnitureType);
    }

    private BlockModelBuilder convertFurnitureEx(String furnitureSet, String furnitureTypeFull, String furnitureTypeShort)
    {
        return convert(
                new ResourceLocation(modId, "block/%s/%s".formatted(furnitureSet, furnitureTypeFull)), // ${modId}:block/${furnitureSet}/${furnitureType}
                new ResourceLocation(modId, "block/%s/particle".formatted(furnitureSet)), // ${modId}:block/${furnitureSet}/particle
                furnitureTypeShort,
                new ResourceLocation(modId, "block/%s/%s".formatted(furnitureSet, furnitureTypeShort)) // ${modId}:block/${furnitureSet}/${furnitureType}
        );
    }

    private BlockModelBuilder convert(ResourceLocation model, ResourceLocation particle, String textureKey, ResourceLocation texturePath)
    {
        return blockModelBuilder(model)
                .parent(modelBlockBlock)
                .texture("particle", particle)
                .texture(textureKey, texturePath)
        ;
    }
}
