package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.data.event.GatherDataEvent;

import xyz.apex.minecraft.fantasyfurniture.forge.FantasyFurnitureDataMod;
import xyz.apex.minecraft.fantasyfurniture.forge.Nordic;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.BlockBenchModelConverter;

public final class BlockBenchConverter extends BlockBenchModelConverter
{
    public BlockBenchConverter(GatherDataEvent event, PackOutput packOutput)
    {
        super(event, packOutput, FantasyFurnitureDataMod.ID);
    }

    @Override
    protected void convertModels()
    {
        convertFurnitureSet(Nordic.NAME);
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
        return blockModelBuilder(new ResourceLocation(FantasyFurnitureDataMod.ID, "block/%s/%s".formatted(furnitureSet, blockType)))
                // unchecked as template generation is done during block state generation
                // which fires after the conversion
                // this means the templates wont have been marked as "existing" yet
                // and system will throw errors, saying model could not be found
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(FantasyFurnitureDataMod.ID, "block/templates/%s".formatted(blockType))))
                .texture("particle", new ResourceLocation(FantasyFurnitureDataMod.ID, "block/%s/particle".formatted(furnitureSet)))
                .texture(textureKey, new ResourceLocation(FantasyFurnitureDataMod.ID, "block/%s/%s".formatted(furnitureSet, texturePath)))
        ;
    }
}
