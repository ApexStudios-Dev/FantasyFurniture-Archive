package xyz.apex.minecraft.fantasyfurniture.royal.common.blocks;

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
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.OvenBlock;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.DyeableBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalVoxelShapes;
import xyz.apex.minecraft.fantasyfurniture.royal.common.blocks.entity.RoyalOvenBlockEntity;

import java.util.function.Consumer;

public final class RoyalOvenBlock extends OvenBlock<RoyalOvenBlockEntity>
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> FantasyFurniture.getCorrectedShape(blockState, RoyalVoxelShapes.OVEN));

    public RoyalOvenBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    public void registerComponents(Registrar registrar)
    {
        super.registerComponents(registrar);
        registrar.register(DyeableBlockComponent.COMPONENT_TYPE);
    }

    @Override
    protected BlockEntityType<RoyalOvenBlockEntity> getBlockEntityType()
    {
        return RoyalFurnitureSet.BlockEntityTypes.OVEN.get();
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
    {
        if(!blockState.getValue(LIT)) return;

        var multiBlockComponent = getComponent(BlockComponentTypes.MULTI_BLOCK);
        if(multiBlockComponent != null && !multiBlockComponent.getMultiBlockType().isOrigin(blockState)) return;

        var x = pos.getX() + .5D;
        var y = pos.getY();
        var z = pos.getZ() + .5D;

        if(rng.nextDouble() < .1D) level.playLocalSound(x, y, z, SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS, 1F, 1F, false);

        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
        var axis = facing.getAxis();
        var h = rng.nextDouble() * .6D - .3D;
        var xOff = axis == Direction.Axis.X ? facing.getStepX() * .52D : h;
        var yOff = rng.nextDouble() * 9D / 16D;
        var zOff = axis == Direction.Axis.Z ? facing.getStepZ() * .52D : h;

        level.addParticle(ParticleTypes.SMOKE, x + xOff, y + yOff, z + zOff, 0D, 0D, 0D);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}
