package xyz.apex.minecraft.fantasyfurniture.shared.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.architectury.utils.GameInstance;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;

import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlock;
import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.shared.util.function.Lazy;

import java.util.function.Supplier;

public final class MultiBlockRenderer
{
    public static final Supplier<MultiBlockRenderer> INSTANCE = Lazy.of(MultiBlockRenderer::new);

    private PlatformRenderer renderer = this::renderBlockState;

    private MultiBlockRenderer() {}

    public void setRenderer(PlatformRenderer renderer)
    {
        this.renderer = renderer;
    }

    public void render(LevelRenderer levelRenderer, PoseStack pose, float partialTick, Camera camera, Frustum frustum)
    {
        var client = GameInstance.getClient();

        // these are required to be non-null, which they should always be when rendering in level contexts
        // but better safe than sorry
        if(client.level == null || client.player == null || client.gameMode == null) return;
        if(client.player.isSpectator()) return; // do not render for spectators

        var renderPos = getRenderPos(client.level, client.player, client.gameMode, partialTick);
        // failed to get position to render at
        // this usually means, player is not looking at a block
        // [looking at a air block, block placement, requires being placed against another block]
        if(renderPos == null) return;

        var blockRenderer = client.getBlockRenderer();
        var buffer = client.renderBuffers().bufferSource();

        // try render for main hand
        // then try render offhand
        // - if we successfully render something, do not render for offhand
        // - if block was not multi block and could not be placed
        if(tryAndRender(blockRenderer, buffer, pose, camera, client.level, renderPos, client.player, InteractionHand.MAIN_HAND, partialTick)) return;
        tryAndRender(blockRenderer, buffer, pose, camera, client.level, renderPos, client.player, InteractionHand.OFF_HAND, partialTick);
    }

    private boolean tryAndRender(BlockRenderDispatcher blockRenderer, MultiBufferSource.BufferSource buffer, PoseStack pose, Camera camera, Level level, BlockPos pos, Player player, InteractionHand hand, float partialTick)
    {
        var stack = player.getItemInHand(hand);
        if(stack.isEmpty()) return false; // nothing to render
        var block = Block.byItem(stack.getItem());
        var renderBlockState = getRenderBlockState(block, player);
        if(renderBlockState.getRenderShape() == RenderShape.INVISIBLE) return false; // block state set to have no render
        var valid = canPlaceBlockAt(level, pos, renderBlockState, player);

        // no matter if block can be placed or not
        // we always render if block is a multi block
        // as visualizer changes color based on
        // if placement would be successful or not
        if(block instanceof MultiBlock)
        {
            var camPos = camera.getPosition();
            var alpha = getAlpha();

            pose.pushPose();
            pose.translate(-camPos.x, -camPos.y, -camPos.z);
            pose.translate(pos.getX(), pos.getY(), pos.getZ());

            renderer.renderBlockState(blockRenderer, pose, buffer, level, pos, renderBlockState, valid, alpha, partialTick);

            pose.popPose();
            return true;
        }

        // skip next render attempt, if block in current hand can be placed
        // but not a multi block, this is to stop
        // rendering for other hand, if current hand can be placed
        // but only if current hand is not a multi block
        return !valid || !isPlacementBlocked(level, pos, renderBlockState, player);
    }

    private void renderBlockState(BlockRenderDispatcher blockRenderer, PoseStack pose, MultiBufferSource.BufferSource buffer, Level level, BlockPos pos, BlockState blockState, boolean validPlacement, int alpha, float partialTick)
    {
        var renderType = RenderType.translucent();
        var consumer = new GhostVertexConsumer(buffer.getBuffer(renderType), alpha);
        var model = blockRenderer.getBlockModel(blockState);
        blockRenderer.renderBatched(blockState, pos, level, pose, consumer, false, level.getRandom());
        buffer.endBatch(renderType);
    }

    private int getAlpha()
    {
        var period = 2500D;
        var timer = System.currentTimeMillis() % period;
        var offset = Mth.cos((float) ((2D / period) * Math.PI * timer));
        return (int) ((.55D - .2D * offset) * 255D);
    }

