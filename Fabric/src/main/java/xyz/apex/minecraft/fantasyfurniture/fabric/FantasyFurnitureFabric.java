package xyz.apex.minecraft.fantasyfurniture.fabric;

import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

import xyz.apex.minecraft.apexcore.fabric.platform.FabricModPlatform;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.client.renderer.MultiBlockRenderer;

public final class FantasyFurnitureFabric extends FabricModPlatform implements FantasyFurniture
{
    public static final FabricModPlatform INSTANCE = new FantasyFurnitureFabric();

    private FantasyFurnitureFabric()
    {
        super(ID, REGISTRAR);

        EnvExecutor.runInEnv(Env.CLIENT, () -> () -> WorldRenderEvents.AFTER_TRANSLUCENT.register(ctx -> MultiBlockRenderer.INSTANCE.get().render(ctx.matrixStack(), ctx.tickDelta(), ctx.camera())));
    }
}
