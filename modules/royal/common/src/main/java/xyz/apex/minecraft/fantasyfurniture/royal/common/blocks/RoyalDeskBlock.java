package xyz.apex.minecraft.fantasyfurniture.royal.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseEntityBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.DyeableBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalVoxelShapes;
import xyz.apex.minecraft.fantasyfurniture.royal.common.blocks.entity.RoyalDeskBlockEntity;

import java.util.function.Consumer;

public final class RoyalDeskBlock extends BaseEntityBlockComponentHolder<RoyalDeskBlockEntity>
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> {
        VoxelShape shape;

        if(RoyalFurnitureSet.Blocks.DESK_RIGHT.hasBlockState(blockState)) shape = RoyalVoxelShapes.DESK_RIGHT;
        else if(RoyalFurnitureSet.Blocks.DESK_LEFT.hasBlockState(blockState)) shape = RoyalVoxelShapes.DESK_LEFT;
        else return Shapes.block();

        return FantasyFurniture.getCorrectedShape(blockState, shape);
    });

    public RoyalDeskBlock(Consumer<Registrar> registrarConsumer, Properties properties)
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
    protected BlockEntityType<RoyalDeskBlockEntity> getBlockEntityType()
    {
        return RoyalFurnitureSet.BlockEntityTypes.DESK.get();
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}
