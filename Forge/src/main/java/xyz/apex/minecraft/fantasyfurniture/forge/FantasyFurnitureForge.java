package xyz.apex.minecraft.fantasyfurniture.forge;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.util.FastColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

import xyz.apex.minecraft.apexcore.forge.platform.ForgeModPlatform;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.client.renderer.GhostVertexConsumer;
import xyz.apex.minecraft.fantasyfurniture.shared.client.renderer.MultiBlockRenderer;

@Mod(FantasyFurniture.ID)
public final class FantasyFurnitureForge extends ForgeModPlatform implements FantasyFurniture
{
    public FantasyFurnitureForge()
    {
        super(ID, REGISTRAR);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            MultiBlockRenderer.INSTANCE.get().setRenderer((blockColors, blockRenderer, pose, buffer, level, pos, blockState, validPlacement, alpha, partialTick) -> {
                var baseRenderType = MultiBlockRenderer.INSTANCE.get().getRenderType(TextureAtlas.LOCATION_BLOCKS, false);
                var consumer = new GhostVertexConsumer(buffer.getBuffer(baseRenderType), alpha);
                var overlay = validPlacement ? OverlayTexture.NO_OVERLAY : OverlayTexture.RED_OVERLAY_V;
                var light = LevelRenderer.getLightColor(level, blockState, pos);
                var model = blockRenderer.getBlockModel(blockState);
                var blockColor = blockColors.getColor(blockState, null, null, 0);
                var r = FastColor.ARGB32.red(blockColor) / 255F;
                var g = FastColor.ARGB32.green(blockColor) / 255F;
                var b = FastColor.ARGB32.blue(blockColor) / 255F;

                for(var renderType : model.getRenderTypes(blockState, level.random, ModelData.EMPTY))
                {
                    blockRenderer.getModelRenderer().renderModel(pose.last(), consumer, blockState, model, r, g, b, light, overlay, ModelData.EMPTY, renderType);
                }

                buffer.endBatch(baseRenderType);
            });

            MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, RenderLevelStageEvent.class, event -> {
                if(event.getStage() != RenderLevelStageEvent.Stage.AFTER_PARTICLES) return;
                MultiBlockRenderer.INSTANCE.get().render(event.getLevelRenderer(), event.getPoseStack(), event.getPartialTick(), event.getCamera(), event.getFrustum());
            });
        });
    }
}
