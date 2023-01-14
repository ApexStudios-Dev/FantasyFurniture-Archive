package xyz.apex.minecraft.fantasyfurniture.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeRenderTypes;
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
            MultiBlockRenderer.INSTANCE.get().setRenderer((blockRenderer, pose, buffer, level, pos, blockState, validPlacement, alpha, partialTick) -> {
                var baseRenderType = ForgeRenderTypes.TRANSLUCENT_ON_PARTICLES_TARGET.get();
                var consumer = new GhostVertexConsumer(buffer.getBuffer(baseRenderType), alpha);
                var model = blockRenderer.getBlockModel(blockState);

                for(var renderType : model.getRenderTypes(blockState, level.random, ModelData.EMPTY))
                {
                    blockRenderer.renderBatched(blockState, pos, level, pose, consumer, false, level.random, ModelData.EMPTY, renderType);
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
