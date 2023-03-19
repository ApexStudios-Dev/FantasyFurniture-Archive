/*
package xyz.apex.minecraft.fantasyfurniture.common.client.renderer;

import com.google.common.base.Suppliers;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import dev.architectury.utils.GameInstance;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

import net.minecraft.Util;
import net.minecraft.client.Camera;
import net.minecraft.client.GraphicsStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;

import xyz.apex.minecraft.apexcore.common.component.ComponentBlock;
import xyz.apex.minecraft.apexcore.common.component.ComponentTypes;
import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlockPattern;
import xyz.apex.minecraft.apexcore.common.util.function.Lazy;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.ModConfig;

import java.util.Map;
import java.util.OptionalDouble;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public final class MultiBlockRenderer
{
    public static final Supplier<MultiBlockRenderer> INSTANCE = Suppliers.memoize(MultiBlockRenderer::new);

    private PlatformRenderer renderer = (client, pose, consumer, ctx, blockState, model, r, g, b, light, overlay) -> client.getBlockRenderer().getModelRenderer().renderModel(pose, consumer, blockState, model, r, g, b, light, overlay);
    private final BiFunction<ResourceLocation, Boolean, RenderType> entityTranslucentCustom;
    private final RenderType linesCustom;
    private int customOverlayTint = -1;
    private boolean useCustomTint = false;
    private final Map<Block, BlockEntity> blockEntityCache = Maps.newHashMap();

    private MultiBlockRenderer()
    {
        entityTranslucentCustom = Util.memoize((texture, outline) -> RenderType.create(
                "%s:entity_translucent_no_depth".formatted(FantasyFurniture.ID),
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                256, false, true,
                RenderType.CompositeState
                        .builder()
                        // block texture
                        .setTextureState(new RenderStateShard.TextureStateShard(texture, false, false))
                        // translucency
                        .setShaderState(RenderStateShard.RENDERTYPE_ENTITY_TRANSLUCENT_SHADER)
                        .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                        .setLightmapState(RenderStateShard.LIGHTMAP) // render with proper block lighting
                        .setOverlayState(new CustomOverlay()) // used for overlay color (red when invalid placement)

                        .setCullState(RenderStateShard.CULL)
                        // disable depth test (see through walls)
                        .setWriteMaskState(RenderStateShard.COLOR_WRITE)
                        .setDepthTestState(new RenderStateShard.DepthTestStateShard("%s:not_equal".formatted(FantasyFurniture.ID), GL11.GL_NOTEQUAL))
                        .setLayeringState(RenderStateShard.POLYGON_OFFSET_LAYERING) // fixes z-fighting?, when in same space as other blocks
                        .createCompositeState(outline)
        ));

        linesCustom = RenderType.create(
                "%s:lines_no_depth".formatted(FantasyFurniture.ID),
                DefaultVertexFormat.POSITION_COLOR_NORMAL,
                VertexFormat.Mode.LINES,
                256,
                RenderType.CompositeState
                        .builder()
                        // translucency
                        .setShaderState(RenderStateShard.RENDERTYPE_LINES_SHADER)
                        .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                        // no line width, calculated based on window size
                        .setLineState(new RenderStateShard.LineStateShard(OptionalDouble.empty()))
                        .setLayeringState(RenderStateShard.VIEW_OFFSET_Z_LAYERING)
                        .setOutputState(RenderType.ITEM_ENTITY_TARGET)

                        .setCullState(RenderStateShard.NO_CULL)
                        // disable depth test (see through walls)
                        .setWriteMaskState(RenderStateShard.COLOR_WRITE)
                        .setDepthTestState(new RenderStateShard.DepthTestStateShard("%s:not_equal".formatted(FantasyFurniture.ID), GL11.GL_NOTEQUAL))
                        .createCompositeState(false)
        );
    }

    public RenderType getRenderType(ResourceLocation texture, boolean outline)
    {
        return entityTranslucentCustom.apply(texture, outline);
    }

    public void setRenderer(PlatformRenderer renderer)
    {
        this.renderer = renderer;
    }

    public void render(PoseStack pose, float partialTick, Camera camera)
    {
        var client = GameInstance.getClient();

        // these are required to be non-null, which they should always be when rendering in level contexts
        // but better safe than sorry
        if(client.level == null || client.player == null || client.gameMode == null) return;
        if(client.player.isSpectator()) return; // do not render for spectators

        var reachDist = client.gameMode.getPickRange();
        var hit = client.player.pick(reachDist, partialTick, false);
        if(hit instanceof BlockHitResult blockHit)
        {
            // try render for main hand
            // then try render offhand
            // - if we successfully render something, do not render for offhand
            // - if block was not multi block and could not be placed
            if(tryAndRender(client, pose, camera, client.player, InteractionHand.MAIN_HAND, blockHit)) return;
            tryAndRender(client, pose, camera, client.player, InteractionHand.OFF_HAND, blockHit);
        }
    }

    private boolean tryAndRender(Minecraft client, PoseStack pose, Camera camera, Player player, InteractionHand hand, BlockHitResult hit)
    {
        var stack = player.getItemInHand(hand);
        if(stack.isEmpty()) return false; // nothing to render
        var block = Block.byItem(stack.getItem());
        if(!(block instanceof ComponentBlock componentBlock)) return false;

        var ctx = new BlockPlaceContext(player, hand, stack, hit);
        var renderPos = getRenderPos(ctx);
        // failed to get position to render at
        // this usually means, player is not looking at a block
        // [looking at a air block, block placement, requires being placed against another block]
        if(renderPos == null) return false;

        var renderBlockState = getRenderBlockState(componentBlock, ctx);
        if(renderBlockState.getRenderShape() == RenderShape.INVISIBLE) return false; // block state set to have no render
        var valid = canPlaceBlockAt(ctx, componentBlock, renderBlockState);

        // no matter if block can be placed or not
        // we always render if block is a multi block
        // as visualizer changes color based on
        // if placement would be successful or not
        if(componentBlock.hasComponent(ComponentTypes.MULTI_BLOCK))
        {
            var camPos = camera.getPosition();
            var alpha = getAlpha();

            pose.pushPose();
            pose.translate(-camPos.x, -camPos.y, -camPos.z);
            pose.translate(renderPos.getX(), renderPos.getY(), renderPos.getZ());

            renderBlockState(client, pose, ctx, renderPos, renderBlockState, valid, alpha);
            renderVoxelShape(client, pose, ctx, renderPos, renderBlockState, valid, alpha);

            pose.popPose();
            return true;
        }

        // skip next render attempt, if block in current hand can be placed
        // but not a multi block, this is to stop
        // rendering for other hand, if current hand can be placed
        // but only if current hand is not a multi block
        return !valid || !isPlacementBlocked(ctx, renderPos, componentBlock, renderBlockState);
    }

    @SuppressWarnings("deprecation")
    private void renderBlockState(Minecraft client, PoseStack pose, BlockPlaceContext ctx, BlockPos pos, BlockState blockState, boolean validPlacement, int alpha)
    {
        if(blockState.getRenderShape() != RenderShape.MODEL) return;

        // results in entity cutout, which doesn't have transparency for ghosting / alpha
        // var renderType = ItemBlockRenderTypes.getRenderType(blockState, false);
        var renderType = getRenderType(TextureAtlas.LOCATION_BLOCKS, false);
        // determine which vertex consumer we should be using
        var bufferSource = client.renderBuffers().bufferSource();
        var consumer = bufferSource.getBuffer(renderType);
        // ghosting effect enabled? use ghosting vertex consumer
        if(ModConfig.MultiBlockRenderer.usesBreathingEffect) consumer = new GhostVertexConsumer(consumer, alpha);
        // use red overlay, when placement is invalid
        // var overlay = validPlacement ? OverlayTexture.NO_OVERLAY : OverlayTexture.RED_OVERLAY_V;
        // use lighting as if block was placed in world
        var light = LevelRenderer.getLightColor(ctx.getLevel(), blockState, pos);
        var model = client.getBlockRenderer().getBlockModel(blockState);
        // render using tint colors (royal furniture set can be dyed)
        var blockColor = client.getBlockColors().getColor(blockState, null, null, 0);
        var r = FastColor.ARGB32.red(blockColor) / 255F;
        var g = FastColor.ARGB32.green(blockColor) / 255F;
        var b = FastColor.ARGB32.blue(blockColor) / 255F;
        setCustomOverlayTint(validPlacement ? ModConfig.MultiBlockRenderer.validColorModel : ModConfig.MultiBlockRenderer.invalidColorModel);
        // render the model, with above properties
        renderer.renderBlockState(client, pose.last(), consumer, ctx, blockState, model, r, g, b, light, OverlayTexture.NO_OVERLAY);
        bufferSource.endBatch(renderType);
    }

    private void renderVoxelShape(Minecraft client, PoseStack pose, BlockPlaceContext ctx, BlockPos pos, BlockState blockState, boolean validPlacement, int alpha)
    {
        if(!ModConfig.MultiBlockRenderer.renderModelOutline) return;

        var shape = blockState.getShape(ctx.getLevel(), pos);
        if(shape.isEmpty()) return;

        var last = pose.last();
        var matrix = last.pose();
        var normal = last.normal();

        var bufferSource = client.renderBuffers().bufferSource();
        var consumer = bufferSource.getBuffer(linesCustom);

        var x = 0;
        var y = 0;
        var z = 0;

        var color = validPlacement ? ModConfig.MultiBlockRenderer.validColorOutline : ModConfig.MultiBlockRenderer.invalidColorOutline;
        var r = FastColor.ARGB32.red(color) / 255F;
        var g = FastColor.ARGB32.green(color) / 255F;
        var b = FastColor.ARGB32.blue(color) / 255F;
        var a = ModConfig.MultiBlockRenderer.usesBreathingEffect ? alpha : 1F;

        shape.forAllEdges((minX, minY, minZ, maxX, maxY, maxZ) -> {
            var distX = (float) (maxX - minX);
            var distY = (float) (maxY - minY);
            var distZ = (float) (maxZ - minZ);
            var t = Mth.sqrt(distX * distX + distY * distY + distZ * distZ);

            consumer.vertex(matrix, (float) (minX + x), (float) (minY + y), (float) (minZ + z)).color(r, g, b, a).normal(normal, distX /= t, distY /= t, distZ /= t).endVertex();
            consumer.vertex(matrix, (float) (maxX + x), (float) (maxY + y), (float) (maxZ + z)).color(r, g, b, a).normal(normal, distX, distY, distZ).endVertex();
        });

        bufferSource.endBatch(linesCustom);
    }

    private int getAlpha()
    {
        if(GameInstance.getClient().options.graphicsMode().get() == GraphicsStatus.FAST) return 127;

        var period = 2500D;
        var timer = System.currentTimeMillis() % period;
        var offset = Mth.cos((float) ((2D / period) * Math.PI * timer));
        return (int) ((.55D - .2D * offset) * 255D);
    }

    private BlockState getRenderBlockState(ComponentBlock block, BlockPlaceContext ctx)
    {
        var vanilla = block.toBlock();
        var blockState = vanilla.getStateForPlacement(ctx);

        // approximate placement state
        if(blockState == null)
        {
            blockState = vanilla.defaultBlockState();
            // make placement face towards player, for facing blocks
            if(blockState.hasProperty(HorizontalDirectionalBlock.FACING)) blockState = blockState.setValue(HorizontalDirectionalBlock.FACING, ctx.getHorizontalDirection().getOpposite());
        }

        // let this block handle what block state we should render
        if(block instanceof MultiBlockRendererPlacementModifier modifier) blockState = modifier.getBlockStateForRenderPlacement(ctx);

        // render from origin point only, for multi blocks
        var multiBlockComponent = block.getComponent(ComponentTypes.MULTI_BLOCK);

        if(multiBlockComponent != null)
        {
            var multiBlockType = multiBlockComponent.getMultiBlockType();
            var multiBlockPlacementState = multiBlockType.getStateForPlacement(blockState, ctx);
            if(multiBlockPlacementState != null) blockState = multiBlockPlacementState;
            blockState = multiBlockType.setIndex(blockState, MultiBlockPattern.ORIGIN_INDEX);
        }

        // this shouldn't rly affect anything, as we are not rendering fluid states,
        // but we also don't want water logging to affect any of the placement checks
        // set as false, to ignore if waterlogged or not
        if(blockState.hasProperty(BlockStateProperties.WATERLOGGED)) blockState = blockState.setValue(BlockStateProperties.WATERLOGGED, false);

        return blockState;
    }

    @Nullable
    private BlockPos getRenderPos(BlockPlaceContext ctx)
    {
        var renderPos = ctx.getClickedPos();
        if(ctx.getLevel().isEmptyBlock(renderPos.relative(ctx.getClickedFace(), -1))) return null;
        return renderPos;
    }

    private boolean canPlaceBlockAt(BlockPlaceContext ctx, ComponentBlock block, BlockState blockState)
    {
        var pos = ctx.getClickedPos();

        if(isPlacementBlocked(ctx, pos, block, blockState)) return false;

        // have multi blocks check every sub position
        var multiBlockComponent = block.getComponent(ComponentTypes.MULTI_BLOCK);

        if(multiBlockComponent != null)
        {
            var multiBlockType = multiBlockComponent.getMultiBlockType();
            var originPos = multiBlockType.getOriginPos(blockState, pos);
            var localPositions = multiBlockType.getLocalPositions();
            var ignoreIndex = multiBlockType.getIndex(blockState);

            for(var i = 0; i < localPositions.size(); i++)
            {
                if(i == ignoreIndex) continue; // already check in 'if' at start of method call
                var worldPos = multiBlockType.getWorldSpaceFromLocalSpace(blockState, originPos, localPositions.get(i));
                var worldBlockState = multiBlockType.setIndex(blockState, i);
                var worldComponentBlock = worldBlockState.getBlock() instanceof ComponentBlock cb ? cb : block;

                if(isPlacementBlocked(ctx, worldPos, worldComponentBlock, worldBlockState)) return false;
            }
        }

        return true;
    }

    // does not check all sub positions of multi-blocks
    // assumes is called for each multi-block sub position
    private boolean isPlacementBlocked(BlockPlaceContext ctx, BlockPos pos, ComponentBlock block, BlockState blockState)
    {
        var player = ctx.getPlayer();
        var level = ctx.getLevel();

        if(!blockState.canSurvive(level, pos)) return true;

        // block collides with player
        if(player != null && doesEntityBlockPlacement(player, pos, blockState)) return true;

        // block collides with some entity
        var nearbyEntities = level.getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, null, AABB.ofSize(pos.getCenter(), 1D, 1D, 1D));

        for(var nearbyEntity : nearbyEntities)
        {
            if(doesEntityBlockPlacement(nearbyEntity, pos, blockState)) return true;
        }

        // placement area is obstructed
        var collisionCtx = player == null ? CollisionContext.empty() : CollisionContext.of(player);
        if(!level.isUnobstructed(blockState, pos, collisionCtx)) return true;

        // check if multi block placement is allowed
        var multiBlockComponent = block.getComponent(ComponentTypes.MULTI_BLOCK);

        if(multiBlockComponent != null)
        {
            var existingBlock = level.getBlockState(pos);
            if(!multiBlockComponent.getMultiBlockType().passesPlacementTests(level, pos, blockState, existingBlock)) return true;
        }

        return false;
    }

    private boolean doesEntityBlockPlacement(Entity entity, BlockPos pos, BlockState blockState)
    {
        return entity.isAlive() && entity.isColliding(pos, blockState);
    }

    private void setCustomOverlayTint(int customOverlayTint)
    {
        this.customOverlayTint = customOverlayTint;
        useCustomTint = true;
    }

    @FunctionalInterface
    public interface PlatformRenderer
    {
        void renderBlockState(Minecraft client, PoseStack.Pose pose, VertexConsumer consumer, BlockPlaceContext ctx, BlockState blockState, BakedModel model, float r, float g, float b, int light, int overlay);
    }

    private final class CustomOverlay extends RenderStateShard.OverlayStateShard
    {
        private boolean resetShaderColor = false;

        private CustomOverlay()
        {
            super(true);
        }

        @Override
        public void setupRenderState()
        {
            super.setupRenderState();

            if(useCustomTint)
            {
                var r = FastColor.ARGB32.red(customOverlayTint) / 255F;
                var g = FastColor.ARGB32.green(customOverlayTint) / 255F;
                var b = FastColor.ARGB32.blue(customOverlayTint) / 255F;
                RenderSystem.setShaderColor(r, g, b, 1F);

                customOverlayTint = -1;
                useCustomTint = false;
                resetShaderColor = true;
            }
        }

        @Override
        public void clearRenderState()
        {
            super.clearRenderState();

            if(resetShaderColor)
            {
                RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
                resetShaderColor = false;
            }
        }
    }
}
*/
