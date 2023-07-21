package xyz.apex.minecraft.fantasyfurniture.neoforge;

import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.neoforge.lib.EventBuses;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.neoforge.datagen.FantasyFurnitureDataGen;

@ApiStatus.Internal
@Mod(FantasyFurniture.ID)
public final class FantasyFurnitureImpl extends FantasyFurniture
{
    public FantasyFurnitureImpl()
    {
        super();

        bootstrap();
        PhysicalSide.CLIENT.runWhenOn(() -> FantasyFurnitureClientImpl::new);
        FantasyFurnitureDataGen.bootstrap();
        EventBuses.registerForJavaFML();
    }
}
