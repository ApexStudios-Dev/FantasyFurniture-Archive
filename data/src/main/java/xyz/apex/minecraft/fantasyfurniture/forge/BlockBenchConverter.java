package xyz.apex.minecraft.fantasyfurniture.forge;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.data.event.GatherDataEvent;

import xyz.apex.minecraft.apexcore.forge.data.BlockBenchModelConverter;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.CounterType;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ShelfType;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.SofaType;
import xyz.apex.minecraft.fantasyfurniture.common.init.NordicSet;

import java.util.Arrays;

public final class BlockBenchConverter extends BlockBenchModelConverter
{
    public BlockBenchConverter(GatherDataEvent event, PackOutput packOutput)
    {
        super(event, packOutput, FantasyFurniture.ID);
    }

    @Override
    protected void convertModels()
    {
        convertBasic("furniture_station");

        convertFurnitureSet(NordicSet.NAME);
    }

    private void convertFurnitureSet(String furnitureSet)
    {
        convertGeneric(furnitureSet, "wall_light");
        convertGeneric(furnitureSet, "floor_light");
        convertTable(furnitureSet, "small");
        convertTable(furnitureSet, "large");
        convertTable(furnitureSet, "wide");
        convertGeneric(furnitureSet, "bench");
        convertGeneric(furnitureSet, "chair");
        convertGeneric(furnitureSet, "chandelier");
        convertGeneric(furnitureSet, "cushion");
        convertGeneric(furnitureSet, "stool");
        convertGeneric(furnitureSet, "chest");
        convertGeneric(furnitureSet, "bookshelf");
        convertDesk(furnitureSet, "left");
        convertDesk(furnitureSet, "right");
        convertGeneric(furnitureSet, "drawer");
        convertGeneric(furnitureSet, "dresser");
        convertGeneric(furnitureSet, "lockbox");
        convertWardrobe(furnitureSet, "bottom");
        convertWardrobe(furnitureSet, "top");
        convertGeneric(furnitureSet, "painting_wide");
        convertGeneric(furnitureSet, "painting_small");
        convertGeneric(furnitureSet, "oven");
        convertDoor(furnitureSet, "double", true);
        convertDoor(furnitureSet, "double", false);
        convertDoor(furnitureSet, "single", true);
        convertDoor(furnitureSet, "single", false);
        convertGeneric(furnitureSet, "bed_single");
        convertGeneric(furnitureSet, "bed_double");
        Arrays.stream(ShelfType.values()).forEach(shelfType -> convertShelf(furnitureSet, shelfType));
        Arrays.stream(SofaType.values()).forEach(sofaType -> convertSofa(furnitureSet, sofaType));
        Arrays.stream(CounterType.values()).forEach(counterType -> convertCounter(furnitureSet, counterType));
    }

    private BlockModelBuilder convertTable(String furnitureSet, String type)
    {
        return convertGeneric(furnitureSet, "table_%s".formatted(type));
    }

    private BlockModelBuilder convertDesk(String furnitureSet, String type)
    {
        return convertGeneric(furnitureSet, "desk_%s".formatted(type), "desk", "desk");
    }

    private BlockModelBuilder convertWardrobe(String furnitureSet, String type)
    {
        return convertGeneric(furnitureSet, "wardrobe_%s".formatted(type), "wardrobe", "wardrobe");
    }

    private BlockModelBuilder convertGeneric(String furnitureSet, String blockType)
    {
        return convertGeneric(furnitureSet, blockType, blockType, blockType);
    }

