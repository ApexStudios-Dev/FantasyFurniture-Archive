package xyz.apex.minecraft.fantasyfurniture.nordic.forge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.nordic.forge.data.NordicFurnitureSetForgeData;

@Mod(NordicFurnitureSet.ID)
public class NordicFurnitureSetForge implements NordicFurnitureSet
{
    public NordicFurnitureSetForge()
    {
        bootstrap();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(NordicFurnitureSetForgeData::onGatherData);
    }
}
