package xyz.apex.minecraft.fantasyfurniture.fabric;

import xyz.apex.minecraft.apexcore.fabric.platform.FabricModPlatform;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;

public final class FantasyFurnitureFabric extends FabricModPlatform implements FantasyFurniture
{
    public static final FabricModPlatform INSTANCE = new FantasyFurnitureFabric();

    private FantasyFurnitureFabric()
    {
        super(ID, REGISTRAR);
    }
}
