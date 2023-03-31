package xyz.apex.minecraft.fantasyfurniture.necrolord.forge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.apex.minecraft.fantasyfurniture.necrolord.common.NecrolordFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.necrolord.forge.data.NecrolordFurnitureSetForgeData;

@Mod(NecrolordFurnitureSet.ID)
public final class NecrolordFurnitureSetForge implements NecrolordFurnitureSet
{
    public NecrolordFurnitureSetForge()
    {
        bootstrap();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(NecrolordFurnitureSetForgeData::onGatherData);
    }
}
