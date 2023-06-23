package xyz.apex.minecraft.fantasyfurniture.forge;

import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.forge.lib.EventBuses;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

@ApiStatus.Internal
@Mod(FantasyFurniture.ID)
public final class FantasyFurnitureImpl extends FantasyFurniture
{
    public FantasyFurnitureImpl()
    {
        super();

        bootstrap();
        PhysicalSide.CLIENT.runWhenOn(() -> FantasyFurnitureClientImpl::new);
        EventBuses.registerForJavaFML();
    }
}
