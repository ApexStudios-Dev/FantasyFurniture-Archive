package xyz.apex.minecraft.fantasyfurniture.common.block.components;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.Vec3;

import xyz.apex.minecraft.apexcore.common.component.ComponentBlock;
import xyz.apex.minecraft.apexcore.common.component.ComponentType;
import xyz.apex.minecraft.apexcore.common.component.ComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.SimpleComponent;
import xyz.apex.minecraft.apexcore.common.component.components.BlockEntityComponent;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.OvenBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllBlockEntityTypes;

import java.util.Optional;
import java.util.function.Consumer;

public final class OvenComponent extends SimpleComponent
{
    public static final ComponentType<OvenComponent> COMPONENT_TYPE = ComponentType
            .builder(new ResourceLocation(FantasyFurniture.ID, "oven"), OvenComponent.class)
                .requires(ComponentTypes.BLOCK_ENTITY)
            .register();

    public static final BooleanProperty LIT = AbstractFurnaceBlock.LIT;

    @ApiStatus.Internal // public cause reflection
    public OvenComponent(ComponentBlock block)
    {
        super(block);
    }

    public Optional<OvenBlockEntity> getBlockEntity(BlockGetter level, BlockPos pos)
    {
        return getOptionalComponent(ComponentTypes.BLOCK_ENTITY)
                .flatMap(component -> BlockEntityComponent.lookupBlockEntity(level, pos))
                .filter(OvenBlockEntity.class::isInstance)
                .map(OvenBlockEntity.class::cast);
    }

    @Override
    public BlockState registerDefaultBlockState(BlockState blockState)
    {
        return blockState.setValue(LIT, false);
    }

    @Override
    public void createBlockStateDefinition(Consumer<Property<?>> consumer)
    {
        consumer.accept(LIT);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState blockState, @Nullable LivingEntity placer, ItemStack stack)
    {
        if(!stack.hasCustomHoverName()) return;
        getBlockEntity(level, pos).ifPresent(blockEntity -> blockEntity.setCustomName(stack.getDisplayName()));
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos pos, BlockState newBlockState, boolean isMoving)
    {
        if(blockState.is(newBlockState.getBlock())) return;

        getBlockEntity(level, pos).ifPresent(blockEntity -> {
            if(level instanceof ServerLevel serverLevel)
            {
                Containers.dropContents(serverLevel, pos, blockEntity);
                blockEntity.getRecipesToAwardAndPopExperience(serverLevel, Vec3.atCenterOf(pos));
            }

            level.updateNeighbourForOutputSignal(pos, blockState.getBlock());
        });
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState blockState)
    {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos)
    {
        return getBlockEntity(level, pos).map(AbstractContainerMenu::getRedstoneSignalFromContainer).orElse(0);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType)
    {
        return AbstractFurnaceBlock.createFurnaceTicker(level, blockEntityType, AllBlockEntityTypes.OVEN.get());
    }
}
