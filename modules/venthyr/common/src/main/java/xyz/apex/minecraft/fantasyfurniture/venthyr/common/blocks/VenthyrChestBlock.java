package xyz.apex.minecraft.fantasyfurniture.venthyr.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseEntityBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrVoxelShapes;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.blocks.entity.VenthyrChestBlockEntity;

import java.util.function.Consumer;

public final class VenthyrChestBlock extends BaseEntityBlockComponentHolder<VenthyrChestBlockEntity>
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> FantasyFurniture.getCorrectedShape(blockState, VenthyrVoxelShapes.CHEST));

    public VenthyrChestBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    protected BlockEntityType<VenthyrChestBlockEntity> getBlockEntityType()
    {
        return VenthyrFurnitureSet.BlockEntityTypes.CHEST.get();
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}
