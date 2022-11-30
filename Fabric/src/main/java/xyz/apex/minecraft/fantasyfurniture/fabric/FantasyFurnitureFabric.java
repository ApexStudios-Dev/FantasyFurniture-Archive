package xyz.apex.minecraft.fantasyfurniture.fabric;

import net.fabricmc.api.ModInitializer;

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;

public final class FantasyFurnitureFabric implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        FantasyFurniture.bootstrap();
    }
}
