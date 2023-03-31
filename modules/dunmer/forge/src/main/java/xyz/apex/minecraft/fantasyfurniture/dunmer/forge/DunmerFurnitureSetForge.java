package xyz.apex.minecraft.fantasyfurniture.dunmer.forge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.DunmerFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.dunmer.forge.data.DunmerFurnitureSetForgeData;

@Mod(DunmerFurnitureSet.ID)
public class DunmerFurnitureSetForge implements DunmerFurnitureSet
{
    public DunmerFurnitureSetForge()
    {
        bootstrap();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(DunmerFurnitureSetForgeData::onGatherData);
    }
}
