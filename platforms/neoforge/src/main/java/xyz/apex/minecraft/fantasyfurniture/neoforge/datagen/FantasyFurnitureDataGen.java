package xyz.apex.minecraft.fantasyfurniture.neoforge.datagen;

import net.minecraftforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.neoforge.lib.EventBuses;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

@ApiStatus.Internal
public interface FantasyFurnitureDataGen
{
    static void bootstrap()
    {
        EventBuses.addListener(FantasyFurniture.ID, bus -> bus.addListener(FantasyFurnitureDataGen::onGatherData));
    }

    private static void onGatherData(GatherDataEvent event)
    {
        var generator = event.getGenerator();
        var inputs = event.getInputs();
        var existingFileHelper = event.getExistingFileHelper();
        var lookupProvider = event.getLookupProvider();
        var includeClient = event.includeClient();
        var includeServer = event.includeServer();
        var output = generator.getPackOutput();

        // client
        generator.addProvider(includeClient, new BlockBenchConverter(output, existingFileHelper, inputs));
        generator.addProvider(includeClient, new LanguageGenerator(output));
        generator.addProvider(includeClient, new BlockStateGenerator(output, existingFileHelper));
        generator.addProvider(includeClient, new ItemModelGenerator(output, existingFileHelper));

        // server
        generator.addProvider(includeServer, new LootTableGenerator(output));
        generator.addProvider(includeServer, new RecipeGenerator(output));
        var blockTags = generator.addProvider(includeServer, new BlockTagGenerator(output, lookupProvider, existingFileHelper));
        generator.addProvider(includeServer, new ItemTagGenerator(output, lookupProvider, blockTags.contentsGetter(), existingFileHelper));
    }
}
