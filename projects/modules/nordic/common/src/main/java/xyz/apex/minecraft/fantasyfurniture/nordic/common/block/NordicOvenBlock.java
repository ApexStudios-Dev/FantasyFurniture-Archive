package xyz.apex.minecraft.fantasyfurniture.nordic.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.apexcore.common.lib.helper.VoxelShapeHelper;
import xyz.apex.minecraft.fantasyfurniture.common.block.FurnaceBlock;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.FurnaceBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;

public final class NordicOvenBlock extends FurnaceBlock
{
    public static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(0D, 0D, 0D, 16D, 1D, 16D),
            box(0D, 1D, 1D, 16D, 9D, 16D),
            box(0D, 9D, 0D, 16D, 10D, 16D),
            box(1D, 10D, 3D, 15D, 14D, 16D),
            box(2D, 14D, 3D, 14D, 16D, 16D)
    );

    public static final VoxelShape SHAPE_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.NORTH);
    public static final VoxelShape SHAPE_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.EAST);
    public static final VoxelShape SHAPE_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.SOUTH);
    public static final VoxelShape SHAPE_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.WEST);

    public NordicOvenBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected BlockEntityType<?> getBlockEntityType()
    {
        return NordicFurnitureSet.OVEN_BLOCK_ENTITY.value();
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return switch(HorizontalFacingBlockComponent.getFacing(blockState)) {
            default -> SHAPE_NORTH;
            case EAST -> SHAPE_EAST;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
        };
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random)
    {
        if(!blockState.getValue(FurnaceBlockComponent.LIT)) return;

        var x = pos.getX() + .5D;
        var y = pos.getY();
        var z = pos.getZ() + .5D;

        if(random.nextDouble() < .1D) level.playLocalSound(x, y, z, SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS, 1F, 1F, false);

        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
        var axis = facing.getAxis();
        var h = random.nextDouble() * .6D - .3D;
        var xOff = axis == Direction.Axis.X ? facing.getStepX() * .52D : h;
        var yOff = random.nextDouble() * 9D / 16D;
        var zOff = axis == Direction.Axis.Z ? facing.getStepZ() * .52D : h;

        level.addParticle(ParticleTypes.SMOKE, x + xOff, y + yOff, z + zOff, 0D, 0D, 0D);
    }
}
