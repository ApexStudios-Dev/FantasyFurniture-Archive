package xyz.apex.minecraft.fantasyfurniture.common;

import net.minecraft.world.item.CreativeModeTabs;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.lib.hook.CreativeModeTabHooks;
import xyz.apex.minecraft.apexcore.common.lib.registry.RegistrarManager;
import xyz.apex.minecraft.apexcore.common.lib.registry.builders.BuilderManager;
import xyz.apex.minecraft.fantasyfurniture.common.feature.station.FurnitureStation;

import java.util.Objects;

@ApiStatus.Internal
@ApiStatus.NonExtendable
public abstract class FantasyFurniture
{
    public static final String ID = "fantasyfurniture";
    public static final Logger LOGGER = LogManager.getLogger();

    public static final RegistrarManager REGISTRAR_MANAGER = RegistrarManager.get(ID);
    public static final BuilderManager<BuilderManager.Impl> BUILDERS = BuilderManager.create(REGISTRAR_MANAGER);

    @Nullable
    private static FantasyFurniture INSTANCE = null;

    protected FantasyFurniture()
    {
        Validate.isTrue(INSTANCE == null);
        INSTANCE = this;
    }

    protected void bootstrap()
    {
        FurnitureStation.bootstrap();
        REGISTRAR_MANAGER.register();
        CreativeModeTabHooks.get().modify(CreativeModeTabs.FUNCTIONAL_BLOCKS, output -> output.accept(FurnitureStation.BLOCK));
    }

    public static FantasyFurniture get()
    {
        return Objects.requireNonNull(INSTANCE);
    }
}
