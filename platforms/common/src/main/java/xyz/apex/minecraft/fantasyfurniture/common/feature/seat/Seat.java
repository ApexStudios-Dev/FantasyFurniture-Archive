package xyz.apex.minecraft.fantasyfurniture.common.feature.seat;

import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
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
    }

    // TODO: These should be moved to actual Events in ApexCore
    //  rather than listening for them here in FantasyFurniture
    @ApiStatus.Internal
    static InteractionResult onRightClickBlock(Player player, Level level, InteractionHand hand, BlockHitResult hitVec)
    {
        if(hand != InteractionHand.MAIN_HAND)
            return InteractionResult.PASS;
        if(trySeat(player, hitVec.getBlockPos()))
            return InteractionResult.sidedSuccess(level.isClientSide);
        return InteractionResult.PASS;
    }

    @ApiStatus.Internal
    static InteractionResult onRightClickEntity(Player player, Level level, InteractionHand hand, Entity entity)
    {
        // some entities hitboxes are huge
        // making it hard to interact with the block
        // they are sat on
        // this redirects entity interaction, to do the same
        // as what would have been done if right-clicked the block

        // only for main hand
        if(hand != InteractionHand.MAIN_HAND)
            return InteractionResult.PASS;
        // ignore spectators and removed entities
        if(entity.isRemoved() || entity.isSpectator())
            return InteractionResult.PASS;
        // living entities only
        if(!(entity instanceof LivingEntity living))
            return InteractionResult.PASS;
        // ignore players and fake players
        if(living instanceof Player || EntityHelper.isFakePlayer(living))
            return InteractionResult.PASS;
        // only if is currently seated
        if(!(living.getVehicle() instanceof SeatEntity))
            return InteractionResult.PASS;
        if(trySeat(player, living.blockPosition()))
            return InteractionResult.sidedSuccess(level.isClientSide);
        return InteractionResult.PASS;
    }

    @ApiStatus.Internal
    static void onEntityTick(LivingEntity entity)
    {
        if(entity.isPassenger())
            return;
        if(!SeatHelper.canSit(entity))
            return;
        if(entity instanceof Player || EntityHelper.isFakePlayer(entity))
            return;

        var level = entity.level();
        var pos = entity.getOnPos();
        var blockState = entity.getBlockStateOn();

        if(canSitAt(level, entity, pos, blockState))
            trySeat(entity, pos);
    }

    private static boolean trySeat(LivingEntity interacting, BlockPos pos)
    {
        var level = interacting.level();
        var blockState = level.getBlockState(pos);
        // determine entity to sit down
        var sitter = determineSitter(interacting);

        // only if loaded and within world borders
        if(!level.isLoaded(pos))
            return false;
        if(level.isOutsideBuildHeight(pos))
            return false;
        if(!level.getWorldBorder().isWithinBounds(pos))
            return false;

        // only if you can sit here
        if(!canSitAt(level, sitter, pos, blockState))
            return false;

        // steal existing seat or spawn new one
        var seat = findExistingSeat(level, sitter, pos, blockState);

        if(seat == null)
        {
            if(!(interacting instanceof Player player) || !player.isSecondaryUseActive())
                seat = spawnSeat(level, pos);
        }

        // failed to steal seat or spawn new one
        if(seat == null)
            return false;

        // sit down
        if(!level.isClientSide)
            onEntitySitDown(level, sitter, pos, blockState, seat);
        return true;
    }

    private static void onEntitySitDown(Level level, LivingEntity sitter, BlockPos pos, BlockState blockState, SeatEntity seat)
    {
        seat.unRide();
        sitter.unRide();

        // dont sit if player sneaking
        if(sitter instanceof Player player && player.isSecondaryUseActive())
        {
            seat.discard();
            return;
        }

        // mark entity as sat down
        if(sitter instanceof Mob mob)
            mob.getNavigation().stop();

        sitter.resetFallDistance();
        sitter.moveTo(pos.getCenter().add(0D, 1D, 0D));
        setEntitySittingState(sitter, true);
        sitter.startRiding(seat, true);
        SitEvents.SIT_DOWN.post().handle(level, sitter, pos, blockState, seat);

        if(blockState.getBlock() instanceof SeatBlock seatBlock)
            seatBlock.onEntitySitDown(level, sitter, pos, blockState, seat);
    }

    @ApiStatus.Internal
    static void onEntityStandUp(Level level, LivingEntity sitter, BlockPos pos, BlockState blockState, SeatEntity seat)
    {
        sitter.unRide();
        setEntitySittingState(sitter, false);
        SitEvents.STAND_UP.post().handle(level, sitter, pos, blockState, seat);

        if(blockState.getBlock() instanceof SeatBlock seatBlock)
            seatBlock.onEntityStandUp(level, sitter, pos, blockState, seat);
    }

    private static void setEntitySittingState(LivingEntity entity, boolean sitting)
    {
        // put entities into their "sitting" states
        if(entity instanceof Camel camel)
        {
            if(sitting)
            {
                // if was already sitting, force standing up
                // to replay the animation
                if(camel.isCamelSitting())
                    camel.standUpInstantly();

                camel.sitDown();
            }
            else
                camel.standUp();
        }
        else if(entity instanceof AbstractHorse horse)
        {
            if(sitting)
            {
                // horse was on 2 hind legs
                // "sit" them down
                if(horse.isStanding())
                    horse.setStanding(false);
            }
        }
        else if(entity instanceof Panda panda)
            panda.sit(sitting);
        else if(entity instanceof TamableAnimal tamable)
            tamable.setInSittingPose(sitting);
    }

    @ApiStatus.Internal
    static boolean canSitAt(Level level, LivingEntity sitter, BlockPos pos, BlockState blockState)
    {
        if(!SeatHelper.canSit(sitter))
            return false;
        if(!SeatHelper.isSittable(blockState))
            return false;
        if(!SitEvents.CAN_SIT_AT.post().handle(level, sitter, pos, blockState))
            return false;
        if(blockState.getBlock() instanceof SeatBlock seatBlock)
            return seatBlock.canSitAt(level, sitter, pos, blockState);
        return true;
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

    private static LivingEntity determineSitter(LivingEntity interacting)
    {
        var leashed = getLeashed(interacting);

        if(leashed != null)
            return leashed;

        return interacting;
    }

    @Nullable
    private static SeatEntity spawnSeat(Level level, BlockPos pos)
    {
        var seat = ENTITY.get().create(level);

        if(seat != null)
        {
            seat.moveTo(pos, 0F, 0F);
            level.addFreshEntity(seat);
        }

        return seat;
    }

    @Nullable
    private static SeatEntity findExistingSeat(Level level, LivingEntity sitter, BlockPos pos, BlockState blockState)
    {
        var seats = level.getEntities(ENTITY, new AABB(pos), EntitySelector.NO_SPECTATORS);

        for(var seat : seats)
        {
            for(var passenger : seat.getPassengers())
            {
                if(passenger == null)
                    return seat;
                if(passenger instanceof LivingEntity living && canStealSeatFrom(level, living, sitter, pos, blockState, seat))
                    return seat;
            }
        }

        return null;
    }

    private static boolean canStealSeatFrom(Level level, LivingEntity sitter, LivingEntity stealer, BlockPos pos, BlockState blockState, SeatEntity seat)
    {
        if(sitter instanceof Player)
            return false;
        if(SitEvents.CAN_STEAL_SEAT.post().handle(level, sitter, stealer, pos, blockState, seat))
            return true;
        if(blockState.getBlock() instanceof SeatBlock seatBlock)
            return seatBlock.canStealSeat(level, sitter, stealer, pos, blockState, seat);

        return true;
    }
}
