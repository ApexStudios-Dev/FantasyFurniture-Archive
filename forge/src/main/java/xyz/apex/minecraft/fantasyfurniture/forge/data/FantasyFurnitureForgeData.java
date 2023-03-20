package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;

public interface FantasyFurnitureForgeData
{
    static void onGatherData(GatherDataEvent event)
    {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();

        var client = event.includeClient();
        var server = event.includeServer();

        generator.addProvider(client, new BlockBenchConverter(event));
        generator.addProvider(client, new BlockStateGenerator(event, output));
        generator.addProvider(client, new ItemModelGenerator(event, output));

        generator.addProvider(client, new LanguageGenerator(output));

        var blockTags = generator.addProvider(server, new BlockTagGenerator(event, output));
        generator.addProvider(server, new ItemTagGenerator(event, output, blockTags.contentsGetter()));
        generator.addProvider(server, new EntityTypeTagGenerator(event, output));

        generator.addProvider(server, new LootTableGenerator(output));
        generator.addProvider(server, new RecipeGenerator(output));
    }
}
