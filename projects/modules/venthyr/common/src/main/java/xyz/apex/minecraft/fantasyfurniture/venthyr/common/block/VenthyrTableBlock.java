package xyz.apex.minecraft.fantasyfurniture.venthyr.common.block;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentRegistrar;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.lib.helper.VoxelShapeHelper;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.TableComponent;

public final class VenthyrTableBlock extends BaseBlockComponentHolder
{
    public static final VoxelShape SHAPE_TOP = box(0D, 13D, 0D, 16D, 16D, 16D);
    public static final VoxelShape SHAPE_LEG_FL = box(1D, 0D, 1D, 3D, 13D, 3D);
    public static final VoxelShape SHAPE_LEG_FR = box(13D, 0D, 1D, 15D, 13D, 3D);
    public static final VoxelShape SHAPE_LEG_BL = box(1D, 0D, 13D, 3D, 13D, 15D);
    public static final VoxelShape SHAPE_LEG_BR = box(13D, 0D, 13D, 15D, 13D, 15D);

    public static final VoxelShape SHAPE_TWO_LEGS = VoxelShapeHelper.combine(
            SHAPE_TOP,
            SHAPE_LEG_BL,
            SHAPE_LEG_BR
    );

    public static final VoxelShape SHAPE_TWO_LEGS_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE_TWO_LEGS, Direction.NORTH);
    public static final VoxelShape SHAPE_TWO_LEGS_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE_TWO_LEGS, Direction.EAST);
    public static final VoxelShape SHAPE_TWO_LEGS_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE_TWO_LEGS, Direction.SOUTH);
    public static final VoxelShape SHAPE_TWO_LEGS_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE_TWO_LEGS, Direction.WEST);

    public static final VoxelShape SHAPE_ONE_LEG = VoxelShapeHelper.combine(
            SHAPE_TOP,
            SHAPE_LEG_BL
    );

    public static final VoxelShape SHAPE_ONE_LEG_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE_ONE_LEG, Direction.NORTH);
    public static final VoxelShape SHAPE_ONE_LEG_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE_ONE_LEG, Direction.EAST);
    public static final VoxelShape SHAPE_ONE_LEG_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE_ONE_LEG, Direction.SOUTH);
    public static final VoxelShape SHAPE_ONE_LEG_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE_ONE_LEG, Direction.WEST);

    public static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            SHAPE_TOP,
            SHAPE_LEG_FL,
            SHAPE_LEG_FR,
            SHAPE_LEG_BL,
            SHAPE_LEG_BR
    );

    public VenthyrTableBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar)
    {
        registrar.register(BlockComponentTypes.WATERLOGGED);
        registrar.register(TableComponent.COMPONENT_TYPE);
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState blockState)
    {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        var connectedDirections = Lists.<Direction>newArrayList();

        for(var direction : Direction.Plane.HORIZONTAL)
        {
            if(blockState.getValue(TableComponent.PROPERTY_BY_DIRECTION.get(direction)))
                connectedDirections.add(direction);
        }

        if(connectedDirections.size() >= 3 || (connectedDirections.size() == 2 && connectedDirections.get(0) == connectedDirections.get(1).getOpposite()))
            return SHAPE_TOP;
        else if(connectedDirections.size() == 1)
        {
            return switch(connectedDirections.get(0)) {
                default -> SHAPE_TWO_LEGS_NORTH;
                case EAST -> SHAPE_TWO_LEGS_EAST;
                case SOUTH -> SHAPE_TWO_LEGS_SOUTH;
                case WEST -> SHAPE_TWO_LEGS_WEST;
            };
        }
        else if(connectedDirections.size() == 2)
        {
            var flag = connectedDirections.get(0).getClockWise() == connectedDirections.get(1);

            return switch(flag ? connectedDirections.get(0) : connectedDirections.get(1)) {
                default -> SHAPE_ONE_LEG_NORTH;
                case EAST -> SHAPE_ONE_LEG_EAST;
                case SOUTH -> SHAPE_ONE_LEG_SOUTH;
                case WEST -> SHAPE_ONE_LEG_WEST;
            };
        }

        return SHAPE;
    }
}
