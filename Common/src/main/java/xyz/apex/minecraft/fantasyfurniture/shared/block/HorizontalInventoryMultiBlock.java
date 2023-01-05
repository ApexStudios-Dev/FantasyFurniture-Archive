package xyz.apex.minecraft.fantasyfurniture.shared.block;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

import xyz.apex.minecraft.apexcore.shared.inventory.InventoryBlock;
import xyz.apex.minecraft.apexcore.shared.inventory.InventoryBlockEntity;
import xyz.apex.minecraft.apexcore.shared.inventory.InventoryMenu;
import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockType;

public abstract class HorizontalInventoryMultiBlock<T extends InventoryBlockEntity, M extends InventoryMenu> extends InventoryBlock.AsMultiBlock<T, M>
{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public HorizontalInventoryMultiBlock(MultiBlockType multiBlockType, Properties properties)
    {
        super(multiBlockType, properties);

        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx)
    {
        var defaultBlockState = defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
        return multiBlockType.getStateForPlacement(this, defaultBlockState, ctx);
    }

    @SuppressWarnings("ConstantValue")
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public BlockState rotate(BlockState blockState, Rotation rotation)
    {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState blockState, Mirror mirror)
    {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }
}
