package xyz.apex.minecraft.fantasyfurniture.shared.block;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlock;
import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.shared.multiblock.SimpleMultiBlock;
import xyz.apex.minecraft.apexcore.shared.util.function.Lazy;
import xyz.apex.minecraft.fantasyfurniture.shared.client.renderer.MultiBlockRendererPlacementModifier;

import java.util.function.Supplier;

public class DoorMultiBlock extends DoorBlock implements MultiBlock, MultiBlockRendererPlacementModifier
{
    public static final DirectionProperty FACING = DoorBlock.FACING;
    public static final BooleanProperty OPEN = DoorBlock.OPEN;
    public static final EnumProperty<DoorHingeSide> HINGE = DoorBlock.HINGE;
    public static final BooleanProperty POWERED = DoorBlock.POWERED;
    public static final EnumProperty<DoubleBlockHalf> HALF = DoorBlock.HALF;

    protected final DoorSounds doorSounds;
    protected final MultiBlockType multiBlockType;

    public DoorMultiBlock(MultiBlockType multiBlockType, DoorSounds doorSounds, Properties properties)
    {
        super(properties, null, null);

        this.doorSounds = doorSounds;
        this.multiBlockType = multiBlockType;
        SimpleMultiBlock.replaceBlockStateContainer(this); // must be after we set the MultiBlockType field
        registerDefaultState(multiBlockType.registerDefaultBlockState(defaultBlockState()));
    }

    protected boolean opensWithInteract(Level level, BlockPos pos, BlockState blockState, Player player, InteractionHand hand)
    {
        return material != Material.METAL;
    }

    // MultiBlock
    @Override
    public boolean isSameBlockTypeForMultiBlock(BlockState blockState)
    {
        return blockState.is(this);
    }

