package xyz.apex.minecraft.fantasyfurniture.royal.fabric;

import net.fabricmc.api.ModInitializer;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalFurnitureSet;

public final class RoyalFurnitureSetFabric implements RoyalFurnitureSet, ModInitializer
{
    public static final RoyalFurnitureSetFabric INSTANCE = new RoyalFurnitureSetFabric();

    @Override
    public void onInitialize()
    {
        bootstrap();
    }
}
