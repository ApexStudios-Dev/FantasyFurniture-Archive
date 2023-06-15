package xyz.apex.minecraft.fantasyfurniture.common;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.lib.registry.RegistrarManager;

import java.util.Objects;

@ApiStatus.Internal
@ApiStatus.NonExtendable
public abstract class FantasyFurniture
{
    public static final String ID = "fantasyfurniture";
    public static final Logger LOGGER = LogManager.getLogger();

    @Nullable
    private static FantasyFurniture INSTANCE = null;

    protected FantasyFurniture()
    {
        Validate.isTrue(INSTANCE == null);
        INSTANCE = this;
    }

    protected void bootstrap()
    {
        RegistrarManager.register(ID);
    }

    public static FantasyFurniture get()
    {
        return Objects.requireNonNull(INSTANCE);
    }
}
