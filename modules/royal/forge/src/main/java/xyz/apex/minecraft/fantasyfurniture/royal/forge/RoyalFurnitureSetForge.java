package xyz.apex.minecraft.fantasyfurniture.royal.forge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.royal.forge.data.RoyalFurnitureSetForgeData;

@Mod(RoyalFurnitureSet.ID)
public final class RoyalFurnitureSetForge implements RoyalFurnitureSet
{
    public RoyalFurnitureSetForge()
    {
        bootstrap();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(RoyalFurnitureSetForgeData::onGatherData);
    }
}
