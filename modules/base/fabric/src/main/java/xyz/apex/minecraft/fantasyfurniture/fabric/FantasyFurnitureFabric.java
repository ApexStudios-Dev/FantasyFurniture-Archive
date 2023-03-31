package xyz.apex.minecraft.fantasyfurniture.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import xyz.apex.minecraft.apexcore.common.platform.Side;
import xyz.apex.minecraft.apexcore.common.platform.SideExecutor;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.client.renderer.MultiBlockRenderer;

public final class FantasyFurnitureFabric implements FantasyFurniture, ModInitializer
{
    public static final FantasyFurnitureFabric INSTANCE = new FantasyFurnitureFabric();

    private FantasyFurnitureFabric() {}

    @Override
    public void onInitialize()
    {
        bootstrap();
        SideExecutor.runWhenOn(Side.CLIENT, () -> Client::new);
    }

    @Environment(EnvType.CLIENT)
    private static final class Client
    {
        private Client()
        {
            WorldRenderEvents.AFTER_TRANSLUCENT.register(ctx -> MultiBlockRenderer.INSTANCE.get().render(ctx.matrixStack(), ctx.tickDelta(), ctx.camera()));
        }
    }
}
