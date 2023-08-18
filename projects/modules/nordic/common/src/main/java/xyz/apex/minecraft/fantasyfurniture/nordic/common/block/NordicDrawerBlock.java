package xyz.apex.minecraft.fantasyfurniture.nordic.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentRegistrar;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.types.BlockEntityComponentTypes;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.apexcore.common.lib.helper.InteractionResultHelper;
import xyz.apex.minecraft.apexcore.common.lib.helper.VoxelShapeHelper;
import xyz.apex.minecraft.apexcore.common.lib.hook.MenuHooks;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;

public final class NordicDrawerBlock extends BaseBlockComponentHolder
{
    public static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(1D, 0D, 1D, 15D, 13D, 15D),
            box(0D, 13D, 0D, 16D, 16D, 16D)
    );

    public static final VoxelShape SHAPE_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.NORTH);
    public static final VoxelShape SHAPE_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.EAST);
    public static final VoxelShape SHAPE_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.SOUTH);
    public static final VoxelShape SHAPE_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.WEST);

    public NordicDrawerBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar)
    {
        registrar.register(BlockComponentTypes.WATERLOGGED);
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
    }

    @Override
    protected BlockEntityType<?> getBlockEntityType()
    {
        return NordicFurnitureSet.SMALL_CONTAINER_BLOCK_ENTITY.value();
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return switch(blockState.getValue(HorizontalFacingBlockComponent.FACING)) {
            default -> SHAPE_NORTH;
            case EAST -> SHAPE_EAST;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
        };
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        var result = super.use(blockState, level, pos, player, hand, hit);

        if(result.consumesAction())
            return result;

        var blockEntity = NordicFurnitureSet.SMALL_CONTAINER_BLOCK_ENTITY.getBlockEntity(level, pos);

        if(blockEntity == null)
            return InteractionResultHelper.BlockUse.noActionTaken();

        MenuHooks.get().openMenu(player, blockEntity.getRequiredComponent(BlockEntityComponentTypes.NAMEABLE).getName(), blockEntity, buffer -> { });
        return InteractionResultHelper.BlockUse.succeedAndSwingArmBothSides(level.isClientSide);
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos)
    {
        var blockEntity = NordicFurnitureSet.SMALL_CONTAINER_BLOCK_ENTITY.getBlockEntity(level, pos);
        return blockEntity == null ? null : MenuHooks.get().createMenuProvider(blockEntity.getRequiredComponent(BlockEntityComponentTypes.NAMEABLE).getName(), blockEntity, buffer -> { });
    }
}
