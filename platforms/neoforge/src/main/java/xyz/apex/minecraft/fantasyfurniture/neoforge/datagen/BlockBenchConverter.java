package xyz.apex.minecraft.fantasyfurniture.neoforge.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.neoforge.lib.datagen.bbmodel.BlockBenchModelConverter;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.feature.station.FurnitureStation;

import java.nio.file.Path;
import java.util.Collection;

@ApiStatus.Internal
final class BlockBenchConverter extends BlockBenchModelConverter
{
    BlockBenchConverter(PackOutput output, ExistingFileHelper existingFileHelper, Collection<Path> inputFolders)
    {
        super(output, FantasyFurniture.ID, existingFileHelper, inputFolders);
    }

    @Override
    protected void convertModels()
    {
        var blockBlock = blockModels().getExistingFile(new ResourceLocation("minecraft", "block/block"));

        blockModelBuilder(FurnitureStation.BLOCK.getRegistryName().withPrefix("block/"))
                .parent(blockBlock);
    }
}
