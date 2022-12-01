package xyz.apex.minecraft.fantasyfurniture.fabric.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public final class DataGenerators implements DataGeneratorEntrypoint
{
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator)
    {
        var blockTags = new BlockTagGenerator(generator);
        generator.addProvider(true, blockTags);
        generator.addProvider(true, new ItemTagGenerator(generator, blockTags));
    }
}
