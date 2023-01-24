package xyz.apex.minecraft.fantasyfurniture.common.block;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;

import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlock;
import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.common.multiblock.SimpleMultiBlock;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class BedMultiBlock extends BedBlock implements MultiBlock
{
    public static final EnumProperty<BedPart> PART = BedBlock.PART;
    public static final BooleanProperty OCCUPIED = BedBlock.OCCUPIED;
    public static final DirectionProperty FACING = BedBlock.FACING;

    protected final MultiBlockType multiBlockType;

    public BedMultiBlock(MultiBlockType multiBlockType, Properties properties)
    {
        super(null, properties);

        this.multiBlockType = multiBlockType;
        SimpleMultiBlock.replaceBlockStateContainer(this); // must be after we set the MultiBlockType field
        registerDefaultState(multiBlockType.registerDefaultBlockState(defaultBlockState().setValue(PART, BedPart.FOOT).setValue(OCCUPIED, false).setValue(FACING, Direction.NORTH)));
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
        var facing = ctx.getHorizontalDirection();
        return multiBlockType.getStateForPlacement(this, defaultBlockState().setValue(FACING, facing), ctx);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader level, BlockPos pos)
    {
        return multiBlockType.canSurvive(this, level, pos, blockState);
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

    // wipe out old logic vanilla "multi-block" logic
    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
    {
        // this used to place the 2nd half of the bed block
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState blockState, Player player)
    {
        // dont call super to skip old bed logic
        // but we do need to call logic in Block
        // no way to super right into Block (skipping past BedBlock)
        // so we implement that logic here, must be kept upto date with future
        // versions, if mojang ever changes Block#playerWillDestroy
        spawnDestroyParticles(level, player, pos, blockState);
        if(blockState.is(BlockTags.GUARDED_BY_PIGLINS)) PiglinAi.angerNearbyPiglins(player, false);
        level.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(player, blockState));
    }

    // wipe out the block entity, vanilla uses this for their model for what ever reason,
    // they could easily make use of normal block models for the bed model
    // like we do for our blocks
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return null;
    }

    public static void registerCustomHomePoi(Block... blocks)
    {
        // collect all possible block states into set
        // block states must have the BdPart property
        // collect only block states that are for the `HEAD` part
        // same logic as vanilla `HOME` poi
        var blockStates = Arrays.stream(blocks)
                                .map(Block::getStateDefinition)
                                .map(StateDefinition::getPossibleStates)
                                .flatMap(Collection::stream)
                                .filter(s -> s.hasProperty(PART))
                                .filter(s -> s.getValue(PART) == BedPart.HEAD)
        .collect(Collectors.toSet());

        if(PoiTypes.BEDS instanceof ImmutableSet) PoiTypes.BEDS = Sets.newHashSet(PoiTypes.BEDS);
        PoiTypes.BEDS.addAll(blockStates);
        var holder = BuiltInRegistries.POINT_OF_INTEREST_TYPE.getHolderOrThrow(PoiTypes.HOME);
        PoiTypes.registerBlockStates(holder, blockStates);
    }
}
