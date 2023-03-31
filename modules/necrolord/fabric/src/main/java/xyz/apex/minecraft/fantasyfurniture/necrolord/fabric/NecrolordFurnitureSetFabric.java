package xyz.apex.minecraft.fantasyfurniture.necrolord.fabric;

import net.fabricmc.api.ModInitializer;
import xyz.apex.minecraft.fantasyfurniture.necrolord.common.NecrolordFurnitureSet;

public final class NecrolordFurnitureSetFabric implements NecrolordFurnitureSet, ModInitializer
{
    public static final NecrolordFurnitureSetFabric INSTANCE = new NecrolordFurnitureSetFabric();

    @Override
    public void onInitialize()
    {
        bootstrap();
    }
}
