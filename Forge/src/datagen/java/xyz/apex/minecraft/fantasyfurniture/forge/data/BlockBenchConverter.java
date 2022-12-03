package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.data.event.GatherDataEvent;

import xyz.apex.minecraft.apexcore.forge.data.AbstractBlockBenchConverter;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;

public final class BlockBenchConverter extends AbstractBlockBenchConverter
{
    BlockBenchConverter(GatherDataEvent event)
    {
        super(event, FantasyFurniture.ID);
    }

    @Override
    protected void convertModels()
    {
        convertWallLight("nordic");
        convertFloorLight("nordic");
    }

    private void convertWallLight(String furnitureSet)
    {
        blockModelBuilder(new ResourceLocation(FantasyFurniture.ID, "block/%s/wall_light".formatted(furnitureSet)))
                // unchecked as template generation is done during block state generation
                // which fires after the conversion
                // this means the templates wont have been marked as "existing" yet
                // and system will throw errors, saying model could not be found
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(FantasyFurniture.ID, "block/templates/wall_light")))
                .texture("particle", new ResourceLocation(FantasyFurniture.ID, "particle/%s".formatted(furnitureSet)))
                .texture("wall_light", new ResourceLocation(FantasyFurniture.ID, "models/%s/wall_light".formatted(furnitureSet)))
        ;
    }

    private void convertFloorLight(String furnitureSet)
    {
        blockModelBuilder(new ResourceLocation(FantasyFurniture.ID, "block/%s/floor_light".formatted(furnitureSet)))
                // unchecked as template generation is done during block state generation
                // which fires after the conversion
                // this means the templates wont have been marked as "existing" yet
                // and system will throw errors, saying model could not be found
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(FantasyFurniture.ID, "block/templates/floor_light")))
                .texture("particle", new ResourceLocation(FantasyFurniture.ID, "particle/%s".formatted(furnitureSet)))
                .texture("floor_light", new ResourceLocation(FantasyFurniture.ID, "models/%s/floor_light".formatted(furnitureSet)))
        ;
    }
}
