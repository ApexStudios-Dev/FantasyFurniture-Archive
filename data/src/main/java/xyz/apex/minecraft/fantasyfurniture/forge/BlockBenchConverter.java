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
import xyz.apex.minecraft.fantasyfurniture.common.init.BoneSet;
import xyz.apex.minecraft.fantasyfurniture.common.init.DunmerSet;
import xyz.apex.minecraft.fantasyfurniture.common.init.NordicSet;
import xyz.apex.minecraft.fantasyfurniture.common.init.VenthyrSet;

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

        nordic();
        venthyr();
        dunmer();
        boneWither();
        boneSkeleton();
    }

    private void nordic()
    {
        convertFurniture(NordicSet.NAME, "wall_light").renderType(renderTypeCutout);
        convertFurniture(NordicSet.NAME, "floor_light");
        convertFurniture(NordicSet.NAME, "table_small");
        convertFurniture(NordicSet.NAME, "table_large");
        convertFurniture(NordicSet.NAME, "table_wide").renderType(renderTypeCutout);
        convertFurniture(NordicSet.NAME, "bench");
        convertFurniture(NordicSet.NAME, "chair").renderType(renderTypeCutout);
        convertFurniture(NordicSet.NAME, "chandelier");
        convertFurniture(NordicSet.NAME, "cushion");
        convertFurniture(NordicSet.NAME, "stool");
        convertFurniture(NordicSet.NAME, "chest");
        convertFurniture(NordicSet.NAME, "bookshelf");
        convertDesk(NordicSet.NAME, "left").renderType(renderTypeCutout);
        convertDesk(NordicSet.NAME, "right").renderType(renderTypeCutout);
        convertFurniture(NordicSet.NAME, "drawer");
        convertFurniture(NordicSet.NAME, "dresser");
        convertFurniture(NordicSet.NAME, "lockbox");
        convertWardrobe(NordicSet.NAME, "bottom");
        convertWardrobe(NordicSet.NAME, "top").renderType(renderTypeCutout);
        convertFurniture(NordicSet.NAME, "painting_wide");
        convertFurniture(NordicSet.NAME, "painting_small");
        convertFurniture(NordicSet.NAME, "oven").renderType(renderTypeCutout);
        convertFurnitureEx(NordicSet.NAME, "oven_lit", "oven").renderType(renderTypeCutout);
        convertDoor(NordicSet.NAME, "double", true);
        convertDoor(NordicSet.NAME, "double", false);
        convertDoor(NordicSet.NAME, "single", true);
        convertDoor(NordicSet.NAME, "single", false);
        convertFurniture(NordicSet.NAME, "bed_single");
        convertFurniture(NordicSet.NAME, "bed_double");
        Arrays.stream(ShelfType.values()).forEach(shelfType -> convertShelf(NordicSet.NAME, shelfType).renderType(renderTypeCutout));
        Arrays.stream(SofaType.values()).forEach(sofaType -> convertSofa(NordicSet.NAME, sofaType));
        Arrays.stream(CounterType.values()).forEach(counterType -> convertCounter(NordicSet.NAME, counterType));
    }

    private void venthyr()
    {
        convertFurniture(VenthyrSet.NAME, "wall_light");
        convertFurniture(VenthyrSet.NAME, "floor_light");
        convertFurniture(VenthyrSet.NAME, "table_small");
        convertFurniture(VenthyrSet.NAME, "table_small_fancy").renderType(renderTypeCutout);
        convertFurniture(VenthyrSet.NAME, "table_large");
        convertFurniture(VenthyrSet.NAME, "table_large_fancy").renderType(renderTypeCutout);
        convertFurniture(VenthyrSet.NAME, "table_wide");
        convertFurniture(VenthyrSet.NAME, "table_wide_fancy").renderType(renderTypeCutout);
        convertFurniture(VenthyrSet.NAME, "bench");
        convertFurniture(VenthyrSet.NAME, "chair").renderType(renderTypeCutout);
        convertFurniture(VenthyrSet.NAME, "chandelier").renderType(renderTypeCutout);
        convertFurniture(VenthyrSet.NAME, "cushion");
        convertFurniture(VenthyrSet.NAME, "stool");
        convertFurniture(VenthyrSet.NAME, "chest");
        convertFurniture(VenthyrSet.NAME, "bookshelf");
        convertDesk(VenthyrSet.NAME, "left");
        convertDesk(VenthyrSet.NAME, "right");
        convertFurniture(VenthyrSet.NAME, "drawer");
        convertFurniture(VenthyrSet.NAME, "dresser");
        convertFurniture(VenthyrSet.NAME, "lockbox");
        convertWardrobe(VenthyrSet.NAME, "bottom");
        convertFurniture(VenthyrSet.NAME, "wardrobe_top");
        convertFurniture(VenthyrSet.NAME, "painting_wide");
        convertFurniture(VenthyrSet.NAME, "painting_small");
        convertFurniture(VenthyrSet.NAME, "oven").renderType(renderTypeCutout);
        convertFurnitureEx(VenthyrSet.NAME, "oven_lit", "oven").renderType(renderTypeCutout);
        convertDoor(VenthyrSet.NAME, "double", true);
        convertDoor(VenthyrSet.NAME, "double", false);
        convertDoor(VenthyrSet.NAME, "single", true);
        convertDoor(VenthyrSet.NAME, "single", false);
        convertFurniture(VenthyrSet.NAME, "bed_single");
        convertFurniture(VenthyrSet.NAME, "bed_double");
        Arrays.stream(ShelfType.values()).forEach(shelfType -> convertShelf(VenthyrSet.NAME, shelfType));
        Arrays.stream(SofaType.values()).forEach(sofaType -> convertSofa(VenthyrSet.NAME, sofaType));
        Arrays.stream(CounterType.values()).forEach(counterType -> convertCounter(VenthyrSet.NAME, counterType));
    }

    private void dunmer()
    {
        convertFurniture(DunmerSet.NAME, "wall_light").renderType(renderTypeCutout);
        convertFurniture(DunmerSet.NAME, "floor_light").renderType(renderTypeCutout);
        convertFurniture(DunmerSet.NAME, "table_small");
        convertFurniture(DunmerSet.NAME, "table_large");
        convertFurniture(DunmerSet.NAME, "table_wide");
        convertFurniture(DunmerSet.NAME, "bench");
        convertFurniture(DunmerSet.NAME, "chair");
        convertFurniture(DunmerSet.NAME, "chandelier");
        convertFurniture(DunmerSet.NAME, "cushion");
        convertFurniture(DunmerSet.NAME, "stool");
        convertFurniture(DunmerSet.NAME, "chest");
        convertFurniture(DunmerSet.NAME, "bookshelf");
        convertDesk(DunmerSet.NAME, "left");
        convertDesk(DunmerSet.NAME, "right");
        convertFurniture(DunmerSet.NAME, "drawer");
        convertFurniture(DunmerSet.NAME, "dresser");
        convertFurniture(DunmerSet.NAME, "lockbox").renderType(renderTypeCutout);
        convertWardrobe(DunmerSet.NAME, "bottom").renderType(renderTypeCutout);
        convertWardrobe(DunmerSet.NAME, "top");
        convertFurniture(DunmerSet.NAME, "painting_wide");
        convertFurniture(DunmerSet.NAME, "painting_small");
        convertFurniture(DunmerSet.NAME, "oven");
        convertFurnitureEx(DunmerSet.NAME, "oven_lit", "oven").renderType(renderTypeCutout).texture("oven_fire", "block/%s/oven_fire".formatted(DunmerSet.NAME));
        convertDoor(DunmerSet.NAME, "double", true);
        convertDoor(DunmerSet.NAME, "double", false);
        convertDoor(DunmerSet.NAME, "single", true);
        convertDoor(DunmerSet.NAME, "single", false);
        convertFurniture(DunmerSet.NAME, "bed_single");
        convertFurniture(DunmerSet.NAME, "bed_double");
        Arrays.stream(ShelfType.values()).forEach(shelfType -> convertShelf(DunmerSet.NAME, shelfType));
        Arrays.stream(SofaType.values()).forEach(sofaType -> convertSofa(DunmerSet.NAME, sofaType).renderType(renderTypeCutout));
        Arrays.stream(CounterType.values()).forEach(counterType -> convertCounter(DunmerSet.NAME, counterType).renderType(renderTypeCutout));
    }

    private void boneWither()
    {
        convertFurniture(BoneSet.Wither.NAME, "wall_light").renderType(renderTypeCutout);
        convertFurniture(BoneSet.Wither.NAME, "floor_light");
        convertFurniture(BoneSet.Wither.NAME, "table_small");
        convertFurniture(BoneSet.Wither.NAME, "table_large");
        convertFurniture(BoneSet.Wither.NAME, "table_wide");
        convertFurniture(BoneSet.Wither.NAME, "bench");
        convertFurniture(BoneSet.Wither.NAME, "chair");
        convertFurniture(BoneSet.Wither.NAME, "chandelier");
        convertFurniture(BoneSet.Wither.NAME, "cushion");
        convertFurniture(BoneSet.Wither.NAME, "stool");
        convertFurniture(BoneSet.Wither.NAME, "chest");
        convertFurniture(BoneSet.Wither.NAME, "bookshelf");
        convertDesk(BoneSet.Wither.NAME, "left");
        convertDesk(BoneSet.Wither.NAME, "right");
        convertFurniture(BoneSet.Wither.NAME, "drawer");
        convertFurniture(BoneSet.Wither.NAME, "dresser");
        convertFurniture(BoneSet.Wither.NAME, "lockbox");
        convertWardrobe(BoneSet.Wither.NAME, "bottom");
        convertWardrobe(BoneSet.Wither.NAME, "top");
        convertFurniture(BoneSet.Wither.NAME, "painting_wide");
        convertFurniture(BoneSet.Wither.NAME, "painting_small");
        convertFurniture(BoneSet.Wither.NAME, "oven");
        convertFurnitureEx(BoneSet.Wither.NAME, "oven_lit", "oven");
        convertDoor(BoneSet.Wither.NAME, "double", true);
        convertDoor(BoneSet.Wither.NAME, "double", false);
        convertDoor(BoneSet.Wither.NAME, "single", true);
        convertDoor(BoneSet.Wither.NAME, "single", false);
        convertFurniture(BoneSet.Wither.NAME, "bed_single");
        convertFurniture(BoneSet.Wither.NAME, "bed_double");
        Arrays.stream(ShelfType.values()).forEach(shelfType -> convertShelf(BoneSet.Wither.NAME, shelfType));
        Arrays.stream(SofaType.values()).forEach(sofaType -> convertSofa(BoneSet.Wither.NAME, sofaType));
        Arrays.stream(CounterType.values()).forEach(counterType -> convertCounter(BoneSet.Wither.NAME, counterType));
    }

    private void boneSkeleton()
    {
        convertFurniture(BoneSet.Skeleton.NAME, "wall_light").renderType(renderTypeCutout);
        convertFurniture(BoneSet.Skeleton.NAME, "floor_light");
        convertFurniture(BoneSet.Skeleton.NAME, "table_small");
        convertFurniture(BoneSet.Skeleton.NAME, "table_large");
        convertFurniture(BoneSet.Skeleton.NAME, "table_wide");
        convertFurniture(BoneSet.Skeleton.NAME, "bench");
        convertFurniture(BoneSet.Skeleton.NAME, "chair");
        convertFurniture(BoneSet.Skeleton.NAME, "chandelier");
        convertFurniture(BoneSet.Skeleton.NAME, "cushion");
        convertFurniture(BoneSet.Skeleton.NAME, "stool");
        convertFurniture(BoneSet.Skeleton.NAME, "chest");
        convertFurniture(BoneSet.Skeleton.NAME, "bookshelf");
        convertDesk(BoneSet.Skeleton.NAME, "left");
        convertDesk(BoneSet.Skeleton.NAME, "right");
        convertFurniture(BoneSet.Skeleton.NAME, "drawer");
        convertFurniture(BoneSet.Skeleton.NAME, "dresser");
        convertFurniture(BoneSet.Skeleton.NAME, "lockbox");
        convertWardrobe(BoneSet.Skeleton.NAME, "bottom");
        convertWardrobe(BoneSet.Skeleton.NAME, "top");
        convertFurniture(BoneSet.Skeleton.NAME, "painting_wide");
        convertFurniture(BoneSet.Skeleton.NAME, "painting_small");
        convertFurniture(BoneSet.Skeleton.NAME, "oven");
        convertFurnitureEx(BoneSet.Skeleton.NAME, "oven_lit", "oven");
        convertDoor(BoneSet.Skeleton.NAME, "double", true);
        convertDoor(BoneSet.Skeleton.NAME, "double", false);
        convertDoor(BoneSet.Skeleton.NAME, "single", true);
        convertDoor(BoneSet.Skeleton.NAME, "single", false);
        convertFurniture(BoneSet.Skeleton.NAME, "bed_single");
        convertFurniture(BoneSet.Skeleton.NAME, "bed_double");
        Arrays.stream(ShelfType.values()).forEach(shelfType -> convertShelf(BoneSet.Skeleton.NAME, shelfType));
        Arrays.stream(SofaType.values()).forEach(sofaType -> convertSofa(BoneSet.Skeleton.NAME, sofaType));
        Arrays.stream(CounterType.values()).forEach(counterType -> convertCounter(BoneSet.Skeleton.NAME, counterType));
    }

    private BlockModelBuilder convertDesk(String furnitureSet, String type)
    {
        return convertFurnitureEx(
                furnitureSet,
                "desk_%s".formatted(type),
                "desk"
        );
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
        );
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
