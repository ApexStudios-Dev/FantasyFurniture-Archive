package xyz.apex.minecraft.fantasyfurniture.forge;

import net.minecraftforge.fml.common.Mod;

import xyz.apex.minecraft.apexcore.forge.platform.ForgeModPlatform;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;

@Mod(FantasyFurniture.ID)
public final class FantasyFurnitureForge extends ForgeModPlatform implements FantasyFurniture
{
    public FantasyFurnitureForge()
    {
        super(ID, REGISTRAR);
    }
}
