package xyz.apex.minecraft.fantasyfurniture.venthyr.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;

public interface VenthyrFurnitureSetForgeData
{
    static void onGatherData(GatherDataEvent event)
    {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();

        var client = event.includeClient();
        var server = event.includeServer();

        generator.addProvider(client, new BlockBenchConverter(event));
        generator.addProvider(client, new BlockStateGenerator(event));
        generator.addProvider(client, new ItemModelGenerator(event));

        generator.addProvider(client, new LanguageGenerator(event));

        var blockTags = generator.addProvider(server, new BlockTagGenerator(event, output));
        generator.addProvider(server, new ItemTagGenerator(event, blockTags.contentsGetter()));

        generator.addProvider(server, new LootTableGenerator(event));
        generator.addProvider(server, new RecipeGenerator(event));
    }
}
