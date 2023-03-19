package xyz.apex.minecraft.fantasyfurniture.common.block.components;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.component.ComponentBlock;
import xyz.apex.minecraft.apexcore.common.component.ComponentType;
import xyz.apex.minecraft.apexcore.common.component.ComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.SimpleComponent;
import xyz.apex.minecraft.apexcore.common.util.TagHelper;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.entity.Seat;

import java.util.Optional;

public final class SeatComponent extends SimpleComponent
{
    public static final ComponentType<SeatComponent> COMPONENT_TYPE = ComponentType
            .builder(new ResourceLocation(FantasyFurniture.ID, "seat"), SeatComponent.class)
                // throws weird saying this component is missing even though it is registered
                // TODO: look into
                // .requires(ComponentTypes.HORIZONTAL_FACING)
            .register();

    public static final TagKey<EntityType<?>> SEAT_BLACKLIST = TagHelper.entityTag(FantasyFurniture.ID, "seat_blacklist");

    private boolean sitAtOriginOnly = false;

    @ApiStatus.Internal // public cause reflection
    public SeatComponent(ComponentBlock block)
    {
        super(block);
    }

    public SeatComponent setSitAtOriginOnly(boolean sitAtOriginOnly)
    {
        this.sitAtOriginOnly = sitAtOriginOnly;
        return this;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        return useSeat(blockState, level, pos, player);
    }

    // @Override // TODO
    public void updateEntityAfterFallOn(BlockGetter level, Entity entity)
    {
        if(entity instanceof Player || !isEntityValidForSeat(entity)) return;
        var pos = entity.blockPosition();
        var blockState = level.getBlockState(pos);
        if(!blockState.is(block.toBlock())) return;

        var multiBlock = getComponent(ComponentTypes.MULTI_BLOCK);
        if(multiBlock != null && !multiBlock.getMultiBlockType().isOrigin(blockState) && sitAtOriginOnly) return;

        sitDown(entity.level, pos, entity);
    }

    private InteractionResult useSeat(BlockState blockState, Level level, BlockPos pos, Player player)
    {
        if(hasComponent(ComponentTypes.MULTI_BLOCK))
        {
            var multiBlockType = getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType();

            if(sitAtOriginOnly && !multiBlockType.isOrigin(blockState))
            {
                var originPos = multiBlockType.getOriginPos(blockState, pos);
                var originBlockState = level.getBlockState(originPos);
                if(!multiBlockType.isValidBlock(originBlockState)) return InteractionResult.PASS;
                return useSeat(originBlockState, level, originPos, player);
            }
        }

        if(player.isShiftKeyDown()) return InteractionResult.PASS;

        var seats = level.getEntitiesOfClass(Seat.class, new AABB(pos));

        if(!seats.isEmpty())
        {
            var seat = seats.get(0);
            var passengers = seat.getPassengers();
            if(!passengers.isEmpty() && passengers.get(0) instanceof Player) return InteractionResult.PASS;
        }

        var seatEntity = getLeashed(player).orElse(player);
        sitDown(level, pos, seatEntity);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    public static Optional<Entity> getLeashed(Player player)
    {
        var entities = player.level.getEntities((Entity) null, player.getBoundingBox().inflate(10D), e -> true);

        for(var entity : entities)
        {
            if(entity instanceof Mob mob)
            {
                var leashHolder = mob.getLeashHolder();
                if(leashHolder != null && leashHolder.is(player) && isEntityValidForSeat(entity)) return Optional.of(entity);
            }
        }

        return Optional.empty();
    }

    public static void sitDown(Level level, BlockPos pos, Entity entity)
    {
        if(level.isClientSide) return;
        var seat = new Seat(level, pos);
        seat.setPos(pos.getX() + .5F, pos.getY(), pos.getZ() + .5F);
        level.addFreshEntity(seat);
        entity.startRiding(seat, false);
        if(entity instanceof TamableAnimal tamable) tamable.setInSittingPose(true);
    }

    public static boolean isEntityValidForSeat(Entity entity)
    {
        // TODO
        // if(entity instanceof Player player && PlayerHooks.isFake(player)) return false; // disallow FakePlayers
        if(entity.getType().is(SEAT_BLACKLIST)) return false;
        return entity instanceof LivingEntity;
    }
}
