package xyz.apex.minecraft.fantasyfurniture.bone.fabric;

import net.fabricmc.api.ModInitializer;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneFurnitureSet;

public final class BoneFurnitureSetFabric implements BoneFurnitureSet, ModInitializer
{
    public static final BoneFurnitureSetFabric INSTANCE = new BoneFurnitureSetFabric();

    @Override
    public void onInitialize()
    {
        bootstrap();
    }
}
