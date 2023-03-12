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
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

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
        convertBasic("furniture_station", false);

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
        convertFurniture(NordicSet.NAME, "wall_light", false).renderType(renderTypeCutout);
        convertFurniture(NordicSet.NAME, "floor_light", false);
        convertFurniture(NordicSet.NAME, "table_small", false);
        convertFurniture(NordicSet.NAME, "table_large", false);
        convertFurniture(NordicSet.NAME, "table_wide", false).renderType(renderTypeCutout);
        convertFurniture(NordicSet.NAME, "bench", false);
        convertFurniture(NordicSet.NAME, "chair", false).renderType(renderTypeCutout);
        convertFurniture(NordicSet.NAME, "ceiling_light", false);
        convertFurniture(NordicSet.NAME, "cushion", false);
        convertFurniture(NordicSet.NAME, "stool", false);
        convertFurniture(NordicSet.NAME, "chest", false);
        convertFurniture(NordicSet.NAME, "bookshelf", false);
        convertDesk(NordicSet.NAME, "left", false).renderType(renderTypeCutout);
        convertDesk(NordicSet.NAME, "right", false).renderType(renderTypeCutout);
        convertFurniture(NordicSet.NAME, "drawer", false);
        convertFurniture(NordicSet.NAME, "dresser", false);
        convertFurniture(NordicSet.NAME, "lockbox", false);
        convertWardrobe(NordicSet.NAME, "bottom", false);
        convertWardrobe(NordicSet.NAME, "top", false).renderType(renderTypeCutout);
        convertFurniture(NordicSet.NAME, "painting_wide", false);
        convertFurniture(NordicSet.NAME, "painting_small", false);
        convertFurniture(NordicSet.NAME, "oven", false).renderType(renderTypeCutout);
        convertFurnitureEx(NordicSet.NAME, "oven_lit", "oven", false).renderType(renderTypeCutout);
        convertDoor(NordicSet.NAME, "double", true, false);
        convertDoor(NordicSet.NAME, "double", false, false);
        convertDoor(NordicSet.NAME, "single", true, false);
        convertDoor(NordicSet.NAME, "single", false, false);
        convertFurniture(NordicSet.NAME, "bed_single", false);
        convertFurniture(NordicSet.NAME, "bed_double", false);
        Arrays.stream(ShelfType.values()).forEach(shelfType -> convertShelf(NordicSet.NAME, shelfType, false).renderType(renderTypeCutout));
        Arrays.stream(SofaType.values()).forEach(sofaType -> convertSofa(NordicSet.NAME, sofaType, false));
        Arrays.stream(CounterType.values()).forEach(counterType -> convertCounter(NordicSet.NAME, counterType, false));
    }

    private void venthyr()
    {
        convertFurniture(VenthyrSet.NAME, "wall_light", false);
        convertFurniture(VenthyrSet.NAME, "floor_light", false);
        convertFurniture(VenthyrSet.NAME, "table_small", false);
        convertFurniture(VenthyrSet.NAME, "table_small_fancy", false).renderType(renderTypeCutout);
        convertFurniture(VenthyrSet.NAME, "table_large", false);
        convertFurniture(VenthyrSet.NAME, "table_large_fancy", false).renderType(renderTypeCutout);
        convertFurniture(VenthyrSet.NAME, "table_wide", false);
        convertFurniture(VenthyrSet.NAME, "table_wide_fancy", false).renderType(renderTypeCutout);
        convertFurniture(VenthyrSet.NAME, "bench", false);
        convertFurniture(VenthyrSet.NAME, "chair", false).renderType(renderTypeCutout);
        convertFurniture(VenthyrSet.NAME, "ceiling_light", false).renderType(renderTypeCutout);
        convertFurniture(VenthyrSet.NAME, "cushion", false);
        convertFurniture(VenthyrSet.NAME, "stool", false);
        convertFurniture(VenthyrSet.NAME, "chest", false);
        convertFurniture(VenthyrSet.NAME, "bookshelf", false);
        convertDesk(VenthyrSet.NAME, "left", false);
        convertDesk(VenthyrSet.NAME, "right", false);
        convertFurniture(VenthyrSet.NAME, "drawer", false);
        convertFurniture(VenthyrSet.NAME, "dresser", false);
        convertFurniture(VenthyrSet.NAME, "lockbox", false);
        convertWardrobe(VenthyrSet.NAME, "bottom", false);
        convertFurniture(VenthyrSet.NAME, "wardrobe_top", false);
        convertFurniture(VenthyrSet.NAME, "painting_wide", false);
        convertFurniture(VenthyrSet.NAME, "painting_small", false);
        convertFurniture(VenthyrSet.NAME, "oven", false).renderType(renderTypeCutout);
        convertFurnitureEx(VenthyrSet.NAME, "oven_lit", "oven", false).renderType(renderTypeCutout);
        convertDoor(VenthyrSet.NAME, "double", true, false);
        convertDoor(VenthyrSet.NAME, "double", false, false);
        convertDoor(VenthyrSet.NAME, "single", true, false);
        convertDoor(VenthyrSet.NAME, "single", false, false);
        convertFurniture(VenthyrSet.NAME, "bed_single", false);
        convertFurniture(VenthyrSet.NAME, "bed_double", false);
        Arrays.stream(ShelfType.values()).forEach(shelfType -> convertShelf(VenthyrSet.NAME, shelfType, false));
        Arrays.stream(SofaType.values()).forEach(sofaType -> convertSofa(VenthyrSet.NAME, sofaType, false));
        Arrays.stream(CounterType.values()).forEach(counterType -> convertCounter(VenthyrSet.NAME, counterType, false));
    }

    private void dunmer()
    {
        convertFurniture(DunmerSet.NAME, "wall_light", false).renderType(renderTypeCutout);
        convertFurniture(DunmerSet.NAME, "floor_light", false).renderType(renderTypeCutout);
        convertFurniture(DunmerSet.NAME, "table_small", false);
        convertFurniture(DunmerSet.NAME, "table_large", false);
        convertFurniture(DunmerSet.NAME, "table_wide", false);
        convertFurniture(DunmerSet.NAME, "bench", false);
        convertFurniture(DunmerSet.NAME, "chair", false);
        convertFurniture(DunmerSet.NAME, "ceiling_light", false);
        convertFurniture(DunmerSet.NAME, "cushion", false);
        convertFurniture(DunmerSet.NAME, "stool", false);
        convertFurniture(DunmerSet.NAME, "chest", false);
        convertFurniture(DunmerSet.NAME, "bookshelf", false);
        convertDesk(DunmerSet.NAME, "left", false);
        convertDesk(DunmerSet.NAME, "right", false);
        convertFurniture(DunmerSet.NAME, "drawer", false);
        convertFurniture(DunmerSet.NAME, "dresser", false);
        convertFurniture(DunmerSet.NAME, "lockbox", false).renderType(renderTypeCutout);
        convertWardrobe(DunmerSet.NAME, "bottom", false).renderType(renderTypeCutout);
        convertWardrobe(DunmerSet.NAME, "top", false);
        convertFurniture(DunmerSet.NAME, "painting_wide", false);
        convertFurniture(DunmerSet.NAME, "painting_small", false);
        convertFurniture(DunmerSet.NAME, "oven", false);
        convertFurnitureEx(DunmerSet.NAME, "oven_lit", "oven", false).renderType(renderTypeCutout).texture("oven_fire", "block/%s/oven_fire".formatted(DunmerSet.NAME));
        convertDoor(DunmerSet.NAME, "double", true, false);
        convertDoor(DunmerSet.NAME, "double", false, false);
        convertDoor(DunmerSet.NAME, "single", true, false);
        convertDoor(DunmerSet.NAME, "single", false, false);
        convertFurniture(DunmerSet.NAME, "bed_single", false);
        convertFurniture(DunmerSet.NAME, "bed_double", false);
        Arrays.stream(ShelfType.values()).forEach(shelfType -> convertShelf(DunmerSet.NAME, shelfType, false));
        Arrays.stream(SofaType.values()).forEach(sofaType -> convertSofa(DunmerSet.NAME, sofaType, false).renderType(renderTypeCutout));
        Arrays.stream(CounterType.values()).forEach(counterType -> convertCounter(DunmerSet.NAME, counterType, false).renderType(renderTypeCutout));
    }

    private void boneWither()
    {
        convertFurniture(BoneSet.Wither.NAME, "wall_light", false).renderType(renderTypeCutout);
        convertFurniture(BoneSet.Wither.NAME, "floor_light", false);
        convertFurniture(BoneSet.Wither.NAME, "table_small", false);
        convertFurniture(BoneSet.Wither.NAME, "table_large", false);
        convertFurniture(BoneSet.Wither.NAME, "table_wide", false);
        convertFurniture(BoneSet.Wither.NAME, "bench", false);
        convertFurniture(BoneSet.Wither.NAME, "chair", false);
        convertFurniture(BoneSet.Wither.NAME, "ceiling_light", false);
        convertFurniture(BoneSet.Wither.NAME, "cushion", false);
        convertFurniture(BoneSet.Wither.NAME, "stool", false);
        convertFurniture(BoneSet.Wither.NAME, "chest", false);
        convertFurniture(BoneSet.Wither.NAME, "bookshelf", false);
        convertDesk(BoneSet.Wither.NAME, "left", false);
        convertDesk(BoneSet.Wither.NAME, "right", false);
        convertFurniture(BoneSet.Wither.NAME, "drawer", false);
        convertFurniture(BoneSet.Wither.NAME, "dresser", false);
        convertFurniture(BoneSet.Wither.NAME, "lockbox", false);
        convertWardrobe(BoneSet.Wither.NAME, "bottom", false);
        convertWardrobe(BoneSet.Wither.NAME, "top", false);
        convertFurniture(BoneSet.Wither.NAME, "painting_wide", false);
        convertFurniture(BoneSet.Wither.NAME, "painting_small", false);
        convertFurniture(BoneSet.Wither.NAME, "oven", false);
        convertFurnitureEx(BoneSet.Wither.NAME, "oven_lit", "oven", false);
        convertDoor(BoneSet.Wither.NAME, "double", true, false);
        convertDoor(BoneSet.Wither.NAME, "double", false, false);
        convertDoor(BoneSet.Wither.NAME, "single", true, false);
        convertDoor(BoneSet.Wither.NAME, "single", false, false);
        convertFurniture(BoneSet.Wither.NAME, "bed_single", false);
        convertFurniture(BoneSet.Wither.NAME, "bed_double", false);
        Arrays.stream(ShelfType.values()).forEach(shelfType -> convertShelf(BoneSet.Wither.NAME, shelfType, false));
        Arrays.stream(SofaType.values()).forEach(sofaType -> convertSofa(BoneSet.Wither.NAME, sofaType, false));
        Arrays.stream(CounterType.values()).forEach(counterType -> convertCounter(BoneSet.Wither.NAME, counterType, false));
    }

    private void boneSkeleton()
    {
        convertFurniture(BoneSet.Skeleton.NAME, "wall_light", false).renderType(renderTypeCutout);
        convertFurniture(BoneSet.Skeleton.NAME, "floor_light", false);
        convertFurniture(BoneSet.Skeleton.NAME, "table_small", false);
        convertFurniture(BoneSet.Skeleton.NAME, "table_large", false);
        convertFurniture(BoneSet.Skeleton.NAME, "table_wide", false);
        convertFurniture(BoneSet.Skeleton.NAME, "bench", false);
        convertFurniture(BoneSet.Skeleton.NAME, "chair", false);
        convertFurniture(BoneSet.Skeleton.NAME, "ceiling_light", false);
        convertFurniture(BoneSet.Skeleton.NAME, "cushion", false);
        convertFurniture(BoneSet.Skeleton.NAME, "stool", false);
        convertFurniture(BoneSet.Skeleton.NAME, "chest", false);
        convertFurniture(BoneSet.Skeleton.NAME, "bookshelf", false);
        convertDesk(BoneSet.Skeleton.NAME, "left", false);
        convertDesk(BoneSet.Skeleton.NAME, "right", false);
        convertFurniture(BoneSet.Skeleton.NAME, "drawer", false);
        convertFurniture(BoneSet.Skeleton.NAME, "dresser", false);
        convertFurniture(BoneSet.Skeleton.NAME, "lockbox", false);
        convertWardrobe(BoneSet.Skeleton.NAME, "bottom", false);
        convertWardrobe(BoneSet.Skeleton.NAME, "top", false);
        convertFurniture(BoneSet.Skeleton.NAME, "painting_wide", false);
        convertFurniture(BoneSet.Skeleton.NAME, "painting_small", false);
        convertFurniture(BoneSet.Skeleton.NAME, "oven", false);
        convertFurnitureEx(BoneSet.Skeleton.NAME, "oven_lit", "oven", false);
        convertDoor(BoneSet.Skeleton.NAME, "double", true, false);
        convertDoor(BoneSet.Skeleton.NAME, "double", false, false);
        convertDoor(BoneSet.Skeleton.NAME, "single", true, false);
        convertDoor(BoneSet.Skeleton.NAME, "single", false, false);
        convertFurniture(BoneSet.Skeleton.NAME, "bed_single", false);
        convertFurniture(BoneSet.Skeleton.NAME, "bed_double", false);
        Arrays.stream(ShelfType.values()).forEach(shelfType -> convertShelf(BoneSet.Skeleton.NAME, shelfType, false));
        Arrays.stream(SofaType.values()).forEach(sofaType -> convertSofa(BoneSet.Skeleton.NAME, sofaType, false));
        Arrays.stream(CounterType.values()).forEach(counterType -> convertCounter(BoneSet.Skeleton.NAME, counterType, false));
    }

    private void necrolord()
    {
        convertFurniture(NecrolordSet.NAME, "wall_light", false);
        convertFurniture(NecrolordSet.NAME, "floor_light", false);
        convertFurniture(NecrolordSet.NAME, "table_small", false).renderType(renderTypeCutout);
        convertFurniture(NecrolordSet.NAME, "table_large", false).renderType(renderTypeCutout);
        convertFurniture(NecrolordSet.NAME, "table_wide", false).renderType(renderTypeCutout);
        convertFurniture(NecrolordSet.NAME, "bench", false).renderType(renderTypeCutout);
        convertFurniture(NecrolordSet.NAME, "chair", false);
        convertFurniture(NecrolordSet.NAME, "ceiling_light", false).renderType(renderTypeCutout);
        convertFurniture(NecrolordSet.NAME, "cushion", false);
        convertFurniture(NecrolordSet.NAME, "stool", false);
        convertFurniture(NecrolordSet.NAME, "chest", false);
        convertFurniture(NecrolordSet.NAME, "bookshelf", false).renderType(renderTypeCutout);
        convertDesk(NecrolordSet.NAME, "left", false);
        convertDesk(NecrolordSet.NAME, "right", false);
        convertFurniture(NecrolordSet.NAME, "drawer", false).renderType(renderTypeCutout);
        convertFurniture(NecrolordSet.NAME, "dresser", false).renderType(renderTypeCutout);
        convertFurniture(NecrolordSet.NAME, "lockbox", false);
        convertWardrobe(NecrolordSet.NAME, "bottom", false).renderType(renderTypeCutout);
        convertWardrobe(NecrolordSet.NAME, "top", false).renderType(renderTypeCutout);
        convertFurniture(NecrolordSet.NAME, "painting_wide", false);
        convertFurniture(NecrolordSet.NAME, "painting_small", false);
        convertFurniture(NecrolordSet.NAME, "oven", false).renderType(renderTypeCutout);
        convertFurnitureEx(NecrolordSet.NAME, "oven_lit", "oven", false).renderType(renderTypeCutout);
        convertDoor(NecrolordSet.NAME, "double", true, false);
        convertDoor(NecrolordSet.NAME, "double", false, false);
        convertDoor(NecrolordSet.NAME, "single", true, false);
        convertDoor(NecrolordSet.NAME, "single", false, false);
        convertFurniture(NecrolordSet.NAME, "bed_single", false).renderType(renderTypeCutout);
        convertFurniture(NecrolordSet.NAME, "bed_double", false).renderType(renderTypeCutout);
        Arrays.stream(ShelfType.values()).forEach(shelfType -> convertShelf(NecrolordSet.NAME, shelfType, false));
        Arrays.stream(SofaType.values()).forEach(sofaType -> convertSofa(NecrolordSet.NAME, sofaType, false));
        Arrays.stream(CounterType.values()).forEach(counterType -> convertCounter(NecrolordSet.NAME, counterType, false));
    }

    private void royal()
    {
        convertFurniture(RoyalSet.NAME, "wall_light", false);
        convertFurniture(RoyalSet.NAME, "floor_light", false);
        convertFurniture(RoyalSet.NAME, "table_small", true);
        convertFurniture(RoyalSet.NAME, "table_large", true);
        convertFurniture(RoyalSet.NAME, "table_wide", true);
        convertFurniture(RoyalSet.NAME, "bench", true);
        convertFurniture(RoyalSet.NAME, "chair", true);
        convertFurniture(RoyalSet.NAME, "ceiling_light", false);
        convertFurniture(RoyalSet.NAME, "cushion", true);
        convertFurniture(RoyalSet.NAME, "stool", true);
        convertFurniture(RoyalSet.NAME, "chest", true);
        convertFurniture(RoyalSet.NAME, "bookshelf", true);
        convertDesk(RoyalSet.NAME, "left", true);
        convertDesk(RoyalSet.NAME, "right", true);
        convertFurniture(RoyalSet.NAME, "drawer", true);
        convertFurniture(RoyalSet.NAME, "dresser", true);
        convertFurniture(RoyalSet.NAME, "lockbox", true);
        convertWardrobe(RoyalSet.NAME, "bottom", true);
        convertWardrobe(RoyalSet.NAME, "top", true);
        convertFurniture(RoyalSet.NAME, "painting_wide", false);
        convertFurniture(RoyalSet.NAME, "painting_small", false);
        convertFurniture(RoyalSet.NAME, "oven", true);
        convertFurnitureEx(RoyalSet.NAME, "oven_lit", "oven", true);
        convertDoor(RoyalSet.NAME, "double", true, true);
        convertDoor(RoyalSet.NAME, "double", false, true);
        convertDoor(RoyalSet.NAME, "single", true, true);
        convertDoor(RoyalSet.NAME, "single", false, true);
        convertFurniture(RoyalSet.NAME, "bed_single", true);
        convertFurniture(RoyalSet.NAME, "bed_double", true);
        Arrays.stream(ShelfType.values()).forEach(shelfType -> convertShelf(RoyalSet.NAME, shelfType, true));
        Arrays.stream(SofaType.values()).forEach(sofaType -> convertSofa(RoyalSet.NAME, sofaType, true));
        Arrays.stream(CounterType.values()).forEach(counterType -> convertCounter(RoyalSet.NAME, counterType, true));
    }

    private BlockModelBuilder convertDesk(String furnitureSet, String type, boolean tinted)
    {
        return convertFurnitureEx(
                furnitureSet,
                "desk_%s".formatted(type),
                "desk",
                tinted
        );
    }

    private BlockModelBuilder convertWardrobe(String furnitureSet, String type, boolean tinted)
    {
        return convertFurnitureEx(
                furnitureSet,
                "wardrobe_%s".formatted(type),
                "wardrobe",
                tinted
        );
    }

    private BlockModelBuilder convertDoor(String furnitureSet, String doorType, boolean isLeft, boolean tinted)
    {
        return convertFurnitureEx(
                furnitureSet,
                "door_%s_%s".formatted(doorType, isLeft ? "left" : "right"),
                "door_%s".formatted(doorType),
                tinted
        );
    }

    private BlockModelBuilder convertShelf(String furnitureSet, ShelfType shelfType, boolean tinted)
    {
        return convertFurnitureEx(
                furnitureSet,
                "shelf_%s".formatted(shelfType.getSerializedName()),
                "shelf",
                tinted
        );
    }

    private BlockModelBuilder convertSofa(String furnitureSet, SofaType sofaType, boolean tinted)
    {
        return convertFurnitureEx(
                furnitureSet,
                "sofa_%s".formatted(sofaType.getSerializedName()),
                "sofa",
                tinted
        );
    }

    private BlockModelBuilder convertCounter(String furnitureSet, CounterType counterType, boolean tinted)
    {
        return convertFurnitureEx(
                furnitureSet,
                "counter_%s".formatted(counterType.getSerializedName()),
                "counter",
                tinted
        );
    }

    private BlockModelBuilder convertBasic(String blockName, boolean tinted)
    {
        return convert(
                new ResourceLocation(modId, "block/%s".formatted(blockName)), // ${modId}:block/${blockName}
                new ResourceLocation(modId, "block/%s/particle".formatted(blockName)), // ${modId}:block/${blockName}/particle
                blockName,
                new ResourceLocation(modId, "block/%s/block".formatted(blockName)), // ${modId}:block/${blockName}/block
                tinted
        );
    }

    private BlockModelBuilder convertFurniture(String furnitureSet, String furnitureType, boolean tinted)
    {
        return convertFurnitureEx(furnitureSet, furnitureType, furnitureType, tinted);
    }

    private BlockModelBuilder convertFurnitureEx(String furnitureSet, String furnitureTypeFull, String furnitureTypeShort, boolean tinted)
    {
        return convert(
                new ResourceLocation(modId, "block/%s/%s".formatted(furnitureSet, furnitureTypeFull)), // ${modId}:block/${furnitureSet}/${furnitureType}
                new ResourceLocation(modId, "block/%s/particle".formatted(furnitureSet)), // ${modId}:block/${furnitureSet}/particle
                furnitureTypeShort,
                new ResourceLocation(modId, "block/%s/%s".formatted(furnitureSet, furnitureTypeShort)), // ${modId}:block/${furnitureSet}/${furnitureType}
                tinted
        );
    }

    private BlockModelBuilder convert(ResourceLocation model, ResourceLocation particle, String textureKey, ResourceLocation texturePath, boolean tinted)
    {
        var result = blockModelBuilder(model)
                .parent(modelBlockBlock)
                .texture("particle", particle)
                .texture(textureKey, texturePath)
        ;

        if(tinted) return result.texture("%s_tint".formatted(textureKey), texturePath.withPath("%s_tint"::formatted));
        return result;
    }
}