    private BlockModelBuilder convertGeneric(String furnitureSet, String blockType, String textureKey, String texturePath)
    {
        return blockModelBuilder(new ResourceLocation(FantasyFurniture.ID, "block/%s/%s".formatted(furnitureSet, blockType)))
                // unchecked as template generation is done during block state generation
                // which fires after the conversion
                // this means the templates wont have been marked as "existing" yet
                // and system will throw errors, saying model could not be found
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(FantasyFurniture.ID, "block/templates/%s".formatted(blockType))))
                .texture("particle", new ResourceLocation(FantasyFurniture.ID, "block/%s/particle".formatted(furnitureSet)))
                .texture(textureKey, new ResourceLocation(FantasyFurniture.ID, "block/%s/%s".formatted(furnitureSet, texturePath)))
        ;
    }

    private BlockModelBuilder convertDoor(String furnitureSet, String doorType, boolean isLeft)
    {
        var rootDoorName = "door_%s".formatted(doorType);
        var furnitureDoorName = "%s/%s".formatted(furnitureSet, rootDoorName);
        return blockModelBuilder(new ResourceLocation(FantasyFurniture.ID, "block/%s_%s".formatted(furnitureDoorName, isLeft ? "left" : "right"))) // fantasyfurniture:block/${furnitureSet}/door_${doorType}_${isLeft ? 'left' : 'right'}
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(FantasyFurniture.ID, "block/templates/%s".formatted(rootDoorName)))) // fantasyfurniture:block/templates/door_${doorType}
                .texture("particle", new ResourceLocation(FantasyFurniture.ID, "block/%s/particle".formatted(furnitureSet))) // fantasyfurniture:block/${furnitureSet}/particle
                .texture(rootDoorName, new ResourceLocation(FantasyFurniture.ID, "block/%s".formatted(furnitureDoorName))) // door_${doorType} | fantasyfurniture:block/${furnitureSet}/door_${doorType}
        ;
    }

    private BlockModelBuilder convertShelf(String furnitureSet, ShelfType shelfType)
    {
        return blockModelBuilder(new ResourceLocation(FantasyFurniture.ID, "block/%s/shelf_%s".formatted(furnitureSet, shelfType.getSerializedName()))) // fantasyfurniture:block/${furnitureSet}/shelf_${shelfType}
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(FantasyFurniture.ID, "block/templates/shelf"))) // fantasyfurniture:block/templates/shelf
                .texture("particle", new ResourceLocation(FantasyFurniture.ID, "block/%s/particle".formatted(furnitureSet))) // fantasyfurniture:block/${furnitureSet}/particle
                .texture("shelf", new ResourceLocation(FantasyFurniture.ID, "block/%s/shelf".formatted(furnitureSet))) // shelf | fantasyfurniture:block/${furnitureSet}/shelf
        ;
    }

    private BlockModelBuilder convertSofa(String furnitureSet, SofaType sofaType)
    {
        return blockModelBuilder(new ResourceLocation(FantasyFurniture.ID, "block/%s/sofa_%s".formatted(furnitureSet, sofaType.getSerializedName()))) // fantasyfurniture:block/${furnitureSet}/shelf_${sofaType}
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(FantasyFurniture.ID, "block/templates/sofa"))) // fantasyfurniture:block/templates/shelf
                .texture("particle", new ResourceLocation(FantasyFurniture.ID, "block/%s/particle".formatted(furnitureSet))) // fantasyfurniture:block/${furnitureSet}/particle
                .texture("sofa", new ResourceLocation(FantasyFurniture.ID, "block/%s/sofa".formatted(furnitureSet))) // shelf | fantasyfurniture:block/${furnitureSet}/shelf
        ;
    }

    private BlockModelBuilder convertCounter(String furnitureSet, CounterType counterType)
    {
        return blockModelBuilder(new ResourceLocation(FantasyFurniture.ID, "block/%s/counter_%s".formatted(furnitureSet, counterType.getSerializedName()))) // fantasyfurniture:block/${furnitureSet}/counter_${counterType}
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(FantasyFurniture.ID, "block/templates/counter"))) // fantasyfurniture:block/templates/counter
                .texture("particle", new ResourceLocation(FantasyFurniture.ID, "block/%s/particle".formatted(furnitureSet))) // fantasyfurniture:block/${furnitureSet}/particle
                .texture("counter", new ResourceLocation(FantasyFurniture.ID, "block/%s/counter".formatted(furnitureSet))) // counter | fantasyfurniture:block/${furnitureSet}/counter
        ;
    }

    private BlockModelBuilder convertBasic(String blockName)
    {
        return blockModelBuilder(new ResourceLocation(FantasyFurniture.ID, "block/%s".formatted(blockName)))
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation("minecraft", "block/block")))
                .texture("particle", new ResourceLocation(FantasyFurniture.ID, "block/%s/particle".formatted(blockName)))
                .texture(blockName, new ResourceLocation(FantasyFurniture.ID, "block/%s/block".formatted(blockName)))
        ;
    }
}
