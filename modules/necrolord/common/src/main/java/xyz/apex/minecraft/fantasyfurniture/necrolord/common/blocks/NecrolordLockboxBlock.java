package xyz.apex.minecraft.fantasyfurniture.necrolord.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseEntityBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.necrolord.common.NecrolordFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.necrolord.common.NecrolordVoxelShapes;
import xyz.apex.minecraft.fantasyfurniture.necrolord.common.blocks.entity.NecrolordLockboxBlockEntity;

import java.util.function.Consumer;

public final class NecrolordLockboxBlock extends BaseEntityBlockComponentHolder<NecrolordLockboxBlockEntity>
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> FantasyFurniture.getCorrectedShape(blockState, NecrolordVoxelShapes.LOCKBOX));

    public NecrolordLockboxBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    protected BlockEntityType<NecrolordLockboxBlockEntity> getBlockEntityType()
    {
        return NecrolordFurnitureSet.BlockEntityTypes.LOCKBOX.get();
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}
