package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.core.HolderLookup;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

public interface FantasyFurnitureForgeData
{
    static void onGatherData(GatherDataEvent event)
    {
        var generator = event.getGenerator();

        var client = event.includeClient();
        var server = event.includeServer();

        generator.addProvider(client, new BlockBenchConverter(event));
        generator.addProvider(client, new BlockStateGenerator(event));
        generator.addProvider(client, new ItemModelGenerator(event));

        generator.addProvider(client, new LanguageGenerator(event));

        var blockTags = generator.addProvider(server, new BlockTagsProvider(generator.getPackOutput(), event.getLookupProvider(), FantasyFurniture.ID, event.getExistingFileHelper()) {
            @Override protected void addTags(HolderLookup.Provider pProvider) {}
        });
        generator.addProvider(server, new ItemTagGenerator(event, blockTags.contentsGetter()));
        generator.addProvider(server, new EntityTypeTagGenerator(event));

        generator.addProvider(server, new LootTableGenerator(event));
        generator.addProvider(server, new RecipeGenerator(event));
    }
}
