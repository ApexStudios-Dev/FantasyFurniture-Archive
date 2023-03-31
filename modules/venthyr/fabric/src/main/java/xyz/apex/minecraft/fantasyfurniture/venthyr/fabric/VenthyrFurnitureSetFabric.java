package xyz.apex.minecraft.fantasyfurniture.venthyr.fabric;

import net.fabricmc.api.ModInitializer;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;

public final class VenthyrFurnitureSetFabric implements VenthyrFurnitureSet, ModInitializer
{
    public static final VenthyrFurnitureSetFabric INSTANCE = new VenthyrFurnitureSetFabric();

    @Override
    public void onInitialize()
    {
        bootstrap();
    }
}
