package xyz.apex.minecraft.fantasyfurniture.nordic.fabric.entrypoint;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.resgen.ApexDataProvider;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;

@ApiStatus.Internal
public final class NordicFurnitureSetDataGeneratorEntrypoint implements DataGeneratorEntrypoint
{
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator)
    {
        ApexDataProvider.register(NordicFurnitureSet.ID, func -> generator.createPack().addProvider(func::apply));
    }
}
