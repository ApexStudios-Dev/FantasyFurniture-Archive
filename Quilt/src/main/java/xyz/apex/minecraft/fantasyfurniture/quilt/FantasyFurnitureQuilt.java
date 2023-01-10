package xyz.apex.minecraft.fantasyfurniture.quilt;

import xyz.apex.minecraft.apexcore.quilt.platform.QuiltModPlatform;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;

public final class FantasyFurnitureQuilt extends QuiltModPlatform implements FantasyFurniture
{
    public static final QuiltModPlatform INSTANCE = new FantasyFurnitureQuilt();

    private FantasyFurnitureQuilt()
    {
        super(ID, REGISTRAR);
    }
}
