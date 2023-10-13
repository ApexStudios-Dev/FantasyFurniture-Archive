package xyz.apex.minecraft.fantasyfurniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentRegistrar;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.apexcore.common.lib.helper.VoxelShapeHelper;
import xyz.apex.minecraft.fantasyfurniture.common.menu.FurnitureStationMenu;

public final class FurnitureStationBlock extends BaseBlockComponentHolder
{
    public static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(1D, 0D, 1D, 3D, 14D, 3D),
            box(1D, 0D, 13D, 3D, 14D, 15D),
            box(13D, 0D, 13D, 15D, 14D, 15D),
            box(13D, 0D, 1D, 15D, 14D, 3D),
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(2D, 2D, 2D, 14D, 14D, 14D)
    );

    public static final VoxelShape SHAPE_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.NORTH);
    public static final VoxelShape SHAPE_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.SOUTH);
    public static final VoxelShape SHAPE_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.EAST);
    public static final VoxelShape SHAPE_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.WEST);

    public FurnitureStationBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar)
    {
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
        registrar.register(BlockComponentTypes.WATERLOGGED);
        registrar.register(BlockComponentTypes.MENU_PROVIDER, component -> component
                .withMenuConstructor((syncId, inventory, level, pos) -> FurnitureStationMenu.create(syncId, inventory, level instanceof Level lvl ? ContainerLevelAccess.create(lvl, pos) : ContainerLevelAccess.NULL))
                .withExtraData((level, pos, blockState, buffer) -> buffer.writeBlockPos(pos))
        );
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return switch(HorizontalFacingBlockComponent.getFacing(blockState)) {
            default -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
        };
    }
}