    @Override
    public final MultiBlockType getMultiBlockType()
    {
        return multiBlockType;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx)
    {
        var pos = ctx.getClickedPos();
        var level = ctx.getLevel();

        if(pos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(pos.above()).canBeReplaced(ctx))
        {
            var blockState = getPlacementState(this, ctx);
            return multiBlockType.getStateForPlacement(this, blockState, ctx);
        }

        return null;
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader level, BlockPos pos)
    {
        return multiBlockType.canSurvive(this, level, pos, blockState) && super.canSurvive(blockState, level, pos);
    }

    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos pos, BlockState oldBlockState, boolean isMoving)
    {
        multiBlockType.onPlace(this, blockState, level, pos, oldBlockState);
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos pos, BlockState newBlockstate, boolean isMoving)
    {
        multiBlockType.onRemove(this, blockState, level, pos, newBlockstate);
        super.onRemove(blockState, level, pos, newBlockstate, isMoving);
    }

    @SuppressWarnings("ConstantValue")
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        // null on first call, as it's set in constructor and this method is called from super
        // none-null on second call, as that's fired in our constructor and replaces the vanilla state definition
        if(multiBlockType != null) multiBlockType.registerBlockProperty(builder::add);
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState)
    {
        return multiBlockType.isOrigin(blockState) ? RenderShape.MODEL : RenderShape.INVISIBLE;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
    {
        // NOOP: MultiBlock handles this
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player)
    {
        // NOOP: MultiBlock handles this
    }

    // must override the following and redirect to make use of DoorSounds
    // otherwise we will get NPEs due to constructor passing null as default sounds
    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        if(opensWithInteract(level, pos, blockState, player, hand))
        {
            var isOpen = isOpen(blockState);
            setOpen(player, level, blockState, pos, !isOpen);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    }

    @Override
    public void setOpen(@Nullable Entity source, Level level, BlockState blockState, BlockPos pos, boolean open)
    {
        if(blockState.is(this) && isOpen(blockState) != open)
        {
            level.setBlock(pos, blockState.setValue(OPEN, open), 10);
            doorSounds.playSound(source, level, pos, open);
            level.gameEvent(source, open ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        }
    }

    @Override
    public void neighborChanged(BlockState blockState, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving)
    {
        var flag = level.hasNeighborSignal(pos) || level.hasNeighborSignal(pos.relative(blockState.getValue(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));

        if(block != this && flag != blockState.getValue(POWERED))
        {
            if(flag != blockState.getValue(OPEN))
            {
                doorSounds.playSound(null, level, pos, flag);
                level.gameEvent(null, flag ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
            }

            level.setBlock(pos, blockState.setValue(POWERED, flag).setValue(OPEN, flag), UPDATE_CLIENTS);
        }
    }

    public final void playSound(@Nullable Entity source, Level level, BlockPos pos, boolean isOpening)
    {
        doorSounds.playSound(source, level, pos, isOpening);
    }

    public final SoundEvent getOpenSound()
    {
        return doorSounds.getOpenSound();
    }

    public final SoundEvent getCloseSound()
    {
        return doorSounds.getCloseSound();
    }

    @Override
    public BlockState getBlockStateForRenderPlacement(BlockPlaceContext ctx)
    {
        return getPlacementState(this, ctx);
    }

    public static BlockState getPlacementState(DoorMultiBlock block, BlockPlaceContext ctx)
    {
        var level = ctx.getLevel();
        var pos = ctx.getClickedPos();
        var open = level.hasNeighborSignal(pos) || level.hasNeighborSignal(pos.above());
        return block.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection()).setValue(HINGE, getHinge(block, ctx)).setValue(POWERED, open).setValue(OPEN, open).setValue(HALF, DoubleBlockHalf.LOWER);
    }

    public static DoorHingeSide getHinge(DoorBlock door, BlockPlaceContext ctx)
    {
        var level = ctx.getLevel();
        var pos = ctx.getClickedPos();
        var facing = ctx.getHorizontalDirection();
        var abovePos = pos.above();
        var ccwFacing = facing.getCounterClockWise();
        var ccwPos = pos.relative(ccwFacing);
        var ccwBlockState = level.getBlockState(ccwPos);
        var ccwAbovePos = abovePos.relative(ccwFacing);
        var ccwAboveBlockState = level.getBlockState(ccwAbovePos);
        var cwFacing = facing.getClockWise();
        var cwPos = pos.relative(cwFacing);
        var cwBlockState = level.getBlockState(cwPos);
        var cwAbovePos = abovePos.relative(cwFacing);
        var cwAboveBlockState = level.getBlockState(cwAbovePos);

        var i = (ccwBlockState.isCollisionShapeFullBlock(level, ccwPos) ? -1 : 0) +
                (ccwAboveBlockState.isCollisionShapeFullBlock(level, ccwAbovePos) ? -1 : 0) +
                (cwBlockState.isCollisionShapeFullBlock(level, cwPos) ? 1 : 0) +
                (cwAboveBlockState.isCollisionShapeFullBlock(level, cwAbovePos) ? 1 : 0)
        ;

        var ccwIsLowerDoor = ccwBlockState.is(door) && ccwBlockState.getValue(HALF) == DoubleBlockHalf.LOWER;
        var cwIsLowerDoor = cwBlockState.is(door) && cwBlockState.getValue(HALF) == DoubleBlockHalf.LOWER;

        if((!ccwIsLowerDoor || cwIsLowerDoor) && i <= 0)
        {
            if((!cwIsLowerDoor || ccwIsLowerDoor) && i >= 0)
            {
                var xOff = facing.getStepX();
                var zOff = facing.getStepZ();
                var clickPos = ctx.getClickLocation();
                var clickX = clickPos.x - pos.getX();
                var clickZ = clickPos.z - pos.getZ();
                return (xOff >= 0D || !(clickZ < .5D)) && (xOff <= 0D || !(clickZ > .5D)) && (zOff >= 0D || !(clickX > .5D)) && (zOff <= 0D || !(clickX < .5D)) ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT;
            }

            return DoorHingeSide.LEFT;
        }

        return DoorHingeSide.RIGHT;
    }

    public record DoorSounds(Supplier<SoundEvent> openSound, Supplier<SoundEvent> closeSound)
    {
        public DoorSounds(Supplier<SoundEvent> sound)
        {
            this(Lazy.of(sound), Lazy.of(sound));
        }

        public DoorSounds(SoundEvent openSound, SoundEvent closeSound)
        {
            this(Lazy.of(openSound), Lazy.of(closeSound));
        }

        public DoorSounds(SoundEvent sound)
        {
            this(sound, sound);
        }

        public SoundEvent getOpenSound()
        {
            return openSound.get();
        }

        public SoundEvent getCloseSound()
        {
            return closeSound.get();
        }

        public void playSound(@Nullable Entity source, Level level, BlockPos pos, boolean isOpening)
        {
            var sound = isOpening ? openSound.get() : closeSound.get();
            var rng = level.getRandom();
            level.playSound(source, pos, sound, SoundSource.BLOCKS, 1F, rng.nextFloat() * .1F + .9F);
        }

        public void playSound(@Nullable Entity source, Level level, BlockPos pos, BlockState blockState)
        {
            var isOpening = blockState.getOptionalValue(OPEN).orElse(false);
            playSound(source, level, pos, isOpening);
        }
    }
}
