package xyz.apex.minecraft.fantasyfurniture.common.block;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.common.multiblock.SimpleMultiBlock;

public class SimpleSeatBlock extends Block implements SeatBlock
{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public SimpleSeatBlock(Properties properties)
    {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter level, Entity entity)
    {
        seatEntityAfterFallOn(level, entity, super::updateEntityAfterFallOn);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
    {
        return useSeat(blockState, level, pos, player);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx)
    {
        return defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }

    public static class WithMultiBlock extends SimpleMultiBlock.WithHorizontalFacing implements SeatBlock.MultiBlock
    {
        public WithMultiBlock(MultiBlockType multiBlockType, Properties properties)
        {
            super(multiBlockType, properties);
        }

        @Override
        public void updateEntityAfterFallOn(BlockGetter level, Entity entity)
        {
            seatEntityAfterFallOn(level, entity, super::updateEntityAfterFallOn);
        }

        @Override
        public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
        {
            return useSeat(blockState, level, pos, player);
        }

        public static class AtOriginOnly extends WithMultiBlock
        {
            public AtOriginOnly(MultiBlockType multiBlockType, Properties properties)
            {
                super(multiBlockType, properties);
            }

            @Override
            public boolean sitAtOriginOnly()
            {
                return true;
            }
        }
    }
}