    private BlockState getRenderBlockState(Block block, Player player)
    {
        var blockState = block.defaultBlockState();
        // default to origin index
        if(block instanceof MultiBlock multiBlock) blockState = multiBlock.getMultiBlockType().setIndex(blockState, MultiBlockType.ORIGIN_INDEX);
        // make placement face towards player, for facing blocks
        if(blockState.hasProperty(HorizontalDirectionalBlock.FACING)) blockState = blockState.setValue(HorizontalDirectionalBlock.FACING, player.getDirection().getOpposite());
        // this shouldn't rly affect anything, as we are not rendering fluid states,
        // but we also don't want water logging to affect any of the placement checks
        // set as false, to ignore if waterlogged or not
        if(blockState.hasProperty(BlockStateProperties.WATERLOGGED)) blockState = blockState.setValue(BlockStateProperties.WATERLOGGED, false);
        return blockState;
    }

    @Nullable
    private BlockPos getRenderPos(Level level, Player player, MultiPlayerGameMode gameMode, float partialTick)
    {
        var reachDist = gameMode.getPickRange();
        var hit = player.pick(reachDist, partialTick, false);
        var renderPos = new BlockPos(hit.getLocation()); // position where player is looking
        var blockPos = renderPos;

        // offset from block player is looking at
        if(hit instanceof BlockHitResult hitBlock)
        {
            blockPos = hitBlock.getBlockPos();
            renderPos = blockPos.relative(hitBlock.getDirection());
        }

        // must be placed against another block
        if(level.isEmptyBlock(blockPos)) return null;
        return renderPos;
    }

    private boolean canPlaceBlockAt(Level level, BlockPos pos, BlockState blockState, Player player)
    {
        if(isPlacementBlocked(level, pos, blockState, player)) return false;

        // have multi blocks check every sub position
        if(blockState.getBlock() instanceof MultiBlock multiBlock)
        {
            var multiBlockType = multiBlock.getMultiBlockType();
            var originPos = multiBlockType.getOriginPos(multiBlock, blockState, pos);
            var localPositions = multiBlockType.getLocalPositions();
            var ignoreIndex = multiBlockType.getIndex(blockState);

            for(var i = 0; i < localPositions.size(); i++)
            {
                if(i == ignoreIndex) continue; // already check in 'if' at start of method call
                var worldPos = multiBlockType.getWorldSpaceFromLocalSpace(multiBlock, blockState, originPos, localPositions.get(i));
                var worldBlockState = multiBlockType.setIndex(blockState, i);

                if(isPlacementBlocked(level, worldPos, worldBlockState, player)) return false;
            }
        }

        return true;
    }

    // does not check all sub positions of multi-blocks
    // assumes is called for each multi-block sub position
    private boolean isPlacementBlocked(Level level, BlockPos pos, BlockState blockState, Player player)
    {
        // block collides with player
        if(doesEntityBlockPlacement(player, pos, blockState)) return true;

        // block collides with some entity
        var nearbyEntities = level.getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, null, AABB.ofSize(pos.getCenter(), 1D, 1D, 1D));

        for(var nearbyEntity : nearbyEntities)
        {
            if(doesEntityBlockPlacement(nearbyEntity, pos, blockState)) return true;
        }

        // placement area is obstructed
        if(!level.isUnobstructed(blockState, pos, CollisionContext.of(player))) return true;

        // check if multi block placement is allowed
        if(blockState.getBlock() instanceof MultiBlock multiBlock)
        {
            var existingBlock = level.getBlockState(pos);
            if(!multiBlock.getMultiBlockType().passesPlacementTests(multiBlock, level, pos, blockState, existingBlock)) return true;
        }

        return false;
    }

    private boolean doesEntityBlockPlacement(Entity entity, BlockPos pos, BlockState blockState)
    {
        return entity.isAlive() && entity.isColliding(pos, blockState);
    }

    @FunctionalInterface
    public interface PlatformRenderer
    {
        void renderBlockState(BlockRenderDispatcher blockRenderer, PoseStack pose, MultiBufferSource.BufferSource buffer, Level level, BlockPos pos, BlockState blockState, boolean validPlacement, int alpha, float partialTick);
    }
}
