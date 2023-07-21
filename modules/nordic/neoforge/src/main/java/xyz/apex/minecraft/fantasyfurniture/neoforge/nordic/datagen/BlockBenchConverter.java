package xyz.apex.minecraft.fantasyfurniture.neoforge.nordic.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.neoforge.lib.datagen.bbmodel.BlockBenchModelConverter;
import xyz.apex.minecraft.fantasyfurniture.common.nordic.NordicFurnitureSet;

import java.nio.file.Path;
import java.util.Collection;

@ApiStatus.Internal
final class BlockBenchConverter extends BlockBenchModelConverter
{
    BlockBenchConverter(PackOutput output, ExistingFileHelper existingFileHelper, Collection<Path> inputFolders)
    {
        super(output, NordicFurnitureSet.ID, existingFileHelper, inputFolders);
    }

    @Override
    protected void convertModels()
    {
    }
}
