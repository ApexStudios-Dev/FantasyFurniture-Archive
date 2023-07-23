package xyz.apex.minecraft.fantasyfurniture.common;

import net.minecraft.world.item.CreativeModeTabs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import xyz.apex.lib.Services;
import xyz.apex.minecraft.apexcore.common.lib.hook.CreativeModeTabHooks;
import xyz.apex.minecraft.apexcore.common.lib.registry.RegistrarManager;
import xyz.apex.minecraft.apexcore.common.lib.registry.builders.BuilderManager;
import xyz.apex.minecraft.fantasyfurniture.common.feature.seat.Seat;
import xyz.apex.minecraft.fantasyfurniture.common.feature.station.FurnitureStation;

@ApiStatus.Internal
@ApiStatus.NonExtendable
public interface FantasyFurniture
{
    String ID = "fantasyfurniture";
    Logger LOGGER = LogManager.getLogger();
    FantasyFurniture INSTANCE = Services.singleton(FantasyFurniture.class);

    RegistrarManager REGISTRAR_MANAGER = RegistrarManager.get(ID);
    BuilderManager<BuilderManager.Impl> BUILDERS = BuilderManager.create(REGISTRAR_MANAGER);

    @MustBeInvokedByOverriders
    default void bootstrap()
    {
        FurnitureStation.bootstrap();
        Seat.bootstrap();
        REGISTRAR_MANAGER.register();
        CreativeModeTabHooks.get().modify(CreativeModeTabs.FUNCTIONAL_BLOCKS, output -> output.accept(FurnitureStation.BLOCK));
    }
}
