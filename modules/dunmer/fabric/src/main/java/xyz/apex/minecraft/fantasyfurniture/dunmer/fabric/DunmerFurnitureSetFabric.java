package xyz.apex.minecraft.fantasyfurniture.dunmer.fabric;

import net.fabricmc.api.ModInitializer;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.DunmerFurnitureSet;

public final class DunmerFurnitureSetFabric implements DunmerFurnitureSet, ModInitializer
{
    public static final DunmerFurnitureSetFabric INSTANCE = new DunmerFurnitureSetFabric();

    @Override
    public void onInitialize()
    {
        bootstrap();
    }
}
