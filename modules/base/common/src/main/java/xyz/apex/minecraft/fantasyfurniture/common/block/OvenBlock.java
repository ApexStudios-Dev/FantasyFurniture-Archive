package xyz.apex.minecraft.fantasyfurniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BaseEntityBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentTypes;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.OvenBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;

import java.util.Optional;
import java.util.function.Consumer;

// TODO: Update to make use of new block entity component system
public abstract class OvenBlock<T extends OvenBlockEntity> extends BaseBlockComponentHolder implements EntityBlock
{
    public static final BooleanProperty LIT = AbstractFurnaceBlock.LIT;

    protected OvenBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);

        registerDefaultState(defaultBlockState().setValue(LIT, false));
    }

    protected abstract BlockEntityType<T> getBlockEntityType();

    public final Optional<T> getBlockEntity(BlockState blockState, BlockGetter level, BlockPos pos)
    {
        return BaseEntityBlockComponentHolder.getBlockEntity(getBlockEntityType(), blockState, level, pos);
    }

    public final Optional<T> getBlockEntity(BlockGetter level, BlockPos pos)
    {
        return BaseEntityBlockComponentHolder.getBlockEntity(getBlockEntityType(), level, pos);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        var blockEntity = getBlockEntity(blockState, level, pos).orElse(null);

        if(blockEntity != null)
        {
            var result = AllMenuTypes.OVEN.open(player, blockEntity.getDisplayName(), blockEntity, extraData -> {});
            if(result.consumesAction()) return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return super.use(blockState, level, pos, player, hand, hit);
    }

    @Override
    public boolean triggerEvent(BlockState blockState, Level level, BlockPos pos, int id, int param)
    {
        return getBlockEntity(blockState, level, pos).map(blockEntity -> blockEntity.triggerEvent(id, param)).orElse(false);
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState blockState, Level level, BlockPos pos)
    {
        return getBlockEntity(blockState, level, pos).orElse(null);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState)
    {
        var multiBlockComponent = getComponent(BlockComponentTypes.MULTI_BLOCK);
        if(multiBlockComponent != null && !multiBlockComponent.getMultiBlockType().isOrigin(blockState)) return null;
        return getBlockEntityType().create(pos, blockState);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder.add(LIT));
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState blockState, @Nullable LivingEntity placer, ItemStack stack)
    {
        super.setPlacedBy(level, pos, blockState, placer, stack);
        if(!stack.hasCustomHoverName()) return;
        getBlockEntity(blockState, level, pos).ifPresent(blockEntity -> blockEntity.setCustomName(stack.getDisplayName()));
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos pos, BlockState newBlockState, boolean isMoving)
    {
        if(!blockState.is(newBlockState.getBlock()))
        {
            getBlockEntity(blockState, level, pos).ifPresent(blockEntity -> {
                if(level instanceof ServerLevel serverLevel)
                {
                    Containers.dropContents(serverLevel, pos, blockEntity);
                    blockEntity.getRecipesToAwardAndPopExperience(serverLevel, Vec3.atCenterOf(pos));
                }

                level.updateNeighbourForOutputSignal(pos, blockState.getBlock());
            });
        }

        super.onRemove(blockState, level, pos, newBlockState, isMoving);
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState blockState)
    {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos)
    {
        return getBlockEntity(blockState, level, pos).map(AbstractContainerMenu::getRedstoneSignalFromContainer).orElse(0);
    }

    @Override
    public <O extends BlockEntity> BlockEntityTicker<O> getTicker(Level level, BlockState blockState, BlockEntityType<O> blockEntityType)
    {
        return AbstractFurnaceBlock.createFurnaceTicker(level, blockEntityType, getBlockEntityType());
    }
}
