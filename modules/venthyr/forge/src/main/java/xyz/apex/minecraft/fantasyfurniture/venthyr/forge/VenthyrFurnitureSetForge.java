package xyz.apex.minecraft.fantasyfurniture.venthyr.forge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.venthyr.forge.data.VenthyrFurnitureSetForgeData;

@Mod(VenthyrFurnitureSet.ID)
public final class VenthyrFurnitureSetForge implements VenthyrFurnitureSet
{
    public VenthyrFurnitureSetForge()
    {
        bootstrap();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(VenthyrFurnitureSetForgeData::onGatherData);
    }
}
