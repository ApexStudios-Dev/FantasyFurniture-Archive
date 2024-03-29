package xyz.apex.minecraft.fantasyfurniture.complete.fabric.entrypoint;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.resgen.ApexDataProvider;
import xyz.apex.minecraft.fantasyfurniture.complete.common.CompleteFurnitureSet;

@ApiStatus.Internal
public final class CompleteFurnitureSetDataGeneratorEntrypoint implements DataGeneratorEntrypoint
{
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator)
    {
        ApexDataProvider.register(CompleteFurnitureSet.ID, func -> generator.createPack().addProvider(func::apply));
    }
}
