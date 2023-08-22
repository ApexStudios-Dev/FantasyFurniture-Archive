package xyz.apex.minecraft.fantasyfurniture.common.block.component;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BaseBlockComponent;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentType;
import xyz.apex.minecraft.apexcore.common.lib.helper.InteractionResultHelper;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.SeatBlock;
import xyz.apex.minecraft.fantasyfurniture.common.entity.SeatEntity;

import java.util.Optional;

public final class SeatComponent extends BaseBlockComponent implements SeatBlock
{
    public static final BlockComponentType<SeatComponent> COMPONENT_TYPE = BlockComponentType.register(FantasyFurniture.ID, "seat", SeatComponent::new);

    private SeatComponent(BlockComponentHolder componentHolder)
    {
        super(componentHolder);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        if(!SeatEntity.isSittable(blockState))
            return InteractionResultHelper.BlockUse.noActionTaken();
        if(player.isSecondaryUseActive())
            return InteractionResultHelper.BlockUse.noActionTaken();

        var seater = determineSeater(player);

        if(SeatEntity.findAssociatedSeat(level, seater) != null)
            return InteractionResultHelper.BlockUse.noActionTaken();
        if(!SeatEntity.canSit(seater))
            return InteractionResultHelper.BlockUse.noActionTaken();

        var seatPos = getSittingPos(level, blockState, pos);
        var seatBlockState = level.getBlockState(seatPos);

        if(!SeatEntity.isSittable(seatBlockState))
            return InteractionResultHelper.BlockUse.noActionTaken();

        var seat = FantasyFurniture.SEAT_ENTITY.create(level);

        if(seat == null)
            return InteractionResult.PASS;

        seat.setSittingPos(seatPos);
        seat.setPos(seatPos.getCenter().subtract(0D, .25D, 0D));
        seater.unRide();
        seater.startRiding(seat, true);
        level.addFreshEntity(seat);
        return InteractionResultHelper.BlockUse.succeedAndSwingArmBothSides(level.isClientSide);
    }

    @Override
    public BlockPos getSittingPos(BlockGetter level, BlockState blockState, BlockPos pos)
    {
        return getGameObject() instanceof SeatBlock seat ? seat.getSittingPos(level, blockState, pos) : pos;
    }

    @Override
    public float getSittingOffset(Entity entity, EntityDimensions dimensions, float f, float defaultOffset)
    {
        return getGameObject() instanceof SeatBlock seat ? seat.getSittingOffset(entity, dimensions, f, defaultOffset) : defaultOffset;
    }

    private static Entity determineSeater(Entity seater)
    {
        return getLeashedEntity(seater).filter(SeatEntity::canSit).orElse(seater);
    }

    private static Optional<Entity> getLeashedEntity(Entity seater)
    {
        for(var entity : seater.level().getEntities(null, seater.getBoundingBox().inflate(10D)))
        {
            if(entity instanceof Mob mob)
            {
                var leashHolder = mob.getLeashHolder();

                if(leashHolder != null && leashHolder.is(seater))
                    return Optional.of(mob);
            }
        }

        return Optional.empty();
    }
}
