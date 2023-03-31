package xyz.apex.minecraft.fantasyfurniture.common.block.components;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.component.block.BaseBlockComponent;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentType;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

import java.util.function.Supplier;

public final class LightBlockComponent extends BaseBlockComponent
{
    public static final BlockComponentType<LightBlockComponent> COMPONENT_TYPE = BlockComponentType.register(
            new ResourceLocation(FantasyFurniture.ID, "light"),
            LightBlockComponent::new
    );

    private boolean placeOnWalls = false;
    private boolean placeOnFloor = true;
    private boolean placeOnCeilings = true;

    private Supplier<ParticleOptions> flameParticle = () -> ParticleTypes.FLAME;

    @ApiStatus.Internal // public cause reflection
    public LightBlockComponent(BlockComponentHolder block)
    {
        super(block);
    }

    public LightBlockComponent setPlaceOnWalls(boolean placeOnWalls)
    {
        this.placeOnWalls = placeOnWalls;
        return this;
    }

    public LightBlockComponent setPlaceOnFloor(boolean placeOnFloor)
    {
        this.placeOnFloor = placeOnFloor;
        return this;
    }

    public LightBlockComponent setPlaceOnCeilings(boolean placeOnCeilings)
    {
        this.placeOnCeilings = placeOnCeilings;
        return this;
    }

    public LightBlockComponent setFlameParticle(Supplier<ParticleOptions> flameParticle)
    {
        this.flameParticle = flameParticle;
        return this;
    }

    @Override
    public void onRegistered(BlockComponentHolder.Registrar registrar)
    {
        if(placeOnWalls || placeOnFloor) registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx, BlockState blockState)
    {
        if(placeOnWalls)
        {
            var level = ctx.getLevel();
            var pos = ctx.getClickedPos();

            for(var direction : ctx.getNearestLookingDirections())
            {
                var facing = direction.getOpposite();

                if(facing.getAxis().isHorizontal())
                {
                    var modified = blockState.setValue(HorizontalFacingBlockComponent.FACING, facing);
                    if(modified.canSurvive(level, pos)) return modified.setValue(HorizontalFacingBlockComponent.FACING, facing);
                }
            }

            if(!placeOnFloor && !placeOnCeilings) return null;
        }

        return blockState;
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader level, BlockPos pos)
    {
        if(placeOnWalls)
        {
            var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
            var wallDirection = facing.getOpposite();
            var wallPos = pos.relative(wallDirection);
            var wallBlockState = level.getBlockState(wallPos);
            if(wallBlockState.isFaceSturdy(level, wallPos, wallDirection)) return true;
        }

        if(placeOnCeilings && Block.canSupportCenter(level, pos.above(), Direction.DOWN)) return true;
        return placeOnFloor;
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos)
    {
        if(placeOnWalls && direction.getOpposite() == blockState.getValue(HorizontalFacingBlockComponent.FACING) && !blockState.canSurvive(level, currentPos)) return Blocks.AIR.defaultBlockState();
        if(placeOnCeilings && direction == Direction.UP && !canSurvive(blockState, level, currentPos)) return Blocks.AIR.defaultBlockState();
        return blockState;
    }
}
