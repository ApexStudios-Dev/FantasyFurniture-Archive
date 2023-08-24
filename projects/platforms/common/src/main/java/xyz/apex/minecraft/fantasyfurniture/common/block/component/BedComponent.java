package xyz.apex.minecraft.fantasyfurniture.common.block.component;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BaseBlockComponent;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentType;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.apexcore.common.lib.helper.InteractionResultHelper;
import xyz.apex.minecraft.apexcore.common.lib.multiblock.MultiBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

import java.util.Optional;
import java.util.stream.IntStream;

public final class BedComponent extends BaseBlockComponent
{
    public static final BlockComponentType<BedComponent> COMPONENT_TYPE = BlockComponentType.register(FantasyFurniture.ID, "bed", BedComponent::new);

    public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;

    private final IntSet headIndices = new IntOpenHashSet();

    private BedComponent(BlockComponentHolder componentHolder)
    {
        super(componentHolder);
    }

    public BedComponent withHeadIndex(int index)
    {
        Validate.isTrue(!isRegistered(), "Can only set head indices during registration");
        headIndices.add(index);
        return this;
    }

    public BedComponent withHeadIndex(int index, int... indices)
    {
        Validate.isTrue(!isRegistered(), "Can only set head indices during registration");
        headIndices.add(index);
        IntStream.of(indices).forEach(headIndices::add);
        return this;
    }

    @Override
    public BlockState registerDefaultBlockState(BlockState defaultBlockState)
    {
        return defaultBlockState.setValue(OCCUPIED, false);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(OCCUPIED);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        if(level.isClientSide)
            return InteractionResultHelper.BlockUse.succeedNoArmSwing();

        var headPos = pos;
        var headBlockState = blockState;

        if(!isHead(blockState))
        {
            headPos = pos.relative(blockState.getValue(HorizontalFacingBlockComponent.FACING).getOpposite());
            headBlockState = level.getBlockState(headPos);
        }

        return useBed(level, headPos, headBlockState, player);
    }

    private InteractionResult useBed(Level level, BlockPos pos, BlockState blockState, Player player)
    {
        if(!BedBlock.canSetSpawn(level))
        {
            level.removeBlock(pos, false);
            var center = pos.getCenter();
            level.explode(null, level.damageSources().badRespawnPointExplosion(center), null, center, 5F, true, Level.ExplosionInteraction.BLOCK);
            return InteractionResultHelper.BlockUse.succeedAndSwingArmOneSide();
        }
        else if(blockState.getValue(OCCUPIED))
        {
            if(!kickVillagerOutOfBed(level, pos))
            {
                player.displayClientMessage(Component.translatable("block.minecraft.bed.occupied"), true); // TODO: translation for this specific block type?
                return InteractionResultHelper.BlockUse.succeedAndSwingArmOneSide();
            }
        }
        else
        {
            player.startSleepInBed(pos).ifLeft(error -> {
                var message = error.getMessage();

                if(message != null)
                    player.displayClientMessage(message, true);
            });

            return InteractionResultHelper.BlockUse.succeedAndSwingArmOneSide();
        }

        return InteractionResultHelper.BlockUse.noActionTaken();
    }

    public boolean isHead(BlockState blockState)
    {
        var index = MultiBlockComponent.getIndex(getRequiredComponent(BlockComponentTypes.MULTI_BLOCK).getMultiBlockType(), blockState);
        return headIndices.contains(index);
    }

    public static boolean kickVillagerOutOfBed(Level level, BlockPos pos)
    {
        var villagers = level.getEntitiesOfClass(Villager.class, new AABB(pos), LivingEntity::isSleeping);

        if(villagers.isEmpty())
            return false;

        villagers.get(0).stopSleeping();
        return true;
    }

    @Nullable
    public static Direction getBedDirection(BlockState blockState)
    {
        // dont use HorizontalFacingBlockComponent.getFacing as we want nullable
        // for the mixin to return original value if not our block
        return BlockComponentHolder.mapAsComponent(blockState, COMPONENT_TYPE, component -> {
            var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
            return facing.getOpposite();
        }).orElse(null);
    }

    public static <T> Optional<T> asHead(BlockGetter level, BlockPos pos, BlockState blockState, Function<BlockState, T> mapper)
    {
        return BlockComponentHolder.mapAsComponent(blockState, COMPONENT_TYPE, component -> {
            if(component.isHead(blockState))
                return mapper.apply(blockState);

            var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
            return mapper.apply(level.getBlockState(pos.relative(facing.getOpposite())));
        });
    }

    public static boolean isBedHead(BlockState blockState)
    {
        return BlockComponentHolder.mapAsComponent(blockState, COMPONENT_TYPE, component -> component.isHead(blockState)).orElse(false);
    }
}
