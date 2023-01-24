package xyz.apex.minecraft.fantasyfurniture.forge;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

import xyz.apex.minecraft.apexcore.forge.platform.ForgeModPlatform;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.client.renderer.MultiBlockRenderer;

@Mod(FantasyFurniture.ID)
public final class FantasyFurnitureForge extends ForgeModPlatform implements FantasyFurniture
{
    public FantasyFurnitureForge()
    {
        super(ID, REGISTRAR);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> Client::new);
    }

    @OnlyIn(Dist.CLIENT)
    private static final class Client
    {
        private Client()
        {
            MultiBlockRenderer.INSTANCE.get().setRenderer(this::renderBlockState);
            MinecraftForge.EVENT_BUS.addListener(this::onRenderLevelStage);
        }

        private void onRenderLevelStage(RenderLevelStageEvent event)
        {
            if(event.getStage() != RenderLevelStageEvent.Stage.AFTER_PARTICLES) return;
            MultiBlockRenderer.INSTANCE.get().render(event.getPoseStack(), event.getPartialTick(), event.getCamera());
        }

        private void renderBlockState(Minecraft client, PoseStack.Pose pose, VertexConsumer consumer, BlockPlaceContext ctx, BlockState blockState, BakedModel model, float r, float g, float b, int light, int overlay)
        {
            var renderer = client.getBlockRenderer().getModelRenderer();
            var rng = ctx.getLevel().random;

            for(var renderType : model.getRenderTypes(blockState, rng, ModelData.EMPTY))
            {
                renderer.renderModel(pose, consumer, blockState, model, r, g, b, light, overlay, ModelData.EMPTY, renderType);
            }
        }
    }
}
