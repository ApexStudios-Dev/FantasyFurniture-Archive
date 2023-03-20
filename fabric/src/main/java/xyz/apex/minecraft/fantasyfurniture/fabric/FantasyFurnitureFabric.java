package xyz.apex.minecraft.fantasyfurniture.fabric;

import net.fabricmc.api.ModInitializer;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

public final class FantasyFurnitureFabric implements FantasyFurniture, ModInitializer
{
    public static final FantasyFurnitureFabric INSTANCE = new FantasyFurnitureFabric();

    private FantasyFurnitureFabric() {}

    @Override
    public void onInitialize()
    {
        bootstrap();
    }
}
