package xyz.apex.minecraft.fantasyfurniture.common.feature.seat;

import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.lib.event.types.EntityEvents;
import xyz.apex.minecraft.apexcore.common.lib.helper.EntityHelper;
import xyz.apex.minecraft.apexcore.common.lib.helper.TagHelper;
import xyz.apex.minecraft.apexcore.common.lib.registry.entries.EntityEntry;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

@ApiStatus.NonExtendable
public interface Seat
{
    // core seat entity, every entity rides when sitting
    // should never need to manually spawn this entity
    EntityEntry<SeatEntity> ENTITY = FantasyFurniture.BUILDERS.entity("seat", SeatEntity::new)
            .category(MobCategory.MISC)
            .sized(.25F, .35F)
            .clientTrackingRange(10)
            .updateInterval(1)
            .noSummon()
            .renderer(() -> () -> NoopRenderer::new)
    .register();

    // whether hostiles can sit or not
    @ApiStatus.Internal
    boolean SEAT_HOSTILES = true; // TODO: Convert to config
    // entities with this tag are black listed from sitting
    TagKey<EntityType<?>> BLACKLIST = TagHelper.apexEntityTag("seat_blacklist");

    // blocks with this tag are valid sittable blocks
    // entities will try to sit in them either via interaction
    // or by colliding with them
    TagKey<Block> SITTABLE = TagHelper.apexBlockTag("sittable");

    @ApiStatus.Internal
    static void bootstrap()
    {
        SitEvents.bootstrap();

        EntityEvents.AFTER_ENTITY_FALL_ON.addListener(Seat::onEntityFallOn);
    }

    // TODO: These should be moved to actual Events in ApexCore
    //  rather than listening for them here in FantasyFurniture
    @ApiStatus.Internal
    static InteractionResult onRightClickBlock(Player player, Level level, InteractionHand hand, BlockHitResult hitVec)
    {
        if(hand != InteractionHand.MAIN_HAND)
            return InteractionResult.PASS;
        if(player.isSecondaryUseActive())
            return InteractionResult.PASS;

        var pos = hitVec.getBlockPos();
        var blockState = level.getBlockState(pos);

        if(tryStealSeat(player, level, pos, blockState))
            return InteractionResult.sidedSuccess(level.isClientSide);
        if(trySeat(null, player, level, pos, blockState))
            return InteractionResult.sidedSuccess(level.isClientSide);
        return InteractionResult.PASS;
    }

    @ApiStatus.Internal
    static InteractionResult onRightClickEntity(Player player, Level level, InteractionHand hand, Entity entity)
    {
        if(hand != InteractionHand.MAIN_HAND)
            return InteractionResult.PASS;
        if(player.isSecondaryUseActive())
            return InteractionResult.PASS;

        var pos = entity.blockPosition();
        var blockState = level.getBlockState(pos);

        if(tryStealSeat(player, level, pos, blockState))
            return InteractionResult.sidedSuccess(level.isClientSide);
        if(trySeat(entity, player, level, pos, blockState))
            return InteractionResult.sidedSuccess(level.isClientSide);
        return InteractionResult.PASS;
    }

    private static void onEntityFallOn(Entity entity, Level level, BlockPos pos, BlockState blockState)
    {
        if(entity instanceof Player || EntityHelper.isFakePlayer(entity))
            return;

        trySeat(entity, null, level, pos, blockState);
    }

    private static boolean trySeat(@Nullable Entity seating, @Nullable Entity seater, Level level, BlockPos pos, BlockState blockState)
    {
        if(!SeatHelper.isSittable(blockState))
            return false;

        var toBeSeated = determineSitter(level, seating, seater, pos, blockState);

        if(toBeSeated == null)
            return false;

        var seats = level.getEntities(ENTITY, new AABB(pos), seat -> true);

        if(!seats.isEmpty())
            return false;
        if(!(level instanceof ServerLevel sLevel))
            return false;

        var seat = ENTITY.get().spawn(sLevel, pos, MobSpawnType.TRIGGERED);

        if(seat == null)
            return false;

        return toBeSeated.startRiding(seat);
    }

    @Nullable
    private static LivingEntity determineSitter(Level level, @Nullable Entity seating, @Nullable Entity seater, BlockPos pos, BlockState blockState)
    {
        if(seating instanceof LivingEntity living && SeatEntity.canSitAt(level, living, pos, blockState))
            return living;

        if(seater instanceof LivingEntity living)
        {
            var leashed = getLeashed(living);

            if(leashed != null)
            {
                if(SeatEntity.canSitAt(level, leashed, pos, blockState))
                    return leashed;
            }

            if(SeatEntity.canSitAt(level, living, pos, blockState))
                return living;
        }

        return null;
    }

    @Nullable
    private static LivingEntity getLeashed(LivingEntity parent)
    {
        for(var entity : parent.level().getEntities(null, parent.getBoundingBox().inflate(10D)))
        {
            if(entity instanceof Mob mob)
            {
                var leashHolder = mob.getLeashHolder();

                if(leashHolder != null && leashHolder.is(parent))
                    return mob;
            }
        }

        return null;
    }

    private static boolean tryStealSeat(Player player, Level level, BlockPos pos, BlockState blockState)
    {
        if(!SeatHelper.isSittable(blockState))
            return false;
        if(!SeatEntity.canSitAt(level, player, pos, blockState))
            return false;

        var seats = level.getEntities(ENTITY, new AABB(pos), seat -> true);

        if(seats.isEmpty())
            return false;

        var seat = seats.get(0);

        if(!(seat.getFirstPassenger() instanceof LivingEntity sitter))
            return false;
        if(!SeatEntity.canStealSeat(level, sitter, player, pos, blockState, seat))
            return false;

        seat.discard();
        return true;
    }
}
