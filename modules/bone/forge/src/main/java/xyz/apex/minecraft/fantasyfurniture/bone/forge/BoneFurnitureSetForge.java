package xyz.apex.minecraft.fantasyfurniture.bone.forge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.bone.forge.data.BoneFurnitureSetForgeData;

@Mod(BoneFurnitureSet.ID)
public final class BoneFurnitureSetForge implements BoneFurnitureSet
{
    public BoneFurnitureSetForge()
    {
        bootstrap();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(BoneFurnitureSetForgeData::onGatherData);
    }
}
