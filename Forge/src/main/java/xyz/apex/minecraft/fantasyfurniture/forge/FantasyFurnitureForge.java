package xyz.apex.minecraft.fantasyfurniture.forge;

import net.minecraftforge.fml.common.Mod;

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;

@Mod(FantasyFurniture.ID)
public final class FantasyFurnitureForge
{
    public FantasyFurnitureForge()
    {
        FantasyFurniture.bootstrap();
    }
}
