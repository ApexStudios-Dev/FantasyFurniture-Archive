package xyz.apex.minecraft.fantasyfurniture.neoforge.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

@ApiStatus.Internal
final class ItemModelGenerator extends ItemModelProvider
{
    ItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, FantasyFurniture.ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
    }
}
