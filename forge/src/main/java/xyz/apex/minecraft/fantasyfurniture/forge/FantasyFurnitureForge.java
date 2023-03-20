package xyz.apex.minecraft.fantasyfurniture.forge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.forge.data.FantasyFurnitureForgeData;

@Mod(FantasyFurniture.ID)
public final class FantasyFurnitureForge implements FantasyFurniture
{
    public FantasyFurnitureForge()
    {
        bootstrap();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(FantasyFurnitureForgeData::onGatherData);
    }
}
