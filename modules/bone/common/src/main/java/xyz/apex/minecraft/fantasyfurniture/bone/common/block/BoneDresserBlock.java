package xyz.apex.minecraft.fantasyfurniture.bone.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseEntityBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneVoxelShapes;
import xyz.apex.minecraft.fantasyfurniture.bone.common.block.entity.BoneDresserBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

import java.util.function.Consumer;

public final class BoneDresserBlock extends BaseEntityBlockComponentHolder<BoneDresserBlockEntity>
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> FantasyFurniture.getCorrectedShape(blockState, BoneVoxelShapes.DRESSER));

    public BoneDresserBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    protected BlockEntityType<BoneDresserBlockEntity> getBlockEntityType()
    {
        return BoneFurnitureSet.BlockEntityTypes.DRESSER.get();
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}
