package xyz.apex.minecraft.fantasyfurniture.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

import xyz.apex.minecraft.apexcore.forge.platform.ForgeModPlatform;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.client.renderer.MultiBlockRenderer;

@Mod(FantasyFurniture.ID)
public final class FantasyFurnitureForge extends ForgeModPlatform implements FantasyFurniture
{
    public FantasyFurnitureForge()
    {
        super(ID, REGISTRAR);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            MultiBlockRenderer.INSTANCE.get().setRenderer((client, pose, consumer, ctx, blockState, model, r, g, b, light, overlay) -> {
                var renderer = client.getBlockRenderer().getModelRenderer();
                var rng = ctx.getLevel().random;

                for(var renderType : model.getRenderTypes(blockState, rng, ModelData.EMPTY))
                {
                    renderer.renderModel(pose, consumer, blockState, model, r, g, b, light, overlay, ModelData.EMPTY, renderType);
                }
            });

            MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, RenderLevelStageEvent.class, event -> {
                if(event.getStage() != RenderLevelStageEvent.Stage.AFTER_PARTICLES) return;
                MultiBlockRenderer.INSTANCE.get().render(event.getPoseStack(), event.getPartialTick(), event.getCamera());
            });
        });
    }
}
