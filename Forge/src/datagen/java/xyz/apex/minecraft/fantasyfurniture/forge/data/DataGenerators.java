package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;

@Mod.EventBusSubscriber(modid = FantasyFurniture.ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class DataGenerators
{
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event)
    {
        var generator = event.getGenerator();

        var client = event.includeClient();
        var server = event.includeServer();

        var blockTags = new BlockTagGenerator(event);

        generator.addProvider(client, new BlockBenchConverter(event)); // conversion *MUST* run before block states
        generator.addProvider(client, new BlockStateGenerator(event));
        generator.addProvider(client, new ItemModelGenerator(event));

        generator.addProvider(client, new LanguageGenerator(event));

        generator.addProvider(server, blockTags);
        generator.addProvider(server, new ItemTagGenerator(event, blockTags));

        generator.addProvider(server, new LootTableGenerator(event));
        generator.addProvider(server, new RecipeGenerator(event));
    }
}
