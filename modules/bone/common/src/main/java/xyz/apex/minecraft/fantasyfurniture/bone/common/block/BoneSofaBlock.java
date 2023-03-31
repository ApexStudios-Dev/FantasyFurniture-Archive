package xyz.apex.minecraft.fantasyfurniture.bone.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneVoxelShapes;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ModBlockStateProperties;

import java.util.function.Consumer;

public final class BoneSofaBlock extends BaseBlockComponentHolder
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> FantasyFurniture.getCorrectedShape(blockState, switch(blockState.getValue(ModBlockStateProperties.SOFA_TYPE)) {
        case LEFT -> BoneVoxelShapes.SOFA_LEFT;
        case RIGHT -> BoneVoxelShapes.SOFA_RIGHT;
        case SINGLE -> BoneVoxelShapes.SOFA_SINGLE;
        case CENTER -> BoneVoxelShapes.SOFA_CENTER;
        case CORNER -> BoneVoxelShapes.SOFA_CORNER;
    }));

    public BoneSofaBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}