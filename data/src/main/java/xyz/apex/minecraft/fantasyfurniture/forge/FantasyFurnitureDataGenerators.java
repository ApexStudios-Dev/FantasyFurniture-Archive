package xyz.apex.minecraft.fantasyfurniture.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

@Mod.EventBusSubscriber(modid = FantasyFurniture.ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class FantasyFurnitureDataGenerators
{
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event)
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
        generator.addProvider(server, new ItemTagGenerator(event, output, blockTags));
        generator.addProvider(server, new EntityTypeTagGenerator(event, output));

        generator.addProvider(server, new LootTableGenerator(output));
        generator.addProvider(server, new RecipeGenerator(output));
    }
}
