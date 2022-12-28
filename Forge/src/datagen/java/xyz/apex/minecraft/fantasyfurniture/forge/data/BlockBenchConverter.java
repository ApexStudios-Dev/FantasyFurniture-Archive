package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.data.event.GatherDataEvent;

import xyz.apex.minecraft.apexcore.forge.data.BlockBenchModelConverter;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;

public final class BlockBenchConverter extends BlockBenchModelConverter
{
    BlockBenchConverter(GatherDataEvent event, PackOutput packOutput)
    {
        super(event, packOutput, FantasyFurniture.ID);
    }

    @Override
    protected void convertModels()
    {
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
    }

    private BlockModelBuilder convertTable(String furnitureSet, String type)
    {
        return convertGeneric(furnitureSet, "table_%s".formatted(type));
    }

    private BlockModelBuilder convertGeneric(String furnitureSet, String blockType)
    {
        return blockModelBuilder(new ResourceLocation(FantasyFurniture.ID, "block/%s/%s".formatted(furnitureSet, blockType)))
                // unchecked as template generation is done during block state generation
                // which fires after the conversion
                // this means the templates wont have been marked as "existing" yet
                // and system will throw errors, saying model could not be found
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(FantasyFurniture.ID, "block/templates/%s".formatted(blockType))))
                .texture("particle", new ResourceLocation(FantasyFurniture.ID, "block/%s/particle".formatted(furnitureSet)))
                .texture(blockType, new ResourceLocation(FantasyFurniture.ID, "block/%s/%s".formatted(furnitureSet, blockType)))
        ;
    }
}
