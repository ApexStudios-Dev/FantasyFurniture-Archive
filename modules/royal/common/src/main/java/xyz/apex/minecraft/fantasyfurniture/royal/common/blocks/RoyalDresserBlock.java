package xyz.apex.minecraft.fantasyfurniture.royal.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseEntityBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.DyeableBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalVoxelShapes;
import xyz.apex.minecraft.fantasyfurniture.royal.common.blocks.entity.RoyalDresserBlockEntity;

import java.util.function.Consumer;

public final class RoyalDresserBlock extends BaseEntityBlockComponentHolder<RoyalDresserBlockEntity>
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> FantasyFurniture.getCorrectedShape(blockState, RoyalVoxelShapes.DRESSER));

    public RoyalDresserBlock(Consumer<Registrar> registrarConsumer, Properties properties)
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
    protected BlockEntityType<RoyalDresserBlockEntity> getBlockEntityType()
    {
        return RoyalFurnitureSet.BlockEntityTypes.DRESSER.get();
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}
